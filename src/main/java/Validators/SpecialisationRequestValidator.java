package Validators;


import Requests.CreateSpecialisationRequest;
import Validators.annotations.ValidSpecialisationRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


public class SpecialisationRequestValidator 
    implements ConstraintValidator<ValidSpecialisationRequest, CreateSpecialisationRequest> {
    
    @Override
    public boolean isValid(CreateSpecialisationRequest request, ConstraintValidatorContext context) {
        if (request == null) return true;
        
        boolean valid = true;
        
        // Business Rule: Description should be longer than name
        if (request.name() != null && request.description() != null) {
            if (request.description().trim().length() <= request.name().trim().length()) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(
                    "Description should be more detailed than the name")
                       .addPropertyNode("description")
                       .addConstraintViolation();
                valid = false;
            }
        }
        
        // Business Rule: Avoid duplicate words in name and description
        if (request.name() != null && request.description() != null) {
            String name = request.name().toLowerCase().trim();
            String description = request.description().toLowerCase().trim();
            
            // Split name into words and check if any single word repeats too much
            String[] nameWords = name.split("\\s+");
            for (String word : nameWords) {
                if (word.length() > 3) { // Only check words longer than 3 chars
                    long count = description.split(word).length - 1;
                    if (count > 2) { // If word appears more than 2 times
                        context.disableDefaultConstraintViolation();
                        context.buildConstraintViolationWithTemplate(
                            String.format("Word '%s' appears too many times in description", word))
                               .addPropertyNode("description")
                               .addConstraintViolation();
                        valid = false;
                        break;
                    }
                }
            }
        }
        
        // Business Rule: Name should not be too generic
        if (request.name() != null) {
            String name = request.name().toLowerCase().trim();
            String[] genericTerms = {"general", "basic", "common", "standard", "regular"};
            
            for (String term : genericTerms) {
                if (name.equals(term) || name.startsWith(term + " ")) {
                    context.disableDefaultConstraintViolation();
                    context.buildConstraintViolationWithTemplate(
                        "Specialisation name should be more specific")
                           .addPropertyNode("name")
                           .addConstraintViolation();
                    valid = false;
                    break;
                }
            }
        }
        
        return valid;
    }
}
