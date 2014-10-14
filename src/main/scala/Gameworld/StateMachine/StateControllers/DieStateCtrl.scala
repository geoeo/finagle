package Gameworld.StateMachine.StateControllers

import Gameworld.StateMachine.Traits.{FSM, FSMController}
import play.api.libs.json.JsValue

/**
 * Created with IntelliJ IDEA.
 * User: marchaubenstock
 * Date: 07/10/2014
 * Time: 19:04
 */
class DieStateCtrl extends FSMController{

  var mCurrentState: FSM = ???

  override def DetermineControllerFrom(command: JsValue)= ???

}
