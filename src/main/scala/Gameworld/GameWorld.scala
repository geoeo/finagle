import play.api.libs.json.Json

/**
 * Created with IntelliJ IDEA.
 * User: marchaubenstock
 * Date: 01/10/2014
 * Time: 22:30
 */
object GameWorld {

  def Consume(command : String) : String = Json.toJson(
    Map( "response" -> "hello")
  ).toString()


}
