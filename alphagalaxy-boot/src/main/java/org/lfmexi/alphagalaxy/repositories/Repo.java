package org.lfmexi.alphagalaxy.repositories;

import java.util.List;
import java.util.function.Predicate;

public interface Repo<T> {
  List<T> find();

  void insert(T t);

  T findById(Long l);

  List<T> filter(Predicate<? super T> predicate);
}
