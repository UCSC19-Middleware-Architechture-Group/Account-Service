package org.ucsc.account_service.Models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Account")
@Data
public class Account {
    private String id;
    private String customerId;
    private String phoneNo;
    private String createdDate;
    private String updatedDate;
    private String status;
}
