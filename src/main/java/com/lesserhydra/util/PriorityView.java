package com.lesserhydra.util;

//TODO: Couldn't this be represented with a Collection<Pair> interface?
//      Sure, this way might be slightly more convenient, but why reinvent the wheel?

/**
 * Represents a "collection" of elements ordered by priority.
 *
 * @param <P> Priority type
 * @param <E> Element type
 * @author Justin Lawen
 */
public interface PriorityView<P, E> extends View<E> {
  
  /**
   * Adds an element with the given priority.
   *
   * @param priority
   * @param element
   */
  void add(P priority, E element);
  
  /**
   * Removes an element.
   *
   * @param element
   * @return
   */
  boolean remove(E element);
  
}
