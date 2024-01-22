package com.coveiot.android.fitnessbuddies.adapters;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.fitnessbuddies.R;
import com.coveiot.android.fitnessbuddies.adapters.ShowBuddiesProgressAdapter;
import com.coveiot.android.fitnessbuddies.models.BuddyReminderModel;
import com.coveiot.android.fitnessbuddies.models.FitnessCheerEvent;
import com.coveiot.android.fitnessbuddies.utils.PreferenceManager;
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.android.theme.LoadingDialog;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.CoveSocial;
import com.coveiot.coveaccess.fitnessbuddies.ReactionType;
import com.coveiot.coveaccess.fitnessbuddies.model.HandleBuddyRequest;
import com.coveiot.coveaccess.fitnessbuddies.model.SendReactionRequest;
import com.coveiot.coveaccess.fitnessbuddies.model.common.BuddiesGoal;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.utils.CoveEventBusManager;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.GlideUtils;
import com.coveiot.utils.utility.ImageLodeListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class ShowBuddiesProgressAdapter extends RecyclerView.Adapter<ViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4436a;
    @NotNull
    public final List<BuddiesGoal> b;
    @NotNull
    public final String c;
    @NotNull
    public final String d;
    @NotNull
    public final String e;
    @NotNull
    public LoadingDialog f;

    /* loaded from: classes4.dex */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public ImageView f4437a;
        @NotNull
        public TextView b;
        @NotNull
        public TextView c;
        @NotNull
        public TextView d;
        @NotNull
        public ProgressBar e;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(@NotNull ShowBuddiesProgressAdapter showBuddiesProgressAdapter, View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            View findViewById = itemView.findViewById(R.id.image);
            Intrinsics.checkNotNullExpressionValue(findViewById, "itemView.findViewById(R.id.image)");
            this.f4437a = (ImageView) findViewById;
            View findViewById2 = itemView.findViewById(R.id.name);
            Intrinsics.checkNotNullExpressionValue(findViewById2, "itemView.findViewById(R.id.name)");
            this.b = (TextView) findViewById2;
            View findViewById3 = itemView.findViewById(R.id.react);
            Intrinsics.checkNotNullExpressionValue(findViewById3, "itemView.findViewById(R.id.react)");
            this.c = (TextView) findViewById3;
            View findViewById4 = itemView.findViewById(R.id.progress_achieved);
            Intrinsics.checkNotNullExpressionValue(findViewById4, "itemView.findViewById(R.id.progress_achieved)");
            this.d = (TextView) findViewById4;
            View findViewById5 = itemView.findViewById(R.id.progress);
            Intrinsics.checkNotNullExpressionValue(findViewById5, "itemView.findViewById(R.id.progress)");
            this.e = (ProgressBar) findViewById5;
        }

        @NotNull
        public final ImageView getImage() {
            return this.f4437a;
        }

        @NotNull
        public final TextView getName() {
            return this.b;
        }

        @NotNull
        public final ProgressBar getProgress() {
            return this.e;
        }

        @NotNull
        public final TextView getProgressAcheived() {
            return this.d;
        }

        @NotNull
        public final TextView getReact() {
            return this.c;
        }

        public final void setImage(@NotNull ImageView imageView) {
            Intrinsics.checkNotNullParameter(imageView, "<set-?>");
            this.f4437a = imageView;
        }

        public final void setName(@NotNull TextView textView) {
            Intrinsics.checkNotNullParameter(textView, "<set-?>");
            this.b = textView;
        }

        public final void setProgress(@NotNull ProgressBar progressBar) {
            Intrinsics.checkNotNullParameter(progressBar, "<set-?>");
            this.e = progressBar;
        }

        public final void setProgressAcheived(@NotNull TextView textView) {
            Intrinsics.checkNotNullParameter(textView, "<set-?>");
            this.d = textView;
        }

        public final void setReact(@NotNull TextView textView) {
            Intrinsics.checkNotNullParameter(textView, "<set-?>");
            this.c = textView;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ShowBuddiesProgressAdapter(@NotNull Context context, @NotNull List<? extends BuddiesGoal> mBuddiesGoals) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(mBuddiesGoals, "mBuddiesGoals");
        this.f4436a = context;
        this.b = mBuddiesGoals;
        String string = context.getString(R.string.nudge);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.nudge)");
        this.c = string;
        String string2 = context.getString(R.string.cheer);
        Intrinsics.checkNotNullExpressionValue(string2, "context.getString(R.string.cheer)");
        this.d = string2;
        String string3 = context.getString(R.string.applaud);
        Intrinsics.checkNotNullExpressionValue(string3, "context.getString(R.string.applaud)");
        this.e = string3;
        this.f = new LoadingDialog(context);
    }

    public static final void e() {
    }

    public static final void f(final ShowBuddiesProgressAdapter this$0, final ViewHolder viewHolder, final int i, final Integer num, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(viewHolder, "$viewHolder");
        final Dialog dialog = new Dialog(this$0.f4436a, R.style.DialogTheme);
        dialog.requestWindowFeature(1);
        Window window = dialog.getWindow();
        Intrinsics.checkNotNull(window);
        window.setBackgroundDrawable(new ColorDrawable(0));
        dialog.setContentView(R.layout.send_buddy_message_dialog);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        View findViewById = dialog.findViewById(R.id.title);
        Intrinsics.checkNotNullExpressionValue(findViewById, "dialog.findViewById(R.id.title)");
        View findViewById2 = dialog.findViewById(R.id.content);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "dialog.findViewById(R.id.content)");
        final TextView textView = (TextView) findViewById2;
        View findViewById3 = dialog.findViewById(R.id.cancel);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "dialog.findViewById(R.id.cancel)");
        TextView textView2 = (TextView) findViewById3;
        View findViewById4 = dialog.findViewById(R.id.proceed);
        Intrinsics.checkNotNullExpressionValue(findViewById4, "dialog.findViewById(R.id.proceed)");
        TextView textView3 = (TextView) findViewById4;
        View findViewById5 = dialog.findViewById(R.id.characterCount);
        Intrinsics.checkNotNullExpressionValue(findViewById5, "dialog.findViewById(R.id.characterCount)");
        final TextView textView4 = (TextView) findViewById5;
        ((TextView) findViewById).setText(this$0.f4436a.getString(R.string.send_caps) + ' ' + ((Object) viewHolder.getReact().getText()) + ' ' + this$0.f4436a.getString(R.string.message_cap));
        String obj = viewHolder.getReact().getText().toString();
        int length = obj.length() - 1;
        int i2 = 0;
        boolean z = false;
        while (i2 <= length) {
            boolean z2 = Intrinsics.compare((int) obj.charAt(!z ? i2 : length), 32) <= 0;
            if (z) {
                if (!z2) {
                    break;
                }
                length--;
            } else if (z2) {
                i2++;
            } else {
                z = true;
            }
        }
        if (Intrinsics.areEqual(obj.subSequence(i2, length + 1).toString(), this$0.d)) {
            AnalyticsLog analyticsLog = new AnalyticsLog();
            analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
            analyticsLog.setScreenName(FirebaseEventParams.ScreenName.NOTIFICATION_SCREEN.getValue());
            analyticsLog.setDescription(FirebaseEventParams.Description.OPEN_CHEER_POPUP.getValue());
            analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.CHEER_BUTTON.getValue());
            CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
            textView.setText(this$0.f4436a.getString(R.string.hey) + ' ' + this$0.b.get(i).userName + ", " + this$0.f4436a.getString(R.string.just_a_few_more_steps_and_you_will));
        } else {
            String obj2 = viewHolder.getReact().getText().toString();
            int length2 = obj2.length() - 1;
            int i3 = 0;
            boolean z3 = false;
            while (i3 <= length2) {
                boolean z4 = Intrinsics.compare((int) obj2.charAt(!z3 ? i3 : length2), 32) <= 0;
                if (z3) {
                    if (!z4) {
                        break;
                    }
                    length2--;
                } else if (z4) {
                    i3++;
                } else {
                    z3 = true;
                }
            }
            if (Intrinsics.areEqual(obj2.subSequence(i3, length2 + 1).toString(), this$0.e)) {
                AnalyticsLog analyticsLog2 = new AnalyticsLog();
                analyticsLog2.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
                analyticsLog2.setScreenName(FirebaseEventParams.ScreenName.NOTIFICATION_SCREEN.getValue());
                analyticsLog2.setDescription(FirebaseEventParams.Description.OPEN_APPLAUD_POPUP.getValue());
                analyticsLog2.setUiElementName(FirebaseEventParams.UiElementName.APPLAUD_BUTTON.getValue());
                CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog2);
                textView.setText(this$0.f4436a.getString(R.string.way_to_go) + ' ' + this$0.b.get(i).userName + ' ' + this$0.f4436a.getString(R.string.on_reaching_your_goal));
            } else {
                String obj3 = viewHolder.getReact().getText().toString();
                int length3 = obj3.length() - 1;
                int i4 = 0;
                boolean z5 = false;
                while (i4 <= length3) {
                    boolean z6 = Intrinsics.compare((int) obj3.charAt(!z5 ? i4 : length3), 32) <= 0;
                    if (z5) {
                        if (!z6) {
                            break;
                        }
                        length3--;
                    } else if (z6) {
                        i4++;
                    } else {
                        z5 = true;
                    }
                }
                if (Intrinsics.areEqual(obj3.subSequence(i4, length3 + 1).toString(), this$0.c)) {
                    AnalyticsLog analyticsLog3 = new AnalyticsLog();
                    analyticsLog3.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
                    analyticsLog3.setScreenName(FirebaseEventParams.ScreenName.NOTIFICATION_SCREEN.getValue());
                    analyticsLog3.setDescription(FirebaseEventParams.Description.OPEN_NUDGE_POPUP.getValue());
                    analyticsLog3.setUiElementName(FirebaseEventParams.UiElementName.NUDGE_BUTTON.getValue());
                    CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog3);
                    textView.setText(this$0.f4436a.getString(R.string.hey) + ' ' + this$0.b.get(i).userName + ", " + this$0.f4436a.getString(R.string.your_are_little_behind));
                }
            }
        }
        textView4.setText(this$0.f4436a.getString(R.string.characters) + ' ' + textView.length() + "/180");
        textView.addTextChangedListener(new TextWatcher() { // from class: com.coveiot.android.fitnessbuddies.adapters.ShowBuddiesProgressAdapter$onBindViewHolder$2$4
            @Override // android.text.TextWatcher
            public void afterTextChanged(@Nullable Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(@Nullable CharSequence charSequence, int i5, int i6, int i7) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(@Nullable CharSequence charSequence, int i5, int i6, int i7) {
                TextView textView5 = textView4;
                textView5.setText(this$0.getContext().getString(R.string.characters) + ' ' + textView.length() + "/180");
            }
        });
        textView3.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.fitnessbuddies.adapters.j
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                ShowBuddiesProgressAdapter.g(textView, this$0, viewHolder, num, i, dialog, view2);
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.fitnessbuddies.adapters.k
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                ShowBuddiesProgressAdapter.h(ShowBuddiesProgressAdapter.ViewHolder.this, this$0, dialog, view2);
            }
        });
        dialog.show();
    }

    public static final void g(TextView content, final ShowBuddiesProgressAdapter this$0, ViewHolder viewHolder, Integer num, final int i, final Dialog dialog, View view) {
        Intrinsics.checkNotNullParameter(content, "$content");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(viewHolder, "$viewHolder");
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        String obj = content.getText().toString();
        if (!AppUtils.isNetConnected(this$0.f4436a)) {
            Toast.makeText(this$0.f4436a, R.string.please_check_network, 1).show();
        } else if (obj != null && StringsKt__StringsKt.trim(obj).toString().length() == 0) {
            Context context = this$0.f4436a;
            Toast.makeText(context, context.getString(R.string.invalid_nudge_message), 1).show();
        } else {
            if (!this$0.f.isShowing()) {
                this$0.f.show();
            }
            if (kotlin.text.m.equals(StringsKt__StringsKt.trim(viewHolder.getReact().getText().toString()).toString(), this$0.e, true)) {
                AnalyticsLog analyticsLog = new AnalyticsLog();
                analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
                analyticsLog.setScreenName(FirebaseEventParams.ScreenName.NOTIFICATION_SCREEN.getValue());
                analyticsLog.setDescription(FirebaseEventParams.Description.APPLAUD_POPUP.getValue());
                analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.SEND_BUTTON.getValue());
                CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
                CoveSocial.sendReaction(num, new SendReactionRequest(ReactionType.APPLAUD, StringsKt__StringsKt.trim(content.getText().toString()).toString()), new CoveApiListener<HandleBuddyRequest, CoveApiErrorModel>() { // from class: com.coveiot.android.fitnessbuddies.adapters.ShowBuddiesProgressAdapter$onBindViewHolder$2$5$1
                    @Override // com.coveiot.coveaccess.CoveApiListener
                    public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                        LoadingDialog loadingDialog;
                        LoadingDialog loadingDialog2;
                        loadingDialog = ShowBuddiesProgressAdapter.this.f;
                        if (loadingDialog.isShowing()) {
                            loadingDialog2 = ShowBuddiesProgressAdapter.this.f;
                            loadingDialog2.dismiss();
                        }
                        Toast.makeText(ShowBuddiesProgressAdapter.this.getContext(), ShowBuddiesProgressAdapter.this.getContext().getString(R.string.error_sending_message), 0).show();
                        dialog.dismiss();
                    }

                    @Override // com.coveiot.coveaccess.CoveApiListener
                    public void onSuccess(@Nullable HandleBuddyRequest handleBuddyRequest) {
                        LoadingDialog loadingDialog;
                        LoadingDialog loadingDialog2;
                        loadingDialog = ShowBuddiesProgressAdapter.this.f;
                        if (loadingDialog.isShowing()) {
                            loadingDialog2 = ShowBuddiesProgressAdapter.this.f;
                            loadingDialog2.dismiss();
                        }
                        ShowBuddiesProgressAdapter.this.i(i);
                        Toast.makeText(ShowBuddiesProgressAdapter.this.getContext(), ShowBuddiesProgressAdapter.this.getContext().getString(R.string.applaud_message_sent), 0).show();
                        dialog.dismiss();
                        CoveEventBusManager.getInstance().getEventBus().post(new FitnessCheerEvent());
                    }
                });
            } else if (kotlin.text.m.equals(StringsKt__StringsKt.trim(viewHolder.getReact().getText().toString()).toString(), this$0.d, true)) {
                AnalyticsLog analyticsLog2 = new AnalyticsLog();
                analyticsLog2.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
                analyticsLog2.setScreenName(FirebaseEventParams.ScreenName.NOTIFICATION_SCREEN.getValue());
                analyticsLog2.setDescription(FirebaseEventParams.Description.CHEER_POPUP.getValue());
                analyticsLog2.setUiElementName(FirebaseEventParams.UiElementName.SEND_BUTTON.getValue());
                CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog2);
                CoveSocial.sendReaction(num, new SendReactionRequest(ReactionType.CHEER, StringsKt__StringsKt.trim(content.getText().toString()).toString()), new CoveApiListener<HandleBuddyRequest, CoveApiErrorModel>() { // from class: com.coveiot.android.fitnessbuddies.adapters.ShowBuddiesProgressAdapter$onBindViewHolder$2$5$2
                    @Override // com.coveiot.coveaccess.CoveApiListener
                    public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                        LoadingDialog loadingDialog;
                        LoadingDialog loadingDialog2;
                        loadingDialog = ShowBuddiesProgressAdapter.this.f;
                        if (loadingDialog.isShowing()) {
                            loadingDialog2 = ShowBuddiesProgressAdapter.this.f;
                            loadingDialog2.dismiss();
                        }
                        Toast.makeText(ShowBuddiesProgressAdapter.this.getContext(), ShowBuddiesProgressAdapter.this.getContext().getString(R.string.error_sending_message), 0).show();
                        dialog.dismiss();
                    }

                    @Override // com.coveiot.coveaccess.CoveApiListener
                    public void onSuccess(@Nullable HandleBuddyRequest handleBuddyRequest) {
                        LoadingDialog loadingDialog;
                        LoadingDialog loadingDialog2;
                        loadingDialog = ShowBuddiesProgressAdapter.this.f;
                        if (loadingDialog.isShowing()) {
                            loadingDialog2 = ShowBuddiesProgressAdapter.this.f;
                            loadingDialog2.dismiss();
                        }
                        Toast.makeText(ShowBuddiesProgressAdapter.this.getContext(), ShowBuddiesProgressAdapter.this.getContext().getString(R.string.cheer_message_sent), 0).show();
                        dialog.dismiss();
                        ShowBuddiesProgressAdapter.this.i(i);
                        CoveEventBusManager.getInstance().getEventBus().post(new FitnessCheerEvent());
                    }
                });
            } else if (kotlin.text.m.equals(StringsKt__StringsKt.trim(viewHolder.getReact().getText().toString()).toString(), this$0.c, true)) {
                AnalyticsLog analyticsLog3 = new AnalyticsLog();
                analyticsLog3.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
                analyticsLog3.setScreenName(FirebaseEventParams.ScreenName.NOTIFICATION_SCREEN.getValue());
                analyticsLog3.setDescription(FirebaseEventParams.Description.NUDGE_POPUP.getValue());
                analyticsLog3.setUiElementName(FirebaseEventParams.UiElementName.SEND_BUTTON.getValue());
                CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog3);
                CoveSocial.sendReaction(num, new SendReactionRequest(ReactionType.NUDGE, StringsKt__StringsKt.trim(content.getText().toString()).toString()), new CoveApiListener<HandleBuddyRequest, CoveApiErrorModel>() { // from class: com.coveiot.android.fitnessbuddies.adapters.ShowBuddiesProgressAdapter$onBindViewHolder$2$5$3
                    @Override // com.coveiot.coveaccess.CoveApiListener
                    public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                        LoadingDialog loadingDialog;
                        LoadingDialog loadingDialog2;
                        loadingDialog = ShowBuddiesProgressAdapter.this.f;
                        if (loadingDialog.isShowing()) {
                            loadingDialog2 = ShowBuddiesProgressAdapter.this.f;
                            loadingDialog2.dismiss();
                        }
                        Toast.makeText(ShowBuddiesProgressAdapter.this.getContext(), ShowBuddiesProgressAdapter.this.getContext().getString(R.string.error_sending_message), 0).show();
                        dialog.dismiss();
                    }

                    @Override // com.coveiot.coveaccess.CoveApiListener
                    public void onSuccess(@Nullable HandleBuddyRequest handleBuddyRequest) {
                        LoadingDialog loadingDialog;
                        LoadingDialog loadingDialog2;
                        loadingDialog = ShowBuddiesProgressAdapter.this.f;
                        if (loadingDialog.isShowing()) {
                            loadingDialog2 = ShowBuddiesProgressAdapter.this.f;
                            loadingDialog2.dismiss();
                        }
                        Toast.makeText(ShowBuddiesProgressAdapter.this.getContext(), ShowBuddiesProgressAdapter.this.getContext().getString(R.string.nudge_message_sent), 0).show();
                        dialog.dismiss();
                        ShowBuddiesProgressAdapter.this.i(i);
                        CoveEventBusManager.getInstance().getEventBus().post(new FitnessCheerEvent());
                    }
                });
            }
        }
    }

    public static final void h(ViewHolder viewHolder, ShowBuddiesProgressAdapter this$0, Dialog dialog, View view) {
        Intrinsics.checkNotNullParameter(viewHolder, "$viewHolder");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        if (kotlin.text.m.equals(StringsKt__StringsKt.trim(viewHolder.getReact().getText().toString()).toString(), this$0.e, true)) {
            AnalyticsLog analyticsLog = new AnalyticsLog();
            analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
            analyticsLog.setScreenName(FirebaseEventParams.ScreenName.NOTIFICATION_SCREEN.getValue());
            analyticsLog.setDescription(FirebaseEventParams.Description.APPLAUD_POPUP.getValue());
            analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.CANCEL_BUTTON.getValue());
            CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        } else if (kotlin.text.m.equals(StringsKt__StringsKt.trim(viewHolder.getReact().getText().toString()).toString(), this$0.d, true)) {
            AnalyticsLog analyticsLog2 = new AnalyticsLog();
            analyticsLog2.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
            analyticsLog2.setScreenName(FirebaseEventParams.ScreenName.NOTIFICATION_SCREEN.getValue());
            analyticsLog2.setDescription(FirebaseEventParams.Description.CHEER_POPUP.getValue());
            analyticsLog2.setUiElementName(FirebaseEventParams.UiElementName.CANCEL_BUTTON.getValue());
            CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog2);
        } else if (kotlin.text.m.equals(StringsKt__StringsKt.trim(viewHolder.getReact().getText().toString()).toString(), this$0.c, true)) {
            AnalyticsLog analyticsLog3 = new AnalyticsLog();
            analyticsLog3.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
            analyticsLog3.setScreenName(FirebaseEventParams.ScreenName.NOTIFICATION_SCREEN.getValue());
            analyticsLog3.setDescription(FirebaseEventParams.Description.NUDGE_POPUP.getValue());
            analyticsLog3.setUiElementName(FirebaseEventParams.UiElementName.CANCEL_BUTTON.getValue());
            CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog3);
        }
        dialog.dismiss();
    }

    @NotNull
    public final Context getContext() {
        return this.f4436a;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.b.size();
    }

    @NotNull
    public final List<BuddiesGoal> getMBuddiesGoals() {
        return this.b;
    }

    public final void i(int i) {
        PreferenceManager.Companion companion = PreferenceManager.Companion;
        List<BuddyReminderModel> fitnessBuddiesReminder = companion.getFitnessBuddiesReminder(this.f4436a);
        List<BuddyReminderModel> fitnessBuddiesReminder2 = companion.getFitnessBuddiesReminder(this.f4436a);
        if (!AppUtils.isEmpty(fitnessBuddiesReminder2)) {
            Intrinsics.checkNotNull(fitnessBuddiesReminder2);
            for (BuddyReminderModel buddyReminderModel : fitnessBuddiesReminder2) {
                int buddyUserId = buddyReminderModel.getBuddyUserId();
                Integer num = this.b.get(i).userId;
                if (num != null && buddyUserId == num.intValue()) {
                    buddyReminderModel.setHasRemindedBuddy(true);
                    Calendar calendar = Calendar.getInstance();
                    Intrinsics.checkNotNullExpressionValue(calendar, "getInstance()");
                    buddyReminderModel.setRemindDate(calendar);
                    Intrinsics.checkNotNull(fitnessBuddiesReminder);
                    List<BuddyReminderModel> mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) fitnessBuddiesReminder);
                    mutableList.set(mutableList.indexOf(buddyReminderModel), buddyReminderModel);
                    PreferenceManager.Companion.saveBuddiesReminderModel(this.f4436a, mutableList);
                }
            }
            return;
        }
        ArrayList arrayList = new ArrayList();
        Integer num2 = this.b.get(i).userId;
        Intrinsics.checkNotNullExpressionValue(num2, "mBuddiesGoals.get(i).userId");
        int intValue = num2.intValue();
        Calendar calendar2 = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(calendar2, "getInstance()");
        arrayList.add(new BuddyReminderModel(intValue, true, calendar2));
        companion.saveBuddiesReminderModel(this.f4436a, arrayList);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull final ViewHolder viewHolder, final int i) {
        Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
        viewHolder.getName().setText(this.b.get(i).userName);
        GlideUtils.loadCircularImage(this.f4436a, this.b.get(i).userDpUrl, viewHolder.getImage(), new ImageLodeListener() { // from class: com.coveiot.android.fitnessbuddies.adapters.m
            @Override // com.coveiot.utils.utility.ImageLodeListener
            public final void onImageLoaded() {
                ShowBuddiesProgressAdapter.e();
            }
        });
        final Integer num = this.b.get(i).goalId;
        double intValue = this.b.get(i).targetAchieved.intValue() / this.b.get(i).target.intValue();
        if (intValue >= 0.0d && intValue <= 0.5d) {
            viewHolder.getReact().setText(this.c);
        } else if (intValue > 0.5d && intValue < 1.0d) {
            viewHolder.getReact().setText(this.d);
        } else {
            viewHolder.getReact().setText(this.e);
        }
        int i2 = 100;
        int round = (int) Math.round(intValue * 100);
        if (round >= 100) {
            viewHolder.getProgressAcheived().setText(this.f4436a.getString(R.string.target_achieved));
        } else {
            TextView progressAcheived = viewHolder.getProgressAcheived();
            progressAcheived.setText((100 - round) + this.f4436a.getString(R.string.left_to_complete));
            i2 = round;
        }
        viewHolder.getProgress().setProgress(i2);
        viewHolder.getReact().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.fitnessbuddies.adapters.l
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ShowBuddiesProgressAdapter.f(ShowBuddiesProgressAdapter.this, viewHolder, i, num, view);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup viewGroup, int i) {
        Intrinsics.checkNotNullParameter(viewGroup, "viewGroup");
        View inflate = LayoutInflater.from(this.f4436a).inflate(R.layout.progress_list_view, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "from(context).inflate(R.…t_view, viewGroup, false)");
        return new ViewHolder(this, inflate);
    }
}
