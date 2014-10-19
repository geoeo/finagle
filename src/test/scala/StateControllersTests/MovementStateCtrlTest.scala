package StateControllersTests

import CtrlCommands.DieCommands.DieCommand
import CtrlCommands.JumpCommands.JumpCommand
import CtrlCommands.MovementCommands.{IdleCommand, DodgeCommand, MoveCommand}
import Gameworld.StateMachine.MovementStates.{MovingState, DodgeState, IdleState}
import Gameworld.StateMachine.StateControllers.{DieStateCtrl, JumpStateCtrl, MovementStateCtrl}
import Model.{InvalidTransition, ValidTransition}
import org.junit.runner.RunWith

import org.specs2.mutable._
import org.specs2.runner.JUnitRunner
import org.specs2.specification.Scope
import org.scalamock.specs2.MockFactory
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


    "return MoveStateCtrl on dodge" in new withMovementStateCtrl {
      ctrl.DetermineControllerFrom(DodgeCommand.retrieve)._2 must beAnInstanceOf[MovementStateCtrl]
    }

    "stay in idle on idle" in new withMovementStateCtrl {

      val idleState = mock[IdleState]
      val action = (IdleCommand.retrieve \ "playerAction").asOpt[JsValue].get

      (idleState.DetermineNextStateAndApply _).expects(action).returning(ValidTransition.retrieve,idleState)

      ctrl.Apply(action,idleState)

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

    "idle to move to dodge is a valid chain" in new withMovementStateCtrl {

      val idleState = mock[IdleState]
      val moveState = mock[MovingState]
      val dodgeState = mock[DodgeState]

      val action = (MoveCommand.retrieve \ "playerAction").asOpt[JsValue].get
      val action2 = (DodgeCommand.retrieve \ "playerAction").asOpt[JsValue].get

      (idleState.DetermineNextStateAndApply _).expects(action).returning(ValidTransition.retrieve,moveState)
      (moveState.DetermineNextStateAndApply _).expects(action2).returning(ValidTransition.retrieve,dodgeState)

      ctrl.Apply(action,idleState)
      ctrl.Apply(action2,moveState)

    }

    "idle to move to move again is a valid chain" in new withMovementStateCtrl {

      val idleState = mock[IdleState]
      val moveState = mock[MovingState]

      val action = (MoveCommand.retrieve \ "playerAction").asOpt[JsValue].get
      val action2 = (DodgeCommand.retrieve \ "playerAction").asOpt[JsValue].get

      (idleState.DetermineNextStateAndApply _).expects(action).returning(ValidTransition.retrieve,moveState)
      (moveState.DetermineNextStateAndApply _).expects(action).returning(ValidTransition.retrieve,moveState)

      ctrl.Apply(action,idleState)
      ctrl.Apply(action,moveState)

    }

    "idle to dodge is an invalid transition" in new withMovementStateCtrl {

      val idleState = mock[IdleState]

      val action = (DodgeCommand.retrieve \ "playerAction").asOpt[JsValue].get

      (idleState.DetermineNextStateAndApply _).expects(action).returning(InvalidTransition.retrieve,idleState)

      ctrl.Apply(action,idleState)

    }


    "idle to move to dodge to idle is a valid chain" in new withMovementStateCtrl {

      val idleState = mock[IdleState]
      val moveState = mock[MovingState]
      val dodgeState = mock[DodgeState]

      val action = (MoveCommand.retrieve \ "playerAction").asOpt[JsValue].get
      val action2 = (DodgeCommand.retrieve \ "playerAction").asOpt[JsValue].get
      val action3  = (IdleCommand.retrieve \ "playerAction").asOpt[JsValue].get

      (idleState.DetermineNextStateAndApply _).expects(action).returning(ValidTransition.retrieve,moveState)
      (moveState.DetermineNextStateAndApply _).expects(action2).returning(ValidTransition.retrieve,dodgeState)
      (dodgeState.DetermineNextStateAndApply _).expects(action3).returning(ValidTransition.retrieve,idleState)

      ctrl.Apply(action,idleState)
      ctrl.Apply(action2,moveState)
      ctrl.Apply(action3,dodgeState)

    }





  }

}
