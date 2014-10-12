package Model.Traits

import play.api.libs.json.JsValue

/**
 * Created with IntelliJ IDEA.
 * User: marchaubenstock
 * Date: 05/10/2014
 * Time: 22:46
 */
trait Request {

  def retrieve : JsValue

}
