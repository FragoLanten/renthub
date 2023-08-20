package ru.jdbcfighters.renthub.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.jdbcfighters.renthub.domain.models.Attribute;
import ru.jdbcfighters.renthub.services.AttributeService;

@RestController
@RequestMapping("/attributes")
public class AttributeController {
    private AttributeService attributeService;

    public AttributeController(AttributeService attributeService)
    {
        this.attributeService = attributeService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Attribute> findById(@PathVariable ("id") Long id) {
        Attribute attribute = attributeService.findById(id);
        return new ResponseEntity<>(attribute, HttpStatus.OK);
    }
}
