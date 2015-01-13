package StateControllersTests

import CtrlCommands.DieCommands.DyingCommand
import CtrlCommands.JumpCommands.JumpCommand
import CtrlCommands.MovementCommands._
import Gameworld.StateMachine.JumpingStates.JumpingState
import Gameworld.StateMachine.MovementStates._
import Gameworld.StateMachine.StateControllers.AbstractController.AFSMController
import Gameworld.StateMachine.StateControllers._
import Model.Responses.{ValidTransition, InvalidTransition}
import org.junit.runner.RunWith
import org.scalamock.specs2.MockContext
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
class MovementStateCtrlSpec extends Specification {

  trait withMovementStateCtrl extends Scope with MockContext {
    val ctrl = new MovementStateCtrl()
  }

  "MovementStateCtrl" should {

    "start with Idle state" in new withMovementStateCtrl {
      ctrl.mCurrentState must beAnInstanceOf[IdleState]
    }

    "return JumpStateCtrl" in new withMovementStateCtrl {
      var currentCtrl = ctrl.DetermineControllerFrom(JumpCommand.retrieve)._2
      currentCtrl must beAnInstanceOf[JumpStateCtrl]
    }

    "return DieStateCtrl" in new withMovementStateCtrl {
      var currentCtrl = ctrl.DetermineControllerFrom(DyingCommand.retrieve)._2
      currentCtrl must haveClass[DieStateCtrl]
    }

    "return MoveStateCtrl on dodge" in new withMovementStateCtrl {
      ctrl.DetermineControllerFrom(DodgeCommand.retrieve)._2 must haveClass[MovementStateCtrl]
    }

    "stay in idle on idle" in new withMovementStateCtrl {

      val idleState = mock[IdleState]
      val playerAction = IdleCommand.retrieve.playerAction

      (idleState.DetermineNextStateAndApply _).expects(playerAction.action).returning(ValidTransition.retrieve,idleState)

      ctrl.Apply(playerAction,idleState)

    }

    "switch state on dodging" in new withMovementStateCtrl {
      val idleState = mock[IdleState]
      val dodgeState = mock[DodgeState]
      val playerAction = DodgeCommand.retrieve.playerAction

      (idleState.DetermineNextStateAndApply _).expects(playerAction.action).returning(ValidTransition.retrieve,dodgeState)

      ctrl.Apply(playerAction,idleState)
    }

    "switch state on moving" in new withMovementStateCtrl {
      val idleState = mock[IdleState]
      val moveState = mock[MovingState]
      val playerAction = MoveCommand.retrieve.playerAction

      (idleState.DetermineNextStateAndApply _).expects(playerAction.action).returning(ValidTransition.retrieve,moveState)

      ctrl.Apply(playerAction,idleState)
    }
    "switch state on attacking" in new withMovementStateCtrl {
      val idleState = mock[IdleState]
      val attackingState = mock[AttackingState]
      val playerAction = MoveCommand.retrieve.playerAction

      (idleState.DetermineNextStateAndApply _).expects(playerAction.action).returning(ValidTransition.retrieve,attackingState)

      ctrl.Apply(playerAction,idleState)
    }

    "idle to move to dodge is a valid chain" in new withMovementStateCtrl {

      val idleState = mock[IdleState]
      val moveState = mock[MovingState]
      val dodgeState = mock[DodgeState]

      val playerAction = MoveCommand.retrieve.playerAction
      val playerAction2 = DodgeCommand.retrieve.playerAction

      (idleState.DetermineNextStateAndApply _).expects(playerAction.action).returning(ValidTransition.retrieve,moveState)
      (moveState.DetermineNextStateAndApply _).expects(playerAction2.action).returning(ValidTransition.retrieve,dodgeState)

      ctrl.Apply(playerAction,idleState)
      ctrl.Apply(playerAction2,moveState)

    }

    "idle to attack is valid" in new withMovementStateCtrl {

      val idleState = mock[IdleState]
      val attackingState = mock[AttackingState]

      val playerAction = AttackingCommand.retrieve.playerAction

      (idleState.DetermineNextStateAndApply _).expects(playerAction.action).returning(ValidTransition.retrieve,attackingState)

      ctrl.Apply(playerAction,idleState)

    }

    "idle to attack recurring is valid" in new withMovementStateCtrl {

      val idleState = mock[IdleState]
      val attackingState = mock[AttackingState]

      val playerAction = AttackingCommand.retrieve.playerAction

      (idleState.DetermineNextStateAndApply _).expects(playerAction.action).returning(ValidTransition.retrieve,attackingState)
      (attackingState.DetermineNextStateAndApply _).expects(playerAction.action).returning(ValidTransition.retrieve,attackingState)

      ctrl.Apply(playerAction,idleState)
      ctrl.Apply(playerAction,attackingState)

    }

    "idle to move to attack is valid" in new withMovementStateCtrl {

      val idleState = mock[IdleState]
      val movingState = mock[MovingState]
      val attackingState = mock[AttackingState]

      val playerAction = AttackingCommand.retrieve.playerAction
      val playerAction2 = MoveCommand.retrieve.playerAction

      (idleState.DetermineNextStateAndApply _).expects(playerAction2.action).returning(ValidTransition.retrieve,movingState)
      (movingState.DetermineNextStateAndApply _).expects(playerAction.action).returning(ValidTransition.retrieve,attackingState)

      ctrl.Apply(playerAction2,idleState)
      ctrl.Apply(playerAction,movingState)

    }

    "idle to dodge is valid, dodge to attack is invalid" in new withMovementStateCtrl {

      val idleState = mock[IdleState]
      val dodgingState = mock[MovingState]
      val attackingState = mock[AttackingState]

      val playerAction = AttackingCommand.retrieve.playerAction
      val playerAction2 = DodgeCommand.retrieve.playerAction

      (idleState.DetermineNextStateAndApply _).expects(playerAction2.action).returning(ValidTransition.retrieve,dodgingState)
      (dodgingState.DetermineNextStateAndApply _).expects(playerAction.action).returning(InvalidTransition.retrieve,idleState)

      ctrl.Apply(playerAction2,idleState)
      ctrl.Apply(playerAction,dodgingState)

    }

    "idle to move to move again is a valid chain" in new withMovementStateCtrl {

      val idleState = mock[IdleState]
      val moveState = mock[MovingState]

      val playerAction = MoveCommand.retrieve.playerAction

      (idleState.DetermineNextStateAndApply _).expects(playerAction.action).returning(ValidTransition.retrieve,moveState)
      (moveState.DetermineNextStateAndApply _).expects(playerAction.action).returning(ValidTransition.retrieve,moveState)

      ctrl.Apply(playerAction,idleState)
      ctrl.Apply(playerAction,moveState)

    }

    "idle to dodge is an invalid transition" in new withMovementStateCtrl {

      val idleState = mock[IdleState]

      val playerAction = DodgeCommand.retrieve.playerAction

      (idleState.DetermineNextStateAndApply _).expects(playerAction.action).returning(InvalidTransition.retrieve,idleState)

      ctrl.Apply(playerAction,idleState)

    }

    "attack to dodge is an invalid transition" in new withMovementStateCtrl {

      val idleState = mock[IdleState]
      val attackingState = mock[AttackingState]

      val playerAction = DodgeCommand.retrieve.playerAction

      (attackingState.DetermineNextStateAndApply _).expects(playerAction.action).returning(InvalidTransition.retrieve,idleState)

      ctrl.Apply(playerAction,attackingState)

    }

    "attack to combo is valid" in new withMovementStateCtrl {

      val attackingState = mock[AttackingState]
      val comboState = mock[ComboAttackState]

      val comboAttack = AttackingCommand.retrieve.playerAction

      (attackingState.DetermineNextStateAndApply _).expects(comboAttack.action).returning(ValidTransition.retrieve,comboState)

      ctrl.Apply(comboAttack,attackingState)

    }


    "combo to endOfCombo is valid" in new withMovementStateCtrl {

      val endOfCombo = mock[EndOfComboState]
      val comboState = mock[ComboAttackState]

      val comboAttack = AttackingCommand.retrieve.playerAction

      (comboState.DetermineNextStateAndApply _).expects(comboAttack.action).returning(ValidTransition.retrieve,endOfCombo)

      ctrl.Apply(comboAttack,comboState)

    }

    "endOfCombo to idle is valid" in new withMovementStateCtrl {

      val endOfCombo = mock[EndOfComboState]
      val idleState = mock[IdleState]

      val idleCommand = IdleCommand.retrieve.playerAction

      (endOfCombo.DetermineNextStateAndApply _).expects(idleCommand.action).returning(ValidTransition.retrieve,idleState)

      ctrl.Apply(idleCommand,endOfCombo)

    }

    "endOfCombo to move is invalid" in new withMovementStateCtrl {

      val endOfCombo = mock[EndOfComboState]
      val idleState = mock[IdleState]

      val moveCommand = MoveCommand.retrieve.playerAction

      (endOfCombo.DetermineNextStateAndApply _).expects(moveCommand.action).returning(InvalidTransition.retrieve,idleState)

      ctrl.Apply(moveCommand,endOfCombo)

    }

    "endOfCombo to move is invalid" in new withMovementStateCtrl {

      val endOfCombo = mock[EndOfComboState]
      val idleState = mock[IdleState]

      val moveCommand = MoveCommand.retrieve.playerAction

      (endOfCombo.DetermineNextStateAndApply _).expects(moveCommand.action).returning(InvalidTransition.retrieve,idleState)

      ctrl.Apply(moveCommand,endOfCombo)

    }

    "endOfCombo to dodge is invalid" in new withMovementStateCtrl {

      val endOfCombo = mock[EndOfComboState]
      val idleState = new IdleState()

      val dodgeCommand = DodgeCommand.retrieve.playerAction

      (endOfCombo.DetermineNextStateAndApply _).expects(dodgeCommand.action).returning(InvalidTransition.retrieve,idleState)

      ctrl.Apply(dodgeCommand,endOfCombo)

    }

    "idle to move to dodge to idle is a valid chain" in new withMovementStateCtrl {

      val idleState = mock[IdleState]
      val moveState = mock[MovingState]
      val dodgeState = mock[DodgeState]

      val action = MoveCommand.retrieve.playerAction
      val action2 = DodgeCommand.retrieve.playerAction
      val action3  = IdleCommand.retrieve.playerAction

      (idleState.DetermineNextStateAndApply _).expects(action.action).returning(ValidTransition.retrieve,moveState)
      (moveState.DetermineNextStateAndApply _).expects(action2.action).returning(ValidTransition.retrieve,dodgeState)
      (dodgeState.DetermineNextStateAndApply _).expects(action3.action).returning(ValidTransition.retrieve,idleState)

      ctrl.Apply(action,idleState)
      ctrl.Apply(action2,moveState)
      ctrl.Apply(action3,dodgeState)

    }

    "idle to dodge is valid; dodge to move is not" in new withMovementStateCtrl {

      val idleState = mock[IdleState]
      val moveState = mock[MovingState]
      val dodgeState = mock[DodgeState]

      val action = MoveCommand.retrieve.playerAction
      val action2 = DodgeCommand.retrieve.playerAction

      (idleState.DetermineNextStateAndApply _).expects(action2.action).returning(ValidTransition.retrieve,dodgeState)
      (dodgeState.DetermineNextStateAndApply _).expects(action.action).returning(InvalidTransition.retrieve,idleState)

      ctrl.Apply(action2,idleState)
      ctrl.Apply(action,dodgeState)

    }





  }

}
