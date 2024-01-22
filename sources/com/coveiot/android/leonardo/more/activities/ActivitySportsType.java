package com.coveiot.android.leonardo.more.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.ActivityTypeModel;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.DeviceType;
import com.coveiot.android.boat.R;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.leonardo.listener.DialogListener;
import com.coveiot.android.leonardo.model.ShowHideTypeModel;
import com.coveiot.android.leonardo.more.adapters.ShowHideRecyclerAdap;
import com.coveiot.android.leonardo.more.models.ShowHideListModel;
import com.coveiot.android.leonardo.more.viewmodel.ActivitySportsTypeViewModel;
import com.coveiot.android.leonardo.utils.PayUtils;
import com.coveiot.android.leonardo.utils.ViewModelFactory;
import com.coveiot.android.leonardo.utils.ViewUtilsKt;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.BottomSheetDialogOneButtonOneTitle;
import com.coveiot.android.theme.BottomSheetDialogTwoButtons;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.ShowHideData;
import com.coveiot.utils.utility.AppUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivitySportsType extends BaseActivity implements ShowHideRecyclerAdap.OnItemClickListenerHideShow, DialogListener {
    public TextView p;
    public ActivitySportsTypeViewModel q;
    @Nullable
    public BottomSheetDialogOneButtonOneTitle t;
    @Nullable
    public BottomSheetDialogOneButtonOneTitle u;
    @Nullable
    public BottomSheetDialogTwoButtons v;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public ArrayList<ShowHideTypeModel> r = new ArrayList<>();
    @NotNull
    public ArrayList<ShowHideTypeModel> s = new ArrayList<>();

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
            if (BleApiManager.getInstance(ActivitySportsType.this).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
                if (AppUtils.isNetConnected(ActivitySportsType.this)) {
                    ActivitySportsType.this.showProgress();
                    ArrayList<ActivityTypeModel> showMenuList = ActivitySportsType.this.getShowMenuList();
                    if (BleApiManager.getInstance(ActivitySportsType.this).getDeviceType() == DeviceType.IDO_CONNECT) {
                        showMenuList.addAll(ActivitySportsType.this.getHideMenuList());
                    }
                    if (showMenuList.size() > 0) {
                        ActivitySportsTypeViewModel activitySportsTypeViewModel = ActivitySportsType.this.q;
                        if (activitySportsTypeViewModel == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                            activitySportsTypeViewModel = null;
                        }
                        activitySportsTypeViewModel.sendShowListToWatch(showMenuList, ActivitySportsType.this.getShowSportsTypeList());
                        return;
                    }
                    ActivitySportsType.this.dismissProgress();
                    ActivitySportsType activitySportsType = ActivitySportsType.this;
                    Toast.makeText(activitySportsType, activitySportsType.getResources().getString(R.string.select_menu), 0).show();
                    return;
                }
                ActivitySportsType activitySportsType2 = ActivitySportsType.this;
                Toast.makeText(activitySportsType2, activitySportsType2.getResources().getString(R.string.please_check_network), 1).show();
                return;
            }
            Toast.makeText(ActivitySportsType.this, (int) R.string.band_not_connected, 1).show();
        }
    }

    public static final void A(ActivitySportsType this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = this$0.v;
        if (bottomSheetDialogTwoButtons != null) {
            bottomSheetDialogTwoButtons.dismiss();
        }
        this$0.finish();
    }

    public static final void B(ActivitySportsType this$0, ShowHideListModel showHideListModel) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (showHideListModel != null) {
            this$0.s.addAll(showHideListModel.getShowList());
            this$0.r.addAll(showHideListModel.getHideList());
            ((RecyclerView) this$0._$_findCachedViewById(R.id.hide_recycler)).setAdapter(new ShowHideRecyclerAdap(this$0, this$0.r, this$0));
            ((RecyclerView) this$0._$_findCachedViewById(R.id.show_recycler)).setAdapter(new ShowHideRecyclerAdap(this$0, this$0.s, this$0));
            ActivitySportsTypeViewModel activitySportsTypeViewModel = this$0.q;
            if (activitySportsTypeViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                activitySportsTypeViewModel = null;
            }
            activitySportsTypeViewModel.getSupportedListLiveData().removeObservers(this$0);
            this$0.dismissProgress();
        }
    }

    public static final void C(ActivitySportsType this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = this$0.t;
        if (bottomSheetDialogOneButtonOneTitle != null) {
            bottomSheetDialogOneButtonOneTitle.dismiss();
        }
        this$0.finish();
    }

    public static final void D(ActivitySportsType this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = this$0.u;
        if (bottomSheetDialogOneButtonOneTitle != null) {
            bottomSheetDialogOneButtonOneTitle.dismiss();
        }
        this$0.finish();
    }

    public static final void x(ActivitySportsType this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    public static final void y(ActivitySportsType this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        TextView textView = this$0.p;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("saveText");
            textView = null;
        }
        textView.performClick();
    }

    public static final void z(ActivitySportsType this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        TextView textView = this$0.p;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("saveText");
            textView = null;
        }
        textView.performClick();
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

    public final void buttonVisible() {
        TextView textView = null;
        if (isChanged()) {
            TextView textView2 = this.p;
            if (textView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("saveText");
            } else {
                textView = textView2;
            }
            textView.setAlpha(1.0f);
            ((Button) _$_findCachedViewById(R.id.btn_ok)).setEnabled(true);
            return;
        }
        TextView textView3 = this.p;
        if (textView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("saveText");
        } else {
            textView = textView3;
        }
        textView.setAlpha(0.5f);
        ((Button) _$_findCachedViewById(R.id.btn_ok)).setEnabled(false);
    }

    @Nullable
    public final BottomSheetDialogOneButtonOneTitle getBottomSheetDialogOneButtonOneTitleError() {
        return this.t;
    }

    @Nullable
    public final BottomSheetDialogOneButtonOneTitle getBottomSheetDialogOneButtonOneTitleSuccess() {
        return this.u;
    }

    @Nullable
    public final BottomSheetDialogTwoButtons getConfirmationDialog() {
        return this.v;
    }

    @NotNull
    public final ArrayList<ActivityTypeModel> getHideMenuList() {
        ArrayList<ActivityTypeModel> arrayList = new ArrayList<>();
        Iterator<ShowHideTypeModel> it = this.r.iterator();
        while (it.hasNext()) {
            ShowHideTypeModel next = it.next();
            DeviceUtils.Companion companion = DeviceUtils.Companion;
            if (companion.isIDODevice(this)) {
                PayUtils payUtils = PayUtils.INSTANCE;
                String typeName = next.getTypeName();
                Intrinsics.checkNotNull(typeName);
                ActivityTypeModel sportsTypeValueIDO = payUtils.getSportsTypeValueIDO(typeName, this);
                if (sportsTypeValueIDO != null) {
                    arrayList.add(sportsTypeValueIDO);
                }
            } else if (companion.isTouchELXDevice(this)) {
                PayUtils payUtils2 = PayUtils.INSTANCE;
                String typeName2 = next.getTypeName();
                Intrinsics.checkNotNull(typeName2);
                ActivityTypeModel sportsTypeValueTouch = payUtils2.getSportsTypeValueTouch(typeName2, this);
                if (sportsTypeValueTouch != null) {
                    arrayList.add(sportsTypeValueTouch);
                }
            }
        }
        return arrayList;
    }

    @NotNull
    public final ArrayList<ShowHideTypeModel> getHideSportsTypeList() {
        return this.r;
    }

    @NotNull
    public final ArrayList<ActivityTypeModel> getShowMenuList() {
        ArrayList<ActivityTypeModel> arrayList = new ArrayList<>();
        Iterator<ShowHideTypeModel> it = this.s.iterator();
        while (it.hasNext()) {
            ShowHideTypeModel next = it.next();
            DeviceUtils.Companion companion = DeviceUtils.Companion;
            if (companion.isIDODevice(this)) {
                PayUtils payUtils = PayUtils.INSTANCE;
                String typeName = next.getTypeName();
                Intrinsics.checkNotNull(typeName);
                ActivityTypeModel sportsTypeValueIDO = payUtils.getSportsTypeValueIDO(typeName, this);
                if (sportsTypeValueIDO != null) {
                    arrayList.add(sportsTypeValueIDO);
                }
            } else if (companion.isTouchELXDevice(this)) {
                PayUtils payUtils2 = PayUtils.INSTANCE;
                String typeName2 = next.getTypeName();
                Intrinsics.checkNotNull(typeName2);
                ActivityTypeModel sportsTypeValueTouch = payUtils2.getSportsTypeValueTouch(typeName2, this);
                if (sportsTypeValueTouch != null) {
                    arrayList.add(sportsTypeValueTouch);
                }
            }
        }
        return arrayList;
    }

    @NotNull
    public final ArrayList<ShowHideTypeModel> getShowSportsTypeList() {
        return this.s;
    }

    public final void initToolbar() {
        ((TextView) findViewById(R.id.toolbar_title)).setText(getString(R.string.sports_type));
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.xi
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivitySportsType.x(ActivitySportsType.this, view);
            }
        });
        View findViewById = findViewById(R.id.save);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById<TextView>(R.id.save)");
        TextView textView = (TextView) findViewById;
        this.p = textView;
        TextView textView2 = null;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("saveText");
            textView = null;
        }
        textView.setText(getString(R.string.save_camel));
        TextView textView3 = this.p;
        if (textView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("saveText");
            textView3 = null;
        }
        textView3.setAlpha(0.5f);
        TextView textView4 = this.p;
        if (textView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("saveText");
        } else {
            textView2 = textView4;
        }
        textView2.setVisibility(8);
        int i = R.id.btn_ok;
        ((Button) _$_findCachedViewById(i)).setEnabled(false);
        ((Button) _$_findCachedViewById(i)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.wi
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivitySportsType.y(ActivitySportsType.this, view);
            }
        });
    }

    public final boolean isChanged() {
        List<ShowHideData> sportsTypeList = UserDataManager.getInstance(this).getSportsTypeList();
        if (this.s.size() > sportsTypeList.size() || this.s.size() < sportsTypeList.size()) {
            return true;
        }
        ArrayList arrayList = new ArrayList();
        for (ShowHideData showHideData : sportsTypeList) {
            String typeName = showHideData.getTypeName();
            Intrinsics.checkNotNull(typeName);
            arrayList.add(typeName);
        }
        Iterator<ShowHideTypeModel> it = this.s.iterator();
        boolean z = false;
        while (it.hasNext()) {
            if (!CollectionsKt___CollectionsKt.contains(arrayList, it.next().getTypeName())) {
                z = true;
            }
        }
        return z;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons;
        if (isChanged()) {
            if (this.v == null) {
                String string = getString(R.string.confirmation);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.confirmation)");
                String string2 = getString(R.string.save_changes);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.save_changes)");
                BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons2 = new BottomSheetDialogTwoButtons(this, string, string2, false, 8, null);
                this.v = bottomSheetDialogTwoButtons2;
                String string3 = getString(R.string.save_changes_btn);
                Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.save_changes_btn)");
                bottomSheetDialogTwoButtons2.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.yi
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        ActivitySportsType.z(ActivitySportsType.this, view);
                    }
                });
                BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons3 = this.v;
                if (bottomSheetDialogTwoButtons3 != null) {
                    String string4 = getString(R.string.discard);
                    Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.discard)");
                    bottomSheetDialogTwoButtons3.setNegativeButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.ui
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            ActivitySportsType.A(ActivitySportsType.this, view);
                        }
                    });
                }
            }
            BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons4 = this.v;
            Boolean valueOf = bottomSheetDialogTwoButtons4 != null ? Boolean.valueOf(bottomSheetDialogTwoButtons4.isShowing()) : null;
            Intrinsics.checkNotNull(valueOf);
            if (valueOf.booleanValue() || (bottomSheetDialogTwoButtons = this.v) == null) {
                return;
            }
            bottomSheetDialogTwoButtons.show();
            return;
        }
        finish();
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.sports_type_activity);
        ActivitySportsTypeViewModel activitySportsTypeViewModel = (ActivitySportsTypeViewModel) ViewModelProviders.of(this, new ViewModelFactory(this)).get(ActivitySportsTypeViewModel.class);
        this.q = activitySportsTypeViewModel;
        TextView textView = null;
        if (activitySportsTypeViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            activitySportsTypeViewModel = null;
        }
        activitySportsTypeViewModel.setDialogListener(this);
        showProgress();
        initToolbar();
        ActivitySportsTypeViewModel activitySportsTypeViewModel2 = this.q;
        if (activitySportsTypeViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            activitySportsTypeViewModel2 = null;
        }
        activitySportsTypeViewModel2.getSupportedListFromWatch();
        ActivitySportsTypeViewModel activitySportsTypeViewModel3 = this.q;
        if (activitySportsTypeViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            activitySportsTypeViewModel3 = null;
        }
        activitySportsTypeViewModel3.getSupportedListLiveData().observe(this, new Observer() { // from class: com.coveiot.android.leonardo.more.activities.aj
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                ActivitySportsType.B(ActivitySportsType.this, (ShowHideListModel) obj);
            }
        });
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.hide_recycler);
        if (recyclerView != null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
        RecyclerView recyclerView2 = (RecyclerView) _$_findCachedViewById(R.id.show_recycler);
        if (recyclerView2 != null) {
            recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        }
        TextView textView2 = this.p;
        if (textView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("saveText");
        } else {
            textView = textView2;
        }
        ViewUtilsKt.setSafeOnClickListener(textView, new a());
    }

    @Override // com.coveiot.android.leonardo.listener.DialogListener
    public void onDismiss() {
        dismissProgress();
    }

    @Override // com.coveiot.android.leonardo.more.adapters.ShowHideRecyclerAdap.OnItemClickListenerHideShow
    public void onHideShow(@NotNull ShowHideTypeModel showHideTypeModel, int i) {
        Intrinsics.checkNotNullParameter(showHideTypeModel, "showHideTypeModel");
        if (kotlin.text.m.equals(showHideTypeModel.getShowOrHideText(), getResources().getString(R.string.show), true)) {
            if (this.s.size() < 5) {
                Toast.makeText(this, getResources().getString(R.string.sports_type_selection_info), 0).show();
            } else {
                this.s.remove(i);
                RecyclerView.Adapter adapter = ((RecyclerView) _$_findCachedViewById(R.id.show_recycler)).getAdapter();
                if (adapter != null) {
                    adapter.notifyDataSetChanged();
                }
                ArrayList<ShowHideTypeModel> arrayList = this.r;
                arrayList.add(arrayList.size(), showHideTypeModel);
                RecyclerView.Adapter adapter2 = ((RecyclerView) _$_findCachedViewById(R.id.hide_recycler)).getAdapter();
                if (adapter2 != null) {
                    adapter2.notifyDataSetChanged();
                }
            }
        } else {
            DeviceUtils.Companion companion = DeviceUtils.Companion;
            if (companion.isIDODevice(this)) {
                if (this.s.size() >= 24) {
                    Toast.makeText(this, getResources().getString(R.string.sports_type_selection_info2), 0).show();
                    return;
                }
            } else if (companion.isTouchELXDevice(this) && this.s.size() >= 16) {
                Toast.makeText(this, getResources().getString(R.string.sports_type_selection_info3), 0).show();
                return;
            }
            this.r.remove(i);
            RecyclerView.Adapter adapter3 = ((RecyclerView) _$_findCachedViewById(R.id.hide_recycler)).getAdapter();
            if (adapter3 != null) {
                adapter3.notifyDataSetChanged();
            }
            this.s.add(0, showHideTypeModel);
            RecyclerView.Adapter adapter4 = ((RecyclerView) _$_findCachedViewById(R.id.show_recycler)).getAdapter();
            if (adapter4 != null) {
                adapter4.notifyDataSetChanged();
            }
        }
        buttonVisible();
    }

    @Override // com.coveiot.android.leonardo.listener.DialogListener
    public void onShowProgressDialog() {
        String string = getString(R.string.please_wait);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.please_wait)");
        showProgresswithMsg(string);
    }

    public final void setBottomSheetDialogOneButtonOneTitleError(@Nullable BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle) {
        this.t = bottomSheetDialogOneButtonOneTitle;
    }

    public final void setBottomSheetDialogOneButtonOneTitleSuccess(@Nullable BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle) {
        this.u = bottomSheetDialogOneButtonOneTitle;
    }

    public final void setConfirmationDialog(@Nullable BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons) {
        this.v = bottomSheetDialogTwoButtons;
    }

    public final void setHideSportsTypeList(@NotNull ArrayList<ShowHideTypeModel> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.r = arrayList;
    }

    public final void setShowSportsTypeList(@NotNull ArrayList<ShowHideTypeModel> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.s = arrayList;
    }

    @Override // com.coveiot.android.leonardo.listener.DialogListener
    public void showErrorDialog() {
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle;
        if (this.t == null) {
            String string = getString(R.string.setting_couldnot_save);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.setting_couldnot_save)");
            BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle2 = new BottomSheetDialogOneButtonOneTitle(this, string);
            this.t = bottomSheetDialogOneButtonOneTitle2;
            String string2 = getString(R.string.ok);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.ok)");
            bottomSheetDialogOneButtonOneTitle2.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.zi
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivitySportsType.C(ActivitySportsType.this, view);
                }
            });
        }
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle3 = this.t;
        Boolean valueOf = bottomSheetDialogOneButtonOneTitle3 != null ? Boolean.valueOf(bottomSheetDialogOneButtonOneTitle3.isShowing()) : null;
        Intrinsics.checkNotNull(valueOf);
        if (valueOf.booleanValue() || (bottomSheetDialogOneButtonOneTitle = this.t) == null) {
            return;
        }
        bottomSheetDialogOneButtonOneTitle.show();
    }

    @Override // com.coveiot.android.leonardo.listener.DialogListener
    public void showSuccessDialog() {
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle;
        if (this.u == null) {
            String string = getString(R.string.success_message);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.success_message)");
            BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle2 = new BottomSheetDialogOneButtonOneTitle(this, string);
            this.u = bottomSheetDialogOneButtonOneTitle2;
            String string2 = getString(R.string.ok);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(\n             …R.string.ok\n            )");
            bottomSheetDialogOneButtonOneTitle2.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.vi
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivitySportsType.D(ActivitySportsType.this, view);
                }
            });
        }
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle3 = this.u;
        Boolean valueOf = bottomSheetDialogOneButtonOneTitle3 != null ? Boolean.valueOf(bottomSheetDialogOneButtonOneTitle3.isShowing()) : null;
        Intrinsics.checkNotNull(valueOf);
        if (valueOf.booleanValue() || (bottomSheetDialogOneButtonOneTitle = this.u) == null) {
            return;
        }
        bottomSheetDialogOneButtonOneTitle.show();
    }
}
