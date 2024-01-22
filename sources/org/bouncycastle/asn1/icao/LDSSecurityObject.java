package org.bouncycastle.asn1.icao;

import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
/* loaded from: classes12.dex */
public class LDSSecurityObject extends ASN1Object implements ICAOObjectIdentifiers {
    public static final int ub_DataGroups = 16;
    public ASN1Integer h;
    public AlgorithmIdentifier i;
    public DataGroupHash[] j;
    public LDSVersionInfo k;

    public LDSSecurityObject(ASN1Sequence aSN1Sequence) {
        this.h = new ASN1Integer(0L);
        if (aSN1Sequence == null || aSN1Sequence.size() == 0) {
            throw new IllegalArgumentException("null or empty sequence passed.");
        }
        Enumeration objects = aSN1Sequence.getObjects();
        this.h = ASN1Integer.getInstance(objects.nextElement());
        this.i = AlgorithmIdentifier.getInstance(objects.nextElement());
        ASN1Sequence aSN1Sequence2 = ASN1Sequence.getInstance(objects.nextElement());
        if (this.h.getValue().intValue() == 1) {
            this.k = LDSVersionInfo.getInstance(objects.nextElement());
        }
        a(aSN1Sequence2.size());
        this.j = new DataGroupHash[aSN1Sequence2.size()];
        for (int i = 0; i < aSN1Sequence2.size(); i++) {
            this.j[i] = DataGroupHash.getInstance(aSN1Sequence2.getObjectAt(i));
        }
    }

    public LDSSecurityObject(AlgorithmIdentifier algorithmIdentifier, DataGroupHash[] dataGroupHashArr) {
        this.h = new ASN1Integer(0L);
        this.h = new ASN1Integer(0L);
        this.i = algorithmIdentifier;
        this.j = dataGroupHashArr;
        a(dataGroupHashArr.length);
    }

    public LDSSecurityObject(AlgorithmIdentifier algorithmIdentifier, DataGroupHash[] dataGroupHashArr, LDSVersionInfo lDSVersionInfo) {
        this.h = new ASN1Integer(0L);
        this.h = new ASN1Integer(1L);
        this.i = algorithmIdentifier;
        this.j = dataGroupHashArr;
        this.k = lDSVersionInfo;
        a(dataGroupHashArr.length);
    }

    public static LDSSecurityObject getInstance(Object obj) {
        if (obj instanceof LDSSecurityObject) {
            return (LDSSecurityObject) obj;
        }
        if (obj != null) {
            return new LDSSecurityObject(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public final void a(int i) {
        if (i < 2 || i > 16) {
            throw new IllegalArgumentException("wrong size in DataGroupHashValues : not in (2..16)");
        }
    }

    public DataGroupHash[] getDatagroupHash() {
        return this.j;
    }

    public AlgorithmIdentifier getDigestAlgorithmIdentifier() {
        return this.i;
    }

    public int getVersion() {
        return this.h.getValue().intValue();
    }

    public LDSVersionInfo getVersionInfo() {
        return this.k;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.h);
        aSN1EncodableVector.add(this.i);
        ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector();
        int i = 0;
        while (true) {
            DataGroupHash[] dataGroupHashArr = this.j;
            if (i >= dataGroupHashArr.length) {
                break;
            }
            aSN1EncodableVector2.add(dataGroupHashArr[i]);
            i++;
        }
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector2));
        LDSVersionInfo lDSVersionInfo = this.k;
        if (lDSVersionInfo != null) {
            aSN1EncodableVector.add(lDSVersionInfo);
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
