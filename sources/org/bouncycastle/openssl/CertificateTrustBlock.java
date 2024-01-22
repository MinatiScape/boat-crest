package org.bouncycastle.openssl;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.DERUTF8String;
/* loaded from: classes13.dex */
public class CertificateTrustBlock {

    /* renamed from: a  reason: collision with root package name */
    public ASN1Sequence f15196a;
    public ASN1Sequence b;
    public String c;

    public CertificateTrustBlock(String str, Set<ASN1ObjectIdentifier> set) {
        this(str, set, null);
    }

    public CertificateTrustBlock(String str, Set<ASN1ObjectIdentifier> set, Set<ASN1ObjectIdentifier> set2) {
        this.c = str;
        this.f15196a = b(set);
        this.b = b(set2);
    }

    public CertificateTrustBlock(Set<ASN1ObjectIdentifier> set) {
        this(null, set, null);
    }

    public CertificateTrustBlock(byte[] bArr) {
        Enumeration objects = ASN1Sequence.getInstance(bArr).getObjects();
        while (objects.hasMoreElements()) {
            ASN1Encodable aSN1Encodable = (ASN1Encodable) objects.nextElement();
            if (aSN1Encodable instanceof ASN1Sequence) {
                this.f15196a = ASN1Sequence.getInstance(aSN1Encodable);
            } else if (aSN1Encodable instanceof ASN1TaggedObject) {
                this.b = ASN1Sequence.getInstance((ASN1TaggedObject) aSN1Encodable, false);
            } else if (aSN1Encodable instanceof DERUTF8String) {
                this.c = DERUTF8String.getInstance(aSN1Encodable).getString();
            }
        }
    }

    public ASN1Sequence a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        ASN1Sequence aSN1Sequence = this.f15196a;
        if (aSN1Sequence != null) {
            aSN1EncodableVector.add(aSN1Sequence);
        }
        if (this.b != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 0, this.b));
        }
        String str = this.c;
        if (str != null) {
            aSN1EncodableVector.add(new DERUTF8String(str));
        }
        return new DERSequence(aSN1EncodableVector);
    }

    public final ASN1Sequence b(Set<ASN1ObjectIdentifier> set) {
        if (set == null || set.isEmpty()) {
            return null;
        }
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        for (ASN1ObjectIdentifier aSN1ObjectIdentifier : set) {
            aSN1EncodableVector.add(aSN1ObjectIdentifier);
        }
        return new DERSequence(aSN1EncodableVector);
    }

    public final Set<ASN1ObjectIdentifier> c(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence != null) {
            HashSet hashSet = new HashSet(aSN1Sequence.size());
            Enumeration objects = aSN1Sequence.getObjects();
            while (objects.hasMoreElements()) {
                hashSet.add(ASN1ObjectIdentifier.getInstance(objects.nextElement()));
            }
            return hashSet;
        }
        return Collections.EMPTY_SET;
    }

    public String getAlias() {
        return this.c;
    }

    public Set<ASN1ObjectIdentifier> getProhibitions() {
        return c(this.b);
    }

    public Set<ASN1ObjectIdentifier> getUses() {
        return c(this.f15196a);
    }
}
