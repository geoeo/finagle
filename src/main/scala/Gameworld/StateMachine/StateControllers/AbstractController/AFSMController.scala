package Gameworld.StateMachine.StateControllers.AbstractController

import Gameworld.StateMachine.Traits.FSMController
import Model.Schemas.PlayerAction
import Model.Schemas.PlayerAction.PlayerActionSchema
import Model.StateExchangeKeys
import play.api.libs.json.JsValue

/**
 * Created with IntelliJ IDEA.
 * User: marchaubenstock
 * Date: 19/10/2014
 * Time: 18:59
 */
abstract class AFSMController extends FSMController{

  def DetermineControllerFrom(command: PlayerAction.PlayerActionSchema): (JsValue, FSMController) = {
    val stateCtrlString = command.stateCtrl
    val playerAction = command.playerAction

    val (stateCtrl,newState) = matchStateCtrlString(stateCtrlString)

    (stateCtrl.Apply(playerAction,newState),stateCtrl)
  }

}
