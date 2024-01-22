package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import java.lang.ref.WeakReference;
import java.util.Locale;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible(emulated = true)
/* loaded from: classes10.dex */
public final class l {

    /* renamed from: a  reason: collision with root package name */
    public static final k f10535a;

    /* loaded from: classes10.dex */
    public static final class b implements k {
        public b() {
        }

        @Override // com.google.common.base.k
        public d a(String str) {
            return new i(Pattern.compile(str));
        }

        @Override // com.google.common.base.k
        public boolean b() {
            return true;
        }
    }

    static {
        Logger.getLogger(l.class.getName());
        f10535a = e();
    }

    public static d a(String str) {
        Preconditions.checkNotNull(str);
        return f10535a.a(str);
    }

    public static String b(@NullableDecl String str) {
        if (i(str)) {
            return null;
        }
        return str;
    }

    public static String c(double d) {
        return String.format(Locale.ROOT, "%.4g", Double.valueOf(d));
    }

    public static <T extends Enum<T>> Optional<T> d(Class<T> cls, String str) {
        WeakReference<? extends Enum<?>> weakReference = Enums.a(cls).get(str);
        return weakReference == null ? Optional.absent() : Optional.of(cls.cast(weakReference.get()));
    }

    public static k e() {
        return new b();
    }

    public static String f(@NullableDecl String str) {
        return str == null ? "" : str;
    }

    public static boolean g() {
        return f10535a.b();
    }

    public static CharMatcher h(CharMatcher charMatcher) {
        return charMatcher.e();
    }

    public static boolean i(@NullableDecl String str) {
        return str == null || str.isEmpty();
    }

    public static long j() {
        return System.nanoTime();
    }
}
