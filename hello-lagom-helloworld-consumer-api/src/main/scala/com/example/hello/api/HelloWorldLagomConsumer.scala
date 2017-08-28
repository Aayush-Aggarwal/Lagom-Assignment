package com.example.hello.api

import javax.management.Descriptor

import com.lightbend.lagom.scaladsl.api.Service

/**
  * Created by knoldus on 8/24/17.
  */
trait HelloWorldLagomConsumer extends Service{

  override def descriptor: Descriptor ={
    import Service._

  }
}
