package org.bouncycastle.asn1.x509;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Vector;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Encoding;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.DEROctetString;
/* loaded from: classes12.dex */
public class X509ExtensionsGenerator {

    /* renamed from: a  reason: collision with root package name */
    public Hashtable f14437a = new Hashtable();
    public Vector b = new Vector();

    public void addExtension(ASN1ObjectIdentifier aSN1ObjectIdentifier, boolean z, ASN1Encodable aSN1Encodable) {
        try {
            addExtension(aSN1ObjectIdentifier, z, aSN1Encodable.toASN1Primitive().getEncoded(ASN1Encoding.DER));
        } catch (IOException e) {
            throw new IllegalArgumentException("error encoding value: " + e);
        }
    }

    public void addExtension(ASN1ObjectIdentifier aSN1ObjectIdentifier, boolean z, byte[] bArr) {
        if (!this.f14437a.containsKey(aSN1ObjectIdentifier)) {
            this.b.addElement(aSN1ObjectIdentifier);
            this.f14437a.put(aSN1ObjectIdentifier, new X509Extension(z, new DEROctetString(bArr)));
            return;
        }
        throw new IllegalArgumentException("extension " + aSN1ObjectIdentifier + " already added");
    }

    public X509Extensions generate() {
        return new X509Extensions(this.b, this.f14437a);
    }

    public boolean isEmpty() {
        return this.b.isEmpty();
    }

    public void reset() {
        this.f14437a = new Hashtable();
        this.b = new Vector();
    }
}
