package com.coveiot.android.leonardo.more.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.bt3call.viewmodel.BT3CallViewModel;
import com.coveiot.android.leonardo.more.fragments.FragmentWatchStatus;
import com.coveiot.android.theme.BaseActivity;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityWatchStatus extends BaseActivity {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public BT3CallViewModel bT3CallViewModel;

    public static final void r(ActivityWatchStatus this$0, View view) {
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

    @NotNull
    public final BT3CallViewModel getBT3CallViewModel() {
        BT3CallViewModel bT3CallViewModel = this.bT3CallViewModel;
        if (bT3CallViewModel != null) {
            return bT3CallViewModel;
        }
        Intrinsics.throwUninitializedPropertyAccessException("bT3CallViewModel");
        return null;
    }

    public final void initToolbar() {
        ((TextView) findViewById(R.id.toolbar_title)).setText(getString(R.string.watch_status));
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.pj
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityWatchStatus.r(ActivityWatchStatus.this, view);
            }
        });
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_watch_status);
        initToolbar();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, FragmentWatchStatus.Companion.newInstance$default(FragmentWatchStatus.Companion, false, 1, null)).commit();
    }

    public final void setBT3CallViewModel(@NotNull BT3CallViewModel bT3CallViewModel) {
        Intrinsics.checkNotNullParameter(bT3CallViewModel, "<set-?>");
        this.bT3CallViewModel = bT3CallViewModel;
    }
}
