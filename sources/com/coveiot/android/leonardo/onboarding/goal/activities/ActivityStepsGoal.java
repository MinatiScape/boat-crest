package com.coveiot.android.leonardo.onboarding.goal.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.coveiot.android.boat.R;
import com.coveiot.android.dashboard2.util.Dashboard2Utils;
import com.coveiot.android.leonardo.onboarding.goal.fragments.FragmentStepsGoal;
import com.coveiot.android.theme.BaseActivity;
import java.util.LinkedHashMap;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityStepsGoal extends BaseActivity {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();

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

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        finish();
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_steps_goal);
        int i = R.id.toolbar;
        ((TextView) _$_findCachedViewById(i).findViewById(R.id.toolbar_title)).setText(getString(R.string.set_your_step_goal));
        ((TextView) _$_findCachedViewById(i).findViewById(R.id.toolbar_back_arrow)).setVisibility(8);
        q();
        Dashboard2Utils.Companion.scheduleJob(this);
    }

    public final void q() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, FragmentStepsGoal.Companion.newInstance()).commit();
    }
}
