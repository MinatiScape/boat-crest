package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.boat.R;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderCosmosBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderCosmosPlusBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderCosmosProBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderFlexConnectBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderLeapCallBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderLunarCallBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderLunarCallConnectAceBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderLunarCallPlusBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderLunarCallProBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderLunarCometBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderLunarConnectBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderLunarConnectPlusBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderLunarConnectProBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderLunarEmbraceBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderLunarFitBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderLunarSeekBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderLunarVelocityBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderMatrixBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderMercuryBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderOpp1WatchesBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderOpp2WatchesBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderOpp3WatchesBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderOpp4WatchesBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderPrimiaAceBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderPrimiaBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderPs1EnigmaOasisBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderStormCallProBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderStormConnectPlusBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderStormPlusBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderStormProBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderStrideVoiceBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderUltimaCallBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderUltimaConnectBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderUltimaVogueBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderVertexBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderWaveArmour2Binding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderWaveArmourBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderWaveBeatBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderWaveBeatCallBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderWaveCallPlusBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderWaveConnectBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderWaveConnectPlusBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderWaveElevateProBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderWaveEliteBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderWaveForce2Binding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderWaveForceBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderWaveGenesisProBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderWaveGloryProBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderWaveLynkVoiceBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderWaveMagmaBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderWaveNeoBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderWavePlayBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderWavePrimeBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderWavePrimeTalkBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderWaveProBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderWaveSelectBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderWaveSmartBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderWaveSmartCallBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderWaveSpectraBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderWaveStyleBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderWaveStyleCallBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderWavefitBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderXtendCallPlusBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderXtendPlusBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderXtendProBinding;
import com.coveiot.android.watchfaceui.databinding.WatchFaceHolderXtendSportsBinding;
/* loaded from: classes3.dex */
public abstract class FragmentMyWatchStatusBinding extends ViewDataBinding {
    @NonNull
    public final ProgressBar batteryProgressBar;
    @NonNull
    public final ImageView batteryStatusImgV;
    @NonNull
    public final TextView batteryStatusTv;
    @NonNull
    public final ImageView btCallStatusImgV;
    @NonNull
    public final TextView btCallStatusTv;
    @NonNull
    public final ImageView btStatusImgV;
    @NonNull
    public final TextView btStatusTv;
    @NonNull
    public final ConstraintLayout clBTCallingStatus;
    @NonNull
    public final ConstraintLayout clBatteryStatus;
    @NonNull
    public final ConstraintLayout clConnectionStatus;
    @NonNull
    public final ConstraintLayout clGuestUser;
    @NonNull
    public final ConstraintLayout clMainWatch;
    @NonNull
    public final ConstraintLayout clSyncStatus;
    @NonNull
    public final ConstraintLayout clTopWatchStatus;
    @NonNull
    public final ImageView ivGuestWatch;
    @NonNull
    public final LinearLayout llWatchStatus;
    @NonNull
    public final ImageView syncStatusImgV;
    @NonNull
    public final TextView syncStatusTv;
    @NonNull
    public final TextView tvBattery;
    @NonNull
    public final TextView tvCall;
    @NonNull
    public final TextView tvRefresh;
    @NonNull
    public final TextView tvWatch;
    @NonNull
    public final View view;
    @NonNull
    public final View view2;
    @NonNull
    public final View view3;
    @NonNull
    public final View viewOne;
    @NonNull
    public final View viewThree;
    @NonNull
    public final View viewTwo;
    @NonNull
    public final WatchFaceHolderCosmosBinding wfCosmos;
    @NonNull
    public final WatchFaceHolderCosmosPlusBinding wfCosmosPlus;
    @NonNull
    public final WatchFaceHolderCosmosProBinding wfCosmosPro;
    @NonNull
    public final WatchFaceHolderFlexConnectBinding wfFlexConnect;
    @NonNull
    public final WatchFaceHolderLeapCallBinding wfLeapCall;
    @NonNull
    public final WatchFaceHolderLunarCallBinding wfLunarCall;
    @NonNull
    public final WatchFaceHolderLunarCallConnectAceBinding wfLunarCallConnectAce;
    @NonNull
    public final WatchFaceHolderLunarCallPlusBinding wfLunarCallPlus;
    @NonNull
    public final WatchFaceHolderLunarCallProBinding wfLunarCallPro;
    @NonNull
    public final WatchFaceHolderLunarCometBinding wfLunarComet;
    @NonNull
    public final WatchFaceHolderLunarConnectBinding wfLunarConnect;
    @NonNull
    public final WatchFaceHolderLunarConnectPlusBinding wfLunarConnectPlus;
    @NonNull
    public final WatchFaceHolderLunarConnectProBinding wfLunarConnectPro;
    @NonNull
    public final WatchFaceHolderLunarEmbraceBinding wfLunarEmbrace;
    @NonNull
    public final WatchFaceHolderLunarSeekBinding wfLunarSeek;
    @NonNull
    public final WatchFaceHolderLunarVelocityBinding wfLunarVelocity;
    @NonNull
    public final WatchFaceHolderWaveLynkVoiceBinding wfLynkVoice;
    @NonNull
    public final WatchFaceHolderMatrixBinding wfMatrix;
    @NonNull
    public final WatchFaceHolderMercuryBinding wfMercury;
    @NonNull
    public final WatchFaceHolderPs1EnigmaOasisBinding wfPS1EnigmaOasis;
    @NonNull
    public final WatchFaceHolderPrimiaAceBinding wfPrimaAce;
    @NonNull
    public final WatchFaceHolderPrimiaBinding wfPrimia;
    @NonNull
    public final WatchFaceHolderStormCallProBinding wfStormCallPro;
    @NonNull
    public final WatchFaceHolderStormConnectPlusBinding wfStormConnectPlus;
    @NonNull
    public final WatchFaceHolderStormPlusBinding wfStormPlus;
    @NonNull
    public final WatchFaceHolderStormProBinding wfStormPro;
    @NonNull
    public final WatchFaceHolderStrideVoiceBinding wfStrideVoice;
    @NonNull
    public final WatchFaceHolderUltimaCallBinding wfUltimaCall;
    @NonNull
    public final WatchFaceHolderUltimaConnectBinding wfUltimaConnect;
    @NonNull
    public final WatchFaceHolderUltimaVogueBinding wfUltimaVogue;
    @NonNull
    public final WatchFaceHolderVertexBinding wfVertex;
    @NonNull
    public final WatchFaceHolderWaveArmourBinding wfWaveArmour;
    @NonNull
    public final WatchFaceHolderWaveArmour2Binding wfWaveArmour2;
    @NonNull
    public final WatchFaceHolderWaveBeatBinding wfWaveBeat;
    @NonNull
    public final WatchFaceHolderWaveBeatCallBinding wfWaveBeatCall;
    @NonNull
    public final WatchFaceHolderWaveCallPlusBinding wfWaveCallPlus;
    @NonNull
    public final WatchFaceHolderWaveConnectBinding wfWaveConnect;
    @NonNull
    public final WatchFaceHolderWaveConnectPlusBinding wfWaveConnectPlus;
    @NonNull
    public final WatchFaceHolderWaveElevateProBinding wfWaveElevatePro;
    @NonNull
    public final WatchFaceHolderWaveEliteBinding wfWaveElite;
    @NonNull
    public final WatchFaceHolderWavefitBinding wfWaveFit;
    @NonNull
    public final WatchFaceHolderWaveForceBinding wfWaveForce;
    @NonNull
    public final WatchFaceHolderWaveForce2Binding wfWaveForce2;
    @NonNull
    public final WatchFaceHolderWaveGenesisProBinding wfWaveGenesisPro;
    @NonNull
    public final WatchFaceHolderWaveGloryProBinding wfWaveGloryPro;
    @NonNull
    public final WatchFaceHolderLunarFitBinding wfWaveLunarFit;
    @NonNull
    public final WatchFaceHolderWaveMagmaBinding wfWaveMagma;
    @NonNull
    public final WatchFaceHolderWaveNeoBinding wfWaveNeo;
    @NonNull
    public final WatchFaceHolderWavePlayBinding wfWavePlay;
    @NonNull
    public final WatchFaceHolderWavePrimeBinding wfWavePrime;
    @NonNull
    public final WatchFaceHolderWavePrimeTalkBinding wfWavePrimeTalk;
    @NonNull
    public final WatchFaceHolderWaveProBinding wfWavePro;
    @NonNull
    public final WatchFaceHolderWaveSelectBinding wfWaveSelect;
    @NonNull
    public final WatchFaceHolderWaveSmartBinding wfWaveSmart;
    @NonNull
    public final WatchFaceHolderWaveSmartCallBinding wfWaveSmartCall;
    @NonNull
    public final WatchFaceHolderWaveSpectraBinding wfWaveSpectra;
    @NonNull
    public final WatchFaceHolderWaveStyleBinding wfWaveStyle;
    @NonNull
    public final WatchFaceHolderWaveStyleCallBinding wfWaveStyleCall;
    @NonNull
    public final WatchFaceHolderXtendCallPlusBinding wfXtendCallPlus;
    @NonNull
    public final WatchFaceHolderXtendPlusBinding wfXtendPlus;
    @NonNull
    public final WatchFaceHolderXtendProBinding wfXtendPro;
    @NonNull
    public final WatchFaceHolderXtendSportsBinding wfXtendSport;
    @NonNull
    public final WatchFaceHolderOpp1WatchesBinding wfopp1Watches;
    @NonNull
    public final WatchFaceHolderOpp2WatchesBinding wfopp2Watches;
    @NonNull
    public final WatchFaceHolderOpp3WatchesBinding wfopp3Watches;
    @NonNull
    public final WatchFaceHolderOpp4WatchesBinding wfopp4Watches;

