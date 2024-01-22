package org.bouncycastle.cms;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.BEROctetStringGenerator;
import org.bouncycastle.asn1.BERSet;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.DERSet;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.cms.CMSObjectIdentifiers;
import org.bouncycastle.asn1.cms.ContentInfo;
import org.bouncycastle.asn1.cms.OtherRevocationInfoFormat;
import org.bouncycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
import org.bouncycastle.asn1.ocsp.OCSPResponse;
import org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.rosstandart.RosstandartObjectIdentifiers;
import org.bouncycastle.asn1.sec.SECObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x9.X9ObjectIdentifiers;
import org.bouncycastle.cert.X509AttributeCertificateHolder;
import org.bouncycastle.cert.X509CRLHolder;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.operator.DigestCalculator;
import org.bouncycastle.util.Store;
import org.bouncycastle.util.Strings;
import org.bouncycastle.util.io.Streams;
import org.bouncycastle.util.io.TeeInputStream;
import org.bouncycastle.util.io.TeeOutputStream;
/* loaded from: classes12.dex */
public class g {

    /* renamed from: a  reason: collision with root package name */
    public static final Set<String> f14566a;
    public static final Set b;
    public static final Set c;
    public static final Set d;

    static {
        HashSet hashSet = new HashSet();
        f14566a = hashSet;
        HashSet hashSet2 = new HashSet();
        b = hashSet2;
        HashSet hashSet3 = new HashSet();
        c = hashSet3;
        HashSet hashSet4 = new HashSet();
        d = hashSet4;
        hashSet.add("DES");
        hashSet.add("DESEDE");
        hashSet.add(OIWObjectIdentifiers.desCBC.getId());
        ASN1ObjectIdentifier aSN1ObjectIdentifier = PKCSObjectIdentifiers.des_EDE3_CBC;
        hashSet.add(aSN1ObjectIdentifier.getId());
        hashSet.add(aSN1ObjectIdentifier.getId());
        hashSet.add(PKCSObjectIdentifiers.id_alg_CMS3DESwrap.getId());
        hashSet2.add(X9ObjectIdentifiers.mqvSinglePass_sha1kdf_scheme);
        hashSet2.add(SECObjectIdentifiers.mqvSinglePass_sha224kdf_scheme);
        hashSet2.add(SECObjectIdentifiers.mqvSinglePass_sha256kdf_scheme);
        hashSet2.add(SECObjectIdentifiers.mqvSinglePass_sha384kdf_scheme);
        hashSet2.add(SECObjectIdentifiers.mqvSinglePass_sha512kdf_scheme);
        hashSet3.add(X9ObjectIdentifiers.dhSinglePass_cofactorDH_sha1kdf_scheme);
        hashSet3.add(X9ObjectIdentifiers.dhSinglePass_stdDH_sha1kdf_scheme);
        hashSet3.add(SECObjectIdentifiers.dhSinglePass_cofactorDH_sha224kdf_scheme);
        hashSet3.add(SECObjectIdentifiers.dhSinglePass_stdDH_sha224kdf_scheme);
        hashSet3.add(SECObjectIdentifiers.dhSinglePass_cofactorDH_sha256kdf_scheme);
        hashSet3.add(SECObjectIdentifiers.dhSinglePass_stdDH_sha256kdf_scheme);
        hashSet3.add(SECObjectIdentifiers.dhSinglePass_cofactorDH_sha384kdf_scheme);
        hashSet3.add(SECObjectIdentifiers.dhSinglePass_stdDH_sha384kdf_scheme);
        hashSet3.add(SECObjectIdentifiers.dhSinglePass_cofactorDH_sha512kdf_scheme);
        hashSet3.add(SECObjectIdentifiers.dhSinglePass_stdDH_sha512kdf_scheme);
        hashSet4.add(CryptoProObjectIdentifiers.gostR3410_2001_CryptoPro_ESDH);
        hashSet4.add(RosstandartObjectIdentifiers.id_tc26_agreement_gost_3410_12_256);
        hashSet4.add(RosstandartObjectIdentifiers.id_tc26_agreement_gost_3410_12_512);
    }

