package com.spring.springpractice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

  @GetMapping("/auth/signin")
  public String signIn() {
    return "auth/signin";
  }

  @PostMapping("/authsignin")
  public String onSignIn() {
    return "redirect:/";
  }
}
