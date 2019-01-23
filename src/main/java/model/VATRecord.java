package model;

import com.google.gson.annotations.SerializedName;
import enums.VATType;

import java.util.List;
import java.util.Map;

public class VATRecord {
    @SerializedName("country_code")
    private String countryCode;
    private String code;
    private String name;
    private List<Period> periods;

    public Double getVATValue(VATType type){
        if(periods != null && !periods.isEmpty()){
            Period latestPeriod = periods.get(0); //It should be latest period according to data sample
            Map<String, String> rates = latestPeriod.getRates();
            if(rates != null && !rates.isEmpty()){
                String rate = rates.get(type.name().toLowerCase());
                if(rate != null){
                    return Double.valueOf(rate);
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return  "country='" + countryCode + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'';
    }
}
