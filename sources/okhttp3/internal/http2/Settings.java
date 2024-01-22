package okhttp3.internal.http2;

import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class Settings {
    public static final int COUNT = 10;
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final int DEFAULT_INITIAL_WINDOW_SIZE = 65535;
    public static final int ENABLE_PUSH = 2;
    public static final int HEADER_TABLE_SIZE = 1;
    public static final int INITIAL_WINDOW_SIZE = 7;
    public static final int MAX_CONCURRENT_STREAMS = 4;
    public static final int MAX_FRAME_SIZE = 5;
    public static final int MAX_HEADER_LIST_SIZE = 6;

    /* renamed from: a  reason: collision with root package name */
    public int f14288a;
    @NotNull
    public final int[] b = new int[10];

    /* loaded from: classes12.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public final void clear() {
        this.f14288a = 0;
        ArraysKt___ArraysJvmKt.fill$default(this.b, 0, 0, 0, 6, (Object) null);
    }

    public final int get(int i) {
        return this.b[i];
    }

    public final boolean getEnablePush(boolean z) {
        return (this.f14288a & 4) != 0 ? this.b[2] == 1 : z;
    }

    public final int getHeaderTableSize() {
        if ((this.f14288a & 2) != 0) {
            return this.b[1];
        }
        return -1;
    }

    public final int getInitialWindowSize() {
        if ((this.f14288a & 128) != 0) {
            return this.b[7];
        }
        return 65535;
    }

    public final int getMaxConcurrentStreams() {
        if ((this.f14288a & 16) != 0) {
            return this.b[4];
        }
        return Integer.MAX_VALUE;
    }

    public final int getMaxFrameSize(int i) {
        return (this.f14288a & 32) != 0 ? this.b[5] : i;
    }

    public final int getMaxHeaderListSize(int i) {
        return (this.f14288a & 64) != 0 ? this.b[6] : i;
    }

    public final boolean isSet(int i) {
        return ((1 << i) & this.f14288a) != 0;
    }

    public final void merge(@NotNull Settings other) {
        Intrinsics.checkNotNullParameter(other, "other");
        int i = 0;
        while (i < 10) {
            int i2 = i + 1;
            if (other.isSet(i)) {
                set(i, other.get(i));
            }
            i = i2;
        }
    }

    @NotNull
    public final Settings set(int i, int i2) {
        if (i >= 0) {
            int[] iArr = this.b;
            if (i < iArr.length) {
                this.f14288a = (1 << i) | this.f14288a;
                iArr[i] = i2;
            }
        }
        return this;
    }

    public final int size() {
        return Integer.bitCount(this.f14288a);
    }
}
