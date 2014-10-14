package CtrlCommands

import Model.Traits.Request
import Model.ValidTransition
import play.api.libs.json.{Json, JsValue}

/**
 * Created with IntelliJ IDEA.
 * User: marchaubenstock
 * Date: 13/10/2014
 * Time: 22:39
 */
object DieCommand extends Request{

  def retrieve: JsValue = Json.obj(
    "stateCtrl" -> "jumping",
    "playerAction" -> Json.obj("action" -> ValidTransition.retrieve)
  )

}
