package com.nncollege.integration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class NotificationClient {
 private final WebClient webClient; private final String baseUrl;
 public NotificationClient(WebClient webClient,@Value("${integration.notification.base-url:http://localhost:8090}") String baseUrl){this.webClient=webClient;this.baseUrl=baseUrl;}
 public void send(String recipient,String subject,String message){
   webClient.post().uri(baseUrl+"/api/v1/notifications").bodyValue(new NotificationRequest(recipient,subject,message)).retrieve().toBodilessEntity().subscribe();
 }
 public record NotificationRequest(String recipient,String subject,String message) {}
}