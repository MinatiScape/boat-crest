package org.bouncycastle.cms;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.BERSequenceGenerator;
import org.bouncycastle.asn1.BERTaggedObject;
import org.bouncycastle.asn1.DERSet;
import org.bouncycastle.asn1.cms.CMSObjectIdentifiers;
import org.bouncycastle.asn1.cms.SignerInfo;
/* loaded from: classes12.dex */
public class CMSSignedDataStreamGenerator extends CMSSignedGenerator {
    public int h;

    /* loaded from: classes12.dex */
    public class a extends OutputStream {
        public OutputStream h;
        public ASN1ObjectIdentifier i;
        public BERSequenceGenerator j;
        public BERSequenceGenerator k;
        public BERSequenceGenerator l;

        public a(OutputStream outputStream, ASN1ObjectIdentifier aSN1ObjectIdentifier, BERSequenceGenerator bERSequenceGenerator, BERSequenceGenerator bERSequenceGenerator2, BERSequenceGenerator bERSequenceGenerator3) {
            this.h = outputStream;
            this.i = aSN1ObjectIdentifier;
            this.j = bERSequenceGenerator;
            this.k = bERSequenceGenerator2;
            this.l = bERSequenceGenerator3;
        }

        @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            this.h.close();
            this.l.close();
            CMSSignedDataStreamGenerator.this.digests.clear();
            if (CMSSignedDataStreamGenerator.this.certs.size() != 0) {
                this.k.getRawOutputStream().write(new BERTaggedObject(false, 0, g.d(CMSSignedDataStreamGenerator.this.certs)).getEncoded());
            }
            if (CMSSignedDataStreamGenerator.this.crls.size() != 0) {
                this.k.getRawOutputStream().write(new BERTaggedObject(false, 1, g.d(CMSSignedDataStreamGenerator.this.crls)).getEncoded());
            }
            ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
            for (SignerInfoGenerator signerInfoGenerator : CMSSignedDataStreamGenerator.this.signerGens) {
                try {
                    aSN1EncodableVector.add(signerInfoGenerator.generate(this.i));
                    CMSSignedDataStreamGenerator.this.digests.put(signerInfoGenerator.getDigestAlgorithm().getAlgorithm().getId(), signerInfoGenerator.getCalculatedDigest());
                } catch (CMSException e) {
                    throw new CMSStreamException("exception generating signers: " + e.getMessage(), e);
                }
            }
            for (SignerInformation signerInformation : CMSSignedDataStreamGenerator.this._signers) {
                aSN1EncodableVector.add(signerInformation.toASN1Structure());
            }
            this.k.getRawOutputStream().write(new DERSet(aSN1EncodableVector).getEncoded());
            this.k.close();
            this.j.close();
        }

        @Override // java.io.OutputStream
        public void write(int i) throws IOException {
            this.h.write(i);
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr) throws IOException {
            this.h.write(bArr);
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr, int i, int i2) throws IOException {
            this.h.write(bArr, i, i2);
        }
    }

    public final ASN1Integer a(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        boolean z;
        boolean z2;
        boolean z3;
        List list = this.certs;
        boolean z4 = false;
        if (list != null) {
            z = false;
            z2 = false;
            z3 = false;
            for (Object obj : list) {
                if (obj instanceof ASN1TaggedObject) {
                    ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject) obj;
                    if (aSN1TaggedObject.getTagNo() == 1) {
                        z2 = true;
                    } else if (aSN1TaggedObject.getTagNo() == 2) {
                        z3 = true;
                    } else if (aSN1TaggedObject.getTagNo() == 3) {
                        z = true;
                    }
                }
            }
        } else {
            z = false;
            z2 = false;
            z3 = false;
        }
        if (z) {
            return new ASN1Integer(5L);
        }
        List<Object> list2 = this.crls;
        if (list2 != null) {
            for (Object obj2 : list2) {
                if (obj2 instanceof ASN1TaggedObject) {
                    z4 = true;
                }
            }
        }
        if (z4) {
            return new ASN1Integer(5L);
        }
        if (z3) {
            return new ASN1Integer(4L);
        }
        if (!z2 && !b(this._signers, this.signerGens) && CMSObjectIdentifiers.data.equals(aSN1ObjectIdentifier)) {
            return new ASN1Integer(1L);
        }
        return new ASN1Integer(3L);
    }

    public final boolean b(List list, List list2) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            if (SignerInfo.getInstance(((SignerInformation) it.next()).toASN1Structure()).getVersion().getValue().intValue() == 3) {
                return true;
            }
        }
        Iterator it2 = list2.iterator();
        while (it2.hasNext()) {
            if (((SignerInfoGenerator) it2.next()).getGeneratedVersion() == 3) {
                return true;
            }
        }
        return false;
    }

    public OutputStream open(OutputStream outputStream) throws IOException {
        return open(outputStream, false);
    }

    public OutputStream open(OutputStream outputStream, boolean z) throws IOException {
        return open(CMSObjectIdentifiers.data, outputStream, z);
    }

    public OutputStream open(OutputStream outputStream, boolean z, OutputStream outputStream2) throws IOException {
        return open(CMSObjectIdentifiers.data, outputStream, z, outputStream2);
    }

    public OutputStream open(ASN1ObjectIdentifier aSN1ObjectIdentifier, OutputStream outputStream, boolean z) throws IOException {
        return open(aSN1ObjectIdentifier, outputStream, z, null);
    }

    public OutputStream open(ASN1ObjectIdentifier aSN1ObjectIdentifier, OutputStream outputStream, boolean z, OutputStream outputStream2) throws IOException {
        BERSequenceGenerator bERSequenceGenerator = new BERSequenceGenerator(outputStream);
        bERSequenceGenerator.addObject(CMSObjectIdentifiers.signedData);
        BERSequenceGenerator bERSequenceGenerator2 = new BERSequenceGenerator(bERSequenceGenerator.getRawOutputStream(), 0, true);
        bERSequenceGenerator2.addObject(a(aSN1ObjectIdentifier));
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        for (SignerInformation signerInformation : this._signers) {
            aSN1EncodableVector.add(f.f14565a.b(signerInformation.getDigestAlgorithmID()));
        }
        for (SignerInfoGenerator signerInfoGenerator : this.signerGens) {
            aSN1EncodableVector.add(signerInfoGenerator.getDigestAlgorithm());
        }
        bERSequenceGenerator2.getRawOutputStream().write(new DERSet(aSN1EncodableVector).getEncoded());
        BERSequenceGenerator bERSequenceGenerator3 = new BERSequenceGenerator(bERSequenceGenerator2.getRawOutputStream());
        bERSequenceGenerator3.addObject(aSN1ObjectIdentifier);
        return new a(g.b(this.signerGens, g.k(outputStream2, z ? g.c(bERSequenceGenerator3.getRawOutputStream(), 0, true, this.h) : null)), aSN1ObjectIdentifier, bERSequenceGenerator, bERSequenceGenerator2, bERSequenceGenerator3);
    }

    public void setBufferSize(int i) {
        this.h = i;
    }
}
