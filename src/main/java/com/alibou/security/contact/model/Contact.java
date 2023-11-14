package com.alibou.security.contact.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "contact_us")
public class Contact {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    Long Id;

    @Column(name = "school_email")
    String email;

    @Column(name = "school_address")
    String address;

    @Column(name = "school_contact_no")
    String contactNumber;


}

