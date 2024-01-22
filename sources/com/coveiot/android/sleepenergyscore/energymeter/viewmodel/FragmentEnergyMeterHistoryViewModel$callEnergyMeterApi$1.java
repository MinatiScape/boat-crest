package com.coveiot.android.sleepenergyscore.energymeter.viewmodel;

import android.content.Context;
import com.coveiot.coveaccess.energyscore.model.ActivityData;
import com.coveiot.coveaccess.energyscore.model.EnergyScoreData;
import java.util.ArrayList;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "com.coveiot.android.sleepenergyscore.energymeter.viewmodel.FragmentEnergyMeterHistoryViewModel$callEnergyMeterApi$1", f = "FragmentEnergyMeterHistoryViewModel.kt", i = {0}, l = {62}, m = "invokeSuspend", n = {"i"}, s = {"I$0"})
/* loaded from: classes6.dex */
public final class FragmentEnergyMeterHistoryViewModel$callEnergyMeterApi$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ Ref.ObjectRef<ArrayList<ActivityData>> $activityDataList;
    public final /* synthetic */ Context $context;
    public final /* synthetic */ Ref.ObjectRef<EnergyScoreData> $data;
    public final /* synthetic */ Ref.ObjectRef<ArrayList<String>> $listOfDates;
    public int I$0;
    public int I$1;
    public int label;
    public final /* synthetic */ FragmentEnergyMeterHistoryViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FragmentEnergyMeterHistoryViewModel$callEnergyMeterApi$1(Ref.ObjectRef<ArrayList<String>> objectRef, Context context, Ref.ObjectRef<ArrayList<ActivityData>> objectRef2, Ref.ObjectRef<EnergyScoreData> objectRef3, FragmentEnergyMeterHistoryViewModel fragmentEnergyMeterHistoryViewModel, Continuation<? super FragmentEnergyMeterHistoryViewModel$callEnergyMeterApi$1> continuation) {
        super(2, continuation);
        this.$listOfDates = objectRef;
        this.$context = context;
        this.$activityDataList = objectRef2;
        this.$data = objectRef3;
        this.this$0 = fragmentEnergyMeterHistoryViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new FragmentEnergyMeterHistoryViewModel$callEnergyMeterApi$1(this.$listOfDates, this.$context, this.$activityDataList, this.$data, this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((FragmentEnergyMeterHistoryViewModel$callEnergyMeterApi$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00c3  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:15:0x0068 -> B:16:0x006e). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:21:0x009d -> B:23:0x00c0). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x009f -> B:23:0x00c0). Please submit an issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r10) {
        /*
            Method dump skipped, instructions count: 253
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.sleepenergyscore.energymeter.viewmodel.FragmentEnergyMeterHistoryViewModel$callEnergyMeterApi$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
