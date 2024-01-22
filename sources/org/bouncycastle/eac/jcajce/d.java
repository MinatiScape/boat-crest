package org.bouncycastle.eac.jcajce;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
/* loaded from: classes13.dex */
public class d implements b {

    /* renamed from: a  reason: collision with root package name */
    public final Provider f14898a;

    public d(Provider provider) {
        this.f14898a = provider;
    }

    @Override // org.bouncycastle.eac.jcajce.b
    public KeyFactory createKeyFactory(String str) throws NoSuchAlgorithmException {
        return KeyFactory.getInstance(str, this.f14898a);
    }
}
