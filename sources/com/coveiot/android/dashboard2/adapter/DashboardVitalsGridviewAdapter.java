package com.coveiot.android.dashboard2.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.dashboard2.R;
import com.coveiot.android.dashboard2.model.EnergyMeterData;
import com.coveiot.android.dashboard2.model.FitnessVitalData;
import com.coveiot.android.dashboard2.model.FitnessVitalsDataModel;
import com.coveiot.android.dashboard2.model.HRVData;
import com.coveiot.android.dashboard2.model.HeartRateData;
import com.coveiot.android.dashboard2.model.RespiratoryRateData;
import com.coveiot.android.dashboard2.model.SPO2Data;
import com.coveiot.android.dashboard2.model.StressData;
import com.coveiot.android.dashboard2.model.TemperatureData;
import com.coveiot.android.dashboard2.util.Dashboard2Utils;
import com.coveiot.android.dashboard2.util.FitnessVitalsHelper;
import com.coveiot.android.dashboard2.util.HRVDataHelper;
import com.coveiot.android.dashboard2.util.StressDataHelper;
import com.coveiot.android.idoSdk.IDOConstants;
import com.coveiot.android.theme.databinding.LayoutDashboardVitalGridItemBinding;
import com.coveiot.covedb.deviceinfo.DeviceInfoRepository;
import com.coveiot.covedb.deviceinfo.EntityDeviceInfo;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.utils.utility.ecg.EcgStyleReportUtil;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class DashboardVitalsGridviewAdapter extends RecyclerView.Adapter<DashboardVitalsViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public List<FitnessVitalsDataModel> f4220a;
    @NotNull
    public Context b;
    @Nullable
    public ItemClickListener c;

    /* loaded from: classes4.dex */
    public static final class DashboardVitalsViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final LayoutDashboardVitalGridItemBinding f4221a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public DashboardVitalsViewHolder(@NotNull LayoutDashboardVitalGridItemBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.f4221a = binding;
        }

        @NotNull
        public final LayoutDashboardVitalGridItemBinding getBinding() {
            return this.f4221a;
        }
    }

    /* loaded from: classes4.dex */
    public interface ItemClickListener {
        void onFitnessVitalItemClicked(@NotNull String str);
    }

    public DashboardVitalsGridviewAdapter(@NotNull List<FitnessVitalsDataModel> fitnessVitalsDateModelList, @NotNull Context context) {
        Intrinsics.checkNotNullParameter(fitnessVitalsDateModelList, "fitnessVitalsDateModelList");
        Intrinsics.checkNotNullParameter(context, "context");
        this.f4220a = fitnessVitalsDateModelList;
        this.b = context;
    }

    public static final void b(DashboardVitalsGridviewAdapter this$0, Ref.ObjectRef fitnessVitalData, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(fitnessVitalData, "$fitnessVitalData");
        ItemClickListener itemClickListener = this$0.c;
        if (itemClickListener != null) {
            itemClickListener.onFitnessVitalItemClicked(((FitnessVitalsDataModel) fitnessVitalData.element).getFitnessVitalType());
        }
    }

    @NotNull
    public final Context getContext() {
        return this.b;
    }

    @Nullable
    public final ItemClickListener getFitnessVitalClickListener() {
        return this.c;
    }

    @NotNull
    public final List<FitnessVitalsDataModel> getFitnessVitalsDateModelList() {
        return this.f4220a;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.f4220a.size();
    }

    public final void setContext(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "<set-?>");
        this.b = context;
    }

    public final void setFitnessVitalClickListener(@Nullable ItemClickListener itemClickListener) {
        this.c = itemClickListener;
    }

    public final void setFitnessVitalsDateModelList(@NotNull List<FitnessVitalsDataModel> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.f4220a = list;
    }

    /* JADX WARN: Type inference failed for: r9v1, types: [T, java.lang.Object] */
    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull DashboardVitalsViewHolder holder, int i) {
        String str;
        String string;
        Intrinsics.checkNotNullParameter(holder, "holder");
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        ?? r9 = this.f4220a.get(i);
        objectRef.element = r9;
        if (((FitnessVitalsDataModel) r9).isDataAvailable()) {
            holder.getBinding().fitnessVitalWithData.setIsDataAvailable(Boolean.TRUE);
            holder.getBinding().fitnessVitalWithData.tvVitalName.setText(((FitnessVitalsDataModel) objectRef.element).getTitle());
            holder.getBinding().fitnessVitalWithData.ivVitalImage.setVisibility(0);
            holder.getBinding().fitnessVitalWithData.speedViewStress.setVisibility(8);
            holder.getBinding().fitnessVitalWithData.ivVitalImage.setImageDrawable(FitnessVitalsHelper.INSTANCE.getFitnessVitalDrawable(this.b, ((FitnessVitalsDataModel) objectRef.element).getFitnessVitalType()));
            if (((FitnessVitalsDataModel) objectRef.element).getFitnessData() != null) {
                String fitnessVitalType = ((FitnessVitalsDataModel) objectRef.element).getFitnessVitalType();
                switch (fitnessVitalType.hashCode()) {
                    case -1962477464:
                        str = "AMBIENT_SOUND";
                        fitnessVitalType.equals(str);
                        break;
                    case -1849873063:
                        if (fitnessVitalType.equals(IDOConstants.DATA_TYPE_HEART_RATE)) {
                            holder.getBinding().fitnessVitalWithData.clLevel.setVisibility(8);
                            holder.getBinding().fitnessVitalWithData.tvVitalValueUnit.setVisibility(0);
                            TextView textView = holder.getBinding().fitnessVitalWithData.tvVitalValue;
                            FitnessVitalData fitnessData = ((FitnessVitalsDataModel) objectRef.element).getFitnessData();
                            Intrinsics.checkNotNull(fitnessData, "null cannot be cast to non-null type com.coveiot.android.dashboard2.model.HeartRateData");
                            textView.setText(String.valueOf(((HeartRateData) fitnessData).getValue()));
                            holder.getBinding().fitnessVitalWithData.tvVitalValueUnit.setText(this.b.getString(R.string.bpm_small));
                            TextView textView2 = holder.getBinding().fitnessVitalWithData.tvVitalLastUpdatedTime;
                            Dashboard2Utils.Companion companion = Dashboard2Utils.Companion;
                            Context context = this.b;
                            FitnessVitalData fitnessData2 = ((FitnessVitalsDataModel) objectRef.element).getFitnessData();
                            Intrinsics.checkNotNull(fitnessData2, "null cannot be cast to non-null type com.coveiot.android.dashboard2.model.HeartRateData");
                            textView2.setText(companion.getLastUpdatedTimeToShow(context, ((HeartRateData) fitnessData2).getTimestamp()));
                            break;
                        }
                        break;
                    case -1838660172:
                        if (fitnessVitalType.equals("STRESS")) {
                            holder.getBinding().fitnessVitalWithData.clLevel.setVisibility(0);
                            holder.getBinding().fitnessVitalWithData.tvVitalValueUnit.setVisibility(8);
                            TextView textView3 = holder.getBinding().fitnessVitalWithData.tvVitalValue;
                            FitnessVitalData fitnessData3 = ((FitnessVitalsDataModel) objectRef.element).getFitnessData();
                            Intrinsics.checkNotNull(fitnessData3, "null cannot be cast to non-null type com.coveiot.android.dashboard2.model.StressData");
                            textView3.setText(String.valueOf(((StressData) fitnessData3).getValue()));
                            TextView textView4 = holder.getBinding().fitnessVitalWithData.tvVitalLevel;
                            FitnessVitalData fitnessData4 = ((FitnessVitalsDataModel) objectRef.element).getFitnessData();
                            Intrinsics.checkNotNull(fitnessData4, "null cannot be cast to non-null type com.coveiot.android.dashboard2.model.StressData");
                            textView4.setText(((StressData) fitnessData4).getLevel());
                            TextView textView5 = holder.getBinding().fitnessVitalWithData.tvVitalLastUpdatedTime;
                            Dashboard2Utils.Companion companion2 = Dashboard2Utils.Companion;
                            Context context2 = this.b;
                            FitnessVitalData fitnessData5 = ((FitnessVitalsDataModel) objectRef.element).getFitnessData();
                            Intrinsics.checkNotNull(fitnessData5, "null cannot be cast to non-null type com.coveiot.android.dashboard2.model.StressData");
                            textView5.setText(companion2.getLastUpdatedTimeToShow(context2, ((StressData) fitnessData5).getTimestamp()));
                            holder.getBinding().fitnessVitalWithData.ivVitalImage.setVisibility(0);
                            TextView textView6 = holder.getBinding().fitnessVitalWithData.tvVitalLevel;
                            StressDataHelper stressDataHelper = StressDataHelper.INSTANCE;
                            Context context3 = this.b;
                            FitnessVitalData fitnessData6 = ((FitnessVitalsDataModel) objectRef.element).getFitnessData();
                            Intrinsics.checkNotNull(fitnessData6, "null cannot be cast to non-null type com.coveiot.android.dashboard2.model.StressData");
                            textView6.setTextColor(Color.parseColor(stressDataHelper.getStressTextColor(context3, ((StressData) fitnessData6).getValue())));
                            break;
                        }
                        break;
                    case -1820305068:
                        if (fitnessVitalType.equals("TEMPERATURE")) {
                            holder.getBinding().fitnessVitalWithData.clLevel.setVisibility(8);
                            holder.getBinding().fitnessVitalWithData.tvVitalValueUnit.setVisibility(0);
                            Boolean temperatureInFahrenheit = UserDataManager.getInstance(this.b).isTemperatureUnitInFahrenheit();
                            Intrinsics.checkNotNullExpressionValue(temperatureInFahrenheit, "temperatureInFahrenheit");
                            if (temperatureInFahrenheit.booleanValue()) {
                                string = this.b.getResources().getString(R.string.fahrenheit_symbol);
                            } else {
                                string = this.b.getResources().getString(R.string.celcis_symbol);
                            }
                            Intrinsics.checkNotNullExpressionValue(string, "if (temperatureInFahrenh…                        }");
                            holder.getBinding().fitnessVitalWithData.tvVitalValueUnit.setText(string);
                            TextView textView7 = holder.getBinding().fitnessVitalWithData.tvVitalValue;
                            FitnessVitalData fitnessData7 = ((FitnessVitalsDataModel) objectRef.element).getFitnessData();
                            Intrinsics.checkNotNull(fitnessData7, "null cannot be cast to non-null type com.coveiot.android.dashboard2.model.TemperatureData");
                            textView7.setText(String.valueOf(((TemperatureData) fitnessData7).getValue()));
                            TextView textView8 = holder.getBinding().fitnessVitalWithData.tvVitalLastUpdatedTime;
                            Dashboard2Utils.Companion companion3 = Dashboard2Utils.Companion;
                            Context context4 = this.b;
                            FitnessVitalData fitnessData8 = ((FitnessVitalsDataModel) objectRef.element).getFitnessData();
                            Intrinsics.checkNotNull(fitnessData8, "null cannot be cast to non-null type com.coveiot.android.dashboard2.model.TemperatureData");
                            textView8.setText(companion3.getLastUpdatedTimeToShow(context4, ((TemperatureData) fitnessData8).getTimestamp()));
                            break;
                        }
                        break;
                    case -1606469902:
                        if (fitnessVitalType.equals("ENERGY_METER")) {
                            holder.getBinding().fitnessVitalWithData.clLevel.setVisibility(8);
                            holder.getBinding().fitnessVitalWithData.tvVitalValueUnit.setVisibility(8);
                            TextView textView9 = holder.getBinding().fitnessVitalWithData.tvVitalValue;
                            FitnessVitalData fitnessData9 = ((FitnessVitalsDataModel) objectRef.element).getFitnessData();
                            Intrinsics.checkNotNull(fitnessData9, "null cannot be cast to non-null type com.coveiot.android.dashboard2.model.EnergyMeterData");
                            textView9.setText(String.valueOf(((EnergyMeterData) fitnessData9).getValue()));
                            EntityDeviceInfo deviceInfoBy = DeviceInfoRepository.getInstance(this.b).getDeviceInfoBy(BleApiManager.getInstance(this.b).getBleApi().getMacAddress());
                            if (deviceInfoBy != null) {
                                deviceInfoBy.getLasySyncTime();
                                if (deviceInfoBy.getLasySyncTime() > 0) {
                                    holder.getBinding().fitnessVitalWithData.tvVitalLastUpdatedTime.setText(Dashboard2Utils.Companion.getLastUpdatedTimeToShow(this.b, deviceInfoBy.getLasySyncTime()));
                                    break;
                                }
                            }
                            TextView textView10 = holder.getBinding().fitnessVitalWithData.tvVitalLastUpdatedTime;
                            Dashboard2Utils.Companion companion4 = Dashboard2Utils.Companion;
                            Context context5 = this.b;
                            FitnessVitalData fitnessData10 = ((FitnessVitalsDataModel) objectRef.element).getFitnessData();
                            Intrinsics.checkNotNull(fitnessData10, "null cannot be cast to non-null type com.coveiot.android.dashboard2.model.EnergyMeterData");
                            textView10.setText(companion4.getLastUpdatedTimeToShow(context5, ((EnergyMeterData) fitnessData10).getTimestamp()));
                            break;
                        }
                        break;
                    case 2126:
                        str = EcgStyleReportUtil.UserInfoKey.BP;
                        fitnessVitalType.equals(str);
                        break;
                    case 71820:
                        if (fitnessVitalType.equals("HRV")) {
                            holder.getBinding().fitnessVitalWithData.clLevel.setVisibility(8);
                            holder.getBinding().fitnessVitalWithData.tvVitalValueUnit.setVisibility(0);
                            TextView textView11 = holder.getBinding().fitnessVitalWithData.tvVitalValue;
                            HRVDataHelper hRVDataHelper = HRVDataHelper.INSTANCE;
                            FitnessVitalData fitnessData11 = ((FitnessVitalsDataModel) objectRef.element).getFitnessData();
                            Intrinsics.checkNotNull(fitnessData11, "null cannot be cast to non-null type com.coveiot.android.dashboard2.model.HRVData");
                            textView11.setText(hRVDataHelper.calculationFormulaHRV(((HRVData) fitnessData11).getValue()));
                            holder.getBinding().fitnessVitalWithData.tvVitalValueUnit.setText(this.b.getString(R.string.ms));
                            TextView textView12 = holder.getBinding().fitnessVitalWithData.tvVitalLastUpdatedTime;
                            Dashboard2Utils.Companion companion5 = Dashboard2Utils.Companion;
                            Context context6 = this.b;
                            FitnessVitalData fitnessData12 = ((FitnessVitalsDataModel) objectRef.element).getFitnessData();
                            Intrinsics.checkNotNull(fitnessData12, "null cannot be cast to non-null type com.coveiot.android.dashboard2.model.HRVData");
                            textView12.setText(companion5.getLastUpdatedTimeToShow(context6, ((HRVData) fitnessData12).getTimestamp()));
                            break;
                        }
                        break;
                    case 77086:
                        if (fitnessVitalType.equals("NBR")) {
                            holder.getBinding().fitnessVitalWithData.clLevel.setVisibility(8);
                            holder.getBinding().fitnessVitalWithData.tvVitalValueUnit.setVisibility(0);
                            holder.getBinding().fitnessVitalWithData.tvVitalValueUnit.setText(this.b.getString(R.string.brpm));
                            TextView textView13 = holder.getBinding().fitnessVitalWithData.tvVitalValue;
                            FitnessVitalData fitnessData13 = ((FitnessVitalsDataModel) objectRef.element).getFitnessData();
                            Intrinsics.checkNotNull(fitnessData13, "null cannot be cast to non-null type com.coveiot.android.dashboard2.model.RespiratoryRateData");
                            textView13.setText(String.valueOf(((RespiratoryRateData) fitnessData13).getValue()));
                            TextView textView14 = holder.getBinding().fitnessVitalWithData.tvVitalLastUpdatedTime;
                            Dashboard2Utils.Companion companion6 = Dashboard2Utils.Companion;
                            Context context7 = this.b;
                            FitnessVitalData fitnessData14 = ((FitnessVitalsDataModel) objectRef.element).getFitnessData();
                            Intrinsics.checkNotNull(fitnessData14, "null cannot be cast to non-null type com.coveiot.android.dashboard2.model.RespiratoryRateData");
                            textView14.setText(companion6.getLastUpdatedTimeToShow(context7, ((RespiratoryRateData) fitnessData14).getTimestamp()));
                            break;
                        }
                        break;
                    case 2552032:
                        if (fitnessVitalType.equals("SPO2")) {
                            holder.getBinding().fitnessVitalWithData.clLevel.setVisibility(8);
                            holder.getBinding().fitnessVitalWithData.tvVitalValueUnit.setVisibility(0);
                            TextView textView15 = holder.getBinding().fitnessVitalWithData.tvVitalValue;
                            FitnessVitalData fitnessData15 = ((FitnessVitalsDataModel) objectRef.element).getFitnessData();
                            Intrinsics.checkNotNull(fitnessData15, "null cannot be cast to non-null type com.coveiot.android.dashboard2.model.SPO2Data");
                            textView15.setText(String.valueOf((int) ((SPO2Data) fitnessData15).getValue()));
                            holder.getBinding().fitnessVitalWithData.tvVitalValueUnit.setText("%");
                            TextView textView16 = holder.getBinding().fitnessVitalWithData.tvVitalLastUpdatedTime;
                            Dashboard2Utils.Companion companion7 = Dashboard2Utils.Companion;
                            Context context8 = this.b;
                            FitnessVitalData fitnessData16 = ((FitnessVitalsDataModel) objectRef.element).getFitnessData();
                            Intrinsics.checkNotNull(fitnessData16, "null cannot be cast to non-null type com.coveiot.android.dashboard2.model.SPO2Data");
                            textView16.setText(companion7.getLastUpdatedTimeToShow(context8, ((SPO2Data) fitnessData16).getTimestamp()));
                            break;
                        }
                        break;
                }
            }
        } else {
            holder.getBinding().fitnessVitalWithData.setIsDataAvailable(Boolean.FALSE);
            holder.getBinding().fitnessVitalWithData.ivIcon.setImageDrawable(FitnessVitalsHelper.INSTANCE.getFitnessVitalDrawable(this.b, ((FitnessVitalsDataModel) objectRef.element).getFitnessVitalType()));
            holder.getBinding().fitnessVitalWithData.tvHeader.setText(((FitnessVitalsDataModel) objectRef.element).getTitle());
            holder.getBinding().fitnessVitalWithData.tvInfo.setText(((FitnessVitalsDataModel) objectRef.element).getInfoText());
        }
        holder.getBinding().fitnessVitalWithData.getRoot().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.dashboard2.adapter.f
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DashboardVitalsGridviewAdapter.b(DashboardVitalsGridviewAdapter.this, objectRef, view);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public DashboardVitalsViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        LayoutDashboardVitalGridItemBinding inflate = LayoutDashboardVitalGridItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(\n               …      false\n            )");
        return new DashboardVitalsViewHolder(inflate);
    }
}
