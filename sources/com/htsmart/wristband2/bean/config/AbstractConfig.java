package com.htsmart.wristband2.bean.config;

import androidx.annotation.Nullable;
import com.htsmart.wristband2.bean.BytesEnabled;
import com.htsmart.wristband2.utils.Utils;
/* loaded from: classes11.dex */
public abstract class AbstractConfig implements BytesEnabled {
    public byte[] values;

    public AbstractConfig() {
        this(null);
    }

    public AbstractConfig(byte[] bArr) {
        int limitLength = limitLength();
        byte[] bArr2 = new byte[limitLength];
        this.values = bArr2;
        if (bArr == null || bArr.length < limitLength) {
            initDefault(bArr);
        } else {
            System.arraycopy(bArr, 0, bArr2, 0, limitLength);
        }
    }

    public static int adjustTime(int i) {
        if (i < 0) {
            return 0;
        }
        return Math.min(i, 1439);
    }

    public void a(int i, boolean z) {
        byte[] bArr = this.values;
        int length = (bArr.length - (i / 8)) - 1;
        int i2 = bArr[length] & 255;
        int i3 = 1 << (i % 8);
        if (z) {
            bArr[length] = (byte) Utils.addFlag(i2, i3);
        } else {
            bArr[length] = (byte) Utils.clearFlag(i2, i3);
        }
    }

    public boolean b(int i) {
        byte[] bArr = this.values;
        return Utils.isFlagEnable(bArr[(bArr.length - (i / 8)) - 1] & 255, 1 << (i % 8));
    }

    @Override // com.htsmart.wristband2.bean.BytesEnabled
    public byte[] getBytes() {
        return this.values;
    }

    public void initDefault(@Nullable byte[] bArr) {
    }

    public abstract int limitLength();
}
