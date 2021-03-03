package com.stocker.api
package models

import io.circe.Decoder
import io.circe.generic.semiauto.deriveDecoder

case class Company(
                    logo: String,
                    exchange: String,
                    exchangeSymbol: String,
                    `type`: String,
                    name: String,
                    symbol: String,
                    listdate: String,
                    country: String,
                    sector: String,
                    marketcap: Long,
                    employees: Long,
                    similar: List[String],
                    ceo: String,
                    url: String,
                    description: String,
                    active: Boolean)

object Company {

  implicit val companyDecoder: Decoder[Company] = deriveDecoder[Company]
}