package CommonsTest

import Commons.Validator
import Model.Schemas.PlayerAction
import Model.Schemas.Request.RequestSchema
import org.junit.runner.RunWith
import org.scalamock.specs2.MockFactory
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import org.specs2.specification.Scope

/**
 * Created with IntelliJ IDEA.
 * User: marchaubenstock
 * Date: 21/12/14
 * Time: 21:50
 */
@RunWith(classOf[JUnitRunner])
class ValidatorTestSpec extends Specification with MockFactory {

  val inputMissingData : String = """{"name" : "marc"}"""
  val RequestSchema_Error = new RequestSchema("error", "Error",PlayerAction.emptyPlayerAction)

  "Validator" should {

    "Return Error on Invalid Data" in {
      Validator.ValidateRequest(inputMissingData).data mustEqual("Error")
    }
  }

}
