package com.coveiot.android.leonardo.more.activities;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.aigestudio.wheelpicker.WheelPicker;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.boat.R;
import com.coveiot.android.boat.databinding.ActivityScheduleBinding;
import com.coveiot.android.leonardo.listener.DialogListener;
import com.coveiot.android.leonardo.more.activities.ActivityScheduleReminder;
import com.coveiot.android.leonardo.more.adapters.ScheduleListAdapter;
import com.coveiot.android.leonardo.more.listeners.ScheduleListener;
import com.coveiot.android.leonardo.more.viewmodel.ScheduleViewModel;
import com.coveiot.android.leonardo.utils.EventType;
import com.coveiot.android.leonardo.utils.ViewModelFactory;
import com.coveiot.android.leonardo.utils.ViewUtilsKt;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.BottomSheetDialogImageTitleMessageTwoBtns;
import com.coveiot.android.theme.BottomSheetDialogOneButtonOneTitle;
import com.coveiot.android.theme.BottomSheetDialogOneButtonTitleMessage;
import com.coveiot.android.theme.BottomSheetDialogTwoButtons;
import com.coveiot.sdk.ble.api.model.ScheduleInfo;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityScheduleReminder extends BaseActivity implements ScheduleListener, DialogListener, ScheduleListAdapter.EditScheduleReminderListener {
    public String A;
    public String B;
    public String C;
    public String D;
    public String E;
    public String F;
    public String G;
    public int I;
    public int J;
    public int K;
    public int L;
    public int M;
    public int N;
    public ScheduleViewModel O;
    public List<ScheduleInfo> P;
    public List<ScheduleInfo> Q;
    public ScheduleListAdapter R;
    public boolean S;
    public ActivityScheduleBinding V;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public final String p = ActivityScheduleReminder.class.getSimpleName();
    @NotNull
    public final List<ScheduleInfo> q = new ArrayList();
    @NotNull
    public final List<ScheduleInfo> r = new ArrayList();
    @NotNull
    public final int[] s = {-1, -1, -1, -1, -1};
    @NotNull
    public final SimpleDateFormat t = new SimpleDateFormat("hh:mm a, dd MMMM yyyy", Locale.ENGLISH);
    @NotNull
    public ArrayList<String> u = new ArrayList<>();
    @NotNull
    public ArrayList<String> v = new ArrayList<>();
    @NotNull
    public ArrayList<String> w = new ArrayList<>();
    @NotNull
    public ArrayList<String> x = new ArrayList<>();
    @NotNull
    public ArrayList<String> y = new ArrayList<>();
    @NotNull
    public ArrayList<String> z = new ArrayList<>();
    @NotNull
    public String H = "1";
    @NotNull
    public EventType T = EventType.ADD;
    @NotNull
    public ScheduleInfo U = new ScheduleInfo();

    /* loaded from: classes5.dex */
    public static final class a extends Lambda implements Function1<View, Unit> {
        public a() {
            super(1);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$0(BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage, View view) {
            Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonTitleMessage, "$bottomSheetDialogOneButtonTitleMessage");
            bottomSheetDialogOneButtonTitleMessage.dismiss();
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(View view) {
            invoke2(view);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull View it) {
            Intrinsics.checkNotNullParameter(it, "it");
            if (ActivityScheduleReminder.this.S) {
                if (!AppUtils.isNetConnected(ActivityScheduleReminder.this)) {
                    ActivityScheduleReminder.this.showNoInternetMessage();
                } else if (BleApiManager.getInstance(ActivityScheduleReminder.this).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
                    ActivityScheduleReminder.this.r.clear();
                    List list = ActivityScheduleReminder.this.P;
                    ScheduleViewModel scheduleViewModel = null;
                    if (list == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("scheduleInfos");
                        list = null;
                    }
                    int size = list.size();
                    for (int i = 0; i < size; i++) {
                        ActivityScheduleReminder activityScheduleReminder = ActivityScheduleReminder.this;
                        List list2 = activityScheduleReminder.P;
                        if (list2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("scheduleInfos");
                            list2 = null;
                        }
                        if (!activityScheduleReminder.O((ScheduleInfo) list2.get(i))) {
                            List list3 = ActivityScheduleReminder.this.P;
                            if (list3 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("scheduleInfos");
                                list3 = null;
                            }
                            ((ScheduleInfo) list3.get(i)).setScheduleId(ActivityScheduleReminder.this.L());
                            List list4 = ActivityScheduleReminder.this.r;
                            List list5 = ActivityScheduleReminder.this.P;
                            if (list5 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("scheduleInfos");
                                list5 = null;
                            }
                            list4.add(list5.get(i));
                        } else {
                            ActivityScheduleReminder activityScheduleReminder2 = ActivityScheduleReminder.this;
                            List list6 = activityScheduleReminder2.P;
                            if (list6 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("scheduleInfos");
                                list6 = null;
                            }
                            if (activityScheduleReminder2.P((ScheduleInfo) list6.get(i))) {
                                List list7 = ActivityScheduleReminder.this.r;
                                List list8 = ActivityScheduleReminder.this.P;
                                if (list8 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("scheduleInfos");
                                    list8 = null;
                                }
                                list7.add(list8.get(i));
                            }
                        }
                    }
                    if (!ActivityScheduleReminder.this.r.isEmpty()) {
                        ScheduleViewModel scheduleViewModel2 = ActivityScheduleReminder.this.O;
                        if (scheduleViewModel2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("scheduleViewModel");
                        } else {
                            scheduleViewModel = scheduleViewModel2;
                        }
                        scheduleViewModel.sendToBle(ActivityScheduleReminder.this.r);
                    } else if (!ActivityScheduleReminder.this.q.isEmpty()) {
                        ScheduleViewModel scheduleViewModel3 = ActivityScheduleReminder.this.O;
                        if (scheduleViewModel3 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("scheduleViewModel");
                        } else {
                            scheduleViewModel = scheduleViewModel3;
                        }
                        scheduleViewModel.sendDeleteToBle(ActivityScheduleReminder.this.q);
                    }
                    ActivityScheduleReminder activityScheduleReminder3 = ActivityScheduleReminder.this;
                    TextView btnSave = (TextView) activityScheduleReminder3._$_findCachedViewById(R.id.btnSave);
                    Intrinsics.checkNotNullExpressionValue(btnSave, "btnSave");
                    activityScheduleReminder3.gone(btnSave);
                    ActivityScheduleReminder activityScheduleReminder4 = ActivityScheduleReminder.this;
                    TextView btnAddScheduleReminder = (TextView) activityScheduleReminder4._$_findCachedViewById(R.id.btnAddScheduleReminder);
                    Intrinsics.checkNotNullExpressionValue(btnAddScheduleReminder, "btnAddScheduleReminder");
                    activityScheduleReminder4.visible(btnAddScheduleReminder);
                } else {
                    ActivityScheduleReminder activityScheduleReminder5 = ActivityScheduleReminder.this;
                    String string = activityScheduleReminder5.getString(R.string.band_not_connected);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.band_not_connected)");
                    String string2 = ActivityScheduleReminder.this.getString(R.string.please_connect_the_device);
                    Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.please_connect_the_device)");
                    final BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = new BottomSheetDialogOneButtonTitleMessage(activityScheduleReminder5, string, string2);
                    String string3 = ActivityScheduleReminder.this.getString(R.string.ok);
                    Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.ok)");
                    bottomSheetDialogOneButtonTitleMessage.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.uh
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            ActivityScheduleReminder.a.invoke$lambda$0(BottomSheetDialogOneButtonTitleMessage.this, view);
                        }
                    });
                    bottomSheetDialogOneButtonTitleMessage.show();
                }
            }
        }
    }

    /* loaded from: classes5.dex */
    public static final class b extends Lambda implements Function1<View, Unit> {
        public b() {
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
            ActivityScheduleReminder.this.openDateTimeDialog();
        }
    }

    /* loaded from: classes5.dex */
    public static final class c extends Lambda implements Function1<View, Unit> {
        public c() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(View view) {
            invoke2(view);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull View it) {
            boolean z;
            Intrinsics.checkNotNullParameter(it, "it");
            ActivityScheduleReminder activityScheduleReminder = ActivityScheduleReminder.this;
            int i = R.id.etTitle;
            String obj = StringsKt__StringsKt.trim(((EditText) activityScheduleReminder._$_findCachedViewById(i)).getText().toString()).toString();
            boolean z2 = false;
            if (obj == null || obj.length() == 0) {
                ActivityScheduleReminder activityScheduleReminder2 = ActivityScheduleReminder.this;
                String string = activityScheduleReminder2.getString(R.string.title_can_not_be_empty);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.title_can_not_be_empty)");
                ViewUtilsKt.toast(activityScheduleReminder2, string);
                z = true;
            } else {
                z = false;
            }
            String obj2 = StringsKt__StringsKt.trim(((EditText) ActivityScheduleReminder.this._$_findCachedViewById(R.id.etScheduleDateTime)).getText().toString()).toString();
            if (obj2 == null || obj2.length() == 0) {
                ActivityScheduleReminder activityScheduleReminder3 = ActivityScheduleReminder.this;
                String string2 = activityScheduleReminder3.getString(R.string.time_can_not_be_empty);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.time_can_not_be_empty)");
                ViewUtilsKt.toast(activityScheduleReminder3, string2);
                z = true;
            }
            String str = ActivityScheduleReminder.this.D;
            List<ScheduleInfo> list = null;
            if (str == null) {
                Intrinsics.throwUninitializedPropertyAccessException("reminder_var");
                str = null;
            }
            if (str.length() == 0) {
                ActivityScheduleReminder activityScheduleReminder4 = ActivityScheduleReminder.this;
                String string3 = activityScheduleReminder4.getString(R.string.reminder_can_not_be_empty);
                Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.reminder_can_not_be_empty)");
                ViewUtilsKt.toast(activityScheduleReminder4, string3);
                z = true;
            }
            ActivityScheduleReminder activityScheduleReminder5 = ActivityScheduleReminder.this;
            int i2 = R.id.etDescription;
            String obj3 = StringsKt__StringsKt.trim(((EditText) activityScheduleReminder5._$_findCachedViewById(i2)).getText().toString()).toString();
            if (obj3 == null || obj3.length() == 0) {
                ActivityScheduleReminder activityScheduleReminder6 = ActivityScheduleReminder.this;
                String string4 = activityScheduleReminder6.getString(R.string.content_can_not_be_empty);
                Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.content_can_not_be_empty)");
                ViewUtilsKt.toast(activityScheduleReminder6, string4);
                z = true;
            }
            Calendar calendar = Calendar.getInstance();
            calendar.set(5, ActivityScheduleReminder.this.U.getDay());
            calendar.set(1, ActivityScheduleReminder.this.U.getYear());
            calendar.set(2, ActivityScheduleReminder.this.U.getMonth());
            calendar.set(11, ActivityScheduleReminder.this.U.getHour());
            calendar.set(12, ActivityScheduleReminder.this.U.getMinute());
            if (calendar.getTimeInMillis() - System.currentTimeMillis() < ActivityScheduleReminder.this.U.getAdvance() * 60000) {
                ActivityScheduleReminder activityScheduleReminder7 = ActivityScheduleReminder.this;
                String string5 = activityScheduleReminder7.getString(R.string.reminder_time_can_not_be_less);
                Intrinsics.checkNotNullExpressionValue(string5, "getString(R.string.reminder_time_can_not_be_less)");
                ViewUtilsKt.toast(activityScheduleReminder7, string5);
                z = true;
            }
            ActivityScheduleReminder.this.U.setTitle(StringsKt__StringsKt.trim(((EditText) ActivityScheduleReminder.this._$_findCachedViewById(i)).getText().toString()).toString());
            ActivityScheduleReminder.this.U.setContent(StringsKt__StringsKt.trim(((EditText) ActivityScheduleReminder.this._$_findCachedViewById(i2)).getText().toString()).toString());
            List list2 = ActivityScheduleReminder.this.P;
            if (list2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("scheduleInfos");
            } else {
                list = list2;
            }
            boolean z3 = false;
            for (ScheduleInfo scheduleInfo : list) {
                if (ActivityScheduleReminder.this.U.getScheduleId() != scheduleInfo.getScheduleId()) {
                    if (kotlin.text.m.equals(ActivityScheduleReminder.this.U.getTitle(), scheduleInfo.getTitle(), true)) {
                        z2 = true;
                    }
                    if (ActivityScheduleReminder.this.U.getDay() == scheduleInfo.getDay() && ActivityScheduleReminder.this.U.getMonth() == scheduleInfo.getMonth() && ActivityScheduleReminder.this.U.getYear() == scheduleInfo.getYear() && ActivityScheduleReminder.this.U.getHour() == scheduleInfo.getHour() && ActivityScheduleReminder.this.U.getMinute() == scheduleInfo.getMinute()) {
                        z3 = true;
                    }
                }
            }
            if (z2) {
                ActivityScheduleReminder activityScheduleReminder8 = ActivityScheduleReminder.this;
                String string6 = activityScheduleReminder8.getString(R.string.schedule_title_can_not_be_same);
                Intrinsics.checkNotNullExpressionValue(string6, "getString(R.string.schedule_title_can_not_be_same)");
                ViewUtilsKt.toast(activityScheduleReminder8, string6);
            } else if (!z3) {
                if (z) {
                    return;
                }
                ActivityScheduleReminder.this.g0();
            } else {
                ActivityScheduleReminder activityScheduleReminder9 = ActivityScheduleReminder.this;
                String string7 = activityScheduleReminder9.getString(R.string.there_is_already_schedule);
                Intrinsics.checkNotNullExpressionValue(string7, "getString(R.string.there_is_already_schedule)");
                ViewUtilsKt.toast(activityScheduleReminder9, string7);
            }
        }
    }

    public static final void H(ActivityScheduleReminder this$0, BottomSheetDialogTwoButtons dialog, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        ((TextView) this$0._$_findCachedViewById(R.id.btnSave)).performClick();
        dialog.dismiss();
    }

    public static final void I(BottomSheetDialogTwoButtons dialog, ActivityScheduleReminder this$0, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        dialog.dismiss();
        this$0.finish();
    }

    public static final void N(ActivityScheduleReminder this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.G();
    }

    public static final void R(ActivityScheduleReminder this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        List<ScheduleInfo> list = this$0.P;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("scheduleInfos");
            list = null;
        }
        if (list.size() >= 5) {
            String string = this$0.getString(R.string.maximum_no_of_schedule);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.maximum_no_of_schedule)");
            ViewUtilsKt.toast(this$0, string);
            return;
        }
        this$0.toggleScheduleView();
        this$0.T = EventType.ADD;
        Calendar c2 = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(c2, "c");
        this$0.M(c2);
        this$0.U.setYear(c2.get(1));
        this$0.U.setDay(c2.get(6));
        this$0.U.setMonth(c2.get(2));
        this$0.U.setMinute(c2.get(12));
        this$0.U.setHour(c2.get(11));
        this$0.U.setScheduleId(-1);
    }

    public static final void S(ActivityScheduleReminder this$0, Dialog dialog, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        if (this$0.Q()) {
            dialog.dismiss();
        }
    }

    public static final void T(Dialog dialog, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        dialog.dismiss();
    }

    public static final void V(ActivityScheduleReminder this$0, WheelPicker wheelPicker, Object obj, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.G = obj.toString();
    }

    public static final void X(ActivityScheduleReminder this$0, WheelPicker wheelPicker, Object obj, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.A = obj.toString();
    }

    public static final void Z(ActivityScheduleReminder this$0, WheelPicker wheelPicker, Object obj, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.E = obj.toString();
    }

    public static final void b0(ActivityScheduleReminder this$0, WheelPicker wheelPicker, Object obj, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.F = obj.toString();
    }

    public static final void d0(ActivityScheduleReminder this$0, Dialog dialog, WheelPicker wheelPicker, Object obj, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        this$0.B = obj.toString();
        this$0.W(dialog);
    }

    public static final void f0(ActivityScheduleReminder this$0, Dialog dialog, WheelPicker wheelPicker, Object obj, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        this$0.C = obj.toString();
        this$0.W(dialog);
    }

    public static final void h0(BottomSheetDialogImageTitleMessageTwoBtns bottomSheetDialogImageTitleMessageTwoBtns, ActivityScheduleReminder this$0, ScheduleInfo info, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(info, "$info");
        bottomSheetDialogImageTitleMessageTwoBtns.dismiss();
        this$0.K(info);
    }

    public static final void i0(BottomSheetDialogImageTitleMessageTwoBtns bottomSheetDialogImageTitleMessageTwoBtns, View view) {
        Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessageTwoBtns);
        bottomSheetDialogImageTitleMessageTwoBtns.dismiss();
    }

    public static final void j0(BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle, ActivityScheduleReminder this$0, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonOneTitle, "$bottomSheetDialogOneButtonOneTitle");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        bottomSheetDialogOneButtonOneTitle.dismiss();
        this$0.finish();
    }

    public static final void k0(BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle, ActivityScheduleReminder this$0, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonOneTitle, "$bottomSheetDialogOneButtonOneTitle");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        bottomSheetDialogOneButtonOneTitle.dismiss();
        this$0.finish();
    }

    public final void G() {
        if (J()) {
            String string = getString(R.string.confirmation);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.confirmation)");
            String string2 = getString(R.string.save_changes);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.save_changes)");
            final BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = new BottomSheetDialogTwoButtons(this, string, string2, false, 8, null);
            String string3 = getString(R.string.save_changes_btn);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.save_changes_btn)");
            bottomSheetDialogTwoButtons.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.oh
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityScheduleReminder.H(ActivityScheduleReminder.this, bottomSheetDialogTwoButtons, view);
                }
            });
            String string4 = getString(R.string.discard);
            Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.discard)");
            bottomSheetDialogTwoButtons.setNegativeButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.th
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityScheduleReminder.I(BottomSheetDialogTwoButtons.this, this, view);
                }
            });
            bottomSheetDialogTwoButtons.show();
            return;
        }
        finish();
    }

    public final boolean J() {
        ScheduleViewModel scheduleViewModel = this.O;
        if (scheduleViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("scheduleViewModel");
            scheduleViewModel = null;
        }
        int size = scheduleViewModel.convertFromScheduleDataToScheduleInfo().size();
        List<ScheduleInfo> list = this.P;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("scheduleInfos");
            list = null;
        }
        if (size != list.size()) {
            return true;
        }
        List<ScheduleInfo> list2 = this.P;
        if (list2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("scheduleInfos");
            list2 = null;
        }
        int size2 = list2.size();
        for (int i = 0; i < size2; i++) {
            List<ScheduleInfo> list3 = this.P;
            if (list3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("scheduleInfos");
                list3 = null;
            }
            if (O(list3.get(i))) {
                List<ScheduleInfo> list4 = this.P;
                if (list4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("scheduleInfos");
                    list4 = null;
                }
                if (!P(list4.get(i))) {
                }
            }
            return true;
        }
        return false;
    }

    public final void K(ScheduleInfo scheduleInfo) {
        List<ScheduleInfo> list = null;
        if (scheduleInfo.getScheduleId() == -1) {
            List<ScheduleInfo> list2 = this.P;
            if (list2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("scheduleInfos");
                list2 = null;
            }
            list2.remove(scheduleInfo);
            ScheduleListAdapter scheduleListAdapter = this.R;
            if (scheduleListAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mScheduleListAdapter");
                scheduleListAdapter = null;
            }
            List<ScheduleInfo> list3 = this.P;
            if (list3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("scheduleInfos");
            } else {
                list = list3;
            }
            scheduleListAdapter.setScheduleInfo(list);
            visibleTextview();
            return;
        }
        if (O(scheduleInfo)) {
            this.q.add(scheduleInfo);
        }
        int i = 0;
        List<ScheduleInfo> list4 = this.P;
        if (list4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("scheduleInfos");
            list4 = null;
        }
        int size = list4.size();
        while (true) {
            if (i >= size) {
                break;
            }
            List<ScheduleInfo> list5 = this.P;
            if (list5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("scheduleInfos");
                list5 = null;
            }
            if (list5.get(i).getScheduleId() == scheduleInfo.getScheduleId()) {
                List<ScheduleInfo> list6 = this.P;
                if (list6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("scheduleInfos");
                    list6 = null;
                }
                if (list6.get(i).getScheduleId() < 5) {
                    int[] iArr = this.s;
                    List<ScheduleInfo> list7 = this.P;
                    if (list7 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("scheduleInfos");
                        list7 = null;
                    }
                    iArr[list7.get(i).getScheduleId()] = -1;
                }
                List<ScheduleInfo> list8 = this.P;
                if (list8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("scheduleInfos");
                    list8 = null;
                }
                list8.remove(i);
            } else {
                i++;
            }
        }
        int i2 = R.id.btnSave;
        TextView btnSave = (TextView) _$_findCachedViewById(i2);
        Intrinsics.checkNotNullExpressionValue(btnSave, "btnSave");
        visible(btnSave);
        List<ScheduleInfo> list9 = this.P;
        if (list9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("scheduleInfos");
            list9 = null;
        }
        if (list9.isEmpty()) {
            RecyclerView rvScheduleList = (RecyclerView) _$_findCachedViewById(R.id.rvScheduleList);
            Intrinsics.checkNotNullExpressionValue(rvScheduleList, "rvScheduleList");
            gone(rvScheduleList);
            TextView btnSave2 = (TextView) _$_findCachedViewById(i2);
            Intrinsics.checkNotNullExpressionValue(btnSave2, "btnSave");
            gone(btnSave2);
            ConstraintLayout emptyCl = (ConstraintLayout) _$_findCachedViewById(R.id.emptyCl);
            Intrinsics.checkNotNullExpressionValue(emptyCl, "emptyCl");
            visible(emptyCl);
        } else {
            ConstraintLayout emptyCl2 = (ConstraintLayout) _$_findCachedViewById(R.id.emptyCl);
            Intrinsics.checkNotNullExpressionValue(emptyCl2, "emptyCl");
            gone(emptyCl2);
            RecyclerView rvScheduleList2 = (RecyclerView) _$_findCachedViewById(R.id.rvScheduleList);
            Intrinsics.checkNotNullExpressionValue(rvScheduleList2, "rvScheduleList");
            visible(rvScheduleList2);
            TextView btnSave3 = (TextView) _$_findCachedViewById(i2);
            Intrinsics.checkNotNullExpressionValue(btnSave3, "btnSave");
            visible(btnSave3);
            ScheduleListAdapter scheduleListAdapter2 = this.R;
            if (scheduleListAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mScheduleListAdapter");
                scheduleListAdapter2 = null;
            }
            List<ScheduleInfo> list10 = this.P;
            if (list10 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("scheduleInfos");
            } else {
                list = list10;
            }
            scheduleListAdapter2.setScheduleInfo(list);
        }
        visibleTextview();
    }

    public final int L() {
        List<ScheduleInfo> list = this.Q;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("scheduleInfoPreference");
            list = null;
        }
        for (ScheduleInfo scheduleInfo : list) {
            if (scheduleInfo.getScheduleId() < 5) {
                this.s[scheduleInfo.getScheduleId()] = scheduleInfo.getScheduleId();
            }
        }
        int i = 0;
        int length = this.s.length;
        while (true) {
            if (i >= length) {
                i = -1;
                break;
            }
            int[] iArr = this.s;
            if (iArr[i] == -1) {
                iArr[i] = i;
                break;
            }
            i++;
        }
        LogHelper.d(this.p, "getAvailableScheduleId-> " + i);
        return i;
    }

    public final void M(Calendar calendar) {
        this.A = String.valueOf(calendar.get(5));
        this.B = String.valueOf(calendar.get(2) + 1);
        this.C = String.valueOf(calendar.get(1));
        this.E = String.valueOf(calendar.get(10));
        this.F = String.valueOf(calendar.get(12));
        this.G = Intrinsics.areEqual(String.valueOf(calendar.get(9)), "1") ? "PM" : "AM";
    }

    public final boolean O(ScheduleInfo scheduleInfo) {
        LogHelper.d(this.p, "isAlreadySaved called-> " + scheduleInfo.getScheduleId());
        List<ScheduleInfo> list = this.Q;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("scheduleInfoPreference");
            list = null;
        }
        int size = list.size();
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= size) {
                break;
            }
            int scheduleId = scheduleInfo.getScheduleId();
            List<ScheduleInfo> list2 = this.Q;
            if (list2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("scheduleInfoPreference");
                list2 = null;
            }
            if (scheduleId == list2.get(i).getScheduleId()) {
                z = true;
                break;
            }
            i++;
        }
        LogHelper.d(this.p, "isAlreadySaved called-> " + z);
        return z;
    }

    public final boolean P(ScheduleInfo scheduleInfo) {
        LogHelper.d(this.p, "isChanged called-> " + scheduleInfo.getScheduleId());
        List<ScheduleInfo> list = this.Q;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("scheduleInfoPreference");
            list = null;
        }
        int size = list.size();
        boolean z = false;
        for (int i = 0; i < size; i++) {
            int scheduleId = scheduleInfo.getScheduleId();
            List<ScheduleInfo> list2 = this.Q;
            if (list2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("scheduleInfoPreference");
                list2 = null;
            }
            if (scheduleId == list2.get(i).getScheduleId()) {
                String title = scheduleInfo.getTitle();
                List<ScheduleInfo> list3 = this.Q;
                if (list3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("scheduleInfoPreference");
                    list3 = null;
                }
                if (Intrinsics.areEqual(title, list3.get(i).getTitle())) {
                    String content = scheduleInfo.getContent();
                    List<ScheduleInfo> list4 = this.Q;
                    if (list4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("scheduleInfoPreference");
                        list4 = null;
                    }
                    if (Intrinsics.areEqual(content, list4.get(i).getContent())) {
                        int advance = scheduleInfo.getAdvance();
                        List<ScheduleInfo> list5 = this.Q;
                        if (list5 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("scheduleInfoPreference");
                            list5 = null;
                        }
                        if (advance == list5.get(i).getAdvance()) {
                            int day = scheduleInfo.getDay();
                            List<ScheduleInfo> list6 = this.Q;
                            if (list6 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("scheduleInfoPreference");
                                list6 = null;
                            }
                            if (day == list6.get(i).getDay()) {
                                int month = scheduleInfo.getMonth();
                                List<ScheduleInfo> list7 = this.Q;
                                if (list7 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("scheduleInfoPreference");
                                    list7 = null;
                                }
                                if (month == list7.get(i).getMonth()) {
                                    int year = scheduleInfo.getYear();
                                    List<ScheduleInfo> list8 = this.Q;
                                    if (list8 == null) {
                                        Intrinsics.throwUninitializedPropertyAccessException("scheduleInfoPreference");
                                        list8 = null;
                                    }
                                    if (year == list8.get(i).getYear()) {
                                        int hour = scheduleInfo.getHour();
                                        List<ScheduleInfo> list9 = this.Q;
                                        if (list9 == null) {
                                            Intrinsics.throwUninitializedPropertyAccessException("scheduleInfoPreference");
                                            list9 = null;
                                        }
                                        if (hour == list9.get(i).getHour()) {
                                            int minute = scheduleInfo.getMinute();
                                            List<ScheduleInfo> list10 = this.Q;
                                            if (list10 == null) {
                                                Intrinsics.throwUninitializedPropertyAccessException("scheduleInfoPreference");
                                                list10 = null;
                                            }
                                            if (minute == list10.get(i).getMinute()) {
                                                int year2 = scheduleInfo.getYear();
                                                List<ScheduleInfo> list11 = this.Q;
                                                if (list11 == null) {
                                                    Intrinsics.throwUninitializedPropertyAccessException("scheduleInfoPreference");
                                                    list11 = null;
                                                }
                                                if (year2 == list11.get(i).getYear()) {
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                z = true;
            }
        }
        LogHelper.d(this.p, "isChanged called-> " + z);
        return z;
    }

    public final boolean Q() {
        int parseInt;
        Calendar calendar = Calendar.getInstance();
        String str = this.C;
        String str2 = null;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("year_var");
            str = null;
        }
        calendar.set(1, Integer.parseInt(str));
        String str3 = this.B;
        if (str3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("month_var");
            str3 = null;
        }
        calendar.set(2, Integer.parseInt(str3) - 1);
        String str4 = this.A;
        if (str4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("date_var");
            str4 = null;
        }
        calendar.set(5, Integer.parseInt(str4));
        String str5 = this.G;
        if (str5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("am_pm_var");
            str5 = null;
        }
        if (Intrinsics.areEqual(str5, "AM")) {
            String str6 = this.E;
            if (str6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("hour_var");
                str6 = null;
            }
            parseInt = Integer.parseInt(str6);
        } else {
            String str7 = this.E;
            if (str7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("hour_var");
                str7 = null;
            }
            parseInt = Integer.parseInt(str7) + 12;
        }
        calendar.set(11, parseInt);
        String str8 = this.F;
        if (str8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("min_var");
        } else {
            str2 = str8;
        }
        calendar.set(12, Integer.parseInt(str2));
        calendar.set(13, 0);
        calendar.set(14, 0);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(13, 0);
        calendar2.set(14, 0);
        if (calendar2.getTimeInMillis() > calendar.getTimeInMillis()) {
            String string = getString(R.string.please_select_future_time);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.please_select_future_time)");
            ViewUtilsKt.toast(this, string);
            return false;
        }
        this.U.setYear(calendar.get(1));
        this.U.setMonth(calendar.get(2));
        this.U.setDay(calendar.get(5));
        this.U.setHour(calendar.get(11));
        this.U.setMinute(calendar.get(12));
        ((EditText) _$_findCachedViewById(R.id.etScheduleDateTime)).setText(this.t.format(Long.valueOf(calendar.getTimeInMillis())));
        return true;
    }

    public final void U(Dialog dialog) {
        populateAMPMDataInView();
        int i = R.id.amPmPicker;
        ((WheelPicker) dialog.findViewById(i)).setData(this.z);
        ((WheelPicker) dialog.findViewById(i)).setAtmospheric(true);
        ((WheelPicker) dialog.findViewById(i)).setVisibleItemCount(2);
        ((WheelPicker) dialog.findViewById(i)).setItemSpace(30);
        ((WheelPicker) dialog.findViewById(i)).setSelectedItemPosition(this.N);
        ((WheelPicker) dialog.findViewById(i)).setCyclic(false);
        ((WheelPicker) dialog.findViewById(i)).setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() { // from class: com.coveiot.android.leonardo.more.activities.ih
            @Override // com.aigestudio.wheelpicker.WheelPicker.OnItemSelectedListener
            public final void onItemSelected(WheelPicker wheelPicker, Object obj, int i2) {
                ActivityScheduleReminder.V(ActivityScheduleReminder.this, wheelPicker, obj, i2);
            }
        });
    }

    public final void W(Dialog dialog) {
        populateDaysDataInView();
        int i = R.id.dayPicker;
        ((WheelPicker) dialog.findViewById(i)).setData(this.u);
        ((WheelPicker) dialog.findViewById(i)).setAtmospheric(true);
        ((WheelPicker) dialog.findViewById(i)).setVisibleItemCount(5);
        ((WheelPicker) dialog.findViewById(i)).setItemSpace(30);
        ((WheelPicker) dialog.findViewById(i)).setSelectedItemPosition(this.I);
        ((WheelPicker) dialog.findViewById(i)).setCyclic(true);
        ((WheelPicker) dialog.findViewById(i)).setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() { // from class: com.coveiot.android.leonardo.more.activities.fh
            @Override // com.aigestudio.wheelpicker.WheelPicker.OnItemSelectedListener
            public final void onItemSelected(WheelPicker wheelPicker, Object obj, int i2) {
                ActivityScheduleReminder.X(ActivityScheduleReminder.this, wheelPicker, obj, i2);
            }
        });
    }

    public final void Y(Dialog dialog) {
        populateHourDataInView();
        int i = R.id.hourPicker;
        ((WheelPicker) dialog.findViewById(i)).setData(this.x);
        ((WheelPicker) dialog.findViewById(i)).setAtmospheric(true);
        ((WheelPicker) dialog.findViewById(i)).setVisibleItemCount(5);
        ((WheelPicker) dialog.findViewById(i)).setItemSpace(30);
        ((WheelPicker) dialog.findViewById(i)).setSelectedItemPosition(this.L);
        ((WheelPicker) dialog.findViewById(i)).setCyclic(true);
        ((WheelPicker) dialog.findViewById(i)).setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() { // from class: com.coveiot.android.leonardo.more.activities.hh
            @Override // com.aigestudio.wheelpicker.WheelPicker.OnItemSelectedListener
            public final void onItemSelected(WheelPicker wheelPicker, Object obj, int i2) {
                ActivityScheduleReminder.Z(ActivityScheduleReminder.this, wheelPicker, obj, i2);
            }
        });
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

    public final void a0(Dialog dialog) {
        populateMinDataInView();
        int i = R.id.minPicker;
        ((WheelPicker) dialog.findViewById(i)).setData(this.y);
        ((WheelPicker) dialog.findViewById(i)).setAtmospheric(true);
        ((WheelPicker) dialog.findViewById(i)).setVisibleItemCount(5);
        ((WheelPicker) dialog.findViewById(i)).setItemSpace(30);
        ((WheelPicker) dialog.findViewById(i)).setSelectedItemPosition(this.M);
        ((WheelPicker) dialog.findViewById(i)).setCyclic(true);
        ((WheelPicker) dialog.findViewById(i)).setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() { // from class: com.coveiot.android.leonardo.more.activities.gh
            @Override // com.aigestudio.wheelpicker.WheelPicker.OnItemSelectedListener
            public final void onItemSelected(WheelPicker wheelPicker, Object obj, int i2) {
                ActivityScheduleReminder.b0(ActivityScheduleReminder.this, wheelPicker, obj, i2);
            }
        });
    }

    public final void c0(final Dialog dialog) {
        populateMonthsDataInView();
        int i = R.id.monthPicker;
        ((WheelPicker) dialog.findViewById(i)).setData(this.v);
        ((WheelPicker) dialog.findViewById(i)).setAtmospheric(true);
        ((WheelPicker) dialog.findViewById(i)).setVisibleItemCount(5);
        ((WheelPicker) dialog.findViewById(i)).setItemSpace(30);
        ((WheelPicker) dialog.findViewById(i)).setSelectedItemPosition(this.J);
        ((WheelPicker) dialog.findViewById(i)).setCyclic(true);
        ((WheelPicker) dialog.findViewById(i)).setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() { // from class: com.coveiot.android.leonardo.more.activities.jh
            @Override // com.aigestudio.wheelpicker.WheelPicker.OnItemSelectedListener
            public final void onItemSelected(WheelPicker wheelPicker, Object obj, int i2) {
                ActivityScheduleReminder.d0(ActivityScheduleReminder.this, dialog, wheelPicker, obj, i2);
            }
        });
    }

    public final void e0(final Dialog dialog) {
        populateYearsDataInView();
        int i = R.id.yearPicker;
        ((WheelPicker) dialog.findViewById(i)).setData(this.w);
        ((WheelPicker) dialog.findViewById(i)).setAtmospheric(true);
        ((WheelPicker) dialog.findViewById(i)).setVisibleItemCount(2);
        ((WheelPicker) dialog.findViewById(i)).setItemSpace(30);
        ((WheelPicker) dialog.findViewById(i)).setSelectedItemPosition(this.K);
        ((WheelPicker) dialog.findViewById(i)).setCyclic(false);
        ((WheelPicker) dialog.findViewById(i)).setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() { // from class: com.coveiot.android.leonardo.more.activities.kh
            @Override // com.aigestudio.wheelpicker.WheelPicker.OnItemSelectedListener
            public final void onItemSelected(WheelPicker wheelPicker, Object obj, int i2) {
                ActivityScheduleReminder.f0(ActivityScheduleReminder.this, dialog, wheelPicker, obj, i2);
            }
        });
    }

    @Override // com.coveiot.android.leonardo.more.adapters.ScheduleListAdapter.EditScheduleReminderListener
    public void editListener(@NotNull ScheduleInfo scheduleInfo, @NotNull EventType typeEvent, @NotNull List<ScheduleInfo> mScheduleInfos) {
        Intrinsics.checkNotNullParameter(scheduleInfo, "scheduleInfo");
        Intrinsics.checkNotNullParameter(typeEvent, "typeEvent");
        Intrinsics.checkNotNullParameter(mScheduleInfos, "mScheduleInfos");
        toggleScheduleView();
        this.T = typeEvent;
        this.U = scheduleInfo;
        Calendar cal = Calendar.getInstance();
        cal.set(1, this.U.getYear());
        cal.set(2, this.U.getMonth());
        cal.set(5, this.U.getDay());
        cal.set(11, this.U.getHour());
        cal.set(12, this.U.getMinute());
        cal.set(13, 0);
        cal.set(14, 0);
        Intrinsics.checkNotNullExpressionValue(cal, "cal");
        M(cal);
        ((EditText) _$_findCachedViewById(R.id.etScheduleDateTime)).setText(this.t.format(Long.valueOf(cal.getTimeInMillis())));
        ((EditText) _$_findCachedViewById(R.id.etTitle)).setText(this.U.getTitle());
        ((EditText) _$_findCachedViewById(R.id.etDescription)).setText(this.U.getContent());
        if (this.U.getAdvance() == 0) {
            ((Spinner) _$_findCachedViewById(R.id.scheduleReminderSpinner)).setSelection(0);
        } else if (scheduleInfo.getAdvance() == 5) {
            ((Spinner) _$_findCachedViewById(R.id.scheduleReminderSpinner)).setSelection(1);
        } else if (scheduleInfo.getAdvance() == 15) {
            ((Spinner) _$_findCachedViewById(R.id.scheduleReminderSpinner)).setSelection(2);
        } else if (scheduleInfo.getAdvance() == 30) {
            ((Spinner) _$_findCachedViewById(R.id.scheduleReminderSpinner)).setSelection(3);
        } else if (scheduleInfo.getAdvance() == 60) {
            ((Spinner) _$_findCachedViewById(R.id.scheduleReminderSpinner)).setSelection(4);
        }
        ((TextView) findViewById(R.id.toolbar_title)).setText(getString(R.string.edit_schedule_reminder));
    }

    public final void g0() {
        if (this.T == EventType.EDIT) {
            scheduleUpdated(this.U);
        } else {
            onScheduleReminderAdded(this.U);
        }
        visibleTextview();
    }

    @Override // com.coveiot.android.leonardo.more.listeners.ScheduleListener
    @NotNull
    public List<ScheduleInfo> getDeletedScheduleList() {
        return this.q;
    }

    @Override // com.coveiot.android.leonardo.more.listeners.ScheduleListener
    @NotNull
    public List<ScheduleInfo> getLatestScheduleList() {
        List<ScheduleInfo> list = this.P;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("scheduleInfos");
            return null;
        }
        return list;
    }

    public final String getTAG() {
        return this.p;
    }

    public final void initToolbar() {
        ((TextView) findViewById(R.id.toolbar_title)).setText(getString(R.string.schedule_reminder));
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.mh
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityScheduleReminder.N(ActivityScheduleReminder.this, view);
            }
        });
    }

    public final boolean isLeapYear(int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1, i);
        return calendar.getActualMaximum(6) > 365;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        G();
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        ActivityScheduleBinding inflate = ActivityScheduleBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
        this.V = inflate;
        List<ScheduleInfo> list = null;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        setContentView(inflate.getRoot());
        initToolbar();
        ScheduleViewModel scheduleViewModel = (ScheduleViewModel) ViewModelProviders.of(this, new ViewModelFactory(this)).get(ScheduleViewModel.class);
        this.O = scheduleViewModel;
        if (scheduleViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("scheduleViewModel");
            scheduleViewModel = null;
        }
        scheduleViewModel.setDialogListener(this);
        ScheduleViewModel scheduleViewModel2 = this.O;
        if (scheduleViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("scheduleViewModel");
            scheduleViewModel2 = null;
        }
        scheduleViewModel2.setScheduleListener(this);
        ScheduleViewModel scheduleViewModel3 = this.O;
        if (scheduleViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("scheduleViewModel");
            scheduleViewModel3 = null;
        }
        this.Q = scheduleViewModel3.convertFromScheduleDataToScheduleInfo();
        ScheduleViewModel scheduleViewModel4 = this.O;
        if (scheduleViewModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("scheduleViewModel");
            scheduleViewModel4 = null;
        }
        List<ScheduleInfo> convertFromScheduleDataToScheduleInfo = scheduleViewModel4.convertFromScheduleDataToScheduleInfo();
        this.P = convertFromScheduleDataToScheduleInfo;
        if (convertFromScheduleDataToScheduleInfo == null) {
            Intrinsics.throwUninitializedPropertyAccessException("scheduleInfos");
            convertFromScheduleDataToScheduleInfo = null;
        }
        this.R = new ScheduleListAdapter(this, convertFromScheduleDataToScheduleInfo, this, this);
        int i = R.id.rvScheduleList;
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(i);
        ScheduleListAdapter scheduleListAdapter = this.R;
        if (scheduleListAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mScheduleListAdapter");
            scheduleListAdapter = null;
        }
        recyclerView.setAdapter(scheduleListAdapter);
        ((RecyclerView) _$_findCachedViewById(i)).setLayoutManager(new LinearLayoutManager(this));
        int i2 = R.id.btnSave;
        TextView btnSave = (TextView) _$_findCachedViewById(i2);
        Intrinsics.checkNotNullExpressionValue(btnSave, "btnSave");
        ViewUtilsKt.setSafeOnClickListener(btnSave, new a());
        ((EditText) _$_findCachedViewById(R.id.etTitle)).addTextChangedListener(new TextWatcher() { // from class: com.coveiot.android.leonardo.more.activities.ActivityScheduleReminder$onCreate$2
            @Override // android.text.TextWatcher
            public void afterTextChanged(@NotNull Editable s) {
                Intrinsics.checkNotNullParameter(s, "s");
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(@NotNull CharSequence s, int i3, int i4, int i5) {
                Intrinsics.checkNotNullParameter(s, "s");
            }

            @Override // android.text.TextWatcher
            @SuppressLint({"SetTextI18n"})
            public void onTextChanged(@NotNull CharSequence s, int i3, int i4, int i5) {
                Intrinsics.checkNotNullParameter(s, "s");
                ((TextView) ActivityScheduleReminder.this._$_findCachedViewById(R.id.tvTitleCharacterCount)).setText(s.length() + "/15");
            }
        });
        ((EditText) _$_findCachedViewById(R.id.etDescription)).addTextChangedListener(new TextWatcher() { // from class: com.coveiot.android.leonardo.more.activities.ActivityScheduleReminder$onCreate$3
            @Override // android.text.TextWatcher
            public void afterTextChanged(@NotNull Editable s) {
                Intrinsics.checkNotNullParameter(s, "s");
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(@NotNull CharSequence s, int i3, int i4, int i5) {
                Intrinsics.checkNotNullParameter(s, "s");
            }

            @Override // android.text.TextWatcher
            @SuppressLint({"SetTextI18n"})
            public void onTextChanged(@NotNull CharSequence s, int i3, int i4, int i5) {
                Intrinsics.checkNotNullParameter(s, "s");
                ((TextView) ActivityScheduleReminder.this._$_findCachedViewById(R.id.tvDescCharacterCount)).setText(s.length() + "/50");
            }
        });
        String[] stringArray = getResources().getStringArray(R.array.repeat_schedule_reminder_type);
        Intrinsics.checkNotNullExpressionValue(stringArray, "resources.getStringArray…t_schedule_reminder_type)");
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, (int) R.layout.reminder_spinner_item, (int) R.id.tvSpinnerItem, stringArray);
        int i3 = R.id.scheduleReminderSpinner;
        ((Spinner) _$_findCachedViewById(i3)).setAdapter((SpinnerAdapter) arrayAdapter);
        ((Spinner) _$_findCachedViewById(i3)).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.coveiot.android.leonardo.more.activities.ActivityScheduleReminder$onCreate$4
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(@Nullable AdapterView<?> adapterView, @NotNull View view, int i4, long j) {
                String str;
                String str2;
                String str3;
                String str4;
                Intrinsics.checkNotNullParameter(view, "view");
                ActivityScheduleReminder activityScheduleReminder = ActivityScheduleReminder.this;
                activityScheduleReminder.D = ((Spinner) activityScheduleReminder._$_findCachedViewById(R.id.scheduleReminderSpinner)).getSelectedItem().toString();
                String str5 = ActivityScheduleReminder.this.D;
                if (str5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("reminder_var");
                    str5 = null;
                }
                if (Intrinsics.areEqual(str5, ActivityScheduleReminder.this.getString(R.string.at_the_time_of_event))) {
                    ActivityScheduleReminder.this.U.setAdvance(0);
                    return;
                }
                String str6 = ActivityScheduleReminder.this.D;
                if (str6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("reminder_var");
                    str6 = null;
                }
                if (StringsKt__StringsKt.contains$default((CharSequence) str6, (CharSequence) "Minute", false, 2, (Object) null)) {
                    ScheduleInfo scheduleInfo = ActivityScheduleReminder.this.U;
                    String str7 = ActivityScheduleReminder.this.D;
                    if (str7 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("reminder_var");
                        str4 = null;
                    } else {
                        str4 = str7;
                    }
                    scheduleInfo.setAdvance(Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) str4, new String[]{HexStringBuilder.DEFAULT_SEPARATOR}, false, 0, 6, (Object) null).get(0)));
                    return;
                }
                String str8 = ActivityScheduleReminder.this.D;
                if (str8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("reminder_var");
                    str8 = null;
                }
                if (StringsKt__StringsKt.contains$default((CharSequence) str8, (CharSequence) "Hour", false, 2, (Object) null)) {
                    ScheduleInfo scheduleInfo2 = ActivityScheduleReminder.this.U;
                    String str9 = ActivityScheduleReminder.this.D;
                    if (str9 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("reminder_var");
                        str3 = null;
                    } else {
                        str3 = str9;
                    }
                    scheduleInfo2.setAdvance(Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) str3, new String[]{HexStringBuilder.DEFAULT_SEPARATOR}, false, 0, 6, (Object) null).get(0)) * 60);
                    return;
                }
                String str10 = ActivityScheduleReminder.this.D;
                if (str10 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("reminder_var");
                    str10 = null;
                }
                if (StringsKt__StringsKt.contains$default((CharSequence) str10, (CharSequence) "Day", false, 2, (Object) null)) {
                    ScheduleInfo scheduleInfo3 = ActivityScheduleReminder.this.U;
                    String str11 = ActivityScheduleReminder.this.D;
                    if (str11 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("reminder_var");
                        str2 = null;
                    } else {
                        str2 = str11;
                    }
                    scheduleInfo3.setAdvance(Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) str2, new String[]{HexStringBuilder.DEFAULT_SEPARATOR}, false, 0, 6, (Object) null).get(0)) * 1440);
                    return;
                }
                String str12 = ActivityScheduleReminder.this.D;
                if (str12 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("reminder_var");
                    str12 = null;
                }
                if (StringsKt__StringsKt.contains$default((CharSequence) str12, (CharSequence) "Week", false, 2, (Object) null)) {
                    ScheduleInfo scheduleInfo4 = ActivityScheduleReminder.this.U;
                    String str13 = ActivityScheduleReminder.this.D;
                    if (str13 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("reminder_var");
                        str = null;
                    } else {
                        str = str13;
                    }
                    scheduleInfo4.setAdvance(Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) str, new String[]{HexStringBuilder.DEFAULT_SEPARATOR}, false, 0, 6, (Object) null).get(0)) * 10080);
                }
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(@Nullable AdapterView<?> adapterView) {
            }
        });
        int i4 = R.id.btnAddScheduleReminder;
        ((TextView) _$_findCachedViewById(i4)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.lh
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityScheduleReminder.R(ActivityScheduleReminder.this, view);
            }
        });
        View scheduleDateTimeView = _$_findCachedViewById(R.id.scheduleDateTimeView);
        Intrinsics.checkNotNullExpressionValue(scheduleDateTimeView, "scheduleDateTimeView");
        ViewUtilsKt.setSafeOnClickListener(scheduleDateTimeView, new b());
        Button btnSetScheduleReminder = (Button) _$_findCachedViewById(R.id.btnSetScheduleReminder);
        Intrinsics.checkNotNullExpressionValue(btnSetScheduleReminder, "btnSetScheduleReminder");
        ViewUtilsKt.setSafeOnClickListener(btnSetScheduleReminder, new c());
        List<ScheduleInfo> list2 = this.P;
        if (list2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("scheduleInfos");
        } else {
            list = list2;
        }
        if (list.size() == 0) {
            ConstraintLayout emptyCl = (ConstraintLayout) _$_findCachedViewById(R.id.emptyCl);
            Intrinsics.checkNotNullExpressionValue(emptyCl, "emptyCl");
            visible(emptyCl);
            TextView btnSave2 = (TextView) _$_findCachedViewById(i2);
            Intrinsics.checkNotNullExpressionValue(btnSave2, "btnSave");
            gone(btnSave2);
            TextView btnAddScheduleReminder = (TextView) _$_findCachedViewById(i4);
            Intrinsics.checkNotNullExpressionValue(btnAddScheduleReminder, "btnAddScheduleReminder");
            visible(btnAddScheduleReminder);
            RecyclerView rvScheduleList = (RecyclerView) _$_findCachedViewById(i);
            Intrinsics.checkNotNullExpressionValue(rvScheduleList, "rvScheduleList");
            gone(rvScheduleList);
            return;
        }
        ConstraintLayout emptyCl2 = (ConstraintLayout) _$_findCachedViewById(R.id.emptyCl);
        Intrinsics.checkNotNullExpressionValue(emptyCl2, "emptyCl");
        gone(emptyCl2);
        RecyclerView rvScheduleList2 = (RecyclerView) _$_findCachedViewById(i);
        Intrinsics.checkNotNullExpressionValue(rvScheduleList2, "rvScheduleList");
        visible(rvScheduleList2);
        TextView btnSave3 = (TextView) _$_findCachedViewById(i2);
        Intrinsics.checkNotNullExpressionValue(btnSave3, "btnSave");
        gone(btnSave3);
        TextView btnAddScheduleReminder2 = (TextView) _$_findCachedViewById(i4);
        Intrinsics.checkNotNullExpressionValue(btnAddScheduleReminder2, "btnAddScheduleReminder");
        visible(btnAddScheduleReminder2);
    }

    @Override // com.coveiot.android.leonardo.more.listeners.ScheduleListener
    public void onDeleteSchedule(@NotNull ScheduleInfo info) {
        Intrinsics.checkNotNullParameter(info, "info");
        showDeleteConfirmationDialog(info);
    }

    @Override // com.coveiot.android.leonardo.listener.DialogListener
    public void onDismiss() {
        dismissProgress();
    }

    @Override // com.coveiot.android.leonardo.more.listeners.ScheduleListener
    public void onScheduleAdded(@NotNull ScheduleInfo info) {
        Intrinsics.checkNotNullParameter(info, "info");
    }

    public final void onScheduleReminderAdded(@NotNull ScheduleInfo info) {
        Intrinsics.checkNotNullParameter(info, "info");
        ConstraintLayout emptyCl = (ConstraintLayout) _$_findCachedViewById(R.id.emptyCl);
        Intrinsics.checkNotNullExpressionValue(emptyCl, "emptyCl");
        gone(emptyCl);
        RecyclerView rvScheduleList = (RecyclerView) _$_findCachedViewById(R.id.rvScheduleList);
        Intrinsics.checkNotNullExpressionValue(rvScheduleList, "rvScheduleList");
        visible(rvScheduleList);
        List<ScheduleInfo> list = this.P;
        List<ScheduleInfo> list2 = null;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("scheduleInfos");
            list = null;
        }
        list.add(info);
        ScheduleListAdapter scheduleListAdapter = this.R;
        if (scheduleListAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mScheduleListAdapter");
            scheduleListAdapter = null;
        }
        List<ScheduleInfo> list3 = this.P;
        if (list3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("scheduleInfos");
        } else {
            list2 = list3;
        }
        scheduleListAdapter.setScheduleInfo(list2);
        visibleTextview();
        ConstraintLayout clReminders = (ConstraintLayout) _$_findCachedViewById(R.id.clReminders);
        Intrinsics.checkNotNullExpressionValue(clReminders, "clReminders");
        visible(clReminders);
        ConstraintLayout clAddReminder = (ConstraintLayout) _$_findCachedViewById(R.id.clAddReminder);
        Intrinsics.checkNotNullExpressionValue(clAddReminder, "clAddReminder");
        gone(clAddReminder);
        TextView btnSave = (TextView) _$_findCachedViewById(R.id.btnSave);
        Intrinsics.checkNotNullExpressionValue(btnSave, "btnSave");
        visible(btnSave);
        TextView btnAddScheduleReminder = (TextView) _$_findCachedViewById(R.id.btnAddScheduleReminder);
        Intrinsics.checkNotNullExpressionValue(btnAddScheduleReminder, "btnAddScheduleReminder");
        gone(btnAddScheduleReminder);
    }

    @Override // com.coveiot.android.leonardo.more.listeners.ScheduleListener
    public void onScheduleUpdated(@NotNull ScheduleInfo info) {
        Intrinsics.checkNotNullParameter(info, "info");
    }

    @Override // com.coveiot.android.leonardo.listener.DialogListener
    public void onShowProgressDialog() {
        BaseActivity.showProgressWithTitle$default(this, null, 1, null);
    }

    public final void openDateTimeDialog() {
        final Dialog dialog = new Dialog(this, R.style.GenericDialog);
        dialog.setContentView(R.layout.dialog_schedule_date_time);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        Window window = dialog.getWindow();
        Intrinsics.checkNotNull(window);
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            Intrinsics.checkNotNullExpressionValue(attributes, "window.attributes");
            attributes.width = -1;
            attributes.height = -2;
            attributes.gravity = 17;
            window.setAttributes(attributes);
        }
        dialog.show();
        ((TextView) dialog.findViewById(R.id.ok_popup)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.nh
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityScheduleReminder.S(ActivityScheduleReminder.this, dialog, view);
            }
        });
        ((TextView) dialog.findViewById(R.id.cancel_popup)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.eh
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityScheduleReminder.T(dialog, view);
            }
        });
        W(dialog);
        c0(dialog);
        e0(dialog);
        Y(dialog);
        a0(dialog);
        U(dialog);
    }

    public final void populateAMPMDataInView() {
        this.z.clear();
        this.z.add("AM");
        this.z.add("PM");
        ArrayList<String> arrayList = this.z;
        String str = this.G;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("am_pm_var");
            str = null;
        }
        this.N = arrayList.indexOf(str);
    }

    /* JADX WARN: Removed duplicated region for block: B:75:0x0113  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0117  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void populateDaysDataInView() {
        /*
            Method dump skipped, instructions count: 289
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.leonardo.more.activities.ActivityScheduleReminder.populateDaysDataInView():void");
    }

    public final void populateHourDataInView() {
        this.x.clear();
        for (int i = 1; i < 13; i++) {
            this.x.add(String.valueOf(i));
        }
        ArrayList<String> arrayList = this.x;
        String str = this.E;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("hour_var");
            str = null;
        }
        this.L = arrayList.indexOf(str);
    }

    public final void populateMinDataInView() {
        this.y.clear();
        for (int i = 0; i < 60; i++) {
            this.y.add(String.valueOf(i));
        }
        ArrayList<String> arrayList = this.y;
        String str = this.F;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("min_var");
            str = null;
        }
        this.M = arrayList.indexOf(str);
    }

    public final void populateMonthsDataInView() {
        this.v.clear();
        for (int i = 1; i < 13; i++) {
            this.v.add(String.valueOf(i));
        }
        ArrayList<String> arrayList = this.v;
        String str = this.B;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("month_var");
            str = null;
        }
        this.J = arrayList.indexOf(str);
    }

    public final void populateYearsDataInView() {
        Calendar calendar = Calendar.getInstance();
        this.w.clear();
        this.w.add(String.valueOf(calendar.get(1)));
        calendar.add(1, 1);
        this.w.add(String.valueOf(calendar.get(1)));
        ArrayList<String> arrayList = this.w;
        String str = this.C;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("year_var");
            str = null;
        }
        this.K = arrayList.indexOf(str);
    }

    public final void scheduleUpdated(@NotNull ScheduleInfo info) {
        Intrinsics.checkNotNullParameter(info, "info");
        List<ScheduleInfo> list = this.P;
        List<ScheduleInfo> list2 = null;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("scheduleInfos");
            list = null;
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            List<ScheduleInfo> list3 = this.P;
            if (list3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("scheduleInfos");
                list3 = null;
            }
            if (list3.get(i).getScheduleId() == info.getScheduleId()) {
                List<ScheduleInfo> list4 = this.P;
                if (list4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("scheduleInfos");
                    list4 = null;
                }
                list4.remove(i);
                List<ScheduleInfo> list5 = this.P;
                if (list5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("scheduleInfos");
                    list5 = null;
                }
                list5.add(i, info);
            }
        }
        ScheduleListAdapter scheduleListAdapter = this.R;
        if (scheduleListAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mScheduleListAdapter");
            scheduleListAdapter = null;
        }
        List<ScheduleInfo> list6 = this.P;
        if (list6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("scheduleInfos");
        } else {
            list2 = list6;
        }
        scheduleListAdapter.setScheduleInfo(list2);
        visibleTextview();
        ConstraintLayout clReminders = (ConstraintLayout) _$_findCachedViewById(R.id.clReminders);
        Intrinsics.checkNotNullExpressionValue(clReminders, "clReminders");
        visible(clReminders);
        ConstraintLayout emptyCl = (ConstraintLayout) _$_findCachedViewById(R.id.emptyCl);
        Intrinsics.checkNotNullExpressionValue(emptyCl, "emptyCl");
        gone(emptyCl);
        ConstraintLayout clAddReminder = (ConstraintLayout) _$_findCachedViewById(R.id.clAddReminder);
        Intrinsics.checkNotNullExpressionValue(clAddReminder, "clAddReminder");
        gone(clAddReminder);
        TextView btnSave = (TextView) _$_findCachedViewById(R.id.btnSave);
        Intrinsics.checkNotNullExpressionValue(btnSave, "btnSave");
        visible(btnSave);
    }

    public final void showDeleteConfirmationDialog(@NotNull final ScheduleInfo info) {
        final BottomSheetDialogImageTitleMessageTwoBtns bottomSheetDialogImageTitleMessageTwoBtns;
        Intrinsics.checkNotNullParameter(info, "info");
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.info_icon_new);
        if (drawable != null) {
            String string = getString(R.string.confirmation);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.confirmation)");
            String string2 = getString(R.string.schedule_delete_confirmation);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.schedule_delete_confirmation)");
            bottomSheetDialogImageTitleMessageTwoBtns = new BottomSheetDialogImageTitleMessageTwoBtns(this, drawable, string, string2, false);
        } else {
            bottomSheetDialogImageTitleMessageTwoBtns = null;
        }
        Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessageTwoBtns);
        String string3 = getString(R.string.yes);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.yes)");
        bottomSheetDialogImageTitleMessageTwoBtns.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.qh
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityScheduleReminder.h0(BottomSheetDialogImageTitleMessageTwoBtns.this, this, info, view);
            }
        });
        String string4 = getString(R.string.f4085no);
        Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.no)");
        bottomSheetDialogImageTitleMessageTwoBtns.setNegativeButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.ph
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityScheduleReminder.i0(BottomSheetDialogImageTitleMessageTwoBtns.this, view);
            }
        });
        bottomSheetDialogImageTitleMessageTwoBtns.show();
    }

    @Override // com.coveiot.android.leonardo.listener.DialogListener
    public void showErrorDialog() {
        onDismiss();
        String string = getString(R.string.setting_couldnot_save);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.setting_couldnot_save)");
        final BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = new BottomSheetDialogOneButtonOneTitle(this, string);
        String string2 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.ok)");
        bottomSheetDialogOneButtonOneTitle.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.rh
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityScheduleReminder.j0(BottomSheetDialogOneButtonOneTitle.this, this, view);
            }
        });
        bottomSheetDialogOneButtonOneTitle.show();
    }

    @Override // com.coveiot.android.leonardo.listener.DialogListener
    public void showSuccessDialog() {
        onDismiss();
        String string = getString(R.string.success_message);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.success_message)");
        final BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = new BottomSheetDialogOneButtonOneTitle(this, string);
        String string2 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(\n            R.string.ok\n        )");
        bottomSheetDialogOneButtonOneTitle.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.sh
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityScheduleReminder.k0(BottomSheetDialogOneButtonOneTitle.this, this, view);
            }
        });
        bottomSheetDialogOneButtonOneTitle.show();
    }

    public final void toggleScheduleView() {
        int i = R.id.clReminders;
        if (((ConstraintLayout) _$_findCachedViewById(i)).getVisibility() == 0) {
            ConstraintLayout clReminders = (ConstraintLayout) _$_findCachedViewById(i);
            Intrinsics.checkNotNullExpressionValue(clReminders, "clReminders");
            gone(clReminders);
            ConstraintLayout clAddReminder = (ConstraintLayout) _$_findCachedViewById(R.id.clAddReminder);
            Intrinsics.checkNotNullExpressionValue(clAddReminder, "clAddReminder");
            visible(clAddReminder);
            return;
        }
        ConstraintLayout clReminders2 = (ConstraintLayout) _$_findCachedViewById(i);
        Intrinsics.checkNotNullExpressionValue(clReminders2, "clReminders");
        visible(clReminders2);
        ConstraintLayout clAddReminder2 = (ConstraintLayout) _$_findCachedViewById(R.id.clAddReminder);
        Intrinsics.checkNotNullExpressionValue(clAddReminder2, "clAddReminder");
        gone(clAddReminder2);
    }

    @Override // com.coveiot.android.leonardo.more.listeners.ScheduleListener
    public void visibleTextview() {
        if (J()) {
            TextView btnSave = (TextView) _$_findCachedViewById(R.id.btnSave);
            Intrinsics.checkNotNullExpressionValue(btnSave, "btnSave");
            visible(btnSave);
            this.S = true;
            return;
        }
        TextView btnSave2 = (TextView) _$_findCachedViewById(R.id.btnSave);
        Intrinsics.checkNotNullExpressionValue(btnSave2, "btnSave");
        gone(btnSave2);
        this.S = false;
    }
}
