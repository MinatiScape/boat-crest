package org.bouncycastle.x509;

import java.security.InvalidAlgorithmParameterException;
import java.security.cert.CertSelector;
import java.security.cert.CertStore;
import java.security.cert.PKIXParameters;
import java.security.cert.TrustAnchor;
import java.security.cert.X509CertSelector;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.bouncycastle.util.Selector;
import org.bouncycastle.util.Store;
/* loaded from: classes13.dex */
public class ExtendedPKIXParameters extends PKIXParameters {
    public static final int CHAIN_VALIDITY_MODEL = 1;
    public static final int PKIX_VALIDITY_MODEL = 0;
    public List h;
    public Selector i;
    public boolean j;
    public List k;
    public Set l;
    public Set m;
    public Set n;
    public Set o;
    public int p;
    public boolean q;

    public ExtendedPKIXParameters(Set set) throws InvalidAlgorithmParameterException {
        super(set);
        this.p = 0;
        this.q = false;
        this.h = new ArrayList();
        this.k = new ArrayList();
        this.l = new HashSet();
        this.m = new HashSet();
        this.n = new HashSet();
        this.o = new HashSet();
    }

    public static ExtendedPKIXParameters getInstance(PKIXParameters pKIXParameters) {
        try {
            ExtendedPKIXParameters extendedPKIXParameters = new ExtendedPKIXParameters(pKIXParameters.getTrustAnchors());
            extendedPKIXParameters.setParams(pKIXParameters);
            return extendedPKIXParameters;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void addAddionalStore(Store store) {
        addAdditionalStore(store);
    }

    public void addAdditionalStore(Store store) {
        if (store != null) {
            this.k.add(store);
        }
    }

    public void addStore(Store store) {
        if (store != null) {
            this.h.add(store);
        }
    }

    @Override // java.security.cert.PKIXParameters, java.security.cert.CertPathParameters
    public Object clone() {
        try {
            ExtendedPKIXParameters extendedPKIXParameters = new ExtendedPKIXParameters(getTrustAnchors());
            extendedPKIXParameters.setParams(this);
            return extendedPKIXParameters;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public List getAdditionalStores() {
        return Collections.unmodifiableList(this.k);
    }

    public Set getAttrCertCheckers() {
        return Collections.unmodifiableSet(this.o);
    }

    public Set getNecessaryACAttributes() {
        return Collections.unmodifiableSet(this.m);
    }

    public Set getProhibitedACAttributes() {
        return Collections.unmodifiableSet(this.n);
    }

    public List getStores() {
        return Collections.unmodifiableList(new ArrayList(this.h));
    }

    public Selector getTargetConstraints() {
        Selector selector = this.i;
        if (selector != null) {
            return (Selector) selector.clone();
        }
        return null;
    }

    public Set getTrustedACIssuers() {
        return Collections.unmodifiableSet(this.l);
    }

    public int getValidityModel() {
        return this.p;
    }

    public boolean isAdditionalLocationsEnabled() {
        return this.j;
    }

    public boolean isUseDeltasEnabled() {
        return this.q;
    }

    public void setAdditionalLocationsEnabled(boolean z) {
        this.j = z;
    }

    public void setAttrCertCheckers(Set set) {
        if (set == null) {
            this.o.clear();
            return;
        }
        for (Object obj : set) {
            if (!(obj instanceof PKIXAttrCertChecker)) {
                throw new ClassCastException("All elements of set must be of type " + PKIXAttrCertChecker.class.getName() + ".");
            }
        }
        this.o.clear();
        this.o.addAll(set);
    }

    @Override // java.security.cert.PKIXParameters
    public void setCertStores(List list) {
        if (list != null) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                addCertStore((CertStore) it.next());
            }
        }
    }

    public void setNecessaryACAttributes(Set set) {
        if (set == null) {
            this.m.clear();
            return;
        }
        for (Object obj : set) {
            if (!(obj instanceof String)) {
                throw new ClassCastException("All elements of set must be of type String.");
            }
        }
        this.m.clear();
        this.m.addAll(set);
    }

    public void setParams(PKIXParameters pKIXParameters) {
        setDate(pKIXParameters.getDate());
        setCertPathCheckers(pKIXParameters.getCertPathCheckers());
        setCertStores(pKIXParameters.getCertStores());
        setAnyPolicyInhibited(pKIXParameters.isAnyPolicyInhibited());
        setExplicitPolicyRequired(pKIXParameters.isExplicitPolicyRequired());
        setPolicyMappingInhibited(pKIXParameters.isPolicyMappingInhibited());
        setRevocationEnabled(pKIXParameters.isRevocationEnabled());
        setInitialPolicies(pKIXParameters.getInitialPolicies());
        setPolicyQualifiersRejected(pKIXParameters.getPolicyQualifiersRejected());
        setSigProvider(pKIXParameters.getSigProvider());
        setTargetCertConstraints(pKIXParameters.getTargetCertConstraints());
        try {
            setTrustAnchors(pKIXParameters.getTrustAnchors());
            if (pKIXParameters instanceof ExtendedPKIXParameters) {
                ExtendedPKIXParameters extendedPKIXParameters = (ExtendedPKIXParameters) pKIXParameters;
                this.p = extendedPKIXParameters.p;
                this.q = extendedPKIXParameters.q;
                this.j = extendedPKIXParameters.j;
                Selector selector = extendedPKIXParameters.i;
                this.i = selector == null ? null : (Selector) selector.clone();
                this.h = new ArrayList(extendedPKIXParameters.h);
                this.k = new ArrayList(extendedPKIXParameters.k);
                this.l = new HashSet(extendedPKIXParameters.l);
                this.n = new HashSet(extendedPKIXParameters.n);
                this.m = new HashSet(extendedPKIXParameters.m);
                this.o = new HashSet(extendedPKIXParameters.o);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void setProhibitedACAttributes(Set set) {
        if (set == null) {
            this.n.clear();
            return;
        }
        for (Object obj : set) {
            if (!(obj instanceof String)) {
                throw new ClassCastException("All elements of set must be of type String.");
            }
        }
        this.n.clear();
        this.n.addAll(set);
    }

    public void setStores(List list) {
        if (list == null) {
            this.h = new ArrayList();
            return;
        }
        for (Object obj : list) {
            if (!(obj instanceof Store)) {
                throw new ClassCastException("All elements of list must be of type org.bouncycastle.util.Store.");
            }
        }
        this.h = new ArrayList(list);
    }

    @Override // java.security.cert.PKIXParameters
    public void setTargetCertConstraints(CertSelector certSelector) {
        super.setTargetCertConstraints(certSelector);
        this.i = certSelector != null ? X509CertStoreSelector.getInstance((X509CertSelector) certSelector) : null;
    }

    public void setTargetConstraints(Selector selector) {
        this.i = selector != null ? (Selector) selector.clone() : null;
    }

    public void setTrustedACIssuers(Set set) {
        if (set == null) {
            this.l.clear();
            return;
        }
        for (Object obj : set) {
            if (!(obj instanceof TrustAnchor)) {
                throw new ClassCastException("All elements of set must be of type " + TrustAnchor.class.getName() + ".");
            }
        }
        this.l.clear();
        this.l.addAll(set);
    }

    public void setUseDeltasEnabled(boolean z) {
        this.q = z;
    }

    public void setValidityModel(int i) {
        this.p = i;
    }
}
