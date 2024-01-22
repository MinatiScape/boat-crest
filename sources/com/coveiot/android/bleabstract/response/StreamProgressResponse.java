package com.coveiot.android.bleabstract.response;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class StreamProgressResponse {

    /* renamed from: a  reason: collision with root package name */
    public boolean f3670a;
    public int b;
    public int c;
    public int d;

    public StreamProgressResponse() {
        this(false, 0, 0, 0, 15, null);
    }

    public StreamProgressResponse(boolean z, int i, int i2, int i3) {
        this.f3670a = z;
        this.b = i;
        this.c = i2;
        this.d = i3;
    }

    public static /* synthetic */ StreamProgressResponse copy$default(StreamProgressResponse streamProgressResponse, boolean z, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            z = streamProgressResponse.f3670a;
        }
        if ((i4 & 2) != 0) {
            i = streamProgressResponse.b;
        }
        if ((i4 & 4) != 0) {
            i2 = streamProgressResponse.c;
        }
        if ((i4 & 8) != 0) {
            i3 = streamProgressResponse.d;
        }
        return streamProgressResponse.copy(z, i, i2, i3);
    }

    public final boolean component1() {
        return this.f3670a;
    }

    public final int component2() {
        return this.b;
    }

    public final int component3() {
        return this.c;
    }

    public final int component4() {
        return this.d;
    }

    @NotNull
    public final StreamProgressResponse copy(boolean z, int i, int i2, int i3) {
        return new StreamProgressResponse(z, i, i2, i3);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof StreamProgressResponse) {
            StreamProgressResponse streamProgressResponse = (StreamProgressResponse) obj;
            return this.f3670a == streamProgressResponse.f3670a && this.b == streamProgressResponse.b && this.c == streamProgressResponse.c && this.d == streamProgressResponse.d;
        }
        return false;
    }

    public final int getCompleted() {
        return this.d;
    }

    public final int getErrorCode() {
        return this.b;
    }

    public final boolean getStatus() {
        return this.f3670a;
    }

    public final int getTotal() {
        return this.c;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [int] */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r0v9 */
    public int hashCode() {
        boolean z = this.f3670a;
        ?? r0 = z;
        if (z) {
            r0 = 1;
        }
        return (((((r0 * 31) + Integer.hashCode(this.b)) * 31) + Integer.hashCode(this.c)) * 31) + Integer.hashCode(this.d);
    }

    public final void setCompleted(int i) {
        this.d = i;
    }

    public final void setErrorCode(int i) {
        this.b = i;
    }

    public final void setStatus(boolean z) {
        this.f3670a = z;
    }

    public final void setTotal(int i) {
        this.c = i;
    }

    @NotNull
    public String toString() {
        return "StreamProgressResponse(status=" + this.f3670a + ", errorCode=" + this.b + ", total=" + this.c + ", completed=" + this.d + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ StreamProgressResponse(boolean z, int i, int i2, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this((i4 & 1) != 0 ? false : z, (i4 & 2) != 0 ? 0 : i, (i4 & 4) != 0 ? 0 : i2, (i4 & 8) != 0 ? 0 : i3);
    }
}
