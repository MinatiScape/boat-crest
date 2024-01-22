package com.touchgui.sdk.internal;

import android.text.TextUtils;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
/* loaded from: classes12.dex */
public final class a6 extends h5 {
    public final /* synthetic */ String l;
    public final /* synthetic */ boolean m;
    public final /* synthetic */ String n;
    public final /* synthetic */ String o;
    public final /* synthetic */ int p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public a6(String str, boolean z, String str2, String str3, int i) {
        super((short) 11);
        this.l = str;
        this.m = z;
        this.n = str2;
        this.o = str3;
        this.p = i;
    }

    @Override // com.touchgui.sdk.internal.h5
    public final Object c(byte[] bArr) {
        return Integer.valueOf(bArr[11] & 255);
    }

    @Override // com.touchgui.sdk.internal.h5
    public final byte[] e() {
        int i;
        byte[] bArr;
        byte[] bArr2;
        byte[] bArr3 = null;
        if (TextUtils.isEmpty(this.l)) {
            i = 7;
            bArr = null;
        } else {
            bArr = this.l.getBytes(StandardCharsets.UTF_8);
            if (this.m) {
                bArr = s.b(bArr);
            }
            i = bArr.length + 12;
        }
        if (TextUtils.isEmpty(this.n)) {
            bArr2 = null;
        } else {
            bArr2 = this.n.getBytes(StandardCharsets.UTF_8);
            if (this.m) {
                bArr2 = s.b(bArr2);
            }
            i = i + 5 + bArr2.length;
        }
        if (!TextUtils.isEmpty(this.o)) {
            bArr3 = s.a(749 - i, this.o).getBytes(StandardCharsets.UTF_8);
            if (this.m) {
                bArr3 = s.b(bArr3);
            }
            i = i + 5 + bArr3.length;
        }
        ByteBuffer allocate = ByteBuffer.allocate(i);
        allocate.order(ByteOrder.LITTLE_ENDIAN);
        allocate.put((byte) 0);
        allocate.put((byte) 0);
        if (bArr == null || bArr.length <= 0) {
            allocate.put((byte) 0);
        } else {
            allocate.put((byte) 1);
        }
        if (bArr2 == null || bArr2.length <= 0) {
            allocate.put((byte) 0);
        } else {
            allocate.put((byte) 1);
        }
        if (bArr3 == null || bArr3.length <= 0) {
            allocate.put((byte) 0);
        } else {
            allocate.put((byte) 1);
        }
        allocate.put((byte) this.p);
        allocate.put((byte) 0);
        if (bArr != null && bArr.length > 0) {
            allocate.put((byte) 0);
            allocate.putInt(bArr.length);
            allocate.put(bArr);
        }
        if (bArr2 != null && bArr2.length > 0) {
            allocate.put((byte) 0);
            allocate.putInt(bArr2.length);
            allocate.put(bArr2);
        }
        if (bArr3 != null && bArr3.length > 0) {
            allocate.put((byte) 0);
            allocate.putInt(bArr3.length);
            allocate.put(bArr3);
        }
        return allocate.array();
    }
}
