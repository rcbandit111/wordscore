package com.wordscore.engine.rest;

import com.wordscore.engine.rest.dto.*;
import com.wordscore.engine.service.KeywordsRestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/keywords")
public class KeywordsController {

    private static final Logger LOG = LoggerFactory.getLogger(KeywordsController.class);

    private KeywordsRestService keywordsRestService;

    @Autowired
    public KeywordsController(KeywordsRestService keywordsRestService){
        this.keywordsRestService = keywordsRestService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/find", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> find(@Valid @RequestBody FindKeywordRequestDTO findKeywordRequestDTO) {

        Optional<FindKeywordResponseDTO> result = keywordsRestService.findKeyword(findKeywordRequestDTO);
        if(!result.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@Valid @RequestBody CreateKeywordRequestDTO createKeywordRequestDTO) {

        Optional<FindKeywordResponseDTO> result = keywordsRestService.createKeyword(createKeywordRequestDTO);
        if(!result.isPresent()){
            return new ResponseEntity<>("Already exists", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> passwordReset(@Valid @RequestBody UpdateKeywordRequestDTO updateKeywordRequestDTO) {

        int result = keywordsRestService.updateKeyword(updateKeywordRequestDTO);
        if(result == 0){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
