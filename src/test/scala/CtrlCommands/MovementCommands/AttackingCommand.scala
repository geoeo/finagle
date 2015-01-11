package CtrlCommands.MovementCommands

import Gameworld.StateMachine.Actions
import Model.StateExchangeKeys
import play.api.libs.json.{Json, JsValue}

/**
 * Created with IntelliJ IDEA.
 * User: marchaubenstock
 * Date: 22/10/14
 * Time: 22:54
 */
object AttackingCommand {
  def retrieve: JsValue = Json.obj(
    StateExchangeKeys.StateCtrl -> "moving",
    StateExchangeKeys.PlayerAction -> Json.obj(StateExchangeKeys.Action -> Actions.Attacking)
  )

}
