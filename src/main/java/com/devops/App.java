
package com.devops;

import java.io.InputStream;
import java.util.Properties;

public class App {

    public static void main(String[] args) {

        Properties properties = new Properties();

        try (InputStream input =
                App.class.getClassLoader()
                         .getResourceAsStream("build-info.properties")) {

            if (input == null) {
                System.out.println("Build information not found");
                return;
            }

            properties.load(input);

            System.out.println("Maven Profile Demo");
            System.out.println("Environment: "
                    + properties.getProperty("environment"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
