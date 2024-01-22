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
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\b\u0080\b\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u000f\u0012\u0006\u0010\u0006\u001a\u00020\u0004¢\u0006\u0004\b\u0013\u0010\u0014J\b\u0010\u0003\u001a\u00020\u0002H\u0016J\t\u0010\u0005\u001a\u00020\u0004HÆ\u0003J\u0013\u0010\u0007\u001a\u00020\u00002\b\b\u0002\u0010\u0006\u001a\u00020\u0004HÆ\u0001J\t\u0010\b\u001a\u00020\u0004HÖ\u0001J\t\u0010\n\u001a\u00020\tHÖ\u0001J\u0013\u0010\u000e\u001a\u00020\r2\b\u0010\f\u001a\u0004\u0018\u00010\u000bHÖ\u0003R\u0019\u0010\u0006\u001a\u00020\u00048\u0006@\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0016"}, d2 = {"Lio/shipbook/shipbooksdk/Models/RefreshTokenResponse;", "Lio/shipbook/shipbooksdk/Models/BaseObj;", "Lorg/json/JSONObject;", "toJson", "", "component1", MapplsLMSDbAdapter.KEY_TOKEN, Constants.COPY_TYPE, "toString", "", "hashCode", "", FitnessActivities.OTHER, "", "equals", "a", "Ljava/lang/String;", "getToken", "()Ljava/lang/String;", "<init>", "(Ljava/lang/String;)V", "Companion", "shipbooksdk_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes12.dex */
public final class RefreshTokenResponse implements BaseObj {
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final String f14036a;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002¨\u0006\b"}, d2 = {"Lio/shipbook/shipbooksdk/Models/RefreshTokenResponse$Companion;", "", "Lorg/json/JSONObject;", "json", "Lio/shipbook/shipbooksdk/Models/RefreshTokenResponse;", "create", "<init>", "()V", "shipbooksdk_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes12.dex */
    public static final class Companion {
        public Companion() {
        }

        @NotNull
        public final RefreshTokenResponse create(@NotNull JSONObject json) {
            Intrinsics.checkParameterIsNotNull(json, "json");
            String token = json.getString(MapplsLMSDbAdapter.KEY_TOKEN);
            Intrinsics.checkExpressionValueIsNotNull(token, "token");
            return new RefreshTokenResponse(token);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public RefreshTokenResponse(@NotNull String token) {
        Intrinsics.checkParameterIsNotNull(token, "token");
        this.f14036a = token;
    }

    @NotNull
    public static /* synthetic */ RefreshTokenResponse copy$default(RefreshTokenResponse refreshTokenResponse, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = refreshTokenResponse.f14036a;
        }
        return refreshTokenResponse.copy(str);
    }

    @NotNull
    public final String component1() {
        return this.f14036a;
    }

    @NotNull
    public final RefreshTokenResponse copy(@NotNull String token) {
        Intrinsics.checkParameterIsNotNull(token, "token");
        return new RefreshTokenResponse(token);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            return (obj instanceof RefreshTokenResponse) && Intrinsics.areEqual(this.f14036a, ((RefreshTokenResponse) obj).f14036a);
        }
        return true;
    }

    @NotNull
    public final String getToken() {
        return this.f14036a;
    }

    public int hashCode() {
        String str = this.f14036a;
        if (str != null) {
            return str.hashCode();
        }
        return 0;
    }

    @Override // io.shipbook.shipbooksdk.Models.BaseObj
    @NotNull
    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(MapplsLMSDbAdapter.KEY_TOKEN, this.f14036a);
        return jSONObject;
    }

    @NotNull
    public String toString() {
        return "RefreshTokenResponse(token=" + this.f14036a + ")";
    }
}
