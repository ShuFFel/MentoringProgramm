package com.instinctools.egor.mentoring.Web;

import com.instinctools.egor.mentoring.Web.factories.GenericServices;
import com.instinctools.egor.mentoring.Web.web.rest.BookServiceRESTImpl;
import com.instinctools.egor.mentoring.Web.web.rest.SettingRESTService;
import com.instinctools.egor.mentoring.Web.web.rest.UserServiceRESTImpl;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/")
public class Start extends Application {

    private BookServiceRESTImpl bookServiceREST;
    private UserServiceRESTImpl userServiceREST;
    private SettingRESTService settings;

    @Override
    public Set<Class<?>> getClasses() {
        HashSet<Class<?>> h = new HashSet<>();
        h.add(BookServiceRESTImpl.class);
        h.add(UserServiceRESTImpl.class);
        h.add(SettingRESTService.class);
        return h;
    }

    @Override
    public Set<Object> getSingletons() {
        HashSet<Object> h = new HashSet<>();
        configurate();
        h.add(bookServiceREST);
        h.add(userServiceREST);
        h.add(settings);
        return h;
    }

    private void configurate() {
        new GenericServices().invoke();
        settings = new SettingRESTService();
        bookServiceREST = new BookServiceRESTImpl(settings);
        userServiceREST = new UserServiceRESTImpl(settings);
    }
}
