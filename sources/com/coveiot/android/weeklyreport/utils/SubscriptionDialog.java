package com.coveiot.android.weeklyreport.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.DialogFragment;
import com.coveiot.android.weeklyreport.R;
import com.coveiot.android.weeklyreport.activities.ActivityWeeklyReport;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.data.ProfileData;
import com.coveiot.utils.utility.AppUtils;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes8.dex */
public final class SubscriptionDialog extends DialogFragment {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();

    public static final void c(SubscriptionDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismiss();
    }

    public static final void d(SubscriptionDialog this$0, View view) {
        Resources resources;
        Resources resources2;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int i = R.id.email_edt;
        EditText editText = (EditText) this$0._$_findCachedViewById(i);
        if (StringsKt__StringsKt.trim(String.valueOf(editText != null ? editText.getText() : null)).toString().length() > 0) {
            if (AppUtils.isNetConnected(this$0.getContext())) {
                EditText editText2 = (EditText) this$0._$_findCachedViewById(i);
                if (!AppUtils.isValidEmail(String.valueOf(editText2 != null ? editText2.getText() : null))) {
                    Toast.makeText(this$0.requireContext(), R.string.please_enter_valid_mail, 0).show();
                    return;
                }
                Intent intent = new Intent(this$0.getContext(), ActivityWeeklyReport.class);
                WeeklyReportConstant weeklyReportConstant = WeeklyReportConstant.HOME_DASHBOARD;
                intent.putExtra(weeklyReportConstant.getValue(), weeklyReportConstant.getValue());
                String value = WeeklyReportConstant.EMAIL_ID.getValue();
                EditText editText3 = (EditText) this$0._$_findCachedViewById(i);
                intent.putExtra(value, StringsKt__StringsKt.trim(String.valueOf(editText3 != null ? editText3.getText() : null)).toString());
                Context context = this$0.getContext();
                if (context != null) {
                    context.startActivity(intent);
                }
                this$0.dismiss();
                return;
            }
            Context context2 = this$0.getContext();
            Context context3 = this$0.getContext();
            if (context3 != null && (resources2 = context3.getResources()) != null) {
                r1 = resources2.getString(R.string.please_check_your_internet);
            }
            Toast.makeText(context2, r1, 1).show();
            return;
        }
        Context context4 = this$0.getContext();
        Context context5 = this$0.getContext();
        if (context5 != null && (resources = context5.getResources()) != null) {
            r1 = resources.getString(R.string.please_enter_email);
        }
        Toast.makeText(context4, r1, 0).show();
    }

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Nullable
    public View _$_findCachedViewById(int i) {
        View findViewById;
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view == null) {
            View view2 = getView();
            if (view2 == null || (findViewById = view2.findViewById(i)) == null) {
                return null;
            }
            map.put(Integer.valueOf(i), findViewById);
            return findViewById;
        }
        return view;
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnCancelListener
    public void onCancel(@NotNull DialogInterface dialog) {
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        super.onCancel(dialog);
        setStyle(1, R.style.DialogTheme);
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.weekly_report_popup, viewGroup, false);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        Window window;
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog == null || (window = dialog.getWindow()) == null) {
            return;
        }
        window.setLayout(-1, -1);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        EditText editText;
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        ProfileData userDetails = SessionManager.getInstance(getContext()).getUserDetails();
        if (userDetails != null && userDetails.getEmail() != null && (editText = (EditText) _$_findCachedViewById(R.id.email_edt)) != null) {
            editText.setText(userDetails.getEmail());
        }
        ImageView imageView = (ImageView) _$_findCachedViewById(R.id.ivClose);
        if (imageView != null) {
            imageView.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.weeklyreport.utils.b
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    SubscriptionDialog.c(SubscriptionDialog.this, view2);
                }
            });
        }
        ((TextView) _$_findCachedViewById(R.id.subscribe_button)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.weeklyreport.utils.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                SubscriptionDialog.d(SubscriptionDialog.this, view2);
            }
        });
    }
}
