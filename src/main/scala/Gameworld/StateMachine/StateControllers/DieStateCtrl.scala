package Gameworld.StateMachine.StateControllers

import Gameworld.StateMachine.DyingStates.DyingState
import Gameworld.StateMachine.Traits.{FSM, FSMController}
import play.api.libs.json.JsValue

/**
 * Created with IntelliJ IDEA.
 * User: marchaubenstock
 * Date: 07/10/2014
 * Time: 19:04
 */
class DieStateCtrl extends FSMController{

  var mCurrentState: FSM = new DyingState()

  override def DetermineControllerFrom(command: JsValue) = {
    val stateCtrlString = (command \ "stateCtrl").asOpt[String].get
    val playerAction = (command \ "playerAction").asOpt[JsValue].get

    val stateCtrl = stateCtrlString match {

      case "moving" => new MovementStateCtrl()

      case "dying" => new DieStateCtrl()

      case _ => this

    }

    (stateCtrl.Apply(playerAction,mCurrentState),stateCtrl)
  }

}
