package Commons

import play.api.libs.json._
import play.api.libs.functional.syntax._
import Model.Schemas.Request._

/**
 * Created with IntelliJ IDEA.
 * User: marchaubenstock
 * Date: 21/12/14
 * Time: 19:43
 */
object Validator {

  implicit val requestReads :Reads[RequestSchema] = (
    (JsPath \ "name").read[String] and
    (JsPath \ "data").read[String]
  )(RequestSchema.apply _)


  def ValidateRequest(request : String)
    = Json.parse(request).validate[RequestSchema].getOrElse[RequestSchema](ResponseToInvalidRequest)

}
