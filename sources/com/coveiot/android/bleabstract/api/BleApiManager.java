package com.coveiot.android.bleabstract.api;

import android.content.Context;
import android.content.SharedPreferences;
import com.coveiot.android.bleabstract.BleApiUtils;
import com.coveiot.android.bleabstract.bleimpl.BESEnigmaVirtuoBleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.BpMeasurementImpl;
import com.coveiot.android.bleabstract.bleimpl.CA0LeonardoBleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.CA2LeonardoBleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.CA3BTCallingLeonardoBleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.CA3BTCallingStromproCallBleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.CA3BTCallingWaveCosmosProBleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.CA3LeonardoBleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.CA3WaveCosmosBleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.CA5WaveBeatBleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.CA5WavePlayBleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.CA5WaveStyleBleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.CA6BTABleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.CRPGPF5BleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.CRPY20BleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.CY1ABleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.CY1BleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.CY2ABleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.CY2BleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.CZ0LeonardoBleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.CZ1WaveEliteBleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.CZ1WavePrimeLeonardoBleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.CZ3LeonardoBleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.EastApexBaseBleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.EastApexCosmosPlusBleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.EastApexFlexConnectBleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.EastApexLunarCallAceBleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.EastApexLunarConnectAceBleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.EastApexPrimiaAceBleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.EastApexStormPlusBleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.EastApexStrideVoiceBleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.EastApexXtendPlusBleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.IDOConnectBleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.IsportBleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.JC2230_01BleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.JC2305BBleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.JC2319BBleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.JStyle1790BleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.JStyle1810GBleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.JStyle1860BleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.JStyle1939BleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.JStyle1963DBleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.JStyle1963YHBleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.JStyle2208ABaseBleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.JStyle2301ABaseBleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.LunarOrbBleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.MatrixBleImpl;
import com.coveiot.android.bleabstract.bleimpl.MatrixLunarFitBleImpl;
import com.coveiot.android.bleabstract.bleimpl.MatrixWaveArmour2BleImpl;
import com.coveiot.android.bleabstract.bleimpl.MatrixWaveArmourBleImpl;
import com.coveiot.android.bleabstract.bleimpl.MatrixWaveForce2BleImpl;
import com.coveiot.android.bleabstract.bleimpl.MatrixWaveForceBleImpl;
import com.coveiot.android.bleabstract.bleimpl.PC60FBleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.PS1EnigmaOasisBleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.PrimiaCurvBleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.SmaF2BleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.SmaLunarCometBleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.SmaLunarSeekBleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.SmaLunarVelocityBleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.SmaS10BleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.SmaS12BleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.SmaUltimaVogueBleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.SmaV2BleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.SmaWaveElevateProBleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.SmaWaveGenesisProBleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.SmaWaveGloryProBleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.SmartTBleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.StormCall3BleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.TFTStormCall2BleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.TFTWaveActiveBleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.TFTWaveAstraBleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.TFTWaveCall2BleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.TFTWaveNeoPlusBleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.TFTWaveSigmaBleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.TouchELXBaseBleImpl;
import com.coveiot.android.bleabstract.bleimpl.TouchLunarCallBleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.TouchLunarCallPlusBleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.TouchLunarConnectBleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.TouchLunarConnectPlusBleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.TouchLunarEmbraceBleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.TouchStormConnectPlusBleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.TouchWaveConnectBleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.TouchWaveFortuneBleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.TouchWaveMagmaBleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.TouchWaveNeoBleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.TouchWaveSpectraBleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.TouchXtendCallPlusBleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.ULC2WaveBeatPlusBleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.ULC2WaveLyncBleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.ULC2WaveSmartPlusBleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.ULC2WaveStylePlusBleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.ULC3WaveSmartBleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.ULC5UltimaCallBleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.ULC5UltimaConnectBleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.UltimaChronosBleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.UltimaPrismBleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.UltimaRiseBleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.UnknownBleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.V7LeonardoBleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.WaveAstra2BleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.WaveCall3BleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.WaveChaseBleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.WaveConvexBleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.WaveRegalBleApiImpl;
import com.coveiot.android.bleabstract.bleimpl.WaveSigma3BleApiImpl;
import com.coveiot.android.bleabstract.exceptions.DeviceNotInitializedException;
import com.coveiot.android.bleabstract.models.DeviceType;
import com.google.gson.Gson;
/* loaded from: classes2.dex */
public class BleApiManager {
    private static final String DEVICE_TYPE = "deviceType";
    private static final String PREF_NAME = "BleAbstract";
    public static int PRIVATE_MODE = 0;
    private static final String TAG = "BleApiManager";
    private static Context context;
    private static SharedPreferences.Editor editor;
    private static BleApiManager instance;
    private static SharedPreferences pref;
    private BleApi mBleApi;

    /* renamed from: com.coveiot.android.bleabstract.api.BleApiManager$1  reason: invalid class name */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;

