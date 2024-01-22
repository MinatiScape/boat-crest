package com.coveiot.android.leonardo.dashboard.health.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProviders;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.dashboard.health.fragments.FragmentAmbientSoundLevelShare;
import com.coveiot.android.leonardo.dashboard.health.fragments.FragmentBloodPressureShare;
import com.coveiot.android.leonardo.dashboard.health.fragments.FragmentECGShare;
import com.coveiot.android.leonardo.dashboard.health.fragments.FragmentFitnessShare;
import com.coveiot.android.leonardo.dashboard.health.fragments.FragmentHeartRateShare;
import com.coveiot.android.leonardo.dashboard.health.fragments.FragmentHrvShare;
import com.coveiot.android.leonardo.dashboard.health.fragments.FragmentPeriodicSpO2Share;
import com.coveiot.android.leonardo.dashboard.health.fragments.FragmentSleepShare;
import com.coveiot.android.leonardo.dashboard.health.fragments.FragmentSpO2Share;
import com.coveiot.android.leonardo.dashboard.health.fragments.FragmentStressShare;
import com.coveiot.android.leonardo.dashboard.health.fragments.FragmentTemperatureShare;
import com.coveiot.android.leonardo.dashboard.health.model.ShareData;
import com.coveiot.android.leonardo.dashboard.health.viewmodel.ActivityShareScreenViewModel;
import com.coveiot.android.leonardo.dashboard.home.fragments.FragmentFitnessDataShare;
import com.coveiot.android.leonardo.dashboard.socialshare.fragments.FragmentSocialShareCard;
import com.coveiot.android.leonardo.listener.PermissionListener;
import com.coveiot.android.leonardo.p000enum.PermissionType;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.leonardo.utils.ViewModelFactory;
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
import kotlin.text.m;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class ActivityShareScreen extends BaseActivity implements PermissionListener {
    public Bitmap bitmap;
    public ShareData data;
    public ActivityShareScreenViewModel q;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public final int p = 125;

    /* loaded from: classes3.dex */
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

    public static final void A(Ref.ObjectRef dialog, ActivityShareScreen this$0, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((GenericMessageDialog) dialog.element).dismiss();
        ActivityCompat.requestPermissions(this$0, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, this$0.p);
    }

    public static final void B(GenericMessageDialog dialog, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        dialog.dismiss();
    }

    public static final void C(GenericMessageDialog dialog, ActivityShareScreen this$0, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        dialog.dismiss();
        Intent intent = new Intent();
        intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.fromParts("package", this$0.getPackageName(), null));
        this$0.startActivityForResult(intent, this$0.p);
    }

    public static final void D(ActivityShareScreen this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismissProgress();
        Bitmap saveScreen = ShareScreen.saveScreen((RelativeLayout) this$0._$_findCachedViewById(R.id.container_layout), this$0);
        Intrinsics.checkNotNullExpressionValue(saveScreen, "saveScreen(container_lay…this@ActivityShareScreen)");
        this$0.setBitmap(saveScreen);
        ShareScreen.shareScreenCommom(this$0.getBitmap(), this$0);
    }

    public static final void x(ActivityShareScreen this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    public static final void y(ActivityShareScreen this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ActivityShareScreenViewModel activityShareScreenViewModel = this$0.q;
        if (activityShareScreenViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            activityShareScreenViewModel = null;
        }
        activityShareScreenViewModel.checkStoragePermission();
    }

    public static final void z(Ref.ObjectRef dialog, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        ((GenericMessageDialog) dialog.element).dismiss();
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
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, this.p);
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

    @NotNull
    public final Bitmap getBitmap() {
        Bitmap bitmap = this.bitmap;
        if (bitmap != null) {
            return bitmap;
        }
        Intrinsics.throwUninitializedPropertyAccessException("bitmap");
        return null;
    }

    @NotNull
    public final ShareData getData() {
        ShareData shareData = this.data;
        if (shareData != null) {
            return shareData;
        }
        Intrinsics.throwUninitializedPropertyAccessException("data");
        return null;
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == this.p) {
            ActivityShareScreenViewModel activityShareScreenViewModel = this.q;
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
        setContentView(R.layout.activity_share_screen);
        ActivityShareScreenViewModel activityShareScreenViewModel = (ActivityShareScreenViewModel) ViewModelProviders.of(this, new ViewModelFactory(this)).get(ActivityShareScreenViewModel.class);
        this.q = activityShareScreenViewModel;
        if (activityShareScreenViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            activityShareScreenViewModel = null;
        }
        activityShareScreenViewModel.setMListener(this);
        String value = AppConstants.EMPTY_STRING.getValue();
        if (getIntent().getExtras() != null) {
            value = getIntent().getStringExtra(AppConstants.SHARE_SCREEN_TYPE.getValue());
            Intrinsics.checkNotNull(value);
            Serializable serializableExtra = getIntent().getSerializableExtra(AppConstants.SHARE_DATA.getValue());
            Intrinsics.checkNotNull(serializableExtra, "null cannot be cast to non-null type com.coveiot.android.leonardo.dashboard.health.model.ShareData");
            setData((ShareData) serializableExtra);
        }
        if (!TextUtils.isEmpty(value)) {
            if (m.equals(value, getResources().getString(R.string.sleep), true)) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container_layout, FragmentSleepShare.Companion.newInstance(getData())).commitAllowingStateLoss();
            } else if (m.equals(value, getResources().getString(R.string.stress), true)) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container_layout, FragmentStressShare.Companion.newInstance(getData())).commitAllowingStateLoss();
            } else if (m.equals(value, getResources().getString(R.string.hrv_caps), true)) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container_layout, FragmentHrvShare.Companion.newInstance(getData())).commitAllowingStateLoss();
            } else if (m.equals(value, getResources().getString(R.string.blood_pressure), true)) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container_layout, FragmentBloodPressureShare.Companion.newInstance(getData())).commitAllowingStateLoss();
            } else if (m.equals(value, getResources().getString(R.string.heart_rate), true)) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container_layout, FragmentHeartRateShare.Companion.newInstance(getData())).commitAllowingStateLoss();
            } else if (m.equals(value, getResources().getString(R.string.fitness), true)) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container_layout, FragmentFitnessShare.Companion.newInstance(getData())).commitAllowingStateLoss();
            } else if (m.equals(value, getResources().getString(R.string.ecg), true)) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container_layout, FragmentECGShare.Companion.newInstance(getData())).commitAllowingStateLoss();
            } else if (m.equals(value, getResources().getString(R.string.temperature), true)) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container_layout, FragmentTemperatureShare.Companion.newInstance(getData())).commitAllowingStateLoss();
            } else if (m.equals(value, getResources().getString(R.string.spo2), true)) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container_layout, FragmentSpO2Share.Companion.newInstance(getData())).commitAllowingStateLoss();
            } else if (m.equals(value, getResources().getString(R.string.periodic_spo2), true)) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container_layout, FragmentPeriodicSpO2Share.Companion.newInstance(getData())).commitAllowingStateLoss();
            } else if (m.equals(value, getResources().getString(R.string.ambient_sound_level_txt), true)) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container_layout, FragmentAmbientSoundLevelShare.Companion.newInstance(getData())).commitAllowingStateLoss();
            } else if (m.equals(value, getResources().getString(R.string.social_share_card), true)) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container_layout, FragmentSocialShareCard.Companion.newInstance()).commitAllowingStateLoss();
            } else if (m.equals(value, getResources().getString(R.string.fitness_tab), true)) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container_layout, FragmentFitnessDataShare.Companion.newInstance(getData())).commitAllowingStateLoss();
            }
        } else {
            finish();
        }
        ((LinearLayout) _$_findCachedViewById(R.id.share_close_layout)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.health.activities.d
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityShareScreen.x(ActivityShareScreen.this, view);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.share_button)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.health.activities.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityShareScreen.y(ActivityShareScreen.this, view);
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
            ((TextView) ((GenericMessageDialog) genericMessageDialog).findViewById(R.id.cancel)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.health.activities.g
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityShareScreen.z(Ref.ObjectRef.this, view);
                }
            });
            ((TextView) ((GenericMessageDialog) objectRef.element).findViewById(R.id.proceed)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.health.activities.h
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityShareScreen.A(Ref.ObjectRef.this, this, view);
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
            ((TextView) genericMessageDialog.findViewById(R.id.cancel)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.health.activities.e
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityShareScreen.B(GenericMessageDialog.this, view);
                }
            });
            ((TextView) genericMessageDialog.findViewById(R.id.proceed)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.health.activities.f
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityShareScreen.C(GenericMessageDialog.this, this, view);
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
            handler.postDelayed(new Runnable() { // from class: com.coveiot.android.leonardo.dashboard.health.activities.i
                @Override // java.lang.Runnable
                public final void run() {
                    ActivityShareScreen.D(ActivityShareScreen.this);
                }
            }, 500L);
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i, @NotNull String[] permissions, @NotNull int[] grantResults) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        super.onRequestPermissionsResult(i, permissions, grantResults);
        if (i == this.p) {
            dismissProgress();
            if (grantResults[0] == 0) {
                Bitmap saveScreen = ShareScreen.saveScreen((RelativeLayout) _$_findCachedViewById(R.id.container_layout), this);
                Intrinsics.checkNotNullExpressionValue(saveScreen, "saveScreen(container_lay…this@ActivityShareScreen)");
                setBitmap(saveScreen);
                ShareScreen.shareScreenCommom(getBitmap(), this);
            }
        }
    }

    public final void setBitmap(@NotNull Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(bitmap, "<set-?>");
        this.bitmap = bitmap;
    }

    public final void setData(@NotNull ShareData shareData) {
        Intrinsics.checkNotNullParameter(shareData, "<set-?>");
        this.data = shareData;
    }
}
