package com.coveiot.android.customreminders.activities;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.customreminders.R;
import com.coveiot.android.customreminders.ReminderType;
import com.coveiot.android.customreminders.adapter.AdapterReminderListAdd;
import com.coveiot.android.customreminders.fragments.HandWashFragmentNew;
import com.coveiot.android.customreminders.fragments.MedicineReminderFragment;
import com.coveiot.android.customreminders.fragments.MedicineReminderFragmentNew;
import com.coveiot.android.customreminders.listeners.AddReminderListener;
import com.coveiot.android.customreminders.listeners.EditReminderListListener;
import com.coveiot.android.customreminders.listeners.ResultListener;
import com.coveiot.android.customreminders.model.CustomReminder;
import com.coveiot.android.customreminders.model.DrinkWaterReminder;
import com.coveiot.android.customreminders.model.HandWashReminder;
import com.coveiot.android.customreminders.model.MedicineReminder;
import com.coveiot.android.customreminders.model.MeetingReminder;
import com.coveiot.android.customreminders.model.OtherReminder;
import com.coveiot.android.customreminders.utils.CustomReminderConstants;
import com.coveiot.android.customreminders.utils.ViewModelFactory;
import com.coveiot.android.customreminders.viewmodel.AddCustomReminderViewModel;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.BottomSheetDialogImageTitleMessage;
import com.coveiot.android.theme.BottomSheetDialogOneButtonTitleMessage;
import com.coveiot.android.theme.BottomSheetDialogTwoButtons;
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.utils.utility.AppUtils;
import com.google.android.gms.fitness.FitnessActivities;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class ActivityAddCustomReminderNew2 extends BaseActivity implements AddReminderListener, EditReminderListListener, MedicineReminderFragmentNew.SaveButtonListener {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public AddCustomReminderViewModel p;
    @Nullable
    public BottomSheetDialogOneButtonTitleMessage q;
    @Nullable
    public BottomSheetDialogOneButtonTitleMessage r;
    @Nullable
    public BottomSheetDialogOneButtonTitleMessage s;
    @Nullable
    public BottomSheetDialogImageTitleMessage t;
    @Nullable
    public BottomSheetDialogTwoButtons u;

    /* loaded from: classes3.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ReminderType.values().length];
            try {
                iArr[ReminderType.MEDICINE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ReminderType.OTHERS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[ReminderType.MEETING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[ReminderType.HAND_WASH.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[ReminderType.DRINK.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final void w(ActivityAddCustomReminderNew2 this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AddCustomReminderViewModel addCustomReminderViewModel = this$0.p;
        AddCustomReminderViewModel addCustomReminderViewModel2 = null;
        if (addCustomReminderViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("addCustomReminderViewModel");
            addCustomReminderViewModel = null;
        }
        if (addCustomReminderViewModel.isCurrentReminderInitialized()) {
            AddCustomReminderViewModel addCustomReminderViewModel3 = this$0.p;
            if (addCustomReminderViewModel3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("addCustomReminderViewModel");
                addCustomReminderViewModel3 = null;
            }
            if (AddCustomReminderViewModel.canAddMoreReminders$default(addCustomReminderViewModel3, null, 1, null)) {
                ((Button) this$0._$_findCachedViewById(R.id.add_reminder)).setVisibility(8);
                ((Button) this$0._$_findCachedViewById(R.id.saveButton)).setVisibility(0);
                int i = R.id.fragment_container_reminder;
                ((FrameLayout) this$0._$_findCachedViewById(i)).setVisibility(0);
                FragmentTransaction beginTransaction = this$0.getSupportFragmentManager().beginTransaction();
                MedicineReminderFragmentNew.Companion companion = MedicineReminderFragmentNew.Companion;
                AddCustomReminderViewModel addCustomReminderViewModel4 = this$0.p;
                if (addCustomReminderViewModel4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("addCustomReminderViewModel");
                } else {
                    addCustomReminderViewModel2 = addCustomReminderViewModel4;
                }
                beginTransaction.replace(i, MedicineReminderFragmentNew.Companion.newInstance$default(companion, addCustomReminderViewModel2.getCurrentReminderType(), null, 0, 6, null)).commit();
                return;
            }
            FragmentTransaction beginTransaction2 = this$0.getSupportFragmentManager().beginTransaction();
            int i2 = R.id.fragment_container_reminder;
            MedicineReminderFragmentNew.Companion companion2 = MedicineReminderFragmentNew.Companion;
            AddCustomReminderViewModel addCustomReminderViewModel5 = this$0.p;
            if (addCustomReminderViewModel5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("addCustomReminderViewModel");
            } else {
                addCustomReminderViewModel2 = addCustomReminderViewModel5;
            }
            beginTransaction2.replace(i2, MedicineReminderFragmentNew.Companion.newInstance$default(companion2, addCustomReminderViewModel2.getCurrentReminderType(), null, 0, 6, null)).commit();
            this$0.C();
        }
    }

    public static final void x(final ActivityAddCustomReminderNew2 this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (!AppUtils.isNetConnected(this$0)) {
            Toast.makeText(this$0, R.string.noconnection, 0).show();
            return;
        }
        ConnectionStatus connectionStatus = BleApiManager.getInstance(this$0).getBleApi().getConnectionStatus();
        ConnectionStatus connectionStatus2 = ConnectionStatus.CONNECTED;
        if (connectionStatus != connectionStatus2) {
            this$0.E();
            return;
        }
        if (BleApiManager.getInstance(this$0).getBleApi().getConnectionStatus() == connectionStatus2) {
            AnalyticsLog analyticsLog = new AnalyticsLog();
            analyticsLog.setEventName(FirebaseEventParams.EventName.CV_REMINDER_SAVE.getValue());
            analyticsLog.setCVPrevScreenName(FirebaseEventParams.ScreenName.REMINDERS.getValue());
            analyticsLog.setCVScreenName(FirebaseEventParams.ScreenName.ADD_REMINDER.getValue());
            HashMap<String, String> hashMap = new HashMap<>();
            String value = FirebaseEventParams.MetaData.CV_REMINDER_TYPE.getValue();
            String lowerCase = this$0.v().toLowerCase();
            Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase()");
            hashMap.put(value, lowerCase);
            analyticsLog.setMapData(hashMap);
            CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        }
        this$0.showProgress();
        this$0.t();
        AddCustomReminderViewModel addCustomReminderViewModel = this$0.p;
        if (addCustomReminderViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("addCustomReminderViewModel");
            addCustomReminderViewModel = null;
        }
        addCustomReminderViewModel.saveReminders(new ResultListener() { // from class: com.coveiot.android.customreminders.activities.ActivityAddCustomReminderNew2$initClickListeners$2$1
            @Override // com.coveiot.android.customreminders.listeners.ResultListener
            public void onError(@Nullable String str) {
                ActivityAddCustomReminderNew2.this.dismissProgress();
                ActivityAddCustomReminderNew2.this.showErrorDialog();
            }

            @Override // com.coveiot.android.customreminders.listeners.ResultListener
            public void onSuccess() {
                ActivityAddCustomReminderNew2.this.dismissProgress();
                ActivityAddCustomReminderNew2.this.showSuccessDialog();
            }
        });
    }

    public static final void y(ActivityAddCustomReminderNew2 this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    public final void A() {
        AddCustomReminderViewModel addCustomReminderViewModel = this.p;
        if (addCustomReminderViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("addCustomReminderViewModel");
            addCustomReminderViewModel = null;
        }
        addCustomReminderViewModel.getLiveDataMeetingRemindersList().observe(this, new Observer<ArrayList<MeetingReminder>>() { // from class: com.coveiot.android.customreminders.activities.ActivityAddCustomReminderNew2$loadMeetingReminderList$1
            @Override // androidx.lifecycle.Observer
            public void onChanged(@Nullable ArrayList<MeetingReminder> arrayList) {
                AddCustomReminderViewModel addCustomReminderViewModel2;
                if (!AppUtils.isEmpty(arrayList)) {
                    ActivityAddCustomReminderNew2 activityAddCustomReminderNew2 = ActivityAddCustomReminderNew2.this;
                    int i = R.id.recycler_view_reminders_list_temp;
                    ((RecyclerView) activityAddCustomReminderNew2._$_findCachedViewById(i)).setVisibility(0);
                    ((Button) ActivityAddCustomReminderNew2.this._$_findCachedViewById(R.id.add_reminder)).setVisibility(0);
                    ((Button) ActivityAddCustomReminderNew2.this._$_findCachedViewById(R.id.saveButton)).setVisibility(0);
                    ActivityAddCustomReminderNew2 activityAddCustomReminderNew22 = ActivityAddCustomReminderNew2.this;
                    AdapterReminderListAdd adapterReminderListAdd = new AdapterReminderListAdd(activityAddCustomReminderNew22, activityAddCustomReminderNew22);
                    Intrinsics.checkNotNull(arrayList, "null cannot be cast to non-null type kotlin.collections.List<com.coveiot.android.customreminders.model.MeetingReminder>");
                    adapterReminderListAdd.setReminderList(arrayList);
                    ((RecyclerView) ActivityAddCustomReminderNew2.this._$_findCachedViewById(i)).setAdapter(adapterReminderListAdd);
                    ((FrameLayout) ActivityAddCustomReminderNew2.this._$_findCachedViewById(R.id.fragment_container_reminder)).setVisibility(8);
                    return;
                }
                ((RecyclerView) ActivityAddCustomReminderNew2.this._$_findCachedViewById(R.id.recycler_view_reminders_list_temp)).setVisibility(8);
                ((Button) ActivityAddCustomReminderNew2.this._$_findCachedViewById(R.id.add_reminder)).setVisibility(8);
                ((Button) ActivityAddCustomReminderNew2.this._$_findCachedViewById(R.id.saveButton)).setVisibility(8);
                ActivityAddCustomReminderNew2 activityAddCustomReminderNew23 = ActivityAddCustomReminderNew2.this;
                int i2 = R.id.fragment_container_reminder;
                ((FrameLayout) activityAddCustomReminderNew23._$_findCachedViewById(i2)).setVisibility(0);
                FragmentTransaction beginTransaction = ActivityAddCustomReminderNew2.this.getSupportFragmentManager().beginTransaction();
                MedicineReminderFragment.Companion companion = MedicineReminderFragment.Companion;
                addCustomReminderViewModel2 = ActivityAddCustomReminderNew2.this.p;
                if (addCustomReminderViewModel2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("addCustomReminderViewModel");
                    addCustomReminderViewModel2 = null;
                }
                beginTransaction.replace(i2, MedicineReminderFragment.Companion.newInstance$default(companion, addCustomReminderViewModel2.getCurrentReminderType(), null, 0, 6, null)).commit();
            }
        });
    }

    public final void B() {
        AddCustomReminderViewModel addCustomReminderViewModel = this.p;
        if (addCustomReminderViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("addCustomReminderViewModel");
            addCustomReminderViewModel = null;
        }
        addCustomReminderViewModel.getLiveDataOtherRemindersList().observe(this, new Observer<ArrayList<OtherReminder>>() { // from class: com.coveiot.android.customreminders.activities.ActivityAddCustomReminderNew2$loadOtherReminderList$1
            @Override // androidx.lifecycle.Observer
            public void onChanged(@Nullable ArrayList<OtherReminder> arrayList) {
                AddCustomReminderViewModel addCustomReminderViewModel2;
                if (!AppUtils.isEmpty(arrayList)) {
                    ActivityAddCustomReminderNew2 activityAddCustomReminderNew2 = ActivityAddCustomReminderNew2.this;
                    int i = R.id.recycler_view_reminders_list_temp;
                    ((RecyclerView) activityAddCustomReminderNew2._$_findCachedViewById(i)).setVisibility(0);
                    ((Button) ActivityAddCustomReminderNew2.this._$_findCachedViewById(R.id.add_reminder)).setVisibility(0);
                    ((Button) ActivityAddCustomReminderNew2.this._$_findCachedViewById(R.id.saveButton)).setVisibility(0);
                    ActivityAddCustomReminderNew2 activityAddCustomReminderNew22 = ActivityAddCustomReminderNew2.this;
                    AdapterReminderListAdd adapterReminderListAdd = new AdapterReminderListAdd(activityAddCustomReminderNew22, activityAddCustomReminderNew22);
                    Intrinsics.checkNotNull(arrayList, "null cannot be cast to non-null type kotlin.collections.List<com.coveiot.android.customreminders.model.OtherReminder>");
                    adapterReminderListAdd.setReminderList(arrayList);
                    ((RecyclerView) ActivityAddCustomReminderNew2.this._$_findCachedViewById(i)).setAdapter(adapterReminderListAdd);
                    ((FrameLayout) ActivityAddCustomReminderNew2.this._$_findCachedViewById(R.id.fragment_container_reminder)).setVisibility(8);
                    return;
                }
                ((RecyclerView) ActivityAddCustomReminderNew2.this._$_findCachedViewById(R.id.recycler_view_reminders_list_temp)).setVisibility(8);
                ((Button) ActivityAddCustomReminderNew2.this._$_findCachedViewById(R.id.add_reminder)).setVisibility(8);
                ((Button) ActivityAddCustomReminderNew2.this._$_findCachedViewById(R.id.saveButton)).setVisibility(8);
                ActivityAddCustomReminderNew2 activityAddCustomReminderNew23 = ActivityAddCustomReminderNew2.this;
                int i2 = R.id.fragment_container_reminder;
                ((FrameLayout) activityAddCustomReminderNew23._$_findCachedViewById(i2)).setVisibility(0);
                FragmentTransaction beginTransaction = ActivityAddCustomReminderNew2.this.getSupportFragmentManager().beginTransaction();
                MedicineReminderFragment.Companion companion = MedicineReminderFragment.Companion;
                addCustomReminderViewModel2 = ActivityAddCustomReminderNew2.this.p;
                if (addCustomReminderViewModel2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("addCustomReminderViewModel");
                    addCustomReminderViewModel2 = null;
                }
                beginTransaction.replace(i2, MedicineReminderFragment.Companion.newInstance$default(companion, addCustomReminderViewModel2.getCurrentReminderType(), null, 0, 6, null)).commit();
            }
        });
    }

    public final void C() {
        if (this.q == null) {
            String string = getString(R.string.max_reminders_added);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.max_reminders_added)");
            String string2 = getString(R.string.max_reminders_msg);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.max_reminders_msg)");
            BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = new BottomSheetDialogOneButtonTitleMessage(this, string, string2);
            this.q = bottomSheetDialogOneButtonTitleMessage;
            Intrinsics.checkNotNull(bottomSheetDialogOneButtonTitleMessage);
            String string3 = getString(R.string.ok);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.ok)");
            bottomSheetDialogOneButtonTitleMessage.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.customreminders.activities.ActivityAddCustomReminderNew2$showMaxRemindersAddedDialog$1
                @Override // android.view.View.OnClickListener
                public void onClick(@Nullable View view) {
                    BottomSheetDialogOneButtonTitleMessage bottomSheetMaxLimitDialog = ActivityAddCustomReminderNew2.this.getBottomSheetMaxLimitDialog();
                    Intrinsics.checkNotNull(bottomSheetMaxLimitDialog);
                    bottomSheetMaxLimitDialog.dismiss();
                }
            });
        }
        BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage2 = this.q;
        Intrinsics.checkNotNull(bottomSheetDialogOneButtonTitleMessage2);
        if (bottomSheetDialogOneButtonTitleMessage2.isShowing()) {
            return;
        }
        BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage3 = this.q;
        Intrinsics.checkNotNull(bottomSheetDialogOneButtonTitleMessage3);
        bottomSheetDialogOneButtonTitleMessage3.show();
    }

    public final void D() {
        if (this.u == null) {
            String string = getString(R.string.confirmation);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.confirmation)");
            String string2 = getString(R.string.unsaved_changes);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.unsaved_changes)");
            BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = new BottomSheetDialogTwoButtons(this, string, string2, false, 8, null);
            this.u = bottomSheetDialogTwoButtons;
            Intrinsics.checkNotNull(bottomSheetDialogTwoButtons);
            String string3 = getString(R.string.ok);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.ok)");
            bottomSheetDialogTwoButtons.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.customreminders.activities.ActivityAddCustomReminderNew2$showSaveUnSavedChanges$1
                @Override // android.view.View.OnClickListener
                public void onClick(@Nullable View view) {
                    ((Button) ActivityAddCustomReminderNew2.this._$_findCachedViewById(R.id.saveButton)).performClick();
                    BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons2 = ActivityAddCustomReminderNew2.this.getBottomSheetDialogTwoButtons();
                    Intrinsics.checkNotNull(bottomSheetDialogTwoButtons2);
                    bottomSheetDialogTwoButtons2.dismiss();
                }
            });
            BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons2 = this.u;
            Intrinsics.checkNotNull(bottomSheetDialogTwoButtons2);
            String string4 = getString(R.string.cancel);
            Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.cancel)");
            bottomSheetDialogTwoButtons2.setNegativeButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.customreminders.activities.ActivityAddCustomReminderNew2$showSaveUnSavedChanges$2
                @Override // android.view.View.OnClickListener
                public void onClick(@Nullable View view) {
                    BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons3 = ActivityAddCustomReminderNew2.this.getBottomSheetDialogTwoButtons();
                    Intrinsics.checkNotNull(bottomSheetDialogTwoButtons3);
                    bottomSheetDialogTwoButtons3.dismiss();
                    ActivityAddCustomReminderNew2.this.finish();
                }
            });
        }
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons3 = this.u;
        Intrinsics.checkNotNull(bottomSheetDialogTwoButtons3);
        if (bottomSheetDialogTwoButtons3.isShowing()) {
            return;
        }
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons4 = this.u;
        Intrinsics.checkNotNull(bottomSheetDialogTwoButtons4);
        bottomSheetDialogTwoButtons4.show();
    }

    public final void E() {
        if (this.t == null) {
            Drawable drawable = getDrawable(R.drawable.watch_disconnected_icon);
            Intrinsics.checkNotNull(drawable);
            String string = getString(R.string.bluetooth_disconnected);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.bluetooth_disconnected)");
            String string2 = getString(R.string.please_connect_your_device);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.please_connect_your_device)");
            BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage = new BottomSheetDialogImageTitleMessage(this, drawable, string, string2, false);
            this.t = bottomSheetDialogImageTitleMessage;
            Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage);
            String string3 = getString(R.string.ok);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.ok)");
            bottomSheetDialogImageTitleMessage.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.customreminders.activities.ActivityAddCustomReminderNew2$showWatchDisconnectedDialog$1
                @Override // android.view.View.OnClickListener
                public void onClick(@Nullable View view) {
                    BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage2 = ActivityAddCustomReminderNew2.this.getBottomSheetDialogImageTitleMessage();
                    Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage2);
                    bottomSheetDialogImageTitleMessage2.dismiss();
                }
            });
        }
        BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage2 = this.t;
        Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage2);
        if (bottomSheetDialogImageTitleMessage2.isShowing()) {
            return;
        }
        BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage3 = this.t;
        Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage3);
        bottomSheetDialogImageTitleMessage3.show();
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
    public final BottomSheetDialogImageTitleMessage getBottomSheetDialogImageTitleMessage() {
        return this.t;
    }

    @Nullable
    public final BottomSheetDialogTwoButtons getBottomSheetDialogTwoButtons() {
        return this.u;
    }

    @Nullable
    public final BottomSheetDialogOneButtonTitleMessage getBottomSheetErrorDialog() {
        return this.r;
    }

    @Nullable
    public final BottomSheetDialogOneButtonTitleMessage getBottomSheetMaxLimitDialog() {
        return this.q;
    }

    @Nullable
    public final BottomSheetDialogOneButtonTitleMessage getBottomSheetSuccessDialog() {
        return this.s;
    }

    public final void initClickListeners() {
        ((Button) _$_findCachedViewById(R.id.add_reminder)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.customreminders.activities.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityAddCustomReminderNew2.w(ActivityAddCustomReminderNew2.this, view);
            }
        });
        ((Button) _$_findCachedViewById(R.id.saveButton)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.customreminders.activities.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityAddCustomReminderNew2.x(ActivityAddCustomReminderNew2.this, view);
            }
        });
    }

    public final void initToolbar() {
        ((TextView) findViewById(R.id.toolbar_title)).setText(u());
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.customreminders.activities.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityAddCustomReminderNew2.y(ActivityAddCustomReminderNew2.this, view);
            }
        });
    }

    @Override // com.coveiot.android.customreminders.fragments.MedicineReminderFragmentNew.SaveButtonListener
    public void isAllDetailsFilled(boolean z) {
        if (z) {
            ((Button) _$_findCachedViewById(R.id.saveButton)).setEnabled(true);
            return;
        }
        AddCustomReminderViewModel addCustomReminderViewModel = this.p;
        AddCustomReminderViewModel addCustomReminderViewModel2 = null;
        if (addCustomReminderViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("addCustomReminderViewModel");
            addCustomReminderViewModel = null;
        }
        if (AppUtils.isEmpty(addCustomReminderViewModel.getLiveDataMeetingRemindersList().getValue())) {
            AddCustomReminderViewModel addCustomReminderViewModel3 = this.p;
            if (addCustomReminderViewModel3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("addCustomReminderViewModel");
                addCustomReminderViewModel3 = null;
            }
            if (AppUtils.isEmpty(addCustomReminderViewModel3.getLiveDataOtherRemindersList().getValue())) {
                AddCustomReminderViewModel addCustomReminderViewModel4 = this.p;
                if (addCustomReminderViewModel4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("addCustomReminderViewModel");
                } else {
                    addCustomReminderViewModel2 = addCustomReminderViewModel4;
                }
                if (AppUtils.isEmpty(addCustomReminderViewModel2.getLiveDataMedicineRemindersList().getValue())) {
                    return;
                }
            }
        }
        ((Button) _$_findCachedViewById(R.id.saveButton)).setEnabled(true);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 101 && i2 == -1 && intent != null) {
            CustomReminderConstants.Companion companion = CustomReminderConstants.Companion;
            if (intent.hasExtra(companion.getEXTRA_CUSTOM_REMINDER_OBJECT())) {
                int intExtra = intent.getIntExtra(companion.getEXTRA_REMINDER_POSITION(), -1);
                Serializable serializableExtra = intent.getSerializableExtra(companion.getEXTRA_REMINDER_TYPE());
                Intrinsics.checkNotNull(serializableExtra, "null cannot be cast to non-null type com.coveiot.android.customreminders.ReminderType");
                ReminderType reminderType = (ReminderType) serializableExtra;
                Serializable serializableExtra2 = intent.getSerializableExtra(companion.getEXTRA_CUSTOM_REMINDER_OBJECT());
                Intrinsics.checkNotNull(serializableExtra2, "null cannot be cast to non-null type com.coveiot.android.customreminders.model.CustomReminder");
                CustomReminder customReminder = (CustomReminder) serializableExtra2;
                AddCustomReminderViewModel addCustomReminderViewModel = null;
                if (reminderType == ReminderType.MEDICINE) {
                    AddCustomReminderViewModel addCustomReminderViewModel2 = this.p;
                    if (addCustomReminderViewModel2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("addCustomReminderViewModel");
                        addCustomReminderViewModel2 = null;
                    }
                    ArrayList<MedicineReminder> value = addCustomReminderViewModel2.getLiveDataMedicineRemindersList().getValue();
                    if (value != null) {
                        value.set(intExtra, (MedicineReminder) customReminder);
                    }
                    AddCustomReminderViewModel addCustomReminderViewModel3 = this.p;
                    if (addCustomReminderViewModel3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("addCustomReminderViewModel");
                        addCustomReminderViewModel3 = null;
                    }
                    addCustomReminderViewModel3.getLiveDataMedicineRemindersList().setValue(value);
                }
                if (reminderType == ReminderType.MEETING) {
                    AddCustomReminderViewModel addCustomReminderViewModel4 = this.p;
                    if (addCustomReminderViewModel4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("addCustomReminderViewModel");
                        addCustomReminderViewModel4 = null;
                    }
                    ArrayList<MeetingReminder> value2 = addCustomReminderViewModel4.getLiveDataMeetingRemindersList().getValue();
                    if (value2 != null) {
                        value2.set(intExtra, (MeetingReminder) customReminder);
                    }
                    AddCustomReminderViewModel addCustomReminderViewModel5 = this.p;
                    if (addCustomReminderViewModel5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("addCustomReminderViewModel");
                        addCustomReminderViewModel5 = null;
                    }
                    addCustomReminderViewModel5.getLiveDataMeetingRemindersList().setValue(value2);
                }
                if (reminderType == ReminderType.OTHERS) {
                    AddCustomReminderViewModel addCustomReminderViewModel6 = this.p;
                    if (addCustomReminderViewModel6 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("addCustomReminderViewModel");
                        addCustomReminderViewModel6 = null;
                    }
                    ArrayList<OtherReminder> value3 = addCustomReminderViewModel6.getLiveDataOtherRemindersList().getValue();
                    if (value3 != null) {
                        value3.set(intExtra, (OtherReminder) customReminder);
                    }
                    AddCustomReminderViewModel addCustomReminderViewModel7 = this.p;
                    if (addCustomReminderViewModel7 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("addCustomReminderViewModel");
                    } else {
                        addCustomReminderViewModel = addCustomReminderViewModel7;
                    }
                    addCustomReminderViewModel.getLiveDataOtherRemindersList().setValue(value3);
                }
            }
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        AddCustomReminderViewModel addCustomReminderViewModel = this.p;
        AddCustomReminderViewModel addCustomReminderViewModel2 = null;
        if (addCustomReminderViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("addCustomReminderViewModel");
            addCustomReminderViewModel = null;
        }
        if (AppUtils.isEmpty(addCustomReminderViewModel.getLiveDataMeetingRemindersList().getValue())) {
            AddCustomReminderViewModel addCustomReminderViewModel3 = this.p;
            if (addCustomReminderViewModel3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("addCustomReminderViewModel");
                addCustomReminderViewModel3 = null;
            }
            if (AppUtils.isEmpty(addCustomReminderViewModel3.getLiveDataOtherRemindersList().getValue())) {
                AddCustomReminderViewModel addCustomReminderViewModel4 = this.p;
                if (addCustomReminderViewModel4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("addCustomReminderViewModel");
                    addCustomReminderViewModel4 = null;
                }
                if (AppUtils.isEmpty(addCustomReminderViewModel4.getLiveDataMedicineRemindersList().getValue())) {
                    AddCustomReminderViewModel addCustomReminderViewModel5 = this.p;
                    if (addCustomReminderViewModel5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("addCustomReminderViewModel");
                        addCustomReminderViewModel5 = null;
                    }
                    if (addCustomReminderViewModel5.isCurrentReminderInitialized()) {
                        AddCustomReminderViewModel addCustomReminderViewModel6 = this.p;
                        if (addCustomReminderViewModel6 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("addCustomReminderViewModel");
                            addCustomReminderViewModel6 = null;
                        }
                        if (addCustomReminderViewModel6.getCurrentReminderType() != ReminderType.DRINK) {
                            AddCustomReminderViewModel addCustomReminderViewModel7 = this.p;
                            if (addCustomReminderViewModel7 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("addCustomReminderViewModel");
                            } else {
                                addCustomReminderViewModel2 = addCustomReminderViewModel7;
                            }
                            if (addCustomReminderViewModel2.getCurrentReminderType() != ReminderType.HAND_WASH) {
                                Fragment findFragmentById = getSupportFragmentManager().findFragmentById(R.id.fragment_container_reminder);
                                Intrinsics.checkNotNull(findFragmentById, "null cannot be cast to non-null type com.coveiot.android.customreminders.fragments.MedicineReminderFragmentNew");
                                ((MedicineReminderFragmentNew) findFragmentById).onBackPressed();
                                return;
                            }
                        }
                        Fragment findFragmentById2 = getSupportFragmentManager().findFragmentById(R.id.fragment_container_reminder);
                        Intrinsics.checkNotNull(findFragmentById2, "null cannot be cast to non-null type com.coveiot.android.customreminders.fragments.HandWashFragmentNew");
                        ((HandWashFragmentNew) findFragmentById2).onBackPressed();
                        return;
                    }
                    super.onBackPressed();
                    return;
                }
            }
        }
        D();
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_add_custom_reminder_new2);
        ViewModel viewModel = new ViewModelProvider(this, new ViewModelFactory(this)).get(AddCustomReminderViewModel.class);
        Intrinsics.checkNotNullExpressionValue(viewModel, "ViewModelProvider(this, …derViewModel::class.java)");
        AddCustomReminderViewModel addCustomReminderViewModel = (AddCustomReminderViewModel) viewModel;
        this.p = addCustomReminderViewModel;
        AddCustomReminderViewModel addCustomReminderViewModel2 = null;
        if (addCustomReminderViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("addCustomReminderViewModel");
            addCustomReminderViewModel = null;
        }
        Serializable serializableExtra = getIntent().getSerializableExtra("reminder_type");
        Intrinsics.checkNotNull(serializableExtra, "null cannot be cast to non-null type com.coveiot.android.customreminders.ReminderType");
        addCustomReminderViewModel.setCurrentReminderType((ReminderType) serializableExtra);
        ((RecyclerView) _$_findCachedViewById(R.id.recycler_view_reminders_list_temp)).setLayoutManager(new LinearLayoutManager(this));
        initToolbar();
        initClickListeners();
        AddCustomReminderViewModel addCustomReminderViewModel3 = this.p;
        if (addCustomReminderViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("addCustomReminderViewModel");
            addCustomReminderViewModel3 = null;
        }
        if (addCustomReminderViewModel3.getCurrentReminderType() == ReminderType.MEDICINE) {
            z();
            ((Button) _$_findCachedViewById(R.id.add_reminder)).performClick();
        }
        AddCustomReminderViewModel addCustomReminderViewModel4 = this.p;
        if (addCustomReminderViewModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("addCustomReminderViewModel");
            addCustomReminderViewModel4 = null;
        }
        if (addCustomReminderViewModel4.getCurrentReminderType() == ReminderType.MEETING) {
            A();
            ((Button) _$_findCachedViewById(R.id.add_reminder)).performClick();
        }
        AddCustomReminderViewModel addCustomReminderViewModel5 = this.p;
        if (addCustomReminderViewModel5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("addCustomReminderViewModel");
            addCustomReminderViewModel5 = null;
        }
        if (addCustomReminderViewModel5.getCurrentReminderType() == ReminderType.OTHERS) {
            B();
            ((Button) _$_findCachedViewById(R.id.add_reminder)).performClick();
        }
        AddCustomReminderViewModel addCustomReminderViewModel6 = this.p;
        if (addCustomReminderViewModel6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("addCustomReminderViewModel");
            addCustomReminderViewModel6 = null;
        }
        if (addCustomReminderViewModel6.getCurrentReminderType() == ReminderType.DRINK) {
            FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
            int i = R.id.fragment_container_reminder;
            HandWashFragmentNew.Companion companion = HandWashFragmentNew.Companion;
            AddCustomReminderViewModel addCustomReminderViewModel7 = this.p;
            if (addCustomReminderViewModel7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("addCustomReminderViewModel");
                addCustomReminderViewModel7 = null;
            }
            beginTransaction.replace(i, HandWashFragmentNew.Companion.newInstance$default(companion, addCustomReminderViewModel7.getCurrentReminderType(), null, 0, 6, null)).commit();
            ((Button) _$_findCachedViewById(R.id.add_reminder)).setVisibility(8);
            ((Button) _$_findCachedViewById(R.id.saveButton)).setVisibility(8);
        }
        AddCustomReminderViewModel addCustomReminderViewModel8 = this.p;
        if (addCustomReminderViewModel8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("addCustomReminderViewModel");
            addCustomReminderViewModel8 = null;
        }
        if (addCustomReminderViewModel8.getCurrentReminderType() == ReminderType.HAND_WASH) {
            FragmentTransaction beginTransaction2 = getSupportFragmentManager().beginTransaction();
            int i2 = R.id.fragment_container_reminder;
            HandWashFragmentNew.Companion companion2 = HandWashFragmentNew.Companion;
            AddCustomReminderViewModel addCustomReminderViewModel9 = this.p;
            if (addCustomReminderViewModel9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("addCustomReminderViewModel");
            } else {
                addCustomReminderViewModel2 = addCustomReminderViewModel9;
            }
            beginTransaction2.replace(i2, HandWashFragmentNew.Companion.newInstance$default(companion2, addCustomReminderViewModel2.getCurrentReminderType(), null, 0, 6, null)).commit();
            ((Button) _$_findCachedViewById(R.id.add_reminder)).setVisibility(8);
            ((Button) _$_findCachedViewById(R.id.saveButton)).setVisibility(8);
        }
    }

    @Override // com.coveiot.android.customreminders.listeners.EditReminderListListener
    public void onDeleteReminder(int i, @NotNull ReminderType reminderType) {
        Intrinsics.checkNotNullParameter(reminderType, "reminderType");
        AddCustomReminderViewModel addCustomReminderViewModel = this.p;
        AddCustomReminderViewModel addCustomReminderViewModel2 = null;
        if (addCustomReminderViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("addCustomReminderViewModel");
            addCustomReminderViewModel = null;
        }
        addCustomReminderViewModel.removeReminderAt(i, reminderType);
        AddCustomReminderViewModel addCustomReminderViewModel3 = this.p;
        if (addCustomReminderViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("addCustomReminderViewModel");
        } else {
            addCustomReminderViewModel2 = addCustomReminderViewModel3;
        }
        if (addCustomReminderViewModel2.canAddMoreReminders(reminderType)) {
            ((Button) _$_findCachedViewById(R.id.add_reminder)).performClick();
        }
    }

    @Override // com.coveiot.android.customreminders.listeners.EditReminderListListener
    public void onEditReminder(int i, @NotNull ReminderType reminderType) {
        Intrinsics.checkNotNullParameter(reminderType, "reminderType");
        AddCustomReminderViewModel addCustomReminderViewModel = null;
        if (reminderType == ReminderType.MEDICINE) {
            AddCustomReminderViewModel addCustomReminderViewModel2 = this.p;
            if (addCustomReminderViewModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("addCustomReminderViewModel");
                addCustomReminderViewModel2 = null;
            }
            ArrayList<MedicineReminder> value = addCustomReminderViewModel2.getLiveDataMedicineRemindersList().getValue();
            if (value != null) {
                Intent intent = new Intent(this, ActivityEditReminder.class);
                CustomReminderConstants.Companion companion = CustomReminderConstants.Companion;
                intent.putExtra(companion.getEXTRA_CUSTOM_REMINDER_OBJECT(), value.get(i));
                intent.putExtra(companion.getEXTRA_REMINDER_POSITION(), i);
                intent.putExtra(companion.getEXTRA_REMINDER_TYPE(), reminderType);
                intent.putExtra(companion.getEXTRA_SHOULD_ADD_TO_MAIN_LIST(), false);
                startActivityForResult(intent, 101);
            }
        }
        if (reminderType == ReminderType.MEETING) {
            AddCustomReminderViewModel addCustomReminderViewModel3 = this.p;
            if (addCustomReminderViewModel3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("addCustomReminderViewModel");
                addCustomReminderViewModel3 = null;
            }
            ArrayList<MeetingReminder> value2 = addCustomReminderViewModel3.getLiveDataMeetingRemindersList().getValue();
            if (value2 != null) {
                Intent intent2 = new Intent(this, ActivityEditReminder.class);
                CustomReminderConstants.Companion companion2 = CustomReminderConstants.Companion;
                intent2.putExtra(companion2.getEXTRA_CUSTOM_REMINDER_OBJECT(), value2.get(i));
                intent2.putExtra(companion2.getEXTRA_REMINDER_POSITION(), i);
                intent2.putExtra(companion2.getEXTRA_REMINDER_TYPE(), reminderType);
                intent2.putExtra(companion2.getEXTRA_SHOULD_ADD_TO_MAIN_LIST(), false);
                startActivityForResult(intent2, 101);
            }
        }
        if (reminderType == ReminderType.OTHERS) {
            AddCustomReminderViewModel addCustomReminderViewModel4 = this.p;
            if (addCustomReminderViewModel4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("addCustomReminderViewModel");
            } else {
                addCustomReminderViewModel = addCustomReminderViewModel4;
            }
            ArrayList<OtherReminder> value3 = addCustomReminderViewModel.getLiveDataOtherRemindersList().getValue();
            if (value3 != null) {
                Intent intent3 = new Intent(this, ActivityEditReminder.class);
                CustomReminderConstants.Companion companion3 = CustomReminderConstants.Companion;
                intent3.putExtra(companion3.getEXTRA_CUSTOM_REMINDER_OBJECT(), value3.get(i));
                intent3.putExtra(companion3.getEXTRA_REMINDER_POSITION(), i);
                intent3.putExtra(companion3.getEXTRA_REMINDER_TYPE(), reminderType);
                intent3.putExtra(companion3.getEXTRA_SHOULD_ADD_TO_MAIN_LIST(), false);
                startActivityForResult(intent3, 101);
            }
        }
    }

    @Override // com.coveiot.android.customreminders.listeners.AddReminderListener
    public void onReminderAdded(@NotNull CustomReminder customReminder, @NotNull ReminderType reminderType) {
        Intrinsics.checkNotNullParameter(customReminder, "customReminder");
        Intrinsics.checkNotNullParameter(reminderType, "reminderType");
        AddCustomReminderViewModel addCustomReminderViewModel = null;
        if (reminderType == ReminderType.MEDICINE) {
            AddCustomReminderViewModel addCustomReminderViewModel2 = this.p;
            if (addCustomReminderViewModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("addCustomReminderViewModel");
                addCustomReminderViewModel2 = null;
            }
            if (addCustomReminderViewModel2.canAddMoreReminders(reminderType)) {
                AddCustomReminderViewModel addCustomReminderViewModel3 = this.p;
                if (addCustomReminderViewModel3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("addCustomReminderViewModel");
                    addCustomReminderViewModel3 = null;
                }
                ArrayList<MedicineReminder> value = addCustomReminderViewModel3.getLiveDataMedicineRemindersList().getValue();
                if (value == null) {
                    value = new ArrayList<>();
                }
                value.add((MedicineReminder) customReminder);
                AddCustomReminderViewModel addCustomReminderViewModel4 = this.p;
                if (addCustomReminderViewModel4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("addCustomReminderViewModel");
                } else {
                    addCustomReminderViewModel = addCustomReminderViewModel4;
                }
                addCustomReminderViewModel.getLiveDataMedicineRemindersList().setValue(value);
                ((Button) _$_findCachedViewById(R.id.add_reminder)).performClick();
            } else {
                FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
                int i = R.id.fragment_container_reminder;
                MedicineReminderFragmentNew.Companion companion = MedicineReminderFragmentNew.Companion;
                AddCustomReminderViewModel addCustomReminderViewModel5 = this.p;
                if (addCustomReminderViewModel5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("addCustomReminderViewModel");
                } else {
                    addCustomReminderViewModel = addCustomReminderViewModel5;
                }
                beginTransaction.replace(i, MedicineReminderFragmentNew.Companion.newInstance$default(companion, addCustomReminderViewModel.getCurrentReminderType(), null, 0, 6, null)).commit();
                C();
            }
        } else if (reminderType == ReminderType.MEETING) {
            AddCustomReminderViewModel addCustomReminderViewModel6 = this.p;
            if (addCustomReminderViewModel6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("addCustomReminderViewModel");
                addCustomReminderViewModel6 = null;
            }
            if (addCustomReminderViewModel6.canAddMoreReminders(reminderType)) {
                AddCustomReminderViewModel addCustomReminderViewModel7 = this.p;
                if (addCustomReminderViewModel7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("addCustomReminderViewModel");
                    addCustomReminderViewModel7 = null;
                }
                ArrayList<MeetingReminder> value2 = addCustomReminderViewModel7.getLiveDataMeetingRemindersList().getValue();
                if (value2 == null) {
                    value2 = new ArrayList<>();
                }
                value2.add((MeetingReminder) customReminder);
                AddCustomReminderViewModel addCustomReminderViewModel8 = this.p;
                if (addCustomReminderViewModel8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("addCustomReminderViewModel");
                } else {
                    addCustomReminderViewModel = addCustomReminderViewModel8;
                }
                addCustomReminderViewModel.getLiveDataMeetingRemindersList().setValue(value2);
                ((Button) _$_findCachedViewById(R.id.add_reminder)).performClick();
            } else {
                FragmentTransaction beginTransaction2 = getSupportFragmentManager().beginTransaction();
                int i2 = R.id.fragment_container_reminder;
                MedicineReminderFragmentNew.Companion companion2 = MedicineReminderFragmentNew.Companion;
                AddCustomReminderViewModel addCustomReminderViewModel9 = this.p;
                if (addCustomReminderViewModel9 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("addCustomReminderViewModel");
                } else {
                    addCustomReminderViewModel = addCustomReminderViewModel9;
                }
                beginTransaction2.replace(i2, MedicineReminderFragmentNew.Companion.newInstance$default(companion2, addCustomReminderViewModel.getCurrentReminderType(), null, 0, 6, null)).commit();
                C();
            }
        } else if (reminderType == ReminderType.OTHERS) {
            AddCustomReminderViewModel addCustomReminderViewModel10 = this.p;
            if (addCustomReminderViewModel10 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("addCustomReminderViewModel");
                addCustomReminderViewModel10 = null;
            }
            if (addCustomReminderViewModel10.canAddMoreReminders(reminderType)) {
                AddCustomReminderViewModel addCustomReminderViewModel11 = this.p;
                if (addCustomReminderViewModel11 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("addCustomReminderViewModel");
                    addCustomReminderViewModel11 = null;
                }
                ArrayList<OtherReminder> value3 = addCustomReminderViewModel11.getLiveDataOtherRemindersList().getValue();
                if (value3 == null) {
                    value3 = new ArrayList<>();
                }
                value3.add((OtherReminder) customReminder);
                AddCustomReminderViewModel addCustomReminderViewModel12 = this.p;
                if (addCustomReminderViewModel12 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("addCustomReminderViewModel");
                } else {
                    addCustomReminderViewModel = addCustomReminderViewModel12;
                }
                addCustomReminderViewModel.getLiveDataOtherRemindersList().setValue(value3);
                ((Button) _$_findCachedViewById(R.id.add_reminder)).performClick();
            } else {
                FragmentTransaction beginTransaction3 = getSupportFragmentManager().beginTransaction();
                int i3 = R.id.fragment_container_reminder;
                MedicineReminderFragmentNew.Companion companion3 = MedicineReminderFragmentNew.Companion;
                AddCustomReminderViewModel addCustomReminderViewModel13 = this.p;
                if (addCustomReminderViewModel13 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("addCustomReminderViewModel");
                } else {
                    addCustomReminderViewModel = addCustomReminderViewModel13;
                }
                beginTransaction3.replace(i3, MedicineReminderFragmentNew.Companion.newInstance$default(companion3, addCustomReminderViewModel.getCurrentReminderType(), null, 0, 6, null)).commit();
                C();
            }
        } else if (reminderType == ReminderType.HAND_WASH) {
            AddCustomReminderViewModel addCustomReminderViewModel14 = this.p;
            if (addCustomReminderViewModel14 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("addCustomReminderViewModel");
            } else {
                addCustomReminderViewModel = addCustomReminderViewModel14;
            }
            addCustomReminderViewModel.setHandWashReminder((HandWashReminder) customReminder);
            ((Button) _$_findCachedViewById(R.id.saveButton)).performClick();
        } else if (reminderType == ReminderType.DRINK) {
            AddCustomReminderViewModel addCustomReminderViewModel15 = this.p;
            if (addCustomReminderViewModel15 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("addCustomReminderViewModel");
            } else {
                addCustomReminderViewModel = addCustomReminderViewModel15;
            }
            addCustomReminderViewModel.setDrinkReminder((DrinkWaterReminder) customReminder);
            ((Button) _$_findCachedViewById(R.id.saveButton)).performClick();
        }
        isAllDetailsFilled(false);
    }

    @Override // com.coveiot.android.customreminders.listeners.EditReminderListListener
    public void onReminderStatusChange(int i, boolean z) {
    }

    public final void setBottomSheetDialogImageTitleMessage(@Nullable BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage) {
        this.t = bottomSheetDialogImageTitleMessage;
    }

    public final void setBottomSheetDialogTwoButtons(@Nullable BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons) {
        this.u = bottomSheetDialogTwoButtons;
    }

    public final void setBottomSheetErrorDialog(@Nullable BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage) {
        this.r = bottomSheetDialogOneButtonTitleMessage;
    }

    public final void setBottomSheetMaxLimitDialog(@Nullable BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage) {
        this.q = bottomSheetDialogOneButtonTitleMessage;
    }

    public final void setBottomSheetSuccessDialog(@Nullable BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage) {
        this.s = bottomSheetDialogOneButtonTitleMessage;
    }

    public final void showErrorDialog() {
        if (this.r == null) {
            String string = getString(R.string.failed);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.failed)");
            String string2 = getString(R.string.setting_could_not_be_saved);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.setting_could_not_be_saved)");
            BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = new BottomSheetDialogOneButtonTitleMessage(this, string, string2);
            this.r = bottomSheetDialogOneButtonTitleMessage;
            Intrinsics.checkNotNull(bottomSheetDialogOneButtonTitleMessage);
            String string3 = getString(R.string.ok);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.ok)");
            bottomSheetDialogOneButtonTitleMessage.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.customreminders.activities.ActivityAddCustomReminderNew2$showErrorDialog$1
                @Override // android.view.View.OnClickListener
                public void onClick(@Nullable View view) {
                    BottomSheetDialogOneButtonTitleMessage bottomSheetErrorDialog = ActivityAddCustomReminderNew2.this.getBottomSheetErrorDialog();
                    Intrinsics.checkNotNull(bottomSheetErrorDialog);
                    bottomSheetErrorDialog.dismiss();
                }
            });
        }
        BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage2 = this.r;
        Intrinsics.checkNotNull(bottomSheetDialogOneButtonTitleMessage2);
        if (bottomSheetDialogOneButtonTitleMessage2.isShowing()) {
            return;
        }
        BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage3 = this.r;
        Intrinsics.checkNotNull(bottomSheetDialogOneButtonTitleMessage3);
        bottomSheetDialogOneButtonTitleMessage3.show();
    }

    public final void showSuccessDialog() {
        if (this.s == null) {
            String string = getString(R.string.success);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.success)");
            String string2 = getString(R.string.setting_saved_successfully);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.setting_saved_successfully)");
            BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = new BottomSheetDialogOneButtonTitleMessage(this, string, string2);
            this.s = bottomSheetDialogOneButtonTitleMessage;
            Intrinsics.checkNotNull(bottomSheetDialogOneButtonTitleMessage);
            String string3 = getString(R.string.ok);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.ok)");
            bottomSheetDialogOneButtonTitleMessage.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.customreminders.activities.ActivityAddCustomReminderNew2$showSuccessDialog$1
                @Override // android.view.View.OnClickListener
                public void onClick(@Nullable View view) {
                    BottomSheetDialogOneButtonTitleMessage bottomSheetSuccessDialog = ActivityAddCustomReminderNew2.this.getBottomSheetSuccessDialog();
                    Intrinsics.checkNotNull(bottomSheetSuccessDialog);
                    bottomSheetSuccessDialog.dismiss();
                    ActivityAddCustomReminderNew2.this.finish();
                }
            });
        }
        BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage2 = this.s;
        Intrinsics.checkNotNull(bottomSheetDialogOneButtonTitleMessage2);
        if (bottomSheetDialogOneButtonTitleMessage2.isShowing()) {
            return;
        }
        BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage3 = this.s;
        Intrinsics.checkNotNull(bottomSheetDialogOneButtonTitleMessage3);
        bottomSheetDialogOneButtonTitleMessage3.show();
    }

    public final void t() {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        int i = R.id.fragment_container_reminder;
        if (supportFragmentManager.findFragmentById(i) instanceof MedicineReminderFragmentNew) {
            Fragment findFragmentById = getSupportFragmentManager().findFragmentById(i);
            Intrinsics.checkNotNull(findFragmentById, "null cannot be cast to non-null type com.coveiot.android.customreminders.fragments.MedicineReminderFragmentNew");
            MedicineReminderFragmentNew medicineReminderFragmentNew = (MedicineReminderFragmentNew) findFragmentById;
            if (medicineReminderFragmentNew.isAllRemindersValid()) {
                CustomReminder reminder = medicineReminderFragmentNew.getReminder();
                Intrinsics.checkNotNull(reminder);
                AddCustomReminderViewModel addCustomReminderViewModel = this.p;
                if (addCustomReminderViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("addCustomReminderViewModel");
                    addCustomReminderViewModel = null;
                }
                onReminderAdded(reminder, addCustomReminderViewModel.getCurrentReminderType());
            }
        }
    }

    public final String u() {
        AddCustomReminderViewModel addCustomReminderViewModel = this.p;
        if (addCustomReminderViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("addCustomReminderViewModel");
            addCustomReminderViewModel = null;
        }
        int i = WhenMappings.$EnumSwitchMapping$0[addCustomReminderViewModel.getCurrentReminderType().ordinal()];
        if (i == 1) {
            String string = getString(R.string.medicine);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.medicine)");
            return string;
        } else if (i == 2) {
            String string2 = getString(R.string.other);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.other)");
            return string2;
        } else if (i == 3) {
            String string3 = getString(R.string.meeting);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.meeting)");
            return string3;
        } else if (i == 4) {
            String string4 = getString(R.string.hand_wash);
            Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.hand_wash)");
            return string4;
        } else if (i != 5) {
            return "";
        } else {
            String string5 = getString(R.string.drink_water);
            Intrinsics.checkNotNullExpressionValue(string5, "getString(R.string.drink_water)");
            return string5;
        }
    }

    public final String v() {
        AddCustomReminderViewModel addCustomReminderViewModel = this.p;
        if (addCustomReminderViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("addCustomReminderViewModel");
            addCustomReminderViewModel = null;
        }
        int i = WhenMappings.$EnumSwitchMapping$0[addCustomReminderViewModel.getCurrentReminderType().ordinal()];
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? "" : "drink water" : "handwash" : "meeting" : FitnessActivities.OTHER : "medicine";
    }

    public final void z() {
        AddCustomReminderViewModel addCustomReminderViewModel = this.p;
        if (addCustomReminderViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("addCustomReminderViewModel");
            addCustomReminderViewModel = null;
        }
        addCustomReminderViewModel.getLiveDataMedicineRemindersList().observe(this, new Observer<ArrayList<MedicineReminder>>() { // from class: com.coveiot.android.customreminders.activities.ActivityAddCustomReminderNew2$loadMedicineReminderList$1
            @Override // androidx.lifecycle.Observer
            public void onChanged(@Nullable ArrayList<MedicineReminder> arrayList) {
                AddCustomReminderViewModel addCustomReminderViewModel2;
                if (!AppUtils.isEmpty(arrayList)) {
                    ActivityAddCustomReminderNew2 activityAddCustomReminderNew2 = ActivityAddCustomReminderNew2.this;
                    int i = R.id.recycler_view_reminders_list_temp;
                    ((RecyclerView) activityAddCustomReminderNew2._$_findCachedViewById(i)).setVisibility(0);
                    ((Button) ActivityAddCustomReminderNew2.this._$_findCachedViewById(R.id.add_reminder)).setVisibility(4);
                    ((Button) ActivityAddCustomReminderNew2.this._$_findCachedViewById(R.id.saveButton)).setVisibility(0);
                    ActivityAddCustomReminderNew2 activityAddCustomReminderNew22 = ActivityAddCustomReminderNew2.this;
                    AdapterReminderListAdd adapterReminderListAdd = new AdapterReminderListAdd(activityAddCustomReminderNew22, activityAddCustomReminderNew22);
                    Intrinsics.checkNotNull(arrayList, "null cannot be cast to non-null type kotlin.collections.List<com.coveiot.android.customreminders.model.MedicineReminder>");
                    adapterReminderListAdd.setReminderList(arrayList);
                    ((RecyclerView) ActivityAddCustomReminderNew2.this._$_findCachedViewById(i)).setAdapter(adapterReminderListAdd);
                    ((FrameLayout) ActivityAddCustomReminderNew2.this._$_findCachedViewById(R.id.fragment_container_reminder)).setVisibility(8);
                    return;
                }
                ((RecyclerView) ActivityAddCustomReminderNew2.this._$_findCachedViewById(R.id.recycler_view_reminders_list_temp)).setVisibility(8);
                ((Button) ActivityAddCustomReminderNew2.this._$_findCachedViewById(R.id.add_reminder)).setVisibility(8);
                ((Button) ActivityAddCustomReminderNew2.this._$_findCachedViewById(R.id.saveButton)).setVisibility(8);
                ActivityAddCustomReminderNew2 activityAddCustomReminderNew23 = ActivityAddCustomReminderNew2.this;
                int i2 = R.id.fragment_container_reminder;
                ((FrameLayout) activityAddCustomReminderNew23._$_findCachedViewById(i2)).setVisibility(0);
                FragmentTransaction beginTransaction = ActivityAddCustomReminderNew2.this.getSupportFragmentManager().beginTransaction();
                MedicineReminderFragment.Companion companion = MedicineReminderFragment.Companion;
                addCustomReminderViewModel2 = ActivityAddCustomReminderNew2.this.p;
                if (addCustomReminderViewModel2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("addCustomReminderViewModel");
                    addCustomReminderViewModel2 = null;
                }
                beginTransaction.replace(i2, MedicineReminderFragment.Companion.newInstance$default(companion, addCustomReminderViewModel2.getCurrentReminderType(), null, 0, 6, null)).commit();
            }
        });
    }
}
