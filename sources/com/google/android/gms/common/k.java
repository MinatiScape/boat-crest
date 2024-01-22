package com.google.android.gms.common;

import java.lang.ref.WeakReference;
/* loaded from: classes6.dex */
public abstract class k extends i {
    public static final WeakReference c = new WeakReference(null);
    public WeakReference b;

    public k(byte[] bArr) {
        super(bArr);
        this.b = c;
    }

    @Override // com.google.android.gms.common.i
    public final byte[] t() {
        byte[] bArr;
        synchronized (this) {
            bArr = (byte[]) this.b.get();
            if (bArr == null) {
                bArr = u();
                this.b = new WeakReference(bArr);
            }
        }
        return bArr;
    }

    public abstract byte[] u();
}
