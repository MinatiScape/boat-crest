package com.realsil.sdk.dfu.j;

import com.realsil.sdk.core.logger.ZLogger;
/* loaded from: classes12.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    public static boolean f13611a = false;
    public int b = 50000000;
    public volatile long c = -1;
    public int d;
    public boolean e;

    public b(boolean z, int i) {
        this.d = -1;
        this.e = false;
        this.e = z;
        this.d = i * this.b;
    }

    public void a() {
        if (this.e) {
            if (this.c != -1 && this.d != -1) {
                do {
                } while (System.nanoTime() - this.c < this.d);
                if (f13611a) {
                    ZLogger.v("flow control stopped");
                }
            } else if (f13611a) {
                ZLogger.d("flow control block with error, must initial first");
            }
        }
    }

    public void b() {
        if (this.e) {
            this.c = System.nanoTime();
            if (f13611a) {
                ZLogger.v("flow control started");
            }
        }
    }
}
