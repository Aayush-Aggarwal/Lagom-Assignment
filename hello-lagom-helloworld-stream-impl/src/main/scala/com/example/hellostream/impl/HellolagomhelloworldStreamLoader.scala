package com.example.hellostream.impl

import com.lightbend.lagom.scaladsl.api.ServiceLocator.NoServiceLocator
import com.lightbend.lagom.scaladsl.server._
import com.lightbend.lagom.scaladsl.devmode.LagomDevModeComponents
import play.api.libs.ws.ahc.AhcWSComponents
import com.example.hellostream.api.HellolagomhelloworldStreamService
import com.example.hello.api.HellolagomhelloworldService
import com.softwaremill.macwire._

class HellolagomhelloworldStreamLoader extends LagomApplicationLoader {

  override def load(context: LagomApplicationContext): LagomApplication =
    new HellolagomhelloworldStreamApplication(context) {
      override def serviceLocator = NoServiceLocator
    }

  override def loadDevMode(context: LagomApplicationContext): LagomApplication =
    new HellolagomhelloworldStreamApplication(context) with LagomDevModeComponents

  override def describeService = Some(readDescriptor[HellolagomhelloworldStreamService])
}

abstract class HellolagomhelloworldStreamApplication(context: LagomApplicationContext)
  extends LagomApplication(context)
    with AhcWSComponents {

  // Bind the service that this server provides
  override lazy val lagomServer = serverFor[HellolagomhelloworldStreamService](wire[HellolagomhelloworldStreamServiceImpl])

  // Bind the HellolagomhelloworldService client
  lazy val hellolagomhelloworldService = serviceClient.implement[HellolagomhelloworldService]
}
