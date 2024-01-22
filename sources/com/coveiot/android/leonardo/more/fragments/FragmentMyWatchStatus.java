package com.coveiot.android.leonardo.more.fragments;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.bumptech.glide.Glide;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.DeviceSupportedFeatures;
import com.coveiot.android.bleabstract.models.DeviceType;
import com.coveiot.android.bleabstract.preferences.PreferenceManagerAbstract;
import com.coveiot.android.boat.R;
import com.coveiot.android.boat.databinding.FragmentMyWatchStatusBinding;
import com.coveiot.android.dashboard2.Dashboard2PreferenceManager;
import com.coveiot.android.devicemodels.DeviceConstants;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.leonardo.bt3call.listener.IBT3ConnectionChangeListener;
import com.coveiot.android.leonardo.bt3call.viewmodel.BT3CallViewModel;
import com.coveiot.android.leonardo.more.viewmodel.MyWatchViewModel;
import com.coveiot.android.leonardo.utils.ViewModelFactory;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.utils.BT3Utils;
import com.coveiot.android.theme.utils.ThemesUtils;
import com.coveiot.android.watchfacecore.model.WatchFaceBean;
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
import com.coveiot.android.watchfaceui.preference.WatchFacePreferenceManager;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.BatteryLevelData;
import com.coveiot.utils.utility.AppUtils;
import com.github.siyamed.shapeimageview.RoundedImageView;
import java.io.File;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt__StringsKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class FragmentMyWatchStatus extends BaseFragment implements IBT3ConnectionChangeListener, Observer<ConnectionStatus> {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public BT3CallViewModel bT3CallViewModel;
    @Nullable
    public FragmentMyWatchStatusBinding m;
    public MyWatchViewModel n;

    /* loaded from: classes5.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ FragmentMyWatchStatus newInstance$default(Companion companion, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                z = false;
            }
            return companion.newInstance(z);
        }

        @JvmStatic
        @NotNull
        public final FragmentMyWatchStatus newInstance(boolean z) {
            FragmentMyWatchStatus fragmentMyWatchStatus = new FragmentMyWatchStatus();
            Bundle bundle = new Bundle();
            bundle.putBoolean("shouldHideTitle", z);
            fragmentMyWatchStatus.setArguments(bundle);
            return fragmentMyWatchStatus;
        }
    }

    /* loaded from: classes5.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ConnectionStatus.values().length];
            try {
                iArr[ConnectionStatus.CONNECTED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ConnectionStatus.CONNECTING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[ConnectionStatus.SCANNING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[ConnectionStatus.DISCONNECTED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* loaded from: classes5.dex */
    public static final class a extends Lambda implements Function1<WatchFaceBean, Unit> {

        @DebugMetadata(c = "com.coveiot.android.leonardo.more.fragments.FragmentMyWatchStatus$onViewCreated$1$1$1", f = "FragmentMyWatchStatus.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.coveiot.android.leonardo.more.fragments.FragmentMyWatchStatus$a$a  reason: collision with other inner class name */
        /* loaded from: classes5.dex */
        public static final class C0280a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ WatchFaceBean $watchfaceBean;
            public int label;
            public final /* synthetic */ FragmentMyWatchStatus this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C0280a(FragmentMyWatchStatus fragmentMyWatchStatus, WatchFaceBean watchFaceBean, Continuation<? super C0280a> continuation) {
                super(2, continuation);
                this.this$0 = fragmentMyWatchStatus;
                this.$watchfaceBean = watchFaceBean;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new C0280a(this.this$0, this.$watchfaceBean, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                return ((C0280a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    WatchFacePreferenceManager.Companion companion = WatchFacePreferenceManager.Companion;
                    Context requireContext = this.this$0.requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                    WatchFaceBean lastSelectedWatchFace = companion.getInstance(requireContext).getLastSelectedWatchFace();
                    if (lastSelectedWatchFace != null) {
                        lastSelectedWatchFace.setPreviewImageUrl(this.$watchfaceBean.getPreviewImageUrl());
                    } else {
                        lastSelectedWatchFace = this.$watchfaceBean;
                    }
                    Context requireContext2 = this.this$0.requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                    companion.getInstance(requireContext2).saveLastSelectedWatchFace(lastSelectedWatchFace);
                    ImageView m = this.this$0.m();
                    if (m != null && this.$watchfaceBean.getPreviewImageUrl() != null) {
                        String previewImageUrl = this.$watchfaceBean.getPreviewImageUrl();
                        Intrinsics.checkNotNull(previewImageUrl);
                        if (StringsKt__StringsKt.contains((CharSequence) previewImageUrl, (CharSequence) ".gif", true)) {
                            try {
                                Glide.with(this.this$0.requireActivity()).asGif().m21load(this.$watchfaceBean.getPreviewImageUrl()).into(m);
                            } catch (Exception e) {
                                e.printStackTrace();
                                Glide.with(this.this$0.requireActivity()).m30load(this.$watchfaceBean.getPreviewImageUrl()).into(m);
                            }
                        } else {
                            Glide.with(this.this$0.requireActivity()).m30load(this.$watchfaceBean.getPreviewImageUrl()).into(m);
                        }
                    }
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        public a() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(WatchFaceBean watchFaceBean) {
            invoke2(watchFaceBean);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(WatchFaceBean watchFaceBean) {
            if (watchFaceBean != null) {
                FragmentMyWatchStatus fragmentMyWatchStatus = FragmentMyWatchStatus.this;
                if (fragmentMyWatchStatus.isAdded()) {
                    kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(fragmentMyWatchStatus), Dispatchers.getMain(), null, new C0280a(fragmentMyWatchStatus, watchFaceBean, null), 2, null);
                }
            }
        }
    }

    @JvmStatic
    @NotNull
    public static final FragmentMyWatchStatus newInstance(boolean z) {
        return Companion.newInstance(z);
    }

    public static final void r(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    @Override // com.coveiot.android.theme.BaseFragment
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.coveiot.android.theme.BaseFragment
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

    @NotNull
    public final BT3CallViewModel getBT3CallViewModel() {
        BT3CallViewModel bT3CallViewModel = this.bT3CallViewModel;
        if (bT3CallViewModel != null) {
            return bT3CallViewModel;
        }
        Intrinsics.throwUninitializedPropertyAccessException("bT3CallViewModel");
        return null;
    }

    @Nullable
    public final ImageView getWatchFaceTimeStampImage() {
        RoundedImageView roundedImageView;
        RoundedImageView roundedImageView2;
        RoundedImageView roundedImageView3;
        RoundedImageView roundedImageView4;
        RoundedImageView roundedImageView5;
        RoundedImageView roundedImageView6;
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        DeviceType deviceType = BleApiManager.getInstance(requireContext()).getDeviceType();
        Intrinsics.checkNotNullExpressionValue(deviceType, "getInstance(requireContext()).deviceType");
        String type = companion.getDeviceModelFromDeviceType(deviceType).getType();
        DeviceConstants.Companion companion2 = DeviceConstants.Companion;
        if (Intrinsics.areEqual(type, companion2.getSTORMPRO())) {
            WatchFaceHolderStormProBinding watchFaceHolderStormProBinding = l().wfStormPro;
            if (watchFaceHolderStormProBinding != null && (roundedImageView6 = watchFaceHolderStormProBinding.selectedWatchFaceBg) != null) {
                visible(roundedImageView6);
            }
            WatchFaceHolderStormProBinding watchFaceHolderStormProBinding2 = l().wfStormPro;
            if (watchFaceHolderStormProBinding2 == null) {
                return null;
            }
            roundedImageView = watchFaceHolderStormProBinding2.selectedWatchFaceBg;
        } else if (Intrinsics.areEqual(type, companion2.getWAVECOSMOS())) {
            WatchFaceHolderCosmosBinding watchFaceHolderCosmosBinding = l().wfCosmos;
            if (watchFaceHolderCosmosBinding != null && (roundedImageView5 = watchFaceHolderCosmosBinding.selectedWatchFaceBg) != null) {
                visible(roundedImageView5);
            }
            WatchFaceHolderCosmosBinding watchFaceHolderCosmosBinding2 = l().wfCosmos;
            if (watchFaceHolderCosmosBinding2 == null) {
                return null;
            }
            roundedImageView = watchFaceHolderCosmosBinding2.selectedWatchFaceBg;
        } else if (Intrinsics.areEqual(type, companion2.getWAVECOSMOSPRO())) {
            WatchFaceHolderCosmosProBinding watchFaceHolderCosmosProBinding = l().wfCosmosPro;
            if (watchFaceHolderCosmosProBinding != null && (roundedImageView4 = watchFaceHolderCosmosProBinding.selectedWatchFaceBg) != null) {
                visible(roundedImageView4);
            }
            WatchFaceHolderCosmosProBinding watchFaceHolderCosmosProBinding2 = l().wfCosmosPro;
            if (watchFaceHolderCosmosProBinding2 == null) {
                return null;
            }
            roundedImageView = watchFaceHolderCosmosProBinding2.selectedWatchFaceBg;
        } else if (Intrinsics.areEqual(type, companion2.getXTENDPROBT3())) {
            WatchFaceHolderXtendProBinding watchFaceHolderXtendProBinding = l().wfXtendPro;
            if (watchFaceHolderXtendProBinding != null && (roundedImageView3 = watchFaceHolderXtendProBinding.selectedWatchFaceBg) != null) {
                visible(roundedImageView3);
            }
            WatchFaceHolderXtendProBinding watchFaceHolderXtendProBinding2 = l().wfXtendPro;
            if (watchFaceHolderXtendProBinding2 == null) {
                return null;
            }
            roundedImageView = watchFaceHolderXtendProBinding2.selectedWatchFaceBg;
        } else if (!Intrinsics.areEqual(type, companion2.getSTORMPROCALL())) {
            return null;
        } else {
            WatchFaceHolderStormCallProBinding watchFaceHolderStormCallProBinding = l().wfStormCallPro;
            if (watchFaceHolderStormCallProBinding != null && (roundedImageView2 = watchFaceHolderStormCallProBinding.selectedWatchFaceBg) != null) {
                visible(roundedImageView2);
            }
            WatchFaceHolderStormCallProBinding watchFaceHolderStormCallProBinding2 = l().wfStormCallPro;
            if (watchFaceHolderStormCallProBinding2 == null) {
                return null;
            }
            roundedImageView = watchFaceHolderStormCallProBinding2.selectedWatchFaceBg;
        }
        return roundedImageView;
    }

    public final boolean isBTCallingSupported() {
        DeviceSupportedFeatures deviceSupportedFeatures;
        BleApi bleApi = BleApiManager.getInstance(requireActivity()).getBleApi();
        Boolean valueOf = (bleApi == null || (deviceSupportedFeatures = bleApi.getDeviceSupportedFeatures()) == null) ? null : Boolean.valueOf(deviceSupportedFeatures.isKahaBTCallSupported());
        Intrinsics.checkNotNull(valueOf);
        if (!valueOf.booleanValue()) {
            DeviceUtils.Companion companion = DeviceUtils.Companion;
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            if (!companion.isTouchELXDevice(requireContext) || BleApiManager.getInstance(getContext()).getDeviceType() == DeviceType.TOUCH_WAVE_NEO) {
                Context requireContext2 = requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                if (!companion.isEastApexDevice(requireContext2)) {
                    Context requireContext3 = requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
                    if (!companion.isSmaJieieDevice(requireContext3)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public final FragmentMyWatchStatusBinding l() {
        FragmentMyWatchStatusBinding fragmentMyWatchStatusBinding = this.m;
        Intrinsics.checkNotNull(fragmentMyWatchStatusBinding);
        return fragmentMyWatchStatusBinding;
    }

    public final ImageView m() {
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        DeviceType deviceType = BleApiManager.getInstance(requireContext()).getDeviceType();
        Intrinsics.checkNotNullExpressionValue(deviceType, "getInstance(requireContext()).deviceType");
        String type = companion.getDeviceModelFromDeviceType(deviceType).getType();
        DeviceConstants.Companion companion2 = DeviceConstants.Companion;
        if (Intrinsics.areEqual(type, companion2.getMERCURY())) {
            WatchFaceHolderMercuryBinding watchFaceHolderMercuryBinding = l().wfMercury;
            ConstraintLayout constraintLayout = watchFaceHolderMercuryBinding != null ? watchFaceHolderMercuryBinding.clMainData : null;
            if (constraintLayout != null) {
                constraintLayout.setVisibility(0);
            }
            WatchFaceHolderMercuryBinding watchFaceHolderMercuryBinding2 = l().wfMercury;
            if (watchFaceHolderMercuryBinding2 != null) {
                return watchFaceHolderMercuryBinding2.watchfacePlaceholderImgV;
            }
            return null;
        } else if (Intrinsics.areEqual(type, companion2.getVERTEX())) {
            WatchFaceHolderVertexBinding watchFaceHolderVertexBinding = l().wfVertex;
            ConstraintLayout constraintLayout2 = watchFaceHolderVertexBinding != null ? watchFaceHolderVertexBinding.clMainData : null;
            if (constraintLayout2 != null) {
                constraintLayout2.setVisibility(0);
            }
            WatchFaceHolderVertexBinding watchFaceHolderVertexBinding2 = l().wfVertex;
            if (watchFaceHolderVertexBinding2 != null) {
                return watchFaceHolderVertexBinding2.watchfacePlaceholderImgV;
            }
            return null;
        } else if (Intrinsics.areEqual(type, companion2.getWAVEPRO())) {
            WatchFaceHolderWaveProBinding watchFaceHolderWaveProBinding = l().wfWavePro;
            ConstraintLayout constraintLayout3 = watchFaceHolderWaveProBinding != null ? watchFaceHolderWaveProBinding.clMainData : null;
            if (constraintLayout3 != null) {
                constraintLayout3.setVisibility(0);
            }
            WatchFaceHolderWaveProBinding watchFaceHolderWaveProBinding2 = l().wfWavePro;
            if (watchFaceHolderWaveProBinding2 != null) {
                return watchFaceHolderWaveProBinding2.watchfacePlaceholderImgV;
            }
            return null;
        } else if (Intrinsics.areEqual(type, companion2.getWAVEFIT())) {
            WatchFaceHolderWavefitBinding watchFaceHolderWavefitBinding = l().wfWaveFit;
            ConstraintLayout constraintLayout4 = watchFaceHolderWavefitBinding != null ? watchFaceHolderWavefitBinding.clMainData : null;
            if (constraintLayout4 != null) {
                constraintLayout4.setVisibility(0);
            }
            WatchFaceHolderWavefitBinding watchFaceHolderWavefitBinding2 = l().wfWaveFit;
            if (watchFaceHolderWavefitBinding2 != null) {
                return watchFaceHolderWavefitBinding2.watchfacePlaceholderImgV;
            }
            return null;
        } else if (Intrinsics.areEqual(type, companion2.getPRIMIA())) {
            WatchFaceHolderPrimiaBinding watchFaceHolderPrimiaBinding = l().wfPrimia;
            ConstraintLayout constraintLayout5 = watchFaceHolderPrimiaBinding != null ? watchFaceHolderPrimiaBinding.clMainData : null;
            if (constraintLayout5 != null) {
                constraintLayout5.setVisibility(0);
            }
            WatchFaceHolderPrimiaBinding watchFaceHolderPrimiaBinding2 = l().wfPrimia;
            if (watchFaceHolderPrimiaBinding2 != null) {
                return watchFaceHolderPrimiaBinding2.watchfacePlaceholderImgV;
            }
            return null;
        } else if (Intrinsics.areEqual(type, companion2.getMATRIX())) {
            WatchFaceHolderMatrixBinding watchFaceHolderMatrixBinding = l().wfMatrix;
            ConstraintLayout constraintLayout6 = watchFaceHolderMatrixBinding != null ? watchFaceHolderMatrixBinding.clMainData : null;
            if (constraintLayout6 != null) {
                constraintLayout6.setVisibility(0);
            }
            WatchFaceHolderMatrixBinding watchFaceHolderMatrixBinding2 = l().wfMatrix;
            if (watchFaceHolderMatrixBinding2 != null) {
                return watchFaceHolderMatrixBinding2.watchfacePlaceholderImgV;
            }
            return null;
        } else if (Intrinsics.areEqual(type, companion2.getWAVE_ARMOUR())) {
            WatchFaceHolderWaveArmourBinding watchFaceHolderWaveArmourBinding = l().wfWaveArmour;
            ConstraintLayout constraintLayout7 = watchFaceHolderWaveArmourBinding != null ? watchFaceHolderWaveArmourBinding.clMainData : null;
            if (constraintLayout7 != null) {
                constraintLayout7.setVisibility(0);
            }
            WatchFaceHolderWaveArmourBinding watchFaceHolderWaveArmourBinding2 = l().wfWaveArmour;
            if (watchFaceHolderWaveArmourBinding2 != null) {
                return watchFaceHolderWaveArmourBinding2.watchfacePlaceholderImgV;
            }
            return null;
        } else if (Intrinsics.areEqual(type, companion2.getWAVE_FORCE())) {
            WatchFaceHolderWaveForceBinding watchFaceHolderWaveForceBinding = l().wfWaveForce;
            ConstraintLayout constraintLayout8 = watchFaceHolderWaveForceBinding != null ? watchFaceHolderWaveForceBinding.clMainData : null;
            if (constraintLayout8 != null) {
                constraintLayout8.setVisibility(0);
            }
            WatchFaceHolderWaveForceBinding watchFaceHolderWaveForceBinding2 = l().wfWaveForce;
            if (watchFaceHolderWaveForceBinding2 != null) {
                return watchFaceHolderWaveForceBinding2.watchfacePlaceholderImgV;
            }
            return null;
        } else if (Intrinsics.areEqual(type, companion2.getWAVEPlUS())) {
            WatchFaceHolderXtendSportsBinding watchFaceHolderXtendSportsBinding = l().wfXtendSport;
            ConstraintLayout constraintLayout9 = watchFaceHolderXtendSportsBinding != null ? watchFaceHolderXtendSportsBinding.clMainData : null;
            if (constraintLayout9 != null) {
                constraintLayout9.setVisibility(0);
            }
            WatchFaceHolderXtendSportsBinding watchFaceHolderXtendSportsBinding2 = l().wfXtendSport;
            if (watchFaceHolderXtendSportsBinding2 != null) {
                return watchFaceHolderXtendSportsBinding2.watchfacePlaceholderImgV;
            }
            return null;
        } else if (Intrinsics.areEqual(type, companion2.getWAVEPRIME())) {
            WatchFaceHolderWavePrimeBinding watchFaceHolderWavePrimeBinding = l().wfWavePrime;
            ConstraintLayout constraintLayout10 = watchFaceHolderWavePrimeBinding != null ? watchFaceHolderWavePrimeBinding.clMainData : null;
            if (constraintLayout10 != null) {
                constraintLayout10.setVisibility(0);
            }
            WatchFaceHolderWavePrimeBinding watchFaceHolderWavePrimeBinding2 = l().wfWavePrime;
            if (watchFaceHolderWavePrimeBinding2 != null) {
                return watchFaceHolderWavePrimeBinding2.watchfacePlaceholderImgV;
            }
            return null;
        } else if (Intrinsics.areEqual(type, companion2.getSTORMPRO())) {
            WatchFaceHolderStormProBinding watchFaceHolderStormProBinding = l().wfStormPro;
            ConstraintLayout constraintLayout11 = watchFaceHolderStormProBinding != null ? watchFaceHolderStormProBinding.clMainData : null;
            if (constraintLayout11 != null) {
                constraintLayout11.setVisibility(0);
            }
            WatchFaceHolderStormProBinding watchFaceHolderStormProBinding2 = l().wfStormPro;
            if (watchFaceHolderStormProBinding2 != null) {
                return watchFaceHolderStormProBinding2.watchfacePlaceholderImgV;
            }
            return null;
        } else if (Intrinsics.areEqual(type, companion2.getIDO_SELECT())) {
            WatchFaceHolderWaveSelectBinding watchFaceHolderWaveSelectBinding = l().wfWaveSelect;
            ConstraintLayout constraintLayout12 = watchFaceHolderWaveSelectBinding != null ? watchFaceHolderWaveSelectBinding.clMainData : null;
            if (constraintLayout12 != null) {
                constraintLayout12.setVisibility(0);
            }
            WatchFaceHolderWaveSelectBinding watchFaceHolderWaveSelectBinding2 = l().wfWaveSelect;
            if (watchFaceHolderWaveSelectBinding2 != null) {
                return watchFaceHolderWaveSelectBinding2.watchfacePlaceholderImgV;
            }
            return null;
        } else if (Intrinsics.areEqual(type, companion2.getXTENDPROBT3())) {
            WatchFaceHolderXtendProBinding watchFaceHolderXtendProBinding = l().wfXtendPro;
            ConstraintLayout constraintLayout13 = watchFaceHolderXtendProBinding != null ? watchFaceHolderXtendProBinding.clMainData : null;
            if (constraintLayout13 != null) {
                constraintLayout13.setVisibility(0);
            }
            WatchFaceHolderXtendProBinding watchFaceHolderXtendProBinding2 = l().wfXtendPro;
            if (watchFaceHolderXtendProBinding2 != null) {
                return watchFaceHolderXtendProBinding2.watchfacePlaceholderImgV;
            }
            return null;
        } else if (Intrinsics.areEqual(type, companion2.getXTEND_SPORT())) {
            WatchFaceHolderXtendSportsBinding watchFaceHolderXtendSportsBinding3 = l().wfXtendSport;
            ConstraintLayout constraintLayout14 = watchFaceHolderXtendSportsBinding3 != null ? watchFaceHolderXtendSportsBinding3.clMainData : null;
            if (constraintLayout14 != null) {
                constraintLayout14.setVisibility(0);
            }
            WatchFaceHolderXtendSportsBinding watchFaceHolderXtendSportsBinding4 = l().wfXtendSport;
            if (watchFaceHolderXtendSportsBinding4 != null) {
                return watchFaceHolderXtendSportsBinding4.watchfacePlaceholderImgV;
            }
            return null;
        } else if (Intrinsics.areEqual(type, companion2.getWAVECOSMOS())) {
            WatchFaceHolderCosmosBinding watchFaceHolderCosmosBinding = l().wfCosmos;
            ConstraintLayout constraintLayout15 = watchFaceHolderCosmosBinding != null ? watchFaceHolderCosmosBinding.clMainData : null;
            if (constraintLayout15 != null) {
                constraintLayout15.setVisibility(0);
            }
            WatchFaceHolderCosmosBinding watchFaceHolderCosmosBinding2 = l().wfCosmos;
            if (watchFaceHolderCosmosBinding2 != null) {
                return watchFaceHolderCosmosBinding2.watchfacePlaceholderImgV;
            }
            return null;
        } else if (Intrinsics.areEqual(type, companion2.getWAVECOSMOSPRO())) {
            WatchFaceHolderCosmosProBinding watchFaceHolderCosmosProBinding = l().wfCosmosPro;
            ConstraintLayout constraintLayout16 = watchFaceHolderCosmosProBinding != null ? watchFaceHolderCosmosProBinding.clMainData : null;
            if (constraintLayout16 != null) {
                constraintLayout16.setVisibility(0);
            }
            WatchFaceHolderCosmosProBinding watchFaceHolderCosmosProBinding2 = l().wfCosmosPro;
            if (watchFaceHolderCosmosProBinding2 != null) {
                return watchFaceHolderCosmosProBinding2.watchfacePlaceholderImgV;
            }
            return null;
        } else if (Intrinsics.areEqual(type, companion2.getSTORMPROCALL())) {
            WatchFaceHolderStormCallProBinding watchFaceHolderStormCallProBinding = l().wfStormCallPro;
            ConstraintLayout constraintLayout17 = watchFaceHolderStormCallProBinding != null ? watchFaceHolderStormCallProBinding.clMainData : null;
            if (constraintLayout17 != null) {
                constraintLayout17.setVisibility(0);
            }
            WatchFaceHolderStormCallProBinding watchFaceHolderStormCallProBinding2 = l().wfStormCallPro;
            if (watchFaceHolderStormCallProBinding2 != null) {
                return watchFaceHolderStormCallProBinding2.watchfacePlaceholderImgV;
            }
            return null;
        } else if (Intrinsics.areEqual(type, companion2.getWAVEBEAT())) {
            WatchFaceHolderWaveBeatBinding watchFaceHolderWaveBeatBinding = l().wfWaveBeat;
            ConstraintLayout constraintLayout18 = watchFaceHolderWaveBeatBinding != null ? watchFaceHolderWaveBeatBinding.clMainData : null;
            if (constraintLayout18 != null) {
                constraintLayout18.setVisibility(0);
            }
            WatchFaceHolderWaveBeatBinding watchFaceHolderWaveBeatBinding2 = l().wfWaveBeat;
            if (watchFaceHolderWaveBeatBinding2 != null) {
                return watchFaceHolderWaveBeatBinding2.watchfacePlaceholderImgV;
            }
            return null;
        } else if (Intrinsics.areEqual(type, companion2.getWAVEPLAY())) {
            WatchFaceHolderWavePlayBinding watchFaceHolderWavePlayBinding = l().wfWavePlay;
            ConstraintLayout constraintLayout19 = watchFaceHolderWavePlayBinding != null ? watchFaceHolderWavePlayBinding.clMainData : null;
            if (constraintLayout19 != null) {
                constraintLayout19.setVisibility(0);
            }
            WatchFaceHolderWavePlayBinding watchFaceHolderWavePlayBinding2 = l().wfWavePlay;
            if (watchFaceHolderWavePlayBinding2 != null) {
                return watchFaceHolderWavePlayBinding2.watchfacePlaceholderImgV;
            }
            return null;
        } else if (Intrinsics.areEqual(type, companion2.getWAVESTYLE())) {
            WatchFaceHolderWaveStyleBinding watchFaceHolderWaveStyleBinding = l().wfWaveStyle;
            ConstraintLayout constraintLayout20 = watchFaceHolderWaveStyleBinding != null ? watchFaceHolderWaveStyleBinding.clMainData : null;
            if (constraintLayout20 != null) {
                constraintLayout20.setVisibility(0);
            }
            WatchFaceHolderWaveStyleBinding watchFaceHolderWaveStyleBinding2 = l().wfWaveStyle;
            if (watchFaceHolderWaveStyleBinding2 != null) {
                return watchFaceHolderWaveStyleBinding2.watchfacePlaceholderImgV;
            }
            return null;
        } else if (Intrinsics.areEqual(type, companion2.getWAVEELITE())) {
            WatchFaceHolderWaveEliteBinding watchFaceHolderWaveEliteBinding = l().wfWaveElite;
            ConstraintLayout constraintLayout21 = watchFaceHolderWaveEliteBinding != null ? watchFaceHolderWaveEliteBinding.clMainData : null;
            if (constraintLayout21 != null) {
                constraintLayout21.setVisibility(0);
            }
            WatchFaceHolderWaveEliteBinding watchFaceHolderWaveEliteBinding2 = l().wfWaveElite;
            if (watchFaceHolderWaveEliteBinding2 != null) {
                return watchFaceHolderWaveEliteBinding2.watchfacePlaceholderImgV;
            }
            return null;
        } else if (Intrinsics.areEqual(type, companion2.getIDO_CONNECT())) {
            WatchFaceHolderWaveConnectBinding watchFaceHolderWaveConnectBinding = l().wfWaveConnect;
            ConstraintLayout constraintLayout22 = watchFaceHolderWaveConnectBinding != null ? watchFaceHolderWaveConnectBinding.clMainData : null;
            if (constraintLayout22 != null) {
                constraintLayout22.setVisibility(0);
            }
            WatchFaceHolderWaveConnectBinding watchFaceHolderWaveConnectBinding2 = l().wfWaveConnect;
            if (watchFaceHolderWaveConnectBinding2 != null) {
                return watchFaceHolderWaveConnectBinding2.watchfacePlaceholderImgV;
            }
            return null;
        } else if (Intrinsics.areEqual(type, companion2.getSTYLEPLUS())) {
            WatchFaceHolderWaveStyleCallBinding watchFaceHolderWaveStyleCallBinding = l().wfWaveStyleCall;
            ConstraintLayout constraintLayout23 = watchFaceHolderWaveStyleCallBinding != null ? watchFaceHolderWaveStyleCallBinding.clMainData : null;
            if (constraintLayout23 != null) {
                constraintLayout23.setVisibility(0);
            }
            WatchFaceHolderWaveStyleCallBinding watchFaceHolderWaveStyleCallBinding2 = l().wfWaveStyleCall;
            if (watchFaceHolderWaveStyleCallBinding2 != null) {
                return watchFaceHolderWaveStyleCallBinding2.watchfacePlaceholderImgV;
            }
            return null;
        } else if (Intrinsics.areEqual(type, companion2.getBEATPLUS())) {
            WatchFaceHolderWaveBeatCallBinding watchFaceHolderWaveBeatCallBinding = l().wfWaveBeatCall;
            ConstraintLayout constraintLayout24 = watchFaceHolderWaveBeatCallBinding != null ? watchFaceHolderWaveBeatCallBinding.clMainData : null;
            if (constraintLayout24 != null) {
                constraintLayout24.setVisibility(0);
            }
            WatchFaceHolderWaveBeatCallBinding watchFaceHolderWaveBeatCallBinding2 = l().wfWaveBeatCall;
            if (watchFaceHolderWaveBeatCallBinding2 != null) {
                return watchFaceHolderWaveBeatCallBinding2.watchfacePlaceholderImgV;
            }
            return null;
        } else if (Intrinsics.areEqual(type, companion2.getSMARTPLUS())) {
            WatchFaceHolderWaveSmartCallBinding watchFaceHolderWaveSmartCallBinding = l().wfWaveSmartCall;
            ConstraintLayout constraintLayout25 = watchFaceHolderWaveSmartCallBinding != null ? watchFaceHolderWaveSmartCallBinding.clMainData : null;
            if (constraintLayout25 != null) {
                constraintLayout25.setVisibility(0);
            }
            WatchFaceHolderWaveSmartCallBinding watchFaceHolderWaveSmartCallBinding2 = l().wfWaveSmartCall;
            if (watchFaceHolderWaveSmartCallBinding2 != null) {
                return watchFaceHolderWaveSmartCallBinding2.watchfacePlaceholderImgV;
            }
            return null;
        } else if (Intrinsics.areEqual(type, companion2.getLYNC())) {
            WatchFaceHolderWaveLynkVoiceBinding watchFaceHolderWaveLynkVoiceBinding = l().wfLynkVoice;
            ConstraintLayout constraintLayout26 = watchFaceHolderWaveLynkVoiceBinding != null ? watchFaceHolderWaveLynkVoiceBinding.clMainData : null;
            if (constraintLayout26 != null) {
                constraintLayout26.setVisibility(0);
            }
            WatchFaceHolderWaveLynkVoiceBinding watchFaceHolderWaveLynkVoiceBinding2 = l().wfLynkVoice;
            if (watchFaceHolderWaveLynkVoiceBinding2 != null) {
                return watchFaceHolderWaveLynkVoiceBinding2.watchfacePlaceholderImgV;
            }
            return null;
        } else if (Intrinsics.areEqual(type, companion2.getWAVESMART())) {
            WatchFaceHolderWaveSmartBinding watchFaceHolderWaveSmartBinding = l().wfWaveSmart;
            ConstraintLayout constraintLayout27 = watchFaceHolderWaveSmartBinding != null ? watchFaceHolderWaveSmartBinding.clMainData : null;
            if (constraintLayout27 != null) {
                constraintLayout27.setVisibility(0);
            }
            WatchFaceHolderWaveSmartBinding watchFaceHolderWaveSmartBinding2 = l().wfWaveSmart;
            if (watchFaceHolderWaveSmartBinding2 != null) {
                return watchFaceHolderWaveSmartBinding2.watchfacePlaceholderImgV;
            }
            return null;
        } else if (Intrinsics.areEqual(type, companion2.getPRIMIA_VOICE())) {
            WatchFaceHolderWavePrimeTalkBinding watchFaceHolderWavePrimeTalkBinding = l().wfWavePrimeTalk;
            ConstraintLayout constraintLayout28 = watchFaceHolderWavePrimeTalkBinding != null ? watchFaceHolderWavePrimeTalkBinding.clMainData : null;
            if (constraintLayout28 != null) {
                constraintLayout28.setVisibility(0);
            }
            WatchFaceHolderWavePrimeTalkBinding watchFaceHolderWavePrimeTalkBinding2 = l().wfWavePrimeTalk;
            if (watchFaceHolderWavePrimeTalkBinding2 != null) {
                return watchFaceHolderWavePrimeTalkBinding2.watchfacePlaceholderImgV;
            }
            return null;
        } else if (Intrinsics.areEqual(type, companion2.getLOOP_CONNECT_PRO())) {
            WatchFaceHolderLunarConnectProBinding watchFaceHolderLunarConnectProBinding = l().wfLunarConnectPro;
            ConstraintLayout constraintLayout29 = watchFaceHolderLunarConnectProBinding != null ? watchFaceHolderLunarConnectProBinding.clMainData : null;
            if (constraintLayout29 != null) {
                constraintLayout29.setVisibility(0);
            }
            WatchFaceHolderLunarConnectProBinding watchFaceHolderLunarConnectProBinding2 = l().wfLunarConnectPro;
            if (watchFaceHolderLunarConnectProBinding2 != null) {
                return watchFaceHolderLunarConnectProBinding2.watchfacePlaceholderImgV;
            }
            return null;
        } else if (Intrinsics.areEqual(type, companion2.getLOOP_CALL_PRO())) {
            WatchFaceHolderLunarCallProBinding watchFaceHolderLunarCallProBinding = l().wfLunarCallPro;
            ConstraintLayout constraintLayout30 = watchFaceHolderLunarCallProBinding != null ? watchFaceHolderLunarCallProBinding.clMainData : null;
            if (constraintLayout30 != null) {
                constraintLayout30.setVisibility(0);
            }
            WatchFaceHolderLunarCallProBinding watchFaceHolderLunarCallProBinding2 = l().wfLunarCallPro;
            if (watchFaceHolderLunarCallProBinding2 != null) {
                return watchFaceHolderLunarCallProBinding2.watchfacePlaceholderImgV;
            }
            return null;
        } else if (Intrinsics.areEqual(type, companion2.getWAVE_CALL_PLUS())) {
            WatchFaceHolderWaveCallPlusBinding watchFaceHolderWaveCallPlusBinding = l().wfWaveCallPlus;
            ConstraintLayout constraintLayout31 = watchFaceHolderWaveCallPlusBinding != null ? watchFaceHolderWaveCallPlusBinding.clMainData : null;
            if (constraintLayout31 != null) {
                constraintLayout31.setVisibility(0);
            }
            WatchFaceHolderWaveCallPlusBinding watchFaceHolderWaveCallPlusBinding2 = l().wfWaveCallPlus;
            if (watchFaceHolderWaveCallPlusBinding2 != null) {
                return watchFaceHolderWaveCallPlusBinding2.watchfacePlaceholderImgV;
            }
            return null;
        } else if (Intrinsics.areEqual(type, companion2.getWAVE_CONNECT_PLUS())) {
            WatchFaceHolderWaveConnectPlusBinding watchFaceHolderWaveConnectPlusBinding = l().wfWaveConnectPlus;
            ConstraintLayout constraintLayout32 = watchFaceHolderWaveConnectPlusBinding != null ? watchFaceHolderWaveConnectPlusBinding.clMainData : null;
            if (constraintLayout32 != null) {
                constraintLayout32.setVisibility(0);
            }
            WatchFaceHolderWaveConnectPlusBinding watchFaceHolderWaveConnectPlusBinding2 = l().wfWaveConnectPlus;
            if (watchFaceHolderWaveConnectPlusBinding2 != null) {
                return watchFaceHolderWaveConnectPlusBinding2.watchfacePlaceholderImgV;
            }
            return null;
        } else if (Intrinsics.areEqual(type, companion2.getLUNAR_CALL())) {
            WatchFaceHolderLunarCallBinding watchFaceHolderLunarCallBinding = l().wfLunarCall;
            ConstraintLayout constraintLayout33 = watchFaceHolderLunarCallBinding != null ? watchFaceHolderLunarCallBinding.clMainData : null;
            if (constraintLayout33 != null) {
                constraintLayout33.setVisibility(0);
            }
            WatchFaceHolderLunarCallBinding watchFaceHolderLunarCallBinding2 = l().wfLunarCall;
            if (watchFaceHolderLunarCallBinding2 != null) {
                return watchFaceHolderLunarCallBinding2.watchfacePlaceholderImgV;
            }
            return null;
        } else if (Intrinsics.areEqual(type, companion2.getLUNAR_CONNECT())) {
            WatchFaceHolderLunarConnectBinding watchFaceHolderLunarConnectBinding = l().wfLunarConnect;
            ConstraintLayout constraintLayout34 = watchFaceHolderLunarConnectBinding != null ? watchFaceHolderLunarConnectBinding.clMainData : null;
            if (constraintLayout34 != null) {
                constraintLayout34.setVisibility(0);
            }
            WatchFaceHolderLunarConnectBinding watchFaceHolderLunarConnectBinding2 = l().wfLunarConnect;
            if (watchFaceHolderLunarConnectBinding2 != null) {
                return watchFaceHolderLunarConnectBinding2.watchfacePlaceholderImgV;
            }
            return null;
        } else if (Intrinsics.areEqual(type, companion2.getLUNAR_CALL_PLUS())) {
            WatchFaceHolderLunarCallPlusBinding watchFaceHolderLunarCallPlusBinding = l().wfLunarCallPlus;
            ConstraintLayout constraintLayout35 = watchFaceHolderLunarCallPlusBinding != null ? watchFaceHolderLunarCallPlusBinding.clMainData : null;
            if (constraintLayout35 != null) {
                constraintLayout35.setVisibility(0);
            }
            WatchFaceHolderLunarCallPlusBinding watchFaceHolderLunarCallPlusBinding2 = l().wfLunarCallPlus;
            if (watchFaceHolderLunarCallPlusBinding2 != null) {
                return watchFaceHolderLunarCallPlusBinding2.watchfacePlaceholderImgV;
            }
            return null;
        } else if (Intrinsics.areEqual(type, companion2.getLUNAR_CONNECT_PLUS())) {
            WatchFaceHolderLunarConnectPlusBinding watchFaceHolderLunarConnectPlusBinding = l().wfLunarConnectPlus;
            ConstraintLayout constraintLayout36 = watchFaceHolderLunarConnectPlusBinding != null ? watchFaceHolderLunarConnectPlusBinding.clMainData : null;
            if (constraintLayout36 != null) {
                constraintLayout36.setVisibility(0);
            }
            WatchFaceHolderLunarConnectPlusBinding watchFaceHolderLunarConnectPlusBinding2 = l().wfLunarConnectPlus;
            if (watchFaceHolderLunarConnectPlusBinding2 != null) {
                return watchFaceHolderLunarConnectPlusBinding2.watchfacePlaceholderImgV;
            }
            return null;
        } else if (Intrinsics.areEqual(type, companion2.getXTEND_CALL_PLUS())) {
            WatchFaceHolderXtendCallPlusBinding watchFaceHolderXtendCallPlusBinding = l().wfXtendCallPlus;
            ConstraintLayout constraintLayout37 = watchFaceHolderXtendCallPlusBinding != null ? watchFaceHolderXtendCallPlusBinding.clMainData : null;
            if (constraintLayout37 != null) {
                constraintLayout37.setVisibility(0);
            }
            WatchFaceHolderXtendCallPlusBinding watchFaceHolderXtendCallPlusBinding2 = l().wfXtendCallPlus;
            if (watchFaceHolderXtendCallPlusBinding2 != null) {
                return watchFaceHolderXtendCallPlusBinding2.watchfacePlaceholderImgV;
            }
            return null;
        } else if (Intrinsics.areEqual(type, companion2.getSTORM_CONNECT_PLUS())) {
            WatchFaceHolderStormConnectPlusBinding watchFaceHolderStormConnectPlusBinding = l().wfStormConnectPlus;
            ConstraintLayout constraintLayout38 = watchFaceHolderStormConnectPlusBinding != null ? watchFaceHolderStormConnectPlusBinding.clMainData : null;
            if (constraintLayout38 != null) {
                constraintLayout38.setVisibility(0);
            }
            WatchFaceHolderStormConnectPlusBinding watchFaceHolderStormConnectPlusBinding2 = l().wfStormConnectPlus;
            if (watchFaceHolderStormConnectPlusBinding2 != null) {
                return watchFaceHolderStormConnectPlusBinding2.watchfacePlaceholderImgV;
            }
            return null;
        } else if (Intrinsics.areEqual(type, companion2.getULTIMA_CALL())) {
            WatchFaceHolderUltimaCallBinding watchFaceHolderUltimaCallBinding = l().wfUltimaCall;
            ConstraintLayout constraintLayout39 = watchFaceHolderUltimaCallBinding != null ? watchFaceHolderUltimaCallBinding.clMainData : null;
            if (constraintLayout39 != null) {
                constraintLayout39.setVisibility(0);
            }
            WatchFaceHolderUltimaCallBinding watchFaceHolderUltimaCallBinding2 = l().wfUltimaCall;
            if (watchFaceHolderUltimaCallBinding2 != null) {
                return watchFaceHolderUltimaCallBinding2.watchfacePlaceholderImgV;
            }
            return null;
        } else if (Intrinsics.areEqual(type, companion2.getULTIMA_CONNECT())) {
            WatchFaceHolderUltimaConnectBinding watchFaceHolderUltimaConnectBinding = l().wfUltimaConnect;
            ConstraintLayout constraintLayout40 = watchFaceHolderUltimaConnectBinding != null ? watchFaceHolderUltimaConnectBinding.clMainData : null;
            if (constraintLayout40 != null) {
                constraintLayout40.setVisibility(0);
            }
            WatchFaceHolderUltimaConnectBinding watchFaceHolderUltimaConnectBinding2 = l().wfUltimaConnect;
            if (watchFaceHolderUltimaConnectBinding2 != null) {
                return watchFaceHolderUltimaConnectBinding2.watchfacePlaceholderImgV;
            }
            return null;
        } else if (Intrinsics.areEqual(type, companion2.getLEAP_CALL())) {
            WatchFaceHolderLeapCallBinding watchFaceHolderLeapCallBinding = l().wfLeapCall;
            ConstraintLayout constraintLayout41 = watchFaceHolderLeapCallBinding != null ? watchFaceHolderLeapCallBinding.clMainData : null;
            if (constraintLayout41 != null) {
                constraintLayout41.setVisibility(0);
            }
            WatchFaceHolderLeapCallBinding watchFaceHolderLeapCallBinding2 = l().wfLeapCall;
            if (watchFaceHolderLeapCallBinding2 != null) {
                return watchFaceHolderLeapCallBinding2.watchfacePlaceholderImgV;
            }
            return null;
        } else if (Intrinsics.areEqual(type, companion2.getFLEX_CONNECT())) {
            WatchFaceHolderFlexConnectBinding watchFaceHolderFlexConnectBinding = l().wfFlexConnect;
            ConstraintLayout constraintLayout42 = watchFaceHolderFlexConnectBinding != null ? watchFaceHolderFlexConnectBinding.clMainData : null;
            if (constraintLayout42 != null) {
                constraintLayout42.setVisibility(0);
            }
            WatchFaceHolderFlexConnectBinding watchFaceHolderFlexConnectBinding2 = l().wfFlexConnect;
            if (watchFaceHolderFlexConnectBinding2 != null) {
                return watchFaceHolderFlexConnectBinding2.watchfacePlaceholderImgV;
            }
            return null;
        } else if (Intrinsics.areEqual(type, companion2.getSTRIDE_VOICE())) {
            WatchFaceHolderStrideVoiceBinding watchFaceHolderStrideVoiceBinding = l().wfStrideVoice;
            ConstraintLayout constraintLayout43 = watchFaceHolderStrideVoiceBinding != null ? watchFaceHolderStrideVoiceBinding.clMainData : null;
            if (constraintLayout43 != null) {
                constraintLayout43.setVisibility(0);
            }
            WatchFaceHolderStrideVoiceBinding watchFaceHolderStrideVoiceBinding2 = l().wfStrideVoice;
            if (watchFaceHolderStrideVoiceBinding2 != null) {
                return watchFaceHolderStrideVoiceBinding2.watchfacePlaceholderImgV;
            }
            return null;
        } else if (Intrinsics.areEqual(type, companion2.getLUNAR_CONNECT_ACE())) {
            WatchFaceHolderLunarCallConnectAceBinding watchFaceHolderLunarCallConnectAceBinding = l().wfLunarCallConnectAce;
            ConstraintLayout constraintLayout44 = watchFaceHolderLunarCallConnectAceBinding != null ? watchFaceHolderLunarCallConnectAceBinding.clMainData : null;
            if (constraintLayout44 != null) {
                constraintLayout44.setVisibility(0);
            }
            WatchFaceHolderLunarCallConnectAceBinding watchFaceHolderLunarCallConnectAceBinding2 = l().wfLunarCallConnectAce;
            if (watchFaceHolderLunarCallConnectAceBinding2 != null) {
                return watchFaceHolderLunarCallConnectAceBinding2.watchfacePlaceholderImgV;
            }
            return null;
        } else if (Intrinsics.areEqual(type, companion2.getLUNAR_CALL_ACE())) {
            WatchFaceHolderLunarCallConnectAceBinding watchFaceHolderLunarCallConnectAceBinding3 = l().wfLunarCallConnectAce;
            ConstraintLayout constraintLayout45 = watchFaceHolderLunarCallConnectAceBinding3 != null ? watchFaceHolderLunarCallConnectAceBinding3.clMainData : null;
            if (constraintLayout45 != null) {
                constraintLayout45.setVisibility(0);
            }
            WatchFaceHolderLunarCallConnectAceBinding watchFaceHolderLunarCallConnectAceBinding4 = l().wfLunarCallConnectAce;
            if (watchFaceHolderLunarCallConnectAceBinding4 != null) {
                return watchFaceHolderLunarCallConnectAceBinding4.watchfacePlaceholderImgV;
            }
            return null;
        } else if (Intrinsics.areEqual(type, companion2.getPRIMIA_ACE())) {
            WatchFaceHolderPrimiaAceBinding watchFaceHolderPrimiaAceBinding = l().wfPrimaAce;
            ConstraintLayout constraintLayout46 = watchFaceHolderPrimiaAceBinding != null ? watchFaceHolderPrimiaAceBinding.clMainData : null;
            if (constraintLayout46 != null) {
                constraintLayout46.setVisibility(0);
            }
            WatchFaceHolderPrimiaAceBinding watchFaceHolderPrimiaAceBinding2 = l().wfPrimaAce;
            if (watchFaceHolderPrimiaAceBinding2 != null) {
                return watchFaceHolderPrimiaAceBinding2.watchfacePlaceholderImgV;
            }
            return null;
        } else if (Intrinsics.areEqual(type, companion2.getXTEND_PLUS())) {
            WatchFaceHolderXtendPlusBinding watchFaceHolderXtendPlusBinding = l().wfXtendPlus;
            ConstraintLayout constraintLayout47 = watchFaceHolderXtendPlusBinding != null ? watchFaceHolderXtendPlusBinding.clMainData : null;
            if (constraintLayout47 != null) {
                constraintLayout47.setVisibility(0);
            }
            WatchFaceHolderXtendPlusBinding watchFaceHolderXtendPlusBinding2 = l().wfXtendPlus;
            if (watchFaceHolderXtendPlusBinding2 != null) {
                return watchFaceHolderXtendPlusBinding2.watchfacePlaceholderImgV;
            }
            return null;
        } else if (Intrinsics.areEqual(type, companion2.getSTORM_PLUS())) {
            WatchFaceHolderStormPlusBinding watchFaceHolderStormPlusBinding = l().wfStormPlus;
            ConstraintLayout constraintLayout48 = watchFaceHolderStormPlusBinding != null ? watchFaceHolderStormPlusBinding.clMainData : null;
            if (constraintLayout48 != null) {
                constraintLayout48.setVisibility(0);
            }
            WatchFaceHolderStormPlusBinding watchFaceHolderStormPlusBinding2 = l().wfStormPlus;
            if (watchFaceHolderStormPlusBinding2 != null) {
                return watchFaceHolderStormPlusBinding2.watchfacePlaceholderImgV;
            }
            return null;
        } else if (Intrinsics.areEqual(type, companion2.getCOSMOS_PLUS())) {
            WatchFaceHolderCosmosPlusBinding watchFaceHolderCosmosPlusBinding = l().wfCosmosPlus;
            ConstraintLayout constraintLayout49 = watchFaceHolderCosmosPlusBinding != null ? watchFaceHolderCosmosPlusBinding.clMainData : null;
            if (constraintLayout49 != null) {
                constraintLayout49.setVisibility(0);
            }
            WatchFaceHolderCosmosPlusBinding watchFaceHolderCosmosPlusBinding2 = l().wfCosmosPlus;
            if (watchFaceHolderCosmosPlusBinding2 != null) {
                return watchFaceHolderCosmosPlusBinding2.watchfacePlaceholderImgV;
            }
            return null;
        } else {
            if (Intrinsics.areEqual(type, companion2.getWAVECALL2()) ? true : Intrinsics.areEqual(type, companion2.getSTORMCALL2()) ? true : Intrinsics.areEqual(type, companion2.getWAVEASTRA()) ? true : Intrinsics.areEqual(type, companion2.getWAVEASTRA2()) ? true : Intrinsics.areEqual(type, companion2.getWAVECALL3()) ? true : Intrinsics.areEqual(type, companion2.getSTORMCALL3())) {
                WatchFaceHolderOpp1WatchesBinding watchFaceHolderOpp1WatchesBinding = l().wfopp1Watches;
                ConstraintLayout constraintLayout50 = watchFaceHolderOpp1WatchesBinding != null ? watchFaceHolderOpp1WatchesBinding.clMainData : null;
                if (constraintLayout50 != null) {
                    constraintLayout50.setVisibility(0);
                }
                WatchFaceHolderOpp1WatchesBinding watchFaceHolderOpp1WatchesBinding2 = l().wfopp1Watches;
                if (watchFaceHolderOpp1WatchesBinding2 != null) {
                    return watchFaceHolderOpp1WatchesBinding2.watchfacePlaceholderImgV;
                }
                return null;
            }
            if (Intrinsics.areEqual(type, companion2.getWAVENEOPLUS()) ? true : Intrinsics.areEqual(type, companion2.getWAVEACTIVE())) {
                WatchFaceHolderOpp2WatchesBinding watchFaceHolderOpp2WatchesBinding = l().wfopp2Watches;
                ConstraintLayout constraintLayout51 = watchFaceHolderOpp2WatchesBinding != null ? watchFaceHolderOpp2WatchesBinding.clMainData : null;
                if (constraintLayout51 != null) {
                    constraintLayout51.setVisibility(0);
                }
                WatchFaceHolderOpp2WatchesBinding watchFaceHolderOpp2WatchesBinding2 = l().wfopp2Watches;
                if (watchFaceHolderOpp2WatchesBinding2 != null) {
                    return watchFaceHolderOpp2WatchesBinding2.watchfacePlaceholderImgV;
                }
                return null;
            }
            if (Intrinsics.areEqual(type, companion2.getWAVECONVEX()) ? true : Intrinsics.areEqual(type, companion2.getULTIMAPRISM()) ? true : Intrinsics.areEqual(type, companion2.getULTIMACHRONOS()) ? true : Intrinsics.areEqual(type, companion2.getWAVESIGMA()) ? true : Intrinsics.areEqual(type, companion2.getWAVESIGMA3()) ? true : Intrinsics.areEqual(type, companion2.getWAVE_CHASE()) ? true : Intrinsics.areEqual(type, companion2.getWAVE_REGAL())) {
                WatchFaceHolderOpp3WatchesBinding watchFaceHolderOpp3WatchesBinding = l().wfopp3Watches;
                ConstraintLayout constraintLayout52 = watchFaceHolderOpp3WatchesBinding != null ? watchFaceHolderOpp3WatchesBinding.clMainData : null;
                if (constraintLayout52 != null) {
                    constraintLayout52.setVisibility(0);
                }
                WatchFaceHolderOpp3WatchesBinding watchFaceHolderOpp3WatchesBinding2 = l().wfopp3Watches;
                if (watchFaceHolderOpp3WatchesBinding2 != null) {
                    return watchFaceHolderOpp3WatchesBinding2.watchfacePlaceholderImgV;
                }
                return null;
            }
            if (Intrinsics.areEqual(type, companion2.getLUNARORB()) ? true : Intrinsics.areEqual(type, companion2.getPRIMIACURV()) ? true : Intrinsics.areEqual(type, companion2.getLUNARPRIME())) {
                WatchFaceHolderOpp4WatchesBinding watchFaceHolderOpp4WatchesBinding = l().wfopp4Watches;
                ConstraintLayout constraintLayout53 = watchFaceHolderOpp4WatchesBinding != null ? watchFaceHolderOpp4WatchesBinding.clMainData : null;
                if (constraintLayout53 != null) {
                    constraintLayout53.setVisibility(0);
                }
                WatchFaceHolderOpp4WatchesBinding watchFaceHolderOpp4WatchesBinding2 = l().wfopp4Watches;
                if (watchFaceHolderOpp4WatchesBinding2 != null) {
                    return watchFaceHolderOpp4WatchesBinding2.watchfacePlaceholderImgV;
                }
                return null;
            } else if (Intrinsics.areEqual(type, companion2.getWAVE_ARMOUR_2())) {
                WatchFaceHolderWaveArmour2Binding watchFaceHolderWaveArmour2Binding = l().wfWaveArmour2;
                ConstraintLayout constraintLayout54 = watchFaceHolderWaveArmour2Binding != null ? watchFaceHolderWaveArmour2Binding.clMainData : null;
                if (constraintLayout54 != null) {
                    constraintLayout54.setVisibility(0);
                }
                WatchFaceHolderWaveArmour2Binding watchFaceHolderWaveArmour2Binding2 = l().wfWaveArmour2;
                if (watchFaceHolderWaveArmour2Binding2 != null) {
                    return watchFaceHolderWaveArmour2Binding2.watchfacePlaceholderImgV;
                }
                return null;
            } else if (Intrinsics.areEqual(type, companion2.getWAVE_FORCE_2())) {
                WatchFaceHolderWaveForce2Binding watchFaceHolderWaveForce2Binding = l().wfWaveForce2;
                ConstraintLayout constraintLayout55 = watchFaceHolderWaveForce2Binding != null ? watchFaceHolderWaveForce2Binding.clMainData : null;
                if (constraintLayout55 != null) {
                    constraintLayout55.setVisibility(0);
                }
                WatchFaceHolderWaveForce2Binding watchFaceHolderWaveForce2Binding2 = l().wfWaveForce2;
                if (watchFaceHolderWaveForce2Binding2 != null) {
                    return watchFaceHolderWaveForce2Binding2.watchfacePlaceholderImgV;
                }
                return null;
            } else if (Intrinsics.areEqual(type, companion2.getLUNAR_FIT())) {
                WatchFaceHolderLunarFitBinding watchFaceHolderLunarFitBinding = l().wfWaveLunarFit;
                ConstraintLayout constraintLayout56 = watchFaceHolderLunarFitBinding != null ? watchFaceHolderLunarFitBinding.clMainData : null;
                if (constraintLayout56 != null) {
                    constraintLayout56.setVisibility(0);
                }
                WatchFaceHolderLunarFitBinding watchFaceHolderLunarFitBinding2 = l().wfWaveLunarFit;
                if (watchFaceHolderLunarFitBinding2 != null) {
                    return watchFaceHolderLunarFitBinding2.watchfacePlaceholderImgV;
                }
                return null;
            } else if (Intrinsics.areEqual(type, companion2.getWAVE_NEO())) {
                WatchFaceHolderWaveNeoBinding watchFaceHolderWaveNeoBinding = l().wfWaveNeo;
                ConstraintLayout constraintLayout57 = watchFaceHolderWaveNeoBinding != null ? watchFaceHolderWaveNeoBinding.clMainData : null;
                if (constraintLayout57 != null) {
                    constraintLayout57.setVisibility(0);
                }
                WatchFaceHolderWaveNeoBinding watchFaceHolderWaveNeoBinding2 = l().wfWaveNeo;
                if (watchFaceHolderWaveNeoBinding2 != null) {
                    return watchFaceHolderWaveNeoBinding2.watchfacePlaceholderImgV;
                }
                return null;
            } else if (Intrinsics.areEqual(type, companion2.getWAVEGENESISPRO())) {
                WatchFaceHolderWaveGenesisProBinding watchFaceHolderWaveGenesisProBinding = l().wfWaveGenesisPro;
                ConstraintLayout constraintLayout58 = watchFaceHolderWaveGenesisProBinding != null ? watchFaceHolderWaveGenesisProBinding.clMainData : null;
                if (constraintLayout58 != null) {
                    constraintLayout58.setVisibility(0);
                }
                WatchFaceHolderWaveGenesisProBinding watchFaceHolderWaveGenesisProBinding2 = l().wfWaveGenesisPro;
                if (watchFaceHolderWaveGenesisProBinding2 != null) {
                    return watchFaceHolderWaveGenesisProBinding2.watchfacePlaceholderImgV;
                }
                return null;
            } else if (Intrinsics.areEqual(type, companion2.getWAVEELEVATEPRO())) {
                WatchFaceHolderWaveElevateProBinding watchFaceHolderWaveElevateProBinding = l().wfWaveElevatePro;
                ConstraintLayout constraintLayout59 = watchFaceHolderWaveElevateProBinding != null ? watchFaceHolderWaveElevateProBinding.clMainData : null;
                if (constraintLayout59 != null) {
                    constraintLayout59.setVisibility(0);
                }
                WatchFaceHolderWaveElevateProBinding watchFaceHolderWaveElevateProBinding2 = l().wfWaveElevatePro;
                if (watchFaceHolderWaveElevateProBinding2 != null) {
                    return watchFaceHolderWaveElevateProBinding2.watchfacePlaceholderImgV;
                }
                return null;
            } else if (Intrinsics.areEqual(type, companion2.getWAVEGLORYPRO())) {
                WatchFaceHolderWaveGloryProBinding watchFaceHolderWaveGloryProBinding = l().wfWaveGloryPro;
                ConstraintLayout constraintLayout60 = watchFaceHolderWaveGloryProBinding != null ? watchFaceHolderWaveGloryProBinding.clMainData : null;
                if (constraintLayout60 != null) {
                    constraintLayout60.setVisibility(0);
                }
                WatchFaceHolderWaveGloryProBinding watchFaceHolderWaveGloryProBinding2 = l().wfWaveGloryPro;
                if (watchFaceHolderWaveGloryProBinding2 != null) {
                    return watchFaceHolderWaveGloryProBinding2.watchfacePlaceholderImgV;
                }
                return null;
            } else if (Intrinsics.areEqual(type, companion2.getULTIMAVOGUE())) {
                WatchFaceHolderUltimaVogueBinding watchFaceHolderUltimaVogueBinding = l().wfUltimaVogue;
                ConstraintLayout constraintLayout61 = watchFaceHolderUltimaVogueBinding != null ? watchFaceHolderUltimaVogueBinding.clMainData : null;
                if (constraintLayout61 != null) {
                    constraintLayout61.setVisibility(0);
                }
                WatchFaceHolderUltimaVogueBinding watchFaceHolderUltimaVogueBinding2 = l().wfUltimaVogue;
                if (watchFaceHolderUltimaVogueBinding2 != null) {
                    return watchFaceHolderUltimaVogueBinding2.watchfacePlaceholderImgV;
                }
                return null;
            } else if (Intrinsics.areEqual(type, companion2.getLUNARSEEK())) {
                WatchFaceHolderLunarSeekBinding watchFaceHolderLunarSeekBinding = l().wfLunarSeek;
                ConstraintLayout constraintLayout62 = watchFaceHolderLunarSeekBinding != null ? watchFaceHolderLunarSeekBinding.clMainData : null;
                if (constraintLayout62 != null) {
                    constraintLayout62.setVisibility(0);
                }
                WatchFaceHolderLunarSeekBinding watchFaceHolderLunarSeekBinding2 = l().wfLunarSeek;
                if (watchFaceHolderLunarSeekBinding2 != null) {
                    return watchFaceHolderLunarSeekBinding2.watchfacePlaceholderImgV;
                }
                return null;
            } else if (Intrinsics.areEqual(type, companion2.getLUNARCOMET())) {
                WatchFaceHolderLunarCometBinding watchFaceHolderLunarCometBinding = l().wfLunarComet;
                ConstraintLayout constraintLayout63 = watchFaceHolderLunarCometBinding != null ? watchFaceHolderLunarCometBinding.clMainData : null;
                if (constraintLayout63 != null) {
                    constraintLayout63.setVisibility(0);
                }
                WatchFaceHolderLunarCometBinding watchFaceHolderLunarCometBinding2 = l().wfLunarComet;
                if (watchFaceHolderLunarCometBinding2 != null) {
                    return watchFaceHolderLunarCometBinding2.watchfacePlaceholderImgV;
                }
                return null;
            } else if (Intrinsics.areEqual(type, companion2.getLUNARVELOCITY())) {
                WatchFaceHolderLunarVelocityBinding watchFaceHolderLunarVelocityBinding = l().wfLunarVelocity;
                ConstraintLayout constraintLayout64 = watchFaceHolderLunarVelocityBinding != null ? watchFaceHolderLunarVelocityBinding.clMainData : null;
                if (constraintLayout64 != null) {
                    constraintLayout64.setVisibility(0);
                }
                WatchFaceHolderLunarVelocityBinding watchFaceHolderLunarVelocityBinding2 = l().wfLunarVelocity;
                if (watchFaceHolderLunarVelocityBinding2 != null) {
                    return watchFaceHolderLunarVelocityBinding2.watchfacePlaceholderImgV;
                }
                return null;
            } else if (Intrinsics.areEqual(type, companion2.getWAVE_MAGMA())) {
                WatchFaceHolderWaveMagmaBinding watchFaceHolderWaveMagmaBinding = l().wfWaveMagma;
                ConstraintLayout constraintLayout65 = watchFaceHolderWaveMagmaBinding != null ? watchFaceHolderWaveMagmaBinding.clMainData : null;
                if (constraintLayout65 != null) {
                    constraintLayout65.setVisibility(0);
                }
                WatchFaceHolderWaveMagmaBinding watchFaceHolderWaveMagmaBinding2 = l().wfWaveMagma;
                if (watchFaceHolderWaveMagmaBinding2 != null) {
                    return watchFaceHolderWaveMagmaBinding2.watchfacePlaceholderImgV;
                }
                return null;
            } else if (Intrinsics.areEqual(type, companion2.getLUNAR_EMBRACE())) {
                WatchFaceHolderLunarEmbraceBinding watchFaceHolderLunarEmbraceBinding = l().wfLunarEmbrace;
                ConstraintLayout constraintLayout66 = watchFaceHolderLunarEmbraceBinding != null ? watchFaceHolderLunarEmbraceBinding.clMainData : null;
                if (constraintLayout66 != null) {
                    constraintLayout66.setVisibility(0);
                }
                WatchFaceHolderLunarEmbraceBinding watchFaceHolderLunarEmbraceBinding2 = l().wfLunarEmbrace;
                if (watchFaceHolderLunarEmbraceBinding2 != null) {
                    return watchFaceHolderLunarEmbraceBinding2.watchfacePlaceholderImgV;
                }
                return null;
            } else if (Intrinsics.areEqual(type, companion2.getWAVE_SPECTRA())) {
                WatchFaceHolderWaveSpectraBinding watchFaceHolderWaveSpectraBinding = l().wfWaveSpectra;
                ConstraintLayout constraintLayout67 = watchFaceHolderWaveSpectraBinding != null ? watchFaceHolderWaveSpectraBinding.clMainData : null;
                if (constraintLayout67 != null) {
                    constraintLayout67.setVisibility(0);
                }
                WatchFaceHolderWaveSpectraBinding watchFaceHolderWaveSpectraBinding2 = l().wfWaveSpectra;
                if (watchFaceHolderWaveSpectraBinding2 != null) {
                    return watchFaceHolderWaveSpectraBinding2.watchfacePlaceholderImgV;
                }
                return null;
            } else if (Intrinsics.areEqual(type, companion2.getENIGMA_OASIS())) {
                WatchFaceHolderPs1EnigmaOasisBinding watchFaceHolderPs1EnigmaOasisBinding = l().wfPS1EnigmaOasis;
                ConstraintLayout constraintLayout68 = watchFaceHolderPs1EnigmaOasisBinding != null ? watchFaceHolderPs1EnigmaOasisBinding.clMainData : null;
                if (constraintLayout68 != null) {
                    constraintLayout68.setVisibility(0);
                }
                WatchFaceHolderPs1EnigmaOasisBinding watchFaceHolderPs1EnigmaOasisBinding2 = l().wfPS1EnigmaOasis;
                if (watchFaceHolderPs1EnigmaOasisBinding2 != null) {
                    return watchFaceHolderPs1EnigmaOasisBinding2.watchfacePlaceholderImgV;
                }
                return null;
            } else {
                WatchFaceHolderStormProBinding watchFaceHolderStormProBinding3 = l().wfStormPro;
                ConstraintLayout constraintLayout69 = watchFaceHolderStormProBinding3 != null ? watchFaceHolderStormProBinding3.clMainData : null;
                if (constraintLayout69 != null) {
                    constraintLayout69.setVisibility(0);
                }
                WatchFaceHolderStormProBinding watchFaceHolderStormProBinding4 = l().wfStormPro;
                if (watchFaceHolderStormProBinding4 != null) {
                    return watchFaceHolderStormProBinding4.watchfacePlaceholderImgV;
                }
                return null;
            }
        }
    }

    public final void n() {
        DeviceSupportedFeatures deviceSupportedFeatures;
        boolean isBTCallingSupported = isBTCallingSupported();
        if (isBTCallingSupported) {
            boolean z = false;
            l().clBTCallingStatus.setVisibility(0);
            if (isBTCallingSupported) {
                ConnectionStatus connectionStatus = BleApiManager.getInstance(getContext()).getBleApi().getConnectionStatus();
                ConnectionStatus connectionStatus2 = ConnectionStatus.CONNECTED;
                if (connectionStatus == connectionStatus2) {
                    String macAddress = BleApiManager.getInstance(getContext()).getBleApi().getMacAddress();
                    BleApi bleApi = BleApiManager.getInstance(requireActivity()).getBleApi();
                    Boolean valueOf = (bleApi == null || (deviceSupportedFeatures = bleApi.getDeviceSupportedFeatures()) == null) ? null : Boolean.valueOf(deviceSupportedFeatures.isKahaBTCallSupported());
                    Intrinsics.checkNotNull(valueOf);
                    if (valueOf.booleanValue()) {
                        macAddress = UserDataManager.getInstance(requireActivity()).getConnectedBTCallDeviceMac();
                    } else {
                        DeviceUtils.Companion companion = DeviceUtils.Companion;
                        Context requireContext = requireContext();
                        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                        if (companion.isTouchELXDevice(requireContext)) {
                            macAddress = PreferenceManagerAbstract.getInstance(requireContext()).getConnectedDeviceBT3MacAddress();
                        } else {
                            Context requireContext2 = requireContext();
                            Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                            if (companion.isEastApexDevice(requireContext2)) {
                                macAddress = BleApiManager.getInstance(getContext()).getBleApi().getMacAddress();
                            }
                        }
                    }
                    if (macAddress == null) {
                        macAddress = BleApiManager.getInstance(getContext()).getBleApi().getMacAddress();
                    }
                    if ((macAddress == null || macAddress.length() == 0) ? true : true) {
                        return;
                    }
                    Integer bTDeviceBondedState = BT3Utils.getBTDeviceBondedState(macAddress, requireActivity());
                    if (bTDeviceBondedState != null && bTDeviceBondedState.intValue() == 12) {
                        DeviceUtils.Companion companion2 = DeviceUtils.Companion;
                        FragmentActivity requireActivity = requireActivity();
                        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
                        if (companion2.isOPPDevice(requireActivity)) {
                            if (SessionManager.getInstance(requireActivity()).getAclBt3LastReceivedConnectionStatus() != null && (SessionManager.getInstance(requireActivity()).getAclBt3LastReceivedConnectionStatus().getConnectionStatus() != connectionStatus2 || !Intrinsics.areEqual(SessionManager.getInstance(requireActivity()).getAclBt3LastReceivedConnectionStatus().getMacAddress(), macAddress))) {
                                l().btCallStatusImgV.setImageResource(2131231951);
                                l().btCallStatusTv.setText(getString(R.string.bt_calling));
                                return;
                            }
                            l().btCallStatusImgV.setImageResource(2131231949);
                            l().btCallStatusTv.setText(getString(R.string.bt_calling));
                            return;
                        }
                        l().btCallStatusImgV.setImageResource(2131231949);
                        l().btCallStatusTv.setText(getString(R.string.bt_calling));
                        return;
                    }
                    l().btCallStatusImgV.setImageResource(2131231951);
                    l().btCallStatusTv.setText(getString(R.string.bt_calling));
                    return;
                }
            }
            l().btCallStatusImgV.setImageResource(2131231951);
            l().btCallStatusTv.setText(getString(R.string.bt_calling));
            return;
        }
        l().clBTCallingStatus.setVisibility(8);
    }

    public final void o() {
        if (BleApiManager.getInstance(requireActivity()).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
            l().btStatusTv.setText(getString(R.string.connected));
            l().btStatusImgV.setImageResource(2131231932);
            return;
        }
        l().btStatusTv.setText(getString(R.string.disconnected));
        l().btStatusImgV.setImageResource(2131231934);
    }

    @Override // com.coveiot.android.leonardo.bt3call.listener.IBT3ConnectionChangeListener
    public void onBT3Connecting() {
    }

    @Override // com.coveiot.android.leonardo.bt3call.listener.IBT3ConnectionChangeListener
    public void onBT3ConnectionFailure(@Nullable String str) {
        requireActivity().isFinishing();
    }

    @Override // com.coveiot.android.leonardo.bt3call.listener.IBT3ConnectionChangeListener
    public void onBT3Disconnected() {
        requireActivity().isFinishing();
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this.m = FragmentMyWatchStatusBinding.inflate(inflater, viewGroup, false);
        return l().getRoot();
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.coveiot.android.leonardo.bt3call.listener.IBT3ConnectionChangeListener
    public void onInitialCheckFailed(@Nullable String str) {
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        ThemesUtils themesUtils = ThemesUtils.INSTANCE;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        if (themesUtils.isGuestUser(requireContext)) {
            return;
        }
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        if (themesUtils.isPairDeviceLater(requireContext2)) {
            return;
        }
        o();
        n();
        p();
        q();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        ThemesUtils themesUtils = ThemesUtils.INSTANCE;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        if (!themesUtils.isGuestUser(requireContext)) {
            Context requireContext2 = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
            if (!themesUtils.isPairDeviceLater(requireContext2)) {
                l().llWatchStatus.setVisibility(0);
                Context requireContext3 = requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
                setBT3CallViewModel((BT3CallViewModel) ViewModelProviders.of(this, new ViewModelFactory(requireContext3)).get(BT3CallViewModel.class));
                getBT3CallViewModel().setBT3ConnectionChangeListener(this);
                BleApiManager.getInstance(requireContext()).getBleApi().registerConnectionStatus().observe(getViewLifecycleOwner(), this);
                FragmentActivity requireActivity = requireActivity();
                Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
                MyWatchViewModel myWatchViewModel = (MyWatchViewModel) ViewModelProviders.of(this, new ViewModelFactory(requireActivity)).get(MyWatchViewModel.class);
                this.n = myWatchViewModel;
                if (myWatchViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("myWatchViewModel");
                    myWatchViewModel = null;
                }
                MutableLiveData<WatchFaceBean> watchFaceDownloadLiveData = myWatchViewModel.getWatchFaceDownloadLiveData();
                LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
                final a aVar = new a();
                watchFaceDownloadLiveData.observe(viewLifecycleOwner, new Observer() { // from class: com.coveiot.android.leonardo.more.fragments.j0
                    @Override // androidx.lifecycle.Observer
                    public final void onChanged(Object obj) {
                        FragmentMyWatchStatus.r(Function1.this, obj);
                    }
                });
                return;
            }
        }
        ConstraintLayout constraintLayout = l().clGuestUser;
        Intrinsics.checkNotNullExpressionValue(constraintLayout, "binding.clGuestUser");
        visible(constraintLayout);
        ConstraintLayout constraintLayout2 = l().clMainWatch;
        Intrinsics.checkNotNullExpressionValue(constraintLayout2, "binding.clMainWatch");
        gone(constraintLayout2);
    }

    public final void p() {
        int i;
        String formatDate;
        TextView textView;
        Dashboard2PreferenceManager.Companion companion = Dashboard2PreferenceManager.Companion;
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        long lastCompleteDataSyncTimestamp = companion.getInstance(requireActivity).getLastCompleteDataSyncTimestamp();
        if (lastCompleteDataSyncTimestamp > 0) {
            FragmentMyWatchStatusBinding l = l();
            TextView textView2 = l != null ? l.syncStatusTv : null;
            if (textView2 != null) {
                textView2.setText(AppUtils.formatDate(new Date(lastCompleteDataSyncTimestamp), "hh:mm aa"));
            }
            if (DateUtils.isToday(lastCompleteDataSyncTimestamp)) {
                formatDate = "";
            } else {
                formatDate = AppUtils.formatDate(new Date(lastCompleteDataSyncTimestamp), "dd MMM yy");
                Intrinsics.checkNotNullExpressionValue(formatDate, "{\n                AppUti…          )\n            }");
            }
            if (formatDate.length() > 0) {
                FragmentMyWatchStatusBinding l2 = l();
                TextView textView3 = l2 != null ? l2.syncStatusTv : null;
                if (textView3 != null) {
                    StringBuilder sb = new StringBuilder();
                    FragmentMyWatchStatusBinding l3 = l();
                    sb.append((Object) ((l3 == null || (textView = l3.syncStatusTv) == null) ? null : textView.getText()));
                    sb.append(' ');
                    sb.append(formatDate);
                    textView3.setText(sb.toString());
                }
            }
        }
        BatteryLevelData batteryLevelData = UserDataManager.getInstance(requireContext()).getBatteryLevelData();
        if (batteryLevelData == null || (i = batteryLevelData.batteryLevel) <= 0 || i > 100) {
            return;
        }
        FragmentMyWatchStatusBinding l4 = l();
        TextView textView4 = l4 != null ? l4.batteryStatusTv : null;
        if (textView4 != null) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(batteryLevelData.batteryLevel);
            sb2.append('%');
            textView4.setText(sb2.toString());
        }
        t(batteryLevelData.batteryLevel);
    }

    public final void q() {
        ImageView m = m();
        WatchFacePreferenceManager.Companion companion = WatchFacePreferenceManager.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        WatchFaceBean lastSelectedWatchFace = companion.getInstance(requireContext).getLastSelectedWatchFace();
        MyWatchViewModel myWatchViewModel = null;
        if (lastSelectedWatchFace != null) {
            String uid = lastSelectedWatchFace.getUid();
            boolean z = false;
            if (!(uid == null || uid.length() == 0)) {
                String previewImageUrl = lastSelectedWatchFace.getPreviewImageUrl();
                if (!(previewImageUrl == null || previewImageUrl.length() == 0)) {
                    if (m != null) {
                        String previewImageUrl2 = lastSelectedWatchFace.getPreviewImageUrl();
                        if (previewImageUrl2 == null || previewImageUrl2.length() == 0) {
                            z = true;
                        }
                        if (!z) {
                            String previewImageUrl3 = lastSelectedWatchFace.getPreviewImageUrl();
                            Intrinsics.checkNotNull(previewImageUrl3);
                            if (StringsKt__StringsKt.contains((CharSequence) previewImageUrl3, (CharSequence) ".gif", true)) {
                                try {
                                    Glide.with(requireActivity()).asGif().m21load(lastSelectedWatchFace.getPreviewImageUrl()).into(m);
                                    return;
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Glide.with(requireActivity()).m30load(lastSelectedWatchFace.getPreviewImageUrl()).into(m);
                                    return;
                                }
                            }
                        }
                        Glide.with(requireActivity()).m30load(lastSelectedWatchFace.getPreviewImageUrl()).into(m);
                        return;
                    }
                    return;
                }
                String previewImageUrlFromLocal = lastSelectedWatchFace.getPreviewImageUrlFromLocal();
                if (previewImageUrlFromLocal == null || previewImageUrlFromLocal.length() == 0) {
                    z = true;
                }
                if (!z) {
                    try {
                        Uri fromFile = Uri.fromFile(new File(lastSelectedWatchFace.getPreviewImageUrlFromLocal()));
                        if (m != null) {
                            m.setImageBitmap(BitmapFactory.decodeStream(requireActivity().getContentResolver().openInputStream(Uri.parse(fromFile.toString()))));
                        }
                        s();
                        return;
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        return;
                    }
                }
                MyWatchViewModel myWatchViewModel2 = this.n;
                if (myWatchViewModel2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("myWatchViewModel");
                } else {
                    myWatchViewModel = myWatchViewModel2;
                }
                myWatchViewModel.downloadWatchFaceFromServer(lastSelectedWatchFace);
                return;
            }
        }
        MyWatchViewModel myWatchViewModel3 = this.n;
        if (myWatchViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("myWatchViewModel");
        } else {
            myWatchViewModel = myWatchViewModel3;
        }
        myWatchViewModel.downloadDefaultWatchFaces();
    }

    public final void s() {
        ImageView watchFaceTimeStampImage = getWatchFaceTimeStampImage();
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        if (!companion.isCADevice(requireContext)) {
            if (watchFaceTimeStampImage != null) {
                gone(watchFaceTimeStampImage);
                return;
            }
            return;
        }
        WatchFacePreferenceManager.Companion companion2 = WatchFacePreferenceManager.Companion;
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        if (companion2.getInstance(requireContext2).getSelectedWatchfaceBackgroundType() == 0) {
            if (watchFaceTimeStampImage != null) {
                watchFaceTimeStampImage.setImageResource(R.drawable.ca3_diy_watch_face_place_holder_transparent_whitef);
            }
        } else if (watchFaceTimeStampImage != null) {
            watchFaceTimeStampImage.setImageResource(R.drawable.ca3_diy_watch_face_place_holder_transparent_blackf);
        }
    }

    public final void setBT3CallViewModel(@NotNull BT3CallViewModel bT3CallViewModel) {
        Intrinsics.checkNotNullParameter(bT3CallViewModel, "<set-?>");
        this.bT3CallViewModel = bT3CallViewModel;
    }

    public final void t(int i) {
        int i2;
        Drawable drawable;
        if (i >= 90) {
            i2 = 5;
        } else {
            boolean z = false;
            if (70 <= i && i < 90) {
                i2 = 4;
            } else {
                if (50 <= i && i < 70) {
                    i2 = 3;
                } else {
                    if (20 <= i && i < 50) {
                        z = true;
                    }
                    i2 = z ? 2 : 1;
                }
            }
        }
        l().batteryProgressBar.setProgress(i2);
        ProgressBar progressBar = l().batteryProgressBar;
        if (i2 == 1) {
            drawable = AppCompatResources.getDrawable(requireContext(), R.drawable.progressbar_battery_red);
        } else if (i2 == 2) {
            drawable = AppCompatResources.getDrawable(requireContext(), R.drawable.progressbar_battery_orange);
        } else if (i2 == 3) {
            drawable = AppCompatResources.getDrawable(requireContext(), R.drawable.progressbar_battery_yellow);
        } else if (i2 == 4) {
            drawable = AppCompatResources.getDrawable(requireContext(), R.drawable.progressbar_battery_green);
        } else if (i2 != 5) {
            drawable = AppCompatResources.getDrawable(requireContext(), R.drawable.progressbar_battery_green);
        } else {
            drawable = AppCompatResources.getDrawable(requireContext(), R.drawable.progressbar_battery_green);
        }
        progressBar.setProgressDrawable(drawable);
    }

    @Override // androidx.lifecycle.Observer
    public void onChanged(@NotNull ConnectionStatus t) {
        Intrinsics.checkNotNullParameter(t, "t");
        n();
        int i = WhenMappings.$EnumSwitchMapping$0[t.ordinal()];
        if (i == 1) {
            l().btStatusTv.setText(getString(R.string.connected));
            l().btStatusImgV.setImageResource(2131231932);
        } else if (i == 2) {
            l().btStatusTv.setText(getString(R.string.connecting_dot));
            l().btStatusImgV.setImageResource(2131231934);
        } else if (i == 3) {
            l().btStatusTv.setText(getString(R.string.trying_to_connect));
            l().btStatusImgV.setImageResource(2131231934);
        } else if (i != 4) {
            l().btStatusTv.setText(getString(R.string.disconnected));
            l().btStatusImgV.setImageResource(2131231934);
        } else {
            l().btStatusTv.setText(getString(R.string.disconnected));
            l().btStatusImgV.setImageResource(2131231934);
        }
    }
}
