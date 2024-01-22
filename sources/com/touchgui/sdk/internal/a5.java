package com.touchgui.sdk.internal;

import android.os.Build;
import com.touchgui.sdk.TGLogger;
import com.touchgui.sdk.bean.TGSleepData;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import kotlin.UShort;
/* loaded from: classes12.dex */
public final class a5 extends x8 {
    public final boolean e;
    public TGSleepData f;
    public ArrayList g;

    public a5(byte b, boolean z) {
        super((byte) 8, b, 1);
        this.e = z;
    }

    @Override // com.touchgui.sdk.internal.z8
    public final byte[] a(byte b, byte b2, int i) {
        return new byte[]{b, b2, 1};
    }

    @Override // com.touchgui.sdk.internal.d8
    public final Object b() {
        return this.f;
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
            if (this.f == null) {
                this.f = new TGSleepData();
            }
            this.f.setDate(Build.VERSION.SDK_INT >= 26 ? new Calendar.Builder().setDate(i3, b - 1, b2).build().getTime() : new Date(i3 - 1900, b - 1, b2));
            this.f.setEndHour(wrap.get() & 255);
            this.f.setEndMinute(wrap.get() & 255);
            int i4 = wrap.getShort() & UShort.MAX_VALUE;
            if (this.e) {
                this.f.setTotalMinute(i4);
            } else {
                this.f.setTotalMinute(i4);
                this.f.setSleepMinute(i4);
            }
            this.f.setItemCount(wrap.get() & 255);
            this.f.setPacketCount(wrap.get() & 255);
            return;
        }
        if (2 == i) {
            TGSleepData tGSleepData = this.f;
            if (tGSleepData != null) {
                tGSleepData.setLightCount(wrap.get() & 255);
                this.f.setDeepCount(wrap.get() & 255);
                this.f.setWakeCount(wrap.get() & 255);
                this.f.setLightMinute(wrap.getShort() & UShort.MAX_VALUE);
                this.f.setDeepMinute(wrap.getShort() & UShort.MAX_VALUE);
                this.f.setSleepScore(wrap.get() & 255);
                this.f.setEyeMoveCount(wrap.get() & 255);
                this.f.setEyeMoveMinute(wrap.getShort() & UShort.MAX_VALUE);
                if (this.e) {
                    this.f.setSleepMinute(this.f.getEyeMoveMinute() + this.f.getDeepMinute() + this.f.getLightMinute());
                    return;
                }
                return;
            }
            str = "no data!!!";
        } else if (this.f != null) {
            if (this.g == null) {
                ArrayList arrayList = new ArrayList();
                this.g = arrayList;
                this.f.setItems(arrayList);
            }
            for (int i5 = 4; i5 < Math.min(i2 + 4, bArr.length) && this.g.size() < this.f.getItemCount(); i5 += 2) {
                if (wrap.position() + 2 > wrap.array().length) {
                    TGLogger.e(String.format("error data:%02x%02x", Byte.valueOf(this.f13852a), Byte.valueOf(this.b)));
                    return;
                }
                TGSleepData.ItemBean itemBean = new TGSleepData.ItemBean();
                itemBean.setStatus(wrap.get() & 255);
                itemBean.setDuration(wrap.get() & 255);
                this.g.add(itemBean);
            }
            return;
        } else {
            str = "error data!!!";
        }
        TGLogger.w(str);
    }
}
