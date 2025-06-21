package com.kleislicat.rest.webservices.restfulwebservices.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

  // value is an alias for path
  //@RequestMapping(method = RequestMethod.GET,value="/hello-world")
  //@RequestMapping(method = RequestMethod.GET, path = "/hello-world")
  @GetMapping(path="/hello-world")
  public String helloWorld() {
    return "Hello World";
  }

  @GetMapping(path="/hello-world-bean")
  public HelloWorldBean helloWorldBean() {
    return new HelloWorldBean("Hello World");
  }

  // Path Parameters
  @GetMapping(path="/hello-world/path-variable/{name}")
  public HelloWorldBean helloWorldPathVariable(@PathVariable String  name) {
    return new HelloWorldBean(String.format("Hello %s", name));
  }

}
