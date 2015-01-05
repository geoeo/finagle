package Model.Schemas

/**
 * Created with IntelliJ IDEA.
 * User: marchaubenstock
 * Date: 23/12/14
 * Time: 00:12
 */
object PlayerAction {
  
  case class PlayerActionSchema(
    state : String,
    playerAction: String,
    action : String
  )

}
