package com.coveiot.android.weeklyreport.activities;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.BottomSheetDialogOneButtonOneTitle;
import com.coveiot.android.weeklyreport.R;
import com.coveiot.android.weeklyreport.listeners.OnSuccessListener;
import com.coveiot.android.weeklyreport.utils.ViewModelFactory;
import com.coveiot.android.weeklyreport.viewmodel.ActivityReportIssueViewModel;
import com.coveiot.utils.utility.AppUtils;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes8.dex */
public final class ActivityReportIssue extends BaseActivity implements OnSuccessListener {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public ActivityReportIssueViewModel viewModel;

    public static final void t(ActivityReportIssue this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.setResult(-1, this$0.getIntent());
        this$0.finish();
    }

    public static final void u(ActivityReportIssue this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String obj = StringsKt__StringsKt.trim(((EditText) this$0._$_findCachedViewById(R.id.edittext_email_report_issue)).getText().toString()).toString();
        if (!(obj.length() == 0) && !AppUtils.isValidEmail(obj)) {
            Toast.makeText(this$0, this$0.getString(R.string.invalid_email_id), 0).show();
        } else {
            this$0.getViewModel().onCallReportIssueApi(StringsKt__StringsKt.trim(((EditText) this$0._$_findCachedViewById(R.id.edittext_report_issue)).getText().toString()).toString(), obj);
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
        setContentView(R.layout.activity_report_issue);
        ViewModel viewModel = ViewModelProviders.of(this, new ViewModelFactory(this)).get(ActivityReportIssueViewModel.class);
        Intrinsics.checkNotNullExpressionValue(viewModel, "of(this, ViewModelFactor…sueViewModel::class.java)");
        setViewModel((ActivityReportIssueViewModel) viewModel);
        getViewModel().setOnSuccessListener(this);
        ((LinearLayout) _$_findCachedViewById(R.id.spinner_layout)).setVisibility(8);
        ((TextView) _$_findCachedViewById(R.id.choose_text)).setVisibility(8);
        int i = R.id.toolbar;
        ((TextView) _$_findCachedViewById(i).findViewById(R.id.toolbar_title)).setText(R.string.report_issue);
        ((TextView) _$_findCachedViewById(i).findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.weeklyreport.activities.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityReportIssue.t(ActivityReportIssue.this, view);
            }
        });
        ((EditText) _$_findCachedViewById(R.id.edittext_email_report_issue)).addTextChangedListener(new TextWatcher() { // from class: com.coveiot.android.weeklyreport.activities.ActivityReportIssue$onCreate$2
            @Override // android.text.TextWatcher
            public void afterTextChanged(@Nullable Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(@Nullable CharSequence charSequence, int i2, int i3, int i4) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(@Nullable CharSequence charSequence, int i2, int i3, int i4) {
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
        ((EditText) _$_findCachedViewById(R.id.edittext_report_issue)).addTextChangedListener(new TextWatcher() { // from class: com.coveiot.android.weeklyreport.activities.ActivityReportIssue$onCreate$3
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
                ActivityReportIssue activityReportIssue = ActivityReportIssue.this;
                int i5 = R.id.edittext_report_issue;
                sb.append(((EditText) activityReportIssue._$_findCachedViewById(i5)).length());
                sb.append("/500");
                ((TextView) ActivityReportIssue.this._$_findCachedViewById(R.id.data_lenght)).setText(sb.toString());
                Button button = (Button) ActivityReportIssue.this._$_findCachedViewById(R.id.btn_submit);
                Editable text = ((EditText) ActivityReportIssue.this._$_findCachedViewById(i5)).getText();
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
        ((Button) _$_findCachedViewById(R.id.btn_submit)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.weeklyreport.activities.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityReportIssue.u(ActivityReportIssue.this, view);
            }
        });
    }

    @Override // com.coveiot.android.weeklyreport.listeners.OnSuccessListener
    public void onDataFailure(@NotNull String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        Toast.makeText(this, message, 0).show();
    }

    @Override // com.coveiot.android.weeklyreport.listeners.OnSuccessListener
    public void onSuccess() {
        String string = getString(R.string.thanks_for_feedback);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.thanks_for_feedback)");
        final BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = new BottomSheetDialogOneButtonOneTitle(this, string);
        String string2 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.ok)");
        bottomSheetDialogOneButtonOneTitle.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.weeklyreport.activities.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityReportIssue.v(BottomSheetDialogOneButtonOneTitle.this, this, view);
            }
        });
        bottomSheetDialogOneButtonOneTitle.show();
    }

    public final void setViewModel(@NotNull ActivityReportIssueViewModel activityReportIssueViewModel) {
        Intrinsics.checkNotNullParameter(activityReportIssueViewModel, "<set-?>");
        this.viewModel = activityReportIssueViewModel;
    }
}
