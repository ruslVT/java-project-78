package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NumberSchemaTest {

    private final Validator validator = new Validator();
    private static final int RANGE_MIN = 3;
    private static final int RANGE_MAX = 10;
    private static final int POSITIVE_NUMBER = 5;
    private static final int NEGATIVE_NUMBER = -3;

    @Test
    public void testNumberSchema() {
        NumberSchema numberSchema = validator.number();
        Boolean actual = numberSchema.isValid(null);
        assertThat(actual).isTrue();

        numberSchema.required().positive().range(RANGE_MIN, RANGE_MAX);
        Boolean actual1 = numberSchema.isValid(POSITIVE_NUMBER);
        assertThat(actual1).isTrue();

        Boolean actual2 = numberSchema.isValid(null);
        assertThat(actual2).isFalse();

        Boolean actual3 = numberSchema.isValid(NEGATIVE_NUMBER);
        assertThat(actual3).isFalse();

        Boolean actual4 = numberSchema.isValid(2);
        assertThat(actual4).isFalse();

        Boolean actual5 = numberSchema.isValid("5");
        assertThat(actual5).isFalse();
    }
}
