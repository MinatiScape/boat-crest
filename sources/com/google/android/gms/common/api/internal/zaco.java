package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.util.concurrent.NumberedThreadFactory;
import java.util.concurrent.ExecutorService;
/* loaded from: classes6.dex */
public final class zaco {

    /* renamed from: a  reason: collision with root package name */
    public static final ExecutorService f8305a = com.google.android.gms.internal.base.zat.zaa().zaa(new NumberedThreadFactory("GAC_Transform"), 1);

    public static ExecutorService zaa() {
        return f8305a;
    }
}
