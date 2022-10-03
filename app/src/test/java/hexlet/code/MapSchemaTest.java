package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class MapSchemaTest {

    private final Validator validator = new Validator();
    private MapSchema mapSchema;
    static final int POSITIVE_NUMBER = 100;
    static final int NEGATIVE_NUMBER = -5;

    @BeforeEach
    public final void beforeEach() {
        mapSchema = validator.map();
    }

    @Test
    public void testMapSchema() {
        Boolean actual1 = mapSchema.isValid(null);
        assertThat(actual1).isTrue();

        int size = 2;
        mapSchema.sizeof(size);

        Boolean actual2 = mapSchema.isValid(new HashMap<>(Map.of("key1", "value1", "key2", "value2")));
        assertThat(actual2).isTrue();

        Boolean actual3 = mapSchema.isValid("hello");
        assertThat(actual3).isFalse();

        mapSchema.required();

        Boolean actual4 = mapSchema.isValid(new HashMap<>(Map.of("key1", "value1")));
        assertThat(actual4).isFalse();

        Boolean actual5 = mapSchema.isValid("");
        assertThat(actual5).isFalse();

        Boolean actual6 = mapSchema.isValid(null);
        assertThat(actual6).isFalse();
    }

    @Test
    public void testShape() {
        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", validator.string().required());
        schemas.put("age", validator.number().positive());
        mapSchema.shape(schemas);

        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Kolya");
        human1.put("age", POSITIVE_NUMBER);
        assertThat(mapSchema.isValid(human1)).isTrue();

        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "Maya");
        human2.put("age", null);
        assertThat(mapSchema.isValid(human2)).isTrue();

        Map<String, Object> human3 = new HashMap<>();
        human3.put("name", "");
        human3.put("age", null);
        assertThat(mapSchema.isValid(human3)).isFalse();

        Map<String, Object> human4 = new HashMap<>();
        human4.put("name", "Valya");
        human4.put("age", NEGATIVE_NUMBER);
        assertThat(mapSchema.isValid(human4)).isFalse();

        assertThat(mapSchema.isValid("hello")).isFalse();

    }
}
