package common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MyLogger { // declare a separate class to avoid redundant LogManager imports everywhere else
    public static final Logger log = LogManager.getLogger();
}
