package Gameworld.StateMachine.StateControllers

import Gameworld.StateMachine.DyingStates.DyingState
import Gameworld.StateMachine.JumpingStates.JumpingState
import Gameworld.StateMachine.StateControllers.AbstractController.AFSMController
import Gameworld.StateMachine.Traits.FSM
import Gameworld.StateMachine.MovementStates.IdleState
import Model.IgnoreCommand
import play.api.libs.json.JsValue

/**
 * Created with IntelliJ IDEA.
 * User: marchaubenstock
 * Date: 06/10/2014
 * Time: 23:09
 */
class MovementStateCtrl extends AFSMController{

  var mCurrentState: FSM = new IdleState

  override def matchStateCtrlString(stateCtrl :  String) = stateCtrl match {

    case "jumping" => (new JumpStateCtrl() , new JumpingState)

    case "dying" => (new DieStateCtrl(), new DyingState)

    case _ => (this, mCurrentState)

  }


}
