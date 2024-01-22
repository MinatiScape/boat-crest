package com.mappls.sdk.services.api.event.route.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Keep;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
@Keep
/* loaded from: classes4.dex */
public class RouteReportSummaryResponse implements Parcelable {
    public static final Parcelable.Creator<RouteReportSummaryResponse> CREATOR = new a();
    @SerializedName("routes")
    @Expose
    private List<RouteReport> routes;

    /* loaded from: classes4.dex */
    public static class a implements Parcelable.Creator<RouteReportSummaryResponse> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public RouteReportSummaryResponse createFromParcel(Parcel parcel) {
            return new RouteReportSummaryResponse(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b */
        public RouteReportSummaryResponse[] newArray(int i) {
            return new RouteReportSummaryResponse[i];
        }
    }

    public RouteReportSummaryResponse() {
        this.routes = null;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public List<RouteReport> getRoutes() {
        return this.routes;
    }

    public void readFromParcel(Parcel parcel) {
        this.routes = parcel.createTypedArrayList(RouteReport.CREATOR);
    }

    public void setRoutes(List<RouteReport> list) {
        this.routes = list;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(this.routes);
    }

    public RouteReportSummaryResponse(Parcel parcel) {
        this.routes = null;
        this.routes = parcel.createTypedArrayList(RouteReport.CREATOR);
    }
}
