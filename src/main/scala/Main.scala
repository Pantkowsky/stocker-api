package com.stocker.api

import com.stocker.api.client.PolygonClient
import zio.console.{getStrLn, putStrLn}
import zio.{ExitCode, URIO}

object Main extends zio.App {

//  val ticker = "tsla"
//  // response.body: by default read into an Either[String, String] to indicate failure or success
//  println(PolygonClient.fetchDetailsForTicker(ticker))

  override def run(args: List[String]): URIO[zio.ZEnv, ExitCode] =
    logic.exitCode

  val logic =
    for {
      _     <- putStrLn("Hello")
      name  <- getStrLn
      _     <- putStrLn(s"Hello ${name}")
    } yield()

}
