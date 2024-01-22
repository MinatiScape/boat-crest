package com.coveiot.android.leonardo.more.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.PickerDialog;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.jstyle.blesdk1860.constant.BleConst;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityAlarmSettings extends BaseActivity {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public TextView p;
    @Nullable
    public String q;
    @Nullable
    public String r;

    public static final void u(final ActivityAlarmSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        PickerDialog.Companion companion = PickerDialog.Companion;
        String str = this$0.q;
        Intrinsics.checkNotNull(str);
        companion.minutesPicker(this$0, Integer.parseInt(str), 5, 15, 5, new PickerDialog.Companion.SelectionListener() { // from class: com.coveiot.android.leonardo.more.activities.ActivityAlarmSettings$initClickListeners$1$1
            @Override // com.coveiot.android.theme.PickerDialog.Companion.SelectionListener
            public void onValueSelected(@NotNull String value) {
                Intrinsics.checkNotNullParameter(value, "value");
                if (ActivityAlarmSettings.this.isFinishing()) {
                    return;
                }
                ((TextView) ActivityAlarmSettings.this._$_findCachedViewById(R.id.tv_interval_time)).setText(value + ' ' + ActivityAlarmSettings.this.getResources().getString(R.string.min));
                ActivityAlarmSettings.this.setInterval_val(value);
            }
        });
    }

    public static final void v(final ActivityAlarmSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        PickerDialog.Companion companion = PickerDialog.Companion;
        String str = this$0.r;
        Intrinsics.checkNotNull(str);
        companion.repeatReminderPicker(this$0, str, 1, 3, 1, new PickerDialog.Companion.SelectionListener() { // from class: com.coveiot.android.leonardo.more.activities.ActivityAlarmSettings$initClickListeners$2$1
            @Override // com.coveiot.android.theme.PickerDialog.Companion.SelectionListener
            public void onValueSelected(@NotNull String value) {
                Intrinsics.checkNotNullParameter(value, "value");
                if (ActivityAlarmSettings.this.isFinishing()) {
                    return;
                }
                ((TextView) ActivityAlarmSettings.this._$_findCachedViewById(R.id.tv_repeat_count)).setText(value);
                ActivityAlarmSettings activityAlarmSettings = ActivityAlarmSettings.this;
                String string = activityAlarmSettings.getResources().getString(R.string.counts);
                Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.counts)");
                activityAlarmSettings.setRepeat_count(kotlin.text.m.replace$default(kotlin.text.m.replace$default(value, string, "", false, 4, (Object) null), HexStringBuilder.DEFAULT_SEPARATOR, "", false, 4, (Object) null));
            }
        });
    }

    public static final void w(ActivityAlarmSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    public static final void x(ActivityAlarmSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String str = this$0.q;
        if (str != null) {
            Intrinsics.checkNotNull(str);
            int parseInt = Integer.parseInt(str);
            boolean z = false;
            if (1 <= parseInt && parseInt < 10) {
                z = true;
            }
            if (z) {
                this$0.q = '0' + this$0.q;
            }
        }
        this$0.getIntent().putExtra(AppConstants.SNOOZE_TIME_INTERVAL_KEY.getValue(), "00:" + this$0.q + ":00");
        this$0.getIntent().putExtra(AppConstants.SNOOZE_REPEAT_COUNT_KEY.getValue(), this$0.r);
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
    public final String getInterval_val() {
        return this.q;
    }

    @Nullable
    public final String getRepeat_count() {
        return this.r;
    }

    public final void initClickListeners() {
        ((TextView) _$_findCachedViewById(R.id.tv_interval_time)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.w
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityAlarmSettings.u(ActivityAlarmSettings.this, view);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.tv_repeat_count)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.u
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityAlarmSettings.v(ActivityAlarmSettings.this, view);
            }
        });
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.x
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityAlarmSettings.w(ActivityAlarmSettings.this, view);
            }
        });
        ((Button) _$_findCachedViewById(R.id.btn_save)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.v
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityAlarmSettings.x(ActivityAlarmSettings.this, view);
            }
        });
    }

    public final void initToolbar() {
        ((TextView) findViewById(R.id.toolbar_title)).setText(getString(R.string.alarm_settings));
        View findViewById = findViewById(R.id.save);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById<TextView>(R.id.save)");
        TextView textView = (TextView) findViewById;
        this.p = textView;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("saveText");
            textView = null;
        }
        textView.setVisibility(8);
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_alarm_setting);
        initToolbar();
        initClickListeners();
        String stringExtra = getIntent().getStringExtra("SNOOZE_TIME_INTERVAL_KEY");
        Intrinsics.checkNotNull(stringExtra);
        this.q = kotlin.text.m.replace$default(kotlin.text.m.replace$default(stringExtra, "00", "", false, 4, (Object) null), ":", "", false, 4, (Object) null);
        this.r = getIntent().getStringExtra("SNOOZE_REPEAT_COUNT_KEY");
        String str = this.q;
        if (str != null) {
            int parseInt = Integer.parseInt(str);
            boolean z = false;
            if (1 <= parseInt && parseInt < 10) {
                z = true;
            }
            if (z) {
                this.q = kotlin.text.m.replace$default(str, BleConst.GetDeviceTime, "", false, 4, (Object) null);
            }
            ((TextView) _$_findCachedViewById(R.id.tv_interval_time)).setText(this.q + ' ' + getResources().getString(R.string.min));
        }
        String str2 = this.r;
        if (str2 != null) {
            if (Integer.parseInt(str2) == 0) {
                ((TextView) _$_findCachedViewById(R.id.tv_repeat_count)).setText(getResources().getString(R.string.donot_repeat));
                return;
            }
            ((TextView) _$_findCachedViewById(R.id.tv_repeat_count)).setText(str2 + ' ' + getResources().getString(R.string.counts));
        }
    }

    public final void setInterval_val(@Nullable String str) {
        this.q = str;
    }

    public final void setRepeat_count(@Nullable String str) {
        this.r = str;
    }
}
