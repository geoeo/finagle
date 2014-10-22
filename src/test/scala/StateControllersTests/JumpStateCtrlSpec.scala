package StateControllersTests

import CtrlCommands.DieCommands.DyingCommand
import CtrlCommands.JumpCommands.JumpCommand
import CtrlCommands.MovementCommands.{IdleCommand, DodgeCommand}
import Gameworld.StateMachine.JumpingStates.JumpingState
import Gameworld.StateMachine.StateControllers.{MovementStateCtrl, DieStateCtrl, JumpStateCtrl}
import org.jboss.netty.handler.timeout.IdleState
import org.scalamock.specs2.MockFactory
import org.specs2.mutable.Specification
import org.specs2.specification.Scope

/**
 * Created with IntelliJ IDEA.
 * User: marchaubenstock
 * Date: 21/10/2014
 * Time: 22:29
 */
class JumpStateCtrlSpec extends Specification with MockFactory  {

  trait withJumpStateCtrl extends Scope {
    val ctrl = new JumpStateCtrl()
  }

  "JumpStateCtrl" should {

    "start with jumping state" in new withJumpStateCtrl {
      ctrl.mCurrentState must beAnInstanceOf[JumpingState]
    }

    "return JumpStateCtrl" in new withJumpStateCtrl {
      ctrl.DetermineControllerFrom(JumpCommand.retrieve)._2 must beAnInstanceOf[JumpStateCtrl]
    }

    "return DieStateCtrl" in new withJumpStateCtrl {
      ctrl.DetermineControllerFrom(DyingCommand.retrieve)._2 must beAnInstanceOf[DieStateCtrl]
    }

    "return MoveStateCtrl" in new withJumpStateCtrl {
      ctrl.DetermineControllerFrom(IdleCommand.retrieve)._2 must beAnInstanceOf[MovementStateCtrl]
    }

  }



}