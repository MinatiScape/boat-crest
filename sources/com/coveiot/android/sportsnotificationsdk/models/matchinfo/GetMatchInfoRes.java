package com.coveiot.android.sportsnotificationsdk.models.matchinfo;

import androidx.core.app.NotificationCompat;
import com.google.android.material.color.c;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;
import org.jose4j.jwk.RsaJsonWebKey;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u001a\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\"\u0010#R$\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR$\u0010\u0011\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R$\u0010\u0015\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u0012\u0010\u0004\u001a\u0004\b\u0013\u0010\u0006\"\u0004\b\u0014\u0010\bR$\u0010\u0019\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u0016\u0010\u0004\u001a\u0004\b\u0017\u0010\u0006\"\u0004\b\u0018\u0010\bR$\u0010\u001d\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u001a\u0010\u0004\u001a\u0004\b\u001b\u0010\u0006\"\u0004\b\u001c\u0010\bR$\u0010!\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u001e\u0010\u0004\u001a\u0004\b\u001f\u0010\u0006\"\u0004\b \u0010\b¨\u0006$"}, d2 = {"Lcom/coveiot/android/sportsnotificationsdk/models/matchinfo/GetMatchInfoRes;", "", "", "a", "Ljava/lang/String;", "getStatus", "()Ljava/lang/String;", "setStatus", "(Ljava/lang/String;)V", NotificationCompat.CATEGORY_STATUS, "Lcom/coveiot/android/sportsnotificationsdk/models/matchinfo/MInfoResponse;", "b", "Lcom/coveiot/android/sportsnotificationsdk/models/matchinfo/MInfoResponse;", "getResponse", "()Lcom/coveiot/android/sportsnotificationsdk/models/matchinfo/MInfoResponse;", "setResponse", "(Lcom/coveiot/android/sportsnotificationsdk/models/matchinfo/MInfoResponse;)V", "response", c.f10260a, "getEtag", "setEtag", "etag", "d", "getModified", "setModified", "modified", RsaJsonWebKey.EXPONENT_MEMBER_NAME, "getDatetime", "setDatetime", "datetime", "f", "getApiVersion", "setApiVersion", "apiVersion", "<init>", "()V", "sportsnotificationsdk_prodRelease"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes7.dex */
public final class GetMatchInfoRes {
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    @Expose
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    private String f5934a;
    @SerializedName("response")
    @Expose
    @Nullable
    private MInfoResponse b;
    @SerializedName("etag")
    @Expose
    @Nullable
    private String c;
    @SerializedName("modified")
    @Expose
    @Nullable
    private String d;
    @SerializedName("datetime")
    @Expose
    @Nullable
    private String e;
    @SerializedName("api_version")
    @Expose
    @Nullable
    private String f;

    @Nullable
    public final String getApiVersion() {
        return this.f;
    }

    @Nullable
    public final String getDatetime() {
        return this.e;
    }

    @Nullable
    public final String getEtag() {
        return this.c;
    }

    @Nullable
    public final String getModified() {
        return this.d;
    }

    @Nullable
    public final MInfoResponse getResponse() {
        return this.b;
    }

    @Nullable
    public final String getStatus() {
        return this.f5934a;
    }

    public final void setApiVersion(@Nullable String str) {
        this.f = str;
    }

    public final void setDatetime(@Nullable String str) {
        this.e = str;
    }

    public final void setEtag(@Nullable String str) {
        this.c = str;
    }

    public final void setModified(@Nullable String str) {
        this.d = str;
    }

    public final void setResponse(@Nullable MInfoResponse mInfoResponse) {
        this.b = mInfoResponse;
    }

    public final void setStatus(@Nullable String str) {
        this.f5934a = str;
    }
}
