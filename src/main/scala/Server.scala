/**
 * Created with IntelliJ IDEA.
 * User: marchaubenstock
 * Date: 30/09/2014
 * Time: 23:25
 */

import Commons.Validator
import Commons.ServerLogger
import com.twitter.finagle.{Http,Service}
import com.twitter.util.{Await, Future}
import com.twitter.finagle.http.Response
import com.twitter.finagle.http.MediaType
import org.jboss.netty.handler.codec.http._
import org.jboss.netty.buffer.ChannelBuffers
import org.jboss.netty.util.CharsetUtil.UTF_8

object Server extends App {

  ServerLogger.logMessage("Starting Server...")

  val service = new Service[HttpRequest, HttpResponse] {
    def apply(req: HttpRequest): Future[HttpResponse] = {

      val headerEntries = req.headers().entries().iterator()

      while(headerEntries.hasNext) ServerLogger.logHeader(headerEntries.next())

      val requestContents = req.getContent().toString(UTF_8)
      ServerLogger.logContents(requestContents)

      val response = Response()
      val responseContent =  GameWorld.Apply(Validator.ValidateRequest(requestContents))

      response.setContentType(MediaType.Json, UTF_8.name)
      response.setContent(ChannelBuffers.copiedBuffer(responseContent, UTF_8))
      Future.value(response)
    }

  }
  val server = Http.serve(":8080", service)
  Await.ready(server)
}
