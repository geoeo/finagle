package Model.Schemas

/**
 * Created with IntelliJ IDEA.
 * User: marchaubenstock
 * Date: 13/01/15
 * Time: 21:19
 */
object Action {

  def emptyAction = new ActionSchema("no action")

  case class ActionSchema(
    action : String
  )

}
