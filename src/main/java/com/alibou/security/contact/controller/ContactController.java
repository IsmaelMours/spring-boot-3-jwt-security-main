package com.alibou.security.contact.controller;

import java.util.stream.Stream;

import com.alibou.security.contact.model.Contact;
import com.alibou.security.contact.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;



import lombok.*;

@RestController
@AllArgsConstructor
@RequestMapping("/")
public class ContactController {
    @Autowired
    private ContactService contactService;

    @PostMapping("/save")
    public ResponseEntity create(@RequestBody Contact contact ){
        contactService.createContact(contact);
        return ResponseEntity.ok().body(
                contact.getEmail()+" " +" \n" + contact.getContactNumber()+" "+ " \n"+ contact.getAddress()+" " +" \n"+
                        contact.getId()
        );
    }

    @GetMapping("/getAll")
    public Stream<Contact> getContactInfo() {
        return contactService.getAll();
    }



}