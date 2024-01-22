package com.coveiot.covepreferences.data;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes8.dex */
public final class WorldClockPrefData implements Serializable {
    @SerializedName("Abbreviation")
    @Expose
    @Nullable
    private String abbreviation;
    @SerializedName("country")
    @Expose
    @Nullable
    private String country;
    @SerializedName("id")
    @Expose
    @Nullable
    private Integer id;
    @SerializedName("latitude")
    @Expose
    @Nullable
    private Double latitude;
    @SerializedName("longitude")
    @Expose
    @Nullable
    private Double longitude;
    @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)
    @Expose
    @Nullable
    private String name;
    @SerializedName("timeZoneName")
    @Expose
    @Nullable
    private String timeZoneName;
    @SerializedName("timeZoneOffset")
    @Expose
    @Nullable
    private String timeZoneOffset;

    @Nullable
    public final String getAbbreviation() {
        return this.abbreviation;
    }

    @Nullable
    public final String getCountry() {
        return this.country;
    }

    @Nullable
    public final Integer getId() {
        return this.id;
    }

    @Nullable
    public final Double getLatitude() {
        return this.latitude;
    }

    @Nullable
    public final Double getLongitude() {
        return this.longitude;
    }

    @Nullable
    public final String getName() {
        return this.name;
    }

    @Nullable
    public final String getTimeZoneName() {
        return this.timeZoneName;
    }

    @Nullable
    public final String getTimeZoneOffset() {
        return this.timeZoneOffset;
    }

    public final void setAbbreviation(@Nullable String str) {
        this.abbreviation = str;
    }

    public final void setCountry(@Nullable String str) {
        this.country = str;
    }

    public final void setId(@Nullable Integer num) {
        this.id = num;
    }

    public final void setLatitude(@Nullable Double d) {
        this.latitude = d;
    }

    public final void setLongitude(@Nullable Double d) {
        this.longitude = d;
    }

    public final void setName(@Nullable String str) {
        this.name = str;
    }

    public final void setTimeZoneName(@Nullable String str) {
        this.timeZoneName = str;
    }

    public final void setTimeZoneOffset(@Nullable String str) {
        this.timeZoneOffset = str;
    }
}
