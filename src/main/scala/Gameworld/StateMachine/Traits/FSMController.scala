package Gameworld.StateMachine.Traits

import play.api.libs.json.JsValue

/**
 * Created with IntelliJ IDEA.
 * User: marchaubenstock
 * Date: 06/10/2014
 * Time: 23:06
 */
trait FSMController {

//  var transitionGraph: Map[String,FSMController]

  val startingState: FSM

  var currentState : FSM

  def determineControllerFrom(command : JsValue): (JsValue,FSMController)
  
  def Apply(command : JsValue): JsValue


}
