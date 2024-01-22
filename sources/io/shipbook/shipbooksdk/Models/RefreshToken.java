package io.shipbook.shipbooksdk.Models;

import com.clevertap.android.sdk.Constants;
import com.google.android.gms.fitness.FitnessActivities;
import com.mappls.android.lms.MapplsLMSDbAdapter;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\b\u0080\b\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u0017\u0012\u0006\u0010\u0007\u001a\u00020\u0004\u0012\u0006\u0010\b\u001a\u00020\u0004¢\u0006\u0004\b\u0017\u0010\u0018J\b\u0010\u0003\u001a\u00020\u0002H\u0016J\t\u0010\u0005\u001a\u00020\u0004HÆ\u0003J\t\u0010\u0006\u001a\u00020\u0004HÆ\u0003J\u001d\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004HÆ\u0001J\t\u0010\n\u001a\u00020\u0004HÖ\u0001J\t\u0010\f\u001a\u00020\u000bHÖ\u0001J\u0013\u0010\u0010\u001a\u00020\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\rHÖ\u0003R\u0019\u0010\u0007\u001a\u00020\u00048\u0006@\u0006¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R\u0019\u0010\b\u001a\u00020\u00048\u0006@\u0006¢\u0006\f\n\u0004\b\u0015\u0010\u0012\u001a\u0004\b\u0016\u0010\u0014¨\u0006\u001a"}, d2 = {"Lio/shipbook/shipbooksdk/Models/RefreshToken;", "Lio/shipbook/shipbooksdk/Models/BaseObj;", "Lorg/json/JSONObject;", "toJson", "", "component1", "component2", MapplsLMSDbAdapter.KEY_TOKEN, "appKey", Constants.COPY_TYPE, "toString", "", "hashCode", "", FitnessActivities.OTHER, "", "equals", "a", "Ljava/lang/String;", "getToken", "()Ljava/lang/String;", "b", "getAppKey", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "Companion", "shipbooksdk_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes12.dex */
public final class RefreshToken implements BaseObj {
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final String f14035a;
    @NotNull
    public final String b;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002¨\u0006\b"}, d2 = {"Lio/shipbook/shipbooksdk/Models/RefreshToken$Companion;", "", "Lorg/json/JSONObject;", "json", "Lio/shipbook/shipbooksdk/Models/RefreshToken;", "create", "<init>", "()V", "shipbooksdk_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes12.dex */
    public static final class Companion {
        public Companion() {
        }

        @NotNull
        public final RefreshToken create(@NotNull JSONObject json) {
            Intrinsics.checkParameterIsNotNull(json, "json");
            String token = json.getString(MapplsLMSDbAdapter.KEY_TOKEN);
            String appKey = json.getString("appKey");
            Intrinsics.checkExpressionValueIsNotNull(token, "token");
            Intrinsics.checkExpressionValueIsNotNull(appKey, "appKey");
            return new RefreshToken(token, appKey);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public RefreshToken(@NotNull String token, @NotNull String appKey) {
        Intrinsics.checkParameterIsNotNull(token, "token");
        Intrinsics.checkParameterIsNotNull(appKey, "appKey");
        this.f14035a = token;
        this.b = appKey;
    }

    @NotNull
    public static /* synthetic */ RefreshToken copy$default(RefreshToken refreshToken, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = refreshToken.f14035a;
        }
        if ((i & 2) != 0) {
            str2 = refreshToken.b;
        }
        return refreshToken.copy(str, str2);
    }

    @NotNull
    public final String component1() {
        return this.f14035a;
    }

    @NotNull
    public final String component2() {
        return this.b;
    }

    @NotNull
    public final RefreshToken copy(@NotNull String token, @NotNull String appKey) {
        Intrinsics.checkParameterIsNotNull(token, "token");
        Intrinsics.checkParameterIsNotNull(appKey, "appKey");
        return new RefreshToken(token, appKey);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof RefreshToken) {
                RefreshToken refreshToken = (RefreshToken) obj;
                return Intrinsics.areEqual(this.f14035a, refreshToken.f14035a) && Intrinsics.areEqual(this.b, refreshToken.b);
            }
            return false;
        }
        return true;
    }

    @NotNull
    public final String getAppKey() {
        return this.b;
    }

    @NotNull
    public final String getToken() {
        return this.f14035a;
    }

    public int hashCode() {
        String str = this.f14035a;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.b;
        return hashCode + (str2 != null ? str2.hashCode() : 0);
    }

    @Override // io.shipbook.shipbooksdk.Models.BaseObj
    @NotNull
    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(MapplsLMSDbAdapter.KEY_TOKEN, this.f14035a);
        jSONObject.put("appKey", this.b);
        return jSONObject;
    }

    @NotNull
    public String toString() {
        return "RefreshToken(token=" + this.f14035a + ", appKey=" + this.b + ")";
    }
}
