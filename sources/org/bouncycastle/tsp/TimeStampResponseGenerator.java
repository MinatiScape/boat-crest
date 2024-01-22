package org.bouncycastle.tsp;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.bouncycastle.asn1.ASN1BitString;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERUTF8String;
import org.bouncycastle.asn1.DLSequence;
import org.bouncycastle.asn1.cmp.PKIFreeText;
import org.bouncycastle.asn1.cmp.PKIStatusInfo;
import org.bouncycastle.asn1.tsp.TimeStampResp;
import org.bouncycastle.asn1.x509.Extensions;
/* loaded from: classes13.dex */
public class TimeStampResponseGenerator {

    /* renamed from: a  reason: collision with root package name */
    public int f15384a;
    public ASN1EncodableVector b;
    public int c;
    public TimeStampTokenGenerator d;
    public Set e;
    public Set f;
    public Set g;

    /* loaded from: classes13.dex */
    public class a extends DERBitString {
        public a(TimeStampResponseGenerator timeStampResponseGenerator, int i) {
            super(ASN1BitString.getBytes(i), ASN1BitString.getPadBits(i));
        }
    }

    public TimeStampResponseGenerator(TimeStampTokenGenerator timeStampTokenGenerator, Set set) {
        this(timeStampTokenGenerator, set, null, null);
    }

    public TimeStampResponseGenerator(TimeStampTokenGenerator timeStampTokenGenerator, Set set, Set set2) {
        this(timeStampTokenGenerator, set, set2, null);
    }

    public TimeStampResponseGenerator(TimeStampTokenGenerator timeStampTokenGenerator, Set set, Set set2, Set set3) {
        this.d = timeStampTokenGenerator;
        this.e = b(set);
        this.f = b(set2);
        this.g = b(set3);
        this.b = new ASN1EncodableVector();
    }

    public final void a(String str) {
        this.b.add(new DERUTF8String(str));
    }

    public final Set b(Set set) {
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

    public final PKIStatusInfo c() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(new ASN1Integer(this.f15384a));
        if (this.b.size() > 0) {
            aSN1EncodableVector.add(PKIFreeText.getInstance(new DERSequence(this.b)));
        }
        if (this.c != 0) {
            aSN1EncodableVector.add(new a(this, this.c));
        }
        return PKIStatusInfo.getInstance(new DERSequence(aSN1EncodableVector));
    }

    public final void d(int i) {
        this.c = i | this.c;
    }

    public TimeStampResponse generate(TimeStampRequest timeStampRequest, BigInteger bigInteger, Date date) throws TSPException {
        try {
            return generateGrantedResponse(timeStampRequest, bigInteger, date, "Operation Okay");
        } catch (Exception e) {
            return generateRejectedResponse(e);
        }
    }

    public TimeStampResponse generateFailResponse(int i, int i2, String str) throws TSPException {
        this.f15384a = i;
        this.b = new ASN1EncodableVector();
        d(i2);
        if (str != null) {
            a(str);
        }
        try {
            return new TimeStampResponse(new TimeStampResp(c(), null));
        } catch (IOException unused) {
            throw new TSPException("created badly formatted response!");
        }
    }

    public TimeStampResponse generateGrantedResponse(TimeStampRequest timeStampRequest, BigInteger bigInteger, Date date) throws TSPException {
        return generateGrantedResponse(timeStampRequest, bigInteger, date, null);
    }

    public TimeStampResponse generateGrantedResponse(TimeStampRequest timeStampRequest, BigInteger bigInteger, Date date, String str) throws TSPException {
        return generateGrantedResponse(timeStampRequest, bigInteger, date, str, null);
    }

    public TimeStampResponse generateGrantedResponse(TimeStampRequest timeStampRequest, BigInteger bigInteger, Date date, String str, Extensions extensions) throws TSPException {
        if (date != null) {
            timeStampRequest.validate(this.e, this.f, this.g);
            this.f15384a = 0;
            this.b = new ASN1EncodableVector();
            if (str != null) {
                a(str);
            }
            try {
                try {
                    return new TimeStampResponse(new DLSequence(new ASN1Encodable[]{c().toASN1Primitive(), this.d.generate(timeStampRequest, bigInteger, date, extensions).toCMSSignedData().toASN1Structure().toASN1Primitive()}));
                } catch (IOException unused) {
                    throw new TSPException("created badly formatted response!");
                }
            } catch (TSPException e) {
                throw e;
            } catch (Exception e2) {
                throw new TSPException("Timestamp token received cannot be converted to ContentInfo", e2);
            }
        }
        throw new TSPValidationException("The time source is not available.", 512);
    }

    public TimeStampResponse generateRejectedResponse(Exception exc) throws TSPException {
        return generateFailResponse(2, exc instanceof TSPValidationException ? ((TSPValidationException) exc).getFailureCode() : 1073741824, exc.getMessage());
    }
}
