package com.coveiot.android.leonardo.more.activities;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import androidx.fragment.app.FragmentManager;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.more.fragments.DFUWatchListFragment;
import com.coveiot.android.leonardo.more.fragments.WatchRecoveryInstructionsFragment;
import com.coveiot.android.theme.BaseActivity;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityFirmwareRestore extends BaseActivity implements WatchRecoveryInstructionsFragment.FragmentInteractionListener {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();

    public ActivityFirmwareRestore() {
        Intrinsics.checkNotNullExpressionValue(ActivityFirmwareRestore.class.getSimpleName(), "javaClass.simpleName");
        new Handler();
    }

    public static final void s(ActivityFirmwareRestore this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    public static final void v(ActivityFirmwareRestore this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.getSupportFragmentManager().findFragmentById(R.id.fragment_container) instanceof WatchRecoveryInstructionsFragment) {
            String string = this$0.getString(R.string.watch_recovery);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.watch_recovery)");
            this$0.initToolbar(string);
        } else if (this$0.getSupportFragmentManager().findFragmentById(R.id.fragment_container) instanceof DFUWatchListFragment) {
            String string2 = this$0.getString(R.string.devices_in_recovery_mode);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.devices_in_recovery_mode)");
            this$0.initToolbar(string2);
        }
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

    public final void initToolbar(String str) {
        ((TextView) findViewById(R.id.toolbar_title)).setText(str);
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.q9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityFirmwareRestore.s(ActivityFirmwareRestore.this, view);
            }
        });
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_firmware_restore);
        t();
        String string = getString(R.string.watch_recovery);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.watch_recovery)");
        initToolbar(string);
        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() { // from class: com.coveiot.android.leonardo.more.activities.r9
            @Override // androidx.fragment.app.FragmentManager.OnBackStackChangedListener
            public final void onBackStackChanged() {
                ActivityFirmwareRestore.v(ActivityFirmwareRestore.this);
            }
        });
    }

    @Override // com.coveiot.android.leonardo.more.fragments.WatchRecoveryInstructionsFragment.FragmentInteractionListener
    public void onNext() {
        u();
    }

    public final void t() {
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, WatchRecoveryInstructionsFragment.Companion.newInstance()).commit();
    }

    public final void u() {
        String string = getString(R.string.devices_in_recovery_mode);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.devices_in_recovery_mode)");
        initToolbar(string);
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, DFUWatchListFragment.Companion.newInstance()).addToBackStack("DFUWatchListFragment").commit();
    }
}
