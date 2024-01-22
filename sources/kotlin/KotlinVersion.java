package kotlin;

import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@SinceKotlin(version = "1.1")
/* loaded from: classes12.dex */
public final class KotlinVersion implements Comparable<KotlinVersion> {
    public static final int MAX_COMPONENT_VALUE = 255;
    public final int h;
    public final int i;
    public final int j;
    public final int k;
    @NotNull
    public static final Companion Companion = new Companion(null);
    @JvmField
    @NotNull
    public static final KotlinVersion CURRENT = b.a();

    /* loaded from: classes12.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public KotlinVersion(int i, int i2, int i3) {
        this.h = i;
        this.i = i2;
        this.j = i3;
        this.k = a(i, i2, i3);
    }

    public final int a(int i, int i2, int i3) {
        boolean z = false;
        if (new IntRange(0, 255).contains(i) && new IntRange(0, 255).contains(i2) && new IntRange(0, 255).contains(i3)) {
            z = true;
        }
        if (z) {
            return (i << 16) + (i2 << 8) + i3;
        }
        throw new IllegalArgumentException(("Version components are out of range: " + i + '.' + i2 + '.' + i3).toString());
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        KotlinVersion kotlinVersion = obj instanceof KotlinVersion ? (KotlinVersion) obj : null;
        return kotlinVersion != null && this.k == kotlinVersion.k;
    }

    public final int getMajor() {
        return this.h;
    }

    public final int getMinor() {
        return this.i;
    }

    public final int getPatch() {
        return this.j;
    }

    public int hashCode() {
        return this.k;
    }

    public final boolean isAtLeast(int i, int i2) {
        int i3 = this.h;
        return i3 > i || (i3 == i && this.i >= i2);
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.h);
        sb.append('.');
        sb.append(this.i);
        sb.append('.');
        sb.append(this.j);
        return sb.toString();
    }

    @Override // java.lang.Comparable
    public int compareTo(@NotNull KotlinVersion other) {
        Intrinsics.checkNotNullParameter(other, "other");
        return this.k - other.k;
    }

    public final boolean isAtLeast(int i, int i2, int i3) {
        int i4;
        int i5 = this.h;
        return i5 > i || (i5 == i && ((i4 = this.i) > i2 || (i4 == i2 && this.j >= i3)));
    }

    public KotlinVersion(int i, int i2) {
        this(i, i2, 0);
    }
}
