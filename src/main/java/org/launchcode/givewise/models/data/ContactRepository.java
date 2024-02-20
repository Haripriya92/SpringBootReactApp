package org.launchcode.givewise.models.data;

import org.launchcode.givewise.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Integer> {
}

