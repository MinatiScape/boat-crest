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
public final class ActivityFirmwareUpdateJStyle$scanCallback$1 extends ScanCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ActivityFirmwareUpdateJStyle f4927a;

    public ActivityFirmwareUpdateJStyle$scanCallback$1(ActivityFirmwareUpdateJStyle activityFirmwareUpdateJStyle) {
        this.f4927a = activityFirmwareUpdateJStyle;
    }

    public static final void b(ActivityFirmwareUpdateJStyle this$0, String name, String address) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(name, "name");
        Intrinsics.checkNotNullExpressionValue(address, "address");
        this$0.initiateDFU(name, address);
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
            String tag = this.f4927a.getTAG();
            LogHelper.i(tag, "run: " + address);
            str = this.f4927a.z;
            if (!TextUtils.isEmpty(str)) {
                str2 = this.f4927a.z;
                if (Intrinsics.areEqual(address, str2)) {
                    this.f4927a.startScan(false);
                    Message obtain = Message.obtain();
                    obtain.obj = result.getDevice();
                    obtain.what = 1;
                    handler = this.f4927a.E;
                    handler.sendMessage(obtain);
                    return;
                }
            }
            if (name != null) {
                String lowerCase = name.toLowerCase();
                Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase()");
                if (StringsKt__StringsKt.contains$default((CharSequence) lowerCase, (CharSequence) this.f4927a.getDfuFilterName(), false, 2, (Object) null)) {
                    z = this.f4927a.x;
                    if (z) {
                        return;
                    }
                    this.f4927a.x = true;
                    this.f4927a.startScan(false);
                    Handler handler2 = new Handler();
                    final ActivityFirmwareUpdateJStyle activityFirmwareUpdateJStyle = this.f4927a;
                    handler2.postDelayed(new Runnable() { // from class: com.coveiot.android.leonardo.more.activities.cb
                        @Override // java.lang.Runnable
                        public final void run() {
                            ActivityFirmwareUpdateJStyle$scanCallback$1.b(ActivityFirmwareUpdateJStyle.this, name, address);
                        }
                    }, Constants.PN_LARGE_ICON_DOWNLOAD_TIMEOUT_IN_MILLIS);
                }
            }
        } catch (Exception e) {
            LogHelper.e(this.f4927a.getTAG(), e.toString());
        }
    }
}
