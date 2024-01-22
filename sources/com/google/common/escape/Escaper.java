package com.google.common.escape;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Function;
import com.google.errorprone.annotations.DoNotMock;
@DoNotMock("Use Escapers.nullEscaper() or another methods from the *Escapers classes")
@GwtCompatible
/* loaded from: classes10.dex */
public abstract class Escaper {

    /* renamed from: a  reason: collision with root package name */
    public final Function<String, String> f10602a = new a();

    /* loaded from: classes10.dex */
    public class a implements Function<String, String> {
        public a() {
        }

        @Override // com.google.common.base.Function
        /* renamed from: a */
        public String apply(String str) {
            return Escaper.this.escape(str);
        }
    }

    public final Function<String, String> asFunction() {
        return this.f10602a;
    }

    public abstract String escape(String str);
}
