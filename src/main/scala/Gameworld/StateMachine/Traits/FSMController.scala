package Gameworld.StateMachine.Traits

import Model.Schemas.{PlayerAction, Action}
import play.api.libs.json.JsValue

/**
 * Created with IntelliJ IDEA.
 * User: marchaubenstock
 * Date: 06/10/2014
 * Time: 23:06
 */
trait FSMController {

  var mCurrentState : FSM

  def DetermineControllerFrom(command : PlayerAction.PlayerActionSchema): (JsValue,FSMController)

  def Apply(command : Action.ActionSchema, currentState : FSM) = {
    val (result, newState) = currentState.DetermineNextStateAndApply(command.action)

    mCurrentState = newState

    result
  }

  def matchStateCtrlString(stateCtrl :  String): (FSMController, FSM)


}
