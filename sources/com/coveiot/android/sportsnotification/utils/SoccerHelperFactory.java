package com.coveiot.android.sportsnotification.utils;

import com.coveiot.android.bleabstract.models.DeviceType;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes7.dex */
public final class SoccerHelperFactory {
    @NotNull
    public static final Companion Companion = new Companion(null);

    /* loaded from: classes7.dex */
    public static final class Companion {

        /* loaded from: classes7.dex */
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[DeviceType.values().length];
                try {
                    iArr[DeviceType.CA3.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[DeviceType.CA3_BT_CALL.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                try {
                    iArr[DeviceType.CA3_BT_STORM_PRO_CALL.ordinal()] = 3;
                } catch (NoSuchFieldError unused3) {
                }
                try {
                    iArr[DeviceType.CA3_WAVE_COSMOS.ordinal()] = 4;
                } catch (NoSuchFieldError unused4) {
                }
                try {
                    iArr[DeviceType.CA3_BT_WAVE_COSMOS_PRO.ordinal()] = 5;
                } catch (NoSuchFieldError unused5) {
                }
                try {
                    iArr[DeviceType.CY1_LOOP_CALL_PRO.ordinal()] = 6;
                } catch (NoSuchFieldError unused6) {
                }
                try {
                    iArr[DeviceType.CY1_LOOP_CONNECT_PRO.ordinal()] = 7;
                } catch (NoSuchFieldError unused7) {
                }
                try {
                    iArr[DeviceType.CY1_PRIMIA_VOICE.ordinal()] = 8;
                } catch (NoSuchFieldError unused8) {
                }
                try {
                    iArr[DeviceType.ULC5_ULTIMA_CALL.ordinal()] = 9;
                } catch (NoSuchFieldError unused9) {
                }
                try {
                    iArr[DeviceType.ULC5_ULTIMA_CONNECT.ordinal()] = 10;
                } catch (NoSuchFieldError unused10) {
                }
                try {
                    iArr[DeviceType.WAVENEOPLUS.ordinal()] = 11;
                } catch (NoSuchFieldError unused11) {
                }
                try {
                    iArr[DeviceType.WAVEACTIVE.ordinal()] = 12;
                } catch (NoSuchFieldError unused12) {
                }
                try {
                    iArr[DeviceType.WAVECALL2.ordinal()] = 13;
                } catch (NoSuchFieldError unused13) {
                }
                try {
                    iArr[DeviceType.STORMCALL2.ordinal()] = 14;
                } catch (NoSuchFieldError unused14) {
                }
                try {
                    iArr[DeviceType.WAVEASTRA.ordinal()] = 15;
                } catch (NoSuchFieldError unused15) {
                }
                try {
                    iArr[DeviceType.ULTIMA_RISE.ordinal()] = 16;
                } catch (NoSuchFieldError unused16) {
                }
                try {
                    iArr[DeviceType.WAVECALL3.ordinal()] = 17;
                } catch (NoSuchFieldError unused17) {
                }
                try {
                    iArr[DeviceType.STORMCALL3.ordinal()] = 18;
                } catch (NoSuchFieldError unused18) {
                }
                try {
                    iArr[DeviceType.WAVEASTRA2.ordinal()] = 19;
                } catch (NoSuchFieldError unused19) {
                }
                try {
                    iArr[DeviceType.WAVESIGMA.ordinal()] = 20;
                } catch (NoSuchFieldError unused20) {
                }
                try {
                    iArr[DeviceType.WAVESIGMA3.ordinal()] = 21;
                } catch (NoSuchFieldError unused21) {
                }
                try {
                    iArr[DeviceType.WAVE_CHASE.ordinal()] = 22;
                } catch (NoSuchFieldError unused22) {
                }
                try {
                    iArr[DeviceType.WAVE_REGAL.ordinal()] = 23;
                } catch (NoSuchFieldError unused23) {
                }
                try {
                    iArr[DeviceType.ULTIMAPRISM.ordinal()] = 24;
                } catch (NoSuchFieldError unused24) {
                }
                try {
                    iArr[DeviceType.ULTIMACHRONOS.ordinal()] = 25;
                } catch (NoSuchFieldError unused25) {
                }
                try {
                    iArr[DeviceType.WAVECONVEX.ordinal()] = 26;
                } catch (NoSuchFieldError unused26) {
                }
                try {
                    iArr[DeviceType.LUNARORB.ordinal()] = 27;
                } catch (NoSuchFieldError unused27) {
                }
                try {
                    iArr[DeviceType.PRIMIACURV.ordinal()] = 28;
                } catch (NoSuchFieldError unused28) {
                }
                try {
                    iArr[DeviceType.LUNARPRIME.ordinal()] = 29;
                } catch (NoSuchFieldError unused29) {
                }
                try {
                    iArr[DeviceType.PS1_ENIGMA_OASIS.ordinal()] = 30;
                } catch (NoSuchFieldError unused30) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final SoccerHelper getSoccerHelper(@NotNull DeviceType deviceType) {
            Intrinsics.checkNotNullParameter(deviceType, "deviceType");
            switch (WhenMappings.$EnumSwitchMapping$0[deviceType.ordinal()]) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                    return SoccerHelperCA.INSTANCE;
                case 6:
                case 7:
                case 8:
                    return SoccerHelperCY.INSTANCE;
                case 9:
                case 10:
                    return SoccerHelperCAULC5.INSTANCE;
                case 11:
                case 12:
                    return SoccerHelperOPPDevices240x282.INSTANCE;
                case 13:
                case 14:
                case 15:
                case 16:
                case 17:
                case 18:
                case 19:
                    return SoccerHelperOPPDevices240x284.INSTANCE;
                case 20:
                case 21:
                case 22:
                case 23:
                    return SoccerHelperOPPDevices240x296.INSTANCE;
                case 24:
                case 25:
                case 26:
                    return SoccerHelperOPPDevices410X502.INSTANCE;
                case 27:
                case 28:
                case 29:
                    return SoccerHelperOPPDevices466X466.INSTANCE;
                case 30:
                    return SoccerHelperPS1Devices466X466.INSTANCE;
                default:
                    return SoccerHelperCA.INSTANCE;
            }
        }
    }
}
