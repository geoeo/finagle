package Commons

import java.util.Map

import com.twitter.logging._

/**
 * Created with IntelliJ IDEA.
 * User: marchaubenstock
 * Date: 11/01/15
 * Time: 16:58
 */
object ServerLogger {

  val serverLog = System.getProperty("user.dir") ++ "/log/server.log"
  val log = Logger.get(getClass)
  LoggerFactory(node = "",level = Some(Level.INFO),handlers = List(FileHandler(rollPolicy = Policy.Daily,filename = this.serverLog,formatter = BareFormatter,level = Some(Level.INFO)))).apply()

  def logHeader(headerEntry: Map.Entry[String,String]) = log.info("header: %s, contents: %s",headerEntry.getKey,headerEntry.getValue)
  def logContents(contents:String) = log.info("request received with contents %s",contents)

  def logMessage(message : String) = log.info(message)

}
