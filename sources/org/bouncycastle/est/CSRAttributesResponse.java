package org.bouncycastle.est;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.est.AttrOrOID;
import org.bouncycastle.asn1.est.CsrAttrs;
import org.bouncycastle.util.Encodable;
/* loaded from: classes13.dex */
public class CSRAttributesResponse implements Encodable {
    public final CsrAttrs h;
    public final HashMap<ASN1ObjectIdentifier, AttrOrOID> i;

    public CSRAttributesResponse(CsrAttrs csrAttrs) throws ESTException {
        HashMap<ASN1ObjectIdentifier, AttrOrOID> hashMap;
        ASN1ObjectIdentifier attrType;
        this.h = csrAttrs;
        this.i = new HashMap<>(csrAttrs.size());
        AttrOrOID[] attrOrOIDs = csrAttrs.getAttrOrOIDs();
        for (int i = 0; i != attrOrOIDs.length; i++) {
            AttrOrOID attrOrOID = attrOrOIDs[i];
            if (attrOrOID.isOid()) {
                hashMap = this.i;
                attrType = attrOrOID.getOid();
            } else {
                hashMap = this.i;
                attrType = attrOrOID.getAttribute().getAttrType();
            }
            hashMap.put(attrType, attrOrOID);
        }
    }

    public CSRAttributesResponse(byte[] bArr) throws ESTException {
        this(a(bArr));
    }

    public static CsrAttrs a(byte[] bArr) throws ESTException {
        try {
            return CsrAttrs.getInstance(ASN1Primitive.fromByteArray(bArr));
        } catch (Exception e) {
            throw new ESTException("malformed data: " + e.getMessage(), e);
        }
    }

    @Override // org.bouncycastle.util.Encodable
    public byte[] getEncoded() throws IOException {
        return this.h.getEncoded();
    }

    public Collection<ASN1ObjectIdentifier> getRequirements() {
        return this.i.keySet();
    }

    public boolean hasRequirement(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return this.i.containsKey(aSN1ObjectIdentifier);
    }

    public boolean isAttribute(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        if (this.i.containsKey(aSN1ObjectIdentifier)) {
            return !this.i.get(aSN1ObjectIdentifier).isOid();
        }
        return false;
    }

    public boolean isEmpty() {
        return this.h.size() == 0;
    }
}
