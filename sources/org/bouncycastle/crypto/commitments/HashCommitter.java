package org.bouncycastle.crypto.commitments;

import java.security.SecureRandom;
import org.bouncycastle.crypto.Commitment;
import org.bouncycastle.crypto.Committer;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.ExtendedDigest;
import org.bouncycastle.util.Arrays;
/* loaded from: classes10.dex */
public class HashCommitter implements Committer {

    /* renamed from: a  reason: collision with root package name */
    public final Digest f14632a;
    public final int b;
    public final SecureRandom c;

    public HashCommitter(ExtendedDigest extendedDigest, SecureRandom secureRandom) {
        this.f14632a = extendedDigest;
        this.b = extendedDigest.getByteLength();
        this.c = secureRandom;
    }

    public final byte[] a(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[this.f14632a.getDigestSize()];
        this.f14632a.update(bArr, 0, bArr.length);
        this.f14632a.update(bArr2, 0, bArr2.length);
        this.f14632a.doFinal(bArr3, 0);
        return bArr3;
    }

    @Override // org.bouncycastle.crypto.Committer
    public Commitment commit(byte[] bArr) {
        int length = bArr.length;
        int i = this.b;
        if (length <= i / 2) {
            byte[] bArr2 = new byte[i - bArr.length];
            this.c.nextBytes(bArr2);
            return new Commitment(bArr2, a(bArr2, bArr));
        }
        throw new DataLengthException("Message to be committed to too large for digest.");
    }

    @Override // org.bouncycastle.crypto.Committer
    public boolean isRevealed(Commitment commitment, byte[] bArr) {
        if (bArr.length + commitment.getSecret().length == this.b) {
            return Arrays.constantTimeAreEqual(commitment.getCommitment(), a(commitment.getSecret(), bArr));
        }
        throw new DataLengthException("Message and witness secret lengths do not match.");
    }
}
