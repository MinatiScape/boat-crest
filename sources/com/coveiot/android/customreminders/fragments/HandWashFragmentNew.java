package com.coveiot.android.customreminders.fragments;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.coveiot.android.customreminders.R;
import com.coveiot.android.customreminders.ReminderType;
import com.coveiot.android.customreminders.adapter.IntervalAdapterReminderNew;
import com.coveiot.android.customreminders.databinding.FragmentHandWashNewBinding;
import com.coveiot.android.customreminders.fragments.MedicineReminderFragmentNew;
import com.coveiot.android.customreminders.listeners.AddReminderListener;
import com.coveiot.android.customreminders.listeners.PostEditReminderListListener;
import com.coveiot.android.customreminders.listeners.ReminderListener;
import com.coveiot.android.customreminders.model.CustomReminder;
import com.coveiot.android.customreminders.model.DrinkWaterReminder;
import com.coveiot.android.customreminders.model.HandWashReminder;
import com.coveiot.android.customreminders.model.RepeatModel;
import com.coveiot.android.customreminders.utils.ViewModelFactory;
import com.coveiot.android.customreminders.viewmodel.HandWashReminderViewModel;
import com.coveiot.android.theme.BottomSheetDialogTwoButtons;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.jstyle.blesdk1860.constant.DeviceKey;
import java.io.Serializable;
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
public final class HandWashFragmentNew extends Fragment implements IntervalAdapterReminderNew.IntervalSelectionListener, View.OnClickListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public FragmentHandWashNewBinding binding;
    @Nullable
    public ReminderType h;
    @Nullable
    public CustomReminder i;
    public HandWashReminderViewModel l;
    public AddReminderListener m;
    public PostEditReminderListListener n;
    public MedicineReminderFragmentNew.SaveButtonListener o;
    @Nullable
    public IntervalAdapterReminderNew p;
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

        public static /* synthetic */ HandWashFragmentNew newInstance$default(Companion companion, ReminderType reminderType, CustomReminder customReminder, int i, int i2, Object obj) {
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
        public final HandWashFragmentNew newInstance(@NotNull ReminderType type, @Nullable CustomReminder customReminder, int i) {
            Intrinsics.checkNotNullParameter(type, "type");
            HandWashFragmentNew handWashFragmentNew = new HandWashFragmentNew();
            Bundle bundle = new Bundle();
            bundle.putSerializable("type", type);
            bundle.putSerializable("reminder_object", customReminder);
            bundle.putSerializable(DeviceKey.position, Integer.valueOf(i));
            handWashFragmentNew.setArguments(bundle);
            return handWashFragmentNew;
        }
    }

    public static final void g(final HandWashFragmentNew this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        new TimePickerDialog(this$0.requireContext(), R.style.DialogThemeDarWindowBG, new TimePickerDialog.OnTimeSetListener() { // from class: com.coveiot.android.customreminders.fragments.c
            @Override // android.app.TimePickerDialog.OnTimeSetListener
            public final void onTimeSet(TimePicker timePicker, int i, int i2) {
                HandWashFragmentNew.h(HandWashFragmentNew.this, timePicker, i, i2);
            }
        }, Calendar.getInstance().get(11), Calendar.getInstance().get(12), DateFormat.is24HourFormat(this$0.requireContext())).show();
    }

    public static final void h(HandWashFragmentNew this$0, TimePicker timePicker, int i, int i2) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        HandWashReminderViewModel handWashReminderViewModel = this$0.l;
        if (handWashReminderViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            handWashReminderViewModel = null;
        }
        HandWashReminderViewModel.setStartTime$default(handWashReminderViewModel, i, i2, false, 4, null);
    }

    public static final void i(final HandWashFragmentNew this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        new TimePickerDialog(this$0.requireContext(), R.style.DialogThemeDarWindowBG, new TimePickerDialog.OnTimeSetListener() { // from class: com.coveiot.android.customreminders.fragments.b
            @Override // android.app.TimePickerDialog.OnTimeSetListener
            public final void onTimeSet(TimePicker timePicker, int i, int i2) {
                HandWashFragmentNew.j(HandWashFragmentNew.this, timePicker, i, i2);
            }
        }, Calendar.getInstance().get(11), Calendar.getInstance().get(12), DateFormat.is24HourFormat(this$0.requireContext())).show();
    }

    public static final void j(HandWashFragmentNew this$0, TimePicker timePicker, int i, int i2) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        HandWashReminderViewModel handWashReminderViewModel = this$0.l;
        if (handWashReminderViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            handWashReminderViewModel = null;
        }
        HandWashReminderViewModel.setEndTime$default(handWashReminderViewModel, i, i2, false, 4, null);
    }

    public static final void k(HandWashFragmentNew this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        HandWashReminderViewModel handWashReminderViewModel = this$0.l;
        if (handWashReminderViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            handWashReminderViewModel = null;
        }
        HandWashReminderViewModel.setRepeat$default(handWashReminderViewModel, new RepeatModel(z), false, 2, null);
    }

    public static final void l(final HandWashFragmentNew this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        HandWashReminderViewModel handWashReminderViewModel = this$0.l;
        if (handWashReminderViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            handWashReminderViewModel = null;
        }
        handWashReminderViewModel.save(new ReminderListener() { // from class: com.coveiot.android.customreminders.fragments.HandWashFragmentNew$initClickListeners$11$1

            @DebugMetadata(c = "com.coveiot.android.customreminders.fragments.HandWashFragmentNew$initClickListeners$11$1$onError$1", f = "HandWashFragmentNew.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* loaded from: classes3.dex */
            public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public final /* synthetic */ String $error;
                public int label;
                public final /* synthetic */ HandWashFragmentNew this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public a(HandWashFragmentNew handWashFragmentNew, String str, Continuation<? super a> continuation) {
                    super(2, continuation);
                    this.this$0 = handWashFragmentNew;
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
                kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new a(HandWashFragmentNew.this, error, null), 2, null);
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
                customReminder2 = HandWashFragmentNew.this.i;
                PostEditReminderListListener postEditReminderListListener3 = null;
                AddReminderListener addReminderListener3 = null;
                if (customReminder2 == null) {
                    addReminderListener = HandWashFragmentNew.this.m;
                    if (addReminderListener != null) {
                        addReminderListener2 = HandWashFragmentNew.this.m;
                        if (addReminderListener2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException(ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
                        } else {
                            addReminderListener3 = addReminderListener2;
                        }
                        reminderType2 = HandWashFragmentNew.this.h;
                        Intrinsics.checkNotNull(reminderType2);
                        addReminderListener3.onReminderAdded(customReminder, reminderType2);
                        return;
                    }
                    return;
                }
                postEditReminderListListener = HandWashFragmentNew.this.n;
                if (postEditReminderListListener != null) {
                    postEditReminderListListener2 = HandWashFragmentNew.this.n;
                    if (postEditReminderListListener2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("editListener");
                    } else {
                        postEditReminderListListener3 = postEditReminderListListener2;
                    }
                    i = HandWashFragmentNew.this.j;
                    reminderType = HandWashFragmentNew.this.h;
                    Intrinsics.checkNotNull(reminderType);
                    postEditReminderListListener3.onEditReminder(i, customReminder, reminderType);
                }
            }
        });
    }

    @JvmStatic
    @NotNull
    public static final HandWashFragmentNew newInstance(@NotNull ReminderType reminderType, @Nullable CustomReminder customReminder, int i) {
        return Companion.newInstance(reminderType, customReminder, i);
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

    @NotNull
    public final FragmentHandWashNewBinding getBinding() {
        FragmentHandWashNewBinding fragmentHandWashNewBinding = this.binding;
        if (fragmentHandWashNewBinding != null) {
            return fragmentHandWashNewBinding;
        }
        Intrinsics.throwUninitializedPropertyAccessException("binding");
        return null;
    }

    @Nullable
    public final BottomSheetDialogTwoButtons getBottomSheetDialogTwoButtons() {
        return this.q;
    }

    @Nullable
    public final IntervalAdapterReminderNew getIntervalAdapterReminderNew() {
        return this.p;
    }

    public final boolean getRepeatFlag() {
        return this.k;
    }

    public final void initClickListeners() {
        getBinding().reminderLayout.tvStartTime.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.customreminders.fragments.d
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HandWashFragmentNew.g(HandWashFragmentNew.this, view);
            }
        });
        getBinding().reminderLayout.tvEndTime.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.customreminders.fragments.e
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HandWashFragmentNew.i(HandWashFragmentNew.this, view);
            }
        });
        getBinding().repeatLayout.sunday.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.customreminders.fragments.HandWashFragmentNew$initClickListeners$3
            @Override // android.view.View.OnClickListener
            public void onClick(@Nullable View view) {
                HandWashReminderViewModel handWashReminderViewModel;
                HandWashReminderViewModel handWashReminderViewModel2;
                handWashReminderViewModel = HandWashFragmentNew.this.l;
                if (handWashReminderViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    handWashReminderViewModel = null;
                }
                RepeatModel repeatModel = handWashReminderViewModel.getRepeatModel();
                repeatModel.sunday = !repeatModel.sunday;
                handWashReminderViewModel2 = HandWashFragmentNew.this.l;
                if (handWashReminderViewModel2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    handWashReminderViewModel2 = null;
                }
                HandWashReminderViewModel.setRepeat$default(handWashReminderViewModel2, repeatModel, false, 2, null);
            }
        });
        getBinding().repeatLayout.tuesday.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.customreminders.fragments.HandWashFragmentNew$initClickListeners$4
            @Override // android.view.View.OnClickListener
            public void onClick(@Nullable View view) {
                HandWashReminderViewModel handWashReminderViewModel;
                HandWashReminderViewModel handWashReminderViewModel2;
                handWashReminderViewModel = HandWashFragmentNew.this.l;
                if (handWashReminderViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    handWashReminderViewModel = null;
                }
                RepeatModel repeatModel = handWashReminderViewModel.getRepeatModel();
                repeatModel.tuesday = !repeatModel.tuesday;
                handWashReminderViewModel2 = HandWashFragmentNew.this.l;
                if (handWashReminderViewModel2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    handWashReminderViewModel2 = null;
                }
                HandWashReminderViewModel.setRepeat$default(handWashReminderViewModel2, repeatModel, false, 2, null);
            }
        });
        getBinding().repeatLayout.monday.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.customreminders.fragments.HandWashFragmentNew$initClickListeners$5
            @Override // android.view.View.OnClickListener
            public void onClick(@Nullable View view) {
                HandWashReminderViewModel handWashReminderViewModel;
                HandWashReminderViewModel handWashReminderViewModel2;
                handWashReminderViewModel = HandWashFragmentNew.this.l;
                if (handWashReminderViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    handWashReminderViewModel = null;
                }
                RepeatModel repeatModel = handWashReminderViewModel.getRepeatModel();
                repeatModel.monday = !repeatModel.monday;
                handWashReminderViewModel2 = HandWashFragmentNew.this.l;
                if (handWashReminderViewModel2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    handWashReminderViewModel2 = null;
                }
                HandWashReminderViewModel.setRepeat$default(handWashReminderViewModel2, repeatModel, false, 2, null);
            }
        });
        getBinding().repeatLayout.wednesday.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.customreminders.fragments.HandWashFragmentNew$initClickListeners$6
            @Override // android.view.View.OnClickListener
            public void onClick(@Nullable View view) {
                HandWashReminderViewModel handWashReminderViewModel;
                HandWashReminderViewModel handWashReminderViewModel2;
                handWashReminderViewModel = HandWashFragmentNew.this.l;
                if (handWashReminderViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    handWashReminderViewModel = null;
                }
                RepeatModel repeatModel = handWashReminderViewModel.getRepeatModel();
                repeatModel.wednesday = !repeatModel.wednesday;
                handWashReminderViewModel2 = HandWashFragmentNew.this.l;
                if (handWashReminderViewModel2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    handWashReminderViewModel2 = null;
                }
                HandWashReminderViewModel.setRepeat$default(handWashReminderViewModel2, repeatModel, false, 2, null);
            }
        });
        getBinding().repeatLayout.thursday.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.customreminders.fragments.HandWashFragmentNew$initClickListeners$7
            @Override // android.view.View.OnClickListener
            public void onClick(@Nullable View view) {
                HandWashReminderViewModel handWashReminderViewModel;
                HandWashReminderViewModel handWashReminderViewModel2;
                handWashReminderViewModel = HandWashFragmentNew.this.l;
                if (handWashReminderViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    handWashReminderViewModel = null;
                }
                RepeatModel repeatModel = handWashReminderViewModel.getRepeatModel();
                repeatModel.thursday = !repeatModel.thursday;
                handWashReminderViewModel2 = HandWashFragmentNew.this.l;
                if (handWashReminderViewModel2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    handWashReminderViewModel2 = null;
                }
                HandWashReminderViewModel.setRepeat$default(handWashReminderViewModel2, repeatModel, false, 2, null);
            }
        });
        getBinding().repeatLayout.friday.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.customreminders.fragments.HandWashFragmentNew$initClickListeners$8
            @Override // android.view.View.OnClickListener
            public void onClick(@Nullable View view) {
                HandWashReminderViewModel handWashReminderViewModel;
                HandWashReminderViewModel handWashReminderViewModel2;
                handWashReminderViewModel = HandWashFragmentNew.this.l;
                if (handWashReminderViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    handWashReminderViewModel = null;
                }
                RepeatModel repeatModel = handWashReminderViewModel.getRepeatModel();
                repeatModel.friday = !repeatModel.friday;
                handWashReminderViewModel2 = HandWashFragmentNew.this.l;
                if (handWashReminderViewModel2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    handWashReminderViewModel2 = null;
                }
                HandWashReminderViewModel.setRepeat$default(handWashReminderViewModel2, repeatModel, false, 2, null);
            }
        });
        getBinding().repeatLayout.saturday.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.customreminders.fragments.HandWashFragmentNew$initClickListeners$9
            @Override // android.view.View.OnClickListener
            public void onClick(@Nullable View view) {
                HandWashReminderViewModel handWashReminderViewModel;
                HandWashReminderViewModel handWashReminderViewModel2;
                handWashReminderViewModel = HandWashFragmentNew.this.l;
                if (handWashReminderViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    handWashReminderViewModel = null;
                }
                RepeatModel repeatModel = handWashReminderViewModel.getRepeatModel();
                repeatModel.saturday = !repeatModel.saturday;
                handWashReminderViewModel2 = HandWashFragmentNew.this.l;
                if (handWashReminderViewModel2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    handWashReminderViewModel2 = null;
                }
                HandWashReminderViewModel.setRepeat$default(handWashReminderViewModel2, repeatModel, false, 2, null);
            }
        });
        getBinding().repeatLayout.cbSelectAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.coveiot.android.customreminders.fragments.g
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                HandWashFragmentNew.k(HandWashFragmentNew.this, compoundButton, z);
            }
        });
        ((Button) _$_findCachedViewById(R.id.saveButton)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.customreminders.fragments.f
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HandWashFragmentNew.l(HandWashFragmentNew.this, view);
            }
        });
    }

    public final void initObservers() {
        HandWashReminderViewModel handWashReminderViewModel = this.l;
        HandWashReminderViewModel handWashReminderViewModel2 = null;
        if (handWashReminderViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            handWashReminderViewModel = null;
        }
        handWashReminderViewModel.getReminderFrequencyLiveData().observe(getViewLifecycleOwner(), new Observer<String>() { // from class: com.coveiot.android.customreminders.fragments.HandWashFragmentNew$initObservers$1
            @Override // androidx.lifecycle.Observer
            public void onChanged(@Nullable String str) {
                IntervalAdapterReminderNew intervalAdapterReminderNew;
                HandWashReminderViewModel handWashReminderViewModel3;
                if (str == null || (intervalAdapterReminderNew = HandWashFragmentNew.this.getIntervalAdapterReminderNew()) == null) {
                    return;
                }
                handWashReminderViewModel3 = HandWashFragmentNew.this.l;
                if (handWashReminderViewModel3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    handWashReminderViewModel3 = null;
                }
                intervalAdapterReminderNew.setSelectedInterval(Integer.valueOf(handWashReminderViewModel3.getMReminderFrequency()));
            }
        });
        HandWashReminderViewModel handWashReminderViewModel3 = this.l;
        if (handWashReminderViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            handWashReminderViewModel3 = null;
        }
        handWashReminderViewModel3.getStartTimeLiveData().observe(getViewLifecycleOwner(), new Observer<String>() { // from class: com.coveiot.android.customreminders.fragments.HandWashFragmentNew$initObservers$2
            @Override // androidx.lifecycle.Observer
            public void onChanged(@Nullable String str) {
                if (str != null) {
                    HandWashFragmentNew.this.getBinding().reminderLayout.tvStartTime.setText(str);
                }
            }
        });
        HandWashReminderViewModel handWashReminderViewModel4 = this.l;
        if (handWashReminderViewModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            handWashReminderViewModel4 = null;
        }
        handWashReminderViewModel4.getEndTimeLiveData().observe(getViewLifecycleOwner(), new Observer<String>() { // from class: com.coveiot.android.customreminders.fragments.HandWashFragmentNew$initObservers$3
            @Override // androidx.lifecycle.Observer
            public void onChanged(@Nullable String str) {
                if (str != null) {
                    HandWashFragmentNew.this.getBinding().reminderLayout.tvEndTime.setText(str);
                }
            }
        });
        HandWashReminderViewModel handWashReminderViewModel5 = this.l;
        if (handWashReminderViewModel5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            handWashReminderViewModel5 = null;
        }
        handWashReminderViewModel5.getRepeatLiveData().observe(getViewLifecycleOwner(), new Observer<RepeatModel>() { // from class: com.coveiot.android.customreminders.fragments.HandWashFragmentNew$initObservers$4
            @Override // androidx.lifecycle.Observer
            public void onChanged(@Nullable RepeatModel repeatModel) {
                if (repeatModel != null) {
                    HandWashFragmentNew.this.o();
                    if (repeatModel.monday) {
                        HandWashFragmentNew.this.getBinding().repeatLayout.monday.setBackgroundResource(R.drawable.repeat_days_selected_bg);
                        HandWashFragmentNew.this.getBinding().repeatLayout.monday.setTextColor(HandWashFragmentNew.this.getResources().getColor(R.color.main_text_color));
                    } else {
                        HandWashFragmentNew.this.getBinding().repeatLayout.monday.setBackgroundResource(R.drawable.repeat_days_unselected_bg);
                        HandWashFragmentNew.this.getBinding().repeatLayout.monday.setTextColor(HandWashFragmentNew.this.getResources().getColor(R.color.secondary_text_color));
                    }
                    if (repeatModel.tuesday) {
                        HandWashFragmentNew.this.getBinding().repeatLayout.tuesday.setBackgroundResource(R.drawable.repeat_days_selected_bg);
                        HandWashFragmentNew.this.getBinding().repeatLayout.tuesday.setTextColor(HandWashFragmentNew.this.getResources().getColor(R.color.main_text_color));
                    } else {
                        HandWashFragmentNew.this.getBinding().repeatLayout.tuesday.setBackgroundResource(R.drawable.repeat_days_unselected_bg);
                        HandWashFragmentNew.this.getBinding().repeatLayout.tuesday.setTextColor(HandWashFragmentNew.this.getResources().getColor(R.color.secondary_text_color));
                    }
                    if (repeatModel.wednesday) {
                        HandWashFragmentNew.this.getBinding().repeatLayout.wednesday.setBackgroundResource(R.drawable.repeat_days_selected_bg);
                        HandWashFragmentNew.this.getBinding().repeatLayout.wednesday.setTextColor(HandWashFragmentNew.this.getResources().getColor(R.color.main_text_color));
                    } else {
                        HandWashFragmentNew.this.getBinding().repeatLayout.wednesday.setBackgroundResource(R.drawable.repeat_days_unselected_bg);
                        HandWashFragmentNew.this.getBinding().repeatLayout.wednesday.setTextColor(HandWashFragmentNew.this.getResources().getColor(R.color.secondary_text_color));
                    }
                    if (repeatModel.thursday) {
                        HandWashFragmentNew.this.getBinding().repeatLayout.thursday.setBackgroundResource(R.drawable.repeat_days_selected_bg);
                        HandWashFragmentNew.this.getBinding().repeatLayout.thursday.setTextColor(HandWashFragmentNew.this.getResources().getColor(R.color.main_text_color));
                    } else {
                        HandWashFragmentNew.this.getBinding().repeatLayout.thursday.setBackgroundResource(R.drawable.repeat_days_unselected_bg);
                        HandWashFragmentNew.this.getBinding().repeatLayout.thursday.setTextColor(HandWashFragmentNew.this.getResources().getColor(R.color.secondary_text_color));
                    }
                    if (repeatModel.friday) {
                        HandWashFragmentNew.this.getBinding().repeatLayout.friday.setBackgroundResource(R.drawable.repeat_days_selected_bg);
                        HandWashFragmentNew.this.getBinding().repeatLayout.friday.setTextColor(HandWashFragmentNew.this.getResources().getColor(R.color.main_text_color));
                    } else {
                        HandWashFragmentNew.this.getBinding().repeatLayout.friday.setBackgroundResource(R.drawable.repeat_days_unselected_bg);
                        HandWashFragmentNew.this.getBinding().repeatLayout.friday.setTextColor(HandWashFragmentNew.this.getResources().getColor(R.color.secondary_text_color));
                    }
                    if (repeatModel.saturday) {
                        HandWashFragmentNew.this.getBinding().repeatLayout.saturday.setBackgroundResource(R.drawable.repeat_days_selected_bg);
                        HandWashFragmentNew.this.getBinding().repeatLayout.saturday.setTextColor(HandWashFragmentNew.this.getResources().getColor(R.color.main_text_color));
                    } else {
                        HandWashFragmentNew.this.getBinding().repeatLayout.saturday.setBackgroundResource(R.drawable.repeat_days_unselected_bg);
                        HandWashFragmentNew.this.getBinding().repeatLayout.saturday.setTextColor(HandWashFragmentNew.this.getResources().getColor(R.color.secondary_text_color));
                    }
                    if (repeatModel.sunday) {
                        HandWashFragmentNew.this.getBinding().repeatLayout.sunday.setBackgroundResource(R.drawable.repeat_days_selected_bg);
                        HandWashFragmentNew.this.getBinding().repeatLayout.sunday.setTextColor(HandWashFragmentNew.this.getResources().getColor(R.color.main_text_color));
                    } else {
                        HandWashFragmentNew.this.getBinding().repeatLayout.sunday.setBackgroundResource(R.drawable.repeat_days_unselected_bg);
                        HandWashFragmentNew.this.getBinding().repeatLayout.sunday.setTextColor(HandWashFragmentNew.this.getResources().getColor(R.color.secondary_text_color));
                    }
                    if (repeatModel.isAllSelected()) {
                        HandWashFragmentNew.this.getBinding().repeatLayout.cbSelectAll.setChecked(true);
                    }
                }
            }
        });
        HandWashReminderViewModel handWashReminderViewModel6 = this.l;
        if (handWashReminderViewModel6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            handWashReminderViewModel2 = handWashReminderViewModel6;
        }
        handWashReminderViewModel2.getDoneBtnVisablityLiveData().observe(getViewLifecycleOwner(), new Observer<Boolean>() { // from class: com.coveiot.android.customreminders.fragments.HandWashFragmentNew$initObservers$5
            @Override // androidx.lifecycle.Observer
            public void onChanged(@Nullable Boolean bool) {
                MedicineReminderFragmentNew.SaveButtonListener saveButtonListener;
                Intrinsics.checkNotNull(bool);
                ((Button) HandWashFragmentNew.this._$_findCachedViewById(R.id.saveButton)).setEnabled(bool.booleanValue());
                saveButtonListener = HandWashFragmentNew.this.o;
                if (saveButtonListener == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("saveButtonListener");
                    saveButtonListener = null;
                }
                saveButtonListener.isAllDetailsFilled(bool.booleanValue());
            }
        });
    }

    public final void m() {
        getBinding().reminderLayout.alertIntervalTitle.setText(getString(R.string.remind_me_every));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext());
        linearLayoutManager.setOrientation(0);
        getBinding().reminderLayout.rcvInterval.setLayoutManager(linearLayoutManager);
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        IntervalAdapterReminderNew intervalAdapterReminderNew = new IntervalAdapterReminderNew(new Integer[]{30, 60, 120, 180}, requireContext);
        this.p = intervalAdapterReminderNew;
        intervalAdapterReminderNew.setListner(this);
        getBinding().reminderLayout.rcvInterval.setAdapter(this.p);
        getBinding().repeatLayout.cbSelectAll.setChecked(false);
        getBinding().repeatLayout.divider.setVisibility(8);
        getBinding().repeatLayout.tvRepeatLabel.setText(getString(R.string.repeat));
    }

    public final void n() {
        CustomReminder customReminder = this.i;
        if (customReminder != null) {
            ReminderType reminderType = this.h;
            if (reminderType == ReminderType.DRINK) {
                Intrinsics.checkNotNull(customReminder, "null cannot be cast to non-null type com.coveiot.android.customreminders.model.DrinkWaterReminder");
                DrinkWaterReminder drinkWaterReminder = (DrinkWaterReminder) customReminder;
                HandWashReminderViewModel handWashReminderViewModel = this.l;
                if (handWashReminderViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    handWashReminderViewModel = null;
                }
                handWashReminderViewModel.setStartTime(drinkWaterReminder.getStartTime().getHour(), drinkWaterReminder.getStartTime().getMinute(), false);
                if (drinkWaterReminder.getEndTime() != null) {
                    HandWashReminderViewModel handWashReminderViewModel2 = this.l;
                    if (handWashReminderViewModel2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                        handWashReminderViewModel2 = null;
                    }
                    handWashReminderViewModel2.setEndTime(drinkWaterReminder.getEndTime().getHour(), drinkWaterReminder.getEndTime().getMinute(), false);
                }
                HandWashReminderViewModel handWashReminderViewModel3 = this.l;
                if (handWashReminderViewModel3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    handWashReminderViewModel3 = null;
                }
                handWashReminderViewModel3.setReminderFrequency(drinkWaterReminder.getFrequency(), false);
            } else if (reminderType == ReminderType.HAND_WASH) {
                Intrinsics.checkNotNull(customReminder, "null cannot be cast to non-null type com.coveiot.android.customreminders.model.HandWashReminder");
                HandWashReminder handWashReminder = (HandWashReminder) customReminder;
                HandWashReminderViewModel handWashReminderViewModel4 = this.l;
                if (handWashReminderViewModel4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    handWashReminderViewModel4 = null;
                }
                handWashReminderViewModel4.setStartTime(handWashReminder.getStartTime().getHour(), handWashReminder.getStartTime().getMinute(), false);
                if (handWashReminder.getEndTime() != null) {
                    HandWashReminderViewModel handWashReminderViewModel5 = this.l;
                    if (handWashReminderViewModel5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                        handWashReminderViewModel5 = null;
                    }
                    handWashReminderViewModel5.setEndTime(handWashReminder.getEndTime().getHour(), handWashReminder.getEndTime().getMinute(), false);
                }
                HandWashReminderViewModel handWashReminderViewModel6 = this.l;
                if (handWashReminderViewModel6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    handWashReminderViewModel6 = null;
                }
                handWashReminderViewModel6.setReminderFrequency(handWashReminder.getFrequency(), false);
            }
            HandWashReminderViewModel handWashReminderViewModel7 = this.l;
            if (handWashReminderViewModel7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                handWashReminderViewModel7 = null;
            }
            CustomReminder customReminder2 = this.i;
            RepeatModel repeatModel = customReminder2 != null ? customReminder2.getRepeatModel() : null;
            Intrinsics.checkNotNull(repeatModel);
            handWashReminderViewModel7.setRepeat(repeatModel, false);
            ((Button) _$_findCachedViewById(R.id.saveButton)).setText(R.string.save);
            return;
        }
        HandWashReminderViewModel handWashReminderViewModel8 = this.l;
        if (handWashReminderViewModel8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            handWashReminderViewModel8 = null;
        }
        HandWashReminderViewModel.setReminderFrequency$default(handWashReminderViewModel8, 60, false, 2, null);
    }

    public final void o() {
        TextView textView = getBinding().repeatLayout.monday;
        int i = R.drawable.repeat_days_unselected_bg;
        textView.setBackgroundResource(i);
        getBinding().repeatLayout.tuesday.setBackgroundResource(i);
        getBinding().repeatLayout.wednesday.setBackgroundResource(i);
        getBinding().repeatLayout.thursday.setBackgroundResource(i);
        getBinding().repeatLayout.friday.setBackgroundResource(i);
        getBinding().repeatLayout.saturday.setBackgroundResource(i);
        getBinding().repeatLayout.sunday.setBackgroundResource(i);
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
        if (activity instanceof MedicineReminderFragmentNew.SaveButtonListener) {
            this.o = (MedicineReminderFragmentNew.SaveButtonListener) activity;
        }
    }

    public final void onBackPressed() {
        if (((Button) _$_findCachedViewById(R.id.saveButton)).isEnabled()) {
            p();
            return;
        }
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.finish();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(@Nullable View view) {
        View view2 = getView();
        Integer valueOf = view2 != null ? Integer.valueOf(view2.getId()) : null;
        int i = R.id.sunday;
        if (valueOf != null && valueOf.intValue() == i) {
            return;
        }
        int i2 = R.id.monday;
        if (valueOf != null && valueOf.intValue() == i2) {
            HandWashReminderViewModel handWashReminderViewModel = this.l;
            if (handWashReminderViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                handWashReminderViewModel = null;
            }
            RepeatModel repeatModel = handWashReminderViewModel.getRepeatModel();
            repeatModel.monday = !repeatModel.monday;
            HandWashReminderViewModel handWashReminderViewModel2 = this.l;
            if (handWashReminderViewModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                handWashReminderViewModel2 = null;
            }
            HandWashReminderViewModel.setRepeat$default(handWashReminderViewModel2, repeatModel, false, 2, null);
            return;
        }
        int i3 = R.id.tuesday;
        if (valueOf != null && valueOf.intValue() == i3) {
            HandWashReminderViewModel handWashReminderViewModel3 = this.l;
            if (handWashReminderViewModel3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                handWashReminderViewModel3 = null;
            }
            RepeatModel repeatModel2 = handWashReminderViewModel3.getRepeatModel();
            repeatModel2.tuesday = !repeatModel2.tuesday;
            HandWashReminderViewModel handWashReminderViewModel4 = this.l;
            if (handWashReminderViewModel4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                handWashReminderViewModel4 = null;
            }
            HandWashReminderViewModel.setRepeat$default(handWashReminderViewModel4, repeatModel2, false, 2, null);
            return;
        }
        int i4 = R.id.wednesday;
        if (valueOf != null && valueOf.intValue() == i4) {
            HandWashReminderViewModel handWashReminderViewModel5 = this.l;
            if (handWashReminderViewModel5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                handWashReminderViewModel5 = null;
            }
            RepeatModel repeatModel3 = handWashReminderViewModel5.getRepeatModel();
            repeatModel3.wednesday = !repeatModel3.wednesday;
            HandWashReminderViewModel handWashReminderViewModel6 = this.l;
            if (handWashReminderViewModel6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                handWashReminderViewModel6 = null;
            }
            HandWashReminderViewModel.setRepeat$default(handWashReminderViewModel6, repeatModel3, false, 2, null);
            return;
        }
        int i5 = R.id.thursday;
        if (valueOf != null && valueOf.intValue() == i5) {
            HandWashReminderViewModel handWashReminderViewModel7 = this.l;
            if (handWashReminderViewModel7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                handWashReminderViewModel7 = null;
            }
            RepeatModel repeatModel4 = handWashReminderViewModel7.getRepeatModel();
            repeatModel4.thursday = !repeatModel4.thursday;
            HandWashReminderViewModel handWashReminderViewModel8 = this.l;
            if (handWashReminderViewModel8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                handWashReminderViewModel8 = null;
            }
            HandWashReminderViewModel.setRepeat$default(handWashReminderViewModel8, repeatModel4, false, 2, null);
            return;
        }
        int i6 = R.id.friday;
        if (valueOf != null && valueOf.intValue() == i6) {
            HandWashReminderViewModel handWashReminderViewModel9 = this.l;
            if (handWashReminderViewModel9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                handWashReminderViewModel9 = null;
            }
            RepeatModel repeatModel5 = handWashReminderViewModel9.getRepeatModel();
            repeatModel5.friday = !repeatModel5.friday;
            HandWashReminderViewModel handWashReminderViewModel10 = this.l;
            if (handWashReminderViewModel10 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                handWashReminderViewModel10 = null;
            }
            HandWashReminderViewModel.setRepeat$default(handWashReminderViewModel10, repeatModel5, false, 2, null);
            return;
        }
        int i7 = R.id.saturday;
        if (valueOf != null && valueOf.intValue() == i7) {
            HandWashReminderViewModel handWashReminderViewModel11 = this.l;
            if (handWashReminderViewModel11 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                handWashReminderViewModel11 = null;
            }
            RepeatModel repeatModel6 = handWashReminderViewModel11.getRepeatModel();
            repeatModel6.saturday = !repeatModel6.saturday;
            HandWashReminderViewModel handWashReminderViewModel12 = this.l;
            if (handWashReminderViewModel12 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                handWashReminderViewModel12 = null;
            }
            HandWashReminderViewModel.setRepeat$default(handWashReminderViewModel12, repeatModel6, false, 2, null);
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
        FragmentHandWashNewBinding inflate = FragmentHandWashNewBinding.inflate(getLayoutInflater(), viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater, container, false)");
        setBinding(inflate);
        return getBinding().getRoot();
    }

    @Override // androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.coveiot.android.customreminders.adapter.IntervalAdapterReminderNew.IntervalSelectionListener
    public void onIntervalSelected(int i) {
        HandWashReminderViewModel handWashReminderViewModel = this.l;
        if (handWashReminderViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            handWashReminderViewModel = null;
        }
        HandWashReminderViewModel.setReminderFrequency$default(handWashReminderViewModel, i, false, 2, null);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        ViewModel viewModel = new ViewModelProvider(this, new ViewModelFactory(requireContext)).get(HandWashReminderViewModel.class);
        Intrinsics.checkNotNullExpressionValue(viewModel, "ViewModelProvider(\n     …derViewModel::class.java)");
        HandWashReminderViewModel handWashReminderViewModel = (HandWashReminderViewModel) viewModel;
        this.l = handWashReminderViewModel;
        if (handWashReminderViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            handWashReminderViewModel = null;
        }
        ReminderType reminderType = this.h;
        Intrinsics.checkNotNull(reminderType);
        handWashReminderViewModel.setReminderType(reminderType);
        m();
        initObservers();
        initClickListeners();
        n();
    }

    public final void p() {
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
            bottomSheetDialogTwoButtons.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.customreminders.fragments.HandWashFragmentNew$showSaveUnSavedChanges$1
                @Override // android.view.View.OnClickListener
                public void onClick(@Nullable View view) {
                    ((Button) HandWashFragmentNew.this._$_findCachedViewById(R.id.saveButton)).performClick();
                    BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons2 = HandWashFragmentNew.this.getBottomSheetDialogTwoButtons();
                    Intrinsics.checkNotNull(bottomSheetDialogTwoButtons2);
                    bottomSheetDialogTwoButtons2.dismiss();
                }
            });
            BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons2 = this.q;
            Intrinsics.checkNotNull(bottomSheetDialogTwoButtons2);
            String string4 = getString(R.string.cancel);
            Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.cancel)");
            bottomSheetDialogTwoButtons2.setNegativeButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.customreminders.fragments.HandWashFragmentNew$showSaveUnSavedChanges$2
                @Override // android.view.View.OnClickListener
                public void onClick(@Nullable View view) {
                    BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons3 = HandWashFragmentNew.this.getBottomSheetDialogTwoButtons();
                    Intrinsics.checkNotNull(bottomSheetDialogTwoButtons3);
                    bottomSheetDialogTwoButtons3.dismiss();
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

    public final void setBinding(@NotNull FragmentHandWashNewBinding fragmentHandWashNewBinding) {
        Intrinsics.checkNotNullParameter(fragmentHandWashNewBinding, "<set-?>");
        this.binding = fragmentHandWashNewBinding;
    }

    public final void setBottomSheetDialogTwoButtons(@Nullable BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons) {
        this.q = bottomSheetDialogTwoButtons;
    }

    public final void setIntervalAdapterReminderNew(@Nullable IntervalAdapterReminderNew intervalAdapterReminderNew) {
        this.p = intervalAdapterReminderNew;
    }

    public final void setRepeatFlag(boolean z) {
        this.k = z;
    }
}
