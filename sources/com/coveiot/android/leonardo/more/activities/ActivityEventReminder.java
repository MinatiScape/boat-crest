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
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.aigestudio.wheelpicker.WheelPicker;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.EventReminder;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.listener.DialogListener;
import com.coveiot.android.leonardo.more.EventReminderHelper;
import com.coveiot.android.leonardo.more.adapters.EventReminderListAdapter;
import com.coveiot.android.leonardo.more.listeners.EventReminderListener;
import com.coveiot.android.leonardo.utils.EventType;
import com.coveiot.android.leonardo.utils.ViewUtilsKt;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.BottomSheetDialogImageTitleMessageTwoBtns;
import com.coveiot.android.theme.BottomSheetDialogOneButtonOneTitle;
import com.coveiot.android.theme.BottomSheetDialogOneButtonTitleMessage;
import com.coveiot.android.theme.BottomSheetDialogTwoButtons;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.jstyle.blesdk1860.constant.BleConst;
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
public final class ActivityEventReminder extends BaseActivity implements EventReminderListener, DialogListener, EventReminderListAdapter.EditEventReminderListener {
    public int G;
    public int H;
    public int I;
    public int J;
    public int K;
    public int L;
    public DialogListener dialogListener;
    public EventReminderHelper eventReminderHelper;
    public EventReminderListAdapter eventReminderListAdapter;
    public List<EventReminder> q;
    public List<EventReminder> r;
    public boolean s;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public final String p = ActivityEventReminder.class.getSimpleName();
    @NotNull
    public String t = "";
    @NotNull
    public String u = "";
    @NotNull
    public String v = "";
    @NotNull
    public String w = "";
    @NotNull
    public String x = "";
    @NotNull
    public String y = "";
    @NotNull
    public String z = "";
    @NotNull
    public ArrayList<String> A = new ArrayList<>();
    @NotNull
    public ArrayList<String> B = new ArrayList<>();
    @NotNull
    public ArrayList<String> C = new ArrayList<>();
    @NotNull
    public ArrayList<String> D = new ArrayList<>();
    @NotNull
    public ArrayList<String> E = new ArrayList<>();
    @NotNull
    public ArrayList<String> F = new ArrayList<>();
    @NotNull
    public String M = "1";
    @NotNull
    public final SimpleDateFormat N = new SimpleDateFormat("hh:mm a, dd MMMM yyyy", Locale.ENGLISH);
    public final Calendar O = Calendar.getInstance();
    @NotNull
    public EventReminder P = new EventReminder(0, null, 0, 0, 0, 0, 0, 127, null);
    @NotNull
    public EventType Q = EventType.ADD;

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
            ActivityEventReminder.this.openDateTimeDialog();
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
            boolean z;
            Intrinsics.checkNotNullParameter(it, "it");
            ActivityEventReminder activityEventReminder = ActivityEventReminder.this;
            int i = R.id.etEventName;
            boolean z2 = false;
            if (StringsKt__StringsKt.trim(((EditText) activityEventReminder._$_findCachedViewById(i)).getText().toString()).toString().length() == 0) {
                ActivityEventReminder activityEventReminder2 = ActivityEventReminder.this;
                String string = activityEventReminder2.getString(R.string.title_can_not_be_empty);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.title_can_not_be_empty)");
                ViewUtilsKt.toast(activityEventReminder2, string);
                z = true;
            } else {
                z = false;
            }
            if (StringsKt__StringsKt.trim(((EditText) ActivityEventReminder.this._$_findCachedViewById(R.id.etDateTime)).getText().toString()).toString().length() == 0) {
                ActivityEventReminder activityEventReminder3 = ActivityEventReminder.this;
                String string2 = activityEventReminder3.getString(R.string.time_can_not_be_empty);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.time_can_not_be_empty)");
                ViewUtilsKt.toast(activityEventReminder3, string2);
                z = true;
            }
            ActivityEventReminder.this.P.setEventName(StringsKt__StringsKt.trim(((EditText) ActivityEventReminder.this._$_findCachedViewById(i)).getText().toString()).toString());
            List<EventReminder> list = ActivityEventReminder.this.q;
            if (list == null) {
                Intrinsics.throwUninitializedPropertyAccessException("eventReminderList");
                list = null;
            }
            boolean z3 = false;
            for (EventReminder eventReminder : list) {
                if (ActivityEventReminder.this.P.getEventId() != eventReminder.getEventId()) {
                    if (kotlin.text.m.equals(ActivityEventReminder.this.P.getEventName(), eventReminder.getEventName(), true)) {
                        z2 = true;
                    }
                    if (ActivityEventReminder.this.P.getDay() == eventReminder.getDay() && ActivityEventReminder.this.P.getMonth() == eventReminder.getMonth() && ActivityEventReminder.this.P.getYear() == eventReminder.getYear() && ActivityEventReminder.this.P.getHour() == eventReminder.getHour() && ActivityEventReminder.this.P.getMinute() == eventReminder.getMinute()) {
                        z3 = true;
                    }
                }
            }
            if (z2) {
                ActivityEventReminder activityEventReminder4 = ActivityEventReminder.this;
                String string3 = activityEventReminder4.getString(R.string.event_title_can_not_be_same);
                Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.event_title_can_not_be_same)");
                ViewUtilsKt.toast(activityEventReminder4, string3);
            } else if (z3) {
                ActivityEventReminder activityEventReminder5 = ActivityEventReminder.this;
                String string4 = activityEventReminder5.getString(R.string.there_is_already_event);
                Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.there_is_already_event)");
                ViewUtilsKt.toast(activityEventReminder5, string4);
            } else if (!z) {
                ActivityEventReminder.this.l0();
                ActivityEventReminder.this.f0();
                ((EditText) ActivityEventReminder.this._$_findCachedViewById(R.id.etDateTime)).setText("");
                ((EditText) ActivityEventReminder.this._$_findCachedViewById(R.id.etEventName)).setText("");
                ActivityEventReminder activityEventReminder6 = ActivityEventReminder.this;
                TextView btnSave = (TextView) activityEventReminder6._$_findCachedViewById(R.id.btnSave);
                Intrinsics.checkNotNullExpressionValue(btnSave, "btnSave");
                activityEventReminder6.visible(btnSave);
            }
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
            Intrinsics.checkNotNullParameter(it, "it");
            ActivityEventReminder.this.sendToBle();
        }
    }

    /* loaded from: classes5.dex */
    public static final class d extends Lambda implements Function1<View, Unit> {
        public d() {
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
            List list = ActivityEventReminder.this.q;
            List list2 = null;
            if (list == null) {
                Intrinsics.throwUninitializedPropertyAccessException("eventReminderList");
                list = null;
            }
            if (list.size() >= 10) {
                ActivityEventReminder activityEventReminder = ActivityEventReminder.this;
                String string = activityEventReminder.getString(R.string.maximum_no_of_event_reminder);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.maximum_no_of_event_reminder)");
                ViewUtilsKt.toast(activityEventReminder, string);
                return;
            }
            ActivityEventReminder.this.Q = EventType.ADD;
            ActivityEventReminder.this.l0();
            ActivityEventReminder activityEventReminder2 = ActivityEventReminder.this;
            TextView btnAddEvent = (TextView) activityEventReminder2._$_findCachedViewById(R.id.btnAddEvent);
            Intrinsics.checkNotNullExpressionValue(btnAddEvent, "btnAddEvent");
            activityEventReminder2.gone(btnAddEvent);
            Calendar c = Calendar.getInstance();
            ActivityEventReminder.this.P.setYear(c.get(1));
            ActivityEventReminder.this.P.setDay(c.get(6));
            ActivityEventReminder.this.P.setMonth(c.get(2));
            ActivityEventReminder.this.P.setMinute(c.get(12));
            ActivityEventReminder.this.P.setHour(c.get(11));
            ActivityEventReminder activityEventReminder3 = ActivityEventReminder.this;
            Intrinsics.checkNotNullExpressionValue(c, "c");
            activityEventReminder3.N(c);
            List list3 = ActivityEventReminder.this.q;
            if (list3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("eventReminderList");
                list3 = null;
            }
            if (list3.size() == 0) {
                ActivityEventReminder.this.P.setEventId(1);
                return;
            }
            EventReminder eventReminder = ActivityEventReminder.this.P;
            List list4 = ActivityEventReminder.this.q;
            if (list4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("eventReminderList");
            } else {
                list2 = list4;
            }
            eventReminder.setEventId(list2.size() + 1);
        }
    }

    public static final void H(ActivityEventReminder this$0, BottomSheetDialogTwoButtons dialog, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        ((TextView) this$0._$_findCachedViewById(R.id.btnSave)).performClick();
        dialog.dismiss();
        this$0.finish();
    }

    public static final void I(BottomSheetDialogTwoButtons dialog, ActivityEventReminder this$0, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        dialog.dismiss();
        this$0.finish();
    }

    public static final void O(ActivityEventReminder this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.G();
    }

    public static final void R(ActivityEventReminder this$0, Dialog dialog, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        if (this$0.Q()) {
            dialog.dismiss();
        }
    }

    public static final void S(Dialog dialog, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        dialog.dismiss();
    }

    public static final void U(ActivityEventReminder this$0, WheelPicker wheelPicker, Object obj, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.z = obj.toString();
    }

    public static final void W(ActivityEventReminder this$0, WheelPicker wheelPicker, Object obj, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.t = obj.toString();
    }

    public static final void Y(ActivityEventReminder this$0, WheelPicker wheelPicker, Object obj, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.x = obj.toString();
    }

    public static final void a0(ActivityEventReminder this$0, WheelPicker wheelPicker, Object obj, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.y = obj.toString();
    }

    public static final void c0(ActivityEventReminder this$0, Dialog dialog, WheelPicker wheelPicker, Object obj, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        this$0.u = obj.toString();
        this$0.V(dialog);
    }

    public static final void e0(ActivityEventReminder this$0, Dialog dialog, WheelPicker wheelPicker, Object obj, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        this$0.v = obj.toString();
        this$0.V(dialog);
    }

    public static final void g0(BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonTitleMessage, "$bottomSheetDialogOneButtonTitleMessage");
        bottomSheetDialogOneButtonTitleMessage.dismiss();
    }

    public static final void h0(BottomSheetDialogImageTitleMessageTwoBtns bottomSheetDialogImageTitleMessageTwoBtns, ActivityEventReminder this$0, EventReminder info, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(info, "$info");
        bottomSheetDialogImageTitleMessageTwoBtns.dismiss();
        this$0.K(info);
    }

    public static final void i0(BottomSheetDialogImageTitleMessageTwoBtns bottomSheetDialogImageTitleMessageTwoBtns, View view) {
        bottomSheetDialogImageTitleMessageTwoBtns.dismiss();
    }

    public static final void j0(BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle, ActivityEventReminder this$0, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonOneTitle, "$bottomSheetDialogOneButtonOneTitle");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        bottomSheetDialogOneButtonOneTitle.dismiss();
        this$0.finish();
    }

    public static final void k0(BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle, ActivityEventReminder this$0, View view) {
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
            bottomSheetDialogTwoButtons.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.g9
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityEventReminder.H(ActivityEventReminder.this, bottomSheetDialogTwoButtons, view);
                }
            });
            String string4 = getString(R.string.discard);
            Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.discard)");
            bottomSheetDialogTwoButtons.setNegativeButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.m9
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityEventReminder.I(BottomSheetDialogTwoButtons.this, this, view);
                }
            });
            bottomSheetDialogTwoButtons.show();
            return;
        }
        finish();
    }

    public final boolean J() {
        int size = EventReminderHelper.Companion.getInstance(this).convertFromEventReminderDataToEventReminder().size();
        List<EventReminder> list = this.q;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("eventReminderList");
            list = null;
        }
        if (size != list.size()) {
            return true;
        }
        List<EventReminder> list2 = this.q;
        if (list2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("eventReminderList");
            list2 = null;
        }
        int size2 = list2.size();
        for (int i = 0; i < size2; i++) {
            List<EventReminder> list3 = this.q;
            if (list3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("eventReminderList");
                list3 = null;
            }
            if (P(list3.get(i))) {
                return true;
            }
        }
        return false;
    }

    public final void K(EventReminder eventReminder) {
        List<EventReminder> list = this.q;
        List<EventReminder> list2 = null;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("eventReminderList");
            list = null;
        }
        int size = list.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                break;
            }
            List<EventReminder> list3 = this.q;
            if (list3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("eventReminderList");
                list3 = null;
            }
            if (list3.get(i).getEventId() == eventReminder.getEventId()) {
                List<EventReminder> list4 = this.q;
                if (list4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("eventReminderList");
                    list4 = null;
                }
                list4.remove(i);
            } else {
                i++;
            }
        }
        ConstraintLayout emptyCl = (ConstraintLayout) _$_findCachedViewById(R.id.emptyCl);
        Intrinsics.checkNotNullExpressionValue(emptyCl, "emptyCl");
        gone(emptyCl);
        RecyclerView eventReminderRecyclerView = (RecyclerView) _$_findCachedViewById(R.id.eventReminderRecyclerView);
        Intrinsics.checkNotNullExpressionValue(eventReminderRecyclerView, "eventReminderRecyclerView");
        visible(eventReminderRecyclerView);
        TextView btnSave = (TextView) _$_findCachedViewById(R.id.btnSave);
        Intrinsics.checkNotNullExpressionValue(btnSave, "btnSave");
        visible(btnSave);
        EventReminderListAdapter eventReminderListAdapter = getEventReminderListAdapter();
        List<EventReminder> list5 = this.q;
        if (list5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("eventReminderList");
        } else {
            list2 = list5;
        }
        eventReminderListAdapter.setEventReminder(list2);
        visibleTextview();
        sendToBle();
    }

    public final void L(EventReminder eventReminder) {
        ConstraintLayout emptyCl = (ConstraintLayout) _$_findCachedViewById(R.id.emptyCl);
        Intrinsics.checkNotNullExpressionValue(emptyCl, "emptyCl");
        gone(emptyCl);
        RecyclerView eventReminderRecyclerView = (RecyclerView) _$_findCachedViewById(R.id.eventReminderRecyclerView);
        Intrinsics.checkNotNullExpressionValue(eventReminderRecyclerView, "eventReminderRecyclerView");
        visible(eventReminderRecyclerView);
        TextView btnSave = (TextView) _$_findCachedViewById(R.id.btnSave);
        Intrinsics.checkNotNullExpressionValue(btnSave, "btnSave");
        visible(btnSave);
        TextView btnAddEvent = (TextView) _$_findCachedViewById(R.id.btnAddEvent);
        Intrinsics.checkNotNullExpressionValue(btnAddEvent, "btnAddEvent");
        gone(btnAddEvent);
        List<EventReminder> list = this.q;
        List<EventReminder> list2 = null;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("eventReminderList");
            list = null;
        }
        list.add(eventReminder);
        EventReminderListAdapter eventReminderListAdapter = getEventReminderListAdapter();
        List<EventReminder> list3 = this.q;
        if (list3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("eventReminderList");
        } else {
            list2 = list3;
        }
        eventReminderListAdapter.setEventReminder(list2);
        visibleTextview();
    }

    public final void M(EventReminder eventReminder) {
        List<EventReminder> list = this.q;
        List<EventReminder> list2 = null;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("eventReminderList");
            list = null;
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            List<EventReminder> list3 = this.q;
            if (list3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("eventReminderList");
                list3 = null;
            }
            if (list3.get(i).getEventId() == eventReminder.getEventId()) {
                List<EventReminder> list4 = this.q;
                if (list4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("eventReminderList");
                    list4 = null;
                }
                list4.remove(i);
                List<EventReminder> list5 = this.q;
                if (list5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("eventReminderList");
                    list5 = null;
                }
                list5.add(i, eventReminder);
            }
        }
        EventReminderListAdapter eventReminderListAdapter = getEventReminderListAdapter();
        List<EventReminder> list6 = this.q;
        if (list6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("eventReminderList");
        } else {
            list2 = list6;
        }
        eventReminderListAdapter.setEventReminder(list2);
        visibleTextview();
        ConstraintLayout clEvents = (ConstraintLayout) _$_findCachedViewById(R.id.clEvents);
        Intrinsics.checkNotNullExpressionValue(clEvents, "clEvents");
        visible(clEvents);
        ConstraintLayout clAddEvent = (ConstraintLayout) _$_findCachedViewById(R.id.clAddEvent);
        Intrinsics.checkNotNullExpressionValue(clAddEvent, "clAddEvent");
        gone(clAddEvent);
        TextView btnSave = (TextView) _$_findCachedViewById(R.id.btnSave);
        Intrinsics.checkNotNullExpressionValue(btnSave, "btnSave");
        visible(btnSave);
        TextView btnAddEvent = (TextView) _$_findCachedViewById(R.id.btnAddEvent);
        Intrinsics.checkNotNullExpressionValue(btnAddEvent, "btnAddEvent");
        gone(btnAddEvent);
    }

    public final void N(Calendar calendar) {
        this.t = String.valueOf(calendar.get(5));
        this.u = String.valueOf(calendar.get(2) + 1);
        this.v = String.valueOf(calendar.get(1));
        this.x = String.valueOf(calendar.get(10));
        this.y = String.valueOf(calendar.get(12));
        this.z = Intrinsics.areEqual(String.valueOf(calendar.get(9)), "1") ? "PM" : "AM";
    }

    public final boolean P(EventReminder eventReminder) {
        LogHelper.d(this.p, "isChanged called-> " + eventReminder.getEventId());
        List<EventReminder> list = this.r;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("eventReminderPreference");
            list = null;
        }
        int size = list.size();
        boolean z = false;
        for (int i = 0; i < size; i++) {
            int eventId = eventReminder.getEventId();
            List<EventReminder> list2 = this.r;
            if (list2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("eventReminderPreference");
                list2 = null;
            }
            if (eventId == list2.get(i).getEventId()) {
                String eventName = eventReminder.getEventName();
                List<EventReminder> list3 = this.r;
                if (list3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("eventReminderPreference");
                    list3 = null;
                }
                if (Intrinsics.areEqual(eventName, list3.get(i).getEventName())) {
                    int repeatType = eventReminder.getRepeatType();
                    List<EventReminder> list4 = this.r;
                    if (list4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("eventReminderPreference");
                        list4 = null;
                    }
                    if (repeatType == list4.get(i).getRepeatType()) {
                        int day = eventReminder.getDay();
                        List<EventReminder> list5 = this.r;
                        if (list5 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("eventReminderPreference");
                            list5 = null;
                        }
                        if (day == list5.get(i).getDay()) {
                            int month = eventReminder.getMonth();
                            List<EventReminder> list6 = this.r;
                            if (list6 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("eventReminderPreference");
                                list6 = null;
                            }
                            if (month == list6.get(i).getMonth()) {
                                int year = eventReminder.getYear();
                                List<EventReminder> list7 = this.r;
                                if (list7 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("eventReminderPreference");
                                    list7 = null;
                                }
                                if (year == list7.get(i).getYear()) {
                                    int hour = eventReminder.getHour();
                                    List<EventReminder> list8 = this.r;
                                    if (list8 == null) {
                                        Intrinsics.throwUninitializedPropertyAccessException("eventReminderPreference");
                                        list8 = null;
                                    }
                                    if (hour == list8.get(i).getHour()) {
                                        int minute = eventReminder.getMinute();
                                        List<EventReminder> list9 = this.r;
                                        if (list9 == null) {
                                            Intrinsics.throwUninitializedPropertyAccessException("eventReminderPreference");
                                            list9 = null;
                                        }
                                        if (minute == list9.get(i).getMinute()) {
                                            int year2 = eventReminder.getYear();
                                            List<EventReminder> list10 = this.r;
                                            if (list10 == null) {
                                                Intrinsics.throwUninitializedPropertyAccessException("eventReminderPreference");
                                                list10 = null;
                                            }
                                            if (year2 == list10.get(i).getYear()) {
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
        calendar.set(1, Integer.parseInt(this.v));
        calendar.set(2, Integer.parseInt(this.u) - 1);
        calendar.set(5, Integer.parseInt(this.t));
        if (Intrinsics.areEqual(this.z, "AM")) {
            parseInt = Intrinsics.areEqual(this.x, BleConst.CMD_Reset) ? 0 : Integer.parseInt(this.x);
        } else {
            parseInt = Intrinsics.areEqual(this.x, BleConst.CMD_Reset) ? 12 : Integer.parseInt(this.x) + 12;
        }
        calendar.set(11, parseInt);
        calendar.set(12, Integer.parseInt(this.y));
        calendar.set(13, 0);
        calendar.set(14, 0);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(13, 0);
        calendar2.set(14, 0);
        if (calendar2.getTimeInMillis() > calendar.getTimeInMillis()) {
            String string = getString(R.string.please_select_future_time_event);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.pleas…select_future_time_event)");
            ViewUtilsKt.toast(this, string);
            return false;
        }
        this.P.setYear(calendar.get(1));
        this.P.setMonth(calendar.get(2));
        this.P.setDay(calendar.get(5));
        this.P.setHour(calendar.get(11));
        this.P.setMinute(calendar.get(12));
        ((EditText) _$_findCachedViewById(R.id.etDateTime)).setText(this.N.format(Long.valueOf(calendar.getTimeInMillis())));
        return true;
    }

    public final void T(Dialog dialog) {
        populateAMPMDataInView();
        int i = R.id.amPmPicker;
        ((WheelPicker) dialog.findViewById(i)).setData(this.F);
        ((WheelPicker) dialog.findViewById(i)).setAtmospheric(true);
        ((WheelPicker) dialog.findViewById(i)).setVisibleItemCount(2);
        ((WheelPicker) dialog.findViewById(i)).setItemSpace(30);
        ((WheelPicker) dialog.findViewById(i)).setSelectedItemPosition(this.L);
        ((WheelPicker) dialog.findViewById(i)).setCyclic(false);
        ((WheelPicker) dialog.findViewById(i)).setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() { // from class: com.coveiot.android.leonardo.more.activities.b9
            @Override // com.aigestudio.wheelpicker.WheelPicker.OnItemSelectedListener
            public final void onItemSelected(WheelPicker wheelPicker, Object obj, int i2) {
                ActivityEventReminder.U(ActivityEventReminder.this, wheelPicker, obj, i2);
            }
        });
    }

    public final void V(Dialog dialog) {
        populateDaysDataInView();
        int i = R.id.dayPicker;
        ((WheelPicker) dialog.findViewById(i)).setData(this.A);
        ((WheelPicker) dialog.findViewById(i)).setAtmospheric(true);
        ((WheelPicker) dialog.findViewById(i)).setVisibleItemCount(5);
        ((WheelPicker) dialog.findViewById(i)).setItemSpace(30);
        ((WheelPicker) dialog.findViewById(i)).setSelectedItemPosition(this.G);
        ((WheelPicker) dialog.findViewById(i)).setCyclic(true);
        ((WheelPicker) dialog.findViewById(i)).setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() { // from class: com.coveiot.android.leonardo.more.activities.z8
            @Override // com.aigestudio.wheelpicker.WheelPicker.OnItemSelectedListener
            public final void onItemSelected(WheelPicker wheelPicker, Object obj, int i2) {
                ActivityEventReminder.W(ActivityEventReminder.this, wheelPicker, obj, i2);
            }
        });
    }

    public final void X(Dialog dialog) {
        populateHourDataInView();
        int i = R.id.hourPicker;
        ((WheelPicker) dialog.findViewById(i)).setData(this.D);
        ((WheelPicker) dialog.findViewById(i)).setAtmospheric(true);
        ((WheelPicker) dialog.findViewById(i)).setVisibleItemCount(5);
        ((WheelPicker) dialog.findViewById(i)).setItemSpace(30);
        ((WheelPicker) dialog.findViewById(i)).setSelectedItemPosition(this.J);
        ((WheelPicker) dialog.findViewById(i)).setCyclic(true);
        ((WheelPicker) dialog.findViewById(i)).setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() { // from class: com.coveiot.android.leonardo.more.activities.a9
            @Override // com.aigestudio.wheelpicker.WheelPicker.OnItemSelectedListener
            public final void onItemSelected(WheelPicker wheelPicker, Object obj, int i2) {
                ActivityEventReminder.Y(ActivityEventReminder.this, wheelPicker, obj, i2);
            }
        });
    }

    public final void Z(Dialog dialog) {
        populateMinDataInView();
        int i = R.id.minPicker;
        ((WheelPicker) dialog.findViewById(i)).setData(this.E);
        ((WheelPicker) dialog.findViewById(i)).setAtmospheric(true);
        ((WheelPicker) dialog.findViewById(i)).setVisibleItemCount(5);
        ((WheelPicker) dialog.findViewById(i)).setItemSpace(30);
        ((WheelPicker) dialog.findViewById(i)).setSelectedItemPosition(this.K);
        ((WheelPicker) dialog.findViewById(i)).setCyclic(true);
        ((WheelPicker) dialog.findViewById(i)).setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() { // from class: com.coveiot.android.leonardo.more.activities.y8
            @Override // com.aigestudio.wheelpicker.WheelPicker.OnItemSelectedListener
            public final void onItemSelected(WheelPicker wheelPicker, Object obj, int i2) {
                ActivityEventReminder.a0(ActivityEventReminder.this, wheelPicker, obj, i2);
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

    public final void b0(final Dialog dialog) {
        populateMonthsDataInView();
        int i = R.id.monthPicker;
        ((WheelPicker) dialog.findViewById(i)).setData(this.B);
        ((WheelPicker) dialog.findViewById(i)).setAtmospheric(true);
        ((WheelPicker) dialog.findViewById(i)).setVisibleItemCount(5);
        ((WheelPicker) dialog.findViewById(i)).setItemSpace(30);
        ((WheelPicker) dialog.findViewById(i)).setSelectedItemPosition(this.H);
        ((WheelPicker) dialog.findViewById(i)).setCyclic(true);
        ((WheelPicker) dialog.findViewById(i)).setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() { // from class: com.coveiot.android.leonardo.more.activities.c9
            @Override // com.aigestudio.wheelpicker.WheelPicker.OnItemSelectedListener
            public final void onItemSelected(WheelPicker wheelPicker, Object obj, int i2) {
                ActivityEventReminder.c0(ActivityEventReminder.this, dialog, wheelPicker, obj, i2);
            }
        });
    }

    public final void d0(final Dialog dialog) {
        populateYearsDataInView();
        int i = R.id.yearPicker;
        ((WheelPicker) dialog.findViewById(i)).setData(this.C);
        ((WheelPicker) dialog.findViewById(i)).setAtmospheric(true);
        ((WheelPicker) dialog.findViewById(i)).setVisibleItemCount(2);
        ((WheelPicker) dialog.findViewById(i)).setItemSpace(30);
        ((WheelPicker) dialog.findViewById(i)).setSelectedItemPosition(this.I);
        ((WheelPicker) dialog.findViewById(i)).setCyclic(false);
        this.v = String.valueOf(this.O.get(1));
        ((WheelPicker) dialog.findViewById(i)).setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() { // from class: com.coveiot.android.leonardo.more.activities.d9
            @Override // com.aigestudio.wheelpicker.WheelPicker.OnItemSelectedListener
            public final void onItemSelected(WheelPicker wheelPicker, Object obj, int i2) {
                ActivityEventReminder.e0(ActivityEventReminder.this, dialog, wheelPicker, obj, i2);
            }
        });
    }

    @Override // com.coveiot.android.leonardo.more.adapters.EventReminderListAdapter.EditEventReminderListener
    public void editListener(@NotNull EventReminder eventReminder, @NotNull EventType typeEvent, @NotNull List<EventReminder> mEventRemindersList) {
        Intrinsics.checkNotNullParameter(eventReminder, "eventReminder");
        Intrinsics.checkNotNullParameter(typeEvent, "typeEvent");
        Intrinsics.checkNotNullParameter(mEventRemindersList, "mEventRemindersList");
        l0();
        this.Q = typeEvent;
        this.P = eventReminder;
        Calendar cal = Calendar.getInstance();
        cal.set(1, this.P.getYear());
        cal.set(2, this.P.getMonth());
        cal.set(5, this.P.getDay());
        cal.set(11, this.P.getHour());
        cal.set(12, this.P.getMinute());
        cal.set(13, 0);
        cal.set(14, 0);
        Intrinsics.checkNotNullExpressionValue(cal, "cal");
        N(cal);
        ((EditText) _$_findCachedViewById(R.id.etDateTime)).setText(this.N.format(Long.valueOf(cal.getTimeInMillis())));
        ((EditText) _$_findCachedViewById(R.id.etEventName)).setText(this.P.getEventName());
        ((Spinner) _$_findCachedViewById(R.id.reminderSpinner)).setSelection(this.P.getRepeatType());
        ((TextView) findViewById(R.id.toolbar_title)).setText(getString(R.string.edit_event_reminder));
    }

    public final void f0() {
        if (this.Q == EventType.EDIT) {
            M(this.P);
        } else {
            L(this.P);
        }
        visibleTextview();
    }

    public final boolean getBoolSaveVisible() {
        return this.s;
    }

    @NotNull
    public final DialogListener getDialogListener() {
        DialogListener dialogListener = this.dialogListener;
        if (dialogListener != null) {
            return dialogListener;
        }
        Intrinsics.throwUninitializedPropertyAccessException("dialogListener");
        return null;
    }

    @NotNull
    public final EventReminderHelper getEventReminderHelper() {
        EventReminderHelper eventReminderHelper = this.eventReminderHelper;
        if (eventReminderHelper != null) {
            return eventReminderHelper;
        }
        Intrinsics.throwUninitializedPropertyAccessException("eventReminderHelper");
        return null;
    }

    @NotNull
    public final EventReminderListAdapter getEventReminderListAdapter() {
        EventReminderListAdapter eventReminderListAdapter = this.eventReminderListAdapter;
        if (eventReminderListAdapter != null) {
            return eventReminderListAdapter;
        }
        Intrinsics.throwUninitializedPropertyAccessException("eventReminderListAdapter");
        return null;
    }

    @Override // com.coveiot.android.leonardo.more.listeners.EventReminderListener
    @NotNull
    public List<EventReminder> getLatestEventReminderList() {
        List<EventReminder> list = this.q;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("eventReminderList");
            return null;
        }
        return list;
    }

    public final String getTAG() {
        return this.p;
    }

    public final void initToolbar() {
        ((TextView) findViewById(R.id.toolbar_title)).setText(getString(R.string.event_reminder));
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.e9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityEventReminder.O(ActivityEventReminder.this, view);
            }
        });
    }

    public final boolean isLeapYear(int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1, i);
        return calendar.getActualMaximum(6) > 365;
    }

    public final void l0() {
        int i = R.id.clEvents;
        if (((ConstraintLayout) _$_findCachedViewById(i)).getVisibility() == 0) {
            ConstraintLayout clEvents = (ConstraintLayout) _$_findCachedViewById(i);
            Intrinsics.checkNotNullExpressionValue(clEvents, "clEvents");
            gone(clEvents);
            ConstraintLayout clAddEvent = (ConstraintLayout) _$_findCachedViewById(R.id.clAddEvent);
            Intrinsics.checkNotNullExpressionValue(clAddEvent, "clAddEvent");
            visible(clAddEvent);
            return;
        }
        ConstraintLayout clEvents2 = (ConstraintLayout) _$_findCachedViewById(i);
        Intrinsics.checkNotNullExpressionValue(clEvents2, "clEvents");
        visible(clEvents2);
        ConstraintLayout clAddEvent2 = (ConstraintLayout) _$_findCachedViewById(R.id.clAddEvent);
        Intrinsics.checkNotNullExpressionValue(clAddEvent2, "clAddEvent");
        gone(clAddEvent2);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        G();
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_event_reminder);
        initToolbar();
        setEventReminderHelper(EventReminderHelper.Companion.getInstance(this));
        getEventReminderHelper().setDialogListener(this);
        this.r = getEventReminderHelper().convertFromEventReminderDataToEventReminder();
        List<EventReminder> convertFromEventReminderDataToEventReminder = getEventReminderHelper().convertFromEventReminderDataToEventReminder();
        this.q = convertFromEventReminderDataToEventReminder;
        List<EventReminder> list = null;
        if (convertFromEventReminderDataToEventReminder == null) {
            Intrinsics.throwUninitializedPropertyAccessException("eventReminderList");
            convertFromEventReminderDataToEventReminder = null;
        }
        setEventReminderListAdapter(new EventReminderListAdapter(this, convertFromEventReminderDataToEventReminder, this, this));
        int i = R.id.eventReminderRecyclerView;
        ((RecyclerView) _$_findCachedViewById(i)).setAdapter(getEventReminderListAdapter());
        ((RecyclerView) _$_findCachedViewById(i)).setLayoutManager(new LinearLayoutManager(this));
        String[] stringArray = getResources().getStringArray(R.array.repeat_event_reminder_type);
        Intrinsics.checkNotNullExpressionValue(stringArray, "resources.getStringArray…peat_event_reminder_type)");
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, (int) R.layout.reminder_spinner_item, (int) R.id.tvSpinnerItem, stringArray);
        int i2 = R.id.reminderSpinner;
        ((Spinner) _$_findCachedViewById(i2)).setAdapter((SpinnerAdapter) arrayAdapter);
        ((Spinner) _$_findCachedViewById(i2)).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.coveiot.android.leonardo.more.activities.ActivityEventReminder$onCreate$1
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(@Nullable AdapterView<?> adapterView, @NotNull View view, int i3, long j) {
                String str;
                Intrinsics.checkNotNullParameter(view, "view");
                ActivityEventReminder activityEventReminder = ActivityEventReminder.this;
                activityEventReminder.w = ((Spinner) activityEventReminder._$_findCachedViewById(R.id.reminderSpinner)).getSelectedItem().toString();
                str = ActivityEventReminder.this.w;
                if (Intrinsics.areEqual(str, ActivityEventReminder.this.getString(R.string.once))) {
                    ActivityEventReminder.this.P.setRepeatType(0);
                } else if (Intrinsics.areEqual(str, ActivityEventReminder.this.getString(R.string.daily))) {
                    ActivityEventReminder.this.P.setRepeatType(1);
                } else if (Intrinsics.areEqual(str, ActivityEventReminder.this.getString(R.string.every_week))) {
                    ActivityEventReminder.this.P.setRepeatType(2);
                } else if (Intrinsics.areEqual(str, ActivityEventReminder.this.getString(R.string.monthly))) {
                    ActivityEventReminder.this.P.setRepeatType(3);
                } else if (Intrinsics.areEqual(str, ActivityEventReminder.this.getString(R.string.yearly))) {
                    ActivityEventReminder.this.P.setRepeatType(4);
                }
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(@Nullable AdapterView<?> adapterView) {
            }
        });
        View dateTimeView = _$_findCachedViewById(R.id.dateTimeView);
        Intrinsics.checkNotNullExpressionValue(dateTimeView, "dateTimeView");
        ViewUtilsKt.setSafeOnClickListener(dateTimeView, new a());
        Button btnSetReminder = (Button) _$_findCachedViewById(R.id.btnSetReminder);
        Intrinsics.checkNotNullExpressionValue(btnSetReminder, "btnSetReminder");
        ViewUtilsKt.setSafeOnClickListener(btnSetReminder, new b());
        ((EditText) _$_findCachedViewById(R.id.etEventName)).addTextChangedListener(new TextWatcher() { // from class: com.coveiot.android.leonardo.more.activities.ActivityEventReminder$onCreate$4
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
                ((TextView) ActivityEventReminder.this._$_findCachedViewById(R.id.tvCharacterCount)).setText(s.length() + "/15");
            }
        });
        int i3 = R.id.btnSave;
        TextView btnSave = (TextView) _$_findCachedViewById(i3);
        Intrinsics.checkNotNullExpressionValue(btnSave, "btnSave");
        ViewUtilsKt.setSafeOnClickListener(btnSave, new c());
        List<EventReminder> list2 = this.q;
        if (list2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("eventReminderList");
        } else {
            list = list2;
        }
        if (list.size() == 0) {
            ConstraintLayout emptyCl = (ConstraintLayout) _$_findCachedViewById(R.id.emptyCl);
            Intrinsics.checkNotNullExpressionValue(emptyCl, "emptyCl");
            visible(emptyCl);
            TextView btnSave2 = (TextView) _$_findCachedViewById(i3);
            Intrinsics.checkNotNullExpressionValue(btnSave2, "btnSave");
            gone(btnSave2);
            TextView btnAddEvent = (TextView) _$_findCachedViewById(R.id.btnAddEvent);
            Intrinsics.checkNotNullExpressionValue(btnAddEvent, "btnAddEvent");
            visible(btnAddEvent);
            RecyclerView eventReminderRecyclerView = (RecyclerView) _$_findCachedViewById(i);
            Intrinsics.checkNotNullExpressionValue(eventReminderRecyclerView, "eventReminderRecyclerView");
            gone(eventReminderRecyclerView);
        } else {
            ConstraintLayout emptyCl2 = (ConstraintLayout) _$_findCachedViewById(R.id.emptyCl);
            Intrinsics.checkNotNullExpressionValue(emptyCl2, "emptyCl");
            gone(emptyCl2);
            RecyclerView eventReminderRecyclerView2 = (RecyclerView) _$_findCachedViewById(i);
            Intrinsics.checkNotNullExpressionValue(eventReminderRecyclerView2, "eventReminderRecyclerView");
            visible(eventReminderRecyclerView2);
            TextView btnAddEvent2 = (TextView) _$_findCachedViewById(R.id.btnAddEvent);
            Intrinsics.checkNotNullExpressionValue(btnAddEvent2, "btnAddEvent");
            visible(btnAddEvent2);
        }
        TextView btnAddEvent3 = (TextView) _$_findCachedViewById(R.id.btnAddEvent);
        Intrinsics.checkNotNullExpressionValue(btnAddEvent3, "btnAddEvent");
        ViewUtilsKt.setSafeOnClickListener(btnAddEvent3, new d());
    }

    @Override // com.coveiot.android.leonardo.more.listeners.EventReminderListener
    public void onDeleteEventReminder(@NotNull EventReminder info) {
        Intrinsics.checkNotNullParameter(info, "info");
        if (!AppUtils.isBluetoothEnabled(this)) {
            Toast.makeText(this, (int) R.string.bluetooth_off_message, 0).show();
        } else {
            showDeleteConfirmationDialog(info);
        }
    }

    @Override // com.coveiot.android.leonardo.listener.DialogListener
    public void onDismiss() {
        dismissProgress();
    }

    @Override // com.coveiot.android.leonardo.more.listeners.EventReminderListener
    public void onEventReminderAdded(@NotNull EventReminder info) {
        Intrinsics.checkNotNullParameter(info, "info");
    }

    @Override // com.coveiot.android.leonardo.more.listeners.EventReminderListener
    public void onEventReminderUpdated(@NotNull EventReminder info) {
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
        ((TextView) dialog.findViewById(R.id.ok_popup)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.f9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityEventReminder.R(ActivityEventReminder.this, dialog, view);
            }
        });
        ((TextView) dialog.findViewById(R.id.cancel_popup)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.x8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityEventReminder.S(dialog, view);
            }
        });
        V(dialog);
        b0(dialog);
        d0(dialog);
        X(dialog);
        Z(dialog);
        T(dialog);
    }

    public final void populateAMPMDataInView() {
        this.F.clear();
        this.F.add("AM");
        this.F.add("PM");
        this.L = this.F.indexOf(this.z);
    }

    public final void populateDaysDataInView() {
        this.A.clear();
        int i = 1;
        if (kotlin.text.m.equals(this.u, "2", true) && !isLeapYear(Integer.parseInt(this.v))) {
            while (true) {
                int i2 = i + 1;
                this.A.add(String.valueOf(i));
                if (i2 > 28) {
                    break;
                }
                i = i2;
            }
            if (Integer.parseInt(this.t) > 28) {
                this.t = this.M;
            }
        } else if (kotlin.text.m.equals(this.u, "2", true) && isLeapYear(Integer.parseInt(this.v))) {
            while (true) {
                int i3 = i + 1;
                this.A.add(String.valueOf(i));
                if (i3 > 29) {
                    break;
                }
                i = i3;
            }
            if (Integer.parseInt(this.t) > 29) {
                this.t = this.M;
            }
        } else if (kotlin.text.m.equals(this.u, BleConst.GetDeviceInfo, true) || kotlin.text.m.equals(this.u, BleConst.CMD_Set_Mac, true) || kotlin.text.m.equals(this.u, BleConst.GetDeviceBatteryLevel, true) || kotlin.text.m.equals(this.u, BleConst.GetDeviceVersion, true)) {
            while (true) {
                int i4 = i + 1;
                this.A.add(String.valueOf(i));
                if (i4 > 30) {
                    break;
                }
                i = i4;
            }
            if (Integer.parseInt(this.t) > 30) {
                this.t = this.M;
            }
        } else {
            while (true) {
                int i5 = i + 1;
                this.A.add(String.valueOf(i));
                if (i5 > 31) {
                    break;
                }
                i = i5;
            }
        }
        this.G = this.A.indexOf(this.t);
    }

    public final void populateHourDataInView() {
        this.D.clear();
        for (int i = 1; i < 13; i++) {
            this.D.add(String.valueOf(i));
        }
        this.J = this.D.indexOf(this.x);
    }

    public final void populateMinDataInView() {
        this.E.clear();
        for (int i = 0; i < 60; i++) {
            this.E.add(String.valueOf(i));
        }
        this.K = this.E.indexOf(this.y);
    }

    public final void populateMonthsDataInView() {
        this.B.clear();
        for (int i = 1; i < 13; i++) {
            this.B.add(String.valueOf(i));
        }
        this.H = this.B.indexOf(this.u);
    }

    public final void populateYearsDataInView() {
        Calendar calendar = Calendar.getInstance();
        this.C.clear();
        this.C.add(String.valueOf(calendar.get(1)));
        calendar.add(1, 1);
        this.C.add(String.valueOf(calendar.get(1)));
        this.I = this.C.indexOf(this.v);
    }

    public final void sendToBle() {
        if (!AppUtils.isNetConnected(this)) {
            showNoInternetMessage();
        } else if (BleApiManager.getInstance(this).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
            EventReminderHelper eventReminderHelper = getEventReminderHelper();
            List<EventReminder> list = this.q;
            List<EventReminder> list2 = null;
            if (list == null) {
                Intrinsics.throwUninitializedPropertyAccessException("eventReminderList");
                list = null;
            }
            eventReminderHelper.sendEventRemindersToBand(list, true);
            TextView btnAddEvent = (TextView) _$_findCachedViewById(R.id.btnAddEvent);
            Intrinsics.checkNotNullExpressionValue(btnAddEvent, "btnAddEvent");
            visible(btnAddEvent);
            TextView btnSave = (TextView) _$_findCachedViewById(R.id.btnSave);
            Intrinsics.checkNotNullExpressionValue(btnSave, "btnSave");
            gone(btnSave);
            List<EventReminder> list3 = this.q;
            if (list3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("eventReminderList");
            } else {
                list2 = list3;
            }
            if (list2.isEmpty()) {
                ConstraintLayout emptyCl = (ConstraintLayout) _$_findCachedViewById(R.id.emptyCl);
                Intrinsics.checkNotNullExpressionValue(emptyCl, "emptyCl");
                visible(emptyCl);
            }
        } else {
            String string = getString(R.string.band_not_connected);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.band_not_connected)");
            String string2 = getString(R.string.please_connect_the_device);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.please_connect_the_device)");
            final BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = new BottomSheetDialogOneButtonTitleMessage(this, string, string2);
            String string3 = getString(R.string.ok);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.ok)");
            bottomSheetDialogOneButtonTitleMessage.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.l9
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityEventReminder.g0(BottomSheetDialogOneButtonTitleMessage.this, view);
                }
            });
            bottomSheetDialogOneButtonTitleMessage.show();
        }
    }

    public final void setBoolSaveVisible(boolean z) {
        this.s = z;
    }

    public final void setDialogListener(@NotNull DialogListener dialogListener) {
        Intrinsics.checkNotNullParameter(dialogListener, "<set-?>");
        this.dialogListener = dialogListener;
    }

    public final void setEventReminderHelper(@NotNull EventReminderHelper eventReminderHelper) {
        Intrinsics.checkNotNullParameter(eventReminderHelper, "<set-?>");
        this.eventReminderHelper = eventReminderHelper;
    }

    public final void setEventReminderListAdapter(@NotNull EventReminderListAdapter eventReminderListAdapter) {
        Intrinsics.checkNotNullParameter(eventReminderListAdapter, "<set-?>");
        this.eventReminderListAdapter = eventReminderListAdapter;
    }

    public final void showDeleteConfirmationDialog(@NotNull final EventReminder info) {
        final BottomSheetDialogImageTitleMessageTwoBtns bottomSheetDialogImageTitleMessageTwoBtns;
        Intrinsics.checkNotNullParameter(info, "info");
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.info_icon_new);
        if (drawable != null) {
            String string = getString(R.string.confirmation);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.confirmation)");
            String string2 = getString(R.string.event_delete_confirmation);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.event_delete_confirmation)");
            bottomSheetDialogImageTitleMessageTwoBtns = new BottomSheetDialogImageTitleMessageTwoBtns(this, drawable, string, string2, false);
        } else {
            bottomSheetDialogImageTitleMessageTwoBtns = null;
        }
        Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessageTwoBtns);
        String string3 = getString(R.string.yes);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.yes)");
        bottomSheetDialogImageTitleMessageTwoBtns.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.i9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityEventReminder.h0(BottomSheetDialogImageTitleMessageTwoBtns.this, this, info, view);
            }
        });
        String string4 = getString(R.string.f4085no);
        Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.no)");
        bottomSheetDialogImageTitleMessageTwoBtns.setNegativeButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.h9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityEventReminder.i0(BottomSheetDialogImageTitleMessageTwoBtns.this, view);
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
        bottomSheetDialogOneButtonOneTitle.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.k9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityEventReminder.j0(BottomSheetDialogOneButtonOneTitle.this, this, view);
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
        Intrinsics.checkNotNullExpressionValue(string2, "getString(\n             …R.string.ok\n            )");
        bottomSheetDialogOneButtonOneTitle.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.j9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityEventReminder.k0(BottomSheetDialogOneButtonOneTitle.this, this, view);
            }
        });
        bottomSheetDialogOneButtonOneTitle.show();
    }

    @Override // com.coveiot.android.leonardo.more.listeners.EventReminderListener
    public void visibleTextview() {
        if (J()) {
            TextView btnSave = (TextView) _$_findCachedViewById(R.id.btnSave);
            Intrinsics.checkNotNullExpressionValue(btnSave, "btnSave");
            visible(btnSave);
            this.s = true;
            return;
        }
        this.s = false;
        TextView btnSave2 = (TextView) _$_findCachedViewById(R.id.btnSave);
        Intrinsics.checkNotNullExpressionValue(btnSave2, "btnSave");
        gone(btnSave2);
    }
}
