package com.coveiot.android.respiratoryrate.activities;

import android.os.Bundle;
import android.view.View;
import com.coveiot.android.respiratoryrate.R;
import com.coveiot.android.respiratoryrate.databinding.ActivityRespiratoryRateHistoryBinding;
import com.coveiot.android.respiratoryrate.fragments.FragmentRespiratoryRateHistory;
import com.coveiot.android.theme.BaseActivity;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class ActivityRespiratoryRateHistory extends BaseActivity {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public ActivityRespiratoryRateHistoryBinding p;

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

    public final void initFragment() {
        new FragmentRespiratoryRateHistory();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentRespiratoryRateHistory()).commitAllowingStateLoss();
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        ActivityRespiratoryRateHistoryBinding inflate = ActivityRespiratoryRateHistoryBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
        this.p = inflate;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        setContentView(inflate.getRoot());
        initFragment();
    }
}
