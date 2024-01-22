package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.concurrent.Executor;
import javax.annotation.CheckForNull;
/* loaded from: classes9.dex */
public final class k1 {
    public static final k1 d = new k1();
    @CheckForNull

    /* renamed from: a  reason: collision with root package name */
    public final Runnable f9432a;
    @CheckForNull
    public final Executor b;
    @CheckForNull
    public k1 c;

    public k1() {
        this.f9432a = null;
        this.b = null;
    }

    public k1(Runnable runnable, Executor executor) {
        this.f9432a = runnable;
        this.b = executor;
    }
}
