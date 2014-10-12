package Gameworld.StateMachine.StateControllers

import Gameworld.StateMachine.Traits.{FSM, FSMController}
import play.api.libs.json.JsValue

/**
 * Created with IntelliJ IDEA.
 * User: marchaubenstock
 * Date: 07/10/2014
 * Time: 19:04
 */
class JumpStateCtrl extends FSMController{

  def determineControllerFrom(command: JsValue) = ???

  val startingState: FSM = ???

  var currentState: FSM = ???

  def Apply(command: JsValue): JsValue = ???
}
