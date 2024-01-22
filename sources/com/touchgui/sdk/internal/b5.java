package com.touchgui.sdk.internal;

import com.touchgui.sdk.TGLogger;
import com.touchgui.sdk.bean.TGStepData;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import kotlin.UShort;
/* loaded from: classes12.dex */
public final class b5 extends x8 {
    public TGStepData e;
    public ArrayList f;
    public int g;
    public int h;
    public boolean i;

    public b5(byte b) {
        super((byte) 8, b, 1);
        this.g = 0;
        this.h = 0;
        this.i = false;
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

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v2 */
    /* JADX WARN: Type inference failed for: r11v3, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r11v6 */
    @Override // com.touchgui.sdk.internal.x8
    public final void d(byte[] bArr) {
        String str;
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        wrap.order(ByteOrder.LITTLE_ENDIAN);
        wrap.position(2);
        boolean z = wrap.get() & 255;
        wrap.get();
        if (1 == z) {
            int i = wrap.getShort() & UShort.MAX_VALUE;
            byte b = wrap.get();
            byte b2 = wrap.get();
            if (i == 0 && b == 0 && b2 == 0) {
                return;
            }
            if (this.e == null) {
                this.e = new TGStepData();
            }
            this.e.setDate(h2.a(i, b, b2));
            this.e.setMinuteOffset(wrap.getShort() & UShort.MAX_VALUE);
            this.e.setPerMinute(wrap.get() & 255);
            this.e.setItemCount(wrap.get() & 255);
            this.e.setPacketCount(wrap.get() & 255);
            return;
        }
        if (2 == z) {
            TGStepData tGStepData = this.e;
            if (tGStepData != null) {
                tGStepData.setTotalSteps(wrap.getInt());
                this.e.setTotalCal(wrap.getInt());
                this.e.setTotalDistance(wrap.getInt());
                this.e.setTotalActiveTime(wrap.getInt());
                return;
            }
            str = "no data!!!";
        } else if (this.e != null) {
            if (this.f == null) {
                ArrayList arrayList = new ArrayList();
                this.f = arrayList;
                this.e.setItems(arrayList);
            }
            int i2 = 4;
            while (true) {
                i2 += 5;
                if (i2 >= bArr.length || this.f.size() >= this.e.getItemCount()) {
                    break;
                } else if (wrap.position() + 5 > wrap.array().length) {
                    TGLogger.e(String.format("error data:%02x%02x", Byte.valueOf(this.f13852a), Byte.valueOf(this.b)));
                    break;
                } else {
                    TGStepData.ItemBean itemBean = new TGStepData.ItemBean();
                    int i3 = wrap.getShort() & UShort.MAX_VALUE;
                    int i4 = wrap.getShort() & UShort.MAX_VALUE;
                    int i5 = wrap.get() & 255;
                    if (this.i) {
                        itemBean.setMode(0);
                    } else {
                        ?? r11 = (i3 & 3) > 0 ? 1 : 0;
                        this.i = r11;
                        itemBean.setMode(r11);
                        this.h += this.i ? 1 : 0;
                    }
                    if (this.i && (this.g + 1) % 4 == 0) {
                        this.i = false;
                    }
                    itemBean.setStepCount(((i3 & 16380) >> 2) & 4095);
                    itemBean.setActiveTime(((i3 & 49152) >> 14) | ((i4 & 3) << 2));
                    itemBean.setCalories((i4 & 4092) >> 2);
                    itemBean.setDistance((i5 << 4) | ((61440 & i4) >> 12));
                    this.f.add(itemBean);
                    this.g++;
                }
            }
            this.e.setStandCount(this.h);
            return;
        } else {
            str = "error data!!!";
        }
        TGLogger.w(str);
    }
}
