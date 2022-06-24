package com.wordscore.engine.rest;

import com.wordscore.engine.rest.dto.*;
import com.wordscore.engine.service.BlacklistedKeywordsRestService;
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

import java.util.Optional;

@RestController
@RequestMapping("/blacklisted_keywords")
public class BlacklistedKeywordsController {

    private static final Logger LOG = LoggerFactory.getLogger(BlacklistedKeywordsController.class);

    private BlacklistedKeywordsRestService blacklistedKeywordsRestService;

    @Autowired
    public BlacklistedKeywordsController(BlacklistedKeywordsRestService blacklistedKeywordsRestService){
        this.blacklistedKeywordsRestService = blacklistedKeywordsRestService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/find", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> find(@RequestBody FindBlacklistedKeywordRequestDTO findBlacklistedKeywordRequestDTO) {

        Optional<FindBlacklistedKeywordResponseDTO> result = blacklistedKeywordsRestService.findKeyword(findBlacklistedKeywordRequestDTO);
        if(!result.isPresent()){
            FindBlacklistedKeywordResponseDTO obj = new FindBlacklistedKeywordResponseDTO();
            obj.setBlacklisted(false);
            return new ResponseEntity<>(obj, HttpStatus.NOT_FOUND);
        } else {
            FindBlacklistedKeywordResponseDTO obj = result.get();
            obj.setBlacklisted(true);

            return new ResponseEntity<>(obj, HttpStatus.OK);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody CreateBlacklistedKeywordRequestDTO createBlacklistedKeywordRequestDTO) {

        Optional<FindBlacklistedKeywordResponseDTO> result = blacklistedKeywordsRestService.createKeyword(createBlacklistedKeywordRequestDTO);
        if(!result.isPresent()){
            return new ResponseEntity<>("Already exists", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }
}
