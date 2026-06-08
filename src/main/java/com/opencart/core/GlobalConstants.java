package com.opencart.core;

import static javax.swing.plaf.synth.Region.SEPARATOR;

public class GlobalConstants {
    public static final int LONG_TIMEOUT = 30;
    public static final int SHORT_TIMEOUT = 3;

    public static final String PROJECT_PATH = System.getProperty("user.dir");
    public static final String SEPARATOR = System.getProperty("file.separator");
    public static final String OS_NAME = System.getProperty("os.name");

    public static final String ENVIRONMENT_CONFIG_PATH = PROJECT_PATH + SEPARATOR + "src" + SEPARATOR + "test" + SEPARATOR + "resources" + SEPARATOR + "config" + SEPARATOR;

    public static final String UPLOAD_PATH = PROJECT_PATH + SEPARATOR + "uploadFiles" + SEPARATOR;

    public static final String ADMIN_USERNAME = "automationfc";
    public static final String ADMIN_PASSWORD = "Auto222@@@";

}
