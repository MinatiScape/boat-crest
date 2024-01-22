package okhttp3;

import java.util.concurrent.TimeUnit;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class CacheControl {

    /* renamed from: a  reason: collision with root package name */
    public final boolean f14209a;
    public final boolean b;
    public final int c;
    public final int d;
    public final boolean e;
    public final boolean f;
    public final boolean g;
    public final int h;
    public final int i;
    public final boolean j;
    public final boolean k;
    public final boolean l;
    @Nullable
    public String m;
    @NotNull
    public static final Companion Companion = new Companion(null);
    @JvmField
    @NotNull
    public static final CacheControl FORCE_NETWORK = new Builder().noCache().build();
    @JvmField
    @NotNull
    public static final CacheControl FORCE_CACHE = new Builder().onlyIfCached().maxStale(Integer.MAX_VALUE, TimeUnit.SECONDS).build();

    /* loaded from: classes12.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public boolean f14210a;
        public boolean b;
        public int c = -1;
        public int d = -1;
        public int e = -1;
        public boolean f;
        public boolean g;
        public boolean h;

        public final int a(long j) {
            if (j > 2147483647L) {
                return Integer.MAX_VALUE;
            }
            return (int) j;
        }

        @NotNull
        public final CacheControl build() {
            return new CacheControl(this.f14210a, this.b, this.c, -1, false, false, false, this.d, this.e, this.f, this.g, this.h, null, null);
        }

        @NotNull
        public final Builder immutable() {
            this.h = true;
            return this;
        }

        @NotNull
        public final Builder maxAge(int i, @NotNull TimeUnit timeUnit) {
            Intrinsics.checkNotNullParameter(timeUnit, "timeUnit");
            if (i >= 0) {
                this.c = a(timeUnit.toSeconds(i));
                return this;
            }
            throw new IllegalArgumentException(Intrinsics.stringPlus("maxAge < 0: ", Integer.valueOf(i)).toString());
        }

        @NotNull
        public final Builder maxStale(int i, @NotNull TimeUnit timeUnit) {
            Intrinsics.checkNotNullParameter(timeUnit, "timeUnit");
            if (i >= 0) {
                this.d = a(timeUnit.toSeconds(i));
                return this;
            }
            throw new IllegalArgumentException(Intrinsics.stringPlus("maxStale < 0: ", Integer.valueOf(i)).toString());
        }

        @NotNull
        public final Builder minFresh(int i, @NotNull TimeUnit timeUnit) {
            Intrinsics.checkNotNullParameter(timeUnit, "timeUnit");
            if (i >= 0) {
                this.e = a(timeUnit.toSeconds(i));
                return this;
            }
            throw new IllegalArgumentException(Intrinsics.stringPlus("minFresh < 0: ", Integer.valueOf(i)).toString());
        }

        @NotNull
        public final Builder noCache() {
            this.f14210a = true;
            return this;
        }

        @NotNull
        public final Builder noStore() {
            this.b = true;
            return this;
        }

        @NotNull
        public final Builder noTransform() {
            this.g = true;
            return this;
        }

        @NotNull
        public final Builder onlyIfCached() {
            this.f = true;
            return this;
        }
    }

    /* loaded from: classes12.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final int a(String str, String str2, int i) {
            int length = str.length();
            while (i < length) {
                int i2 = i + 1;
                if (StringsKt__StringsKt.contains$default((CharSequence) str2, str.charAt(i), false, 2, (Object) null)) {
                    return i;
                }
                i = i2;
            }
            return str.length();
        }

        /* JADX WARN: Removed duplicated region for block: B:15:0x004c  */
        @kotlin.jvm.JvmStatic
        @org.jetbrains.annotations.NotNull
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final okhttp3.CacheControl parse(@org.jetbrains.annotations.NotNull okhttp3.Headers r31) {
            /*
                Method dump skipped, instructions count: 398
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.CacheControl.Companion.parse(okhttp3.Headers):okhttp3.CacheControl");
        }
    }

    public CacheControl(boolean z, boolean z2, int i, int i2, boolean z3, boolean z4, boolean z5, int i3, int i4, boolean z6, boolean z7, boolean z8, String str) {
        this.f14209a = z;
        this.b = z2;
        this.c = i;
        this.d = i2;
        this.e = z3;
        this.f = z4;
        this.g = z5;
        this.h = i3;
        this.i = i4;
        this.j = z6;
        this.k = z7;
        this.l = z8;
        this.m = str;
    }

    public /* synthetic */ CacheControl(boolean z, boolean z2, int i, int i2, boolean z3, boolean z4, boolean z5, int i3, int i4, boolean z6, boolean z7, boolean z8, String str, DefaultConstructorMarker defaultConstructorMarker) {
        this(z, z2, i, i2, z3, z4, z5, i3, i4, z6, z7, z8, str);
    }

    @JvmStatic
    @NotNull
    public static final CacheControl parse(@NotNull Headers headers) {
        return Companion.parse(headers);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "immutable", imports = {}))
    @JvmName(name = "-deprecated_immutable")
    /* renamed from: -deprecated_immutable  reason: not valid java name */
    public final boolean m799deprecated_immutable() {
        return this.l;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "maxAgeSeconds", imports = {}))
    @JvmName(name = "-deprecated_maxAgeSeconds")
    /* renamed from: -deprecated_maxAgeSeconds  reason: not valid java name */
    public final int m800deprecated_maxAgeSeconds() {
        return this.c;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "maxStaleSeconds", imports = {}))
    @JvmName(name = "-deprecated_maxStaleSeconds")
    /* renamed from: -deprecated_maxStaleSeconds  reason: not valid java name */
    public final int m801deprecated_maxStaleSeconds() {
        return this.h;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "minFreshSeconds", imports = {}))
    @JvmName(name = "-deprecated_minFreshSeconds")
    /* renamed from: -deprecated_minFreshSeconds  reason: not valid java name */
    public final int m802deprecated_minFreshSeconds() {
        return this.i;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "mustRevalidate", imports = {}))
    @JvmName(name = "-deprecated_mustRevalidate")
    /* renamed from: -deprecated_mustRevalidate  reason: not valid java name */
    public final boolean m803deprecated_mustRevalidate() {
        return this.g;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "noCache", imports = {}))
    @JvmName(name = "-deprecated_noCache")
    /* renamed from: -deprecated_noCache  reason: not valid java name */
    public final boolean m804deprecated_noCache() {
        return this.f14209a;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "noStore", imports = {}))
    @JvmName(name = "-deprecated_noStore")
    /* renamed from: -deprecated_noStore  reason: not valid java name */
    public final boolean m805deprecated_noStore() {
        return this.b;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "noTransform", imports = {}))
    @JvmName(name = "-deprecated_noTransform")
    /* renamed from: -deprecated_noTransform  reason: not valid java name */
    public final boolean m806deprecated_noTransform() {
        return this.k;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "onlyIfCached", imports = {}))
    @JvmName(name = "-deprecated_onlyIfCached")
    /* renamed from: -deprecated_onlyIfCached  reason: not valid java name */
    public final boolean m807deprecated_onlyIfCached() {
        return this.j;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "sMaxAgeSeconds", imports = {}))
    @JvmName(name = "-deprecated_sMaxAgeSeconds")
    /* renamed from: -deprecated_sMaxAgeSeconds  reason: not valid java name */
    public final int m808deprecated_sMaxAgeSeconds() {
        return this.d;
    }

    @JvmName(name = "immutable")
    public final boolean immutable() {
        return this.l;
    }

    public final boolean isPrivate() {
        return this.e;
    }

    public final boolean isPublic() {
        return this.f;
    }

    @JvmName(name = "maxAgeSeconds")
    public final int maxAgeSeconds() {
        return this.c;
    }

    @JvmName(name = "maxStaleSeconds")
    public final int maxStaleSeconds() {
        return this.h;
    }

    @JvmName(name = "minFreshSeconds")
    public final int minFreshSeconds() {
        return this.i;
    }

    @JvmName(name = "mustRevalidate")
    public final boolean mustRevalidate() {
        return this.g;
    }

    @JvmName(name = "noCache")
    public final boolean noCache() {
        return this.f14209a;
    }

    @JvmName(name = "noStore")
    public final boolean noStore() {
        return this.b;
    }

    @JvmName(name = "noTransform")
    public final boolean noTransform() {
        return this.k;
    }

    @JvmName(name = "onlyIfCached")
    public final boolean onlyIfCached() {
        return this.j;
    }

    @JvmName(name = "sMaxAgeSeconds")
    public final int sMaxAgeSeconds() {
        return this.d;
    }

    @NotNull
    public String toString() {
        String str = this.m;
        if (str == null) {
            StringBuilder sb = new StringBuilder();
            if (noCache()) {
                sb.append("no-cache, ");
            }
            if (noStore()) {
                sb.append("no-store, ");
            }
            if (maxAgeSeconds() != -1) {
                sb.append("max-age=");
                sb.append(maxAgeSeconds());
                sb.append(", ");
            }
            if (sMaxAgeSeconds() != -1) {
                sb.append("s-maxage=");
                sb.append(sMaxAgeSeconds());
                sb.append(", ");
            }
            if (isPrivate()) {
                sb.append("private, ");
            }
            if (isPublic()) {
                sb.append("public, ");
            }
            if (mustRevalidate()) {
                sb.append("must-revalidate, ");
            }
            if (maxStaleSeconds() != -1) {
                sb.append("max-stale=");
                sb.append(maxStaleSeconds());
                sb.append(", ");
            }
            if (minFreshSeconds() != -1) {
                sb.append("min-fresh=");
                sb.append(minFreshSeconds());
                sb.append(", ");
            }
            if (onlyIfCached()) {
                sb.append("only-if-cached, ");
            }
            if (noTransform()) {
                sb.append("no-transform, ");
            }
            if (immutable()) {
                sb.append("immutable, ");
            }
            if (sb.length() == 0) {
                return "";
            }
            sb.delete(sb.length() - 2, sb.length());
            String sb2 = sb.toString();
            Intrinsics.checkNotNullExpressionValue(sb2, "StringBuilder().apply(builderAction).toString()");
            this.m = sb2;
            return sb2;
        }
        return str;
    }
}
