package io.github.sullis.openapi.diff;

import com.google.common.collect.Lists;
import com.google.common.io.Resources;

import java.nio.charset.Charset;
import java.util.List;
import java.util.stream.Collectors;

public final class TestUtil {
    static public final String PETSTORE_BASELINE = loadResource("petstore/petstore-baseline.yaml");
    static public final String PETSTORE_WITH_NON_BREAKING_CHANGES = loadResource("petstore/petstore-with-non-breaking-changes.yaml");
    static public final String PETSTORE_WITH_BREAKING_CHANGES = loadResource("petstore/petstore-with-breaking-changes.yaml");

    static public final List<String> ALL = Lists.newArrayList(PETSTORE_BASELINE, PETSTORE_WITH_BREAKING_CHANGES, PETSTORE_WITH_NON_BREAKING_CHANGES);

    static private String loadResource(String resourceName) {
        try {
            return Resources.toString(Resources.getResource(resourceName), Charset.defaultCharset());
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    
    static public List<Diff> breaking(List<Diff> diffs) {
        return diffs.stream().filter(d -> d.getDiffType() == DiffType.BREAKING).collect(Collectors.toList());
    }

    static public List<Diff> nonBreaking(List<Diff> diffs) {
        return diffs.stream().filter(d -> d.getDiffType() == DiffType.NON_BREAKING).collect(Collectors.toList());
    }
}
