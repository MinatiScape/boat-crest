package org.bouncycastle.x509;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.util.Collection;
import org.bouncycastle.x509.e;
import org.bouncycastle.x509.util.StreamParser;
import org.bouncycastle.x509.util.StreamParsingException;
/* loaded from: classes13.dex */
public class X509StreamParser implements StreamParser {

    /* renamed from: a  reason: collision with root package name */
    public Provider f15415a;
    public X509StreamParserSpi b;

    public X509StreamParser(Provider provider, X509StreamParserSpi x509StreamParserSpi) {
        this.f15415a = provider;
        this.b = x509StreamParserSpi;
    }

    public static X509StreamParser a(e.a aVar) {
        return new X509StreamParser(aVar.b(), (X509StreamParserSpi) aVar.a());
    }

    public static X509StreamParser getInstance(String str) throws NoSuchParserException {
        try {
            return a(e.g("X509StreamParser", str));
        } catch (NoSuchAlgorithmException e) {
            throw new NoSuchParserException(e.getMessage());
        }
    }

    public static X509StreamParser getInstance(String str, String str2) throws NoSuchParserException, NoSuchProviderException {
        return getInstance(str, e.i(str2));
    }

    public static X509StreamParser getInstance(String str, Provider provider) throws NoSuchParserException {
        try {
            return a(e.h("X509StreamParser", str, provider));
        } catch (NoSuchAlgorithmException e) {
            throw new NoSuchParserException(e.getMessage());
        }
    }

    public Provider getProvider() {
        return this.f15415a;
    }

    public void init(InputStream inputStream) {
        this.b.engineInit(inputStream);
    }

    public void init(byte[] bArr) {
        this.b.engineInit(new ByteArrayInputStream(bArr));
    }

    @Override // org.bouncycastle.x509.util.StreamParser
    public Object read() throws StreamParsingException {
        return this.b.engineRead();
    }

    @Override // org.bouncycastle.x509.util.StreamParser
    public Collection readAll() throws StreamParsingException {
        return this.b.engineReadAll();
    }
}
