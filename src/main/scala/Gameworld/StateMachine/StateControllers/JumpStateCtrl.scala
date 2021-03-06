package Gameworld.StateMachine.StateControllers

import Gameworld.StateMachine.States
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

    case States.Moving => (new MovementStateCtrl(), new IdleState())

    case States.Dying => (new DieStateCtrl(), new DyingState())

    case _ => (this,mCurrentState)

  }


}
