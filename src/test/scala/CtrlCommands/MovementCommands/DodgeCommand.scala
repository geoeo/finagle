package CtrlCommands.MovementCommands

import Gameworld.StateMachine.Actions
import Model.StateExchangeKeys
import Model.Traits.Request
import play.api.libs.json.{JsValue, Json}

/**
 * Created with IntelliJ IDEA.
 * User: marchaubenstock
 * Date: 13/10/2014
 * Time: 23:22
 */
object DodgeCommand extends Request {
  def retrieve: JsValue = Json.obj(
    StateExchangeKeys.StateCtrl -> "moving",
    StateExchangeKeys.PlayerAction -> Json.obj(StateExchangeKeys.Action -> Actions.Dodging)
  )

}
