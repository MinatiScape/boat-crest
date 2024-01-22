package com.google.android.gms.gcm;

import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.internal.gcm.zzj;
import java.util.concurrent.BlockingQueue;
/* loaded from: classes6.dex */
public final class c extends zzj {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ GoogleCloudMessaging f8481a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c(GoogleCloudMessaging googleCloudMessaging, Looper looper) {
        super(looper);
        this.f8481a = googleCloudMessaging;
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        boolean d;
        Context context;
        Context context2;
        BlockingQueue blockingQueue;
        if (message == null || !(message.obj instanceof Intent)) {
            Log.w("GCM", "Dropping invalid message");
        }
        Intent intent = (Intent) message.obj;
        if ("com.google.android.c2dm.intent.REGISTRATION".equals(intent.getAction())) {
            blockingQueue = this.f8481a.d;
            blockingQueue.add(intent);
            return;
        }
        d = this.f8481a.d(intent);
        if (d) {
            return;
        }
        context = this.f8481a.f8476a;
        intent.setPackage(context.getPackageName());
        context2 = this.f8481a.f8476a;
        context2.sendBroadcast(intent);
    }
}
