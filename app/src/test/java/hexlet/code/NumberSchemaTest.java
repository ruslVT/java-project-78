package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NumberSchemaTest {

    private final Validator validator = new Validator();
    private NumberSchema v;

    @BeforeEach
    public final void beforeEach() {
        v = validator.number();
    }

    @Test
    public void testAllPredicates() {
        v.required().positive().range(0, 1);
        assertThat(v.isValid(1)).isTrue();
        assertThat(v.isValid(2)).isFalse();
    }

    @Test
    public void testRequired() {
        v.required();
        assertThat(v.isValid(1)).isTrue();
        assertThat(v.isValid(-1)).isTrue();
        assertThat(v.isValid(0)).isTrue();
        assertThat(v.isValid(null)).isFalse();
        assertThat(v.isValid("")).isFalse();
    }

    @Test
    public void testPositive() {
        v.positive();
        assertThat(v.isValid(1)).isTrue();
        assertThat(v.isValid(-1)).isFalse();
        assertThat(v.isValid(null)).isTrue();
        assertThat(v.isValid(0)).isFalse();
        assertThat(v.isValid("hello")).isFalse();
    }

    @Test
    public void testRange() {
        v.range(0, 2);
        assertThat(v.isValid(1)).isTrue();
        assertThat(v.isValid(-1)).isFalse();
        assertThat(v.isValid(null)).isTrue();
        assertThat(v.isValid("hello")).isFalse();
    }
}
