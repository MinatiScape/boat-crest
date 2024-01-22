package com.google.common.base;

import com.google.common.annotations.GwtIncompatible;
import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@GwtIncompatible
/* loaded from: classes10.dex */
public final class i extends d implements Serializable {
    private static final long serialVersionUID = 0;
    private final Pattern pattern;

    /* loaded from: classes10.dex */
    public static final class a extends c {

        /* renamed from: a  reason: collision with root package name */
        public final Matcher f10534a;

        public a(Matcher matcher) {
            this.f10534a = (Matcher) Preconditions.checkNotNull(matcher);
        }

        @Override // com.google.common.base.c
        public int a() {
            return this.f10534a.end();
        }

        @Override // com.google.common.base.c
        public boolean b() {
            return this.f10534a.find();
        }

        @Override // com.google.common.base.c
        public boolean c(int i) {
            return this.f10534a.find(i);
        }

        @Override // com.google.common.base.c
        public boolean d() {
            return this.f10534a.matches();
        }

        @Override // com.google.common.base.c
        public int e() {
            return this.f10534a.start();
        }
    }

    public i(Pattern pattern) {
        this.pattern = (Pattern) Preconditions.checkNotNull(pattern);
    }

    @Override // com.google.common.base.d
    public int flags() {
        return this.pattern.flags();
    }

    @Override // com.google.common.base.d
    public c matcher(CharSequence charSequence) {
        return new a(this.pattern.matcher(charSequence));
    }

    @Override // com.google.common.base.d
    public String pattern() {
        return this.pattern.pattern();
    }

    @Override // com.google.common.base.d
    public String toString() {
        return this.pattern.toString();
    }
}
