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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.coveiot.android.customreminders.R;
import com.coveiot.android.customreminders.ReminderType;
import com.coveiot.android.customreminders.listeners.AddReminderListener;
import com.coveiot.android.customreminders.listeners.PostEditReminderListListener;
import com.coveiot.android.customreminders.listeners.ReminderListener;
import com.coveiot.android.customreminders.model.CustomReminder;
import com.coveiot.android.customreminders.model.MedicineReminder;
import com.coveiot.android.customreminders.model.MeetingReminder;
import com.coveiot.android.customreminders.model.OtherReminder;
import com.coveiot.android.customreminders.model.RepeatModel;
import com.coveiot.android.customreminders.utils.ViewModelFactory;
import com.coveiot.android.customreminders.viewmodel.MedicineReminderViewModel;
import com.coveiot.android.theme.BottomSheetDialogTwoButtons;
import com.coveiot.android.theme.PickerDialog;
import com.coveiot.utils.utility.AppUtils;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.jstyle.blesdk1860.constant.DeviceKey;
import java.io.Serializable;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Pair;
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
public final class MedicineReminderFragment extends Fragment {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public ReminderType h;
    @Nullable
    public CustomReminder i;
    public MedicineReminderViewModel l;
    public AddReminderListener m;
    public PostEditReminderListListener n;
    @Nullable
    public BottomSheetDialogTwoButtons o;
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

