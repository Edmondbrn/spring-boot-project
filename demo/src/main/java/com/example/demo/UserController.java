// package com.example.demo;

// import com.example.demo.entity.User;
// import com.example.demo.repository.UserRepository;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import java.util.List;

// // @Controller
// public class UserController {

//     private final UserRepository userRepository;

//     public UserController(UserRepository userRepository) {
//         this.userRepository = userRepository;
//     }

//     @GetMapping("/users")
//     public String index(@RequestParam(required = false) String email, Model model) {
//         List<User> users;
//         if (email != null) {
//             users = userRepository.findByEmail(email);
//         } else {
//             users = userRepository.findAll();
//         }
//         model.addAttribute("users", users);
//         return "users";
//     }
// }