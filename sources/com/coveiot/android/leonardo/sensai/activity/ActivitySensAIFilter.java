package com.coveiot.android.leonardo.sensai.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
import com.coveiot.android.boat.databinding.ActivitySensaiFilterBinding;
import com.coveiot.android.leonardo.sensai.adapter.SensAIFilterAdapter;
import com.coveiot.android.leonardo.sensai.adapter.SensAIFilterOptionsAdapter;
import com.coveiot.android.leonardo.sensai.fragment.FragmentSensAIFilterOptions;
import com.coveiot.android.leonardo.sensai.model.SensAIFilter;
import com.coveiot.android.leonardo.sensai.model.SensAIFilterOptions;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.theme.BaseActivity;
import com.jstyle.blesdk1860.constant.BleConst;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivitySensAIFilter extends BaseActivity implements SensAIFilterAdapter.OnItemClickListener, SensAIFilterOptionsAdapter.FilterSelectionListener {
    public ActivitySensaiFilterBinding p;
    public SensAIFilterAdapter q;
    @Nullable
    public ArrayList<String> v;
    public boolean w;
    @Nullable
    public SensAIFilter x;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public List<SensAIFilter> r = new ArrayList();
    @NotNull
    public List<SensAIFilterOptions> s = new ArrayList();
    @NotNull
    public List<SensAIFilterOptions> t = new ArrayList();
    @NotNull
    public List<SensAIFilterOptions> u = new ArrayList();

    public static final void t(ActivitySensAIFilter this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    public static final void v(ActivitySensAIFilter this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intent intent = this$0.getIntent();
        intent.putExtra("extra_selected_filter_options", this$0.v);
        this$0.setResult(-1, intent);
        this$0.finish();
    }

    public static final void w(ActivitySensAIFilter this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ArrayList<String> arrayList = this$0.v;
        if (arrayList != null) {
            arrayList.clear();
        }
        this$0.x = this$0.r.get(0);
        this$0.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, FragmentSensAIFilterOptions.Companion.newInstance(this$0.x, this$0.v)).commit();
        SensAIFilterAdapter sensAIFilterAdapter = this$0.q;
        ActivitySensaiFilterBinding activitySensaiFilterBinding = null;
        if (sensAIFilterAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sensAIFilterAdapter");
            sensAIFilterAdapter = null;
        }
        sensAIFilterAdapter.setSelectedIndex(0);
        SensAIFilterAdapter sensAIFilterAdapter2 = this$0.q;
        if (sensAIFilterAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sensAIFilterAdapter");
            sensAIFilterAdapter2 = null;
        }
        sensAIFilterAdapter2.notifyDataSetChanged();
        ActivitySensaiFilterBinding activitySensaiFilterBinding2 = this$0.p;
        if (activitySensaiFilterBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activitySensaiFilterBinding = activitySensaiFilterBinding2;
        }
        TextView textView = activitySensaiFilterBinding.tvFiltersApplied;
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Locale locale = Locale.ENGLISH;
        String string = this$0.getString(R.string.filters_selected);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.filters_selected)");
        String format = String.format(locale, string, Arrays.copyOf(new Object[]{BleConst.GetDeviceTime}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
        textView.setText(format);
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

    @Nullable
    public final SensAIFilter getCurrentFilter() {
        return this.x;
    }

    @NotNull
    public final List<SensAIFilter> getFilterList() {
        return this.r;
    }

    @NotNull
    public final List<SensAIFilterOptions> getSensAIFilterOptionBowlingTypeList() {
        return this.u;
    }

    @NotNull
    public final List<SensAIFilterOptions> getSensAIFilterOptionHandList() {
        return this.t;
    }

    @NotNull
    public final List<SensAIFilterOptions> getSensAIFilterOptionTypeList() {
        return this.s;
    }

    public final void initToolbar() {
        ActivitySensaiFilterBinding activitySensaiFilterBinding = this.p;
        ActivitySensaiFilterBinding activitySensaiFilterBinding2 = null;
        if (activitySensaiFilterBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySensaiFilterBinding = null;
        }
        TextView textView = (TextView) activitySensaiFilterBinding.toolbar.findViewById(R.id.toolbar_title);
        ActivitySensaiFilterBinding activitySensaiFilterBinding3 = this.p;
        if (activitySensaiFilterBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activitySensaiFilterBinding2 = activitySensaiFilterBinding3;
        }
        textView.setText(getString(R.string.filters));
        ((TextView) activitySensaiFilterBinding2.toolbar.findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.sensai.activity.m
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivitySensAIFilter.t(ActivitySensAIFilter.this, view);
            }
        });
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        ActivitySensaiFilterBinding inflate = ActivitySensaiFilterBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
        this.p = inflate;
        ActivitySensaiFilterBinding activitySensaiFilterBinding = null;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        setContentView(inflate.getRoot());
        initToolbar();
        if (getIntent().hasExtra("extra_selected_filter_options")) {
            Bundle extras = getIntent().getExtras();
            Serializable serializable = extras != null ? extras.getSerializable("extra_selected_filter_options") : null;
            Intrinsics.checkNotNull(serializable, "null cannot be cast to non-null type java.util.ArrayList<kotlin.String>");
            this.v = (ArrayList) serializable;
        }
        this.s.add(new SensAIFilterOptions(getString(R.string.batting)));
        this.s.add(new SensAIFilterOptions(getString(R.string.bowling)));
        this.t.add(new SensAIFilterOptions(getString(R.string.right_hand)));
        this.t.add(new SensAIFilterOptions(getString(R.string.left_hand)));
        this.u.add(new SensAIFilterOptions(getString(R.string.spin)));
        this.u.add(new SensAIFilterOptions(getString(R.string.medium_pace)));
        this.u.add(new SensAIFilterOptions(getString(R.string.fast)));
        this.r.add(new SensAIFilter(AppConstants.CRICKET_FILTER_TYPE.getValue(), this.s));
        this.r.add(new SensAIFilter(AppConstants.CRICKET_FILTER_HAND.getValue(), this.t));
        this.r.add(new SensAIFilter(AppConstants.CRICKET_FILTER_BOWLING_TYPE.getValue(), this.u));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        ActivitySensaiFilterBinding activitySensaiFilterBinding2 = this.p;
        if (activitySensaiFilterBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySensaiFilterBinding2 = null;
        }
        activitySensaiFilterBinding2.rcvFilterTypes.setLayoutManager(linearLayoutManager);
        this.q = new SensAIFilterAdapter(this, this.r, this);
        ActivitySensaiFilterBinding activitySensaiFilterBinding3 = this.p;
        if (activitySensaiFilterBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySensaiFilterBinding3 = null;
        }
        RecyclerView recyclerView = activitySensaiFilterBinding3.rcvFilterTypes;
        SensAIFilterAdapter sensAIFilterAdapter = this.q;
        if (sensAIFilterAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sensAIFilterAdapter");
            sensAIFilterAdapter = null;
        }
        recyclerView.setAdapter(sensAIFilterAdapter);
        SensAIFilter sensAIFilter = this.r.get(0);
        this.x = sensAIFilter;
        u(sensAIFilter);
        ActivitySensaiFilterBinding activitySensaiFilterBinding4 = this.p;
        if (activitySensaiFilterBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySensaiFilterBinding4 = null;
        }
        activitySensaiFilterBinding4.btnApply.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.sensai.activity.l
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivitySensAIFilter.v(ActivitySensAIFilter.this, view);
            }
        });
        ActivitySensaiFilterBinding activitySensaiFilterBinding5 = this.p;
        if (activitySensaiFilterBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySensaiFilterBinding5 = null;
        }
        activitySensaiFilterBinding5.clearFilters.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.sensai.activity.k
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivitySensAIFilter.w(ActivitySensAIFilter.this, view);
            }
        });
        ArrayList<String> arrayList = this.v;
        if (arrayList != null) {
            if (arrayList != null && arrayList.size() == 1) {
                ActivitySensaiFilterBinding activitySensaiFilterBinding6 = this.p;
                if (activitySensaiFilterBinding6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activitySensaiFilterBinding6 = null;
                }
                TextView textView = activitySensaiFilterBinding6.tvFiltersApplied;
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                Locale locale = Locale.ENGLISH;
                String string = getString(R.string.filter_selected);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.filter_selected)");
                Object[] objArr = new Object[1];
                StringBuilder sb = new StringBuilder();
                sb.append(' ');
                ArrayList<String> arrayList2 = this.v;
                sb.append(arrayList2 != null ? Integer.valueOf(arrayList2.size()) : null);
                objArr[0] = sb.toString();
                String format = String.format(locale, string, Arrays.copyOf(objArr, 1));
                Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
                textView.setText(format);
            } else {
                ActivitySensaiFilterBinding activitySensaiFilterBinding7 = this.p;
                if (activitySensaiFilterBinding7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activitySensaiFilterBinding7 = null;
                }
                TextView textView2 = activitySensaiFilterBinding7.tvFiltersApplied;
                StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
                Locale locale2 = Locale.ENGLISH;
                String string2 = getString(R.string.filters_selected);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.filters_selected)");
                Object[] objArr2 = new Object[1];
                StringBuilder sb2 = new StringBuilder();
                sb2.append(' ');
                ArrayList<String> arrayList3 = this.v;
                sb2.append(arrayList3 != null ? Integer.valueOf(arrayList3.size()) : null);
                objArr2[0] = sb2.toString();
                String format2 = String.format(locale2, string2, Arrays.copyOf(objArr2, 1));
                Intrinsics.checkNotNullExpressionValue(format2, "format(locale, format, *args)");
                textView2.setText(format2);
            }
            ArrayList<String> arrayList4 = this.v;
            Integer valueOf = arrayList4 != null ? Integer.valueOf(arrayList4.size()) : null;
            Intrinsics.checkNotNull(valueOf);
            if (valueOf.intValue() > 0) {
                ActivitySensaiFilterBinding activitySensaiFilterBinding8 = this.p;
                if (activitySensaiFilterBinding8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activitySensaiFilterBinding = activitySensaiFilterBinding8;
                }
                activitySensaiFilterBinding.btnApply.setEnabled(true);
                this.w = true;
                return;
            }
            return;
        }
        ActivitySensaiFilterBinding activitySensaiFilterBinding9 = this.p;
        if (activitySensaiFilterBinding9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySensaiFilterBinding9 = null;
        }
        TextView textView3 = activitySensaiFilterBinding9.tvFiltersApplied;
        StringCompanionObject stringCompanionObject3 = StringCompanionObject.INSTANCE;
        Locale locale3 = Locale.ENGLISH;
        String string3 = getString(R.string.filters_selected);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.filters_selected)");
        String format3 = String.format(locale3, string3, Arrays.copyOf(new Object[]{BleConst.GetDeviceTime}, 1));
        Intrinsics.checkNotNullExpressionValue(format3, "format(locale, format, *args)");
        textView3.setText(format3);
        ActivitySensaiFilterBinding activitySensaiFilterBinding10 = this.p;
        if (activitySensaiFilterBinding10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activitySensaiFilterBinding = activitySensaiFilterBinding10;
        }
        activitySensaiFilterBinding.btnApply.setEnabled(false);
        this.w = false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0059, code lost:
        if (r10.contains(getString(com.coveiot.android.boat.R.string.fast)) != false) goto L17;
     */
    @Override // com.coveiot.android.leonardo.sensai.adapter.SensAIFilterOptionsAdapter.FilterSelectionListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onFilterOption(@org.jetbrains.annotations.NotNull java.lang.String r10, boolean r11) {
        /*
            Method dump skipped, instructions count: 437
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.leonardo.sensai.activity.ActivitySensAIFilter.onFilterOption(java.lang.String, boolean):void");
    }

    @Override // com.coveiot.android.leonardo.sensai.adapter.SensAIFilterAdapter.OnItemClickListener
    public void onItemClicked(int i) {
        SensAIFilter sensAIFilter = this.r.get(i);
        this.x = sensAIFilter;
        u(sensAIFilter);
    }

    public final void setCurrentFilter(@Nullable SensAIFilter sensAIFilter) {
        this.x = sensAIFilter;
    }

    public final void setFilterList(@NotNull List<SensAIFilter> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.r = list;
    }

    public final void setSensAIFilterOptionBowlingTypeList(@NotNull List<SensAIFilterOptions> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.u = list;
    }

    public final void setSensAIFilterOptionHandList(@NotNull List<SensAIFilterOptions> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.t = list;
    }

    public final void setSensAIFilterOptionTypeList(@NotNull List<SensAIFilterOptions> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.s = list;
    }

    public final void u(SensAIFilter sensAIFilter) {
        this.x = sensAIFilter;
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, FragmentSensAIFilterOptions.Companion.newInstance(this.x, this.v)).commit();
    }
}
