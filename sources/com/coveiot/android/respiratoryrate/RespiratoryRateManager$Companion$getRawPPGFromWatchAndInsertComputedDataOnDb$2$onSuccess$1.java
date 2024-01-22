package com.coveiot.android.respiratoryrate;

import android.content.Context;
import com.coveiot.android.bleabstract.models.RawPPGHistoryData;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.respiratoryrate.RespiratoryRateApiManager;
import com.coveiot.coveaccess.respiratoryrate.RespiratoryRateApiReq;
import com.coveiot.coveaccess.respiratoryrate.RespiratoryRateApiRes;
import com.coveiot.utils.utility.LogHelper;
import java.util.ArrayList;
import java.util.Map;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.a;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.e;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "com.coveiot.android.respiratoryrate.RespiratoryRateManager$Companion$getRawPPGFromWatchAndInsertComputedDataOnDb$2$onSuccess$1", f = "RespiratoryRateManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes6.dex */
public final class RespiratoryRateManager$Companion$getRawPPGFromWatchAndInsertComputedDataOnDb$2$onSuccess$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ Context $context;
    public final /* synthetic */ Map<String, ArrayList<RawPPGHistoryData>> $mapDateRawPPGHistoryDataList;
    public int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RespiratoryRateManager$Companion$getRawPPGFromWatchAndInsertComputedDataOnDb$2$onSuccess$1(Map<String, ArrayList<RawPPGHistoryData>> map, Context context, Continuation<? super RespiratoryRateManager$Companion$getRawPPGFromWatchAndInsertComputedDataOnDb$2$onSuccess$1> continuation) {
        super(2, continuation);
        this.$mapDateRawPPGHistoryDataList = map;
        this.$context = context;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new RespiratoryRateManager$Companion$getRawPPGFromWatchAndInsertComputedDataOnDb$2$onSuccess$1(this.$mapDateRawPPGHistoryDataList, this.$context, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((RespiratoryRateManager$Companion$getRawPPGFromWatchAndInsertComputedDataOnDb$2$onSuccess$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Type inference failed for: r2v0, types: [T, java.util.ArrayList] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        RespiratoryRateApiReq h;
        a.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            Map<String, ArrayList<RawPPGHistoryData>> map = this.$mapDateRawPPGHistoryDataList;
            if (!(map == null || map.isEmpty())) {
                Ref.ObjectRef objectRef = new Ref.ObjectRef();
                objectRef.element = new ArrayList();
                for (Map.Entry<String, ArrayList<RawPPGHistoryData>> entry : this.$mapDateRawPPGHistoryDataList.entrySet()) {
                    ArrayList<RawPPGHistoryData> value = entry.getValue();
                    if (!(value == null || value.isEmpty())) {
                        ArrayList<RawPPGHistoryData> value2 = entry.getValue();
                        Intrinsics.checkNotNull(value2);
                        ((ArrayList) objectRef.element).addAll(value2);
                    }
                }
                h = RespiratoryRateManager.Companion.h(this.$context, (ArrayList) objectRef.element);
                if (h != null) {
                    final Context context = this.$context;
                    final Map<String, ArrayList<RawPPGHistoryData>> map2 = this.$mapDateRawPPGHistoryDataList;
                    RespiratoryRateApiManager.sendDataForRespiratoryRateCal(h, new CoveApiListener<RespiratoryRateApiRes, CoveApiErrorModel>() { // from class: com.coveiot.android.respiratoryrate.RespiratoryRateManager$Companion$getRawPPGFromWatchAndInsertComputedDataOnDb$2$onSuccess$1$2$1
                        @Override // com.coveiot.coveaccess.CoveApiListener
                        public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                            LogHelper.d(RespiratoryRateManager.Companion.getTAG(), coveApiErrorModel != null ? coveApiErrorModel.getMsg() : null);
                        }

                        @Override // com.coveiot.coveaccess.CoveApiListener
                        public void onSuccess(@Nullable RespiratoryRateApiRes respiratoryRateApiRes) {
                            e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new RespiratoryRateManager$Companion$getRawPPGFromWatchAndInsertComputedDataOnDb$2$onSuccess$1$2$1$onSuccess$1(respiratoryRateApiRes, context, map2, null), 2, null);
                        }
                    });
                }
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
