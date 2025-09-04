package com.nsac.badgecollectorserver;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {
	@GetMapping("/user")
	public User user() {
		return new User(0, "nick", new int[] {1, 2, 3});
	}
}
