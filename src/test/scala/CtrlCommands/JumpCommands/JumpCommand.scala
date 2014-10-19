package CtrlCommands.JumpCommands

import Model.Traits.Request
import play.api.libs.json.{JsValue, Json}

/**
 * Created with IntelliJ IDEA.
 * User: marchaubenstock
 * Date: 12/10/2014
 * Time: 23:05
 */
object JumpCommand extends Request {
  def retrieve: JsValue = Json.obj(
    "stateCtrl" -> "jumping",
    "playerAction" -> Json.obj("action" -> "jumping")
  )
}
