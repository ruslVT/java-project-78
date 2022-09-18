package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StringSchemaTest {

    private final Validator validator = new Validator();
    private static final int MIN_LENGTH = 3;

    @Test
    public void testStringSchema() {
        StringSchema stringSchema = validator.string();

        Boolean actual = stringSchema.isValid("what does the fox say");
        assertThat(actual).isTrue();

        stringSchema.required().minLength(MIN_LENGTH).contains("what");

        Boolean actual1 = stringSchema.isValid("what does the fox say");
        assertThat(actual1).isTrue();

        Boolean actual2 = stringSchema.isValid("");
        assertThat(actual2).isFalse();

        Boolean actual3 = stringSchema.isValid("hi");
        assertThat(actual3).isFalse();

        Boolean actual4 = stringSchema.isValid("fox say");
        assertThat(actual4).isFalse();

        Boolean actual5 = stringSchema.isValid(1);
        assertThat(actual5).isFalse();
    }
}
