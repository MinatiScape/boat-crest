package com.coveiot.android.activitymodes.autodetection.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.coveiot.android.activitymodes.R;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.covepreferences.UserDataManager;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class ActivityAutoRecognitionActivitiesWithOneKDisclaimer extends BaseActivity {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();

    public static final void u(ActivityAutoRecognitionActivitiesWithOneKDisclaimer this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.x();
    }

    public static final void v(ActivityAutoRecognitionActivitiesWithOneKDisclaimer this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        UserDataManager.getInstance(this$0).saveAutoActivityDetectionDisclaimerSeen(Boolean.valueOf(z));
    }

    public static final void w(ActivityAutoRecognitionActivitiesWithOneKDisclaimer this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
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

    public final void initToolbar() {
        ((TextView) findViewById(R.id.toolbar_title)).setText(getString(R.string.auto_activity_detection));
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.autodetection.activities.k
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityAutoRecognitionActivitiesWithOneKDisclaimer.w(ActivityAutoRecognitionActivitiesWithOneKDisclaimer.this, view);
            }
        });
        ((ImageView) findViewById(R.id.share_iv)).setVisibility(8);
        ((TextView) findViewById(R.id.save)).setVisibility(8);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        finish();
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_auto_recog_with_onek_disclaimer);
        initToolbar();
        t();
    }

    public final void t() {
        ((Button) _$_findCachedViewById(R.id.btn_ok)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.autodetection.activities.l
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityAutoRecognitionActivitiesWithOneKDisclaimer.u(ActivityAutoRecognitionActivitiesWithOneKDisclaimer.this, view);
            }
        });
        ((CheckBox) _$_findCachedViewById(R.id.cb_do_not_show_again)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.coveiot.android.activitymodes.autodetection.activities.m
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                ActivityAutoRecognitionActivitiesWithOneKDisclaimer.v(ActivityAutoRecognitionActivitiesWithOneKDisclaimer.this, compoundButton, z);
            }
        });
    }

    public final void x() {
        if (BleApiManager.getInstance(this).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
            startActivity(new Intent(this, ActivityAutoRecognitionActivitiesWithOneK.class));
        } else {
            Toast.makeText(this, getString(R.string.band_not_connected), 1).show();
        }
        finish();
    }
}
