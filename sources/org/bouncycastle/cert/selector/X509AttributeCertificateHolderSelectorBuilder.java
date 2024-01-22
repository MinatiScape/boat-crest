package org.bouncycastle.cert.selector;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.cert.AttributeCertificateHolder;
import org.bouncycastle.cert.AttributeCertificateIssuer;
import org.bouncycastle.cert.X509AttributeCertificateHolder;
/* loaded from: classes12.dex */
public class X509AttributeCertificateHolderSelectorBuilder {

    /* renamed from: a  reason: collision with root package name */
    public AttributeCertificateHolder f14515a;
    public AttributeCertificateIssuer b;
    public BigInteger c;
    public Date d;
    public X509AttributeCertificateHolder e;
    public Collection f = new HashSet();
    public Collection g = new HashSet();

    public final Set a(Collection collection) throws IOException {
        if (collection == null || collection.isEmpty()) {
            return new HashSet();
        }
        HashSet hashSet = new HashSet();
        for (Object obj : collection) {
            hashSet.add(GeneralName.getInstance(obj));
        }
        return hashSet;
    }

    public void addTargetGroup(GeneralName generalName) {
        this.g.add(generalName);
    }

    public void addTargetName(GeneralName generalName) {
        this.f.add(generalName);
    }

    public X509AttributeCertificateHolderSelector build() {
        return new X509AttributeCertificateHolderSelector(this.f14515a, this.b, this.c, this.d, this.e, Collections.unmodifiableCollection(new HashSet(this.f)), Collections.unmodifiableCollection(new HashSet(this.g)));
    }

    public void setAttributeCert(X509AttributeCertificateHolder x509AttributeCertificateHolder) {
        this.e = x509AttributeCertificateHolder;
    }

    public void setAttributeCertificateValid(Date date) {
        if (date != null) {
            this.d = new Date(date.getTime());
        } else {
            this.d = null;
        }
    }

    public void setHolder(AttributeCertificateHolder attributeCertificateHolder) {
        this.f14515a = attributeCertificateHolder;
    }

    public void setIssuer(AttributeCertificateIssuer attributeCertificateIssuer) {
        this.b = attributeCertificateIssuer;
    }

    public void setSerialNumber(BigInteger bigInteger) {
        this.c = bigInteger;
    }

    public void setTargetGroups(Collection collection) throws IOException {
        this.g = a(collection);
    }

    public void setTargetNames(Collection collection) throws IOException {
        this.f = a(collection);
    }
}
