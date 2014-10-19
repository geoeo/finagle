package Gameworld.StateMachine.StateControllers.AbstractController

import Gameworld.StateMachine.Traits.FSMController
import play.api.libs.json.JsValue

/**
 * Created with IntelliJ IDEA.
 * User: marchaubenstock
 * Date: 19/10/2014
 * Time: 18:59
 */
abstract class AFSMController extends FSMController{

  def DetermineControllerFrom(command: JsValue): (JsValue, FSMController) = {
    val stateCtrlString = (command \ "stateCtrl").asOpt[String].get
    val playerAction = (command \ "playerAction").asOpt[JsValue].get

    val (stateCtrl,newState) = matchStateCtrlString(stateCtrlString)

    (stateCtrl.Apply(playerAction,newState),stateCtrl)
  }

}
