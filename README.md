# ibm-heapdump-spring-boot-actuator
A library that that makes the Spring Boot Actuator `/actuator/heapdump` endpoint work both on IBM J9 JVM and on HotSpot JVM. 

First tries to see if the app is on J9 JVM, and falls back to Spring Boot default if it doesn't seem so. 

# Usage

## Use the [Spring Boot starter](https://github.com/sa1nt/ibm-heapdump-spring-boot-actuator-auto-starter) 

1. Add the Starter dependency to POM
```
<dependency>
    <groupId>io.github.sa1nt</groupId>
    <artifactId>ibm-heapdump-spring-boot-actuator-starter</artifactId>
    <version>0.8</version>
</dependency>
```

## Configure it yourself

1. Add the artifact as a dependency
```
<dependency>
    <groupId>io.github.sa1nt</groupId>
    <artifactId>ibm-heapdump-spring-boot-actuator</artifactId>
    <version>0.8</version>
</dependency>
```

2. Configure `J9HeapdumpMvcEndpoint` bean
```
import io.github.sa1nt.ibmheapdump.J9HeapdumpMvcEndpoint;
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

# Build

1. Clone the repo
2. Run `mvn clean package`
