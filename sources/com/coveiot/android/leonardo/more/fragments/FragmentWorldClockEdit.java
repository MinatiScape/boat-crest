package com.coveiot.android.leonardo.more.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.more.activities.ActivityWorldClock;
import com.coveiot.android.leonardo.more.adapters.WorldClockAdapterEdit;
import com.coveiot.android.leonardo.more.models.WorldClockData;
import com.coveiot.android.leonardo.more.viewmodel.WorldClockViewModel;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.leonardo.utils.ViewModelFactory;
import com.coveiot.android.leonardo.utils.ViewUtilsKt;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.BottomSheetDialogOneButtonOneTitle;
import com.coveiot.android.theme.BottomSheetDialogTwoButtons;
import com.coveiot.android.weeklyreport.listeners.OnSuccessListener;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.WorldClockPrefData;
import com.coveiot.utils.utility.AppUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class FragmentWorldClockEdit extends BaseFragment implements WorldClockAdapterEdit.OnItemClickListener, OnSuccessListener, WorldClockAdapterEdit.OnItemSwapListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static ArrayList<WorldClockData> q = new ArrayList<>();
    public static boolean r;
    public static TextView saveText;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public String m = "WORLDCLOCKLIST";
    public WorldClockViewModel mViewModel;
    @Nullable
    public String n;
    @Nullable
    public BottomSheetDialogTwoButtons o;
    @Nullable
    public BottomSheetDialogOneButtonOneTitle p;
    public EditSaveClickListener saveClickListener;

    /* loaded from: classes5.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final TextView getSaveText() {
            TextView textView = FragmentWorldClockEdit.saveText;
            if (textView != null) {
                return textView;
            }
            Intrinsics.throwUninitializedPropertyAccessException("saveText");
            return null;
        }

        @NotNull
        public final ArrayList<WorldClockData> getWorldClockList() {
            return FragmentWorldClockEdit.q;
        }

        public final boolean isEdited() {
            return FragmentWorldClockEdit.r;
        }

        @NotNull
        public final FragmentWorldClockEdit newInstance(@NotNull ArrayList<WorldClockData> worldClockList) {
            Intrinsics.checkNotNullParameter(worldClockList, "worldClockList");
            FragmentWorldClockEdit fragmentWorldClockEdit = new FragmentWorldClockEdit();
            Bundle bundle = new Bundle();
            bundle.putSerializable(fragmentWorldClockEdit.m, worldClockList);
            fragmentWorldClockEdit.setArguments(bundle);
            return fragmentWorldClockEdit;
        }

        public final void setEdited(boolean z) {
            FragmentWorldClockEdit.r = z;
        }

        public final void setSaveText(@NotNull TextView textView) {
            Intrinsics.checkNotNullParameter(textView, "<set-?>");
            FragmentWorldClockEdit.saveText = textView;
        }

        public final void setWorldClockList(@NotNull ArrayList<WorldClockData> arrayList) {
            Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
            FragmentWorldClockEdit.q = arrayList;
        }
    }

    /* loaded from: classes5.dex */
    public interface EditSaveClickListener {
        void onEditSave(@NotNull String str, @NotNull ArrayList<WorldClockData> arrayList);
    }

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
            if (!AppUtils.isBluetoothEnabled(FragmentWorldClockEdit.this.requireContext())) {
                Toast.makeText(FragmentWorldClockEdit.this.requireContext(), (int) R.string.bluetooth_off_message, 0).show();
            } else if (BleApiManager.getInstance(FragmentWorldClockEdit.this.requireContext()).getBleApi().getConnectionStatus() != ConnectionStatus.CONNECTED) {
                Toast.makeText(FragmentWorldClockEdit.this.requireContext(), (int) R.string.band_not_connected, 0).show();
            } else if (!AppUtils.isNetConnected(FragmentWorldClockEdit.this.requireContext())) {
                Toast.makeText(FragmentWorldClockEdit.this.requireContext(), FragmentWorldClockEdit.this.getString(R.string.no_internet_connection), 0).show();
            } else if (FragmentWorldClockEdit.this.getCountryName() != null) {
                BaseFragment.showProgress$default(FragmentWorldClockEdit.this, false, 1, null);
                FragmentWorldClockEdit.Companion.setEdited(false);
                FragmentWorldClockEdit.this.getMViewModel().sendToBle(true, FragmentWorldClockEdit.this.getMViewModel().getRemovedCountryList());
            } else {
                Companion companion = FragmentWorldClockEdit.Companion;
                if (companion.isEdited()) {
                    BaseFragment.showProgress$default(FragmentWorldClockEdit.this, false, 1, null);
                    companion.setEdited(false);
                    FragmentWorldClockEdit.this.getMViewModel().swapedList(companion.getWorldClockList());
                    return;
                }
                Toast.makeText(FragmentWorldClockEdit.this.requireContext(), FragmentWorldClockEdit.this.getResources().getString(R.string.no_changes), 0).show();
            }
        }
    }

    public static final void o(FragmentWorldClockEdit this$0, int i, View view) {
        RecyclerView.Adapter adapter;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        r = true;
        this$0.n = q.get(i).getCountryName();
        q.remove(i);
        RecyclerView recyclerView = (RecyclerView) this$0._$_findCachedViewById(R.id.recyclerview);
        if (recyclerView != null && (adapter = recyclerView.getAdapter()) != null) {
            adapter.notifyDataSetChanged();
        }
        WorldClockPrefData worldClockPrefData = new WorldClockPrefData();
        List<WorldClockPrefData> worldClocList = UserDataManager.getInstance(this$0.getContext()).getWorldClocList();
        int i2 = 0;
        int size = worldClocList.size();
        while (true) {
            if (i2 >= size) {
                break;
            }
            String name = worldClocList.get(i2).getName();
            String str = this$0.n;
            Intrinsics.checkNotNull(str);
            if (kotlin.text.m.equals(name, str, true)) {
                WorldClockPrefData worldClockPrefData2 = worldClocList.get(i2);
                Intrinsics.checkNotNullExpressionValue(worldClockPrefData2, "dataList[i]");
                worldClockPrefData = worldClockPrefData2;
                break;
            }
            i2++;
        }
        this$0.getMViewModel().getCountryList(worldClockPrefData);
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = this$0.o;
        if (bottomSheetDialogTwoButtons != null) {
            bottomSheetDialogTwoButtons.dismiss();
        }
    }

    public static final void p(FragmentWorldClockEdit this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = this$0.o;
        if (bottomSheetDialogTwoButtons != null) {
            bottomSheetDialogTwoButtons.dismiss();
        }
    }

    public static final void q(FragmentWorldClockEdit this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentActivity activity = this$0.getActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.leonardo.more.activities.ActivityWorldClock");
        ((ActivityWorldClock) activity).onBackPressed();
    }

    public static final void r(FragmentWorldClockEdit this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        EditSaveClickListener saveClickListener = this$0.getSaveClickListener();
        String string = this$0.getResources().getString(R.string.normal);
        Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.normal)");
        saveClickListener.onEditSave(string, q);
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = this$0.p;
        if (bottomSheetDialogOneButtonOneTitle != null) {
            bottomSheetDialogOneButtonOneTitle.dismiss();
        }
    }

    @Override // com.coveiot.android.theme.BaseFragment
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.coveiot.android.theme.BaseFragment
    @Nullable
    public View _$_findCachedViewById(int i) {
        View findViewById;
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view == null) {
            View view2 = getView();
            if (view2 == null || (findViewById = view2.findViewById(i)) == null) {
                return null;
            }
            map.put(Integer.valueOf(i), findViewById);
            return findViewById;
        }
        return view;
    }

    public final void deletePopup(final int i) {
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons;
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons2;
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons3 = this.o;
        if (bottomSheetDialogTwoButtons3 != null) {
            Boolean valueOf = bottomSheetDialogTwoButtons3 != null ? Boolean.valueOf(bottomSheetDialogTwoButtons3.isShowing()) : null;
            Intrinsics.checkNotNull(valueOf);
            if (valueOf.booleanValue() && (bottomSheetDialogTwoButtons2 = this.o) != null) {
                bottomSheetDialogTwoButtons2.dismiss();
            }
            this.o = null;
        }
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        String string = getString(R.string.delete_confirmatiob);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.delete_confirmatiob)");
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons4 = new BottomSheetDialogTwoButtons(requireContext, string, AppConstants.EMPTY_SPACE.getValue(), false, 8, null);
        this.o = bottomSheetDialogTwoButtons4;
        String string2 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.ok)");
        bottomSheetDialogTwoButtons4.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.fragments.d1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentWorldClockEdit.o(FragmentWorldClockEdit.this, i, view);
            }
        });
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons5 = this.o;
        if (bottomSheetDialogTwoButtons5 != null) {
            String string3 = getString(R.string.cancel);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.cancel)");
            bottomSheetDialogTwoButtons5.setNegativeButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.fragments.b1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FragmentWorldClockEdit.p(FragmentWorldClockEdit.this, view);
                }
            });
        }
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons6 = this.o;
        Boolean valueOf2 = bottomSheetDialogTwoButtons6 != null ? Boolean.valueOf(bottomSheetDialogTwoButtons6.isShowing()) : null;
        Intrinsics.checkNotNull(valueOf2);
        if (valueOf2.booleanValue() || (bottomSheetDialogTwoButtons = this.o) == null) {
            return;
        }
        bottomSheetDialogTwoButtons.show();
    }

    @Nullable
    public final BottomSheetDialogOneButtonOneTitle getBottomSheetDialogOneButtonOneTitle() {
        return this.p;
    }

    @Nullable
    public final BottomSheetDialogTwoButtons getConfirmationDailog() {
        return this.o;
    }

    @Nullable
    public final String getCountryName() {
        return this.n;
    }

    @NotNull
    public final WorldClockViewModel getMViewModel() {
        WorldClockViewModel worldClockViewModel = this.mViewModel;
        if (worldClockViewModel != null) {
            return worldClockViewModel;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
        return null;
    }

    @NotNull
    public final EditSaveClickListener getSaveClickListener() {
        EditSaveClickListener editSaveClickListener = this.saveClickListener;
        if (editSaveClickListener != null) {
            return editSaveClickListener;
        }
        Intrinsics.throwUninitializedPropertyAccessException("saveClickListener");
        return null;
    }

    public final void loadAdapter() {
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        ArrayList<WorldClockData> arrayList = q;
        Intrinsics.checkNotNull(arrayList);
        ((RecyclerView) _$_findCachedViewById(R.id.recyclerview)).setAdapter(new WorldClockAdapterEdit(requireContext, arrayList, this, this));
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(@NotNull Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        super.onAttach(activity);
        if (activity instanceof EditSaveClickListener) {
            setSaveClickListener((EditSaveClickListener) activity);
        }
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            Serializable serializable = requireArguments().getSerializable(this.m);
            Intrinsics.checkNotNull(serializable, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.android.leonardo.more.models.WorldClockData>{ kotlin.collections.TypeAliasesKt.ArrayList<com.coveiot.android.leonardo.more.models.WorldClockData> }");
            q = (ArrayList) serializable;
        }
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.world_clock_fragment, viewGroup, false);
    }

    @Override // com.coveiot.android.weeklyreport.listeners.OnSuccessListener
    public void onDataFailure(@NotNull String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        dismissProgress();
        Toast.makeText(requireContext(), message, 0).show();
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.coveiot.android.leonardo.more.adapters.WorldClockAdapterEdit.OnItemClickListener
    public void onItemClick(int i) {
        if (q.size() < 2) {
            Toast.makeText(requireContext(), getResources().getString(R.string.atleast_one_country_info), 0).show();
        } else {
            deletePopup(i);
        }
    }

    @Override // com.coveiot.android.weeklyreport.listeners.OnSuccessListener
    public void onSuccess() {
        dismissProgress();
        successDialog();
    }

    @Override // com.coveiot.android.leonardo.more.adapters.WorldClockAdapterEdit.OnItemSwapListener
    public void onSwap() {
        r = true;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        setMViewModel((WorldClockViewModel) ViewModelProviders.of(this, new ViewModelFactory(requireContext)).get(WorldClockViewModel.class));
        Companion companion = Companion;
        int i = R.id.toolbar;
        View findViewById = _$_findCachedViewById(i).findViewById(R.id.save);
        Intrinsics.checkNotNullExpressionValue(findViewById, "toolbar.findViewById(R.id.save)");
        companion.setSaveText((TextView) findViewById);
        getMViewModel().setOnSuccessListener(this);
        ((Button) _$_findCachedViewById(R.id.add_city)).setVisibility(8);
        companion.getSaveText().setText(getResources().getString(R.string.save_camel));
        ViewUtilsKt.setSafeOnClickListener(companion.getSaveText(), new a());
        ((TextView) _$_findCachedViewById(i).findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.fragments.a1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentWorldClockEdit.q(FragmentWorldClockEdit.this, view2);
            }
        });
        ((TextView) _$_findCachedViewById(i).findViewById(R.id.toolbar_title)).setText(getString(R.string.world_clock));
        loadAdapter();
    }

    public final void setBottomSheetDialogOneButtonOneTitle(@Nullable BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle) {
        this.p = bottomSheetDialogOneButtonOneTitle;
    }

    public final void setConfirmationDailog(@Nullable BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons) {
        this.o = bottomSheetDialogTwoButtons;
    }

    public final void setCountryName(@Nullable String str) {
        this.n = str;
    }

    public final void setMViewModel(@NotNull WorldClockViewModel worldClockViewModel) {
        Intrinsics.checkNotNullParameter(worldClockViewModel, "<set-?>");
        this.mViewModel = worldClockViewModel;
    }

    public final void setSaveClickListener(@NotNull EditSaveClickListener editSaveClickListener) {
        Intrinsics.checkNotNullParameter(editSaveClickListener, "<set-?>");
        this.saveClickListener = editSaveClickListener;
    }

    public final void successDialog() {
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle;
        if (this.p == null) {
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            String string = getString(R.string.setting_saved_successfully);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.setting_saved_successfully)");
            BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle2 = new BottomSheetDialogOneButtonOneTitle(requireContext, string);
            this.p = bottomSheetDialogOneButtonOneTitle2;
            String string2 = getString(R.string.ok);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.ok)");
            bottomSheetDialogOneButtonOneTitle2.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.fragments.c1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FragmentWorldClockEdit.r(FragmentWorldClockEdit.this, view);
                }
            });
        }
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle3 = this.p;
        Boolean valueOf = bottomSheetDialogOneButtonOneTitle3 != null ? Boolean.valueOf(bottomSheetDialogOneButtonOneTitle3.isShowing()) : null;
        Intrinsics.checkNotNull(valueOf);
        if (!valueOf.booleanValue() && (bottomSheetDialogOneButtonOneTitle = this.p) != null) {
            bottomSheetDialogOneButtonOneTitle.show();
        }
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle4 = this.p;
        if (bottomSheetDialogOneButtonOneTitle4 != null) {
            bottomSheetDialogOneButtonOneTitle4.setCancelable(false);
        }
    }
}
