package actors

import akka.actor._
import play.api.libs.json.JsValue
import play.api.libs.json.Json

object MyWebSocketActor {
  def props(out: ActorRef) = Props(new MyWebSocketActor(out))
}

class MyWebSocketActor(out: ActorRef) extends Actor {
  
  /*
  override def preStart() = {
    // We need to send this back to Meteor right away:
    // See http://sockjs.github.io/sockjs-protocol/sockjs-protocol-0.3.3.html#section-42
    //out ! ("o")
  }
  */

  def receive = {
    case in: JsValue =>
      println("in " + in)
      
      val msg = (in \ "msg").as[String]

      if (msg == "connect") {
        send(Json.obj("server_id" -> "0"))
        send(Json.obj(
            "msg" -> "connected",
            "session" -> "FNsuoJyjLMmNiJMr2")) // TODO generate session id
      }
      
      if (msg == "sub") {        
        val name = (in \ "name").as[String]
        val id = (in \ "id").as[String]
        
        send(Json.obj(
            "msg" -> "added",
            "collection" -> name,
            "id" -> "1", // TODO
            "fields" -> Json.obj("name" -> "Christiaan")))
        send(Json.obj(
            "msg" -> "added",
            "collection" -> name,
            "id" -> "2", // TODO
            "fields" -> Json.obj("name" -> "Rahul")))
        
        // We're done sending this collection for now:
        send(Json.obj(
            "msg" -> "ready",
            "subs" -> List(id)))
      }
      
      if (msg == "ping") {
        send(Json.obj("msg" -> "pong"))
      }
  }
  
  def send(json: JsValue) = {
    println("out " + json)
    out ! json
  }
}
