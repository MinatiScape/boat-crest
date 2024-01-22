package org.bouncycastle.cert.path;

import java.util.ArrayList;
import java.util.List;
import org.bouncycastle.util.Integers;
/* loaded from: classes12.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    public final CertPathValidationContext f14508a;
    public final List<Integer> b = new ArrayList();
    public final List<Integer> c = new ArrayList();
    public final List<CertPathValidationException> d = new ArrayList();

    public b(CertPathValidationContext certPathValidationContext) {
        this.f14508a = certPathValidationContext;
    }

    public void a(int i, int i2, CertPathValidationException certPathValidationException) {
        this.b.add(Integers.valueOf(i));
        this.c.add(Integers.valueOf(i2));
        this.d.add(certPathValidationException);
    }

    public CertPathValidationResult b() {
        if (this.d.isEmpty()) {
            return new CertPathValidationResult(this.f14508a);
        }
        CertPathValidationContext certPathValidationContext = this.f14508a;
        int[] c = c(this.b);
        int[] c2 = c(this.c);
        List<CertPathValidationException> list = this.d;
        return new CertPathValidationResult(certPathValidationContext, c, c2, (CertPathValidationException[]) list.toArray(new CertPathValidationException[list.size()]));
    }

    public final int[] c(List<Integer> list) {
        int size = list.size();
        int[] iArr = new int[size];
        for (int i = 0; i != size; i++) {
            iArr[i] = list.get(i).intValue();
        }
        return iArr;
    }
}
