package com.coveiot.android.leonardo.more.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.lifecycle.Observer;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.boat.R;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.BottomSheetDialogOneButtonOneTitle;
import com.coveiot.android.theme.BottomSheetDialogOneButtonTitleMessage;
import com.coveiot.android.theme.BottomSheetDialogTwoButtons;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityContactTracingSync extends BaseActivity {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @Nullable
    public BottomSheetDialogOneButtonTitleMessage p;
    @Nullable
    public BottomSheetDialogOneButtonTitleMessage q;
    @Nullable
    public BottomSheetDialogTwoButtons r;
    @Nullable
    public BottomSheetDialogOneButtonOneTitle s;

    public static final void A(ActivityContactTracingSync this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = this$0.r;
        Intrinsics.checkNotNull(bottomSheetDialogTwoButtons);
        bottomSheetDialogTwoButtons.dismiss();
    }

    public static final void B(ActivityContactTracingSync this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = this$0.p;
        Intrinsics.checkNotNull(bottomSheetDialogOneButtonTitleMessage);
        bottomSheetDialogOneButtonTitleMessage.dismiss();
        this$0.finish();
    }

    public static final void C(ActivityContactTracingSync this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = this$0.q;
        Intrinsics.checkNotNull(bottomSheetDialogOneButtonTitleMessage);
        bottomSheetDialogOneButtonTitleMessage.dismiss();
        this$0.finish();
    }

    public static final void E(ActivityContactTracingSync this$0, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((ProgressBar) this$0._$_findCachedViewById(R.id.progressbar)).setProgress(i);
        StringBuilder sb = new StringBuilder();
        sb.append(i);
        sb.append('%');
        ((TextView) this$0._$_findCachedViewById(R.id.progressValue)).setText(sb.toString());
    }

    public static final void w(ActivityContactTracingSync this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    public static final void z(ActivityContactTracingSync this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = this$0.r;
        Intrinsics.checkNotNull(bottomSheetDialogTwoButtons);
        bottomSheetDialogTwoButtons.dismiss();
        BleApiManager.getInstance(this$0).getBleApi().restartService();
        this$0.finish();
    }

    public final void D(final int i) {
        runOnUiThread(new Runnable() { // from class: com.coveiot.android.leonardo.more.activities.c5
            @Override // java.lang.Runnable
            public final void run() {
                ActivityContactTracingSync.E(ActivityContactTracingSync.this, i);
            }
        });
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
    public final BottomSheetDialogOneButtonOneTitle getBandDisconnectedDialog() {
        return this.s;
    }

    @Nullable
    public final BottomSheetDialogOneButtonTitleMessage getSyncCompleteDialog() {
        return this.p;
    }

    @Nullable
    public final BottomSheetDialogTwoButtons getSyncExitDialog() {
        return this.r;
    }

    @Nullable
    public final BottomSheetDialogOneButtonTitleMessage getSyncFailedDialog() {
        return this.q;
    }

    public final void initToolbar() {
        ((TextView) findViewById(R.id.toolbar_title)).setText(getString(R.string.manual_sync));
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.y4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityContactTracingSync.w(ActivityContactTracingSync.this, view);
            }
        });
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        y();
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_contact_tracting_sync);
        initToolbar();
        BleApiManager.getInstance(this).getBleApi().getDeviceSupportedFeatures().isBandSocialDistanceFeatureSupported();
        BleApiManager.getInstance(this).getBleApi().registerConnectionStatus().observe(this, new Observer<ConnectionStatus>() { // from class: com.coveiot.android.leonardo.more.activities.ActivityContactTracingSync$onCreate$1
            @Override // androidx.lifecycle.Observer
            public void onChanged(@Nullable ConnectionStatus connectionStatus) {
                if (connectionStatus == ConnectionStatus.DISCONNECTED) {
                    ActivityContactTracingSync.this.x();
                }
            }
        });
    }

    public final void setBandDisconnectedDialog(@Nullable BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle) {
        this.s = bottomSheetDialogOneButtonOneTitle;
    }

    public final void setSyncCompleteDialog(@Nullable BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage) {
        this.p = bottomSheetDialogOneButtonTitleMessage;
    }

    public final void setSyncExitDialog(@Nullable BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons) {
        this.r = bottomSheetDialogTwoButtons;
    }

    public final void setSyncFailedDialog(@Nullable BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage) {
        this.q = bottomSheetDialogOneButtonTitleMessage;
    }

    public final void syncComplete() {
        D(100);
        if (this.p == null) {
            String string = getString(R.string.successful_text);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.successful_text)");
            String string2 = getString(R.string.data_sync_complete);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.data_sync_complete)");
            BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = new BottomSheetDialogOneButtonTitleMessage(this, string, string2);
            this.p = bottomSheetDialogOneButtonTitleMessage;
            Intrinsics.checkNotNull(bottomSheetDialogOneButtonTitleMessage);
            String string3 = getString(R.string.ok);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.ok)");
            bottomSheetDialogOneButtonTitleMessage.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.z4
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityContactTracingSync.B(ActivityContactTracingSync.this, view);
                }
            });
            BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage2 = this.p;
            Intrinsics.checkNotNull(bottomSheetDialogOneButtonTitleMessage2);
            bottomSheetDialogOneButtonTitleMessage2.setCancelable(false);
        }
        BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage3 = this.p;
        Intrinsics.checkNotNull(bottomSheetDialogOneButtonTitleMessage3);
        if (bottomSheetDialogOneButtonTitleMessage3.isShowing()) {
            return;
        }
        BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage4 = this.p;
        Intrinsics.checkNotNull(bottomSheetDialogOneButtonTitleMessage4);
        bottomSheetDialogOneButtonTitleMessage4.show();
    }

    public final void syncFailed() {
        if (this.q == null) {
            String string = getString(R.string.sync_falied);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.sync_falied)");
            String string2 = getString(R.string.data_sync_failed);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.data_sync_failed)");
            BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = new BottomSheetDialogOneButtonTitleMessage(this, string, string2);
            this.q = bottomSheetDialogOneButtonTitleMessage;
            Intrinsics.checkNotNull(bottomSheetDialogOneButtonTitleMessage);
            String string3 = getString(R.string.ok);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.ok)");
            bottomSheetDialogOneButtonTitleMessage.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.b5
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityContactTracingSync.C(ActivityContactTracingSync.this, view);
                }
            });
            BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage2 = this.q;
            Intrinsics.checkNotNull(bottomSheetDialogOneButtonTitleMessage2);
            bottomSheetDialogOneButtonTitleMessage2.setCancelable(false);
        }
        BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage3 = this.q;
        Intrinsics.checkNotNull(bottomSheetDialogOneButtonTitleMessage3);
        if (bottomSheetDialogOneButtonTitleMessage3.isShowing()) {
            return;
        }
        BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage4 = this.q;
        Intrinsics.checkNotNull(bottomSheetDialogOneButtonTitleMessage4);
        bottomSheetDialogOneButtonTitleMessage4.show();
    }

    public final void x() {
        if (this.s == null) {
            String string = getString(R.string.band_disconnected);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.band_disconnected)");
            BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = new BottomSheetDialogOneButtonOneTitle(this, string);
            this.s = bottomSheetDialogOneButtonOneTitle;
            Intrinsics.checkNotNull(bottomSheetDialogOneButtonOneTitle);
            String string2 = getString(R.string.ok);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.ok)");
            bottomSheetDialogOneButtonOneTitle.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.ActivityContactTracingSync$showBandDisconnected$1
                @Override // android.view.View.OnClickListener
                public void onClick(@Nullable View view) {
                    BottomSheetDialogOneButtonOneTitle bandDisconnectedDialog = ActivityContactTracingSync.this.getBandDisconnectedDialog();
                    Intrinsics.checkNotNull(bandDisconnectedDialog);
                    bandDisconnectedDialog.dismiss();
                    ActivityContactTracingSync.this.finish();
                }
            });
        }
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle2 = this.s;
        Intrinsics.checkNotNull(bottomSheetDialogOneButtonOneTitle2);
        if (bottomSheetDialogOneButtonOneTitle2.isShowing()) {
            return;
        }
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle3 = this.s;
        Intrinsics.checkNotNull(bottomSheetDialogOneButtonOneTitle3);
        bottomSheetDialogOneButtonOneTitle3.show();
    }

    public final void y() {
        if (this.r == null) {
            String string = getString(R.string.alert);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.alert)");
            String string2 = getString(R.string.data_sync_inprogress_warning);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.data_sync_inprogress_warning)");
            BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = new BottomSheetDialogTwoButtons(this, string, string2, false, 8, null);
            this.r = bottomSheetDialogTwoButtons;
            Intrinsics.checkNotNull(bottomSheetDialogTwoButtons);
            String string3 = getString(R.string.yes);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.yes)");
            bottomSheetDialogTwoButtons.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.x4
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityContactTracingSync.z(ActivityContactTracingSync.this, view);
                }
            });
            BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons2 = this.r;
            Intrinsics.checkNotNull(bottomSheetDialogTwoButtons2);
            String string4 = getString(R.string.f4085no);
            Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.no)");
            bottomSheetDialogTwoButtons2.setNegativeButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.a5
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityContactTracingSync.A(ActivityContactTracingSync.this, view);
                }
            });
        }
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons3 = this.r;
        Intrinsics.checkNotNull(bottomSheetDialogTwoButtons3);
        if (bottomSheetDialogTwoButtons3.isShowing()) {
            return;
        }
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons4 = this.r;
        Intrinsics.checkNotNull(bottomSheetDialogTwoButtons4);
        bottomSheetDialogTwoButtons4.show();
    }
}
