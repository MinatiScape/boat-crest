package com.coveiot.android.leonardo.more.activities;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.lifecycle.ViewModelProviders;
import com.coveiot.android.boat.R;
import com.coveiot.android.dashboard2.listener.ViewModelListener;
import com.coveiot.android.leonardo.onboarding.phonevalidation.viewmodel.ActivityReportIssueViewModel;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.leonardo.utils.ViewModelFactory;
import com.coveiot.android.leonardo.utils.ViewUtilsKt;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.BottomSheetDialogOneButtonOneTitle;
import com.coveiot.utils.utility.AppUtils;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityNotificationFeedBack extends BaseActivity implements ViewModelListener {
    @Nullable
    public String p;
    @Nullable
    public String r;
    public ActivityReportIssueViewModel viewModel;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public String q = "";

    /* loaded from: classes5.dex */
    public static final class a extends Lambda implements Function1<View, Unit> {
        public a() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(View view) {
            invoke2(view);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull View it) {
            Intrinsics.checkNotNullParameter(it, "it");
            ActivityNotificationFeedBack activityNotificationFeedBack = ActivityNotificationFeedBack.this;
            int i = R.id.edittext_email_report_issue;
            String obj = StringsKt__StringsKt.trim(((EditText) activityNotificationFeedBack._$_findCachedViewById(i)).getText().toString()).toString();
            if (!(obj.length() == 0) && !AppUtils.isValidEmail(obj)) {
                ActivityNotificationFeedBack activityNotificationFeedBack2 = ActivityNotificationFeedBack.this;
                Toast.makeText(activityNotificationFeedBack2, activityNotificationFeedBack2.getString(R.string.invalid_email_id), 0).show();
                return;
            }
            ActivityNotificationFeedBack.this.showProgress();
            ActivityReportIssueViewModel.onCallFeedbackApi$default(ActivityNotificationFeedBack.this.getViewModel(), AppConstants.DVC_NOTIFICATION_ISSUE.getValue(), StringsKt__StringsKt.trim(((EditText) ActivityNotificationFeedBack.this._$_findCachedViewById(i)).getText().toString()).toString(), StringsKt__StringsKt.trim(((EditText) ActivityNotificationFeedBack.this._$_findCachedViewById(R.id.edittext_report_issue)).getText().toString()).toString(), null, null, 24, null);
        }
    }

    public static final void s(ActivityNotificationFeedBack this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.setResult(-1, this$0.getIntent());
        this$0.finish();
    }

    public static final void t(BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle, ActivityNotificationFeedBack this$0, View view) {
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
        setResult(-1, getIntent());
        finish();
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_troubleshoot_notification_feedback);
        setViewModel((ActivityReportIssueViewModel) ViewModelProviders.of(this, new ViewModelFactory(this)).get(ActivityReportIssueViewModel.class));
        getViewModel().setViewModelListener(this);
        int i = R.id.toolbar;
        ((TextView) _$_findCachedViewById(i).findViewById(R.id.toolbar_title)).setText(R.string.troubleshoot_notifications);
        ((TextView) _$_findCachedViewById(i).findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.cf
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityNotificationFeedBack.s(ActivityNotificationFeedBack.this, view);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.tv_device_model)).setText(Build.MODEL);
        ((EditText) _$_findCachedViewById(R.id.edittext_email_report_issue)).addTextChangedListener(new TextWatcher() { // from class: com.coveiot.android.leonardo.more.activities.ActivityNotificationFeedBack$onCreate$2
            @Override // android.text.TextWatcher
            public void afterTextChanged(@Nullable Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(@Nullable CharSequence charSequence, int i2, int i3, int i4) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(@Nullable CharSequence charSequence, int i2, int i3, int i4) {
                boolean z;
                Button button = (Button) ActivityNotificationFeedBack.this._$_findCachedViewById(R.id.btn_submit);
                Editable text = ((EditText) ActivityNotificationFeedBack.this._$_findCachedViewById(R.id.edittext_report_issue)).getText();
                Intrinsics.checkNotNullExpressionValue(text, "edittext_report_issue.text");
                if (StringsKt__StringsKt.trim(text).length() > 0) {
                    Editable text2 = ((EditText) ActivityNotificationFeedBack.this._$_findCachedViewById(R.id.edittext_email_report_issue)).getText();
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
        ((EditText) _$_findCachedViewById(R.id.edittext_report_issue)).addTextChangedListener(new TextWatcher() { // from class: com.coveiot.android.leonardo.more.activities.ActivityNotificationFeedBack$onCreate$3
            @Override // android.text.TextWatcher
            public void afterTextChanged(@Nullable Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(@Nullable CharSequence charSequence, int i2, int i3, int i4) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(@Nullable CharSequence charSequence, int i2, int i3, int i4) {
                boolean z;
                StringBuilder sb = new StringBuilder();
                ActivityNotificationFeedBack activityNotificationFeedBack = ActivityNotificationFeedBack.this;
                int i5 = R.id.edittext_report_issue;
                sb.append(((EditText) activityNotificationFeedBack._$_findCachedViewById(i5)).length());
                sb.append("/500");
                ((TextView) ActivityNotificationFeedBack.this._$_findCachedViewById(R.id.data_lenght)).setText(sb.toString());
                Button button = (Button) ActivityNotificationFeedBack.this._$_findCachedViewById(R.id.btn_submit);
                Editable text = ((EditText) ActivityNotificationFeedBack.this._$_findCachedViewById(i5)).getText();
                Intrinsics.checkNotNullExpressionValue(text, "edittext_report_issue.text");
                if (StringsKt__StringsKt.trim(text).length() > 0) {
                    Editable text2 = ((EditText) ActivityNotificationFeedBack.this._$_findCachedViewById(R.id.edittext_email_report_issue)).getText();
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
        Button btn_submit = (Button) _$_findCachedViewById(R.id.btn_submit);
        Intrinsics.checkNotNullExpressionValue(btn_submit, "btn_submit");
        ViewUtilsKt.setSafeOnClickListener(btn_submit, new a());
    }

    @Override // com.coveiot.android.dashboard2.listener.ViewModelListener
    public void onDataFailure(@NotNull String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        if (isFinishing()) {
            return;
        }
        dismissProgress();
        Toast.makeText(this, message, 0).show();
    }

    @Override // com.coveiot.android.dashboard2.listener.ViewModelListener
    public void onSuccess() {
        if (isFinishing()) {
            return;
        }
        dismissProgress();
        String string = getString(R.string.thanks_for_feedback);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.thanks_for_feedback)");
        final BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = new BottomSheetDialogOneButtonOneTitle(this, string);
        String string2 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.ok)");
        bottomSheetDialogOneButtonOneTitle.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.df
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityNotificationFeedBack.t(BottomSheetDialogOneButtonOneTitle.this, this, view);
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
}
