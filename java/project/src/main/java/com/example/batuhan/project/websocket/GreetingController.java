package com.example.batuhan.project.websocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

@RestController
@CrossOrigin(origins = "*")
public class GreetingController {


	@MessageMapping("/hello")
	@SendTo("/topic")
	public Greeting greeting(HelloMessage message) throws Exception {
		//Thread.sleep(1000);

		return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
	}

}
