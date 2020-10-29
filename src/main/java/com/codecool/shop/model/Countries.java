package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Countries {

    private List<String> countries;

    public Countries() {
        String[] locales = Locale.getISOCountries();
        for(String country : locales){
            Locale obj = new Locale("", country);
            this.countries.add(obj.getDisplayCountry());
        }
    }

    public List<String> getListOfCountries(){
        return countries;
    }

}
