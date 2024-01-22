package com.clevertap.android.sdk.bitmap;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.Map;
import kotlin.collections.s;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class HttpUrlConnectionParams {

    /* renamed from: a  reason: collision with root package name */
    public int f2597a;
    public int b;
    public boolean c;
    public boolean d;
    @NotNull
    public Map<String, String> e;

    @JvmOverloads
    public HttpUrlConnectionParams() {
        this(0, 0, false, false, null, 31, null);
    }

    @JvmOverloads
    public HttpUrlConnectionParams(int i) {
        this(i, 0, false, false, null, 30, null);
    }

    @JvmOverloads
    public HttpUrlConnectionParams(int i, int i2) {
        this(i, i2, false, false, null, 28, null);
    }

    @JvmOverloads
    public HttpUrlConnectionParams(int i, int i2, boolean z) {
        this(i, i2, z, false, null, 24, null);
    }

    @JvmOverloads
    public HttpUrlConnectionParams(int i, int i2, boolean z, boolean z2) {
        this(i, i2, z, z2, null, 16, null);
    }

    @JvmOverloads
    public HttpUrlConnectionParams(int i, int i2, boolean z, boolean z2, @NotNull Map<String, String> requestMap) {
        Intrinsics.checkNotNullParameter(requestMap, "requestMap");
        this.f2597a = i;
        this.b = i2;
        this.c = z;
        this.d = z2;
        this.e = requestMap;
    }

    public static /* synthetic */ HttpUrlConnectionParams copy$default(HttpUrlConnectionParams httpUrlConnectionParams, int i, int i2, boolean z, boolean z2, Map map, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = httpUrlConnectionParams.f2597a;
        }
        if ((i3 & 2) != 0) {
            i2 = httpUrlConnectionParams.b;
        }
        int i4 = i2;
        if ((i3 & 4) != 0) {
            z = httpUrlConnectionParams.c;
        }
        boolean z3 = z;
        if ((i3 & 8) != 0) {
            z2 = httpUrlConnectionParams.d;
        }
        boolean z4 = z2;
        Map<String, String> map2 = map;
        if ((i3 & 16) != 0) {
            map2 = httpUrlConnectionParams.e;
        }
        return httpUrlConnectionParams.copy(i, i4, z3, z4, map2);
    }

    public final int component1() {
        return this.f2597a;
    }

    public final int component2() {
        return this.b;
    }

    public final boolean component3() {
        return this.c;
    }

    public final boolean component4() {
        return this.d;
    }

    @NotNull
    public final Map<String, String> component5() {
        return this.e;
    }

    @NotNull
    public final HttpUrlConnectionParams copy(int i, int i2, boolean z, boolean z2, @NotNull Map<String, String> requestMap) {
        Intrinsics.checkNotNullParameter(requestMap, "requestMap");
        return new HttpUrlConnectionParams(i, i2, z, z2, requestMap);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof HttpUrlConnectionParams) {
            HttpUrlConnectionParams httpUrlConnectionParams = (HttpUrlConnectionParams) obj;
            return this.f2597a == httpUrlConnectionParams.f2597a && this.b == httpUrlConnectionParams.b && this.c == httpUrlConnectionParams.c && this.d == httpUrlConnectionParams.d && Intrinsics.areEqual(this.e, httpUrlConnectionParams.e);
        }
        return false;
    }

    public final int getConnectTimeout() {
        return this.f2597a;
    }

    public final boolean getDoInput() {
        return this.d;
    }

    public final int getReadTimeout() {
        return this.b;
    }

    @NotNull
    public final Map<String, String> getRequestMap() {
        return this.e;
    }

    public final boolean getUseCaches() {
        return this.c;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int hashCode = ((Integer.hashCode(this.f2597a) * 31) + Integer.hashCode(this.b)) * 31;
        boolean z = this.c;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        int i2 = (hashCode + i) * 31;
        boolean z2 = this.d;
        return ((i2 + (z2 ? 1 : z2 ? 1 : 0)) * 31) + this.e.hashCode();
    }

    public final void setConnectTimeout(int i) {
        this.f2597a = i;
    }

    public final void setDoInput(boolean z) {
        this.d = z;
    }

    public final void setReadTimeout(int i) {
        this.b = i;
    }

    public final void setRequestMap(@NotNull Map<String, String> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        this.e = map;
    }

    public final void setUseCaches(boolean z) {
        this.c = z;
    }

    @NotNull
    public String toString() {
        return "HttpUrlConnectionParams(connectTimeout=" + this.f2597a + ", readTimeout=" + this.b + ", useCaches=" + this.c + ", doInput=" + this.d + ", requestMap=" + this.e + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ HttpUrlConnectionParams(int i, int i2, boolean z, boolean z2, Map map, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? 0 : i, (i3 & 2) != 0 ? 0 : i2, (i3 & 4) != 0 ? false : z, (i3 & 8) == 0 ? z2 : false, (i3 & 16) != 0 ? s.emptyMap() : map);
    }
}
