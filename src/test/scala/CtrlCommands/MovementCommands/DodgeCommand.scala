package CtrlCommands.MovementCommands

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
    "stateCtrl" -> "moving",
    "playerAction" -> Json.obj("action" -> "dodging")
  )

}
