package Gameworld.StateMachine.StateControllers

import Gameworld.StateMachine.Actions
import Gameworld.StateMachine.DyingStates.DyingState
import Gameworld.StateMachine.JumpingStates.JumpingState
import Gameworld.StateMachine.MovementStates.IdleState
import Gameworld.StateMachine.StateControllers.AbstractController.AFSMController
import Gameworld.StateMachine.Traits.FSM

/**
 * Created with IntelliJ IDEA.
 * User: marchaubenstock
 * Date: 07/10/2014
 * Time: 19:04
 */
class JumpStateCtrl extends AFSMController{

  var mCurrentState: FSM = new JumpingState

  override def matchStateCtrlString(stateCtrl :  String) = stateCtrl match {

    case Actions.Idle => (new MovementStateCtrl(), new IdleState())

    case Actions.Dying => (new DieStateCtrl(), new DyingState())

    case _ => (this,mCurrentState)

  }


}
