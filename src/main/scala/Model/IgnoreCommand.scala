package Model

import Model.Traits.Request
import play.api.libs.json.{Json, JsValue}

/**
 * Created with IntelliJ IDEA.
 * User: marchaubenstock
 * Date: 06/10/2014
 * Time: 20:52
 */
object IgnoreCommand extends Request {
  def retrieve: JsValue = Json.toJson(Map("ignoreCommand" -> true))
}
