package Gameworld.StateMachine.StateControllers

import Gameworld.StateMachine.Traits.{FSMController,FSM}
import Gameworld.StateMachine.MovementStates.IdleState
import Model.IgnoreCommand
import play.api.libs.json.JsValue

/**
 * Created with IntelliJ IDEA.
 * User: marchaubenstock
 * Date: 06/10/2014
 * Time: 23:09
 */
class MovementStateCtrl extends FSMController{

  val startingState : FSM = new IdleState
  var currentState = startingState

  def determineControllerFrom(command: JsValue): (JsValue, FSMController) = {
    val stateCtrlString = (command \ "stateCtrl").asOpt[String].get
    val playerAction = (command \ "playerAction").asOpt[JsValue].get

    val stateCtrl = stateCtrlString match {

      case "jumping" => new JumpStateCtrl()

      case "dying" => new DieStateCtrl()

      case _ => this

    }

    (stateCtrl.Apply(playerAction),stateCtrl)
  }

  def Apply(command : JsValue) = {
    val(result, newState) = currentState.determineNextStateAndApply(command)

    currentState = newState

    result
  }
}
