package com.realsil.sdk.core.c;

import android.content.Context;
import com.realsil.sdk.core.bluetooth.scanner.ScannerParams;
import com.realsil.sdk.core.logger.ZLogger;
/* loaded from: classes12.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    public a f13584a;

    public b(Context context, int i) {
        this.f13584a = a(context, i);
    }

    public a a(Context context, int i) {
        if (i >= 21) {
            return new d(context);
        }
        if (i >= 18) {
            return new c(context);
        }
        return null;
    }

    public boolean a(ScannerParams scannerParams, boolean z) {
        a aVar = this.f13584a;
        if (z) {
            if (!aVar.c.isEnabled()) {
                ZLogger.d("BT Adapter is not enable");
                return false;
            }
            return aVar.a(scannerParams);
        }
        return aVar.a();
    }
}
