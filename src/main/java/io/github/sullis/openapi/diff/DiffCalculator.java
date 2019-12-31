package io.github.sullis.openapi.diff;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.Paths;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;
import io.swagger.v3.parser.OpenAPIV3Parser;
import io.swagger.v3.parser.core.models.SwaggerParseResult;

public class DiffCalculator {
    
    public List<Diff> calculate(String contents1, String contents2) {
      SwaggerParseResult result1 = createParser().readContents(contents1);
      SwaggerParseResult result2 = createParser().readContents(contents2);
      return Collections.unmodifiableList(calculate(result1.getOpenAPI(), result2.getOpenAPI()));
    }

    protected List<Diff> calculate(OpenAPI api1, OpenAPI api2) {
        List<Diff> diffs = new ArrayList<Diff>();
        diffs.addAll(diffServers(api1.getServers(), api2.getServers()));
        diffs.addAll(diffComponents(api1.getComponents(), api2.getComponents()));
        diffs.addAll(diffExtensions(api1.getExtensions(), api2.getExtensions()));
        diffs.addAll(diffExternalDocumentation(api1.getExternalDocs(), api2.getExternalDocs()));
        diffs.addAll(diffInfo(api1.getInfo(), api2.getInfo()));
        diffs.addAll(diffOpenAPIVersion(api1.getOpenapi(), api2.getOpenapi()));
        diffs.addAll(diffPaths(api1.getPaths(), api2.getPaths()));
        diffs.addAll(diffSecurityRequirements(api1.getSecurity(), api2.getSecurity()));
        diffs.addAll(diffTags(api1.getTags(), api1.getTags()));
        return diffs;
    }

    private List<Diff> diffTags(List<Tag> tags1, List<Tag> tags2) {
        List<Diff> diffs = new ArrayList<Diff>();
        // TODO :  diffTag(a, b)
        return diffs;
    }

    private List<Diff> diffTag(Tag tag1, Tag tag2) {
        List<Diff> diffs = new ArrayList<Diff>();

        // TODO

        return diffs;
    }

    private List<Diff> diffSecurityRequirements(List<SecurityRequirement> security1, List<SecurityRequirement> security2) {
        List<Diff> diffs = new ArrayList<Diff>();

        // TODO :  diffSecurityRequirement(a, b)

        return diffs;
    }

    private List<Diff> diffSecurityRequirement(SecurityRequirement security1, SecurityRequirement security2) {
        List<Diff> diffs = new ArrayList<Diff>();

        MapDifference<String, List<String>> mapDiff = Maps.difference(security1, security2);

        for (Map.Entry entry : mapDiff.entriesOnlyOnLeft().entrySet()) {
            diffs.add(new Diff(DiffType.BREAKING, "Security removed: " + entry.getKey()));
        }

        for (Map.Entry entry : mapDiff.entriesOnlyOnRight().entrySet()) {
            diffs.add(new Diff(DiffType.NON_BREAKING, "Security added: " + entry.getKey()));
        }

        return diffs;
    }

    private List<Diff> diffPaths(Paths paths1, Paths paths2) {
        List<Diff> diffs = new ArrayList<Diff>();
        MapDifference<String, PathItem> mapDiff = Maps.difference(paths1, paths2);

        for (Map.Entry entry : mapDiff.entriesOnlyOnLeft().entrySet()) {
            diffs.add(new Diff(DiffType.BREAKING, "Path removed: " + entry.getKey()));
        }

        for (Map.Entry entry : mapDiff.entriesDiffering().entrySet()) {
            diffs.add(new Diff(DiffType.BREAKING, "Path changed: " + entry.getKey()));
        }

        for (Map.Entry entry : mapDiff.entriesOnlyOnRight().entrySet()) {
          diffs.add(new Diff(DiffType.NON_BREAKING, "Path added: " + entry.getKey()));
        }

        return diffs;
    }

    private static List<String> sortedKeys(LinkedHashMap<String, ?> map) {
        List<String> keys = new ArrayList(map.keySet());
        Collections.sort(keys);
        return keys;
    }

    private List<Diff> diffOpenAPIVersion(String openapi1, String openapi2) {
        List<Diff> diffs = new ArrayList<Diff>();
        return diffs;
    }

    private List<Diff> diffInfo(Info info1, Info info2) {
        List<Diff> diffs = new ArrayList<Diff>();
        return diffs;
    }

    private List<Diff> diffExternalDocumentation(ExternalDocumentation externalDocs1, ExternalDocumentation externalDocs2) {
        List<Diff> diffs = new ArrayList<Diff>();
        return diffs;
    }

    private List<Diff> diffExtensions(Map<String, Object> extensions1, Map<String, Object> extensions2) {
        List<Diff> diffs = new ArrayList<Diff>();
        return diffs;
    }

    private List<Diff> diffComponents(Components components1, Components components2) {
        List<Diff> diffs = new ArrayList<Diff>();
        
        if (!components1.equals(components2)) {
            diffs.add(new Diff(DiffType.NON_BREAKING, "Whatever"));
        }

        return diffs;
    }

    private List<Diff> diffServers(List<Server> servers1, List<Server> servers2) {
        List<Diff> diffs = new ArrayList<Diff>();
        return diffs;
    }

    private OpenAPIV3Parser createParser() {
        return new OpenAPIV3Parser();
    }
}
