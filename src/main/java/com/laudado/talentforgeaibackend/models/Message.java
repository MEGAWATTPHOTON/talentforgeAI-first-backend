package com.laudado.talentforgeaibackend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection="messages")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    @Id
    private ObjectId id;
    private ObjectId conversationId;
    private ObjectId senderId;
    private String content;
    private boolean read;
    private LocalDateTime createdAt;
}
