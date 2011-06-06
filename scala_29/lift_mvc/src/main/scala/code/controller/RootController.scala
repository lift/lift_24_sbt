package code 
package controller 

import net.liftweb.http._
import net.liftweb.util._
import java.util.Date
import code.lib._
import Helpers._

/**
 * A simple MVC controller.  This controller will intercept
 * the given URL and will transform the reterm page based on
 * some computed value
 */
object RootController extends MVCHelper {

  // Update the time on the index (home) page
  serve {
    case "index" :: Nil => {
      // replace the contents of the element with id "time" with the date
      "#time *" #> DependencyFactory.inject[Date].map(_.toString)
    }
  }

  // serve a page if and only if the second URL param
  // is an Int
  serve {
    case "show_int" :: AsInt(param) :: Nil =>
      "#int_value" #> param
  }
}
