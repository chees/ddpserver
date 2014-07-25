package controllers

import play.api._
import play.api.mvc._
import play.api.libs.json.Json
import play.api.Play.current
import actors.MyWebSocketActor

object Application extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }
  
  /**
   * We need to pretend we run SockJS since meteor checks this first before even trying to setup a websocket.
   */
  def sockjsinfo = Action {
    // {"websocket":true,"origins":["*:*"],"cookie_needed":false,"entropy":4036343750}
    Ok(Json.obj(
      "websocket" -> true,
      "origins" -> List("*:*"),
      "cookie_needed" -> false,
      "entropy" -> 4036343750L)
    ).withHeaders(
      "access-control-allow-credentials" -> "true",
      "access-control-allow-origin" -> "http://localhost:3000")
  }
  
  def websocket(path: String) = WebSocket.acceptWithActor[String, String] { request => out =>
    MyWebSocketActor.props(out)
  }

}