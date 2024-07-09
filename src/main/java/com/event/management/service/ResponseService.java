package com.event.management.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.event.management.model.Response;
import com.event.management.repository.ResponseRepository;

@Service
public class ResponseService {

    @Autowired
    private ResponseRepository responseRepository;

    public List<Response> getAllResponses() {
        return responseRepository.findAll();
    }

    public Response addResponse(Response response) {
        return responseRepository.save(response);
    }

    public Response getResponseById(Long responseId) {
        // Get the Response object from Optional<Response>
        Optional<Response> response = (Optional<Response>) responseRepository.findById(responseId);
        // Return the Response object or null if not found
        return response.orElse(null);
    }

    public void deleteResponse(Long responseId) {
        responseRepository.deleteById(responseId);
    }
}
