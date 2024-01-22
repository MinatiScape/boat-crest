package org.bouncycastle.jcajce.provider.asymmetric.ies;

import java.io.IOException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidParameterSpecException;
import java.util.Objects;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Encoding;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.jce.spec.IESParameterSpec;
/* loaded from: classes13.dex */
public class AlgorithmParametersSpi extends java.security.AlgorithmParametersSpi {

    /* renamed from: a  reason: collision with root package name */
    public IESParameterSpec f14952a;

    @Override // java.security.AlgorithmParametersSpi
    public byte[] engineGetEncoded() {
        try {
            ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
            if (this.f14952a.getDerivationV() != null) {
                aSN1EncodableVector.add(new DERTaggedObject(false, 0, new DEROctetString(this.f14952a.getDerivationV())));
            }
            if (this.f14952a.getEncodingV() != null) {
                aSN1EncodableVector.add(new DERTaggedObject(false, 1, new DEROctetString(this.f14952a.getEncodingV())));
            }
            aSN1EncodableVector.add(new ASN1Integer(this.f14952a.getMacKeySize()));
            if (this.f14952a.getNonce() != null) {
                ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector();
                aSN1EncodableVector2.add(new ASN1Integer(this.f14952a.getCipherKeySize()));
                aSN1EncodableVector2.add(new ASN1Integer(this.f14952a.getNonce()));
                aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector2));
            }
            return new DERSequence(aSN1EncodableVector).getEncoded(ASN1Encoding.DER);
        } catch (IOException unused) {
            throw new RuntimeException("Error encoding IESParameters");
        }
    }

    @Override // java.security.AlgorithmParametersSpi
    public byte[] engineGetEncoded(String str) {
        if (isASN1FormatString(str) || str.equalsIgnoreCase("X.509")) {
            return engineGetEncoded();
        }
        return null;
    }

    @Override // java.security.AlgorithmParametersSpi
    public AlgorithmParameterSpec engineGetParameterSpec(Class cls) throws InvalidParameterSpecException {
        Objects.requireNonNull(cls, "argument to getParameterSpec must not be null");
        return localEngineGetParameterSpec(cls);
    }

    @Override // java.security.AlgorithmParametersSpi
    public void engineInit(AlgorithmParameterSpec algorithmParameterSpec) throws InvalidParameterSpecException {
        if (!(algorithmParameterSpec instanceof IESParameterSpec)) {
            throw new InvalidParameterSpecException("IESParameterSpec required to initialise a IES algorithm parameters object");
        }
        this.f14952a = (IESParameterSpec) algorithmParameterSpec;
    }

    @Override // java.security.AlgorithmParametersSpi
    public void engineInit(byte[] bArr) throws IOException {
        try {
            ASN1Sequence aSN1Sequence = (ASN1Sequence) ASN1Primitive.fromByteArray(bArr);
            if (aSN1Sequence.size() == 1) {
                this.f14952a = new IESParameterSpec(null, null, ASN1Integer.getInstance(aSN1Sequence.getObjectAt(0)).getValue().intValue());
            } else if (aSN1Sequence.size() == 2) {
                ASN1TaggedObject aSN1TaggedObject = ASN1TaggedObject.getInstance(aSN1Sequence.getObjectAt(0));
                this.f14952a = aSN1TaggedObject.getTagNo() == 0 ? new IESParameterSpec(ASN1OctetString.getInstance(aSN1TaggedObject, false).getOctets(), null, ASN1Integer.getInstance(aSN1Sequence.getObjectAt(1)).getValue().intValue()) : new IESParameterSpec(null, ASN1OctetString.getInstance(aSN1TaggedObject, false).getOctets(), ASN1Integer.getInstance(aSN1Sequence.getObjectAt(1)).getValue().intValue());
            } else if (aSN1Sequence.size() == 3) {
                this.f14952a = new IESParameterSpec(ASN1OctetString.getInstance(ASN1TaggedObject.getInstance(aSN1Sequence.getObjectAt(0)), false).getOctets(), ASN1OctetString.getInstance(ASN1TaggedObject.getInstance(aSN1Sequence.getObjectAt(1)), false).getOctets(), ASN1Integer.getInstance(aSN1Sequence.getObjectAt(2)).getValue().intValue());
            } else if (aSN1Sequence.size() == 4) {
                ASN1TaggedObject aSN1TaggedObject2 = ASN1TaggedObject.getInstance(aSN1Sequence.getObjectAt(0));
                ASN1TaggedObject aSN1TaggedObject3 = ASN1TaggedObject.getInstance(aSN1Sequence.getObjectAt(1));
                ASN1Sequence aSN1Sequence2 = ASN1Sequence.getInstance(aSN1Sequence.getObjectAt(3));
                this.f14952a = new IESParameterSpec(ASN1OctetString.getInstance(aSN1TaggedObject2, false).getOctets(), ASN1OctetString.getInstance(aSN1TaggedObject3, false).getOctets(), ASN1Integer.getInstance(aSN1Sequence.getObjectAt(2)).getValue().intValue(), ASN1Integer.getInstance(aSN1Sequence2.getObjectAt(0)).getValue().intValue(), ASN1OctetString.getInstance(aSN1Sequence2.getObjectAt(1)).getOctets());
            }
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new IOException("Not a valid IES Parameter encoding.");
        } catch (ClassCastException unused2) {
            throw new IOException("Not a valid IES Parameter encoding.");
        }
    }

    @Override // java.security.AlgorithmParametersSpi
    public void engineInit(byte[] bArr, String str) throws IOException {
        if (isASN1FormatString(str) || str.equalsIgnoreCase("X.509")) {
            engineInit(bArr);
            return;
        }
        throw new IOException("Unknown parameter format " + str);
    }

    @Override // java.security.AlgorithmParametersSpi
    public String engineToString() {
        return "IES Parameters";
    }

    public boolean isASN1FormatString(String str) {
        return str == null || str.equals("ASN.1");
    }

    public AlgorithmParameterSpec localEngineGetParameterSpec(Class cls) throws InvalidParameterSpecException {
        if (cls == IESParameterSpec.class || cls == AlgorithmParameterSpec.class) {
            return this.f14952a;
        }
        throw new InvalidParameterSpecException("unknown parameter spec passed to ElGamal parameters object.");
    }
}
