package Gameworld.StateMachine.Traits.MovingTraits

import Gameworld.StateMachine.AbstractStates.AMovementState
import Model.ValidTransition

/**
 * Created with IntelliJ IDEA.
 * User: marchaubenstock
 * Date: 08/10/2014
 * Time: 23:11
 */
trait Moving extends AMovementState {


  abstract override def transitionGraph = super.transitionGraph.updated("idle",(validTransition,idleState))
    .updated("dodging", (validTransition,dodgeState)).updated("moving",(validTransition,this))



}
