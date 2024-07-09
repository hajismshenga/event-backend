package com.event.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.event.management.model.Response;
import com.event.management.service.ResponseService;

@RestController
@RequestMapping("/api/responses")
public class ResponseController {

    @Autowired
    private ResponseService responseService;

    @GetMapping("/all")
    public ResponseEntity<List<Response>> getAllResponses() {
        List<Response> responses = responseService.getAllResponses();
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Response> addResponse(@RequestBody Response response) {
        Response savedResponse = responseService.addResponse(response);
        return new ResponseEntity<>(savedResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getResponseById(@PathVariable("id") Long responseId) {
        Response response = responseService.getResponseById(responseId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteResponse(@PathVariable("id") Long responseId) {
        responseService.deleteResponse(responseId);
        return new ResponseEntity<>("Response deleted successfully", HttpStatus.OK);
    }
}
