package com.instinctools.egor.mentoring.spring.core.factory;

import com.instinctools.egor.mentoring.spring.core.repository.BookRepository;
import com.instinctools.egor.mentoring.spring.core.repository.UserRepository;

public interface RepositoryFactory {
    UserRepository getUserRepo();

    BookRepository getBookRepo();
}
