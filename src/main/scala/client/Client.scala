package com.stocker.api
package client

import sttp.client3.{HttpURLConnectionBackend, Response, UriContext, basicRequest}

trait Client {

  def fetch(url: String) : Response[Either[String, String]] = {
    val request = basicRequest.get(uri"$url")

    val connection = HttpURLConnectionBackend()
    request.send(connection)
  }
}
