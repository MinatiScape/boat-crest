package com.google.android.gms.common;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.internal.base.zau;
@SuppressLint({"HandlerLeak"})
/* loaded from: classes6.dex */
public final class a extends zau {

    /* renamed from: a  reason: collision with root package name */
    public final Context f8240a;
    public final /* synthetic */ GoogleApiAvailability b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public a(GoogleApiAvailability googleApiAvailability, Context context) {
        super(Looper.myLooper() == null ? Looper.getMainLooper() : Looper.myLooper());
        this.b = googleApiAvailability;
        this.f8240a = context.getApplicationContext();
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        int i = message.what;
        if (i != 1) {
            Log.w("GoogleApiAvailability", "Don't know how to handle this message: " + i);
            return;
        }
        int isGooglePlayServicesAvailable = this.b.isGooglePlayServicesAvailable(this.f8240a);
        if (this.b.isUserResolvableError(isGooglePlayServicesAvailable)) {
            this.b.showErrorNotification(this.f8240a, isGooglePlayServicesAvailable);
        }
    }
}
