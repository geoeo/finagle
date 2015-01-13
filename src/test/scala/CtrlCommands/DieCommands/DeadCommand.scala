package CtrlCommands.DieCommands

import Model.Schemas.PlayerAction.PlayerActionSchema
import Model.Schemas.Action.ActionSchema
import Model.{Actions, StateExchangeKeys}
import Model.Traits.PlayerAction
import play.api.libs.json.{Json, JsValue}

/**
 * Created with IntelliJ IDEA.
 * User: marchaubenstock
 * Date: 20/10/2014
 * Time: 22:44
 */
object DeadCommand extends PlayerAction {

  def retrieve = new PlayerActionSchema("dying",new ActionSchema(Actions.Dead))

}
