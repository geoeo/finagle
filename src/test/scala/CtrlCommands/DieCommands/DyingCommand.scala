package CtrlCommands.DieCommands

import Model.Schemas.Action.ActionSchema
import Model.Schemas.PlayerAction.PlayerActionSchema
import Model.{Actions, StateExchangeKeys}
import Model.Traits.PlayerAction
import play.api.libs.json.{JsValue, Json}

/**
 * Created with IntelliJ IDEA.
 * User: marchaubenstock
 * Date: 13/10/2014
 * Time: 22:39
 */
object DyingCommand extends PlayerAction{

  def retrieve = new PlayerActionSchema("dying",new ActionSchema(Actions.Dying))

}
