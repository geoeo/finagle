package Gameworld.StateMachine.AbstractStates

import Gameworld.StateMachine.DyingStates.{DyingState, DeadState}
import Gameworld.StateMachine.Traits.FSM
import play.api.libs.json.JsValue

/**
 * Created with IntelliJ IDEA.
 * User: marchaubenstock
 * Date: 15/10/2014
 * Time: 23:50
 */
class ADyingState extends FSM{
  /**
   * transitionGraph : Action -> Next State
   */
  def transitionGraph: Map[String, (JsValue, FSM)] = Map("dying" -> (ignoreCommand,deadState),
                                                         "dead" -> (validTransition,deadState))

  def dyingState = new DyingState()
  def deadState = new DeadState()
}
