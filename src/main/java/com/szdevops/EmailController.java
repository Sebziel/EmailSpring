package com.szdevops;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/emails")
public class EmailController {

    @Autowired
    private EmailRepository emailRepository;

    @GetMapping
    public List<Email> getAllEmails() {
        return emailRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Email> getEmailById(@PathVariable Long id) {
        Optional<Email> email = emailRepository.findById(id);
        if (email.isPresent()) {
            return ResponseEntity.ok(email.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Email createEmail(@RequestBody Email email) {
        return emailRepository.save(email);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Email> updateEmail(@PathVariable Long id, @RequestBody Email emailDetails) {
        Optional<Email> email = emailRepository.findById(id);
        if (email.isPresent()) {
            Email updatedEmail = email.get();
            updatedEmail.setFirstName(emailDetails.getFirstName());
            updatedEmail.setLastName(emailDetails.getLastName());
            updatedEmail.setDepartment(emailDetails.getDepartment());
            updatedEmail.setPassword(emailDetails.getPassword());
            updatedEmail.setEmail(emailDetails.getEmail());
            updatedEmail.setMailboxCapacity(emailDetails.getMailboxCapacity());
            updatedEmail.setAlternateEmail(emailDetails.getAlternateEmail());
            emailRepository.save(updatedEmail);
            return ResponseEntity.ok(updatedEmail);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmail(@PathVariable Long id) {
        Optional<Email> email = emailRepository.findById(id);
        if (email.isPresent()) {
            emailRepository.delete(email.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}