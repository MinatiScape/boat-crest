package com.google.common.escape;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.HashMap;
import java.util.Map;
import kotlin.jvm.internal.CharCompanionObject;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@Beta
@GwtCompatible
/* loaded from: classes10.dex */
public final class Escapers {

    /* renamed from: a  reason: collision with root package name */
    public static final Escaper f10603a = new a();

    @Beta
    /* loaded from: classes10.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public final Map<Character, String> f10604a;
        public char b;
        public char c;
        public String d;

        /* loaded from: classes10.dex */
        public class a extends ArrayBasedCharEscaper {
            public final char[] f;

            public a(Map map, char c, char c2) {
                super(map, c, c2);
                this.f = Builder.this.d != null ? Builder.this.d.toCharArray() : null;
            }

            @Override // com.google.common.escape.ArrayBasedCharEscaper
            public char[] escapeUnsafe(char c) {
                return this.f;
            }
        }

        public /* synthetic */ Builder(a aVar) {
            this();
        }

        @CanIgnoreReturnValue
        public Builder addEscape(char c, String str) {
            Preconditions.checkNotNull(str);
            this.f10604a.put(Character.valueOf(c), str);
            return this;
        }

        public Escaper build() {
            return new a(this.f10604a, this.b, this.c);
        }

        @CanIgnoreReturnValue
        public Builder setSafeRange(char c, char c2) {
            this.b = c;
            this.c = c2;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setUnsafeReplacement(@NullableDecl String str) {
            this.d = str;
            return this;
        }

        public Builder() {
            this.f10604a = new HashMap();
            this.b = (char) 0;
            this.c = CharCompanionObject.MAX_VALUE;
            this.d = null;
        }
    }

    /* loaded from: classes10.dex */
    public class a extends CharEscaper {
        @Override // com.google.common.escape.CharEscaper, com.google.common.escape.Escaper
        public String escape(String str) {
            return (String) Preconditions.checkNotNull(str);
        }

        @Override // com.google.common.escape.CharEscaper
        public char[] escape(char c) {
            return null;
        }
    }

    public static String a(char[] cArr) {
        if (cArr == null) {
            return null;
        }
        return new String(cArr);
    }

    public static Builder builder() {
        return new Builder(null);
    }

    public static String computeReplacement(CharEscaper charEscaper, char c) {
        return a(charEscaper.escape(c));
    }

    public static Escaper nullEscaper() {
        return f10603a;
    }

    public static String computeReplacement(UnicodeEscaper unicodeEscaper, int i) {
        return a(unicodeEscaper.escape(i));
    }
}
