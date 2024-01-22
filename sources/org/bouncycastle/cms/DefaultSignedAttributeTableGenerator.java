package org.bouncycastle.cms;

import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSet;
import org.bouncycastle.asn1.cms.Attribute;
import org.bouncycastle.asn1.cms.AttributeTable;
import org.bouncycastle.asn1.cms.CMSAlgorithmProtection;
import org.bouncycastle.asn1.cms.CMSAttributes;
import org.bouncycastle.asn1.cms.Time;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
/* loaded from: classes12.dex */
public class DefaultSignedAttributeTableGenerator implements CMSAttributeTableGenerator {

    /* renamed from: a  reason: collision with root package name */
    public final Hashtable f14535a;

    public DefaultSignedAttributeTableGenerator() {
        this.f14535a = new Hashtable();
    }

    public DefaultSignedAttributeTableGenerator(AttributeTable attributeTable) {
        this.f14535a = attributeTable != null ? attributeTable.toHashtable() : new Hashtable();
    }

    public static Hashtable a(Hashtable hashtable) {
        Hashtable hashtable2 = new Hashtable();
        Enumeration keys = hashtable.keys();
        while (keys.hasMoreElements()) {
            Object nextElement = keys.nextElement();
            hashtable2.put(nextElement, hashtable.get(nextElement));
        }
        return hashtable2;
    }

    public Hashtable createStandardAttributeTable(Map map) {
        ASN1ObjectIdentifier aSN1ObjectIdentifier;
        Hashtable a2 = a(this.f14535a);
        ASN1ObjectIdentifier aSN1ObjectIdentifier2 = CMSAttributes.contentType;
        if (!a2.containsKey(aSN1ObjectIdentifier2) && (aSN1ObjectIdentifier = ASN1ObjectIdentifier.getInstance(map.get(CMSAttributeTableGenerator.CONTENT_TYPE))) != null) {
            Attribute attribute = new Attribute(aSN1ObjectIdentifier2, new DERSet(aSN1ObjectIdentifier));
            a2.put(attribute.getAttrType(), attribute);
        }
        ASN1ObjectIdentifier aSN1ObjectIdentifier3 = CMSAttributes.signingTime;
        if (!a2.containsKey(aSN1ObjectIdentifier3)) {
            Attribute attribute2 = new Attribute(aSN1ObjectIdentifier3, new DERSet(new Time(new Date())));
            a2.put(attribute2.getAttrType(), attribute2);
        }
        ASN1ObjectIdentifier aSN1ObjectIdentifier4 = CMSAttributes.messageDigest;
        if (!a2.containsKey(aSN1ObjectIdentifier4)) {
            Attribute attribute3 = new Attribute(aSN1ObjectIdentifier4, new DERSet(new DEROctetString((byte[]) map.get(CMSAttributeTableGenerator.DIGEST))));
            a2.put(attribute3.getAttrType(), attribute3);
        }
        ASN1ObjectIdentifier aSN1ObjectIdentifier5 = CMSAttributes.cmsAlgorithmProtect;
        if (!a2.contains(aSN1ObjectIdentifier5)) {
            Attribute attribute4 = new Attribute(aSN1ObjectIdentifier5, new DERSet(new CMSAlgorithmProtection((AlgorithmIdentifier) map.get(CMSAttributeTableGenerator.DIGEST_ALGORITHM_IDENTIFIER), 1, (AlgorithmIdentifier) map.get(CMSAttributeTableGenerator.SIGNATURE_ALGORITHM_IDENTIFIER))));
            a2.put(attribute4.getAttrType(), attribute4);
        }
        return a2;
    }

    @Override // org.bouncycastle.cms.CMSAttributeTableGenerator
    public AttributeTable getAttributes(Map map) {
        return new AttributeTable(createStandardAttributeTable(map));
    }
}
