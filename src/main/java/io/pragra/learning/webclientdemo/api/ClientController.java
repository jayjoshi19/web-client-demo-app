package io.pragra.learning.webclientdemo.api;

import io.pragra.learning.webclientdemo.dto.UserResponseDTO;
import io.pragra.learning.webclientdemo.entity.GitUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;
import java.util.Optional;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    WebClient webClient;

    @GetMapping("/getUserDetails")
    public GitUser getData(@RequestParam String username) {
        GitUser gitUser = webClient
                .get()
                .uri("https://api.github.com/users/" + username)
                .header("username", "Jay1212")
                .header("password", "J@111")
                .retrieve()
                .bodyToMono(GitUser.class)
                .timeout(Duration.ofMillis(3000))
                .block();

        System.out.println(gitUser);
        return gitUser;
    }

    @GetMapping("/user")
    public ResponseEntity<UserResponseDTO> client(@RequestParam String username){

        if ((!validateUserName(username))){
            UserResponseDTO userResponseDTO = UserResponseDTO
                    .builder()
                    .statusCode("1010")  // failure code
                    .statusDesc("Invalid username")
                    .build();

            ResponseEntity<UserResponseDTO> responseEntity = ResponseEntity
                    .ok()
                    .body(userResponseDTO);

            return responseEntity;
        }

        Optional<GitUser> tempUser;
        try {
            GitUser gitUser = webClient
                    .get()
                    .uri("https://api.github.com/users/" + username)
                    .header("username", "Jay1212")
                    .header("password", "J@111")
                    .retrieve()
                    .bodyToMono(GitUser.class)
                    .timeout(Duration.ofMillis(3000))
                    .block();

            assert gitUser != null;
            tempUser = Optional.of(gitUser);

            System.out.println(gitUser);
        } catch (Exception e) {
            UserResponseDTO userResponseDTO = UserResponseDTO.builder()
                    .statusCode(e.getMessage().substring(0, 4))
                    .statusDesc(e.getMessage())
                    .build();

            ResponseEntity<UserResponseDTO> responseEntity = ResponseEntity
                    .ok()
                    .header("test", "test1234")
                    .body(userResponseDTO);

            return responseEntity;
        }

        //Optional<GitUser> tempUser = Optional.ofNullable(gitUser);
        if (tempUser.isPresent()){
            GitUser user = tempUser.get();
            UserResponseDTO userResponseDTO = UserResponseDTO.builder()
                    .data(user)
                    .statusCode("SS0")  // success
                    .build();

            ResponseEntity<UserResponseDTO> responseEntity = ResponseEntity
                    .ok()
                    .header("test", "test1223")
                    .body(userResponseDTO);

            return responseEntity;
        } else {
            UserResponseDTO userResponseDTO = UserResponseDTO
                    .builder()
                    .statusCode("1011")  //failure code
                    .statusDesc("Username '" + username + "' does not exist")
                    .build();

            ResponseEntity<UserResponseDTO> responseEntity = ResponseEntity
                    .ok()
                    .body(userResponseDTO);

            return responseEntity;
        }

        //return responseEntity;
    }

    private boolean validateUserName(String username) {
        // validate for alphanumeric username
        return username.matches("^[a-zA-Z0-9]*$");
    }
}