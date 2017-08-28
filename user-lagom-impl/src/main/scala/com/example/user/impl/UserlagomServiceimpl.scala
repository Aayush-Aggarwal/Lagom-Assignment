package com.example.user.impl

import akka.NotUsed
import akka.actor.ActorSystem
import com.example.user.api.{ExternalService, UserData, Userlagomservice}
import com.lightbend.lagom.scaladsl.api.{Descriptor, ServiceCall}
import scala.concurrent.duration._

import scala.concurrent.{ExecutionContext, Future}


class UserlagomServiceimpl(externalService: ExternalService)
                          (implicit ec: ExecutionContext) extends Userlagomservice {

  override def greetUser(name: String) = ServiceCall { _ =>
    Future.successful("Hi, " + name)
  }

  override def testUser() = ServiceCall { _ =>
    val result: Future[UserData] = externalService.getUser().invoke()
    result.map(response => response.toString)
  }

  val actorSystem = ActorSystem("EmployeeSystemActor")

  val lagomAssignmentProducerActor = actorSystem.actorOf(SchedulerActor.props(externalService))

  actorSystem.scheduler.schedule(
    10 seconds,
    10 seconds,
    lagomAssignmentProducerActor,
    "scheduler")
}
