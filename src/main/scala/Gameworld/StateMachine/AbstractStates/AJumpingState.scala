package Gameworld.StateMachine.AbstractStates

import Gameworld.StateMachine.JumpingStates.{JumpAttackState, LandedState, LandingState, JumpingState}
import Gameworld.StateMachine.Traits.FSM
import play.api.libs.json.JsValue

/**
 * Created with IntelliJ IDEA.
 * User: marchaubenstock
 * Date: 14/10/2014
 * Time: 23:24
 */
abstract class AJumpingState extends FSM{

  def transitionGraph: Map[String, (JsValue, FSM)] = Map("jumping" -> (validTransition,landingState),
                                                         "jumpAttack" -> (validTransition,landedState),
                                                         "landing" -> (validTransition,landedState),
                                                         "landed" -> (ignoreCommand,landedState))

  def jumpingState = new JumpingState()
  def landingState = new LandingState()
  def landedState = new LandedState()
  def jumpAttackState = new JumpAttackState()

}
