package org.bouncycastle.x509.extension;

import java.io.IOException;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1String;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.X509Extension;
import org.bouncycastle.util.Integers;
/* loaded from: classes13.dex */
public class X509ExtensionUtil {
    public static Collection a(byte[] bArr) throws CertificateParsingException {
        Object aSN1Primitive;
        if (bArr == null) {
            return Collections.EMPTY_LIST;
        }
        try {
            ArrayList arrayList = new ArrayList();
            Enumeration objects = ASN1Sequence.getInstance(fromExtensionValue(bArr)).getObjects();
            while (objects.hasMoreElements()) {
                GeneralName generalName = GeneralName.getInstance(objects.nextElement());
                ArrayList arrayList2 = new ArrayList();
                arrayList2.add(Integers.valueOf(generalName.getTagNo()));
                switch (generalName.getTagNo()) {
                    case 0:
                    case 3:
                    case 5:
                        aSN1Primitive = generalName.getName().toASN1Primitive();
                        break;
                    case 1:
                    case 2:
                    case 6:
                        aSN1Primitive = ((ASN1String) generalName.getName()).getString();
                        break;
                    case 4:
                        aSN1Primitive = X500Name.getInstance(generalName.getName()).toString();
                        break;
                    case 7:
                        arrayList2.add(ASN1OctetString.getInstance(generalName.getName()).getOctets());
                        continue;
                        arrayList.add(arrayList2);
                    case 8:
                        aSN1Primitive = ASN1ObjectIdentifier.getInstance(generalName.getName()).getId();
                        break;
                    default:
                        throw new IOException("Bad tag number: " + generalName.getTagNo());
                }
                arrayList2.add(aSN1Primitive);
                arrayList.add(arrayList2);
            }
            return Collections.unmodifiableCollection(arrayList);
        } catch (Exception e) {
            throw new CertificateParsingException(e.getMessage());
        }
    }

    public static ASN1Primitive fromExtensionValue(byte[] bArr) throws IOException {
        return ASN1Primitive.fromByteArray(((ASN1OctetString) ASN1Primitive.fromByteArray(bArr)).getOctets());
    }

    public static Collection getIssuerAlternativeNames(X509Certificate x509Certificate) throws CertificateParsingException {
        return a(x509Certificate.getExtensionValue(X509Extension.issuerAlternativeName.getId()));
    }

    public static Collection getSubjectAlternativeNames(X509Certificate x509Certificate) throws CertificateParsingException {
        return a(x509Certificate.getExtensionValue(X509Extension.subjectAlternativeName.getId()));
    }
}
