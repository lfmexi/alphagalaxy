package org.lfmexi.alphagalaxy.util;

import java.util.Comparator;

public interface List<E> {
  public int size();

  public boolean add(E element);

  public E get(int index);

  public E remove(int index);

  public E remove(E element);

  public E find(E element);

  public List<E> subListWith(E element, Comparator<E> comparator);

  public E[] toArray(Class<?> componentType);
}
