package com.example.user.api

import akka.NotUsed
import com.lightbend.lagom.scaladsl.api.transport.Method
import com.lightbend.lagom.scaladsl.api.{Descriptor, Service, ServiceCall}

/**
  * Created by knoldus on 8/25/17.
  */
trait Userlagomservice extends Service{


  def greetUser(name: String) : ServiceCall[NotUsed,String]
  def testUser() : ServiceCall[NotUsed,String]

  override def descriptor:Descriptor={
    import Service._

    named("user")
      .withCalls(
        restCall(Method.GET,"/api/user/:name",greetUser _),
        restCall(Method.GET,"/api/userdata",testUser _)
      )
      .withAutoAcl(true)
  }
}
