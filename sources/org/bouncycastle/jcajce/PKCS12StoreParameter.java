package org.bouncycastle.jcajce;

import java.io.OutputStream;
import java.security.KeyStore;
/* loaded from: classes13.dex */
public class PKCS12StoreParameter implements KeyStore.LoadStoreParameter {

    /* renamed from: a  reason: collision with root package name */
    public final OutputStream f14925a;
    public final KeyStore.ProtectionParameter b;
    public final boolean c;

    public PKCS12StoreParameter(OutputStream outputStream, KeyStore.ProtectionParameter protectionParameter) {
        this(outputStream, protectionParameter, false);
    }

    public PKCS12StoreParameter(OutputStream outputStream, KeyStore.ProtectionParameter protectionParameter, boolean z) {
        this.f14925a = outputStream;
        this.b = protectionParameter;
        this.c = z;
    }

    public PKCS12StoreParameter(OutputStream outputStream, char[] cArr) {
        this(outputStream, cArr, false);
    }

    public PKCS12StoreParameter(OutputStream outputStream, char[] cArr, boolean z) {
        this(outputStream, new KeyStore.PasswordProtection(cArr), z);
    }

    public OutputStream getOutputStream() {
        return this.f14925a;
    }

    @Override // java.security.KeyStore.LoadStoreParameter
    public KeyStore.ProtectionParameter getProtectionParameter() {
        return this.b;
    }

    public boolean isForDEREncoding() {
        return this.c;
    }
}
