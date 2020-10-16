package com.youpd.phototsrv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.youpd"})

public class PhototSrvApplication {

  public static void main(String[] args) {
    SpringApplication.run(PhototSrvApplication.class, args);
  }

}
