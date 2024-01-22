package org.bouncycastle.cert.path.validations;

import org.bouncycastle.cert.path.CertPath;
/* loaded from: classes12.dex */
public class CertificatePoliciesValidationBuilder {

    /* renamed from: a  reason: collision with root package name */
    public boolean f14512a;
    public boolean b;
    public boolean c;

    public CertificatePoliciesValidation build(int i) {
        return new CertificatePoliciesValidation(i, this.f14512a, this.b, this.c);
    }

    public CertificatePoliciesValidation build(CertPath certPath) {
        return build(certPath.length());
    }

    public void setAnyPolicyInhibited(boolean z) {
        this.b = z;
    }

    public void setExplicitPolicyRequired(boolean z) {
        this.f14512a = z;
    }

    public void setPolicyMappingInhibited(boolean z) {
        this.c = z;
    }
}
