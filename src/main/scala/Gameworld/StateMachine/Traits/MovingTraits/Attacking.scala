package Gameworld.StateMachine.Traits.MovingTraits

import Gameworld.StateMachine.AbstractStates.AMovementState
import Gameworld.StateMachine.Actions

/**
 * Created with IntelliJ IDEA.
 * User: marchaubenstock
 * Date: 22/10/14
 * Time: 22:35
 */
trait Attacking extends AMovementState{

  abstract override def transitionGraph = super.transitionGraph.updated(Actions.Dodging, (ignoreCommand,idleState))
                                                               .updated(Actions.Moving,(ignoreCommand,idleState))
                                                               .updated(Actions.Attacking,(validTransition,this))


}
