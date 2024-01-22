package com.coveiot.android.leonardo.more.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.boat.R;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.leonardo.more.ItemMoveCallbackWorldClock;
import com.coveiot.android.leonardo.more.activities.ActivityWorldClock;
import com.coveiot.android.leonardo.more.activities.ActivityWorldClockCountrySelection;
import com.coveiot.android.leonardo.more.adapters.WorldClockAdapter;
import com.coveiot.android.leonardo.more.models.WorldClockData;
import com.coveiot.android.leonardo.more.viewmodel.WorldClockViewModel;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.leonardo.utils.PayUtils;
import com.coveiot.android.leonardo.utils.ViewModelFactory;
import com.coveiot.android.leonardo.utils.ViewUtilsKt;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.BottomSheetDialogOneButtonOneTitle;
import com.coveiot.android.theme.BottomSheetDialogTwoButtons;
import com.coveiot.android.theme.BottomSheetDialogTwoButtonsOneTitle;
import com.coveiot.android.weeklyreport.listeners.OnSuccessListener;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.WorldClockPrefData;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.StringCompanionObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class FragmentWorldClock extends BaseFragment implements WorldClockAdapter.MenuClickListener, OnSuccessListener, WorldClockAdapter.OnItemSwapListener {
    public static boolean u;
    public EditSaveClickListener listener;
    public WorldClockViewModel mViewModel;
    @Nullable
    public String n;
    public boolean p;
    @Nullable
    public BottomSheetDialogTwoButtonsOneTitle r;
    @Nullable
    public BottomSheetDialogOneButtonOneTitle s;
    @Nullable
    public BottomSheetDialogTwoButtons t;
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static ArrayList<WorldClockData> v = new ArrayList<>();
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public final int m = 201;
    @NotNull
    public ArrayList<WorldClockPrefData> o = new ArrayList<>();
    @NotNull
    public String q = "WORLDCLOCKLIST";

    /* loaded from: classes5.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final ArrayList<WorldClockData> getWorldClockList() {
            return FragmentWorldClock.v;
        }

        public final boolean isEdited() {
            return FragmentWorldClock.u;
        }

        @NotNull
        public final FragmentWorldClock newInstance(@NotNull ArrayList<WorldClockData> worldClockList) {
            Intrinsics.checkNotNullParameter(worldClockList, "worldClockList");
            FragmentWorldClock fragmentWorldClock = new FragmentWorldClock();
            Bundle bundle = new Bundle();
            bundle.putSerializable(fragmentWorldClock.q, worldClockList);
            fragmentWorldClock.setArguments(bundle);
            return fragmentWorldClock;
        }

        public final void setEdited(boolean z) {
            FragmentWorldClock.u = z;
        }

        public final void setWorldClockList(@NotNull ArrayList<WorldClockData> arrayList) {
            Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
            FragmentWorldClock.v = arrayList;
        }
    }

    /* loaded from: classes5.dex */
    public interface EditSaveClickListener {
        void onEditSave(@NotNull String str, @NotNull ArrayList<WorldClockData> arrayList);
    }

    /* loaded from: classes5.dex */
    public static final class a extends Lambda implements Function1<View, Unit> {
        public final /* synthetic */ Integer $maxCityCounts;
        public final /* synthetic */ FragmentWorldClock this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(Integer num, FragmentWorldClock fragmentWorldClock) {
            super(1);
            this.$maxCityCounts = num;
            this.this$0 = fragmentWorldClock;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(View view) {
            invoke2(view);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull View it) {
            Intrinsics.checkNotNullParameter(it, "it");
            int size = FragmentWorldClock.Companion.getWorldClockList().size();
            Integer num = this.$maxCityCounts;
            Intrinsics.checkNotNull(num);
            if (size < num.intValue()) {
                if (!AppUtils.isNetConnected(this.this$0.requireContext())) {
                    Toast.makeText(this.this$0.requireContext(), this.this$0.getString(R.string.no_internet_connection), 0).show();
                    return;
                }
                Intent intent = new Intent(this.this$0.getActivity(), ActivityWorldClockCountrySelection.class);
                intent.putExtra("updatedCountryList", this.this$0.getUpdateCountryList());
                FragmentWorldClock fragmentWorldClock = this.this$0;
                fragmentWorldClock.startActivityForResult(intent, fragmentWorldClock.m);
                return;
            }
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String string = this.this$0.getString(R.string.max_ten_city);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.max_ten_city)");
            String format = String.format(string, Arrays.copyOf(new Object[]{this.$maxCityCounts}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
            Toast.makeText(this.this$0.getContext(), format, 0).show();
        }
    }

    public static final void s(FragmentWorldClock this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        UserDataManager.getInstance(this$0.getContext()).saveWorldClockList(this$0.o);
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = this$0.t;
        if (bottomSheetDialogTwoButtons != null) {
            bottomSheetDialogTwoButtons.dismiss();
        }
        this$0.y();
    }

    public static final void t(FragmentWorldClock this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = this$0.t;
        if (bottomSheetDialogTwoButtons != null) {
            bottomSheetDialogTwoButtons.dismiss();
        }
        List<WorldClockPrefData> worldClocList = UserDataManager.getInstance(this$0.getContext()).getWorldClocList();
        Intrinsics.checkNotNull(worldClocList, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.covepreferences.data.WorldClockPrefData>{ kotlin.collections.TypeAliasesKt.ArrayList<com.coveiot.covepreferences.data.WorldClockPrefData> }");
        this$0.o = (ArrayList) worldClocList;
        FragmentActivity activity = this$0.getActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.leonardo.more.activities.ActivityWorldClock");
        ((ActivityWorldClock) activity).onBackPressed();
    }

    public static final void u(FragmentWorldClock this$0, int i, View view) {
        RecyclerView.Adapter adapter;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        u = true;
        this$0.n = v.get(i).getCountryName();
        v.remove(i);
        RecyclerView recyclerView = (RecyclerView) this$0._$_findCachedViewById(R.id.recyclerview);
        if (recyclerView != null && (adapter = recyclerView.getAdapter()) != null) {
            adapter.notifyDataSetChanged();
        }
        this$0.o.remove(i);
        BottomSheetDialogTwoButtonsOneTitle bottomSheetDialogTwoButtonsOneTitle = this$0.r;
        if (bottomSheetDialogTwoButtonsOneTitle != null) {
            bottomSheetDialogTwoButtonsOneTitle.dismiss();
        }
    }

    public static final void v(FragmentWorldClock this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogTwoButtonsOneTitle bottomSheetDialogTwoButtonsOneTitle = this$0.r;
        if (bottomSheetDialogTwoButtonsOneTitle != null) {
            bottomSheetDialogTwoButtonsOneTitle.dismiss();
        }
    }

    public static final boolean w(FragmentWorldClock this$0, View view, int i, KeyEvent keyEvent) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (keyEvent.getAction() == 1 && i == 4) {
            List<WorldClockPrefData> worldClocList = UserDataManager.getInstance(this$0.getContext()).getWorldClocList();
            if (worldClocList == null || worldClocList.isEmpty()) {
                return false;
            }
            if (this$0.o.size() != UserDataManager.getInstance(this$0.getContext()).getWorldClocList().size()) {
                this$0.p = false;
                this$0.r(true);
            } else {
                FragmentActivity activity = this$0.getActivity();
                Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.leonardo.more.activities.ActivityWorldClock");
                ((ActivityWorldClock) activity).onBackPressed();
            }
            return true;
        }
        this$0.A();
        return false;
    }

    public static final void x(FragmentWorldClock this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.p = false;
        List<WorldClockPrefData> worldClocList = UserDataManager.getInstance(this$0.getContext()).getWorldClocList();
        if (worldClocList == null || worldClocList.isEmpty()) {
            return;
        }
        if (UserDataManager.getInstance(this$0.getContext()).getWorldClocList().size() == v.size() && this$0.o.size() == v.size()) {
            Toast.makeText(this$0.getContext(), (int) R.string.no_changes, 0).show();
        } else {
            this$0.y();
        }
    }

    public static final void z(FragmentWorldClock this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.p) {
            ((Button) this$0._$_findCachedViewById(R.id.add_city)).performClick();
            this$0.p = false;
            BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = this$0.s;
            if (bottomSheetDialogOneButtonOneTitle != null) {
                bottomSheetDialogOneButtonOneTitle.dismiss();
                return;
            }
            return;
        }
        this$0.onBackPressedCheck();
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle2 = this$0.s;
        if (bottomSheetDialogOneButtonOneTitle2 != null) {
            bottomSheetDialogOneButtonOneTitle2.dismiss();
        }
    }

    public final void A() {
        List<WorldClockPrefData> worldClocList = UserDataManager.getInstance(getContext()).getWorldClocList();
        if (!(worldClocList == null || worldClocList.isEmpty())) {
            ((ConstraintLayout) _$_findCachedViewById(R.id.empty_world_clock_img_layout)).setVisibility(8);
            ((RecyclerView) _$_findCachedViewById(R.id.recyclerview)).setVisibility(0);
            ((Button) _$_findCachedViewById(R.id.save_city)).setVisibility(0);
            int i = R.id.add_city;
            ((Button) _$_findCachedViewById(i)).getLayoutParams().width = (int) ((156 * getResources().getDisplayMetrics().density) + 0.5f);
            ((Button) _$_findCachedViewById(i)).setBackgroundResource(R.drawable.disable_button_background_transparent_bg);
            return;
        }
        dismissProgress();
        ((ConstraintLayout) _$_findCachedViewById(R.id.empty_world_clock_img_layout)).setVisibility(0);
        ((RecyclerView) _$_findCachedViewById(R.id.recyclerview)).setVisibility(8);
        ((Button) _$_findCachedViewById(R.id.save_city)).setVisibility(8);
        int i2 = R.id.add_city;
        ViewGroup.LayoutParams layoutParams = ((Button) _$_findCachedViewById(i2)).getLayoutParams();
        layoutParams.width = -1;
        ((Button) _$_findCachedViewById(i2)).setLayoutParams(layoutParams);
        ((Button) _$_findCachedViewById(i2)).setBackgroundResource(R.drawable.enable_button_background);
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
        BottomSheetDialogTwoButtonsOneTitle bottomSheetDialogTwoButtonsOneTitle;
        BottomSheetDialogTwoButtonsOneTitle bottomSheetDialogTwoButtonsOneTitle2;
        BottomSheetDialogTwoButtonsOneTitle bottomSheetDialogTwoButtonsOneTitle3 = this.r;
        if (bottomSheetDialogTwoButtonsOneTitle3 != null) {
            Boolean valueOf = bottomSheetDialogTwoButtonsOneTitle3 != null ? Boolean.valueOf(bottomSheetDialogTwoButtonsOneTitle3.isShowing()) : null;
            Intrinsics.checkNotNull(valueOf);
            if (valueOf.booleanValue() && (bottomSheetDialogTwoButtonsOneTitle2 = this.r) != null) {
                bottomSheetDialogTwoButtonsOneTitle2.dismiss();
            }
            this.r = null;
        }
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        String string = getString(R.string.delete_confirmatiob);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.delete_confirmatiob)");
        BottomSheetDialogTwoButtonsOneTitle bottomSheetDialogTwoButtonsOneTitle4 = new BottomSheetDialogTwoButtonsOneTitle(requireContext, string);
        this.r = bottomSheetDialogTwoButtonsOneTitle4;
        String string2 = getString(R.string.yes);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.yes)");
        bottomSheetDialogTwoButtonsOneTitle4.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.fragments.y0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentWorldClock.u(FragmentWorldClock.this, i, view);
            }
        });
        BottomSheetDialogTwoButtonsOneTitle bottomSheetDialogTwoButtonsOneTitle5 = this.r;
        if (bottomSheetDialogTwoButtonsOneTitle5 != null) {
            String string3 = getString(R.string.f4085no);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.no)");
            bottomSheetDialogTwoButtonsOneTitle5.setNegativeButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.fragments.v0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FragmentWorldClock.v(FragmentWorldClock.this, view);
                }
            });
        }
        BottomSheetDialogTwoButtonsOneTitle bottomSheetDialogTwoButtonsOneTitle6 = this.r;
        Boolean valueOf2 = bottomSheetDialogTwoButtonsOneTitle6 != null ? Boolean.valueOf(bottomSheetDialogTwoButtonsOneTitle6.isShowing()) : null;
        Intrinsics.checkNotNull(valueOf2);
        if (valueOf2.booleanValue() || (bottomSheetDialogTwoButtonsOneTitle = this.r) == null) {
            return;
        }
        bottomSheetDialogTwoButtonsOneTitle.show();
    }

    @Nullable
    public final BottomSheetDialogOneButtonOneTitle getBottomSheetDialogOneButtonOneTitle() {
        return this.s;
    }

    @Nullable
    public final BottomSheetDialogTwoButtonsOneTitle getConfirmationDailog() {
        return this.r;
    }

    @Nullable
    public final BottomSheetDialogTwoButtons getConfirmationDailog1() {
        return this.t;
    }

    @Nullable
    public final String getCountryName() {
        return this.n;
    }

    @NotNull
    public final EditSaveClickListener getListener() {
        EditSaveClickListener editSaveClickListener = this.listener;
        if (editSaveClickListener != null) {
            return editSaveClickListener;
        }
        Intrinsics.throwUninitializedPropertyAccessException(ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        return null;
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
    public final ArrayList<WorldClockPrefData> getUpdateCountryList() {
        return this.o;
    }

    public final boolean isAddButtonClicked() {
        return this.p;
    }

    public final void loadAdapter() {
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        ArrayList<WorldClockData> arrayList = v;
        Intrinsics.checkNotNull(arrayList);
        WorldClockAdapter worldClockAdapter = new WorldClockAdapter(requireContext, arrayList, this, this);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemMoveCallbackWorldClock(worldClockAdapter));
        int i = R.id.recyclerview;
        itemTouchHelper.attachToRecyclerView((RecyclerView) _$_findCachedViewById(i));
        ((RecyclerView) _$_findCachedViewById(i)).setAdapter(worldClockAdapter);
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
        RecyclerView.Adapter adapter;
        super.onActivityResult(i, i2, intent);
        if (i == this.m && i2 == 200) {
            if (intent != null) {
                A();
                Serializable serializableExtra = intent.getSerializableExtra(AppConstants.EXTRA_COUNTRY_SELECTED.getValue());
                ArrayList arrayList = serializableExtra instanceof ArrayList ? (ArrayList) serializableExtra : null;
                if (arrayList != null) {
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        WorldClockPrefData worldClockPrefData = (WorldClockPrefData) it.next();
                        String timeZoneName = worldClockPrefData.getTimeZoneName();
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm a");
                        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd hh:mm a");
                        SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat("hh:mm a");
                        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(timeZoneName));
                        simpleDateFormat3.setTimeZone(TimeZone.getTimeZone(timeZoneName));
                        simpleDateFormat2.setTimeZone(TimeZone.getDefault());
                        LogHelper.d("FragmentWorlClock", "Date and time in city selected: " + simpleDateFormat.format(new Date()));
                        LogHelper.d("FragmentWorlClock", "Date and time in city india: " + simpleDateFormat2.format(new Date()));
                        StringBuilder sb = new StringBuilder();
                        sb.append("Today ");
                        PayUtils payUtils = PayUtils.INSTANCE;
                        String format = simpleDateFormat.format(new Date());
                        Intrinsics.checkNotNullExpressionValue(format, "selectedCountry.format(Date())");
                        String format2 = simpleDateFormat2.format(new Date());
                        Intrinsics.checkNotNullExpressionValue(format2, "currentCountry.format(Date())");
                        sb.append(payUtils.getTimeDifferenceWorldClock(format, format2, "yyyy-MM-dd hh:mm a"));
                        sb.append(" hrs");
                        String sb2 = sb.toString();
                        String name = worldClockPrefData.getName();
                        ArrayList arrayList2 = new ArrayList();
                        int size = v.size();
                        for (int i3 = 0; i3 < size; i3++) {
                            arrayList2.add(v.get(i3).getCountryName());
                        }
                        if (CollectionsKt___CollectionsKt.contains(arrayList2, name)) {
                            Toast.makeText(requireContext(), getResources().getString(R.string.country_added_already), 0).show();
                        } else {
                            ArrayList<WorldClockData> arrayList3 = v;
                            Intrinsics.checkNotNull(name);
                            String format3 = simpleDateFormat3.format(new Date());
                            Intrinsics.checkNotNullExpressionValue(format3, "selectedCountryTime.format(Date())");
                            arrayList3.add(new WorldClockData(name, sb2, format3));
                            RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.recyclerview);
                            if (recyclerView != null && (adapter = recyclerView.getAdapter()) != null) {
                                adapter.notifyDataSetChanged();
                            }
                        }
                    }
                }
            } else {
                A();
            }
            this.o.clear();
            this.o.addAll(UserDataManager.getInstance(getContext()).getWorldClocList());
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(@NotNull Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        super.onAttach(activity);
        if (activity instanceof EditSaveClickListener) {
            setListener((EditSaveClickListener) activity);
        }
    }

    public final void onBackPressedCheck() {
        List<WorldClockPrefData> worldClocList = UserDataManager.getInstance(getContext()).getWorldClocList();
        if (!(worldClocList == null || worldClocList.isEmpty()) && this.o.size() != UserDataManager.getInstance(getContext()).getWorldClocList().size()) {
            r(true);
            return;
        }
        FragmentActivity activity = getActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.leonardo.more.activities.ActivityWorldClock");
        ((ActivityWorldClock) activity).onBackPressed();
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            Serializable serializable = requireArguments().getSerializable(this.q);
            Intrinsics.checkNotNull(serializable, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.android.leonardo.more.models.WorldClockData>{ kotlin.collections.TypeAliasesKt.ArrayList<com.coveiot.android.leonardo.more.models.WorldClockData> }");
            v = (ArrayList) serializable;
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
        Toast.makeText(getContext(), message, 0).show();
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.coveiot.android.leonardo.more.adapters.WorldClockAdapter.MenuClickListener
    public void onItemDeleteClick(int i) {
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        if (companion.isSmaDevice(requireContext)) {
            deletePopup(i);
        } else if (v.size() < 2) {
            Toast.makeText(requireContext(), getResources().getString(R.string.atleast_one_country_info), 0).show();
        } else {
            deletePopup(i);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        requireView().setFocusableInTouchMode(true);
        requireView().requestFocus();
        requireView().setOnKeyListener(new View.OnKeyListener() { // from class: com.coveiot.android.leonardo.more.fragments.z0
            @Override // android.view.View.OnKeyListener
            public final boolean onKey(View view, int i, KeyEvent keyEvent) {
                boolean w;
                w = FragmentWorldClock.w(FragmentWorldClock.this, view, i, keyEvent);
                return w;
            }
        });
        A();
    }

    @Override // com.coveiot.android.weeklyreport.listeners.OnSuccessListener
    public void onSuccess() {
        dismissProgress();
        successDialog();
    }

    @Override // com.coveiot.android.leonardo.more.adapters.WorldClockAdapter.OnItemSwapListener
    public void onSwap() {
        u = true;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        A();
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        setMViewModel((WorldClockViewModel) ViewModelProviders.of(this, new ViewModelFactory(requireContext)).get(WorldClockViewModel.class));
        getMViewModel().setOnSuccessListener(this);
        List<WorldClockPrefData> worldClocList = UserDataManager.getInstance(getContext()).getWorldClocList();
        if (!(worldClocList == null || worldClocList.isEmpty())) {
            this.o.addAll(UserDataManager.getInstance(getContext()).getWorldClocList());
        }
        TextView textView = (TextView) _$_findCachedViewById(R.id.max_city_tv);
        Context context = getContext();
        Integer valueOf = context != null ? Integer.valueOf(PayUtils.INSTANCE.getMaximumWorldClockCount(context)) : null;
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String string = getString(R.string.max_ten_city);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.max_ten_city)");
        String format = String.format(string, Arrays.copyOf(new Object[]{valueOf}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        textView.setText(format);
        Button add_city = (Button) _$_findCachedViewById(R.id.add_city);
        Intrinsics.checkNotNullExpressionValue(add_city, "add_city");
        ViewUtilsKt.setSafeOnClickListener(add_city, new a(valueOf, this));
        ((Button) _$_findCachedViewById(R.id.save_city)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.fragments.x0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentWorldClock.x(FragmentWorldClock.this, view2);
            }
        });
        loadAdapter();
    }

    public final void r(boolean z) {
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons;
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons2;
        if (z) {
            if (this.t == null) {
                Context context = getContext();
                if (context != null) {
                    String string = getString(R.string.confirmation);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.confirmation)");
                    String string2 = getString(R.string.save_changes);
                    Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.save_changes)");
                    bottomSheetDialogTwoButtons2 = new BottomSheetDialogTwoButtons(context, string, string2, false, 8, null);
                } else {
                    bottomSheetDialogTwoButtons2 = null;
                }
                this.t = bottomSheetDialogTwoButtons2;
                if (bottomSheetDialogTwoButtons2 != null) {
                    String string3 = getString(R.string.save_changes_btn);
                    Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.save_changes_btn)");
                    bottomSheetDialogTwoButtons2.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.fragments.w0
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            FragmentWorldClock.s(FragmentWorldClock.this, view);
                        }
                    });
                }
                BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons3 = this.t;
                if (bottomSheetDialogTwoButtons3 != null) {
                    String string4 = getString(R.string.discard);
                    Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.discard)");
                    bottomSheetDialogTwoButtons3.setNegativeButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.fragments.t0
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            FragmentWorldClock.t(FragmentWorldClock.this, view);
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
        onBackPressedCheck();
    }

    public final void setAddButtonClicked(boolean z) {
        this.p = z;
    }

    public final void setBottomSheetDialogOneButtonOneTitle(@Nullable BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle) {
        this.s = bottomSheetDialogOneButtonOneTitle;
    }

    public final void setConfirmationDailog(@Nullable BottomSheetDialogTwoButtonsOneTitle bottomSheetDialogTwoButtonsOneTitle) {
        this.r = bottomSheetDialogTwoButtonsOneTitle;
    }

    public final void setConfirmationDailog1(@Nullable BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons) {
        this.t = bottomSheetDialogTwoButtons;
    }

    public final void setCountryName(@Nullable String str) {
        this.n = str;
    }

    public final void setListener(@NotNull EditSaveClickListener editSaveClickListener) {
        Intrinsics.checkNotNullParameter(editSaveClickListener, "<set-?>");
        this.listener = editSaveClickListener;
    }

    public final void setMViewModel(@NotNull WorldClockViewModel worldClockViewModel) {
        Intrinsics.checkNotNullParameter(worldClockViewModel, "<set-?>");
        this.mViewModel = worldClockViewModel;
    }

    public final void setUpdateCountryList(@NotNull ArrayList<WorldClockPrefData> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.o = arrayList;
    }

    public final void successDialog() {
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle;
        if (this.s == null) {
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            String string = getString(R.string.setting_saved_successfully);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.setting_saved_successfully)");
            BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle2 = new BottomSheetDialogOneButtonOneTitle(requireContext, string);
            this.s = bottomSheetDialogOneButtonOneTitle2;
            String string2 = getString(R.string.ok);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.ok)");
            bottomSheetDialogOneButtonOneTitle2.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.fragments.u0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FragmentWorldClock.z(FragmentWorldClock.this, view);
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

    public final void y() {
        if (!AppUtils.isBluetoothEnabled(requireContext())) {
            Toast.makeText(requireContext(), (int) R.string.bluetooth_off_message, 0).show();
        } else if (BleApiManager.getInstance(requireContext()).getBleApi().getConnectionStatus() != ConnectionStatus.CONNECTED) {
            Toast.makeText(requireContext(), (int) R.string.band_not_connected, 0).show();
        } else if (!AppUtils.isNetConnected(requireContext())) {
            Toast.makeText(requireContext(), getString(R.string.no_internet_connection), 0).show();
        } else if (this.o != null) {
            BaseFragment.showProgress$default(this, false, 1, null);
            getMViewModel().getRemovedCountryList().clear();
            UserDataManager.getInstance(getContext()).saveWorldClockList(this.o);
            getMViewModel().getRemovedCountryList().addAll(this.o);
            u = false;
            getMViewModel().sendToBle(true, getMViewModel().getRemovedCountryList());
        } else if (u) {
            BaseFragment.showProgress$default(this, false, 1, null);
            u = false;
            getMViewModel().swapedList(v);
        } else {
            Toast.makeText(requireContext(), getResources().getString(R.string.no_changes), 0).show();
        }
    }
}
