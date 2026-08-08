package com.nnit.college360.integration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class NotificationClient {
    private final WebClient webClient;
    private final String baseUrl;

    public NotificationClient(WebClient webClient, @Value("${college360.integrations.notification-base-url}") String baseUrl) {
        this.webClient=webClient; this.baseUrl=baseUrl;
    }

    public Mono<String> sendStudentCreatedNotification(Long studentId) {
        return webClient.post().uri(baseUrl + "/api/v1/notifications/student-created/{studentId}", studentId)
                .retrieve().bodyToMono(String.class);
    }
}
