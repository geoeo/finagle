package CtrlCommands.DieCommands

import Model.Traits.Request
import play.api.libs.json.{JsValue, Json}

/**
 * Created with IntelliJ IDEA.
 * User: marchaubenstock
 * Date: 13/10/2014
 * Time: 22:39
 */
object DieCommand extends Request{

  def retrieve: JsValue = Json.obj(
    "stateCtrl" -> "dying",
    "playerAction" -> Json.obj("action" -> "dying")
  )

}
