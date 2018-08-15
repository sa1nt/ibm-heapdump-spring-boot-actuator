# ibm-heapdump-spring-boot-actuator
A library that that makes the Spring Boot Actuator `/actuator/heapdump` endpoint work both on IBM J9 JVM and on HotSpot JVM. 

First tries to see if the app is on J9 JVM, and falls back to Spring Boot default if it doesn't seem so. 

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

2. Configure `J9HeapdumpMvcEndpoint` bean
```
import com.github.sa1nt.ibmheapdump.J9HeapdumpMvcEndpoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IBMHeapdumpConfiguration {
    @Bean
    public J9HeapdumpMvcEndpoint ibmHeapdumpMvcEndpoint() {
        return new J9HeapdumpMvcEndpoint();
    }
}
```
