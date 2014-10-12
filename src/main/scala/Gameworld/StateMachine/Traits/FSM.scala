package Gameworld.StateMachine.Traits

import Model.{ValidTransition, IgnoreCommand}
import play.api.libs.json.{Json, JsValue}

/**
 * Created with IntelliJ IDEA.
 * User: marchaubenstock
 * Date: 05/10/2014
 * Time: 14:50
 */
trait FSM {

  def ignoreCommand = IgnoreCommand.retrieve
  def validTransition = ValidTransition.retrieve

  /**
   * transitionGraph : Action -> Next State
   */

  def transitionGraph : Map[String,(JsValue,FSM)]

  def apply : JsValue


  def determineNextStateAndApply(command: JsValue): (JsValue, FSM) = {
    val action = (command \ "action").asOpt[String].get

    val (rsp,newState) = transitionGraph(action)

    val isValid = (rsp \ "validTransition").asOpt[Boolean].getOrElse(false)

    (if(isValid) newState.apply else Json.obj("newState" -> ignoreCommand), newState)

  }





}
