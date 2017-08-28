package com.example.hellostream.impl

import com.lightbend.lagom.scaladsl.api.ServiceCall
import com.example.hellostream.api.HellolagomhelloworldStreamService
import com.example.hello.api.HellolagomhelloworldService

import scala.concurrent.Future

/**
  * Implementation of the HellolagomhelloworldStreamService.
  */
class HellolagomhelloworldStreamServiceImpl(hellolagomhelloworldService: HellolagomhelloworldService) extends HellolagomhelloworldStreamService {
  def stream = ServiceCall { hellos =>
    Future.successful(hellos.mapAsync(8)(hellolagomhelloworldService.hello(_).invoke()))
  }
}
