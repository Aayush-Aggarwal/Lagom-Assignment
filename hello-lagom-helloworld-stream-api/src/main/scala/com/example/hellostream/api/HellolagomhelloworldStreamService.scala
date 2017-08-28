package com.example.hellostream.api

import akka.NotUsed
import akka.stream.scaladsl.Source
import com.lightbend.lagom.scaladsl.api.{Service, ServiceCall}

/**
  * The hello-lagom-HelloWorld stream interface.
  *
  * This describes everything that Lagom needs to know about how to serve and
  * consume the HellolagomhelloworldStream service.
  */
trait HellolagomhelloworldStreamService extends Service {

  def stream: ServiceCall[Source[String, NotUsed], Source[String, NotUsed]]

  override final def descriptor = {
    import Service._

    named("hello-lagom-helloworld-stream")
      .withCalls(
        namedCall("stream", stream)
      ).withAutoAcl(true)
  }
}

