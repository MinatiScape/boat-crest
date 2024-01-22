package com.coveiot.android.bleabstract.services;

import android.os.Handler;
import com.coveiot.sdk.ble.utils.ModuleNames;
import com.coveiot.utils.utility.LogHelper;
import com.crrepa.ble.conn.listener.CRPPhoneOperationListener;
import kotlin.jvm.internal.Intrinsics;
/* loaded from: classes2.dex */
public final class CRPBaseBleService$crpPhoneOperationListener$1 implements CRPPhoneOperationListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ CRPBaseBleService f3705a;

    public CRPBaseBleService$crpPhoneOperationListener$1(CRPBaseBleService cRPBaseBleService) {
        this.f3705a = cRPBaseBleService;
    }

    public static final void a(CRPBaseBleService this$0, int i) {
        String str;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        str = this$0.m;
        LogHelper.d(str, "crpPhoneOperationListener->onOperationChange-> " + i, ModuleNames.BLEABSTRACT.getModuleName());
        if (i == 3) {
            this$0.b();
        } else {
            CRPBaseBleService.access$updateMusicActionChangeToAppLayer(this$0, i);
        }
    }

    @Override // com.crrepa.ble.conn.listener.CRPPhoneOperationListener
    public void onOperationChange(final int i) {
        Handler handler;
        handler = this.f3705a.c;
        final CRPBaseBleService cRPBaseBleService = this.f3705a;
        handler.post(new Runnable() { // from class: com.coveiot.android.bleabstract.services.l0
            @Override // java.lang.Runnable
            public final void run() {
                CRPBaseBleService$crpPhoneOperationListener$1.a(CRPBaseBleService.this, i);
            }
        });
    }
}
