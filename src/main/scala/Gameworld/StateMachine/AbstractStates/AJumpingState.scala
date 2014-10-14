package Gameworld.StateMachine.AbstractStates

import Gameworld.StateMachine.JumpingStates.{LandingState, JumpingState}
import Gameworld.StateMachine.Traits.FSM

/**
 * Created with IntelliJ IDEA.
 * User: marchaubenstock
 * Date: 14/10/2014
 * Time: 23:24
 */
abstract class AJumpingState extends FSM{

  def transitionGraph = Map("jumping" -> (validTransition,landingState),
                            "landing" -> (ignoreCommand,landingState))

  def jumpingState = new JumpingState()
  def landingState = new LandingState()

}
