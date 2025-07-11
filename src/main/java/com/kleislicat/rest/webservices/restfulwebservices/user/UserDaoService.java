package com.kleislicat.rest.webservices.restfulwebservices.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class UserDaoService {

  // JPA/Hibernate > Database
  // UserDaoService > Static List

  private static List<User> users = new ArrayList<>();
  private static int usersCount = 0;

  static {
    users.add(new User(++usersCount, "Adam", LocalDate.now().minusYears(30)));
    users.add(new User(++usersCount, "Eve", LocalDate.now().minusYears(25)));
    users.add(new User(++usersCount, "Jim", LocalDate.now().minusYears(20)));
  }

  public List<User> findAll() {
    return users;
  }

  public User save(User user) {
    user.setId(++usersCount);
    users.add(user);
    return user;
  }

  public User findOne(int id) {
    return users.stream()
        .filter(user -> user.getId().equals(id))
        .findFirst()
        .orElse(null);
  }

  public void deleteById(int id ){
    users.removeIf(user -> user.getId().equals(id));
  }

}
