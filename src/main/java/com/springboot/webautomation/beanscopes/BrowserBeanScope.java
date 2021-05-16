package com.springboot.webautomation.beanscopes;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.SimpleThreadScope;

import java.util.Objects;

public class BrowserBeanScope extends SimpleThreadScope {

    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        Object object = super.get(name, objectFactory);
        SessionId sessionId = ((RemoteWebDriver) object).getSessionId();
        if (Objects.isNull(sessionId)) {
            super.remove(name);
            object = super.get(name, objectFactory);
        }

        return object;
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {
    }
}