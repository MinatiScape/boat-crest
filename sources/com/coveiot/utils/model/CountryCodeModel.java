package com.coveiot.utils.model;

import androidx.annotation.Nullable;
import java.util.Locale;
/* loaded from: classes9.dex */
public class CountryCodeModel {

    /* renamed from: a  reason: collision with root package name */
    public String f7622a;
    public String b;

    public CountryCodeModel(String str, String str2) {
        this.f7622a = str;
        this.b = str2;
    }

    public boolean equals(@Nullable Object obj) {
        return ((CountryCodeModel) obj).getCountryName().equalsIgnoreCase(getCountryName());
    }

    public String getCountryCode() {
        return this.b;
    }

    public String getCountryName() {
        return new Locale("", this.b).getDisplayCountry();
    }

    public String getIsoCode() {
        return this.f7622a;
    }

    public void setCountryCode(String str) {
        this.b = str;
    }

    public void setIsoCode(String str) {
        this.f7622a = str;
    }
}
