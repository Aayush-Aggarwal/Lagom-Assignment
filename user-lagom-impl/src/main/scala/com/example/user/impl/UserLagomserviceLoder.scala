package com.example.user.impl

import com.example.user.api.{ExternalService, Userlagomservice}
import com.lightbend.lagom.scaladsl.api.ServiceLocator.NoServiceLocator
import com.lightbend.lagom.scaladsl.devmode.LagomDevModeComponents
import com.lightbend.lagom.scaladsl.server.{LagomApplication, LagomApplicationContext, LagomApplicationLoader}
import com.softwaremill.macwire.wire
import play.api.libs.ws.ahc.AhcWSComponents

/**
  * Created by knoldus on 8/25/17.
  */
class UserLagomserviceLoder extends LagomApplicationLoader{
  override def load(context: LagomApplicationContext): LagomApplication =
    new UserlagomApplication(context) {
      override def serviceLocator = NoServiceLocator
    }

  override def loadDevMode(context: LagomApplicationContext): LagomApplication =
    new UserlagomApplication(context) with LagomDevModeComponents

  override def describeService = Some(readDescriptor[Userlagomservice])
}

abstract class UserlagomApplication(context: LagomApplicationContext)
  extends LagomApplication(context)
    with AhcWSComponents {

  // Bind the service that this server provides
  override lazy val lagomServer = serverFor[Userlagomservice](wire[UserlagomServiceimpl])

  lazy val UserlagomService: Userlagomservice = serviceClient.implement[Userlagomservice]

  lazy val externalService: ExternalService = serviceClient.implement[ExternalService]
}
