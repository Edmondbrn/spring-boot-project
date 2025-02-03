// package com.example.demo;

// import com.example.demo.entity.User;
// import com.example.demo.repository.UserRepository;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.stereotype.Component;

// // @Component
// public class DataLoader implements CommandLineRunner {

//     private final UserRepository userRepository;

//     public DataLoader(UserRepository userRepository) {
//         this.userRepository = userRepository;
//     }

//     @Override
//     public void run(String... args) throws Exception {
//         // Créer un nouveau users et l'ajoute à la table MySQL
//         User user = new User("Gugus", "BG@42.com");
//         userRepository.save(user);

//         // Affiche les éléments dans la table users
//         System.out.println("Users in the database:");
//         userRepository.findAll().forEach(System.out::println);
//     }
// }
