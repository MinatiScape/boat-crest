package com.htsmart.wristband2.bean.config;

import androidx.annotation.Nullable;
/* loaded from: classes11.dex */
public class SdkFunction extends AbstractConfig {
    public static final int PACKET_LENGTH = 4;

    @Override // com.htsmart.wristband2.bean.config.AbstractConfig
    public void initDefault(@Nullable byte[] bArr) {
        a(0, true);
    }

    @Override // com.htsmart.wristband2.bean.config.AbstractConfig
    public int limitLength() {
        return 4;
    }
}
