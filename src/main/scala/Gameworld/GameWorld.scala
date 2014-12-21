import Model.Schemas.Request.RequestSchema
import play.api.libs.json.Json

/**
 * Created with IntelliJ IDEA.
 * User: marchaubenstock
 * Date: 01/10/2014
 * Time: 22:30
 */
object GameWorld {

  def Apply(requestData : RequestSchema) : String = Json.toJson(
    Map( "echo" -> requestData.data )
  ).toString()


}
