package io.shipbook.shipbooksdk.Models;

import com.clevertap.android.sdk.Constants;
import com.google.android.gms.fitness.FitnessActivities;
import com.google.android.material.color.c;
import com.mappls.android.lms.MapplsLMSDbAdapter;
import io.shipbook.shipbooksdk.Models.ConfigResponse;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;
@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000f\b\u0080\b\u0018\u0000  2\u00020\u0001:\u0001 B\u001f\u0012\u0006\u0010\t\u001a\u00020\u0004\u0012\u0006\u0010\n\u001a\u00020\u0006\u0012\u0006\u0010\u000b\u001a\u00020\u0004¢\u0006\u0004\b\u001e\u0010\u001fJ\b\u0010\u0003\u001a\u00020\u0002H\u0016J\t\u0010\u0005\u001a\u00020\u0004HÆ\u0003J\t\u0010\u0007\u001a\u00020\u0006HÆ\u0003J\t\u0010\b\u001a\u00020\u0004HÆ\u0003J'\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\t\u001a\u00020\u00042\b\b\u0002\u0010\n\u001a\u00020\u00062\b\b\u0002\u0010\u000b\u001a\u00020\u0004HÆ\u0001J\t\u0010\r\u001a\u00020\u0004HÖ\u0001J\t\u0010\u000f\u001a\u00020\u000eHÖ\u0001J\u0013\u0010\u0013\u001a\u00020\u00122\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010HÖ\u0003R\u0019\u0010\t\u001a\u00020\u00048\u0006@\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017R\u0019\u0010\n\u001a\u00020\u00068\u0006@\u0006¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001bR\u0019\u0010\u000b\u001a\u00020\u00048\u0006@\u0006¢\u0006\f\n\u0004\b\u001c\u0010\u0015\u001a\u0004\b\u001d\u0010\u0017¨\u0006!"}, d2 = {"Lio/shipbook/shipbooksdk/Models/LoginResponse;", "Lio/shipbook/shipbooksdk/Models/BaseObj;", "Lorg/json/JSONObject;", "toJson", "", "component1", "Lio/shipbook/shipbooksdk/Models/ConfigResponse;", "component2", "component3", MapplsLMSDbAdapter.KEY_TOKEN, Constants.KEY_CONFIG, "sessionUrl", Constants.COPY_TYPE, "toString", "", "hashCode", "", FitnessActivities.OTHER, "", "equals", "a", "Ljava/lang/String;", "getToken", "()Ljava/lang/String;", "b", "Lio/shipbook/shipbooksdk/Models/ConfigResponse;", "getConfig", "()Lio/shipbook/shipbooksdk/Models/ConfigResponse;", c.f10260a, "getSessionUrl", "<init>", "(Ljava/lang/String;Lio/shipbook/shipbooksdk/Models/ConfigResponse;Ljava/lang/String;)V", "Companion", "shipbooksdk_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes12.dex */
public final class LoginResponse implements BaseObj {
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final String f14033a;
    @NotNull
    public final ConfigResponse b;
    @NotNull
    public final String c;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002¨\u0006\b"}, d2 = {"Lio/shipbook/shipbooksdk/Models/LoginResponse$Companion;", "", "Lorg/json/JSONObject;", "json", "Lio/shipbook/shipbooksdk/Models/LoginResponse;", "create", "<init>", "()V", "shipbooksdk_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes12.dex */
    public static final class Companion {
        public Companion() {
        }

        @NotNull
        public final LoginResponse create(@NotNull JSONObject json) {
            Intrinsics.checkParameterIsNotNull(json, "json");
            String token = json.getString(MapplsLMSDbAdapter.KEY_TOKEN);
            ConfigResponse.Companion companion = ConfigResponse.Companion;
            JSONObject jSONObject = json.getJSONObject(Constants.KEY_CONFIG);
            Intrinsics.checkExpressionValueIsNotNull(jSONObject, "json.getJSONObject(\"config\")");
            ConfigResponse create = companion.create(jSONObject);
            String sessionUrl = json.getString("sessionUrl");
            Intrinsics.checkExpressionValueIsNotNull(token, "token");
            Intrinsics.checkExpressionValueIsNotNull(sessionUrl, "sessionUrl");
            return new LoginResponse(token, create, sessionUrl);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public LoginResponse(@NotNull String token, @NotNull ConfigResponse config, @NotNull String sessionUrl) {
        Intrinsics.checkParameterIsNotNull(token, "token");
        Intrinsics.checkParameterIsNotNull(config, "config");
        Intrinsics.checkParameterIsNotNull(sessionUrl, "sessionUrl");
        this.f14033a = token;
        this.b = config;
        this.c = sessionUrl;
    }

    @NotNull
    public static /* synthetic */ LoginResponse copy$default(LoginResponse loginResponse, String str, ConfigResponse configResponse, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = loginResponse.f14033a;
        }
        if ((i & 2) != 0) {
            configResponse = loginResponse.b;
        }
        if ((i & 4) != 0) {
            str2 = loginResponse.c;
        }
        return loginResponse.copy(str, configResponse, str2);
    }

    @NotNull
    public final String component1() {
        return this.f14033a;
    }

    @NotNull
    public final ConfigResponse component2() {
        return this.b;
    }

    @NotNull
    public final String component3() {
        return this.c;
    }

    @NotNull
    public final LoginResponse copy(@NotNull String token, @NotNull ConfigResponse config, @NotNull String sessionUrl) {
        Intrinsics.checkParameterIsNotNull(token, "token");
        Intrinsics.checkParameterIsNotNull(config, "config");
        Intrinsics.checkParameterIsNotNull(sessionUrl, "sessionUrl");
        return new LoginResponse(token, config, sessionUrl);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof LoginResponse) {
                LoginResponse loginResponse = (LoginResponse) obj;
                return Intrinsics.areEqual(this.f14033a, loginResponse.f14033a) && Intrinsics.areEqual(this.b, loginResponse.b) && Intrinsics.areEqual(this.c, loginResponse.c);
            }
            return false;
        }
        return true;
    }

    @NotNull
    public final ConfigResponse getConfig() {
        return this.b;
    }

    @NotNull
    public final String getSessionUrl() {
        return this.c;
    }

    @NotNull
    public final String getToken() {
        return this.f14033a;
    }

    public int hashCode() {
        String str = this.f14033a;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        ConfigResponse configResponse = this.b;
        int hashCode2 = (hashCode + (configResponse != null ? configResponse.hashCode() : 0)) * 31;
        String str2 = this.c;
        return hashCode2 + (str2 != null ? str2.hashCode() : 0);
    }

    @Override // io.shipbook.shipbooksdk.Models.BaseObj
    @NotNull
    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(MapplsLMSDbAdapter.KEY_TOKEN, this.f14033a);
        jSONObject.put(Constants.KEY_CONFIG, this.b.toJson());
        jSONObject.put("sessionUrl", this.c);
        return jSONObject;
    }

    @NotNull
    public String toString() {
        return "LoginResponse(token=" + this.f14033a + ", config=" + this.b + ", sessionUrl=" + this.c + ")";
    }
}
