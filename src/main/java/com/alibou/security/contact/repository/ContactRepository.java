package com.alibou.security.contact.repository;


import com.alibou.security.contact.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
}
