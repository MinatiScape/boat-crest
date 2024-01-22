package com.coveiot.android.sportsnotificationsdk.models.matchinfo;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.android.material.color.c;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0012\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0012\u0010\u0013R$\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR$\u0010\r\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\n\u0010\u0004\u001a\u0004\b\u000b\u0010\u0006\"\u0004\b\f\u0010\bR$\u0010\u0011\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u000e\u0010\u0004\u001a\u0004\b\u000f\u0010\u0006\"\u0004\b\u0010\u0010\b¨\u0006\u0014"}, d2 = {"Lcom/coveiot/android/sportsnotificationsdk/models/matchinfo/MInfoVenue;", "", "", "a", "Ljava/lang/String;", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", AppMeasurementSdk.ConditionalUserProperty.NAME, "b", "getLocation", "setLocation", FirebaseAnalytics.Param.LOCATION, c.f10260a, "getTimezone", "setTimezone", "timezone", "<init>", "()V", "sportsnotificationsdk_prodRelease"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes7.dex */
public final class MInfoVenue {
    @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)
    @Expose
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    private String f5940a;
    @SerializedName(FirebaseAnalytics.Param.LOCATION)
    @Expose
    @Nullable
    private String b;
    @SerializedName("timezone")
    @Expose
    @Nullable
    private String c;

    @Nullable
    public final String getLocation() {
        return this.b;
    }

    @Nullable
    public final String getName() {
        return this.f5940a;
    }

    @Nullable
    public final String getTimezone() {
        return this.c;
    }

    public final void setLocation(@Nullable String str) {
        this.b = str;
    }

    public final void setName(@Nullable String str) {
        this.f5940a = str;
    }

    public final void setTimezone(@Nullable String str) {
        this.c = str;
    }
}
