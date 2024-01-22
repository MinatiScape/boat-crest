package org.bouncycastle.x509;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.util.Collection;
import org.bouncycastle.util.Selector;
import org.bouncycastle.util.Store;
import org.bouncycastle.x509.e;
/* loaded from: classes13.dex */
public class X509Store implements Store {
    public Provider h;
    public X509StoreSpi i;

    public X509Store(Provider provider, X509StoreSpi x509StoreSpi) {
        this.h = provider;
        this.i = x509StoreSpi;
    }

    public static X509Store a(e.a aVar, X509StoreParameters x509StoreParameters) {
        X509StoreSpi x509StoreSpi = (X509StoreSpi) aVar.a();
        x509StoreSpi.engineInit(x509StoreParameters);
        return new X509Store(aVar.b(), x509StoreSpi);
    }

    public static X509Store getInstance(String str, X509StoreParameters x509StoreParameters) throws NoSuchStoreException {
        try {
            return a(e.g("X509Store", str), x509StoreParameters);
        } catch (NoSuchAlgorithmException e) {
            throw new NoSuchStoreException(e.getMessage());
        }
    }

    public static X509Store getInstance(String str, X509StoreParameters x509StoreParameters, String str2) throws NoSuchStoreException, NoSuchProviderException {
        return getInstance(str, x509StoreParameters, e.i(str2));
    }

    public static X509Store getInstance(String str, X509StoreParameters x509StoreParameters, Provider provider) throws NoSuchStoreException {
        try {
            return a(e.h("X509Store", str, provider), x509StoreParameters);
        } catch (NoSuchAlgorithmException e) {
            throw new NoSuchStoreException(e.getMessage());
        }
    }

    @Override // org.bouncycastle.util.Store
    public Collection getMatches(Selector selector) {
        return this.i.engineGetMatches(selector);
    }

    public Provider getProvider() {
        return this.h;
    }
}
