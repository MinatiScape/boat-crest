package com.h6ah4i.android.widget.advrecyclerview.composedadapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import com.h6ah4i.android.widget.advrecyclerview.adapter.BridgeAdapterDataObserver;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes11.dex */
public class a {
    public static long f = -1;

    /* renamed from: a  reason: collision with root package name */
    public BridgeAdapterDataObserver.Subscriber f11894a;
    public List<ComposedChildAdapterTag> b = new ArrayList();
    public List<RecyclerView.Adapter> c = new ArrayList();
    public List<RecyclerView.Adapter> d = new ArrayList();
    public List<b> e = new ArrayList();

    public a(@NonNull BridgeAdapterDataObserver.Subscriber subscriber) {
        this.f11894a = subscriber;
    }

    public static long b(int i, int i2) {
        return (i2 & 4294967295L) | (i << 32);
    }

    public static int c(long j) {
        return (int) (j >>> 32);
    }

    public static int d(long j) {
        return (int) (j & 4294967295L);
    }

    @NonNull
    public ComposedChildAdapterTag a(@NonNull RecyclerView.Adapter adapter, int i) {
        b bVar;
        ComposedChildAdapterTag composedChildAdapterTag = new ComposedChildAdapterTag();
        this.b.add(i, composedChildAdapterTag);
        this.c.add(i, adapter);
        int indexOf = this.d.indexOf(adapter);
        if (indexOf >= 0) {
            bVar = this.e.get(indexOf);
        } else {
            b bVar2 = new b(this.f11894a, adapter);
            this.e.add(bVar2);
            this.d.add(adapter);
            adapter.registerAdapterDataObserver(bVar2);
            bVar = bVar2;
        }
        bVar.c(composedChildAdapterTag);
        return composedChildAdapterTag;
    }

    @NonNull
    public RecyclerView.Adapter e(int i) {
        return this.c.get(i);
    }

    public int f(@NonNull ComposedChildAdapterTag composedChildAdapterTag) {
        return this.b.indexOf(composedChildAdapterTag);
    }

    public int g() {
        return this.c.size();
    }

    @NonNull
    public ComposedChildAdapterTag h(int i) {
        return this.b.get(i);
    }

    @NonNull
    public List<RecyclerView.Adapter> i() {
        return this.d;
    }

    public void j() {
        this.b.clear();
        this.c.clear();
        int size = this.d.size();
        for (int i = 0; i < size; i++) {
            b bVar = this.e.get(i);
            this.d.get(i).unregisterAdapterDataObserver(bVar);
            bVar.d();
        }
        this.d.clear();
        this.e.clear();
    }

    @Nullable
    public RecyclerView.Adapter k(@NonNull ComposedChildAdapterTag composedChildAdapterTag) {
        int f2 = f(composedChildAdapterTag);
        if (f2 < 0) {
            return null;
        }
        RecyclerView.Adapter remove = this.c.remove(f2);
        this.b.remove(f2);
        int indexOf = this.d.indexOf(remove);
        if (indexOf >= 0) {
            b bVar = this.e.get(indexOf);
            bVar.e(composedChildAdapterTag);
            if (!bVar.b()) {
                remove.unregisterAdapterDataObserver(bVar);
            }
            return remove;
        }
        throw new IllegalStateException("Something wrong. Inconsistency detected.");
    }
}
