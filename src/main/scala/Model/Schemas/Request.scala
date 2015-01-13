package Model.Schemas

/**
 * Created with IntelliJ IDEA.
 * User: marchaubenstock
 * Date: 21/12/14
 * Time: 20:09
 */
object Request {

  val ResponseToInvalidRequest = new RequestSchema("no name","error",PlayerAction.emptyPlayerAction)

  case class RequestSchema(
    name:String,
    data:String,
    playerData:PlayerAction.PlayerActionSchema
  )


}
