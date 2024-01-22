package com.coveiot.android.leonardo.more.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.more.adapters.SettingsTitleAdapterWithListner;
import com.coveiot.android.leonardo.utils.AppNavigator;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.FirebaseConstants;
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.android.theme.ItemClickListenerNew;
import com.coveiot.android.theme.model.SettingsListItemModelWithListener;
import com.coveiot.android.theme.utils.ThemesUtils;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.utils.utility.AppUtils;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityAboutUs extends BaseActivity implements ItemClickListenerNew {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();

    public static final void r(ActivityAboutUs this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
        analyticsLog.setScreenName(FirebaseEventParams.ScreenName.ABOUT_SCREEN.getValue());
        analyticsLog.setDescription(FirebaseEventParams.Description.OPEN_BOTTOM_MENU_MORE_SCREEN.getValue());
        analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.BACK_BUTTON.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        this$0.onBackPressed();
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

    public final void initToolbar() {
        ((TextView) findViewById(R.id.toolbar_title)).setText(getString(R.string.about_app));
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityAboutUs.r(ActivityAboutUs.this, view);
            }
        });
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_about_us);
        initToolbar();
        ArrayList arrayList = new ArrayList();
        String string = getString(R.string.disclaimer);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.disclaimer)");
        arrayList.add(new SettingsListItemModelWithListener(null, string, this, null, null));
        String string2 = getString(R.string.privacy_policy);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.privacy_policy)");
        arrayList.add(new SettingsListItemModelWithListener(null, string2, this, null, null));
        String string3 = getString(R.string.eula);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.eula)");
        arrayList.add(new SettingsListItemModelWithListener(null, string3, this, null, null));
        int i = R.id.rcv_about_us;
        ((RecyclerView) _$_findCachedViewById(i)).setLayoutManager(new LinearLayoutManager(this));
        ((RecyclerView) _$_findCachedViewById(i)).setAdapter(new SettingsTitleAdapterWithListner(arrayList));
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.KH_OPEN.getValue());
        FirebaseConstants firebaseConstants = FirebaseConstants.PREVIOUS_SCREEN_NAME;
        analyticsLog.setPreviousScreenName(firebaseConstants.getValue());
        FirebaseEventParams.ScreenName screenName = FirebaseEventParams.ScreenName.ABOUT_SCREEN;
        analyticsLog.setScreenName(screenName.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        ImageView iv_powered_by_logo = (ImageView) _$_findCachedViewById(R.id.iv_powered_by_logo);
        Intrinsics.checkNotNullExpressionValue(iv_powered_by_logo, "iv_powered_by_logo");
        ThemesUtils.setPoweredByLogoIcon(this, iv_powered_by_logo, false);
        firebaseConstants.setValue(screenName.getValue());
    }

    @Override // com.coveiot.android.theme.ItemClickListenerNew
    public void onItemSelected(@NotNull String itemName) {
        Intrinsics.checkNotNullParameter(itemName, "itemName");
        if (Intrinsics.areEqual(itemName, getString(R.string.privacy_policy))) {
            if (!AppUtils.isNetConnected(this)) {
                Toast.makeText(this, (int) R.string.noconnection, 0).show();
                return;
            }
            AppNavigator.Companion companion = AppNavigator.Companion;
            String string = getString(R.string.privacy_policy);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.privacy_policy)");
            String privacyPolicyDocUrl = SessionManager.getInstance(this).getPrivacyPolicyDocUrl();
            Intrinsics.checkNotNullExpressionValue(privacyPolicyDocUrl, "getInstance(this).privacyPolicyDocUrl");
            companion.navigateToWebViewer(this, string, privacyPolicyDocUrl);
        } else if (Intrinsics.areEqual(itemName, getString(R.string.eula))) {
            if (!AppUtils.isNetConnected(this)) {
                Toast.makeText(this, (int) R.string.noconnection, 0).show();
                return;
            }
            AppNavigator.Companion companion2 = AppNavigator.Companion;
            String string2 = getString(R.string.eula);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.eula)");
            String legalDocUrl = SessionManager.getInstance(this).getLegalDocUrl();
            Intrinsics.checkNotNullExpressionValue(legalDocUrl, "getInstance(this).legalDocUrl");
            companion2.navigateToWebViewer(this, string2, legalDocUrl);
        } else if (Intrinsics.areEqual(itemName, getString(R.string.disclaimer))) {
            AppNavigator.Companion.navigateToDisclaimer(this);
        }
    }
}
