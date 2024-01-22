package com.coveiot.android.sportsnotification;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import com.coveiot.android.sportsnotification.SportsPreference;
import com.coveiot.android.sportsnotification.fragment.SportsSettingsFragmentNew;
import com.coveiot.android.sportsnotification.fragment.TodaysMatchFragment;
import com.coveiot.android.sportsnotification.fragment.TodaysMatchFragmentSoccer;
import com.coveiot.android.sportsnotification.model.SportsPreferenceModel;
import com.coveiot.android.sportsnotification.viewmodel.SportsActivityViewModel;
import com.coveiot.android.sportsnotificationsdk.network.SportType;
import com.coveiot.android.theme.BaseActivity;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class SportsNotificationActivity extends BaseActivity {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @Nullable
    public SportsActivityViewModel p;
    @Nullable
    public ConstraintLayout q;
    @Nullable
    public Button r;
    @Nullable
    public Context s;

    public static final void u(SportsNotificationActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    public static final void v(SportsNotificationActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startActivity(new Intent(this$0.s, SportsDisclaimerActivity.class));
    }

    public static final void w(SportsNotificationActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ConstraintLayout constraintLayout = this$0.q;
        Intrinsics.checkNotNull(constraintLayout);
        constraintLayout.setVisibility(8);
        SportsPreference.Companion companion = SportsPreference.Companion;
        Context context = this$0.s;
        Intrinsics.checkNotNull(context);
        companion.saveFTUOpened(context, true);
        this$0.addFragment(SportsSettingsFragmentNew.Companion.newInstance());
    }

    public static final void x(SportsNotificationActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        SportsPreference.Companion companion = SportsPreference.Companion;
        Context context = this$0.s;
        Intrinsics.checkNotNull(context);
        companion.saveFTUOpened(context, true);
        this$0.addFragment(TodaysMatchFragment.Companion.newInstance());
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

    public final void addFragment(@NotNull Fragment fragment) {
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).addToBackStack(null).commit();
    }

    @Nullable
    public final Button getBtnEnable() {
        return this.r;
    }

    @Nullable
    public final Context getContext() {
        return this.s;
    }

    @Nullable
    public final ConstraintLayout getFtuLayout() {
        return this.q;
    }

    @Nullable
    public final SportsActivityViewModel getSportsActivityViewModel() {
        return this.p;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if ((getSupportFragmentManager().findFragmentById(R.id.container) instanceof SportsSettingsFragmentNew) && getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            finish();
        }
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_sports_notification);
        this.s = this;
        this.p = (SportsActivityViewModel) ViewModelProviders.of(this).get(SportsActivityViewModel.class);
        ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(R.id.ftu_layout);
        this.q = constraintLayout;
        Intrinsics.checkNotNull(constraintLayout);
        ConstraintLayout constraintLayout2 = (ConstraintLayout) constraintLayout.findViewById(R.id.toolbar);
        constraintLayout2.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sportsnotification.j
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SportsNotificationActivity.u(SportsNotificationActivity.this, view);
            }
        });
        ((TextView) constraintLayout2.findViewById(R.id.toolbar_title)).setVisibility(8);
        int i = R.id.tvDisclaimer;
        ((TextView) _$_findCachedViewById(i)).setText(Html.fromHtml(getString(R.string.disclaimer_underscore)));
        ((TextView) _$_findCachedViewById(i)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sportsnotification.k
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SportsNotificationActivity.v(SportsNotificationActivity.this, view);
            }
        });
        SportsUtils sportsUtils = SportsUtils.INSTANCE;
        if (!sportsUtils.isDeviceSupportsSoccerSportsSettings(this)) {
            ((ImageView) _$_findCachedViewById(R.id.image_sports_ftu)).setImageResource(R.drawable.img_ftu);
        } else {
            ((ImageView) _$_findCachedViewById(R.id.image_sports_ftu)).setImageResource(R.drawable.img_ftu_soccer);
        }
        this.r = (Button) findViewById(R.id.btn_enable_now);
        if (getIntent().hasExtra("screen_type")) {
            String stringExtra = getIntent().getStringExtra("screen_type");
            ConstraintLayout constraintLayout3 = this.q;
            Intrinsics.checkNotNull(constraintLayout3);
            constraintLayout3.setVisibility(8);
            if (Intrinsics.areEqual(stringExtra, "settings")) {
                SportsPreference.Companion companion = SportsPreference.Companion;
                Context context = this.s;
                Intrinsics.checkNotNull(context);
                companion.saveFTUOpened(context, true);
                addFragment(SportsSettingsFragmentNew.Companion.newInstance());
            } else {
                String name = SportType.SOCCER.name();
                Locale ROOT = Locale.ROOT;
                Intrinsics.checkNotNullExpressionValue(ROOT, "ROOT");
                String lowerCase = name.toLowerCase(ROOT);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
                if (Intrinsics.areEqual(stringExtra, lowerCase)) {
                    SportsPreference.Companion companion2 = SportsPreference.Companion;
                    Context context2 = this.s;
                    Intrinsics.checkNotNull(context2);
                    companion2.saveFTUOpened(context2, true);
                    addFragment(TodaysMatchFragmentSoccer.Companion.newInstance());
                } else {
                    String name2 = SportType.CRICKET.name();
                    Intrinsics.checkNotNullExpressionValue(ROOT, "ROOT");
                    String lowerCase2 = name2.toLowerCase(ROOT);
                    Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase(locale)");
                    if (Intrinsics.areEqual(stringExtra, lowerCase2)) {
                        SportsPreference.Companion companion3 = SportsPreference.Companion;
                        Context context3 = this.s;
                        Intrinsics.checkNotNull(context3);
                        companion3.saveFTUOpened(context3, true);
                        addFragment(TodaysMatchFragment.Companion.newInstance());
                    }
                }
            }
        } else {
            SportsActivityViewModel sportsActivityViewModel = this.p;
            Intrinsics.checkNotNull(sportsActivityViewModel);
            Context context4 = this.s;
            Intrinsics.checkNotNull(context4);
            if (!sportsActivityViewModel.isNotificationEnabled(context4)) {
                SportsPreference.Companion companion4 = SportsPreference.Companion;
                Context context5 = this.s;
                Intrinsics.checkNotNull(context5);
                if (!companion4.isFTUOpened(context5)) {
                    ConstraintLayout constraintLayout4 = this.q;
                    Intrinsics.checkNotNull(constraintLayout4);
                    constraintLayout4.setVisibility(0);
                    Button button = this.r;
                    Intrinsics.checkNotNull(button);
                    button.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sportsnotification.i
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            SportsNotificationActivity.w(SportsNotificationActivity.this, view);
                        }
                    });
                }
            }
            ConstraintLayout constraintLayout5 = this.q;
            Intrinsics.checkNotNull(constraintLayout5);
            constraintLayout5.setVisibility(8);
            Context context6 = this.s;
            Intrinsics.checkNotNull(context6);
            if (sportsUtils.isDeviceSupportsSoccerSportsSettings(context6)) {
                SportsPreference.Companion companion5 = SportsPreference.Companion;
                Context context7 = this.s;
                Intrinsics.checkNotNull(context7);
                SportsPreferenceModel sportsNotification = companion5.getSportsNotification(context7);
                if (kotlin.text.m.equals$default(sportsNotification != null ? sportsNotification.getSport() : null, "Football", false, 2, null)) {
                    addFragment(TodaysMatchFragmentSoccer.Companion.newInstance());
                }
            }
            addFragment(TodaysMatchFragment.Companion.newInstance());
        }
        ((TextView) _$_findCachedViewById(R.id.text_skip)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sportsnotification.h
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SportsNotificationActivity.x(SportsNotificationActivity.this, view);
            }
        });
    }

    public final void setBtnEnable(@Nullable Button button) {
        this.r = button;
    }

    public final void setContext(@Nullable Context context) {
        this.s = context;
    }

    public final void setFtuLayout(@Nullable ConstraintLayout constraintLayout) {
        this.q = constraintLayout;
    }

    public final void setSportsActivityViewModel(@Nullable SportsActivityViewModel sportsActivityViewModel) {
        this.p = sportsActivityViewModel;
    }
}
