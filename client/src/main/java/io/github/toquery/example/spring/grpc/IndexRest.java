package io.github.toquery.example.spring.grpc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.net.ssl.SSLException;

@RestController
public class IndexRest {

    @Autowired
    private HelloWorldClient helloWorldClient;

    @RequestMapping("/")
    public String helloWorld() throws SSLException {
        return helloWorldClient.sayHello();
    }
}
