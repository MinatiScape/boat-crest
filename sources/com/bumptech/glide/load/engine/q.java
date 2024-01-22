package com.bumptech.glide.load.engine;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
/* loaded from: classes2.dex */
public class q {

    /* renamed from: a  reason: collision with root package name */
    public boolean f2395a;
    public final Handler b = new Handler(Looper.getMainLooper(), new a());

    /* loaded from: classes2.dex */
    public static final class a implements Handler.Callback {
        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            if (message.what == 1) {
                ((Resource) message.obj).recycle();
                return true;
            }
            return false;
        }
    }

    public synchronized void a(Resource<?> resource, boolean z) {
        if (!this.f2395a && !z) {
            this.f2395a = true;
            resource.recycle();
            this.f2395a = false;
        }
        this.b.obtainMessage(1, resource).sendToTarget();
    }
}
