package code
package snippet

import org.specs._
import net.liftweb._
import http._
import net.liftweb.util._
import net.liftweb.common._
import org.specs.specification._
import Helpers._
import lib._


object HelloWorldTestSpecs extends Specification {
  val session = new LiftSession("", randomString(20), Empty)
  val stableTime = now

  /**
   * For additional ways of writing tests,
   * please see http://www.assembla.com/spaces/liftweb/wiki/Mocking_HTTP_Requests
   */
  override def executeExpectations(ex: Examples, t: =>Any): Any = {
    S.initIfUninitted(session) {
      DependencyFactory.time.doWith(stableTime) {
        super.executeExpectations(ex, t)
      }
    }
  }

  "HelloWorld Snippet" should {
    "Put the time in the node" in {
      val hello = new HelloWorld
      Thread.sleep(1000) // make sure the time changes

      val str = hello.howdy(<span>Welcome to your Lift app at <span id="time">Time goes here</span></span>).toString

      str.indexOf(stableTime.toString) must be >= 0
      str must startWith("<span>Welcome to")
    }
  }
}
