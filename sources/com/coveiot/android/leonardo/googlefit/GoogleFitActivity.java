package com.coveiot.android.leonardo.googlefit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.boat.R;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.utils.utility.AppUtils;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.fitness.FitnessOptions;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.HealthDataTypes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class GoogleFitActivity extends BaseActivity {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final int r = 1;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public final int p = 1001;
    public boolean q;

    /* loaded from: classes2.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public static final void A(GoogleFitActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    public static final void v(GoogleFitActivity this$0, Task it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        ((Button) this$0._$_findCachedViewById(R.id.google_fit_connect)).setText(R.string.fit_connect);
        UserDataManager.getInstance(this$0).saveGoogleFitConnect(false);
        GoogleFitSyncWorkManager.Companion.cancelAll(this$0);
    }

    public static final void x(GoogleFitActivity this$0, Task it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        ((Button) this$0._$_findCachedViewById(R.id.google_fit_connect)).setText(R.string.fit_connect);
        UserDataManager.getInstance(this$0).saveGoogleFitConnect(false);
        GoogleFitSyncWorkManager.Companion.cancelAll(this$0);
    }

    public static final void y(GoogleFitActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.q) {
            if (ContextCompat.checkSelfPermission(this$0, "android.permission.BODY_SENSORS") == 0) {
                this$0.w();
                return;
            } else {
                this$0.requestPermissions(new String[]{"android.permission.BODY_SENSORS"}, this$0.p);
                return;
            }
        }
        this$0.w();
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

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1 && i == r) {
            GoogleFitSyncWorkManager.Companion.scheduleRepeat(this);
            UserDataManager.getInstance(this).saveGoogleFitConnect(true);
            new GoogleFitDataManager(this).insertAndReadData();
            if (!UserDataManager.getInstance(this).isGoogleFitConnected(this)) {
                ((Button) _$_findCachedViewById(R.id.google_fit_connect)).setText(R.string.fit_connect);
                ((TextView) _$_findCachedViewById(R.id.google_fit_content2)).setText(getString(R.string.fit_msg1));
                return;
            }
            ((Button) _$_findCachedViewById(R.id.google_fit_connect)).setText(R.string.fit_disconnect);
            ((TextView) _$_findCachedViewById(R.id.google_fit_content2)).setText(R.string.connected_msg);
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
        analyticsLog.setScreenName(FirebaseEventParams.ScreenName.GOOGLE_FIT_SCREEN.getValue());
        analyticsLog.setDescription(FirebaseEventParams.Description.CONNECT_GOOGLE_FIT.getValue());
        analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.BACK_BUTTON.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_google_fit);
        Boolean isGoogleFitHrSPO2TempSleepSupportAvailable = UserDataManager.getInstance(this).isGoogleFitHrSPO2TempSleepSupportAvailable();
        Intrinsics.checkNotNullExpressionValue(isGoogleFitHrSPO2TempSleepSupportAvailable, "getInstance(this@GoogleF…TempSleepSupportAvailable");
        this.q = isGoogleFitHrSPO2TempSleepSupportAvailable.booleanValue();
        u();
        z();
        if (!UserDataManager.getInstance(this).isGoogleFitConnected(this)) {
            ((Button) _$_findCachedViewById(R.id.google_fit_connect)).setText(R.string.fit_connect);
        } else {
            ((Button) _$_findCachedViewById(R.id.google_fit_connect)).setText(R.string.fit_disconnect);
        }
        ((Button) _$_findCachedViewById(R.id.google_fit_connect)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.googlefit.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                GoogleFitActivity.y(GoogleFitActivity.this, view);
            }
        });
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.KH_OPEN.getValue());
        analyticsLog.setPreviousScreenName(FirebaseEventParams.ScreenName.BOTTOM_MENU_MORE_SCREEN.getValue());
        analyticsLog.setScreenName(FirebaseEventParams.ScreenName.GOOGLE_FIT_SCREEN.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        if (i == this.p) {
            Integer firstOrNull = ArraysKt___ArraysKt.firstOrNull(grantResults);
            if (firstOrNull == null || firstOrNull.intValue() != 0) {
                Toast.makeText(this, getString(R.string.please_enable_body_sensor_permission), 1).show();
            } else {
                w();
            }
        }
        super.onRequestPermissionsResult(i, permissions, grantResults);
    }

    public final void u() {
        if (this.q && ContextCompat.checkSelfPermission(this, "android.permission.BODY_SENSORS") != 0 && AppUtils.isNetConnected(this) && UserDataManager.getInstance(this).isGoogleFitConnected(this)) {
            AnalyticsLog analyticsLog = new AnalyticsLog();
            analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
            analyticsLog.setScreenName(FirebaseEventParams.ScreenName.GOOGLE_FIT_SCREEN.getValue());
            analyticsLog.setDescription(FirebaseEventParams.Description.OPEN_GOOGLE_FIT_SCREEN.getValue());
            analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.DISCONNECT_BUTTON.getValue());
            CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
            GoogleSignIn.getClient((Activity) this, GoogleSignInOptions.DEFAULT_SIGN_IN).signOut().addOnCompleteListener(new OnCompleteListener() { // from class: com.coveiot.android.leonardo.googlefit.c
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public final void onComplete(Task task) {
                    GoogleFitActivity.v(GoogleFitActivity.this, task);
                }
            });
        }
    }

    public final void w() {
        if (AppUtils.isNetConnected(this)) {
            if (!UserDataManager.getInstance(this).isGoogleFitConnected(this)) {
                AnalyticsLog analyticsLog = new AnalyticsLog();
                analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
                analyticsLog.setScreenName(FirebaseEventParams.ScreenName.GOOGLE_FIT_SCREEN.getValue());
                analyticsLog.setDescription(FirebaseEventParams.Description.CONNECT_GOOGLE_FIT.getValue());
                analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.CONNECT_BUTTON.getValue());
                CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
                int i = R.id.google_fit_content2;
                ((TextView) _$_findCachedViewById(i)).setText(getString(R.string.fit_msg1));
                FitnessOptions.Builder builder = FitnessOptions.builder();
                Intrinsics.checkNotNullExpressionValue(builder, "builder()");
                FitnessOptions.Builder addDataType = builder.addDataType(DataType.TYPE_STEP_COUNT_DELTA, 1);
                DataType dataType = DataType.AGGREGATE_DISTANCE_DELTA;
                FitnessOptions.Builder addDataType2 = addDataType.addDataType(dataType, 1);
                DataType dataType2 = DataType.TYPE_DISTANCE_DELTA;
                FitnessOptions.Builder addDataType3 = addDataType2.addDataType(dataType2, 1).addDataType(dataType, 1);
                DataType dataType3 = DataType.TYPE_CALORIES_EXPENDED;
                addDataType3.addDataType(dataType3, 1).addDataType(DataType.AGGREGATE_CALORIES_EXPENDED, 1).addDataType(DataType.TYPE_ACTIVITY_SEGMENT, 1).addDataType(dataType3, 1).addDataType(dataType2, 1);
                if (this.q) {
                    builder.addDataType(DataType.TYPE_HEART_RATE_BPM, 1);
                    builder.addDataType(DataType.TYPE_SLEEP_SEGMENT, 1);
                    builder.addDataType(HealthDataTypes.TYPE_OXYGEN_SATURATION, 1);
                    builder.addDataType(HealthDataTypes.TYPE_BODY_TEMPERATURE, 1);
                    builder.accessSleepSessions(1);
                }
                FitnessOptions build = builder.build();
                Intrinsics.checkNotNullExpressionValue(build, "fitnessOptionsBuilder.build()");
                if (!GoogleSignIn.hasPermissions(GoogleSignIn.getLastSignedInAccount(this), build)) {
                    GoogleSignIn.requestPermissions(this, r, GoogleSignIn.getLastSignedInAccount(this), build);
                    return;
                }
                ((TextView) _$_findCachedViewById(i)).setText(R.string.connected_msg);
                ((Button) _$_findCachedViewById(R.id.google_fit_connect)).setText(R.string.fit_disconnect);
                UserDataManager.getInstance(this).saveGoogleFitConnect(true);
                new GoogleFitDataManager(this).insertAndReadData();
                GoogleFitSyncWorkManager.Companion.scheduleRepeat(this);
                return;
            }
            AnalyticsLog analyticsLog2 = new AnalyticsLog();
            analyticsLog2.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
            analyticsLog2.setScreenName(FirebaseEventParams.ScreenName.GOOGLE_FIT_SCREEN.getValue());
            analyticsLog2.setDescription(FirebaseEventParams.Description.OPEN_GOOGLE_FIT_SCREEN.getValue());
            analyticsLog2.setUiElementName(FirebaseEventParams.UiElementName.DISCONNECT_BUTTON.getValue());
            CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog2);
            GoogleSignIn.getClient((Activity) this, GoogleSignInOptions.DEFAULT_SIGN_IN).signOut().addOnCompleteListener(new OnCompleteListener() { // from class: com.coveiot.android.leonardo.googlefit.d
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public final void onComplete(Task task) {
                    GoogleFitActivity.x(GoogleFitActivity.this, task);
                }
            });
            return;
        }
        showNoInternetMessage();
    }

    public final void z() {
        ((TextView) findViewById(R.id.toolbar_title)).setText(getString(R.string.google_fit_screen_title));
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.googlefit.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                GoogleFitActivity.A(GoogleFitActivity.this, view);
            }
        });
    }
}
