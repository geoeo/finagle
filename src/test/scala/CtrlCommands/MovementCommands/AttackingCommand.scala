package CtrlCommands.MovementCommands

import Model.Schemas.Action.ActionSchema
import Model.Schemas.PlayerAction.PlayerActionSchema
import Model.Traits.PlayerAction
import Model.{Actions, StateExchangeKeys}
import play.api.libs.json.{Json, JsValue}

/**
 * Created with IntelliJ IDEA.
 * User: marchaubenstock
 * Date: 22/10/14
 * Time: 22:54
 */
object AttackingCommand extends PlayerAction{
  def retrieve = new PlayerActionSchema("moving",new ActionSchema(Actions.Attacking))
}
