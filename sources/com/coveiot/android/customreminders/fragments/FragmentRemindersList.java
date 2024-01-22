package com.coveiot.android.customreminders.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.customreminders.R;
import com.coveiot.android.customreminders.ReminderType;
import com.coveiot.android.customreminders.activities.ActivityCustomReminders;
import com.coveiot.android.customreminders.activities.ActivityEditReminder;
import com.coveiot.android.customreminders.adapter.AdapterReminderListMain;
import com.coveiot.android.customreminders.listeners.EditReminderListListener;
import com.coveiot.android.customreminders.listeners.ResultListener;
import com.coveiot.android.customreminders.model.CustomReminder;
import com.coveiot.android.customreminders.model.CustomReminderDataHolder;
import com.coveiot.android.customreminders.model.MainReminderListDataHolder;
import com.coveiot.android.customreminders.preference.PreferenceManagerCustomReminders;
import com.coveiot.android.customreminders.utils.CustomReminderConstants;
import com.coveiot.android.customreminders.utils.ReminderHelper;
import com.coveiot.android.customreminders.utils.ViewModelFactory;
import com.coveiot.android.customreminders.viewmodel.RemindersListViewModel;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.BottomSheetDialogOneButtonTitleMessage;
import com.coveiot.android.theme.BottomSheetDialogTwoButtons;
import com.coveiot.utils.utility.AppUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class FragmentRemindersList extends BaseFragment implements EditReminderListListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @Nullable
    public BottomSheetDialogOneButtonTitleMessage m;
    @Nullable
    public BottomSheetDialogOneButtonTitleMessage n;
    public RemindersListViewModel viewModel;

    /* loaded from: classes3.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final FragmentRemindersList newInstance() {
            return new FragmentRemindersList();
        }
    }

    public static final void l(FragmentRemindersList this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentActivity activity = this$0.getActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.customreminders.activities.ActivityCustomReminders");
        ((ActivityCustomReminders) activity).loadAddReminderScreen();
    }

    public static /* synthetic */ void n(FragmentRemindersList fragmentRemindersList, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        fragmentRemindersList.m(z);
    }

    @JvmStatic
    @NotNull
    public static final FragmentRemindersList newInstance() {
        return Companion.newInstance();
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

    @Nullable
    public final BottomSheetDialogOneButtonTitleMessage getBottomSheetErrorDialog() {
        return this.m;
    }

    @Nullable
    public final BottomSheetDialogOneButtonTitleMessage getBottomSheetSuccessDialog() {
        return this.n;
    }

    @NotNull
    public final RemindersListViewModel getViewModel() {
        RemindersListViewModel remindersListViewModel = this.viewModel;
        if (remindersListViewModel != null) {
            return remindersListViewModel;
        }
        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        return null;
    }

    public final void m(boolean z) {
        if (this.n == null) {
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            String string = getString(R.string.success);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.success)");
            String string2 = getString(R.string.setting_saved_successfully);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.setting_saved_successfully)");
            BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = new BottomSheetDialogOneButtonTitleMessage(requireContext, string, string2);
            this.n = bottomSheetDialogOneButtonTitleMessage;
            Intrinsics.checkNotNull(bottomSheetDialogOneButtonTitleMessage);
            String string3 = getString(R.string.ok);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.ok)");
            bottomSheetDialogOneButtonTitleMessage.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.customreminders.fragments.FragmentRemindersList$showSuccessDialog$1
                @Override // android.view.View.OnClickListener
                public void onClick(@Nullable View view) {
                    BottomSheetDialogOneButtonTitleMessage bottomSheetSuccessDialog = FragmentRemindersList.this.getBottomSheetSuccessDialog();
                    Intrinsics.checkNotNull(bottomSheetSuccessDialog);
                    bottomSheetSuccessDialog.dismiss();
                    FragmentActivity activity = FragmentRemindersList.this.getActivity();
                    Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.customreminders.activities.ActivityCustomReminders");
                    ((ActivityCustomReminders) activity).loadData();
                }
            });
        }
        BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage2 = this.n;
        Intrinsics.checkNotNull(bottomSheetDialogOneButtonTitleMessage2);
        if (bottomSheetDialogOneButtonTitleMessage2.isShowing()) {
            return;
        }
        BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage3 = this.n;
        Intrinsics.checkNotNull(bottomSheetDialogOneButtonTitleMessage3);
        bottomSheetDialogOneButtonTitleMessage3.show();
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_reminders_list, viewGroup, false);
    }

    @Override // com.coveiot.android.customreminders.listeners.EditReminderListListener
    public void onDeleteReminder(final int i, @NotNull ReminderType reminderType) {
        Intrinsics.checkNotNullParameter(reminderType, "reminderType");
        if (!AppUtils.isNetConnected(requireContext())) {
            Toast.makeText(requireContext(), R.string.noconnection, 0).show();
        } else if (BleApiManager.getInstance(requireContext()).getBleApi().getConnectionStatus() != ConnectionStatus.CONNECTED) {
            showWatchDisconnectedDialog();
        } else {
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            String string = getString(R.string.are_you_sure_you_want_to_delete);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.are_y…_sure_you_want_to_delete)");
            final BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = new BottomSheetDialogTwoButtons(requireContext, "", string, false, 8, null);
            String string2 = getString(R.string.cancel);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.cancel)");
            bottomSheetDialogTwoButtons.setNegativeButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.customreminders.fragments.FragmentRemindersList$onDeleteReminder$1
                @Override // android.view.View.OnClickListener
                public void onClick(@Nullable View view) {
                    BottomSheetDialogTwoButtons.this.dismiss();
                }
            });
            String string3 = getString(R.string.delete);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.delete)");
            bottomSheetDialogTwoButtons.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.customreminders.fragments.FragmentRemindersList$onDeleteReminder$2
                @Override // android.view.View.OnClickListener
                public void onClick(@Nullable View view) {
                    BottomSheetDialogTwoButtons.this.dismiss();
                    PreferenceManagerCustomReminders.Companion companion = PreferenceManagerCustomReminders.Companion;
                    Context requireContext2 = this.requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                    List<CustomReminder> reminders = companion.getReminders(requireContext2);
                    final List mutableList = reminders != null ? CollectionsKt___CollectionsKt.toMutableList((Collection) reminders) : null;
                    if (AppUtils.isEmpty(mutableList)) {
                        return;
                    }
                    if (mutableList != null) {
                        CustomReminder customReminder = (CustomReminder) mutableList.remove(i);
                    }
                    BaseFragment.showProgress$default(this, false, 1, null);
                    ReminderHelper.Companion companion2 = ReminderHelper.Companion;
                    Context requireContext3 = this.requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
                    Intrinsics.checkNotNull(mutableList);
                    final FragmentRemindersList fragmentRemindersList = this;
                    companion2.getInstance(requireContext3).sendRemindersToWatch((ArrayList) mutableList, new ResultListener() { // from class: com.coveiot.android.customreminders.fragments.FragmentRemindersList$onDeleteReminder$2$onClick$1
                        @Override // com.coveiot.android.customreminders.listeners.ResultListener
                        public void onError(@Nullable String str) {
                            FragmentRemindersList.this.dismissProgress();
                            FragmentRemindersList.this.showErrorDialog();
                        }

                        @Override // com.coveiot.android.customreminders.listeners.ResultListener
                        public void onSuccess() {
                            FragmentRemindersList.this.dismissProgress();
                            FragmentRemindersList fragmentRemindersList2 = FragmentRemindersList.this;
                            List<CustomReminder> list = mutableList;
                            boolean z = false;
                            if (list != null && list.size() == 0) {
                                z = true;
                            }
                            fragmentRemindersList2.m(z);
                        }
                    });
                }
            });
            bottomSheetDialogTwoButtons.show();
        }
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.coveiot.android.customreminders.listeners.EditReminderListListener
    public void onEditReminder(int i, @NotNull ReminderType reminderType) {
        Intrinsics.checkNotNullParameter(reminderType, "reminderType");
        Intent intent = new Intent(requireActivity(), ActivityEditReminder.class);
        CustomReminderConstants.Companion companion = CustomReminderConstants.Companion;
        String extra_custom_reminder_object = companion.getEXTRA_CUSTOM_REMINDER_OBJECT();
        List<CustomReminder> value = getViewModel().getRemindersListLiveData().getValue();
        CustomReminder customReminder = value != null ? value.get(i) : null;
        Intrinsics.checkNotNull(customReminder);
        intent.putExtra(extra_custom_reminder_object, customReminder);
        intent.putExtra(companion.getEXTRA_REMINDER_POSITION(), i);
        intent.putExtra(companion.getEXTRA_REMINDER_TYPE(), reminderType);
        intent.putExtra(companion.getEXTRA_SHOULD_ADD_TO_MAIN_LIST(), true);
        startActivityForResult(intent, 20220);
    }

    @Override // com.coveiot.android.customreminders.listeners.EditReminderListListener
    public void onReminderStatusChange(int i, boolean z) {
        if (!AppUtils.isNetConnected(requireContext())) {
            Toast.makeText(requireContext(), R.string.noconnection, 0).show();
            getViewModel().loadRemindersList();
        } else if (BleApiManager.getInstance(requireContext()).getBleApi().getConnectionStatus() != ConnectionStatus.CONNECTED) {
            showWatchDisconnectedDialog();
            getViewModel().loadRemindersList();
        } else {
            PreferenceManagerCustomReminders.Companion companion = PreferenceManagerCustomReminders.Companion;
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            List<CustomReminder> reminders = companion.getReminders(requireContext);
            List mutableList = reminders != null ? CollectionsKt___CollectionsKt.toMutableList((Collection) reminders) : null;
            if (AppUtils.isEmpty(mutableList)) {
                return;
            }
            CustomReminder customReminder = mutableList != null ? (CustomReminder) mutableList.get(i) : null;
            if (customReminder != null) {
                customReminder.setReminderOn(z);
            }
            if (mutableList != null) {
                Intrinsics.checkNotNull(customReminder);
                CustomReminder customReminder2 = (CustomReminder) mutableList.set(i, customReminder);
            }
            BaseFragment.showProgress$default(this, false, 1, null);
            ReminderHelper.Companion companion2 = ReminderHelper.Companion;
            Context requireContext2 = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
            Intrinsics.checkNotNull(mutableList);
            companion2.getInstance(requireContext2).sendRemindersToWatch((ArrayList) mutableList, new ResultListener() { // from class: com.coveiot.android.customreminders.fragments.FragmentRemindersList$onReminderStatusChange$1
                @Override // com.coveiot.android.customreminders.listeners.ResultListener
                public void onError(@Nullable String str) {
                    FragmentRemindersList.this.getViewModel().loadRemindersList();
                    FragmentRemindersList.this.dismissProgress();
                    FragmentRemindersList.this.showErrorDialog();
                }

                @Override // com.coveiot.android.customreminders.listeners.ResultListener
                public void onSuccess() {
                    FragmentRemindersList.this.dismissProgress();
                    FragmentRemindersList.n(FragmentRemindersList.this, false, 1, null);
                }
            });
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        getViewModel().loadRemindersList();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        ((Button) _$_findCachedViewById(R.id.add_reminder)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.customreminders.fragments.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentRemindersList.l(FragmentRemindersList.this, view2);
            }
        });
        ((RecyclerView) _$_findCachedViewById(R.id.rcv_reminder_list)).setLayoutManager(new LinearLayoutManager(requireContext()));
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        ViewModel viewModel = new ViewModelProvider(this, new ViewModelFactory(requireContext)).get(RemindersListViewModel.class);
        Intrinsics.checkNotNullExpressionValue(viewModel, "ViewModelProvider(\n     …istViewModel::class.java)");
        setViewModel((RemindersListViewModel) viewModel);
        getViewModel().getRemindersListLiveData().observe(getViewLifecycleOwner(), new Observer<List<? extends CustomReminder>>() { // from class: com.coveiot.android.customreminders.fragments.FragmentRemindersList$onViewCreated$2
            @Override // androidx.lifecycle.Observer
            public void onChanged(@Nullable List<? extends CustomReminder> list) {
                if (list == null || !(!list.isEmpty())) {
                    return;
                }
                ArrayList arrayList = new ArrayList();
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    arrayList.add(new CustomReminderDataHolder(list.get(i), i));
                }
                ArrayList arrayList2 = new ArrayList();
                ArrayList arrayList3 = new ArrayList();
                ArrayList arrayList4 = new ArrayList();
                ArrayList arrayList5 = new ArrayList();
                ArrayList arrayList6 = new ArrayList();
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    CustomReminderDataHolder customReminderDataHolder = (CustomReminderDataHolder) it.next();
                    if (Intrinsics.areEqual(customReminderDataHolder.getCustomReminder().getType(), ReminderType.MEDICINE.name())) {
                        arrayList2.add(customReminderDataHolder);
                    }
                    if (Intrinsics.areEqual(customReminderDataHolder.getCustomReminder().getType(), ReminderType.MEETING.name())) {
                        arrayList3.add(customReminderDataHolder);
                    }
                    if (Intrinsics.areEqual(customReminderDataHolder.getCustomReminder().getType(), ReminderType.OTHERS.name())) {
                        arrayList4.add(customReminderDataHolder);
                    }
                    if (Intrinsics.areEqual(customReminderDataHolder.getCustomReminder().getType(), ReminderType.DRINK.name())) {
                        arrayList5.add(customReminderDataHolder);
                    }
                    if (Intrinsics.areEqual(customReminderDataHolder.getCustomReminder().getType(), ReminderType.HAND_WASH.name())) {
                        arrayList6.add(customReminderDataHolder);
                    }
                }
                ArrayList<MainReminderListDataHolder> arrayList7 = new ArrayList<>();
                if (!arrayList2.isEmpty()) {
                    ReminderType reminderType = ReminderType.MEDICINE;
                    String string = FragmentRemindersList.this.getString(R.string.medicine);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.medicine)");
                    arrayList7.add(new MainReminderListDataHolder(reminderType, string, R.drawable.medicine_icon, arrayList2));
                }
                if (!arrayList5.isEmpty()) {
                    ReminderType reminderType2 = ReminderType.DRINK;
                    String string2 = FragmentRemindersList.this.getString(R.string.drink_water);
                    Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.drink_water)");
                    arrayList7.add(new MainReminderListDataHolder(reminderType2, string2, R.drawable.drink_water_icon, arrayList5));
                }
                if (!arrayList3.isEmpty()) {
                    ReminderType reminderType3 = ReminderType.MEETING;
                    String string3 = FragmentRemindersList.this.getString(R.string.meeting);
                    Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.meeting)");
                    arrayList7.add(new MainReminderListDataHolder(reminderType3, string3, R.drawable.meeting_icon, arrayList3));
                }
                if (!arrayList6.isEmpty()) {
                    ReminderType reminderType4 = ReminderType.HAND_WASH;
                    String string4 = FragmentRemindersList.this.getString(R.string.hand_wash);
                    Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.hand_wash)");
                    arrayList7.add(new MainReminderListDataHolder(reminderType4, string4, R.drawable.handwash_icon, arrayList6));
                }
                if (!arrayList4.isEmpty()) {
                    ReminderType reminderType5 = ReminderType.OTHERS;
                    String string5 = FragmentRemindersList.this.getString(R.string.other);
                    Intrinsics.checkNotNullExpressionValue(string5, "getString(R.string.other)");
                    arrayList7.add(new MainReminderListDataHolder(reminderType5, string5, R.drawable.other_icon, arrayList4));
                }
                AdapterReminderListMain adapterReminderListMain = new AdapterReminderListMain(FragmentRemindersList.this);
                adapterReminderListMain.setMainReminderListDataHolder(arrayList7);
                ((RecyclerView) FragmentRemindersList.this._$_findCachedViewById(R.id.rcv_reminder_list)).setAdapter(adapterReminderListMain);
            }
        });
    }

    public final void setBottomSheetErrorDialog(@Nullable BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage) {
        this.m = bottomSheetDialogOneButtonTitleMessage;
    }

    public final void setBottomSheetSuccessDialog(@Nullable BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage) {
        this.n = bottomSheetDialogOneButtonTitleMessage;
    }

    public final void setViewModel(@NotNull RemindersListViewModel remindersListViewModel) {
        Intrinsics.checkNotNullParameter(remindersListViewModel, "<set-?>");
        this.viewModel = remindersListViewModel;
    }

    public final void showErrorDialog() {
        if (this.m == null) {
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            String string = getString(R.string.failed);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.failed)");
            String string2 = getString(R.string.setting_could_not_be_saved);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.setting_could_not_be_saved)");
            BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = new BottomSheetDialogOneButtonTitleMessage(requireContext, string, string2);
            this.m = bottomSheetDialogOneButtonTitleMessage;
            Intrinsics.checkNotNull(bottomSheetDialogOneButtonTitleMessage);
            String string3 = getString(R.string.ok);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.ok)");
            bottomSheetDialogOneButtonTitleMessage.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.customreminders.fragments.FragmentRemindersList$showErrorDialog$1
                @Override // android.view.View.OnClickListener
                public void onClick(@Nullable View view) {
                    BottomSheetDialogOneButtonTitleMessage bottomSheetErrorDialog = FragmentRemindersList.this.getBottomSheetErrorDialog();
                    Intrinsics.checkNotNull(bottomSheetErrorDialog);
                    bottomSheetErrorDialog.dismiss();
                }
            });
        }
        BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage2 = this.m;
        Intrinsics.checkNotNull(bottomSheetDialogOneButtonTitleMessage2);
        if (bottomSheetDialogOneButtonTitleMessage2.isShowing()) {
            return;
        }
        BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage3 = this.m;
        Intrinsics.checkNotNull(bottomSheetDialogOneButtonTitleMessage3);
        bottomSheetDialogOneButtonTitleMessage3.show();
    }
}
