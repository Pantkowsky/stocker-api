package com.stocker.api
package client

import secrets.Keys

import sttp.client3.{HttpURLConnectionBackend, Response, UriContext, basicRequest}

class PolygonClient {

  private val apiKey = s"apiKey=${Keys.apiKey}"
  private val baseUrl = s"https://api.polygon.io/v1/meta/symbols"

  /**
   * Fetches the metadata about the given company
   * @param symbol the ticker of the company for which the metadata is to be fetched
   * @return json response containing the metadata about the company
   */
  def fetchDetailsForTicker(symbol: String) : Response[Either[String, String]] = {

    val normalized = symbol.toUpperCase
    val urlSuffix = s"/$normalized/company?&$apiKey"

    fetch(baseUrl + urlSuffix)
  }

  /**
   * Fetches the latests news for the given ticker.
   * @param symbol the ticker of the company for which the news are fetched
   * @return json response containing the list of news
   */
  def fetchNewsForTicker(symbol: String) : Response[Either[String, String]] = {

    val normalized = symbol.toUpperCase
    val urlSuffix = s"/$normalized/news?perpage=50&page=1&$apiKey"

    fetch(baseUrl + urlSuffix)

  }

  private def fetch(url: String) : Response[Either[String, String]] = {

    val request = basicRequest.get(uri"$url")

    val backend = HttpURLConnectionBackend()
    request.send(backend)
  }
}
