package ru.jdbcfighters.renthub.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.jdbcfighters.renthub.domain.models.AttributeValue;
import ru.jdbcfighters.renthub.services.AttributeValueService;

@RestController
@RequestMapping("/attribute_values")
public class AttributeValueController {
    private AttributeValueService attributeValueService;

    public AttributeValueController(AttributeValueService attributeValueService)
    {
        this.attributeValueService = attributeValueService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AttributeValue> findById(@PathVariable("id") Long id) {
        AttributeValue attributeValue = attributeValueService.findById(id);
        System.out.println();
        System.out.println("-------------");
        System.out.println(attributeValue);
        System.out.println("-------------");
        System.out.println();
        return new ResponseEntity<>(attributeValue, HttpStatus.OK);
    }
}
