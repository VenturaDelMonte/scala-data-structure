package org.data.structures.tree


class Node [K <% Ordered[K], T]  (
                                   _key : K,
                                   _value : T,
                                   _left : Node[K, T] = null,
                                   _right : Node[K, T] = null,
                                   _parent : Node[K, T] = null) {

  var key = _key
  var value = _value
  var left = _left
  var right = _right
  var parent = _parent

  def compare(that: Node [K, T])(implicit ordering: Ordering[K]): Int = {
    return ordering.compare(this.key, that.key)
  }

  def compare(thatKey: K)(implicit ordering: Ordering[K]): Int = {
    return ordering.compare(this.key, thatKey)
  }

}
