package com.stocker.api

import client.Client

import zio.console.putStr
import zio.{ExitCode, ZIO}

object Main extends zio.App {

  val ticker = "tsla"

  override def run(args: List[String]): ZIO[zio.ZEnv, Nothing, ExitCode] =
    logic.provideCustomLayer(Client.live.orDie).exitCode

  val logic =
    for {
      result <- Client.fetch(ticker)
      _ <- putStr(result.toString.replaceAll("[,]", "$0 "))
    } yield()

}
