package com.coveiot.android.leonardo.more.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.coveiot.android.bleabstract.Constants;
import com.coveiot.android.boat.R;
import com.coveiot.android.boat.databinding.ActivityWatchDiagnosticsBinding;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.BottomSheetDialogImageTitleMessage;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.sdk.ble.api.response.SendDiagnosticsRes;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class WatchDiagnosticsActivity extends BaseActivity {
    public ActivityWatchDiagnosticsBinding p;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public final DiagnosticsStatusEventReceiver q = new DiagnosticsStatusEventReceiver();
    @NotNull
    public MutableLiveData<Boolean> r = new MutableLiveData<>();

    /* loaded from: classes5.dex */
    public final class DiagnosticsStatusEventReceiver extends BroadcastReceiver {
        public DiagnosticsStatusEventReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(@NotNull Context context, @NotNull Intent intent) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(intent, "intent");
            if (intent.hasExtra(Constants.SEND_DIAGNOSTICS_BROADCAST_INTENT_EXTRA)) {
                Bundle extras = intent.getExtras();
                Intrinsics.checkNotNull(extras);
                Serializable serializable = extras.getSerializable(Constants.SEND_DIAGNOSTICS_BROADCAST_INTENT_EXTRA);
                Intrinsics.checkNotNull(serializable, "null cannot be cast to non-null type com.coveiot.sdk.ble.api.response.SendDiagnosticsRes");
                if (((SendDiagnosticsRes) serializable).getStatus() == 0) {
                    WatchDiagnosticsActivity.this.getDiagnosticStatus().postValue(Boolean.TRUE);
                }
            }
        }
    }

    public static final void s(WatchDiagnosticsActivity this$0, Boolean it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(it, "it");
        if (it.booleanValue()) {
            this$0.t();
        }
    }

    public static final void u(BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage, WatchDiagnosticsActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogImageTitleMessage, "$bottomSheetDialogImageTitleMessage");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        bottomSheetDialogImageTitleMessage.dismiss();
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

    @NotNull
    public final MutableLiveData<Boolean> getDiagnosticStatus() {
        return this.r;
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        ActivityWatchDiagnosticsBinding inflate = ActivityWatchDiagnosticsBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
        this.p = inflate;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        setContentView(inflate.getRoot());
        LocalBroadcastManager.getInstance(this).registerReceiver(new DiagnosticsStatusEventReceiver(), new IntentFilter(Constants.SEND_DIAGNOSTICS_BROADCAST_INTENT_EXTRA));
        this.r.observe(this, new Observer() { // from class: com.coveiot.android.leonardo.more.activities.al
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                WatchDiagnosticsActivity.s(WatchDiagnosticsActivity.this, (Boolean) obj);
            }
        });
        SessionManager.getInstance(this).saveIsDiagnosticTestSelected(Boolean.valueOf(getIntent().getBooleanExtra(AppConstants.IS_RUN_DIAGNOSTIC_FRAGMENT.getValue(), true)));
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.q);
        super.onDestroy();
    }

    public final void setDiagnosticStatus(@NotNull MutableLiveData<Boolean> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.r = mutableLiveData;
    }

    public final void t() {
        Drawable drawable = getResources().getDrawable(R.drawable.info_icon_new);
        Intrinsics.checkNotNullExpressionValue(drawable, "resources.getDrawable(co…R.drawable.info_icon_new)");
        String string = getString(R.string.information);
        Intrinsics.checkNotNullExpressionValue(string, "getString(com.coveiot.an…oat.R.string.information)");
        String string2 = getString(R.string.diagnostics_exit);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(com.coveiot.an….string.diagnostics_exit)");
        final BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage = new BottomSheetDialogImageTitleMessage(this, drawable, string, string2, false);
        String string3 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(com.coveiot.leaderboard.R.string.ok)");
        bottomSheetDialogImageTitleMessage.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.zk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WatchDiagnosticsActivity.u(BottomSheetDialogImageTitleMessage.this, this, view);
            }
        });
        bottomSheetDialogImageTitleMessage.show();
    }
}
