package com.coveiot.android.activitymodes.activities;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.activitymodes.R;
import com.coveiot.android.activitymodes.eventmodels.StartGpsRun;
import com.coveiot.android.activitymodes.models.ResumeGpsRun;
import com.coveiot.android.activitymodes.preference.PreferenceManager;
import com.coveiot.android.activitymodes.services.WorkoutSessionService;
import com.coveiot.android.activitymodes.utils.ActivityMode;
import com.coveiot.android.activitymodes.utils.IndoorOutdoor;
import com.coveiot.android.activitymodes.utils.WorkoutConstants;
import com.coveiot.android.activitymodes.utils.WorkoutUtils;
import com.coveiot.android.leonardo.utils.MusicConstants;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.BottomSheetDialogOneButtonTitleMessage;
import com.coveiot.android.theme.ExtensionFuncsKt;
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.android.theme.GenericMessageDialog;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.utils.CoveEventBusManager;
import com.coveiot.utils.eventmodels.CurrentStepsValue;
import com.coveiot.utils.eventmodels.GetCurrentSteps;
import com.coveiot.utils.eventmodels.OnDeviceConnected;
import com.coveiot.utils.eventmodels.OnDeviceDisconnected;
import com.coveiot.utils.eventmodels.OnRunEnd;
import com.coveiot.utils.eventmodels.OnRunPaused;
import com.coveiot.utils.eventmodels.OnRunResumed;
import com.coveiot.utils.eventmodels.OnRunStarted;
import com.coveiot.utils.eventmodels.OnStopSession;
import com.coveiot.utils.utility.AppUtils;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import java.io.Serializable;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.Regex;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class ActivityWorkoutSession extends BaseActivity implements View.OnClickListener, ServiceConnection, WorkoutSessionService.RunSessionCallbacks {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public final String p;
    public ActivityMode q;
    public IndoorOutdoor r;
    public int s;
    @NotNull
    public final String t;
    @NotNull
    public final String u;
    @NotNull
    public final GpsSwitchReceiver v;
    @Nullable
    public WorkoutSessionService w;
    public boolean x;
    public boolean y;
    @NotNull
    public CountDownTimer z;

    /* loaded from: classes2.dex */
    public final class GpsSwitchReceiver extends BroadcastReceiver {
        public GpsSwitchReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(@NotNull Context context, @NotNull Intent intent) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(intent, "intent");
            String action = intent.getAction();
            Intrinsics.checkNotNull(action);
            if (new Regex("android.location.PROVIDERS_CHANGED").matches(action)) {
                if (AppUtils.isGpsEnabled(ActivityWorkoutSession.this) && AppUtils.checkIfLocationPermissionExists(ActivityWorkoutSession.this)) {
                    ((ImageView) ActivityWorkoutSession.this._$_findCachedViewById(R.id.location_update_image)).setImageDrawable(ActivityWorkoutSession.this.getResources().getDrawable(R.drawable.modes_loc_ic));
                    ((TextView) ActivityWorkoutSession.this._$_findCachedViewById(R.id.location_status)).setText(ActivityWorkoutSession.this.getResources().getString(R.string.gps_on));
                    return;
                }
                ((ImageView) ActivityWorkoutSession.this._$_findCachedViewById(R.id.location_update_image)).setImageDrawable(ActivityWorkoutSession.this.getResources().getDrawable(R.drawable.location_disable_image));
                ((TextView) ActivityWorkoutSession.this._$_findCachedViewById(R.id.location_status)).setText(ActivityWorkoutSession.this.getResources().getString(R.string.gps_off));
            }
        }
    }

    public ActivityWorkoutSession() {
        String simpleName = ActivityWorkoutSession.class.getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "ActivityWorkoutSession::class.java.simpleName");
        this.p = simpleName;
        this.t = MusicConstants.CMDPAUSE;
        this.u = "play";
        this.v = new GpsSwitchReceiver();
        this.z = new CountDownTimer() { // from class: com.coveiot.android.activitymodes.activities.ActivityWorkoutSession$countDownTimer$1
            {
                super(3000L, 1000L);
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                Log.d(ActivityWorkoutSession.this.getTAG(), "countDownTimer onFinish");
                ActivityWorkoutSession.this.setCountDownTimerActive(false);
                ActivityWorkoutSession.this.K(false);
            }

            @Override // android.os.CountDownTimer
            public void onTick(long j) {
                int i = (int) (j / 1000);
                if (i == 1) {
                    ActivityWorkoutSession activityWorkoutSession = ActivityWorkoutSession.this;
                    ImageView countdown_iv = (ImageView) activityWorkoutSession._$_findCachedViewById(R.id.countdown_iv);
                    Intrinsics.checkNotNullExpressionValue(countdown_iv, "countdown_iv");
                    activityWorkoutSession.changeImageWithImageView(activityWorkoutSession, countdown_iv, R.drawable.number_1);
                } else if (i != 2) {
                } else {
                    ActivityWorkoutSession activityWorkoutSession2 = ActivityWorkoutSession.this;
                    ImageView countdown_iv2 = (ImageView) activityWorkoutSession2._$_findCachedViewById(R.id.countdown_iv);
                    Intrinsics.checkNotNullExpressionValue(countdown_iv2, "countdown_iv");
                    activityWorkoutSession2.changeImageWithImageView(activityWorkoutSession2, countdown_iv2, R.drawable.number_2);
                }
            }
        };
    }

    public static final void A(GenericMessageDialog dialog, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        dialog.dismiss();
    }

    public static final void B(ActivityWorkoutSession this$0, GenericMessageDialog dialog, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        this$0.I();
        dialog.dismiss();
    }

    public static final void F(ActivityWorkoutSession this$0, GenericMessageDialog dialog, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        CoveEventBusManager.getInstance().getEventBus().post(new OnRunEnd(false));
        this$0.J();
        dialog.dismiss();
    }

    public static final void G(ActivityWorkoutSession this$0, GenericMessageDialog dialog, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        CoveEventBusManager.getInstance().getEventBus().post(new OnRunEnd(true));
        this$0.J();
        dialog.dismiss();
    }

    public static final void H(BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage, ActivityWorkoutSession this$0, OnStopSession onStopSession, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonTitleMessage, "$bottomSheetDialogOneButtonTitleMessage");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(onStopSession, "$onStopSession");
        bottomSheetDialogOneButtonTitleMessage.dismiss();
        this$0.finish();
        if (onStopSession.isSaveSession()) {
            this$0.J();
        }
    }

    public static final void y(GenericMessageDialog dialog, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        dialog.dismiss();
    }

    public static final void z(ActivityWorkoutSession this$0, GenericMessageDialog dialog, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        this$0.E();
        dialog.dismiss();
    }

    public final void C(boolean z) {
        int i = R.id.ib_start_run;
        ((ImageButton) _$_findCachedViewById(i)).setImageResource(R.drawable.ic_start_run);
        ((ImageButton) _$_findCachedViewById(i)).setTag(this.u);
        ((ImageButton) _$_findCachedViewById(i)).setVisibility(0);
        ((ImageButton) _$_findCachedViewById(R.id.ib_stop_run)).setVisibility(0);
        ((ImageButton) _$_findCachedViewById(R.id.lock_button)).setVisibility(8);
        ((TextView) _$_findCachedViewById(R.id.status_text)).setVisibility(8);
        ((ImageButton) _$_findCachedViewById(R.id.lock_press_button)).setVisibility(8);
        if (z) {
            return;
        }
        CoveEventBusManager.getInstance().getEventBus().post(new OnRunPaused(z));
    }

    public final void D(boolean z) {
        WorkoutSessionService workoutSessionService = this.w;
        boolean z2 = true;
        if (workoutSessionService == null || !workoutSessionService.isDiconnectedWhenPaused()) {
            z2 = false;
        }
        if (z2) {
            return;
        }
        int i = R.id.ib_start_run;
        ((ImageButton) _$_findCachedViewById(i)).setImageResource(R.drawable.ic_pause_run);
        ((ImageButton) _$_findCachedViewById(i)).setTag(this.t);
        ((ImageButton) _$_findCachedViewById(R.id.ib_stop_run)).setVisibility(8);
        ((ImageButton) _$_findCachedViewById(R.id.lock_button)).setVisibility(0);
        if (!z) {
            CoveEventBusManager.getInstance().getEventBus().post(new OnRunResumed());
        } else {
            CoveEventBusManager.getInstance().getEventBus().post(new ResumeGpsRun());
        }
    }

    public final void E() {
        int i = this.s;
        WorkoutUtils workoutUtils = WorkoutUtils.INSTANCE;
        ActivityMode activityMode = this.q;
        if (activityMode == null) {
            Intrinsics.throwUninitializedPropertyAccessException("activityMode");
            activityMode = null;
        }
        if (i < workoutUtils.getMinUnitsForSession(activityMode)) {
            String string = getString(R.string.save_session_title);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.save_session_title)");
            String string2 = getString(R.string.save_session_content);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.save_session_content)");
            final GenericMessageDialog genericMessageDialog = new GenericMessageDialog(this, string, string2);
            View findViewById = genericMessageDialog.findViewById(R.id.cancel);
            Intrinsics.checkNotNullExpressionValue(findViewById, "dialog.findViewById(R.id.cancel)");
            TextView textView = (TextView) findViewById;
            View findViewById2 = genericMessageDialog.findViewById(R.id.proceed);
            Intrinsics.checkNotNullExpressionValue(findViewById2, "dialog.findViewById(R.id.proceed)");
            TextView textView2 = (TextView) findViewById2;
            textView2.setText(getString(R.string.save));
            textView.setText(getString(R.string.discard));
            textView.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.activities.i2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityWorkoutSession.F(ActivityWorkoutSession.this, genericMessageDialog, view);
                }
            });
            textView2.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.activities.j2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityWorkoutSession.G(ActivityWorkoutSession.this, genericMessageDialog, view);
                }
            });
            genericMessageDialog.show();
            return;
        }
        CoveEventBusManager.getInstance().getEventBus().post(new OnRunEnd(true));
        J();
    }

    public final void I() {
        int i = R.id.ib_start_run;
        if (((ImageButton) _$_findCachedViewById(i)).getTag().equals(this.u)) {
            ((ImageButton) _$_findCachedViewById(R.id.ib_stop_run)).setVisibility(0);
        } else {
            ((ImageButton) _$_findCachedViewById(R.id.ib_stop_run)).setVisibility(8);
        }
        ((ImageButton) _$_findCachedViewById(i)).setVisibility(0);
        ((ImageButton) _$_findCachedViewById(R.id.lock_button)).setVisibility(0);
        ((ImageButton) _$_findCachedViewById(R.id.lock_press_button)).setVisibility(8);
        ((TextView) _$_findCachedViewById(R.id.status_text)).setVisibility(8);
    }

    public final void J() {
        Intent intent = new Intent(this, ActivityWorkoutHistory.class);
        ActivityMode activityMode = this.q;
        if (activityMode == null) {
            Intrinsics.throwUninitializedPropertyAccessException("activityMode");
            activityMode = null;
        }
        intent.putExtra("ACTIVITY_TYPE_EXTRA", activityMode == ActivityMode.WALK ? 0 : 1);
        startActivity(intent);
        finish();
    }

    public final void K(boolean z) {
        ((ConstraintLayout) _$_findCachedViewById(R.id.countdown_layout)).setVisibility(8);
        ((ConstraintLayout) _$_findCachedViewById(R.id.on_session_fragment)).setVisibility(0);
        if (z) {
            return;
        }
        CoveEventBusManager.getInstance().getEventBus().post(new OnRunStarted());
    }

    public final void L() {
        String string;
        TextView textView = (TextView) _$_findCachedViewById(R.id.location_status);
        if (AppUtils.isGpsEnabled(this) && AppUtils.checkIfLocationPermissionExists(this)) {
            string = getResources().getString(R.string.gps_on);
        } else {
            string = getResources().getString(R.string.gps_off);
        }
        textView.setText(string);
        registerReceiver(this.v, new IntentFilter("android.location.PROVIDERS_CHANGED"));
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

    public final void changeImageWithImageView(@NotNull Context context, @NotNull final ImageView imageView, final int i) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(imageView, "imageView");
        Animation loadAnimation = AnimationUtils.loadAnimation(context, 17432577);
        final Animation loadAnimation2 = AnimationUtils.loadAnimation(context, 17432576);
        loadAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.coveiot.android.activitymodes.activities.ActivityWorkoutSession$changeImageWithImageView$1
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(@NotNull Animation animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
                imageView.setImageResource(i);
                loadAnimation2.setAnimationListener(new Animation.AnimationListener() { // from class: com.coveiot.android.activitymodes.activities.ActivityWorkoutSession$changeImageWithImageView$1$onAnimationEnd$1
                    @Override // android.view.animation.Animation.AnimationListener
                    public void onAnimationEnd(@NotNull Animation animation2) {
                        Intrinsics.checkNotNullParameter(animation2, "animation");
                    }

                    @Override // android.view.animation.Animation.AnimationListener
                    public void onAnimationRepeat(@NotNull Animation animation2) {
                        Intrinsics.checkNotNullParameter(animation2, "animation");
                    }

                    @Override // android.view.animation.Animation.AnimationListener
                    public void onAnimationStart(@NotNull Animation animation2) {
                        Intrinsics.checkNotNullParameter(animation2, "animation");
                    }
                });
                imageView.startAnimation(loadAnimation2);
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(@NotNull Animation animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(@NotNull Animation animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
            }
        });
        imageView.startAnimation(loadAnimation);
    }

    @NotNull
    public final CountDownTimer getCountDownTimer() {
        return this.z;
    }

    public final boolean getCurrentStepsCommandCalled() {
        return this.y;
    }

    @Subscribe
    public final void getDailySteps(@NotNull CurrentStepsValue currentStepsValue) {
        Intrinsics.checkNotNullParameter(currentStepsValue, "currentStepsValue");
        String str = this.p;
        Log.d(str, "getDailySteps " + currentStepsValue.getSteps());
        this.y = false;
        Intrinsics.checkNotNull(this);
        new PreferenceManager(this).setInitialSteps(currentStepsValue.getSteps());
        new PreferenceManager(this).setInitialSteps(currentStepsValue.getSteps());
        WorkoutSessionService workoutSessionService = this.w;
        if (workoutSessionService != null) {
            workoutSessionService.setDiconnectedWhenPaused(false);
        }
        D(false);
    }

    @NotNull
    public final GpsSwitchReceiver getGpsSwitchReceiver() {
        return this.v;
    }

    @NotNull
    public final String getPAUSE() {
        return this.t;
    }

    @NotNull
    public final String getPLAY() {
        return this.u;
    }

    @NotNull
    public final String getTAG() {
        return this.p;
    }

    @Nullable
    public final WorkoutSessionService getWorkoutSessionService() {
        return this.w;
    }

    public final boolean isCountDownTimerActive() {
        return this.x;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (new PreferenceManager(this).isRunSessionActive()) {
            ExtensionFuncsKt.toastShort(this, "Please stop the session to exit");
        } else {
            super.onBackPressed();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(@Nullable View view) {
        Integer valueOf = view != null ? Integer.valueOf(view.getId()) : null;
        int i = R.id.ib_start_run;
        int id = ((ImageButton) _$_findCachedViewById(i)).getId();
        if (valueOf != null && valueOf.intValue() == id) {
            Object tag = ((ImageButton) _$_findCachedViewById(i)).getTag();
            if (Intrinsics.areEqual(tag, this.t)) {
                AnalyticsLog analyticsLog = new AnalyticsLog();
                analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
                analyticsLog.setScreenName(FirebaseEventParams.ScreenName.ACTIVITY_PROGRESS_SCREEN.getValue());
                analyticsLog.setDescription(FirebaseEventParams.Description.ACTIVITY_PAUSE.getValue());
                analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.ACTIVITY_PAUSE_BUTTON.getValue());
                CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
                WorkoutSessionService workoutSessionService = this.w;
                if (workoutSessionService != null) {
                    workoutSessionService.setPausedManually(true);
                }
                C(false);
                return;
            } else if (Intrinsics.areEqual(tag, this.u)) {
                AnalyticsLog analyticsLog2 = new AnalyticsLog();
                analyticsLog2.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
                analyticsLog2.setScreenName(FirebaseEventParams.ScreenName.ACTIVITY_PROGRESS_SCREEN.getValue());
                analyticsLog2.setDescription(FirebaseEventParams.Description.ACTIVITY_PLAY.getValue());
                analyticsLog2.setUiElementName(FirebaseEventParams.UiElementName.ACTIVITY_PLAY_BUTTON.getValue());
                CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog2);
                if (new PreferenceManager(this).isDeviceConnected()) {
                    if (this.y) {
                        return;
                    }
                    this.y = true;
                    CoveEventBusManager.getInstance().getEventBus().post(new GetCurrentSteps());
                    return;
                }
                String string = getString(R.string.device_disconnected);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.device_disconnected)");
                ExtensionFuncsKt.toastShort(this, string);
                return;
            } else {
                return;
            }
        }
        int i2 = R.id.lock_button;
        int id2 = ((ImageButton) _$_findCachedViewById(i2)).getId();
        if (valueOf != null && valueOf.intValue() == id2) {
            AnalyticsLog analyticsLog3 = new AnalyticsLog();
            analyticsLog3.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
            analyticsLog3.setScreenName(FirebaseEventParams.ScreenName.ACTIVITY_PROGRESS_SCREEN.getValue());
            analyticsLog3.setDescription(FirebaseEventParams.Description.UNLOCK_PHONE.getValue());
            analyticsLog3.setUiElementName(FirebaseEventParams.UiElementName.DEVICE_UNLOCK_BUTTON.getValue());
            CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog3);
            ((ImageButton) _$_findCachedViewById(R.id.ib_stop_run)).setVisibility(8);
            ((ImageButton) _$_findCachedViewById(i)).setVisibility(8);
            ((ImageButton) _$_findCachedViewById(i2)).setVisibility(8);
            ((TextView) _$_findCachedViewById(R.id.status_text)).setVisibility(0);
            ((ImageButton) _$_findCachedViewById(R.id.lock_press_button)).setVisibility(0);
            return;
        }
        int id3 = ((ImageButton) _$_findCachedViewById(R.id.ib_stop_run)).getId();
        if (valueOf != null && valueOf.intValue() == id3) {
            AnalyticsLog analyticsLog4 = new AnalyticsLog();
            analyticsLog4.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
            analyticsLog4.setScreenName(FirebaseEventParams.ScreenName.ACTIVITY_PROGRESS_SCREEN.getValue());
            analyticsLog4.setDescription(FirebaseEventParams.Description.ACTIVITY_STOP.getValue());
            analyticsLog4.setUiElementName(FirebaseEventParams.UiElementName.ACTIVITY_STOP_BUTTON.getValue());
            CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog4);
            String string2 = getResources().getString(R.string.end_session);
            Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(R.string.end_session)");
            String string3 = getResources().getString(R.string.end_your_run_session);
            Intrinsics.checkNotNullExpressionValue(string3, "resources.getString(R.string.end_your_run_session)");
            final GenericMessageDialog genericMessageDialog = new GenericMessageDialog(this, string2, string3);
            View findViewById = genericMessageDialog.findViewById(R.id.cancel);
            Intrinsics.checkNotNullExpressionValue(findViewById, "dialog.findViewById(R.id.cancel)");
            TextView textView = (TextView) findViewById;
            View findViewById2 = genericMessageDialog.findViewById(R.id.proceed);
            Intrinsics.checkNotNullExpressionValue(findViewById2, "dialog.findViewById(R.id.proceed)");
            TextView textView2 = (TextView) findViewById2;
            textView2.setText(getResources().getString(R.string.yes));
            textView.setText(getResources().getString(R.string.f2703no));
            textView.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.activities.m2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    ActivityWorkoutSession.y(GenericMessageDialog.this, view2);
                }
            });
            textView2.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.activities.k2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    ActivityWorkoutSession.z(ActivityWorkoutSession.this, genericMessageDialog, view2);
                }
            });
            genericMessageDialog.show();
            return;
        }
        int id4 = ((ImageButton) _$_findCachedViewById(R.id.lock_press_button)).getId();
        if (valueOf != null && valueOf.intValue() == id4) {
            AnalyticsLog analyticsLog5 = new AnalyticsLog();
            analyticsLog5.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
            analyticsLog5.setScreenName(FirebaseEventParams.ScreenName.ACTIVITY_PROGRESS_SCREEN.getValue());
            analyticsLog5.setDescription(FirebaseEventParams.Description.LOCK_PHONE.getValue());
            analyticsLog5.setUiElementName(FirebaseEventParams.UiElementName.DEVICE_LOCK_BUTTON.getValue());
            CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog5);
            String string4 = getResources().getString(R.string.unlock);
            Intrinsics.checkNotNullExpressionValue(string4, "resources.getString(R.string.unlock)");
            String string5 = getResources().getString(R.string.unlock_your_screen);
            Intrinsics.checkNotNullExpressionValue(string5, "resources.getString(R.string.unlock_your_screen)");
            final GenericMessageDialog genericMessageDialog2 = new GenericMessageDialog(this, string4, string5);
            View findViewById3 = genericMessageDialog2.findViewById(R.id.cancel);
            Intrinsics.checkNotNullExpressionValue(findViewById3, "dialog.findViewById(R.id.cancel)");
            TextView textView3 = (TextView) findViewById3;
            View findViewById4 = genericMessageDialog2.findViewById(R.id.proceed);
            Intrinsics.checkNotNullExpressionValue(findViewById4, "dialog.findViewById(R.id.proceed)");
            TextView textView4 = (TextView) findViewById4;
            textView4.setText(getResources().getString(R.string.yes));
            textView3.setText(getResources().getString(R.string.f2703no));
            textView3.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.activities.n2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    ActivityWorkoutSession.A(GenericMessageDialog.this, view2);
                }
            });
            textView4.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.activities.h2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    ActivityWorkoutSession.B(ActivityWorkoutSession.this, genericMessageDialog2, view2);
                }
            });
            genericMessageDialog2.show();
        }
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        Serializable serializableExtra = getIntent().getSerializableExtra(WorkoutConstants.ACTIVITY_MODE);
        Intrinsics.checkNotNull(serializableExtra, "null cannot be cast to non-null type com.coveiot.android.activitymodes.utils.ActivityMode");
        this.q = (ActivityMode) serializableExtra;
        Serializable serializableExtra2 = getIntent().getSerializableExtra(WorkoutConstants.INDOOR_OUTDOOR);
        Intrinsics.checkNotNull(serializableExtra2, "null cannot be cast to non-null type com.coveiot.android.activitymodes.utils.IndoorOutdoor");
        this.r = (IndoorOutdoor) serializableExtra2;
        this.y = false;
        getWindow().addFlags(6815872);
        setContentView(R.layout.activity_workout_tracking);
        Boolean isDistanceUnitInMile = UserDataManager.getInstance(this).isDistanceUnitInMile();
        Intrinsics.checkNotNullExpressionValue(isDistanceUnitInMile, "getInstance(this).isDistanceUnitInMile");
        if (isDistanceUnitInMile.booleanValue()) {
            ((TextView) _$_findCachedViewById(R.id.tv_distance_unit)).setText(getString(R.string.dist_mile));
        } else {
            ((TextView) _$_findCachedViewById(R.id.tv_distance_unit)).setText(getString(R.string.kilometres));
        }
        if (!new PreferenceManager(this).isRunSessionActive()) {
            J();
        } else if (x()) {
            K(true);
        } else {
            Bus eventBus = CoveEventBusManager.getInstance().getEventBus();
            ActivityMode activityMode = this.q;
            IndoorOutdoor indoorOutdoor = null;
            if (activityMode == null) {
                Intrinsics.throwUninitializedPropertyAccessException("activityMode");
                activityMode = null;
            }
            eventBus.post(new StartGpsRun(activityMode.name()));
            this.z.start();
            this.x = true;
            Intent intent = new Intent(this, WorkoutSessionService.class);
            ActivityMode activityMode2 = this.q;
            if (activityMode2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("activityMode");
                activityMode2 = null;
            }
            intent.putExtra(WorkoutConstants.ACTIVITY_MODE, activityMode2);
            IndoorOutdoor indoorOutdoor2 = this.r;
            if (indoorOutdoor2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("indoorOutdoor");
            } else {
                indoorOutdoor = indoorOutdoor2;
            }
            intent.putExtra(WorkoutConstants.INDOOR_OUTDOOR, indoorOutdoor);
            if (Build.VERSION.SDK_INT >= 26) {
                startForegroundService(intent);
            } else {
                startService(intent);
            }
        }
        ((ImageButton) _$_findCachedViewById(R.id.ib_start_run)).setOnClickListener(this);
        ((ImageButton) _$_findCachedViewById(R.id.ib_stop_run)).setOnClickListener(this);
        int i = R.id.lock_button;
        ((ImageButton) _$_findCachedViewById(i)).setOnClickListener(this);
        ((ImageButton) _$_findCachedViewById(R.id.lock_press_button)).setOnClickListener(this);
        ((ImageButton) _$_findCachedViewById(i)).performClick();
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.KH_OPEN.getValue());
        analyticsLog.setPreviousScreenName(FirebaseEventParams.ScreenName.MAIN_ACTIVITY_DASHBOARD.getValue());
        analyticsLog.setScreenName(FirebaseEventParams.ScreenName.ACTIVITY_PROGRESS_SCREEN.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        Log.d(this.p, "onDestroy: called");
        int i = this.s;
        WorkoutUtils workoutUtils = WorkoutUtils.INSTANCE;
        ActivityMode activityMode = this.q;
        if (activityMode == null) {
            Intrinsics.throwUninitializedPropertyAccessException("activityMode");
            activityMode = null;
        }
        if (i < workoutUtils.getMinUnitsForSession(activityMode)) {
            CoveEventBusManager.getInstance().getEventBus().post(new OnRunEnd(false));
        } else {
            CoveEventBusManager.getInstance().getEventBus().post(new OnRunEnd(true, true));
        }
        super.onDestroy();
    }

    @Subscribe
    public final void onDeviceConnected(@NotNull OnDeviceConnected onDeviceConnected) {
        Intrinsics.checkNotNullParameter(onDeviceConnected, "onDeviceConnected");
        new PreferenceManager(this).setDeviceConnected(true);
        ((TextView) _$_findCachedViewById(R.id.connection_status_text)).setVisibility(8);
        ((TextView) _$_findCachedViewById(R.id.fatigue_status)).setVisibility(0);
        D(true);
    }

    @Subscribe
    public final void onDeviceDisconnected(@NotNull OnDeviceDisconnected onDeviceDisconnected) {
        Intrinsics.checkNotNullParameter(onDeviceDisconnected, "onDeviceDisconnected");
        new PreferenceManager(this).setDeviceConnected(false);
        ((TextView) _$_findCachedViewById(R.id.connection_status_text)).setVisibility(0);
        ((TextView) _$_findCachedViewById(R.id.fatigue_status)).setVisibility(8);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        WorkoutSessionService workoutSessionService = this.w;
        if (workoutSessionService != null) {
            workoutSessionService.unregisterClient();
        }
        unbindService(this);
        unregisterReceiver(this.v);
    }

    @Override // android.app.Activity
    public void onRestart() {
        super.onRestart();
        if (new PreferenceManager(this).isRunSessionActive()) {
            return;
        }
        J();
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        bindService(new Intent(this, WorkoutSessionService.class), this, 1);
        registerReceiver(this.v, new IntentFilter("android.location.PROVIDERS_CHANGED"));
        L();
        ((TextView) _$_findCachedViewById(R.id.tv_steps)).setVisibility(8);
    }

    @Subscribe
    public final void onRunPaused(@Nullable OnRunPaused onRunPaused) {
        C(true);
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(@Nullable ComponentName componentName, @Nullable IBinder iBinder) {
        Log.d(this.p, "onServiceConnected: called");
        Intrinsics.checkNotNull(iBinder, "null cannot be cast to non-null type com.coveiot.android.activitymodes.services.WorkoutSessionService.TrackSessionServiceBinder");
        WorkoutSessionService service = ((WorkoutSessionService.TrackSessionServiceBinder) iBinder).getService();
        this.w = service;
        if (service != null) {
            service.registerClient(this);
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(@Nullable ComponentName componentName) {
        Log.d(this.p, "onServiceDisconnected: called");
        this.w = null;
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        CoveEventBusManager.getInstance().getEventBus().register(this);
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        CoveEventBusManager.getInstance().getEventBus().unregister(this);
        this.x = false;
        super.onStop();
    }

    @Subscribe
    public final void onStopSessionRecievedFromService(@NotNull final OnStopSession onStopSession) {
        Intrinsics.checkNotNullParameter(onStopSession, "onStopSession");
        String string = getString(R.string.disconneted_message);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.disconneted_message)");
        if (!onStopSession.isSaveSession()) {
            string = getString(R.string.disconneted_message2);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.disconneted_message2)");
        }
        String string2 = getString(R.string.device_disconnected);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.device_disconnected)");
        final BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = new BottomSheetDialogOneButtonTitleMessage(this, string2, string);
        bottomSheetDialogOneButtonTitleMessage.setCancelable(false);
        String string3 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.ok)");
        bottomSheetDialogOneButtonTitleMessage.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.activities.l2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityWorkoutSession.H(BottomSheetDialogOneButtonTitleMessage.this, this, onStopSession, view);
            }
        });
        bottomSheetDialogOneButtonTitleMessage.show();
    }

    public final void setCountDownTimer(@NotNull CountDownTimer countDownTimer) {
        Intrinsics.checkNotNullParameter(countDownTimer, "<set-?>");
        this.z = countDownTimer;
    }

    public final void setCountDownTimerActive(boolean z) {
        this.x = z;
    }

    public final void setCurrentStepsCommandCalled(boolean z) {
        this.y = z;
    }

    public final void setWorkoutSessionService(@Nullable WorkoutSessionService workoutSessionService) {
        this.w = workoutSessionService;
    }

    @Override // com.coveiot.android.activitymodes.services.WorkoutSessionService.RunSessionCallbacks
    public void showFeedbackScreen(@NotNull String sessionId) {
        Intrinsics.checkNotNullParameter(sessionId, "sessionId");
        Intent intent = new Intent(this, ActivityWorkoutFeedback.class);
        intent.putExtra(WorkoutConstants.SESSION_ID, sessionId);
        startActivity(intent);
    }

    @Override // com.coveiot.android.activitymodes.services.WorkoutSessionService.RunSessionCallbacks
    public void updateDuration(int i) {
        StringBuilder sb = new StringBuilder();
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Locale locale = Locale.ENGLISH;
        String format = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(i / 60)}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
        sb.append(format);
        sb.append(" : ");
        String format2 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(i % 60)}, 1));
        Intrinsics.checkNotNullExpressionValue(format2, "format(locale, format, *args)");
        sb.append(format2);
        ((TextView) _$_findCachedViewById(R.id.tv_duration)).setText(sb.toString());
    }

    @Override // com.coveiot.android.activitymodes.services.WorkoutSessionService.RunSessionCallbacks
    public void updateHeartRateInfo(int i, int i2, int i3) {
        if (i != 0) {
            ((TextView) _$_findCachedViewById(R.id.tv_hr)).setText(i + ' ' + getResources().getString(R.string.bpm_small));
        } else {
            ((TextView) _$_findCachedViewById(R.id.tv_hr)).setText(getResources().getString(R.string.empty_bpm));
        }
        if (i2 != 0) {
            ((TextView) _$_findCachedViewById(R.id.tv_max_hr)).setText(i2 + ' ' + getResources().getString(R.string.bpm_small));
        } else {
            ((TextView) _$_findCachedViewById(R.id.tv_max_hr)).setText(getResources().getString(R.string.empty_bpm));
        }
        if (i3 != 0) {
            ((TextView) _$_findCachedViewById(R.id.tv_min_hr)).setText(i3 + ' ' + getResources().getString(R.string.bpm_small));
            return;
        }
        ((TextView) _$_findCachedViewById(R.id.tv_min_hr)).setText(getResources().getString(R.string.empty_bpm));
    }

    @Override // com.coveiot.android.activitymodes.services.WorkoutSessionService.RunSessionCallbacks
    public void updatePace(@NotNull String pace) {
        Intrinsics.checkNotNullParameter(pace, "pace");
        ((TextView) _$_findCachedViewById(R.id.tv_pace)).setText(pace);
    }

    @Override // com.coveiot.android.activitymodes.services.WorkoutSessionService.RunSessionCallbacks
    public void updateStepsInfo(int i, int i2, float f) {
        this.s = i;
        String str = this.p;
        Log.d(str, "Disatnce :" + i2);
        WorkoutUtils workoutUtils = WorkoutUtils.INSTANCE;
        float convertCmToKm = workoutUtils.convertCmToKm(i2);
        Boolean isDistanceUnitInMile = UserDataManager.getInstance(this).isDistanceUnitInMile();
        Intrinsics.checkNotNullExpressionValue(isDistanceUnitInMile, "getInstance(this).isDistanceUnitInMile");
        if (isDistanceUnitInMile.booleanValue()) {
            convertCmToKm = (float) workoutUtils.convertKMToMiles(convertCmToKm);
        }
        DecimalFormat decimalFormat = new DecimalFormat("#00.000");
        decimalFormat.setRoundingMode(RoundingMode.CEILING);
        Locale locale = Locale.ENGLISH;
        decimalFormat.setDecimalFormatSymbols(new DecimalFormatSymbols(locale));
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format(locale, "%.1f", Arrays.copyOf(new Object[]{Float.valueOf(f)}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
        ((TextView) _$_findCachedViewById(R.id.tv_calories)).setText(format);
        ((TextView) _$_findCachedViewById(R.id.tv_distance)).setText(decimalFormat.format(Float.valueOf(convertCmToKm)));
        ((TextView) _$_findCachedViewById(R.id.tv_steps)).setText(this.s + " Steps");
    }

    public final boolean x() {
        Object systemService = getSystemService("activity");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.ActivityManager");
        for (ActivityManager.RunningServiceInfo runningServiceInfo : ((ActivityManager) systemService).getRunningServices(Integer.MAX_VALUE)) {
            if (Intrinsics.areEqual(WorkoutSessionService.class.getName(), runningServiceInfo.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}
