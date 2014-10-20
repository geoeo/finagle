package CtrlCommands.DieCommands

import Gameworld.StateExchangeKeys
import Gameworld.StateMachine.Actions
import Model.Traits.Request
import play.api.libs.json.{Json, JsValue}

/**
 * Created with IntelliJ IDEA.
 * User: marchaubenstock
 * Date: 20/10/2014
 * Time: 22:44
 */
object DeadCommand extends Request {

  def retrieve: JsValue = Json.obj(
    StateExchangeKeys.StateCtrl -> "dying",
    StateExchangeKeys.PlayerAction -> Json.obj(StateExchangeKeys.Action -> Actions.Dead)
  )

}
