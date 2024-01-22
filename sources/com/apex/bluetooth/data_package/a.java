package com.apex.bluetooth.data_package;

import com.apex.bluetooth.data_package.b.b;
/* loaded from: classes.dex */
public class a extends b {
    public com.apex.bluetooth.data_package.b.a<byte[]> c;
    public boolean d;

    public a(int i) {
        this.c = new com.apex.bluetooth.data_package.b.a<>(i);
    }

    public void a(byte[] bArr) {
        if (bArr != null) {
            try {
                this.c.add(bArr);
            } catch (IllegalStateException unused) {
            }
        }
    }

    public boolean c() {
        return this.d;
    }

    @Override // com.apex.bluetooth.data_package.b.b
    public com.apex.bluetooth.data_package.b.a<byte[]> a() {
        return this.c;
    }
}
