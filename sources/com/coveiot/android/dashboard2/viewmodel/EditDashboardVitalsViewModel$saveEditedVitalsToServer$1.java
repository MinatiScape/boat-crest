package com.coveiot.android.dashboard2.viewmodel;

import com.coveiot.android.dashboard2.model.FitnessVitalsDataModel;
import com.coveiot.android.idoSdk.IDOConstants;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.CoveUserDeviceSettings;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.AppDashboardDTO;
import com.coveiot.coveaccess.userdevicesetting.SaveVitalCardSequenceRes;
import com.coveiot.coveaccess.userdevicesetting.model.SaveVitalCardsSequenceReq;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.coveiot.utils.utility.ecg.EcgStyleReportUtil;
import java.util.ArrayList;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "com.coveiot.android.dashboard2.viewmodel.EditDashboardVitalsViewModel$saveEditedVitalsToServer$1", f = "EditDashboardVitalsViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes4.dex */
public final class EditDashboardVitalsViewModel$saveEditedVitalsToServer$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ List<FitnessVitalsDataModel> $fitnessVitalDataModels;
    public int label;
    public final /* synthetic */ EditDashboardVitalsViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EditDashboardVitalsViewModel$saveEditedVitalsToServer$1(EditDashboardVitalsViewModel editDashboardVitalsViewModel, List<FitnessVitalsDataModel> list, Continuation<? super EditDashboardVitalsViewModel$saveEditedVitalsToServer$1> continuation) {
        super(2, continuation);
        this.this$0 = editDashboardVitalsViewModel;
        this.$fitnessVitalDataModels = list;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new EditDashboardVitalsViewModel$saveEditedVitalsToServer$1(this.this$0, this.$fitnessVitalDataModels, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((EditDashboardVitalsViewModel$saveEditedVitalsToServer$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            if (AppUtils.isNetConnected(this.this$0.getContext()) && this.$fitnessVitalDataModels != null) {
                AppDashboardDTO appDashboardDTO = new AppDashboardDTO();
                appDashboardDTO.setActiveVitalCards(new ArrayList());
                for (FitnessVitalsDataModel fitnessVitalsDataModel : this.$fitnessVitalDataModels) {
                    String fitnessVitalType = fitnessVitalsDataModel.getFitnessVitalType();
                    switch (fitnessVitalType.hashCode()) {
                        case -1849873063:
                            if (fitnessVitalType.equals(IDOConstants.DATA_TYPE_HEART_RATE)) {
                                appDashboardDTO.getActiveVitalCards().add(EcgStyleReportUtil.UserInfoKey.HR);
                                break;
                            } else {
                                break;
                            }
                        case -1838660172:
                            if (fitnessVitalType.equals("STRESS")) {
                                appDashboardDTO.getActiveVitalCards().add("STRESS");
                                break;
                            } else {
                                break;
                            }
                        case -1820305068:
                            if (fitnessVitalType.equals("TEMPERATURE")) {
                                appDashboardDTO.getActiveVitalCards().add("BODY_TEMPERATURE");
                                break;
                            } else {
                                break;
                            }
                        case -1606469902:
                            if (fitnessVitalType.equals("ENERGY_METER")) {
                                appDashboardDTO.getActiveVitalCards().add("ENERGY_METER");
                                break;
                            } else {
                                break;
                            }
                        case 2126:
                            if (fitnessVitalType.equals(EcgStyleReportUtil.UserInfoKey.BP)) {
                                appDashboardDTO.getActiveVitalCards().add(EcgStyleReportUtil.UserInfoKey.BP);
                                break;
                            } else {
                                break;
                            }
                        case 71820:
                            if (fitnessVitalType.equals("HRV")) {
                                appDashboardDTO.getActiveVitalCards().add("HRV");
                                break;
                            } else {
                                break;
                            }
                        case 77086:
                            if (fitnessVitalType.equals("NBR")) {
                                appDashboardDTO.getActiveVitalCards().add("RESPIRATORY_RATE");
                                break;
                            } else {
                                break;
                            }
                        case 2552032:
                            if (fitnessVitalType.equals("SPO2")) {
                                appDashboardDTO.getActiveVitalCards().add("SPO2");
                                break;
                            } else {
                                break;
                            }
                        case 78984887:
                            if (fitnessVitalType.equals("SLEEP")) {
                                appDashboardDTO.getActiveVitalCards().add("SLEEP");
                                break;
                            } else {
                                break;
                            }
                        case 79223559:
                            if (fitnessVitalType.equals("STEPS")) {
                                appDashboardDTO.getActiveVitalCards().add("STEPS");
                                break;
                            } else {
                                break;
                            }
                    }
                }
                SaveVitalCardsSequenceReq saveVitalCardsSequenceReq = new SaveVitalCardsSequenceReq();
                saveVitalCardsSequenceReq.setAppDashboard(appDashboardDTO);
                final EditDashboardVitalsViewModel editDashboardVitalsViewModel = this.this$0;
                CoveUserDeviceSettings.saveVitalCardsSequence(saveVitalCardsSequenceReq, new CoveApiListener<SaveVitalCardSequenceRes, CoveApiErrorModel>() { // from class: com.coveiot.android.dashboard2.viewmodel.EditDashboardVitalsViewModel$saveEditedVitalsToServer$1.1
                    @Override // com.coveiot.coveaccess.CoveApiListener
                    public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                        String str;
                        str = EditDashboardVitalsViewModel.this.b;
                        LogHelper.d(str, "Vital cards saved failed");
                    }

                    @Override // com.coveiot.coveaccess.CoveApiListener
                    public void onSuccess(@NotNull SaveVitalCardSequenceRes object) {
                        String str;
                        Intrinsics.checkNotNullParameter(object, "object");
                        str = EditDashboardVitalsViewModel.this.b;
                        LogHelper.d(str, "Vital cards saved successfully");
                    }
                });
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
