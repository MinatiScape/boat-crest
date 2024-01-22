package org.bouncycastle.crypto.modes;

import com.coveiot.sdk.ble.api.BleUUID;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.StreamBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.crypto.params.ParametersWithSBox;
/* loaded from: classes13.dex */
public class GCFBBlockCipher extends StreamBlockCipher {
    public static final byte[] f = {105, 0, 114, 34, 100, -55, 4, 35, -115, 58, -37, BleUUID.CMD_ID_96, com.htsmart.wristband2.a.a.a.U0, -23, 42, -60, 24, -2, -84, -108, 0, -19, 7, 18, -64, -122, -36, -62, -17, com.htsmart.wristband2.a.a.a.a1, -87, 43};
    public final CFBBlockCipher b;
    public KeyParameter c;
    public long d;
    public boolean e;

    public GCFBBlockCipher(BlockCipher blockCipher) {
        super(blockCipher);
        this.d = 0L;
        this.b = new CFBBlockCipher(blockCipher, blockCipher.getBlockSize() * 8);
    }

    @Override // org.bouncycastle.crypto.StreamBlockCipher
    public byte calculateByte(byte b) {
        long j = this.d;
        if (j > 0 && j % 1024 == 0) {
            BlockCipher underlyingCipher = this.b.getUnderlyingCipher();
            underlyingCipher.init(false, this.c);
            byte[] bArr = new byte[32];
            byte[] bArr2 = f;
            underlyingCipher.processBlock(bArr2, 0, bArr, 0);
            underlyingCipher.processBlock(bArr2, 8, bArr, 8);
            underlyingCipher.processBlock(bArr2, 16, bArr, 16);
            underlyingCipher.processBlock(bArr2, 24, bArr, 24);
            KeyParameter keyParameter = new KeyParameter(bArr);
            this.c = keyParameter;
            underlyingCipher.init(true, keyParameter);
            byte[] currentIV = this.b.getCurrentIV();
            underlyingCipher.processBlock(currentIV, 0, currentIV, 0);
            this.b.init(this.e, new ParametersWithIV(this.c, currentIV));
        }
        this.d++;
        return this.b.calculateByte(b);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        String algorithmName = this.b.getAlgorithmName();
        return algorithmName.substring(0, algorithmName.indexOf(47)) + "/G" + algorithmName.substring(algorithmName.indexOf(47) + 1);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return this.b.getBlockSize();
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        this.d = 0L;
        this.b.init(z, cipherParameters);
        this.e = z;
        if (cipherParameters instanceof ParametersWithIV) {
            cipherParameters = ((ParametersWithIV) cipherParameters).getParameters();
        }
        if (cipherParameters instanceof ParametersWithRandom) {
            cipherParameters = ((ParametersWithRandom) cipherParameters).getParameters();
        }
        if (cipherParameters instanceof ParametersWithSBox) {
            cipherParameters = ((ParametersWithSBox) cipherParameters).getParameters();
        }
        this.c = (KeyParameter) cipherParameters;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) throws DataLengthException, IllegalStateException {
        processBytes(bArr, i, this.b.getBlockSize(), bArr2, i2);
        return this.b.getBlockSize();
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
        this.d = 0L;
        this.b.reset();
    }
}
