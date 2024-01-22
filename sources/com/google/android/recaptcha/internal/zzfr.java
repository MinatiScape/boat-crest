package com.google.android.recaptcha.internal;

import java.io.IOException;
/* loaded from: classes10.dex */
public final class zzfr extends IOException {
    public zzfr() {
        super("CodedOutputStream was writing to a flat byte array and ran out of space.");
    }

    public zzfr(String str, Throwable th) {
        super("CodedOutputStream was writing to a flat byte array and ran out of space.: ".concat(String.valueOf(str)), th);
    }

    public zzfr(Throwable th) {
        super("CodedOutputStream was writing to a flat byte array and ran out of space.", th);
    }
}
