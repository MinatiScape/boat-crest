package com.coveiot.android.leonardo.more.activities;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.SwitchCompat;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.leonardo.utils.AppNavigator;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.BottomSheetDialogImageTitleMessageTwoBtns;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.CoveUserAppSettings;
import com.coveiot.coveaccess.fitness.model.ActivityRes;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.SConsentRequest;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.utils.utility.AppUtils;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityConsentSetting extends BaseActivity {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public boolean p = UserDataManager.getInstance(this).isRemoteMonitoringConsentGiven();

    public static final void A(ActivityConsentSetting this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (!AppUtils.isNetConnected(this$0)) {
            Toast.makeText(this$0, (int) R.string.noconnection, 0).show();
            return;
        }
        AppNavigator.Companion companion = AppNavigator.Companion;
        String string = this$0.getString(R.string.privacy_policy);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.privacy_policy)");
        companion.navigateToWebViewer(this$0, string, AppConstants.CONSENT_PRIVACY_POLICY_URL.getValue());
    }

    public static final void C(BottomSheetDialogImageTitleMessageTwoBtns bottomSheetDialogImageTitleMessage, ActivityConsentSetting this$0, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogImageTitleMessage, "$bottomSheetDialogImageTitleMessage");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        bottomSheetDialogImageTitleMessage.dismiss();
        this$0.w(false, false);
    }

    public static final void D(BottomSheetDialogImageTitleMessageTwoBtns bottomSheetDialogImageTitleMessage, ActivityConsentSetting this$0, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogImageTitleMessage, "$bottomSheetDialogImageTitleMessage");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        bottomSheetDialogImageTitleMessage.dismiss();
        ((SwitchCompat) this$0._$_findCachedViewById(R.id.permission_switch)).setChecked(this$0.p);
    }

    public static final void x(ActivityConsentSetting this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    public static final void y(ActivityConsentSetting this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (z) {
            this$0.w(true, false);
        } else {
            this$0.B();
        }
    }

    public static final void z(ActivityConsentSetting this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (!AppUtils.isNetConnected(this$0)) {
            Toast.makeText(this$0, (int) R.string.noconnection, 0).show();
            return;
        }
        AppNavigator.Companion companion = AppNavigator.Companion;
        String string = this$0.getString(R.string.terms_and_conditions);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.terms_and_conditions)");
        companion.navigateToWebViewer(this$0, string, AppConstants.CONSENT_EULA_URL.getValue());
    }

    public final void B() {
        Drawable drawable = getResources().getDrawable(R.drawable.info_icon_new);
        Intrinsics.checkNotNullExpressionValue(drawable, "resources.getDrawable(co…R.drawable.info_icon_new)");
        String string = getString(R.string.confirmation);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.confirmation)");
        String string2 = getString(R.string.you_are_not_able_to);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.you_are_not_able_to)");
        final BottomSheetDialogImageTitleMessageTwoBtns bottomSheetDialogImageTitleMessageTwoBtns = new BottomSheetDialogImageTitleMessageTwoBtns(this, drawable, string, string2, false);
        String string3 = getString(R.string.proceed);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.proceed)");
        bottomSheetDialogImageTitleMessageTwoBtns.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.v4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityConsentSetting.C(BottomSheetDialogImageTitleMessageTwoBtns.this, this, view);
            }
        });
        String string4 = getString(R.string.cancel);
        Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.cancel)");
        bottomSheetDialogImageTitleMessageTwoBtns.setNegativeButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.u4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityConsentSetting.D(BottomSheetDialogImageTitleMessageTwoBtns.this, this, view);
            }
        });
        bottomSheetDialogImageTitleMessageTwoBtns.show();
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

    public final boolean getPrevState() {
        return this.p;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        finish();
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_consent_settings);
        int i = R.id.toolbar;
        ((TextView) _$_findCachedViewById(i).findViewById(R.id.toolbar_title)).setText(getString(R.string.permission_setting));
        ((TextView) _$_findCachedViewById(i).findViewById(R.id.toolbar_back_arrow)).setVisibility(0);
        ((TextView) _$_findCachedViewById(i).findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.t4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityConsentSetting.x(ActivityConsentSetting.this, view);
            }
        });
        int i2 = R.id.permission_switch;
        ((SwitchCompat) _$_findCachedViewById(i2)).setChecked(this.p);
        ((SwitchCompat) _$_findCachedViewById(i2)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.coveiot.android.leonardo.more.activities.w4
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                ActivityConsentSetting.y(ActivityConsentSetting.this, compoundButton, z);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.textViewTermsConditionView)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.s4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityConsentSetting.z(ActivityConsentSetting.this, view);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.textViewPolicyView)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.r4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityConsentSetting.A(ActivityConsentSetting.this, view);
            }
        });
    }

    public final void setPrevState(boolean z) {
        this.p = z;
    }

    public final void w(final boolean z, boolean z2) {
        if (AppUtils.isNetConnected(this)) {
            SConsentRequest sConsentRequest = new SConsentRequest();
            sConsentRequest.setMonitoringConsent(Boolean.valueOf(z));
            sConsentRequest.setInvalidateSession(Boolean.valueOf(z2));
            CoveUserAppSettings.saveUserConsent(sConsentRequest, new CoveApiListener<ActivityRes, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.more.activities.ActivityConsentSetting$callUserConsentApi$1
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    ((SwitchCompat) ActivityConsentSetting.this._$_findCachedViewById(R.id.permission_switch)).setChecked(ActivityConsentSetting.this.getPrevState());
                    ActivityConsentSetting activityConsentSetting = ActivityConsentSetting.this;
                    Toast.makeText(activityConsentSetting, activityConsentSetting.getResources().getString(R.string.failure_message), 0).show();
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable ActivityRes activityRes) {
                    ActivityConsentSetting activityConsentSetting = ActivityConsentSetting.this;
                    Toast.makeText(activityConsentSetting, activityConsentSetting.getResources().getString(R.string.success_message), 0).show();
                    UserDataManager.getInstance(ActivityConsentSetting.this).saveRemoteMonitoringConsent(z);
                    ActivityConsentSetting.this.finish();
                }
            });
            return;
        }
        Toast.makeText(this, getResources().getString(R.string.noconnection), 0).show();
        ((SwitchCompat) _$_findCachedViewById(R.id.permission_switch)).setChecked(this.p);
    }
}
