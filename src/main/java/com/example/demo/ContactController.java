package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/contact")
public class ContactController {
    private final ContactRepository contactRepository;

    public ContactController(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @GetMapping
    private List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    @PostMapping
    private Contact postContact(@RequestBody Contact contact) {
        return contactRepository.save(contact);
    }

    @DeleteMapping
    private ResponseEntity<?> deleteAllContacts() {
        contactRepository.deleteAll();
        return ResponseEntity.ok("Deleted all");
    }

    @GetMapping("/{id}")
    private Contact getContactById(@PathVariable Long id) {
        return contactRepository.findById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> deleteContactById(@PathVariable Long id) {
        contactRepository.deleteById(id);
        return ResponseEntity.ok("Deleted "+ id);
    }

    @PutMapping("/{id}")
    private ResponseEntity<?> putContactById(@PathVariable Long id, @RequestBody Contact contact) {
        Contact contact1 = contactRepository.findById(id).orElse(null);
        if (contact1 != null) {
            contact1.setEmail(contact.getEmail());
            contact1.setUsername(contact.getUsername());
            contact1.setMobile(contact.getMobile());
            contact1.setHome(contact.getHome());
            contactRepository.save(contact1);
            return ResponseEntity.ok("Patch successful");
        } else return ResponseEntity.ok("Patch fail");
    }
}

