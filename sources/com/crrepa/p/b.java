package com.crrepa.p;

import java.util.LinkedList;
/* loaded from: classes9.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    public LinkedList<a> f7789a = new LinkedList<>();

    public void a() {
        this.f7789a.clear();
    }

    public synchronized void a(a aVar) {
        this.f7789a.add(aVar);
    }

    public int b() {
        return this.f7789a.size();
    }

    public synchronized a c() {
        return d() ? this.f7789a.get(0) : null;
    }

    public boolean d() {
        return b() > 0;
    }

    public synchronized void e() {
        if (d()) {
            this.f7789a.remove(0);
        }
    }
}
