package StateControllersTests

import CtrlCommands.DieCommands.{DeadCommand, DyingCommand}
import Gameworld.StateMachine.DyingStates.{DeadState, DyingState}
import Gameworld.StateMachine.StateControllers.DieStateCtrl
import Gameworld.StateMachine.Traits.FSMController
import Model.Responses.ValidTransition
import org.junit.runner.RunWith
import org.scalamock.specs2.MockContext
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import org.specs2.specification.Scope
import play.api.libs.json.JsValue

/**
 * Created with IntelliJ IDEA.
 * User: marchaubenstock
 * Date: 20/10/2014
 * Time: 22:20
 */
@RunWith(classOf[JUnitRunner])
class DyingStateCtrlSpec extends Specification {

  trait withDyingStateCtrl extends Scope with MockContext {
    val ctrl = new DieStateCtrl()
  }

  "DyingStateCtrl" should {

    "start with dying state" in new withDyingStateCtrl {
      ctrl.mCurrentState must beAnInstanceOf[DyingState]
    }

    "return dying state controller on any action " in new withDyingStateCtrl {
      ctrl.DetermineControllerFrom(DyingCommand.retrieve)._2 must beAnInstanceOf[DieStateCtrl]
      ctrl.DetermineControllerFrom(DeadCommand.retrieve)._2 must beAnInstanceOf[DieStateCtrl]
    }

    "dying to dead is a valid action chain" in new withDyingStateCtrl {
      val dyingState = mock[DyingState]
      val deadState = mock[DeadState]
      val playerAction = DyingCommand.retrieve.playerAction

      (dyingState.DetermineNextStateAndApply _).expects(playerAction.action).returning(ValidTransition.retrieve,deadState)

      ctrl.Apply(playerAction,dyingState)
    }



  }



}
