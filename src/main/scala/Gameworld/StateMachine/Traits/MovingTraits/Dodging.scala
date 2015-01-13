package Gameworld.StateMachine.Traits.MovingTraits

import Gameworld.StateMachine.AbstractStates.AMovementState
import Model.Actions

/**
 * Created with IntelliJ IDEA.
 * User: marchaubenstock
 * Date: 08/10/2014
 * Time: 23:13
 */
trait Dodging extends AMovementState{

  abstract override def transitionGraph = super.transitionGraph.updated(Actions.Dodging, (ignoreCommand,idleState))
                                                               .updated(Actions.Moving,(ignoreCommand,idleState))
                                                               .updated(Actions.Attacking,(ignoreCommand,idleState))


}
