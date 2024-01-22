package com.apex.bluetooth.core;
/* loaded from: classes.dex */
public class b implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ a f2171a;

    public b(a aVar) {
        this.f2171a = aVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        a aVar = this.f2171a;
        if (aVar.l) {
            return;
        }
        aVar.c = aVar.d.connectGatt(aVar.i, false, aVar.h, 2);
    }
}
