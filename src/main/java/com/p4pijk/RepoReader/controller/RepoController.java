package com.p4pijk.RepoReader.controller;

import com.p4pijk.RepoReader.dto.ErrorResponseDTO;
import com.p4pijk.RepoReader.exception.RepoDataException;
import com.p4pijk.RepoReader.service.impl.RepoServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/repos")
public class RepoController {

    private final RepoServiceImpl service;

    @GetMapping(value = "/{username}")
    public ResponseEntity<?> getRepository(@PathVariable String username) {

        var response = service.getRepoData(username);

        return ResponseEntity.ok(response);
    }

    @ExceptionHandler
    public ResponseEntity<?> handleReposNotFoundException(RepoDataException exception) {
        ErrorResponseDTO response = new ErrorResponseDTO("404", exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpClientErrorException.NotFound.class)
    public ResponseEntity<ErrorResponseDTO> handleNotFoundException(HttpClientErrorException.NotFound ex) {
        ErrorResponseDTO response = new ErrorResponseDTO("404", "User doesn't exist");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public ResponseEntity<String> handleMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex) {
        ErrorResponseDTO response = new ErrorResponseDTO("406", ex.getMessage());
        return new ResponseEntity<>(response.toString(), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorResponseDTO> handleNLPException(Exception e) {
        return new ResponseEntity<>(new ErrorResponseDTO("500", "Something went wrong it server side"),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
