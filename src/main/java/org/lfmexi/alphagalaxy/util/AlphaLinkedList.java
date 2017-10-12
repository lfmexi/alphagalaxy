package org.lfmexi.alphagalaxy.util;

import java.lang.reflect.Array;
import java.util.Comparator;

public class AlphaLinkedList<E> implements List<E> {

  private LinkedNode<E> first;
  private LinkedNode<E> last;

  private int size;

  @Override
  public int size() {
    return size;
  }

  @Override
  public boolean add(E e) {
    linkLast(e);
    return true;
  }

  private void linkLast(E element) {
    final LinkedNode<E> lastNode = this.last;
    final LinkedNode<E> newNode = new LinkedNode<E>(lastNode, element, null);
    this.last = newNode;
    if (lastNode == null) {
      this.first = newNode;
    } else {
      lastNode.next = newNode;
    }

    this.size++;
  }

  @Override
  public E find(E element) {
    LinkedNode<E> current = this.first;

    for (int i = 0; i < size; i++) {
      if (current.element.equals(element)) {
        return current.element;
      }
      current = current.next;
    }

    return null;
  }

  @Override
  public E get(int index) {
    if (index >= size) {
      throw new IndexOutOfBoundsException();
    }

    LinkedNode<E> current = this.first;

    for (int i = 0; i < index; i++) {
      current = current.next;
    }

    return current.element;
  }

  @Override
  public List<E> subListWith(E element, Comparator<E> comparator) {
    LinkedNode<E> current = this.first;

    List<E> sublist = new AlphaLinkedList<E>();

    for (int i = 0; i < size; i++) {
      if (comparator.compare(element, current.element) > -1) {
        sublist.add(current.element);
      }

      current = current.next;
    }

    return sublist;
  }

  @Override
  public E remove(int index) {
    if (index >= size) {
      throw new IndexOutOfBoundsException();
    }

    if (size <= 0) {
      return null;
    }

    LinkedNode<E> current = this.first;

    for (int i = 0; i < index; i++) {
      current = current.next;
    }

    LinkedNode<E> previous = current.prev;

    if (previous != null) {
      previous.next = current.next;
    }

    LinkedNode<E> next = current.next;

    if (next != null) {
      next.prev = previous;
    }

    current.prev = null;
    current.next = null;

    size--;

    return current.element;
  }

  @Override
  public E remove(E e) {
    if (size <= 0) {
      return null;
    }

    LinkedNode<E> current = this.first;

    for (int i = 0; i < size; i++) {
      if (e.equals(current.element)) {
        break;
      }
      current = current.next;
    }

    if (current == null) {
      return null;
    }

    LinkedNode<E> previous = current.prev;

    if (previous != null) {
      previous.next = current.next;
    }

    LinkedNode<E> next = current.next;

    if (next != null) {
      next.prev = previous;
    }

    current.prev = null;
    current.next = null;

    size--;

    return current.element;
  }

  @Override
  public String toString() {
    LinkedNode<E> current = this.first;

    StringBuilder stringBuilder = new StringBuilder();

    for (int i = 0; i < size; i++) {
      stringBuilder.append(" ");
      stringBuilder.append(current.element.toString());
      stringBuilder.append(" ");
      current = current.next;
    }

    return String.format("AlphaLinkedList [%s]", stringBuilder.toString());
  }

  @Override
  public E[] toArray(Class<?> componentType) {
    @SuppressWarnings("unchecked")
    E[] resultArray = (E[]) Array.newInstance(componentType, size);

    LinkedNode<E> current = first;

    int i = 0;

    while (current != null) {
      resultArray[i] = current.element;
      current = current.next;
      i++;
    }

    return resultArray;
  }

  private static final class LinkedNode<E> {
    private E element;
    private LinkedNode<E> prev;
    private LinkedNode<E> next;

    LinkedNode(LinkedNode<E> prev, E element, LinkedNode<E> next) {
      this.element = element;
      this.prev = prev;
      this.next = next;
    }
  }
}
