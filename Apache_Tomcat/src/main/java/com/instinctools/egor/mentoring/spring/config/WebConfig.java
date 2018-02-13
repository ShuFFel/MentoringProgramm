package com.instinctools.egor.mentoring.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    @Override
    public void configureContentNegotiation(
                    final ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(true).
                        ignoreAcceptHeader(true).
                        useJaf(false).
                        defaultContentType(MediaType.TEXT_HTML).
                        mediaType("html", MediaType.TEXT_HTML).
                        mediaType("xml", MediaType.APPLICATION_XML).
                        mediaType("json", MediaType.APPLICATION_JSON);
    }

}
