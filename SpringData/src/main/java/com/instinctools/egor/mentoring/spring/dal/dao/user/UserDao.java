package com.instinctools.egor.mentoring.spring.dal.dao.user;

import com.instinctools.egor.mentoring.spring.dal.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends CrudRepository<UserEntity, String> {
}
