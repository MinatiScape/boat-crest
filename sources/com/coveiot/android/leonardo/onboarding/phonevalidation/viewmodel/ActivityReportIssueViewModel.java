package com.coveiot.android.leonardo.onboarding.phonevalidation.viewmodel;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.DeviceSupportedFeatures;
import com.coveiot.android.boat.R;
import com.coveiot.android.dashboard2.listener.ViewModelListener;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.theme.utils.ThemeConstants;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.CoveOnboarding;
import com.coveiot.coveaccess.CoveUserAppSettings;
import com.coveiot.coveaccess.feedback.FeedbackCategoriesRes;
import com.coveiot.coveaccess.feedback.PostFeedBackReq;
import com.coveiot.coveaccess.feedback.PostFeedBackRes;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.onboarding.model.PostReportIssueReq;
import com.coveiot.coveaccess.onboarding.model.ReportIssueRes;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.ktx.Firebase;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.ktx.RemoteConfigKt;
import com.mappls.sdk.navigation.util.SavingTrackHelper;
import java.util.Iterator;
import kotlin.collections.IntIterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.h;
import kotlin.text.m;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityReportIssueViewModel extends ViewModel {
    @NotNull

    /* renamed from: a */
    public final Context f5309a;
    @NotNull
    public MutableLiveData<String[]> b;
    @NotNull
    public MutableLiveData<String[]> c;
    @NotNull
    public String[] d;
    @NotNull
    public String[] e;
    @NotNull
    public MutableLiveData<Boolean> f;
    public ViewModelListener viewModelListener;

    public ActivityReportIssueViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f5309a = context;
        this.b = new MutableLiveData<>();
        this.c = new MutableLiveData<>();
        this.d = new String[0];
        this.e = new String[0];
        this.f = new MutableLiveData<>();
    }

    public static final void d(ActivityReportIssueViewModel this$0, Exception it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        LogHelper.e("contactUsCheck", "Remote Config Failed");
        this$0.f.postValue(Boolean.FALSE);
    }

    public static final void e(final FirebaseRemoteConfig remoteConfig, final ActivityReportIssueViewModel this$0, Task task) {
        Intrinsics.checkNotNullParameter(remoteConfig, "$remoteConfig");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(task, "task");
        if (task.isSuccessful()) {
            remoteConfig.activate().addOnCompleteListener(new OnCompleteListener() { // from class: com.coveiot.android.leonardo.onboarding.phonevalidation.viewmodel.a
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public final void onComplete(Task task2) {
                    ActivityReportIssueViewModel.f(FirebaseRemoteConfig.this, this$0, task2);
                }
            });
            return;
        }
        LogHelper.e("contactUsCheck", "Remote Config Failed");
        this$0.f.postValue(Boolean.FALSE);
    }

    public static final void f(FirebaseRemoteConfig remoteConfig, ActivityReportIssueViewModel this$0, Task it) {
        Intrinsics.checkNotNullParameter(remoteConfig, "$remoteConfig");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        String string = remoteConfig.getString(ThemeConstants.CONTACTUS_PREFERMODE_VISIBILITY.getValue());
        Intrinsics.checkNotNullExpressionValue(string, "remoteConfig.getString(T…FERMODE_VISIBILITY.value)");
        this$0.f.postValue(Boolean.valueOf(Boolean.parseBoolean(string)));
        LogHelper.d("contactUsCheck", "Config " + string);
    }

    public static /* synthetic */ void onCallFeedbackApi$default(ActivityReportIssueViewModel activityReportIssueViewModel, String str, String str2, String str3, String str4, String str5, int i, Object obj) {
        activityReportIssueViewModel.onCallFeedbackApi(str, str2, str3, (i & 8) != 0 ? null : str4, (i & 16) != 0 ? null : str5);
    }

    public final void getContactUsFirebaseConfig() {
        if (this.f5309a != null) {
            final FirebaseRemoteConfig remoteConfig = RemoteConfigKt.getRemoteConfig(Firebase.INSTANCE);
            remoteConfig.fetch(10L).addOnFailureListener(new OnFailureListener() { // from class: com.coveiot.android.leonardo.onboarding.phonevalidation.viewmodel.c
                @Override // com.google.android.gms.tasks.OnFailureListener
                public final void onFailure(Exception exc) {
                    ActivityReportIssueViewModel.d(ActivityReportIssueViewModel.this, exc);
                }
            }).addOnCompleteListener(new OnCompleteListener() { // from class: com.coveiot.android.leonardo.onboarding.phonevalidation.viewmodel.b
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public final void onComplete(Task task) {
                    ActivityReportIssueViewModel.e(FirebaseRemoteConfig.this, this, task);
                }
            });
        }
    }

    @NotNull
    public final Context getContext() {
        return this.f5309a;
    }

    @NotNull
    public final String[] getFeedbackCategoryArray() {
        return this.d;
    }

    @NotNull
    public final String[] getFeedbackCategoryArrayForUI() {
        return this.e;
    }

    @NotNull
    public final MutableLiveData<String[]> getMSpinnerData() {
        return this.b;
    }

    @NotNull
    public final MutableLiveData<String[]> getMSpinnerDataForUI() {
        return this.c;
    }

    @NotNull
    public final MutableLiveData<Boolean> getPreferredContactVisibility() {
        return this.f;
    }

    @NotNull
    public final ViewModelListener getViewModelListener() {
        ViewModelListener viewModelListener = this.viewModelListener;
        if (viewModelListener != null) {
            return viewModelListener;
        }
        Intrinsics.throwUninitializedPropertyAccessException("viewModelListener");
        return null;
    }

    public final boolean isBTCallingSupported() {
        DeviceSupportedFeatures deviceSupportedFeatures;
        DeviceSupportedFeatures deviceSupportedFeatures2;
        BleApi bleApi = BleApiManager.getInstance(this.f5309a).getBleApi();
        Boolean bool = null;
        Boolean valueOf = (bleApi == null || (deviceSupportedFeatures2 = bleApi.getDeviceSupportedFeatures()) == null) ? null : Boolean.valueOf(deviceSupportedFeatures2.isKahaBTCallSupported());
        Intrinsics.checkNotNull(valueOf);
        if (!valueOf.booleanValue()) {
            BleApi bleApi2 = BleApiManager.getInstance(this.f5309a).getBleApi();
            if (bleApi2 != null && (deviceSupportedFeatures = bleApi2.getDeviceSupportedFeatures()) != null) {
                bool = Boolean.valueOf(deviceSupportedFeatures.isBTCallingSupported());
            }
            Intrinsics.checkNotNull(bool);
            if (!bool.booleanValue()) {
                return false;
            }
        }
        return true;
    }

    public final void onCallCategoryApi() {
        if (AppUtils.isNetConnected(this.f5309a)) {
            CoveUserAppSettings.getFeedBackCategories(new CoveApiListener<FeedbackCategoriesRes, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.onboarding.phonevalidation.viewmodel.ActivityReportIssueViewModel$onCallCategoryApi$1
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@NotNull CoveApiErrorModel coveApiErrorModel) {
                    Intrinsics.checkNotNullParameter(coveApiErrorModel, "coveApiErrorModel");
                    ViewModelListener viewModelListener = ActivityReportIssueViewModel.this.getViewModelListener();
                    String string = ActivityReportIssueViewModel.this.getContext().getResources().getString(R.string.somethings_went_wrong);
                    Intrinsics.checkNotNullExpressionValue(string, "context.resources.getStr…ng.somethings_went_wrong)");
                    viewModelListener.onDataFailure(string);
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@NotNull FeedbackCategoriesRes feedbackCategoriesRes) {
                    Intrinsics.checkNotNullParameter(feedbackCategoriesRes, "feedbackCategoriesRes");
                    int size = feedbackCategoriesRes.getFields().size();
                    for (int i = 0; i < size; i++) {
                        if (m.equals(feedbackCategoriesRes.getFields().get(i).getName(), SavingTrackHelper.POINT_COL_CATEGORY, true)) {
                            ActivityReportIssueViewModel activityReportIssueViewModel = ActivityReportIssueViewModel.this;
                            int size2 = activityReportIssueViewModel.isBTCallingSupported() ? feedbackCategoriesRes.getFields().get(i).getOptions().size() + 1 : feedbackCategoriesRes.getFields().get(i).getOptions().size() + 0;
                            String[] strArr = new String[size2];
                            for (int i2 = 0; i2 < size2; i2++) {
                                strArr[i2] = "";
                            }
                            activityReportIssueViewModel.setFeedbackCategoryArray(strArr);
                            ActivityReportIssueViewModel activityReportIssueViewModel2 = ActivityReportIssueViewModel.this;
                            int size3 = activityReportIssueViewModel2.isBTCallingSupported() ? feedbackCategoriesRes.getFields().get(i).getOptions().size() + 1 : feedbackCategoriesRes.getFields().get(i).getOptions().size() + 0;
                            String[] strArr2 = new String[size3];
                            for (int i3 = 0; i3 < size3; i3++) {
                                strArr2[i3] = "";
                            }
                            activityReportIssueViewModel2.setFeedbackCategoryArrayForUI(strArr2);
                            IntRange until = h.until(0, feedbackCategoriesRes.getFields().get(i).getOptions().size());
                            ActivityReportIssueViewModel activityReportIssueViewModel3 = ActivityReportIssueViewModel.this;
                            Iterator<Integer> it = until.iterator();
                            while (it.hasNext()) {
                                int nextInt = ((IntIterator) it).nextInt();
                                String[] feedbackCategoryArray = activityReportIssueViewModel3.getFeedbackCategoryArray();
                                Intrinsics.checkNotNull(feedbackCategoryArray);
                                String value = feedbackCategoriesRes.getFields().get(i).getOptions().get(nextInt).getValue();
                                Intrinsics.checkNotNullExpressionValue(value, "feedbackCategoriesRes.fields[i].options[j].value");
                                feedbackCategoryArray[nextInt] = value;
                                String[] feedbackCategoryArrayForUI = activityReportIssueViewModel3.getFeedbackCategoryArrayForUI();
                                String text = feedbackCategoriesRes.getFields().get(i).getOptions().get(nextInt).getText();
                                Intrinsics.checkNotNullExpressionValue(text, "feedbackCategoriesRes.fields[i].options[j].text");
                                feedbackCategoryArrayForUI[nextInt] = text;
                            }
                        }
                    }
                    if (ActivityReportIssueViewModel.this.isBTCallingSupported()) {
                        String string = ActivityReportIssueViewModel.this.getContext().getString(R.string.bluetooth_calling);
                        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.bluetooth_calling)");
                        ActivityReportIssueViewModel.this.getFeedbackCategoryArray()[ActivityReportIssueViewModel.this.getFeedbackCategoryArray().length - 1] = string;
                        String string2 = ActivityReportIssueViewModel.this.getContext().getString(R.string.bluetooth_calling);
                        Intrinsics.checkNotNullExpressionValue(string2, "context.getString(R.string.bluetooth_calling)");
                        ActivityReportIssueViewModel.this.getFeedbackCategoryArrayForUI()[ActivityReportIssueViewModel.this.getFeedbackCategoryArrayForUI().length - 1] = string2;
                    }
                    ActivityReportIssueViewModel.this.getMSpinnerData().setValue(ActivityReportIssueViewModel.this.getFeedbackCategoryArray());
                    ActivityReportIssueViewModel.this.getMSpinnerDataForUI().setValue(ActivityReportIssueViewModel.this.getFeedbackCategoryArrayForUI());
                }
            });
            return;
        }
        ViewModelListener viewModelListener = getViewModelListener();
        String string = this.f5309a.getResources().getString(R.string.please_check_your_internet);
        Intrinsics.checkNotNullExpressionValue(string, "context.resources.getStr…ease_check_your_internet)");
        viewModelListener.onDataFailure(string);
    }

    public final void onCallFeedbackApi(@Nullable String str, @NotNull String email, @NotNull final String feedback, @Nullable String str2, @Nullable String str3) {
        Intrinsics.checkNotNullParameter(email, "email");
        Intrinsics.checkNotNullParameter(feedback, "feedback");
        if (AppUtils.isNetConnected(this.f5309a)) {
            PostFeedBackReq postFeedBackReq = new PostFeedBackReq();
            postFeedBackReq.setMessage(feedback);
            postFeedBackReq.setEmailId(email);
            postFeedBackReq.setSubject(str);
            if (!AppUtils.isEmpty(str2)) {
                postFeedBackReq.setName(str2);
            }
            if (!AppUtils.isEmpty(str3)) {
                postFeedBackReq.setContactPreference(str3);
            }
            CoveUserAppSettings.postFeedbackToServer(postFeedBackReq, new CoveApiListener<PostFeedBackRes, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.onboarding.phonevalidation.viewmodel.ActivityReportIssueViewModel$onCallFeedbackApi$1
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    if (feedback.length() < 3) {
                        ViewModelListener viewModelListener = ActivityReportIssueViewModel.this.getViewModelListener();
                        String string = ActivityReportIssueViewModel.this.getContext().getString(R.string.minimum_three_character_limit);
                        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.stri…um_three_character_limit)");
                        viewModelListener.onDataFailure(string);
                        return;
                    }
                    ViewModelListener viewModelListener2 = ActivityReportIssueViewModel.this.getViewModelListener();
                    String string2 = ActivityReportIssueViewModel.this.getContext().getResources().getString(R.string.somethings_went_wrong);
                    Intrinsics.checkNotNullExpressionValue(string2, "context.resources.getStr…ng.somethings_went_wrong)");
                    viewModelListener2.onDataFailure(string2);
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable PostFeedBackRes postFeedBackRes) {
                    ActivityReportIssueViewModel.this.getViewModelListener().onSuccess();
                }
            });
            return;
        }
        ViewModelListener viewModelListener = getViewModelListener();
        String string = this.f5309a.getResources().getString(R.string.please_check_your_internet);
        Intrinsics.checkNotNullExpressionValue(string, "context.resources.getStr…ease_check_your_internet)");
        viewModelListener.onDataFailure(string);
    }

    public final void onCallReportIssueApi(@Nullable String str, @NotNull String feedback, @NotNull String emailId) {
        Intrinsics.checkNotNullParameter(feedback, "feedback");
        Intrinsics.checkNotNullParameter(emailId, "emailId");
        if (AppUtils.isNetConnected(this.f5309a)) {
            PostReportIssueReq postReportIssueReq = new PostReportIssueReq();
            postReportIssueReq.setPhoneNo(str != null ? m.replace$default(str, " - ", AppConstants.EMPTY_STRING.getValue(), false, 4, (Object) null) : null);
            postReportIssueReq.setFeedback(feedback);
            postReportIssueReq.setEmailId(emailId);
            CoveOnboarding.postReportIssue(postReportIssueReq, new CoveApiListener<ReportIssueRes, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.onboarding.phonevalidation.viewmodel.ActivityReportIssueViewModel$onCallReportIssueApi$1
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    ViewModelListener viewModelListener = ActivityReportIssueViewModel.this.getViewModelListener();
                    String string = ActivityReportIssueViewModel.this.getContext().getResources().getString(R.string.somethings_went_wrong);
                    Intrinsics.checkNotNullExpressionValue(string, "context.resources.getStr…ng.somethings_went_wrong)");
                    viewModelListener.onDataFailure(string);
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable ReportIssueRes reportIssueRes) {
                    ActivityReportIssueViewModel.this.getViewModelListener().onSuccess();
                }
            });
            return;
        }
        ViewModelListener viewModelListener = getViewModelListener();
        String string = this.f5309a.getResources().getString(R.string.please_check_your_internet);
        Intrinsics.checkNotNullExpressionValue(string, "context.resources.getStr…ease_check_your_internet)");
        viewModelListener.onDataFailure(string);
    }

    public final void setFeedbackCategoryArray(@NotNull String[] strArr) {
        Intrinsics.checkNotNullParameter(strArr, "<set-?>");
        this.d = strArr;
    }

    public final void setFeedbackCategoryArrayForUI(@NotNull String[] strArr) {
        Intrinsics.checkNotNullParameter(strArr, "<set-?>");
        this.e = strArr;
    }

    public final void setMSpinnerData(@NotNull MutableLiveData<String[]> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.b = mutableLiveData;
    }

    public final void setMSpinnerDataForUI(@NotNull MutableLiveData<String[]> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.c = mutableLiveData;
    }

    public final void setPreferredContactVisibility(@NotNull MutableLiveData<Boolean> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.f = mutableLiveData;
    }

    public final void setViewModelListener(@NotNull ViewModelListener viewModelListener) {
        Intrinsics.checkNotNullParameter(viewModelListener, "<set-?>");
        this.viewModelListener = viewModelListener;
    }
}
