package org.bouncycastle.crypto;
/* loaded from: classes5.dex */
public abstract class StreamBlockCipher implements BlockCipher, StreamCipher {

    /* renamed from: a  reason: collision with root package name */
    public final BlockCipher f14608a;

    public StreamBlockCipher(BlockCipher blockCipher) {
        this.f14608a = blockCipher;
    }

    public abstract byte calculateByte(byte b);

    public BlockCipher getUnderlyingCipher() {
        return this.f14608a;
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public int processBytes(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws DataLengthException {
        int i4 = i + i2;
        if (i4 <= bArr.length) {
            if (i3 + i2 <= bArr2.length) {
                while (i < i4) {
                    bArr2[i3] = calculateByte(bArr[i]);
                    i3++;
                    i++;
                }
                return i2;
            }
            throw new OutputLengthException("output buffer too short");
        }
        throw new DataLengthException("input buffer too small");
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public final byte returnByte(byte b) {
        return calculateByte(b);
    }
}
