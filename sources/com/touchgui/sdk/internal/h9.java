package com.touchgui.sdk.internal;

import com.touchgui.sdk.TGLogger;
import com.touchgui.sdk.bean.TGWorkoutRecord;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import kotlin.UShort;
/* loaded from: classes12.dex */
public final class h9 extends x8 {
    public final int e;
    public final int f;
    public ByteBuffer g;
    public com.touchgui.sdk.bean.a h;
    public int i;

    public h9(int i, int i2) {
        super((byte) 32, (byte) 3, 4);
        this.i = 0;
        this.e = i;
        this.f = i2;
    }

    @Override // com.touchgui.sdk.internal.z8
    public final byte[] a(byte b, byte b2, int i) {
        return new byte[]{b, b2, (byte) this.e, 2};
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
        com.touchgui.sdk.bean.a aVar = new com.touchgui.sdk.bean.a();
        int i = 0;
        for (int i2 = 0; i2 < this.g.array().length; i2 += 8) {
            this.g.position(i2);
            int i3 = this.g.get() & 255;
            i += this.g.get() & 255;
            if (i3 == 1) {
                pb pbVar = new pb();
                pbVar.f13814a = i;
                pbVar.b = this.g.getShort();
                pbVar.c = this.g.getShort();
                pbVar.d = this.g.getShort();
                if (aVar.c == null) {
                    aVar.c = new ArrayList();
                }
                aVar.c.add(pbVar);
            } else if (i3 == 2) {
                TGWorkoutRecord.HeartRate heartRate = new TGWorkoutRecord.HeartRate(i, this.g.get() & 255);
                if (aVar.b == null) {
                    aVar.b = new ArrayList();
                }
                aVar.b.add(heartRate);
            } else if (i3 == 3 || i3 == 4) {
                TGWorkoutRecord.Event event = new TGWorkoutRecord.Event(i, this.g.get() & 255);
                if (aVar.f13732a == null) {
                    aVar.f13732a = new ArrayList();
                }
                aVar.f13732a.add(event);
            } else if (i3 == 5 || i3 == 6 || i3 == 7) {
                TGWorkoutRecord.Pace pace = new TGWorkoutRecord.Pace(i3, i, this.g.getShort() & UShort.MAX_VALUE);
                if (aVar.d == null) {
                    aVar.d = new ArrayList();
                }
                aVar.d.add(pace);
            } else if (i3 == 8) {
                TGWorkoutRecord.Rowing rowing = new TGWorkoutRecord.Rowing(i, this.g.getShort() & UShort.MAX_VALUE);
                if (aVar.e == null) {
                    aVar.e = new ArrayList();
                }
                aVar.e.add(rowing);
            }
        }
        this.h = aVar;
    }

    @Override // com.touchgui.sdk.internal.x8, com.touchgui.sdk.internal.z8, com.touchgui.sdk.internal.d8
    public final boolean a(byte[] bArr) {
        return super.a(bArr) || (bArr[0] == 33 && bArr[1] == 2);
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
