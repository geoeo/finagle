package StateControllersTests

import Gameworld.StateMachine.MovementStates.IdleState
import Gameworld.StateMachine.StateControllers.MovementStateCtrl
import org.junit.runner.RunWith

import org.specs2.mutable._
import org.specs2.runner.JUnitRunner
import org.specs2.specification.Scope

/**
 * Created with IntelliJ IDEA.
 * User: marchaubenstock
 * Date: 12/10/2014
 * Time: 17:53
 */
@RunWith(classOf[JUnitRunner])
class MovementStateCtrlSpec extends Specification {

  trait withMovementStateCtrl extends Scope {
    val ctrl = new MovementStateCtrl()
  }

  "MovementStateCtrl" should {

    val movementStateCtrl =

    "start with Idle state" in new withMovementStateCtrl {
      ctrl.startingState must beAnInstanceOf[IdleState]
    }
  }

}
