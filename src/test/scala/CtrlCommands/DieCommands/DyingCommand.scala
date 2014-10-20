package CtrlCommands.DieCommands

import Gameworld.StateExchangeKeys
import Gameworld.StateMachine.Actions
import Model.Traits.Request
import play.api.libs.json.{JsValue, Json}

/**
 * Created with IntelliJ IDEA.
 * User: marchaubenstock
 * Date: 13/10/2014
 * Time: 22:39
 */
object DyingCommand extends Request{

  def retrieve: JsValue = Json.obj(
    StateExchangeKeys.StateCtrl -> "dying",
    StateExchangeKeys.PlayerAction -> Json.obj(StateExchangeKeys.Action -> Actions.Dying)
  )

}
