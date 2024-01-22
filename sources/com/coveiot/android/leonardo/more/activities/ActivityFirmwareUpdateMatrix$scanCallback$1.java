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
public final class ActivityFirmwareUpdateMatrix$scanCallback$1 extends ScanCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ActivityFirmwareUpdateMatrix f4941a;

    public ActivityFirmwareUpdateMatrix$scanCallback$1(ActivityFirmwareUpdateMatrix activityFirmwareUpdateMatrix) {
        this.f4941a = activityFirmwareUpdateMatrix;
    }

    public static final void b(ActivityFirmwareUpdateMatrix this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.initiateDFU();
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
            String name = result.getDevice().getName();
            String address = result.getDevice().getAddress();
            String tag = this.f4941a.getTAG();
            LogHelper.i(tag, "run: " + address);
            str = this.f4941a.y;
            if (!TextUtils.isEmpty(str)) {
                str2 = this.f4941a.y;
                if (Intrinsics.areEqual(address, str2)) {
                    this.f4941a.startScan(false);
                    Message obtain = Message.obtain();
                    obtain.obj = result.getDevice();
                    obtain.what = 1;
                    handler = this.f4941a.F;
                    handler.sendMessage(obtain);
                    return;
                }
            }
            if (name != null) {
                String lowerCase = name.toLowerCase();
                Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase()");
                if (StringsKt__StringsKt.contains$default((CharSequence) lowerCase, (CharSequence) this.f4941a.getDfuFilterName(), false, 2, (Object) null)) {
                    z = this.f4941a.w;
                    if (z) {
                        return;
                    }
                    this.f4941a.w = true;
                    this.f4941a.startScan(false);
                    Handler handler2 = new Handler();
                    final ActivityFirmwareUpdateMatrix activityFirmwareUpdateMatrix = this.f4941a;
                    handler2.postDelayed(new Runnable() { // from class: com.coveiot.android.leonardo.more.activities.mc
                        @Override // java.lang.Runnable
                        public final void run() {
                            ActivityFirmwareUpdateMatrix$scanCallback$1.b(ActivityFirmwareUpdateMatrix.this);
                        }
                    }, Constants.PN_LARGE_ICON_DOWNLOAD_TIMEOUT_IN_MILLIS);
                }
            }
        } catch (Exception e) {
            LogHelper.e(this.f4941a.getTAG(), e.toString());
        }
    }
}
