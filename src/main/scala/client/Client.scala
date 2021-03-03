package com.stocker.api
package client

import secrets.Keys

import io.circe.Json
import sttp.client.asynchttpclient.WebSocketHandler
import sttp.client.asynchttpclient.zio.AsyncHttpClientZioBackend
import sttp.client.circe.SttpCirceApi
import sttp.client.logging.slf4j.{Slf4jCurlBackend, Slf4jLoggingBackend}
import sttp.client.{SttpApi, SttpBackend}
import zio.{Has, IO, Task, ZIO, ZLayer}

object Client extends SttpApi with SttpCirceApi {

  private val apiKey = s"apiKey=${Keys.apiKey}"
  private val baseUrl = s"https://api.polygon.io/v1/meta/symbols"

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

  def fetch(symbol: String) : ZIO[Has[Backend], Throwable, Json] = {

    val url = buildUrl(symbol)

    for {
      backend <- ZIO.service[Backend]
      response <- {
        implicit val b: Backend = backend

        // model classes go into asJson[Model]
        basicRequest.get(uri"$url").response(asJson[Json]).send[Task]
      }
      parsedBody <- IO.fromEither(response.body)
    } yield parsedBody
  }

  private def buildUrl(symbol: String) : String = {

    val normalized = symbol.toUpperCase
    val urlSuffix = s"/$normalized/company?&$apiKey"

    baseUrl + urlSuffix
  }

}
