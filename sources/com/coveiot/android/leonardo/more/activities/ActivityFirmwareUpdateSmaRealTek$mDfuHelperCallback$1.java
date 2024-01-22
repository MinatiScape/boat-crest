package com.coveiot.android.leonardo.more.activities;

import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.coveiot.android.boat.R;
import com.coveiot.coveaccess.userappsetting.SoftwareUpdateRes;
import com.coveiot.utils.utility.LogHelper;
import com.realsil.sdk.dfu.model.DfuProgressInfo;
import com.realsil.sdk.dfu.model.Throughput;
import com.realsil.sdk.dfu.utils.DfuAdapter;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityFirmwareUpdateSmaRealTek$mDfuHelperCallback$1 extends DfuAdapter.DfuHelperCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ActivityFirmwareUpdateSmaRealTek f4958a;

    public ActivityFirmwareUpdateSmaRealTek$mDfuHelperCallback$1(ActivityFirmwareUpdateSmaRealTek activityFirmwareUpdateSmaRealTek) {
        this.f4958a = activityFirmwareUpdateSmaRealTek;
    }

    public static final void d(ActivityFirmwareUpdateSmaRealTek this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Object systemService = this$0.getSystemService("notification");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
        ((NotificationManager) systemService).cancel(283);
    }

    public static final void e(ActivityFirmwareUpdateSmaRealTek this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.E();
        Object systemService = this$0.getSystemService("notification");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
        ((NotificationManager) systemService).cancel(283);
    }

    public static final void f(ActivityFirmwareUpdateSmaRealTek this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.F();
        Object systemService = this$0.getSystemService("notification");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
        ((NotificationManager) systemService).cancel(283);
    }

    @Override // com.realsil.sdk.dfu.utils.DfuAdapter.DfuHelperCallback
    @SuppressLint({"StringFormatInvalid"})
    public void onError(int i, int i2) {
        Handler handler;
        SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean;
        handler = this.f4958a.w;
        final ActivityFirmwareUpdateSmaRealTek activityFirmwareUpdateSmaRealTek = this.f4958a;
        handler.postDelayed(new Runnable() { // from class: com.coveiot.android.leonardo.more.activities.yd
            @Override // java.lang.Runnable
            public final void run() {
                ActivityFirmwareUpdateSmaRealTek$mDfuHelperCallback$1.d(ActivityFirmwareUpdateSmaRealTek.this);
            }
        }, 200L);
        ActivityFirmwareUpdateSmaRealTek activityFirmwareUpdateSmaRealTek2 = this.f4958a;
        Object[] objArr = new Object[1];
        firmwareBean = activityFirmwareUpdateSmaRealTek2.u;
        if (firmwareBean == null) {
            Intrinsics.throwUninitializedPropertyAccessException("firmwareBean");
            firmwareBean = null;
        }
        objArr[0] = firmwareBean.getUpdateVersion();
        String string = activityFirmwareUpdateSmaRealTek2.getString(R.string.fw_update_failure, objArr);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.fw_up…rmwareBean.updateVersion)");
        activityFirmwareUpdateSmaRealTek2.I(string, "");
        String tag = this.f4958a.getTAG();
        LogHelper.d(tag, "onError message  " + i);
    }

    @Override // com.realsil.sdk.dfu.utils.DfuAdapter.DfuHelperCallback
    public void onProcessStateChanged(int i, @Nullable Throughput throughput) {
        Handler handler;
        if (i == 258) {
            handler = this.f4958a.w;
            final ActivityFirmwareUpdateSmaRealTek activityFirmwareUpdateSmaRealTek = this.f4958a;
            handler.postDelayed(new Runnable() { // from class: com.coveiot.android.leonardo.more.activities.xd
                @Override // java.lang.Runnable
                public final void run() {
                    ActivityFirmwareUpdateSmaRealTek$mDfuHelperCallback$1.e(ActivityFirmwareUpdateSmaRealTek.this);
                }
            }, 200L);
            LogHelper.d(this.f4958a.getTAG(), "onDfuCompleted");
        }
    }

    @Override // com.realsil.sdk.dfu.utils.DfuAdapter.DfuHelperCallback
    public void onProgressChanged(@NotNull DfuProgressInfo progressInfo) {
        ProgressBar progressBar;
        ProgressBar progressBar2;
        TextView textView;
        Intrinsics.checkNotNullParameter(progressInfo, "progressInfo");
        progressBar = this.f4958a.q;
        if (progressBar != null) {
            progressBar.setIndeterminate(false);
        }
        progressBar2 = this.f4958a.q;
        if (progressBar2 != null) {
            progressBar2.setProgress(progressInfo.getProgress());
        }
        ActivityFirmwareUpdateSmaRealTek activityFirmwareUpdateSmaRealTek = this.f4958a;
        int i = R.id.tv_progress_percentage;
        ((TextView) activityFirmwareUpdateSmaRealTek._$_findCachedViewById(i)).setVisibility(0);
        ((TextView) this.f4958a._$_findCachedViewById(i)).setText(this.f4958a.getString(R.string.dfu_uploading_percentage, new Object[]{Integer.valueOf(progressInfo.getProgress())}));
        textView = this.f4958a.r;
        if (textView != null) {
            textView.setText(this.f4958a.getString(R.string.sending));
        }
        LogHelper.d(this.f4958a.getTAG(), "onProgressChanged");
    }

    @Override // com.realsil.sdk.dfu.utils.DfuAdapter.DfuHelperCallback
    public void onStateChanged(int i) {
        ProgressBar progressBar;
        TextView textView;
        ProgressBar progressBar2;
        TextView textView2;
        ProgressBar progressBar3;
        TextView textView3;
        TextView textView4;
        Handler handler;
        ProgressBar progressBar4;
        TextView textView5;
        ProgressBar progressBar5;
        TextView textView6;
        if (i == 258) {
            progressBar = this.f4958a.q;
            if (progressBar != null) {
                progressBar.setIndeterminate(true);
            }
            textView = this.f4958a.r;
            if (textView != null) {
                textView.setText("STATE_INIT_OK");
            }
            LogHelper.d(this.f4958a.getTAG(), "STATE_INIT_OK");
        } else if (i == 527) {
            progressBar2 = this.f4958a.q;
            if (progressBar2 != null) {
                progressBar2.setIndeterminate(true);
            }
            textView2 = this.f4958a.r;
            if (textView2 != null) {
                textView2.setText("STATE_PREPARED");
            }
            LogHelper.d(this.f4958a.getTAG(), "STATE_PREPARED");
        } else if (i == 1025) {
            progressBar3 = this.f4958a.q;
            if (progressBar3 != null) {
                progressBar3.setIndeterminate(true);
            }
            textView3 = this.f4958a.r;
            if (textView3 != null) {
                textView3.setText("STATE_OTA_PROCESSING");
            }
            LogHelper.d(this.f4958a.getTAG(), "STATE_OTA_PROCESSING");
        } else if (i == 8193) {
            textView4 = this.f4958a.r;
            if (textView4 != null) {
                textView4.setText(R.string.dfu_status_aborted);
            }
            handler = this.f4958a.w;
            final ActivityFirmwareUpdateSmaRealTek activityFirmwareUpdateSmaRealTek = this.f4958a;
            handler.postDelayed(new Runnable() { // from class: com.coveiot.android.leonardo.more.activities.zd
                @Override // java.lang.Runnable
                public final void run() {
                    ActivityFirmwareUpdateSmaRealTek$mDfuHelperCallback$1.f(ActivityFirmwareUpdateSmaRealTek.this);
                }
            }, 200L);
            LogHelper.d(this.f4958a.getTAG(), "onDfuAborted");
        } else if (i == 4097) {
            progressBar4 = this.f4958a.q;
            if (progressBar4 != null) {
                progressBar4.setIndeterminate(true);
            }
            textView5 = this.f4958a.r;
            if (textView5 != null) {
                textView5.setText("STATE_DISCONNECTED");
            }
            LogHelper.d(this.f4958a.getTAG(), "STATE_DISCONNECTED");
        } else if (i != 4098) {
        } else {
            progressBar5 = this.f4958a.q;
            if (progressBar5 != null) {
                progressBar5.setIndeterminate(true);
            }
            textView6 = this.f4958a.r;
            if (textView6 != null) {
                textView6.setText("STATE_CONNECT_FAILED");
            }
            LogHelper.d(this.f4958a.getTAG(), "STATE_CONNECT_FAILED");
        }
    }
}
