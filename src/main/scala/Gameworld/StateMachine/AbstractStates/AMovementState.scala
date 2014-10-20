package Gameworld.StateMachine.AbstractStates

import Gameworld.StateMachine.Actions
import Gameworld.StateMachine.MovementStates.{MovingState, DodgeState, IdleState}
import Gameworld.StateMachine.Traits.FSM
import Model.{ValidTransition, IgnoreCommand}
import play.api.libs.json.{Json, JsValue}

/**
 * Created with IntelliJ IDEA.
 * User: marchaubenstock
 * Date: 05/10/2014
 * Time: 14:49
 */
abstract class AMovementState extends FSM{

  def transitionGraph:Map[String,(JsValue,FSM)] = Map(Actions.Idle -> (validTransition, idleState),
                            Actions.Moving -> (validTransition,movingState),
                            Actions.Dodging -> (ignoreCommand,idleState))

  def idleState = new IdleState()
  def movingState = new MovingState()
  def dodgeState = new DodgeState()


}
