package io.shipbook.shipbooksdk.Models;

import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.google.android.gms.fitness.FitnessActivities;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.android.material.color.c;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u000e\b\u0080\b\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB!\u0012\u0006\u0010\b\u001a\u00020\u0002\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u001b\u0010\u001cJ\t\u0010\u0003\u001a\u00020\u0002HÆ\u0003J\t\u0010\u0004\u001a\u00020\u0002HÆ\u0003J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0004\b\u0006\u0010\u0007J0\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\b\u001a\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\u00022\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\r\u001a\u00020\u0002HÖ\u0001J\t\u0010\u000e\u001a\u00020\u0005HÖ\u0001J\u0013\u0010\u0011\u001a\u00020\u00102\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\b\u001a\u00020\u00028\u0006@\u0006¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u0019\u0010\t\u001a\u00020\u00028\u0006@\u0006¢\u0006\f\n\u0004\b\u0016\u0010\u0013\u001a\u0004\b\u0017\u0010\u0015R\u001b\u0010\n\u001a\u0004\u0018\u00010\u00058\u0006@\u0006¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u0007¨\u0006\u001e"}, d2 = {"Lio/shipbook/shipbooksdk/Models/ErrorResponse;", "", "", "component1", "component2", "", "component3", "()Ljava/lang/Integer;", AppMeasurementSdk.ConditionalUserProperty.NAME, Constants.KEY_MESSAGE, NotificationCompat.CATEGORY_STATUS, Constants.COPY_TYPE, "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;)Lio/shipbook/shipbooksdk/Models/ErrorResponse;", "toString", "hashCode", FitnessActivities.OTHER, "", "equals", "a", "Ljava/lang/String;", "getName", "()Ljava/lang/String;", "b", "getMessage", c.f10260a, "Ljava/lang/Integer;", "getStatus", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;)V", "Companion", "shipbooksdk_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes12.dex */
public final class ErrorResponse {
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final String f14031a;
    @NotNull
    public final String b;
    @Nullable
    public final Integer c;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002¨\u0006\b"}, d2 = {"Lio/shipbook/shipbooksdk/Models/ErrorResponse$Companion;", "", "Lorg/json/JSONObject;", "json", "Lio/shipbook/shipbooksdk/Models/ErrorResponse;", "create", "<init>", "()V", "shipbooksdk_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes12.dex */
    public static final class Companion {
        public Companion() {
        }

        @NotNull
        public final ErrorResponse create(@NotNull JSONObject json) {
            Intrinsics.checkParameterIsNotNull(json, "json");
            String name = json.getString(AppMeasurementSdk.ConditionalUserProperty.NAME);
            String message = json.getString(Constants.KEY_MESSAGE);
            int optInt = json.optInt(NotificationCompat.CATEGORY_STATUS);
            Intrinsics.checkExpressionValueIsNotNull(name, "name");
            Intrinsics.checkExpressionValueIsNotNull(message, "message");
            return new ErrorResponse(name, message, Integer.valueOf(optInt));
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public ErrorResponse(@NotNull String name, @NotNull String message, @Nullable Integer num) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(message, "message");
        this.f14031a = name;
        this.b = message;
        this.c = num;
    }

    @NotNull
    public static /* synthetic */ ErrorResponse copy$default(ErrorResponse errorResponse, String str, String str2, Integer num, int i, Object obj) {
        if ((i & 1) != 0) {
            str = errorResponse.f14031a;
        }
        if ((i & 2) != 0) {
            str2 = errorResponse.b;
        }
        if ((i & 4) != 0) {
            num = errorResponse.c;
        }
        return errorResponse.copy(str, str2, num);
    }

    @NotNull
    public final String component1() {
        return this.f14031a;
    }

    @NotNull
    public final String component2() {
        return this.b;
    }

    @Nullable
    public final Integer component3() {
        return this.c;
    }

    @NotNull
    public final ErrorResponse copy(@NotNull String name, @NotNull String message, @Nullable Integer num) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(message, "message");
        return new ErrorResponse(name, message, num);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof ErrorResponse) {
                ErrorResponse errorResponse = (ErrorResponse) obj;
                return Intrinsics.areEqual(this.f14031a, errorResponse.f14031a) && Intrinsics.areEqual(this.b, errorResponse.b) && Intrinsics.areEqual(this.c, errorResponse.c);
            }
            return false;
        }
        return true;
    }

    @NotNull
    public final String getMessage() {
        return this.b;
    }

    @NotNull
    public final String getName() {
        return this.f14031a;
    }

    @Nullable
    public final Integer getStatus() {
        return this.c;
    }

    public int hashCode() {
        String str = this.f14031a;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.b;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        Integer num = this.c;
        return hashCode2 + (num != null ? num.hashCode() : 0);
    }

    @NotNull
    public String toString() {
        return "ErrorResponse(name=" + this.f14031a + ", message=" + this.b + ", status=" + this.c + ")";
    }
}
