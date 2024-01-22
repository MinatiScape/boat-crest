package org.bouncycastle.jcajce;

import java.io.OutputStream;
import java.security.KeyStore;
import org.bouncycastle.crypto.util.PBKDFConfig;
/* loaded from: classes13.dex */
public class BCFKSStoreParameter implements KeyStore.LoadStoreParameter {

    /* renamed from: a  reason: collision with root package name */
    public final KeyStore.ProtectionParameter f14924a;
    public final PBKDFConfig b;
    public OutputStream c;

    public BCFKSStoreParameter(OutputStream outputStream, PBKDFConfig pBKDFConfig, KeyStore.ProtectionParameter protectionParameter) {
        this.c = outputStream;
        this.b = pBKDFConfig;
        this.f14924a = protectionParameter;
    }

    public BCFKSStoreParameter(OutputStream outputStream, PBKDFConfig pBKDFConfig, char[] cArr) {
        this(outputStream, pBKDFConfig, new KeyStore.PasswordProtection(cArr));
    }

    public OutputStream getOutputStream() {
        return this.c;
    }

    @Override // java.security.KeyStore.LoadStoreParameter
    public KeyStore.ProtectionParameter getProtectionParameter() {
        return this.f14924a;
    }

    public PBKDFConfig getStorePBKDFConfig() {
        return this.b;
    }
}
