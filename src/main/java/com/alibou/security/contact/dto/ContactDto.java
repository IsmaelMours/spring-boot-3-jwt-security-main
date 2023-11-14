package com.alibou.security.contact.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class ContactDto {
    private Long Id;
    private String email;
    private String phoneNumber;
    private String address;

}