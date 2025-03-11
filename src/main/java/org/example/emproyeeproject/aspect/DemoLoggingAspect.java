package org.example.emproyeeproject.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class DemoLoggingAspect {

    // setup logger
    Logger logger = Logger.getLogger(getClass().getName());
}
