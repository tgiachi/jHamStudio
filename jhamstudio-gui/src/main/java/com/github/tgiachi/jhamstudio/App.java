/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.github.tgiachi.jhamstudio;


import com.tgiachi.jhamstudio.api.annotations.HamStudioPlugin;
import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {


    public static void main(String[] args) {
        Application.launch(HamStudioApplication.class, args);
    }
}
