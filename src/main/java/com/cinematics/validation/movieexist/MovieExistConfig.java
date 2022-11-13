//package com.cinematics.validation.movieexist;
//
//import com.cinematics.controller.MovieController;
//import lombok.RequiredArgsConstructor;
//import org.springframework.aop.support.DefaultPointcutAdvisor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//@RequiredArgsConstructor
//@ComponentScan(basePackageClasses = MovieController.class)
//public class MovieExistConfig
//{
//  private final MovieExistAspect movieExistAspect;
//
//  @Bean
//  public DefaultPointcutAdvisor movieExistAdvisor()
//  {
////    JdkRegexpMethodPointcut pointcut = new JdkRegexpMethodPointcut();
//    //  pointcut.setPattern("com.cinematics.controller.MovieController.createMovie");
//    return new DefaultPointcutAdvisor(movieExistAspect);
//  }
//}
