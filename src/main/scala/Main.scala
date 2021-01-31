package com.stocker.api

import com.stocker.api.client.PolygonClient

object Main extends App {

  val client = new PolygonClient

  val ticker = "tsla"
  // response.body: by default read into an Either[String, String] to indicate failure or success
  println(client.fetchDetailsForTicker(ticker))
}
