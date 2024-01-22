package com.crrepa.t0;

import java.io.IOException;
/* loaded from: classes9.dex */
public final class e extends IOException {

    /* renamed from: a  reason: collision with root package name */
    private static final long f7841a = 1;

    public e(String str) {
        super(str);
    }

    public e(String str, Throwable th) {
        super(str);
        initCause(th);
    }

    public e(Throwable th) {
        initCause(th);
    }
}
