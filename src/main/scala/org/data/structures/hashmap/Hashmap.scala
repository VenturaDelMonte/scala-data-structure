package org.data.structures.hashmap

import org.data.structures.tree.{Node => Bucket}

class Hashmap [K <% Ordered[K], V] {

  private val BUCKETS_COUNT = 32
  private val buckets: Array[Bucket[K, V]] = new Array[Bucket[K, V]](BUCKETS_COUNT)
  private var length: Int = 0

  private def getHash(key: K): Int = {
    var ret = key.hashCode
    ret = ret >>> 16
    ret = ret % BUCKETS_COUNT
    ret
  }

  private def bstSearch(target: K, _root: Bucket[K, V]): Bucket[K, V] = {
    var root = _root
    while (root != null) {
      val cmp = root.compare(target)
      if (cmp == 0) {
        return root
      } else if (cmp < 0) {
        root = root.left
      } else {
        root = root.right
      }
    }
    null
  }

  def size() : Int = {
    length
  }

  def apply(key: K): Option[V] = {
    val hash = getHash(key)
    val node = buckets(hash)

    if (node != null) {
      val target = bstSearch(key, node)
      if (target != null) {
        return Some(target.value)
      }
    }
    None
  }

  def update(key: K, value: V): Unit = {
    val hash = getHash(key)
    val root = buckets(hash)
    if (root == null) {
      buckets(hash) = new Bucket[K, V](key, value)
      length += 1
    } else {
      def loop(node : Bucket[K, V]): Unit = {
        if (root.compare(key) < 0) {
          // lt -> goes to left
          if (root.left == null) {
            val target = new Bucket[K, V](key, value)
            root.left = target
            target.parent = root
            length += 1
          } else {
           loop(root.left)
          }
        } else if (root.compare(key) > 0) {
          // gt -> goes to right
          if (root.right == null) {
            val target = new Bucket[K, V](key, value)
            root.right = target
            target.parent = root
            length += 1
          } else {
            loop(root.right)
          }
        } else {
          // eq -> update value
          root.value = value
        }
      }
      loop(root)
    }
  }

}

