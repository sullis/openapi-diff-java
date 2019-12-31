package io.github.sullis.openapi.diff;

import org.junit.jupiter.api.Test;

import java.util.List;

import static com.google.common.truth.Truth.*;
import static io.github.sullis.openapi.diff.TestUtil.*;

public class DiffCalculatorTest {
    @Test
    public void identicalSpecsHaveZeroDiffs() {
        for (String contents : ALL) {
            DiffCalculator calc = new DiffCalculator();
            assertThat(calc.calculate(contents, contents)).isEmpty();
        }
    }

    @Test
    public void detectBreakingChanges() {
        List<Diff> diffs = new DiffCalculator().calculate(PETSTORE_BASELINE, PETSTORE_WITH_BREAKING_CHANGES);
        List<Diff> breaking = breaking(diffs);
        assertThat(breaking.size()).isEqualTo(1);
        assertThat(breaking.get(0).getDescription()).isEqualTo("Path changed: /pet/{petId}");
        assertThat(nonBreaking(diffs).size()).isEqualTo(0);
    }

    @Test
    public void detectNonBreakingChanges() {
        List<Diff> diffs = new DiffCalculator().calculate(PETSTORE_BASELINE, PETSTORE_WITH_NON_BREAKING_CHANGES);
        List<Diff> nonBreaking = nonBreaking(diffs);
        assertThat(nonBreaking.size()).isEqualTo(1);
        assertThat(nonBreaking.get(0).getDescription()).isEqualTo("Whatever");
        assertThat(breaking(diffs).size()).isEqualTo(0);
    }
    
}
