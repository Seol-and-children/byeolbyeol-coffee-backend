package com.seolandfriends.byeolbyeolcoffee.admin.command.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class example {
	@GetMapping("admin")
	public void menu() {

	}
}