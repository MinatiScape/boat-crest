package com.coveiot.android.dashboard2.viewmodel;

import androidx.lifecycle.ViewModelKt;
import com.coveiot.android.dashboard2.Dashboard2PreferenceManager;
import com.coveiot.android.dashboard2.model.FitnessVitalsDataModel;
import com.coveiot.android.dashboard2.model.SelectedFitnessVitalsDataModel;
import com.coveiot.android.dashboard2.util.FitnessVitalsHelper;
import com.coveiot.android.idoSdk.IDOConstants;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.CoveUserDeviceSettings;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.userdevicesetting.GetAllUserDeviceSettingRes;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.coveiot.utils.utility.ecg.EcgStyleReportUtil;
import java.util.Iterator;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "com.coveiot.android.dashboard2.viewmodel.FragmentHomeViewModel$getSelectedFitnessVitals$1", f = "FragmentHomeViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes4.dex */
public final class FragmentHomeViewModel$getSelectedFitnessVitals$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public int label;
    public final /* synthetic */ FragmentHomeViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FragmentHomeViewModel$getSelectedFitnessVitals$1(FragmentHomeViewModel fragmentHomeViewModel, Continuation<? super FragmentHomeViewModel$getSelectedFitnessVitals$1> continuation) {
        super(2, continuation);
        this.this$0 = fragmentHomeViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new FragmentHomeViewModel$getSelectedFitnessVitals$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((FragmentHomeViewModel$getSelectedFitnessVitals$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            if (Dashboard2PreferenceManager.Companion.getInstance(this.this$0.getContext()).getLastVitalSequenceApiCallTimestamp() > 0) {
                this.this$0.m();
            } else if (!AppUtils.isNetConnected(this.this$0.getContext())) {
                this.this$0.m();
            } else {
                final FragmentHomeViewModel fragmentHomeViewModel = this.this$0;
                CoveUserDeviceSettings.getAllUserDeviceSettings(new CoveApiListener<GetAllUserDeviceSettingRes, CoveApiErrorModel>() { // from class: com.coveiot.android.dashboard2.viewmodel.FragmentHomeViewModel$getSelectedFitnessVitals$1.1

                    @DebugMetadata(c = "com.coveiot.android.dashboard2.viewmodel.FragmentHomeViewModel$getSelectedFitnessVitals$1$1$onSuccess$1", f = "FragmentHomeViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                    /* renamed from: com.coveiot.android.dashboard2.viewmodel.FragmentHomeViewModel$getSelectedFitnessVitals$1$1$a */
                    /* loaded from: classes4.dex */
                    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                        public final /* synthetic */ GetAllUserDeviceSettingRes $p0;
                        public int label;
                        public final /* synthetic */ FragmentHomeViewModel this$0;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        public a(GetAllUserDeviceSettingRes getAllUserDeviceSettingRes, FragmentHomeViewModel fragmentHomeViewModel, Continuation<? super a> continuation) {
                            super(2, continuation);
                            this.$p0 = getAllUserDeviceSettingRes;
                            this.this$0 = fragmentHomeViewModel;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        @NotNull
                        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                            return new a(this.$p0, this.this$0, continuation);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        @Nullable
                        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                            return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        @Nullable
                        public final Object invokeSuspend(@NotNull Object obj) {
                            Iterator<String> it;
                            GetAllUserDeviceSettingRes.DataBean dataBean;
                            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                            if (this.label == 0) {
                                ResultKt.throwOnFailure(obj);
                                GetAllUserDeviceSettingRes getAllUserDeviceSettingRes = this.$p0;
                                if (getAllUserDeviceSettingRes != null) {
                                    if ((getAllUserDeviceSettingRes != null ? getAllUserDeviceSettingRes.getDataBean() : null) != null) {
                                        GetAllUserDeviceSettingRes getAllUserDeviceSettingRes2 = this.$p0;
                                        if (((getAllUserDeviceSettingRes2 == null || (dataBean = getAllUserDeviceSettingRes2.getDataBean()) == null) ? null : dataBean.getAppDashboard()) != null && this.$p0.getDataBean().getAppDashboard().getActiveVitalCards() != null) {
                                            SelectedFitnessVitalsDataModel selectedFitnessVitalsDataModel = new SelectedFitnessVitalsDataModel(null, 1, null);
                                            Iterator<String> it2 = this.$p0.getDataBean().getAppDashboard().getActiveVitalCards().iterator();
                                            while (it2.hasNext()) {
                                                String next = it2.next();
                                                if (next != null) {
                                                    switch (next.hashCode()) {
                                                        case -1838660172:
                                                            it = it2;
                                                            if (next.equals("STRESS")) {
                                                                List<FitnessVitalsDataModel> fitnessDataModels = selectedFitnessVitalsDataModel.getFitnessDataModels();
                                                                FitnessVitalsHelper fitnessVitalsHelper = FitnessVitalsHelper.INSTANCE;
                                                                fitnessDataModels.add(new FitnessVitalsDataModel("STRESS", true, false, null, fitnessVitalsHelper.getFitnessVitalTitle(this.this$0.getContext(), "STRESS"), 0, 0, fitnessVitalsHelper.getFitnessVitalInfoText(this.this$0.getContext(), "STRESS"), 108, null));
                                                            }
                                                            it2 = it;
                                                            break;
                                                        case -1606469902:
                                                            it = it2;
                                                            if (next.equals("ENERGY_METER")) {
                                                                List<FitnessVitalsDataModel> fitnessDataModels2 = selectedFitnessVitalsDataModel.getFitnessDataModels();
                                                                FitnessVitalsHelper fitnessVitalsHelper2 = FitnessVitalsHelper.INSTANCE;
                                                                fitnessDataModels2.add(new FitnessVitalsDataModel("ENERGY_METER", true, false, null, fitnessVitalsHelper2.getFitnessVitalTitle(this.this$0.getContext(), "ENERGY_METER"), 0, 0, fitnessVitalsHelper2.getFitnessVitalInfoText(this.this$0.getContext(), "ENERGY_METER"), 108, null));
                                                            }
                                                            it2 = it;
                                                            break;
                                                        case -692316348:
                                                            it = it2;
                                                            next.equals("SED_ALERTS");
                                                            it2 = it;
                                                            break;
                                                        case 2126:
                                                            it = it2;
                                                            if (next.equals(EcgStyleReportUtil.UserInfoKey.BP)) {
                                                                List<FitnessVitalsDataModel> fitnessDataModels3 = selectedFitnessVitalsDataModel.getFitnessDataModels();
                                                                FitnessVitalsHelper fitnessVitalsHelper3 = FitnessVitalsHelper.INSTANCE;
                                                                fitnessDataModels3.add(new FitnessVitalsDataModel(EcgStyleReportUtil.UserInfoKey.BP, true, false, null, fitnessVitalsHelper3.getFitnessVitalTitle(this.this$0.getContext(), EcgStyleReportUtil.UserInfoKey.BP), 0, 0, fitnessVitalsHelper3.getFitnessVitalInfoText(this.this$0.getContext(), EcgStyleReportUtil.UserInfoKey.BP), 108, null));
                                                            }
                                                            it2 = it;
                                                            break;
                                                        case 2314:
                                                            it = it2;
                                                            if (next.equals(EcgStyleReportUtil.UserInfoKey.HR)) {
                                                                List<FitnessVitalsDataModel> fitnessDataModels4 = selectedFitnessVitalsDataModel.getFitnessDataModels();
                                                                FitnessVitalsHelper fitnessVitalsHelper4 = FitnessVitalsHelper.INSTANCE;
                                                                fitnessDataModels4.add(new FitnessVitalsDataModel(IDOConstants.DATA_TYPE_HEART_RATE, true, false, null, fitnessVitalsHelper4.getFitnessVitalTitle(this.this$0.getContext(), IDOConstants.DATA_TYPE_HEART_RATE), 0, 0, fitnessVitalsHelper4.getFitnessVitalInfoText(this.this$0.getContext(), IDOConstants.DATA_TYPE_HEART_RATE), 108, null));
                                                            }
                                                            it2 = it;
                                                            break;
                                                        case 71820:
                                                            it = it2;
                                                            if (next.equals("HRV")) {
                                                                List<FitnessVitalsDataModel> fitnessDataModels5 = selectedFitnessVitalsDataModel.getFitnessDataModels();
                                                                FitnessVitalsHelper fitnessVitalsHelper5 = FitnessVitalsHelper.INSTANCE;
                                                                fitnessDataModels5.add(new FitnessVitalsDataModel("HRV", true, false, null, fitnessVitalsHelper5.getFitnessVitalTitle(this.this$0.getContext(), "HRV"), 0, 0, fitnessVitalsHelper5.getFitnessVitalInfoText(this.this$0.getContext(), "HRV"), 108, null));
                                                            }
                                                            it2 = it;
                                                            break;
                                                        case 2552032:
                                                            it = it2;
                                                            if (next.equals("SPO2")) {
                                                                List<FitnessVitalsDataModel> fitnessDataModels6 = selectedFitnessVitalsDataModel.getFitnessDataModels();
                                                                FitnessVitalsHelper fitnessVitalsHelper6 = FitnessVitalsHelper.INSTANCE;
                                                                fitnessDataModels6.add(new FitnessVitalsDataModel("SPO2", true, false, null, fitnessVitalsHelper6.getFitnessVitalTitle(this.this$0.getContext(), "SPO2"), 0, 0, fitnessVitalsHelper6.getFitnessVitalInfoText(this.this$0.getContext(), "SPO2"), 108, null));
                                                            }
                                                            it2 = it;
                                                            break;
                                                        case 78984887:
                                                            it = it2;
                                                            if (next.equals("SLEEP")) {
                                                                List<FitnessVitalsDataModel> fitnessDataModels7 = selectedFitnessVitalsDataModel.getFitnessDataModels();
                                                                FitnessVitalsHelper fitnessVitalsHelper7 = FitnessVitalsHelper.INSTANCE;
                                                                fitnessDataModels7.add(new FitnessVitalsDataModel("SLEEP", true, false, null, fitnessVitalsHelper7.getFitnessVitalTitle(this.this$0.getContext(), "SLEEP"), 0, 0, fitnessVitalsHelper7.getFitnessVitalInfoText(this.this$0.getContext(), "SLEEP"), 108, null));
                                                            }
                                                            it2 = it;
                                                            break;
                                                        case 79223559:
                                                            if (next.equals("STEPS")) {
                                                                List<FitnessVitalsDataModel> fitnessDataModels8 = selectedFitnessVitalsDataModel.getFitnessDataModels();
                                                                FitnessVitalsHelper fitnessVitalsHelper8 = FitnessVitalsHelper.INSTANCE;
                                                                it = it2;
                                                                fitnessDataModels8.add(new FitnessVitalsDataModel("STEPS", true, false, null, fitnessVitalsHelper8.getFitnessVitalTitle(this.this$0.getContext(), "STEPS"), 0, 0, fitnessVitalsHelper8.getFitnessVitalInfoText(this.this$0.getContext(), "STEPS"), 108, null));
                                                                it2 = it;
                                                            }
                                                            break;
                                                        case 993297975:
                                                            if (next.equals("BODY_TEMPERATURE")) {
                                                                List<FitnessVitalsDataModel> fitnessDataModels9 = selectedFitnessVitalsDataModel.getFitnessDataModels();
                                                                FitnessVitalsHelper fitnessVitalsHelper9 = FitnessVitalsHelper.INSTANCE;
                                                                fitnessDataModels9.add(new FitnessVitalsDataModel("TEMPERATURE", true, false, null, fitnessVitalsHelper9.getFitnessVitalTitle(this.this$0.getContext(), "TEMPERATURE"), 0, 0, fitnessVitalsHelper9.getFitnessVitalInfoText(this.this$0.getContext(), "TEMPERATURE"), 108, null));
                                                            }
                                                            break;
                                                        case 1781975957:
                                                            if (!next.equals("RESPIRATORY_RATE")) {
                                                                break;
                                                            } else {
                                                                List<FitnessVitalsDataModel> fitnessDataModels10 = selectedFitnessVitalsDataModel.getFitnessDataModels();
                                                                FitnessVitalsHelper fitnessVitalsHelper10 = FitnessVitalsHelper.INSTANCE;
                                                                fitnessDataModels10.add(new FitnessVitalsDataModel("NBR", true, false, null, fitnessVitalsHelper10.getFitnessVitalTitle(this.this$0.getContext(), "NBR"), 0, 0, fitnessVitalsHelper10.getFitnessVitalInfoText(this.this$0.getContext(), "NBR"), 108, null));
                                                                break;
                                                            }
                                                        default:
                                                            it = it2;
                                                            it2 = it;
                                                            break;
                                                    }
                                                }
                                            }
                                            List<FitnessVitalsDataModel> fitnessDataModels11 = selectedFitnessVitalsDataModel.getFitnessDataModels();
                                            if (!(fitnessDataModels11 == null || fitnessDataModels11.isEmpty())) {
                                                Dashboard2PreferenceManager.Companion.getInstance(this.this$0.getContext()).saveSelectedFitnessVitals(selectedFitnessVitalsDataModel);
                                            }
                                        }
                                    }
                                }
                                this.this$0.m();
                                Dashboard2PreferenceManager.Companion.getInstance(this.this$0.getContext()).saveLastVitalSequenceApiCallTimestamp(System.currentTimeMillis());
                                return Unit.INSTANCE;
                            }
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                    }

                    @Override // com.coveiot.coveaccess.CoveApiListener
                    public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                        String str;
                        str = FragmentHomeViewModel.this.b;
                        LogHelper.d(str, "getSelectedFitnessVitals failed");
                        FragmentHomeViewModel.this.m();
                    }

                    @Override // com.coveiot.coveaccess.CoveApiListener
                    public void onSuccess(@Nullable GetAllUserDeviceSettingRes getAllUserDeviceSettingRes) {
                        String str;
                        str = FragmentHomeViewModel.this.b;
                        LogHelper.d(str, "getSelectedFitnessVitals success");
                        kotlinx.coroutines.e.e(ViewModelKt.getViewModelScope(FragmentHomeViewModel.this), Dispatchers.getIO(), null, new a(getAllUserDeviceSettingRes, FragmentHomeViewModel.this, null), 2, null);
                    }
                });
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
