package com.sritel.account.Models;

import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Account")
@Data
public class Account {
    private String id;
    private String email;

    @Indexed(unique = true)
    private String phoneNo;
    private String createdDate;
    private String updatedDate;
    private String status;
}
