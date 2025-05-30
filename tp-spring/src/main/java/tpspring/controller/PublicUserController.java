package tpspring.controller;

import tpspring.model.User;
import jakarta.servlet.ServletException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import tpspring.service.UserService;

@RestController
@RequestMapping("api/v2/public/user")
@AllArgsConstructor
@CrossOrigin
public class PublicUserController {
    private final UserService userService;

    @PostMapping(path = "new", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void newAccount(@RequestBody final UserDTO user) {
        try {
            userService.newAccount(user.login(), user.pwd());
        }catch(final IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not possible");
        }
    }

    @PostMapping(path = "login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void login(@RequestBody final UserDTO user) {
        try {
            final boolean loginSuccess = userService.login(user.login(), user.pwd());

            if(!loginSuccess) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Already logged in. Log out first");
            }
        }catch(final ServletException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not possible to log in");
        }
    }
}


record UserDTO(String login, String pwd) {
    public void patch(final User user) {
        if(user.getAddress() != null) {
            user.setName(login);
        }
        if(user.getName() != null) {
            user.setPwd(pwd);
        }
    }
}
