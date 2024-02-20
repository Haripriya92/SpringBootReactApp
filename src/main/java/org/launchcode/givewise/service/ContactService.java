package org.launchcode.givewise.service;

import org.launchcode.givewise.dto.ContactFormDTO;
import org.launchcode.givewise.models.Contact;
import org.launchcode.givewise.models.data.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public void saveContactForm(ContactFormDTO contactForm) {
        Contact contact = new Contact();
        contact.setName(contactForm.getName());
        contact.setEmail(contactForm.getEmail());
        contact.setMessage(contactForm.getMessage());
        contactRepository.save(contact);
    }
}

