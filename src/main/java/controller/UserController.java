package controller;

import service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

//CRUD
//    @GetMapping("")
//
//    void deleteById(ID id);
//
//    boolean existsById(ID id);
//
//    List<T> findAll();
//
//    Optional<T> findById(Long id);
//
//    T save(T persisted);
}
