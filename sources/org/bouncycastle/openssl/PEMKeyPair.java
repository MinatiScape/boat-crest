package org.bouncycastle.openssl;

import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
/* loaded from: classes13.dex */
public class PEMKeyPair {

    /* renamed from: a  reason: collision with root package name */
    public final SubjectPublicKeyInfo f15199a;
    public final PrivateKeyInfo b;

    public PEMKeyPair(SubjectPublicKeyInfo subjectPublicKeyInfo, PrivateKeyInfo privateKeyInfo) {
        this.f15199a = subjectPublicKeyInfo;
        this.b = privateKeyInfo;
    }

    public PrivateKeyInfo getPrivateKeyInfo() {
        return this.b;
    }

    public SubjectPublicKeyInfo getPublicKeyInfo() {
        return this.f15199a;
    }
}
