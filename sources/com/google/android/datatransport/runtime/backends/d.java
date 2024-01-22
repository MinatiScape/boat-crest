package com.google.android.datatransport.runtime.backends;

import android.content.Context;
import com.google.android.datatransport.runtime.time.Clock;
import com.google.android.datatransport.runtime.time.Monotonic;
import com.google.android.datatransport.runtime.time.WallTime;
import javax.inject.Inject;
/* loaded from: classes6.dex */
public class d {

    /* renamed from: a  reason: collision with root package name */
    public final Context f8091a;
    public final Clock b;
    public final Clock c;

    @Inject
    public d(Context context, @WallTime Clock clock, @Monotonic Clock clock2) {
        this.f8091a = context;
        this.b = clock;
        this.c = clock2;
    }

    public CreationContext a(String str) {
        return CreationContext.create(this.f8091a, this.b, this.c, str);
    }
}
