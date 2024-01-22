package com.coveiot.android.leonardo.more.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.more.fragments.FragmentWorldClock;
import com.coveiot.android.leonardo.more.fragments.FragmentWorldClockEdit;
import com.coveiot.android.leonardo.more.models.WorldClockData;
import com.coveiot.android.leonardo.more.viewmodel.WorldClockViewModel;
import com.coveiot.android.leonardo.utils.ViewModelFactory;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.BottomSheetDialogTwoButtons;
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
public final class ActivityWorldClock extends BaseActivity implements FragmentWorldClock.EditSaveClickListener, FragmentWorldClockEdit.EditSaveClickListener {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @Nullable
    public BottomSheetDialogTwoButtons p;
    public WorldClockViewModel worldClockViewModel;

    /* loaded from: classes5.dex */
    public static final class a extends Lambda implements Function1<Boolean, Unit> {
        public a() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
            invoke2(bool);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(Boolean bool) {
            ActivityWorldClock activityWorldClock = ActivityWorldClock.this;
            activityWorldClock.w(activityWorldClock.getWorldClockViewModel().getDefaultCountryListWithTime());
            ActivityWorldClock.this.dismissProgress();
        }
    }

    public static final void u(ActivityWorldClock this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentWorldClockEdit.Companion.getSaveText().performClick();
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = this$0.p;
        if (bottomSheetDialogTwoButtons != null) {
            bottomSheetDialogTwoButtons.dismiss();
        }
    }

    public static final void v(ActivityWorldClock this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentWorldClockEdit.Companion.setEdited(false);
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = this$0.p;
        if (bottomSheetDialogTwoButtons != null) {
            bottomSheetDialogTwoButtons.dismiss();
        }
        this$0.finish();
    }

    public static final void y(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void z(ActivityWorldClock this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Fragment findFragmentById = this$0.getSupportFragmentManager().findFragmentById(R.id.worldClock_container);
        FragmentWorldClock fragmentWorldClock = findFragmentById instanceof FragmentWorldClock ? (FragmentWorldClock) findFragmentById : null;
        if (fragmentWorldClock != null) {
            fragmentWorldClock.onBackPressedCheck();
        }
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

    public final void confirmationDialog() {
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons;
        if (this.p == null) {
            String string = getString(R.string.confirmation);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.confirmation)");
            String string2 = getString(R.string.save_changes);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.save_changes)");
            BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons2 = new BottomSheetDialogTwoButtons(this, string, string2, false, 8, null);
            this.p = bottomSheetDialogTwoButtons2;
            String string3 = getString(R.string.save_changes_btn);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.save_changes_btn)");
            bottomSheetDialogTwoButtons2.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.qj
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityWorldClock.u(ActivityWorldClock.this, view);
                }
            });
            BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons3 = this.p;
            if (bottomSheetDialogTwoButtons3 != null) {
                String string4 = getString(R.string.discard);
                Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.discard)");
                bottomSheetDialogTwoButtons3.setNegativeButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.rj
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        ActivityWorldClock.v(ActivityWorldClock.this, view);
                    }
                });
            }
        }
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons4 = this.p;
        Boolean valueOf = bottomSheetDialogTwoButtons4 != null ? Boolean.valueOf(bottomSheetDialogTwoButtons4.isShowing()) : null;
        Intrinsics.checkNotNull(valueOf);
        if (valueOf.booleanValue() || (bottomSheetDialogTwoButtons = this.p) == null) {
            return;
        }
        bottomSheetDialogTwoButtons.show();
    }

    @Nullable
    public final BottomSheetDialogTwoButtons getConfirmationDailog() {
        return this.p;
    }

    @NotNull
    public final WorldClockViewModel getWorldClockViewModel() {
        WorldClockViewModel worldClockViewModel = this.worldClockViewModel;
        if (worldClockViewModel != null) {
            return worldClockViewModel;
        }
        Intrinsics.throwUninitializedPropertyAccessException("worldClockViewModel");
        return null;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (getSupportFragmentManager().findFragmentById(R.id.worldClock_container) instanceof FragmentWorldClockEdit) {
            FragmentWorldClockEdit.Companion companion = FragmentWorldClockEdit.Companion;
            if (companion.isEdited()) {
                confirmationDialog();
                return;
            } else {
                w(companion.getWorldClockList());
                return;
            }
        }
        finish();
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.world_clock_common_activity);
        setWorldClockViewModel((WorldClockViewModel) ViewModelProviders.of(this, new ViewModelFactory(this)).get(WorldClockViewModel.class));
        if (BleApiManager.getInstance(this).getBleApi().getDeviceSupportedFeatures().isReadWorldClockFromWatchSupported()) {
            MutableLiveData<Boolean> getWorldClockFromWatchLiveData = getWorldClockViewModel().getGetWorldClockFromWatchLiveData();
            final a aVar = new a();
            getWorldClockFromWatchLiveData.observe(this, new Observer() { // from class: com.coveiot.android.leonardo.more.activities.tj
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    ActivityWorldClock.y(Function1.this, obj);
                }
            });
            getWorldClockViewModel().getWorldClockFromWatch();
            showProgress();
        } else {
            w(getWorldClockViewModel().getDefaultCountryListWithTime());
        }
        int i = R.id.toolbar;
        ((TextView) _$_findCachedViewById(i).findViewById(R.id.toolbar_title)).setText(getString(R.string.world_clock));
        ((TextView) _$_findCachedViewById(i).findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.sj
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityWorldClock.z(ActivityWorldClock.this, view);
            }
        });
    }

    @Override // com.coveiot.android.leonardo.more.fragments.FragmentWorldClock.EditSaveClickListener, com.coveiot.android.leonardo.more.fragments.FragmentWorldClockEdit.EditSaveClickListener
    public void onEditSave(@NotNull String type, @NotNull ArrayList<WorldClockData> worldClockList) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(worldClockList, "worldClockList");
        if (type.equals(getResources().getString(R.string.edit))) {
            x(worldClockList);
        } else {
            w(worldClockList);
        }
    }

    public final void setConfirmationDailog(@Nullable BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons) {
        this.p = bottomSheetDialogTwoButtons;
    }

    public final void setWorldClockViewModel(@NotNull WorldClockViewModel worldClockViewModel) {
        Intrinsics.checkNotNullParameter(worldClockViewModel, "<set-?>");
        this.worldClockViewModel = worldClockViewModel;
    }

    public final void w(ArrayList<WorldClockData> arrayList) {
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        FragmentWorldClock.Companion companion = FragmentWorldClock.Companion;
        beginTransaction.replace(R.id.worldClock_container, companion.newInstance(arrayList)).addToBackStack(companion.toString()).commitAllowingStateLoss();
    }

    public final void x(ArrayList<WorldClockData> arrayList) {
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        FragmentWorldClockEdit.Companion companion = FragmentWorldClockEdit.Companion;
        beginTransaction.replace(R.id.worldClock_container, companion.newInstance(arrayList)).addToBackStack(companion.toString()).commitAllowingStateLoss();
    }
}
