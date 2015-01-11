package Commons

import Model.Schemas.PlayerAction.PlayerActionSchema
import Model.Schemas.Request.RequestSchema
import Model.StateExchangeKeys
import play.api.libs.json._
import play.api.libs.functional.syntax._
import Model.Schemas.Request._


/**
 * Created with IntelliJ IDEA.
 * User: marchaubenstock
 * Date: 23/12/14
 * Time: 00:01
 */

object Validator {


  implicit val playerActionRead : Reads[PlayerActionSchema] = (
    (JsPath \ StateExchangeKeys.StateCtrl).read[String] and
      (JsPath \ StateExchangeKeys.PlayerAction).read[String] and
      (JsPath \ StateExchangeKeys.Action).read[String]
    )(PlayerActionSchema.apply _)


  implicit val requestReads :Reads[RequestSchema] = (
        (JsPath \ StateExchangeKeys.Name).read[String] and
        (JsPath \ StateExchangeKeys.Data).read[String] and
        (JsPath \ StateExchangeKeys.PlayerAction).read[PlayerActionSchema]
      )(RequestSchema.apply _)


    def ValidateRequest(request : String)
      = Json.parse(request).validate[RequestSchema].getOrElse[RequestSchema](ResponseToInvalidRequest)



}
