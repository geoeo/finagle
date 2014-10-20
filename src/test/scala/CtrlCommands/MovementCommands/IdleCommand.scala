package CtrlCommands.MovementCommands

import Gameworld.StateExchangeKeys
import Gameworld.StateMachine.Actions
import Model.Traits.Request
import play.api.libs.json.{JsValue, Json}

/**
 * Created with IntelliJ IDEA.
 * User: marchaubenstock
 * Date: 12/10/2014
 * Time: 23:05
 */
object IdleCommand extends Request {
  def retrieve: JsValue = Json.obj(
    StateExchangeKeys.StateCtrl -> "moving",
    StateExchangeKeys.PlayerAction -> Json.obj(StateExchangeKeys.Action -> Actions.Idle)
  )
}
