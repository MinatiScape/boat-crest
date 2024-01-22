package com.coveiot.android.customreminders.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.coveiot.android.customreminders.R;
import com.coveiot.android.customreminders.fragments.FragmentNoReminders;
import com.coveiot.android.customreminders.fragments.FragmentRemindersList;
import com.coveiot.android.customreminders.model.CustomReminder;
import com.coveiot.android.customreminders.preference.PreferenceManagerCustomReminders;
import com.coveiot.android.theme.BaseActivity;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class ActivityCustomReminders extends BaseActivity {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();

    public static final void r(ActivityCustomReminders this$0, View view) {
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
        ((TextView) findViewById(R.id.toolbar_title)).setText(getString(R.string.reminders));
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.customreminders.activities.d
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityCustomReminders.r(ActivityCustomReminders.this, view);
            }
        });
    }

    public final void loadAddReminderScreen() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, FragmentNoReminders.Companion.newInstance(true)).addToBackStack(Reflection.getOrCreateKotlinClass(FragmentNoReminders.class).getSimpleName()).commit();
    }

    public final void loadData() {
        List<CustomReminder> reminders = PreferenceManagerCustomReminders.Companion.getReminders(this);
        if (reminders != null && reminders.size() > 0) {
            loadRemindersListFragment();
        } else {
            loadNoRemindersFragment();
        }
    }

    public final void loadNoRemindersFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, FragmentNoReminders.Companion.newInstance$default(FragmentNoReminders.Companion, false, 1, null)).commit();
    }

    public final void loadRemindersListFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, FragmentRemindersList.Companion.newInstance()).commit();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (getSupportFragmentManager().findFragmentById(R.id.fragment_container) instanceof FragmentRemindersList) {
            finish();
        } else {
            super.onBackPressed();
        }
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_custom_reminders);
        initToolbar();
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        loadData();
    }
}