        public static /* synthetic */ MedicineReminderFragment newInstance$default(Companion companion, ReminderType reminderType, CustomReminder customReminder, int i, int i2, Object obj) {
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
        public final MedicineReminderFragment newInstance(@NotNull ReminderType param1, @Nullable CustomReminder customReminder, int i) {
            Intrinsics.checkNotNullParameter(param1, "param1");
            MedicineReminderFragment medicineReminderFragment = new MedicineReminderFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable("type", param1);
            bundle.putSerializable("reminder_object", customReminder);
            bundle.putSerializable(DeviceKey.position, Integer.valueOf(i));
            medicineReminderFragment.setArguments(bundle);
            return medicineReminderFragment;
        }
    }

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
                iArr[ReminderType.MEETING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[ReminderType.OTHERS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @DebugMetadata(c = "com.coveiot.android.customreminders.fragments.MedicineReminderFragment$populateData$1", f = "MedicineReminderFragment.kt", i = {0}, l = {105}, m = "invokeSuspend", n = {"i"}, s = {"I$0"})
    /* loaded from: classes3.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ MedicineReminder $medicineReminder;
        public int I$0;
        public int I$1;
        public int label;
        public final /* synthetic */ MedicineReminderFragment this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(MedicineReminder medicineReminder, MedicineReminderFragment medicineReminderFragment, Continuation<? super a> continuation) {
            super(2, continuation);
            this.$medicineReminder = medicineReminder;
            this.this$0 = medicineReminderFragment;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new a(this.$medicineReminder, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:29:0x002f  */
        /* JADX WARN: Removed duplicated region for block: B:36:0x0065  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:33:0x0060 -> B:35:0x0063). Please submit an issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r9) {
            /*
                r8 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()
                int r1 = r8.label
                r2 = 0
                r3 = 1
                if (r1 == 0) goto L1d
                if (r1 != r3) goto L15
                int r1 = r8.I$1
                int r4 = r8.I$0
                kotlin.ResultKt.throwOnFailure(r9)
                r9 = r8
                goto L63
            L15:
                java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r9.<init>(r0)
                throw r9
            L1d:
                kotlin.ResultKt.throwOnFailure(r9)
                com.coveiot.android.customreminders.model.MedicineReminder r9 = r8.$medicineReminder
                java.util.ArrayList r9 = r9.getTimeInfos()
                int r9 = r9.size()
                r1 = r9
                r4 = r2
                r9 = r8
            L2d:
                if (r4 >= r1) goto L65
                com.coveiot.android.customreminders.model.MedicineReminder r5 = r9.$medicineReminder
                java.util.ArrayList r5 = r5.getTimeInfos()
                java.lang.Object r5 = r5.get(r4)
                com.coveiot.sdk.ble.api.model.TimeInfo r5 = (com.coveiot.sdk.ble.api.model.TimeInfo) r5
                com.coveiot.android.customreminders.fragments.MedicineReminderFragment r6 = r9.this$0
                com.coveiot.android.customreminders.viewmodel.MedicineReminderViewModel r6 = com.coveiot.android.customreminders.fragments.MedicineReminderFragment.access$getViewModel$p(r6)
                if (r6 != 0) goto L49
                java.lang.String r6 = "viewModel"
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r6)
                r6 = 0
            L49:
                int r7 = r5.getHour()
                int r5 = r5.getMinute()
                r6.setTime(r7, r5, r4, r2)
                r5 = 100
                r9.I$0 = r4
                r9.I$1 = r1
                r9.label = r3
                java.lang.Object r5 = kotlinx.coroutines.DelayKt.delay(r5, r9)
                if (r5 != r0) goto L63
                return r0
            L63:
                int r4 = r4 + r3
                goto L2d
            L65:
                kotlin.Unit r9 = kotlin.Unit.INSTANCE
                return r9
            */
            throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.customreminders.fragments.MedicineReminderFragment.a.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.customreminders.fragments.MedicineReminderFragment$populateData$2", f = "MedicineReminderFragment.kt", i = {0}, l = {123}, m = "invokeSuspend", n = {"i"}, s = {"I$0"})
    /* loaded from: classes3.dex */
    public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ MeetingReminder $meetingReminder;
        public int I$0;
        public int I$1;
        public int label;
        public final /* synthetic */ MedicineReminderFragment this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(MeetingReminder meetingReminder, MedicineReminderFragment medicineReminderFragment, Continuation<? super b> continuation) {
            super(2, continuation);
            this.$meetingReminder = meetingReminder;
            this.this$0 = medicineReminderFragment;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new b(this.$meetingReminder, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((b) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:29:0x002f  */
        /* JADX WARN: Removed duplicated region for block: B:36:0x0065  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:33:0x0060 -> B:35:0x0063). Please submit an issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r9) {
            /*
                r8 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()
                int r1 = r8.label
                r2 = 0
                r3 = 1
                if (r1 == 0) goto L1d
                if (r1 != r3) goto L15
                int r1 = r8.I$1
                int r4 = r8.I$0
                kotlin.ResultKt.throwOnFailure(r9)
                r9 = r8
                goto L63
            L15:
                java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r9.<init>(r0)
                throw r9
            L1d:
                kotlin.ResultKt.throwOnFailure(r9)
                com.coveiot.android.customreminders.model.MeetingReminder r9 = r8.$meetingReminder
                java.util.ArrayList r9 = r9.getTimeInfos()
                int r9 = r9.size()
                r1 = r9
                r4 = r2
                r9 = r8
            L2d:
                if (r4 >= r1) goto L65
                com.coveiot.android.customreminders.model.MeetingReminder r5 = r9.$meetingReminder
                java.util.ArrayList r5 = r5.getTimeInfos()
                java.lang.Object r5 = r5.get(r4)
                com.coveiot.sdk.ble.api.model.TimeInfo r5 = (com.coveiot.sdk.ble.api.model.TimeInfo) r5
                com.coveiot.android.customreminders.fragments.MedicineReminderFragment r6 = r9.this$0
                com.coveiot.android.customreminders.viewmodel.MedicineReminderViewModel r6 = com.coveiot.android.customreminders.fragments.MedicineReminderFragment.access$getViewModel$p(r6)
                if (r6 != 0) goto L49
                java.lang.String r6 = "viewModel"
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r6)
                r6 = 0
            L49:
                int r7 = r5.getHour()
                int r5 = r5.getMinute()
                r6.setTime(r7, r5, r4, r2)
                r5 = 100
                r9.I$0 = r4
                r9.I$1 = r1
                r9.label = r3
                java.lang.Object r5 = kotlinx.coroutines.DelayKt.delay(r5, r9)
                if (r5 != r0) goto L63
                return r0
            L63:
                int r4 = r4 + r3
                goto L2d
            L65:
                kotlin.Unit r9 = kotlin.Unit.INSTANCE
                return r9
            */
            throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.customreminders.fragments.MedicineReminderFragment.b.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.customreminders.fragments.MedicineReminderFragment$populateData$3", f = "MedicineReminderFragment.kt", i = {0}, l = {141}, m = "invokeSuspend", n = {"i"}, s = {"I$0"})
    /* loaded from: classes3.dex */
    public static final class c extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ OtherReminder $otherReminder;
        public int I$0;
        public int I$1;
        public int label;
        public final /* synthetic */ MedicineReminderFragment this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public c(OtherReminder otherReminder, MedicineReminderFragment medicineReminderFragment, Continuation<? super c> continuation) {
            super(2, continuation);
            this.$otherReminder = otherReminder;
            this.this$0 = medicineReminderFragment;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new c(this.$otherReminder, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((c) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:29:0x002f  */
        /* JADX WARN: Removed duplicated region for block: B:36:0x0065  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:33:0x0060 -> B:35:0x0063). Please submit an issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r9) {
            /*
                r8 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()
                int r1 = r8.label
                r2 = 0
                r3 = 1
                if (r1 == 0) goto L1d
                if (r1 != r3) goto L15
                int r1 = r8.I$1
                int r4 = r8.I$0
                kotlin.ResultKt.throwOnFailure(r9)
                r9 = r8
                goto L63
            L15:
                java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r9.<init>(r0)
                throw r9
            L1d:
                kotlin.ResultKt.throwOnFailure(r9)
                com.coveiot.android.customreminders.model.OtherReminder r9 = r8.$otherReminder
                java.util.ArrayList r9 = r9.getTimeInfos()
                int r9 = r9.size()
                r1 = r9
                r4 = r2
                r9 = r8
            L2d:
                if (r4 >= r1) goto L65
                com.coveiot.android.customreminders.model.OtherReminder r5 = r9.$otherReminder
                java.util.ArrayList r5 = r5.getTimeInfos()
                java.lang.Object r5 = r5.get(r4)
                com.coveiot.sdk.ble.api.model.TimeInfo r5 = (com.coveiot.sdk.ble.api.model.TimeInfo) r5
                com.coveiot.android.customreminders.fragments.MedicineReminderFragment r6 = r9.this$0
                com.coveiot.android.customreminders.viewmodel.MedicineReminderViewModel r6 = com.coveiot.android.customreminders.fragments.MedicineReminderFragment.access$getViewModel$p(r6)
                if (r6 != 0) goto L49
                java.lang.String r6 = "viewModel"
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r6)
                r6 = 0
            L49:
                int r7 = r5.getHour()
                int r5 = r5.getMinute()
                r6.setTime(r7, r5, r4, r2)
                r5 = 100
                r9.I$0 = r4
                r9.I$1 = r1
                r9.label = r3
                java.lang.Object r5 = kotlinx.coroutines.DelayKt.delay(r5, r9)
                if (r5 != r0) goto L63
                return r0
            L63:
                int r4 = r4 + r3
                goto L2d
            L65:
                kotlin.Unit r9 = kotlin.Unit.INSTANCE
                return r9
            */
            throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.customreminders.fragments.MedicineReminderFragment.c.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public static final void A(MedicineReminderFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        MedicineReminderViewModel medicineReminderViewModel = this$0.l;
        if (medicineReminderViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            medicineReminderViewModel = null;
        }
        MedicineReminderViewModel.removeTimeAt$default(medicineReminderViewModel, 1, false, 2, null);
    }

    public static final void B(MedicineReminderFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        MedicineReminderViewModel medicineReminderViewModel = this$0.l;
        if (medicineReminderViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            medicineReminderViewModel = null;
        }
        MedicineReminderViewModel.removeTimeAt$default(medicineReminderViewModel, 2, false, 2, null);
    }

    public static final void C(MedicineReminderFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        PickerDialog.Companion companion = PickerDialog.Companion;
        Context requireContext = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        companion.remindInPicker(requireContext, 5, "", new PickerDialog.Companion.SelectionListener() { // from class: com.coveiot.android.customreminders.fragments.MedicineReminderFragment$initClickListeners$10$1
            @Override // com.coveiot.android.theme.PickerDialog.Companion.SelectionListener
            public void onValueSelected(@NotNull String value) {
                Intrinsics.checkNotNullParameter(value, "value");
                MedicineReminderViewModel medicineReminderViewModel = MedicineReminderFragment.this.l;
                if (medicineReminderViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    medicineReminderViewModel = null;
                }
                MedicineReminderViewModel.setReminderInAdvance$default(medicineReminderViewModel, Integer.parseInt(value), false, 2, null);
            }
        });
    }

    public static final void D(MedicineReminderFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.k = !this$0.k;
        MedicineReminderViewModel medicineReminderViewModel = this$0.l;
        if (medicineReminderViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            medicineReminderViewModel = null;
        }
        MedicineReminderViewModel.setRepeat$default(medicineReminderViewModel, new RepeatModel(this$0.k), false, 2, null);
    }

    public static final void E(MedicineReminderFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        MedicineReminderViewModel medicineReminderViewModel = this$0.l;
        if (medicineReminderViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            medicineReminderViewModel = null;
        }
        RepeatModel repeatModel = medicineReminderViewModel.getRepeatModel();
        repeatModel.monday = !repeatModel.monday;
        MedicineReminderViewModel medicineReminderViewModel2 = this$0.l;
        if (medicineReminderViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            medicineReminderViewModel2 = null;
        }
        MedicineReminderViewModel.setRepeat$default(medicineReminderViewModel2, repeatModel, false, 2, null);
    }

    public static final void F(MedicineReminderFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        MedicineReminderViewModel medicineReminderViewModel = this$0.l;
        if (medicineReminderViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            medicineReminderViewModel = null;
        }
        RepeatModel repeatModel = medicineReminderViewModel.getRepeatModel();
        repeatModel.tuesday = !repeatModel.tuesday;
        MedicineReminderViewModel medicineReminderViewModel2 = this$0.l;
        if (medicineReminderViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            medicineReminderViewModel2 = null;
        }
        MedicineReminderViewModel.setRepeat$default(medicineReminderViewModel2, repeatModel, false, 2, null);
    }

    public static final void G(MedicineReminderFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        MedicineReminderViewModel medicineReminderViewModel = this$0.l;
        if (medicineReminderViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            medicineReminderViewModel = null;
        }
        RepeatModel repeatModel = medicineReminderViewModel.getRepeatModel();
        repeatModel.wednesday = !repeatModel.wednesday;
        MedicineReminderViewModel medicineReminderViewModel2 = this$0.l;
        if (medicineReminderViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            medicineReminderViewModel2 = null;
        }
        MedicineReminderViewModel.setRepeat$default(medicineReminderViewModel2, repeatModel, false, 2, null);
    }

    public static final void H(MedicineReminderFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        MedicineReminderViewModel medicineReminderViewModel = this$0.l;
        if (medicineReminderViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            medicineReminderViewModel = null;
        }
        RepeatModel repeatModel = medicineReminderViewModel.getRepeatModel();
        repeatModel.thursday = !repeatModel.thursday;
        MedicineReminderViewModel medicineReminderViewModel2 = this$0.l;
        if (medicineReminderViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            medicineReminderViewModel2 = null;
        }
        MedicineReminderViewModel.setRepeat$default(medicineReminderViewModel2, repeatModel, false, 2, null);
    }

    public static final void I(MedicineReminderFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Calendar calendar = Calendar.getInstance();
        new DatePickerDialog(this$0.requireActivity(), R.style.DialogThemeDarWindowBG, new DatePickerDialog.OnDateSetListener() { // from class: com.coveiot.android.customreminders.fragments.h
            @Override // android.app.DatePickerDialog.OnDateSetListener
            public final void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
                MedicineReminderFragment.J(MedicineReminderFragment.this, datePicker, i, i2, i3);
            }
        }, calendar.get(1), calendar.get(2), calendar.get(5)).show();
    }

    public static final void J(MedicineReminderFragment this$0, DatePicker datePicker, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Calendar startDate = Calendar.getInstance();
        startDate.set(i, i2, i3);
        MedicineReminderViewModel medicineReminderViewModel = this$0.l;
        if (medicineReminderViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            medicineReminderViewModel = null;
        }
        Intrinsics.checkNotNullExpressionValue(startDate, "startDate");
        MedicineReminderViewModel.setStartDate$default(medicineReminderViewModel, startDate, false, 2, null);
    }

    public static final void K(MedicineReminderFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        MedicineReminderViewModel medicineReminderViewModel = this$0.l;
        if (medicineReminderViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            medicineReminderViewModel = null;
        }
        RepeatModel repeatModel = medicineReminderViewModel.getRepeatModel();
        repeatModel.friday = !repeatModel.friday;
        MedicineReminderViewModel medicineReminderViewModel2 = this$0.l;
        if (medicineReminderViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            medicineReminderViewModel2 = null;
        }
        MedicineReminderViewModel.setRepeat$default(medicineReminderViewModel2, repeatModel, false, 2, null);
    }

    public static final void L(MedicineReminderFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        MedicineReminderViewModel medicineReminderViewModel = this$0.l;
        if (medicineReminderViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            medicineReminderViewModel = null;
        }
        RepeatModel repeatModel = medicineReminderViewModel.getRepeatModel();
        repeatModel.saturday = !repeatModel.saturday;
        MedicineReminderViewModel medicineReminderViewModel2 = this$0.l;
        if (medicineReminderViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            medicineReminderViewModel2 = null;
        }
        MedicineReminderViewModel.setRepeat$default(medicineReminderViewModel2, repeatModel, false, 2, null);
    }

    public static final void M(MedicineReminderFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        MedicineReminderViewModel medicineReminderViewModel = this$0.l;
        if (medicineReminderViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            medicineReminderViewModel = null;
        }
        RepeatModel repeatModel = medicineReminderViewModel.getRepeatModel();
        repeatModel.sunday = !repeatModel.sunday;
        MedicineReminderViewModel medicineReminderViewModel2 = this$0.l;
        if (medicineReminderViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            medicineReminderViewModel2 = null;
        }
        MedicineReminderViewModel.setRepeat$default(medicineReminderViewModel2, repeatModel, false, 2, null);
    }

    public static final void N(MedicineReminderFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        MedicineReminderViewModel medicineReminderViewModel = this$0.l;
        if (medicineReminderViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            medicineReminderViewModel = null;
        }
        medicineReminderViewModel.save(new ReminderListener() { // from class: com.coveiot.android.customreminders.fragments.MedicineReminderFragment$initClickListeners$19$1

            @DebugMetadata(c = "com.coveiot.android.customreminders.fragments.MedicineReminderFragment$initClickListeners$19$1$onError$1", f = "MedicineReminderFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* loaded from: classes3.dex */
            public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public final /* synthetic */ String $error;
                public int label;
                public final /* synthetic */ MedicineReminderFragment this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public a(MedicineReminderFragment medicineReminderFragment, String str, Continuation<? super a> continuation) {
                    super(2, continuation);
                    this.this$0 = medicineReminderFragment;
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
                kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new a(MedicineReminderFragment.this, error, null), 2, null);
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
                customReminder2 = MedicineReminderFragment.this.i;
                ReminderType reminderType3 = null;
                if (customReminder2 == null) {
                    addReminderListener = MedicineReminderFragment.this.m;
                    if (addReminderListener != null) {
                        addReminderListener2 = MedicineReminderFragment.this.m;
                        if (addReminderListener2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException(ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
                            addReminderListener2 = null;
                        }
                        reminderType2 = MedicineReminderFragment.this.h;
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
                postEditReminderListListener = MedicineReminderFragment.this.n;
                if (postEditReminderListListener != null) {
                    postEditReminderListListener2 = MedicineReminderFragment.this.n;
                    if (postEditReminderListListener2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("editListener");
                        postEditReminderListListener2 = null;
                    }
                    i = MedicineReminderFragment.this.j;
                    reminderType = MedicineReminderFragment.this.h;
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

    public static final void O(MedicineReminderFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Calendar calendar = Calendar.getInstance();
        new DatePickerDialog(this$0.requireActivity(), R.style.DialogThemeDarWindowBG, new DatePickerDialog.OnDateSetListener() { // from class: com.coveiot.android.customreminders.fragments.s
            @Override // android.app.DatePickerDialog.OnDateSetListener
            public final void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
                MedicineReminderFragment.P(MedicineReminderFragment.this, datePicker, i, i2, i3);
            }
        }, calendar.get(1), calendar.get(2), calendar.get(5)).show();
    }

    public static final void P(MedicineReminderFragment this$0, DatePicker datePicker, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Calendar startDate = Calendar.getInstance();
        startDate.set(i, i2, i3);
        MedicineReminderViewModel medicineReminderViewModel = this$0.l;
        if (medicineReminderViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            medicineReminderViewModel = null;
        }
        Intrinsics.checkNotNullExpressionValue(startDate, "startDate");
        MedicineReminderViewModel.setEndDate$default(medicineReminderViewModel, startDate, false, 2, null);
    }

    public static final void Q(MedicineReminderFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        new TimePickerDialog(this$0.requireContext(), R.style.DialogThemeDarWindowBG, new TimePickerDialog.OnTimeSetListener() { // from class: com.coveiot.android.customreminders.fragments.x
            @Override // android.app.TimePickerDialog.OnTimeSetListener
            public final void onTimeSet(TimePicker timePicker, int i, int i2) {
                MedicineReminderFragment.R(MedicineReminderFragment.this, timePicker, i, i2);
            }
        }, Calendar.getInstance().get(11), Calendar.getInstance().get(12), DateFormat.is24HourFormat(this$0.requireContext())).show();
    }

    public static final void R(MedicineReminderFragment this$0, TimePicker timePicker, int i, int i2) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        MedicineReminderViewModel medicineReminderViewModel = this$0.l;
        if (medicineReminderViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            medicineReminderViewModel = null;
        }
        MedicineReminderViewModel.setTime$default(medicineReminderViewModel, i, i2, 0, false, 8, null);
    }

    public static final void S(MedicineReminderFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        new TimePickerDialog(this$0.requireContext(), R.style.DialogThemeDarWindowBG, new TimePickerDialog.OnTimeSetListener() { // from class: com.coveiot.android.customreminders.fragments.y
            @Override // android.app.TimePickerDialog.OnTimeSetListener
            public final void onTimeSet(TimePicker timePicker, int i, int i2) {
                MedicineReminderFragment.T(MedicineReminderFragment.this, timePicker, i, i2);
            }
        }, Calendar.getInstance().get(11), Calendar.getInstance().get(12), DateFormat.is24HourFormat(this$0.requireContext())).show();
    }

    public static final void T(MedicineReminderFragment this$0, TimePicker timePicker, int i, int i2) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        MedicineReminderViewModel medicineReminderViewModel = this$0.l;
        if (medicineReminderViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            medicineReminderViewModel = null;
        }
        MedicineReminderViewModel.setTime$default(medicineReminderViewModel, i, i2, 1, false, 8, null);
    }

    @JvmStatic
    @NotNull
    public static final MedicineReminderFragment newInstance(@NotNull ReminderType reminderType, @Nullable CustomReminder customReminder, int i) {
        return Companion.newInstance(reminderType, customReminder, i);
    }

    public static final void x(MedicineReminderFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        new TimePickerDialog(this$0.requireContext(), R.style.DialogThemeDarWindowBG, new TimePickerDialog.OnTimeSetListener() { // from class: com.coveiot.android.customreminders.fragments.w
            @Override // android.app.TimePickerDialog.OnTimeSetListener
            public final void onTimeSet(TimePicker timePicker, int i, int i2) {
                MedicineReminderFragment.y(MedicineReminderFragment.this, timePicker, i, i2);
            }
        }, Calendar.getInstance().get(11), Calendar.getInstance().get(12), DateFormat.is24HourFormat(this$0.requireContext())).show();
    }

    public static final void y(MedicineReminderFragment this$0, TimePicker timePicker, int i, int i2) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        MedicineReminderViewModel medicineReminderViewModel = this$0.l;
        if (medicineReminderViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            medicineReminderViewModel = null;
        }
        MedicineReminderViewModel.setTime$default(medicineReminderViewModel, i, i2, 2, false, 8, null);
    }

    public static final void z(MedicineReminderFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        MedicineReminderViewModel medicineReminderViewModel = this$0.l;
        if (medicineReminderViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            medicineReminderViewModel = null;
        }
        MedicineReminderViewModel.removeTimeAt$default(medicineReminderViewModel, 0, false, 2, null);
    }

    public final void U() {
        ReminderType reminderType = this.h;
        ReminderType reminderType2 = null;
        if (reminderType == null) {
            Intrinsics.throwUninitializedPropertyAccessException("reminderType");
            reminderType = null;
        }
        if (reminderType != ReminderType.MEDICINE) {
            int i = R.id.time2;
            ((EditText) _$_findCachedViewById(i)).setVisibility(4);
            int i2 = R.id.time3;
            ((EditText) _$_findCachedViewById(i2)).setVisibility(4);
            ((EditText) _$_findCachedViewById(i)).setEnabled(false);
            ((EditText) _$_findCachedViewById(i2)).setEnabled(false);
        }
        ReminderType reminderType3 = this.h;
        if (reminderType3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("reminderType");
        } else {
            reminderType2 = reminderType3;
        }
        int i3 = WhenMappings.$EnumSwitchMapping$0[reminderType2.ordinal()];
        if (i3 == 1) {
            ((TextView) _$_findCachedViewById(R.id.reminder_title)).setText(getString(R.string.medicine_name));
            ((ImageView) _$_findCachedViewById(R.id.reminder_icon)).setImageResource(R.drawable.medicine_icon);
        } else if (i3 == 2) {
            ((TextView) _$_findCachedViewById(R.id.reminder_title)).setText(getString(R.string.meeting_name));
            ((ImageView) _$_findCachedViewById(R.id.reminder_icon)).setImageResource(R.drawable.meeting_icon);
        } else if (i3 == 3) {
            ((TextView) _$_findCachedViewById(R.id.reminder_title)).setText(getString(R.string.name));
            ((ImageView) _$_findCachedViewById(R.id.reminder_icon)).setImageResource(R.drawable.other_icon);
        }
        Y();
    }

    public final void V() {
        if (this.i != null) {
            EditText editText = (EditText) _$_findCachedViewById(R.id.edt_reminder_name);
            CustomReminder customReminder = this.i;
            String description = customReminder != null ? customReminder.getDescription() : null;
            Intrinsics.checkNotNull(description);
            editText.setText(description);
            MedicineReminderViewModel medicineReminderViewModel = this.l;
            if (medicineReminderViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                medicineReminderViewModel = null;
            }
            CustomReminder customReminder2 = this.i;
            medicineReminderViewModel.setReminderName(customReminder2 != null ? customReminder2.getDescription() : null, false);
            ReminderType reminderType = this.h;
            if (reminderType == null) {
                Intrinsics.throwUninitializedPropertyAccessException("reminderType");
                reminderType = null;
            }
            if (reminderType == ReminderType.MEDICINE) {
                CustomReminder customReminder3 = this.i;
                Intrinsics.checkNotNull(customReminder3, "null cannot be cast to non-null type com.coveiot.android.customreminders.model.MedicineReminder");
                MedicineReminder medicineReminder = (MedicineReminder) customReminder3;
                MedicineReminderViewModel medicineReminderViewModel2 = this.l;
                if (medicineReminderViewModel2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    medicineReminderViewModel2 = null;
                }
                Calendar startDate = medicineReminder.getStartDate();
                Intrinsics.checkNotNullExpressionValue(startDate, "medicineReminder.startDate");
                medicineReminderViewModel2.setStartDate(startDate, false);
                if (medicineReminder.getEndDate() != null) {
                    MedicineReminderViewModel medicineReminderViewModel3 = this.l;
                    if (medicineReminderViewModel3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                        medicineReminderViewModel3 = null;
                    }
                    Calendar endDate = medicineReminder.getEndDate();
                    Intrinsics.checkNotNullExpressionValue(endDate, "medicineReminder.endDate");
                    medicineReminderViewModel3.setEndDate(endDate, false);
                }
                kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new a(medicineReminder, this, null), 2, null);
                MedicineReminderViewModel medicineReminderViewModel4 = this.l;
                if (medicineReminderViewModel4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    medicineReminderViewModel4 = null;
                }
                medicineReminderViewModel4.setReminderInAdvance(medicineReminder.getAdvanceTime(), false);
            } else {
                ReminderType reminderType2 = this.h;
                if (reminderType2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("reminderType");
                    reminderType2 = null;
                }
                if (reminderType2 == ReminderType.MEETING) {
                    CustomReminder customReminder4 = this.i;
                    Intrinsics.checkNotNull(customReminder4, "null cannot be cast to non-null type com.coveiot.android.customreminders.model.MeetingReminder");
                    MeetingReminder meetingReminder = (MeetingReminder) customReminder4;
                    MedicineReminderViewModel medicineReminderViewModel5 = this.l;
                    if (medicineReminderViewModel5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                        medicineReminderViewModel5 = null;
                    }
                    Calendar startDate2 = meetingReminder.getStartDate();
                    Intrinsics.checkNotNullExpressionValue(startDate2, "meetingReminder.startDate");
                    medicineReminderViewModel5.setStartDate(startDate2, false);
                    if (meetingReminder.getEndDate() != null) {
                        MedicineReminderViewModel medicineReminderViewModel6 = this.l;
                        if (medicineReminderViewModel6 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                            medicineReminderViewModel6 = null;
                        }
                        Calendar endDate2 = meetingReminder.getEndDate();
                        Intrinsics.checkNotNullExpressionValue(endDate2, "meetingReminder.endDate");
                        medicineReminderViewModel6.setEndDate(endDate2, false);
                    }
                    kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new b(meetingReminder, this, null), 2, null);
                    MedicineReminderViewModel medicineReminderViewModel7 = this.l;
                    if (medicineReminderViewModel7 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                        medicineReminderViewModel7 = null;
                    }
                    medicineReminderViewModel7.setReminderInAdvance(meetingReminder.getAdvanceTime(), false);
                } else {
                    ReminderType reminderType3 = this.h;
                    if (reminderType3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("reminderType");
                        reminderType3 = null;
                    }
                    if (reminderType3 == ReminderType.OTHERS) {
                        CustomReminder customReminder5 = this.i;
                        Intrinsics.checkNotNull(customReminder5, "null cannot be cast to non-null type com.coveiot.android.customreminders.model.OtherReminder");
                        OtherReminder otherReminder = (OtherReminder) customReminder5;
                        MedicineReminderViewModel medicineReminderViewModel8 = this.l;
                        if (medicineReminderViewModel8 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                            medicineReminderViewModel8 = null;
                        }
                        Calendar startDate3 = otherReminder.getStartDate();
                        Intrinsics.checkNotNullExpressionValue(startDate3, "otherReminder.startDate");
                        medicineReminderViewModel8.setStartDate(startDate3, false);
                        if (otherReminder.getEndDate() != null) {
                            MedicineReminderViewModel medicineReminderViewModel9 = this.l;
                            if (medicineReminderViewModel9 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                                medicineReminderViewModel9 = null;
                            }
                            Calendar endDate3 = otherReminder.getEndDate();
                            Intrinsics.checkNotNullExpressionValue(endDate3, "otherReminder.endDate");
                            medicineReminderViewModel9.setEndDate(endDate3, false);
                        }
                        kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new c(otherReminder, this, null), 2, null);
                        MedicineReminderViewModel medicineReminderViewModel10 = this.l;
                        if (medicineReminderViewModel10 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                            medicineReminderViewModel10 = null;
                        }
                        medicineReminderViewModel10.setReminderInAdvance(otherReminder.getAdvanceTime(), false);
                    }
                }
            }
            MedicineReminderViewModel medicineReminderViewModel11 = this.l;
            if (medicineReminderViewModel11 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                medicineReminderViewModel11 = null;
            }
            CustomReminder customReminder6 = this.i;
            RepeatModel repeatModel = customReminder6 != null ? customReminder6.getRepeatModel() : null;
            Intrinsics.checkNotNull(repeatModel);
            medicineReminderViewModel11.setRepeat(repeatModel, false);
            ((Button) _$_findCachedViewById(R.id.doneButton)).setText(R.string.save);
        }
    }

    public final void W() {
        ((ImageView) _$_findCachedViewById(R.id.delete_time1)).setVisibility(8);
        int i = R.id.delete_time2;
        ((ImageView) _$_findCachedViewById(i)).setVisibility(8);
        ((ImageView) _$_findCachedViewById(i)).setVisibility(8);
    }

    public final void X() {
        int i = R.drawable.bg_notification_reminder_unselected;
        ((TextView) _$_findCachedViewById(R.id.all)).setBackgroundResource(i);
        ((TextView) _$_findCachedViewById(R.id.mon)).setBackgroundResource(i);
        ((TextView) _$_findCachedViewById(R.id.tue)).setBackgroundResource(i);
        ((TextView) _$_findCachedViewById(R.id.wed)).setBackgroundResource(i);
        ((TextView) _$_findCachedViewById(R.id.thu)).setBackgroundResource(i);
        ((TextView) _$_findCachedViewById(R.id.fri)).setBackgroundResource(i);
        ((TextView) _$_findCachedViewById(R.id.sat)).setBackgroundResource(i);
        ((TextView) _$_findCachedViewById(R.id.sun)).setBackgroundResource(i);
    }

    public final void Y() {
        int i = R.drawable.bg_notification_reminder_selected;
        ((TextView) _$_findCachedViewById(R.id.all)).setBackgroundResource(i);
        ((TextView) _$_findCachedViewById(R.id.mon)).setBackgroundResource(i);
        ((TextView) _$_findCachedViewById(R.id.tue)).setBackgroundResource(i);
        ((TextView) _$_findCachedViewById(R.id.wed)).setBackgroundResource(i);
        ((TextView) _$_findCachedViewById(R.id.thu)).setBackgroundResource(i);
        ((TextView) _$_findCachedViewById(R.id.fri)).setBackgroundResource(i);
        ((TextView) _$_findCachedViewById(R.id.sat)).setBackgroundResource(i);
        ((TextView) _$_findCachedViewById(R.id.sun)).setBackgroundResource(i);
    }

    public final void Z() {
        if (this.o == null) {
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            String string = getString(R.string.confirmation);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.confirmation)");
            String string2 = getString(R.string.unsaved_changes);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.unsaved_changes)");
            BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = new BottomSheetDialogTwoButtons(requireContext, string, string2, false, 8, null);
            this.o = bottomSheetDialogTwoButtons;
            Intrinsics.checkNotNull(bottomSheetDialogTwoButtons);
            String string3 = getString(R.string.ok);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.ok)");
            bottomSheetDialogTwoButtons.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.customreminders.fragments.MedicineReminderFragment$showSaveUnSavedChanges$1
                @Override // android.view.View.OnClickListener
                public void onClick(@Nullable View view) {
                    ((Button) MedicineReminderFragment.this._$_findCachedViewById(R.id.doneButton)).performClick();
                    BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons2 = MedicineReminderFragment.this.getBottomSheetDialogTwoButtons();
                    Intrinsics.checkNotNull(bottomSheetDialogTwoButtons2);
                    bottomSheetDialogTwoButtons2.dismiss();
                }
            });
            BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons2 = this.o;
            Intrinsics.checkNotNull(bottomSheetDialogTwoButtons2);
            String string4 = getString(R.string.cancel);
            Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.cancel)");
            bottomSheetDialogTwoButtons2.setNegativeButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.customreminders.fragments.MedicineReminderFragment$showSaveUnSavedChanges$2
                @Override // android.view.View.OnClickListener
                public void onClick(@Nullable View view) {
                    BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons3 = MedicineReminderFragment.this.getBottomSheetDialogTwoButtons();
                    Intrinsics.checkNotNull(bottomSheetDialogTwoButtons3);
                    bottomSheetDialogTwoButtons3.dismiss();
                    FragmentActivity requireActivity = MedicineReminderFragment.this.requireActivity();
                    if (requireActivity != null) {
                        requireActivity.finish();
                    }
                }
            });
        }
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons3 = this.o;
        Intrinsics.checkNotNull(bottomSheetDialogTwoButtons3);
        if (bottomSheetDialogTwoButtons3.isShowing()) {
            return;
        }
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons4 = this.o;
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

    @Nullable
    public final BottomSheetDialogTwoButtons getBottomSheetDialogTwoButtons() {
        return this.o;
    }

    public final boolean getRepeatFlag() {
        return this.k;
    }

    public final void initClickListeners() {
        ((EditText) _$_findCachedViewById(R.id.edt_reminder_name)).addTextChangedListener(new TextWatcher() { // from class: com.coveiot.android.customreminders.fragments.MedicineReminderFragment$initClickListeners$1
            @Override // android.text.TextWatcher
            public void afterTextChanged(@Nullable Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(@Nullable CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(@Nullable CharSequence charSequence, int i, int i2, int i3) {
                if (charSequence != null) {
                    MedicineReminderViewModel medicineReminderViewModel = MedicineReminderFragment.this.l;
                    if (medicineReminderViewModel == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                        medicineReminderViewModel = null;
                    }
                    MedicineReminderViewModel.setReminderName$default(medicineReminderViewModel, charSequence.toString(), false, 2, null);
                    ((TextView) MedicineReminderFragment.this._$_findCachedViewById(R.id.data_lenght)).setText(charSequence.length() + "/24");
                    return;
                }
                MedicineReminderViewModel medicineReminderViewModel2 = MedicineReminderFragment.this.l;
                if (medicineReminderViewModel2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    medicineReminderViewModel2 = null;
                }
                MedicineReminderViewModel.setReminderName$default(medicineReminderViewModel2, "", false, 2, null);
            }
        });
        ((EditText) _$_findCachedViewById(R.id.edt_start_date)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.customreminders.fragments.q
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MedicineReminderFragment.I(MedicineReminderFragment.this, view);
            }
        });
        ((EditText) _$_findCachedViewById(R.id.edt_end_date)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.customreminders.fragments.t
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MedicineReminderFragment.O(MedicineReminderFragment.this, view);
            }
        });
        ((EditText) _$_findCachedViewById(R.id.time1)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.customreminders.fragments.l
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MedicineReminderFragment.Q(MedicineReminderFragment.this, view);
            }
        });
        ((EditText) _$_findCachedViewById(R.id.time2)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.customreminders.fragments.m
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MedicineReminderFragment.S(MedicineReminderFragment.this, view);
            }
        });
        ((EditText) _$_findCachedViewById(R.id.time3)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.customreminders.fragments.a0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MedicineReminderFragment.x(MedicineReminderFragment.this, view);
            }
        });
        ((ImageView) _$_findCachedViewById(R.id.delete_time1)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.customreminders.fragments.b0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MedicineReminderFragment.z(MedicineReminderFragment.this, view);
            }
        });
        ((ImageView) _$_findCachedViewById(R.id.delete_time2)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.customreminders.fragments.r
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MedicineReminderFragment.A(MedicineReminderFragment.this, view);
            }
        });
        ((ImageView) _$_findCachedViewById(R.id.delete_time3)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.customreminders.fragments.c0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MedicineReminderFragment.B(MedicineReminderFragment.this, view);
            }
        });
        ((ConstraintLayout) _$_findCachedViewById(R.id.layout_remind_in)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.customreminders.fragments.d0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MedicineReminderFragment.C(MedicineReminderFragment.this, view);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.all)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.customreminders.fragments.k
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MedicineReminderFragment.D(MedicineReminderFragment.this, view);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.mon)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.customreminders.fragments.v
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MedicineReminderFragment.E(MedicineReminderFragment.this, view);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.tue)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.customreminders.fragments.p
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MedicineReminderFragment.F(MedicineReminderFragment.this, view);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.wed)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.customreminders.fragments.n
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MedicineReminderFragment.G(MedicineReminderFragment.this, view);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.thu)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.customreminders.fragments.z
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MedicineReminderFragment.H(MedicineReminderFragment.this, view);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.fri)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.customreminders.fragments.o
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MedicineReminderFragment.K(MedicineReminderFragment.this, view);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.sat)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.customreminders.fragments.i
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MedicineReminderFragment.L(MedicineReminderFragment.this, view);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.sun)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.customreminders.fragments.u
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MedicineReminderFragment.M(MedicineReminderFragment.this, view);
            }
        });
        ((Button) _$_findCachedViewById(R.id.doneButton)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.customreminders.fragments.j
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MedicineReminderFragment.N(MedicineReminderFragment.this, view);
            }
        });
    }

    public final void initObservers() {
        MedicineReminderViewModel medicineReminderViewModel = this.l;
        MedicineReminderViewModel medicineReminderViewModel2 = null;
        if (medicineReminderViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            medicineReminderViewModel = null;
        }
        medicineReminderViewModel.getStartDateLiveData().observe(getViewLifecycleOwner(), new Observer<String>() { // from class: com.coveiot.android.customreminders.fragments.MedicineReminderFragment$initObservers$1
            @Override // androidx.lifecycle.Observer
            public void onChanged(@Nullable String str) {
                if (str != null) {
                    MedicineReminderFragment medicineReminderFragment = MedicineReminderFragment.this;
                    int i = R.id.edt_start_date;
                    ((EditText) medicineReminderFragment._$_findCachedViewById(i)).setText(str);
                    ((EditText) MedicineReminderFragment.this._$_findCachedViewById(i)).setBackgroundResource(R.drawable.bg_notification_config_0_selected);
                }
            }
        });
        MedicineReminderViewModel medicineReminderViewModel3 = this.l;
        if (medicineReminderViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            medicineReminderViewModel3 = null;
        }
        medicineReminderViewModel3.getEndDateLiveData().observe(getViewLifecycleOwner(), new Observer<String>() { // from class: com.coveiot.android.customreminders.fragments.MedicineReminderFragment$initObservers$2
            @Override // androidx.lifecycle.Observer
            public void onChanged(@Nullable String str) {
                if (str != null) {
                    MedicineReminderFragment medicineReminderFragment = MedicineReminderFragment.this;
                    int i = R.id.edt_end_date;
                    ((EditText) medicineReminderFragment._$_findCachedViewById(i)).setText(str);
                    ((EditText) MedicineReminderFragment.this._$_findCachedViewById(i)).setBackgroundResource(R.drawable.bg_notification_config_0_selected);
                }
            }
        });
        MedicineReminderViewModel medicineReminderViewModel4 = this.l;
        if (medicineReminderViewModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            medicineReminderViewModel4 = null;
        }
        medicineReminderViewModel4.getRemindInAdvanceLiveData().observe(getViewLifecycleOwner(), new Observer<String>() { // from class: com.coveiot.android.customreminders.fragments.MedicineReminderFragment$initObservers$3
            @Override // androidx.lifecycle.Observer
            public void onChanged(@Nullable String str) {
                if (str != null) {
                    if (str.equals("On time")) {
                        ((TextView) MedicineReminderFragment.this._$_findCachedViewById(R.id.inadvance)).setVisibility(8);
                    } else {
                        ((TextView) MedicineReminderFragment.this._$_findCachedViewById(R.id.inadvance)).setVisibility(0);
                    }
                    ((TextView) MedicineReminderFragment.this._$_findCachedViewById(R.id.reminder_advance_time)).setText(str);
                }
            }
        });
        W();
        MedicineReminderViewModel medicineReminderViewModel5 = this.l;
        if (medicineReminderViewModel5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            medicineReminderViewModel5 = null;
        }
        medicineReminderViewModel5.getTimeInfoLiveData().observe(getViewLifecycleOwner(), new Observer<Pair<? extends Integer, ? extends String>>() { // from class: com.coveiot.android.customreminders.fragments.MedicineReminderFragment$initObservers$4
            @Override // androidx.lifecycle.Observer
            public /* bridge */ /* synthetic */ void onChanged(Pair<? extends Integer, ? extends String> pair) {
                onChanged2((Pair<Integer, String>) pair);
            }

            /* renamed from: onChanged  reason: avoid collision after fix types in other method */
            public void onChanged2(@Nullable Pair<Integer, String> pair) {
                CustomReminder customReminder;
                CustomReminder customReminder2;
                CustomReminder customReminder3;
                if (pair != null) {
                    int intValue = pair.getFirst().intValue();
                    if (intValue == 0) {
                        MedicineReminderFragment medicineReminderFragment = MedicineReminderFragment.this;
                        int i = R.id.time1;
                        ((EditText) medicineReminderFragment._$_findCachedViewById(i)).setText(pair.getSecond());
                        if (!kotlin.text.m.equals(pair.getSecond(), "00:00 AM", true) && !AppUtils.isEmpty(pair.getSecond())) {
                            ((EditText) MedicineReminderFragment.this._$_findCachedViewById(i)).setBackgroundResource(R.drawable.bg_notification_config_0_selected);
                        } else {
                            ((EditText) MedicineReminderFragment.this._$_findCachedViewById(i)).setBackgroundResource(R.drawable.bg_notification_config_0);
                        }
                        customReminder = MedicineReminderFragment.this.i;
                        if (customReminder != null) {
                            if (!kotlin.text.m.equals(pair.getSecond(), "00:00 AM", true) && !AppUtils.isEmpty(pair.getSecond())) {
                                ((ImageView) MedicineReminderFragment.this._$_findCachedViewById(R.id.delete_time1)).setVisibility(0);
                            } else {
                                ((ImageView) MedicineReminderFragment.this._$_findCachedViewById(R.id.delete_time1)).setVisibility(8);
                            }
                        }
                    } else if (intValue == 1) {
                        MedicineReminderFragment medicineReminderFragment2 = MedicineReminderFragment.this;
                        int i2 = R.id.time2;
                        ((EditText) medicineReminderFragment2._$_findCachedViewById(i2)).setText(pair.getSecond());
                        if (!kotlin.text.m.equals(pair.getSecond(), "00:00 AM", true) && !AppUtils.isEmpty(pair.getSecond())) {
                            ((EditText) MedicineReminderFragment.this._$_findCachedViewById(i2)).setBackgroundResource(R.drawable.bg_notification_config_0_selected);
                        } else {
                            ((EditText) MedicineReminderFragment.this._$_findCachedViewById(i2)).setBackgroundResource(R.drawable.bg_notification_config_0);
                        }
                        customReminder2 = MedicineReminderFragment.this.i;
                        if (customReminder2 != null) {
                            if (!kotlin.text.m.equals(pair.getSecond(), "00:00 AM", true) && !AppUtils.isEmpty(pair.getSecond())) {
                                ((ImageView) MedicineReminderFragment.this._$_findCachedViewById(R.id.delete_time2)).setVisibility(0);
                            } else {
                                ((ImageView) MedicineReminderFragment.this._$_findCachedViewById(R.id.delete_time2)).setVisibility(8);
                            }
                        }
                    } else if (intValue != 2) {
                    } else {
                        MedicineReminderFragment medicineReminderFragment3 = MedicineReminderFragment.this;
                        int i3 = R.id.time3;
                        ((EditText) medicineReminderFragment3._$_findCachedViewById(i3)).setText(pair.getSecond());
                        if (!kotlin.text.m.equals(pair.getSecond(), "00:00 AM", true) && !AppUtils.isEmpty(pair.getSecond())) {
                            ((EditText) MedicineReminderFragment.this._$_findCachedViewById(i3)).setBackgroundResource(R.drawable.bg_notification_config_0_selected);
                        } else {
                            ((EditText) MedicineReminderFragment.this._$_findCachedViewById(i3)).setBackgroundResource(R.drawable.bg_notification_config_0);
                        }
                        customReminder3 = MedicineReminderFragment.this.i;
                        if (customReminder3 != null) {
                            if (!kotlin.text.m.equals(pair.getSecond(), "00:00 AM", true) && !AppUtils.isEmpty(pair.getSecond())) {
                                ((ImageView) MedicineReminderFragment.this._$_findCachedViewById(R.id.delete_time3)).setVisibility(0);
                            } else {
                                ((ImageView) MedicineReminderFragment.this._$_findCachedViewById(R.id.delete_time3)).setVisibility(8);
                            }
                        }
                    }
                }
            }
        });
        MedicineReminderViewModel medicineReminderViewModel6 = this.l;
        if (medicineReminderViewModel6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            medicineReminderViewModel6 = null;
        }
        medicineReminderViewModel6.getRepeatLiveData().observe(getViewLifecycleOwner(), new Observer<RepeatModel>() { // from class: com.coveiot.android.customreminders.fragments.MedicineReminderFragment$initObservers$5
            @Override // androidx.lifecycle.Observer
            public void onChanged(@Nullable RepeatModel repeatModel) {
                if (repeatModel != null) {
                    MedicineReminderFragment.this.X();
                    if (repeatModel.isAllSelected()) {
                        MedicineReminderFragment.this.Y();
                        ((TextView) MedicineReminderFragment.this._$_findCachedViewById(R.id.all)).setBackgroundResource(R.drawable.bg_notification_reminder_selected);
                        return;
                    }
                    if (repeatModel.monday) {
                        ((TextView) MedicineReminderFragment.this._$_findCachedViewById(R.id.mon)).setBackgroundResource(R.drawable.bg_notification_reminder_selected);
                    }
                    if (repeatModel.tuesday) {
                        ((TextView) MedicineReminderFragment.this._$_findCachedViewById(R.id.tue)).setBackgroundResource(R.drawable.bg_notification_reminder_selected);
                    }
                    if (repeatModel.wednesday) {
                        ((TextView) MedicineReminderFragment.this._$_findCachedViewById(R.id.wed)).setBackgroundResource(R.drawable.bg_notification_reminder_selected);
                    }
                    if (repeatModel.thursday) {
                        ((TextView) MedicineReminderFragment.this._$_findCachedViewById(R.id.thu)).setBackgroundResource(R.drawable.bg_notification_reminder_selected);
                    }
                    if (repeatModel.friday) {
                        ((TextView) MedicineReminderFragment.this._$_findCachedViewById(R.id.fri)).setBackgroundResource(R.drawable.bg_notification_reminder_selected);
                    }
                    if (repeatModel.saturday) {
                        ((TextView) MedicineReminderFragment.this._$_findCachedViewById(R.id.sat)).setBackgroundResource(R.drawable.bg_notification_reminder_selected);
                    }
                    if (repeatModel.sunday) {
                        ((TextView) MedicineReminderFragment.this._$_findCachedViewById(R.id.sun)).setBackgroundResource(R.drawable.bg_notification_reminder_selected);
                    }
                }
            }
        });
        MedicineReminderViewModel medicineReminderViewModel7 = this.l;
        if (medicineReminderViewModel7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            medicineReminderViewModel2 = medicineReminderViewModel7;
        }
        medicineReminderViewModel2.getDoneBtnVisablityLiveData().observe(getViewLifecycleOwner(), new Observer<Boolean>() { // from class: com.coveiot.android.customreminders.fragments.MedicineReminderFragment$initObservers$6
            @Override // androidx.lifecycle.Observer
            public void onChanged(@Nullable Boolean bool) {
                if (bool != null) {
                    ((Button) MedicineReminderFragment.this._$_findCachedViewById(R.id.doneButton)).setEnabled(bool.booleanValue());
                } else {
                    ((Button) MedicineReminderFragment.this._$_findCachedViewById(R.id.doneButton)).setEnabled(false);
                }
            }
        });
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
    }

    public final void onBackPressed() {
        if (((Button) _$_findCachedViewById(R.id.doneButton)).isEnabled()) {
            Z();
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
        return inflater.inflate(R.layout.fragment_medicine_reminder, viewGroup, false);
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
        ReminderType reminderType = null;
        if (medicineReminderViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            medicineReminderViewModel = null;
        }
        ReminderType reminderType2 = this.h;
        if (reminderType2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("reminderType");
        } else {
            reminderType = reminderType2;
        }
        medicineReminderViewModel.setReminderType(reminderType);
        U();
        initObservers();
        initClickListeners();
        V();
    }

    public final void setBottomSheetDialogTwoButtons(@Nullable BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons) {
        this.o = bottomSheetDialogTwoButtons;
    }

    public final void setRepeatFlag(boolean z) {
        this.k = z;
    }
}
