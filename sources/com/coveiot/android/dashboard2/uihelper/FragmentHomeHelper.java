package com.coveiot.android.dashboard2.uihelper;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import com.coveiot.android.dashboard2.R;
import com.coveiot.android.dashboard2.adapter.DashboardVitalsGridviewAdapter;
import com.coveiot.android.dashboard2.databinding.FragmentHomeBinding;
import com.coveiot.android.dashboard2.listener.DoMoreWithYourWatchItemClickListener;
import com.coveiot.android.dashboard2.model.DoMoreWithYourWatchDataModel;
import com.coveiot.android.dashboard2.model.DoMoreWithYourWatchFeatureType;
import com.coveiot.android.dashboard2.model.EnergyMeterData;
import com.coveiot.android.dashboard2.model.FitnessVitalData;
import com.coveiot.android.dashboard2.model.HRVData;
import com.coveiot.android.dashboard2.model.HeartRateData;
import com.coveiot.android.dashboard2.model.RespiratoryRateData;
import com.coveiot.android.dashboard2.model.SPO2Data;
import com.coveiot.android.dashboard2.model.Sections;
import com.coveiot.android.dashboard2.model.StressData;
import com.coveiot.android.dashboard2.model.TemperatureData;
import com.coveiot.android.dashboard2.util.Dashboard2Utils;
import com.coveiot.android.dashboard2.util.DoMoreWithYourWatchHelper;
import com.coveiot.android.dashboard2.util.FitnessVitalsHelper;
import com.coveiot.android.dashboard2.util.HRVDataHelper;
import com.coveiot.android.dashboard2.util.StressDataHelper;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.idoSdk.IDOConstants;
import com.coveiot.android.theme.CleverTapConstants;
import com.coveiot.android.theme.databinding.DoMoreWithYourWatchCardContainerDashboardBinding;
import com.coveiot.android.weather.weather.WeatherAppPreferenceManager;
import com.coveiot.android.weathersdk.WeatherPreferenceManager;
import com.coveiot.android.weathersdk.response.currentweathermodel.CurrentForecastModel;
import com.coveiot.android.weathersdk.response.currentweathermodel.CurrentWeather;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.utils.utility.AppUtils;
import java.util.List;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class FragmentHomeHelper {

    /* loaded from: classes4.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[DoMoreWithYourWatchFeatureType.values().length];
            try {
                iArr[DoMoreWithYourWatchFeatureType.WELLNESS_CREW_SETUP.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[DoMoreWithYourWatchFeatureType.SPORT_SCORE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[DoMoreWithYourWatchFeatureType.BUILD_FITNESS_PLAN.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[DoMoreWithYourWatchFeatureType.SETUP_ALEXA_CONNECT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[DoMoreWithYourWatchFeatureType.TAP_AND_PAY.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[DoMoreWithYourWatchFeatureType.SOS_SETTINGS.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final void i(DoMoreWithYourWatchItemClickListener doMoreWithYourWatchItemClickListener, DoMoreWithYourWatchDataModel doMoreWithYourWatchDataModel, View view) {
        Intrinsics.checkNotNullParameter(doMoreWithYourWatchItemClickListener, "$doMoreWithYourWatchItemClickListener");
        Intrinsics.checkNotNullParameter(doMoreWithYourWatchDataModel, "$doMoreWithYourWatchDataModel");
        DeviceUtils.Companion.logAnalyticsEvent(CleverTapConstants.EventName.BC_HP_CREWSETUP_TAPPED.getValue(), null);
        doMoreWithYourWatchItemClickListener.doMoreWithYourWatchItemClicked(doMoreWithYourWatchDataModel.getDoMoreWithYourWatchType());
    }

    public static final void j(DoMoreWithYourWatchItemClickListener doMoreWithYourWatchItemClickListener, DoMoreWithYourWatchDataModel doMoreWithYourWatchDataModel, View view) {
        Intrinsics.checkNotNullParameter(doMoreWithYourWatchItemClickListener, "$doMoreWithYourWatchItemClickListener");
        Intrinsics.checkNotNullParameter(doMoreWithYourWatchDataModel, "$doMoreWithYourWatchDataModel");
        DeviceUtils.Companion.logAnalyticsEvent(CleverTapConstants.EventName.BC_HP_SPORTSCORES_TAPPED.getValue(), null);
        doMoreWithYourWatchItemClickListener.doMoreWithYourWatchItemClicked(doMoreWithYourWatchDataModel.getDoMoreWithYourWatchType());
    }

    public static final void k(DoMoreWithYourWatchItemClickListener doMoreWithYourWatchItemClickListener, DoMoreWithYourWatchDataModel doMoreWithYourWatchDataModel, View view) {
        Intrinsics.checkNotNullParameter(doMoreWithYourWatchItemClickListener, "$doMoreWithYourWatchItemClickListener");
        Intrinsics.checkNotNullParameter(doMoreWithYourWatchDataModel, "$doMoreWithYourWatchDataModel");
        DeviceUtils.Companion.logAnalyticsEvent(CleverTapConstants.EventName.BC_HP_FITNESSPLAN_TAPPED.getValue(), null);
        doMoreWithYourWatchItemClickListener.doMoreWithYourWatchItemClicked(doMoreWithYourWatchDataModel.getDoMoreWithYourWatchType());
    }

    public static final void l(DoMoreWithYourWatchItemClickListener doMoreWithYourWatchItemClickListener, DoMoreWithYourWatchDataModel doMoreWithYourWatchDataModel, View view) {
        Intrinsics.checkNotNullParameter(doMoreWithYourWatchItemClickListener, "$doMoreWithYourWatchItemClickListener");
        Intrinsics.checkNotNullParameter(doMoreWithYourWatchDataModel, "$doMoreWithYourWatchDataModel");
        doMoreWithYourWatchItemClickListener.doMoreWithYourWatchItemClicked(doMoreWithYourWatchDataModel.getDoMoreWithYourWatchType());
    }

    public static final void m(DoMoreWithYourWatchItemClickListener doMoreWithYourWatchItemClickListener, DoMoreWithYourWatchDataModel doMoreWithYourWatchDataModel, View view) {
        Intrinsics.checkNotNullParameter(doMoreWithYourWatchItemClickListener, "$doMoreWithYourWatchItemClickListener");
        Intrinsics.checkNotNullParameter(doMoreWithYourWatchDataModel, "$doMoreWithYourWatchDataModel");
        doMoreWithYourWatchItemClickListener.doMoreWithYourWatchItemClicked(doMoreWithYourWatchDataModel.getDoMoreWithYourWatchType());
    }

    public static final void n(DoMoreWithYourWatchItemClickListener doMoreWithYourWatchItemClickListener, DoMoreWithYourWatchDataModel doMoreWithYourWatchDataModel, View view) {
        Intrinsics.checkNotNullParameter(doMoreWithYourWatchItemClickListener, "$doMoreWithYourWatchItemClickListener");
        Intrinsics.checkNotNullParameter(doMoreWithYourWatchDataModel, "$doMoreWithYourWatchDataModel");
        doMoreWithYourWatchItemClickListener.doMoreWithYourWatchItemClicked(doMoreWithYourWatchDataModel.getDoMoreWithYourWatchType());
    }

    public static final void o(DashboardVitalsGridviewAdapter.ItemClickListener itemClickListener, Ref.ObjectRef fitnessVitalType, View view) {
        Intrinsics.checkNotNullParameter(fitnessVitalType, "$fitnessVitalType");
        if (itemClickListener != null) {
            itemClickListener.onFitnessVitalItemClicked((String) fitnessVitalType.element);
        }
    }

    public static final void p(DashboardVitalsGridviewAdapter.ItemClickListener itemClickListener, Ref.ObjectRef fitnessVitalType, View view) {
        Intrinsics.checkNotNullParameter(fitnessVitalType, "$fitnessVitalType");
        if (itemClickListener != null) {
            itemClickListener.onFitnessVitalItemClicked((String) fitnessVitalType.element);
        }
    }

    @NotNull
    public final Pair<ViewGroup, ViewGroup> getPrevNextLayout(int i, @NotNull FragmentHomeBinding binding) {
        Intrinsics.checkNotNullParameter(binding, "binding");
        if (i == Sections.SYNC_STATUS.ordinal()) {
            return new Pair<>(binding.section1, binding.section2);
        }
        if (i == Sections.PROFILE.ordinal()) {
            return new Pair<>(binding.section2, binding.section3);
        }
        if (i == Sections.OLD_DYNAMIC_TAB.ordinal()) {
            return new Pair<>(binding.section3, binding.section4);
        }
        if (i == Sections.SETUP_YOUR_WATCH.ordinal()) {
            return new Pair<>(binding.section4, binding.section5);
        }
        if (i == Sections.FITNESS_OVERVIEW.ordinal()) {
            return new Pair<>(binding.section5, binding.section61);
        }
        if (i == Sections.FITNESS_DASHBOARD.ordinal()) {
            return new Pair<>(binding.section62, binding.section7);
        }
        if (i == Sections.VITALS.ordinal()) {
            return new Pair<>(binding.section7, binding.section8);
        }
        if (i == Sections.ACTIVITIES.ordinal()) {
            return new Pair<>(binding.section8, binding.section9);
        }
        if (i == Sections.FITNESS_PLAN.ordinal()) {
            return new Pair<>(binding.section9, binding.section10);
        }
        if (i == Sections.BOAT_EXCLUSIVE.ordinal()) {
            return new Pair<>(binding.section10, binding.section11);
        }
        if (i == Sections.MY_BUDDIES.ordinal()) {
            return new Pair<>(binding.section11, binding.section12);
        }
        if (i == Sections.DO_MORE_WITH_YOUR_WATCH.ordinal()) {
            return new Pair<>(binding.section12, binding.section13);
        }
        if (i == Sections.CULT_VIDEO.ordinal()) {
            return new Pair<>(binding.section13, binding.section14);
        }
        if (i == Sections.BEST_OFFER.ordinal()) {
            return new Pair<>(binding.section14, binding.section15);
        }
        if (i == Sections.APP_LOGO.ordinal()) {
            return new Pair<>(binding.section15, binding.section16);
        }
        return new Pair<>(binding.section3, binding.section4);
    }

    public final void populateDoMoreWithYourWatchData(@NotNull List<DoMoreWithYourWatchDataModel> doMoreWithYourWatchDataModels, @NotNull FragmentHomeBinding binding, @NotNull Activity activity, @NotNull final DoMoreWithYourWatchItemClickListener doMoreWithYourWatchItemClickListener) {
        Intrinsics.checkNotNullParameter(doMoreWithYourWatchDataModels, "doMoreWithYourWatchDataModels");
        Intrinsics.checkNotNullParameter(binding, "binding");
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(doMoreWithYourWatchItemClickListener, "doMoreWithYourWatchItemClickListener");
        DoMoreWithYourWatchCardContainerDashboardBinding doMoreWithYourWatchCardContainerDashboardBinding = binding.doMoreWithYourWatchCardContainer;
        Boolean bool = Boolean.FALSE;
        doMoreWithYourWatchCardContainerDashboardBinding.setShowWellnessCrew(bool);
        binding.doMoreWithYourWatchCardContainer.setShowSportScores(bool);
        binding.doMoreWithYourWatchCardContainer.setShowFitnessPlan(bool);
        binding.doMoreWithYourWatchCardContainer.setShowAlexaConnect(bool);
        binding.doMoreWithYourWatchCardContainer.setShowTapAndPay(bool);
        binding.doMoreWithYourWatchCardContainer.setShowSOSSettings(bool);
        if (doMoreWithYourWatchDataModels.isEmpty()) {
            return;
        }
        for (final DoMoreWithYourWatchDataModel doMoreWithYourWatchDataModel : doMoreWithYourWatchDataModels) {
            DoMoreWithYourWatchFeatureType doMoreWithYourWatchType = doMoreWithYourWatchDataModel.getDoMoreWithYourWatchType();
            switch (doMoreWithYourWatchType == null ? -1 : WhenMappings.$EnumSwitchMapping$0[doMoreWithYourWatchType.ordinal()]) {
                case 1:
                    binding.doMoreWithYourWatchCardContainer.setShowWellnessCrew(Boolean.TRUE);
                    binding.doMoreWithYourWatchCardContainer.wellnessCrewSetup.tvHeader.setText(doMoreWithYourWatchDataModel.getTitle());
                    binding.doMoreWithYourWatchCardContainer.wellnessCrewSetup.tvInfo.setText(doMoreWithYourWatchDataModel.getInfo());
                    ConstraintLayout constraintLayout = binding.doMoreWithYourWatchCardContainer.wellnessCrewSetup.cardBackground;
                    DoMoreWithYourWatchHelper doMoreWithYourWatchHelper = DoMoreWithYourWatchHelper.INSTANCE;
                    DoMoreWithYourWatchFeatureType doMoreWithYourWatchType2 = doMoreWithYourWatchDataModel.getDoMoreWithYourWatchType();
                    Intrinsics.checkNotNull(doMoreWithYourWatchType2);
                    constraintLayout.setBackground(doMoreWithYourWatchHelper.getDoMoreWithYourWatchBackgroundDrawable(activity, doMoreWithYourWatchType2.name()));
                    ImageView imageView = binding.doMoreWithYourWatchCardContainer.wellnessCrewSetup.ivIcon;
                    DoMoreWithYourWatchFeatureType doMoreWithYourWatchType3 = doMoreWithYourWatchDataModel.getDoMoreWithYourWatchType();
                    Intrinsics.checkNotNull(doMoreWithYourWatchType3);
                    imageView.setImageDrawable(doMoreWithYourWatchHelper.getDoMoreWithYourWatchDrawable(activity, doMoreWithYourWatchType3.name()));
                    binding.doMoreWithYourWatchCardContainer.wellnessCrewSetup.getRoot().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.dashboard2.uihelper.e
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            FragmentHomeHelper.i(DoMoreWithYourWatchItemClickListener.this, doMoreWithYourWatchDataModel, view);
                        }
                    });
                    break;
                case 2:
                    binding.doMoreWithYourWatchCardContainer.setShowSportScores(Boolean.TRUE);
                    binding.doMoreWithYourWatchCardContainer.sportScores.tvHeader.setText(doMoreWithYourWatchDataModel.getTitle());
                    binding.doMoreWithYourWatchCardContainer.sportScores.tvInfo.setText(doMoreWithYourWatchDataModel.getInfo());
                    ConstraintLayout constraintLayout2 = binding.doMoreWithYourWatchCardContainer.sportScores.cardBackground;
                    DoMoreWithYourWatchHelper doMoreWithYourWatchHelper2 = DoMoreWithYourWatchHelper.INSTANCE;
                    DoMoreWithYourWatchFeatureType doMoreWithYourWatchType4 = doMoreWithYourWatchDataModel.getDoMoreWithYourWatchType();
                    Intrinsics.checkNotNull(doMoreWithYourWatchType4);
                    constraintLayout2.setBackground(doMoreWithYourWatchHelper2.getDoMoreWithYourWatchBackgroundDrawable(activity, doMoreWithYourWatchType4.name()));
                    ImageView imageView2 = binding.doMoreWithYourWatchCardContainer.sportScores.ivIcon;
                    DoMoreWithYourWatchFeatureType doMoreWithYourWatchType5 = doMoreWithYourWatchDataModel.getDoMoreWithYourWatchType();
                    Intrinsics.checkNotNull(doMoreWithYourWatchType5);
                    imageView2.setImageDrawable(doMoreWithYourWatchHelper2.getDoMoreWithYourWatchDrawable(activity, doMoreWithYourWatchType5.name()));
                    binding.doMoreWithYourWatchCardContainer.sportScores.getRoot().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.dashboard2.uihelper.c
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            FragmentHomeHelper.j(DoMoreWithYourWatchItemClickListener.this, doMoreWithYourWatchDataModel, view);
                        }
                    });
                    break;
                case 3:
                    if (binding.clFitnessJourneyMain.getVisibility() != 0) {
                        binding.doMoreWithYourWatchCardContainer.setShowFitnessPlan(Boolean.TRUE);
                        binding.doMoreWithYourWatchCardContainer.buildFitnessPlan.tvHeader.setText(doMoreWithYourWatchDataModel.getTitle());
                        binding.doMoreWithYourWatchCardContainer.buildFitnessPlan.tvInfo.setText(doMoreWithYourWatchDataModel.getInfo());
                        ConstraintLayout constraintLayout3 = binding.doMoreWithYourWatchCardContainer.buildFitnessPlan.cardBackground;
                        DoMoreWithYourWatchHelper doMoreWithYourWatchHelper3 = DoMoreWithYourWatchHelper.INSTANCE;
                        DoMoreWithYourWatchFeatureType doMoreWithYourWatchType6 = doMoreWithYourWatchDataModel.getDoMoreWithYourWatchType();
                        Intrinsics.checkNotNull(doMoreWithYourWatchType6);
                        constraintLayout3.setBackground(doMoreWithYourWatchHelper3.getDoMoreWithYourWatchBackgroundDrawable(activity, doMoreWithYourWatchType6.name()));
                        ImageView imageView3 = binding.doMoreWithYourWatchCardContainer.buildFitnessPlan.ivIcon;
                        DoMoreWithYourWatchFeatureType doMoreWithYourWatchType7 = doMoreWithYourWatchDataModel.getDoMoreWithYourWatchType();
                        Intrinsics.checkNotNull(doMoreWithYourWatchType7);
                        imageView3.setImageDrawable(doMoreWithYourWatchHelper3.getDoMoreWithYourWatchDrawable(activity, doMoreWithYourWatchType7.name()));
                        binding.doMoreWithYourWatchCardContainer.buildFitnessPlan.getRoot().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.dashboard2.uihelper.g
                            @Override // android.view.View.OnClickListener
                            public final void onClick(View view) {
                                FragmentHomeHelper.k(DoMoreWithYourWatchItemClickListener.this, doMoreWithYourWatchDataModel, view);
                            }
                        });
                        break;
                    } else {
                        break;
                    }
                case 4:
                    binding.doMoreWithYourWatchCardContainer.setShowAlexaConnect(Boolean.TRUE);
                    binding.doMoreWithYourWatchCardContainer.alexaConnect.tvHeader.setText(doMoreWithYourWatchDataModel.getTitle());
                    binding.doMoreWithYourWatchCardContainer.alexaConnect.tvInfo.setText(doMoreWithYourWatchDataModel.getInfo());
                    ConstraintLayout constraintLayout4 = binding.doMoreWithYourWatchCardContainer.alexaConnect.cardBackground;
                    DoMoreWithYourWatchHelper doMoreWithYourWatchHelper4 = DoMoreWithYourWatchHelper.INSTANCE;
                    DoMoreWithYourWatchFeatureType doMoreWithYourWatchType8 = doMoreWithYourWatchDataModel.getDoMoreWithYourWatchType();
                    Intrinsics.checkNotNull(doMoreWithYourWatchType8);
                    constraintLayout4.setBackground(doMoreWithYourWatchHelper4.getDoMoreWithYourWatchBackgroundDrawable(activity, doMoreWithYourWatchType8.name()));
                    ImageView imageView4 = binding.doMoreWithYourWatchCardContainer.alexaConnect.ivIcon;
                    DoMoreWithYourWatchFeatureType doMoreWithYourWatchType9 = doMoreWithYourWatchDataModel.getDoMoreWithYourWatchType();
                    Intrinsics.checkNotNull(doMoreWithYourWatchType9);
                    imageView4.setImageDrawable(doMoreWithYourWatchHelper4.getDoMoreWithYourWatchDrawable(activity, doMoreWithYourWatchType9.name()));
                    binding.doMoreWithYourWatchCardContainer.alexaConnect.getRoot().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.dashboard2.uihelper.h
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            FragmentHomeHelper.l(DoMoreWithYourWatchItemClickListener.this, doMoreWithYourWatchDataModel, view);
                        }
                    });
                    break;
                case 5:
                    binding.doMoreWithYourWatchCardContainer.setShowTapAndPay(Boolean.TRUE);
                    binding.doMoreWithYourWatchCardContainer.tapAndPay.tvHeader.setText(doMoreWithYourWatchDataModel.getTitle());
                    binding.doMoreWithYourWatchCardContainer.tapAndPay.tvInfo.setText(doMoreWithYourWatchDataModel.getInfo());
                    ConstraintLayout constraintLayout5 = binding.doMoreWithYourWatchCardContainer.tapAndPay.cardBackground;
                    DoMoreWithYourWatchHelper doMoreWithYourWatchHelper5 = DoMoreWithYourWatchHelper.INSTANCE;
                    DoMoreWithYourWatchFeatureType doMoreWithYourWatchType10 = doMoreWithYourWatchDataModel.getDoMoreWithYourWatchType();
                    Intrinsics.checkNotNull(doMoreWithYourWatchType10);
                    constraintLayout5.setBackground(doMoreWithYourWatchHelper5.getDoMoreWithYourWatchBackgroundDrawable(activity, doMoreWithYourWatchType10.name()));
                    ImageView imageView5 = binding.doMoreWithYourWatchCardContainer.tapAndPay.ivIcon;
                    DoMoreWithYourWatchFeatureType doMoreWithYourWatchType11 = doMoreWithYourWatchDataModel.getDoMoreWithYourWatchType();
                    Intrinsics.checkNotNull(doMoreWithYourWatchType11);
                    imageView5.setImageDrawable(doMoreWithYourWatchHelper5.getDoMoreWithYourWatchDrawable(activity, doMoreWithYourWatchType11.name()));
                    binding.doMoreWithYourWatchCardContainer.tapAndPay.getRoot().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.dashboard2.uihelper.f
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            FragmentHomeHelper.m(DoMoreWithYourWatchItemClickListener.this, doMoreWithYourWatchDataModel, view);
                        }
                    });
                    break;
                case 6:
                    binding.doMoreWithYourWatchCardContainer.setShowSOSSettings(Boolean.TRUE);
                    binding.doMoreWithYourWatchCardContainer.sosSettings.tvHeader.setText(doMoreWithYourWatchDataModel.getTitle());
                    binding.doMoreWithYourWatchCardContainer.sosSettings.tvInfo.setText(doMoreWithYourWatchDataModel.getInfo());
                    ConstraintLayout constraintLayout6 = binding.doMoreWithYourWatchCardContainer.sosSettings.cardBackground;
                    DoMoreWithYourWatchHelper doMoreWithYourWatchHelper6 = DoMoreWithYourWatchHelper.INSTANCE;
                    DoMoreWithYourWatchFeatureType doMoreWithYourWatchType12 = doMoreWithYourWatchDataModel.getDoMoreWithYourWatchType();
                    Intrinsics.checkNotNull(doMoreWithYourWatchType12);
                    constraintLayout6.setBackground(doMoreWithYourWatchHelper6.getDoMoreWithYourWatchBackgroundDrawable(activity, doMoreWithYourWatchType12.name()));
                    ImageView imageView6 = binding.doMoreWithYourWatchCardContainer.sosSettings.ivIcon;
                    DoMoreWithYourWatchFeatureType doMoreWithYourWatchType13 = doMoreWithYourWatchDataModel.getDoMoreWithYourWatchType();
                    Intrinsics.checkNotNull(doMoreWithYourWatchType13);
                    imageView6.setImageDrawable(doMoreWithYourWatchHelper6.getDoMoreWithYourWatchDrawable(activity, doMoreWithYourWatchType13.name()));
                    binding.doMoreWithYourWatchCardContainer.sosSettings.getRoot().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.dashboard2.uihelper.d
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            FragmentHomeHelper.n(DoMoreWithYourWatchItemClickListener.this, doMoreWithYourWatchDataModel, view);
                        }
                    });
                    break;
            }
        }
    }

    public final void updateFirstVitalCard(@NotNull FragmentHomeBinding binding, @NotNull Activity activity, boolean z, @NotNull FitnessVitalData fitnessVitalData, @Nullable final DashboardVitalsGridviewAdapter.ItemClickListener itemClickListener) {
        String string;
        Intrinsics.checkNotNullParameter(binding, "binding");
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(fitnessVitalData, "fitnessVitalData");
        binding.healthVitalsCardContainer.setIsFirstCardDataAvailable(Boolean.valueOf(z));
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        boolean z2 = false;
        if (fitnessVitalData instanceof HeartRateData) {
            objectRef.element = IDOConstants.DATA_TYPE_HEART_RATE;
            if (z) {
                binding.healthVitalsCardContainer.firstFitnessVitalsCardWithValue.ivVitalImage.setVisibility(0);
                binding.healthVitalsCardContainer.firstFitnessVitalsCardWithValue.speedViewStress.setVisibility(8);
                binding.healthVitalsCardContainer.firstFitnessVitalsCardWithValue.clLevel.setVisibility(8);
                binding.healthVitalsCardContainer.firstFitnessVitalsCardWithValue.clUnit.setVisibility(0);
                HeartRateData heartRateData = (HeartRateData) fitnessVitalData;
                binding.healthVitalsCardContainer.firstFitnessVitalsCardWithValue.tvVitalValue.setText(String.valueOf(heartRateData.getValue()));
                binding.healthVitalsCardContainer.firstFitnessVitalsCardWithValue.tvVitalValueUnit.setText(activity.getString(R.string.bpm_small));
                binding.healthVitalsCardContainer.firstFitnessVitalsCardWithValue.tvVitalLastUpdatedTime.setText(Dashboard2Utils.Companion.getLastUpdatedTimeToShow(activity, heartRateData.getTimestamp()));
            }
        } else if (fitnessVitalData instanceof SPO2Data) {
            objectRef.element = "SPO2";
            if (z) {
                binding.healthVitalsCardContainer.firstFitnessVitalsCardWithValue.ivVitalImage.setVisibility(0);
                binding.healthVitalsCardContainer.firstFitnessVitalsCardWithValue.speedViewStress.setVisibility(8);
                binding.healthVitalsCardContainer.firstFitnessVitalsCardWithValue.clLevel.setVisibility(8);
                binding.healthVitalsCardContainer.firstFitnessVitalsCardWithValue.clUnit.setVisibility(8);
                TextView textView = binding.healthVitalsCardContainer.firstFitnessVitalsCardWithValue.tvVitalValue;
                StringBuilder sb = new StringBuilder();
                SPO2Data sPO2Data = (SPO2Data) fitnessVitalData;
                sb.append((int) sPO2Data.getValue());
                sb.append('%');
                textView.setText(sb.toString());
                binding.healthVitalsCardContainer.firstFitnessVitalsCardWithValue.tvVitalLastUpdatedTime.setText(Dashboard2Utils.Companion.getLastUpdatedTimeToShow(activity, sPO2Data.getTimestamp()));
            }
        } else if (fitnessVitalData instanceof StressData) {
            objectRef.element = "STRESS";
            if (z) {
                binding.healthVitalsCardContainer.firstFitnessVitalsCardWithValue.ivVitalImage.setVisibility(0);
                binding.healthVitalsCardContainer.firstFitnessVitalsCardWithValue.speedViewStress.setVisibility(8);
                binding.healthVitalsCardContainer.firstFitnessVitalsCardWithValue.clLevel.setVisibility(0);
                binding.healthVitalsCardContainer.firstFitnessVitalsCardWithValue.clUnit.setVisibility(8);
                StressData stressData = (StressData) fitnessVitalData;
                binding.healthVitalsCardContainer.firstFitnessVitalsCardWithValue.tvVitalValue.setText(String.valueOf(stressData.getValue()));
                TextView textView2 = binding.healthVitalsCardContainer.firstFitnessVitalsCardWithValue.tvVitalLevel;
                StressDataHelper stressDataHelper = StressDataHelper.INSTANCE;
                textView2.setText(stressDataHelper.getStressRange(stressData.getValue(), activity));
                binding.healthVitalsCardContainer.firstFitnessVitalsCardWithValue.tvVitalLastUpdatedTime.setText(Dashboard2Utils.Companion.getLastUpdatedTimeToShow(activity, stressData.getTimestamp()));
                binding.healthVitalsCardContainer.firstFitnessVitalsCardWithValue.tvVitalLevel.setTextColor(Color.parseColor(stressDataHelper.getStressTextColor(activity, stressData.getValue())));
            }
        } else if (fitnessVitalData instanceof HRVData) {
            objectRef.element = "HRV";
            if (z) {
                binding.healthVitalsCardContainer.firstFitnessVitalsCardWithValue.ivVitalImage.setVisibility(0);
                binding.healthVitalsCardContainer.firstFitnessVitalsCardWithValue.speedViewStress.setVisibility(8);
                binding.healthVitalsCardContainer.firstFitnessVitalsCardWithValue.clLevel.setVisibility(8);
                binding.healthVitalsCardContainer.firstFitnessVitalsCardWithValue.clUnit.setVisibility(0);
                HRVData hRVData = (HRVData) fitnessVitalData;
                binding.healthVitalsCardContainer.firstFitnessVitalsCardWithValue.tvVitalValue.setText(HRVDataHelper.INSTANCE.calculationFormulaHRV(hRVData.getValue()));
                binding.healthVitalsCardContainer.firstFitnessVitalsCardWithValue.tvVitalValueUnit.setText(activity.getString(R.string.ms));
                binding.healthVitalsCardContainer.firstFitnessVitalsCardWithValue.tvVitalLastUpdatedTime.setText(Dashboard2Utils.Companion.getLastUpdatedTimeToShow(activity, hRVData.getTimestamp()));
            }
        } else if (fitnessVitalData instanceof EnergyMeterData) {
            objectRef.element = "ENERGY_METER";
            if (z) {
                binding.healthVitalsCardContainer.firstFitnessVitalsCardWithValue.ivVitalImage.setVisibility(0);
                binding.healthVitalsCardContainer.firstFitnessVitalsCardWithValue.speedViewStress.setVisibility(8);
                binding.healthVitalsCardContainer.firstFitnessVitalsCardWithValue.clLevel.setVisibility(8);
                binding.healthVitalsCardContainer.firstFitnessVitalsCardWithValue.clUnit.setVisibility(8);
                EnergyMeterData energyMeterData = (EnergyMeterData) fitnessVitalData;
                binding.healthVitalsCardContainer.firstFitnessVitalsCardWithValue.tvVitalValue.setText(String.valueOf(energyMeterData.getValue()));
                binding.healthVitalsCardContainer.firstFitnessVitalsCardWithValue.tvVitalValueUnit.setText(activity.getString(R.string.ms));
                binding.healthVitalsCardContainer.firstFitnessVitalsCardWithValue.tvVitalLastUpdatedTime.setText(Dashboard2Utils.Companion.getLastUpdatedTimeToShow(activity, energyMeterData.getTimestamp()));
            }
        } else if (fitnessVitalData instanceof RespiratoryRateData) {
            objectRef.element = "NBR";
            if (z) {
                binding.healthVitalsCardContainer.firstFitnessVitalsCardWithValue.ivVitalImage.setVisibility(0);
                binding.healthVitalsCardContainer.firstFitnessVitalsCardWithValue.speedViewStress.setVisibility(8);
                binding.healthVitalsCardContainer.firstFitnessVitalsCardWithValue.clLevel.setVisibility(8);
                binding.healthVitalsCardContainer.firstFitnessVitalsCardWithValue.clUnit.setVisibility(0);
                RespiratoryRateData respiratoryRateData = (RespiratoryRateData) fitnessVitalData;
                binding.healthVitalsCardContainer.firstFitnessVitalsCardWithValue.tvVitalValue.setText(String.valueOf(respiratoryRateData.getValue()));
                binding.healthVitalsCardContainer.firstFitnessVitalsCardWithValue.tvVitalValueUnit.setText(activity.getString(R.string.brpm));
                binding.healthVitalsCardContainer.firstFitnessVitalsCardWithValue.tvVitalLastUpdatedTime.setText(Dashboard2Utils.Companion.getLastUpdatedTimeToShow(activity, respiratoryRateData.getTimestamp()));
            }
        } else if (fitnessVitalData instanceof TemperatureData) {
            objectRef.element = "TEMPERATURE";
            if (z) {
                binding.healthVitalsCardContainer.firstFitnessVitalsCardWithValue.ivVitalImage.setVisibility(0);
                binding.healthVitalsCardContainer.firstFitnessVitalsCardWithValue.speedViewStress.setVisibility(8);
                binding.healthVitalsCardContainer.firstFitnessVitalsCardWithValue.clLevel.setVisibility(8);
                binding.healthVitalsCardContainer.firstFitnessVitalsCardWithValue.clUnit.setVisibility(0);
                TemperatureData temperatureData = (TemperatureData) fitnessVitalData;
                binding.healthVitalsCardContainer.firstFitnessVitalsCardWithValue.tvVitalValue.setText(String.valueOf(temperatureData.getValue()));
                Boolean temperatureInFahrenheit = UserDataManager.getInstance(activity).isTemperatureUnitInFahrenheit();
                Intrinsics.checkNotNullExpressionValue(temperatureInFahrenheit, "temperatureInFahrenheit");
                if (temperatureInFahrenheit.booleanValue()) {
                    string = activity.getResources().getString(R.string.fahrenheit_symbol);
                } else {
                    string = activity.getResources().getString(R.string.celcis_symbol);
                }
                Intrinsics.checkNotNullExpressionValue(string, "if (temperatureInFahrenh…ol)\n                    }");
                binding.healthVitalsCardContainer.firstFitnessVitalsCardWithValue.tvVitalValueUnit.setText(string);
                binding.healthVitalsCardContainer.firstFitnessVitalsCardWithValue.tvVitalLastUpdatedTime.setText(Dashboard2Utils.Companion.getLastUpdatedTimeToShow(activity, temperatureData.getTimestamp()));
            }
        }
        if (!z) {
            CharSequence charSequence = (CharSequence) objectRef.element;
            if (charSequence == null || charSequence.length() == 0) {
                z2 = true;
            }
            if (z2) {
                return;
            }
            binding.healthVitalsCardContainer.setIsFirstCardDataAvailable(Boolean.FALSE);
            binding.healthVitalsCardContainer.firstFitnessVitalsCard.getRoot().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.dashboard2.uihelper.a
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FragmentHomeHelper.o(DashboardVitalsGridviewAdapter.ItemClickListener.this, objectRef, view);
                }
            });
            TextView textView3 = binding.healthVitalsCardContainer.firstFitnessVitalsCard.tvHeader;
            FitnessVitalsHelper fitnessVitalsHelper = FitnessVitalsHelper.INSTANCE;
            textView3.setText(fitnessVitalsHelper.getFitnessVitalTitle(activity, (String) objectRef.element));
            binding.healthVitalsCardContainer.firstFitnessVitalsCard.tvInfo.setText(fitnessVitalsHelper.getFitnessVitalInfoText(activity, (String) objectRef.element));
            binding.healthVitalsCardContainer.firstFitnessVitalsCard.ivIcon.setImageDrawable(fitnessVitalsHelper.getFitnessVitalDrawable(activity, (String) objectRef.element));
            return;
        }
        CharSequence charSequence2 = (CharSequence) objectRef.element;
        if (charSequence2 == null || charSequence2.length() == 0) {
            z2 = true;
        }
        if (z2) {
            return;
        }
        binding.healthVitalsCardContainer.setIsFirstCardDataAvailable(Boolean.TRUE);
        binding.healthVitalsCardContainer.firstFitnessVitalsCardWithValue.getRoot().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.dashboard2.uihelper.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentHomeHelper.p(DashboardVitalsGridviewAdapter.ItemClickListener.this, objectRef, view);
            }
        });
        TextView textView4 = binding.healthVitalsCardContainer.firstFitnessVitalsCardWithValue.tvVitalName;
        FitnessVitalsHelper fitnessVitalsHelper2 = FitnessVitalsHelper.INSTANCE;
        textView4.setText(fitnessVitalsHelper2.getFitnessVitalTitle(activity, (String) objectRef.element));
        binding.healthVitalsCardContainer.firstFitnessVitalsCardWithValue.ivVitalImage.setImageDrawable(fitnessVitalsHelper2.getFitnessVitalBigDrawable(activity, (String) objectRef.element));
    }

    public final void updateWeather(@NotNull FragmentHomeBinding binding, @NotNull Activity activity) {
        Intrinsics.checkNotNullParameter(binding, "binding");
        Intrinsics.checkNotNullParameter(activity, "activity");
        if (ContextCompat.checkSelfPermission(activity, "android.permission.ACCESS_FINE_LOCATION") != 0) {
            binding.toolbar.txtWeatherDash.setText("--");
            binding.toolbar.txtWeatherUnitDash.setText("-");
        } else if (!AppUtils.isNetConnected(activity)) {
            binding.toolbar.txtWeatherDash.setText("--");
            binding.toolbar.txtWeatherUnitDash.setText("-");
        } else {
            WeatherAppPreferenceManager.Companion companion = WeatherAppPreferenceManager.Companion;
            WeatherAppPreferenceManager companion2 = companion.getInstance(activity);
            Intrinsics.checkNotNull(companion2);
            Boolean isWeatherEnabled = companion2.isWeatherEnabled();
            Boolean bool = Boolean.TRUE;
            if (Intrinsics.areEqual(isWeatherEnabled, bool)) {
                WeatherPreferenceManager companion3 = WeatherPreferenceManager.Companion.getInstance(activity);
                Intrinsics.checkNotNull(companion3);
                CurrentForecastModel currentWeatherModel = companion3.getCurrentWeatherModel();
                if ((currentWeatherModel != null ? currentWeatherModel.getCurrentWeather() : null) != null) {
                    List<CurrentWeather> currentWeather = currentWeatherModel.getCurrentWeather();
                    Intrinsics.checkNotNull(currentWeather);
                    if (currentWeather.isEmpty()) {
                        return;
                    }
                    WeatherAppPreferenceManager companion4 = companion.getInstance(activity);
                    Intrinsics.checkNotNull(companion4);
                    String str = Intrinsics.areEqual(companion4.isMetricUnitEnabled(), bool) ? "˚C" : "˚F";
                    TextView textView = binding.toolbar.txtWeatherDash;
                    StringBuilder sb = new StringBuilder();
                    Double temp = currentWeatherModel.getTemp();
                    Intrinsics.checkNotNull(temp);
                    sb.append(kotlin.math.c.roundToInt(temp.doubleValue()));
                    sb.append(str);
                    textView.setText(sb.toString());
                    return;
                }
                return;
            }
            binding.toolbar.txtWeatherDash.setText("--");
            binding.toolbar.txtWeatherUnitDash.setText("-");
        }
    }
}
