package com.coveiot.android.leonardo.onboarding.phonevalidation.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.boat.R;
import com.coveiot.android.dashboard2.listener.ViewModelListener;
import com.coveiot.android.leonardo.onboarding.phonevalidation.viewmodel.ActivityReportIssueViewModel;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.leonardo.utils.ViewModelFactory;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.BottomSheetDialogOneButtonOneTitle;
import com.coveiot.android.theme.FirebaseConstants;
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.utils.utility.AppUtils;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityReportIssue extends BaseActivity implements ViewModelListener, Observer<String[]> {
    @Nullable
    public String p;
    @Nullable
    public String r;
    public ActivityReportIssueViewModel viewModel;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public String q = "";

    public static final void t(ActivityReportIssue this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.setResult(-1, this$0.getIntent());
        this$0.finish();
    }

    public static final void u(ActivityReportIssue this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.r == null) {
            AnalyticsLog analyticsLog = new AnalyticsLog();
            analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
            analyticsLog.setScreenName(FirebaseEventParams.ScreenName.OTP_REPORT_ISSUE_SCREEN.getValue());
            analyticsLog.setDescription(FirebaseEventParams.Description.SUBMIT_OTP_FEEDBACK.getValue());
            analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.SUBMIT_BUTTON.getValue());
            CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        } else {
            AnalyticsLog analyticsLog2 = new AnalyticsLog();
            analyticsLog2.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
            analyticsLog2.setScreenName(FirebaseEventParams.ScreenName.FEEDBACK_SCREEN.getValue());
            String str = this$0.getViewModel().getFeedbackCategoryArray()[((Spinner) this$0._$_findCachedViewById(R.id.spinner_report_issue)).getSelectedItemPosition()].toString();
            Intrinsics.checkNotNull(str);
            analyticsLog2.setDescription(str);
            analyticsLog2.setUiElementName(FirebaseEventParams.UiElementName.SUBMIT_BUTTON.getValue());
            CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog2);
        }
        int i = R.id.edittext_email_report_issue;
        String obj = StringsKt__StringsKt.trim(((EditText) this$0._$_findCachedViewById(i)).getText().toString()).toString();
        if (!(obj.length() == 0) && !AppUtils.isValidEmail(obj)) {
            Toast.makeText(this$0, this$0.getString(R.string.invalid_email_id), 0).show();
        } else if (this$0.r == null) {
            this$0.getViewModel().onCallReportIssueApi(this$0.p, StringsKt__StringsKt.trim(((EditText) this$0._$_findCachedViewById(R.id.edittext_report_issue)).getText().toString()).toString(), obj);
        } else {
            ActivityReportIssueViewModel.onCallFeedbackApi$default(this$0.getViewModel(), ((Spinner) this$0._$_findCachedViewById(R.id.spinner_report_issue)).getSelectedItem().toString(), StringsKt__StringsKt.trim(((EditText) this$0._$_findCachedViewById(i)).getText().toString()).toString(), StringsKt__StringsKt.trim(((EditText) this$0._$_findCachedViewById(R.id.edittext_report_issue)).getText().toString()).toString(), null, null, 24, null);
        }
    }

    public static final void v(BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle, ActivityReportIssue this$0, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonOneTitle, "$bottomSheetDialogOneButtonOneTitle");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        bottomSheetDialogOneButtonOneTitle.dismiss();
        this$0.setResult(-1, this$0.getIntent());
        this$0.finish();
    }

    @Override // com.coveiot.android.theme.BaseActivity
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.coveiot.android.theme.BaseActivity
    @Nullable
    public View _$_findCachedViewById(int i) {
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view == null) {
            View findViewById = findViewById(i);
            if (findViewById != null) {
                map.put(Integer.valueOf(i), findViewById);
                return findViewById;
            }
            return null;
        }
        return view;
    }

    @Nullable
    public final String getPhoneNumber() {
        return this.p;
    }

    @Override // android.app.Activity
    @NotNull
    public final String getTitle() {
        return this.q;
    }

    @Nullable
    public final String getType() {
        return this.r;
    }

    @NotNull
    public final ActivityReportIssueViewModel getViewModel() {
        ActivityReportIssueViewModel activityReportIssueViewModel = this.viewModel;
        if (activityReportIssueViewModel != null) {
            return activityReportIssueViewModel;
        }
        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        return null;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (this.r == null) {
            AnalyticsLog analyticsLog = new AnalyticsLog();
            analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
            analyticsLog.setScreenName(FirebaseEventParams.ScreenName.OTP_REPORT_ISSUE_SCREEN.getValue());
            analyticsLog.setDescription(FirebaseEventParams.Description.OPEN_PHONE_NUMBER_SCREEN.getValue());
            analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.BACK_BUTTON.getValue());
            CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        } else {
            AnalyticsLog analyticsLog2 = new AnalyticsLog();
            analyticsLog2.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
            analyticsLog2.setScreenName(FirebaseEventParams.ScreenName.FEEDBACK_SCREEN.getValue());
            analyticsLog2.setDescription(FirebaseEventParams.Description.OPEN_HELP_FEEDBACK_SCREEN.getValue());
            analyticsLog2.setUiElementName(FirebaseEventParams.UiElementName.BACK_BUTTON.getValue());
            CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog2);
        }
        setResult(-1, getIntent());
        finish();
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_report_issue);
        setViewModel((ActivityReportIssueViewModel) ViewModelProviders.of(this, new ViewModelFactory(this)).get(ActivityReportIssueViewModel.class));
        getViewModel().setViewModelListener(this);
        this.p = getIntent().getStringExtra(AppConstants.PHONE_NUMBER.getValue());
        this.r = getIntent().getStringExtra("type");
        Intent intent = getIntent();
        AppConstants appConstants = AppConstants.INTENT_EXTRA_TITLE;
        if (intent.hasExtra(appConstants.getValue())) {
            String stringExtra = getIntent().getStringExtra(appConstants.getValue());
            Intrinsics.checkNotNull(stringExtra);
            this.q = stringExtra;
        }
        if (this.r == null) {
            AnalyticsLog analyticsLog = new AnalyticsLog();
            analyticsLog.setEventName(FirebaseEventParams.EventName.KH_OPEN.getValue());
            FirebaseConstants firebaseConstants = FirebaseConstants.PREVIOUS_SCREEN_NAME;
            analyticsLog.setPreviousScreenName(firebaseConstants.getValue());
            FirebaseEventParams.ScreenName screenName = FirebaseEventParams.ScreenName.OTP_REPORT_ISSUE_SCREEN;
            analyticsLog.setScreenName(screenName.getValue());
            CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
            firebaseConstants.setValue(screenName.getValue());
            ((LinearLayout) _$_findCachedViewById(R.id.spinner_layout)).setVisibility(8);
            ((TextView) _$_findCachedViewById(R.id.choose_text)).setVisibility(8);
            String str = this.q;
            if (str != null) {
                if (str.length() > 0) {
                    ((TextView) _$_findCachedViewById(R.id.toolbar).findViewById(R.id.toolbar_title)).setText(this.q);
                }
            }
            ((TextView) _$_findCachedViewById(R.id.toolbar).findViewById(R.id.toolbar_title)).setText(R.string.report_issue);
        } else {
            AnalyticsLog analyticsLog2 = new AnalyticsLog();
            analyticsLog2.setEventName(FirebaseEventParams.EventName.KH_OPEN.getValue());
            FirebaseConstants firebaseConstants2 = FirebaseConstants.PREVIOUS_SCREEN_NAME;
            analyticsLog2.setPreviousScreenName(firebaseConstants2.getValue());
            FirebaseEventParams.ScreenName screenName2 = FirebaseEventParams.ScreenName.FEEDBACK_SCREEN;
            analyticsLog2.setScreenName(screenName2.getValue());
            CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog2);
            firebaseConstants2.setValue(screenName2.getValue());
            getViewModel().onCallCategoryApi();
            getViewModel().getMSpinnerData().observe(this, this);
            ((TextView) _$_findCachedViewById(R.id.choose_text)).setVisibility(0);
            ((TextView) _$_findCachedViewById(R.id.toolbar).findViewById(R.id.toolbar_title)).setText(R.string.feedback);
        }
        ((TextView) _$_findCachedViewById(R.id.toolbar).findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.phonevalidation.activities.i
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityReportIssue.t(ActivityReportIssue.this, view);
            }
        });
        ((EditText) _$_findCachedViewById(R.id.edittext_email_report_issue)).addTextChangedListener(new TextWatcher() { // from class: com.coveiot.android.leonardo.onboarding.phonevalidation.activities.ActivityReportIssue$onCreate$2
            @Override // android.text.TextWatcher
            public void afterTextChanged(@Nullable Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(@Nullable CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(@Nullable CharSequence charSequence, int i, int i2, int i3) {
                boolean z;
                Button button = (Button) ActivityReportIssue.this._$_findCachedViewById(R.id.btn_submit);
                Editable text = ((EditText) ActivityReportIssue.this._$_findCachedViewById(R.id.edittext_report_issue)).getText();
                Intrinsics.checkNotNullExpressionValue(text, "edittext_report_issue.text");
                if (StringsKt__StringsKt.trim(text).length() > 0) {
                    Editable text2 = ((EditText) ActivityReportIssue.this._$_findCachedViewById(R.id.edittext_email_report_issue)).getText();
                    Intrinsics.checkNotNullExpressionValue(text2, "edittext_email_report_issue.text");
                    if (StringsKt__StringsKt.trim(text2).length() > 0) {
                        z = true;
                        button.setEnabled(z);
                    }
                }
                z = false;
                button.setEnabled(z);
            }
        });
        ((EditText) _$_findCachedViewById(R.id.edittext_report_issue)).addTextChangedListener(new TextWatcher() { // from class: com.coveiot.android.leonardo.onboarding.phonevalidation.activities.ActivityReportIssue$onCreate$3
            @Override // android.text.TextWatcher
            public void afterTextChanged(@Nullable Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(@Nullable CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(@Nullable CharSequence charSequence, int i, int i2, int i3) {
                boolean z;
                StringBuilder sb = new StringBuilder();
                ActivityReportIssue activityReportIssue = ActivityReportIssue.this;
                int i4 = R.id.edittext_report_issue;
                sb.append(((EditText) activityReportIssue._$_findCachedViewById(i4)).length());
                sb.append("/500");
                ((TextView) ActivityReportIssue.this._$_findCachedViewById(R.id.data_lenght)).setText(sb.toString());
                Button button = (Button) ActivityReportIssue.this._$_findCachedViewById(R.id.btn_submit);
                Editable text = ((EditText) ActivityReportIssue.this._$_findCachedViewById(i4)).getText();
                Intrinsics.checkNotNullExpressionValue(text, "edittext_report_issue.text");
                if (StringsKt__StringsKt.trim(text).length() > 0) {
                    Editable text2 = ((EditText) ActivityReportIssue.this._$_findCachedViewById(R.id.edittext_email_report_issue)).getText();
                    Intrinsics.checkNotNullExpressionValue(text2, "edittext_email_report_issue.text");
                    if (StringsKt__StringsKt.trim(text2).length() > 0) {
                        z = true;
                        button.setEnabled(z);
                    }
                }
                z = false;
                button.setEnabled(z);
            }
        });
        ((Button) _$_findCachedViewById(R.id.btn_submit)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.phonevalidation.activities.j
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityReportIssue.u(ActivityReportIssue.this, view);
            }
        });
    }

    @Override // com.coveiot.android.dashboard2.listener.ViewModelListener
    public void onDataFailure(@NotNull String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        Toast.makeText(this, message, 0).show();
    }

    @Override // com.coveiot.android.dashboard2.listener.ViewModelListener
    public void onSuccess() {
        String string = getString(R.string.thanks_for_feedback);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.thanks_for_feedback)");
        final BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = new BottomSheetDialogOneButtonOneTitle(this, string);
        String string2 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.ok)");
        bottomSheetDialogOneButtonOneTitle.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.phonevalidation.activities.k
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityReportIssue.v(BottomSheetDialogOneButtonOneTitle.this, this, view);
            }
        });
        bottomSheetDialogOneButtonOneTitle.show();
    }

    public final void setPhoneNumber(@Nullable String str) {
        this.p = str;
    }

    public final void setTitle(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.q = str;
    }

    public final void setType(@Nullable String str) {
        this.r = str;
    }

    public final void setViewModel(@NotNull ActivityReportIssueViewModel activityReportIssueViewModel) {
        Intrinsics.checkNotNullParameter(activityReportIssueViewModel, "<set-?>");
        this.viewModel = activityReportIssueViewModel;
    }

    @Override // androidx.lifecycle.Observer
    public void onChanged(@Nullable String[] strArr) {
        if (strArr != null) {
            int i = R.id.spinner_report_issue;
            ((Spinner) _$_findCachedViewById(i)).setAdapter((SpinnerAdapter) new ArrayAdapter(this, (int) R.layout.feedback_spinner_item, strArr));
            ((Spinner) _$_findCachedViewById(i)).setSelection(0);
            ((LinearLayout) _$_findCachedViewById(R.id.spinner_layout)).setVisibility(0);
        }
    }
}
