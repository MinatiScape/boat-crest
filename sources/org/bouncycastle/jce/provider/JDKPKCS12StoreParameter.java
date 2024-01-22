package org.bouncycastle.jce.provider;

import java.io.OutputStream;
import java.security.KeyStore;
/* loaded from: classes13.dex */
public class JDKPKCS12StoreParameter implements KeyStore.LoadStoreParameter {

    /* renamed from: a  reason: collision with root package name */
    public OutputStream f15087a;
    public KeyStore.ProtectionParameter b;
    public boolean c;

    public OutputStream getOutputStream() {
        return this.f15087a;
    }

    @Override // java.security.KeyStore.LoadStoreParameter
    public KeyStore.ProtectionParameter getProtectionParameter() {
        return this.b;
    }

    public boolean isUseDEREncoding() {
        return this.c;
    }

    public void setOutputStream(OutputStream outputStream) {
        this.f15087a = outputStream;
    }

    public void setPassword(char[] cArr) {
        this.b = new KeyStore.PasswordProtection(cArr);
    }

    public void setProtectionParameter(KeyStore.ProtectionParameter protectionParameter) {
        this.b = protectionParameter;
    }

    public void setUseDEREncoding(boolean z) {
        this.c = z;
    }
}
