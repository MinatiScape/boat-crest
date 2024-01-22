package rx.internal.util;

import java.util.ArrayList;
import java.util.List;
/* loaded from: classes13.dex */
public class LinkedArrayList {
    public final int h;
    public Object[] i;
    public Object[] j;
    public volatile int k;
    public int l;

    public LinkedArrayList(int i) {
        this.h = i;
    }

    List<Object> a() {
        int i = this.h;
        int i2 = this.k;
        ArrayList arrayList = new ArrayList(i2 + 1);
        Object[] head = head();
        int i3 = 0;
        int i4 = 0;
        while (i3 < i2) {
            arrayList.add(head[i4]);
            i3++;
            i4++;
            if (i4 == i) {
                head = (Object[]) head[i];
                i4 = 0;
            }
        }
        return arrayList;
    }

    public void add(Object obj) {
        if (this.k == 0) {
            Object[] objArr = new Object[this.h + 1];
            this.i = objArr;
            this.j = objArr;
            objArr[0] = obj;
            this.l = 1;
            this.k = 1;
            return;
        }
        int i = this.l;
        int i2 = this.h;
        if (i == i2) {
            Object[] objArr2 = new Object[i2 + 1];
            objArr2[0] = obj;
            this.j[i2] = objArr2;
            this.j = objArr2;
            this.l = 1;
            this.k++;
            return;
        }
        this.j[i] = obj;
        this.l = i + 1;
        this.k++;
    }

    public int capacityHint() {
        return this.h;
    }

    public Object[] head() {
        return this.i;
    }

    public int indexInTail() {
        return this.l;
    }

    public int size() {
        return this.k;
    }

    public Object[] tail() {
        return this.j;
    }

    public String toString() {
        return a().toString();
    }
}
