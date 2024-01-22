package com.coveiot.android.customreminders.adapter;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.TimePicker;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.customreminders.R;
import com.coveiot.android.customreminders.adapter.AdapterMedicineReminder;
import com.coveiot.android.customreminders.adapter.AdapterRemindMeAt;
import com.coveiot.android.customreminders.databinding.MedicineReminderItemBinding;
import com.coveiot.android.customreminders.listeners.ReminderValidationListner;
import com.coveiot.android.customreminders.model.CustomReminder;
import com.coveiot.android.customreminders.model.MedicineReminder;
import com.coveiot.android.customreminders.model.RepeatModel;
import com.coveiot.android.customreminders.utils.ExtensionsKt;
import com.coveiot.android.customreminders.utils.ReminderConstants;
import com.coveiot.sdk.ble.api.model.TimeInfo;
import com.coveiot.utils.utility.AppUtils;
import java.util.ArrayList;
import java.util.Calendar;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class AdapterMedicineReminder extends RecyclerView.Adapter<MedicineViewHolder> implements ReminderValidationListner {
    @NotNull
    public ArrayList<MedicineReminder> h = new ArrayList<>();
    @NotNull
    public final MutableLiveData<Boolean> i = new MutableLiveData<>(Boolean.FALSE);

    /* loaded from: classes3.dex */
    public final class MedicineViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final MedicineReminderItemBinding f4129a;
        public final /* synthetic */ AdapterMedicineReminder b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public MedicineViewHolder(@NotNull AdapterMedicineReminder adapterMedicineReminder, MedicineReminderItemBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.b = adapterMedicineReminder;
            this.f4129a = binding;
        }

        public static final void h(final MedicineViewHolder this$0, final MedicineReminder medicineReminder, final AdapterMedicineReminder this$1, final int i, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(medicineReminder, "$medicineReminder");
            Intrinsics.checkNotNullParameter(this$1, "this$1");
            Calendar calendar = Calendar.getInstance();
            new DatePickerDialog(this$0.f4129a.getRoot().getContext(), R.style.DialogThemeDarWindowBG, new DatePickerDialog.OnDateSetListener() { // from class: com.coveiot.android.customreminders.adapter.a
                @Override // android.app.DatePickerDialog.OnDateSetListener
                public final void onDateSet(DatePicker datePicker, int i2, int i3, int i4) {
                    AdapterMedicineReminder.MedicineViewHolder.i(MedicineReminder.this, this$1, i, this$0, datePicker, i2, i3, i4);
                }
            }, calendar.get(1), calendar.get(2), calendar.get(5)).show();
        }

        public static final void i(MedicineReminder medicineReminder, AdapterMedicineReminder this$0, int i, MedicineViewHolder this$1, DatePicker datePicker, int i2, int i3, int i4) {
            Intrinsics.checkNotNullParameter(medicineReminder, "$medicineReminder");
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(this$1, "this$1");
            Calendar startDate = Calendar.getInstance();
            startDate.set(i2, i3, i4);
            medicineReminder.setStartDate(startDate);
            this$0.setReminder(medicineReminder, i);
            TextView textView = this$1.f4129a.tvStartDate;
            Intrinsics.checkNotNullExpressionValue(startDate, "startDate");
            textView.setText(ExtensionsKt.toFormattedDateStr(startDate, "dd/MM/YYYY"));
        }

        public static final void j(final MedicineViewHolder this$0, final MedicineReminder medicineReminder, final AdapterMedicineReminder this$1, final int i, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(medicineReminder, "$medicineReminder");
            Intrinsics.checkNotNullParameter(this$1, "this$1");
            Calendar calendar = Calendar.getInstance();
            new DatePickerDialog(this$0.f4129a.getRoot().getContext(), R.style.DialogThemeDarWindowBG, new DatePickerDialog.OnDateSetListener() { // from class: com.coveiot.android.customreminders.adapter.b
                @Override // android.app.DatePickerDialog.OnDateSetListener
                public final void onDateSet(DatePicker datePicker, int i2, int i3, int i4) {
                    AdapterMedicineReminder.MedicineViewHolder.k(MedicineReminder.this, this$1, i, this$0, datePicker, i2, i3, i4);
                }
            }, calendar.get(1), calendar.get(2), calendar.get(5)).show();
        }

        public static final void k(MedicineReminder medicineReminder, AdapterMedicineReminder this$0, int i, MedicineViewHolder this$1, DatePicker datePicker, int i2, int i3, int i4) {
            Intrinsics.checkNotNullParameter(medicineReminder, "$medicineReminder");
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(this$1, "this$1");
            Calendar startDate = Calendar.getInstance();
            startDate.set(i2, i3, i4);
            medicineReminder.setEndDate(startDate);
            this$0.setReminder(medicineReminder, i);
            TextView textView = this$1.f4129a.tvEndDate;
            Intrinsics.checkNotNullExpressionValue(startDate, "startDate");
            textView.setText(ExtensionsKt.toFormattedDateStr(startDate, "dd/MM/YYYY"));
        }

        public static final void l(MedicineReminder medicineReminder, AdapterMedicineReminder this$0, int i, MedicineViewHolder this$1, CompoundButton compoundButton, boolean z) {
            Intrinsics.checkNotNullParameter(medicineReminder, "$medicineReminder");
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(this$1, "this$1");
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
            Context context = this$1.f4129a.getRoot().getContext();
            Intrinsics.checkNotNullExpressionValue(context, "binding.root.context");
            this$1.updateRepeatLayout(medicineReminder, context);
        }

        public static final void m(final AdapterRemindMeAt adapterRemindMeAt, MedicineViewHolder this$0, View view) {
            Intrinsics.checkNotNullParameter(adapterRemindMeAt, "$adapterRemindMeAt");
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            if (adapterRemindMeAt.getTimeInfosList().size() == 0) {
                return;
            }
            new TimePickerDialog(this$0.f4129a.getRoot().getContext(), R.style.DialogThemeDarWindowBG, new TimePickerDialog.OnTimeSetListener() { // from class: com.coveiot.android.customreminders.adapter.c
                @Override // android.app.TimePickerDialog.OnTimeSetListener
                public final void onTimeSet(TimePicker timePicker, int i, int i2) {
                    AdapterMedicineReminder.MedicineViewHolder.n(AdapterRemindMeAt.this, timePicker, i, i2);
                }
            }, Calendar.getInstance().get(11), Calendar.getInstance().get(12), DateFormat.is24HourFormat(this$0.f4129a.getRoot().getContext())).show();
        }

        public static final void n(AdapterRemindMeAt adapterRemindMeAt, TimePicker timePicker, int i, int i2) {
            Intrinsics.checkNotNullParameter(adapterRemindMeAt, "$adapterRemindMeAt");
            adapterRemindMeAt.addTimeInfo(new TimeInfo(i, i2));
        }

        public final void bind(@NotNull final MedicineReminder medicineReminder, final int i) {
            Intrinsics.checkNotNullParameter(medicineReminder, "medicineReminder");
            EditText editText = this.f4129a.edtReminderName;
            final AdapterMedicineReminder adapterMedicineReminder = this.b;
            editText.addTextChangedListener(new TextWatcher() { // from class: com.coveiot.android.customreminders.adapter.AdapterMedicineReminder$MedicineViewHolder$bind$1
                @Override // android.text.TextWatcher
                public void afterTextChanged(@Nullable Editable editable) {
                }

                @Override // android.text.TextWatcher
                public void beforeTextChanged(@Nullable CharSequence charSequence, int i2, int i3, int i4) {
                }

                @Override // android.text.TextWatcher
                public void onTextChanged(@Nullable CharSequence charSequence, int i2, int i3, int i4) {
                    MedicineReminder.this.setDescription(charSequence != null ? charSequence.toString() : null);
                    adapterMedicineReminder.setReminder(MedicineReminder.this, i);
                    TextView textView = this.getBinding().tvCharactersLeft;
                    StringBuilder sb = new StringBuilder();
                    sb.append(charSequence != null ? Integer.valueOf(charSequence.length()) : null);
                    sb.append("/24");
                    textView.setText(sb.toString());
                }
            });
            TextView textView = this.f4129a.tvStartDate;
            final AdapterMedicineReminder adapterMedicineReminder2 = this.b;
            textView.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.customreminders.adapter.e
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AdapterMedicineReminder.MedicineViewHolder.h(AdapterMedicineReminder.MedicineViewHolder.this, medicineReminder, adapterMedicineReminder2, i, view);
                }
            });
            TextView textView2 = this.f4129a.tvEndDate;
            final AdapterMedicineReminder adapterMedicineReminder3 = this.b;
            textView2.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.customreminders.adapter.d
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AdapterMedicineReminder.MedicineViewHolder.j(AdapterMedicineReminder.MedicineViewHolder.this, medicineReminder, adapterMedicineReminder3, i, view);
                }
            });
            TextView textView3 = this.f4129a.repeatLayout.sunday;
            final AdapterMedicineReminder adapterMedicineReminder4 = this.b;
            textView3.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.customreminders.adapter.AdapterMedicineReminder$MedicineViewHolder$bind$4
                @Override // android.view.View.OnClickListener
                public void onClick(@Nullable View view) {
                    MedicineReminder.this.getRepeatModel().sunday = !MedicineReminder.this.getRepeatModel().sunday;
                    adapterMedicineReminder4.setReminder(MedicineReminder.this, i);
                    AdapterMedicineReminder.MedicineViewHolder medicineViewHolder = this;
                    MedicineReminder medicineReminder2 = MedicineReminder.this;
                    Context context = medicineViewHolder.getBinding().getRoot().getContext();
                    Intrinsics.checkNotNullExpressionValue(context, "binding.root.context");
                    medicineViewHolder.updateRepeatLayout(medicineReminder2, context);
                }
            });
            TextView textView4 = this.f4129a.repeatLayout.monday;
            final AdapterMedicineReminder adapterMedicineReminder5 = this.b;
            textView4.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.customreminders.adapter.AdapterMedicineReminder$MedicineViewHolder$bind$5
                @Override // android.view.View.OnClickListener
                public void onClick(@Nullable View view) {
                    MedicineReminder.this.getRepeatModel().monday = !MedicineReminder.this.getRepeatModel().monday;
                    adapterMedicineReminder5.setReminder(MedicineReminder.this, i);
                    AdapterMedicineReminder.MedicineViewHolder medicineViewHolder = this;
                    MedicineReminder medicineReminder2 = MedicineReminder.this;
                    Context context = medicineViewHolder.getBinding().getRoot().getContext();
                    Intrinsics.checkNotNullExpressionValue(context, "binding.root.context");
                    medicineViewHolder.updateRepeatLayout(medicineReminder2, context);
                }
            });
            TextView textView5 = this.f4129a.repeatLayout.tuesday;
            final AdapterMedicineReminder adapterMedicineReminder6 = this.b;
            textView5.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.customreminders.adapter.AdapterMedicineReminder$MedicineViewHolder$bind$6
                @Override // android.view.View.OnClickListener
                public void onClick(@Nullable View view) {
                    MedicineReminder.this.getRepeatModel().tuesday = !MedicineReminder.this.getRepeatModel().tuesday;
                    adapterMedicineReminder6.setReminder(MedicineReminder.this, i);
                    AdapterMedicineReminder.MedicineViewHolder medicineViewHolder = this;
                    MedicineReminder medicineReminder2 = MedicineReminder.this;
                    Context context = medicineViewHolder.getBinding().getRoot().getContext();
                    Intrinsics.checkNotNullExpressionValue(context, "binding.root.context");
                    medicineViewHolder.updateRepeatLayout(medicineReminder2, context);
                }
            });
            TextView textView6 = this.f4129a.repeatLayout.wednesday;
            final AdapterMedicineReminder adapterMedicineReminder7 = this.b;
            textView6.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.customreminders.adapter.AdapterMedicineReminder$MedicineViewHolder$bind$7
                @Override // android.view.View.OnClickListener
                public void onClick(@Nullable View view) {
                    MedicineReminder.this.getRepeatModel().wednesday = !MedicineReminder.this.getRepeatModel().wednesday;
                    adapterMedicineReminder7.setReminder(MedicineReminder.this, i);
                    AdapterMedicineReminder.MedicineViewHolder medicineViewHolder = this;
                    MedicineReminder medicineReminder2 = MedicineReminder.this;
                    Context context = medicineViewHolder.getBinding().getRoot().getContext();
                    Intrinsics.checkNotNullExpressionValue(context, "binding.root.context");
                    medicineViewHolder.updateRepeatLayout(medicineReminder2, context);
                }
            });
            TextView textView7 = this.f4129a.repeatLayout.thursday;
            final AdapterMedicineReminder adapterMedicineReminder8 = this.b;
            textView7.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.customreminders.adapter.AdapterMedicineReminder$MedicineViewHolder$bind$8
                @Override // android.view.View.OnClickListener
                public void onClick(@Nullable View view) {
                    MedicineReminder.this.getRepeatModel().thursday = !MedicineReminder.this.getRepeatModel().thursday;
                    adapterMedicineReminder8.setReminder(MedicineReminder.this, i);
                    AdapterMedicineReminder.MedicineViewHolder medicineViewHolder = this;
                    MedicineReminder medicineReminder2 = MedicineReminder.this;
                    Context context = medicineViewHolder.getBinding().getRoot().getContext();
                    Intrinsics.checkNotNullExpressionValue(context, "binding.root.context");
                    medicineViewHolder.updateRepeatLayout(medicineReminder2, context);
                }
            });
            TextView textView8 = this.f4129a.repeatLayout.friday;
            final AdapterMedicineReminder adapterMedicineReminder9 = this.b;
            textView8.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.customreminders.adapter.AdapterMedicineReminder$MedicineViewHolder$bind$9
                @Override // android.view.View.OnClickListener
                public void onClick(@Nullable View view) {
                    MedicineReminder.this.getRepeatModel().friday = !MedicineReminder.this.getRepeatModel().friday;
                    adapterMedicineReminder9.setReminder(MedicineReminder.this, i);
                    AdapterMedicineReminder.MedicineViewHolder medicineViewHolder = this;
                    MedicineReminder medicineReminder2 = MedicineReminder.this;
                    Context context = medicineViewHolder.getBinding().getRoot().getContext();
                    Intrinsics.checkNotNullExpressionValue(context, "binding.root.context");
                    medicineViewHolder.updateRepeatLayout(medicineReminder2, context);
                }
            });
            TextView textView9 = this.f4129a.repeatLayout.saturday;
            final AdapterMedicineReminder adapterMedicineReminder10 = this.b;
            textView9.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.customreminders.adapter.AdapterMedicineReminder$MedicineViewHolder$bind$10
                @Override // android.view.View.OnClickListener
                public void onClick(@Nullable View view) {
                    MedicineReminder.this.getRepeatModel().saturday = !MedicineReminder.this.getRepeatModel().saturday;
                    adapterMedicineReminder10.setReminder(MedicineReminder.this, i);
                    AdapterMedicineReminder.MedicineViewHolder medicineViewHolder = this;
                    MedicineReminder medicineReminder2 = MedicineReminder.this;
                    Context context = medicineViewHolder.getBinding().getRoot().getContext();
                    Intrinsics.checkNotNullExpressionValue(context, "binding.root.context");
                    medicineViewHolder.updateRepeatLayout(medicineReminder2, context);
                }
            });
            CheckBox checkBox = this.f4129a.repeatLayout.cbSelectAll;
            final AdapterMedicineReminder adapterMedicineReminder11 = this.b;
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.coveiot.android.customreminders.adapter.g
                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    AdapterMedicineReminder.MedicineViewHolder.l(MedicineReminder.this, adapterMedicineReminder11, i, this, compoundButton, z);
                }
            });
            MedicineReminderItemBinding medicineReminderItemBinding = this.f4129a;
            medicineReminderItemBinding.spNotifyMe.setAdapter((SpinnerAdapter) ArrayAdapter.createFromResource(medicineReminderItemBinding.getRoot().getContext(), R.array.notify_me_list, R.layout.spinner_list_item));
            Spinner spinner = this.f4129a.spNotifyMe;
            final AdapterMedicineReminder adapterMedicineReminder12 = this.b;
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.coveiot.android.customreminders.adapter.AdapterMedicineReminder$MedicineViewHolder$bind$12
                @Override // android.widget.AdapterView.OnItemSelectedListener
                public void onItemSelected(@Nullable AdapterView<?> adapterView, @Nullable View view, int i2, long j) {
                    MedicineReminder.this.setAdvanceTime(ReminderConstants.Companion.getNOTIFIY_ME_BEFOR_DATA()[i2].intValue());
                    adapterMedicineReminder12.setReminder(MedicineReminder.this, i);
                }

                @Override // android.widget.AdapterView.OnItemSelectedListener
                public void onNothingSelected(@Nullable AdapterView<?> adapterView) {
                }
            });
            this.f4129a.spNotifyMe.setSelection(1, true);
            final AdapterRemindMeAt adapterRemindMeAt = new AdapterRemindMeAt();
            if (!AppUtils.isEmpty(medicineReminder.getTimeInfos())) {
                ArrayList<TimeInfo> timeInfos = medicineReminder.getTimeInfos();
                Intrinsics.checkNotNullExpressionValue(timeInfos, "medicineReminder.timeInfos");
                adapterRemindMeAt.setTimeInfosList(timeInfos);
            }
            final AdapterMedicineReminder adapterMedicineReminder13 = this.b;
            adapterRemindMeAt.setListener(new AdapterRemindMeAt.RemindAtListener() { // from class: com.coveiot.android.customreminders.adapter.AdapterMedicineReminder$MedicineViewHolder$bind$13
                @Override // com.coveiot.android.customreminders.adapter.AdapterRemindMeAt.RemindAtListener
                public void onTimeInfoUpdate(@NotNull ArrayList<TimeInfo> timeInfosLst) {
                    Intrinsics.checkNotNullParameter(timeInfosLst, "timeInfosLst");
                    MedicineReminder.this.setTimeInfos(timeInfosLst);
                    adapterMedicineReminder13.setReminder(MedicineReminder.this, i);
                }
            });
            MedicineReminderItemBinding medicineReminderItemBinding2 = this.f4129a;
            medicineReminderItemBinding2.rcvTimeInfos.setLayoutManager(new LinearLayoutManager(medicineReminderItemBinding2.getRoot().getContext()));
            this.f4129a.rcvTimeInfos.setAdapter(adapterRemindMeAt);
            this.f4129a.addTimeInfo.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.customreminders.adapter.f
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AdapterMedicineReminder.MedicineViewHolder.m(AdapterRemindMeAt.this, this, view);
                }
            });
        }

        @NotNull
        public final MedicineReminderItemBinding getBinding() {
            return this.f4129a;
        }

        public final void updateRepeatLayout(@NotNull MedicineReminder medicineReminder, @NotNull Context context) {
            Intrinsics.checkNotNullParameter(medicineReminder, "medicineReminder");
            Intrinsics.checkNotNullParameter(context, "context");
            if (medicineReminder.getRepeatModel().sunday) {
                this.f4129a.repeatLayout.sunday.setBackground(context.getResources().getDrawable(R.drawable.repeat_days_selected_bg));
                this.f4129a.repeatLayout.sunday.setTextColor(context.getResources().getColor(R.color.main_text_color));
                this.f4129a.repeatLayout.sunday.setTextAppearance(R.style.semi_bold);
            } else {
                this.f4129a.repeatLayout.sunday.setBackground(context.getResources().getDrawable(R.drawable.repeat_days_unselected_bg));
                this.f4129a.repeatLayout.sunday.setTextColor(context.getResources().getColor(R.color.secondary_text_color));
                this.f4129a.repeatLayout.sunday.setTextAppearance(R.style.regular);
            }
            if (medicineReminder.getRepeatModel().monday) {
                this.f4129a.repeatLayout.monday.setBackground(context.getResources().getDrawable(R.drawable.repeat_days_selected_bg));
                this.f4129a.repeatLayout.monday.setTextColor(context.getResources().getColor(R.color.main_text_color));
                this.f4129a.repeatLayout.monday.setTextAppearance(R.style.semi_bold);
            } else {
                this.f4129a.repeatLayout.monday.setBackground(context.getResources().getDrawable(R.drawable.repeat_days_unselected_bg));
                this.f4129a.repeatLayout.monday.setTextColor(context.getResources().getColor(R.color.secondary_text_color));
                this.f4129a.repeatLayout.monday.setTextAppearance(R.style.regular);
            }
            if (medicineReminder.getRepeatModel().tuesday) {
                this.f4129a.repeatLayout.tuesday.setBackground(context.getResources().getDrawable(R.drawable.repeat_days_selected_bg));
                this.f4129a.repeatLayout.tuesday.setTextColor(context.getResources().getColor(R.color.main_text_color));
                this.f4129a.repeatLayout.tuesday.setTextAppearance(R.style.semi_bold);
            } else {
                this.f4129a.repeatLayout.tuesday.setBackground(context.getResources().getDrawable(R.drawable.repeat_days_unselected_bg));
                this.f4129a.repeatLayout.tuesday.setTextColor(context.getResources().getColor(R.color.secondary_text_color));
                this.f4129a.repeatLayout.tuesday.setTextAppearance(R.style.regular);
            }
            if (medicineReminder.getRepeatModel().wednesday) {
                this.f4129a.repeatLayout.wednesday.setBackground(context.getResources().getDrawable(R.drawable.repeat_days_selected_bg));
                this.f4129a.repeatLayout.wednesday.setTextColor(context.getResources().getColor(R.color.main_text_color));
                this.f4129a.repeatLayout.wednesday.setTextAppearance(R.style.semi_bold);
            } else {
                this.f4129a.repeatLayout.wednesday.setBackground(context.getResources().getDrawable(R.drawable.repeat_days_unselected_bg));
                this.f4129a.repeatLayout.wednesday.setTextColor(context.getResources().getColor(R.color.secondary_text_color));
                this.f4129a.repeatLayout.wednesday.setTextAppearance(R.style.regular);
            }
            if (medicineReminder.getRepeatModel().thursday) {
                this.f4129a.repeatLayout.thursday.setBackground(context.getResources().getDrawable(R.drawable.repeat_days_selected_bg));
                this.f4129a.repeatLayout.thursday.setTextColor(context.getResources().getColor(R.color.main_text_color));
                this.f4129a.repeatLayout.thursday.setTextAppearance(R.style.semi_bold);
            } else {
                this.f4129a.repeatLayout.thursday.setBackground(context.getResources().getDrawable(R.drawable.repeat_days_unselected_bg));
                this.f4129a.repeatLayout.thursday.setTextColor(context.getResources().getColor(R.color.secondary_text_color));
                this.f4129a.repeatLayout.thursday.setTextAppearance(R.style.regular);
            }
            if (medicineReminder.getRepeatModel().friday) {
                this.f4129a.repeatLayout.friday.setBackground(context.getResources().getDrawable(R.drawable.repeat_days_selected_bg));
                this.f4129a.repeatLayout.friday.setTextColor(context.getResources().getColor(R.color.main_text_color));
                this.f4129a.repeatLayout.friday.setTextAppearance(R.style.semi_bold);
            } else {
                this.f4129a.repeatLayout.friday.setBackground(context.getResources().getDrawable(R.drawable.repeat_days_unselected_bg));
                this.f4129a.repeatLayout.friday.setTextColor(context.getResources().getColor(R.color.secondary_text_color));
                this.f4129a.repeatLayout.friday.setTextAppearance(R.style.regular);
            }
            if (medicineReminder.getRepeatModel().saturday) {
                this.f4129a.repeatLayout.saturday.setBackground(context.getResources().getDrawable(R.drawable.repeat_days_selected_bg));
                this.f4129a.repeatLayout.saturday.setTextColor(context.getResources().getColor(R.color.main_text_color));
                this.f4129a.repeatLayout.saturday.setTextAppearance(R.style.semi_bold);
            } else {
                this.f4129a.repeatLayout.saturday.setBackground(context.getResources().getDrawable(R.drawable.repeat_days_unselected_bg));
                this.f4129a.repeatLayout.saturday.setTextColor(context.getResources().getColor(R.color.secondary_text_color));
                this.f4129a.repeatLayout.saturday.setTextAppearance(R.style.regular);
            }
            if (!medicineReminder.getRepeatModel().isAllSelected() || this.f4129a.repeatLayout.cbSelectAll.isChecked()) {
                return;
            }
            this.f4129a.repeatLayout.cbSelectAll.setChecked(true);
        }
    }

    @Override // com.coveiot.android.customreminders.listeners.ReminderValidationListner
    public void addReminder(@NotNull CustomReminder reminder) {
        Intrinsics.checkNotNullParameter(reminder, "reminder");
        this.h.add((MedicineReminder) reminder);
        notifyDataSetChanged();
    }

    @Override // com.coveiot.android.customreminders.listeners.ReminderValidationListner
    @NotNull
    public ArrayList<MedicineReminder> getAllMedicineReminderList() {
        return this.h;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.h.size();
    }

    @NotNull
    public final ArrayList<MedicineReminder> getMedicinesReminderList() {
        return this.h;
    }

    @NotNull
    public final MutableLiveData<Boolean> getShouldEnableSubmit() {
        return this.i;
    }

    /* JADX WARN: Removed duplicated region for block: B:5:0x000c  */
    @Override // com.coveiot.android.customreminders.listeners.ReminderValidationListner
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean isAllRemindersValid() {
        /*
            r8 = this;
            java.util.ArrayList<com.coveiot.android.customreminders.model.MedicineReminder> r0 = r8.h
            java.util.Iterator r0 = r0.iterator()
        L6:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L51
            java.lang.Object r1 = r0.next()
            com.coveiot.android.customreminders.model.MedicineReminder r1 = (com.coveiot.android.customreminders.model.MedicineReminder) r1
            java.lang.String r2 = r1.getDescription()
            boolean r2 = com.coveiot.utils.utility.AppUtils.isEmpty(r2)
            r3 = 0
            if (r2 == 0) goto L1e
            return r3
        L1e:
            java.util.Calendar r2 = r1.getStartDate()
            if (r2 != 0) goto L25
            return r3
        L25:
            java.util.Calendar r2 = r1.getStartDate()
            if (r2 == 0) goto L46
            java.util.Calendar r2 = r1.getEndDate()
            if (r2 == 0) goto L46
            java.util.Calendar r2 = r1.getEndDate()
            long r4 = r2.getTimeInMillis()
            java.util.Calendar r2 = r1.getStartDate()
            long r6 = r2.getTimeInMillis()
            int r2 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r2 >= 0) goto L46
            return r3
        L46:
            java.util.ArrayList r1 = r1.getTimeInfos()
            boolean r1 = com.coveiot.utils.utility.AppUtils.isEmpty(r1)
            if (r1 == 0) goto L6
            return r3
        L51:
            r0 = 1
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.customreminders.adapter.AdapterMedicineReminder.isAllRemindersValid():boolean");
    }

    public final void setMedicinesReminderList(@NotNull ArrayList<MedicineReminder> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.h = arrayList;
    }

    public final void setReminder(@NotNull MedicineReminder reminder, int i) {
        Intrinsics.checkNotNullParameter(reminder, "reminder");
        this.h.set(i, reminder);
        this.i.setValue(Boolean.valueOf(isAllRemindersValid()));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull MedicineViewHolder holder, int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        MedicineReminder medicineReminder = this.h.get(i);
        Intrinsics.checkNotNullExpressionValue(medicineReminder, "medicinesReminderList.get(position)");
        holder.bind(medicineReminder, i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public MedicineViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        MedicineReminderItemBinding inflate = MedicineReminderItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(\n               …      false\n            )");
        return new MedicineViewHolder(this, inflate);
    }
}
