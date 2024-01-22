package com.coveiot.android.leonardo.dashboard.health.spo2.model;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class Spo2DataResponse {
    @NotNull
    private String spo2ErrorMsg;
    @NotNull
    private final Spo2ResponseType spo2ResponseType;
    private final double spo2Value;

    public Spo2DataResponse(@NotNull Spo2ResponseType spo2ResponseType, double d, @NotNull String spo2ErrorMsg) {
        Intrinsics.checkNotNullParameter(spo2ResponseType, "spo2ResponseType");
        Intrinsics.checkNotNullParameter(spo2ErrorMsg, "spo2ErrorMsg");
        this.spo2ResponseType = spo2ResponseType;
        this.spo2Value = d;
        this.spo2ErrorMsg = spo2ErrorMsg;
    }

    public static /* synthetic */ Spo2DataResponse copy$default(Spo2DataResponse spo2DataResponse, Spo2ResponseType spo2ResponseType, double d, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            spo2ResponseType = spo2DataResponse.spo2ResponseType;
        }
        if ((i & 2) != 0) {
            d = spo2DataResponse.spo2Value;
        }
        if ((i & 4) != 0) {
            str = spo2DataResponse.spo2ErrorMsg;
        }
        return spo2DataResponse.copy(spo2ResponseType, d, str);
    }

    @NotNull
    public final Spo2ResponseType component1() {
        return this.spo2ResponseType;
    }

    public final double component2() {
        return this.spo2Value;
    }

    @NotNull
    public final String component3() {
        return this.spo2ErrorMsg;
    }

    @NotNull
    public final Spo2DataResponse copy(@NotNull Spo2ResponseType spo2ResponseType, double d, @NotNull String spo2ErrorMsg) {
        Intrinsics.checkNotNullParameter(spo2ResponseType, "spo2ResponseType");
        Intrinsics.checkNotNullParameter(spo2ErrorMsg, "spo2ErrorMsg");
        return new Spo2DataResponse(spo2ResponseType, d, spo2ErrorMsg);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Spo2DataResponse) {
            Spo2DataResponse spo2DataResponse = (Spo2DataResponse) obj;
            return this.spo2ResponseType == spo2DataResponse.spo2ResponseType && Double.compare(this.spo2Value, spo2DataResponse.spo2Value) == 0 && Intrinsics.areEqual(this.spo2ErrorMsg, spo2DataResponse.spo2ErrorMsg);
        }
        return false;
    }

    @NotNull
    public final String getSpo2ErrorMsg() {
        return this.spo2ErrorMsg;
    }

    @NotNull
    public final Spo2ResponseType getSpo2ResponseType() {
        return this.spo2ResponseType;
    }

    public final double getSpo2Value() {
        return this.spo2Value;
    }

    public int hashCode() {
        return (((this.spo2ResponseType.hashCode() * 31) + Double.hashCode(this.spo2Value)) * 31) + this.spo2ErrorMsg.hashCode();
    }

    public final void setSpo2ErrorMsg(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.spo2ErrorMsg = str;
    }

    @NotNull
    public String toString() {
        return "Spo2DataResponse(spo2ResponseType=" + this.spo2ResponseType + ", spo2Value=" + this.spo2Value + ", spo2ErrorMsg=" + this.spo2ErrorMsg + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ Spo2DataResponse(Spo2ResponseType spo2ResponseType, double d, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(spo2ResponseType, d, (i & 4) != 0 ? "" : str);
    }
}
