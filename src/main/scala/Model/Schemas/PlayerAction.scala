package Model.Schemas

/**
 * Created with IntelliJ IDEA.
 * User: marchaubenstock
 * Date: 23/12/14
 * Time: 00:12
 */
object PlayerAction {

  def emptyAction = new PlayerActionSchema("no state","no playerAction","no action")
  
  case class PlayerActionSchema(
    stateCtrl : String,
    playerAction: String,
    action : String
  )

}
