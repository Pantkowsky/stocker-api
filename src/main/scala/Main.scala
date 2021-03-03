package com.stocker.api

import com.stocker.api.client.Client
import zio.console.{getStrLn, putStr, putStrLn}
import zio.{ExitCode, URIO, ZIO}

object Main extends zio.App {

  val ticker = "tsla"

  override def run(args: List[String]): ZIO[zio.ZEnv, Nothing, ExitCode] =
    logic.provideCustomLayer(Client.live.orDie).exitCode

  val logic =
    for {
      result <- Client.fetch(ticker)
      _ <- putStr(result.noSpaces)
    } yield()

}
