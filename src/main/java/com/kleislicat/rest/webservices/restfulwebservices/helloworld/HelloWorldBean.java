package com.kleislicat.rest.webservices.restfulwebservices.helloworld;

public record HelloWorldBean(String message) {

  @Override
  public String toString() {
    return "HelloWorld{" +
        "message='" + message + '\'' +
        '}';
  }
}
