package de.htwberlin.webtech.todolist.web;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloWorldController {

    @GetMapping(path = "/")
    public ModelAndView showHelloWorld(){
        return new ModelAndView("helloworld");
    }

    @GetMapping(path = "/test")
    public ModelAndView showTest(){
        return new ModelAndView("test");
    }
}
