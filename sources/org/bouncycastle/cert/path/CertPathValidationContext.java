package org.bouncycastle.cert.path;

import java.util.HashSet;
import java.util.Set;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.util.Memoable;
/* loaded from: classes12.dex */
public class CertPathValidationContext implements Memoable {

    /* renamed from: a  reason: collision with root package name */
    public Set f14506a;
    public Set b = new HashSet();
    public boolean c;

    public CertPathValidationContext(Set set) {
        this.f14506a = set;
    }

    public void addHandledExtension(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        this.b.add(aSN1ObjectIdentifier);
    }

    @Override // org.bouncycastle.util.Memoable
    public Memoable copy() {
        return null;
    }

    public Set getUnhandledCriticalExtensionOIDs() {
        HashSet hashSet = new HashSet(this.f14506a);
        hashSet.removeAll(this.b);
        return hashSet;
    }

    public boolean isEndEntity() {
        return this.c;
    }

    @Override // org.bouncycastle.util.Memoable
    public void reset(Memoable memoable) {
    }

    public void setIsEndEntity(boolean z) {
        this.c = z;
    }
}
