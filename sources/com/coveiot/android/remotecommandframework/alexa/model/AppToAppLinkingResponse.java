package com.coveiot.android.remotecommandframework.alexa.model;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class AppToAppLinkingResponse {
    @Nullable
    private EnablementData data;
    private int errorCode;
    @Nullable
    private String errorMessage;
    private final boolean isSuccess;

    public AppToAppLinkingResponse(boolean z, @Nullable String str, @Nullable EnablementData enablementData, int i) {
        this.isSuccess = z;
        this.errorMessage = str;
        this.data = enablementData;
        this.errorCode = i;
    }

    public static /* synthetic */ AppToAppLinkingResponse copy$default(AppToAppLinkingResponse appToAppLinkingResponse, boolean z, String str, EnablementData enablementData, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z = appToAppLinkingResponse.isSuccess;
        }
        if ((i2 & 2) != 0) {
            str = appToAppLinkingResponse.errorMessage;
        }
        if ((i2 & 4) != 0) {
            enablementData = appToAppLinkingResponse.data;
        }
        if ((i2 & 8) != 0) {
            i = appToAppLinkingResponse.errorCode;
        }
        return appToAppLinkingResponse.copy(z, str, enablementData, i);
    }

    public final boolean component1() {
        return this.isSuccess;
    }

    @Nullable
    public final String component2() {
        return this.errorMessage;
    }

    @Nullable
    public final EnablementData component3() {
        return this.data;
    }

    public final int component4() {
        return this.errorCode;
    }

    @NotNull
    public final AppToAppLinkingResponse copy(boolean z, @Nullable String str, @Nullable EnablementData enablementData, int i) {
        return new AppToAppLinkingResponse(z, str, enablementData, i);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof AppToAppLinkingResponse) {
            AppToAppLinkingResponse appToAppLinkingResponse = (AppToAppLinkingResponse) obj;
            return this.isSuccess == appToAppLinkingResponse.isSuccess && Intrinsics.areEqual(this.errorMessage, appToAppLinkingResponse.errorMessage) && Intrinsics.areEqual(this.data, appToAppLinkingResponse.data) && this.errorCode == appToAppLinkingResponse.errorCode;
        }
        return false;
    }

    @Nullable
    public final EnablementData getData() {
        return this.data;
    }

    public final int getErrorCode() {
        return this.errorCode;
    }

    @Nullable
    public final String getErrorMessage() {
        return this.errorMessage;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [int] */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r0v9 */
    public int hashCode() {
        boolean z = this.isSuccess;
        ?? r0 = z;
        if (z) {
            r0 = 1;
        }
        int i = r0 * 31;
        String str = this.errorMessage;
        int hashCode = (i + (str == null ? 0 : str.hashCode())) * 31;
        EnablementData enablementData = this.data;
        return ((hashCode + (enablementData != null ? enablementData.hashCode() : 0)) * 31) + Integer.hashCode(this.errorCode);
    }

    public final boolean isSuccess() {
        return this.isSuccess;
    }

    public final void setData(@Nullable EnablementData enablementData) {
        this.data = enablementData;
    }

    public final void setErrorCode(int i) {
        this.errorCode = i;
    }

    public final void setErrorMessage(@Nullable String str) {
        this.errorMessage = str;
    }

    @NotNull
    public String toString() {
        return "AppToAppLinkingResponse(isSuccess=" + this.isSuccess + ", errorMessage=" + this.errorMessage + ", data=" + this.data + ", errorCode=" + this.errorCode + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ AppToAppLinkingResponse(boolean z, String str, EnablementData enablementData, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(z, (i2 & 2) != 0 ? null : str, (i2 & 4) != 0 ? null : enablementData, (i2 & 8) != 0 ? 0 : i);
    }
}
