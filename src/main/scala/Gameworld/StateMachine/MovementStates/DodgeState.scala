package Gameworld.StateMachine.MovementStates

import Gameworld.StateMachine.AbstractStates.AMovementState
import Gameworld.StateMachine.Traits.FSM
import Gameworld.StateMachine.Traits.MovingStates.Dodging
import Model.ValidTransition
import play.api.libs.json.{Json, JsValue}

/**
 * Created with IntelliJ IDEA.
 * User: marchaubenstock
 * Date: 05/10/2014
 * Time: 21:23
 */
class DodgeState extends Dodging {

  override def apply = Json.obj("ignoreCommand" -> false)
}
