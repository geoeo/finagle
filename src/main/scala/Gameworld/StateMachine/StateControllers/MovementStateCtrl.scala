package Gameworld.StateMachine.StateControllers

import Gameworld.StateMachine.DyingStates.DyingState
import Gameworld.StateMachine.JumpingStates.JumpingState
import Gameworld.StateMachine.Traits.{FSMController,FSM}
import Gameworld.StateMachine.MovementStates.IdleState
import Model.IgnoreCommand
import play.api.libs.json.JsValue

/**
 * Created with IntelliJ IDEA.
 * User: marchaubenstock
 * Date: 06/10/2014
 * Time: 23:09
 */
class MovementStateCtrl extends FSMController{

  var mCurrentState: FSM = new IdleState

  def DetermineControllerFrom(command: JsValue): (JsValue, FSMController) = {
    val stateCtrlString = (command \ "stateCtrl").asOpt[String].get
    val playerAction = (command \ "playerAction").asOpt[JsValue].get

    val (stateCtrl,newState) = stateCtrlString match {

      case "jumping" => (new JumpStateCtrl() , new JumpingState)

      case "dying" => (new DieStateCtrl(), new DyingState)

      case _ => (this, mCurrentState)

    }

    (stateCtrl.Apply(playerAction,newState),stateCtrl)
  }

}
