package org.ucsc.account_service.Models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "Customer")
public class Customer {
    private String id;
    private String name;
    private String address;
    private String email;
    private String userId;
    private String nic;
    private String dob;
}
