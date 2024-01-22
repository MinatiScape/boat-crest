package org.bouncycastle.asn1.x9;
/* loaded from: classes12.dex */
public abstract class X9ECParametersHolder {

    /* renamed from: a  reason: collision with root package name */
    public X9ECParameters f14440a;

    public abstract X9ECParameters createParameters();

    public synchronized X9ECParameters getParameters() {
        if (this.f14440a == null) {
            this.f14440a = createParameters();
        }
        return this.f14440a;
    }
}
