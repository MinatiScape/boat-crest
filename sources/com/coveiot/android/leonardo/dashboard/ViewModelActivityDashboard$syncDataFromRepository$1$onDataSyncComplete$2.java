package com.coveiot.android.leonardo.dashboard;

import android.content.Context;
import android.os.Handler;
import com.clevertap.android.sdk.Constants;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.repository.Error;
import com.coveiot.repository.datasync.ProgressDataBean;
import com.coveiot.repository.datasync.domainlogic.SyncCompleteListner;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class ViewModelActivityDashboard$syncDataFromRepository$1$onDataSyncComplete$2 implements SyncCompleteListner {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ViewModelActivityDashboard f4688a;

    public ViewModelActivityDashboard$syncDataFromRepository$1$onDataSyncComplete$2(ViewModelActivityDashboard viewModelActivityDashboard) {
        this.f4688a = viewModelActivityDashboard;
    }

    public static final void c(ViewModelActivityDashboard this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getProgressUpdateData().setProgressPercent(this$0.getProgressUpdateData().getProgressPercent() + this$0.getProgressUpdateData().getPercentBySatge());
        this$0.getProgressValueSync().postValue(this$0.getProgressUpdateData());
        Context context = this$0.q;
        Context context2 = null;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context = null;
        }
        if (BleApiManager.getInstance(context).getBleApi() != null) {
            Context context3 = this$0.q;
            if (context3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mContext");
            } else {
                context2 = context3;
            }
            if (BleApiManager.getInstance(context2).getBleApi().getDeviceSupportedFeatures().isSportsModeHistorySupported()) {
                this$0.l();
                return;
            }
        }
        this$0.checkForAgpsFileUpdate();
    }

    public static final void d(ViewModelActivityDashboard this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Context context = this$0.q;
        Context context2 = null;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context = null;
        }
        if (BleApiManager.getInstance(context).getBleApi() != null) {
            Context context3 = this$0.q;
            if (context3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mContext");
            } else {
                context2 = context3;
            }
            if (BleApiManager.getInstance(context2).getBleApi().getDeviceSupportedFeatures().isSportsModeHistorySupported()) {
                this$0.l();
                return;
            }
        }
        this$0.checkForAgpsFileUpdate();
    }

    @Override // com.coveiot.repository.datasync.domainlogic.SyncCompleteListner
    public void onDataSyncComplete() {
        Handler handler = new Handler();
        final ViewModelActivityDashboard viewModelActivityDashboard = this.f4688a;
        handler.postDelayed(new Runnable() { // from class: com.coveiot.android.leonardo.dashboard.k1
            @Override // java.lang.Runnable
            public final void run() {
                ViewModelActivityDashboard$syncDataFromRepository$1$onDataSyncComplete$2.c(ViewModelActivityDashboard.this);
            }
        }, Constants.PN_LARGE_ICON_DOWNLOAD_TIMEOUT_IN_MILLIS);
    }

    @Override // com.coveiot.repository.datasync.domainlogic.SyncCompleteListner
    public void onFailure(@Nullable String str, @Nullable Error error) {
        this.f4688a.getProgressUpdateData().setProgressPercent(this.f4688a.getProgressUpdateData().getProgressPercent() + this.f4688a.getProgressUpdateData().getPercentBySatge());
        this.f4688a.getProgressValueSync().postValue(this.f4688a.getProgressUpdateData());
        Handler handler = new Handler();
        final ViewModelActivityDashboard viewModelActivityDashboard = this.f4688a;
        handler.postDelayed(new Runnable() { // from class: com.coveiot.android.leonardo.dashboard.j1
            @Override // java.lang.Runnable
            public final void run() {
                ViewModelActivityDashboard$syncDataFromRepository$1$onDataSyncComplete$2.d(ViewModelActivityDashboard.this);
            }
        }, 5000L);
    }

    @Override // com.coveiot.repository.datasync.domainlogic.SyncCompleteListner
    public void onProgressUpdate(@Nullable ProgressDataBean progressDataBean) {
    }
}
