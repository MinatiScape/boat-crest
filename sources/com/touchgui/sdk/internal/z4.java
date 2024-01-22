package com.touchgui.sdk.internal;

import com.touchgui.sdk.TGLogger;
import com.touchgui.sdk.bean.TGHeartRateData;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import kotlin.UShort;
/* loaded from: classes12.dex */
public final class z4 extends x8 {
    public TGHeartRateData e;
    public ArrayList f;

    public z4(byte b) {
        super((byte) 8, b, 1);
    }

    @Override // com.touchgui.sdk.internal.z8
    public final byte[] a(byte b, byte b2, int i) {
        return new byte[]{b, b2, 1};
    }

    @Override // com.touchgui.sdk.internal.d8
    public final Object b() {
        return this.e;
    }

    @Override // com.touchgui.sdk.internal.x8
    public final boolean c(byte[] bArr) {
        return bArr[0] == this.f13852a && bArr[1] == -18;
    }

    @Override // com.touchgui.sdk.internal.x8
    public final void d(byte[] bArr) {
        String str;
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        wrap.order(ByteOrder.LITTLE_ENDIAN);
        wrap.position(2);
        int i = wrap.get() & 255;
        int i2 = wrap.get() & 255;
        if (1 == i) {
            int i3 = wrap.getShort() & UShort.MAX_VALUE;
            byte b = wrap.get();
            byte b2 = wrap.get();
            if (i3 == 0 && b == 0 && b2 == 0) {
                return;
            }
            if (this.e == null) {
                this.e = new TGHeartRateData();
            }
            this.e.setDate(h2.a(i3, b, b2));
            this.e.setMinuteOffset(wrap.getShort() & UShort.MAX_VALUE);
            this.e.setSilentHr(wrap.get() & 255);
            this.e.setItemCount(wrap.getShort() & UShort.MAX_VALUE);
            this.e.setPacketCount(wrap.get() & 255);
            return;
        }
        if (2 == i) {
            TGHeartRateData tGHeartRateData = this.e;
            if (tGHeartRateData != null) {
                tGHeartRateData.setBurnFatThreshold(wrap.get() & 255);
                this.e.setAerobicThreshold(wrap.get() & 255);
                this.e.setLimitThreshold(wrap.get() & 255);
                this.e.setBurnFatMinutes(wrap.getShort() & UShort.MAX_VALUE);
                this.e.setAerobicMinutes(wrap.getShort() & UShort.MAX_VALUE);
                this.e.setLimitMinutes(wrap.getShort() & UShort.MAX_VALUE);
                this.e.setWarmUpThreshold(wrap.get() & 255);
                this.e.setWarmUpMinutes(wrap.getShort() & UShort.MAX_VALUE);
                this.e.setAnaerobicThreshold(wrap.get() & 255);
                this.e.setAnaerobicMinutes(wrap.getShort() & UShort.MAX_VALUE);
                return;
            }
            str = "no data!!!";
        } else if (this.e != null) {
            if (this.f == null) {
                ArrayList arrayList = new ArrayList();
                this.f = arrayList;
                this.e.setItems(arrayList);
            }
            int i4 = 0;
            for (int i5 = 4; i5 < Math.min(bArr.length, i2 + 4) && this.f.size() < this.e.getItemCount(); i5 += 2) {
                if (wrap.position() + 2 > wrap.array().length) {
                    TGLogger.e(String.format("error data:%02x%02x", Byte.valueOf(this.f13852a), Byte.valueOf(this.b)));
                    return;
                }
                i4 += wrap.get() & 255;
                int i6 = wrap.get() & 255;
                if (i6 != 0) {
                    this.f.add(new TGHeartRateData.ItemBean(i4, i6));
                    i4 = 0;
                }
            }
            return;
        } else {
            str = "error data!!!";
        }
        TGLogger.w(str);
    }
}
