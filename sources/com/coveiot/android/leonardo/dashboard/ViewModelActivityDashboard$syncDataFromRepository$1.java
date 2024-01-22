package com.coveiot.android.leonardo.dashboard;

import android.content.Context;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.dashboard.model.ProgressUpdateData;
import com.coveiot.android.sleepenergyscore.energymeter.EnergyScoreApiCall;
import com.coveiot.android.sleepenergyscore.sleepscore.SleepScoreApiCall;
import com.coveiot.android.sleepenergyscore.sleepscore.database.SleepScoreRepository;
import com.coveiot.android.sleepenergyscore.sleepscore.database.entities.SleepScoreData;
import com.coveiot.repository.Error;
import com.coveiot.repository.RepositoryUtils;
import com.coveiot.repository.datasync.ProgressDataBean;
import com.coveiot.repository.datasync.domainlogic.SyncCompleteListner;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.Calendar;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class ViewModelActivityDashboard$syncDataFromRepository$1 implements SyncCompleteListner {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ViewModelActivityDashboard f4687a;

    public ViewModelActivityDashboard$syncDataFromRepository$1(ViewModelActivityDashboard viewModelActivityDashboard) {
        this.f4687a = viewModelActivityDashboard;
    }

    public static final void c(ViewModelActivityDashboard this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Context context = this$0.q;
        Context context2 = null;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context = null;
        }
        if (AppUtils.isNetConnected(context)) {
            Calendar calendar = Calendar.getInstance();
            Intrinsics.checkNotNullExpressionValue(calendar, "getInstance()");
            calendar.add(6, -4);
            SleepScoreRepository.Companion companion = SleepScoreRepository.Companion;
            Context context3 = this$0.q;
            if (context3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mContext");
                context3 = null;
            }
            SleepScoreRepository singletonHolder = companion.getInstance(context3);
            String formatDate = RepositoryUtils.formatDate(Calendar.getInstance().getTime(), "yyyy-MM-dd");
            Intrinsics.checkNotNullExpressionValue(formatDate, "formatDate(\n            …                        )");
            Context context4 = this$0.q;
            if (context4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mContext");
                context4 = null;
            }
            SleepScoreData sleepScoreData = singletonHolder.getSleepScoreData(formatDate, BleApiManager.getInstance(context4).getBleApi().getMacAddress());
            if ((sleepScoreData != null ? sleepScoreData.getSleepScore() : null) == null) {
                SleepScoreApiCall sleepScoreApiCall = SleepScoreApiCall.INSTANCE;
                Calendar calendar2 = Calendar.getInstance();
                Intrinsics.checkNotNullExpressionValue(calendar2, "getInstance()");
                Context context5 = this$0.q;
                if (context5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mContext");
                    context5 = null;
                }
                Context context6 = this$0.q;
                if (context6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mContext");
                } else {
                    context2 = context6;
                }
                String string = context2.getResources().getString(R.string.home);
                Intrinsics.checkNotNullExpressionValue(string, "mContext.resources.getSt…           R.string.home)");
                sleepScoreApiCall.getSleepScoreBatchApiCall(calendar, calendar2, context5, string);
                return;
            }
            EnergyScoreApiCall energyScoreApiCall = EnergyScoreApiCall.INSTANCE;
            Calendar calendar3 = Calendar.getInstance();
            Intrinsics.checkNotNullExpressionValue(calendar3, "getInstance()");
            Context context7 = this$0.q;
            if (context7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mContext");
                context7 = null;
            }
            Context context8 = this$0.q;
            if (context8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mContext");
            } else {
                context2 = context8;
            }
            String string2 = context2.getResources().getString(R.string.home);
            Intrinsics.checkNotNullExpressionValue(string2, "mContext.resources.getSt…nergyscore.R.string.home)");
            energyScoreApiCall.energyScoreBatchApiCall(calendar, calendar3, context7, string2);
        }
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

    /* JADX WARN: Code restructure failed: missing block: B:34:0x00ad, code lost:
        if (r0.isEastApexDevice(r4) != false) goto L57;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v21, types: [android.content.Context] */
    @Override // com.coveiot.repository.datasync.domainlogic.SyncCompleteListner
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onDataSyncComplete() {
        /*
            Method dump skipped, instructions count: 574
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboard$syncDataFromRepository$1.onDataSyncComplete():void");
    }

    @Override // com.coveiot.repository.datasync.domainlogic.SyncCompleteListner
    public void onFailure(@Nullable String str, @Nullable Error error) {
        this.f4687a.W(str, error);
    }

    @Override // com.coveiot.repository.datasync.domainlogic.SyncCompleteListner
    public void onProgressUpdate(@Nullable ProgressDataBean progressDataBean) {
        String str = this.f4687a.e;
        StringBuilder sb = new StringBuilder();
        sb.append("sync_progress_precent");
        Intrinsics.checkNotNull(progressDataBean);
        sb.append(progressDataBean.getProgress());
        sb.append(" stage type *** ");
        sb.append(progressDataBean.getActivityType());
        sb.append(" total stage *** ");
        sb.append(progressDataBean.getTotalStage());
        sb.append("  stage progress *** ");
        sb.append(progressDataBean.getPercentByStage());
        LogHelper.d(str, sb.toString());
        this.f4687a.getProgressUpdateData().setPercentBySatge(progressDataBean.getPercentByStage());
        this.f4687a.getProgressUpdateData().setProgressPercent(progressDataBean.getProgress());
        String lowerCase = progressDataBean.getActivityType().name().toLowerCase();
        Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase()");
        Context context = null;
        if (lowerCase != null && StringsKt__StringsKt.contains$default((CharSequence) lowerCase, (CharSequence) "_", false, 2, (Object) null)) {
            lowerCase = kotlin.text.m.replace(lowerCase, "_", HexStringBuilder.DEFAULT_SEPARATOR, true);
        }
        ProgressUpdateData progressUpdateData = this.f4687a.getProgressUpdateData();
        StringBuilder sb2 = new StringBuilder();
        Context context2 = this.f4687a.q;
        if (context2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
        } else {
            context = context2;
        }
        sb2.append(context.getResources().getString(R.string.syncing));
        sb2.append(' ');
        sb2.append(lowerCase);
        sb2.append("..");
        progressUpdateData.setTextProgress(sb2.toString());
        this.f4687a.getProgressValueSync().postValue(this.f4687a.getProgressUpdateData());
    }
}
