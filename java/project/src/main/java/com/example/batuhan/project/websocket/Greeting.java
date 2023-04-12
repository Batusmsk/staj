package com.example.batuhan.project.websocket;

import lombok.Data;

@Data
public class Greeting {

	  private String content;

	  public Greeting() {
	  }

	  public Greeting(String content) {
	    this.content = content;
	  }

	  public String getContent() {
	    return content;
	  }

	}