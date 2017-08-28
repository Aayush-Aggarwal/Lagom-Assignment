package com.example.user.impl

import akka.actor.{Actor, Props}
import com.example.user.api.ExternalService
import scala.concurrent.ExecutionContext.Implicits.global
import play.api.Logger

/**
  * Created by knoldus on 8/28/17.
  */
class SchedulerActor(externalService: ExternalService) extends Actor{

  override def receive: Receive = {
    case "scheduler" =>
      val userData = externalService.getUser().invoke()
      userData.map(userData => Logger.info("User Id ::" +userData.id +"\n"+"User Tittle ::" +userData.title
        +"\n"+"User Body ::" +userData.body))
    case  _ =>
      Logger.info("Invalid")

  }
}
object SchedulerActor{
  def props(externalService: ExternalService):Props=
    Props(classOf[SchedulerActor],externalService)
}
