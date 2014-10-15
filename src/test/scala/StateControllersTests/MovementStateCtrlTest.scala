package StateControllersTests

import Gameworld.StateMachine.MovementStates.{MovingState, DodgeState, IdleState}
import Gameworld.StateMachine.StateControllers.{DieStateCtrl, JumpStateCtrl, MovementStateCtrl}
import CtrlCommands.JumpCommand
import Model.ValidTransition
import org.junit.runner.RunWith

import org.specs2.mutable._
import org.specs2.runner.JUnitRunner
import org.specs2.specification.Scope
import org.scalamock.specs2.MockFactory
import CtrlCommands._
import play.api.libs.json.JsValue

/**
 * Created with IntelliJ IDEA.
 * User: marchaubenstock
 * Date: 12/10/2014
 * Time: 17:53
 */
@RunWith(classOf[JUnitRunner])
class MovementStateCtrlSpec extends Specification with MockFactory {

  trait withMovementStateCtrl extends Scope {
    val ctrl = new MovementStateCtrl()
  }

  "MovementStateCtrl" should {

    "start with Idle state" in new withMovementStateCtrl {
      ctrl.mCurrentState must beAnInstanceOf[IdleState]
    }

    "return JumpStateCtrl" in new withMovementStateCtrl {
      ctrl.DetermineControllerFrom(JumpCommand.retrieve)._2 must beAnInstanceOf[JumpStateCtrl]
    }

    "return DieStateCtrl" in new withMovementStateCtrl {
      ctrl.DetermineControllerFrom(DieCommand.retrieve)._2 must beAnInstanceOf[DieStateCtrl]
    }

    "switch state on dodging" in new withMovementStateCtrl {
      val idleState = mock[IdleState]
      val dodgeState = mock[DodgeState]
      val action = (DodgeCommand.retrieve \ "playerAction").asOpt[JsValue].get

      (idleState.DetermineNextStateAndApply _).expects(action).returning(ValidTransition.retrieve,dodgeState)

      ctrl.Apply(action,idleState)
    }

    "switch state on moving" in new withMovementStateCtrl {
      val idleState = mock[IdleState]
      val moveState = mock[MovingState]
      val action = (MoveCommand.retrieve \ "playerAction").asOpt[JsValue].get

      (idleState.DetermineNextStateAndApply _).expects(action).returning(ValidTransition.retrieve,moveState)

      ctrl.Apply(action,idleState)
    }

    "return MoveStateCtrl on dodge" in new withMovementStateCtrl {
      ctrl.DetermineControllerFrom(DodgeCommand.retrieve)._2 must beAnInstanceOf[MovementStateCtrl]
    }




  }

}
