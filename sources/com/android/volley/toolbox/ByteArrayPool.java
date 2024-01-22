package com.android.volley.toolbox;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
/* loaded from: classes.dex */
public class ByteArrayPool {
    public static final Comparator<byte[]> BUF_COMPARATOR = new a();

    /* renamed from: a  reason: collision with root package name */
    public final List<byte[]> f2154a = new ArrayList();
    public final List<byte[]> b = new ArrayList(64);
    public int c = 0;
    public final int d;

    /* loaded from: classes.dex */
    public class a implements Comparator<byte[]> {
        @Override // java.util.Comparator
        /* renamed from: a */
        public int compare(byte[] bArr, byte[] bArr2) {
            return bArr.length - bArr2.length;
        }
    }

    public ByteArrayPool(int i) {
        this.d = i;
    }

    public final synchronized void a() {
        while (this.c > this.d) {
            byte[] remove = this.f2154a.remove(0);
            this.b.remove(remove);
            this.c -= remove.length;
        }
    }

    public synchronized byte[] getBuf(int i) {
        for (int i2 = 0; i2 < this.b.size(); i2++) {
            byte[] bArr = this.b.get(i2);
            if (bArr.length >= i) {
                this.c -= bArr.length;
                this.b.remove(i2);
                this.f2154a.remove(bArr);
                return bArr;
            }
        }
        return new byte[i];
    }

    public synchronized void returnBuf(byte[] bArr) {
        if (bArr != null) {
            if (bArr.length <= this.d) {
                this.f2154a.add(bArr);
                int binarySearch = Collections.binarySearch(this.b, bArr, BUF_COMPARATOR);
                if (binarySearch < 0) {
                    binarySearch = (-binarySearch) - 1;
                }
                this.b.add(binarySearch, bArr);
                this.c += bArr.length;
                a();
            }
        }
    }
}
