package com.coveiot.android.sleepenergyscore.energymeter;

import android.content.Context;
import com.coveiot.coveaccess.energyscore.model.ActivityData;
import com.coveiot.coveaccess.energyscore.model.EnergyScoreData;
import com.coveiot.utils.CoveEventBusManager;
import java.util.ArrayList;
import java.util.Calendar;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "com.coveiot.android.sleepenergyscore.energymeter.EnergyScoreApiCall$energyScoreBatchApiCall$1", f = "EnergyScoreApiCall.kt", i = {0, 0, 0, 0}, l = {112}, m = "invokeSuspend", n = {"dateListNotSynced", "datesList", "formatter", "i"}, s = {"L$0", "L$1", "L$2", "I$0"})
/* loaded from: classes6.dex */
public final class EnergyScoreApiCall$energyScoreBatchApiCall$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ Ref.ObjectRef<ArrayList<ActivityData>> $activityDataList;
    public final /* synthetic */ Context $context;
    public final /* synthetic */ Ref.ObjectRef<EnergyScoreData> $data;
    public final /* synthetic */ Calendar $endDate;
    public final /* synthetic */ Ref.ObjectRef<String> $energyScoreDataStartDatefit;
    public final /* synthetic */ Calendar $startDate;
    public final /* synthetic */ String $type;
    public int I$0;
    public int I$1;
    public Object L$0;
    public Object L$1;
    public Object L$2;
    public int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EnergyScoreApiCall$energyScoreBatchApiCall$1(Context context, Ref.ObjectRef<String> objectRef, Calendar calendar, Calendar calendar2, Ref.ObjectRef<ArrayList<ActivityData>> objectRef2, Ref.ObjectRef<EnergyScoreData> objectRef3, String str, Continuation<? super EnergyScoreApiCall$energyScoreBatchApiCall$1> continuation) {
        super(2, continuation);
        this.$context = context;
        this.$energyScoreDataStartDatefit = objectRef;
        this.$startDate = calendar;
        this.$endDate = calendar2;
        this.$activityDataList = objectRef2;
        this.$data = objectRef3;
        this.$type = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invokeSuspend$lambda$1() {
        CoveEventBusManager.getInstance().getEventBus().post(new EnergyScoreEventData());
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new EnergyScoreApiCall$energyScoreBatchApiCall$1(this.$context, this.$energyScoreDataStartDatefit, this.$startDate, this.$endDate, this.$activityDataList, this.$data, this.$type, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((EnergyScoreApiCall$energyScoreBatchApiCall$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x010b  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x013f  */
    /* JADX WARN: Type inference failed for: r5v8, types: [T, java.lang.String] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:18:0x00cb -> B:32:0x013c). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:24:0x00fe -> B:25:0x0107). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:30:0x0128 -> B:32:0x013c). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:31:0x012a -> B:32:0x013c). Please submit an issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r14) {
        /*
            Method dump skipped, instructions count: 408
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.sleepenergyscore.energymeter.EnergyScoreApiCall$energyScoreBatchApiCall$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
