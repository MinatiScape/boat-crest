package com.coveiot.android.leonardo.onboarding.phonevalidation.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.onboarding.phonevalidation.adapters.CountryAdapter;
import com.coveiot.android.leonardo.onboarding.phonevalidation.fragments.FragmentEnterPhoneNumber;
import com.coveiot.android.leonardo.onboarding.phonevalidation.viewmodel.ActivityCountrySelectionViewModel;
import com.coveiot.android.leonardo.utils.ViewModelFactory;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.FirebaseConstants;
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.utils.model.CountryCodeModel;
import com.coveiot.utils.utility.AppUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityChooseCountry extends BaseActivity implements Observer<ArrayList<CountryCodeModel>>, CountryAdapter.OnCountryClick {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();

    public static final int t(CountryCodeModel countryCodeModel, CountryCodeModel countryCodeModel2) {
        String countryName = countryCodeModel.getCountryName();
        String countryName2 = countryCodeModel2.getCountryName();
        Intrinsics.checkNotNullExpressionValue(countryName2, "t2.countryName");
        return countryName.compareTo(countryName2);
    }

    public static final void u(ActivityChooseCountry this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
        analyticsLog.setScreenName(FirebaseEventParams.ScreenName.COUNTRY_SELECTION_SCREEN.getValue());
        analyticsLog.setDescription(FirebaseEventParams.Description.OPEN_PHONE_NUMBER_SCREEN.getValue());
        analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.BACK_BUTTON.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        this$0.onBackPressed();
    }

    public static final boolean v(ActivityCountrySelectionViewModel mViewModelCountrySelection) {
        Intrinsics.checkNotNullParameter(mViewModelCountrySelection, "$mViewModelCountrySelection");
        mViewModelCountrySelection.loadAllCountries();
        return false;
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

    @Override // com.coveiot.android.leonardo.onboarding.phonevalidation.adapters.CountryAdapter.OnCountryClick
    public void onCountryClick(@NotNull String isoCode, @NotNull String countryCode) {
        Intrinsics.checkNotNullParameter(isoCode, "isoCode");
        Intrinsics.checkNotNullParameter(countryCode, "countryCode");
        Intent intent = getIntent();
        intent.putExtra(FragmentEnterPhoneNumber.EXTRA_COUNTRY_SELECTED, isoCode);
        intent.putExtra(FragmentEnterPhoneNumber.EXTRA_COUNTRY_NAME_SELECTED, countryCode);
        setResult(200, intent);
        finish();
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_choose_country);
        w();
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.KH_OPEN.getValue());
        FirebaseConstants firebaseConstants = FirebaseConstants.PREVIOUS_SCREEN_NAME;
        analyticsLog.setPreviousScreenName(firebaseConstants.getValue());
        FirebaseEventParams.ScreenName screenName = FirebaseEventParams.ScreenName.COUNTRY_SELECTION_SCREEN;
        analyticsLog.setScreenName(screenName.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        firebaseConstants.setValue(screenName.getValue());
        ((RecyclerView) _$_findCachedViewById(R.id.countryList)).setLayoutManager(new LinearLayoutManager(this));
        ViewModelFactory viewModelFactory = new ViewModelFactory(this);
        int i = R.id.toolbar;
        ((TextView) _$_findCachedViewById(i).findViewById(R.id.toolbar_title)).setText(getString(R.string.choose_a_country));
        ((TextView) _$_findCachedViewById(i).findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.phonevalidation.activities.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityChooseCountry.u(ActivityChooseCountry.this, view);
            }
        });
        final ActivityCountrySelectionViewModel activityCountrySelectionViewModel = (ActivityCountrySelectionViewModel) ViewModelProviders.of(this, viewModelFactory).get(ActivityCountrySelectionViewModel.class);
        activityCountrySelectionViewModel.getCountriesLiveData().observe(this, this);
        activityCountrySelectionViewModel.loadAllCountries();
        int i2 = R.id.mSearchCountry;
        ((SearchView) _$_findCachedViewById(i2)).setOnQueryTextListener(new SearchView.OnQueryTextListener() { // from class: com.coveiot.android.leonardo.onboarding.phonevalidation.activities.ActivityChooseCountry$onCreate$2
            @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
            public boolean onQueryTextChange(@NotNull String newText) {
                Intrinsics.checkNotNullParameter(newText, "newText");
                ActivityCountrySelectionViewModel.this.filterCountries(newText);
                return false;
            }

            @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
            public boolean onQueryTextSubmit(@NotNull String s) {
                Intrinsics.checkNotNullParameter(s, "s");
                return false;
            }
        });
        ((SearchView) _$_findCachedViewById(i2)).setOnCloseListener(new SearchView.OnCloseListener() { // from class: com.coveiot.android.leonardo.onboarding.phonevalidation.activities.b
            @Override // androidx.appcompat.widget.SearchView.OnCloseListener
            public final boolean onClose() {
                boolean v;
                v = ActivityChooseCountry.v(ActivityCountrySelectionViewModel.this);
                return v;
            }
        });
    }

    public final void w() {
        int i = R.id.mSearchCountry;
        View findViewById = ((SearchView) _$_findCachedViewById(i)).findViewById(R.id.search_src_text);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type android.widget.EditText");
        EditText editText = (EditText) findViewById;
        editText.setTextColor(getResources().getColor(R.color.main_text_color));
        editText.setHintTextColor(getResources().getColor(R.color.secondary_text_color));
        ((SearchView) _$_findCachedViewById(i)).setIconifiedByDefault(false);
        ((SearchView) _$_findCachedViewById(i)).setQueryHint(getResources().getString(R.string.search));
    }

    @Override // androidx.lifecycle.Observer
    public void onChanged(@Nullable ArrayList<CountryCodeModel> arrayList) {
        if (AppUtils.isEmpty(arrayList)) {
            return;
        }
        Intrinsics.checkNotNull(arrayList);
        Collections.sort(arrayList, new Comparator() { // from class: com.coveiot.android.leonardo.onboarding.phonevalidation.activities.c
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int t;
                t = ActivityChooseCountry.t((CountryCodeModel) obj, (CountryCodeModel) obj2);
                return t;
            }
        });
        ((RecyclerView) _$_findCachedViewById(R.id.countryList)).setAdapter(new CountryAdapter(arrayList, this, this));
    }
}
