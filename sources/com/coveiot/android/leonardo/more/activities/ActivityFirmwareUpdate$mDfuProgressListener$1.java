package com.coveiot.android.leonardo.more.activities;

import android.app.NotificationManager;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.utils.AppSessionManager;
import com.coveiot.coveaccess.userappsetting.SoftwareUpdateRes;
import kotlin.jvm.internal.Intrinsics;
import no.nordicsemi.android.dfu.DfuProgressListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityFirmwareUpdate$mDfuProgressListener$1 extends DfuProgressListenerAdapter {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ActivityFirmwareUpdate f4912a;

    public ActivityFirmwareUpdate$mDfuProgressListener$1(ActivityFirmwareUpdate activityFirmwareUpdate) {
        this.f4912a = activityFirmwareUpdate;
    }

    public static final void d(ActivityFirmwareUpdate this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.F();
        Object systemService = this$0.getSystemService("notification");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
        ((NotificationManager) systemService).cancel(283);
    }

    public static final void e(ActivityFirmwareUpdate this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.E();
        Object systemService = this$0.getSystemService("notification");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
        ((NotificationManager) systemService).cancel(283);
    }

    public static final void f(ActivityFirmwareUpdate this$0) {
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
        progressBar = this.f4912a.t;
        if (progressBar != null) {
            progressBar.setIndeterminate(true);
        }
        textView = this.f4912a.u;
        if (textView != null) {
            textView.setText(this.f4912a.getResources().getString(R.string.dfu_status_connecting));
        }
    }

    @Override // no.nordicsemi.android.dfu.DfuProgressListenerAdapter, no.nordicsemi.android.dfu.DfuProgressListener
    public void onDeviceDisconnecting(@Nullable String str) {
        ProgressBar progressBar;
        TextView textView;
        progressBar = this.f4912a.t;
        if (progressBar != null) {
            progressBar.setIndeterminate(true);
        }
        textView = this.f4912a.u;
        if (textView != null) {
            textView.setText(R.string.dfu_status_disconnecting);
        }
    }

    @Override // no.nordicsemi.android.dfu.DfuProgressListenerAdapter, no.nordicsemi.android.dfu.DfuProgressListener
    public void onDfuAborted(@NotNull String deviceAddress) {
        TextView textView;
        Intrinsics.checkNotNullParameter(deviceAddress, "deviceAddress");
        textView = this.f4912a.u;
        if (textView != null) {
            textView.setText(R.string.dfu_status_aborted);
        }
        Handler handler = new Handler();
        final ActivityFirmwareUpdate activityFirmwareUpdate = this.f4912a;
        handler.postDelayed(new Runnable() { // from class: com.coveiot.android.leonardo.more.activities.ba
            @Override // java.lang.Runnable
            public final void run() {
                ActivityFirmwareUpdate$mDfuProgressListener$1.d(ActivityFirmwareUpdate.this);
            }
        }, 200L);
    }

    @Override // no.nordicsemi.android.dfu.DfuProgressListenerAdapter, no.nordicsemi.android.dfu.DfuProgressListener
    public void onDfuCompleted(@NotNull String deviceAddress) {
        Intrinsics.checkNotNullParameter(deviceAddress, "deviceAddress");
        AppSessionManager.getInstance(this.f4912a).setIsDfuFailed(false);
        Handler handler = new Handler();
        final ActivityFirmwareUpdate activityFirmwareUpdate = this.f4912a;
        handler.postDelayed(new Runnable() { // from class: com.coveiot.android.leonardo.more.activities.aa
            @Override // java.lang.Runnable
            public final void run() {
                ActivityFirmwareUpdate$mDfuProgressListener$1.e(ActivityFirmwareUpdate.this);
            }
        }, 200L);
    }

    @Override // no.nordicsemi.android.dfu.DfuProgressListenerAdapter, no.nordicsemi.android.dfu.DfuProgressListener
    public void onDfuProcessStarting(@NotNull String deviceAddress) {
        ProgressBar progressBar;
        TextView textView;
        Intrinsics.checkNotNullParameter(deviceAddress, "deviceAddress");
        ((TextView) this.f4912a._$_findCachedViewById(R.id.tv_progress_percentage)).setVisibility(8);
        progressBar = this.f4912a.t;
        if (progressBar != null) {
            progressBar.setIndeterminate(true);
        }
        textView = this.f4912a.u;
        if (textView != null) {
            textView.setText(R.string.dfu_status_starting);
        }
    }

    @Override // no.nordicsemi.android.dfu.DfuProgressListenerAdapter, no.nordicsemi.android.dfu.DfuProgressListener
    public void onEnablingDfuMode(@NotNull String deviceAddress) {
        ProgressBar progressBar;
        TextView textView;
        Intrinsics.checkNotNullParameter(deviceAddress, "deviceAddress");
        progressBar = this.f4912a.t;
        if (progressBar != null) {
            progressBar.setIndeterminate(true);
        }
        textView = this.f4912a.u;
        if (textView != null) {
            textView.setText(R.string.dfu_status_switching_to_dfu);
        }
    }

    @Override // no.nordicsemi.android.dfu.DfuProgressListenerAdapter, no.nordicsemi.android.dfu.DfuProgressListener
    public void onError(@NotNull String deviceAddress, int i, int i2, @Nullable String str) {
        SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean;
        Intrinsics.checkNotNullParameter(deviceAddress, "deviceAddress");
        Handler handler = new Handler();
        final ActivityFirmwareUpdate activityFirmwareUpdate = this.f4912a;
        handler.postDelayed(new Runnable() { // from class: com.coveiot.android.leonardo.more.activities.z9
            @Override // java.lang.Runnable
            public final void run() {
                ActivityFirmwareUpdate$mDfuProgressListener$1.f(ActivityFirmwareUpdate.this);
            }
        }, 200L);
        ActivityFirmwareUpdate activityFirmwareUpdate2 = this.f4912a;
        Object[] objArr = new Object[1];
        firmwareBean = activityFirmwareUpdate2.y;
        if (firmwareBean == null) {
            Intrinsics.throwUninitializedPropertyAccessException("firmwareBean");
            firmwareBean = null;
        }
        objArr[0] = firmwareBean.getUpdateVersion();
        String string = activityFirmwareUpdate2.getString(R.string.fw_update_failure_nordic, objArr);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.fw_up…rmwareBean.updateVersion)");
        activityFirmwareUpdate2.L(string, "", true);
        AppSessionManager.getInstance(this.f4912a).setIsDfuFailed(true);
    }

    @Override // no.nordicsemi.android.dfu.DfuProgressListenerAdapter, no.nordicsemi.android.dfu.DfuProgressListener
    public void onFirmwareValidating(@NotNull String deviceAddress) {
        ProgressBar progressBar;
        TextView textView;
        Intrinsics.checkNotNullParameter(deviceAddress, "deviceAddress");
        progressBar = this.f4912a.t;
        if (progressBar != null) {
            progressBar.setIndeterminate(true);
        }
        textView = this.f4912a.u;
        if (textView != null) {
            textView.setText(R.string.dfu_status_validating);
        }
    }

    @Override // no.nordicsemi.android.dfu.DfuProgressListenerAdapter, no.nordicsemi.android.dfu.DfuProgressListener
    public void onProgressChanged(@NotNull String deviceAddress, int i, float f, float f2, int i2, int i3) {
        ProgressBar progressBar;
        ProgressBar progressBar2;
        TextView textView;
        Intrinsics.checkNotNullParameter(deviceAddress, "deviceAddress");
        ActivityFirmwareUpdate activityFirmwareUpdate = this.f4912a;
        int i4 = R.id.tv_progress_percentage;
        ((TextView) activityFirmwareUpdate._$_findCachedViewById(i4)).setVisibility(0);
        progressBar = this.f4912a.t;
        if (progressBar != null) {
            progressBar.setIndeterminate(false);
        }
        progressBar2 = this.f4912a.t;
        if (progressBar2 != null) {
            progressBar2.setProgress(i);
        }
        ((TextView) this.f4912a._$_findCachedViewById(i4)).setText(this.f4912a.getString(R.string.dfu_uploading_percentage, new Object[]{Integer.valueOf(i)}));
        textView = this.f4912a.u;
        if (textView == null) {
            return;
        }
        textView.setText(this.f4912a.getString(R.string.sending));
    }
}
