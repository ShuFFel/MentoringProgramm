package com.instinctools.egor.mentoring.Web;

import com.instinctools.egor.mentoring.Web.core.factory.EntityFactory;
import com.instinctools.egor.mentoring.Web.core.repository.BookRepository;
import com.instinctools.egor.mentoring.Web.core.repository.UserRepository;
import com.instinctools.egor.mentoring.Web.core.services.BookService;
import com.instinctools.egor.mentoring.Web.core.services.UserService;
import com.instinctools.egor.mentoring.Web.core.services.serviceimpl.BookServiceImpl;
import com.instinctools.egor.mentoring.Web.core.services.serviceimpl.UserServiceImpl;
import com.instinctools.egor.mentoring.Web.dal.mongo.dao.MongoBookDAO;
import com.instinctools.egor.mentoring.Web.dal.mongo.dao.MongoUserDAO;
import com.instinctools.egor.mentoring.Web.dal.mongo.factory.MongoEntityFactory;
import com.instinctools.egor.mentoring.Web.web.rest.BookServiceRESTImpl;
import com.instinctools.egor.mentoring.Web.web.rest.UserServiceRESTImpl;
import com.mongodb.DB;
import com.mongodb.MongoClient;

import javax.ws.rs.core.Application;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Set;

public class Start extends Application {

	private BookServiceRESTImpl bookServiceREST;
	private UserServiceRESTImpl userServiceREST;
	private UserRepository userRepository;
	private BookRepository bookRepository;

	@Override
	public Set<Class<?>> getClasses() {
		HashSet<Class<?>> h = new HashSet<>();
		h.add(BookServiceRESTImpl.class);
		h.add(UserServiceRESTImpl.class);
		return h;
	}

	@Override
	public Set<Object> getSingletons() {
		HashSet<Object> h = new HashSet<>();
		configurate();
		h.add(bookServiceREST);
		h.add(userServiceREST);
		return h;
	}

	private void configurate() {
		DB db = null;
		try {
			db = new MongoClient("localhost", 27017).getDB("test");
			userRepository = new MongoUserDAO(db);
			bookRepository = new MongoBookDAO(db);

		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		BookService bookService = new BookServiceImpl(userRepository, bookRepository);
		UserService userService = new UserServiceImpl(userRepository, bookRepository);
		EntityFactory factory = new MongoEntityFactory();
		bookServiceREST = new BookServiceRESTImpl(bookService, userService, factory);
		userServiceREST = new UserServiceRESTImpl(bookService, userService, factory);
	}
}
