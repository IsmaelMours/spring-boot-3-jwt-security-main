package com.alibou.security.contact.service.Impl;

import com.alibou.security.contact.model.Contact;
import com.alibou.security.contact.repository.ContactRepository;
import com.alibou.security.contact.service.ContactService;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;

    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }


    @Override
    public void createContact(Contact contact) {
        contactRepository.save(contact);
    }



    @Override
    public Stream<Contact> getAll() {
        return contactRepository.findAll().stream();
    }



    @Override
    public Contact getById(Long Id) {
        return contactRepository.findById(Id)
                .orElseThrow();
    }



    @Override
    public Contact update(Contact updateContact) {
        updateContact.setEmail(updateContact.getEmail());
        updateContact.setAddress(updateContact.getAddress());
        updateContact.setContactNumber(updateContact.getContactNumber());

        return contactRepository.save(updateContact);
    }
}
