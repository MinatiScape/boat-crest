package com.google.android.gms.gcm;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.RequiresPermission;
import com.google.android.gms.iid.InstanceID;
import java.io.IOException;
import java.util.regex.Pattern;
@Deprecated
/* loaded from: classes6.dex */
public class GcmPubSub {
    public static GcmPubSub b;
    public static final Pattern c = Pattern.compile("/topics/[a-zA-Z0-9-_.~%]{1,900}");

    /* renamed from: a  reason: collision with root package name */
    public InstanceID f8474a;

    public GcmPubSub(Context context) {
        this.f8474a = InstanceID.getInstance(context);
    }

    @Deprecated
    public static synchronized GcmPubSub getInstance(Context context) {
        GcmPubSub gcmPubSub;
        synchronized (GcmPubSub.class) {
            if (b == null) {
                GoogleCloudMessaging.g(context);
                b = new GcmPubSub(context);
            }
            gcmPubSub = b;
        }
        return gcmPubSub;
    }

    @RequiresPermission("com.google.android.c2dm.permission.RECEIVE")
    @Deprecated
    public void subscribe(String str, String str2, Bundle bundle) throws IOException {
        if (str != null && !str.isEmpty()) {
            if (str2 != null && c.matcher(str2).matches()) {
                if (bundle == null) {
                    bundle = new Bundle();
                }
                bundle.putString("gcm.topic", str2);
                this.f8474a.getToken(str, str2, bundle);
                return;
            }
            String valueOf = String.valueOf(str2);
            throw new IllegalArgumentException(valueOf.length() != 0 ? "Invalid topic name: ".concat(valueOf) : new String("Invalid topic name: "));
        }
        String valueOf2 = String.valueOf(str);
        throw new IllegalArgumentException(valueOf2.length() != 0 ? "Invalid appInstanceToken: ".concat(valueOf2) : new String("Invalid appInstanceToken: "));
    }

    @RequiresPermission("com.google.android.c2dm.permission.RECEIVE")
    @Deprecated
    public void unsubscribe(String str, String str2) throws IOException {
        Bundle bundle = new Bundle();
        bundle.putString("gcm.topic", str2);
        this.f8474a.zzd(str, str2, bundle);
    }
}
