package org.bouncycastle.eac.jcajce;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
/* loaded from: classes13.dex */
public class c implements b {

    /* renamed from: a  reason: collision with root package name */
    public final String f14897a;

    public c(String str) {
        this.f14897a = str;
    }

    @Override // org.bouncycastle.eac.jcajce.b
    public KeyFactory createKeyFactory(String str) throws NoSuchProviderException, NoSuchAlgorithmException {
        return KeyFactory.getInstance(str, this.f14897a);
    }
}
