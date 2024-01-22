package kotlin.reflect;

import kotlin.NoWhenBranchMatchedException;
import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Marker;
@SinceKotlin(version = "1.1")
/* loaded from: classes12.dex */
public final class KTypeProjection {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @JvmField
    @NotNull
    public static final KTypeProjection star = new KTypeProjection(null, null);
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public final KVariance f14088a;
    @Nullable
    public final KType b;

    /* loaded from: classes12.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @PublishedApi
        public static /* synthetic */ void getStar$annotations() {
        }

        @JvmStatic
        @NotNull
        public final KTypeProjection contravariant(@NotNull KType type) {
            Intrinsics.checkNotNullParameter(type, "type");
            return new KTypeProjection(KVariance.IN, type);
        }

        @JvmStatic
        @NotNull
        public final KTypeProjection covariant(@NotNull KType type) {
            Intrinsics.checkNotNullParameter(type, "type");
            return new KTypeProjection(KVariance.OUT, type);
        }

        @NotNull
        public final KTypeProjection getSTAR() {
            return KTypeProjection.star;
        }

        @JvmStatic
        @NotNull
        public final KTypeProjection invariant(@NotNull KType type) {
            Intrinsics.checkNotNullParameter(type, "type");
            return new KTypeProjection(KVariance.INVARIANT, type);
        }
    }

    /* loaded from: classes12.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[KVariance.values().length];
            try {
                iArr[KVariance.INVARIANT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[KVariance.IN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[KVariance.OUT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public KTypeProjection(@Nullable KVariance kVariance, @Nullable KType kType) {
        String str;
        this.f14088a = kVariance;
        this.b = kType;
        if ((kVariance == null) == (kType == null)) {
            return;
        }
        if (kVariance == null) {
            str = "Star projection must have no type specified.";
        } else {
            str = "The projection variance " + kVariance + " requires type to be specified.";
        }
        throw new IllegalArgumentException(str.toString());
    }

    @JvmStatic
    @NotNull
    public static final KTypeProjection contravariant(@NotNull KType kType) {
        return Companion.contravariant(kType);
    }

    public static /* synthetic */ KTypeProjection copy$default(KTypeProjection kTypeProjection, KVariance kVariance, KType kType, int i, Object obj) {
        if ((i & 1) != 0) {
            kVariance = kTypeProjection.f14088a;
        }
        if ((i & 2) != 0) {
            kType = kTypeProjection.b;
        }
        return kTypeProjection.copy(kVariance, kType);
    }

    @JvmStatic
    @NotNull
    public static final KTypeProjection covariant(@NotNull KType kType) {
        return Companion.covariant(kType);
    }

    @JvmStatic
    @NotNull
    public static final KTypeProjection invariant(@NotNull KType kType) {
        return Companion.invariant(kType);
    }

    @Nullable
    public final KVariance component1() {
        return this.f14088a;
    }

    @Nullable
    public final KType component2() {
        return this.b;
    }

    @NotNull
    public final KTypeProjection copy(@Nullable KVariance kVariance, @Nullable KType kType) {
        return new KTypeProjection(kVariance, kType);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof KTypeProjection) {
            KTypeProjection kTypeProjection = (KTypeProjection) obj;
            return this.f14088a == kTypeProjection.f14088a && Intrinsics.areEqual(this.b, kTypeProjection.b);
        }
        return false;
    }

    @Nullable
    public final KType getType() {
        return this.b;
    }

    @Nullable
    public final KVariance getVariance() {
        return this.f14088a;
    }

    public int hashCode() {
        KVariance kVariance = this.f14088a;
        int hashCode = (kVariance == null ? 0 : kVariance.hashCode()) * 31;
        KType kType = this.b;
        return hashCode + (kType != null ? kType.hashCode() : 0);
    }

    @NotNull
    public String toString() {
        KVariance kVariance = this.f14088a;
        int i = kVariance == null ? -1 : WhenMappings.$EnumSwitchMapping$0[kVariance.ordinal()];
        if (i != -1) {
            if (i != 1) {
                if (i == 2) {
                    return "in " + this.b;
                } else if (i == 3) {
                    return "out " + this.b;
                } else {
                    throw new NoWhenBranchMatchedException();
                }
            }
            return String.valueOf(this.b);
        }
        return Marker.ANY_MARKER;
    }
}
