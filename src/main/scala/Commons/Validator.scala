package Commons

import Gameworld.StateExchangeKeys
import Model.Schemas.PlayerAction.PlayerActionSchema
import Model.Schemas.Request.RequestSchema
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

    implicit val requestReads :Reads[RequestSchema] = (
        (JsPath \ "name").read[String] and
        (JsPath \ "data").read[String]
      )(RequestSchema.apply _)

    implicit val playerActionRead : Reads[PlayerActionSchema] = (
      (JsPath \ StateExchangeKeys.StateCtrl).read[String] and
        (JsPath \ StateExchangeKeys.PlayerAction).read[String] and
        (JsPath \ StateExchangeKeys.Action).read[String]
      )(PlayerActionSchema.apply _)


    def ValidateRequest(request : String)
      = Json.parse(request).validate[RequestSchema].getOrElse[RequestSchema](ResponseToInvalidRequest)



}
