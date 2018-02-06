package com.instinctools.egor.mentoring.spring.dal.dao.book;

import com.instinctools.egor.mentoring.spring.dal.entities.BookEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookDao extends CrudRepository<BookEntity, String> {
    List<BookEntity> findByOwnerId(String id);
}
