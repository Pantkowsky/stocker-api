package com.stocker.api
package client

import sttp.client.asynchttpclient.WebSocketHandler
import sttp.client.asynchttpclient.zio.AsyncHttpClientZioBackend
import sttp.client.circe.SttpCirceApi
import sttp.client.logging.slf4j.{Slf4jCurlBackend, Slf4jLoggingBackend}
import sttp.client.{SttpApi, SttpBackend}
import zio.{Has, Task, ZIO, ZLayer}

//trait Client {
//
//  def fetch(url: String) : zio.IO[String, String] = {
//    val request = basicRequest.get(uri"$url")
//
//    val connection = HttpURLConnectionBackend()
//    val response = request.send(connection)
//    response.body match {
//      case Left(s) => ZIO.fromEither(Left(s))
//      case Right(s) => ZIO.fromEither(Right(s))
//    }
//  }
//}

object HttpClient extends SttpApi with SttpCirceApi {

  type Backend = SttpBackend[Task, Nothing, WebSocketHandler]
  type ZioBackend = Has[Backend]

  def createBackend: Task[Backend] =
    AsyncHttpClientZioBackend().map { backend =>
      Slf4jCurlBackend[Task, Nothing, WebSocketHandler](
        Slf4jLoggingBackend[Task, Nothing, WebSocketHandler](backend)
      )
    }

  val live: ZLayer[Any, Throwable, Has[Backend]] =
    createBackend.toLayer
}
