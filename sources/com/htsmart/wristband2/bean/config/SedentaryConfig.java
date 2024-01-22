package com.htsmart.wristband2.bean.config;

import androidx.annotation.Nullable;
import com.htsmart.wristband2.utils.Utils;
/* loaded from: classes11.dex */
public class SedentaryConfig extends AbstractConfig {
    public static final int PACKET_LENGTH_V1 = 5;
    public static final int PACKET_LENGTH_V2 = 7;

    public SedentaryConfig() {
    }

    public SedentaryConfig(byte[] bArr) {
        super(bArr);
    }

    public final int c(int i) {
        if (i < 10) {
            return 10;
        }
        return Math.min(i, 720);
    }

    public int getEnd() {
        byte[] bArr = this.values;
        return AbstractConfig.adjustTime((bArr[4] & 255) | ((bArr[3] & 255) << 8));
    }

    public int getInterval() {
        byte[] bArr = this.values;
        return c((bArr[6] & 255) | ((bArr[5] & 255) << 8));
    }

    public int getStart() {
        byte[] bArr = this.values;
        return AbstractConfig.adjustTime((bArr[2] & 255) | ((bArr[1] & 255) << 8));
    }

    @Override // com.htsmart.wristband2.bean.config.AbstractConfig
    public void initDefault(@Nullable byte[] bArr) {
        if (bArr == null || bArr.length <= 0) {
            return;
        }
        System.arraycopy(bArr, 0, this.values, 0, bArr.length);
    }

    public boolean isEnable() {
        return Utils.isFlagEnable(this.values[0] & 255, 1);
    }

    public boolean isNotDisturbEnable() {
        return Utils.isFlagEnable(this.values[0] & 255, 2);
    }

    @Override // com.htsmart.wristband2.bean.config.AbstractConfig
    public int limitLength() {
        return 7;
    }

    public void setEnable(boolean z) {
        byte[] bArr = this.values;
        int i = bArr[0] & 255;
        if (z) {
            bArr[0] = (byte) Utils.addFlag(i, 1);
        } else {
            bArr[0] = (byte) Utils.clearFlag(i, 1);
        }
    }

    public void setEnd(int i) {
        int adjustTime = AbstractConfig.adjustTime(i);
        byte[] bArr = this.values;
        bArr[3] = (byte) ((adjustTime >> 8) & 255);
        bArr[4] = (byte) (adjustTime & 255);
    }

    public void setInterval(int i) {
        int c = c(i);
        byte[] bArr = this.values;
        bArr[5] = (byte) ((c >> 8) & 255);
        bArr[6] = (byte) (c & 255);
    }

    public void setNotDisturbEnable(boolean z) {
        byte[] bArr = this.values;
        int i = bArr[0] & 255;
        if (z) {
            bArr[0] = (byte) Utils.addFlag(i, 2);
        } else {
            bArr[0] = (byte) Utils.clearFlag(i, 2);
        }
    }

    public void setStart(int i) {
        int adjustTime = AbstractConfig.adjustTime(i);
        byte[] bArr = this.values;
        bArr[1] = (byte) ((adjustTime >> 8) & 255);
        bArr[2] = (byte) (adjustTime & 255);
    }
}
