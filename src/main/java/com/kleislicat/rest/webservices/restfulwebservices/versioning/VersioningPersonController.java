package com.kleislicat.rest.webservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {

  // 1) - URI - Twitter
  @GetMapping("/v1/person")
  public PersonV1 getFirstVersionOfPerson() {
    return new PersonV1("Bob Charlie");
  }

  @GetMapping("/v2/person")
  public PersonV2 getSecondVersionOfPerson() {
    return new PersonV2(new Name("Bob", "Charlie"));
  }

  // 2) - Request parameter - Amazon
  @GetMapping(path = "/person", params = "version=1")
  public PersonV1 getFirstVersionOfPersonRequestParameter() {
    return new PersonV1("Bob Charlie");
  }

  @GetMapping(path = "/person", params = "version=2")
  public PersonV2 getSecondVersionOfPersonRequestParameter() {
    return new PersonV2(new Name("Bob", "Charlie"));
  }

  // 3) - (Custom) headers versioning - Microsoft
  @GetMapping(path = "/person/header", headers = "X-API-VERSION=1")
  public PersonV1 getFirstVersionOfPersonRequestHeader() {
    return new PersonV1("Bob Charlie");
  }

  @GetMapping(path = "/person/header", headers = "X-API-VERSION=2")
  public PersonV2 getSecondVersionOfPersonRequestHeader() {
    return new PersonV2(new Name("Bob", "Charlie"));
  }

  // 4) Media type versioning (a.k.a. "content negotiation" or "accept header") - GitHub
  @GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v1+json")
  public PersonV1 getFirstVersionOfPersonAcceptHeader() {
    return new PersonV1("Bob Charlie");
  }

  @GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v2+json")
  public PersonV2 getSecondVersionOfPersonAcceptHeader() {
    return new PersonV2(new Name("Bob", "Charlie"));
  }
}
