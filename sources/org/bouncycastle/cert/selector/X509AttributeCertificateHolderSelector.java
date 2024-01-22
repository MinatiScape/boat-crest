package org.bouncycastle.cert.selector;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.Target;
import org.bouncycastle.asn1.x509.TargetInformation;
import org.bouncycastle.asn1.x509.Targets;
import org.bouncycastle.cert.AttributeCertificateHolder;
import org.bouncycastle.cert.AttributeCertificateIssuer;
import org.bouncycastle.cert.X509AttributeCertificateHolder;
import org.bouncycastle.util.Selector;
/* loaded from: classes12.dex */
public class X509AttributeCertificateHolderSelector implements Selector {
    public final AttributeCertificateHolder h;
    public final AttributeCertificateIssuer i;
    public final BigInteger j;
    public final Date k;
    public final X509AttributeCertificateHolder l;
    public final Collection m;
    public final Collection n;

    public X509AttributeCertificateHolderSelector(AttributeCertificateHolder attributeCertificateHolder, AttributeCertificateIssuer attributeCertificateIssuer, BigInteger bigInteger, Date date, X509AttributeCertificateHolder x509AttributeCertificateHolder, Collection collection, Collection collection2) {
        this.h = attributeCertificateHolder;
        this.i = attributeCertificateIssuer;
        this.j = bigInteger;
        this.k = date;
        this.l = x509AttributeCertificateHolder;
        this.m = collection;
        this.n = collection2;
    }

    @Override // org.bouncycastle.util.Selector
    public Object clone() {
        return new X509AttributeCertificateHolderSelector(this.h, this.i, this.j, this.k, this.l, this.m, this.n);
    }

    public X509AttributeCertificateHolder getAttributeCert() {
        return this.l;
    }

    public Date getAttributeCertificateValid() {
        if (this.k != null) {
            return new Date(this.k.getTime());
        }
        return null;
    }

    public AttributeCertificateHolder getHolder() {
        return this.h;
    }

    public AttributeCertificateIssuer getIssuer() {
        return this.i;
    }

    public BigInteger getSerialNumber() {
        return this.j;
    }

    public Collection getTargetGroups() {
        return this.n;
    }

    public Collection getTargetNames() {
        return this.m;
    }

    @Override // org.bouncycastle.util.Selector
    public boolean match(Object obj) {
        Extension extension;
        Targets[] targetsObjects;
        if (obj instanceof X509AttributeCertificateHolder) {
            X509AttributeCertificateHolder x509AttributeCertificateHolder = (X509AttributeCertificateHolder) obj;
            X509AttributeCertificateHolder x509AttributeCertificateHolder2 = this.l;
            if (x509AttributeCertificateHolder2 == null || x509AttributeCertificateHolder2.equals(x509AttributeCertificateHolder)) {
                if (this.j == null || x509AttributeCertificateHolder.getSerialNumber().equals(this.j)) {
                    if (this.h == null || x509AttributeCertificateHolder.getHolder().equals(this.h)) {
                        if (this.i == null || x509AttributeCertificateHolder.getIssuer().equals(this.i)) {
                            Date date = this.k;
                            if (date == null || x509AttributeCertificateHolder.isValidOn(date)) {
                                if ((!this.m.isEmpty() || !this.n.isEmpty()) && (extension = x509AttributeCertificateHolder.getExtension(Extension.targetInformation)) != null) {
                                    try {
                                        targetsObjects = TargetInformation.getInstance(extension.getParsedValue()).getTargetsObjects();
                                        if (!this.m.isEmpty()) {
                                            boolean z = false;
                                            for (Targets targets : targetsObjects) {
                                                Target[] targets2 = targets.getTargets();
                                                int i = 0;
                                                while (true) {
                                                    if (i >= targets2.length) {
                                                        break;
                                                    } else if (this.m.contains(GeneralName.getInstance(targets2[i].getTargetName()))) {
                                                        z = true;
                                                        break;
                                                    } else {
                                                        i++;
                                                    }
                                                }
                                            }
                                            if (!z) {
                                                return false;
                                            }
                                        }
                                    } catch (IllegalArgumentException unused) {
                                    }
                                    if (!this.n.isEmpty()) {
                                        boolean z2 = false;
                                        for (Targets targets3 : targetsObjects) {
                                            Target[] targets4 = targets3.getTargets();
                                            int i2 = 0;
                                            while (true) {
                                                if (i2 >= targets4.length) {
                                                    break;
                                                } else if (this.n.contains(GeneralName.getInstance(targets4[i2].getTargetGroup()))) {
                                                    z2 = true;
                                                    break;
                                                } else {
                                                    i2++;
                                                }
                                            }
                                        }
                                        if (!z2) {
                                            return false;
                                        }
                                    }
                                }
                                return true;
                            }
                            return false;
                        }
                        return false;
                    }
                    return false;
                }
                return false;
            }
            return false;
        }
        return false;
    }
}