    public FragmentMyWatchStatusBinding(Object obj, View view, int i, ProgressBar progressBar, ImageView imageView, TextView textView, ImageView imageView2, TextView textView2, ImageView imageView3, TextView textView3, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, ConstraintLayout constraintLayout4, ConstraintLayout constraintLayout5, ConstraintLayout constraintLayout6, ConstraintLayout constraintLayout7, ImageView imageView4, LinearLayout linearLayout, ImageView imageView5, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, View view2, View view3, View view4, View view5, View view6, View view7, WatchFaceHolderCosmosBinding watchFaceHolderCosmosBinding, WatchFaceHolderCosmosPlusBinding watchFaceHolderCosmosPlusBinding, WatchFaceHolderCosmosProBinding watchFaceHolderCosmosProBinding, WatchFaceHolderFlexConnectBinding watchFaceHolderFlexConnectBinding, WatchFaceHolderLeapCallBinding watchFaceHolderLeapCallBinding, WatchFaceHolderLunarCallBinding watchFaceHolderLunarCallBinding, WatchFaceHolderLunarCallConnectAceBinding watchFaceHolderLunarCallConnectAceBinding, WatchFaceHolderLunarCallPlusBinding watchFaceHolderLunarCallPlusBinding, WatchFaceHolderLunarCallProBinding watchFaceHolderLunarCallProBinding, WatchFaceHolderLunarCometBinding watchFaceHolderLunarCometBinding, WatchFaceHolderLunarConnectBinding watchFaceHolderLunarConnectBinding, WatchFaceHolderLunarConnectPlusBinding watchFaceHolderLunarConnectPlusBinding, WatchFaceHolderLunarConnectProBinding watchFaceHolderLunarConnectProBinding, WatchFaceHolderLunarEmbraceBinding watchFaceHolderLunarEmbraceBinding, WatchFaceHolderLunarSeekBinding watchFaceHolderLunarSeekBinding, WatchFaceHolderLunarVelocityBinding watchFaceHolderLunarVelocityBinding, WatchFaceHolderWaveLynkVoiceBinding watchFaceHolderWaveLynkVoiceBinding, WatchFaceHolderMatrixBinding watchFaceHolderMatrixBinding, WatchFaceHolderMercuryBinding watchFaceHolderMercuryBinding, WatchFaceHolderPs1EnigmaOasisBinding watchFaceHolderPs1EnigmaOasisBinding, WatchFaceHolderPrimiaAceBinding watchFaceHolderPrimiaAceBinding, WatchFaceHolderPrimiaBinding watchFaceHolderPrimiaBinding, WatchFaceHolderStormCallProBinding watchFaceHolderStormCallProBinding, WatchFaceHolderStormConnectPlusBinding watchFaceHolderStormConnectPlusBinding, WatchFaceHolderStormPlusBinding watchFaceHolderStormPlusBinding, WatchFaceHolderStormProBinding watchFaceHolderStormProBinding, WatchFaceHolderStrideVoiceBinding watchFaceHolderStrideVoiceBinding, WatchFaceHolderUltimaCallBinding watchFaceHolderUltimaCallBinding, WatchFaceHolderUltimaConnectBinding watchFaceHolderUltimaConnectBinding, WatchFaceHolderUltimaVogueBinding watchFaceHolderUltimaVogueBinding, WatchFaceHolderVertexBinding watchFaceHolderVertexBinding, WatchFaceHolderWaveArmourBinding watchFaceHolderWaveArmourBinding, WatchFaceHolderWaveArmour2Binding watchFaceHolderWaveArmour2Binding, WatchFaceHolderWaveBeatBinding watchFaceHolderWaveBeatBinding, WatchFaceHolderWaveBeatCallBinding watchFaceHolderWaveBeatCallBinding, WatchFaceHolderWaveCallPlusBinding watchFaceHolderWaveCallPlusBinding, WatchFaceHolderWaveConnectBinding watchFaceHolderWaveConnectBinding, WatchFaceHolderWaveConnectPlusBinding watchFaceHolderWaveConnectPlusBinding, WatchFaceHolderWaveElevateProBinding watchFaceHolderWaveElevateProBinding, WatchFaceHolderWaveEliteBinding watchFaceHolderWaveEliteBinding, WatchFaceHolderWavefitBinding watchFaceHolderWavefitBinding, WatchFaceHolderWaveForceBinding watchFaceHolderWaveForceBinding, WatchFaceHolderWaveForce2Binding watchFaceHolderWaveForce2Binding, WatchFaceHolderWaveGenesisProBinding watchFaceHolderWaveGenesisProBinding, WatchFaceHolderWaveGloryProBinding watchFaceHolderWaveGloryProBinding, WatchFaceHolderLunarFitBinding watchFaceHolderLunarFitBinding, WatchFaceHolderWaveMagmaBinding watchFaceHolderWaveMagmaBinding, WatchFaceHolderWaveNeoBinding watchFaceHolderWaveNeoBinding, WatchFaceHolderWavePlayBinding watchFaceHolderWavePlayBinding, WatchFaceHolderWavePrimeBinding watchFaceHolderWavePrimeBinding, WatchFaceHolderWavePrimeTalkBinding watchFaceHolderWavePrimeTalkBinding, WatchFaceHolderWaveProBinding watchFaceHolderWaveProBinding, WatchFaceHolderWaveSelectBinding watchFaceHolderWaveSelectBinding, WatchFaceHolderWaveSmartBinding watchFaceHolderWaveSmartBinding, WatchFaceHolderWaveSmartCallBinding watchFaceHolderWaveSmartCallBinding, WatchFaceHolderWaveSpectraBinding watchFaceHolderWaveSpectraBinding, WatchFaceHolderWaveStyleBinding watchFaceHolderWaveStyleBinding, WatchFaceHolderWaveStyleCallBinding watchFaceHolderWaveStyleCallBinding, WatchFaceHolderXtendCallPlusBinding watchFaceHolderXtendCallPlusBinding, WatchFaceHolderXtendPlusBinding watchFaceHolderXtendPlusBinding, WatchFaceHolderXtendProBinding watchFaceHolderXtendProBinding, WatchFaceHolderXtendSportsBinding watchFaceHolderXtendSportsBinding, WatchFaceHolderOpp1WatchesBinding watchFaceHolderOpp1WatchesBinding, WatchFaceHolderOpp2WatchesBinding watchFaceHolderOpp2WatchesBinding, WatchFaceHolderOpp3WatchesBinding watchFaceHolderOpp3WatchesBinding, WatchFaceHolderOpp4WatchesBinding watchFaceHolderOpp4WatchesBinding) {
        super(obj, view, i);
        this.batteryProgressBar = progressBar;
        this.batteryStatusImgV = imageView;
        this.batteryStatusTv = textView;
        this.btCallStatusImgV = imageView2;
        this.btCallStatusTv = textView2;
        this.btStatusImgV = imageView3;
        this.btStatusTv = textView3;
        this.clBTCallingStatus = constraintLayout;
        this.clBatteryStatus = constraintLayout2;
        this.clConnectionStatus = constraintLayout3;
        this.clGuestUser = constraintLayout4;
        this.clMainWatch = constraintLayout5;
        this.clSyncStatus = constraintLayout6;
        this.clTopWatchStatus = constraintLayout7;
        this.ivGuestWatch = imageView4;
        this.llWatchStatus = linearLayout;
        this.syncStatusImgV = imageView5;
        this.syncStatusTv = textView4;
        this.tvBattery = textView5;
        this.tvCall = textView6;
        this.tvRefresh = textView7;
        this.tvWatch = textView8;
        this.view = view2;
        this.view2 = view3;
        this.view3 = view4;
        this.viewOne = view5;
        this.viewThree = view6;
        this.viewTwo = view7;
        this.wfCosmos = watchFaceHolderCosmosBinding;
        this.wfCosmosPlus = watchFaceHolderCosmosPlusBinding;
        this.wfCosmosPro = watchFaceHolderCosmosProBinding;
        this.wfFlexConnect = watchFaceHolderFlexConnectBinding;
        this.wfLeapCall = watchFaceHolderLeapCallBinding;
        this.wfLunarCall = watchFaceHolderLunarCallBinding;
        this.wfLunarCallConnectAce = watchFaceHolderLunarCallConnectAceBinding;
        this.wfLunarCallPlus = watchFaceHolderLunarCallPlusBinding;
        this.wfLunarCallPro = watchFaceHolderLunarCallProBinding;
        this.wfLunarComet = watchFaceHolderLunarCometBinding;
        this.wfLunarConnect = watchFaceHolderLunarConnectBinding;
        this.wfLunarConnectPlus = watchFaceHolderLunarConnectPlusBinding;
        this.wfLunarConnectPro = watchFaceHolderLunarConnectProBinding;
        this.wfLunarEmbrace = watchFaceHolderLunarEmbraceBinding;
        this.wfLunarSeek = watchFaceHolderLunarSeekBinding;
        this.wfLunarVelocity = watchFaceHolderLunarVelocityBinding;
        this.wfLynkVoice = watchFaceHolderWaveLynkVoiceBinding;
        this.wfMatrix = watchFaceHolderMatrixBinding;
        this.wfMercury = watchFaceHolderMercuryBinding;
        this.wfPS1EnigmaOasis = watchFaceHolderPs1EnigmaOasisBinding;
        this.wfPrimaAce = watchFaceHolderPrimiaAceBinding;
        this.wfPrimia = watchFaceHolderPrimiaBinding;
        this.wfStormCallPro = watchFaceHolderStormCallProBinding;
        this.wfStormConnectPlus = watchFaceHolderStormConnectPlusBinding;
        this.wfStormPlus = watchFaceHolderStormPlusBinding;
        this.wfStormPro = watchFaceHolderStormProBinding;
        this.wfStrideVoice = watchFaceHolderStrideVoiceBinding;
        this.wfUltimaCall = watchFaceHolderUltimaCallBinding;
        this.wfUltimaConnect = watchFaceHolderUltimaConnectBinding;
        this.wfUltimaVogue = watchFaceHolderUltimaVogueBinding;
        this.wfVertex = watchFaceHolderVertexBinding;
        this.wfWaveArmour = watchFaceHolderWaveArmourBinding;
        this.wfWaveArmour2 = watchFaceHolderWaveArmour2Binding;
        this.wfWaveBeat = watchFaceHolderWaveBeatBinding;
        this.wfWaveBeatCall = watchFaceHolderWaveBeatCallBinding;
        this.wfWaveCallPlus = watchFaceHolderWaveCallPlusBinding;
        this.wfWaveConnect = watchFaceHolderWaveConnectBinding;
        this.wfWaveConnectPlus = watchFaceHolderWaveConnectPlusBinding;
        this.wfWaveElevatePro = watchFaceHolderWaveElevateProBinding;
        this.wfWaveElite = watchFaceHolderWaveEliteBinding;
        this.wfWaveFit = watchFaceHolderWavefitBinding;
        this.wfWaveForce = watchFaceHolderWaveForceBinding;
        this.wfWaveForce2 = watchFaceHolderWaveForce2Binding;
        this.wfWaveGenesisPro = watchFaceHolderWaveGenesisProBinding;
        this.wfWaveGloryPro = watchFaceHolderWaveGloryProBinding;
        this.wfWaveLunarFit = watchFaceHolderLunarFitBinding;
        this.wfWaveMagma = watchFaceHolderWaveMagmaBinding;
        this.wfWaveNeo = watchFaceHolderWaveNeoBinding;
        this.wfWavePlay = watchFaceHolderWavePlayBinding;
        this.wfWavePrime = watchFaceHolderWavePrimeBinding;
        this.wfWavePrimeTalk = watchFaceHolderWavePrimeTalkBinding;
        this.wfWavePro = watchFaceHolderWaveProBinding;
        this.wfWaveSelect = watchFaceHolderWaveSelectBinding;
        this.wfWaveSmart = watchFaceHolderWaveSmartBinding;
        this.wfWaveSmartCall = watchFaceHolderWaveSmartCallBinding;
        this.wfWaveSpectra = watchFaceHolderWaveSpectraBinding;
        this.wfWaveStyle = watchFaceHolderWaveStyleBinding;
        this.wfWaveStyleCall = watchFaceHolderWaveStyleCallBinding;
        this.wfXtendCallPlus = watchFaceHolderXtendCallPlusBinding;
        this.wfXtendPlus = watchFaceHolderXtendPlusBinding;
        this.wfXtendPro = watchFaceHolderXtendProBinding;
        this.wfXtendSport = watchFaceHolderXtendSportsBinding;
        this.wfopp1Watches = watchFaceHolderOpp1WatchesBinding;
        this.wfopp2Watches = watchFaceHolderOpp2WatchesBinding;
        this.wfopp3Watches = watchFaceHolderOpp3WatchesBinding;
        this.wfopp4Watches = watchFaceHolderOpp4WatchesBinding;
    }

    public static FragmentMyWatchStatusBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentMyWatchStatusBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentMyWatchStatusBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentMyWatchStatusBinding) ViewDataBinding.bind(obj, view, R.layout.fragment_my_watch_status);
    }

    @NonNull
    @Deprecated
    public static FragmentMyWatchStatusBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentMyWatchStatusBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_my_watch_status, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentMyWatchStatusBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentMyWatchStatusBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentMyWatchStatusBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_my_watch_status, null, false, obj);
    }
}
