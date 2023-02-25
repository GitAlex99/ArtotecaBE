package io.reflectoring.ArtotecaBE.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class TestController {

    @RequestMapping({"/hello"})
    public String test(){
        return "test___";
    }
}
