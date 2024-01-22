package com.google.common.net;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Ascii;
import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import com.google.errorprone.annotations.Immutable;
import com.google.thirdparty.publicsuffix.PublicSuffixPatterns;
import com.google.thirdparty.publicsuffix.PublicSuffixType;
import java.util.List;
import org.bouncycastle.pqc.math.linearalgebra.Matrix;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@Immutable
@Beta
@GwtCompatible(emulated = true)
/* loaded from: classes10.dex */
public final class InternetDomainName {
    public static final CharMatcher e = CharMatcher.anyOf(".。．｡");
    public static final Splitter f = Splitter.on('.');
    public static final Joiner g = Joiner.on('.');
    public static final CharMatcher h;
    public static final CharMatcher i;
    public static final CharMatcher j;
    public static final CharMatcher k;

    /* renamed from: a  reason: collision with root package name */
    public final String f10712a;
    public final ImmutableList<String> b;
    public final int c;
    public final int d;

    static {
        CharMatcher anyOf = CharMatcher.anyOf("-_");
        h = anyOf;
        CharMatcher inRange = CharMatcher.inRange('0', '9');
        i = inRange;
        CharMatcher or = CharMatcher.inRange('a', 'z').or(CharMatcher.inRange('A', Matrix.MATRIX_TYPE_ZERO));
        j = or;
        k = inRange.or(or).or(anyOf);
    }

    public InternetDomainName(String str) {
        String lowerCase = Ascii.toLowerCase(e.replaceFrom((CharSequence) str, '.'));
        lowerCase = lowerCase.endsWith(".") ? lowerCase.substring(0, lowerCase.length() - 1) : lowerCase;
        Preconditions.checkArgument(lowerCase.length() <= 253, "Domain name too long: '%s':", lowerCase);
        this.f10712a = lowerCase;
        ImmutableList<String> copyOf = ImmutableList.copyOf(f.split(lowerCase));
        this.b = copyOf;
        Preconditions.checkArgument(copyOf.size() <= 127, "Domain has too many parts: '%s'", lowerCase);
        Preconditions.checkArgument(f(copyOf), "Not a valid domain name: '%s'", lowerCase);
        this.c = b(Optional.absent());
        this.d = b(Optional.of(PublicSuffixType.REGISTRY));
    }

    public static boolean c(Optional<PublicSuffixType> optional, Optional<PublicSuffixType> optional2) {
        return optional.isPresent() ? optional.equals(optional2) : optional2.isPresent();
    }

    public static boolean d(Optional<PublicSuffixType> optional, String str) {
        List<String> splitToList = f.limit(2).splitToList(str);
        return splitToList.size() == 2 && c(optional, Optional.fromNullable(PublicSuffixPatterns.UNDER.get(splitToList.get(1))));
    }

    public static boolean e(String str, boolean z) {
        if (str.length() >= 1 && str.length() <= 63) {
            if (!k.matchesAllOf(CharMatcher.ascii().retainFrom(str))) {
                return false;
            }
            CharMatcher charMatcher = h;
            if (!charMatcher.matches(str.charAt(0)) && !charMatcher.matches(str.charAt(str.length() - 1))) {
                return (z && i.matches(str.charAt(0))) ? false : true;
            }
        }
        return false;
    }

    public static boolean f(List<String> list) {
        int size = list.size() - 1;
        if (e(list.get(size), true)) {
            for (int i2 = 0; i2 < size; i2++) {
                if (!e(list.get(i2), false)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public static InternetDomainName from(String str) {
        return new InternetDomainName((String) Preconditions.checkNotNull(str));
    }

    public static boolean isValid(String str) {
        try {
            from(str);
            return true;
        } catch (IllegalArgumentException unused) {
            return false;
        }
    }

    public final InternetDomainName a(int i2) {
        Joiner joiner = g;
        ImmutableList<String> immutableList = this.b;
        return from(joiner.join(immutableList.subList(i2, immutableList.size())));
    }

    public final int b(Optional<PublicSuffixType> optional) {
        int size = this.b.size();
        for (int i2 = 0; i2 < size; i2++) {
            String join = g.join(this.b.subList(i2, size));
            if (c(optional, Optional.fromNullable(PublicSuffixPatterns.EXACT.get(join)))) {
                return i2;
            }
            if (PublicSuffixPatterns.EXCLUDED.containsKey(join)) {
                return i2 + 1;
            }
            if (d(optional, join)) {
                return i2;
            }
        }
        return -1;
    }

    public InternetDomainName child(String str) {
        String str2 = (String) Preconditions.checkNotNull(str);
        String str3 = this.f10712a;
        StringBuilder sb = new StringBuilder(String.valueOf(str2).length() + 1 + String.valueOf(str3).length());
        sb.append(str2);
        sb.append(".");
        sb.append(str3);
        return from(sb.toString());
    }

    public boolean equals(@NullableDecl Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof InternetDomainName) {
            return this.f10712a.equals(((InternetDomainName) obj).f10712a);
        }
        return false;
    }

    public boolean hasParent() {
        return this.b.size() > 1;
    }

    public boolean hasPublicSuffix() {
        return this.c != -1;
    }

    public boolean hasRegistrySuffix() {
        return this.d != -1;
    }

    public int hashCode() {
        return this.f10712a.hashCode();
    }

    public boolean isPublicSuffix() {
        return this.c == 0;
    }

    public boolean isRegistrySuffix() {
        return this.d == 0;
    }

    public boolean isTopDomainUnderRegistrySuffix() {
        return this.d == 1;
    }

    public boolean isTopPrivateDomain() {
        return this.c == 1;
    }

    public boolean isUnderPublicSuffix() {
        return this.c > 0;
    }

    public boolean isUnderRegistrySuffix() {
        return this.d > 0;
    }

    public InternetDomainName parent() {
        Preconditions.checkState(hasParent(), "Domain '%s' has no parent", this.f10712a);
        return a(1);
    }

    public ImmutableList<String> parts() {
        return this.b;
    }

    public InternetDomainName publicSuffix() {
        if (hasPublicSuffix()) {
            return a(this.c);
        }
        return null;
    }

    public InternetDomainName registrySuffix() {
        if (hasRegistrySuffix()) {
            return a(this.d);
        }
        return null;
    }

    public String toString() {
        return this.f10712a;
    }

    public InternetDomainName topDomainUnderRegistrySuffix() {
        if (isTopDomainUnderRegistrySuffix()) {
            return this;
        }
        Preconditions.checkState(isUnderRegistrySuffix(), "Not under a registry suffix: %s", this.f10712a);
        return a(this.d - 1);
    }

    public InternetDomainName topPrivateDomain() {
        if (isTopPrivateDomain()) {
            return this;
        }
        Preconditions.checkState(isUnderPublicSuffix(), "Not under a public suffix: %s", this.f10712a);
        return a(this.c - 1);
    }
}
