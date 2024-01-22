package com.mappls.sdk.services.api.event.route.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Keep;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
@Keep
/* loaded from: classes4.dex */
public class RouteReport implements Parcelable {
    public static final Parcelable.Creator<RouteReport> CREATOR = new a();
    @SerializedName(alternate = {FirebaseAnalytics.Param.INDEX}, value = "routeIdx")
    @Expose
    private Long index;
    @SerializedName("reports")
    @Expose
    private List<ReportDetails> reportDetails;

    /* loaded from: classes4.dex */
    public static class a implements Parcelable.Creator<RouteReport> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public RouteReport createFromParcel(Parcel parcel) {
            return new RouteReport(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b */
        public RouteReport[] newArray(int i) {
            return new RouteReport[i];
        }
    }

    public RouteReport() {
        this.reportDetails = null;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Long getIndex() {
        return this.index;
    }

    public List<ReportDetails> getReports() {
        return this.reportDetails;
    }

    public void readFromParcel(Parcel parcel) {
        this.index = (Long) parcel.readValue(Long.class.getClassLoader());
        this.reportDetails = parcel.createTypedArrayList(ReportDetails.CREATOR);
    }

    public void setIndex(Long l) {
        this.index = l;
    }

    public void setReports(List<ReportDetails> list) {
        this.reportDetails = list;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.index);
        parcel.writeTypedList(this.reportDetails);
    }

    public RouteReport(Parcel parcel) {
        this.reportDetails = null;
        this.index = (Long) parcel.readValue(Long.class.getClassLoader());
        this.reportDetails = parcel.createTypedArrayList(ReportDetails.CREATOR);
    }
}
