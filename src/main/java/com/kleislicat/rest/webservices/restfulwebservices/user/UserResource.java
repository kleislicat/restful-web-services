package com.kleislicat.rest.webservices.restfulwebservices.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {

  private UserDaoService service;

  public UserResource(UserDaoService service) {
    this.service = service;
  }

  @GetMapping("/users")
  public List<User> retrieveAllUsers() {
    return service.findAll();
  }


  /*
  {
    "id": 1,
    "name": "Adam",
    "birthDate": "1995-06-23",
    "_links": {                 <------ HATEOAS: tell consumer how to perform subsequent actions
        "all-users": {
            "href": "http://localhost:8080/users"
        }
    }
}
   */
  @GetMapping("/users/{id}")
  public EntityModel<User> retrieveUser(@PathVariable int id) {
    User user = service.findOne(id);

    if(user==null) {
      throw new UserNotFoundException("id:"+id);
    }

    EntityModel<User> entityModel = EntityModel.of(user);

    WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
    entityModel.add(link.withRel("all-users"));

    return entityModel;
  }

  @DeleteMapping("/users/{id}")
  public void deleteUser(@PathVariable int id) {
    service.deleteById(id);
  }

  @PostMapping("/users")
  public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
    User savedUser = service.save(user);

    // We want to return a 201 (Created) with a URI to the newly created user e.g. /users/4
    // The consumer of this request can see this result in the 'location' field of 'response headers'.
    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(savedUser.getId())
        .toUri();

    return ResponseEntity.created(location).build();
  }
}
