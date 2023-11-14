package com.alibou.security.contact.service;

import com.alibou.security.contact.model.Contact;

import java.util.stream.Stream;

public interface ContactService {
    public void createContact(Contact contact);
    public Stream<Contact> getAll();
    public Contact getById(Long Id);
    public Contact update(Contact updateContact);
}
