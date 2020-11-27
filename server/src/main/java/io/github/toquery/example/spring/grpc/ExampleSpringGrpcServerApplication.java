package io.github.toquery.example.spring.grpc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Map;

/**
 * Spring Boot 启动器
 */
@SpringBootApplication
public class ExampleSpringGrpcServerApplication {
    public static void main(String[] args) {
        // 启动SpringBoot web
        ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(ExampleSpringGrpcServerApplication.class, args);
        Map<String, Object> grpcServiceBeanMap = configurableApplicationContext.getBeansWithAnnotation(GrpcService.class);
        GrpcLauncher grpcLauncher = configurableApplicationContext.getBean("grpcLauncher", GrpcLauncher.class);
        grpcLauncher.grpcStart(grpcServiceBeanMap);
    }
}
