package org.bouncycastle.jcajce;

import java.security.InvalidParameterException;
import java.security.cert.CertPathParameters;
import java.security.cert.PKIXBuilderParameters;
import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.bouncycastle.jcajce.PKIXExtendedParameters;
/* loaded from: classes13.dex */
public class PKIXExtendedBuilderParameters implements CertPathParameters {
    public final PKIXExtendedParameters h;
    public final Set<X509Certificate> i;
    public final int j;

    /* loaded from: classes13.dex */
    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        public final PKIXExtendedParameters f14928a;
        public int b;
        public Set<X509Certificate> c;

        public Builder(PKIXBuilderParameters pKIXBuilderParameters) {
            this.b = 5;
            this.c = new HashSet();
            this.f14928a = new PKIXExtendedParameters.Builder(pKIXBuilderParameters).build();
            this.b = pKIXBuilderParameters.getMaxPathLength();
        }

        public Builder(PKIXExtendedParameters pKIXExtendedParameters) {
            this.b = 5;
            this.c = new HashSet();
            this.f14928a = pKIXExtendedParameters;
        }

        public Builder addExcludedCerts(Set<X509Certificate> set) {
            this.c.addAll(set);
            return this;
        }

        public PKIXExtendedBuilderParameters build() {
            return new PKIXExtendedBuilderParameters(this);
        }

        public Builder setMaxPathLength(int i) {
            if (i >= -1) {
                this.b = i;
                return this;
            }
            throw new InvalidParameterException("The maximum path length parameter can not be less than -1.");
        }
    }

    public PKIXExtendedBuilderParameters(Builder builder) {
        this.h = builder.f14928a;
        this.i = Collections.unmodifiableSet(builder.c);
        this.j = builder.b;
    }

    @Override // java.security.cert.CertPathParameters
    public Object clone() {
        return this;
    }

    public PKIXExtendedParameters getBaseParameters() {
        return this.h;
    }

    public Set getExcludedCerts() {
        return this.i;
    }

    public int getMaxPathLength() {
        return this.j;
    }
}
