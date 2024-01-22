package com.coveiot.android.leonardo.more.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.more.viewmodel.WorldClockViewModel;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.leonardo.utils.ViewModelFactory;
import com.coveiot.android.leonardo.utils.ViewUtilsKt;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.BottomSheetDialogOneButtonOneTitle;
import com.coveiot.android.theme.BottomSheetDialogTwoButtons;
import com.coveiot.android.weeklyreport.listeners.OnSuccessListener;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.WorldClockPrefData;
import com.coveiot.utils.utility.AppUtils;
import defpackage.WorldClockCountryAdapterNew;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityWorldClockCountrySelection extends BaseActivity implements Observer<ArrayList<WorldClockPrefData>>, WorldClockCountryAdapterNew.OnCountryClick, OnSuccessListener {
    public WorldClockViewModel mViewModelCountrySelection;
    @Nullable
    public BottomSheetDialogOneButtonOneTitle s;
    @Nullable
    public BottomSheetDialogTwoButtons t;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public WorldClockPrefData p = new WorldClockPrefData();
    @NotNull
    public final ArrayList<WorldClockPrefData> q = new ArrayList<>();
    @NotNull
    public ArrayList<WorldClockPrefData> r = new ArrayList<>();

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
            if (!AppUtils.isBluetoothEnabled(ActivityWorldClockCountrySelection.this)) {
                Toast.makeText(ActivityWorldClockCountrySelection.this, (int) R.string.bluetooth_off_message, 0).show();
            } else if (BleApiManager.getInstance(ActivityWorldClockCountrySelection.this).getBleApi().getConnectionStatus() != ConnectionStatus.CONNECTED) {
                Toast.makeText(ActivityWorldClockCountrySelection.this, (int) R.string.band_not_connected, 0).show();
            } else if (!AppUtils.isNetConnected(ActivityWorldClockCountrySelection.this)) {
                ActivityWorldClockCountrySelection activityWorldClockCountrySelection = ActivityWorldClockCountrySelection.this;
                Toast.makeText(activityWorldClockCountrySelection, activityWorldClockCountrySelection.getString(R.string.no_internet_connection), 0).show();
            } else if (!ActivityWorldClockCountrySelection.this.getSaveCountryList().isEmpty()) {
                ActivityWorldClockCountrySelection.this.getMViewModelCountrySelection().getRemovedCountryList().clear();
                ActivityWorldClockCountrySelection.this.getMViewModelCountrySelection().getRemovedCountryList().addAll(ActivityWorldClockCountrySelection.this.getUpdatedCountryList());
                ActivityWorldClockCountrySelection.this.getMViewModelCountrySelection().getRemovedCountryList().addAll(ActivityWorldClockCountrySelection.this.getSaveCountryList());
                ActivityWorldClockCountrySelection.this.showProgress();
                ActivityWorldClockCountrySelection.this.getMViewModelCountrySelection().sendToBle(true, ActivityWorldClockCountrySelection.this.getMViewModelCountrySelection().getRemovedCountryList());
            } else {
                ActivityWorldClockCountrySelection activityWorldClockCountrySelection2 = ActivityWorldClockCountrySelection.this;
                Toast.makeText(activityWorldClockCountrySelection2, activityWorldClockCountrySelection2.getResources().getString(R.string.please_select_country), 0).show();
            }
        }
    }

    public static final void A(ActivityWorldClockCountrySelection this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    public static final boolean B(ActivityWorldClockCountrySelection this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getMViewModelCountrySelection().loadAllCountries();
        return false;
    }

    public static final void C(ActivityWorldClockCountrySelection this$0, String message) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(message, "$message");
        this$0.dismissProgress();
        ViewUtilsKt.toast(this$0, message);
    }

    public static final void E(ActivityWorldClockCountrySelection this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intent intent = this$0.getIntent();
        intent.putExtra(AppConstants.EXTRA_COUNTRY_SELECTED.getValue(), this$0.q);
        this$0.setResult(200, intent);
        this$0.finish();
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = this$0.s;
        if (bottomSheetDialogOneButtonOneTitle != null) {
            bottomSheetDialogOneButtonOneTitle.dismiss();
        }
    }

    public static final void x(ActivityWorldClockCountrySelection this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((Button) this$0._$_findCachedViewById(R.id.save_city_btn)).performClick();
    }

    public static final void y(ActivityWorldClockCountrySelection this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = this$0.t;
        if (bottomSheetDialogTwoButtons != null) {
            bottomSheetDialogTwoButtons.dismiss();
        }
        this$0.finish();
    }

    public static final int z(WorldClockPrefData worldClockPrefData, WorldClockPrefData worldClockPrefData2) {
        String name = worldClockPrefData.getName();
        Intrinsics.checkNotNull(name);
        String name2 = worldClockPrefData2.getName();
        Intrinsics.checkNotNull(name2);
        return name.compareTo(name2);
    }

    public final void D() {
        int i = R.id.mSearchCountry;
        View findViewById = ((SearchView) _$_findCachedViewById(i)).findViewById(R.id.search_src_text);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type android.widget.EditText");
        EditText editText = (EditText) findViewById;
        editText.setTextColor(getResources().getColor(R.color.main_text_color));
        editText.setHintTextColor(getResources().getColor(R.color.secondary_text_color));
        ((SearchView) _$_findCachedViewById(i)).setIconifiedByDefault(false);
        ((SearchView) _$_findCachedViewById(i)).setQueryHint(getResources().getString(R.string.search));
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

    public final void confirmationDialog(boolean z) {
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons;
        if (z) {
            if (this.t == null) {
                String string = getString(R.string.confirmation);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.confirmation)");
                String string2 = getString(R.string.save_changes);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.save_changes)");
                BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons2 = new BottomSheetDialogTwoButtons(this, string, string2, false, 8, null);
                this.t = bottomSheetDialogTwoButtons2;
                String string3 = getString(R.string.save_changes_btn);
                Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.save_changes_btn)");
                bottomSheetDialogTwoButtons2.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.xj
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        ActivityWorldClockCountrySelection.x(ActivityWorldClockCountrySelection.this, view);
                    }
                });
                BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons3 = this.t;
                if (bottomSheetDialogTwoButtons3 != null) {
                    String string4 = getString(R.string.discard);
                    Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.discard)");
                    bottomSheetDialogTwoButtons3.setNegativeButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.wj
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            ActivityWorldClockCountrySelection.y(ActivityWorldClockCountrySelection.this, view);
                        }
                    });
                }
            }
            BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons4 = this.t;
            Boolean valueOf = bottomSheetDialogTwoButtons4 != null ? Boolean.valueOf(bottomSheetDialogTwoButtons4.isShowing()) : null;
            Intrinsics.checkNotNull(valueOf);
            if (valueOf.booleanValue() || (bottomSheetDialogTwoButtons = this.t) == null) {
                return;
            }
            bottomSheetDialogTwoButtons.show();
            return;
        }
        finish();
    }

    @Nullable
    public final BottomSheetDialogOneButtonOneTitle getBottomSheetDialogOneButtonOneTitle() {
        return this.s;
    }

    @NotNull
    public final WorldClockPrefData getClockPrefData() {
        return this.p;
    }

    @Nullable
    public final BottomSheetDialogTwoButtons getConfirmationDailog() {
        return this.t;
    }

    @NotNull
    public final WorldClockViewModel getMViewModelCountrySelection() {
        WorldClockViewModel worldClockViewModel = this.mViewModelCountrySelection;
        if (worldClockViewModel != null) {
            return worldClockViewModel;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mViewModelCountrySelection");
        return null;
    }

    @NotNull
    public final ArrayList<WorldClockPrefData> getSaveCountryList() {
        return this.q;
    }

    @NotNull
    public final ArrayList<WorldClockPrefData> getUpdatedCountryList() {
        return this.r;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (isFinishing()) {
            return;
        }
        confirmationDialog(getMViewModelCountrySelection().isChanged(this.p));
    }

    @Override // defpackage.WorldClockCountryAdapterNew.OnCountryClick
    public void onCountryClick(@NotNull WorldClockPrefData prefData) {
        Intrinsics.checkNotNullParameter(prefData, "prefData");
        if (isFinishing()) {
            return;
        }
        this.p.setCountry(prefData.getCountry());
        this.p.setName(prefData.getName());
        this.p.setLatitude(prefData.getLatitude());
        this.p.setLongitude(prefData.getLongitude());
        this.p.setId(prefData.getId());
        this.p.setTimeZoneName(prefData.getTimeZoneName());
        this.p.setTimeZoneOffset(prefData.getTimeZoneOffset());
        this.p.setAbbreviation(prefData.getAbbreviation());
        if (UserDataManager.getInstance(this).getWorldClocList() != null && UserDataManager.getInstance(this).getWorldClocList().size() > 0) {
            getMViewModelCountrySelection().getRemovedCountryList().clear();
            getMViewModelCountrySelection().getRemovedCountryList().addAll(UserDataManager.getInstance(this).getWorldClocList());
        }
        if (this.q.contains(prefData)) {
            this.q.remove(prefData);
        } else if (this.p.getName() == null || this.p.getCountry() == null || this.p.getTimeZoneName() == null) {
        } else {
            this.q.add(prefData);
        }
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_choose_country);
        D();
        ((RecyclerView) _$_findCachedViewById(R.id.countryList)).setLayoutManager(new LinearLayoutManager(this));
        setMViewModelCountrySelection((WorldClockViewModel) ViewModelProviders.of(this, new ViewModelFactory(this)).get(WorldClockViewModel.class));
        getMViewModelCountrySelection().setOnSuccessListener(this);
        getMViewModelCountrySelection().getCountriesLiveData().observe(this, this);
        showProgress();
        getMViewModelCountrySelection().getCountryListUrlFromRemoteConfig();
        if (getIntent().getExtras() != null) {
            ArrayList<WorldClockPrefData> arrayList = (ArrayList) getIntent().getSerializableExtra("updatedCountryList");
            Intrinsics.checkNotNull(arrayList);
            this.r = arrayList;
        }
        int i = R.id.toolbar;
        ((TextView) _$_findCachedViewById(i).findViewById(R.id.toolbar_title)).setText(getString(R.string.select_your_city));
        ((TextView) _$_findCachedViewById(i).findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.vj
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityWorldClockCountrySelection.A(ActivityWorldClockCountrySelection.this, view);
            }
        });
        Button save_city_btn = (Button) _$_findCachedViewById(R.id.save_city_btn);
        Intrinsics.checkNotNullExpressionValue(save_city_btn, "save_city_btn");
        ViewUtilsKt.setSafeOnClickListener(save_city_btn, new a());
        int i2 = R.id.mSearchCountry;
        ((SearchView) _$_findCachedViewById(i2)).setOnQueryTextListener(new SearchView.OnQueryTextListener() { // from class: com.coveiot.android.leonardo.more.activities.ActivityWorldClockCountrySelection$onCreate$3
            @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
            public boolean onQueryTextChange(@NotNull String newText) {
                Intrinsics.checkNotNullParameter(newText, "newText");
                ActivityWorldClockCountrySelection.this.getMViewModelCountrySelection().filterCountries(newText);
                return false;
            }

            @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
            public boolean onQueryTextSubmit(@NotNull String s) {
                Intrinsics.checkNotNullParameter(s, "s");
                return false;
            }
        });
        ((SearchView) _$_findCachedViewById(i2)).setOnCloseListener(new SearchView.OnCloseListener() { // from class: com.coveiot.android.leonardo.more.activities.yj
            @Override // androidx.appcompat.widget.SearchView.OnCloseListener
            public final boolean onClose() {
                boolean B;
                B = ActivityWorldClockCountrySelection.B(ActivityWorldClockCountrySelection.this);
                return B;
            }
        });
    }

    @Override // com.coveiot.android.weeklyreport.listeners.OnSuccessListener
    public void onDataFailure(@NotNull final String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        if (isFinishing()) {
            return;
        }
        runOnUiThread(new Runnable() { // from class: com.coveiot.android.leonardo.more.activities.zj
            @Override // java.lang.Runnable
            public final void run() {
                ActivityWorldClockCountrySelection.C(ActivityWorldClockCountrySelection.this, message);
            }
        });
    }

    @Override // com.coveiot.android.weeklyreport.listeners.OnSuccessListener
    public void onSuccess() {
        dismissProgress();
        successDialog();
    }

    public final void setBottomSheetDialogOneButtonOneTitle(@Nullable BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle) {
        this.s = bottomSheetDialogOneButtonOneTitle;
    }

    public final void setClockPrefData(@NotNull WorldClockPrefData worldClockPrefData) {
        Intrinsics.checkNotNullParameter(worldClockPrefData, "<set-?>");
        this.p = worldClockPrefData;
    }

    public final void setConfirmationDailog(@Nullable BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons) {
        this.t = bottomSheetDialogTwoButtons;
    }

    public final void setMViewModelCountrySelection(@NotNull WorldClockViewModel worldClockViewModel) {
        Intrinsics.checkNotNullParameter(worldClockViewModel, "<set-?>");
        this.mViewModelCountrySelection = worldClockViewModel;
    }

    public final void setUpdatedCountryList(@NotNull ArrayList<WorldClockPrefData> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.r = arrayList;
    }

    public final void successDialog() {
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle;
        if (this.s == null) {
            String string = getString(R.string.setting_saved_successfully);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.setting_saved_successfully)");
            BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle2 = new BottomSheetDialogOneButtonOneTitle(this, string);
            this.s = bottomSheetDialogOneButtonOneTitle2;
            String string2 = getString(R.string.ok);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.ok)");
            bottomSheetDialogOneButtonOneTitle2.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.uj
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityWorldClockCountrySelection.E(ActivityWorldClockCountrySelection.this, view);
                }
            });
        }
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle3 = this.s;
        Boolean valueOf = bottomSheetDialogOneButtonOneTitle3 != null ? Boolean.valueOf(bottomSheetDialogOneButtonOneTitle3.isShowing()) : null;
        Intrinsics.checkNotNull(valueOf);
        if (!valueOf.booleanValue() && (bottomSheetDialogOneButtonOneTitle = this.s) != null) {
            bottomSheetDialogOneButtonOneTitle.show();
        }
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle4 = this.s;
        if (bottomSheetDialogOneButtonOneTitle4 != null) {
            bottomSheetDialogOneButtonOneTitle4.setCancelable(false);
        }
    }

    @Override // androidx.lifecycle.Observer
    public void onChanged(@Nullable ArrayList<WorldClockPrefData> arrayList) {
        if (isFinishing() || AppUtils.isEmpty(arrayList)) {
            return;
        }
        dismissProgress();
        Intrinsics.checkNotNull(arrayList);
        Collections.sort(arrayList, new Comparator() { // from class: com.coveiot.android.leonardo.more.activities.ak
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int z;
                z = ActivityWorldClockCountrySelection.z((WorldClockPrefData) obj, (WorldClockPrefData) obj2);
                return z;
            }
        });
        ((RecyclerView) _$_findCachedViewById(R.id.countryList)).setAdapter(new WorldClockCountryAdapterNew(this, arrayList, this, this.r));
    }
}
