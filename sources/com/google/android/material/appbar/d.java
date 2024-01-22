package com.google.android.material.appbar;

import android.view.View;
import androidx.core.view.ViewCompat;
/* loaded from: classes10.dex */
public class d {

    /* renamed from: a  reason: collision with root package name */
    public final View f10218a;
    public int b;
    public int c;
    public int d;
    public int e;
    public boolean f = true;
    public boolean g = true;

    public d(View view) {
        this.f10218a = view;
    }

    public void a() {
        View view = this.f10218a;
        ViewCompat.offsetTopAndBottom(view, this.d - (view.getTop() - this.b));
        View view2 = this.f10218a;
        ViewCompat.offsetLeftAndRight(view2, this.e - (view2.getLeft() - this.c));
    }

    public int b() {
        return this.b;
    }

    public int c() {
        return this.e;
    }

    public int d() {
        return this.d;
    }

    public boolean e() {
        return this.g;
    }

    public boolean f() {
        return this.f;
    }

    public void g() {
        this.b = this.f10218a.getTop();
        this.c = this.f10218a.getLeft();
    }

    public void h(boolean z) {
        this.g = z;
    }

    public boolean i(int i) {
        if (!this.g || this.e == i) {
            return false;
        }
        this.e = i;
        a();
        return true;
    }

    public boolean j(int i) {
        if (!this.f || this.d == i) {
            return false;
        }
        this.d = i;
        a();
        return true;
    }

    public void k(boolean z) {
        this.f = z;
    }
}
