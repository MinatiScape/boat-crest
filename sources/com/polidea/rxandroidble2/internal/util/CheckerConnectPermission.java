package com.polidea.rxandroidble2.internal.util;

import bleshadow.javax.inject.Inject;
import bleshadow.javax.inject.Named;
import com.polidea.rxandroidble2.ClientScope;
@ClientScope
/* loaded from: classes12.dex */
public class CheckerConnectPermission {

    /* renamed from: a  reason: collision with root package name */
    public final CheckerPermission f13503a;
    public final String[][] b;

    @Inject
    public CheckerConnectPermission(CheckerPermission checkerPermission, @Named("connect-permissions") String[][] strArr) {
        this.f13503a = checkerPermission;
        this.b = strArr;
    }

    public String[] getRecommendedConnectRuntimePermissions() {
        String[][] strArr;
        int i = 0;
        for (String[] strArr2 : this.b) {
            i += strArr2.length;
        }
        String[] strArr3 = new String[i];
        int i2 = 0;
        for (String[] strArr4 : this.b) {
            int length = strArr4.length;
            int i3 = 0;
            while (i3 < length) {
                strArr3[i2] = strArr4[i3];
                i3++;
                i2++;
            }
        }
        return strArr3;
    }

    public boolean isConnectRuntimePermissionGranted() {
        boolean z = true;
        for (String[] strArr : this.b) {
            z &= this.f13503a.a(strArr);
        }
        return z;
    }
}
