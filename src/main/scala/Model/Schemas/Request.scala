package Model.Schemas

/**
 * Created with IntelliJ IDEA.
 * User: marchaubenstock
 * Date: 21/12/14
 * Time: 20:09
 */
object Request {

  val ResponseToInvalidRequest = new RequestSchema("","Error",PlayerAction.emptyAction)

  case class RequestSchema(
    name:String,
    data:String,
    playerAction:PlayerAction.PlayerActionSchema
  )


}
