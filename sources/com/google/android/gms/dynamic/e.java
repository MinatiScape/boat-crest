package com.google.android.gms.dynamic;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
/* loaded from: classes6.dex */
public final class e implements View.OnClickListener {
    public final /* synthetic */ Context h;
    public final /* synthetic */ Intent i;

    public e(Context context, Intent intent) {
        this.h = context;
        this.i = intent;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        try {
            this.h.startActivity(this.i);
        } catch (ActivityNotFoundException e) {
            Log.e("DeferredLifecycleHelper", "Failed to start resolution intent", e);
        }
    }
}
