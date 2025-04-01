package io.pragra.learning.webclientdemo.api;

import io.pragra.learning.webclientdemo.entity.GitUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    WebClient webClient;

    @GetMapping("/user")
    public GitUser client(@RequestParam String username){
        GitUser responseEntity = webClient
                .get()
                .uri("https://api.github.com/users/"+username)
                .header("username", "Jay1212")
                .header("password", "J@111")
                .retrieve()
                .bodyToMono(GitUser.class)
                .timeout(Duration.ofMillis(3000))
                .block();

        System.out.println(responseEntity);

        return responseEntity;
    }
}