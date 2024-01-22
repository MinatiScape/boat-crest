package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.Objects;
/* loaded from: classes9.dex */
public final class j1 {

    /* renamed from: a  reason: collision with root package name */
    public final Throwable f9424a;

    /* loaded from: classes9.dex */
    public class a extends Throwable {
        public a(String str) {
            super("Failure occurred while trying to finish a future.");
        }

        @Override // java.lang.Throwable
        public final synchronized Throwable fillInStackTrace() {
            return this;
        }
    }

    static {
        new j1(new a("Failure occurred while trying to finish a future."));
    }

    public j1(Throwable th) {
        Objects.requireNonNull(th);
        this.f9424a = th;
    }
}
