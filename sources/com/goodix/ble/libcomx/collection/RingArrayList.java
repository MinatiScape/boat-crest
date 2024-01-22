package com.goodix.ble.libcomx.collection;

import java.util.AbstractList;
import java.util.Collection;
/* loaded from: classes5.dex */
public class RingArrayList<E> extends AbstractList<E> {
    public Object[] h;
    public int i = 0;
    public int j = 0;

    public RingArrayList(int i) {
        this.h = new Object[i];
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean add(E e) {
        Object[] objArr = this.h;
        int i = this.i;
        objArr[i] = e;
        this.i = (i + 1) % objArr.length;
        int i2 = this.j;
        if (i2 < objArr.length) {
            this.j = i2 + 1;
        }
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean addAll(Collection<? extends E> collection) {
        int size = collection.size();
        Object[] objArr = this.h;
        if (objArr.length > size) {
            for (E e : collection) {
                add(e);
            }
            return true;
        }
        int length = size - objArr.length;
        Object[] array = collection.toArray();
        Object[] objArr2 = this.h;
        System.arraycopy(array, length, objArr2, 0, objArr2.length);
        this.i = 0;
        this.j = this.h.length;
        return true;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public void clear() {
        this.i = 0;
        this.j = 0;
    }

    @Override // java.util.AbstractList, java.util.List
    public E get(int i) {
        int i2 = this.j;
        Object[] objArr = this.h;
        if (i2 < objArr.length) {
            return (E) objArr[i];
        }
        return (E) objArr[(this.i + i) % objArr.length];
    }

    public E reuseElement() {
        return (E) this.h[this.i];
    }

    public void setCapability(int i) {
        Object[] objArr = this.h;
        int i2 = this.j;
        int i3 = this.i;
        if (i == objArr.length) {
            return;
        }
        this.h = new Object[i];
        this.i = 0;
        this.j = 0;
        for (int i4 = i2 > i ? i2 - i : 0; i4 < i2; i4++) {
            int length = (i3 + i4) % objArr.length;
            Object[] objArr2 = this.h;
            int i5 = this.i;
            objArr2[i5] = objArr[length];
            this.i = (i5 + 1) % objArr2.length;
            int i6 = this.j;
            if (i6 < objArr2.length) {
                this.j = i6 + 1;
            }
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        return this.j;
    }

    @Override // java.util.AbstractList, java.util.List
    public void add(int i, E e) {
        Object[] objArr = new Object[this.h.length];
        objArr[i] = e;
        for (int i2 = 0; i2 < i; i2++) {
            objArr[i2] = get(i2);
        }
        int i3 = this.j;
        if (i3 >= this.h.length) {
            i3--;
        }
        for (int i4 = i; i4 < i3; i4++) {
            objArr[i + 1] = get(i4);
        }
        this.h = objArr;
        int i5 = i + 1 + i3;
        this.j = i5;
        this.i = i5 % objArr.length;
    }
}
