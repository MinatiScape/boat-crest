package com.coveiot.mki;

import android.os.Handler;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public final class e {

    /* renamed from: a  reason: collision with root package name */
    public final d f7279a;
    public final f b;
    public h e;
    public long h;
    public List<List<Byte>> i;
    public int j;
    public int m;
    public int n;
    public Handler p;
    public List<Byte> d = new ArrayList();
    public final Runnable f = new Runnable() { // from class: com.coveiot.mki.t0
        @Override // java.lang.Runnable
        public final void run() {
            e.this.o();
        }
    };
    public short k = -1;
    public int l = -1;
    public int o = 0;
    public final boolean c = true;
    public short g = 2;

    public e(d dVar, f fVar) {
        this.f7279a = dVar;
        this.b = fVar;
    }

    public e(d dVar, f fVar, h hVar) {
        this.f7279a = dVar;
        this.b = fVar;
        this.e = hVar;
        dVar.getClass();
        this.h = 10000L;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void o() {
        this.e.a(this);
    }

    public final void a() {
        this.d.clear();
    }

    public final void a(int i) {
        this.l = i;
    }

    public final void a(Handler handler) {
        this.p = handler;
    }

    public final void a(List<List<Byte>> list) {
        this.i = list;
    }

    public final void a(short s) {
        this.g = s;
    }

    public final void b() {
        Handler handler = this.p;
        if (handler != null) {
            handler.removeCallbacks(this.f);
        }
    }

    public final void b(int i) {
        this.j = i;
    }

    public final void b(List<Byte> list) {
        this.d = list;
    }

    public final void b(short s) {
        this.k = s;
    }

    public final f c() {
        return this.b;
    }

    public final void c(int i) {
        this.n = i;
    }

    public final d d() {
        return this.f7279a;
    }

    public final void d(int i) {
        this.o = i;
    }

    public final int e() {
        return this.l;
    }

    public final void e(int i) {
        this.m = i;
    }

    public final short f() {
        return this.g;
    }

    public final List<List<Byte>> g() {
        return this.i;
    }

    public final int h() {
        return this.j;
    }

    public final List<Byte> i() {
        return this.d;
    }

    public final short j() {
        return this.k;
    }

    public final int k() {
        return this.n;
    }

    public final int l() {
        return this.o;
    }

    public final int m() {
        return this.m;
    }

    public final boolean n() {
        return this.c;
    }

    public final void p() {
        Handler handler = this.p;
        if (handler != null) {
            handler.removeCallbacks(this.f);
            this.p.postDelayed(this.f, this.h);
        }
    }
}
