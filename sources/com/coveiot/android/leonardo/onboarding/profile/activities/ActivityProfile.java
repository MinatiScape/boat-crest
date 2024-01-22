package com.coveiot.android.leonardo.onboarding.profile.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.onboarding.profile.fragments.FragmentBasicProfileInfo;
import com.coveiot.android.leonardo.onboarding.profile.fragments.FragmentFinalProfile;
import com.coveiot.android.leonardo.onboarding.profile.fragments.FragmentGender;
import com.coveiot.android.leonardo.onboarding.profile.fragments.FragmentSocialLogin;
import com.coveiot.android.leonardo.utils.AppNavigator;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.covepreferences.data.ProfileData;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityProfile extends BaseActivity {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public final long p = 3000;
    public long q;
    public TextView skipTextView;

    public static final void t(ActivityProfile this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    public static final void u(ActivityProfile this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        View findViewById = this$0._$_findCachedViewById(R.id.toolbar).findViewById(R.id.toolbar_back_arrow);
        Intrinsics.checkNotNullExpressionValue(findViewById, "toolbar.findViewById<Tex…(R.id.toolbar_back_arrow)");
        findViewById.setVisibility(this$0.getSupportFragmentManager().getBackStackEntryCount() > 1 ? 0 : 8);
        this$0.getSkipTextView().setVisibility(this$0.getSupportFragmentManager().findFragmentById(R.id.fragment_container) instanceof FragmentGender ? 0 : 8);
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
    public final TextView getSkipTextView() {
        TextView textView = this.skipTextView;
        if (textView != null) {
            return textView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("skipTextView");
        return null;
    }

    public final boolean isSkipTextViewInitialised() {
        return this.skipTextView != null;
    }

    public final void loadBlueToothScanActivity() {
        AppNavigator.Companion.navigateToBluetoothScanActivity(this);
        finish();
    }

    public final void loadFinalProfileFragment() {
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        FragmentFinalProfile.Companion companion = FragmentFinalProfile.Companion;
        beginTransaction.add(R.id.fragment_container, companion.newInstance()).addToBackStack(companion.toString()).commit();
    }

    public final void loadGenderFragment() {
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        FragmentGender.Companion companion = FragmentGender.Companion;
        beginTransaction.add(R.id.fragment_container, companion.newInstance()).addToBackStack(companion.toString()).commit();
    }

    public final void loadProfilePictureFragment() {
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        FragmentBasicProfileInfo.Companion companion = FragmentBasicProfileInfo.Companion;
        beginTransaction.add(R.id.fragment_container, companion.newInstance()).addToBackStack(companion.toString()).commit();
    }

    public final void loadProfilePictureFragmentWith(@NotNull Bundle bundle) {
        Intrinsics.checkNotNullParameter(bundle, "bundle");
        FragmentBasicProfileInfo.Companion companion = FragmentBasicProfileInfo.Companion;
        FragmentBasicProfileInfo newInstance = companion.newInstance();
        newInstance.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, newInstance).addToBackStack(companion.toString()).commit();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() != 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            if (this.q + this.p > System.currentTimeMillis()) {
                finish();
            }
            Toast.makeText(this, getString(R.string.quit_app), 0).show();
            this.q = System.currentTimeMillis();
        }
        Fragment findFragmentById = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (findFragmentById instanceof FragmentGender) {
            AnalyticsLog analyticsLog = new AnalyticsLog();
            analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
            analyticsLog.setScreenName(FirebaseEventParams.ScreenName.GENDER_SELECTION_SCREEN.getValue());
            analyticsLog.setDescription(FirebaseEventParams.Description.OPEN_NAME_EMAIL_SIGN_UP_SCREEN.getValue());
            analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.BACK_BUTTON.getValue());
            CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        }
        if (findFragmentById instanceof FragmentFinalProfile) {
            AnalyticsLog analyticsLog2 = new AnalyticsLog();
            analyticsLog2.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
            analyticsLog2.setScreenName(FirebaseEventParams.ScreenName.HEIGHT_WEIGHT_SCREEN.getValue());
            analyticsLog2.setDescription(FirebaseEventParams.Description.OPEN_GENDER_SELECTION_SCREEN.getValue());
            analyticsLog2.setUiElementName(FirebaseEventParams.UiElementName.BACK_BUTTON.getValue());
            CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog2);
        }
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_profile);
        int i = R.id.toolbar;
        View findViewById = _$_findCachedViewById(i).findViewById(R.id.save);
        Intrinsics.checkNotNullExpressionValue(findViewById, "toolbar.findViewById(R.id.save)");
        setSkipTextView((TextView) findViewById);
        if (ProfileData.getInstance().getMobileNumber() == null) {
            AppNavigator.Companion.navigateToSplashActivityAndClear(this);
            return;
        }
        ((TextView) _$_findCachedViewById(i).findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.profile.activities.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityProfile.t(ActivityProfile.this, view);
            }
        });
        ((TextView) _$_findCachedViewById(i).findViewById(R.id.toolbar_title)).setText(R.string.sign_up);
        s();
        getSkipTextView().setText(getString(R.string.skip));
        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() { // from class: com.coveiot.android.leonardo.onboarding.profile.activities.b
            @Override // androidx.fragment.app.FragmentManager.OnBackStackChangedListener
            public final void onBackStackChanged() {
                ActivityProfile.u(ActivityProfile.this);
            }
        });
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i, @NotNull String[] permissions, @NotNull int[] grantResults) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        super.onRequestPermissionsResult(i, permissions, grantResults);
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
        analyticsLog.setScreenName(FirebaseEventParams.ScreenName.GRANT_PERMISSION_DIALOG.getValue());
        analyticsLog.setAppPermissionId("Manifest.permission.CAMERA");
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        Fragment findFragmentById = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        Intrinsics.checkNotNull(findFragmentById);
        findFragmentById.onRequestPermissionsResult(i, permissions, grantResults);
    }

    public final void s() {
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        FragmentSocialLogin.Companion companion = FragmentSocialLogin.Companion;
        beginTransaction.replace(R.id.fragment_container, companion.newInstance()).addToBackStack(companion.toString()).commit();
    }

    public final void setSkipTextView(@NotNull TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<set-?>");
        this.skipTextView = textView;
    }
}
