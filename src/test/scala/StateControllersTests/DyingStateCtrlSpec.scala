package StateControllersTests

import CtrlCommands.DieCommands.{DeadCommand, DyingCommand}
import Gameworld.StateMachine.DyingStates.{DeadState, DyingState}
import Gameworld.StateMachine.StateControllers.DieStateCtrl
import Model.ValidTransition
import org.junit.runner.RunWith
import org.scalamock.specs2.MockFactory
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
class DyingStateCtrlSpec extends Specification with MockFactory  {

  trait withDyingStateCtrl extends Scope {
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
      val action = (DyingCommand.retrieve \ "playerAction").asOpt[JsValue].get

      (dyingState.DetermineNextStateAndApply _).expects(action).returning(ValidTransition.retrieve,deadState)

      ctrl.Apply(action,dyingState)
    }



  }



}
