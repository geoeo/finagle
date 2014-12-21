package Model.Schemas

import play.api.libs.json.{JsPath, Reads, JsValue}

/**
 * Created with IntelliJ IDEA.
 * User: marchaubenstock
 * Date: 21/12/14
 * Time: 20:09
 */
object Request {

  val ResponseToInvalidRequest = new RequestSchema("","Error")

  case class RequestSchema(
    name:String,
    data:String
  )


}