        static {
            DeviceType.values();
            int[] iArr = new int[117];
            $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType = iArr;
            try {
                DeviceType deviceType = DeviceType.v2;
                iArr[0] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                int[] iArr2 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType2 = DeviceType.v7;
                iArr2[2] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                int[] iArr3 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType3 = DeviceType.CZ0;
                iArr3[3] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                int[] iArr4 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType4 = DeviceType.v3;
                iArr4[1] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                int[] iArr5 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType5 = DeviceType.iSport;
                iArr5[4] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                int[] iArr6 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType6 = DeviceType.jstyle1939;
                iArr6[7] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                int[] iArr7 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType7 = DeviceType.smartT;
                iArr7[5] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                int[] iArr8 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType8 = DeviceType.jstyle1790;
                iArr8[8] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                int[] iArr9 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType9 = DeviceType.G215;
                iArr9[6] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                int[] iArr10 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType10 = DeviceType.jstyle1810G;
                iArr10[9] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                int[] iArr11 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType11 = DeviceType.jstyle1963D;
                iArr11[10] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                int[] iArr12 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType12 = DeviceType.jstyle1963YH;
                iArr12[11] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                int[] iArr13 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType13 = DeviceType.spo2;
                iArr13[15] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                int[] iArr14 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType14 = DeviceType.smaF2;
                iArr14[16] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                int[] iArr15 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType15 = DeviceType.smaM6;
                iArr15[17] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                int[] iArr16 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType16 = DeviceType.smaR9;
                iArr16[18] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                int[] iArr17 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType17 = DeviceType.omronbp;
                iArr17[19] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                int[] iArr18 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType18 = DeviceType.jstyle1860;
                iArr18[12] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                int[] iArr19 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType19 = DeviceType.jstyle2301a;
                iArr19[13] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                int[] iArr20 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType20 = DeviceType.jstyle2208a;
                iArr20[14] = 20;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                int[] iArr21 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType21 = DeviceType.kh17;
                iArr21[20] = 21;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                int[] iArr22 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType22 = DeviceType.matrix;
                iArr22[21] = 22;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                int[] iArr23 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType23 = DeviceType.WAVEFORCE;
                iArr23[61] = 23;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                int[] iArr24 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType24 = DeviceType.WAVEARMOUR;
                iArr24[62] = 24;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                int[] iArr25 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType25 = DeviceType.smaV2;
                iArr25[22] = 25;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                int[] iArr26 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType26 = DeviceType.crpGPF5;
                iArr26[23] = 26;
            } catch (NoSuchFieldError unused26) {
            }
            try {
                int[] iArr27 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType27 = DeviceType.CZ3;
                iArr27[24] = 27;
            } catch (NoSuchFieldError unused27) {
            }
            try {
                int[] iArr28 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType28 = DeviceType.wavePrime;
                iArr28[25] = 28;
            } catch (NoSuchFieldError unused28) {
            }
            try {
                int[] iArr29 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType29 = DeviceType.CA0;
                iArr29[26] = 29;
            } catch (NoSuchFieldError unused29) {
            }
            try {
                int[] iArr30 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType30 = DeviceType.CA3;
                iArr30[27] = 30;
            } catch (NoSuchFieldError unused30) {
            }
            try {
                int[] iArr31 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType31 = DeviceType.CA2;
                iArr31[31] = 31;
            } catch (NoSuchFieldError unused31) {
            }
            try {
                int[] iArr32 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType32 = DeviceType.CA5_WAVE_PLAY;
                iArr32[34] = 32;
            } catch (NoSuchFieldError unused32) {
            }
            try {
                int[] iArr33 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType33 = DeviceType.CA5_WAVE_BEAT;
                iArr33[33] = 33;
            } catch (NoSuchFieldError unused33) {
            }
            try {
                int[] iArr34 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType34 = DeviceType.CA5_WAVE_STYLE;
                iArr34[32] = 34;
            } catch (NoSuchFieldError unused34) {
            }
            try {
                int[] iArr35 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType35 = DeviceType.CA3_BT_STORM_PRO_CALL;
                iArr35[37] = 35;
            } catch (NoSuchFieldError unused35) {
            }
            try {
                int[] iArr36 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType36 = DeviceType.CA3_WAVE_COSMOS;
                iArr36[35] = 36;
            } catch (NoSuchFieldError unused36) {
            }
            try {
                int[] iArr37 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType37 = DeviceType.CA3_BT_WAVE_COSMOS_PRO;
                iArr37[36] = 37;
            } catch (NoSuchFieldError unused37) {
            }
            try {
                int[] iArr38 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType38 = DeviceType.IDO_SELECT;
                iArr38[28] = 38;
            } catch (NoSuchFieldError unused38) {
            }
            try {
                int[] iArr39 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType39 = DeviceType.WAVE_ELITE;
                iArr39[30] = 39;
            } catch (NoSuchFieldError unused39) {
            }
            try {
                int[] iArr40 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType40 = DeviceType.CA3_BT_CALL;
                iArr40[29] = 40;
            } catch (NoSuchFieldError unused40) {
            }
            try {
                int[] iArr41 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType41 = DeviceType.IDO_CONNECT;
                iArr41[38] = 41;
            } catch (NoSuchFieldError unused41) {
            }
            try {
                int[] iArr42 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType42 = DeviceType.ULC3_WAVE_SMART;
                iArr42[39] = 42;
            } catch (NoSuchFieldError unused42) {
            }
            try {
                int[] iArr43 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType43 = DeviceType.ULC2_WAVE_BEAT_PLUS;
                iArr43[40] = 43;
            } catch (NoSuchFieldError unused43) {
            }
            try {
                int[] iArr44 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType44 = DeviceType.JC2230_01;
                iArr44[108] = 44;
            } catch (NoSuchFieldError unused44) {
            }
            try {
                int[] iArr45 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType45 = DeviceType.ULC2_WAVE_STYLE_PLUS;
                iArr45[41] = 45;
            } catch (NoSuchFieldError unused45) {
            }
            try {
                int[] iArr46 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType46 = DeviceType.ULC2_WAVE_SMART_PLUS;
                iArr46[42] = 46;
            } catch (NoSuchFieldError unused46) {
            }
            try {
                int[] iArr47 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType47 = DeviceType.ULC2_WAVE_LYNC;
                iArr47[43] = 47;
            } catch (NoSuchFieldError unused47) {
            }
            try {
                int[] iArr48 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType48 = DeviceType.CY1_PRIMIA_VOICE;
                iArr48[44] = 48;
            } catch (NoSuchFieldError unused48) {
            }
            try {
                int[] iArr49 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType49 = DeviceType.CY1_LOOP_CALL_PRO;
                iArr49[45] = 49;
            } catch (NoSuchFieldError unused49) {
            }
            try {
                int[] iArr50 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType50 = DeviceType.CY1_LOOP_CONNECT_PRO;
                iArr50[46] = 50;
            } catch (NoSuchFieldError unused50) {
            }
            try {
                int[] iArr51 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType51 = DeviceType.CY2_ACE;
                iArr51[47] = 51;
            } catch (NoSuchFieldError unused51) {
            }
            try {
                int[] iArr52 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType52 = DeviceType.TOUCH_WAVE_CALL_PLUS;
                iArr52[48] = 52;
            } catch (NoSuchFieldError unused52) {
            }
            try {
                int[] iArr53 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType53 = DeviceType.TOUCH_WAVE_CONNECT_PLUS;
                iArr53[49] = 53;
            } catch (NoSuchFieldError unused53) {
            }
            try {
                int[] iArr54 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType54 = DeviceType.TOUCH_LUNAR_CALL;
                iArr54[50] = 54;
            } catch (NoSuchFieldError unused54) {
            }
            try {
                int[] iArr55 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType55 = DeviceType.TOUCH_LUNAR_CONNECT;
                iArr55[51] = 55;
            } catch (NoSuchFieldError unused55) {
            }
            try {
                int[] iArr56 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType56 = DeviceType.TOUCH_LUNAR_CALL_PLUS;
                iArr56[52] = 56;
            } catch (NoSuchFieldError unused56) {
            }
            try {
                int[] iArr57 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType57 = DeviceType.TOUCH_LUNAR_CONNECT_PLUS;
                iArr57[53] = 57;
            } catch (NoSuchFieldError unused57) {
            }
            try {
                int[] iArr58 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType58 = DeviceType.TOUCH_XTEND_CALL_PLUS;
                iArr58[54] = 58;
            } catch (NoSuchFieldError unused58) {
            }
            try {
                int[] iArr59 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType59 = DeviceType.TOUCH_STORM_CONNECT_PLUS;
                iArr59[55] = 59;
            } catch (NoSuchFieldError unused59) {
            }
            try {
                int[] iArr60 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType60 = DeviceType.TOUCH_LUNAR_EMBRACE;
                iArr60[56] = 60;
            } catch (NoSuchFieldError unused60) {
            }
            try {
                int[] iArr61 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType61 = DeviceType.TOUCH_WAVE_SPECTRA;
                iArr61[57] = 61;
            } catch (NoSuchFieldError unused61) {
            }
            try {
                int[] iArr62 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType62 = DeviceType.ULC5_ULTIMA_CALL;
                iArr62[59] = 62;
            } catch (NoSuchFieldError unused62) {
            }
            try {
                int[] iArr63 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType63 = DeviceType.ULC5_ULTIMA_CONNECT;
                iArr63[60] = 63;
            } catch (NoSuchFieldError unused63) {
            }
            try {
                int[] iArr64 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType64 = DeviceType.EA_LEAP_CALL;
                iArr64[63] = 64;
            } catch (NoSuchFieldError unused64) {
            }
            try {
                int[] iArr65 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType65 = DeviceType.EA_FLEX_CONNECT;
                iArr65[64] = 65;
            } catch (NoSuchFieldError unused65) {
            }
            try {
                int[] iArr66 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType66 = DeviceType.EA_STRIDE_VOICE;
                iArr66[65] = 66;
            } catch (NoSuchFieldError unused66) {
            }
            try {
                int[] iArr67 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType67 = DeviceType.EA_XTEND_PLUS;
                iArr67[66] = 67;
            } catch (NoSuchFieldError unused67) {
            }
            try {
                int[] iArr68 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType68 = DeviceType.EA_STORM_PLUS;
                iArr68[67] = 68;
            } catch (NoSuchFieldError unused68) {
            }
            try {
                int[] iArr69 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType69 = DeviceType.EA_COSMOS_PLUS;
                iArr69[68] = 69;
            } catch (NoSuchFieldError unused69) {
            }
            try {
                int[] iArr70 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType70 = DeviceType.EA_LUNAR_CALL_ACE;
                iArr70[69] = 70;
            } catch (NoSuchFieldError unused70) {
            }
            try {
                int[] iArr71 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType71 = DeviceType.EA_LUNAR_CONNECT_ACE;
                iArr71[70] = 71;
            } catch (NoSuchFieldError unused71) {
            }
            try {
                int[] iArr72 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType72 = DeviceType.EA_PRIMIA_ACE;
                iArr72[71] = 72;
            } catch (NoSuchFieldError unused72) {
            }
            try {
                int[] iArr73 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType73 = DeviceType.WAVEARMOUR2;
                iArr73[73] = 73;
            } catch (NoSuchFieldError unused73) {
            }
            try {
                int[] iArr74 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType74 = DeviceType.WAVEFORCE2;
                iArr74[72] = 74;
            } catch (NoSuchFieldError unused74) {
            }
            try {
                int[] iArr75 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType75 = DeviceType.LUNARFIT;
                iArr75[74] = 75;
            } catch (NoSuchFieldError unused75) {
            }
            try {
                int[] iArr76 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType76 = DeviceType.WAVECALL2;
                iArr76[75] = 76;
            } catch (NoSuchFieldError unused76) {
            }
            try {
                int[] iArr77 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType77 = DeviceType.WAVECALL3;
                iArr77[76] = 77;
            } catch (NoSuchFieldError unused77) {
            }
            try {
                int[] iArr78 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType78 = DeviceType.STORMCALL2;
                iArr78[77] = 78;
            } catch (NoSuchFieldError unused78) {
            }
            try {
                int[] iArr79 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType79 = DeviceType.STORMCALL3;
                iArr79[78] = 79;
            } catch (NoSuchFieldError unused79) {
            }
            try {
                int[] iArr80 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType80 = DeviceType.WAVEASTRA;
                iArr80[79] = 80;
            } catch (NoSuchFieldError unused80) {
            }
            try {
                int[] iArr81 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType81 = DeviceType.WAVEASTRA2;
                iArr81[80] = 81;
            } catch (NoSuchFieldError unused81) {
            }
            try {
                int[] iArr82 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType82 = DeviceType.WAVESIGMA;
                iArr82[81] = 82;
            } catch (NoSuchFieldError unused82) {
            }
            try {
                int[] iArr83 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType83 = DeviceType.WAVESIGMA3;
                iArr83[82] = 83;
            } catch (NoSuchFieldError unused83) {
            }
            try {
                int[] iArr84 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType84 = DeviceType.WAVENEOPLUS;
                iArr84[83] = 84;
            } catch (NoSuchFieldError unused84) {
            }
            try {
                int[] iArr85 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType85 = DeviceType.WAVEACTIVE;
                iArr85[84] = 85;
            } catch (NoSuchFieldError unused85) {
            }
            try {
                int[] iArr86 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType86 = DeviceType.XTENDPRO2;
                iArr86[85] = 86;
            } catch (NoSuchFieldError unused86) {
            }
            try {
                int[] iArr87 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType87 = DeviceType.STROMPROCALL2;
                iArr87[86] = 87;
            } catch (NoSuchFieldError unused87) {
            }
            try {
                int[] iArr88 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType88 = DeviceType.LUNARCALLPRO2;
                iArr88[87] = 88;
            } catch (NoSuchFieldError unused88) {
            }
            try {
                int[] iArr89 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType89 = DeviceType.LUNARCONNECTPRO2;
                iArr89[88] = 89;
            } catch (NoSuchFieldError unused89) {
            }
            try {
                int[] iArr90 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType90 = DeviceType.WAVEPRIMIATALK2;
                iArr90[89] = 90;
            } catch (NoSuchFieldError unused90) {
            }
            try {
                int[] iArr91 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType91 = DeviceType.ULTIMACALLPRO;
                iArr91[90] = 91;
            } catch (NoSuchFieldError unused91) {
            }
            try {
                int[] iArr92 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType92 = DeviceType.ULTIMACONNECTPRO;
                iArr92[91] = 92;
            } catch (NoSuchFieldError unused92) {
            }
            try {
                int[] iArr93 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType93 = DeviceType.WAVECOSMOSTALK;
                iArr93[92] = 93;
            } catch (NoSuchFieldError unused93) {
            }
            try {
                int[] iArr94 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType94 = DeviceType.ULTIMAPRISM;
                iArr94[93] = 94;
            } catch (NoSuchFieldError unused94) {
            }
            try {
                int[] iArr95 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType95 = DeviceType.JC2319B;
                iArr95[109] = 95;
            } catch (NoSuchFieldError unused95) {
            }
            try {
                int[] iArr96 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType96 = DeviceType.ULTIMACHRONOS;
                iArr96[94] = 96;
            } catch (NoSuchFieldError unused96) {
            }
            try {
                int[] iArr97 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType97 = DeviceType.WAVECONVEX;
                iArr97[95] = 97;
            } catch (NoSuchFieldError unused97) {
            }
            try {
                int[] iArr98 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType98 = DeviceType.LUNARORB;
                iArr98[96] = 98;
            } catch (NoSuchFieldError unused98) {
            }
            try {
                int[] iArr99 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType99 = DeviceType.JC2305B;
                iArr99[110] = 99;
            } catch (NoSuchFieldError unused99) {
            }
            try {
                int[] iArr100 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType100 = DeviceType.LUNARPRIME;
                iArr100[97] = 100;
            } catch (NoSuchFieldError unused100) {
            }
            try {
                int[] iArr101 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType101 = DeviceType.PRIMIACURV;
                iArr101[98] = 101;
            } catch (NoSuchFieldError unused101) {
            }
            try {
                int[] iArr102 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType102 = DeviceType.TOUCH_WAVE_NEO;
                iArr102[99] = 102;
            } catch (NoSuchFieldError unused102) {
            }
            try {
                int[] iArr103 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType103 = DeviceType.SMA_WAVE_GENESIS_PRO;
                iArr103[100] = 103;
            } catch (NoSuchFieldError unused103) {
            }
            try {
                int[] iArr104 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType104 = DeviceType.SMA_WAVE_ELEVATE_PRO;
                iArr104[101] = 104;
            } catch (NoSuchFieldError unused104) {
            }
            try {
                int[] iArr105 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType105 = DeviceType.SMA_WAVE_GLORY_PRO;
                iArr105[102] = 105;
            } catch (NoSuchFieldError unused105) {
            }
            try {
                int[] iArr106 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType106 = DeviceType.SMA_LUNAR_SEEK;
                iArr106[103] = 106;
            } catch (NoSuchFieldError unused106) {
            }
            try {
                int[] iArr107 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType107 = DeviceType.SMA_ULTIMA_VOGUE;
                iArr107[104] = 107;
            } catch (NoSuchFieldError unused107) {
            }
            try {
                int[] iArr108 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType108 = DeviceType.SMA_LUNAR_COMET;
                iArr108[105] = 108;
            } catch (NoSuchFieldError unused108) {
            }
            try {
                int[] iArr109 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType109 = DeviceType.SMA_LUNAR_VELOCITY;
                iArr109[106] = 109;
            } catch (NoSuchFieldError unused109) {
            }
            try {
                int[] iArr110 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType110 = DeviceType.TOUCH_WAVE_MAGMA;
                iArr110[107] = 110;
            } catch (NoSuchFieldError unused110) {
            }
            try {
                int[] iArr111 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType111 = DeviceType.TOUCH_WAVE_FORTUNE;
                iArr111[58] = 111;
            } catch (NoSuchFieldError unused111) {
            }
            try {
                int[] iArr112 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType112 = DeviceType.PS1_ENIGMA_OASIS;
                iArr112[111] = 112;
            } catch (NoSuchFieldError unused112) {
            }
            try {
                int[] iArr113 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType113 = DeviceType.ULTIMA_RISE;
                iArr113[112] = 113;
            } catch (NoSuchFieldError unused113) {
            }
            try {
                int[] iArr114 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType114 = DeviceType.WAVE_CHASE;
                iArr114[113] = 114;
            } catch (NoSuchFieldError unused114) {
            }
            try {
                int[] iArr115 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType115 = DeviceType.WAVE_REGAL;
                iArr115[114] = 115;
            } catch (NoSuchFieldError unused115) {
            }
            try {
                int[] iArr116 = $SwitchMap$com$coveiot$android$bleabstract$models$DeviceType;
                DeviceType deviceType116 = DeviceType.BES_ENIGMA_VIRTUO;
                iArr116[115] = 116;
            } catch (NoSuchFieldError unused116) {
            }
        }
    }

    private BleApi getBleImpl() throws DeviceNotInitializedException {
        if (getDeviceType() != null) {
            switch (getDeviceType().ordinal()) {
                case 0:
                    this.mBleApi = LeonardoBleApiImpl.getInstance(context);
                    break;
                case 1:
                    this.mBleApi = IsportBleApiImpl.getInstance();
                    break;
                case 2:
                    this.mBleApi = V7LeonardoBleApiImpl.getInstance(context);
                    break;
                case 3:
                    this.mBleApi = CZ0LeonardoBleApiImpl.getInstance(context);
                    break;
                case 4:
                    this.mBleApi = IsportBleApiImpl.getInstance();
                    break;
                case 5:
                    this.mBleApi = SmartTBleApiImpl.getInstance(context);
                    break;
                case 6:
                    break;
                case 7:
                    this.mBleApi = JStyle1939BleApiImpl.getInstance(context);
                    break;
                case 8:
                    this.mBleApi = JStyle1790BleApiImpl.getInstance(context);
                    break;
                case 9:
                    this.mBleApi = JStyle1810GBleApiImpl.getInstance(context);
                    break;
                case 10:
                    this.mBleApi = JStyle1963DBleApiImpl.getInstance(context);
                    break;
                case 11:
                    this.mBleApi = JStyle1963YHBleApiImpl.getInstance(context);
                    break;
                case 12:
                    this.mBleApi = JStyle1860BleApiImpl.getInstance(context);
                    break;
                case 13:
                    this.mBleApi = JStyle2301ABaseBleApiImpl.Companion.getInstance(context.getApplicationContext());
                    break;
                case 14:
                    this.mBleApi = JStyle2208ABaseBleApiImpl.Companion.getInstance(context.getApplicationContext());
                    break;
                case 15:
                    this.mBleApi = PC60FBleApiImpl.Companion.getInstance(context.getApplicationContext());
                    break;
                case 16:
                    SmaF2BleApiImpl singletonHolder = SmaF2BleApiImpl.Companion.getInstance(context.getApplicationContext());
                    this.mBleApi = singletonHolder;
                    singletonHolder.checkAndStartService();
                    break;
                case 17:
                    SmaS10BleApiImpl singletonHolder2 = SmaS10BleApiImpl.Companion.getInstance(context.getApplicationContext());
                    this.mBleApi = singletonHolder2;
                    singletonHolder2.checkAndStartService();
                    break;
                case 18:
                    SmaS12BleApiImpl singletonHolder3 = SmaS12BleApiImpl.Companion.getInstance(context.getApplicationContext());
                    this.mBleApi = singletonHolder3;
                    singletonHolder3.checkAndStartService();
                    break;
                case 19:
                    this.mBleApi = BpMeasurementImpl.Companion.getInstance(context.getApplicationContext());
                    break;
                case 20:
                    CRPY20BleApiImpl singletonHolder4 = CRPY20BleApiImpl.Companion.getInstance(context.getApplicationContext());
                    this.mBleApi = singletonHolder4;
                    singletonHolder4.checkAndStartService();
                    break;
                case 21:
                    MatrixBleImpl singletonHolder5 = MatrixBleImpl.Companion.getInstance(context.getApplicationContext());
                    this.mBleApi = singletonHolder5;
                    singletonHolder5.checkAndStartService();
                    break;
                case 22:
                    SmaV2BleApiImpl singletonHolder6 = SmaV2BleApiImpl.Companion.getInstance(context.getApplicationContext());
                    this.mBleApi = singletonHolder6;
                    singletonHolder6.checkAndStartService();
                    break;
                case 23:
                    CRPGPF5BleApiImpl singletonHolder7 = CRPGPF5BleApiImpl.Companion.getInstance(context.getApplicationContext());
                    this.mBleApi = singletonHolder7;
                    singletonHolder7.checkAndStartService();
                    break;
                case 24:
                    this.mBleApi = CZ3LeonardoBleApiImpl.Companion.getInstance(context);
                    break;
                case 25:
                    this.mBleApi = CZ1WavePrimeLeonardoBleApiImpl.Companion.getInstance(context);
                    break;
                case 26:
                    this.mBleApi = CA0LeonardoBleApiImpl.Companion.getInstance(context);
                    break;
                case 27:
                    this.mBleApi = CA3LeonardoBleApiImpl.Companion.getInstance(context);
                    break;
                case 28:
                    this.mBleApi = IDOBaseBleApiImpl.Companion.getInstance(context);
                    break;
                case 29:
                    this.mBleApi = CA3BTCallingLeonardoBleApiImpl.Companion.getInstance(context);
                    break;
                case 30:
                    this.mBleApi = CZ1WaveEliteBleApiImpl.Companion.getInstance(context);
                    break;
                case 31:
                    this.mBleApi = CA2LeonardoBleApiImpl.Companion.getInstance(context);
                    break;
                case 32:
                    this.mBleApi = CA5WaveStyleBleApiImpl.Companion.getInstance(context);
                    break;
                case 33:
                    this.mBleApi = CA5WaveBeatBleApiImpl.Companion.getInstance(context);
                    break;
                case 34:
                    this.mBleApi = CA5WavePlayBleApiImpl.Companion.getInstance(context);
                    break;
                case 35:
                    this.mBleApi = CA3WaveCosmosBleApiImpl.Companion.getInstance(context);
                    break;
                case 36:
                    this.mBleApi = CA3BTCallingWaveCosmosProBleApiImpl.Companion.getInstance(context);
                    break;
                case 37:
                    this.mBleApi = CA3BTCallingStromproCallBleApiImpl.Companion.getInstance(context);
                    break;
                case 38:
                    this.mBleApi = IDOConnectBleApiImpl.Companion.getInstance(context.getApplicationContext());
                    break;
                case 39:
                    this.mBleApi = ULC3WaveSmartBleApiImpl.Companion.getInstance(context);
                    break;
                case 40:
                    this.mBleApi = ULC2WaveBeatPlusBleApiImpl.Companion.getInstance(context);
                    break;
                case 41:
                    this.mBleApi = ULC2WaveStylePlusBleApiImpl.Companion.getInstance(context);
                    break;
                case 42:
                    this.mBleApi = ULC2WaveSmartPlusBleApiImpl.Companion.getInstance(context);
                    break;
                case 43:
                    this.mBleApi = ULC2WaveLyncBleApiImpl.Companion.getInstance(context);
                    break;
                case 44:
                    this.mBleApi = CY1BleApiImpl.Companion.getInstance(context);
                    break;
                case 45:
                    this.mBleApi = CY1BleApiImpl.Companion.getInstance(context);
                    break;
                case 46:
                    this.mBleApi = CY1BleApiImpl.Companion.getInstance(context);
                    break;
                case 47:
                    this.mBleApi = CY2BleApiImpl.Companion.getInstance(context);
                    break;
                case 48:
                    TouchELXBaseBleImpl singletonHolder8 = TouchELXBaseBleImpl.Companion.getInstance(context.getApplicationContext());
                    this.mBleApi = singletonHolder8;
                    singletonHolder8.checkAndStartService();
                    break;
                case 49:
                    TouchWaveConnectBleApiImpl singletonHolder9 = TouchWaveConnectBleApiImpl.Companion.getInstance(context.getApplicationContext());
                    this.mBleApi = singletonHolder9;
                    singletonHolder9.checkAndStartService();
                    break;
                case 50:
                    TouchLunarCallBleApiImpl singletonHolder10 = TouchLunarCallBleApiImpl.Companion.getInstance(context.getApplicationContext());
                    this.mBleApi = singletonHolder10;
                    singletonHolder10.checkAndStartService();
                    break;
                case 51:
                    TouchLunarConnectBleApiImpl singletonHolder11 = TouchLunarConnectBleApiImpl.Companion.getInstance(context.getApplicationContext());
                    this.mBleApi = singletonHolder11;
                    singletonHolder11.checkAndStartService();
                    break;
                case 52:
                    TouchLunarCallPlusBleApiImpl singletonHolder12 = TouchLunarCallPlusBleApiImpl.Companion.getInstance(context.getApplicationContext());
                    this.mBleApi = singletonHolder12;
                    singletonHolder12.checkAndStartService();
                    break;
                case 53:
                    TouchLunarConnectPlusBleApiImpl singletonHolder13 = TouchLunarConnectPlusBleApiImpl.Companion.getInstance(context.getApplicationContext());
                    this.mBleApi = singletonHolder13;
                    singletonHolder13.checkAndStartService();
                    break;
                case 54:
                    TouchXtendCallPlusBleApiImpl singletonHolder14 = TouchXtendCallPlusBleApiImpl.Companion.getInstance(context.getApplicationContext());
                    this.mBleApi = singletonHolder14;
                    singletonHolder14.checkAndStartService();
                    break;
                case 55:
                    TouchStormConnectPlusBleApiImpl singletonHolder15 = TouchStormConnectPlusBleApiImpl.Companion.getInstance(context.getApplicationContext());
                    this.mBleApi = singletonHolder15;
                    singletonHolder15.checkAndStartService();
                    break;
                case 56:
                    TouchLunarEmbraceBleApiImpl singletonHolder16 = TouchLunarEmbraceBleApiImpl.Companion.getInstance(context.getApplicationContext());
                    this.mBleApi = singletonHolder16;
                    singletonHolder16.checkAndStartService();
                    break;
                case 57:
                    TouchWaveSpectraBleApiImpl singletonHolder17 = TouchWaveSpectraBleApiImpl.Companion.getInstance(context.getApplicationContext());
                    this.mBleApi = singletonHolder17;
                    singletonHolder17.checkAndStartService();
                    break;
                case 58:
                    TouchWaveFortuneBleApiImpl singletonHolder18 = TouchWaveFortuneBleApiImpl.Companion.getInstance(context.getApplicationContext());
                    this.mBleApi = singletonHolder18;
                    singletonHolder18.checkAndStartService();
                    break;
                case 59:
                    this.mBleApi = ULC5UltimaCallBleApiImpl.Companion.getInstance(context);
                    break;
                case 60:
                    this.mBleApi = ULC5UltimaConnectBleApiImpl.Companion.getInstance(context);
                    break;
                case 61:
                    MatrixWaveForceBleImpl singletonHolder19 = MatrixWaveForceBleImpl.Companion.getInstance(context.getApplicationContext());
                    this.mBleApi = singletonHolder19;
                    singletonHolder19.checkAndStartService();
                    break;
                case 62:
                    MatrixWaveArmourBleImpl singletonHolder20 = MatrixWaveArmourBleImpl.Companion.getInstance(context.getApplicationContext());
                    this.mBleApi = singletonHolder20;
                    singletonHolder20.checkAndStartService();
                    break;
                case 63:
                    EastApexBaseBleApiImpl singletonHolder21 = EastApexBaseBleApiImpl.Companion.getInstance(context.getApplicationContext());
                    this.mBleApi = singletonHolder21;
                    singletonHolder21.checkAndStartService();
                    break;
                case 64:
                    EastApexFlexConnectBleApiImpl singletonHolder22 = EastApexFlexConnectBleApiImpl.Companion.getInstance(context.getApplicationContext());
                    this.mBleApi = singletonHolder22;
                    singletonHolder22.checkAndStartService();
                    break;
                case 65:
                    EastApexStrideVoiceBleApiImpl singletonHolder23 = EastApexStrideVoiceBleApiImpl.Companion.getInstance(context.getApplicationContext());
                    this.mBleApi = singletonHolder23;
                    singletonHolder23.checkAndStartService();
                    break;
                case 66:
                    EastApexXtendPlusBleApiImpl singletonHolder24 = EastApexXtendPlusBleApiImpl.Companion.getInstance(context.getApplicationContext());
                    this.mBleApi = singletonHolder24;
                    singletonHolder24.checkAndStartService();
                    break;
                case 67:
                    EastApexStormPlusBleApiImpl singletonHolder25 = EastApexStormPlusBleApiImpl.Companion.getInstance(context.getApplicationContext());
                    this.mBleApi = singletonHolder25;
                    singletonHolder25.checkAndStartService();
                    break;
                case 68:
                    EastApexCosmosPlusBleApiImpl singletonHolder26 = EastApexCosmosPlusBleApiImpl.Companion.getInstance(context.getApplicationContext());
                    this.mBleApi = singletonHolder26;
                    singletonHolder26.checkAndStartService();
                    break;
                case 69:
                    EastApexLunarCallAceBleApiImpl singletonHolder27 = EastApexLunarCallAceBleApiImpl.Companion.getInstance(context.getApplicationContext());
                    this.mBleApi = singletonHolder27;
                    singletonHolder27.checkAndStartService();
                    break;
                case 70:
                    EastApexLunarConnectAceBleApiImpl singletonHolder28 = EastApexLunarConnectAceBleApiImpl.Companion.getInstance(context.getApplicationContext());
                    this.mBleApi = singletonHolder28;
                    singletonHolder28.checkAndStartService();
                    break;
                case 71:
                    EastApexPrimiaAceBleApiImpl singletonHolder29 = EastApexPrimiaAceBleApiImpl.Companion.getInstance(context.getApplicationContext());
                    this.mBleApi = singletonHolder29;
                    singletonHolder29.checkAndStartService();
                    break;
                case 72:
                    MatrixWaveForce2BleImpl singletonHolder30 = MatrixWaveForce2BleImpl.Companion.getInstance(context.getApplicationContext());
                    this.mBleApi = singletonHolder30;
                    singletonHolder30.checkAndStartService();
                    break;
                case 73:
                    MatrixWaveArmour2BleImpl singletonHolder31 = MatrixWaveArmour2BleImpl.Companion.getInstance(context.getApplicationContext());
                    this.mBleApi = singletonHolder31;
                    singletonHolder31.checkAndStartService();
                    break;
                case 74:
                    MatrixLunarFitBleImpl singletonHolder32 = MatrixLunarFitBleImpl.Companion.getInstance(context.getApplicationContext());
                    this.mBleApi = singletonHolder32;
                    singletonHolder32.checkAndStartService();
                    break;
                case 75:
                    this.mBleApi = TFTWaveCall2BleApiImpl.Companion.getInstance(context.getApplicationContext());
                    break;
                case 76:
                    this.mBleApi = WaveCall3BleApiImpl.Companion.getInstance(context.getApplicationContext());
                    break;
                case 77:
                    this.mBleApi = TFTStormCall2BleApiImpl.Companion.getInstance(context.getApplicationContext());
                    break;
                case 78:
                    this.mBleApi = StormCall3BleApiImpl.Companion.getInstance(context.getApplicationContext());
                    break;
                case 79:
                    this.mBleApi = TFTWaveAstraBleApiImpl.Companion.getInstance(context.getApplicationContext());
                    break;
                case 80:
                    this.mBleApi = WaveAstra2BleApiImpl.Companion.getInstance(context.getApplicationContext());
                    break;
                case 81:
                    this.mBleApi = TFTWaveSigmaBleApiImpl.Companion.getInstance(context.getApplicationContext());
                    break;
                case 82:
                    this.mBleApi = WaveSigma3BleApiImpl.Companion.getInstance(context.getApplicationContext());
                    break;
                case 83:
                    this.mBleApi = TFTWaveNeoPlusBleApiImpl.Companion.getInstance(context.getApplicationContext());
                    break;
                case 84:
                    this.mBleApi = TFTWaveActiveBleApiImpl.Companion.getInstance(context.getApplicationContext());
                    break;
                case 85:
                    this.mBleApi = CA6BTABleApiImpl.Companion.getInstance(context.getApplicationContext());
                    break;
                case 86:
                    this.mBleApi = CA6BTABleApiImpl.Companion.getInstance(context.getApplicationContext());
                    break;
                case 87:
                    this.mBleApi = CY1ABleApiImpl.Companion.getInstance(context.getApplicationContext());
                    break;
                case 88:
                    this.mBleApi = CY1ABleApiImpl.Companion.getInstance(context.getApplicationContext());
                    break;
                case 89:
                    this.mBleApi = CY1ABleApiImpl.Companion.getInstance(context.getApplicationContext());
                    break;
                case 90:
                    this.mBleApi = CY2ABleApiImpl.Companion.getInstance(context.getApplicationContext());
                    break;
                case 91:
                    this.mBleApi = CY2ABleApiImpl.Companion.getInstance(context.getApplicationContext());
                    break;
                case 92:
                    this.mBleApi = CY2ABleApiImpl.Companion.getInstance(context.getApplicationContext());
                    break;
                case 93:
                    this.mBleApi = UltimaPrismBleApiImpl.Companion.getInstance(context.getApplicationContext());
                    break;
                case 94:
                    this.mBleApi = UltimaChronosBleApiImpl.Companion.getInstance(context.getApplicationContext());
                    break;
                case 95:
                    this.mBleApi = WaveConvexBleApiImpl.Companion.getInstance(context.getApplicationContext());
                    break;
                case 96:
                    this.mBleApi = LunarOrbBleApiImpl.Companion.getInstance(context.getApplicationContext());
                    break;
                case 97:
                    this.mBleApi = LunarOrbBleApiImpl.Companion.getInstance(context.getApplicationContext());
                    break;
                case 98:
                    this.mBleApi = PrimiaCurvBleApiImpl.Companion.getInstance(context.getApplicationContext());
                    break;
                case 99:
                    TouchWaveNeoBleApiImpl singletonHolder33 = TouchWaveNeoBleApiImpl.Companion.getInstance(context.getApplicationContext());
                    this.mBleApi = singletonHolder33;
                    singletonHolder33.checkAndStartService();
                    break;
                case 100:
                    SmaWaveGenesisProBleApiImpl singletonHolder34 = SmaWaveGenesisProBleApiImpl.Companion.getInstance(context.getApplicationContext());
                    this.mBleApi = singletonHolder34;
                    singletonHolder34.checkAndStartService();
                    break;
                case 101:
                    SmaWaveElevateProBleApiImpl singletonHolder35 = SmaWaveElevateProBleApiImpl.Companion.getInstance(context.getApplicationContext());
                    this.mBleApi = singletonHolder35;
                    singletonHolder35.checkAndStartService();
                    break;
                case 102:
                    SmaWaveGloryProBleApiImpl singletonHolder36 = SmaWaveGloryProBleApiImpl.Companion.getInstance(context.getApplicationContext());
                    this.mBleApi = singletonHolder36;
                    singletonHolder36.checkAndStartService();
                    break;
                case 103:
                    SmaLunarSeekBleApiImpl singletonHolder37 = SmaLunarSeekBleApiImpl.Companion.getInstance(context.getApplicationContext());
                    this.mBleApi = singletonHolder37;
                    singletonHolder37.checkAndStartService();
                    break;
                case 104:
                    SmaUltimaVogueBleApiImpl singletonHolder38 = SmaUltimaVogueBleApiImpl.Companion.getInstance(context.getApplicationContext());
                    this.mBleApi = singletonHolder38;
                    singletonHolder38.checkAndStartService();
                    break;
                case 105:
                    SmaLunarCometBleApiImpl singletonHolder39 = SmaLunarCometBleApiImpl.Companion.getInstance(context.getApplicationContext());
                    this.mBleApi = singletonHolder39;
                    singletonHolder39.checkAndStartService();
                    break;
                case 106:
                    SmaLunarVelocityBleApiImpl singletonHolder40 = SmaLunarVelocityBleApiImpl.Companion.getInstance(context.getApplicationContext());
                    this.mBleApi = singletonHolder40;
                    singletonHolder40.checkAndStartService();
                    break;
                case 107:
                    TouchWaveMagmaBleApiImpl singletonHolder41 = TouchWaveMagmaBleApiImpl.Companion.getInstance(context.getApplicationContext());
                    this.mBleApi = singletonHolder41;
                    singletonHolder41.checkAndStartService();
                    break;
                case 108:
                    this.mBleApi = JC2230_01BleApiImpl.Companion.getInstance(context);
                    break;
                case 109:
                    this.mBleApi = JC2319BBleApiImpl.Companion.getInstance(context.getApplicationContext());
                    break;
                case 110:
                    this.mBleApi = JC2305BBleApiImpl.Companion.getInstance(context.getApplicationContext());
                    break;
                case 111:
                    this.mBleApi = PS1EnigmaOasisBleApiImpl.Companion.getInstance(context);
                    break;
                case 112:
                    this.mBleApi = UltimaRiseBleApiImpl.Companion.getInstance(context);
                    break;
                case 113:
                    this.mBleApi = WaveChaseBleApiImpl.Companion.getInstance(context);
                    break;
                case 114:
                    this.mBleApi = WaveRegalBleApiImpl.Companion.getInstance(context);
                    break;
                case 115:
                    this.mBleApi = BESEnigmaVirtuoBleApiImpl.Companion.getInstance(context.getApplicationContext());
                    break;
                default:
                    this.mBleApi = UnknownBleApiImpl.getInstance();
                    break;
            }
            return this.mBleApi;
        }
        new DeviceNotInitializedException("device is not initialized");
        return this.mBleApi;
    }

    public static BleApiManager getInstance(Context context2) {
        if (instance == null) {
            instance = new BleApiManager();
            context = context2;
            BleApiUtils.getMetadata(context2);
            SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
            pref = sharedPreferences;
            editor = sharedPreferences.edit();
        }
        return instance;
    }

    public BleApi getBleApi() throws DeviceNotInitializedException {
        return getBleImpl();
    }

    public DeviceType getDeviceType() {
        DeviceType deviceType;
        DeviceType deviceType2 = DeviceType.UNKNOWN;
        return (pref == null || (deviceType = (DeviceType) new Gson().fromJson(pref.getString(DEVICE_TYPE, null), (Class<Object>) DeviceType.class)) == null) ? deviceType2 : deviceType;
    }

    public BleApi getSecondaryBleImpl(DeviceType deviceType) {
        if (deviceType != null) {
            int ordinal = deviceType.ordinal();
            if (ordinal != 0) {
                if (ordinal != 15) {
                    if (ordinal == 19) {
                        BpMeasurementImpl singletonHolder = BpMeasurementImpl.Companion.getInstance(context);
                        singletonHolder.checkAndStartService();
                        return singletonHolder;
                    } else if (ordinal != 21) {
                        if (ordinal != 8) {
                            if (ordinal != 9) {
                                if (ordinal != 11) {
                                    if (ordinal != 12) {
                                        return null;
                                    }
                                    return JStyle1860BleApiImpl.getInstance(context);
                                }
                                return JStyle1963YHBleApiImpl.getInstance(context);
                            }
                            return JStyle1810GBleApiImpl.getInstance(context);
                        }
                        return JStyle1790BleApiImpl.getInstance(context);
                    } else {
                        return MatrixBleImpl.Companion.getInstance(context);
                    }
                }
                return PC60FBleApiImpl.Companion.getInstance(context);
            }
            return LeonardoBleApiImpl.getInstance(context);
        }
        return null;
    }

    public void init(DeviceType deviceType) {
        editor.putString(DEVICE_TYPE, new Gson().toJson(deviceType));
        editor.commit();
    }
}
