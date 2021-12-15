package com.test.automation.utils;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.util.logging.Logger;

import static org.junit.jupiter.api.extension.ExtensionContext.Namespace.GLOBAL;

public class Hooks implements BeforeAllCallback, ExtensionContext.Store.CloseableResource {

    private static boolean started = false;
    private final Logger LOG = Logger.getLogger(getClass().getName());

    @Override
    public void beforeAll(ExtensionContext context) {
        if (!started) {
            started = true;
            LOG.info("executing beforeAll hook");

            context.getRoot().getStore(GLOBAL).put("before all tests", this);
        }
    }

    @Override
    public void close() {
        LOG.info("executing afterAll hook");
    }
}