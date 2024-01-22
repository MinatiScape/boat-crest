package org.bouncycastle.operator.bc;

import java.io.IOException;
import java.io.OutputStream;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.operator.DigestCalculator;
import org.bouncycastle.operator.DigestCalculatorProvider;
import org.bouncycastle.operator.OperatorCreationException;
/* loaded from: classes13.dex */
public class BcDigestCalculatorProvider implements DigestCalculatorProvider {

    /* renamed from: a  reason: collision with root package name */
    public BcDigestProvider f15236a = BcDefaultDigestProvider.INSTANCE;

    /* loaded from: classes13.dex */
    public class a implements DigestCalculator {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ AlgorithmIdentifier f15237a;
        public final /* synthetic */ b b;

        public a(BcDigestCalculatorProvider bcDigestCalculatorProvider, AlgorithmIdentifier algorithmIdentifier, b bVar) {
            this.f15237a = algorithmIdentifier;
            this.b = bVar;
        }

        @Override // org.bouncycastle.operator.DigestCalculator
        public AlgorithmIdentifier getAlgorithmIdentifier() {
            return this.f15237a;
        }

        @Override // org.bouncycastle.operator.DigestCalculator
        public byte[] getDigest() {
            return this.b.a();
        }

        @Override // org.bouncycastle.operator.DigestCalculator
        public OutputStream getOutputStream() {
            return this.b;
        }
    }

    /* loaded from: classes13.dex */
    public class b extends OutputStream {
        public Digest h;

        public b(BcDigestCalculatorProvider bcDigestCalculatorProvider, Digest digest) {
            this.h = digest;
        }

        public byte[] a() {
            byte[] bArr = new byte[this.h.getDigestSize()];
            this.h.doFinal(bArr, 0);
            return bArr;
        }

        @Override // java.io.OutputStream
        public void write(int i) throws IOException {
            this.h.update((byte) i);
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr) throws IOException {
            this.h.update(bArr, 0, bArr.length);
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr, int i, int i2) throws IOException {
            this.h.update(bArr, i, i2);
        }
    }

    @Override // org.bouncycastle.operator.DigestCalculatorProvider
    public DigestCalculator get(AlgorithmIdentifier algorithmIdentifier) throws OperatorCreationException {
        return new a(this, algorithmIdentifier, new b(this, this.f15236a.get(algorithmIdentifier)));
    }
}
