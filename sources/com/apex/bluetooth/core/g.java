package com.apex.bluetooth.core;

import com.apex.bluetooth.callback.DataResponseCallback;
import com.apex.bluetooth.callback.EABleCallback;
import com.apex.bluetooth.callback.MotionDataResponseCallback;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
/* loaded from: classes.dex */
public class g implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ c f2185a;

    public g(c cVar) {
        this.f2185a = cVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        com.apex.bluetooth.data_package.b.b bVar;
        com.apex.bluetooth.data_package.b.b bVar2;
        com.apex.bluetooth.data_package.b.b bVar3;
        com.apex.bluetooth.data_package.b.b bVar4;
        com.apex.bluetooth.data_package.b.b bVar5 = this.f2185a.j;
        if (bVar5 != null && !bVar5.f2207a) {
            this.f2185a.j.f2207a = true;
            EABleCallback eABleCallback = this.f2185a.M;
            if (eABleCallback != null) {
                eABleCallback.mutualFail(6);
            }
        }
        com.apex.bluetooth.data_package.b.b bVar6 = this.f2185a.g;
        if (bVar6 != null && !bVar6.f2207a) {
            this.f2185a.g.f2207a = true;
            DataResponseCallback dataResponseCallback = this.f2185a.K;
            if (dataResponseCallback != null) {
                dataResponseCallback.mutualFail(6);
            }
        }
        com.apex.bluetooth.data_package.b.b bVar7 = this.f2185a.h;
        if (bVar7 != null && !bVar7.f2207a) {
            this.f2185a.h.f2207a = true;
            MotionDataResponseCallback motionDataResponseCallback = this.f2185a.L;
            if (motionDataResponseCallback != null) {
                motionDataResponseCallback.mutualFail(6);
            }
        }
        com.apex.bluetooth.data_package.b.b bVar8 = this.f2185a.i;
        if (bVar8 != null && !bVar8.f2207a) {
            this.f2185a.i.f2207a = true;
            com.apex.bluetooth.listener.b bVar9 = this.f2185a.X;
            if (bVar9 != null) {
                bVar9.mutualFail(6);
            }
        }
        ConcurrentLinkedQueue<l> concurrentLinkedQueue = this.f2185a.u;
        if (concurrentLinkedQueue != null && !concurrentLinkedQueue.isEmpty()) {
            Iterator<l> it = this.f2185a.u.iterator();
            while (it.hasNext()) {
                l next = it.next();
                if (next != null && (bVar4 = next.b) != null && !bVar4.f2207a) {
                    next.b.f2207a = true;
                    EABleCallback eABleCallback2 = next.f2191a;
                    if (eABleCallback2 != null) {
                        eABleCallback2.mutualFail(6);
                    }
                }
            }
        }
        ConcurrentLinkedQueue<l> concurrentLinkedQueue2 = this.f2185a.v;
        if (concurrentLinkedQueue2 != null && !concurrentLinkedQueue2.isEmpty()) {
            Iterator<l> it2 = this.f2185a.v.iterator();
            while (it2.hasNext()) {
                l next2 = it2.next();
                if (next2 != null && (bVar3 = next2.b) != null && !bVar3.f2207a) {
                    next2.b.f2207a = true;
                    EABleCallback eABleCallback3 = next2.f2191a;
                    if (eABleCallback3 != null) {
                        eABleCallback3.mutualFail(6);
                    }
                }
            }
        }
        ConcurrentLinkedQueue<l> concurrentLinkedQueue3 = this.f2185a.w;
        if (concurrentLinkedQueue3 != null && !concurrentLinkedQueue3.isEmpty()) {
            Iterator<l> it3 = this.f2185a.w.iterator();
            while (it3.hasNext()) {
                l next3 = it3.next();
                if (next3 != null && (bVar2 = next3.b) != null && !bVar2.f2207a) {
                    next3.b.f2207a = true;
                    EABleCallback eABleCallback4 = next3.f2191a;
                    if (eABleCallback4 != null) {
                        eABleCallback4.mutualFail(6);
                    }
                }
            }
        }
        ConcurrentLinkedQueue<l> concurrentLinkedQueue4 = this.f2185a.x;
        if (concurrentLinkedQueue4 != null && !concurrentLinkedQueue4.isEmpty()) {
            Iterator<l> it4 = this.f2185a.x.iterator();
            while (it4.hasNext()) {
                l next4 = it4.next();
                if (next4 != null && (bVar = next4.b) != null && !bVar.f2207a) {
                    next4.b.f2207a = true;
                    EABleCallback eABleCallback5 = next4.f2191a;
                    if (eABleCallback5 != null) {
                        eABleCallback5.mutualFail(6);
                    }
                }
            }
        }
        c.h(this.f2185a);
    }
}
