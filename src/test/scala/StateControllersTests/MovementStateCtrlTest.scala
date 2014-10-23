package StateControllersTests

import CtrlCommands.DieCommands.DyingCommand
import CtrlCommands.JumpCommands.JumpCommand
import CtrlCommands.MovementCommands._
import Gameworld.StateMachine.MovementStates._
import Gameworld.StateMachine.StateControllers._
import Model.{InvalidTransition, ValidTransition}
import org.junit.runner.RunWith
import org.scalamock.specs2.MockFactory
import org.specs2.mutable._
import org.specs2.runner.JUnitRunner
import org.specs2.specification.Scope
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
      ctrl.DetermineControllerFrom(DyingCommand.retrieve)._2 must beAnInstanceOf[DieStateCtrl]
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
    "switch state on attacking" in new withMovementStateCtrl {
      val idleState = mock[IdleState]
      val attackingState = mock[AttackingState]
      val action = (MoveCommand.retrieve \ "playerAction").asOpt[JsValue].get

      (idleState.DetermineNextStateAndApply _).expects(action).returning(ValidTransition.retrieve,attackingState)

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

    "idle to attack is valid" in new withMovementStateCtrl {

      val idleState = mock[IdleState]
      val attackingState = mock[AttackingState]

      val action = (AttackingCommand.retrieve \ "playerAction").asOpt[JsValue].get

      (idleState.DetermineNextStateAndApply _).expects(action).returning(ValidTransition.retrieve,attackingState)

      ctrl.Apply(action,idleState)

    }

    "idle to attack recurring is valid" in new withMovementStateCtrl {

      val idleState = mock[IdleState]
      val attackingState = mock[AttackingState]

      val action = (AttackingCommand.retrieve \ "playerAction").asOpt[JsValue].get

      (idleState.DetermineNextStateAndApply _).expects(action).returning(ValidTransition.retrieve,attackingState)
      (attackingState.DetermineNextStateAndApply _).expects(action).returning(ValidTransition.retrieve,attackingState)

      ctrl.Apply(action,idleState)
      ctrl.Apply(action,attackingState)

    }

    "idle to move to attack is valid" in new withMovementStateCtrl {

      val idleState = mock[IdleState]
      val movingState = mock[MovingState]
      val attackingState = mock[AttackingState]

      val action = (AttackingCommand.retrieve \ "playerAction").asOpt[JsValue].get
      val action2 = (MoveCommand.retrieve \ "playerAction").asOpt[JsValue].get

      (idleState.DetermineNextStateAndApply _).expects(action2).returning(ValidTransition.retrieve,movingState)
      (movingState.DetermineNextStateAndApply _).expects(action).returning(ValidTransition.retrieve,attackingState)

      ctrl.Apply(action2,idleState)
      ctrl.Apply(action,movingState)

    }

    "idle to dodge is valid, dodge to attack is invalid" in new withMovementStateCtrl {

      val idleState = mock[IdleState]
      val dodgingState = mock[MovingState]
      val attackingState = mock[AttackingState]

      val action = (AttackingCommand.retrieve \ "playerAction").asOpt[JsValue].get
      val action2 = (DodgeCommand.retrieve \ "playerAction").asOpt[JsValue].get

      (idleState.DetermineNextStateAndApply _).expects(action2).returning(ValidTransition.retrieve,dodgingState)
      (dodgingState.DetermineNextStateAndApply _).expects(action).returning(InvalidTransition.retrieve,idleState)

      ctrl.Apply(action2,idleState)
      ctrl.Apply(action,dodgingState)

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

    "attack to dodge is an invalid transition" in new withMovementStateCtrl {

      val idleState = mock[IdleState]
      val attackingState = mock[AttackingState]

      val action = (DodgeCommand.retrieve \ "playerAction").asOpt[JsValue].get

      (attackingState.DetermineNextStateAndApply _).expects(action).returning(InvalidTransition.retrieve,idleState)

      ctrl.Apply(action,attackingState)

    }

    "attack to combo is valid" in new withMovementStateCtrl {

      val attackingState = mock[AttackingState]
      val comboState = mock[ComboAttackState]

      val comboAttack = (AttackingCommand.retrieve \ "playerAction").asOpt[JsValue].get

      (attackingState.DetermineNextStateAndApply _).expects(comboAttack).returning(ValidTransition.retrieve,comboState)

      ctrl.Apply(comboAttack,attackingState)

    }


    "combo to endOfCombo is valid" in new withMovementStateCtrl {

      val endOfCombo = mock[EndOfComboState]
      val comboState = mock[ComboAttackState]

      val comboAttack = (AttackingCommand.retrieve \ "playerAction").asOpt[JsValue].get

      (comboState.DetermineNextStateAndApply _).expects(comboAttack).returning(ValidTransition.retrieve,endOfCombo)

      ctrl.Apply(comboAttack,comboState)

    }

    "endOfCombo to idle is valid" in new withMovementStateCtrl {

      val endOfCombo = mock[EndOfComboState]
      val idleState = mock[IdleState]

      val idleCommand = (IdleCommand.retrieve \ "playerAction").asOpt[JsValue].get

      (endOfCombo.DetermineNextStateAndApply _).expects(idleCommand).returning(ValidTransition.retrieve,idleState)

      ctrl.Apply(idleCommand,endOfCombo)

    }

    "endOfCombo to move is invalid" in new withMovementStateCtrl {

      val endOfCombo = mock[EndOfComboState]
      val idleState = mock[IdleState]

      val moveCommand = (MoveCommand.retrieve \ "playerAction").asOpt[JsValue].get

      (endOfCombo.DetermineNextStateAndApply _).expects(moveCommand).returning(InvalidTransition.retrieve,idleState)

      ctrl.Apply(moveCommand,endOfCombo)

    }

    "endOfCombo to move is invalid" in new withMovementStateCtrl {

      val endOfCombo = mock[EndOfComboState]
      val idleState = mock[IdleState]

      val moveCommand = (MoveCommand.retrieve \ "playerAction").asOpt[JsValue].get

      (endOfCombo.DetermineNextStateAndApply _).expects(moveCommand).returning(InvalidTransition.retrieve,idleState)

      ctrl.Apply(moveCommand,endOfCombo)

    }

    "endOfCombo to dodge is invalid" in new withMovementStateCtrl {

      val endOfCombo = mock[EndOfComboState]
      val idleState = new IdleState()

      val dodgeCommand = (DodgeCommand.retrieve \ "playerAction").asOpt[JsValue].get

      (endOfCombo.DetermineNextStateAndApply _).expects(dodgeCommand).returning(InvalidTransition.retrieve,idleState)

      ctrl.Apply(dodgeCommand,endOfCombo)

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

    "idle to dodge is valid; dodge to move is not" in new withMovementStateCtrl {

      val idleState = mock[IdleState]
      val moveState = mock[MovingState]
      val dodgeState = mock[DodgeState]

      val action = (MoveCommand.retrieve \ "playerAction").asOpt[JsValue].get
      val action2 = (DodgeCommand.retrieve \ "playerAction").asOpt[JsValue].get

      (idleState.DetermineNextStateAndApply _).expects(action2).returning(ValidTransition.retrieve,dodgeState)
      (dodgeState.DetermineNextStateAndApply _).expects(action).returning(InvalidTransition.retrieve,idleState)

      ctrl.Apply(action2,idleState)
      ctrl.Apply(action,dodgeState)

    }





  }

}
