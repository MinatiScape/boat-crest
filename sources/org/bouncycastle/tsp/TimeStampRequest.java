package org.bouncycastle.tsp;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.tsp.TimeStampReq;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.Extensions;
/* loaded from: classes13.dex */
public class TimeStampRequest {
    public static Set c = Collections.unmodifiableSet(new HashSet());

    /* renamed from: a  reason: collision with root package name */
    public TimeStampReq f15381a;
    public Extensions b;

    public TimeStampRequest(InputStream inputStream) throws IOException {
        this(c(inputStream));
    }

    public TimeStampRequest(TimeStampReq timeStampReq) {
        this.f15381a = timeStampReq;
        this.b = timeStampReq.getExtensions();
    }

    public TimeStampRequest(byte[] bArr) throws IOException {
        this(new ByteArrayInputStream(bArr));
    }

    public static TimeStampReq c(InputStream inputStream) throws IOException {
        try {
            return TimeStampReq.getInstance(new ASN1InputStream(inputStream).readObject());
        } catch (ClassCastException e) {
            throw new IOException("malformed request: " + e);
        } catch (IllegalArgumentException e2) {
            throw new IOException("malformed request: " + e2);
        }
    }

    public final Set a(Set set) {
        if (set == null) {
            return set;
        }
        HashSet hashSet = new HashSet(set.size());
        for (Object obj : set) {
            if (obj instanceof String) {
                hashSet.add(new ASN1ObjectIdentifier((String) obj));
            } else {
                hashSet.add(obj);
            }
        }
        return hashSet;
    }

    public Extensions b() {
        return this.b;
    }

    public boolean getCertReq() {
        if (this.f15381a.getCertReq() != null) {
            return this.f15381a.getCertReq().isTrue();
        }
        return false;
    }

    public Set getCriticalExtensionOIDs() {
        return this.b == null ? c : Collections.unmodifiableSet(new HashSet(Arrays.asList(this.b.getCriticalExtensionOIDs())));
    }

    public byte[] getEncoded() throws IOException {
        return this.f15381a.getEncoded();
    }

    public Extension getExtension(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        Extensions extensions = this.b;
        if (extensions != null) {
            return extensions.getExtension(aSN1ObjectIdentifier);
        }
        return null;
    }

    public List getExtensionOIDs() {
        return TSPUtil.c(this.b);
    }

    public ASN1ObjectIdentifier getMessageImprintAlgOID() {
        return this.f15381a.getMessageImprint().getHashAlgorithm().getAlgorithm();
    }

    public byte[] getMessageImprintDigest() {
        return this.f15381a.getMessageImprint().getHashedMessage();
    }

    public Set getNonCriticalExtensionOIDs() {
        return this.b == null ? c : Collections.unmodifiableSet(new HashSet(Arrays.asList(this.b.getNonCriticalExtensionOIDs())));
    }

    public BigInteger getNonce() {
        if (this.f15381a.getNonce() != null) {
            return this.f15381a.getNonce().getValue();
        }
        return null;
    }

    public ASN1ObjectIdentifier getReqPolicy() {
        if (this.f15381a.getReqPolicy() != null) {
            return this.f15381a.getReqPolicy();
        }
        return null;
    }

    public int getVersion() {
        return this.f15381a.getVersion().getValue().intValue();
    }

    public boolean hasExtensions() {
        return this.b != null;
    }

    public void validate(Set set, Set set2, Set set3) throws TSPException {
        Set a2 = a(set);
        Set a3 = a(set2);
        Set a4 = a(set3);
        if (!a2.contains(getMessageImprintAlgOID())) {
            throw new TSPValidationException("request contains unknown algorithm", 128);
        }
        if (a3 != null && getReqPolicy() != null && !a3.contains(getReqPolicy())) {
            throw new TSPValidationException("request contains unknown policy", 256);
        }
        if (b() != null && a4 != null) {
            Enumeration oids = b().oids();
            while (oids.hasMoreElements()) {
                if (!a4.contains((ASN1ObjectIdentifier) oids.nextElement())) {
                    throw new TSPValidationException("request contains unknown extension", 8388608);
                }
            }
        }
        if (TSPUtil.b(getMessageImprintAlgOID().getId()) != getMessageImprintDigest().length) {
            throw new TSPValidationException("imprint digest the wrong length", 4);
        }
    }
}
