package org.bouncycastle.asn1.x509;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Vector;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Encoding;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.DEROctetString;
/* loaded from: classes12.dex */
public class ExtensionsGenerator {

    /* renamed from: a  reason: collision with root package name */
    public Hashtable f14428a = new Hashtable();
    public Vector b = new Vector();

    public void addExtension(ASN1ObjectIdentifier aSN1ObjectIdentifier, boolean z, ASN1Encodable aSN1Encodable) throws IOException {
        addExtension(aSN1ObjectIdentifier, z, aSN1Encodable.toASN1Primitive().getEncoded(ASN1Encoding.DER));
    }

    public void addExtension(ASN1ObjectIdentifier aSN1ObjectIdentifier, boolean z, byte[] bArr) {
        if (!this.f14428a.containsKey(aSN1ObjectIdentifier)) {
            this.b.addElement(aSN1ObjectIdentifier);
            this.f14428a.put(aSN1ObjectIdentifier, new Extension(aSN1ObjectIdentifier, z, new DEROctetString(bArr)));
            return;
        }
        throw new IllegalArgumentException("extension " + aSN1ObjectIdentifier + " already added");
    }

    public void addExtension(Extension extension) {
        if (!this.f14428a.containsKey(extension.getExtnId())) {
            this.b.addElement(extension.getExtnId());
            this.f14428a.put(extension.getExtnId(), extension);
            return;
        }
        throw new IllegalArgumentException("extension " + extension.getExtnId() + " already added");
    }

    public Extensions generate() {
        Extension[] extensionArr = new Extension[this.b.size()];
        for (int i = 0; i != this.b.size(); i++) {
            extensionArr[i] = (Extension) this.f14428a.get(this.b.elementAt(i));
        }
        return new Extensions(extensionArr);
    }

    public boolean isEmpty() {
        return this.b.isEmpty();
    }

    public void reset() {
        this.f14428a = new Hashtable();
        this.b = new Vector();
    }
}