    public static InputStream a(Collection collection, InputStream inputStream) {
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            inputStream = new TeeInputStream(inputStream, ((DigestCalculator) it.next()).getOutputStream());
        }
        return inputStream;
    }

    public static OutputStream b(Collection collection, OutputStream outputStream) {
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            outputStream = k(outputStream, ((SignerInfoGenerator) it.next()).getCalculatingOutputStream());
        }
        return outputStream;
    }

    public static OutputStream c(OutputStream outputStream, int i, boolean z, int i2) throws IOException {
        BEROctetStringGenerator bEROctetStringGenerator = new BEROctetStringGenerator(outputStream, i, z);
        return i2 != 0 ? bEROctetStringGenerator.getOctetOutputStream(new byte[i2]) : bEROctetStringGenerator.getOctetOutputStream();
    }

    public static ASN1Set d(List list) {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            aSN1EncodableVector.add((ASN1Encodable) it.next());
        }
        return new BERSet(aSN1EncodableVector);
    }

    public static ASN1Set e(List list) {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            aSN1EncodableVector.add((ASN1Encodable) it.next());
        }
        return new DERSet(aSN1EncodableVector);
    }

    public static List f(Store store) throws CMSException {
        ArrayList arrayList = new ArrayList();
        try {
            for (X509AttributeCertificateHolder x509AttributeCertificateHolder : store.getMatches(null)) {
                arrayList.add(new DERTaggedObject(false, 2, x509AttributeCertificateHolder.toASN1Structure()));
            }
            return arrayList;
        } catch (ClassCastException e) {
            throw new CMSException("error processing certs", e);
        }
    }

    public static List g(Store store) throws CMSException {
        ArrayList arrayList = new ArrayList();
        try {
            for (Object obj : store.getMatches(null)) {
                if (obj instanceof X509CRLHolder) {
                    obj = ((X509CRLHolder) obj).toASN1Structure();
                } else if (obj instanceof OtherRevocationInfoFormat) {
                    OtherRevocationInfoFormat otherRevocationInfoFormat = OtherRevocationInfoFormat.getInstance(obj);
                    s(otherRevocationInfoFormat);
                    arrayList.add(new DERTaggedObject(false, 1, otherRevocationInfoFormat));
                } else if (obj instanceof ASN1TaggedObject) {
                }
                arrayList.add(obj);
            }
            return arrayList;
        } catch (ClassCastException e) {
            throw new CMSException("error processing certs", e);
        }
    }

    public static List h(Store store) throws CMSException {
        ArrayList arrayList = new ArrayList();
        try {
            for (X509CertificateHolder x509CertificateHolder : store.getMatches(null)) {
                arrayList.add(x509CertificateHolder.toASN1Structure());
            }
            return arrayList;
        } catch (ClassCastException e) {
            throw new CMSException("error processing certs", e);
        }
    }

    public static Collection i(ASN1ObjectIdentifier aSN1ObjectIdentifier, Store store) {
        ArrayList arrayList = new ArrayList();
        for (ASN1Encodable aSN1Encodable : store.getMatches(null)) {
            OtherRevocationInfoFormat otherRevocationInfoFormat = new OtherRevocationInfoFormat(aSN1ObjectIdentifier, aSN1Encodable);
            s(otherRevocationInfoFormat);
            arrayList.add(new DERTaggedObject(false, 1, otherRevocationInfoFormat));
        }
        return arrayList;
    }

    public static OutputStream j(OutputStream outputStream) {
        return outputStream == null ? new h() : outputStream;
    }

    public static OutputStream k(OutputStream outputStream, OutputStream outputStream2) {
        return outputStream == null ? j(outputStream2) : outputStream2 == null ? j(outputStream) : new TeeOutputStream(outputStream, outputStream2);
    }

    public static boolean l(String str) {
        return f14566a.contains(Strings.toUpperCase(str));
    }

    public static boolean m(AlgorithmIdentifier algorithmIdentifier, AlgorithmIdentifier algorithmIdentifier2) {
        if (algorithmIdentifier == null || algorithmIdentifier2 == null || !algorithmIdentifier.getAlgorithm().equals(algorithmIdentifier2.getAlgorithm())) {
            return false;
        }
        ASN1Encodable parameters = algorithmIdentifier.getParameters();
        ASN1Encodable parameters2 = algorithmIdentifier2.getParameters();
        return parameters != null ? parameters.equals(parameters2) || (parameters.equals(DERNull.INSTANCE) && parameters2 == null) : parameters2 == null || parameters2.equals(DERNull.INSTANCE);
    }

    public static boolean n(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return d.contains(aSN1ObjectIdentifier);
    }

    public static ContentInfo o(InputStream inputStream) throws CMSException {
        return p(new ASN1InputStream(inputStream));
    }

    public static ContentInfo p(ASN1InputStream aSN1InputStream) throws CMSException {
        try {
            ContentInfo contentInfo = ContentInfo.getInstance(aSN1InputStream.readObject());
            if (contentInfo != null) {
                return contentInfo;
            }
            throw new CMSException("No content found.");
        } catch (IOException e) {
            throw new CMSException("IOException reading content.", e);
        } catch (ClassCastException e2) {
            throw new CMSException("Malformed content.", e2);
        } catch (IllegalArgumentException e3) {
            throw new CMSException("Malformed content.", e3);
        }
    }

    public static ContentInfo q(byte[] bArr) throws CMSException {
        return p(new ASN1InputStream(bArr));
    }

    public static byte[] r(InputStream inputStream) throws IOException {
        return Streams.readAll(inputStream);
    }

    public static void s(OtherRevocationInfoFormat otherRevocationInfoFormat) {
        if (CMSObjectIdentifiers.id_ri_ocsp_response.equals(otherRevocationInfoFormat.getInfoFormat()) && OCSPResponse.getInstance(otherRevocationInfoFormat.getInfo()).getResponseStatus().getValue().intValue() != 0) {
            throw new IllegalArgumentException("cannot add unsuccessful OCSP response to CMS SignedData");
        }
    }
}
