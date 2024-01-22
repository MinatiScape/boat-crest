package com.clevertap.android.sdk.bitmap;

import android.content.Context;
import com.clevertap.android.sdk.CleverTapInstanceConfig;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class BitmapDownloadRequest {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public String f2590a;
    public boolean b;
    @Nullable
    public final Context c;
    @Nullable
    public final CleverTapInstanceConfig d;
    public long e;
    public int f;

    @JvmOverloads
    public BitmapDownloadRequest(@Nullable String str) {
        this(str, false, null, null, 0L, 0, 62, null);
    }

    @JvmOverloads
    public BitmapDownloadRequest(@Nullable String str, boolean z) {
        this(str, z, null, null, 0L, 0, 60, null);
    }

    @JvmOverloads
    public BitmapDownloadRequest(@Nullable String str, boolean z, @Nullable Context context) {
        this(str, z, context, null, 0L, 0, 56, null);
    }

    @JvmOverloads
    public BitmapDownloadRequest(@Nullable String str, boolean z, @Nullable Context context, @Nullable CleverTapInstanceConfig cleverTapInstanceConfig) {
        this(str, z, context, cleverTapInstanceConfig, 0L, 0, 48, null);
    }

    @JvmOverloads
    public BitmapDownloadRequest(@Nullable String str, boolean z, @Nullable Context context, @Nullable CleverTapInstanceConfig cleverTapInstanceConfig, long j) {
        this(str, z, context, cleverTapInstanceConfig, j, 0, 32, null);
    }

    @JvmOverloads
    public BitmapDownloadRequest(@Nullable String str, boolean z, @Nullable Context context, @Nullable CleverTapInstanceConfig cleverTapInstanceConfig, long j, int i) {
        this.f2590a = str;
        this.b = z;
        this.c = context;
        this.d = cleverTapInstanceConfig;
        this.e = j;
        this.f = i;
    }

    public static /* synthetic */ BitmapDownloadRequest copy$default(BitmapDownloadRequest bitmapDownloadRequest, String str, boolean z, Context context, CleverTapInstanceConfig cleverTapInstanceConfig, long j, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = bitmapDownloadRequest.f2590a;
        }
        if ((i2 & 2) != 0) {
            z = bitmapDownloadRequest.b;
        }
        boolean z2 = z;
        if ((i2 & 4) != 0) {
            context = bitmapDownloadRequest.c;
        }
        Context context2 = context;
        if ((i2 & 8) != 0) {
            cleverTapInstanceConfig = bitmapDownloadRequest.d;
        }
        CleverTapInstanceConfig cleverTapInstanceConfig2 = cleverTapInstanceConfig;
        if ((i2 & 16) != 0) {
            j = bitmapDownloadRequest.e;
        }
        long j2 = j;
        if ((i2 & 32) != 0) {
            i = bitmapDownloadRequest.f;
        }
        return bitmapDownloadRequest.copy(str, z2, context2, cleverTapInstanceConfig2, j2, i);
    }

    @Nullable
    public final String component1() {
        return this.f2590a;
    }

    public final boolean component2() {
        return this.b;
    }

    @Nullable
    public final Context component3() {
        return this.c;
    }

    @Nullable
    public final CleverTapInstanceConfig component4() {
        return this.d;
    }

    public final long component5() {
        return this.e;
    }

    public final int component6() {
        return this.f;
    }

    @NotNull
    public final BitmapDownloadRequest copy(@Nullable String str, boolean z, @Nullable Context context, @Nullable CleverTapInstanceConfig cleverTapInstanceConfig, long j, int i) {
        return new BitmapDownloadRequest(str, z, context, cleverTapInstanceConfig, j, i);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BitmapDownloadRequest) {
            BitmapDownloadRequest bitmapDownloadRequest = (BitmapDownloadRequest) obj;
            return Intrinsics.areEqual(this.f2590a, bitmapDownloadRequest.f2590a) && this.b == bitmapDownloadRequest.b && Intrinsics.areEqual(this.c, bitmapDownloadRequest.c) && Intrinsics.areEqual(this.d, bitmapDownloadRequest.d) && this.e == bitmapDownloadRequest.e && this.f == bitmapDownloadRequest.f;
        }
        return false;
    }

    @Nullable
    public final String getBitmapPath() {
        return this.f2590a;
    }

    @Nullable
    public final Context getContext() {
        return this.c;
    }

    public final int getDownloadSizeLimitInBytes() {
        return this.f;
    }

    public final long getDownloadTimeLimitInMillis() {
        return this.e;
    }

    public final boolean getFallbackToAppIcon() {
        return this.b;
    }

    @Nullable
    public final CleverTapInstanceConfig getInstanceConfig() {
        return this.d;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        String str = this.f2590a;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        boolean z = this.b;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        int i2 = (hashCode + i) * 31;
        Context context = this.c;
        int hashCode2 = (i2 + (context == null ? 0 : context.hashCode())) * 31;
        CleverTapInstanceConfig cleverTapInstanceConfig = this.d;
        return ((((hashCode2 + (cleverTapInstanceConfig != null ? cleverTapInstanceConfig.hashCode() : 0)) * 31) + Long.hashCode(this.e)) * 31) + Integer.hashCode(this.f);
    }

    public final void setBitmapPath(@Nullable String str) {
        this.f2590a = str;
    }

    public final void setDownloadSizeLimitInBytes(int i) {
        this.f = i;
    }

    public final void setDownloadTimeLimitInMillis(long j) {
        this.e = j;
    }

    public final void setFallbackToAppIcon(boolean z) {
        this.b = z;
    }

    @NotNull
    public String toString() {
        return "BitmapDownloadRequest(bitmapPath=" + this.f2590a + ", fallbackToAppIcon=" + this.b + ", context=" + this.c + ", instanceConfig=" + this.d + ", downloadTimeLimitInMillis=" + this.e + ", downloadSizeLimitInBytes=" + this.f + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ BitmapDownloadRequest(String str, boolean z, Context context, CleverTapInstanceConfig cleverTapInstanceConfig, long j, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i2 & 2) != 0 ? false : z, (i2 & 4) != 0 ? null : context, (i2 & 8) == 0 ? cleverTapInstanceConfig : null, (i2 & 16) != 0 ? -1L : j, (i2 & 32) != 0 ? -1 : i);
    }
}
