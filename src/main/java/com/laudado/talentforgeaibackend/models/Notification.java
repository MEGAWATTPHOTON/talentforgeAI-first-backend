package com.laudado.talentforgeaibackend.models;

import com.laudado.talentforgeaibackend.enums.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection="notifications")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
    @Id
    private ObjectId id;
    private ObjectId recipientId;
    private NotificationType type;
    private String title;
    private String message;
    private ObjectId referenceId;
    private boolean read;
    private LocalDateTime createdAt;
}
