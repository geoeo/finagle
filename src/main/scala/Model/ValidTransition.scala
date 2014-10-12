package Model

import Model.Traits.Request
import play.api.libs.json.{Json, JsValue}

/**
 * Created with IntelliJ IDEA.
 * User: marchaubenstock
 * Date: 07/10/2014
 * Time: 21:37
 */
object ValidTransition extends Request{

  def retrieve: JsValue = Json.toJson(Map("validTransition" -> true))

}
