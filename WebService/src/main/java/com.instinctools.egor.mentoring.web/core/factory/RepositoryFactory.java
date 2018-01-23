package com.instinctools.egor.mentoring.web.core.factory;

import com.instinctools.egor.mentoring.web.core.repository.BookRepository;
import com.instinctools.egor.mentoring.web.core.repository.UserRepository;

public interface RepositoryFactory {
    UserRepository getUserRepo();
    BookRepository getBookRepo();
}
