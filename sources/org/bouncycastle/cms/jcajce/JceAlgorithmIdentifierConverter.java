package org.bouncycastle.cms.jcajce;

import java.security.AlgorithmParameters;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cms.CMSException;
/* loaded from: classes12.dex */
public class JceAlgorithmIdentifierConverter {

    /* renamed from: a  reason: collision with root package name */
    public EnvelopedDataHelper f14580a = new EnvelopedDataHelper(new b());

    public AlgorithmParameters getAlgorithmParameters(AlgorithmIdentifier algorithmIdentifier) throws CMSException {
        if (algorithmIdentifier.getParameters() == null) {
            return null;
        }
        try {
            AlgorithmParameters c = this.f14580a.c(algorithmIdentifier.getAlgorithm());
            a.j(c, algorithmIdentifier.getParameters());
            return c;
        } catch (NoSuchAlgorithmException e) {
            throw new CMSException("can't find parameters for algorithm", e);
        } catch (NoSuchProviderException e2) {
            throw new CMSException("can't find provider for algorithm", e2);
        }
    }

    public JceAlgorithmIdentifierConverter setProvider(String str) {
        this.f14580a = new EnvelopedDataHelper(new e(str));
        return this;
    }

    public JceAlgorithmIdentifierConverter setProvider(Provider provider) {
        this.f14580a = new EnvelopedDataHelper(new f(provider));
        return this;
    }
}
