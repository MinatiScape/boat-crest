package com.polidea.rxandroidble2.internal.util;

import android.content.Context;
import android.os.Process;
import bleshadow.javax.inject.Inject;
import com.polidea.rxandroidble2.ClientScope;
import java.util.HashSet;
import java.util.Set;
@ClientScope
/* loaded from: classes12.dex */
public class CheckerPermission {

    /* renamed from: a  reason: collision with root package name */
    public final Context f13507a;
    public final Set<String> b = new HashSet();

    @Inject
    public CheckerPermission(Context context) {
        this.f13507a = context;
    }

    public boolean a(String[] strArr) {
        for (String str : strArr) {
            if (b(str)) {
                return true;
            }
        }
        return false;
    }

    public final boolean b(String str) {
        if (str != null) {
            if (this.b.contains(str)) {
                return true;
            }
            boolean z = this.f13507a.checkPermission(str, Process.myPid(), Process.myUid()) == 0;
            if (z) {
                this.b.add(str);
            }
            return z;
        }
        throw new IllegalArgumentException("permission is null");
    }
}
