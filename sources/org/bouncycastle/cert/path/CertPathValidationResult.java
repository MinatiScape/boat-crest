package org.bouncycastle.cert.path;

import java.util.Collections;
import java.util.Set;
import org.bouncycastle.util.Arrays;
/* loaded from: classes12.dex */
public class CertPathValidationResult {

    /* renamed from: a  reason: collision with root package name */
    public final boolean f14507a;
    public final CertPathValidationException b;
    public final Set c;
    public final int d;
    public final int e;
    public CertPathValidationException[] f;
    public int[] g;
    public int[] h;

    public CertPathValidationResult(CertPathValidationContext certPathValidationContext) {
        Set unmodifiableSet = Collections.unmodifiableSet(certPathValidationContext.getUnhandledCriticalExtensionOIDs());
        this.c = unmodifiableSet;
        this.f14507a = unmodifiableSet.isEmpty();
        this.d = -1;
        this.e = -1;
        this.b = null;
    }

    public CertPathValidationResult(CertPathValidationContext certPathValidationContext, int i, int i2, CertPathValidationException certPathValidationException) {
        this.c = Collections.unmodifiableSet(certPathValidationContext.getUnhandledCriticalExtensionOIDs());
        this.f14507a = false;
        this.d = i;
        this.e = i2;
        this.b = certPathValidationException;
    }

    public CertPathValidationResult(CertPathValidationContext certPathValidationContext, int[] iArr, int[] iArr2, CertPathValidationException[] certPathValidationExceptionArr) {
        this.c = Collections.unmodifiableSet(certPathValidationContext.getUnhandledCriticalExtensionOIDs());
        this.f14507a = false;
        this.b = certPathValidationExceptionArr[0];
        this.d = iArr[0];
        this.e = iArr2[0];
        this.f = certPathValidationExceptionArr;
        this.g = iArr;
        this.h = iArr2;
    }

    public CertPathValidationException getCause() {
        CertPathValidationException certPathValidationException = this.b;
        if (certPathValidationException != null) {
            return certPathValidationException;
        }
        if (this.c.isEmpty()) {
            return null;
        }
        return new CertPathValidationException("Unhandled Critical Extensions");
    }

    public CertPathValidationException[] getCauses() {
        CertPathValidationException[] certPathValidationExceptionArr = this.f;
        if (certPathValidationExceptionArr != null) {
            CertPathValidationException[] certPathValidationExceptionArr2 = new CertPathValidationException[certPathValidationExceptionArr.length];
            System.arraycopy(certPathValidationExceptionArr, 0, certPathValidationExceptionArr2, 0, certPathValidationExceptionArr.length);
            return certPathValidationExceptionArr2;
        } else if (this.c.isEmpty()) {
            return null;
        } else {
            return new CertPathValidationException[]{new CertPathValidationException("Unhandled Critical Extensions")};
        }
    }

    public int getFailingCertIndex() {
        return this.d;
    }

    public int[] getFailingCertIndexes() {
        return Arrays.clone(this.g);
    }

    public int getFailingRuleIndex() {
        return this.e;
    }

    public int[] getFailingRuleIndexes() {
        return Arrays.clone(this.h);
    }

    public Set getUnhandledCriticalExtensionOIDs() {
        return this.c;
    }

    public boolean isDetailed() {
        return this.g != null;
    }

    public boolean isValid() {
        return this.f14507a;
    }
}
