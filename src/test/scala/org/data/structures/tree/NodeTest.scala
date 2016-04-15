package org.data.structures.tree

import org.junit.runner.RunWith
import org.scalatest._
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class NodeTest extends FlatSpec with Matchers  {
 "a node" should "comparable" in {
    val n1: Node[Int, String] = new Node(1, "abc")
    val n2: Node[Int, String] = new Node(2, "abc")
    n1.compare(n2) should be (-1)
  }
}
