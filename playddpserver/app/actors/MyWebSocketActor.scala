package actors

import akka.actor._

object MyWebSocketActor {
  def props(out: ActorRef) = Props(new MyWebSocketActor(out))
}

class MyWebSocketActor(out: ActorRef) extends Actor {
  
  override def preStart() = {
    // We need to send this back to Meteor right away:
    out ! ("o")
  }
  
  def receive = {
    case msg: String =>
      println(msg)
      out ! ("I received your message: " + msg)
  }
}
