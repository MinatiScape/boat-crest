package com.coveiot.android.leonardo.more.activities;

import android.app.NotificationManager;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.coveiot.android.boat.R;
import com.coveiot.coveaccess.userappsetting.SoftwareUpdateRes;
import com.coveiot.utils.utility.LogHelper;
import kotlin.jvm.internal.Intrinsics;
import no.nordicsemi.android.dfu.DfuProgressListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityFirmwareUpdateJStyle$mDfuProgressListener$1 extends DfuProgressListenerAdapter {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ActivityFirmwareUpdateJStyle f4925a;

    public ActivityFirmwareUpdateJStyle$mDfuProgressListener$1(ActivityFirmwareUpdateJStyle activityFirmwareUpdateJStyle) {
        this.f4925a = activityFirmwareUpdateJStyle;
    }

    public static final void d(ActivityFirmwareUpdateJStyle this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.y();
        Object systemService = this$0.getSystemService("notification");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
        ((NotificationManager) systemService).cancel(283);
    }

    public static final void e(ActivityFirmwareUpdateJStyle this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.x();
        Object systemService = this$0.getSystemService("notification");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
        ((NotificationManager) systemService).cancel(283);
    }

    public static final void f(ActivityFirmwareUpdateJStyle this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Object systemService = this$0.getSystemService("notification");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
        ((NotificationManager) systemService).cancel(283);
    }

    @Override // no.nordicsemi.android.dfu.DfuProgressListenerAdapter, no.nordicsemi.android.dfu.DfuProgressListener
    public void onDeviceConnecting(@NotNull String deviceAddress) {
        ProgressBar progressBar;
        TextView textView;
        Intrinsics.checkNotNullParameter(deviceAddress, "deviceAddress");
        progressBar = this.f4925a.q;
        if (progressBar != null) {
            progressBar.setIndeterminate(true);
        }
        textView = this.f4925a.r;
        if (textView != null) {
            textView.setText(this.f4925a.getResources().getString(R.string.dfu_status_connecting));
        }
        LogHelper.d(this.f4925a.getTAG(), this.f4925a.getString(R.string.dfu_status_connecting));
    }

    @Override // no.nordicsemi.android.dfu.DfuProgressListenerAdapter, no.nordicsemi.android.dfu.DfuProgressListener
    public void onDeviceDisconnecting(@Nullable String str) {
        ProgressBar progressBar;
        TextView textView;
        progressBar = this.f4925a.q;
        if (progressBar != null) {
            progressBar.setIndeterminate(true);
        }
        textView = this.f4925a.r;
        if (textView != null) {
            textView.setText(R.string.dfu_status_disconnecting);
        }
        LogHelper.d(this.f4925a.getTAG(), "onDeviceDisconnecting");
    }

    @Override // no.nordicsemi.android.dfu.DfuProgressListenerAdapter, no.nordicsemi.android.dfu.DfuProgressListener
    public void onDfuAborted(@NotNull String deviceAddress) {
        TextView textView;
        Handler handler;
        Intrinsics.checkNotNullParameter(deviceAddress, "deviceAddress");
        textView = this.f4925a.r;
        if (textView != null) {
            textView.setText(R.string.dfu_status_aborted);
        }
        handler = this.f4925a.v;
        final ActivityFirmwareUpdateJStyle activityFirmwareUpdateJStyle = this.f4925a;
        handler.postDelayed(new Runnable() { // from class: com.coveiot.android.leonardo.more.activities.ya
            @Override // java.lang.Runnable
            public final void run() {
                ActivityFirmwareUpdateJStyle$mDfuProgressListener$1.d(ActivityFirmwareUpdateJStyle.this);
            }
        }, 200L);
        LogHelper.d(this.f4925a.getTAG(), "onDfuAborted");
    }

    @Override // no.nordicsemi.android.dfu.DfuProgressListenerAdapter, no.nordicsemi.android.dfu.DfuProgressListener
    public void onDfuCompleted(@NotNull String deviceAddress) {
        Handler handler;
        Intrinsics.checkNotNullParameter(deviceAddress, "deviceAddress");
        handler = this.f4925a.v;
        final ActivityFirmwareUpdateJStyle activityFirmwareUpdateJStyle = this.f4925a;
        handler.postDelayed(new Runnable() { // from class: com.coveiot.android.leonardo.more.activities.za
            @Override // java.lang.Runnable
            public final void run() {
                ActivityFirmwareUpdateJStyle$mDfuProgressListener$1.e(ActivityFirmwareUpdateJStyle.this);
            }
        }, 200L);
        LogHelper.d(this.f4925a.getTAG(), "onDfuCompleted");
    }

    @Override // no.nordicsemi.android.dfu.DfuProgressListenerAdapter, no.nordicsemi.android.dfu.DfuProgressListener
    public void onDfuProcessStarting(@NotNull String deviceAddress) {
        ProgressBar progressBar;
        TextView textView;
        Intrinsics.checkNotNullParameter(deviceAddress, "deviceAddress");
        progressBar = this.f4925a.q;
        if (progressBar != null) {
            progressBar.setIndeterminate(true);
        }
        textView = this.f4925a.r;
        if (textView != null) {
            textView.setText(R.string.dfu_status_starting);
        }
        LogHelper.d(this.f4925a.getTAG(), "onDfuProcessStarting");
    }

    @Override // no.nordicsemi.android.dfu.DfuProgressListenerAdapter, no.nordicsemi.android.dfu.DfuProgressListener
    public void onEnablingDfuMode(@NotNull String deviceAddress) {
        ProgressBar progressBar;
        TextView textView;
        Intrinsics.checkNotNullParameter(deviceAddress, "deviceAddress");
        progressBar = this.f4925a.q;
        if (progressBar != null) {
            progressBar.setIndeterminate(true);
        }
        textView = this.f4925a.r;
        if (textView != null) {
            textView.setText(R.string.dfu_status_switching_to_dfu);
        }
        LogHelper.d(this.f4925a.getTAG(), "onEnablingDfuMode");
    }

    @Override // no.nordicsemi.android.dfu.DfuProgressListenerAdapter, no.nordicsemi.android.dfu.DfuProgressListener
    public void onError(@NotNull String deviceAddress, int i, int i2, @Nullable String str) {
        Handler handler;
        Intrinsics.checkNotNullParameter(deviceAddress, "deviceAddress");
        handler = this.f4925a.v;
        final ActivityFirmwareUpdateJStyle activityFirmwareUpdateJStyle = this.f4925a;
        handler.postDelayed(new Runnable() { // from class: com.coveiot.android.leonardo.more.activities.ab
            @Override // java.lang.Runnable
            public final void run() {
                ActivityFirmwareUpdateJStyle$mDfuProgressListener$1.f(ActivityFirmwareUpdateJStyle.this);
            }
        }, 200L);
        ActivityFirmwareUpdateJStyle activityFirmwareUpdateJStyle2 = this.f4925a;
        Object[] objArr = new Object[1];
        SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean = activityFirmwareUpdateJStyle2.u;
        if (firmwareBean == null) {
            Intrinsics.throwUninitializedPropertyAccessException("firmwareBean");
            firmwareBean = null;
        }
        objArr[0] = firmwareBean.getUpdateVersion();
        String string = activityFirmwareUpdateJStyle2.getString(R.string.fw_update_failure, objArr);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.fw_up…rmwareBean.updateVersion)");
        activityFirmwareUpdateJStyle2.B(string, "", true);
        String tag = this.f4925a.getTAG();
        LogHelper.d(tag, "onError message " + str);
    }

    @Override // no.nordicsemi.android.dfu.DfuProgressListenerAdapter, no.nordicsemi.android.dfu.DfuProgressListener
    public void onFirmwareValidating(@NotNull String deviceAddress) {
        ProgressBar progressBar;
        TextView textView;
        Intrinsics.checkNotNullParameter(deviceAddress, "deviceAddress");
        progressBar = this.f4925a.q;
        if (progressBar != null) {
            progressBar.setIndeterminate(true);
        }
        textView = this.f4925a.r;
        if (textView != null) {
            textView.setText(R.string.dfu_status_validating);
        }
        LogHelper.d(this.f4925a.getTAG(), "onFirmwareValidating");
    }

    @Override // no.nordicsemi.android.dfu.DfuProgressListenerAdapter, no.nordicsemi.android.dfu.DfuProgressListener
    public void onProgressChanged(@NotNull String deviceAddress, int i, float f, float f2, int i2, int i3) {
        ProgressBar progressBar;
        ProgressBar progressBar2;
        TextView textView;
        Intrinsics.checkNotNullParameter(deviceAddress, "deviceAddress");
        progressBar = this.f4925a.q;
        if (progressBar != null) {
            progressBar.setIndeterminate(false);
        }
        progressBar2 = this.f4925a.q;
        if (progressBar2 != null) {
            progressBar2.setProgress(i);
        }
        textView = this.f4925a.r;
        if (textView != null) {
            textView.setText(this.f4925a.getString(R.string.dfu_uploading_percentage, new Object[]{Integer.valueOf(i)}));
        }
        LogHelper.d(this.f4925a.getTAG(), "onProgressChanged");
    }
}
