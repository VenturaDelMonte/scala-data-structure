package org.data.structures.hashmap

import org.junit.runner.RunWith
import org.scalatest._
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class HashmapTest extends FlatSpec with Matchers {
  "An empty hashmap" should "say it's empty" in {
    val map : Hashmap[String, Int] = new Hashmap[String, Int]
    map("none") should be (None)
  }

  "A map filled with two collided elements" should "work as expected" in {
    val map : Hashmap[String, Int] = new Hashmap[String, Int]
    map("one") = 12
    map("eno") = 13
    map("word") = 13
    map("neo") should be (None)
    map("one") should be (Option(12))
    map("eno") should be (Option(13))
    map("word") should be (Option(13))
    map.size should be (3)
  }
}
