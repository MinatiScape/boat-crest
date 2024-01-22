package com.coveiot.android.customreminders.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.customreminders.R;
import com.coveiot.android.customreminders.ReminderType;
import com.coveiot.android.customreminders.activities.ActivityAddCustomReminderNew2;
import com.coveiot.android.customreminders.adapter.AdapterReminderSelection;
import com.coveiot.android.customreminders.model.ReminderTypeModel;
import com.coveiot.android.customreminders.utils.ViewModelFactory;
import com.coveiot.android.customreminders.viewmodel.AddCustomReminderViewModel;
import com.coveiot.android.theme.BottomSheetDialogOneButtonTitleMessage;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class FragmentNoReminders extends Fragment implements AdapterReminderSelection.ReminderTypeSelectionListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @Nullable
    public BottomSheetDialogOneButtonTitleMessage h;
    public AddCustomReminderViewModel i;
    @Nullable
    public Boolean j;

    /* loaded from: classes3.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ FragmentNoReminders newInstance$default(Companion companion, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                z = false;
            }
            return companion.newInstance(z);
        }

        @NotNull
        public final FragmentNoReminders newInstance(boolean z) {
            FragmentNoReminders fragmentNoReminders = new FragmentNoReminders();
            Bundle bundle = new Bundle();
            bundle.putBoolean("isFromAdd", z);
            fragmentNoReminders.setArguments(bundle);
            return fragmentNoReminders;
        }
    }

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

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

    public final void b() {
        if (this.h == null) {
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            String string = getString(R.string.max_reminders_added);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.max_reminders_added)");
            String string2 = getString(R.string.max_reminders_msg);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.max_reminders_msg)");
            BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = new BottomSheetDialogOneButtonTitleMessage(requireContext, string, string2);
            this.h = bottomSheetDialogOneButtonTitleMessage;
            Intrinsics.checkNotNull(bottomSheetDialogOneButtonTitleMessage);
            String string3 = getString(R.string.ok);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.ok)");
            bottomSheetDialogOneButtonTitleMessage.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.customreminders.fragments.FragmentNoReminders$showMaxRemindersAddedDialog$1
                @Override // android.view.View.OnClickListener
                public void onClick(@Nullable View view) {
                    BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage2;
                    bottomSheetDialogOneButtonTitleMessage2 = FragmentNoReminders.this.h;
                    Intrinsics.checkNotNull(bottomSheetDialogOneButtonTitleMessage2);
                    bottomSheetDialogOneButtonTitleMessage2.dismiss();
                }
            });
        }
        BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage2 = this.h;
        Intrinsics.checkNotNull(bottomSheetDialogOneButtonTitleMessage2);
        if (bottomSheetDialogOneButtonTitleMessage2.isShowing()) {
            return;
        }
        BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage3 = this.h;
        Intrinsics.checkNotNull(bottomSheetDialogOneButtonTitleMessage3);
        bottomSheetDialogOneButtonTitleMessage3.show();
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        this.j = arguments != null ? Boolean.valueOf(arguments.getBoolean("isFromAdd")) : null;
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_no_reminders, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.coveiot.android.customreminders.adapter.AdapterReminderSelection.ReminderTypeSelectionListener
    public void onReminderTypeSelected(@NotNull ReminderType reminderType) {
        Intrinsics.checkNotNullParameter(reminderType, "reminderType");
        AddCustomReminderViewModel addCustomReminderViewModel = this.i;
        if (addCustomReminderViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("addCustomReminderViewModel");
            addCustomReminderViewModel = null;
        }
        if (addCustomReminderViewModel.canAddMoreReminders(reminderType)) {
            Intent intent = new Intent(requireContext(), ActivityAddCustomReminderNew2.class);
            intent.putExtra("reminder_type", reminderType);
            startActivity(intent);
            return;
        }
        b();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        ViewModel viewModel = new ViewModelProvider(this, new ViewModelFactory(requireContext)).get(AddCustomReminderViewModel.class);
        Intrinsics.checkNotNullExpressionValue(viewModel, "ViewModelProvider(this, …derViewModel::class.java)");
        this.i = (AddCustomReminderViewModel) viewModel;
        Boolean bool = this.j;
        Intrinsics.checkNotNull(bool);
        if (bool.booleanValue()) {
            ((TextView) _$_findCachedViewById(R.id.tv_set_reminder_label)).setText(R.string.add_reminder);
        }
        AdapterReminderSelection adapterReminderSelection = new AdapterReminderSelection(this);
        int i = R.drawable.medicine_icon;
        String string = getString(R.string.medicine);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.medicine)");
        adapterReminderSelection.addReminderType(new ReminderTypeModel(i, string, ReminderType.MEDICINE));
        int i2 = R.drawable.drink_water_icon;
        String string2 = getString(R.string.drink_water);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.drink_water)");
        adapterReminderSelection.addReminderType(new ReminderTypeModel(i2, string2, ReminderType.DRINK));
        int i3 = R.drawable.meeting_icon;
        String string3 = getString(R.string.meeting);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.meeting)");
        adapterReminderSelection.addReminderType(new ReminderTypeModel(i3, string3, ReminderType.MEETING));
        int i4 = R.drawable.handwash_icon;
        String string4 = getString(R.string.hand_wash);
        Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.hand_wash)");
        adapterReminderSelection.addReminderType(new ReminderTypeModel(i4, string4, ReminderType.HAND_WASH));
        int i5 = R.drawable.other_icon;
        String string5 = getString(R.string.other);
        Intrinsics.checkNotNullExpressionValue(string5, "getString(R.string.other)");
        adapterReminderSelection.addReminderType(new ReminderTypeModel(i5, string5, ReminderType.OTHERS));
        int i6 = R.id.rcv_custom_reminder_selection;
        ((RecyclerView) _$_findCachedViewById(i6)).setLayoutManager(new LinearLayoutManager(requireContext()));
        ((RecyclerView) _$_findCachedViewById(i6)).setAdapter(adapterReminderSelection);
    }
}
