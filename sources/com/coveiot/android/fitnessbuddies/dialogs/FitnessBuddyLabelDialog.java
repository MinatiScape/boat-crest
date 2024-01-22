package com.coveiot.android.fitnessbuddies.dialogs;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.fitnessbuddies.R;
import com.coveiot.android.fitnessbuddies.constants.FitnessBuddiesLabels;
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.coveaccess.fitnessbuddies.model.buddydetails.GetBuddyItems;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import java.util.Arrays;
import java.util.Locale;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes4.dex */
public class FitnessBuddyLabelDialog {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4454a;
    @NotNull
    public final BottomSheetDialog b;
    @NotNull
    public final Button c;
    @NotNull
    public final Button d;
    @NotNull
    public final TextView e;
    @NotNull
    public final EditText f;
    @NotNull
    public final TextView g;

    public FitnessBuddyLabelDialog(@NotNull Context context, @NotNull GetBuddyItems buddyDetails, @NotNull final FitnessBuddiesLabels fitnessBuddiesLabels) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(buddyDetails, "buddyDetails");
        Intrinsics.checkNotNullParameter(fitnessBuddiesLabels, "fitnessBuddiesLabels");
        this.f4454a = context;
        Intrinsics.checkNotNull(context);
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context, R.style.DialogThemeBottomSheet);
        this.b = bottomSheetDialog;
        bottomSheetDialog.requestWindowFeature(1);
        bottomSheetDialog.setContentView(R.layout.cheer_buddy_dialog);
        View findViewById = bottomSheetDialog.findViewById(R.id.btn_cancel);
        Intrinsics.checkNotNull(findViewById);
        Button button = (Button) findViewById;
        this.c = button;
        View findViewById2 = bottomSheetDialog.findViewById(R.id.btn_send);
        Intrinsics.checkNotNull(findViewById2);
        this.d = (Button) findViewById2;
        View findViewById3 = bottomSheetDialog.findViewById(R.id.tv_buddy_label);
        Intrinsics.checkNotNull(findViewById3);
        TextView textView = (TextView) findViewById3;
        this.e = textView;
        View findViewById4 = bottomSheetDialog.findViewById(R.id.updateMsgEditText);
        Intrinsics.checkNotNull(findViewById4);
        EditText editText = (EditText) findViewById4;
        this.f = editText;
        View findViewById5 = bottomSheetDialog.findViewById(R.id.tv_text_num);
        Intrinsics.checkNotNull(findViewById5);
        TextView textView2 = (TextView) findViewById5;
        this.g = textView2;
        if (fitnessBuddiesLabels == FitnessBuddiesLabels.CHEER) {
            AnalyticsLog analyticsLog = new AnalyticsLog();
            analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
            analyticsLog.setScreenName(FirebaseEventParams.ScreenName.NOTIFICATION_SCREEN.getValue());
            analyticsLog.setDescription(FirebaseEventParams.Description.OPEN_CHEER_POPUP.getValue());
            analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.CHEER_BUTTON.getValue());
            CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            Locale locale = Locale.ENGLISH;
            String string = context.getString(R.string.cheer_up_buddy_name);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.cheer_up_buddy_name)");
            String format = String.format(locale, string, Arrays.copyOf(new Object[]{buddyDetails.getBuddyDetails().name}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
            textView.setText(format);
            editText.setText(context.getString(R.string.hey) + ' ' + buddyDetails.getBuddyDetails().name + ", " + context.getString(R.string.just_a_few_more_steps_and_you_will));
        } else if (fitnessBuddiesLabels == FitnessBuddiesLabels.APPLAUD) {
            AnalyticsLog analyticsLog2 = new AnalyticsLog();
            analyticsLog2.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
            analyticsLog2.setScreenName(FirebaseEventParams.ScreenName.NOTIFICATION_SCREEN.getValue());
            analyticsLog2.setDescription(FirebaseEventParams.Description.OPEN_APPLAUD_POPUP.getValue());
            analyticsLog2.setUiElementName(FirebaseEventParams.UiElementName.APPLAUD_BUTTON.getValue());
            CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog2);
            StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
            Locale locale2 = Locale.ENGLISH;
            String string2 = context.getString(R.string.applaud_buddy_name);
            Intrinsics.checkNotNullExpressionValue(string2, "context.getString(R.string.applaud_buddy_name)");
            String format2 = String.format(locale2, string2, Arrays.copyOf(new Object[]{buddyDetails.getBuddyDetails().name}, 1));
            Intrinsics.checkNotNullExpressionValue(format2, "format(locale, format, *args)");
            textView.setText(format2);
            editText.setText(context.getString(R.string.way_to_go) + ' ' + buddyDetails.getBuddyDetails().name + ' ' + context.getString(R.string.on_reaching_your_goal));
        } else {
            AnalyticsLog analyticsLog3 = new AnalyticsLog();
            analyticsLog3.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
            analyticsLog3.setScreenName(FirebaseEventParams.ScreenName.NOTIFICATION_SCREEN.getValue());
            analyticsLog3.setDescription(FirebaseEventParams.Description.OPEN_NUDGE_POPUP.getValue());
            analyticsLog3.setUiElementName(FirebaseEventParams.UiElementName.NUDGE_BUTTON.getValue());
            CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog3);
            StringCompanionObject stringCompanionObject3 = StringCompanionObject.INSTANCE;
            Locale locale3 = Locale.ENGLISH;
            String string3 = context.getString(R.string.remind_name);
            Intrinsics.checkNotNullExpressionValue(string3, "context.getString(R.string.remind_name)");
            String format3 = String.format(locale3, string3, Arrays.copyOf(new Object[]{buddyDetails.getBuddyDetails().name}, 1));
            Intrinsics.checkNotNullExpressionValue(format3, "format(locale, format, *args)");
            textView.setText(format3);
            editText.setText(context.getString(R.string.hey) + ' ' + buddyDetails.getBuddyDetails().name + ", " + context.getString(R.string.your_are_little_behind));
        }
        textView2.setText(editText.getText().length() + " / 180 Characters");
        editText.addTextChangedListener(new TextWatcher() { // from class: com.coveiot.android.fitnessbuddies.dialogs.FitnessBuddyLabelDialog.1
            @Override // android.text.TextWatcher
            public void afterTextChanged(@NotNull Editable s) {
                Intrinsics.checkNotNullParameter(s, "s");
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(@NotNull CharSequence s, int i, int i2, int i3) {
                Intrinsics.checkNotNullParameter(s, "s");
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(@NotNull CharSequence s, int i, int i2, int i3) {
                Intrinsics.checkNotNullParameter(s, "s");
                if (s.length() > 0) {
                    TextView tv_text_num = FitnessBuddyLabelDialog.this.getTv_text_num();
                    tv_text_num.setText(FitnessBuddyLabelDialog.this.getUpdateMsgEditText().length() + " / 180 Characters");
                }
            }
        });
        button.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.fitnessbuddies.dialogs.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FitnessBuddyLabelDialog.b(FitnessBuddiesLabels.this, this, view);
            }
        });
    }

    public static final void b(FitnessBuddiesLabels fitnessBuddiesLabels, FitnessBuddyLabelDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(fitnessBuddiesLabels, "$fitnessBuddiesLabels");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (fitnessBuddiesLabels.getLabelType().equals(FitnessBuddiesLabels.CHEER.name())) {
            AnalyticsLog analyticsLog = new AnalyticsLog();
            analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
            analyticsLog.setScreenName(FirebaseEventParams.ScreenName.NOTIFICATION_SCREEN.getValue());
            analyticsLog.setDescription(FirebaseEventParams.Description.CHEER_POPUP.getValue());
            analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.CANCEL_BUTTON.getValue());
            CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        } else if (fitnessBuddiesLabels.getLabelType().equals(FitnessBuddiesLabels.APPLAUD.name())) {
            AnalyticsLog analyticsLog2 = new AnalyticsLog();
            analyticsLog2.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
            analyticsLog2.setScreenName(FirebaseEventParams.ScreenName.NOTIFICATION_SCREEN.getValue());
            analyticsLog2.setDescription(FirebaseEventParams.Description.APPLAUD_POPUP.getValue());
            analyticsLog2.setUiElementName(FirebaseEventParams.UiElementName.CANCEL_BUTTON.getValue());
            CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog2);
        } else {
            AnalyticsLog analyticsLog3 = new AnalyticsLog();
            analyticsLog3.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
            analyticsLog3.setScreenName(FirebaseEventParams.ScreenName.NOTIFICATION_SCREEN.getValue());
            analyticsLog3.setDescription(FirebaseEventParams.Description.NUDGE_POPUP.getValue());
            analyticsLog3.setUiElementName(FirebaseEventParams.UiElementName.CANCEL_BUTTON.getValue());
            CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog3);
        }
        this$0.dismiss();
    }

    public final void dismiss() {
        this.b.dismiss();
    }

    @NotNull
    public final BottomSheetDialog getBottomSheetDialog() {
        return this.b;
    }

    @NotNull
    public final Button getBtn_cancel() {
        return this.c;
    }

    @NotNull
    public final Button getBtn_send() {
        return this.d;
    }

    @NotNull
    public final Context getContext() {
        return this.f4454a;
    }

    @NotNull
    public final TextView getTv_buddy_label() {
        return this.e;
    }

    @NotNull
    public final TextView getTv_text_num() {
        return this.g;
    }

    @NotNull
    public final EditText getUpdateMsgEditText() {
        return this.f;
    }

    public final boolean isShowing() {
        return this.b.isShowing();
    }

    public final void setCancelable(boolean z) {
        this.b.setCancelable(z);
    }

    public final void setCancelableOutside(boolean z) {
        this.b.setCanceledOnTouchOutside(z);
    }

    public final void setPositiveButton(@NotNull View.OnClickListener listner) {
        Intrinsics.checkNotNullParameter(listner, "listner");
        this.d.setOnClickListener(listner);
    }

    public final void show() {
        Context context = this.f4454a;
        if (context instanceof Activity) {
            Intrinsics.checkNotNull(context, "null cannot be cast to non-null type android.app.Activity");
            if (((Activity) context).isFinishing()) {
                return;
            }
        }
        if (this.b.isShowing()) {
            return;
        }
        this.b.show();
    }
}
