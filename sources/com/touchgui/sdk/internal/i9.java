package com.touchgui.sdk.internal;

import com.touchgui.sdk.TGLogger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Calendar;
import java.util.TimeZone;
import kotlin.UShort;
/* loaded from: classes12.dex */
public final class i9 extends x8 {
    public final int e;
    public final int f;
    public ByteBuffer g;
    public qb h;
    public int i;

    public i9(int i, int i2) {
        super((byte) 32, (byte) 3, 4);
        this.i = 0;
        this.e = i;
        this.f = i2;
    }

    @Override // com.touchgui.sdk.internal.z8
    public final byte[] a(byte b, byte b2, int i) {
        return new byte[]{b, b2, (byte) this.e, 1};
    }

    @Override // com.touchgui.sdk.internal.d8
    public final Object b() {
        return this.h;
    }

    @Override // com.touchgui.sdk.internal.x8
    public final boolean c(byte[] bArr) {
        return bArr[0] == this.f13852a && bArr[1] == this.b;
    }

    @Override // com.touchgui.sdk.internal.x8
    public final void d() {
        this.g.position(0);
        qb qbVar = new qb();
        this.g.getShort();
        qbVar.setType(this.g.get() & 255);
        int i = this.g.getInt();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(i * 1000);
        calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
        qbVar.setDate(h2.a(calendar.get(1), calendar.get(2) + 1, calendar.get(5), calendar.get(11), calendar.get(12), calendar.get(13)));
        this.g.getInt();
        qbVar.setDuration(this.g.getInt());
        qbVar.f13820a = this.g.getInt();
        qbVar.b = this.g.getInt();
        qbVar.c = this.g.getInt();
        qbVar.setCalories(this.g.getInt());
        qbVar.setDistance(this.g.getInt());
        qbVar.setStep(this.g.getInt());
        qbVar.setAvgHr(this.g.get() & 255);
        qbVar.setMaxHr(this.g.get() & 255);
        qbVar.setMinHr(this.g.get() & 255);
        qbVar.setAvgSpeed((this.g.getShort() & UShort.MAX_VALUE) * 10);
        qbVar.setMaxSpeed((this.g.getShort() & UShort.MAX_VALUE) * 10);
        qbVar.setMinSpeed((this.g.getShort() & UShort.MAX_VALUE) * 10);
        qbVar.setAvgPaceSecs(this.g.getShort() & UShort.MAX_VALUE);
        qbVar.setMaxPace(this.g.getShort() & UShort.MAX_VALUE);
        qbVar.setMinPace(this.g.getShort() & UShort.MAX_VALUE);
        qbVar.setAvgStrideFrequency(this.g.getInt());
        qbVar.setAvgStrideLength(this.g.getInt());
        qbVar.setPaddleNum(this.g.getShort() & UShort.MAX_VALUE);
        qbVar.setPaddleFrq(this.g.get() & 255);
        qbVar.setBoxingNum(this.g.getShort() & UShort.MAX_VALUE);
        qbVar.setAvgSkipFrq(this.g.getShort() & UShort.MAX_VALUE);
        qbVar.setSkipNum(this.g.getShort() & UShort.MAX_VALUE);
        qbVar.setDumbbellNum(this.g.getShort() & UShort.MAX_VALUE);
        qbVar.setRelax(this.g.getShort() & UShort.MAX_VALUE);
        qbVar.setWarmUp(this.g.getShort() & UShort.MAX_VALUE);
        qbVar.setFatBurning(this.g.getShort() & UShort.MAX_VALUE);
        qbVar.setAerobicExercise(this.g.getShort() & UShort.MAX_VALUE);
        qbVar.setAnaerobicExercise(this.g.getShort() & UShort.MAX_VALUE);
        qbVar.setExtremeExercise(this.g.getShort() & UShort.MAX_VALUE);
        this.h = qbVar;
    }

    @Override // com.touchgui.sdk.internal.x8, com.touchgui.sdk.internal.z8, com.touchgui.sdk.internal.d8
    public final boolean a(byte[] bArr) {
        return super.a(bArr) || (bArr[0] == 33 && bArr[1] == 1);
    }

    @Override // com.touchgui.sdk.internal.x8
    public final void d(byte[] bArr) {
        if (this.g == null) {
            ByteBuffer allocate = ByteBuffer.allocate(this.f);
            this.g = allocate;
            allocate.order(ByteOrder.LITTLE_ENDIAN);
        }
        if (this.g == null || c(bArr)) {
            return;
        }
        int b = s.b(bArr, 2, 4);
        if (this.i + 1 != b) {
            TGLogger.e("miss data, prevIndex=" + this.i + ", index=" + b);
        }
        this.i = b;
        this.g.put(bArr, 6, Math.min(this.f - this.g.position(), bArr.length - 6));
    }
}
