package com.example.baitap1.configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import jakarta.servlet.MultipartConfigElement;
import jakarta.servlet.ServletRegistration;

public class AppInit extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{AppConfiguration.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        MultipartConfigElement multipartConfig = new MultipartConfigElement(
                System.getProperty("java.io.tmpdir"),
                50 * 1024 * 1024,
                100 * 1024 * 1024,
                0
        );
        registration.setMultipartConfig(multipartConfig);
    }
}
