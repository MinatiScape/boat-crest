package com.coveiot.coveaccess.navigation.model;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.Constants;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
/* loaded from: classes8.dex */
public class FavouritePlace implements Serializable {
    @SerializedName("endDate")
    @Expose
    private String endDate;
    @SerializedName("fullAddress")
    @Expose
    private String fullAddress;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName(Constants.ScionAnalytics.PARAM_LABEL)
    @Expose
    private String label;
    @SerializedName(FirebaseAnalytics.Param.LOCATION)
    @Expose
    private Location location;
    @SerializedName("mapApi")
    @Expose
    private String mapApi;
    @SerializedName("mapplsPin")
    @Expose
    private String mapplsPin;
    @SerializedName("mode")
    @Expose
    private String mode;
    @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)
    @Expose
    private String name;
    @SerializedName("sortIndex")
    @Expose
    private Integer sortIndex;

    /* loaded from: classes8.dex */
    public static class Location {
        @SerializedName("type")
        @Expose

        /* renamed from: a  reason: collision with root package name */
        private String f6659a;
        @SerializedName("coordinates")
        @Expose
        private List<Double> b;

        public List<Double> getCoordinates() {
            return this.b;
        }

        public String getType() {
            return this.f6659a;
        }

        public void setCoordinates(List<Double> list) {
            this.b = list;
        }

        public void setType(String str) {
            this.f6659a = str;
        }
    }

    public String getEndDate() {
        return this.endDate;
    }

    public String getFullAddress() {
        return this.fullAddress;
    }

    public String getId() {
        return this.id;
    }

    public String getLabel() {
        return this.label;
    }

    public Location getLocation() {
        return this.location;
    }

    public String getMapApi() {
        return this.mapApi;
    }

    public String getMapplsPin() {
        return this.mapplsPin;
    }

    public String getMode() {
        return this.mode;
    }

    public String getName() {
        return this.name;
    }

    public Integer getSortIndex() {
        return this.sortIndex;
    }

    public void setEndDate(String str) {
        this.endDate = str;
    }

    public void setFullAddress(String str) {
        this.fullAddress = str;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setLabel(String str) {
        this.label = str;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setMapApi(String str) {
        this.mapApi = str;
    }

    public void setMapplsPin(String str) {
        this.mapplsPin = str;
    }

    public void setMode(String str) {
        this.mode = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setSortIndex(Integer num) {
        this.sortIndex = num;
    }
}
