# ibm-heapdump-spring-boot-actuator
A lib to make the Spring Boot Actuator `/actuator/heapdump` endpoint work on IBM JRE

# Usage

0. Clone the repo and run `mvn clean install` to make available in the local Maven repository
1. Add the artifact as a dependency
```
<dependency>
    <groupId>com.github.sa1nt</groupId>
    <artifactId>ibm-heapdump-spring-boot-actuator</artifactId>
    <version>0.2</version>
</dependency>
```

2. Configure `IBMHeapdumpMvcEndpoint` bean
```
import com.github.sa1nt.ibmheapdump.IBMHeapdumpMvcEndpoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IbmHeapdumpConfiguration {
    @Bean
    public IBMHeapdumpMvcEndpoint ibmHeapdumpMvcEndpoint() {
        return new IBMHeapdumpMvcEndpoint();
    }
}
```
