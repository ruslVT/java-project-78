package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StringSchemaTest {

    private final Validator validator = new Validator();
    private StringSchema v;

    @BeforeEach
    public final void beforeEach() {
        v = validator.string();
    }

    @Test
    public void testAllPredicates() {
        v.required().minLength(2).contains("2");
        assertThat(v.isValid("22")).isTrue();
        assertThat(v.isValid("77")).isFalse();
    }

    @Test
    public void testRequired() {
        v.required();
        assertThat(v.isValid("hi")).isTrue();
        assertThat(v.isValid(1)).isFalse();
        v.required();
        assertThat(v.isValid("")).isFalse();
        assertThat(v.isValid(null)).isFalse();
    }

    @Test
    public void testMinLength() {
        v.minLength(2);
        assertThat(v.isValid("hi")).isTrue();
        assertThat(v.isValid("1")).isFalse();
        v.minLength(1);
        assertThat(v.isValid("1")).isTrue();
        assertThat(v.isValid(1)).isFalse();
    }

    @Test
    public void testContains() {
        v.contains("hi");
        assertThat(v.isValid("hi all")).isTrue();
        assertThat(v.isValid("all")).isFalse();
        v.contains("hello");
        assertThat(v.isValid(1)).isFalse();
    }
}
