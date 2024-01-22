package com.coveiot.android.leonardo.more.activities;

import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.clevertap.android.sdk.Constants;
import com.coveiot.utils.utility.LogHelper;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class ActivityFirmwareUpdateSmaRealTek$scanCallback$1 extends ScanCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ActivityFirmwareUpdateSmaRealTek f4960a;

    public ActivityFirmwareUpdateSmaRealTek$scanCallback$1(ActivityFirmwareUpdateSmaRealTek activityFirmwareUpdateSmaRealTek) {
        this.f4960a = activityFirmwareUpdateSmaRealTek;
    }

    public static final void b(ActivityFirmwareUpdateSmaRealTek this$0, String str, String address) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(address, "address");
        this$0.initiateDFU(str, address);
    }

    @Override // android.bluetooth.le.ScanCallback
    public void onScanFailed(int i) {
        super.onScanFailed(i);
        LogHelper.e("onScanFailed", "Error code " + i);
    }

    @Override // android.bluetooth.le.ScanCallback
    public void onScanResult(int i, @NotNull ScanResult result) {
        String str;
        boolean z;
        String str2;
        Handler handler;
        Intrinsics.checkNotNullParameter(result, "result");
        super.onScanResult(i, result);
        try {
            final String name = result.getDevice().getName();
            final String address = result.getDevice().getAddress();
            String tag = this.f4960a.getTAG();
            LogHelper.i(tag, "run: " + address);
            str = this.f4960a.A;
            if (!TextUtils.isEmpty(str)) {
                str2 = this.f4960a.A;
                if (Intrinsics.areEqual(address, str2)) {
                    this.f4960a.startScan(false);
                    Message obtain = Message.obtain();
                    obtain.obj = result.getDevice();
                    obtain.what = 1;
                    handler = this.f4960a.F;
                    handler.sendMessage(obtain);
                    return;
                }
            }
            if (name != null) {
                String lowerCase = name.toLowerCase();
                Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase()");
                if (StringsKt__StringsKt.contains$default((CharSequence) lowerCase, (CharSequence) this.f4960a.getDfuFilterName(), false, 2, (Object) null)) {
                    z = this.f4960a.y;
                    if (z) {
                        return;
                    }
                    this.f4960a.y = true;
                    this.f4960a.startScan(false);
                    Handler handler2 = new Handler();
                    final ActivityFirmwareUpdateSmaRealTek activityFirmwareUpdateSmaRealTek = this.f4960a;
                    handler2.postDelayed(new Runnable() { // from class: com.coveiot.android.leonardo.more.activities.be
                        @Override // java.lang.Runnable
                        public final void run() {
                            ActivityFirmwareUpdateSmaRealTek$scanCallback$1.b(ActivityFirmwareUpdateSmaRealTek.this, name, address);
                        }
                    }, Constants.PN_LARGE_ICON_DOWNLOAD_TIMEOUT_IN_MILLIS);
                }
            }
        } catch (Exception e) {
            LogHelper.e(this.f4960a.getTAG(), e.toString());
        }
    }
}
