package com.shu.backend.constants;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

public class DocEntryType {

    public static final Map<Integer, String> DOCTYPE2STRING = ImmutableMap.of(
            1, "PDF", 2, "DOC", 3, "MD", 4, "TXT"
    );

    public static final Map<String, Integer> STRING2DOCTYPE = ImmutableMap.of(
            "PDF", 1, "DOC", 2, "MD", 3, "TXT", 4
    );
}
