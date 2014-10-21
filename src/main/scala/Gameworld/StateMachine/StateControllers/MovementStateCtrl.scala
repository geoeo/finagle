package Gameworld.StateMachine.StateControllers

import Gameworld.StateMachine.States
import Gameworld.StateMachine.DyingStates.DyingState
import Gameworld.StateMachine.JumpingStates.JumpingState
import Gameworld.StateMachine.StateControllers.AbstractController.AFSMController
import Gameworld.StateMachine.Traits.FSM
import Gameworld.StateMachine.MovementStates.IdleState
/**
 * Created with IntelliJ IDEA.
 * User: marchaubenstock
 * Date: 06/10/2014
 * Time: 23:09
 */
class MovementStateCtrl extends AFSMController{

  var mCurrentState: FSM = new IdleState

  override def matchStateCtrlString(stateCtrl :  String) = stateCtrl match {

    case States.Jumping => (new JumpStateCtrl() , new JumpingState)

    case States.Dying => (new DieStateCtrl(), new DyingState)

    case _ => (this, mCurrentState)

  }


}
