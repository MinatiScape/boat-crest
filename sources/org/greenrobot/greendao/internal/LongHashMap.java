package org.greenrobot.greendao.internal;

import java.util.Arrays;
import org.greenrobot.greendao.DaoLog;
/* loaded from: classes13.dex */
public final class LongHashMap<T> {

    /* renamed from: a  reason: collision with root package name */
    public a<T>[] f15479a;
    public int b;
    public int c;
    public int d;

    /* loaded from: classes13.dex */
    public static final class a<T> {

        /* renamed from: a  reason: collision with root package name */
        public final long f15480a;
        public T b;
        public a<T> c;

        public a(long j, T t, a<T> aVar) {
            this.f15480a = j;
            this.b = t;
            this.c = aVar;
        }
    }

    public LongHashMap() {
        this(16);
    }

    public void clear() {
        this.d = 0;
        Arrays.fill(this.f15479a, (Object) null);
    }

    public boolean containsKey(long j) {
        for (a<T> aVar = this.f15479a[((((int) (j >>> 32)) ^ ((int) j)) & Integer.MAX_VALUE) % this.b]; aVar != null; aVar = aVar.c) {
            if (aVar.f15480a == j) {
                return true;
            }
        }
        return false;
    }

    public T get(long j) {
        for (a<T> aVar = this.f15479a[((((int) (j >>> 32)) ^ ((int) j)) & Integer.MAX_VALUE) % this.b]; aVar != null; aVar = aVar.c) {
            if (aVar.f15480a == j) {
                return aVar.b;
            }
        }
        return null;
    }

    public void logStats() {
        a<T>[] aVarArr;
        int i = 0;
        for (a<T> aVar : this.f15479a) {
            while (aVar != null) {
                aVar = aVar.c;
                if (aVar != null) {
                    i++;
                }
            }
        }
        DaoLog.d("load: " + (this.d / this.b) + ", size: " + this.d + ", capa: " + this.b + ", collisions: " + i + ", collision ratio: " + (i / this.d));
    }

    public T put(long j, T t) {
        int i = ((((int) (j >>> 32)) ^ ((int) j)) & Integer.MAX_VALUE) % this.b;
        a<T> aVar = this.f15479a[i];
        for (a<T> aVar2 = aVar; aVar2 != null; aVar2 = aVar2.c) {
            if (aVar2.f15480a == j) {
                T t2 = aVar2.b;
                aVar2.b = t;
                return t2;
            }
        }
        this.f15479a[i] = new a<>(j, t, aVar);
        int i2 = this.d + 1;
        this.d = i2;
        if (i2 > this.c) {
            setCapacity(this.b * 2);
            return null;
        }
        return null;
    }

    public T remove(long j) {
        int i = ((((int) (j >>> 32)) ^ ((int) j)) & Integer.MAX_VALUE) % this.b;
        a<T> aVar = this.f15479a[i];
        a<T> aVar2 = null;
        while (aVar != null) {
            a<T> aVar3 = aVar.c;
            if (aVar.f15480a == j) {
                if (aVar2 == null) {
                    this.f15479a[i] = aVar3;
                } else {
                    aVar2.c = aVar3;
                }
                this.d--;
                return aVar.b;
            }
            aVar2 = aVar;
            aVar = aVar3;
        }
        return null;
    }

    public void reserveRoom(int i) {
        setCapacity((i * 5) / 3);
    }

    public void setCapacity(int i) {
        a<T>[] aVarArr = new a[i];
        int length = this.f15479a.length;
        for (int i2 = 0; i2 < length; i2++) {
            a<T> aVar = this.f15479a[i2];
            while (aVar != null) {
                long j = aVar.f15480a;
                int i3 = ((((int) j) ^ ((int) (j >>> 32))) & Integer.MAX_VALUE) % i;
                a<T> aVar2 = aVar.c;
                aVar.c = aVarArr[i3];
                aVarArr[i3] = aVar;
                aVar = aVar2;
            }
        }
        this.f15479a = aVarArr;
        this.b = i;
        this.c = (i * 4) / 3;
    }

    public int size() {
        return this.d;
    }

    public LongHashMap(int i) {
        this.b = i;
        this.c = (i * 4) / 3;
        this.f15479a = new a[i];
    }
}
