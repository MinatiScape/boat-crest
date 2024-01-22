package org.bouncycastle.cms;

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
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
/* loaded from: classes12.dex */
public class DefaultAuthenticatedAttributeTableGenerator implements CMSAttributeTableGenerator {

    /* renamed from: a  reason: collision with root package name */
    public final Hashtable f14532a;

    public DefaultAuthenticatedAttributeTableGenerator() {
        this.f14532a = new Hashtable();
    }

    public DefaultAuthenticatedAttributeTableGenerator(AttributeTable attributeTable) {
        this.f14532a = attributeTable != null ? attributeTable.toHashtable() : new Hashtable();
    }

    public Hashtable createStandardAttributeTable(Map map) {
        Hashtable hashtable = new Hashtable();
        Enumeration keys = this.f14532a.keys();
        while (keys.hasMoreElements()) {
            Object nextElement = keys.nextElement();
            hashtable.put(nextElement, this.f14532a.get(nextElement));
        }
        ASN1ObjectIdentifier aSN1ObjectIdentifier = CMSAttributes.contentType;
        if (!hashtable.containsKey(aSN1ObjectIdentifier)) {
            Attribute attribute = new Attribute(aSN1ObjectIdentifier, new DERSet(ASN1ObjectIdentifier.getInstance(map.get(CMSAttributeTableGenerator.CONTENT_TYPE))));
            hashtable.put(attribute.getAttrType(), attribute);
        }
        ASN1ObjectIdentifier aSN1ObjectIdentifier2 = CMSAttributes.messageDigest;
        if (!hashtable.containsKey(aSN1ObjectIdentifier2)) {
            Attribute attribute2 = new Attribute(aSN1ObjectIdentifier2, new DERSet(new DEROctetString((byte[]) map.get(CMSAttributeTableGenerator.DIGEST))));
            hashtable.put(attribute2.getAttrType(), attribute2);
        }
        ASN1ObjectIdentifier aSN1ObjectIdentifier3 = CMSAttributes.cmsAlgorithmProtect;
        if (!hashtable.contains(aSN1ObjectIdentifier3)) {
            Attribute attribute3 = new Attribute(aSN1ObjectIdentifier3, new DERSet(new CMSAlgorithmProtection((AlgorithmIdentifier) map.get(CMSAttributeTableGenerator.DIGEST_ALGORITHM_IDENTIFIER), 2, (AlgorithmIdentifier) map.get(CMSAttributeTableGenerator.MAC_ALGORITHM_IDENTIFIER))));
            hashtable.put(attribute3.getAttrType(), attribute3);
        }
        return hashtable;
    }

    @Override // org.bouncycastle.cms.CMSAttributeTableGenerator
    public AttributeTable getAttributes(Map map) {
        return new AttributeTable(createStandardAttributeTable(map));
    }
}
