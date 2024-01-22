package org.bouncycastle.cms.bc;

import java.io.OutputStream;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cms.CMSAlgorithm;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.StreamCipher;
import org.bouncycastle.crypto.io.CipherOutputStream;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.operator.GenericKey;
import org.bouncycastle.operator.OutputEncryptor;
import org.bouncycastle.util.Integers;
/* loaded from: classes12.dex */
public class BcCMSContentEncryptorBuilder {
    public static Map e;

    /* renamed from: a  reason: collision with root package name */
    public final ASN1ObjectIdentifier f14554a;
    public final int b;
    public b c;
    public SecureRandom d;

    /* loaded from: classes12.dex */
    public class a implements OutputEncryptor {

        /* renamed from: a  reason: collision with root package name */
        public KeyParameter f14555a;
        public AlgorithmIdentifier b;
        public Object c;

        public a(BcCMSContentEncryptorBuilder bcCMSContentEncryptorBuilder, ASN1ObjectIdentifier aSN1ObjectIdentifier, int i, SecureRandom secureRandom) throws CMSException {
            secureRandom = secureRandom == null ? new SecureRandom() : secureRandom;
            this.f14555a = new KeyParameter(bcCMSContentEncryptorBuilder.c.d(aSN1ObjectIdentifier, secureRandom).generateKey());
            this.b = bcCMSContentEncryptorBuilder.c.g(aSN1ObjectIdentifier, this.f14555a, secureRandom);
            b unused = bcCMSContentEncryptorBuilder.c;
            this.c = b.c(true, this.f14555a, this.b);
        }

        @Override // org.bouncycastle.operator.OutputEncryptor
        public AlgorithmIdentifier getAlgorithmIdentifier() {
            return this.b;
        }

        @Override // org.bouncycastle.operator.OutputEncryptor
        public GenericKey getKey() {
            return new GenericKey(this.b, this.f14555a.getKey());
        }

        @Override // org.bouncycastle.operator.OutputEncryptor
        public OutputStream getOutputStream(OutputStream outputStream) {
            return this.c instanceof BufferedBlockCipher ? new CipherOutputStream(outputStream, (BufferedBlockCipher) this.c) : new CipherOutputStream(outputStream, (StreamCipher) this.c);
        }
    }

    static {
        HashMap hashMap = new HashMap();
        e = hashMap;
        hashMap.put(CMSAlgorithm.AES128_CBC, Integers.valueOf(128));
        e.put(CMSAlgorithm.AES192_CBC, Integers.valueOf(192));
        e.put(CMSAlgorithm.AES256_CBC, Integers.valueOf(256));
        e.put(CMSAlgorithm.CAMELLIA128_CBC, Integers.valueOf(128));
        e.put(CMSAlgorithm.CAMELLIA192_CBC, Integers.valueOf(192));
        e.put(CMSAlgorithm.CAMELLIA256_CBC, Integers.valueOf(256));
    }

    public BcCMSContentEncryptorBuilder(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        this(aSN1ObjectIdentifier, b(aSN1ObjectIdentifier));
    }

    public BcCMSContentEncryptorBuilder(ASN1ObjectIdentifier aSN1ObjectIdentifier, int i) {
        this.c = new b();
        this.f14554a = aSN1ObjectIdentifier;
        this.b = i;
    }

    public static int b(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        Integer num = (Integer) e.get(aSN1ObjectIdentifier);
        if (num != null) {
            return num.intValue();
        }
        return -1;
    }

    public OutputEncryptor build() throws CMSException {
        return new a(this, this.f14554a, this.b, this.d);
    }

    public BcCMSContentEncryptorBuilder setSecureRandom(SecureRandom secureRandom) {
        this.d = secureRandom;
        return this;
    }
}
