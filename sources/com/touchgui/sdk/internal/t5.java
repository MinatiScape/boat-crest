package com.touchgui.sdk.internal;

import com.touchgui.sdk.TGLogger;
import com.touchgui.sdk.bean.TGIotFeatureSetFunction;
import com.touchgui.sdk.bean.TGIotFluctuateFunction;
import com.touchgui.sdk.bean.TGIotOnlineFunction;
import com.touchgui.sdk.bean.TGIotSwitchFunction;
import com.touchgui.sdk.bean.TGIotTextFunction;
import com.touchgui.sdk.bean.TGSyncIotDevice;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import kotlin.UShort;
/* loaded from: classes12.dex */
public final class t5 extends h5 {
    public t5() {
        super((short) 18);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v3, types: [com.touchgui.sdk.bean.TGIotSwitchFunction, com.touchgui.sdk.bean.TGIotFunction] */
    /* JADX WARN: Type inference failed for: r6v4, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r6v5, types: [com.touchgui.sdk.bean.TGIotTextFunction, com.touchgui.sdk.bean.TGIotFunction] */
    /* JADX WARN: Type inference failed for: r6v6, types: [com.touchgui.sdk.bean.TGIotOnlineFunction, com.touchgui.sdk.bean.TGIotFunction] */
    /* JADX WARN: Type inference failed for: r6v7, types: [com.touchgui.sdk.bean.TGIotFunction, com.touchgui.sdk.bean.TGIotFluctuateFunction] */
    /* JADX WARN: Type inference failed for: r6v8, types: [com.touchgui.sdk.bean.TGIotFeatureSetFunction, com.touchgui.sdk.bean.TGIotFunction] */
    /* JADX WARN: Type inference failed for: r6v9 */
    @Override // com.touchgui.sdk.internal.h5
    public final Object c(byte[] bArr) {
        ?? tGIotSwitchFunction;
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        wrap.order(ByteOrder.LITTLE_ENDIAN);
        wrap.position(11);
        TGSyncIotDevice tGSyncIotDevice = new TGSyncIotDevice();
        tGSyncIotDevice.setOperateType(wrap.get() & 255);
        tGSyncIotDevice.setTotalCount(wrap.get() & 255);
        tGSyncIotDevice.setCurrentIndex(wrap.get() & 255);
        int i = wrap.get() & 255;
        byte[] bArr2 = new byte[6];
        wrap.get(bArr2, 0, 6);
        tGSyncIotDevice.setIconName(new String(bArr2));
        byte[] bArr3 = new byte[64];
        wrap.get(bArr3, 0, 64);
        tGSyncIotDevice.setIconName(new String(bArr3));
        byte[] bArr4 = new byte[64];
        wrap.get(bArr4, 0, 64);
        tGSyncIotDevice.setIconName(new String(bArr4));
        tGSyncIotDevice.setDeviceType(wrap.get() & 255);
        if (i > 0) {
            ArrayList arrayList = new ArrayList();
            for (int i2 = 0; i2 < i; i2++) {
                int i3 = wrap.get() & 255;
                if (i3 == 1) {
                    tGIotSwitchFunction = new TGIotSwitchFunction();
                    f5.a(tGIotSwitchFunction, wrap);
                    tGIotSwitchFunction.setEnable((wrap.get() & 255) == 1);
                } else if (i3 == 2) {
                    tGIotSwitchFunction = new TGIotTextFunction();
                    f5.a(tGIotSwitchFunction, wrap);
                } else if (i3 == 3) {
                    tGIotSwitchFunction = new TGIotOnlineFunction();
                    f5.a(tGIotSwitchFunction, wrap);
                    tGIotSwitchFunction.setOnline((wrap.get() & 255) == 1);
                } else if (i3 == 4) {
                    tGIotSwitchFunction = new TGIotFluctuateFunction();
                    f5.a(tGIotSwitchFunction, wrap);
                    tGIotSwitchFunction.setMaxValue(wrap.getShort() & UShort.MAX_VALUE);
                    tGIotSwitchFunction.setMinValue(wrap.getShort() & UShort.MAX_VALUE);
                    tGIotSwitchFunction.setStepValue(wrap.getShort() & UShort.MAX_VALUE);
                    tGIotSwitchFunction.setCurrentValue(wrap.getShort() & UShort.MAX_VALUE);
                } else if (i3 != 5) {
                    tGIotSwitchFunction = 0;
                } else {
                    tGIotSwitchFunction = new TGIotFeatureSetFunction();
                    f5.a(tGIotSwitchFunction, wrap);
                    tGIotSwitchFunction.setCurrentIndex(wrap.get() & 255);
                    int i4 = wrap.get() & 255;
                    int i5 = wrap.getShort() & UShort.MAX_VALUE;
                    byte[] bArr5 = new byte[i5];
                    wrap.get(bArr5, 0, i5);
                    ArrayList arrayList2 = new ArrayList();
                    int i6 = 0;
                    for (int i7 = 0; i7 < i5; i7++) {
                        if (bArr5[i7] == 0 && i6 < i7) {
                            TGIotFeatureSetFunction.IotFeature iotFeature = new TGIotFeatureSetFunction.IotFeature();
                            iotFeature.setName(new String(bArr5, i6, i7));
                            arrayList2.add(iotFeature);
                            i6 = i7 + 1;
                        }
                    }
                    int i8 = 65535 & wrap.getShort();
                    byte[] bArr6 = new byte[i8];
                    wrap.get(bArr6, 0, i8);
                    int i9 = 0;
                    int i10 = 0;
                    for (int i11 = 0; i11 < i8; i11++) {
                        if (bArr6[i11] == 0 && i9 < i11) {
                            ((TGIotFeatureSetFunction.IotFeature) arrayList2.get(i10)).setValue(new String(bArr6, i9, i11));
                            i9 = i11 + 1;
                            i10++;
                        }
                    }
                    if (arrayList2.size() != i4) {
                        TGLogger.w("features size:" + arrayList2.size() + " != " + i4);
                    }
                    tGIotSwitchFunction.setFeatures(arrayList2);
                }
                arrayList.add(tGIotSwitchFunction);
            }
            tGSyncIotDevice.setFunctions(arrayList);
        }
        return tGSyncIotDevice;
    }

    @Override // com.touchgui.sdk.internal.h5
    public final byte[] e() {
        return new byte[0];
    }
}
