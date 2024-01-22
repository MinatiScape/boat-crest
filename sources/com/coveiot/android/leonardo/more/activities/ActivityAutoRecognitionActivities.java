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
import com.coveiot.android.bleabstract.models.ActivityTypes;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.listener.DialogListener;
import com.coveiot.android.leonardo.more.adapters.AutoRecognitionAdapter;
import com.coveiot.android.leonardo.more.models.AutoRecognitonModel;
import com.coveiot.android.leonardo.more.viewmodel.ActivityAutoRecognitionViewModel;
import com.coveiot.android.leonardo.utils.ViewModelFactory;
import com.coveiot.android.leonardo.utils.ViewUtilsKt;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.BottomSheetDialogOneButtonOneTitle;
import com.coveiot.android.theme.BottomSheetDialogTwoButtons;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.StringCompanionObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityAutoRecognitionActivities extends BaseActivity implements DialogListener, AutoRecognitionAdapter.OnItemClickListener {
    public TextView q;
    public ActivityAutoRecognitionViewModel r;
    public boolean u;
    @Nullable
    public BottomSheetDialogOneButtonOneTitle v;
    @Nullable
    public BottomSheetDialogOneButtonOneTitle w;
    @Nullable
    public BottomSheetDialogTwoButtons x;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public final String p = "ActivityAutoRecognitionActivities";
    @NotNull
    public ArrayList<AutoRecognitonModel> s = new ArrayList<>();
    @NotNull
    public ArrayList<AutoRecognitonModel> t = new ArrayList<>();

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
            if (BleApiManager.getInstance(ActivityAutoRecognitionActivities.this).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
                if (AppUtils.isNetConnected(ActivityAutoRecognitionActivities.this)) {
                    ActivityAutoRecognitionActivities.this.showProgress();
                    ActivityAutoRecognitionViewModel activityAutoRecognitionViewModel = ActivityAutoRecognitionActivities.this.r;
                    if (activityAutoRecognitionViewModel == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                        activityAutoRecognitionViewModel = null;
                    }
                    activityAutoRecognitionViewModel.setAutoRecognitionToWatch(ActivityAutoRecognitionActivities.this.getActivityList());
                    return;
                }
                ActivityAutoRecognitionActivities activityAutoRecognitionActivities = ActivityAutoRecognitionActivities.this;
                Toast.makeText(activityAutoRecognitionActivities, activityAutoRecognitionActivities.getResources().getString(R.string.please_check_network), 1).show();
                return;
            }
            Toast.makeText(ActivityAutoRecognitionActivities.this, (int) R.string.band_not_connected, 1).show();
        }
    }

    public static final void A(ActivityAutoRecognitionActivities this$0, ArrayList arrayList) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (arrayList != null) {
            this$0.s.addAll(arrayList);
            this$0.t.addAll(arrayList);
            ((RecyclerView) this$0._$_findCachedViewById(R.id.auto_recog_recyl)).setAdapter(new AutoRecognitionAdapter(this$0, this$0.s, this$0));
            ActivityAutoRecognitionViewModel activityAutoRecognitionViewModel = this$0.r;
            if (activityAutoRecognitionViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                activityAutoRecognitionViewModel = null;
            }
            activityAutoRecognitionViewModel.getListOfActivityLiveData().removeObservers(this$0);
            this$0.dismissProgress();
        }
    }

    public static final void B(ActivityAutoRecognitionActivities this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = this$0.v;
        if (bottomSheetDialogOneButtonOneTitle != null) {
            bottomSheetDialogOneButtonOneTitle.dismiss();
        }
        this$0.finish();
    }

    public static final void C(ActivityAutoRecognitionActivities this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = this$0.w;
        if (bottomSheetDialogOneButtonOneTitle != null) {
            bottomSheetDialogOneButtonOneTitle.dismiss();
        }
        this$0.finish();
    }

    public static final void x(ActivityAutoRecognitionActivities this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    public static final void y(ActivityAutoRecognitionActivities this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = this$0.x;
        if (bottomSheetDialogTwoButtons != null) {
            bottomSheetDialogTwoButtons.dismiss();
        }
        TextView textView = this$0.q;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("saveText");
            textView = null;
        }
        textView.performClick();
    }

    public static final void z(ActivityAutoRecognitionActivities this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = this$0.x;
        if (bottomSheetDialogTwoButtons != null) {
            bottomSheetDialogTwoButtons.dismiss();
        }
        this$0.finish();
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
        ((Button) _$_findCachedViewById(R.id.btn_ok)).setEnabled(this.u);
    }

    @NotNull
    public final ArrayList<AutoRecognitonModel> getActivityList() {
        return this.s;
    }

    @NotNull
    public final ArrayList<AutoRecognitonModel> getActivityListPref() {
        return this.t;
    }

    public final boolean getBoolSaveVisible() {
        return this.u;
    }

    @Nullable
    public final BottomSheetDialogOneButtonOneTitle getBottomSheetDialogOneButtonOneTitleError() {
        return this.v;
    }

    @Nullable
    public final BottomSheetDialogOneButtonOneTitle getBottomSheetDialogOneButtonOneTitleSucess() {
        return this.w;
    }

    @Nullable
    public final BottomSheetDialogTwoButtons getConfirmationBottomSheetDialog() {
        return this.x;
    }

    public final void initToolbar() {
        ((TextView) findViewById(R.id.toolbar_title)).setText(getString(R.string.intelligent_recognition_activity));
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.o0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityAutoRecognitionActivities.x(ActivityAutoRecognitionActivities.this, view);
            }
        });
        View findViewById = findViewById(R.id.save);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById<TextView>(R.id.save)");
        TextView textView = (TextView) findViewById;
        this.q = textView;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("saveText");
            textView = null;
        }
        textView.setVisibility(8);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons;
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons2;
        if (this.u) {
            BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons3 = this.x;
            if (bottomSheetDialogTwoButtons3 != null) {
                Boolean valueOf = bottomSheetDialogTwoButtons3 != null ? Boolean.valueOf(bottomSheetDialogTwoButtons3.isShowing()) : null;
                Intrinsics.checkNotNull(valueOf);
                if (valueOf.booleanValue() && (bottomSheetDialogTwoButtons2 = this.x) != null) {
                    bottomSheetDialogTwoButtons2.dismiss();
                }
                this.x = null;
            }
            String string = getString(R.string.intelligent_recognition_activity);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.intel…ent_recognition_activity)");
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String string2 = getString(R.string.save_changes_hr);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.save_changes_hr)");
            String format = String.format(string2, Arrays.copyOf(new Object[]{getString(R.string.intelligent_recognition_activity)}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
            BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons4 = new BottomSheetDialogTwoButtons(this, string, format, false, 8, null);
            this.x = bottomSheetDialogTwoButtons4;
            String string3 = getString(R.string.save_changes_btn);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.save_changes_btn)");
            bottomSheetDialogTwoButtons4.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.n0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityAutoRecognitionActivities.y(ActivityAutoRecognitionActivities.this, view);
                }
            });
            BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons5 = this.x;
            if (bottomSheetDialogTwoButtons5 != null) {
                String string4 = getString(R.string.discard);
                Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.discard)");
                bottomSheetDialogTwoButtons5.setNegativeButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.l0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        ActivityAutoRecognitionActivities.z(ActivityAutoRecognitionActivities.this, view);
                    }
                });
            }
            BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons6 = this.x;
            Boolean valueOf2 = bottomSheetDialogTwoButtons6 != null ? Boolean.valueOf(bottomSheetDialogTwoButtons6.isShowing()) : null;
            Intrinsics.checkNotNull(valueOf2);
            if (valueOf2.booleanValue() || (bottomSheetDialogTwoButtons = this.x) == null) {
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
        setContentView(R.layout.activity_auto_recog);
        ActivityAutoRecognitionViewModel activityAutoRecognitionViewModel = (ActivityAutoRecognitionViewModel) ViewModelProviders.of(this, new ViewModelFactory(this)).get(ActivityAutoRecognitionViewModel.class);
        this.r = activityAutoRecognitionViewModel;
        ActivityAutoRecognitionViewModel activityAutoRecognitionViewModel2 = null;
        if (activityAutoRecognitionViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            activityAutoRecognitionViewModel = null;
        }
        activityAutoRecognitionViewModel.setDialogListener(this);
        initToolbar();
        w();
        showProgress();
        buttonVisible();
        ActivityAutoRecognitionViewModel activityAutoRecognitionViewModel3 = this.r;
        if (activityAutoRecognitionViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            activityAutoRecognitionViewModel3 = null;
        }
        activityAutoRecognitionViewModel3.getListOfActivities();
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.auto_recog_recyl);
        if (recyclerView != null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
        ActivityAutoRecognitionViewModel activityAutoRecognitionViewModel4 = this.r;
        if (activityAutoRecognitionViewModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            activityAutoRecognitionViewModel2 = activityAutoRecognitionViewModel4;
        }
        activityAutoRecognitionViewModel2.getListOfActivityLiveData().observe(this, new Observer() { // from class: com.coveiot.android.leonardo.more.activities.q0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                ActivityAutoRecognitionActivities.A(ActivityAutoRecognitionActivities.this, (ArrayList) obj);
            }
        });
    }

    @Override // com.coveiot.android.leonardo.listener.DialogListener
    public void onDismiss() {
        dismissProgress();
    }

    @Override // com.coveiot.android.leonardo.more.adapters.AutoRecognitionAdapter.OnItemClickListener
    public void onItemClick(@NotNull ActivityTypes activityType, boolean z, int i) {
        RecyclerView.Adapter adapter;
        Intrinsics.checkNotNullParameter(activityType, "activityType");
        this.s.set(i, new AutoRecognitonModel(activityType, z, null, 4, null));
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.auto_recog_recyl);
        if (recyclerView != null && (adapter = recyclerView.getAdapter()) != null) {
            adapter.notifyDataSetChanged();
        }
        int size = this.t.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.u = this.t.get(i2).isSelected() != this.s.get(i2).isSelected();
            LogHelper.d(this.p, "boolsave " + this.u + " pref " + this.t.get(i2).isSelected() + " list " + this.s.get(i2).isSelected());
            if (this.u) {
                break;
            }
        }
        LogHelper.d(this.p, "boolsaveut " + this.u);
        ((Button) _$_findCachedViewById(R.id.btn_ok)).setEnabled(this.u);
    }

    @Override // com.coveiot.android.leonardo.listener.DialogListener
    public void onShowProgressDialog() {
        String string = getString(R.string.please_wait);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.please_wait)");
        showProgresswithMsg(string);
    }

    public final void setActivityList(@NotNull ArrayList<AutoRecognitonModel> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.s = arrayList;
    }

    public final void setActivityListPref(@NotNull ArrayList<AutoRecognitonModel> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.t = arrayList;
    }

    public final void setBoolSaveVisible(boolean z) {
        this.u = z;
    }

    public final void setBottomSheetDialogOneButtonOneTitleError(@Nullable BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle) {
        this.v = bottomSheetDialogOneButtonOneTitle;
    }

    public final void setBottomSheetDialogOneButtonOneTitleSucess(@Nullable BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle) {
        this.w = bottomSheetDialogOneButtonOneTitle;
    }

    public final void setConfirmationBottomSheetDialog(@Nullable BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons) {
        this.x = bottomSheetDialogTwoButtons;
    }

    @Override // com.coveiot.android.leonardo.listener.DialogListener
    public void showErrorDialog() {
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle;
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle2;
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle3 = this.v;
        if (bottomSheetDialogOneButtonOneTitle3 != null) {
            Boolean valueOf = bottomSheetDialogOneButtonOneTitle3 != null ? Boolean.valueOf(bottomSheetDialogOneButtonOneTitle3.isShowing()) : null;
            Intrinsics.checkNotNull(valueOf);
            if (valueOf.booleanValue() && (bottomSheetDialogOneButtonOneTitle2 = this.v) != null) {
                bottomSheetDialogOneButtonOneTitle2.dismiss();
            }
            this.v = null;
        }
        String string = getString(R.string.setting_couldnot_save);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.setting_couldnot_save)");
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle4 = new BottomSheetDialogOneButtonOneTitle(this, string);
        this.v = bottomSheetDialogOneButtonOneTitle4;
        String string2 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.ok)");
        bottomSheetDialogOneButtonOneTitle4.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.m0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityAutoRecognitionActivities.B(ActivityAutoRecognitionActivities.this, view);
            }
        });
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle5 = this.v;
        Boolean valueOf2 = bottomSheetDialogOneButtonOneTitle5 != null ? Boolean.valueOf(bottomSheetDialogOneButtonOneTitle5.isShowing()) : null;
        Intrinsics.checkNotNull(valueOf2);
        if (valueOf2.booleanValue() || (bottomSheetDialogOneButtonOneTitle = this.v) == null) {
            return;
        }
        bottomSheetDialogOneButtonOneTitle.show();
    }

    @Override // com.coveiot.android.leonardo.listener.DialogListener
    public void showSuccessDialog() {
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle;
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle2;
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle3 = this.w;
        if (bottomSheetDialogOneButtonOneTitle3 != null) {
            Boolean valueOf = bottomSheetDialogOneButtonOneTitle3 != null ? Boolean.valueOf(bottomSheetDialogOneButtonOneTitle3.isShowing()) : null;
            Intrinsics.checkNotNull(valueOf);
            if (valueOf.booleanValue() && (bottomSheetDialogOneButtonOneTitle2 = this.w) != null) {
                bottomSheetDialogOneButtonOneTitle2.dismiss();
            }
            this.w = null;
        }
        String string = getString(R.string.success_message);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.success_message)");
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle4 = new BottomSheetDialogOneButtonOneTitle(this, string);
        this.w = bottomSheetDialogOneButtonOneTitle4;
        String string2 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(\n            R.string.ok\n        )");
        bottomSheetDialogOneButtonOneTitle4.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.p0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityAutoRecognitionActivities.C(ActivityAutoRecognitionActivities.this, view);
            }
        });
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle5 = this.w;
        Boolean valueOf2 = bottomSheetDialogOneButtonOneTitle5 != null ? Boolean.valueOf(bottomSheetDialogOneButtonOneTitle5.isShowing()) : null;
        Intrinsics.checkNotNull(valueOf2);
        if (valueOf2.booleanValue() || (bottomSheetDialogOneButtonOneTitle = this.w) == null) {
            return;
        }
        bottomSheetDialogOneButtonOneTitle.show();
    }

    public final void w() {
        Button btn_ok = (Button) _$_findCachedViewById(R.id.btn_ok);
        Intrinsics.checkNotNullExpressionValue(btn_ok, "btn_ok");
        ViewUtilsKt.setSafeOnClickListener(btn_ok, new a());
    }
}
