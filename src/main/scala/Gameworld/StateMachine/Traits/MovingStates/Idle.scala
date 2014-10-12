package Gameworld.StateMachine.Traits.MovingStates

import Gameworld.StateMachine.AbstractStates.AMovementState
import Model.ValidTransition

/**
 * Created with IntelliJ IDEA.
 * User: marchaubenstock
 * Date: 08/10/2014
 * Time: 23:14
 */
trait Idle extends AMovementState {

  abstract override def transitionGraph = super.transitionGraph.updated("dodging",(ValidTransition.retrieve,dodgeState))
    .updated("moving", (ValidTransition.retrieve,movingState))

}
