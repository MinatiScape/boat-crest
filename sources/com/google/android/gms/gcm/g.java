package com.google.android.gms.gcm;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import org.jose4j.jwx.HeaderParameterNames;
/* loaded from: classes6.dex */
public final class g implements h {

    /* renamed from: a  reason: collision with root package name */
    public final Context f8482a;
    public final PendingIntent b;

    public g(Context context) {
        this.f8482a = context;
        this.b = PendingIntent.getBroadcast(context, 0, new Intent().setPackage("com.google.example.invalidpackage"), 0);
    }

    @Override // com.google.android.gms.gcm.h
    public final boolean a(ComponentName componentName) {
        Intent d = d("CANCEL_ALL");
        d.putExtra("component", componentName);
        this.f8482a.sendBroadcast(d);
        return true;
    }

    @Override // com.google.android.gms.gcm.h
    public final boolean b(ComponentName componentName, String str) {
        Intent d = d("CANCEL_TASK");
        d.putExtra("component", componentName);
        d.putExtra(HeaderParameterNames.AUTHENTICATION_TAG, str);
        this.f8482a.sendBroadcast(d);
        return true;
    }

    @Override // com.google.android.gms.gcm.h
    public final boolean c(Task task) {
        Intent d = d("SCHEDULE_TASK");
        Bundle bundle = new Bundle();
        task.toBundle(bundle);
        d.putExtras(bundle);
        this.f8482a.sendBroadcast(d);
        return true;
    }

    public final Intent d(String str) {
        Intent intent = new Intent("com.google.android.gms.gcm.ACTION_SCHEDULE");
        intent.setPackage("com.google.android.gms");
        intent.putExtra("app", this.b);
        intent.putExtra("source", 4);
        intent.putExtra("source_version", GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE);
        intent.putExtra("scheduler_action", str);
        return intent;
    }
}
