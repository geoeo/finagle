package Gameworld.StateMachine.Traits

import Model.Responses.{ValidTransition, IgnoreCommand}
import Model.StateExchangeKeys
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

  def DetermineNextStateAndApply(action: String): (JsValue, FSM)
    = transitionGraph(action)







}
