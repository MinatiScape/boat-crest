package com.coveiot.android.weeklyreport.viewmodel;

import android.content.Context;
import androidx.lifecycle.ViewModel;
import com.coveiot.android.weeklyreport.R;
import com.coveiot.android.weeklyreport.listeners.OnSuccessListener;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.CoveOnboarding;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.onboarding.model.PostReportIssueReq;
import com.coveiot.coveaccess.onboarding.model.ReportIssueRes;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.data.ProfileData;
import com.coveiot.utils.utility.AppUtils;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes8.dex */
public final class ActivityReportIssueViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f6244a;
    public OnSuccessListener onSuccessListener;

    public ActivityReportIssueViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f6244a = context;
    }

    @NotNull
    public final Context getContext() {
        return this.f6244a;
    }

    @NotNull
    public final OnSuccessListener getOnSuccessListener() {
        OnSuccessListener onSuccessListener = this.onSuccessListener;
        if (onSuccessListener != null) {
            return onSuccessListener;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onSuccessListener");
        return null;
    }

    public final void onCallReportIssueApi(@NotNull String feedback, @NotNull String emailId) {
        Intrinsics.checkNotNullParameter(feedback, "feedback");
        Intrinsics.checkNotNullParameter(emailId, "emailId");
        ProfileData userDetails = SessionManager.getInstance(this.f6244a).getUserDetails();
        if (AppUtils.isNetConnected(this.f6244a)) {
            PostReportIssueReq postReportIssueReq = new PostReportIssueReq();
            postReportIssueReq.setPhoneNo(userDetails.getMobileNumber());
            postReportIssueReq.setFeedback(feedback);
            postReportIssueReq.setEmailId(emailId);
            CoveOnboarding.postReportIssue(postReportIssueReq, new CoveApiListener<ReportIssueRes, CoveApiErrorModel>() { // from class: com.coveiot.android.weeklyreport.viewmodel.ActivityReportIssueViewModel$onCallReportIssueApi$1
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    OnSuccessListener onSuccessListener = ActivityReportIssueViewModel.this.getOnSuccessListener();
                    String string = ActivityReportIssueViewModel.this.getContext().getResources().getString(R.string.somethings_went_wrong);
                    Intrinsics.checkNotNullExpressionValue(string, "context.resources.getStr…ng.somethings_went_wrong)");
                    onSuccessListener.onDataFailure(string);
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable ReportIssueRes reportIssueRes) {
                    ActivityReportIssueViewModel.this.getOnSuccessListener().onSuccess();
                }
            });
            return;
        }
        OnSuccessListener onSuccessListener = getOnSuccessListener();
        String string = this.f6244a.getResources().getString(R.string.please_check_your_internet);
        Intrinsics.checkNotNullExpressionValue(string, "context.resources.getStr…ease_check_your_internet)");
        onSuccessListener.onDataFailure(string);
    }

    public final void setOnSuccessListener(@NotNull OnSuccessListener onSuccessListener) {
        Intrinsics.checkNotNullParameter(onSuccessListener, "<set-?>");
        this.onSuccessListener = onSuccessListener;
    }
}
