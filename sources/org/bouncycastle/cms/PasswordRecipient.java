package org.bouncycastle.cms;

import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
/* loaded from: classes12.dex */
public interface PasswordRecipient extends Recipient {
    public static final int PKCS5_SCHEME2 = 0;
    public static final int PKCS5_SCHEME2_UTF8 = 1;

    /* loaded from: classes12.dex */
    public static final class PRF {
        public static final PRF HMacSHA1;
        public static final PRF HMacSHA224;
        public static final PRF HMacSHA256;
        public static final PRF HMacSHA384;
        public static final PRF HMacSHA512;

        /* renamed from: a  reason: collision with root package name */
        public final String f14542a;
        public final AlgorithmIdentifier b;

        static {
            ASN1ObjectIdentifier aSN1ObjectIdentifier = PKCSObjectIdentifiers.id_hmacWithSHA1;
            DERNull dERNull = DERNull.INSTANCE;
            HMacSHA1 = new PRF("HMacSHA1", new AlgorithmIdentifier(aSN1ObjectIdentifier, dERNull));
            HMacSHA224 = new PRF("HMacSHA224", new AlgorithmIdentifier(PKCSObjectIdentifiers.id_hmacWithSHA224, dERNull));
            HMacSHA256 = new PRF("HMacSHA256", new AlgorithmIdentifier(PKCSObjectIdentifiers.id_hmacWithSHA256, dERNull));
            HMacSHA384 = new PRF("HMacSHA384", new AlgorithmIdentifier(PKCSObjectIdentifiers.id_hmacWithSHA384, dERNull));
            HMacSHA512 = new PRF("HMacSHA512", new AlgorithmIdentifier(PKCSObjectIdentifiers.id_hmacWithSHA512, dERNull));
        }

        public PRF(String str, AlgorithmIdentifier algorithmIdentifier) {
            this.f14542a = str;
            this.b = algorithmIdentifier;
        }

        public AlgorithmIdentifier getAlgorithmID() {
            return this.b;
        }

        public String getName() {
            return this.f14542a;
        }
    }

    byte[] calculateDerivedKey(int i, AlgorithmIdentifier algorithmIdentifier, int i2) throws CMSException;

    char[] getPassword();

    int getPasswordConversionScheme();

    RecipientOperator getRecipientOperator(AlgorithmIdentifier algorithmIdentifier, AlgorithmIdentifier algorithmIdentifier2, byte[] bArr, byte[] bArr2) throws CMSException;
}
