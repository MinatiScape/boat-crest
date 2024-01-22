package com.coveiot.android.leonardo.sensai.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.boat.R;
import com.coveiot.android.boat.databinding.ActivitySensaiShareScreenBinding;
import com.coveiot.android.leonardo.dashboard.health.viewmodel.ActivityShareScreenViewModel;
import com.coveiot.android.leonardo.listener.PermissionListener;
import com.coveiot.android.leonardo.p000enum.PermissionType;
import com.coveiot.android.leonardo.sensai.fragment.FragmentSensAIShareData;
import com.coveiot.android.leonardo.sensai.model.SummaryShareData;
import com.coveiot.android.leonardo.utils.ViewModelFactory;
import com.coveiot.android.leonardo.utils.ViewUtilsKt;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.android.theme.GenericMessageDialog;
import com.coveiot.utils.permissions.PermissionUtils;
import com.coveiot.utils.utility.ShareScreen;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivitySensAIShareScreen extends BaseActivity implements PermissionListener {
    public ActivitySensaiShareScreenBinding p;
    @Nullable
    public Bitmap q;
    @Nullable
    public SummaryShareData r;
    public ActivityShareScreenViewModel t;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public final int s = 115;

    /* loaded from: classes5.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[PermissionType.values().length];
            try {
                iArr[PermissionType.STORAGE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final void A(Ref.ObjectRef dialog, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        ((GenericMessageDialog) dialog.element).dismiss();
    }

    public static final void B(Ref.ObjectRef dialog, ActivitySensAIShareScreen this$0, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((GenericMessageDialog) dialog.element).dismiss();
        ActivityCompat.requestPermissions(this$0, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, this$0.s);
    }

    public static final void C(GenericMessageDialog dialog, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        dialog.dismiss();
    }

    public static final void D(GenericMessageDialog dialog, ActivitySensAIShareScreen this$0, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        dialog.dismiss();
        Intent intent = new Intent();
        intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.fromParts("package", this$0.getPackageName(), null));
        this$0.startActivityForResult(intent, this$0.s);
    }

    public static final void E(ActivitySensAIShareScreen this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismissProgress();
        Fragment findFragmentById = this$0.getSupportFragmentManager().findFragmentById(R.id.container_layout);
        Intrinsics.checkNotNull(findFragmentById, "null cannot be cast to non-null type com.coveiot.android.leonardo.sensai.fragment.FragmentSensAIShareData");
        Bitmap saveScreen = ((FragmentSensAIShareData) findFragmentById).saveScreen();
        this$0.q = saveScreen;
        if (saveScreen != null) {
            ShareScreen.shareScreenCommom(saveScreen, this$0);
            return;
        }
        String string = this$0.getString(R.string.unable_to_share_sensai);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.unable_to_share_sensai)");
        ViewUtilsKt.toast(this$0, string);
    }

    public static final void y(ActivitySensAIShareScreen this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    public static final void z(ActivitySensAIShareScreen this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ActivityShareScreenViewModel activityShareScreenViewModel = this$0.t;
        if (activityShareScreenViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            activityShareScreenViewModel = null;
        }
        activityShareScreenViewModel.checkStoragePermission();
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

    @Override // com.coveiot.android.leonardo.listener.PermissionListener
    public void askPermission(@NotNull PermissionType permission) {
        Intrinsics.checkNotNullParameter(permission, "permission");
        if (WhenMappings.$EnumSwitchMapping$0[permission.ordinal()] == 1) {
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, this.s);
        }
    }

    @Override // com.coveiot.android.leonardo.listener.PermissionListener
    public void checkPermssion(@NotNull String permission, @NotNull PermissionUtils.PermissionAskListener permissionListener) {
        Intrinsics.checkNotNullParameter(permission, "permission");
        Intrinsics.checkNotNullParameter(permissionListener, "permissionListener");
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
        analyticsLog.setScreenName(FirebaseEventParams.ScreenName.GRANT_PERMISSION_DIALOG.getValue());
        analyticsLog.setAppPermissionId("Manifest.permission.READ_EXTERNAL_STORAGE");
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        PermissionUtils.checkPermission(this, permission, permissionListener);
    }

    @Nullable
    public final Bitmap getBitmap() {
        return this.q;
    }

    @Nullable
    public final SummaryShareData getDataActivity() {
        return this.r;
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == this.s) {
            ActivityShareScreenViewModel activityShareScreenViewModel = this.t;
            if (activityShareScreenViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                activityShareScreenViewModel = null;
            }
            activityShareScreenViewModel.checkStoragePermission();
        }
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        ActivitySensaiShareScreenBinding inflate = ActivitySensaiShareScreenBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
        this.p = inflate;
        ActivitySensaiShareScreenBinding activitySensaiShareScreenBinding = null;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        setContentView(inflate.getRoot());
        ActivityShareScreenViewModel activityShareScreenViewModel = (ActivityShareScreenViewModel) ViewModelProviders.of(this, new ViewModelFactory(this)).get(ActivityShareScreenViewModel.class);
        this.t = activityShareScreenViewModel;
        if (activityShareScreenViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            activityShareScreenViewModel = null;
        }
        activityShareScreenViewModel.setMListener(this);
        if (getIntent().getExtras() != null && getIntent().getSerializableExtra("share_data") != null && (getIntent().getSerializableExtra("share_data") instanceof SummaryShareData)) {
            Serializable serializableExtra = getIntent().getSerializableExtra("share_data");
            Intrinsics.checkNotNull(serializableExtra, "null cannot be cast to non-null type com.coveiot.android.leonardo.sensai.model.SummaryShareData");
            this.r = (SummaryShareData) serializableExtra;
        }
        if (this.r == null) {
            finish();
        } else {
            x();
        }
        ActivitySensaiShareScreenBinding activitySensaiShareScreenBinding2 = this.p;
        if (activitySensaiShareScreenBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySensaiShareScreenBinding2 = null;
        }
        activitySensaiShareScreenBinding2.shareCloseLayout.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.sensai.activity.r
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivitySensAIShareScreen.y(ActivitySensAIShareScreen.this, view);
            }
        });
        ActivitySensaiShareScreenBinding activitySensaiShareScreenBinding3 = this.p;
        if (activitySensaiShareScreenBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activitySensaiShareScreenBinding = activitySensaiShareScreenBinding3;
        }
        activitySensaiShareScreenBinding.shareButton.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.sensai.activity.s
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivitySensAIShareScreen.z(ActivitySensAIShareScreen.this, view);
            }
        });
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [com.coveiot.android.theme.GenericMessageDialog, T] */
    @Override // com.coveiot.android.leonardo.listener.PermissionListener
    public void onPermissionDenied(@NotNull PermissionType permission) {
        Intrinsics.checkNotNullParameter(permission, "permission");
        if (WhenMappings.$EnumSwitchMapping$0[permission.ordinal()] == 1) {
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            String string = getString(R.string.storage_permission);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.storage_permission)");
            String string2 = getString(R.string.storage_share_permission_required);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.stora…hare_permission_required)");
            ?? genericMessageDialog = new GenericMessageDialog(this, string, string2);
            objectRef.element = genericMessageDialog;
            ((TextView) ((GenericMessageDialog) genericMessageDialog).findViewById(R.id.cancel)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.sensai.activity.v
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivitySensAIShareScreen.A(Ref.ObjectRef.this, view);
                }
            });
            ((TextView) ((GenericMessageDialog) objectRef.element).findViewById(R.id.proceed)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.sensai.activity.w
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivitySensAIShareScreen.B(Ref.ObjectRef.this, this, view);
                }
            });
            if (isFinishing()) {
                return;
            }
            ((GenericMessageDialog) objectRef.element).show();
        }
    }

    @Override // com.coveiot.android.leonardo.listener.PermissionListener
    public void onPermissionDisabled(@NotNull PermissionType permission) {
        Intrinsics.checkNotNullParameter(permission, "permission");
        if (WhenMappings.$EnumSwitchMapping$0[permission.ordinal()] == 1) {
            String string = getString(R.string.storage_permission);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.storage_permission)");
            String string2 = getString(R.string.storage_share_permission_required);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.stora…hare_permission_required)");
            final GenericMessageDialog genericMessageDialog = new GenericMessageDialog(this, string, string2);
            ((TextView) genericMessageDialog.findViewById(R.id.cancel)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.sensai.activity.t
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivitySensAIShareScreen.C(GenericMessageDialog.this, view);
                }
            });
            ((TextView) genericMessageDialog.findViewById(R.id.proceed)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.sensai.activity.u
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivitySensAIShareScreen.D(GenericMessageDialog.this, this, view);
                }
            });
            if (isFinishing()) {
                return;
            }
            genericMessageDialog.show();
        }
    }

    @Override // com.coveiot.android.leonardo.listener.PermissionListener
    public void onPermissionSuccess(@NotNull PermissionType permission) {
        Intrinsics.checkNotNullParameter(permission, "permission");
        if (WhenMappings.$EnumSwitchMapping$0[permission.ordinal()] == 1) {
            Handler handler = new Handler();
            showProgress();
            handler.postDelayed(new Runnable() { // from class: com.coveiot.android.leonardo.sensai.activity.x
                @Override // java.lang.Runnable
                public final void run() {
                    ActivitySensAIShareScreen.E(ActivitySensAIShareScreen.this);
                }
            }, 500L);
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i, @NotNull String[] permissions, @NotNull int[] grantResults) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        super.onRequestPermissionsResult(i, permissions, grantResults);
        if (i == this.s) {
            dismissProgress();
            if (grantResults[0] == 0) {
                Bitmap saveScreen = ShareScreen.saveScreen((RelativeLayout) _$_findCachedViewById(R.id.container_layout), this);
                this.q = saveScreen;
                ShareScreen.shareScreenCommom(saveScreen, this);
            }
        }
    }

    public final void setBitmap(@Nullable Bitmap bitmap) {
        this.q = bitmap;
    }

    public final void setDataActivity(@Nullable SummaryShareData summaryShareData) {
        this.r = summaryShareData;
    }

    public final void x() {
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        SummaryShareData summaryShareData = this.r;
        FragmentSensAIShareData newInstance = summaryShareData != null ? FragmentSensAIShareData.Companion.newInstance(summaryShareData) : null;
        Intrinsics.checkNotNull(newInstance);
        beginTransaction.replace(R.id.container_layout, newInstance).commitAllowingStateLoss();
    }
}
