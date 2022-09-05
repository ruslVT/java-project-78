package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;

public class StringSchema {
    private final List<String> validators = new ArrayList<>();
    private Integer minLength;
    private String subString;

    public StringSchema() {
    }

    public final Boolean isValid(String strForCheck) {
        if (validators.isEmpty()) {
            return true;
        }

        for (String str : validators) {
            switch (str) {
                case "required":
                    if (!isRequired(strForCheck)) {
                        return false;
                    }
                    break;
                case "minLength":
                    if (!isMinLength(strForCheck)) {
                        return false;
                    }
                    break;
                case "contains":
                    if (!isContains(strForCheck)) {
                        return false;
                    }
                    break;
                default:
            }
        }
        return true;
    }

    public final StringSchema required() {
        if (!validators.contains("required")) {
            validators.add("required");
        }
        return this;
    }

    private Boolean isRequired(String str) {
        return str != null && !str.equals("");
    }

    public final StringSchema minLength(Integer length) {
        if (!validators.contains("minLength")) {
            validators.add("minLength");
        }
        this.minLength = length;
        return this;
    }

    private Boolean isMinLength(String str) {
        return str.length() >= this.minLength;
    }

    public final StringSchema contains(String str) {
        if (!validators.contains("contains")) {
            validators.add("contains");
        }
        this.subString = str;
        return this;
    }
    private Boolean isContains(String str) {
        return str.contains(subString);
    }
}
