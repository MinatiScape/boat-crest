package com.coveiot.android.leonardo.more.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.more.adapters.LanguageAdapter;
import com.coveiot.android.leonardo.onboarding.splash.activities.ActivitySplash;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.leonardo.utils.ViewUtilsKt;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.FirebaseConstants;
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.coveaccess.prefs.PreferenceManager;
import com.coveiot.covepreferences.SessionManager;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityLanguageSettings extends BaseActivity implements LanguageAdapter.SubmitButtonEnableListner {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public final ArrayList<String> p = new ArrayList<>();
    @NotNull
    public String q;
    public int r;
    @NotNull
    public String s;

    /* loaded from: classes5.dex */
    public static final class a extends Lambda implements Function1<View, Unit> {
        public a() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(View view) {
            invoke2(view);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull View it) {
            Intrinsics.checkNotNullParameter(it, "it");
            AnalyticsLog analyticsLog = new AnalyticsLog();
            analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
            analyticsLog.setScreenName(FirebaseEventParams.ScreenName.LANGUAGE_SELECTION_SCREEN.getValue());
            analyticsLog.setDescription(FirebaseEventParams.Description.OPEN_CONFIGURATION_SCREEN.getValue());
            analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.NEXT_BUTTON.getValue());
            CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
            ActivityLanguageSettings.this.s();
        }
    }

    /* loaded from: classes5.dex */
    public static final class b extends Lambda implements Function1<View, Unit> {
        public b() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(View view) {
            invoke2(view);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull View it) {
            Intrinsics.checkNotNullParameter(it, "it");
            AnalyticsLog analyticsLog = new AnalyticsLog();
            analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
            analyticsLog.setScreenName(FirebaseEventParams.ScreenName.LANGUAGE_SELECTION_SCREEN.getValue());
            analyticsLog.setDescription(FirebaseEventParams.Description.OPEN_CONFIGURATION_SCREEN.getValue());
            analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.NEXT_BUTTON.getValue());
            CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
            SessionManager.getInstance(ActivityLanguageSettings.this).setLanguageSettings(Boolean.TRUE);
            ActivityLanguageSettings.this.s();
        }
    }

    public ActivityLanguageSettings() {
        String selectedLanguage = SessionManager.getInstance(this).getSelectedLanguage();
        Intrinsics.checkNotNullExpressionValue(selectedLanguage, "getInstance(this).selectedLanguage");
        this.q = selectedLanguage;
        this.r = SessionManager.getInstance(this).getSelectedPosition();
        this.s = "";
    }

    public static final void r(ActivityLanguageSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
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

    @NotNull
    public final ArrayList<String> getArrayListLanguage() {
        return this.p;
    }

    @NotNull
    public final String getPageType() {
        return this.s;
    }

    @NotNull
    public final String getSelectedLanguage() {
        return this.q;
    }

    public final int getSelectedPosition() {
        return this.r;
    }

    public final void initToolbar() {
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        if (kotlin.text.m.equals(this.s, AppConstants.ONBOARDING.getValue(), true)) {
            ((TextView) findViewById(R.id.toolbar_title)).setText(getString(R.string.select_language));
            ((TextView) findViewById(R.id.toolbar_back_arrow)).setVisibility(8);
        } else {
            ((TextView) findViewById(R.id.toolbar_title)).setText(getString(R.string.language_preferred));
            ((TextView) findViewById(R.id.toolbar_back_arrow)).setVisibility(0);
        }
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.ke
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityLanguageSettings.r(ActivityLanguageSettings.this, view);
            }
        });
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_language_settings);
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.KH_OPEN.getValue());
        FirebaseConstants firebaseConstants = FirebaseConstants.PREVIOUS_SCREEN_NAME;
        analyticsLog.setPreviousScreenName(firebaseConstants.getValue());
        FirebaseEventParams.ScreenName screenName = FirebaseEventParams.ScreenName.LANGUAGE_SELECTION_SCREEN;
        analyticsLog.setScreenName(screenName.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        firebaseConstants.setValue(screenName.getValue());
        String stringExtra = getIntent().getStringExtra(AppConstants.PAGE_TYPE.getValue());
        Intrinsics.checkNotNull(stringExtra);
        this.s = stringExtra;
        if (kotlin.text.m.equals(stringExtra, AppConstants.ONBOARDING.getValue(), true)) {
            ((FrameLayout) _$_findCachedViewById(R.id.done_frameLayout)).setVisibility(0);
            ((Button) _$_findCachedViewById(R.id.done_submit)).setVisibility(8);
        } else {
            ((FrameLayout) _$_findCachedViewById(R.id.done_frameLayout)).setVisibility(8);
            ((Button) _$_findCachedViewById(R.id.done_submit)).setVisibility(0);
        }
        initToolbar();
        ArrayList<String> arrayList = this.p;
        if (arrayList != null) {
            arrayList.clear();
        }
        this.p.add("English");
        this.p.add("বাংলা");
        this.p.add("தமிழ்");
        this.p.add("普通话");
        ((ListView) _$_findCachedViewById(R.id.lang_listview)).setAdapter((ListAdapter) new LanguageAdapter(this, this.p, SessionManager.getInstance(this).getSelectedPosition()));
        Button done_submit = (Button) _$_findCachedViewById(R.id.done_submit);
        Intrinsics.checkNotNullExpressionValue(done_submit, "done_submit");
        ViewUtilsKt.setSafeOnClickListener(done_submit, new a());
        FrameLayout done_frameLayout = (FrameLayout) _$_findCachedViewById(R.id.done_frameLayout);
        Intrinsics.checkNotNullExpressionValue(done_frameLayout, "done_frameLayout");
        ViewUtilsKt.setSafeOnClickListener(done_frameLayout, new b());
    }

    @Override // com.coveiot.android.leonardo.more.adapters.LanguageAdapter.SubmitButtonEnableListner
    public void onLanguageSelected(@NotNull String selectedLang, int i) {
        Intrinsics.checkNotNullParameter(selectedLang, "selectedLang");
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
        analyticsLog.setScreenName(FirebaseEventParams.ScreenName.LANGUAGE_SELECTION_SCREEN.getValue());
        analyticsLog.setDescription(selectedLang);
        analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.LANGUAGE_SELECTION_RADIO_BUTTON.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        ((Button) _$_findCachedViewById(R.id.done_submit)).setEnabled(!kotlin.text.m.equals(selectedLang, SessionManager.getInstance(this).getSelectedLanguage(), true));
        this.q = selectedLang;
        this.r = i;
    }

    public final void s() {
        SessionManager.getInstance(this).setSelectedLanguage(this.q);
        SessionManager.getInstance(this).setSelectedPosition(this.r);
        PreferenceManager.getInstance().saveLanguage(this.q);
        startActivity(new Intent(this, ActivitySplash.class));
        finish();
    }

    public final void setPageType(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.s = str;
    }

    public final void setSelectedLanguage(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.q = str;
    }

    public final void setSelectedPosition(int i) {
        this.r = i;
    }
}
