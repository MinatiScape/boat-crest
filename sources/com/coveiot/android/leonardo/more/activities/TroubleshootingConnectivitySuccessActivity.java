package com.coveiot.android.leonardo.more.activities;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.coveiot.android.boat.R;
import com.coveiot.android.boat.databinding.ActivityTroubleshootingConnectivitySuccessBinding;
import com.coveiot.android.devicemodels.DeviceUtils;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class TroubleshootingConnectivitySuccessActivity extends AppCompatActivity {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public ActivityTroubleshootingConnectivitySuccessBinding binding;

    public static final void f(TroubleshootingConnectivitySuccessActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    public static final void g(TroubleshootingConnectivitySuccessActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getBinding().setIsShowingInProgress(Boolean.FALSE);
    }

    public static final void h(TroubleshootingConnectivitySuccessActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

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

    @NotNull
    public final ActivityTroubleshootingConnectivitySuccessBinding getBinding() {
        ActivityTroubleshootingConnectivitySuccessBinding activityTroubleshootingConnectivitySuccessBinding = this.binding;
        if (activityTroubleshootingConnectivitySuccessBinding != null) {
            return activityTroubleshootingConnectivitySuccessBinding;
        }
        Intrinsics.throwUninitializedPropertyAccessException("binding");
        return null;
    }

    public final void initToolbar() {
        ((TextView) findViewById(R.id.toolbar_title)).setText(getString(R.string.troubleshooting));
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.xk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TroubleshootingConnectivitySuccessActivity.f(TroubleshootingConnectivitySuccessActivity.this, view);
            }
        });
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        ActivityTroubleshootingConnectivitySuccessBinding inflate = ActivityTroubleshootingConnectivitySuccessBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
        setBinding(inflate);
        setContentView(getBinding().getRoot());
        getBinding().setIsShowingInProgress(Boolean.TRUE);
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String string = getString(R.string.your_troubleshooting_is_in_progress_for);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.your_…oting_is_in_progress_for)");
        String format = String.format(string, Arrays.copyOf(new Object[]{DeviceUtils.Companion.getWatchName(this)}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        ((TextView) _$_findCachedViewById(R.id.tv_your_troubleshoot_in_progress)).setText(format);
        initToolbar();
        new Handler().postDelayed(new Runnable() { // from class: com.coveiot.android.leonardo.more.activities.yk
            @Override // java.lang.Runnable
            public final void run() {
                TroubleshootingConnectivitySuccessActivity.g(TroubleshootingConnectivitySuccessActivity.this);
            }
        }, 3000L);
        ((Button) _$_findCachedViewById(R.id.btn_ok)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.wk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TroubleshootingConnectivitySuccessActivity.h(TroubleshootingConnectivitySuccessActivity.this, view);
            }
        });
    }

    public final void setBinding(@NotNull ActivityTroubleshootingConnectivitySuccessBinding activityTroubleshootingConnectivitySuccessBinding) {
        Intrinsics.checkNotNullParameter(activityTroubleshootingConnectivitySuccessBinding, "<set-?>");
        this.binding = activityTroubleshootingConnectivitySuccessBinding;
    }
}
