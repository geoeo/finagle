package CtrlCommands

import Model.Traits.Request
import play.api.libs.json.{Json, JsValue}

/**
 * Created with IntelliJ IDEA.
 * User: marchaubenstock
 * Date: 13/10/2014
 * Time: 23:22
 */
object MoveCommand extends Request {
  def retrieve: JsValue = Json.obj(
    "stateCtrl" -> "moving",
    "playerAction" -> Json.obj("action" -> "moving")
  )

}
