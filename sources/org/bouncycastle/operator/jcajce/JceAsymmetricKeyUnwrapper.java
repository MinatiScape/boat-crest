package org.bouncycastle.operator.jcajce;

import java.security.PrivateKey;
import java.security.Provider;
import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.jcajce.util.DefaultJcaJceHelper;
import org.bouncycastle.jcajce.util.NamedJcaJceHelper;
import org.bouncycastle.jcajce.util.ProviderJcaJceHelper;
import org.bouncycastle.operator.AsymmetricKeyUnwrapper;
/* loaded from: classes13.dex */
public class JceAsymmetricKeyUnwrapper extends AsymmetricKeyUnwrapper {
    public a b;
    public Map c;
    public PrivateKey d;
    public boolean e;

    public JceAsymmetricKeyUnwrapper(AlgorithmIdentifier algorithmIdentifier, PrivateKey privateKey) {
        super(algorithmIdentifier);
        this.b = new a(new DefaultJcaJceHelper());
        this.c = new HashMap();
        this.d = privateKey;
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0043, code lost:
        if (r2.length == 0) goto L15;
     */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0049  */
    @Override // org.bouncycastle.operator.KeyUnwrapper
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public org.bouncycastle.operator.GenericKey generateUnwrappedKey(org.bouncycastle.asn1.x509.AlgorithmIdentifier r6, byte[] r7) throws org.bouncycastle.operator.OperatorException {
        /*
            r5 = this;
            org.bouncycastle.operator.jcajce.a r0 = r5.b     // Catch: javax.crypto.BadPaddingException -> L66 javax.crypto.IllegalBlockSizeException -> L82 java.security.InvalidKeyException -> L9e
            org.bouncycastle.asn1.x509.AlgorithmIdentifier r1 = r5.getAlgorithmIdentifier()     // Catch: javax.crypto.BadPaddingException -> L66 javax.crypto.IllegalBlockSizeException -> L82 java.security.InvalidKeyException -> L9e
            org.bouncycastle.asn1.ASN1ObjectIdentifier r1 = r1.getAlgorithm()     // Catch: javax.crypto.BadPaddingException -> L66 javax.crypto.IllegalBlockSizeException -> L82 java.security.InvalidKeyException -> L9e
            java.util.Map r2 = r5.c     // Catch: javax.crypto.BadPaddingException -> L66 javax.crypto.IllegalBlockSizeException -> L82 java.security.InvalidKeyException -> L9e
            javax.crypto.Cipher r0 = r0.d(r1, r2)     // Catch: javax.crypto.BadPaddingException -> L66 javax.crypto.IllegalBlockSizeException -> L82 java.security.InvalidKeyException -> L9e
            org.bouncycastle.operator.jcajce.a r1 = r5.b     // Catch: javax.crypto.BadPaddingException -> L66 javax.crypto.IllegalBlockSizeException -> L82 java.security.InvalidKeyException -> L9e
            org.bouncycastle.asn1.x509.AlgorithmIdentifier r2 = r5.getAlgorithmIdentifier()     // Catch: javax.crypto.BadPaddingException -> L66 javax.crypto.IllegalBlockSizeException -> L82 java.security.InvalidKeyException -> L9e
            java.security.AlgorithmParameters r1 = r1.c(r2)     // Catch: javax.crypto.BadPaddingException -> L66 javax.crypto.IllegalBlockSizeException -> L82 java.security.InvalidKeyException -> L9e
            r2 = 4
            r3 = 0
            if (r1 == 0) goto L24
            java.security.PrivateKey r4 = r5.d     // Catch: java.lang.Throwable -> L47
            r0.init(r2, r4, r1)     // Catch: java.lang.Throwable -> L47
            goto L29
        L24:
            java.security.PrivateKey r1 = r5.d     // Catch: java.lang.Throwable -> L47
            r0.init(r2, r1)     // Catch: java.lang.Throwable -> L47
        L29:
            org.bouncycastle.operator.jcajce.a r1 = r5.b     // Catch: java.lang.Throwable -> L47
            org.bouncycastle.asn1.ASN1ObjectIdentifier r2 = r6.getAlgorithm()     // Catch: java.lang.Throwable -> L47
            java.lang.String r1 = r1.j(r2)     // Catch: java.lang.Throwable -> L47
            r2 = 3
            java.security.Key r1 = r0.unwrap(r7, r1, r2)     // Catch: java.lang.Throwable -> L47
            boolean r2 = r5.e     // Catch: java.lang.Throwable -> L46
            if (r2 == 0) goto L46
            byte[] r2 = r1.getEncoded()
            if (r2 == 0) goto L47
            int r2 = r2.length
            if (r2 != 0) goto L46
            goto L47
        L46:
            r3 = r1
        L47:
            if (r3 != 0) goto L60
            r1 = 2
            java.security.PrivateKey r2 = r5.d     // Catch: javax.crypto.BadPaddingException -> L66 javax.crypto.IllegalBlockSizeException -> L82 java.security.InvalidKeyException -> L9e
            r0.init(r1, r2)     // Catch: javax.crypto.BadPaddingException -> L66 javax.crypto.IllegalBlockSizeException -> L82 java.security.InvalidKeyException -> L9e
            javax.crypto.spec.SecretKeySpec r3 = new javax.crypto.spec.SecretKeySpec     // Catch: javax.crypto.BadPaddingException -> L66 javax.crypto.IllegalBlockSizeException -> L82 java.security.InvalidKeyException -> L9e
            byte[] r7 = r0.doFinal(r7)     // Catch: javax.crypto.BadPaddingException -> L66 javax.crypto.IllegalBlockSizeException -> L82 java.security.InvalidKeyException -> L9e
            org.bouncycastle.asn1.ASN1ObjectIdentifier r0 = r6.getAlgorithm()     // Catch: javax.crypto.BadPaddingException -> L66 javax.crypto.IllegalBlockSizeException -> L82 java.security.InvalidKeyException -> L9e
            java.lang.String r0 = r0.getId()     // Catch: javax.crypto.BadPaddingException -> L66 javax.crypto.IllegalBlockSizeException -> L82 java.security.InvalidKeyException -> L9e
            r3.<init>(r7, r0)     // Catch: javax.crypto.BadPaddingException -> L66 javax.crypto.IllegalBlockSizeException -> L82 java.security.InvalidKeyException -> L9e
        L60:
            org.bouncycastle.operator.jcajce.JceGenericKey r7 = new org.bouncycastle.operator.jcajce.JceGenericKey     // Catch: javax.crypto.BadPaddingException -> L66 javax.crypto.IllegalBlockSizeException -> L82 java.security.InvalidKeyException -> L9e
            r7.<init>(r6, r3)     // Catch: javax.crypto.BadPaddingException -> L66 javax.crypto.IllegalBlockSizeException -> L82 java.security.InvalidKeyException -> L9e
            return r7
        L66:
            r6 = move-exception
            org.bouncycastle.operator.OperatorException r7 = new org.bouncycastle.operator.OperatorException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "bad padding: "
            r0.append(r1)
            java.lang.String r1 = r6.getMessage()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r7.<init>(r0, r6)
            throw r7
        L82:
            r6 = move-exception
            org.bouncycastle.operator.OperatorException r7 = new org.bouncycastle.operator.OperatorException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "illegal blocksize: "
            r0.append(r1)
            java.lang.String r1 = r6.getMessage()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r7.<init>(r0, r6)
            throw r7
        L9e:
            r6 = move-exception
            org.bouncycastle.operator.OperatorException r7 = new org.bouncycastle.operator.OperatorException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "key invalid: "
            r0.append(r1)
            java.lang.String r1 = r6.getMessage()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r7.<init>(r0, r6)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.operator.jcajce.JceAsymmetricKeyUnwrapper.generateUnwrappedKey(org.bouncycastle.asn1.x509.AlgorithmIdentifier, byte[]):org.bouncycastle.operator.GenericKey");
    }

    public JceAsymmetricKeyUnwrapper setAlgorithmMapping(ASN1ObjectIdentifier aSN1ObjectIdentifier, String str) {
        this.c.put(aSN1ObjectIdentifier, str);
        return this;
    }

    public JceAsymmetricKeyUnwrapper setMustProduceEncodableUnwrappedKey(boolean z) {
        this.e = z;
        return this;
    }

    public JceAsymmetricKeyUnwrapper setProvider(String str) {
        this.b = new a(new NamedJcaJceHelper(str));
        return this;
    }

    public JceAsymmetricKeyUnwrapper setProvider(Provider provider) {
        this.b = new a(new ProviderJcaJceHelper(provider));
        return this;
    }
}
