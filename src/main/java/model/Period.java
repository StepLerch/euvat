package model;

import java.util.Map;

public class Period {
    private Map<String, String> rates;
    private String effective_from;

    public Map<String, String> getRates() {
        return rates;
    }

    public String getEffective_from() {
        return effective_from;
    }
}
