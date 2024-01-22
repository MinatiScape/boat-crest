package org.bouncycastle.jcajce;

import java.security.cert.CertPathParameters;
import java.security.cert.CertSelector;
import java.security.cert.CertStore;
import java.security.cert.PKIXParameters;
import java.security.cert.TrustAnchor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.jcajce.PKIXCertStoreSelector;
/* loaded from: classes13.dex */
public class PKIXExtendedParameters implements CertPathParameters {
    public static final int CHAIN_VALIDITY_MODEL = 1;
    public static final int PKIX_VALIDITY_MODEL = 0;
    public final PKIXParameters h;
    public final PKIXCertStoreSelector i;
    public final Date j;
    public final List<PKIXCertStore> k;
    public final Map<GeneralName, PKIXCertStore> l;
    public final List<PKIXCRLStore> m;
    public final Map<GeneralName, PKIXCRLStore> n;
    public final boolean o;
    public final boolean p;
    public final int q;
    public final Set<TrustAnchor> r;

    /* loaded from: classes13.dex */
    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        public final PKIXParameters f14929a;
        public final Date b;
        public PKIXCertStoreSelector c;
        public List<PKIXCertStore> d;
        public Map<GeneralName, PKIXCertStore> e;
        public List<PKIXCRLStore> f;
        public Map<GeneralName, PKIXCRLStore> g;
        public boolean h;
        public int i;
        public boolean j;
        public Set<TrustAnchor> k;

        public Builder(PKIXParameters pKIXParameters) {
            this.d = new ArrayList();
            this.e = new HashMap();
            this.f = new ArrayList();
            this.g = new HashMap();
            this.i = 0;
            this.j = false;
            this.f14929a = (PKIXParameters) pKIXParameters.clone();
            CertSelector targetCertConstraints = pKIXParameters.getTargetCertConstraints();
            if (targetCertConstraints != null) {
                this.c = new PKIXCertStoreSelector.Builder(targetCertConstraints).build();
            }
            Date date = pKIXParameters.getDate();
            this.b = date == null ? new Date() : date;
            this.h = pKIXParameters.isRevocationEnabled();
            this.k = pKIXParameters.getTrustAnchors();
        }

        public Builder(PKIXExtendedParameters pKIXExtendedParameters) {
            this.d = new ArrayList();
            this.e = new HashMap();
            this.f = new ArrayList();
            this.g = new HashMap();
            this.i = 0;
            this.j = false;
            this.f14929a = pKIXExtendedParameters.h;
            this.b = pKIXExtendedParameters.j;
            this.c = pKIXExtendedParameters.i;
            this.d = new ArrayList(pKIXExtendedParameters.k);
            this.e = new HashMap(pKIXExtendedParameters.l);
            this.f = new ArrayList(pKIXExtendedParameters.m);
            this.g = new HashMap(pKIXExtendedParameters.n);
            this.j = pKIXExtendedParameters.p;
            this.i = pKIXExtendedParameters.q;
            this.h = pKIXExtendedParameters.isRevocationEnabled();
            this.k = pKIXExtendedParameters.getTrustAnchors();
        }

        public Builder addCRLStore(PKIXCRLStore pKIXCRLStore) {
            this.f.add(pKIXCRLStore);
            return this;
        }

        public Builder addCertificateStore(PKIXCertStore pKIXCertStore) {
            this.d.add(pKIXCertStore);
            return this;
        }

        public Builder addNamedCRLStore(GeneralName generalName, PKIXCRLStore pKIXCRLStore) {
            this.g.put(generalName, pKIXCRLStore);
            return this;
        }

        public Builder addNamedCertificateStore(GeneralName generalName, PKIXCertStore pKIXCertStore) {
            this.e.put(generalName, pKIXCertStore);
            return this;
        }

        public PKIXExtendedParameters build() {
            return new PKIXExtendedParameters(this);
        }

        public void setRevocationEnabled(boolean z) {
            this.h = z;
        }

        public Builder setTargetConstraints(PKIXCertStoreSelector pKIXCertStoreSelector) {
            this.c = pKIXCertStoreSelector;
            return this;
        }

        public Builder setTrustAnchor(TrustAnchor trustAnchor) {
            this.k = Collections.singleton(trustAnchor);
            return this;
        }

        public Builder setTrustAnchors(Set<TrustAnchor> set) {
            this.k = set;
            return this;
        }

        public Builder setUseDeltasEnabled(boolean z) {
            this.j = z;
            return this;
        }

        public Builder setValidityModel(int i) {
            this.i = i;
            return this;
        }
    }

    public PKIXExtendedParameters(Builder builder) {
        this.h = builder.f14929a;
        this.j = builder.b;
        this.k = Collections.unmodifiableList(builder.d);
        this.l = Collections.unmodifiableMap(new HashMap(builder.e));
        this.m = Collections.unmodifiableList(builder.f);
        this.n = Collections.unmodifiableMap(new HashMap(builder.g));
        this.i = builder.c;
        this.o = builder.h;
        this.p = builder.j;
        this.q = builder.i;
        this.r = Collections.unmodifiableSet(builder.k);
    }

    @Override // java.security.cert.CertPathParameters
    public Object clone() {
        return this;
    }

    public List<PKIXCRLStore> getCRLStores() {
        return this.m;
    }

    public List getCertPathCheckers() {
        return this.h.getCertPathCheckers();
    }

    public List<CertStore> getCertStores() {
        return this.h.getCertStores();
    }

    public List<PKIXCertStore> getCertificateStores() {
        return this.k;
    }

    public Date getDate() {
        return new Date(this.j.getTime());
    }

    public Set getInitialPolicies() {
        return this.h.getInitialPolicies();
    }

    public Map<GeneralName, PKIXCRLStore> getNamedCRLStoreMap() {
        return this.n;
    }

    public Map<GeneralName, PKIXCertStore> getNamedCertificateStoreMap() {
        return this.l;
    }

    public String getSigProvider() {
        return this.h.getSigProvider();
    }

    public PKIXCertStoreSelector getTargetConstraints() {
        return this.i;
    }

    public Set getTrustAnchors() {
        return this.r;
    }

    public int getValidityModel() {
        return this.q;
    }

    public boolean isAnyPolicyInhibited() {
        return this.h.isAnyPolicyInhibited();
    }

    public boolean isExplicitPolicyRequired() {
        return this.h.isExplicitPolicyRequired();
    }

    public boolean isPolicyMappingInhibited() {
        return this.h.isPolicyMappingInhibited();
    }

    public boolean isRevocationEnabled() {
        return this.o;
    }

    public boolean isUseDeltasEnabled() {
        return this.p;
    }
}
