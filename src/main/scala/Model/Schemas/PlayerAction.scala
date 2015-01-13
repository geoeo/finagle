package Model.Schemas

/**
 * Created with IntelliJ IDEA.
 * User: marchaubenstock
 * Date: 23/12/14
 * Time: 00:12
 */
object PlayerAction {

  def emptyPlayerAction = new PlayerActionSchema("no state",Action.emptyAction)
  
  case class PlayerActionSchema(
    stateCtrl : String,
    playerAction: Action.ActionSchema
  )

}
