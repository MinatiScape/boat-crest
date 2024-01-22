package com.coveiot.android.leonardo.onboarding.consent;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.leonardo.utils.AppNavigator;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.BottomSheetDialogImageTitleMessage;
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
public final class ActivityConsent extends BaseActivity {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();

    public static final void A(ActivityConsent this$0, View view) {
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

    public static final void w(ActivityConsent this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.v(true, false);
    }

    public static final void x(final ActivityConsent this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Drawable drawable = this$0.getResources().getDrawable(R.drawable.info_icon_new);
        Intrinsics.checkNotNullExpressionValue(drawable, "resources.getDrawable(co…R.drawable.info_icon_new)");
        String string = this$0.getString(R.string.information);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.information)");
        String string2 = this$0.getString(R.string.you_are_not_able_to);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.you_are_not_able_to)");
        final BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage = new BottomSheetDialogImageTitleMessage(this$0, drawable, string, string2, false);
        String string3 = this$0.getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(com.coveiot.leaderboard.R.string.ok)");
        bottomSheetDialogImageTitleMessage.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.consent.e
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                ActivityConsent.y(BottomSheetDialogImageTitleMessage.this, this$0, view2);
            }
        });
        bottomSheetDialogImageTitleMessage.show();
    }

    public static final void y(BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage, ActivityConsent this$0, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogImageTitleMessage, "$bottomSheetDialogImageTitleMessage");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        bottomSheetDialogImageTitleMessage.dismiss();
        this$0.v(false, false);
    }

    public static final void z(ActivityConsent this$0, View view) {
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

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        finish();
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_consent_screen);
        int i = R.id.toolbar;
        ((TextView) _$_findCachedViewById(i).findViewById(R.id.toolbar_title)).setText(getString(R.string.terms_and_conditions));
        ((TextView) _$_findCachedViewById(i).findViewById(R.id.toolbar_back_arrow)).setVisibility(8);
        ((Button) _$_findCachedViewById(R.id.buttonAccept)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.consent.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityConsent.w(ActivityConsent.this, view);
            }
        });
        ((Button) _$_findCachedViewById(R.id.buttonReject)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.consent.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityConsent.x(ActivityConsent.this, view);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.textViewTermsConditionView)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.consent.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityConsent.z(ActivityConsent.this, view);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.textViewPolicyView)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.consent.d
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityConsent.A(ActivityConsent.this, view);
            }
        });
    }

    public final void v(final boolean z, boolean z2) {
        if (AppUtils.isNetConnected(this)) {
            SConsentRequest sConsentRequest = new SConsentRequest();
            sConsentRequest.setMonitoringConsent(Boolean.valueOf(z));
            sConsentRequest.setInvalidateSession(Boolean.valueOf(z2));
            CoveUserAppSettings.saveUserConsent(sConsentRequest, new CoveApiListener<ActivityRes, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.onboarding.consent.ActivityConsent$callUserConsentApi$1
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    ActivityConsent activityConsent = ActivityConsent.this;
                    Toast.makeText(activityConsent, activityConsent.getResources().getString(R.string.failure_message), 0).show();
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable ActivityRes activityRes) {
                    ActivityConsent activityConsent = ActivityConsent.this;
                    Toast.makeText(activityConsent, activityConsent.getResources().getString(R.string.success_message), 0).show();
                    UserDataManager.getInstance(ActivityConsent.this).saveRemoteMonitoringConsent(z);
                    AppNavigator.Companion.navigateToStepsGoal(ActivityConsent.this);
                }
            });
            return;
        }
        Toast.makeText(this, getResources().getString(R.string.please_check_your_internet), 0).show();
    }
}
