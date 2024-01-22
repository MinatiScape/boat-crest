package org.bouncycastle.x509;

import java.io.IOException;
import java.math.BigInteger;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.Target;
import org.bouncycastle.asn1.x509.TargetInformation;
import org.bouncycastle.asn1.x509.Targets;
import org.bouncycastle.asn1.x509.X509Extensions;
import org.bouncycastle.util.Selector;
/* loaded from: classes13.dex */
public class X509AttributeCertStoreSelector implements Selector {
    public AttributeCertificateHolder h;
    public AttributeCertificateIssuer i;
    public BigInteger j;
    public Date k;
    public X509AttributeCertificate l;
    public Collection m = new HashSet();
    public Collection n = new HashSet();

    public final Set a(Collection collection) throws IOException {
        if (collection == null || collection.isEmpty()) {
            return new HashSet();
        }
        HashSet hashSet = new HashSet();
        for (Object obj : collection) {
            if (!(obj instanceof GeneralName)) {
                obj = GeneralName.getInstance(ASN1Primitive.fromByteArray((byte[]) obj));
            }
            hashSet.add(obj);
        }
        return hashSet;
    }

    public void addTargetGroup(GeneralName generalName) {
        this.n.add(generalName);
    }

    public void addTargetGroup(byte[] bArr) throws IOException {
        addTargetGroup(GeneralName.getInstance(ASN1Primitive.fromByteArray(bArr)));
    }

    public void addTargetName(GeneralName generalName) {
        this.m.add(generalName);
    }

    public void addTargetName(byte[] bArr) throws IOException {
        addTargetName(GeneralName.getInstance(ASN1Primitive.fromByteArray(bArr)));
    }

    @Override // org.bouncycastle.util.Selector
    public Object clone() {
        X509AttributeCertStoreSelector x509AttributeCertStoreSelector = new X509AttributeCertStoreSelector();
        x509AttributeCertStoreSelector.l = this.l;
        x509AttributeCertStoreSelector.k = getAttributeCertificateValid();
        x509AttributeCertStoreSelector.h = this.h;
        x509AttributeCertStoreSelector.i = this.i;
        x509AttributeCertStoreSelector.j = this.j;
        x509AttributeCertStoreSelector.n = getTargetGroups();
        x509AttributeCertStoreSelector.m = getTargetNames();
        return x509AttributeCertStoreSelector;
    }

    public X509AttributeCertificate getAttributeCert() {
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
        return Collections.unmodifiableCollection(this.n);
    }

    public Collection getTargetNames() {
        return Collections.unmodifiableCollection(this.m);
    }

    @Override // org.bouncycastle.util.Selector
    public boolean match(Object obj) {
        byte[] extensionValue;
        Targets[] targetsObjects;
        if (obj instanceof X509AttributeCertificate) {
            X509AttributeCertificate x509AttributeCertificate = (X509AttributeCertificate) obj;
            X509AttributeCertificate x509AttributeCertificate2 = this.l;
            if (x509AttributeCertificate2 == null || x509AttributeCertificate2.equals(x509AttributeCertificate)) {
                if (this.j == null || x509AttributeCertificate.getSerialNumber().equals(this.j)) {
                    if (this.h == null || x509AttributeCertificate.getHolder().equals(this.h)) {
                        if (this.i == null || x509AttributeCertificate.getIssuer().equals(this.i)) {
                            Date date = this.k;
                            if (date != null) {
                                try {
                                    x509AttributeCertificate.checkValidity(date);
                                } catch (CertificateExpiredException | CertificateNotYetValidException unused) {
                                    return false;
                                }
                            }
                            if ((!this.m.isEmpty() || !this.n.isEmpty()) && (extensionValue = x509AttributeCertificate.getExtensionValue(X509Extensions.TargetInformation.getId())) != null) {
                                try {
                                    targetsObjects = TargetInformation.getInstance(new ASN1InputStream(((DEROctetString) ASN1Primitive.fromByteArray(extensionValue)).getOctets()).readObject()).getTargetsObjects();
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
                                } catch (IOException | IllegalArgumentException unused2) {
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

    public void setAttributeCert(X509AttributeCertificate x509AttributeCertificate) {
        this.l = x509AttributeCertificate;
    }

    public void setAttributeCertificateValid(Date date) {
        if (date != null) {
            this.k = new Date(date.getTime());
        } else {
            this.k = null;
        }
    }

    public void setHolder(AttributeCertificateHolder attributeCertificateHolder) {
        this.h = attributeCertificateHolder;
    }

    public void setIssuer(AttributeCertificateIssuer attributeCertificateIssuer) {
        this.i = attributeCertificateIssuer;
    }

    public void setSerialNumber(BigInteger bigInteger) {
        this.j = bigInteger;
    }

    public void setTargetGroups(Collection collection) throws IOException {
        this.n = a(collection);
    }

    public void setTargetNames(Collection collection) throws IOException {
        this.m = a(collection);
    }
}
