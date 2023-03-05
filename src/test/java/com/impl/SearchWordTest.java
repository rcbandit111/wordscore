package com.impl;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SearchWordTest {

    @Test
    void justAnExample() {
        List<String> list = findPhrasesInDocument("adventure dream is a fun c++", List.of("dre", "adve", "dream", "long program", "Adventure", "is a", "c++"));

        System.out.print(String.join("\n", list));
    }

    List<String> findPhrasesInDocument(String document, List<String> phrases) {
        return phrases
                .stream()
                .filter(p -> Pattern.compile("(^|\\s)"+ Pattern.quote(p.toLowerCase()) + "(\\s|$)")
                        .asPredicate()
                        .test(document.toLowerCase()))
                .collect(Collectors.toList());
    }
}
