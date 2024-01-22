package com.touchgui.sdk.internal;

import android.os.Build;
import com.touchgui.sdk.bean.TGSportRecord;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import kotlin.UShort;
/* loaded from: classes12.dex */
public final class b9 extends x8 {
    public TGSportRecord e;
    public ArrayList f;

    public b9() {
        super((byte) 9, (byte) 4, 1);
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
        return bArr[0] == this.f13852a && bArr[1] == this.b && bArr[2] == 0;
    }

    @Override // com.touchgui.sdk.internal.x8
    public final void d(byte[] bArr) {
        ByteBuffer wrap = ByteBuffer.wrap(bArr, 2, bArr.length - 2);
        wrap.order(ByteOrder.LITTLE_ENDIAN);
        int i = wrap.get() & 255;
        if (i == 1) {
            if (this.e == null) {
                this.e = new TGSportRecord();
            }
            wrap.get();
            int i2 = (wrap.get() & 255) + 2000;
            byte b = wrap.get();
            byte b2 = wrap.get();
            Date time = Build.VERSION.SDK_INT >= 26 ? new Calendar.Builder().setDate(i2, b - 1, b2).build().getTime() : new Date(i2 - 1900, b - 1, b2);
            byte b3 = wrap.get();
            byte b4 = wrap.get();
            byte b5 = wrap.get();
            time.setHours(b3);
            time.setMinutes(b4);
            time.setSeconds(b5);
            this.e.setDate(time);
            this.e.setMinHr(wrap.get() & 255);
            this.e.setAvgPaceSecs(wrap.getShort() & UShort.MAX_VALUE);
            wrap.getShort();
            wrap.getShort();
        } else if (i == 2) {
            wrap.get();
            this.e.setType(wrap.get() & 255);
            this.e.setStep(wrap.getInt());
            this.e.setDuration(wrap.getShort() & UShort.MAX_VALUE);
            this.e.setCalories(wrap.getShort() & UShort.MAX_VALUE);
            this.e.setDistance(wrap.getInt());
        } else if (i != 3) {
            if (this.f == null) {
                ArrayList arrayList = new ArrayList();
                this.f = arrayList;
                this.e.setHearts(arrayList);
            }
            int i3 = wrap.get() & 255;
            for (int i4 = 0; i4 < i3; i4++) {
                TGSportRecord.HeartRateItem heartRateItem = new TGSportRecord.HeartRateItem();
                heartRateItem.setHeartrate(wrap.get() & 255);
                this.f.add(heartRateItem);
            }
        } else {
            wrap.get();
            this.e.setAvgHr(wrap.get() & 255);
            this.e.setMaxHr(wrap.get() & 255);
            this.e.setWarmUp(wrap.get() & 255);
            this.e.setFatBurning(wrap.get() & 255);
            this.e.setAerobicExercise(wrap.get() & 255);
            this.e.setAnaerobicExercise(wrap.get() & 255);
            this.e.setExtremeExercise(wrap.get() & 255);
            this.e.setAvgStrideFrequency(wrap.getShort() & UShort.MAX_VALUE);
            this.e.setAvgStrideLength(wrap.get() & 255);
            this.e.setAvgSpeed((wrap.getShort() & UShort.MAX_VALUE) * 10);
            this.e.setMaxSpeed((wrap.getShort() & UShort.MAX_VALUE) * 10);
            this.e.setMinSpeed((wrap.getShort() & UShort.MAX_VALUE) * 10);
        }
    }
}
