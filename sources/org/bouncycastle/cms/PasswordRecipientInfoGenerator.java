package org.bouncycastle.cms;

import java.security.SecureRandom;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.cms.PasswordRecipientInfo;
import org.bouncycastle.asn1.cms.RecipientInfo;
import org.bouncycastle.asn1.pkcs.PBKDF2Params;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cms.PasswordRecipient;
import org.bouncycastle.operator.GenericKey;
import org.bouncycastle.util.Arrays;
/* loaded from: classes12.dex */
public abstract class PasswordRecipientInfoGenerator implements RecipientInfoGenerator {

    /* renamed from: a  reason: collision with root package name */
    public AlgorithmIdentifier f14543a;
    public ASN1ObjectIdentifier b;
    public SecureRandom c;
    public int d;
    public int e;
    public int f;
    public PasswordRecipient.PRF g;
    public byte[] h;
    public int i;
    public char[] password;

    public PasswordRecipientInfoGenerator(ASN1ObjectIdentifier aSN1ObjectIdentifier, char[] cArr) {
        this(aSN1ObjectIdentifier, cArr, a(aSN1ObjectIdentifier), ((Integer) PasswordRecipientInformation.f.get(aSN1ObjectIdentifier)).intValue());
    }

    public PasswordRecipientInfoGenerator(ASN1ObjectIdentifier aSN1ObjectIdentifier, char[] cArr, int i, int i2) {
        this.password = cArr;
        this.d = 1;
        this.b = aSN1ObjectIdentifier;
        this.e = i;
        this.f = i2;
        this.g = PasswordRecipient.PRF.HMacSHA1;
        this.i = 1024;
    }

    public static int a(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        Integer num = (Integer) PasswordRecipientInformation.e.get(aSN1ObjectIdentifier);
        if (num != null) {
            return num.intValue();
        }
        throw new IllegalArgumentException("cannot find key size for algorithm: " + aSN1ObjectIdentifier);
    }

    public abstract byte[] calculateDerivedKey(int i, AlgorithmIdentifier algorithmIdentifier, int i2) throws CMSException;

    @Override // org.bouncycastle.cms.RecipientInfoGenerator
    public RecipientInfo generate(GenericKey genericKey) throws CMSException {
        byte[] bArr = new byte[this.f];
        if (this.c == null) {
            this.c = new SecureRandom();
        }
        this.c.nextBytes(bArr);
        if (this.h == null) {
            byte[] bArr2 = new byte[20];
            this.h = bArr2;
            this.c.nextBytes(bArr2);
        }
        AlgorithmIdentifier algorithmIdentifier = new AlgorithmIdentifier(PKCSObjectIdentifiers.id_PBKDF2, new PBKDF2Params(this.h, this.i, this.g.b));
        this.f14543a = algorithmIdentifier;
        DEROctetString dEROctetString = new DEROctetString(generateEncryptedBytes(new AlgorithmIdentifier(this.b, new DEROctetString(bArr)), calculateDerivedKey(this.d, algorithmIdentifier, this.e), genericKey));
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.b);
        aSN1EncodableVector.add(new DEROctetString(bArr));
        return new RecipientInfo(new PasswordRecipientInfo(this.f14543a, new AlgorithmIdentifier(PKCSObjectIdentifiers.id_alg_PWRI_KEK, new DERSequence(aSN1EncodableVector)), dEROctetString));
    }

    public abstract byte[] generateEncryptedBytes(AlgorithmIdentifier algorithmIdentifier, byte[] bArr, GenericKey genericKey) throws CMSException;

    public PasswordRecipientInfoGenerator setPRF(PasswordRecipient.PRF prf) {
        this.g = prf;
        return this;
    }

    public PasswordRecipientInfoGenerator setPasswordConversionScheme(int i) {
        this.d = i;
        return this;
    }

    public PasswordRecipientInfoGenerator setSaltAndIterationCount(byte[] bArr, int i) {
        this.h = Arrays.clone(bArr);
        this.i = i;
        return this;
    }

    public PasswordRecipientInfoGenerator setSecureRandom(SecureRandom secureRandom) {
        this.c = secureRandom;
        return this;
    }
}
