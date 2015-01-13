package Model.Responses

import Model.Traits.Request
import play.api.libs.json.{JsValue, Json}

/**
 * Created with IntelliJ IDEA.
 * User: marchaubenstock
 * Date: 05/10/2014
 * Time: 22:46
 */
object InvalidRequest extends Request {

  def retrieve: JsValue = Json.toJson(Map("requestAccepted" -> false))
}
