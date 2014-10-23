package Gameworld.StateMachine.Traits.MovingTraits

import Gameworld.StateMachine.AbstractStates.AMovementState
import Gameworld.StateMachine.Actions

/**
 * Created with IntelliJ IDEA.
 * User: marchaubenstock
 * Date: 23/10/14
 * Time: 21:21
 */
trait ComboAttack extends AMovementState {


  abstract override def transitionGraph = super.transitionGraph.updated(Actions.Dodging, (ignoreCommand,idleState))
    .updated(Actions.Moving,(ignoreCommand,idleState))
    .updated(Actions.Attacking,(validTransition,endOfComboState))



}
