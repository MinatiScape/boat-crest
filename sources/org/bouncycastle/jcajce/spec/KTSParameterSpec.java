package org.bouncycastle.jcajce.spec;

import java.security.spec.AlgorithmParameterSpec;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x9.X9ObjectIdentifiers;
import org.bouncycastle.util.Arrays;
/* loaded from: classes13.dex */
public class KTSParameterSpec implements AlgorithmParameterSpec {

    /* renamed from: a  reason: collision with root package name */
    public final String f15073a;
    public final int b;
    public final AlgorithmParameterSpec c;
    public final AlgorithmIdentifier d;
    public byte[] e;

    /* loaded from: classes13.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public final String f15074a;
        public final int b;
        public AlgorithmParameterSpec c;
        public AlgorithmIdentifier d;
        public byte[] e;

        public Builder(String str, int i) {
            this(str, i, null);
        }

        public Builder(String str, int i, byte[] bArr) {
            this.f15074a = str;
            this.b = i;
            this.d = new AlgorithmIdentifier(X9ObjectIdentifiers.id_kdf_kdf3, new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha256));
            this.e = bArr == null ? new byte[0] : Arrays.clone(bArr);
        }

        public KTSParameterSpec build() {
            return new KTSParameterSpec(this.f15074a, this.b, this.c, this.d, this.e);
        }

        public Builder withKdfAlgorithm(AlgorithmIdentifier algorithmIdentifier) {
            this.d = algorithmIdentifier;
            return this;
        }

        public Builder withParameterSpec(AlgorithmParameterSpec algorithmParameterSpec) {
            this.c = algorithmParameterSpec;
            return this;
        }
    }

    public KTSParameterSpec(String str, int i, AlgorithmParameterSpec algorithmParameterSpec, AlgorithmIdentifier algorithmIdentifier, byte[] bArr) {
        this.f15073a = str;
        this.b = i;
        this.c = algorithmParameterSpec;
        this.d = algorithmIdentifier;
        this.e = bArr;
    }

    public AlgorithmIdentifier getKdfAlgorithm() {
        return this.d;
    }

    public String getKeyAlgorithmName() {
        return this.f15073a;
    }

    public int getKeySize() {
        return this.b;
    }

    public byte[] getOtherInfo() {
        return Arrays.clone(this.e);
    }

    public AlgorithmParameterSpec getParameterSpec() {
        return this.c;
    }
}
