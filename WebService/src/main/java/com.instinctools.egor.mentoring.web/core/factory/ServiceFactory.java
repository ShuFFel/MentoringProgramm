package com.instinctools.egor.mentoring.web.core.factory;

import com.instinctools.egor.mentoring.web.core.repository.BookRepository;
import com.instinctools.egor.mentoring.web.core.repository.UserRepository;

public interface ServiceFactory {
    UserRepository getUserRepo();
    BookRepository getBookRepo();
}
