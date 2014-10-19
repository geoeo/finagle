package Gameworld.StateMachine.StateControllers

import Gameworld.StateMachine.DyingStates.DyingState
import Gameworld.StateMachine.StateControllers.AbstractController.AFSMController
import Gameworld.StateMachine.Traits.FSM

/**
 * Created with IntelliJ IDEA.
 * User: marchaubenstock
 * Date: 07/10/2014
 * Time: 19:04
 */
class DieStateCtrl extends AFSMController{

  var mCurrentState: FSM = new DyingState()

  override def matchStateCtrlString(stateCtrl :  String) = stateCtrl match {

    case _ => (this,mCurrentState)

  }

}
