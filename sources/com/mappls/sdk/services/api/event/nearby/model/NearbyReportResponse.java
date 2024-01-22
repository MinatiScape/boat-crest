package com.mappls.sdk.services.api.event.nearby.model;

import androidx.annotation.Keep;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
@Keep
/* loaded from: classes4.dex */
public class NearbyReportResponse {
    @SerializedName("reports")
    @Expose
    private List<NearbyReport> nearbyReports = null;
    @SerializedName("pagination")
    @Expose
    private NearbyReportPagination pagination;

    public NearbyReportPagination getPagination() {
        return this.pagination;
    }

    public List<NearbyReport> getReports() {
        return this.nearbyReports;
    }

    public void setPagination(NearbyReportPagination nearbyReportPagination) {
        this.pagination = nearbyReportPagination;
    }

    public void setReports(List<NearbyReport> list) {
        this.nearbyReports = list;
    }
}
