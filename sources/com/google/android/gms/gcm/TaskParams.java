package com.google.android.gms.gcm;

import android.net.Uri;
import android.os.Bundle;
import java.util.List;
/* loaded from: classes6.dex */
public class TaskParams {

    /* renamed from: a  reason: collision with root package name */
    public final String f8479a;
    public final Bundle b;
    public final List<Uri> c;

    public TaskParams(String str) {
        this(str, null);
    }

    public Bundle getExtras() {
        return this.b;
    }

    public String getTag() {
        return this.f8479a;
    }

    public TaskParams(String str, Bundle bundle) {
        this(str, bundle, null);
    }

    public TaskParams(String str, Bundle bundle, List<Uri> list) {
        this(str, bundle, 180L, list);
    }

    public TaskParams(String str, Bundle bundle, long j, List<Uri> list) {
        this.f8479a = str;
        this.b = bundle;
        this.c = list;
    }
}
