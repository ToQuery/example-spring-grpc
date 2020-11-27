package io.github.toquery.example.spring.grpc;

import io.grpc.BindableService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.netty.shaded.io.netty.handler.ssl.util.SelfSignedCertificate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.util.Map;

@Slf4j
@Component("grpcLauncher")
public class GrpcLauncher {

    private Server server;
    @Value("${grpc.server.port}")
    private Integer grpcServerPort;
    /**
     * GRPC 服务启动方法
     * @param grpcServiceBeanMap
     */
    public void grpcStart(Map<String, Object> grpcServiceBeanMap) {
        try{
            SelfSignedCertificate ssc = new SelfSignedCertificate();
            ServerBuilder serverBuilder = ServerBuilder.forPort(grpcServerPort).useTransportSecurity(ssc.certificate(), ssc.privateKey());
            for (Object bean : grpcServiceBeanMap.values()){
                serverBuilder.addService((BindableService) bean);
                log.info(bean.getClass().getSimpleName() + " is register in Spring Boot");
            }
            server = serverBuilder.build().start();
            log.info("grpc server is started at " +  grpcServerPort);
            server.awaitTermination();
            Runtime.getRuntime().addShutdownHook(new Thread(this::grpcStop));
        } catch (Exception exception){
            exception.printStackTrace();
        }
    }
    /**
     * GRPC 服务Stop方法
     */
    private void grpcStop(){
        if (server != null){
            server.shutdownNow();
        }
    }
}
