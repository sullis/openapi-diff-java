package io.github.sullis.openapi.diff;

public final class Diff {
    private final DiffType diffType;
    private final String description;

    public Diff(DiffType diffType, String description) {
        this.diffType = diffType;
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public DiffType getDiffType() {
        return this.diffType;
    }
}
