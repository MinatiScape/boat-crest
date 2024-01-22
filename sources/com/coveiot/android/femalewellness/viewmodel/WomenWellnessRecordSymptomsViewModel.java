package com.coveiot.android.femalewellness.viewmodel;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.coveiot.android.femalewellness.db.FemaleWellnessRepository;
import com.coveiot.android.femalewellness.db.entities.EntityFemaleWellnessSymptoms;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.fitness.model.MensSettings;
import com.coveiot.coveaccess.fitness.model.MensurationSymptomsRecordBeans;
import com.coveiot.coveaccess.fitnessrecord.FitnessRecordApiManager;
import com.coveiot.coveaccess.fitnessrecord.FitnessRecordType;
import com.coveiot.coveaccess.fitnessrecord.SaveFitnessRecordsResponse;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.SSaveMensurationFitnessRecordReq;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.WomenWellnessData;
import java.util.ArrayList;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class WomenWellnessRecordSymptomsViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4397a;
    @NotNull
    public final String b;
    public WomenWellnessData c;
    @NotNull
    public LiveData<List<EntityFemaleWellnessSymptoms>> d;
    public SymptomsSavedListener symptomsSavedListener;

    /* loaded from: classes4.dex */
    public interface SymptomsSavedListener {
        void onSymptomsSaved(boolean z);
    }

    @DebugMetadata(c = "com.coveiot.android.femalewellness.viewmodel.WomenWellnessRecordSymptomsViewModel$saveWomenWellnessSymptomsToDB$1", f = "WomenWellnessRecordSymptomsViewModel.kt", i = {}, l = {90}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes4.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ EntityFemaleWellnessSymptoms $recordSymptom;
        public int label;

        @DebugMetadata(c = "com.coveiot.android.femalewellness.viewmodel.WomenWellnessRecordSymptomsViewModel$saveWomenWellnessSymptomsToDB$1$1", f = "WomenWellnessRecordSymptomsViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.coveiot.android.femalewellness.viewmodel.WomenWellnessRecordSymptomsViewModel$a$a  reason: collision with other inner class name */
        /* loaded from: classes4.dex */
        public static final class C0269a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ EntityFemaleWellnessSymptoms $recordSymptom;
            public int label;
            public final /* synthetic */ WomenWellnessRecordSymptomsViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C0269a(WomenWellnessRecordSymptomsViewModel womenWellnessRecordSymptomsViewModel, EntityFemaleWellnessSymptoms entityFemaleWellnessSymptoms, Continuation<? super C0269a> continuation) {
                super(2, continuation);
                this.this$0 = womenWellnessRecordSymptomsViewModel;
                this.$recordSymptom = entityFemaleWellnessSymptoms;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new C0269a(this.this$0, this.$recordSymptom, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                return ((C0269a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    FemaleWellnessRepository.Companion.getInstance(this.this$0.getContext()).insert(this.$recordSymptom);
                    this.this$0.getSymptomsSavedListener().onSymptomsSaved(true);
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(EntityFemaleWellnessSymptoms entityFemaleWellnessSymptoms, Continuation<? super a> continuation) {
            super(2, continuation);
            this.$recordSymptom = entityFemaleWellnessSymptoms;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new a(this.$recordSymptom, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineDispatcher io2 = Dispatchers.getIO();
                C0269a c0269a = new C0269a(WomenWellnessRecordSymptomsViewModel.this, this.$recordSymptom, null);
                this.label = 1;
                if (BuildersKt.withContext(io2, c0269a, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            } else {
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    public WomenWellnessRecordSymptomsViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f4397a = context;
        this.b = "WomenWellnessRecordSymptomsViewModel";
        this.d = new MutableLiveData();
    }

    @NotNull
    public final Context getContext() {
        return this.f4397a;
    }

    public final void getFemaleWellnessSymptomsListFromDb(@NotNull String selectedDate) {
        Intrinsics.checkNotNullParameter(selectedDate, "selectedDate");
        this.d = new FemaleWellnessRepository(this.f4397a).getLatestRecord(selectedDate);
    }

    @NotNull
    public final LiveData<List<EntityFemaleWellnessSymptoms>> getFemaleWellnessSymptomsLiveData() {
        return this.d;
    }

    @NotNull
    public final SymptomsSavedListener getSymptomsSavedListener() {
        SymptomsSavedListener symptomsSavedListener = this.symptomsSavedListener;
        if (symptomsSavedListener != null) {
            return symptomsSavedListener;
        }
        Intrinsics.throwUninitializedPropertyAccessException("symptomsSavedListener");
        return null;
    }

    @NotNull
    public final String getTAG() {
        return this.b;
    }

    @NotNull
    public final WomenWellnessData getWomenWellnessFromPref() {
        try {
            WomenWellnessData womenWellnessData = UserDataManager.getInstance(this.f4397a).getWomenWellnessData();
            if (womenWellnessData == null) {
                WomenWellnessData womenWellnessData2 = WomenWellnessData.getInstance();
                Intrinsics.checkNotNullExpressionValue(womenWellnessData2, "getInstance()");
                this.c = womenWellnessData2;
                if (womenWellnessData2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("womenWellnessData");
                    womenWellnessData2 = null;
                }
                womenWellnessData2.setEnabled(false);
                WomenWellnessData womenWellnessData3 = this.c;
                if (womenWellnessData3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("womenWellnessData");
                    womenWellnessData3 = null;
                }
                womenWellnessData3.setReminderHour(9);
                WomenWellnessData womenWellnessData4 = this.c;
                if (womenWellnessData4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("womenWellnessData");
                    womenWellnessData4 = null;
                }
                womenWellnessData4.setReminderMinute(0);
                WomenWellnessData womenWellnessData5 = this.c;
                if (womenWellnessData5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("womenWellnessData");
                    womenWellnessData5 = null;
                }
                womenWellnessData5.setPeriodReminderAdvance(2);
                WomenWellnessData womenWellnessData6 = this.c;
                if (womenWellnessData6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("womenWellnessData");
                    womenWellnessData6 = null;
                }
                womenWellnessData6.setOvulationReminderAdvance(2);
                WomenWellnessData womenWellnessData7 = this.c;
                if (womenWellnessData7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("womenWellnessData");
                    womenWellnessData7 = null;
                }
                womenWellnessData7.setLastPeriodYear(0);
                WomenWellnessData womenWellnessData8 = this.c;
                if (womenWellnessData8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("womenWellnessData");
                    womenWellnessData8 = null;
                }
                womenWellnessData8.setLastPeriodMonth(0);
                WomenWellnessData womenWellnessData9 = this.c;
                if (womenWellnessData9 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("womenWellnessData");
                    womenWellnessData9 = null;
                }
                womenWellnessData9.setLastPeriodDay(0);
                WomenWellnessData womenWellnessData10 = this.c;
                if (womenWellnessData10 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("womenWellnessData");
                    womenWellnessData10 = null;
                }
                womenWellnessData10.setMenstruationPeriodLength(5);
                WomenWellnessData womenWellnessData11 = this.c;
                if (womenWellnessData11 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("womenWellnessData");
                    womenWellnessData11 = null;
                }
                womenWellnessData11.setMenstruationCycleLength(28);
            } else {
                WomenWellnessData womenWellnessData12 = WomenWellnessData.getInstance();
                Intrinsics.checkNotNullExpressionValue(womenWellnessData12, "getInstance()");
                this.c = womenWellnessData12;
                if (womenWellnessData12 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("womenWellnessData");
                    womenWellnessData12 = null;
                }
                womenWellnessData12.setEnabled(womenWellnessData.isEnabled());
                WomenWellnessData womenWellnessData13 = this.c;
                if (womenWellnessData13 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("womenWellnessData");
                    womenWellnessData13 = null;
                }
                womenWellnessData13.setReminderHour(womenWellnessData.getReminderHour());
                WomenWellnessData womenWellnessData14 = this.c;
                if (womenWellnessData14 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("womenWellnessData");
                    womenWellnessData14 = null;
                }
                womenWellnessData14.setReminderMinute(womenWellnessData.getReminderMinute());
                WomenWellnessData womenWellnessData15 = this.c;
                if (womenWellnessData15 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("womenWellnessData");
                    womenWellnessData15 = null;
                }
                womenWellnessData15.setPeriodReminderAdvance(womenWellnessData.getPeriodReminderAdvance());
                WomenWellnessData womenWellnessData16 = this.c;
                if (womenWellnessData16 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("womenWellnessData");
                    womenWellnessData16 = null;
                }
                womenWellnessData16.setOvulationReminderAdvance(womenWellnessData.getOvulationReminderAdvance());
                WomenWellnessData womenWellnessData17 = this.c;
                if (womenWellnessData17 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("womenWellnessData");
                    womenWellnessData17 = null;
                }
                womenWellnessData17.setLastPeriodYear(womenWellnessData.getLastPeriodYear());
                WomenWellnessData womenWellnessData18 = this.c;
                if (womenWellnessData18 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("womenWellnessData");
                    womenWellnessData18 = null;
                }
                womenWellnessData18.setLastPeriodMonth(womenWellnessData.getLastPeriodMonth());
                WomenWellnessData womenWellnessData19 = this.c;
                if (womenWellnessData19 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("womenWellnessData");
                    womenWellnessData19 = null;
                }
                womenWellnessData19.setLastPeriodDay(womenWellnessData.getLastPeriodDay());
                WomenWellnessData womenWellnessData20 = this.c;
                if (womenWellnessData20 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("womenWellnessData");
                    womenWellnessData20 = null;
                }
                womenWellnessData20.setMenstruationPeriodLength(womenWellnessData.getMenstruationPeriodLength());
                WomenWellnessData womenWellnessData21 = this.c;
                if (womenWellnessData21 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("womenWellnessData");
                    womenWellnessData21 = null;
                }
                womenWellnessData21.setMenstruationCycleLength(womenWellnessData.getMenstruationCycleLength());
            }
            WomenWellnessData womenWellnessData22 = this.c;
            if (womenWellnessData22 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("womenWellnessData");
                return null;
            }
            return womenWellnessData22;
        } catch (Exception unused) {
            WomenWellnessData womenWellnessData23 = WomenWellnessData.getInstance();
            Intrinsics.checkNotNullExpressionValue(womenWellnessData23, "getInstance()");
            this.c = womenWellnessData23;
            if (womenWellnessData23 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("womenWellnessData");
                womenWellnessData23 = null;
            }
            womenWellnessData23.setEnabled(false);
            WomenWellnessData womenWellnessData24 = this.c;
            if (womenWellnessData24 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("womenWellnessData");
                womenWellnessData24 = null;
            }
            womenWellnessData24.setReminderHour(9);
            WomenWellnessData womenWellnessData25 = this.c;
            if (womenWellnessData25 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("womenWellnessData");
                womenWellnessData25 = null;
            }
            womenWellnessData25.setReminderMinute(0);
            WomenWellnessData womenWellnessData26 = this.c;
            if (womenWellnessData26 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("womenWellnessData");
                womenWellnessData26 = null;
            }
            womenWellnessData26.setPeriodReminderAdvance(2);
            WomenWellnessData womenWellnessData27 = this.c;
            if (womenWellnessData27 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("womenWellnessData");
                womenWellnessData27 = null;
            }
            womenWellnessData27.setOvulationReminderAdvance(2);
            WomenWellnessData womenWellnessData28 = this.c;
            if (womenWellnessData28 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("womenWellnessData");
                womenWellnessData28 = null;
            }
            womenWellnessData28.setLastPeriodYear(0);
            WomenWellnessData womenWellnessData29 = this.c;
            if (womenWellnessData29 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("womenWellnessData");
                womenWellnessData29 = null;
            }
            womenWellnessData29.setLastPeriodMonth(0);
            WomenWellnessData womenWellnessData30 = this.c;
            if (womenWellnessData30 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("womenWellnessData");
                womenWellnessData30 = null;
            }
            womenWellnessData30.setLastPeriodDay(0);
            WomenWellnessData womenWellnessData31 = this.c;
            if (womenWellnessData31 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("womenWellnessData");
                womenWellnessData31 = null;
            }
            womenWellnessData31.setMenstruationPeriodLength(5);
            WomenWellnessData womenWellnessData32 = this.c;
            if (womenWellnessData32 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("womenWellnessData");
                womenWellnessData32 = null;
            }
            womenWellnessData32.setMenstruationCycleLength(28);
            WomenWellnessData womenWellnessData33 = this.c;
            if (womenWellnessData33 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("womenWellnessData");
                return null;
            }
            return womenWellnessData33;
        }
    }

    public final void saveWomenWellnessSymptomsToDB(@NotNull EntityFemaleWellnessSymptoms recordSymptom) {
        Intrinsics.checkNotNullParameter(recordSymptom, "recordSymptom");
        kotlinx.coroutines.e.e(ViewModelKt.getViewModelScope(this), null, null, new a(recordSymptom, null), 3, null);
    }

    public final void saveWomenWellnessSymptomsToServer(@NotNull final EntityFemaleWellnessSymptoms recordSymptom) {
        Intrinsics.checkNotNullParameter(recordSymptom, "recordSymptom");
        SSaveMensurationFitnessRecordReq sSaveMensurationFitnessRecordReq = new SSaveMensurationFitnessRecordReq();
        MensurationSymptomsRecordBeans mensurationSymptomsRecordBeans = new MensurationSymptomsRecordBeans();
        mensurationSymptomsRecordBeans.setFlow(recordSymptom.flow);
        mensurationSymptomsRecordBeans.setMood(recordSymptom.mood);
        mensurationSymptomsRecordBeans.setPain(recordSymptom.pain);
        mensurationSymptomsRecordBeans.setSymptoms(recordSymptom.symptoms);
        mensurationSymptomsRecordBeans.setDate(recordSymptom.date);
        mensurationSymptomsRecordBeans.setType(FitnessRecordType.MENSES.name());
        mensurationSymptomsRecordBeans.setBaseUnit("UNITLESS");
        mensurationSymptomsRecordBeans.setPhase(recordSymptom.phase);
        WomenWellnessData womenWellnessData = UserDataManager.getInstance(this.f4397a).getWomenWellnessData();
        MensSettings mensSettings = new MensSettings();
        if (womenWellnessData != null) {
            mensSettings.setActive(Boolean.valueOf(womenWellnessData.isEnabled()));
            mensSettings.setCycleLength(Integer.valueOf(womenWellnessData.getMenstruationCycleLength()));
            mensSettings.setPeriodLength(Integer.valueOf(womenWellnessData.getMenstruationPeriodLength()));
            mensSettings.setCycleStartDate(recordSymptom.cycleStartDate);
            mensSettings.setPmsLength(0);
            mensurationSymptomsRecordBeans.setMensSettings(mensSettings);
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(mensurationSymptomsRecordBeans);
        sSaveMensurationFitnessRecordReq.setItems(arrayList);
        FitnessRecordApiManager.saveMensurationFitnessRecords(sSaveMensurationFitnessRecordReq, new CoveApiListener<SaveFitnessRecordsResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.femalewellness.viewmodel.WomenWellnessRecordSymptomsViewModel$saveWomenWellnessSymptomsToServer$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@NotNull CoveApiErrorModel object) {
                Intrinsics.checkNotNullParameter(object, "object");
                WomenWellnessRecordSymptomsViewModel.this.getSymptomsSavedListener().onSymptomsSaved(false);
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@NotNull SaveFitnessRecordsResponse object) {
                Intrinsics.checkNotNullParameter(object, "object");
                WomenWellnessRecordSymptomsViewModel.this.saveWomenWellnessSymptomsToDB(recordSymptom);
            }
        });
    }

    public final void setFemaleWellnessSymptomsLiveData(@NotNull LiveData<List<EntityFemaleWellnessSymptoms>> liveData) {
        Intrinsics.checkNotNullParameter(liveData, "<set-?>");
        this.d = liveData;
    }

    public final void setSymptomsSavedListener(@NotNull SymptomsSavedListener symptomsSavedListener) {
        Intrinsics.checkNotNullParameter(symptomsSavedListener, "<set-?>");
        this.symptomsSavedListener = symptomsSavedListener;
    }
}
