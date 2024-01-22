package org.bouncycastle.est.jcajce;

import java.security.Provider;
import java.security.SecureRandom;
import org.bouncycastle.est.HttpAuth;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaDigestCalculatorProviderBuilder;
/* loaded from: classes13.dex */
public class JcaHttpAuthBuilder {

    /* renamed from: a  reason: collision with root package name */
    public JcaDigestCalculatorProviderBuilder f14915a;
    public final String b;
    public final String c;
    public final char[] d;
    public SecureRandom e;

    public JcaHttpAuthBuilder(String str, String str2, char[] cArr) {
        this.f14915a = new JcaDigestCalculatorProviderBuilder();
        this.e = new SecureRandom();
        this.b = str;
        this.c = str2;
        this.d = cArr;
    }

    public JcaHttpAuthBuilder(String str, char[] cArr) {
        this(null, str, cArr);
    }

    public HttpAuth build() throws OperatorCreationException {
        return new HttpAuth(this.b, this.c, this.d, this.e, this.f14915a.build());
    }

    public JcaHttpAuthBuilder setNonceGenerator(SecureRandom secureRandom) {
        this.e = secureRandom;
        return this;
    }

    public JcaHttpAuthBuilder setProvider(String str) {
        this.f14915a.setProvider(str);
        return this;
    }

    public JcaHttpAuthBuilder setProvider(Provider provider) {
        this.f14915a.setProvider(provider);
        return this;
    }
}
