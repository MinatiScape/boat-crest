package com.google.android.gms.common;

import java.util.concurrent.Callable;
/* loaded from: classes6.dex */
public final class n extends o {
    public final Callable f;

    public /* synthetic */ n(Callable callable, zzu zzuVar) {
        super();
        this.f = callable;
    }

    @Override // com.google.android.gms.common.o
    public final String a() {
        try {
            return (String) this.f.call();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
