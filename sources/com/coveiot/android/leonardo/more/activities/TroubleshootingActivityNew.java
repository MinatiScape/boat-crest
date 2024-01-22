package com.coveiot.android.leonardo.more.activities;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import com.coveiot.android.boat.databinding.ActivityTroubleshootingNewBinding;
import com.coveiot.android.leonardo.more.fragments.TroubleshootingFTUFragment;
import com.coveiot.android.leonardo.more.fragments.TroubleshootingFragment;
import com.coveiot.covepreferences.SessionManager;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class TroubleshootingActivityNew extends AppCompatActivity {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public ActivityTroubleshootingNewBinding h;

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

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

    public final void c() {
        TroubleshootingFTUFragment troubleshootingFTUFragment = new TroubleshootingFTUFragment();
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        ActivityTroubleshootingNewBinding activityTroubleshootingNewBinding = this.h;
        if (activityTroubleshootingNewBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityTroubleshootingNewBinding = null;
        }
        beginTransaction.replace(activityTroubleshootingNewBinding.tfragmentContainer.getId(), troubleshootingFTUFragment).commitAllowingStateLoss();
    }

    public final void loadTroubleshootingFragment() {
        TroubleshootingFragment troubleshootingFragment = new TroubleshootingFragment();
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        ActivityTroubleshootingNewBinding activityTroubleshootingNewBinding = this.h;
        if (activityTroubleshootingNewBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityTroubleshootingNewBinding = null;
        }
        beginTransaction.replace(activityTroubleshootingNewBinding.tfragmentContainer.getId(), troubleshootingFragment).commitAllowingStateLoss();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        ActivityTroubleshootingNewBinding inflate = ActivityTroubleshootingNewBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
        this.h = inflate;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        setContentView(inflate.getRoot());
        if (!SessionManager.getInstance(this).isTroubleshootFtuShown()) {
            c();
        } else {
            loadTroubleshootingFragment();
        }
    }
}
