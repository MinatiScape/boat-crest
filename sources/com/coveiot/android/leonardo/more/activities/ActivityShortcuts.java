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
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.boat.R;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.leonardo.listener.DialogListener;
import com.coveiot.android.leonardo.model.ShowHideTypeModel;
import com.coveiot.android.leonardo.more.adapters.ShowHideRecyclerAdap;
import com.coveiot.android.leonardo.more.models.ShowHideListModel;
import com.coveiot.android.leonardo.more.viewmodel.ActivityShortcutViewModel;
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
public final class ActivityShortcuts extends BaseActivity implements ShowHideRecyclerAdap.OnItemClickListenerHideShow, DialogListener {
    public BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle;
    public BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitleError;
    public TextView p;
    public ActivityShortcutViewModel q;
    @Nullable
    public BottomSheetDialogTwoButtons t;
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
            if (BleApiManager.getInstance(ActivityShortcuts.this).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
                if (AppUtils.isNetConnected(ActivityShortcuts.this)) {
                    ActivityShortcuts.this.showProgress();
                    ArrayList<Integer> showMenuList = ActivityShortcuts.this.getShowMenuList();
                    if (showMenuList.size() > 0) {
                        ActivityShortcutViewModel activityShortcutViewModel = ActivityShortcuts.this.q;
                        if (activityShortcutViewModel == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                            activityShortcutViewModel = null;
                        }
                        activityShortcutViewModel.sendShowListToWatch(showMenuList, ActivityShortcuts.this.getShowShortsCutsList());
                        return;
                    }
                    ActivityShortcuts.this.dismissProgress();
                    ActivityShortcuts activityShortcuts = ActivityShortcuts.this;
                    Toast.makeText(activityShortcuts, activityShortcuts.getResources().getString(R.string.select_menu), 0).show();
                    return;
                }
                ActivityShortcuts activityShortcuts2 = ActivityShortcuts.this;
                Toast.makeText(activityShortcuts2, activityShortcuts2.getResources().getString(R.string.please_check_network), 1).show();
                return;
            }
            Toast.makeText(ActivityShortcuts.this, (int) R.string.band_not_connected, 1).show();
        }
    }

    public static final void A(ActivityShortcuts this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        TextView textView = this$0.p;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("saveText");
            textView = null;
        }
        textView.performClick();
    }

    public static final void B(ActivityShortcuts this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        TextView textView = this$0.p;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("saveText");
            textView = null;
        }
        textView.performClick();
    }

    public static final void C(ActivityShortcuts this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = this$0.t;
        if (bottomSheetDialogTwoButtons != null) {
            bottomSheetDialogTwoButtons.dismiss();
        }
        this$0.finish();
    }

    public static final void D(ActivityShortcuts this$0, ShowHideListModel showHideListModel) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (showHideListModel != null) {
            this$0.s.addAll(showHideListModel.getShowList());
            this$0.r.addAll(showHideListModel.getHideList());
            ((RecyclerView) this$0._$_findCachedViewById(R.id.hide_recycler)).setAdapter(new ShowHideRecyclerAdap(this$0, this$0.r, this$0));
            ((RecyclerView) this$0._$_findCachedViewById(R.id.show_recycler)).setAdapter(new ShowHideRecyclerAdap(this$0, this$0.s, this$0));
            ActivityShortcutViewModel activityShortcutViewModel = this$0.q;
            if (activityShortcutViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                activityShortcutViewModel = null;
            }
            activityShortcutViewModel.getSupportedListLiveData().removeObservers(this$0);
            this$0.dismissProgress();
        }
    }

    public static final void x(ActivityShortcuts this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getBottomSheetDialogOneButtonOneTitle().dismiss();
        this$0.finish();
    }

    public static final void y(ActivityShortcuts this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getBottomSheetDialogOneButtonOneTitleError().dismiss();
        this$0.finish();
    }

    public static final void z(ActivityShortcuts this$0, View view) {
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

    @NotNull
    public final BottomSheetDialogOneButtonOneTitle getBottomSheetDialogOneButtonOneTitle() {
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = this.bottomSheetDialogOneButtonOneTitle;
        if (bottomSheetDialogOneButtonOneTitle != null) {
            return bottomSheetDialogOneButtonOneTitle;
        }
        Intrinsics.throwUninitializedPropertyAccessException("bottomSheetDialogOneButtonOneTitle");
        return null;
    }

    @NotNull
    public final BottomSheetDialogOneButtonOneTitle getBottomSheetDialogOneButtonOneTitleError() {
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = this.bottomSheetDialogOneButtonOneTitleError;
        if (bottomSheetDialogOneButtonOneTitle != null) {
            return bottomSheetDialogOneButtonOneTitle;
        }
        Intrinsics.throwUninitializedPropertyAccessException("bottomSheetDialogOneButtonOneTitleError");
        return null;
    }

    @Nullable
    public final BottomSheetDialogTwoButtons getConfirmationPopup() {
        return this.t;
    }

    @NotNull
    public final ArrayList<ShowHideTypeModel> getHideShortCutsList() {
        return this.r;
    }

    @NotNull
    public final ArrayList<Integer> getShowMenuList() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        Iterator<ShowHideTypeModel> it = this.s.iterator();
        while (it.hasNext()) {
            ShowHideTypeModel next = it.next();
            DeviceUtils.Companion companion = DeviceUtils.Companion;
            if (companion.isIDODevice(this)) {
                PayUtils payUtils = PayUtils.INSTANCE;
                String typeName = next.getTypeName();
                Intrinsics.checkNotNull(typeName);
                arrayList.add(Integer.valueOf(payUtils.getShortcutMenuValueIDO(typeName, this)));
            } else if (companion.isTouchELXDevice(this)) {
                PayUtils payUtils2 = PayUtils.INSTANCE;
                String typeName2 = next.getTypeName();
                Intrinsics.checkNotNull(typeName2);
                arrayList.add(Integer.valueOf(payUtils2.getShortcutMenuValueTouch(typeName2, this)));
            }
        }
        return arrayList;
    }

    @NotNull
    public final ArrayList<ShowHideTypeModel> getShowShortsCutsList() {
        return this.s;
    }

    public final void initDialog() {
        String string = getResources().getString(R.string.success_message);
        Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.success_message)");
        setBottomSheetDialogOneButtonOneTitle(new BottomSheetDialogOneButtonOneTitle(this, string));
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = getBottomSheetDialogOneButtonOneTitle();
        String string2 = getResources().getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(\n   …    R.string.ok\n        )");
        bottomSheetDialogOneButtonOneTitle.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.li
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityShortcuts.x(ActivityShortcuts.this, view);
            }
        });
        String string3 = getString(R.string.setting_couldnot_save);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.setting_couldnot_save)");
        setBottomSheetDialogOneButtonOneTitleError(new BottomSheetDialogOneButtonOneTitle(this, string3));
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitleError = getBottomSheetDialogOneButtonOneTitleError();
        String string4 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.ok)");
        bottomSheetDialogOneButtonOneTitleError.setPositiveButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.mi
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityShortcuts.y(ActivityShortcuts.this, view);
            }
        });
    }

    public final void initToolbar() {
        ((TextView) findViewById(R.id.toolbar_title)).setText(getString(R.string.shortcuts));
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.qi
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityShortcuts.z(ActivityShortcuts.this, view);
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
        ((Button) _$_findCachedViewById(i)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.pi
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityShortcuts.A(ActivityShortcuts.this, view);
            }
        });
    }

    public final boolean isChanged() {
        List<ShowHideData> shortcutsList = UserDataManager.getInstance(this).getShortcutsList();
        if (this.s.size() > shortcutsList.size() || this.s.size() < shortcutsList.size()) {
            return true;
        }
        ArrayList arrayList = new ArrayList();
        for (ShowHideData showHideData : shortcutsList) {
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
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons2;
        if (isChanged()) {
            BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons3 = this.t;
            if (bottomSheetDialogTwoButtons3 != null) {
                Boolean valueOf = bottomSheetDialogTwoButtons3 != null ? Boolean.valueOf(bottomSheetDialogTwoButtons3.isShowing()) : null;
                Intrinsics.checkNotNull(valueOf);
                if (valueOf.booleanValue() && (bottomSheetDialogTwoButtons2 = this.t) != null) {
                    bottomSheetDialogTwoButtons2.dismiss();
                }
                this.t = null;
            }
            String string = getString(R.string.confirmation);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.confirmation)");
            String string2 = getString(R.string.save_changes);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.save_changes)");
            BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons4 = new BottomSheetDialogTwoButtons(this, string, string2, false, 8, null);
            this.t = bottomSheetDialogTwoButtons4;
            String string3 = getString(R.string.save_changes_btn);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.save_changes_btn)");
            bottomSheetDialogTwoButtons4.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.oi
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityShortcuts.B(ActivityShortcuts.this, view);
                }
            });
            BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons5 = this.t;
            if (bottomSheetDialogTwoButtons5 != null) {
                String string4 = getString(R.string.discard);
                Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.discard)");
                bottomSheetDialogTwoButtons5.setNegativeButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.ni
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        ActivityShortcuts.C(ActivityShortcuts.this, view);
                    }
                });
            }
            BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons6 = this.t;
            Boolean valueOf2 = bottomSheetDialogTwoButtons6 != null ? Boolean.valueOf(bottomSheetDialogTwoButtons6.isShowing()) : null;
            Intrinsics.checkNotNull(valueOf2);
            if (valueOf2.booleanValue() || (bottomSheetDialogTwoButtons = this.t) == null) {
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
        setContentView(R.layout.shortcuts_activity);
        ActivityShortcutViewModel activityShortcutViewModel = (ActivityShortcutViewModel) ViewModelProviders.of(this, new ViewModelFactory(this)).get(ActivityShortcutViewModel.class);
        this.q = activityShortcutViewModel;
        TextView textView = null;
        if (activityShortcutViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            activityShortcutViewModel = null;
        }
        activityShortcutViewModel.setDialogListener(this);
        showProgress();
        initToolbar();
        initDialog();
        ActivityShortcutViewModel activityShortcutViewModel2 = this.q;
        if (activityShortcutViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            activityShortcutViewModel2 = null;
        }
        activityShortcutViewModel2.getSupoortedListFromWatch();
        ActivityShortcutViewModel activityShortcutViewModel3 = this.q;
        if (activityShortcutViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            activityShortcutViewModel3 = null;
        }
        activityShortcutViewModel3.getSupportedListLiveData().observe(this, new Observer() { // from class: com.coveiot.android.leonardo.more.activities.ri
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                ActivityShortcuts.D(ActivityShortcuts.this, (ShowHideListModel) obj);
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
            if (this.s.size() < 3) {
                Toast.makeText(this, getResources().getString(R.string.shortcuts_type_selection_info), 0).show();
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

    public final void setBottomSheetDialogOneButtonOneTitle(@NotNull BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonOneTitle, "<set-?>");
        this.bottomSheetDialogOneButtonOneTitle = bottomSheetDialogOneButtonOneTitle;
    }

    public final void setBottomSheetDialogOneButtonOneTitleError(@NotNull BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonOneTitle, "<set-?>");
        this.bottomSheetDialogOneButtonOneTitleError = bottomSheetDialogOneButtonOneTitle;
    }

    public final void setConfirmationPopup(@Nullable BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons) {
        this.t = bottomSheetDialogTwoButtons;
    }

    public final void setHideShortCutsList(@NotNull ArrayList<ShowHideTypeModel> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.r = arrayList;
    }

    public final void setShowShortsCutsList(@NotNull ArrayList<ShowHideTypeModel> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.s = arrayList;
    }

    @Override // com.coveiot.android.leonardo.listener.DialogListener
    public void showErrorDialog() {
        if (getBottomSheetDialogOneButtonOneTitleError().isShowing()) {
            return;
        }
        getBottomSheetDialogOneButtonOneTitleError().show();
    }

    @Override // com.coveiot.android.leonardo.listener.DialogListener
    public void showSuccessDialog() {
        if (getBottomSheetDialogOneButtonOneTitle().isShowing()) {
            return;
        }
        getBottomSheetDialogOneButtonOneTitle().show();
    }
}
