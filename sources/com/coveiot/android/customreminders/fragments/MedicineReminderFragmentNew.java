package com.coveiot.android.customreminders.fragments;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.coveiot.android.customreminders.R;
import com.coveiot.android.customreminders.ReminderType;
import com.coveiot.android.customreminders.adapter.AdapterRemindMeAt;
import com.coveiot.android.customreminders.databinding.MedicineReminderItemBinding;
import com.coveiot.android.customreminders.listeners.AddReminderListener;
import com.coveiot.android.customreminders.listeners.PostEditReminderListListener;
import com.coveiot.android.customreminders.listeners.ReminderListener;
import com.coveiot.android.customreminders.model.CustomReminder;
import com.coveiot.android.customreminders.model.MedicineReminder;
import com.coveiot.android.customreminders.model.MeetingReminder;
import com.coveiot.android.customreminders.model.OtherReminder;
import com.coveiot.android.customreminders.model.RepeatModel;
import com.coveiot.android.customreminders.utils.ExtensionsKt;
import com.coveiot.android.customreminders.utils.ReminderConstants;
import com.coveiot.android.customreminders.utils.ViewModelFactory;
import com.coveiot.android.customreminders.viewmodel.MedicineReminderViewModel;
import com.coveiot.android.theme.BottomSheetDialogTwoButtons;
import com.coveiot.sdk.ble.api.model.TimeInfo;
import com.coveiot.utils.utility.AppUtils;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.jstyle.blesdk1860.constant.DeviceKey;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class MedicineReminderFragmentNew extends Fragment {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public ReminderType h;
    @Nullable
    public CustomReminder i;
    public MedicineReminderViewModel l;
    public AddReminderListener m;
    public PostEditReminderListListener n;
    public MedicineReminderItemBinding o;
    public SaveButtonListener p;
    @Nullable
    public BottomSheetDialogTwoButtons q;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public int j = -1;
    public boolean k = true;

    /* loaded from: classes3.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ MedicineReminderFragmentNew newInstance$default(Companion companion, ReminderType reminderType, CustomReminder customReminder, int i, int i2, Object obj) {
            if ((i2 & 2) != 0) {
                customReminder = null;
            }
            if ((i2 & 4) != 0) {
                i = -1;
            }
            return companion.newInstance(reminderType, customReminder, i);
        }

        @JvmStatic
        @NotNull
        public final MedicineReminderFragmentNew newInstance(@NotNull ReminderType param1, @Nullable CustomReminder customReminder, int i) {
            Intrinsics.checkNotNullParameter(param1, "param1");
            MedicineReminderFragmentNew medicineReminderFragmentNew = new MedicineReminderFragmentNew();
            Bundle bundle = new Bundle();
            bundle.putSerializable("type", param1);
            bundle.putSerializable("reminder_object", customReminder);
            bundle.putSerializable(DeviceKey.position, Integer.valueOf(i));
            medicineReminderFragmentNew.setArguments(bundle);
            return medicineReminderFragmentNew;
        }
    }

    /* loaded from: classes3.dex */
    public interface SaveButtonListener {
        void isAllDetailsFilled(boolean z);
    }

    public static final void A(AdapterRemindMeAt adapterRemindMeAt, TimePicker timePicker, int i, int i2) {
        Intrinsics.checkNotNullParameter(adapterRemindMeAt, "$adapterRemindMeAt");
        adapterRemindMeAt.addTimeInfo(new TimeInfo(i, i2));
    }

    public static final void B(final MedicineReminderFragmentNew this$0, final OtherReminder otherReminder, final int i, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(otherReminder, "$otherReminder");
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(this$0.requireContext(), R.style.DialogThemeDarWindowBG, new DatePickerDialog.OnDateSetListener() { // from class: com.coveiot.android.customreminders.fragments.u0
            @Override // android.app.DatePickerDialog.OnDateSetListener
            public final void onDateSet(DatePicker datePicker, int i2, int i3, int i4) {
                MedicineReminderFragmentNew.C(OtherReminder.this, this$0, i, datePicker, i2, i3, i4);
            }
        }, calendar.get(1), calendar.get(2), calendar.get(5));
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
        datePickerDialog.show();
    }

    public static final void C(OtherReminder otherReminder, MedicineReminderFragmentNew this$0, int i, DatePicker datePicker, int i2, int i3, int i4) {
        Intrinsics.checkNotNullParameter(otherReminder, "$otherReminder");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Calendar startDate = Calendar.getInstance();
        startDate.set(i2, i3, i4);
        otherReminder.setStartDate(startDate);
        this$0.setReminder(otherReminder, i);
        MedicineReminderItemBinding medicineReminderItemBinding = this$0.o;
        if (medicineReminderItemBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            medicineReminderItemBinding = null;
        }
        TextView textView = medicineReminderItemBinding.tvStartDate;
        Intrinsics.checkNotNullExpressionValue(startDate, "startDate");
        textView.setText(ExtensionsKt.toFormattedDateStr(startDate, "dd/MM/YYYY"));
    }

    public static final void D(final MedicineReminderFragmentNew this$0, final OtherReminder otherReminder, final int i, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(otherReminder, "$otherReminder");
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(this$0.requireContext(), R.style.DialogThemeDarWindowBG, new DatePickerDialog.OnDateSetListener() { // from class: com.coveiot.android.customreminders.fragments.v0
            @Override // android.app.DatePickerDialog.OnDateSetListener
            public final void onDateSet(DatePicker datePicker, int i2, int i3, int i4) {
                MedicineReminderFragmentNew.E(OtherReminder.this, this$0, i, datePicker, i2, i3, i4);
            }
        }, calendar.get(1), calendar.get(2), calendar.get(5));
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
        datePickerDialog.show();
    }

    public static final void E(OtherReminder otherReminder, MedicineReminderFragmentNew this$0, int i, DatePicker datePicker, int i2, int i3, int i4) {
        Intrinsics.checkNotNullParameter(otherReminder, "$otherReminder");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Calendar startDate = Calendar.getInstance();
        startDate.set(i2, i3, i4);
        otherReminder.setEndDate(startDate);
        this$0.setReminder(otherReminder, i);
        MedicineReminderItemBinding medicineReminderItemBinding = this$0.o;
        if (medicineReminderItemBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            medicineReminderItemBinding = null;
        }
        TextView textView = medicineReminderItemBinding.tvEndDate;
        Intrinsics.checkNotNullExpressionValue(startDate, "startDate");
        textView.setText(ExtensionsKt.toFormattedDateStr(startDate, "dd/MM/YYYY"));
    }

    public static final void F(OtherReminder otherReminder, MedicineReminderFragmentNew this$0, int i, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(otherReminder, "$otherReminder");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        RepeatModel repeatModel = otherReminder.getRepeatModel();
        if (repeatModel != null) {
            repeatModel.sunday = z;
        }
        RepeatModel repeatModel2 = otherReminder.getRepeatModel();
        if (repeatModel2 != null) {
            repeatModel2.monday = z;
        }
        RepeatModel repeatModel3 = otherReminder.getRepeatModel();
        if (repeatModel3 != null) {
            repeatModel3.tuesday = z;
        }
        RepeatModel repeatModel4 = otherReminder.getRepeatModel();
        if (repeatModel4 != null) {
            repeatModel4.wednesday = z;
        }
        RepeatModel repeatModel5 = otherReminder.getRepeatModel();
        if (repeatModel5 != null) {
            repeatModel5.thursday = z;
        }
        RepeatModel repeatModel6 = otherReminder.getRepeatModel();
        if (repeatModel6 != null) {
            repeatModel6.friday = z;
        }
        RepeatModel repeatModel7 = otherReminder.getRepeatModel();
        if (repeatModel7 != null) {
            repeatModel7.saturday = z;
        }
        this$0.setReminder(otherReminder, i);
        Context requireContext = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        this$0.updateRepeatLayout(otherReminder, requireContext);
    }

    public static final void G(final MedicineReminderFragmentNew this$0, final MedicineReminder medicineReminder, final int i, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(medicineReminder, "$medicineReminder");
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(this$0.requireContext(), R.style.DialogThemeDarWindowBG, new DatePickerDialog.OnDateSetListener() { // from class: com.coveiot.android.customreminders.fragments.e0
            @Override // android.app.DatePickerDialog.OnDateSetListener
            public final void onDateSet(DatePicker datePicker, int i2, int i3, int i4) {
                MedicineReminderFragmentNew.H(MedicineReminder.this, this$0, i, datePicker, i2, i3, i4);
            }
        }, calendar.get(1), calendar.get(2), calendar.get(5));
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
        datePickerDialog.show();
    }

    public static final void H(MedicineReminder medicineReminder, MedicineReminderFragmentNew this$0, int i, DatePicker datePicker, int i2, int i3, int i4) {
        Intrinsics.checkNotNullParameter(medicineReminder, "$medicineReminder");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Calendar startDate = Calendar.getInstance();
        startDate.set(i2, i3, i4);
        medicineReminder.setStartDate(startDate);
        this$0.setReminder(medicineReminder, i);
        MedicineReminderItemBinding medicineReminderItemBinding = this$0.o;
        if (medicineReminderItemBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            medicineReminderItemBinding = null;
        }
        TextView textView = medicineReminderItemBinding.tvStartDate;
        Intrinsics.checkNotNullExpressionValue(startDate, "startDate");
        textView.setText(ExtensionsKt.toFormattedDateStr(startDate, "dd/MM/YYYY"));
    }

    public static final void I(final AdapterRemindMeAt adapterRemindMeAt, MedicineReminderFragmentNew this$0, View view) {
        Intrinsics.checkNotNullParameter(adapterRemindMeAt, "$adapterRemindMeAt");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (adapterRemindMeAt.getTimeInfosList().size() == 0) {
            return;
        }
        new TimePickerDialog(this$0.requireContext(), R.style.DialogThemeDarWindowBG, new TimePickerDialog.OnTimeSetListener() { // from class: com.coveiot.android.customreminders.fragments.y0
            @Override // android.app.TimePickerDialog.OnTimeSetListener
            public final void onTimeSet(TimePicker timePicker, int i, int i2) {
                MedicineReminderFragmentNew.J(AdapterRemindMeAt.this, timePicker, i, i2);
            }
        }, Calendar.getInstance().get(11), Calendar.getInstance().get(12), DateFormat.is24HourFormat(this$0.requireContext())).show();
    }

    public static final void J(AdapterRemindMeAt adapterRemindMeAt, TimePicker timePicker, int i, int i2) {
        Intrinsics.checkNotNullParameter(adapterRemindMeAt, "$adapterRemindMeAt");
        adapterRemindMeAt.addTimeInfo(new TimeInfo(i, i2));
    }

    public static final void K(final MedicineReminderFragmentNew this$0, final MedicineReminder medicineReminder, final int i, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(medicineReminder, "$medicineReminder");
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(this$0.requireContext(), R.style.DialogThemeDarWindowBG, new DatePickerDialog.OnDateSetListener() { // from class: com.coveiot.android.customreminders.fragments.p0
            @Override // android.app.DatePickerDialog.OnDateSetListener
            public final void onDateSet(DatePicker datePicker, int i2, int i3, int i4) {
                MedicineReminderFragmentNew.L(MedicineReminder.this, this$0, i, datePicker, i2, i3, i4);
            }
        }, calendar.get(1), calendar.get(2), calendar.get(5));
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
        datePickerDialog.show();
    }

    public static final void L(MedicineReminder medicineReminder, MedicineReminderFragmentNew this$0, int i, DatePicker datePicker, int i2, int i3, int i4) {
        Intrinsics.checkNotNullParameter(medicineReminder, "$medicineReminder");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Calendar startDate = Calendar.getInstance();
        startDate.set(i2, i3, i4);
        medicineReminder.setEndDate(startDate);
        this$0.setReminder(medicineReminder, i);
        MedicineReminderItemBinding medicineReminderItemBinding = this$0.o;
        if (medicineReminderItemBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            medicineReminderItemBinding = null;
        }
        TextView textView = medicineReminderItemBinding.tvEndDate;
        Intrinsics.checkNotNullExpressionValue(startDate, "startDate");
        textView.setText(ExtensionsKt.toFormattedDateStr(startDate, "dd/MM/YYYY"));
    }

    public static final void M(MedicineReminder medicineReminder, MedicineReminderFragmentNew this$0, int i, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(medicineReminder, "$medicineReminder");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        RepeatModel repeatModel = medicineReminder.getRepeatModel();
        if (repeatModel != null) {
            repeatModel.sunday = z;
        }
        RepeatModel repeatModel2 = medicineReminder.getRepeatModel();
        if (repeatModel2 != null) {
            repeatModel2.monday = z;
        }
        RepeatModel repeatModel3 = medicineReminder.getRepeatModel();
        if (repeatModel3 != null) {
            repeatModel3.tuesday = z;
        }
        RepeatModel repeatModel4 = medicineReminder.getRepeatModel();
        if (repeatModel4 != null) {
            repeatModel4.wednesday = z;
        }
        RepeatModel repeatModel5 = medicineReminder.getRepeatModel();
        if (repeatModel5 != null) {
            repeatModel5.thursday = z;
        }
        RepeatModel repeatModel6 = medicineReminder.getRepeatModel();
        if (repeatModel6 != null) {
            repeatModel6.friday = z;
        }
        RepeatModel repeatModel7 = medicineReminder.getRepeatModel();
        if (repeatModel7 != null) {
            repeatModel7.saturday = z;
        }
        this$0.setReminder(medicineReminder, i);
        Context requireContext = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        this$0.updateRepeatLayout(medicineReminder, requireContext);
    }

    public static final void N(final AdapterRemindMeAt adapterRemindMeAt, MedicineReminderFragmentNew this$0, View view) {
        Intrinsics.checkNotNullParameter(adapterRemindMeAt, "$adapterRemindMeAt");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (adapterRemindMeAt.getTimeInfosList().size() == 0) {
            return;
        }
        new TimePickerDialog(this$0.requireContext(), R.style.DialogThemeDarWindowBG, new TimePickerDialog.OnTimeSetListener() { // from class: com.coveiot.android.customreminders.fragments.w0
            @Override // android.app.TimePickerDialog.OnTimeSetListener
            public final void onTimeSet(TimePicker timePicker, int i, int i2) {
                MedicineReminderFragmentNew.O(AdapterRemindMeAt.this, timePicker, i, i2);
            }
        }, Calendar.getInstance().get(11), Calendar.getInstance().get(12), DateFormat.is24HourFormat(this$0.requireContext())).show();
    }

    public static final void O(AdapterRemindMeAt adapterRemindMeAt, TimePicker timePicker, int i, int i2) {
        Intrinsics.checkNotNullParameter(adapterRemindMeAt, "$adapterRemindMeAt");
        adapterRemindMeAt.addTimeInfo(new TimeInfo(i, i2));
    }

    public static final void P(final MedicineReminderFragmentNew this$0, final MeetingReminder meetingReminder, final int i, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(meetingReminder, "$meetingReminder");
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(this$0.requireContext(), R.style.DialogThemeDarWindowBG, new DatePickerDialog.OnDateSetListener() { // from class: com.coveiot.android.customreminders.fragments.s0
            @Override // android.app.DatePickerDialog.OnDateSetListener
            public final void onDateSet(DatePicker datePicker, int i2, int i3, int i4) {
                MedicineReminderFragmentNew.Q(MeetingReminder.this, this$0, i, datePicker, i2, i3, i4);
            }
        }, calendar.get(1), calendar.get(2), calendar.get(5));
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
        datePickerDialog.show();
    }

    public static final void Q(MeetingReminder meetingReminder, MedicineReminderFragmentNew this$0, int i, DatePicker datePicker, int i2, int i3, int i4) {
        Intrinsics.checkNotNullParameter(meetingReminder, "$meetingReminder");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Calendar startDate = Calendar.getInstance();
        startDate.set(i2, i3, i4);
        meetingReminder.setStartDate(startDate);
        this$0.setReminder(meetingReminder, i);
        MedicineReminderItemBinding medicineReminderItemBinding = this$0.o;
        if (medicineReminderItemBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            medicineReminderItemBinding = null;
        }
        TextView textView = medicineReminderItemBinding.tvStartDate;
        Intrinsics.checkNotNullExpressionValue(startDate, "startDate");
        textView.setText(ExtensionsKt.toFormattedDateStr(startDate, "dd/MM/YYYY"));
    }

    public static final void S(final MedicineReminderFragmentNew this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        MedicineReminderViewModel medicineReminderViewModel = this$0.l;
        if (medicineReminderViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            medicineReminderViewModel = null;
        }
        medicineReminderViewModel.save(new ReminderListener() { // from class: com.coveiot.android.customreminders.fragments.MedicineReminderFragmentNew$initClickListeners$1$1

            @DebugMetadata(c = "com.coveiot.android.customreminders.fragments.MedicineReminderFragmentNew$initClickListeners$1$1$onError$1", f = "MedicineReminderFragmentNew.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* loaded from: classes3.dex */
            public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public final /* synthetic */ String $error;
                public int label;
                public final /* synthetic */ MedicineReminderFragmentNew this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public a(MedicineReminderFragmentNew medicineReminderFragmentNew, String str, Continuation<? super a> continuation) {
                    super(2, continuation);
                    this.this$0 = medicineReminderFragmentNew;
                    this.$error = str;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new a(this.this$0, this.$error, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                @Nullable
                public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                    return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                    if (this.label == 0) {
                        ResultKt.throwOnFailure(obj);
                        Toast.makeText(this.this$0.requireContext(), this.$error, 0).show();
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }

            @Override // com.coveiot.android.customreminders.listeners.ReminderListener
            public void onError(@NotNull String error) {
                Intrinsics.checkNotNullParameter(error, "error");
                kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new a(MedicineReminderFragmentNew.this, error, null), 2, null);
            }

            @Override // com.coveiot.android.customreminders.listeners.ReminderListener
            public void onSuccess(@NotNull CustomReminder customReminder) {
                CustomReminder customReminder2;
                PostEditReminderListListener postEditReminderListListener;
                PostEditReminderListListener postEditReminderListListener2;
                int i;
                ReminderType reminderType;
                AddReminderListener addReminderListener;
                AddReminderListener addReminderListener2;
                ReminderType reminderType2;
                Intrinsics.checkNotNullParameter(customReminder, "customReminder");
                customReminder2 = MedicineReminderFragmentNew.this.i;
                ReminderType reminderType3 = null;
                if (customReminder2 == null) {
                    addReminderListener = MedicineReminderFragmentNew.this.m;
                    if (addReminderListener != null) {
                        addReminderListener2 = MedicineReminderFragmentNew.this.m;
                        if (addReminderListener2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException(ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
                            addReminderListener2 = null;
                        }
                        reminderType2 = MedicineReminderFragmentNew.this.h;
                        if (reminderType2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("reminderType");
                        } else {
                            reminderType3 = reminderType2;
                        }
                        addReminderListener2.onReminderAdded(customReminder, reminderType3);
                        return;
                    }
                    return;
                }
                postEditReminderListListener = MedicineReminderFragmentNew.this.n;
                if (postEditReminderListListener != null) {
                    postEditReminderListListener2 = MedicineReminderFragmentNew.this.n;
                    if (postEditReminderListListener2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("editListener");
                        postEditReminderListListener2 = null;
                    }
                    i = MedicineReminderFragmentNew.this.j;
                    reminderType = MedicineReminderFragmentNew.this.h;
                    if (reminderType == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("reminderType");
                    } else {
                        reminderType3 = reminderType;
                    }
                    postEditReminderListListener2.onEditReminder(i, customReminder, reminderType3);
                }
            }
        });
    }

    @JvmStatic
    @NotNull
    public static final MedicineReminderFragmentNew newInstance(@NotNull ReminderType reminderType, @Nullable CustomReminder customReminder, int i) {
        return Companion.newInstance(reminderType, customReminder, i);
    }

    public static final void w(final MedicineReminderFragmentNew this$0, final MeetingReminder meetingReminder, final int i, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(meetingReminder, "$meetingReminder");
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(this$0.requireContext(), R.style.DialogThemeDarWindowBG, new DatePickerDialog.OnDateSetListener() { // from class: com.coveiot.android.customreminders.fragments.t0
            @Override // android.app.DatePickerDialog.OnDateSetListener
            public final void onDateSet(DatePicker datePicker, int i2, int i3, int i4) {
                MedicineReminderFragmentNew.x(MeetingReminder.this, this$0, i, datePicker, i2, i3, i4);
            }
        }, calendar.get(1), calendar.get(2), calendar.get(5));
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
        datePickerDialog.show();
    }

    public static final void x(MeetingReminder meetingReminder, MedicineReminderFragmentNew this$0, int i, DatePicker datePicker, int i2, int i3, int i4) {
        Intrinsics.checkNotNullParameter(meetingReminder, "$meetingReminder");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Calendar startDate = Calendar.getInstance();
        startDate.set(i2, i3, i4);
        meetingReminder.setEndDate(startDate);
        this$0.setReminder(meetingReminder, i);
        MedicineReminderItemBinding medicineReminderItemBinding = this$0.o;
        if (medicineReminderItemBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            medicineReminderItemBinding = null;
        }
        TextView textView = medicineReminderItemBinding.tvEndDate;
        Intrinsics.checkNotNullExpressionValue(startDate, "startDate");
        textView.setText(ExtensionsKt.toFormattedDateStr(startDate, "dd/MM/YYYY"));
    }

    public static final void y(MeetingReminder meetingReminder, MedicineReminderFragmentNew this$0, int i, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(meetingReminder, "$meetingReminder");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        RepeatModel repeatModel = meetingReminder.getRepeatModel();
        if (repeatModel != null) {
            repeatModel.sunday = z;
        }
        RepeatModel repeatModel2 = meetingReminder.getRepeatModel();
        if (repeatModel2 != null) {
            repeatModel2.monday = z;
        }
        RepeatModel repeatModel3 = meetingReminder.getRepeatModel();
        if (repeatModel3 != null) {
            repeatModel3.tuesday = z;
        }
        RepeatModel repeatModel4 = meetingReminder.getRepeatModel();
        if (repeatModel4 != null) {
            repeatModel4.wednesday = z;
        }
        RepeatModel repeatModel5 = meetingReminder.getRepeatModel();
        if (repeatModel5 != null) {
            repeatModel5.thursday = z;
        }
        RepeatModel repeatModel6 = meetingReminder.getRepeatModel();
        if (repeatModel6 != null) {
            repeatModel6.friday = z;
        }
        RepeatModel repeatModel7 = meetingReminder.getRepeatModel();
        if (repeatModel7 != null) {
            repeatModel7.saturday = z;
        }
        this$0.setReminder(meetingReminder, i);
        Context requireContext = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        this$0.updateRepeatLayout(meetingReminder, requireContext);
    }

    public static final void z(final AdapterRemindMeAt adapterRemindMeAt, MedicineReminderFragmentNew this$0, View view) {
        Intrinsics.checkNotNullParameter(adapterRemindMeAt, "$adapterRemindMeAt");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (adapterRemindMeAt.getTimeInfosList().size() == 0) {
            return;
        }
        new TimePickerDialog(this$0.requireContext(), R.style.DialogThemeDarWindowBG, new TimePickerDialog.OnTimeSetListener() { // from class: com.coveiot.android.customreminders.fragments.x0
            @Override // android.app.TimePickerDialog.OnTimeSetListener
            public final void onTimeSet(TimePicker timePicker, int i, int i2) {
                MedicineReminderFragmentNew.A(AdapterRemindMeAt.this, timePicker, i, i2);
            }
        }, Calendar.getInstance().get(11), Calendar.getInstance().get(12), DateFormat.is24HourFormat(this$0.requireContext())).show();
    }

    public final int R(int i) {
        int length = ReminderConstants.Companion.getNOTIFIY_ME_BEFOR_DATA().length;
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            if (ReminderConstants.Companion.getNOTIFIY_ME_BEFOR_DATA()[i3].intValue() == i) {
                i2 = i3;
            }
        }
        return i2;
    }

    public final void T() {
        OtherReminder otherReminder;
        MeetingReminder meetingReminder;
        MedicineReminder medicineReminder;
        ReminderType reminderType = this.h;
        MedicineReminderItemBinding medicineReminderItemBinding = null;
        if (reminderType == null) {
            Intrinsics.throwUninitializedPropertyAccessException("reminderType");
            reminderType = null;
        }
        if (reminderType == ReminderType.MEDICINE) {
            CustomReminder customReminder = this.i;
            if (customReminder != null) {
                Intrinsics.checkNotNull(customReminder, "null cannot be cast to non-null type com.coveiot.android.customreminders.model.MedicineReminder");
                medicineReminder = (MedicineReminder) customReminder;
            } else {
                medicineReminder = new MedicineReminder();
                medicineReminder.setRepeatModel(new RepeatModel());
            }
            bind(medicineReminder, this.j);
            if (this.i == null) {
                MedicineReminderItemBinding medicineReminderItemBinding2 = this.o;
                if (medicineReminderItemBinding2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    medicineReminderItemBinding2 = null;
                }
                medicineReminderItemBinding2.doneButton.setText(getString(R.string.add_medicine, getString(R.string.medicine)));
            }
            MedicineReminderItemBinding medicineReminderItemBinding3 = this.o;
            if (medicineReminderItemBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                medicineReminderItemBinding = medicineReminderItemBinding3;
            }
            medicineReminderItemBinding.tvMedicineNameLable.setText(getString(R.string.medicine_name));
            return;
        }
        ReminderType reminderType2 = this.h;
        if (reminderType2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("reminderType");
            reminderType2 = null;
        }
        if (reminderType2 == ReminderType.MEETING) {
            CustomReminder customReminder2 = this.i;
            if (customReminder2 != null) {
                Intrinsics.checkNotNull(customReminder2, "null cannot be cast to non-null type com.coveiot.android.customreminders.model.MeetingReminder");
                meetingReminder = (MeetingReminder) customReminder2;
            } else {
                meetingReminder = new MeetingReminder();
                meetingReminder.setRepeatModel(new RepeatModel());
            }
            bind(meetingReminder, this.j);
            if (this.i == null) {
                MedicineReminderItemBinding medicineReminderItemBinding4 = this.o;
                if (medicineReminderItemBinding4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    medicineReminderItemBinding4 = null;
                }
                medicineReminderItemBinding4.doneButton.setText(getString(R.string.add_medicine, getString(R.string.meeting)));
            }
            MedicineReminderItemBinding medicineReminderItemBinding5 = this.o;
            if (medicineReminderItemBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                medicineReminderItemBinding5 = null;
            }
            medicineReminderItemBinding5.tvMedicineNameLable.setText(getString(R.string.meeting_name));
            MedicineReminderItemBinding medicineReminderItemBinding6 = this.o;
            if (medicineReminderItemBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                medicineReminderItemBinding = medicineReminderItemBinding6;
            }
            medicineReminderItemBinding.addTimeInfo.setVisibility(8);
            return;
        }
        ReminderType reminderType3 = this.h;
        if (reminderType3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("reminderType");
            reminderType3 = null;
        }
        if (reminderType3 == ReminderType.OTHERS) {
            CustomReminder customReminder3 = this.i;
            if (customReminder3 != null) {
                Intrinsics.checkNotNull(customReminder3, "null cannot be cast to non-null type com.coveiot.android.customreminders.model.OtherReminder");
                otherReminder = (OtherReminder) customReminder3;
            } else {
                otherReminder = new OtherReminder();
                otherReminder.setRepeatModel(new RepeatModel());
            }
            bind(otherReminder, this.j);
            if (this.i == null) {
                MedicineReminderItemBinding medicineReminderItemBinding7 = this.o;
                if (medicineReminderItemBinding7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    medicineReminderItemBinding7 = null;
                }
                medicineReminderItemBinding7.doneButton.setText(getString(R.string.add_medicine, getString(R.string.other)));
            }
            MedicineReminderItemBinding medicineReminderItemBinding8 = this.o;
            if (medicineReminderItemBinding8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                medicineReminderItemBinding8 = null;
            }
            medicineReminderItemBinding8.tvMedicineNameLable.setText(getString(R.string.title));
            MedicineReminderItemBinding medicineReminderItemBinding9 = this.o;
            if (medicineReminderItemBinding9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                medicineReminderItemBinding = medicineReminderItemBinding9;
            }
            medicineReminderItemBinding.addTimeInfo.setVisibility(8);
        }
    }

    public final void U() {
        if (this.q == null) {
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            String string = getString(R.string.confirmation);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.confirmation)");
            String string2 = getString(R.string.unsaved_changes);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.unsaved_changes)");
            BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = new BottomSheetDialogTwoButtons(requireContext, string, string2, false, 8, null);
            this.q = bottomSheetDialogTwoButtons;
            Intrinsics.checkNotNull(bottomSheetDialogTwoButtons);
            String string3 = getString(R.string.ok);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.ok)");
            bottomSheetDialogTwoButtons.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.customreminders.fragments.MedicineReminderFragmentNew$showSaveUnSavedChanges$1
                @Override // android.view.View.OnClickListener
                public void onClick(@Nullable View view) {
                    ((Button) MedicineReminderFragmentNew.this._$_findCachedViewById(R.id.doneButton)).performClick();
                    BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons2 = MedicineReminderFragmentNew.this.getBottomSheetDialogTwoButtons();
                    Intrinsics.checkNotNull(bottomSheetDialogTwoButtons2);
                    bottomSheetDialogTwoButtons2.dismiss();
                }
            });
            BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons2 = this.q;
            Intrinsics.checkNotNull(bottomSheetDialogTwoButtons2);
            String string4 = getString(R.string.cancel);
            Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.cancel)");
            bottomSheetDialogTwoButtons2.setNegativeButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.customreminders.fragments.MedicineReminderFragmentNew$showSaveUnSavedChanges$2
                @Override // android.view.View.OnClickListener
                public void onClick(@Nullable View view) {
                    BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons3 = MedicineReminderFragmentNew.this.getBottomSheetDialogTwoButtons();
                    Intrinsics.checkNotNull(bottomSheetDialogTwoButtons3);
                    bottomSheetDialogTwoButtons3.dismiss();
                    FragmentActivity requireActivity = MedicineReminderFragmentNew.this.requireActivity();
                    if (requireActivity != null) {
                        requireActivity.finish();
                    }
                }
            });
        }
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons3 = this.q;
        Intrinsics.checkNotNull(bottomSheetDialogTwoButtons3);
        if (bottomSheetDialogTwoButtons3.isShowing()) {
            return;
        }
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons4 = this.q;
        Intrinsics.checkNotNull(bottomSheetDialogTwoButtons4);
        bottomSheetDialogTwoButtons4.show();
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

    public final void bind(@NotNull final MedicineReminder medicineReminder, final int i) {
        Intrinsics.checkNotNullParameter(medicineReminder, "medicineReminder");
        MedicineReminderItemBinding medicineReminderItemBinding = null;
        if (!AppUtils.isEmpty(medicineReminder.getDescription())) {
            MedicineReminderItemBinding medicineReminderItemBinding2 = this.o;
            if (medicineReminderItemBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                medicineReminderItemBinding2 = null;
            }
            medicineReminderItemBinding2.edtReminderName.setText(medicineReminder.getDescription());
        }
        if (medicineReminder.getStartDate() != null) {
            MedicineReminderItemBinding medicineReminderItemBinding3 = this.o;
            if (medicineReminderItemBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                medicineReminderItemBinding3 = null;
            }
            TextView textView = medicineReminderItemBinding3.tvStartDate;
            Calendar startDate = medicineReminder.getStartDate();
            Intrinsics.checkNotNullExpressionValue(startDate, "medicineReminder.startDate");
            textView.setText(ExtensionsKt.toFormattedDateStr(startDate, "dd/MM/YYYY"));
        }
        if (medicineReminder.getEndDate() != null) {
            MedicineReminderItemBinding medicineReminderItemBinding4 = this.o;
            if (medicineReminderItemBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                medicineReminderItemBinding4 = null;
            }
            TextView textView2 = medicineReminderItemBinding4.tvEndDate;
            Calendar endDate = medicineReminder.getEndDate();
            Intrinsics.checkNotNullExpressionValue(endDate, "medicineReminder.endDate");
            textView2.setText(ExtensionsKt.toFormattedDateStr(endDate, "dd/MM/YYYY"));
        }
        MedicineReminderItemBinding medicineReminderItemBinding5 = this.o;
        if (medicineReminderItemBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            medicineReminderItemBinding5 = null;
        }
        medicineReminderItemBinding5.edtReminderName.addTextChangedListener(new TextWatcher() { // from class: com.coveiot.android.customreminders.fragments.MedicineReminderFragmentNew$bind$1
            @Override // android.text.TextWatcher
            public void afterTextChanged(@Nullable Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(@Nullable CharSequence charSequence, int i2, int i3, int i4) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(@Nullable CharSequence charSequence, int i2, int i3, int i4) {
                MedicineReminderItemBinding medicineReminderItemBinding6;
                MedicineReminder.this.setDescription(charSequence != null ? charSequence.toString() : null);
                this.setReminder(MedicineReminder.this, i);
                medicineReminderItemBinding6 = this.o;
                if (medicineReminderItemBinding6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    medicineReminderItemBinding6 = null;
                }
                TextView textView3 = medicineReminderItemBinding6.tvCharactersLeft;
                StringBuilder sb = new StringBuilder();
                sb.append(charSequence != null ? Integer.valueOf(charSequence.length()) : null);
                sb.append("/24");
                textView3.setText(sb.toString());
            }
        });
        MedicineReminderItemBinding medicineReminderItemBinding6 = this.o;
        if (medicineReminderItemBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            medicineReminderItemBinding6 = null;
        }
        medicineReminderItemBinding6.tvStartDate.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.customreminders.fragments.i0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MedicineReminderFragmentNew.G(MedicineReminderFragmentNew.this, medicineReminder, i, view);
            }
        });
        MedicineReminderItemBinding medicineReminderItemBinding7 = this.o;
        if (medicineReminderItemBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            medicineReminderItemBinding7 = null;
        }
        medicineReminderItemBinding7.tvEndDate.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.customreminders.fragments.j0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MedicineReminderFragmentNew.K(MedicineReminderFragmentNew.this, medicineReminder, i, view);
            }
        });
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        updateRepeatLayout(medicineReminder, requireContext);
        MedicineReminderItemBinding medicineReminderItemBinding8 = this.o;
        if (medicineReminderItemBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            medicineReminderItemBinding8 = null;
        }
        medicineReminderItemBinding8.repeatLayout.sunday.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.customreminders.fragments.MedicineReminderFragmentNew$bind$4
            @Override // android.view.View.OnClickListener
            public void onClick(@Nullable View view) {
                MedicineReminder.this.getRepeatModel().sunday = !MedicineReminder.this.getRepeatModel().sunday;
                this.setReminder(MedicineReminder.this, i);
                MedicineReminderFragmentNew medicineReminderFragmentNew = this;
                MedicineReminder medicineReminder2 = MedicineReminder.this;
                Context requireContext2 = medicineReminderFragmentNew.requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                medicineReminderFragmentNew.updateRepeatLayout(medicineReminder2, requireContext2);
            }
        });
        MedicineReminderItemBinding medicineReminderItemBinding9 = this.o;
        if (medicineReminderItemBinding9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            medicineReminderItemBinding9 = null;
        }
        medicineReminderItemBinding9.repeatLayout.monday.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.customreminders.fragments.MedicineReminderFragmentNew$bind$5
            @Override // android.view.View.OnClickListener
            public void onClick(@Nullable View view) {
                MedicineReminder.this.getRepeatModel().monday = !MedicineReminder.this.getRepeatModel().monday;
                this.setReminder(MedicineReminder.this, i);
                MedicineReminderFragmentNew medicineReminderFragmentNew = this;
                MedicineReminder medicineReminder2 = MedicineReminder.this;
                Context requireContext2 = medicineReminderFragmentNew.requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                medicineReminderFragmentNew.updateRepeatLayout(medicineReminder2, requireContext2);
            }
        });
        MedicineReminderItemBinding medicineReminderItemBinding10 = this.o;
        if (medicineReminderItemBinding10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            medicineReminderItemBinding10 = null;
        }
        medicineReminderItemBinding10.repeatLayout.tuesday.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.customreminders.fragments.MedicineReminderFragmentNew$bind$6
            @Override // android.view.View.OnClickListener
            public void onClick(@Nullable View view) {
                MedicineReminder.this.getRepeatModel().tuesday = !MedicineReminder.this.getRepeatModel().tuesday;
                this.setReminder(MedicineReminder.this, i);
                MedicineReminderFragmentNew medicineReminderFragmentNew = this;
                MedicineReminder medicineReminder2 = MedicineReminder.this;
                Context requireContext2 = medicineReminderFragmentNew.requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                medicineReminderFragmentNew.updateRepeatLayout(medicineReminder2, requireContext2);
            }
        });
        MedicineReminderItemBinding medicineReminderItemBinding11 = this.o;
        if (medicineReminderItemBinding11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            medicineReminderItemBinding11 = null;
        }
        medicineReminderItemBinding11.repeatLayout.wednesday.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.customreminders.fragments.MedicineReminderFragmentNew$bind$7
            @Override // android.view.View.OnClickListener
            public void onClick(@Nullable View view) {
                MedicineReminder.this.getRepeatModel().wednesday = !MedicineReminder.this.getRepeatModel().wednesday;
                this.setReminder(MedicineReminder.this, i);
                MedicineReminderFragmentNew medicineReminderFragmentNew = this;
                MedicineReminder medicineReminder2 = MedicineReminder.this;
                Context requireContext2 = medicineReminderFragmentNew.requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                medicineReminderFragmentNew.updateRepeatLayout(medicineReminder2, requireContext2);
            }
        });
        MedicineReminderItemBinding medicineReminderItemBinding12 = this.o;
        if (medicineReminderItemBinding12 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            medicineReminderItemBinding12 = null;
        }
        medicineReminderItemBinding12.repeatLayout.thursday.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.customreminders.fragments.MedicineReminderFragmentNew$bind$8
            @Override // android.view.View.OnClickListener
            public void onClick(@Nullable View view) {
                MedicineReminder.this.getRepeatModel().thursday = !MedicineReminder.this.getRepeatModel().thursday;
                this.setReminder(MedicineReminder.this, i);
                MedicineReminderFragmentNew medicineReminderFragmentNew = this;
                MedicineReminder medicineReminder2 = MedicineReminder.this;
                Context requireContext2 = medicineReminderFragmentNew.requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                medicineReminderFragmentNew.updateRepeatLayout(medicineReminder2, requireContext2);
            }
        });
        MedicineReminderItemBinding medicineReminderItemBinding13 = this.o;
        if (medicineReminderItemBinding13 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            medicineReminderItemBinding13 = null;
        }
        medicineReminderItemBinding13.repeatLayout.friday.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.customreminders.fragments.MedicineReminderFragmentNew$bind$9
            @Override // android.view.View.OnClickListener
            public void onClick(@Nullable View view) {
                MedicineReminder.this.getRepeatModel().friday = !MedicineReminder.this.getRepeatModel().friday;
                this.setReminder(MedicineReminder.this, i);
                MedicineReminderFragmentNew medicineReminderFragmentNew = this;
                MedicineReminder medicineReminder2 = MedicineReminder.this;
                Context requireContext2 = medicineReminderFragmentNew.requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                medicineReminderFragmentNew.updateRepeatLayout(medicineReminder2, requireContext2);
            }
        });
        MedicineReminderItemBinding medicineReminderItemBinding14 = this.o;
        if (medicineReminderItemBinding14 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            medicineReminderItemBinding14 = null;
        }
        medicineReminderItemBinding14.repeatLayout.saturday.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.customreminders.fragments.MedicineReminderFragmentNew$bind$10
            @Override // android.view.View.OnClickListener
            public void onClick(@Nullable View view) {
                MedicineReminder.this.getRepeatModel().saturday = !MedicineReminder.this.getRepeatModel().saturday;
                this.setReminder(MedicineReminder.this, i);
                MedicineReminderFragmentNew medicineReminderFragmentNew = this;
                MedicineReminder medicineReminder2 = MedicineReminder.this;
                Context requireContext2 = medicineReminderFragmentNew.requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                medicineReminderFragmentNew.updateRepeatLayout(medicineReminder2, requireContext2);
            }
        });
        MedicineReminderItemBinding medicineReminderItemBinding15 = this.o;
        if (medicineReminderItemBinding15 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            medicineReminderItemBinding15 = null;
        }
        medicineReminderItemBinding15.repeatLayout.cbSelectAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.coveiot.android.customreminders.fragments.o0
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                MedicineReminderFragmentNew.M(MedicineReminder.this, this, i, compoundButton, z);
            }
        });
        ArrayAdapter<CharSequence> createFromResource = ArrayAdapter.createFromResource(requireContext(), R.array.notify_me_list, R.layout.spinner_list_item);
        Intrinsics.checkNotNullExpressionValue(createFromResource, "createFromResource(\n    …inner_list_item\n        )");
        createFromResource.setDropDownViewResource(R.layout.spinner_drop_down_view);
        MedicineReminderItemBinding medicineReminderItemBinding16 = this.o;
        if (medicineReminderItemBinding16 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            medicineReminderItemBinding16 = null;
        }
        medicineReminderItemBinding16.spNotifyMe.setAdapter((SpinnerAdapter) createFromResource);
        MedicineReminderItemBinding medicineReminderItemBinding17 = this.o;
        if (medicineReminderItemBinding17 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            medicineReminderItemBinding17 = null;
        }
        medicineReminderItemBinding17.spNotifyMe.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.coveiot.android.customreminders.fragments.MedicineReminderFragmentNew$bind$12
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(@Nullable AdapterView<?> adapterView, @Nullable View view, int i2, long j) {
                MedicineReminder.this.setAdvanceTime(ReminderConstants.Companion.getNOTIFIY_ME_BEFOR_DATA()[i2].intValue());
                this.setReminder(MedicineReminder.this, i);
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(@Nullable AdapterView<?> adapterView) {
            }
        });
        final AdapterRemindMeAt adapterRemindMeAt = new AdapterRemindMeAt();
        if (!AppUtils.isEmpty(medicineReminder.getTimeInfos())) {
            ArrayList<TimeInfo> timeInfos = medicineReminder.getTimeInfos();
            Intrinsics.checkNotNullExpressionValue(timeInfos, "medicineReminder.timeInfos");
            adapterRemindMeAt.setTimeInfosList(timeInfos);
        }
        adapterRemindMeAt.setListener(new AdapterRemindMeAt.RemindAtListener() { // from class: com.coveiot.android.customreminders.fragments.MedicineReminderFragmentNew$bind$13
            @Override // com.coveiot.android.customreminders.adapter.AdapterRemindMeAt.RemindAtListener
            public void onTimeInfoUpdate(@NotNull ArrayList<TimeInfo> timeInfosLst) {
                Intrinsics.checkNotNullParameter(timeInfosLst, "timeInfosLst");
                MedicineReminder.this.setTimeInfos(timeInfosLst);
                this.setReminder(MedicineReminder.this, i);
            }
        });
        MedicineReminderItemBinding medicineReminderItemBinding18 = this.o;
        if (medicineReminderItemBinding18 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            medicineReminderItemBinding18 = null;
        }
        medicineReminderItemBinding18.rcvTimeInfos.setLayoutManager(new LinearLayoutManager(requireContext()));
        MedicineReminderItemBinding medicineReminderItemBinding19 = this.o;
        if (medicineReminderItemBinding19 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            medicineReminderItemBinding19 = null;
        }
        medicineReminderItemBinding19.rcvTimeInfos.setAdapter(adapterRemindMeAt);
        MedicineReminderItemBinding medicineReminderItemBinding20 = this.o;
        if (medicineReminderItemBinding20 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            medicineReminderItemBinding20 = null;
        }
        medicineReminderItemBinding20.addTimeInfo.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.customreminders.fragments.f0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MedicineReminderFragmentNew.N(AdapterRemindMeAt.this, this, view);
            }
        });
        MedicineReminderItemBinding medicineReminderItemBinding21 = this.o;
        if (medicineReminderItemBinding21 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            medicineReminderItemBinding = medicineReminderItemBinding21;
        }
        medicineReminderItemBinding.spNotifyMe.setSelection(R(medicineReminder.getAdvanceTime()), true);
    }

    @Nullable
    public final BottomSheetDialogTwoButtons getBottomSheetDialogTwoButtons() {
        return this.q;
    }

    @Nullable
    public final CustomReminder getReminder() {
        MedicineReminderViewModel medicineReminderViewModel = this.l;
        if (medicineReminderViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            medicineReminderViewModel = null;
        }
        return medicineReminderViewModel.getCustomReminder();
    }

    public final boolean getRepeatFlag() {
        return this.k;
    }

    public final void initClickListeners() {
        MedicineReminderItemBinding medicineReminderItemBinding = this.o;
        if (medicineReminderItemBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            medicineReminderItemBinding = null;
        }
        medicineReminderItemBinding.doneButton.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.customreminders.fragments.h0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MedicineReminderFragmentNew.S(MedicineReminderFragmentNew.this, view);
            }
        });
    }

    public final boolean isAllRemindersValid() {
        MedicineReminderViewModel medicineReminderViewModel = this.l;
        MedicineReminderViewModel medicineReminderViewModel2 = null;
        if (medicineReminderViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            medicineReminderViewModel = null;
        }
        CustomReminder customReminder = medicineReminderViewModel.getCustomReminder();
        if (AppUtils.isEmpty(customReminder != null ? customReminder.getDescription() : null)) {
            return false;
        }
        MedicineReminderViewModel medicineReminderViewModel3 = this.l;
        if (medicineReminderViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            medicineReminderViewModel3 = null;
        }
        if (medicineReminderViewModel3.getCustomReminder() instanceof MedicineReminder) {
            MedicineReminderViewModel medicineReminderViewModel4 = this.l;
            if (medicineReminderViewModel4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            } else {
                medicineReminderViewModel2 = medicineReminderViewModel4;
            }
            CustomReminder customReminder2 = medicineReminderViewModel2.getCustomReminder();
            Intrinsics.checkNotNull(customReminder2, "null cannot be cast to non-null type com.coveiot.android.customreminders.model.MedicineReminder");
            MedicineReminder medicineReminder = (MedicineReminder) customReminder2;
            if (medicineReminder.getStartDate() == null) {
                return false;
            }
            return (medicineReminder.getStartDate() == null || medicineReminder.getEndDate() == null || medicineReminder.getEndDate().getTimeInMillis() >= medicineReminder.getStartDate().getTimeInMillis()) && !AppUtils.isEmpty(medicineReminder.getTimeInfos());
        }
        MedicineReminderViewModel medicineReminderViewModel5 = this.l;
        if (medicineReminderViewModel5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            medicineReminderViewModel5 = null;
        }
        if (medicineReminderViewModel5.getCustomReminder() instanceof MeetingReminder) {
            MedicineReminderViewModel medicineReminderViewModel6 = this.l;
            if (medicineReminderViewModel6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            } else {
                medicineReminderViewModel2 = medicineReminderViewModel6;
            }
            CustomReminder customReminder3 = medicineReminderViewModel2.getCustomReminder();
            Intrinsics.checkNotNull(customReminder3, "null cannot be cast to non-null type com.coveiot.android.customreminders.model.MeetingReminder");
            MeetingReminder meetingReminder = (MeetingReminder) customReminder3;
            if (meetingReminder.getStartDate() == null) {
                return false;
            }
            return (meetingReminder.getStartDate() == null || meetingReminder.getEndDate() == null || meetingReminder.getEndDate().getTimeInMillis() >= meetingReminder.getStartDate().getTimeInMillis()) && !AppUtils.isEmpty(meetingReminder.getTimeInfos());
        }
        MedicineReminderViewModel medicineReminderViewModel7 = this.l;
        if (medicineReminderViewModel7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            medicineReminderViewModel7 = null;
        }
        if (medicineReminderViewModel7.getCustomReminder() instanceof OtherReminder) {
            MedicineReminderViewModel medicineReminderViewModel8 = this.l;
            if (medicineReminderViewModel8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            } else {
                medicineReminderViewModel2 = medicineReminderViewModel8;
            }
            CustomReminder customReminder4 = medicineReminderViewModel2.getCustomReminder();
            Intrinsics.checkNotNull(customReminder4, "null cannot be cast to non-null type com.coveiot.android.customreminders.model.OtherReminder");
            OtherReminder otherReminder = (OtherReminder) customReminder4;
            if (otherReminder.getStartDate() == null) {
                return false;
            }
            return (otherReminder.getStartDate() == null || otherReminder.getEndDate() == null || otherReminder.getEndDate().getTimeInMillis() >= otherReminder.getStartDate().getTimeInMillis()) && !AppUtils.isEmpty(otherReminder.getTimeInfos());
        }
        return true;
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(@NotNull Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        super.onAttach(activity);
        if (activity instanceof AddReminderListener) {
            this.m = (AddReminderListener) activity;
        }
        if (activity instanceof PostEditReminderListListener) {
            this.n = (PostEditReminderListListener) activity;
        }
        if (activity instanceof SaveButtonListener) {
            this.p = (SaveButtonListener) activity;
        }
    }

    public final void onBackPressed() {
        if (((Button) _$_findCachedViewById(R.id.doneButton)).isEnabled()) {
            U();
            return;
        }
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.finish();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            Serializable serializable = arguments.getSerializable("type");
            Intrinsics.checkNotNull(serializable, "null cannot be cast to non-null type com.coveiot.android.customreminders.ReminderType");
            this.h = (ReminderType) serializable;
            if (arguments.getSerializable("reminder_object") != null) {
                Serializable serializable2 = arguments.getSerializable("reminder_object");
                Intrinsics.checkNotNull(serializable2, "null cannot be cast to non-null type com.coveiot.android.customreminders.model.CustomReminder");
                this.i = (CustomReminder) serializable2;
                this.j = arguments.getInt(DeviceKey.position);
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        MedicineReminderItemBinding inflate = MedicineReminderItemBinding.inflate(getLayoutInflater(), viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater, container, false)");
        this.o = inflate;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        return inflate.getRoot();
    }

    @Override // androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        ViewModel viewModel = new ViewModelProvider(this, new ViewModelFactory(requireContext)).get(MedicineReminderViewModel.class);
        Intrinsics.checkNotNullExpressionValue(viewModel, "ViewModelProvider(\n     …derViewModel::class.java)");
        MedicineReminderViewModel medicineReminderViewModel = (MedicineReminderViewModel) viewModel;
        this.l = medicineReminderViewModel;
        MedicineReminderItemBinding medicineReminderItemBinding = null;
        if (medicineReminderViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            medicineReminderViewModel = null;
        }
        ReminderType reminderType = this.h;
        if (reminderType == null) {
            Intrinsics.throwUninitializedPropertyAccessException("reminderType");
            reminderType = null;
        }
        medicineReminderViewModel.setReminderType(reminderType);
        MedicineReminderItemBinding medicineReminderItemBinding2 = this.o;
        if (medicineReminderItemBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            medicineReminderItemBinding2 = null;
        }
        medicineReminderItemBinding2.repeatLayout.cbSelectAll.setChecked(false);
        MedicineReminderItemBinding medicineReminderItemBinding3 = this.o;
        if (medicineReminderItemBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            medicineReminderItemBinding3 = null;
        }
        medicineReminderItemBinding3.repeatLayout.divider.setVisibility(8);
        MedicineReminderItemBinding medicineReminderItemBinding4 = this.o;
        if (medicineReminderItemBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            medicineReminderItemBinding = medicineReminderItemBinding4;
        }
        medicineReminderItemBinding.repeatLayout.tvRepeatLabel.setText(getString(R.string.repeat));
        initClickListeners();
        T();
    }

    public final void setBottomSheetDialogTwoButtons(@Nullable BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons) {
        this.q = bottomSheetDialogTwoButtons;
    }

    public final void setReminder(@NotNull CustomReminder reminder, int i) {
        Intrinsics.checkNotNullParameter(reminder, "reminder");
        MedicineReminderViewModel medicineReminderViewModel = this.l;
        SaveButtonListener saveButtonListener = null;
        if (medicineReminderViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            medicineReminderViewModel = null;
        }
        medicineReminderViewModel.setCustomReminder(reminder);
        MedicineReminderItemBinding medicineReminderItemBinding = this.o;
        if (medicineReminderItemBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            medicineReminderItemBinding = null;
        }
        medicineReminderItemBinding.doneButton.setEnabled(isAllRemindersValid());
        if (this.i != null) {
            if (isAllRemindersValid()) {
                MedicineReminderItemBinding medicineReminderItemBinding2 = this.o;
                if (medicineReminderItemBinding2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    medicineReminderItemBinding2 = null;
                }
                medicineReminderItemBinding2.doneButton.setEnabled(true);
                MedicineReminderItemBinding medicineReminderItemBinding3 = this.o;
                if (medicineReminderItemBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    medicineReminderItemBinding3 = null;
                }
                medicineReminderItemBinding3.doneButton.setBackgroundResource(R.drawable.enable_button_background);
                MedicineReminderItemBinding medicineReminderItemBinding4 = this.o;
                if (medicineReminderItemBinding4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    medicineReminderItemBinding4 = null;
                }
                medicineReminderItemBinding4.doneButton.setTextColor(getResources().getColor(R.color.white));
            } else {
                MedicineReminderItemBinding medicineReminderItemBinding5 = this.o;
                if (medicineReminderItemBinding5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    medicineReminderItemBinding5 = null;
                }
                medicineReminderItemBinding5.doneButton.setEnabled(false);
                MedicineReminderItemBinding medicineReminderItemBinding6 = this.o;
                if (medicineReminderItemBinding6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    medicineReminderItemBinding6 = null;
                }
                medicineReminderItemBinding6.doneButton.setBackgroundResource(R.drawable.disable_button_background);
                MedicineReminderItemBinding medicineReminderItemBinding7 = this.o;
                if (medicineReminderItemBinding7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    medicineReminderItemBinding7 = null;
                }
                medicineReminderItemBinding7.doneButton.setTextColor(getResources().getColor(R.color.color_666666));
            }
        }
        SaveButtonListener saveButtonListener2 = this.p;
        if (saveButtonListener2 != null) {
            if (saveButtonListener2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("saveButtonListener");
            } else {
                saveButtonListener = saveButtonListener2;
            }
            saveButtonListener.isAllDetailsFilled(isAllRemindersValid());
        }
    }

    public final void setRepeatFlag(boolean z) {
        this.k = z;
    }

    public final void updateRepeatLayout(@NotNull CustomReminder medicineReminder, @NotNull Context context) {
        Intrinsics.checkNotNullParameter(medicineReminder, "medicineReminder");
        Intrinsics.checkNotNullParameter(context, "context");
        MedicineReminderItemBinding medicineReminderItemBinding = null;
        if (medicineReminder.getRepeatModel().sunday) {
            MedicineReminderItemBinding medicineReminderItemBinding2 = this.o;
            if (medicineReminderItemBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                medicineReminderItemBinding2 = null;
            }
            medicineReminderItemBinding2.repeatLayout.sunday.setBackground(context.getResources().getDrawable(R.drawable.repeat_days_selected_bg));
            MedicineReminderItemBinding medicineReminderItemBinding3 = this.o;
            if (medicineReminderItemBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                medicineReminderItemBinding3 = null;
            }
            medicineReminderItemBinding3.repeatLayout.sunday.setTextColor(context.getResources().getColor(R.color.main_text_color));
            MedicineReminderItemBinding medicineReminderItemBinding4 = this.o;
            if (medicineReminderItemBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                medicineReminderItemBinding4 = null;
            }
            medicineReminderItemBinding4.repeatLayout.sunday.setTextAppearance(R.style.semi_bold);
        } else {
            MedicineReminderItemBinding medicineReminderItemBinding5 = this.o;
            if (medicineReminderItemBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                medicineReminderItemBinding5 = null;
            }
            medicineReminderItemBinding5.repeatLayout.sunday.setBackground(context.getResources().getDrawable(R.drawable.repeat_days_unselected_bg));
            MedicineReminderItemBinding medicineReminderItemBinding6 = this.o;
            if (medicineReminderItemBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                medicineReminderItemBinding6 = null;
            }
            medicineReminderItemBinding6.repeatLayout.sunday.setTextColor(context.getResources().getColor(R.color.secondary_text_color));
            MedicineReminderItemBinding medicineReminderItemBinding7 = this.o;
            if (medicineReminderItemBinding7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                medicineReminderItemBinding7 = null;
            }
            medicineReminderItemBinding7.repeatLayout.sunday.setTextAppearance(R.style.regular);
        }
        if (medicineReminder.getRepeatModel().monday) {
            MedicineReminderItemBinding medicineReminderItemBinding8 = this.o;
            if (medicineReminderItemBinding8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                medicineReminderItemBinding8 = null;
            }
            medicineReminderItemBinding8.repeatLayout.monday.setBackground(context.getResources().getDrawable(R.drawable.repeat_days_selected_bg));
            MedicineReminderItemBinding medicineReminderItemBinding9 = this.o;
            if (medicineReminderItemBinding9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                medicineReminderItemBinding9 = null;
            }
            medicineReminderItemBinding9.repeatLayout.monday.setTextColor(context.getResources().getColor(R.color.main_text_color));
            MedicineReminderItemBinding medicineReminderItemBinding10 = this.o;
            if (medicineReminderItemBinding10 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                medicineReminderItemBinding10 = null;
            }
            medicineReminderItemBinding10.repeatLayout.monday.setTextAppearance(R.style.semi_bold);
        } else {
            MedicineReminderItemBinding medicineReminderItemBinding11 = this.o;
            if (medicineReminderItemBinding11 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                medicineReminderItemBinding11 = null;
            }
            medicineReminderItemBinding11.repeatLayout.monday.setBackground(context.getResources().getDrawable(R.drawable.repeat_days_unselected_bg));
            MedicineReminderItemBinding medicineReminderItemBinding12 = this.o;
            if (medicineReminderItemBinding12 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                medicineReminderItemBinding12 = null;
            }
            medicineReminderItemBinding12.repeatLayout.monday.setTextColor(context.getResources().getColor(R.color.secondary_text_color));
            MedicineReminderItemBinding medicineReminderItemBinding13 = this.o;
            if (medicineReminderItemBinding13 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                medicineReminderItemBinding13 = null;
            }
            medicineReminderItemBinding13.repeatLayout.monday.setTextAppearance(R.style.regular);
        }
        if (medicineReminder.getRepeatModel().tuesday) {
            MedicineReminderItemBinding medicineReminderItemBinding14 = this.o;
            if (medicineReminderItemBinding14 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                medicineReminderItemBinding14 = null;
            }
            medicineReminderItemBinding14.repeatLayout.tuesday.setBackground(context.getResources().getDrawable(R.drawable.repeat_days_selected_bg));
            MedicineReminderItemBinding medicineReminderItemBinding15 = this.o;
            if (medicineReminderItemBinding15 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                medicineReminderItemBinding15 = null;
            }
            medicineReminderItemBinding15.repeatLayout.tuesday.setTextColor(context.getResources().getColor(R.color.main_text_color));
            MedicineReminderItemBinding medicineReminderItemBinding16 = this.o;
            if (medicineReminderItemBinding16 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                medicineReminderItemBinding16 = null;
            }
            medicineReminderItemBinding16.repeatLayout.tuesday.setTextAppearance(R.style.semi_bold);
        } else {
            MedicineReminderItemBinding medicineReminderItemBinding17 = this.o;
            if (medicineReminderItemBinding17 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                medicineReminderItemBinding17 = null;
            }
            medicineReminderItemBinding17.repeatLayout.tuesday.setBackground(context.getResources().getDrawable(R.drawable.repeat_days_unselected_bg));
            MedicineReminderItemBinding medicineReminderItemBinding18 = this.o;
            if (medicineReminderItemBinding18 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                medicineReminderItemBinding18 = null;
            }
            medicineReminderItemBinding18.repeatLayout.tuesday.setTextColor(context.getResources().getColor(R.color.secondary_text_color));
            MedicineReminderItemBinding medicineReminderItemBinding19 = this.o;
            if (medicineReminderItemBinding19 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                medicineReminderItemBinding19 = null;
            }
            medicineReminderItemBinding19.repeatLayout.tuesday.setTextAppearance(R.style.regular);
        }
        if (medicineReminder.getRepeatModel().wednesday) {
            MedicineReminderItemBinding medicineReminderItemBinding20 = this.o;
            if (medicineReminderItemBinding20 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                medicineReminderItemBinding20 = null;
            }
            medicineReminderItemBinding20.repeatLayout.wednesday.setBackground(context.getResources().getDrawable(R.drawable.repeat_days_selected_bg));
            MedicineReminderItemBinding medicineReminderItemBinding21 = this.o;
            if (medicineReminderItemBinding21 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                medicineReminderItemBinding21 = null;
            }
            medicineReminderItemBinding21.repeatLayout.wednesday.setTextColor(context.getResources().getColor(R.color.main_text_color));
            MedicineReminderItemBinding medicineReminderItemBinding22 = this.o;
            if (medicineReminderItemBinding22 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                medicineReminderItemBinding22 = null;
            }
            medicineReminderItemBinding22.repeatLayout.wednesday.setTextAppearance(R.style.semi_bold);
        } else {
            MedicineReminderItemBinding medicineReminderItemBinding23 = this.o;
            if (medicineReminderItemBinding23 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                medicineReminderItemBinding23 = null;
            }
            medicineReminderItemBinding23.repeatLayout.wednesday.setBackground(context.getResources().getDrawable(R.drawable.repeat_days_unselected_bg));
            MedicineReminderItemBinding medicineReminderItemBinding24 = this.o;
            if (medicineReminderItemBinding24 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                medicineReminderItemBinding24 = null;
            }
            medicineReminderItemBinding24.repeatLayout.wednesday.setTextColor(context.getResources().getColor(R.color.secondary_text_color));
            MedicineReminderItemBinding medicineReminderItemBinding25 = this.o;
            if (medicineReminderItemBinding25 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                medicineReminderItemBinding25 = null;
            }
            medicineReminderItemBinding25.repeatLayout.wednesday.setTextAppearance(R.style.regular);
        }
        if (medicineReminder.getRepeatModel().thursday) {
            MedicineReminderItemBinding medicineReminderItemBinding26 = this.o;
            if (medicineReminderItemBinding26 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                medicineReminderItemBinding26 = null;
            }
            medicineReminderItemBinding26.repeatLayout.thursday.setBackground(context.getResources().getDrawable(R.drawable.repeat_days_selected_bg));
            MedicineReminderItemBinding medicineReminderItemBinding27 = this.o;
            if (medicineReminderItemBinding27 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                medicineReminderItemBinding27 = null;
            }
            medicineReminderItemBinding27.repeatLayout.thursday.setTextColor(context.getResources().getColor(R.color.main_text_color));
            MedicineReminderItemBinding medicineReminderItemBinding28 = this.o;
            if (medicineReminderItemBinding28 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                medicineReminderItemBinding28 = null;
            }
            medicineReminderItemBinding28.repeatLayout.thursday.setTextAppearance(R.style.semi_bold);
        } else {
            MedicineReminderItemBinding medicineReminderItemBinding29 = this.o;
            if (medicineReminderItemBinding29 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                medicineReminderItemBinding29 = null;
            }
            medicineReminderItemBinding29.repeatLayout.thursday.setBackground(context.getResources().getDrawable(R.drawable.repeat_days_unselected_bg));
            MedicineReminderItemBinding medicineReminderItemBinding30 = this.o;
            if (medicineReminderItemBinding30 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                medicineReminderItemBinding30 = null;
            }
            medicineReminderItemBinding30.repeatLayout.thursday.setTextColor(context.getResources().getColor(R.color.secondary_text_color));
            MedicineReminderItemBinding medicineReminderItemBinding31 = this.o;
            if (medicineReminderItemBinding31 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                medicineReminderItemBinding31 = null;
            }
            medicineReminderItemBinding31.repeatLayout.thursday.setTextAppearance(R.style.regular);
        }
        if (medicineReminder.getRepeatModel().friday) {
            MedicineReminderItemBinding medicineReminderItemBinding32 = this.o;
            if (medicineReminderItemBinding32 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                medicineReminderItemBinding32 = null;
            }
            medicineReminderItemBinding32.repeatLayout.friday.setBackground(context.getResources().getDrawable(R.drawable.repeat_days_selected_bg));
            MedicineReminderItemBinding medicineReminderItemBinding33 = this.o;
            if (medicineReminderItemBinding33 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                medicineReminderItemBinding33 = null;
            }
            medicineReminderItemBinding33.repeatLayout.friday.setTextColor(context.getResources().getColor(R.color.main_text_color));
            MedicineReminderItemBinding medicineReminderItemBinding34 = this.o;
            if (medicineReminderItemBinding34 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                medicineReminderItemBinding34 = null;
            }
            medicineReminderItemBinding34.repeatLayout.friday.setTextAppearance(R.style.semi_bold);
        } else {
            MedicineReminderItemBinding medicineReminderItemBinding35 = this.o;
            if (medicineReminderItemBinding35 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                medicineReminderItemBinding35 = null;
            }
            medicineReminderItemBinding35.repeatLayout.friday.setBackground(context.getResources().getDrawable(R.drawable.repeat_days_unselected_bg));
            MedicineReminderItemBinding medicineReminderItemBinding36 = this.o;
            if (medicineReminderItemBinding36 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                medicineReminderItemBinding36 = null;
            }
            medicineReminderItemBinding36.repeatLayout.friday.setTextColor(context.getResources().getColor(R.color.secondary_text_color));
            MedicineReminderItemBinding medicineReminderItemBinding37 = this.o;
            if (medicineReminderItemBinding37 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                medicineReminderItemBinding37 = null;
            }
            medicineReminderItemBinding37.repeatLayout.friday.setTextAppearance(R.style.regular);
        }
        if (medicineReminder.getRepeatModel().saturday) {
            MedicineReminderItemBinding medicineReminderItemBinding38 = this.o;
            if (medicineReminderItemBinding38 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                medicineReminderItemBinding38 = null;
            }
            medicineReminderItemBinding38.repeatLayout.saturday.setBackground(context.getResources().getDrawable(R.drawable.repeat_days_selected_bg));
            MedicineReminderItemBinding medicineReminderItemBinding39 = this.o;
            if (medicineReminderItemBinding39 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                medicineReminderItemBinding39 = null;
            }
            medicineReminderItemBinding39.repeatLayout.saturday.setTextColor(context.getResources().getColor(R.color.main_text_color));
            MedicineReminderItemBinding medicineReminderItemBinding40 = this.o;
            if (medicineReminderItemBinding40 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                medicineReminderItemBinding40 = null;
            }
            medicineReminderItemBinding40.repeatLayout.saturday.setTextAppearance(R.style.semi_bold);
        } else {
            MedicineReminderItemBinding medicineReminderItemBinding41 = this.o;
            if (medicineReminderItemBinding41 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                medicineReminderItemBinding41 = null;
            }
            medicineReminderItemBinding41.repeatLayout.saturday.setBackground(context.getResources().getDrawable(R.drawable.repeat_days_unselected_bg));
            MedicineReminderItemBinding medicineReminderItemBinding42 = this.o;
            if (medicineReminderItemBinding42 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                medicineReminderItemBinding42 = null;
            }
            medicineReminderItemBinding42.repeatLayout.saturday.setTextColor(context.getResources().getColor(R.color.secondary_text_color));
            MedicineReminderItemBinding medicineReminderItemBinding43 = this.o;
            if (medicineReminderItemBinding43 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                medicineReminderItemBinding43 = null;
            }
            medicineReminderItemBinding43.repeatLayout.saturday.setTextAppearance(R.style.regular);
        }
        if (medicineReminder.getRepeatModel().isAllSelected()) {
            MedicineReminderItemBinding medicineReminderItemBinding44 = this.o;
            if (medicineReminderItemBinding44 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                medicineReminderItemBinding44 = null;
            }
            if (medicineReminderItemBinding44.repeatLayout.cbSelectAll.isChecked()) {
                return;
            }
            MedicineReminderItemBinding medicineReminderItemBinding45 = this.o;
            if (medicineReminderItemBinding45 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                medicineReminderItemBinding = medicineReminderItemBinding45;
            }
            medicineReminderItemBinding.repeatLayout.cbSelectAll.setChecked(true);
        }
    }

    public final void bind(@NotNull final MeetingReminder meetingReminder, final int i) {
        Intrinsics.checkNotNullParameter(meetingReminder, "meetingReminder");
        MedicineReminderItemBinding medicineReminderItemBinding = null;
        if (!AppUtils.isEmpty(meetingReminder.getDescription())) {
            MedicineReminderItemBinding medicineReminderItemBinding2 = this.o;
            if (medicineReminderItemBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                medicineReminderItemBinding2 = null;
            }
            medicineReminderItemBinding2.edtReminderName.setText(meetingReminder.getDescription());
        }
        if (meetingReminder.getStartDate() != null) {
            MedicineReminderItemBinding medicineReminderItemBinding3 = this.o;
            if (medicineReminderItemBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                medicineReminderItemBinding3 = null;
            }
            TextView textView = medicineReminderItemBinding3.tvStartDate;
            Calendar startDate = meetingReminder.getStartDate();
            Intrinsics.checkNotNullExpressionValue(startDate, "meetingReminder.startDate");
            textView.setText(ExtensionsKt.toFormattedDateStr(startDate, "dd/MM/YYYY"));
        }
        if (meetingReminder.getEndDate() != null) {
            MedicineReminderItemBinding medicineReminderItemBinding4 = this.o;
            if (medicineReminderItemBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                medicineReminderItemBinding4 = null;
            }
            TextView textView2 = medicineReminderItemBinding4.tvEndDate;
            Calendar endDate = meetingReminder.getEndDate();
            Intrinsics.checkNotNullExpressionValue(endDate, "meetingReminder.endDate");
            textView2.setText(ExtensionsKt.toFormattedDateStr(endDate, "dd/MM/YYYY"));
        }
        MedicineReminderItemBinding medicineReminderItemBinding5 = this.o;
        if (medicineReminderItemBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            medicineReminderItemBinding5 = null;
        }
        medicineReminderItemBinding5.edtReminderName.addTextChangedListener(new TextWatcher() { // from class: com.coveiot.android.customreminders.fragments.MedicineReminderFragmentNew$bind$15
            @Override // android.text.TextWatcher
            public void afterTextChanged(@Nullable Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(@Nullable CharSequence charSequence, int i2, int i3, int i4) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(@Nullable CharSequence charSequence, int i2, int i3, int i4) {
                MedicineReminderItemBinding medicineReminderItemBinding6;
                MeetingReminder.this.setDescription(charSequence != null ? charSequence.toString() : null);
                this.setReminder(MeetingReminder.this, i);
                medicineReminderItemBinding6 = this.o;
                if (medicineReminderItemBinding6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    medicineReminderItemBinding6 = null;
                }
                TextView textView3 = medicineReminderItemBinding6.tvCharactersLeft;
                StringBuilder sb = new StringBuilder();
                sb.append(charSequence != null ? Integer.valueOf(charSequence.length()) : null);
                sb.append("/24");
                textView3.setText(sb.toString());
            }
        });
        MedicineReminderItemBinding medicineReminderItemBinding6 = this.o;
        if (medicineReminderItemBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            medicineReminderItemBinding6 = null;
        }
        medicineReminderItemBinding6.tvStartDate.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.customreminders.fragments.l0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MedicineReminderFragmentNew.P(MedicineReminderFragmentNew.this, meetingReminder, i, view);
            }
        });
        MedicineReminderItemBinding medicineReminderItemBinding7 = this.o;
        if (medicineReminderItemBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            medicineReminderItemBinding7 = null;
        }
        medicineReminderItemBinding7.tvEndDate.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.customreminders.fragments.k0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MedicineReminderFragmentNew.w(MedicineReminderFragmentNew.this, meetingReminder, i, view);
            }
        });
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        updateRepeatLayout(meetingReminder, requireContext);
        MedicineReminderItemBinding medicineReminderItemBinding8 = this.o;
        if (medicineReminderItemBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            medicineReminderItemBinding8 = null;
        }
        medicineReminderItemBinding8.repeatLayout.sunday.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.customreminders.fragments.MedicineReminderFragmentNew$bind$18
            @Override // android.view.View.OnClickListener
            public void onClick(@Nullable View view) {
                MeetingReminder.this.getRepeatModel().sunday = !MeetingReminder.this.getRepeatModel().sunday;
                this.setReminder(MeetingReminder.this, i);
                MedicineReminderFragmentNew medicineReminderFragmentNew = this;
                MeetingReminder meetingReminder2 = MeetingReminder.this;
                Context requireContext2 = medicineReminderFragmentNew.requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                medicineReminderFragmentNew.updateRepeatLayout(meetingReminder2, requireContext2);
            }
        });
        MedicineReminderItemBinding medicineReminderItemBinding9 = this.o;
        if (medicineReminderItemBinding9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            medicineReminderItemBinding9 = null;
        }
        medicineReminderItemBinding9.repeatLayout.monday.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.customreminders.fragments.MedicineReminderFragmentNew$bind$19
            @Override // android.view.View.OnClickListener
            public void onClick(@Nullable View view) {
                MeetingReminder.this.getRepeatModel().monday = !MeetingReminder.this.getRepeatModel().monday;
                this.setReminder(MeetingReminder.this, i);
                MedicineReminderFragmentNew medicineReminderFragmentNew = this;
                MeetingReminder meetingReminder2 = MeetingReminder.this;
                Context requireContext2 = medicineReminderFragmentNew.requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                medicineReminderFragmentNew.updateRepeatLayout(meetingReminder2, requireContext2);
            }
        });
        MedicineReminderItemBinding medicineReminderItemBinding10 = this.o;
        if (medicineReminderItemBinding10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            medicineReminderItemBinding10 = null;
        }
        medicineReminderItemBinding10.repeatLayout.tuesday.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.customreminders.fragments.MedicineReminderFragmentNew$bind$20
            @Override // android.view.View.OnClickListener
            public void onClick(@Nullable View view) {
                MeetingReminder.this.getRepeatModel().tuesday = !MeetingReminder.this.getRepeatModel().tuesday;
                this.setReminder(MeetingReminder.this, i);
                MedicineReminderFragmentNew medicineReminderFragmentNew = this;
                MeetingReminder meetingReminder2 = MeetingReminder.this;
                Context requireContext2 = medicineReminderFragmentNew.requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                medicineReminderFragmentNew.updateRepeatLayout(meetingReminder2, requireContext2);
            }
        });
        MedicineReminderItemBinding medicineReminderItemBinding11 = this.o;
        if (medicineReminderItemBinding11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            medicineReminderItemBinding11 = null;
        }
        medicineReminderItemBinding11.repeatLayout.wednesday.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.customreminders.fragments.MedicineReminderFragmentNew$bind$21
            @Override // android.view.View.OnClickListener
            public void onClick(@Nullable View view) {
                MeetingReminder.this.getRepeatModel().wednesday = !MeetingReminder.this.getRepeatModel().wednesday;
                this.setReminder(MeetingReminder.this, i);
                MedicineReminderFragmentNew medicineReminderFragmentNew = this;
                MeetingReminder meetingReminder2 = MeetingReminder.this;
                Context requireContext2 = medicineReminderFragmentNew.requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                medicineReminderFragmentNew.updateRepeatLayout(meetingReminder2, requireContext2);
            }
        });
        MedicineReminderItemBinding medicineReminderItemBinding12 = this.o;
        if (medicineReminderItemBinding12 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            medicineReminderItemBinding12 = null;
        }
        medicineReminderItemBinding12.repeatLayout.thursday.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.customreminders.fragments.MedicineReminderFragmentNew$bind$22
            @Override // android.view.View.OnClickListener
            public void onClick(@Nullable View view) {
                MeetingReminder.this.getRepeatModel().thursday = !MeetingReminder.this.getRepeatModel().thursday;
                this.setReminder(MeetingReminder.this, i);
                MedicineReminderFragmentNew medicineReminderFragmentNew = this;
                MeetingReminder meetingReminder2 = MeetingReminder.this;
                Context requireContext2 = medicineReminderFragmentNew.requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                medicineReminderFragmentNew.updateRepeatLayout(meetingReminder2, requireContext2);
            }
        });
        MedicineReminderItemBinding medicineReminderItemBinding13 = this.o;
        if (medicineReminderItemBinding13 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            medicineReminderItemBinding13 = null;
        }
        medicineReminderItemBinding13.repeatLayout.friday.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.customreminders.fragments.MedicineReminderFragmentNew$bind$23
            @Override // android.view.View.OnClickListener
            public void onClick(@Nullable View view) {
                MeetingReminder.this.getRepeatModel().friday = !MeetingReminder.this.getRepeatModel().friday;
                this.setReminder(MeetingReminder.this, i);
                MedicineReminderFragmentNew medicineReminderFragmentNew = this;
                MeetingReminder meetingReminder2 = MeetingReminder.this;
                Context requireContext2 = medicineReminderFragmentNew.requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                medicineReminderFragmentNew.updateRepeatLayout(meetingReminder2, requireContext2);
            }
        });
        MedicineReminderItemBinding medicineReminderItemBinding14 = this.o;
        if (medicineReminderItemBinding14 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            medicineReminderItemBinding14 = null;
        }
        medicineReminderItemBinding14.repeatLayout.saturday.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.customreminders.fragments.MedicineReminderFragmentNew$bind$24
            @Override // android.view.View.OnClickListener
            public void onClick(@Nullable View view) {
                MeetingReminder.this.getRepeatModel().saturday = !MeetingReminder.this.getRepeatModel().saturday;
                this.setReminder(MeetingReminder.this, i);
                MedicineReminderFragmentNew medicineReminderFragmentNew = this;
                MeetingReminder meetingReminder2 = MeetingReminder.this;
                Context requireContext2 = medicineReminderFragmentNew.requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                medicineReminderFragmentNew.updateRepeatLayout(meetingReminder2, requireContext2);
            }
        });
        MedicineReminderItemBinding medicineReminderItemBinding15 = this.o;
        if (medicineReminderItemBinding15 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            medicineReminderItemBinding15 = null;
        }
        medicineReminderItemBinding15.repeatLayout.cbSelectAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.coveiot.android.customreminders.fragments.q0
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                MedicineReminderFragmentNew.y(MeetingReminder.this, this, i, compoundButton, z);
            }
        });
        ArrayAdapter<CharSequence> createFromResource = ArrayAdapter.createFromResource(requireContext(), R.array.notify_me_list, R.layout.spinner_list_item);
        Intrinsics.checkNotNullExpressionValue(createFromResource, "createFromResource(\n    …inner_list_item\n        )");
        createFromResource.setDropDownViewResource(R.layout.spinner_drop_down_view);
        MedicineReminderItemBinding medicineReminderItemBinding16 = this.o;
        if (medicineReminderItemBinding16 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            medicineReminderItemBinding16 = null;
        }
        medicineReminderItemBinding16.spNotifyMe.setAdapter((SpinnerAdapter) createFromResource);
        MedicineReminderItemBinding medicineReminderItemBinding17 = this.o;
        if (medicineReminderItemBinding17 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            medicineReminderItemBinding17 = null;
        }
        medicineReminderItemBinding17.spNotifyMe.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.coveiot.android.customreminders.fragments.MedicineReminderFragmentNew$bind$26
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(@Nullable AdapterView<?> adapterView, @Nullable View view, int i2, long j) {
                MeetingReminder.this.setAdvanceTime(ReminderConstants.Companion.getNOTIFIY_ME_BEFOR_DATA()[i2].intValue());
                this.setReminder(MeetingReminder.this, i);
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(@Nullable AdapterView<?> adapterView) {
            }
        });
        MedicineReminderItemBinding medicineReminderItemBinding18 = this.o;
        if (medicineReminderItemBinding18 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            medicineReminderItemBinding18 = null;
        }
        medicineReminderItemBinding18.spNotifyMe.setSelection(1, true);
        final AdapterRemindMeAt adapterRemindMeAt = new AdapterRemindMeAt();
        if (!AppUtils.isEmpty(meetingReminder.getTimeInfos())) {
            ArrayList<TimeInfo> timeInfos = meetingReminder.getTimeInfos();
            Intrinsics.checkNotNullExpressionValue(timeInfos, "meetingReminder.timeInfos");
            adapterRemindMeAt.setTimeInfosList(timeInfos);
        }
        adapterRemindMeAt.setListener(new AdapterRemindMeAt.RemindAtListener() { // from class: com.coveiot.android.customreminders.fragments.MedicineReminderFragmentNew$bind$27
            @Override // com.coveiot.android.customreminders.adapter.AdapterRemindMeAt.RemindAtListener
            public void onTimeInfoUpdate(@NotNull ArrayList<TimeInfo> timeInfosLst) {
                Intrinsics.checkNotNullParameter(timeInfosLst, "timeInfosLst");
                MeetingReminder.this.setTimeInfos(timeInfosLst);
                this.setReminder(MeetingReminder.this, i);
            }
        });
        MedicineReminderItemBinding medicineReminderItemBinding19 = this.o;
        if (medicineReminderItemBinding19 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            medicineReminderItemBinding19 = null;
        }
        medicineReminderItemBinding19.rcvTimeInfos.setLayoutManager(new LinearLayoutManager(requireContext()));
        MedicineReminderItemBinding medicineReminderItemBinding20 = this.o;
        if (medicineReminderItemBinding20 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            medicineReminderItemBinding20 = null;
        }
        medicineReminderItemBinding20.rcvTimeInfos.setAdapter(adapterRemindMeAt);
        MedicineReminderItemBinding medicineReminderItemBinding21 = this.o;
        if (medicineReminderItemBinding21 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            medicineReminderItemBinding21 = null;
        }
        medicineReminderItemBinding21.addTimeInfo.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.customreminders.fragments.g0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MedicineReminderFragmentNew.z(AdapterRemindMeAt.this, this, view);
            }
        });
        MedicineReminderItemBinding medicineReminderItemBinding22 = this.o;
        if (medicineReminderItemBinding22 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            medicineReminderItemBinding = medicineReminderItemBinding22;
        }
        medicineReminderItemBinding.spNotifyMe.setSelection(R(meetingReminder.getAdvanceTime()), true);
    }

    public final void bind(@NotNull final OtherReminder otherReminder, final int i) {
        Intrinsics.checkNotNullParameter(otherReminder, "otherReminder");
        MedicineReminderItemBinding medicineReminderItemBinding = null;
        if (!AppUtils.isEmpty(otherReminder.getDescription())) {
            MedicineReminderItemBinding medicineReminderItemBinding2 = this.o;
            if (medicineReminderItemBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                medicineReminderItemBinding2 = null;
            }
            medicineReminderItemBinding2.edtReminderName.setText(otherReminder.getDescription());
        }
        if (otherReminder.getStartDate() != null) {
            MedicineReminderItemBinding medicineReminderItemBinding3 = this.o;
            if (medicineReminderItemBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                medicineReminderItemBinding3 = null;
            }
            TextView textView = medicineReminderItemBinding3.tvStartDate;
            Calendar startDate = otherReminder.getStartDate();
            Intrinsics.checkNotNullExpressionValue(startDate, "otherReminder.startDate");
            textView.setText(ExtensionsKt.toFormattedDateStr(startDate, "dd/MM/YYYY"));
        }
        if (otherReminder.getEndDate() != null) {
            MedicineReminderItemBinding medicineReminderItemBinding4 = this.o;
            if (medicineReminderItemBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                medicineReminderItemBinding4 = null;
            }
            TextView textView2 = medicineReminderItemBinding4.tvEndDate;
            Calendar endDate = otherReminder.getEndDate();
            Intrinsics.checkNotNullExpressionValue(endDate, "otherReminder.endDate");
            textView2.setText(ExtensionsKt.toFormattedDateStr(endDate, "dd/MM/YYYY"));
        }
        MedicineReminderItemBinding medicineReminderItemBinding5 = this.o;
        if (medicineReminderItemBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            medicineReminderItemBinding5 = null;
        }
        medicineReminderItemBinding5.edtReminderName.addTextChangedListener(new TextWatcher() { // from class: com.coveiot.android.customreminders.fragments.MedicineReminderFragmentNew$bind$29
            @Override // android.text.TextWatcher
            public void afterTextChanged(@Nullable Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(@Nullable CharSequence charSequence, int i2, int i3, int i4) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(@Nullable CharSequence charSequence, int i2, int i3, int i4) {
                MedicineReminderItemBinding medicineReminderItemBinding6;
                OtherReminder.this.setDescription(charSequence != null ? charSequence.toString() : null);
                this.setReminder(OtherReminder.this, i);
                medicineReminderItemBinding6 = this.o;
                if (medicineReminderItemBinding6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    medicineReminderItemBinding6 = null;
                }
                TextView textView3 = medicineReminderItemBinding6.tvCharactersLeft;
                StringBuilder sb = new StringBuilder();
                sb.append(charSequence != null ? Integer.valueOf(charSequence.length()) : null);
                sb.append("/24");
                textView3.setText(sb.toString());
            }
        });
        MedicineReminderItemBinding medicineReminderItemBinding6 = this.o;
        if (medicineReminderItemBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            medicineReminderItemBinding6 = null;
        }
        medicineReminderItemBinding6.tvStartDate.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.customreminders.fragments.m0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MedicineReminderFragmentNew.B(MedicineReminderFragmentNew.this, otherReminder, i, view);
            }
        });
        MedicineReminderItemBinding medicineReminderItemBinding7 = this.o;
        if (medicineReminderItemBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            medicineReminderItemBinding7 = null;
        }
        medicineReminderItemBinding7.tvEndDate.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.customreminders.fragments.n0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MedicineReminderFragmentNew.D(MedicineReminderFragmentNew.this, otherReminder, i, view);
            }
        });
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        updateRepeatLayout(otherReminder, requireContext);
        MedicineReminderItemBinding medicineReminderItemBinding8 = this.o;
        if (medicineReminderItemBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            medicineReminderItemBinding8 = null;
        }
        medicineReminderItemBinding8.repeatLayout.sunday.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.customreminders.fragments.MedicineReminderFragmentNew$bind$32
            @Override // android.view.View.OnClickListener
            public void onClick(@Nullable View view) {
                OtherReminder.this.getRepeatModel().sunday = !OtherReminder.this.getRepeatModel().sunday;
                this.setReminder(OtherReminder.this, i);
                MedicineReminderFragmentNew medicineReminderFragmentNew = this;
                OtherReminder otherReminder2 = OtherReminder.this;
                Context requireContext2 = medicineReminderFragmentNew.requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                medicineReminderFragmentNew.updateRepeatLayout(otherReminder2, requireContext2);
            }
        });
        MedicineReminderItemBinding medicineReminderItemBinding9 = this.o;
        if (medicineReminderItemBinding9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            medicineReminderItemBinding9 = null;
        }
        medicineReminderItemBinding9.repeatLayout.monday.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.customreminders.fragments.MedicineReminderFragmentNew$bind$33
            @Override // android.view.View.OnClickListener
            public void onClick(@Nullable View view) {
                OtherReminder.this.getRepeatModel().monday = !OtherReminder.this.getRepeatModel().monday;
                this.setReminder(OtherReminder.this, i);
                MedicineReminderFragmentNew medicineReminderFragmentNew = this;
                OtherReminder otherReminder2 = OtherReminder.this;
                Context requireContext2 = medicineReminderFragmentNew.requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                medicineReminderFragmentNew.updateRepeatLayout(otherReminder2, requireContext2);
            }
        });
        MedicineReminderItemBinding medicineReminderItemBinding10 = this.o;
        if (medicineReminderItemBinding10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            medicineReminderItemBinding10 = null;
        }
        medicineReminderItemBinding10.repeatLayout.tuesday.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.customreminders.fragments.MedicineReminderFragmentNew$bind$34
            @Override // android.view.View.OnClickListener
            public void onClick(@Nullable View view) {
                OtherReminder.this.getRepeatModel().tuesday = !OtherReminder.this.getRepeatModel().tuesday;
                this.setReminder(OtherReminder.this, i);
                MedicineReminderFragmentNew medicineReminderFragmentNew = this;
                OtherReminder otherReminder2 = OtherReminder.this;
                Context requireContext2 = medicineReminderFragmentNew.requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                medicineReminderFragmentNew.updateRepeatLayout(otherReminder2, requireContext2);
            }
        });
        MedicineReminderItemBinding medicineReminderItemBinding11 = this.o;
        if (medicineReminderItemBinding11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            medicineReminderItemBinding11 = null;
        }
        medicineReminderItemBinding11.repeatLayout.wednesday.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.customreminders.fragments.MedicineReminderFragmentNew$bind$35
            @Override // android.view.View.OnClickListener
            public void onClick(@Nullable View view) {
                OtherReminder.this.getRepeatModel().wednesday = !OtherReminder.this.getRepeatModel().wednesday;
                this.setReminder(OtherReminder.this, i);
                MedicineReminderFragmentNew medicineReminderFragmentNew = this;
                OtherReminder otherReminder2 = OtherReminder.this;
                Context requireContext2 = medicineReminderFragmentNew.requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                medicineReminderFragmentNew.updateRepeatLayout(otherReminder2, requireContext2);
            }
        });
        MedicineReminderItemBinding medicineReminderItemBinding12 = this.o;
        if (medicineReminderItemBinding12 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            medicineReminderItemBinding12 = null;
        }
        medicineReminderItemBinding12.repeatLayout.thursday.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.customreminders.fragments.MedicineReminderFragmentNew$bind$36
            @Override // android.view.View.OnClickListener
            public void onClick(@Nullable View view) {
                OtherReminder.this.getRepeatModel().thursday = !OtherReminder.this.getRepeatModel().thursday;
                this.setReminder(OtherReminder.this, i);
                MedicineReminderFragmentNew medicineReminderFragmentNew = this;
                OtherReminder otherReminder2 = OtherReminder.this;
                Context requireContext2 = medicineReminderFragmentNew.requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                medicineReminderFragmentNew.updateRepeatLayout(otherReminder2, requireContext2);
            }
        });
        MedicineReminderItemBinding medicineReminderItemBinding13 = this.o;
        if (medicineReminderItemBinding13 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            medicineReminderItemBinding13 = null;
        }
        medicineReminderItemBinding13.repeatLayout.friday.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.customreminders.fragments.MedicineReminderFragmentNew$bind$37
            @Override // android.view.View.OnClickListener
            public void onClick(@Nullable View view) {
                OtherReminder.this.getRepeatModel().friday = !OtherReminder.this.getRepeatModel().friday;
                this.setReminder(OtherReminder.this, i);
                MedicineReminderFragmentNew medicineReminderFragmentNew = this;
                OtherReminder otherReminder2 = OtherReminder.this;
                Context requireContext2 = medicineReminderFragmentNew.requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                medicineReminderFragmentNew.updateRepeatLayout(otherReminder2, requireContext2);
            }
        });
        MedicineReminderItemBinding medicineReminderItemBinding14 = this.o;
        if (medicineReminderItemBinding14 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            medicineReminderItemBinding14 = null;
        }
        medicineReminderItemBinding14.repeatLayout.saturday.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.customreminders.fragments.MedicineReminderFragmentNew$bind$38
            @Override // android.view.View.OnClickListener
            public void onClick(@Nullable View view) {
                OtherReminder.this.getRepeatModel().saturday = !OtherReminder.this.getRepeatModel().saturday;
                this.setReminder(OtherReminder.this, i);
                MedicineReminderFragmentNew medicineReminderFragmentNew = this;
                OtherReminder otherReminder2 = OtherReminder.this;
                Context requireContext2 = medicineReminderFragmentNew.requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                medicineReminderFragmentNew.updateRepeatLayout(otherReminder2, requireContext2);
            }
        });
        MedicineReminderItemBinding medicineReminderItemBinding15 = this.o;
        if (medicineReminderItemBinding15 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            medicineReminderItemBinding15 = null;
        }
        medicineReminderItemBinding15.repeatLayout.cbSelectAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.coveiot.android.customreminders.fragments.r0
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                MedicineReminderFragmentNew.F(OtherReminder.this, this, i, compoundButton, z);
            }
        });
        ArrayAdapter<CharSequence> createFromResource = ArrayAdapter.createFromResource(requireContext(), R.array.notify_me_list, R.layout.spinner_list_item);
        Intrinsics.checkNotNullExpressionValue(createFromResource, "createFromResource(\n    …inner_list_item\n        )");
        createFromResource.setDropDownViewResource(R.layout.spinner_drop_down_view);
        MedicineReminderItemBinding medicineReminderItemBinding16 = this.o;
        if (medicineReminderItemBinding16 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            medicineReminderItemBinding16 = null;
        }
        medicineReminderItemBinding16.spNotifyMe.setAdapter((SpinnerAdapter) createFromResource);
        MedicineReminderItemBinding medicineReminderItemBinding17 = this.o;
        if (medicineReminderItemBinding17 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            medicineReminderItemBinding17 = null;
        }
        medicineReminderItemBinding17.spNotifyMe.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.coveiot.android.customreminders.fragments.MedicineReminderFragmentNew$bind$40
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(@Nullable AdapterView<?> adapterView, @Nullable View view, int i2, long j) {
                OtherReminder.this.setAdvanceTime(ReminderConstants.Companion.getNOTIFIY_ME_BEFOR_DATA()[i2].intValue());
                this.setReminder(OtherReminder.this, i);
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(@Nullable AdapterView<?> adapterView) {
            }
        });
        MedicineReminderItemBinding medicineReminderItemBinding18 = this.o;
        if (medicineReminderItemBinding18 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            medicineReminderItemBinding18 = null;
        }
        medicineReminderItemBinding18.spNotifyMe.setSelection(1, true);
        final AdapterRemindMeAt adapterRemindMeAt = new AdapterRemindMeAt();
        if (!AppUtils.isEmpty(otherReminder.getTimeInfos())) {
            ArrayList<TimeInfo> timeInfos = otherReminder.getTimeInfos();
            Intrinsics.checkNotNullExpressionValue(timeInfos, "otherReminder.timeInfos");
            adapterRemindMeAt.setTimeInfosList(timeInfos);
        }
        adapterRemindMeAt.setListener(new AdapterRemindMeAt.RemindAtListener() { // from class: com.coveiot.android.customreminders.fragments.MedicineReminderFragmentNew$bind$41
            @Override // com.coveiot.android.customreminders.adapter.AdapterRemindMeAt.RemindAtListener
            public void onTimeInfoUpdate(@NotNull ArrayList<TimeInfo> timeInfosLst) {
                Intrinsics.checkNotNullParameter(timeInfosLst, "timeInfosLst");
                OtherReminder.this.setTimeInfos(timeInfosLst);
                this.setReminder(OtherReminder.this, i);
            }
        });
        MedicineReminderItemBinding medicineReminderItemBinding19 = this.o;
        if (medicineReminderItemBinding19 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            medicineReminderItemBinding19 = null;
        }
        medicineReminderItemBinding19.rcvTimeInfos.setLayoutManager(new LinearLayoutManager(requireContext()));
        MedicineReminderItemBinding medicineReminderItemBinding20 = this.o;
        if (medicineReminderItemBinding20 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            medicineReminderItemBinding20 = null;
        }
        medicineReminderItemBinding20.rcvTimeInfos.setAdapter(adapterRemindMeAt);
        MedicineReminderItemBinding medicineReminderItemBinding21 = this.o;
        if (medicineReminderItemBinding21 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            medicineReminderItemBinding21 = null;
        }
        medicineReminderItemBinding21.addTimeInfo.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.customreminders.fragments.z0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MedicineReminderFragmentNew.I(AdapterRemindMeAt.this, this, view);
            }
        });
        MedicineReminderItemBinding medicineReminderItemBinding22 = this.o;
        if (medicineReminderItemBinding22 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            medicineReminderItemBinding = medicineReminderItemBinding22;
        }
        medicineReminderItemBinding.spNotifyMe.setSelection(R(otherReminder.getAdvanceTime()), true);
    }
}
