package com.coveiot.android.leonardo.quickreply.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.SwitchCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProviders;
import com.coveiot.android.activitymodes.utils.ViewUtilsKt;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.boat.R;
import com.coveiot.android.dashboard2.listener.ViewModelListener;
import com.coveiot.android.leonardo.quickreply.viewmodel.QuickReplySettingsViewModel;
import com.coveiot.android.leonardo.utils.ViewModelFactory;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.BottomSheetDialogOneButtonOneTitle;
import com.coveiot.android.theme.BottomSheetDialogOneButtonTitleMessage;
import com.coveiot.android.theme.BottomSheetDialogTwoButtons;
import com.coveiot.android.theme.permissions.PermissionCheckDialog;
import com.coveiot.utils.utility.AppUtils;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityQuickReplySettings extends BaseActivity implements PermissionCheckDialog.OnItemClickListener, ViewModelListener {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public final int p = 1001;
    public QuickReplySettingsViewModel q;

    public static final void A(ActivityQuickReplySettings this$0, BottomSheetDialogTwoButtons dialog, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        ((Button) this$0._$_findCachedViewById(R.id.saveBtn)).performClick();
        dialog.dismiss();
    }

    public static final void B(BottomSheetDialogTwoButtons dialog, ActivityQuickReplySettings this$0, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        dialog.dismiss();
        this$0.finish();
    }

    public static final void C(ActivityQuickReplySettings this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        QuickReplySettingsViewModel quickReplySettingsViewModel = null;
        if (((SwitchCompat) this$0._$_findCachedViewById(R.id.quick_reply_switch)).isChecked()) {
            if (this$0.y()) {
                QuickReplySettingsViewModel quickReplySettingsViewModel2 = this$0.q;
                if (quickReplySettingsViewModel2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                } else {
                    quickReplySettingsViewModel = quickReplySettingsViewModel2;
                }
                quickReplySettingsViewModel.setQuickReplyList(true);
                ((Button) this$0._$_findCachedViewById(R.id.saveBtn)).setEnabled(false);
                return;
            }
            return;
        }
        QuickReplySettingsViewModel quickReplySettingsViewModel3 = this$0.q;
        if (quickReplySettingsViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            quickReplySettingsViewModel = quickReplySettingsViewModel3;
        }
        quickReplySettingsViewModel.setQuickReplyList(false);
        ((Button) this$0._$_findCachedViewById(R.id.saveBtn)).setEnabled(false);
    }

    public static final void D(ActivityQuickReplySettings this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (((SwitchCompat) this$0._$_findCachedViewById(R.id.quick_reply_switch)).isChecked()) {
            this$0.startActivity(new Intent(this$0, ActivityManageQuickReply.class));
        } else {
            Toast.makeText(this$0, this$0.getResources().getString(R.string.enable_quick_reply), 0).show();
        }
    }

    public static final void E(ActivityQuickReplySettings this$0, CompoundButton compoundButton, boolean z) {
        BleApi bleApi;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BleApiManager bleApiManager = BleApiManager.getInstance(this$0);
        if (((bleApiManager == null || (bleApi = bleApiManager.getBleApi()) == null) ? null : bleApi.getConnectionStatus()) != ConnectionStatus.CONNECTED) {
            String string = this$0.getString(R.string.please_connect_your_device);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.please_connect_your_device)");
            ViewUtilsKt.toast(this$0, string);
        } else if (z) {
            if (this$0.y()) {
                ((Button) this$0._$_findCachedViewById(R.id.saveBtn)).setEnabled(true);
            }
        } else {
            ((Button) this$0._$_findCachedViewById(R.id.saveBtn)).setEnabled(true);
        }
    }

    public static final void G(ActivityQuickReplySettings this$0, BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonTitleMessage, "$bottomSheetDialogOneButtonTitleMessage");
        AppUtils.openAppSettings(this$0, -1);
        bottomSheetDialogOneButtonTitleMessage.dismiss();
    }

    public static final void H(BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle, ActivityQuickReplySettings this$0, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonOneTitle, "$bottomSheetDialogOneButtonOneTitle");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        bottomSheetDialogOneButtonOneTitle.dismiss();
        this$0.finish();
    }

    public static final void z(ActivityQuickReplySettings this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    public final void F(String str) {
        Intrinsics.checkNotNull(this);
        String string = getString(R.string.permission_required);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.permission_required)");
        final BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = new BottomSheetDialogOneButtonTitleMessage(this, string, str);
        String string2 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.ok)");
        bottomSheetDialogOneButtonTitleMessage.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.quickreply.activity.l
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityQuickReplySettings.G(ActivityQuickReplySettings.this, bottomSheetDialogOneButtonTitleMessage, view);
            }
        });
        bottomSheetDialogOneButtonTitleMessage.show();
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

    public final int getREQUEST_SMS_PERMISSION() {
        return this.p;
    }

    public final void initToolbar() {
        ((TextView) findViewById(R.id.toolbar_title)).setText(getString(R.string.quick_reply));
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.quickreply.activity.i
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityQuickReplySettings.z(ActivityQuickReplySettings.this, view);
            }
        });
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == this.p) {
            y();
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (((Button) _$_findCachedViewById(R.id.saveBtn)).isEnabled()) {
            String string = getString(R.string.confirmation);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.confirmation)");
            String string2 = getString(R.string.save_changes);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.save_changes)");
            final BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = new BottomSheetDialogTwoButtons(this, string, string2, false, 8, null);
            String string3 = getString(R.string.save_changes_btn);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.save_changes_btn)");
            bottomSheetDialogTwoButtons.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.quickreply.activity.m
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityQuickReplySettings.A(ActivityQuickReplySettings.this, bottomSheetDialogTwoButtons, view);
                }
            });
            String string4 = getString(R.string.discard);
            Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.discard)");
            bottomSheetDialogTwoButtons.setNegativeButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.quickreply.activity.o
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityQuickReplySettings.B(BottomSheetDialogTwoButtons.this, this, view);
                }
            });
            bottomSheetDialogTwoButtons.show();
            return;
        }
        finish();
    }

    @Override // com.coveiot.android.theme.permissions.PermissionCheckDialog.OnItemClickListener
    public void onCancelItemClickListener() {
        ((SwitchCompat) _$_findCachedViewById(R.id.quick_reply_switch)).setChecked(false);
        ((Button) _$_findCachedViewById(R.id.saveBtn)).setEnabled(false);
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_quick_reply_settings);
        initToolbar();
        QuickReplySettingsViewModel quickReplySettingsViewModel = (QuickReplySettingsViewModel) ViewModelProviders.of(this, new ViewModelFactory(this)).get(QuickReplySettingsViewModel.class);
        this.q = quickReplySettingsViewModel;
        QuickReplySettingsViewModel quickReplySettingsViewModel2 = null;
        if (quickReplySettingsViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            quickReplySettingsViewModel = null;
        }
        quickReplySettingsViewModel.setMListener(this);
        int i = R.id.saveBtn;
        ((Button) _$_findCachedViewById(i)).setEnabled(false);
        ((Button) _$_findCachedViewById(i)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.quickreply.activity.j
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityQuickReplySettings.C(ActivityQuickReplySettings.this, view);
            }
        });
        ((ConstraintLayout) _$_findCachedViewById(R.id.cl_manage_quick_reply)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.quickreply.activity.k
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityQuickReplySettings.D(ActivityQuickReplySettings.this, view);
            }
        });
        int i2 = R.id.quick_reply_switch;
        ((SwitchCompat) _$_findCachedViewById(i2)).setOnCheckedChangeListener(null);
        SwitchCompat switchCompat = (SwitchCompat) _$_findCachedViewById(i2);
        QuickReplySettingsViewModel quickReplySettingsViewModel3 = this.q;
        if (quickReplySettingsViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            quickReplySettingsViewModel2 = quickReplySettingsViewModel3;
        }
        switchCompat.setChecked(quickReplySettingsViewModel2.getQuickReplyIsEnabled());
        if (ContextCompat.checkSelfPermission(this, "android.permission.SEND_SMS") != 0) {
            ((SwitchCompat) _$_findCachedViewById(i2)).setChecked(false);
        }
        ((SwitchCompat) _$_findCachedViewById(i2)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.coveiot.android.leonardo.quickreply.activity.p
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                ActivityQuickReplySettings.E(ActivityQuickReplySettings.this, compoundButton, z);
            }
        });
    }

    @Override // com.coveiot.android.dashboard2.listener.ViewModelListener
    public void onDataFailure(@NotNull String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        Toast.makeText(this, message, 0).show();
    }

    @Override // com.coveiot.android.theme.permissions.PermissionCheckDialog.OnItemClickListener
    public void onOKayItemClickListener() {
        if (ContextCompat.checkSelfPermission(this, "android.permission.SEND_SMS") == 0) {
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.SEND_SMS"}, this.p);
        } else if (ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.SEND_SMS")) {
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.SEND_SMS"}, this.p);
        } else {
            ((Button) _$_findCachedViewById(R.id.saveBtn)).setEnabled(false);
            ((SwitchCompat) _$_findCachedViewById(R.id.quick_reply_switch)).setChecked(false);
            String string = getString(R.string.send_sms_permission_required);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.send_sms_permission_required)");
            F(string);
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i, @NotNull String[] permissions, @NotNull int[] grantResults) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        super.onRequestPermissionsResult(i, permissions, grantResults);
        if (i == this.p) {
            if ((!(grantResults.length == 0)) && grantResults[0] == 0) {
                Toast.makeText(this, getString(R.string.permission_granted), 1).show();
            } else {
                ((SwitchCompat) _$_findCachedViewById(R.id.quick_reply_switch)).setChecked(false);
            }
        }
    }

    @Override // com.coveiot.android.dashboard2.listener.ViewModelListener
    public void onSuccess() {
        showSuccessDialog();
    }

    public final void showSuccessDialog() {
        String string = getString(R.string.success_message);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.success_message)");
        final BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = new BottomSheetDialogOneButtonOneTitle(this, string);
        String string2 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(\n            R.string.ok\n        )");
        bottomSheetDialogOneButtonOneTitle.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.quickreply.activity.n
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityQuickReplySettings.H(BottomSheetDialogOneButtonOneTitle.this, this, view);
            }
        });
        bottomSheetDialogOneButtonOneTitle.show();
    }

    public final boolean y() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(this, "android.permission.SEND_SMS") != 0) {
                ((SwitchCompat) _$_findCachedViewById(R.id.quick_reply_switch)).setChecked(false);
                ((Button) _$_findCachedViewById(R.id.saveBtn)).setEnabled(false);
                String string = getString(R.string.quick_reply_permission_desc);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.quick_reply_permission_desc)");
                new PermissionCheckDialog(this, string, this).show(getSupportFragmentManager(), "smsPermissionDialog");
                return false;
            }
            return true;
        }
        return false;
    }
}
