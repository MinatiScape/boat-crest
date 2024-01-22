package com.mappls.sdk.services.api.weather;

import androidx.annotation.Keep;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
@Keep
/* loaded from: classes8.dex */
public class WeatherCriteria {
    public static final String SIZE_24PX = "24px";
    public static final String SIZE_36PX = "36px";
    public static final String THEME_DARK = "dark";
    public static final String THEME_LIGHT = "light";
    public static final String UNIT_CELSIUS = "C";
    public static final String UNIT_FARENHEIT = "F";
    public static final String UNIT_TYPE_DAY = "day";
    public static final String UNIT_TYPE_HOUR = "hour";

    @Keep
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes8.dex */
    public @interface SizeCriteria {
    }

    @Keep
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes8.dex */
    public @interface ThemeCriteria {
    }

    @Keep
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes8.dex */
    public @interface UnitCriteria {
    }

    @Keep
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes8.dex */
    public @interface UnitTypeCriteria {
    }
}
