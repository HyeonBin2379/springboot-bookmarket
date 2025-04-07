package org.project.springbootbookmarket.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String welcomeMethod() throws IOException {
        List<String> outHtmlLines = Files.readAllLines(Paths.get("src\\main\\resources\\static\\welcome.html"));

        return String.join("\n", outHtmlLines);
    }
}
