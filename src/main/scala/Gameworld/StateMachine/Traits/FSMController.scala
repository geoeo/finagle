package Gameworld.StateMachine.Traits

import play.api.libs.json.JsValue

/**
 * Created with IntelliJ IDEA.
 * User: marchaubenstock
 * Date: 06/10/2014
 * Time: 23:06
 */
trait FSMController {

  var mCurrentState : FSM

  def DetermineControllerFrom(command : JsValue): (JsValue,FSMController)

  def Apply(command : JsValue, currentState : FSM) = {
    val (result, newState) = currentState.DetermineNextStateAndApply(command)

    mCurrentState = newState

    result
  }


}
