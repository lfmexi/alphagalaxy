package org.lfmexi.alphagalaxy.repositories;

import org.lfmexi.alphagalaxy.exceptions.DuplicatedRecordException;

public interface Repository<E> {
  void insert(E element) throws DuplicatedRecordException;

  int update(E newField);

  int delete(E element);

  E findById(Object id);

  E[] findAll();
}
