package Gameworld.StateMachine.MovementStates

import Gameworld.StateMachine.Traits.FSM
import Gameworld.StateMachine.Traits.MovingStates.Moving
import Model.{ValidTransition, InvalidRequest}
import play.api.libs.json.{Json, JsValue}

/**
 * Created with IntelliJ IDEA.
 * User: marchaubenstock
 * Date: 05/10/2014
 * Time: 16:13
 */
class MovingState extends Moving{

  override def apply = Json.obj("ignoreCommand" -> false)
}
