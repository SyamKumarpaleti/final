package com.springboot.main.library.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.main.library.exception.InvalidIdException;
import com.springboot.main.library.model.Admin;
import com.springboot.main.library.model.Customer;
import com.springboot.main.library.model.User;
import com.springboot.main.library.service.AdminService;
import com.springboot.main.library.service.BookService;
import com.springboot.main.library.service.CustomerBookService;
import com.springboot.main.library.service.CustomerService;
import com.springboot.main.library.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {
	@Autowired
	private Logger logger;
    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private BookService bookService;

    @Autowired
    private CustomerBookService customerBookService;

    
    @PostMapping("/user/login")
    public User login(Principal principal) {
        String username = principal.getName();
        User user = (User) userService.loadUserByUsername(username);
        return user;
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> userLogin(Principal principal) {
        String username = principal.getName();
        User user = (User) userService.loadUserByUsername(username);
        if (user != null) {
            try {
                switch (user.getRole()) {
                    case "CUSTOMER":
                        Customer customer = customerService.getCustomer(user.getId());
                        return ResponseEntity.ok().body(customer);
                    case "Admin":
                        Admin admin = adminService.getAdmin(user.getId());
                        return ResponseEntity.ok().body(admin);
                    default:
                        // Handle other roles or provide an error response
                        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unknown user role");
                }
            } catch (Exception e) {
            	 logger.info("successfully posted");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
    }
}
