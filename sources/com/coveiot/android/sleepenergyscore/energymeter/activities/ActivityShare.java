package com.coveiot.android.sleepenergyscore.energymeter.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import com.coveiot.android.sleepenergyscore.R;
import com.coveiot.android.sleepenergyscore.energymeter.fragments.FragmentEnergyMeterShare;
import com.coveiot.android.sleepenergyscore.energymeter.model.ShareEnergyMeterData;
import com.coveiot.android.sleepenergyscore.energymeter.viewmodel.ActivityShareViewModel;
import com.coveiot.android.sleepenergyscore.utils.Constants;
import com.coveiot.android.sleepenergyscore.utils.ViewModelFactory;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.GenericMessageDialog;
import com.coveiot.android.theme.permissions.PermissionListener;
import com.coveiot.android.theme.permissions.PermissionType;
import com.coveiot.utils.permissions.PermissionUtils;
import com.coveiot.utils.utility.ShareScreen;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class ActivityShare extends BaseActivity implements PermissionListener {
    public Bitmap bitmap;
    public ShareEnergyMeterData data;
    public ActivityShareViewModel q;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public final int p = 125;

    /* loaded from: classes6.dex */
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

    public static final void A(Ref.ObjectRef dialog, ActivityShare this$0, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((GenericMessageDialog) dialog.element).dismiss();
        ActivityCompat.requestPermissions(this$0, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, this$0.p);
    }

    public static final void B(GenericMessageDialog dialog, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        dialog.dismiss();
    }

    public static final void C(GenericMessageDialog dialog, ActivityShare this$0, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        dialog.dismiss();
        Intent intent = new Intent();
        intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.fromParts("package", this$0.getPackageName(), null));
        this$0.startActivityForResult(intent, this$0.p);
    }

    public static final void D(ActivityShare this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismissProgress();
        Bitmap saveScreen = ShareScreen.saveScreen((RelativeLayout) this$0._$_findCachedViewById(R.id.container_layout), this$0);
        Intrinsics.checkNotNullExpressionValue(saveScreen, "saveScreen(container_layout, this@ActivityShare)");
        this$0.setBitmap(saveScreen);
        ShareScreen.shareScreenCommom(this$0.getBitmap(), this$0);
    }

    public static final void x(ActivityShare this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    public static final void y(ActivityShare this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ActivityShareViewModel activityShareViewModel = this$0.q;
        if (activityShareViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            activityShareViewModel = null;
        }
        activityShareViewModel.checkStoragePermission();
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

    @Override // com.coveiot.android.theme.permissions.PermissionListener
    public void askPermission(@NotNull PermissionType permission) {
        Intrinsics.checkNotNullParameter(permission, "permission");
        if (WhenMappings.$EnumSwitchMapping$0[permission.ordinal()] == 1) {
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, this.p);
        }
    }

    @Override // com.coveiot.android.theme.permissions.PermissionListener
    public void checkPermssion(@NotNull String permission, @NotNull PermissionUtils.PermissionAskListener permissionListener) {
        Intrinsics.checkNotNullParameter(permission, "permission");
        Intrinsics.checkNotNullParameter(permissionListener, "permissionListener");
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
    public final ShareEnergyMeterData getData() {
        ShareEnergyMeterData shareEnergyMeterData = this.data;
        if (shareEnergyMeterData != null) {
            return shareEnergyMeterData;
        }
        Intrinsics.throwUninitializedPropertyAccessException("data");
        return null;
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == this.p) {
            ActivityShareViewModel activityShareViewModel = this.q;
            if (activityShareViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                activityShareViewModel = null;
            }
            activityShareViewModel.checkStoragePermission();
        }
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_share);
        ViewModel viewModel = ViewModelProviders.of(this, new ViewModelFactory(this)).get(ActivityShareViewModel.class);
        Intrinsics.checkNotNullExpressionValue(viewModel, "of(\n            this,\n  …areViewModel::class.java)");
        ActivityShareViewModel activityShareViewModel = (ActivityShareViewModel) viewModel;
        this.q = activityShareViewModel;
        if (activityShareViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            activityShareViewModel = null;
        }
        activityShareViewModel.setMListener(this);
        if (getIntent().getExtras() != null) {
            Serializable serializableExtra = getIntent().getSerializableExtra(Constants.SHARE_DATA.getValue());
            Intrinsics.checkNotNull(serializableExtra, "null cannot be cast to non-null type com.coveiot.android.sleepenergyscore.energymeter.model.ShareEnergyMeterData");
            setData((ShareEnergyMeterData) serializableExtra);
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.container_layout, FragmentEnergyMeterShare.Companion.newInstance(getData())).commitAllowingStateLoss();
        ((LinearLayout) _$_findCachedViewById(R.id.share_close_layout)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sleepenergyscore.energymeter.activities.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityShare.x(ActivityShare.this, view);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.share_button)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sleepenergyscore.energymeter.activities.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityShare.y(ActivityShare.this, view);
            }
        });
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [com.coveiot.android.theme.GenericMessageDialog, T] */
    @Override // com.coveiot.android.theme.permissions.PermissionListener
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
            ((TextView) ((GenericMessageDialog) genericMessageDialog).findViewById(R.id.cancel)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sleepenergyscore.energymeter.activities.e
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityShare.z(Ref.ObjectRef.this, view);
                }
            });
            ((TextView) ((GenericMessageDialog) objectRef.element).findViewById(R.id.proceed)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sleepenergyscore.energymeter.activities.f
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityShare.A(Ref.ObjectRef.this, this, view);
                }
            });
            if (isFinishing()) {
                return;
            }
            ((GenericMessageDialog) objectRef.element).show();
        }
    }

    @Override // com.coveiot.android.theme.permissions.PermissionListener
    public void onPermissionDisabled(@NotNull PermissionType permission) {
        Intrinsics.checkNotNullParameter(permission, "permission");
        if (WhenMappings.$EnumSwitchMapping$0[permission.ordinal()] == 1) {
            String string = getString(R.string.storage_permission);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.storage_permission)");
            String string2 = getString(R.string.storage_share_permission_required);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.stora…hare_permission_required)");
            final GenericMessageDialog genericMessageDialog = new GenericMessageDialog(this, string, string2);
            ((TextView) genericMessageDialog.findViewById(R.id.cancel)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sleepenergyscore.energymeter.activities.c
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityShare.B(GenericMessageDialog.this, view);
                }
            });
            ((TextView) genericMessageDialog.findViewById(R.id.proceed)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sleepenergyscore.energymeter.activities.d
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityShare.C(GenericMessageDialog.this, this, view);
                }
            });
            if (isFinishing()) {
                return;
            }
            genericMessageDialog.show();
        }
    }

    @Override // com.coveiot.android.theme.permissions.PermissionListener
    public void onPermissionSuccess(@NotNull PermissionType permission) {
        Intrinsics.checkNotNullParameter(permission, "permission");
        if (WhenMappings.$EnumSwitchMapping$0[permission.ordinal()] == 1) {
            Handler handler = new Handler();
            showProgress();
            handler.postDelayed(new Runnable() { // from class: com.coveiot.android.sleepenergyscore.energymeter.activities.g
                @Override // java.lang.Runnable
                public final void run() {
                    ActivityShare.D(ActivityShare.this);
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
                Intrinsics.checkNotNullExpressionValue(saveScreen, "saveScreen(container_layout, this@ActivityShare)");
                setBitmap(saveScreen);
                ShareScreen.shareScreenCommom(getBitmap(), this);
            }
        }
    }

    public final void setBitmap(@NotNull Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(bitmap, "<set-?>");
        this.bitmap = bitmap;
    }

    public final void setData(@NotNull ShareEnergyMeterData shareEnergyMeterData) {
        Intrinsics.checkNotNullParameter(shareEnergyMeterData, "<set-?>");
        this.data = shareEnergyMeterData;
    }
}
