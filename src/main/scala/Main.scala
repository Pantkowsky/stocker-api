package com.stocker.api

object Main extends App {

  val calculator = new Calculator
  println(s"Computing ... ${calculator.compute(5)}")

}

class Calculator {

  def compute(x: Int) : Int = {
    x * x
  }
}
