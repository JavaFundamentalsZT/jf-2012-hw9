/**
 * Our very special List implementation
 */
trait List[+A] {
  /** The first element */
  def head: A
  /** The rest of the elements */
  def tail: List[A]

  def map[B](f: A => B): List[B]
  def flatMap[B](f: A => List[B]): List[B]
  def filter(f: A => Boolean): List[A]

  // Concatenate two lists
  def concat[B >: A](that: List[B]): List[B] = this match {
    case Empty => that
    case NonEmpty(head, tail) => NonEmpty(head, tail concat that)
  }
}

/** The empty list, also known as Nil */
case object Empty extends List[Nothing] {
  def head = throw new UnsupportedOperationException("Empty.head")
  def tail = throw new UnsupportedOperationException("Empty.tail")
  
  def map[B](f: Nothing => B): List[B] = Empty
  def flatMap[B](f: Nothing => List[B]): List[B] = Empty
  def filter(f: Nothing => Boolean): List[Nothing] = Empty

  override def toString = "Empty"
}

/** The non-empty list, also known as Cons or :: */
case class NonEmpty[A](head: A, tail: List[A]) extends List[A] {
  /* Assignment: implement map. filter, flatMap */
  /* Maximum points without implementing flatMap 0.9 */
  
  /**
   * Return a new list where function f has been applied to all elements.
   * 
   * TODO implement this!
   */
  def map[B](f: A => B): List[B] = ???

  /**
   * Return a new list where function f has been applied to all elements
   * and the resulting list of lists has been flattened.
   * 
   * TODO implement this!
   */
  def flatMap[B](f: A => List[B]): List[B] = ???

  /**
   * Return a new list which contains only the elements matching the predicate.
   * 
   * TODO implement this!
   */
  def filter(predicate: A => Boolean): List[A] = ???
  
  override def toString: String = head.toString + ", " + tail.toString
}

object ListApp {

  def even(x: Int) = x % 2 == 0

  def testFilter() {
    val list = List(1,2,3,4,5,6)
    assert ((list filter even) == List(2,4,6))
  }
  
  def testFilter1() = ???

  def testMap() {
    val list = List(1, 2, 3)
    assert ((list map { x => x + 1 }) == List(2,3,4))
  }
  
  def testMap1() = ???

  def testFlatMap() {
    val strings = List("abc", "", "def")
    val chars = strings flatMap { str => List(str.toCharArray():_*) }
    assert (chars == List('a', 'b', 'c', 'd', 'e', 'f'))
  }
  
  def testFlatMap1() = ???

  def main(args: Array[String]) {
    testFilter()
    testMap()
    testFlatMap()
    /**
     * TODO Please implement 3 more tests.
     * 
     * 1 test for filter
     * 1 test for map
     * 1 test for flat map
     * 
     * Either some other data, some corner case or something you
     * think is important enough to test.
     */
    testFilter1()
    testMap1()
    testFlatMap1()
    println("Success!")
  }

  // A factory method for lists.
  //  A* = A... in java (variadic)
  //  :_* = treat collection as variable arguments
  def List[A](elems: A*): List[A] = elems.toList match {
    case Nil => Empty
    case ::(head, tail) => NonEmpty(head, List(tail:_*))
  }
  
}