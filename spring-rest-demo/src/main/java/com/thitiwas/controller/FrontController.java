package com.thitiwas.controller;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class FrontController {
    private final static Logger logger = Logger.getLogger(FrontController.class);
    private void init()
    {
        PropertyConfigurator.configure("C:\\logJar\\log4j.properties");
        logger.debug("==================== start ====================");
    }
}
