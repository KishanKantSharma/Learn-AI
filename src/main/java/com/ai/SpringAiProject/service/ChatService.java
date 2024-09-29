package com.ai.SpringAiProject.service;

import java.util.Map;

import org.springframework.ai.azure.openai.AzureOpenAiChatModel;
import org.springframework.ai.azure.openai.AzureOpenAiChatOptions;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

@Service
public class ChatService {
	
	private AzureOpenAiChatModel chatModel; 
	
	public ChatService(AzureOpenAiChatModel chatModel) {
		this.chatModel = chatModel;
	}
	
	public String getResponse(String prompt) {
		return chatModel.call(prompt); 
	}
	
	public String modifyPrompt(String topic, String level) {
		String modifiedPrompt; 
		
		switch(level.toLowerCase()) {
		
		case "beginner": 
			modifiedPrompt = "Explain me the {topic} as you are teaching to a child.."
					+ "Take fun examples and keep it brief, answer in paragraph not points."; 
			System.out.println("This prompt is active");
			break; 
		
		case "intermediate": 
			modifiedPrompt = "Explain me {topic} in simpler terms. Also explain the core concept behind it in simple language.. Keep it brief"
					+ "and use appropiate examples, answer in paragraph not points."; 
			break; 
			
		case "advanced": 
			modifiedPrompt = "I want to gather core knowledge related to this {topic}. Explain the concept in detail,"
					+ "explore in depth with the help of example, answer in paragraph not points.";
			break; 
	
		default: 
			modifiedPrompt = "Explain me this {topic}";
			break; 
			
		}
		
		PromptTemplate promptTemplate = new PromptTemplate(modifiedPrompt);
		Prompt prompt = promptTemplate.create(Map.of("topic", topic));
	
		AzureOpenAiChatOptions options = AzureOpenAiChatOptions.builder()
								.withMaxTokens(100)
								.withTemperature((float) 0.4)
								.build(); 	
		
		ChatResponse finalPrompt = chatModel.call(new Prompt(prompt.getContents(), options));
		
		return finalPrompt.getResult().getOutput().getContent();
		}
	
		
		public String getWeatherInfo(String query) {
			
			String llmPrompt = "Tell me the weather of {query} city.. And also tell me the is it good to travel or not in this weather."
					+ "Suggest me top three places recommendation to visit there in points";
			
			PromptTemplate promptTemplate = new PromptTemplate(llmPrompt);
			
			Prompt prompt = promptTemplate.create(Map.of("query", query));
				
			AzureOpenAiChatOptions options = AzureOpenAiChatOptions.builder()
											.withFunction("currentWeather")
											.withMaxTokens(200)
											.withTemperature((float) 0.6)
											.build();
			
			ChatResponse response = chatModel.call(new Prompt(prompt.getContents(), options));
			
			return response.getResult().getOutput().getContent();
						
		}
	
	
	
	
	
}
