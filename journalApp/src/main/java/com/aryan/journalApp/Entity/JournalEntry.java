package com.aryan.journalApp.Entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Document(collection = "journal_entries") //This annotation is used to map the entries with database
@Data
@NoArgsConstructor
public class JournalEntry {

    @Id //This annotation is used to provide information to spring boot that this is the id in my table
    private ObjectId id;
    private String title;
    private String Content;
    private LocalDateTime date;
}
