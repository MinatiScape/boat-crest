package com.coveiot.android.sportsnotificationsdk.models.accesstoken;

import androidx.core.app.NotificationCompat;
import com.google.android.material.color.c;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mappls.android.lms.MapplsLMSDbAdapter;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u000f\u0018\u00002\u00020\u0001:\u0001\u0018B\u0007¢\u0006\u0004\b\u0016\u0010\u0017R$\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR$\u0010\u0011\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R$\u0010\u0015\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u0012\u0010\u0004\u001a\u0004\b\u0013\u0010\u0006\"\u0004\b\u0014\u0010\b¨\u0006\u0019"}, d2 = {"Lcom/coveiot/android/sportsnotificationsdk/models/accesstoken/AccessTokenResponse;", "", "", "a", "Ljava/lang/String;", "getStatus", "()Ljava/lang/String;", "setStatus", "(Ljava/lang/String;)V", NotificationCompat.CATEGORY_STATUS, "Lcom/coveiot/android/sportsnotificationsdk/models/accesstoken/AccessTokenResponse$Response;", "b", "Lcom/coveiot/android/sportsnotificationsdk/models/accesstoken/AccessTokenResponse$Response;", "getResponse", "()Lcom/coveiot/android/sportsnotificationsdk/models/accesstoken/AccessTokenResponse$Response;", "setResponse", "(Lcom/coveiot/android/sportsnotificationsdk/models/accesstoken/AccessTokenResponse$Response;)V", "response", c.f10260a, "getApiVersion", "setApiVersion", "apiVersion", "<init>", "()V", "Response", "sportsnotificationsdk_prodRelease"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes7.dex */
public final class AccessTokenResponse {
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    @Expose
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    private String f5918a;
    @SerializedName("response")
    @Expose
    @Nullable
    private Response b;
    @SerializedName("api_version")
    @Expose
    @Nullable
    private String c;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u000e\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u000e\u0010\u000fR$\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR$\u0010\r\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\n\u0010\u0004\u001a\u0004\b\u000b\u0010\u0006\"\u0004\b\f\u0010\b¨\u0006\u0010"}, d2 = {"Lcom/coveiot/android/sportsnotificationsdk/models/accesstoken/AccessTokenResponse$Response;", "", "", "a", "Ljava/lang/String;", "getToken", "()Ljava/lang/String;", "setToken", "(Ljava/lang/String;)V", MapplsLMSDbAdapter.KEY_TOKEN, "b", "getExpires", "setExpires", "expires", "<init>", "()V", "sportsnotificationsdk_prodRelease"}, k = 1, mv = {1, 5, 1})
    /* loaded from: classes7.dex */
    public static final class Response {
        @SerializedName(MapplsLMSDbAdapter.KEY_TOKEN)
        @Expose
        @Nullable

        /* renamed from: a  reason: collision with root package name */
        private String f5919a;
        @SerializedName("expires")
        @Expose
        @Nullable
        private String b;

        @Nullable
        public final String getExpires() {
            return this.b;
        }

        @Nullable
        public final String getToken() {
            return this.f5919a;
        }

        public final void setExpires(@Nullable String str) {
            this.b = str;
        }

        public final void setToken(@Nullable String str) {
            this.f5919a = str;
        }
    }

    @Nullable
    public final String getApiVersion() {
        return this.c;
    }

    @Nullable
    public final Response getResponse() {
        return this.b;
    }

    @Nullable
    public final String getStatus() {
        return this.f5918a;
    }

    public final void setApiVersion(@Nullable String str) {
        this.c = str;
    }

    public final void setResponse(@Nullable Response response) {
        this.b = response;
    }

    public final void setStatus(@Nullable String str) {
        this.f5918a = str;
    }
}
