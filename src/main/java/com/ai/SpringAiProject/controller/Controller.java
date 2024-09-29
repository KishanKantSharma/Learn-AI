package com.ai.SpringAiProject.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ai.SpringAiProject.service.ChatService;

import io.github.wimdeblauwe.htmx.spring.boot.mvc.HtmxResponse;
import io.github.wimdeblauwe.htmx.spring.boot.mvc.HxRequest;

@org.springframework.stereotype.Controller
@CrossOrigin
public class Controller {
	
	private static final Logger log = LoggerFactory.getLogger(Controller.class); 
	
	private ChatService service;
	private ChatClient chatClient; 	
	
	public Controller(ChatService service, ChatClient.Builder builder) {
		this.service = service;
		this.chatClient = builder.build();
	}
	
	@GetMapping("/chat")
    public String getChatPage() {
        return "chat"; 
    }
	
	@GetMapping("/api/learn")
	public HtmxResponse getResponse(@RequestParam String topic, String level, Model model) {
		
		String response = service.modifyPrompt(topic, level);
		
		model.addAttribute("learnResponse", response);
		
		System.out.println(response);
	    
	    return HtmxResponse.builder()
	            .view("learnResponse :: learnFragment")
	            .build();
	}
	
	@GetMapping("/home")
	public String home() {
		return "home";
	}
	
	@GetMapping("/")
	public String bar_default() {
		return "layout"; 
	}
	
	 @GetMapping("/learn")
	 public String getQuestionAnswerPage() {
	    return "learn";
	 }
	
	 @HxRequest
	 @PostMapping("/api/chat")
	 public HtmxResponse generate(@RequestParam String message, Model model) {
	    log.info("User Message: {}", message);
	    String response = chatClient.prompt()
	                	.user(message)
	                	.call()
	                	.content();

	    model.addAttribute("response",response);
	    model.addAttribute("message",message);

	    return HtmxResponse.builder()
	                .view("response :: responseFragment")
	                .view("history :: messageFragment")
	                .build();
	    }
		
}
