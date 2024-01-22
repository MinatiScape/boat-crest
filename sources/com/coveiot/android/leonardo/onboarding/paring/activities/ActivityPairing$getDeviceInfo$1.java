package com.coveiot.android.leonardo.onboarding.paring.activities;

import android.widget.Toast;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.DeviceType;
import com.coveiot.android.bleabstract.models.ProgressData;
import com.coveiot.android.bleabstract.preferences.PreferenceManagerAbstract;
import com.coveiot.android.bleabstract.request.GetFirmwareCapabilityRequest;
import com.coveiot.android.bleabstract.request.SetScreenTimeOutRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.bleabstract.response.DeviceInfoData;
import com.coveiot.android.bleabstract.response.DeviceInfoResponse;
import com.coveiot.android.bleabstract.response.GetFirmwareCapabilityResponse;
import com.coveiot.android.boat.R;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.devicemodels.ModelNameList;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.coveaccess.device.model.BleDeviceInfo;
import com.coveiot.coveaccess.prefs.PreferenceManager;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.data.FirmwareCapabilityData;
import com.coveiot.utils.utility.LogHelper;
import com.google.gson.Gson;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class ActivityPairing$getDeviceInfo$1 implements DataResultListener {
    public final /* synthetic */ ActivityPairing h;
    public final /* synthetic */ String i;
    public final /* synthetic */ String j;

    /* loaded from: classes5.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[DeviceType.values().length];
            try {
                iArr[DeviceType.matrix.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[DeviceType.WAVEFORCE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[DeviceType.WAVEARMOUR.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[DeviceType.LUNARFIT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[DeviceType.WAVEARMOUR2.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[DeviceType.WAVEFORCE2.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[DeviceType.TOUCH_WAVE_CALL_PLUS.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr[DeviceType.TOUCH_WAVE_CONNECT_PLUS.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                iArr[DeviceType.TOUCH_LUNAR_CALL.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                iArr[DeviceType.TOUCH_LUNAR_CONNECT.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                iArr[DeviceType.TOUCH_LUNAR_CALL_PLUS.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                iArr[DeviceType.TOUCH_LUNAR_CONNECT_PLUS.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                iArr[DeviceType.TOUCH_XTEND_CALL_PLUS.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                iArr[DeviceType.TOUCH_STORM_CONNECT_PLUS.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                iArr[DeviceType.TOUCH_WAVE_NEO.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                iArr[DeviceType.TOUCH_WAVE_MAGMA.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                iArr[DeviceType.TOUCH_LUNAR_EMBRACE.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                iArr[DeviceType.TOUCH_WAVE_SPECTRA.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                iArr[DeviceType.EA_LEAP_CALL.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                iArr[DeviceType.EA_FLEX_CONNECT.ordinal()] = 20;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                iArr[DeviceType.EA_STRIDE_VOICE.ordinal()] = 21;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                iArr[DeviceType.EA_XTEND_PLUS.ordinal()] = 22;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                iArr[DeviceType.EA_STORM_PLUS.ordinal()] = 23;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                iArr[DeviceType.EA_COSMOS_PLUS.ordinal()] = 24;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                iArr[DeviceType.EA_LUNAR_CALL_ACE.ordinal()] = 25;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                iArr[DeviceType.EA_LUNAR_CONNECT_ACE.ordinal()] = 26;
            } catch (NoSuchFieldError unused26) {
            }
            try {
                iArr[DeviceType.EA_PRIMIA_ACE.ordinal()] = 27;
            } catch (NoSuchFieldError unused27) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public ActivityPairing$getDeviceInfo$1(ActivityPairing activityPairing, String str, String str2) {
        this.h = activityPairing;
        this.i = str;
        this.j = str2;
    }

    public static final void b(ActivityPairing this$0, BleBaseError error) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(error, "$error");
        Toast.makeText(this$0, error.getErrorMsg(), 1).show();
        this$0.dismissProgress();
    }

    @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
    public void onDataError(@NotNull final BleBaseError error) {
        Intrinsics.checkNotNullParameter(error, "error");
        final ActivityPairing activityPairing = this.h;
        activityPairing.runOnUiThread(new Runnable() { // from class: com.coveiot.android.leonardo.onboarding.paring.activities.k
            @Override // java.lang.Runnable
            public final void run() {
                ActivityPairing$getDeviceInfo$1.b(ActivityPairing.this, error);
            }
        });
    }

    @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
    public void onDataResponse(@NotNull BleBaseResponse response) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        boolean z6;
        String value;
        boolean z7;
        boolean z8;
        boolean z9;
        Intrinsics.checkNotNullParameter(response, "response");
        if (response.getResponseData() instanceof DeviceInfoResponse) {
            Object responseData = response.getResponseData();
            Intrinsics.checkNotNull(responseData, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.DeviceInfoResponse");
            DeviceInfoData deviceInfo = ((DeviceInfoResponse) responseData).getDeviceInfo();
            DeviceUtils.Companion companion = DeviceUtils.Companion;
            if (companion.isJstyleDevice(this.h)) {
                z9 = this.h.s;
                if (!z9 && deviceInfo != null && deviceInfo.getSerialNo() != null && deviceInfo.getModelNo() != null && deviceInfo.getFwVersion() != null) {
                    this.h.s = true;
                    BleDeviceInfo bleDeviceInfo = BleDeviceInfo.getInstance();
                    bleDeviceInfo.setMacAddress(this.i);
                    String serialNo = deviceInfo.getSerialNo();
                    if (serialNo == null) {
                        String str = this.i;
                        serialNo = str != null ? new Regex(":").replace(str, "") : null;
                    }
                    String modelNo = deviceInfo.getModelNo();
                    if (BleApiManager.getInstance(this.h).getDeviceType() == DeviceType.jstyle1810G) {
                        modelNo = ModelNameList.MODEL_NUMBER_COVE9.getValue();
                    } else if (BleApiManager.getInstance(this.h).getDeviceType() == DeviceType.jstyle1790) {
                        modelNo = ModelNameList.MODEL_NUMBER_BA1009V11.getValue();
                    } else if (BleApiManager.getInstance(this.h).getDeviceType() == DeviceType.jstyle1963D) {
                        modelNo = ModelNameList.MODEL_NUMBER_WA1002V11.getValue();
                    } else if (BleApiManager.getInstance(this.h).getDeviceType() == DeviceType.jstyle1963YH) {
                        modelNo = ModelNameList.MODEL_NUMBER_TWTXW1.getValue();
                    } else if (BleApiManager.getInstance(this.h).getDeviceType() == DeviceType.jstyle1860) {
                        modelNo = ModelNameList.MODEL_NUMBER_WA4V1.getValue();
                    }
                    Intrinsics.checkNotNull(modelNo);
                    String lowerCase = modelNo.toLowerCase();
                    Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase()");
                    bleDeviceInfo.setmModelNumber(lowerCase);
                    bleDeviceInfo.setSerialNumber(serialNo);
                    bleDeviceInfo.setmDeviceName(this.j);
                    bleDeviceInfo.setFirmwareRevision(deviceInfo.getFwVersion());
                    bleDeviceInfo.setmManufacturerName("KaHa");
                    bleDeviceInfo.setHwRevision("2.0");
                    PreferenceManager preferenceManager = PreferenceManager.getInstance();
                    preferenceManager.saveDeviceAgent(this.h.getString(R.string.cove_client_id) + ';' + modelNo + ';' + serialNo);
                    SessionManager.getInstance(this.h).saveBleDeviceInfo(new Gson().toJson(bleDeviceInfo));
                    ActivityPairing activityPairing = this.h;
                    Intrinsics.checkNotNullExpressionValue(bleDeviceInfo, "bleDeviceInfo");
                    activityPairing.x(bleDeviceInfo);
                }
            } else if (companion.isSmaDevice(this.h)) {
                z8 = this.h.s;
                if (!z8 && deviceInfo != null && deviceInfo.getSerialNo() != null && deviceInfo.getModelNo() != null && deviceInfo.getFwVersion() != null) {
                    this.h.s = true;
                    final BleDeviceInfo bleDeviceInfo2 = BleDeviceInfo.getInstance();
                    bleDeviceInfo2.setMacAddress(this.i);
                    String serialNo2 = deviceInfo.getSerialNo();
                    if (serialNo2 == null) {
                        String str2 = this.i;
                        serialNo2 = str2 != null ? new Regex(":").replace(str2, "") : null;
                    }
                    if (kotlin.text.m.equals(SessionManager.getInstance(this.h).getDeviceType(), this.h.getString(R.string.smaf2_device), false)) {
                        String lowerCase2 = ModelNameList.MODEL_NUMBER_WA3V1.getValue().toLowerCase();
                        Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase()");
                        bleDeviceInfo2.setmModelNumber(lowerCase2);
                    } else if (kotlin.text.m.equals(SessionManager.getInstance(this.h).getDeviceType(), this.h.getString(R.string.smas12_device), false)) {
                        String lowerCase3 = ModelNameList.MODEL_NUMBER_WA6V1.getValue().toLowerCase();
                        Intrinsics.checkNotNullExpressionValue(lowerCase3, "this as java.lang.String).toLowerCase()");
                        bleDeviceInfo2.setmModelNumber(lowerCase3);
                    } else if (kotlin.text.m.equals(SessionManager.getInstance(this.h).getDeviceType(), this.h.getString(R.string.sma_wave_genesis_pro), false)) {
                        String lowerCase4 = ModelNameList.MODEL_NUMBER_WA41V1.getValue().toLowerCase();
                        Intrinsics.checkNotNullExpressionValue(lowerCase4, "this as java.lang.String).toLowerCase()");
                        bleDeviceInfo2.setmModelNumber(lowerCase4);
                    } else if (kotlin.text.m.equals(SessionManager.getInstance(this.h).getDeviceType(), this.h.getString(R.string.sma_wave_elevate_pro), false)) {
                        String lowerCase5 = ModelNameList.MODEL_NUMBER_WA41V2.getValue().toLowerCase();
                        Intrinsics.checkNotNullExpressionValue(lowerCase5, "this as java.lang.String).toLowerCase()");
                        bleDeviceInfo2.setmModelNumber(lowerCase5);
                    } else if (kotlin.text.m.equals(SessionManager.getInstance(this.h).getDeviceType(), this.h.getString(R.string.sma_wave_glory_pro), false)) {
                        String lowerCase6 = ModelNameList.MODEL_NUMBER_WA41V3.getValue().toLowerCase();
                        Intrinsics.checkNotNullExpressionValue(lowerCase6, "this as java.lang.String).toLowerCase()");
                        bleDeviceInfo2.setmModelNumber(lowerCase6);
                    } else if (kotlin.text.m.equals(SessionManager.getInstance(this.h).getDeviceType(), this.h.getString(R.string.sma_ultima_vogue), false)) {
                        String lowerCase7 = ModelNameList.MODEL_NUMBER_WA42V1.getValue().toLowerCase();
                        Intrinsics.checkNotNullExpressionValue(lowerCase7, "this as java.lang.String).toLowerCase()");
                        bleDeviceInfo2.setmModelNumber(lowerCase7);
                    } else if (kotlin.text.m.equals(SessionManager.getInstance(this.h).getDeviceType(), this.h.getString(R.string.sma_lunar_seek), false)) {
                        String lowerCase8 = ModelNameList.MODEL_NUMBER_WA43V1.getValue().toLowerCase();
                        Intrinsics.checkNotNullExpressionValue(lowerCase8, "this as java.lang.String).toLowerCase()");
                        bleDeviceInfo2.setmModelNumber(lowerCase8);
                    } else if (kotlin.text.m.equals(SessionManager.getInstance(this.h).getDeviceType(), this.h.getString(R.string.sma_lunar_comet), false)) {
                        String lowerCase9 = ModelNameList.MODEL_NUMBER_WA43V2.getValue().toLowerCase();
                        Intrinsics.checkNotNullExpressionValue(lowerCase9, "this as java.lang.String).toLowerCase()");
                        bleDeviceInfo2.setmModelNumber(lowerCase9);
                    } else if (kotlin.text.m.equals(SessionManager.getInstance(this.h).getDeviceType(), this.h.getString(R.string.sma_lunar_velocity), false)) {
                        String lowerCase10 = ModelNameList.MODEL_NUMBER_WA43V3.getValue().toLowerCase();
                        Intrinsics.checkNotNullExpressionValue(lowerCase10, "this as java.lang.String).toLowerCase()");
                        bleDeviceInfo2.setmModelNumber(lowerCase10);
                    }
                    bleDeviceInfo2.setSerialNumber(serialNo2);
                    bleDeviceInfo2.setmDeviceName(this.j);
                    bleDeviceInfo2.setFirmwareRevision(deviceInfo.getFwVersion());
                    bleDeviceInfo2.setmManufacturerName(deviceInfo.getManufacturerName());
                    bleDeviceInfo2.setHwRevision(deviceInfo.getHwVersion());
                    PreferenceManager preferenceManager2 = PreferenceManager.getInstance();
                    preferenceManager2.saveDeviceAgent(this.h.getString(R.string.cove_client_id) + ';' + bleDeviceInfo2.getmModelNumber() + ';' + serialNo2);
                    SessionManager.getInstance(this.h).saveBleDeviceInfo(new Gson().toJson(bleDeviceInfo2));
                    BleApi bleApi = BleApiManager.getInstance(this.h).getBleApi();
                    SetScreenTimeOutRequest build = new SetScreenTimeOutRequest.Builder(Integer.parseInt(AppConstants.MERCURY_SCREEN_TIMEOUT.getValue())).build();
                    Intrinsics.checkNotNullExpressionValue(build, "Builder(AppConstants.MER…                 .build()");
                    final ActivityPairing activityPairing2 = this.h;
                    bleApi.setUserSettings(build, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.activities.ActivityPairing$getDeviceInfo$1$onDataResponse$1
                        @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                        public void onSettingsError(@NotNull BleBaseError error) {
                            Intrinsics.checkNotNullParameter(error, "error");
                            LogHelper.d(ActivityPairing.this.getTAG(), "Screen timeout setting failed");
                            ActivityPairing activityPairing3 = ActivityPairing.this;
                            BleDeviceInfo bleDeviceInfo3 = bleDeviceInfo2;
                            Intrinsics.checkNotNullExpressionValue(bleDeviceInfo3, "bleDeviceInfo");
                            activityPairing3.x(bleDeviceInfo3);
                        }

                        @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                        public void onSettingsResponse(@NotNull BleBaseResponse response2) {
                            Intrinsics.checkNotNullParameter(response2, "response");
                            LogHelper.d(ActivityPairing.this.getTAG(), "Screen timeout set to 10 seconds");
                            ActivityPairing activityPairing3 = ActivityPairing.this;
                            BleDeviceInfo bleDeviceInfo3 = bleDeviceInfo2;
                            Intrinsics.checkNotNullExpressionValue(bleDeviceInfo3, "bleDeviceInfo");
                            activityPairing3.x(bleDeviceInfo3);
                        }
                    });
                }
            } else if (companion.isMoyangDevice(this.h)) {
                z7 = this.h.s;
                if (!z7 && deviceInfo != null && deviceInfo.getSerialNo() != null && deviceInfo.getModelNo() != null && deviceInfo.getFwVersion() != null) {
                    this.h.s = true;
                    BleDeviceInfo bleDeviceInfo3 = BleDeviceInfo.getInstance();
                    bleDeviceInfo3.setMacAddress(this.i);
                    String serialNo3 = deviceInfo.getSerialNo();
                    if (serialNo3 == null) {
                        String str3 = this.i;
                        serialNo3 = str3 != null ? new Regex(":").replace(str3, "") : null;
                    }
                    String modelNo2 = deviceInfo.getModelNo();
                    if (BleApiManager.getInstance(this.h).getDeviceType() == DeviceType.kh17) {
                        modelNo2 = ModelNameList.MODEL_NUMBER_WA5V1.getValue();
                    } else if (BleApiManager.getInstance(this.h).getDeviceType() == DeviceType.crpGPF5) {
                        modelNo2 = ModelNameList.MODEL_NUMBER_WA5V2.getValue();
                    }
                    Intrinsics.checkNotNull(modelNo2);
                    String lowerCase11 = modelNo2.toLowerCase();
                    Intrinsics.checkNotNullExpressionValue(lowerCase11, "this as java.lang.String).toLowerCase()");
                    bleDeviceInfo3.setmModelNumber(lowerCase11);
                    bleDeviceInfo3.setSerialNumber(serialNo3);
                    bleDeviceInfo3.setmDeviceName(this.j);
                    bleDeviceInfo3.setFirmwareRevision(deviceInfo.getFwVersion());
                    bleDeviceInfo3.setmManufacturerName("KaHa");
                    bleDeviceInfo3.setHwRevision("2.0");
                    PreferenceManager preferenceManager3 = PreferenceManager.getInstance();
                    preferenceManager3.saveDeviceAgent(this.h.getString(R.string.cove_client_id) + ';' + modelNo2 + ';' + serialNo3);
                    SessionManager.getInstance(this.h).saveBleDeviceInfo(new Gson().toJson(bleDeviceInfo3));
                    ActivityPairing activityPairing3 = this.h;
                    Intrinsics.checkNotNullExpressionValue(bleDeviceInfo3, "bleDeviceInfo");
                    activityPairing3.x(bleDeviceInfo3);
                }
            } else {
                if (companion.isMatrixDevice(this.h)) {
                    z6 = this.h.s;
                    if (!z6 && deviceInfo != null && deviceInfo.getFwVersion() != null) {
                        this.h.s = true;
                        BleDeviceInfo bleDeviceInfo4 = BleDeviceInfo.getInstance();
                        bleDeviceInfo4.setMacAddress(this.i);
                        String serialNo4 = deviceInfo.getSerialNo();
                        if (serialNo4 == null) {
                            String str4 = this.i;
                            serialNo4 = str4 != null ? new Regex(":").replace(str4, "") : null;
                        }
                        DeviceType deviceType = BleApiManager.getInstance(this.h).getDeviceType();
                        switch (deviceType != null ? WhenMappings.$EnumSwitchMapping$0[deviceType.ordinal()] : -1) {
                            case 1:
                                value = ModelNameList.MODEL_NUMBER_WA8V1.getValue();
                                break;
                            case 2:
                                value = ModelNameList.MODEL_NUMBER_WA28V1.getValue();
                                break;
                            case 3:
                                value = ModelNameList.MODEL_NUMBER_WA28V2.getValue();
                                break;
                            case 4:
                                value = ModelNameList.MODEL_NUMBER_WA28V3.getValue();
                                break;
                            case 5:
                                value = ModelNameList.MODEL_NUMBER_WA28V4.getValue();
                                break;
                            case 6:
                                value = ModelNameList.MODEL_NUMBER_WA28V5.getValue();
                                break;
                            default:
                                value = ModelNameList.MODEL_NUMBER_WA8V1.getValue();
                                break;
                        }
                        Intrinsics.checkNotNull(value);
                        String lowerCase12 = value.toLowerCase();
                        Intrinsics.checkNotNullExpressionValue(lowerCase12, "this as java.lang.String).toLowerCase()");
                        bleDeviceInfo4.setmModelNumber(lowerCase12);
                        bleDeviceInfo4.setSerialNumber(serialNo4);
                        bleDeviceInfo4.setmDeviceName(this.j);
                        bleDeviceInfo4.setFirmwareRevision(deviceInfo.getFwVersion());
                        bleDeviceInfo4.setmManufacturerName("KaHa");
                        bleDeviceInfo4.setHwRevision(deviceInfo.getHwVersion());
                        PreferenceManager preferenceManager4 = PreferenceManager.getInstance();
                        preferenceManager4.saveDeviceAgent(this.h.getString(R.string.cove_client_id) + ';' + value + ';' + serialNo4);
                        SessionManager.getInstance(this.h).saveBleDeviceInfo(new Gson().toJson(bleDeviceInfo4));
                        ActivityPairing activityPairing4 = this.h;
                        Intrinsics.checkNotNullExpressionValue(bleDeviceInfo4, "bleDeviceInfo");
                        activityPairing4.x(bleDeviceInfo4);
                    }
                } else if (companion.isIDODevice(this.h)) {
                    z5 = this.h.s;
                    if (!z5 && deviceInfo != null && deviceInfo.getFwVersion() != null) {
                        this.h.s = true;
                        BleDeviceInfo bleDeviceInfo5 = BleDeviceInfo.getInstance();
                        bleDeviceInfo5.setMacAddress(this.i);
                        String serialNo5 = deviceInfo.getSerialNo();
                        if (serialNo5 == null) {
                            String str5 = this.i;
                            serialNo5 = str5 != null ? new Regex(":").replace(str5, "") : null;
                        }
                        String modelNo3 = deviceInfo.getModelNo();
                        if (BleApiManager.getInstance(this.h).getDeviceType() == DeviceType.IDO_SELECT) {
                            modelNo3 = ModelNameList.MODEL_NUMBER_WA14V1.getValue();
                        } else if (BleApiManager.getInstance(this.h).getDeviceType() == DeviceType.IDO_CONNECT) {
                            modelNo3 = ModelNameList.MODEL_NUMBER_WA15V1.getValue();
                        }
                        Intrinsics.checkNotNull(modelNo3);
                        String lowerCase13 = modelNo3.toLowerCase();
                        Intrinsics.checkNotNullExpressionValue(lowerCase13, "this as java.lang.String).toLowerCase()");
                        bleDeviceInfo5.setmModelNumber(lowerCase13);
                        bleDeviceInfo5.setSerialNumber(serialNo5);
                        bleDeviceInfo5.setmDeviceName(this.j);
                        bleDeviceInfo5.setFirmwareRevision(deviceInfo.getFwVersion());
                        bleDeviceInfo5.setmManufacturerName("KaHa");
                        bleDeviceInfo5.setHwRevision("2.0");
                        PreferenceManager preferenceManager5 = PreferenceManager.getInstance();
                        preferenceManager5.saveDeviceAgent(this.h.getString(R.string.cove_client_id) + ';' + modelNo3 + ';' + serialNo5);
                        SessionManager.getInstance(this.h).saveBleDeviceInfo(new Gson().toJson(bleDeviceInfo5));
                        ActivityPairing activityPairing5 = this.h;
                        Intrinsics.checkNotNullExpressionValue(bleDeviceInfo5, "bleDeviceInfo");
                        activityPairing5.x(bleDeviceInfo5);
                    }
                } else if (!kotlin.text.m.equals(SessionManager.getInstance(this.h).getDeviceType(), this.h.getResources().getString(R.string.v2_device), false) && !SessionManager.getInstance(this.h).getDeviceType().equals(this.h.getString(R.string.v7_device)) && !companion.isCZDevice(this.h) && !companion.isCADevice(this.h) && !companion.isCYDevice(this.h) && !companion.isPS1Device(this.h) && !companion.isBESDevice(this.h) && !kotlin.text.m.equals(SessionManager.getInstance(this.h).getDeviceType(), this.h.getResources().getString(R.string.smart_t), false)) {
                    if (kotlin.text.m.equals(SessionManager.getInstance(this.h).getDeviceType(), this.h.getResources().getString(R.string.C11G), false)) {
                        z4 = this.h.s;
                        if (!z4 && deviceInfo != null && deviceInfo.getFwVersion() != null) {
                            this.h.s = true;
                            BleDeviceInfo bleDeviceInfo6 = BleDeviceInfo.getInstance();
                            bleDeviceInfo6.setMacAddress(this.i);
                            deviceInfo.getSerialNo();
                            String str6 = this.i;
                            String replace = str6 != null ? new Regex(":").replace(str6, "") : null;
                            String value2 = ModelNameList.MODEL_NUMBER_WA2V1.getValue();
                            bleDeviceInfo6.setmModelNumber(value2);
                            bleDeviceInfo6.setSerialNumber(replace);
                            bleDeviceInfo6.setmDeviceName(this.j + this.i);
                            bleDeviceInfo6.setFirmwareRevision(deviceInfo.getFwVersion());
                            bleDeviceInfo6.setmManufacturerName("KaHa");
                            bleDeviceInfo6.setHwRevision("1.0");
                            PreferenceManager preferenceManager6 = PreferenceManager.getInstance();
                            preferenceManager6.saveDeviceAgent(this.h.getString(R.string.cove_client_id) + ';' + value2 + ';' + replace);
                            SessionManager.getInstance(this.h).saveBleDeviceInfo(new Gson().toJson(bleDeviceInfo6));
                            ActivityPairing activityPairing6 = this.h;
                            Intrinsics.checkNotNullExpressionValue(bleDeviceInfo6, "bleDeviceInfo");
                            activityPairing6.x(bleDeviceInfo6);
                        }
                    } else if (companion.isTouchELXDevice(this.h)) {
                        z3 = this.h.s;
                        if (!z3 && deviceInfo != null && deviceInfo.getFwVersion() != null) {
                            this.h.s = true;
                            BleDeviceInfo bleDeviceInfo7 = BleDeviceInfo.getInstance();
                            bleDeviceInfo7.setMacAddress(this.i);
                            String serialNo6 = deviceInfo.getSerialNo();
                            if (serialNo6 == null) {
                                String str7 = this.i;
                                serialNo6 = str7 != null ? new Regex(":").replace(str7, "") : null;
                            }
                            String modelNo4 = deviceInfo.getModelNo();
                            DeviceType deviceType2 = BleApiManager.getInstance(this.h).getDeviceType();
                            switch (deviceType2 != null ? WhenMappings.$EnumSwitchMapping$0[deviceType2.ordinal()] : -1) {
                                case 7:
                                    modelNo4 = ModelNameList.MODEL_NUMBER_WA26V1.getValue();
                                    Unit unit = Unit.INSTANCE;
                                    break;
                                case 8:
                                    modelNo4 = ModelNameList.MODEL_NUMBER_WA26V2.getValue();
                                    Unit unit2 = Unit.INSTANCE;
                                    break;
                                case 9:
                                    modelNo4 = ModelNameList.MODEL_NUMBER_WA23V1.getValue();
                                    Unit unit3 = Unit.INSTANCE;
                                    break;
                                case 10:
                                    modelNo4 = ModelNameList.MODEL_NUMBER_WA23V2.getValue();
                                    Unit unit4 = Unit.INSTANCE;
                                    break;
                                case 11:
                                    modelNo4 = ModelNameList.MODEL_NUMBER_WA25V1.getValue();
                                    Unit unit5 = Unit.INSTANCE;
                                    break;
                                case 12:
                                    modelNo4 = ModelNameList.MODEL_NUMBER_WA25V2.getValue();
                                    Unit unit6 = Unit.INSTANCE;
                                    break;
                                case 13:
                                    modelNo4 = ModelNameList.MODEL_NUMBER_WA24V1.getValue();
                                    Unit unit7 = Unit.INSTANCE;
                                    break;
                                case 14:
                                    modelNo4 = ModelNameList.MODEL_NUMBER_WA24V2.getValue();
                                    Unit unit8 = Unit.INSTANCE;
                                    break;
                                case 15:
                                    modelNo4 = ModelNameList.MODEL_NUMBER_WA44V1.getValue();
                                    Unit unit9 = Unit.INSTANCE;
                                    break;
                                case 16:
                                    modelNo4 = ModelNameList.MODEL_NUMBER_WA45V1.getValue();
                                    Unit unit10 = Unit.INSTANCE;
                                    break;
                                case 17:
                                    modelNo4 = ModelNameList.MODEL_NUMBER_WA22V1.getValue();
                                    Unit unit11 = Unit.INSTANCE;
                                    break;
                                case 18:
                                    modelNo4 = ModelNameList.MODEL_NUMBER_WA22V2.getValue();
                                default:
                                    Unit unit12 = Unit.INSTANCE;
                                    break;
                            }
                            Intrinsics.checkNotNull(modelNo4);
                            String lowerCase14 = modelNo4.toLowerCase();
                            Intrinsics.checkNotNullExpressionValue(lowerCase14, "this as java.lang.String).toLowerCase()");
                            bleDeviceInfo7.setmModelNumber(lowerCase14);
                            bleDeviceInfo7.setSerialNumber(serialNo6);
                            bleDeviceInfo7.setmDeviceName(this.j);
                            bleDeviceInfo7.setFirmwareRevision(deviceInfo.getFwVersion());
                            bleDeviceInfo7.setmManufacturerName("KaHa");
                            bleDeviceInfo7.setHwRevision("1.0");
                            PreferenceManager preferenceManager7 = PreferenceManager.getInstance();
                            preferenceManager7.saveDeviceAgent(this.h.getString(R.string.cove_client_id) + ';' + modelNo4 + ';' + serialNo6);
                            SessionManager.getInstance(this.h).saveBleDeviceInfo(new Gson().toJson(bleDeviceInfo7));
                            ActivityPairing activityPairing7 = this.h;
                            Intrinsics.checkNotNullExpressionValue(bleDeviceInfo7, "bleDeviceInfo");
                            activityPairing7.x(bleDeviceInfo7);
                        }
                    } else if (companion.isEastApexDevice(this.h)) {
                        z2 = this.h.s;
                        if (!z2 && deviceInfo != null && deviceInfo.getFwVersion() != null) {
                            this.h.s = true;
                            BleDeviceInfo bleDeviceInfo8 = BleDeviceInfo.getInstance();
                            bleDeviceInfo8.setMacAddress(this.i);
                            String serialNo7 = deviceInfo.getSerialNo();
                            if (serialNo7 == null) {
                                String str8 = this.i;
                                serialNo7 = str8 != null ? new Regex(":").replace(str8, "") : null;
                            }
                            String modelNo5 = deviceInfo.getModelNo();
                            DeviceType deviceType3 = BleApiManager.getInstance(this.h).getDeviceType();
                            switch (deviceType3 != null ? WhenMappings.$EnumSwitchMapping$0[deviceType3.ordinal()] : -1) {
                                case 19:
                                    modelNo5 = ModelNameList.MODEL_NUMBER_WA31V2.getValue();
                                    Unit unit13 = Unit.INSTANCE;
                                    break;
                                case 20:
                                    modelNo5 = ModelNameList.MODEL_NUMBER_WA31V1.getValue();
                                    Unit unit14 = Unit.INSTANCE;
                                    break;
                                case 21:
                                    modelNo5 = ModelNameList.MODEL_NUMBER_WA31V3.getValue();
                                    Unit unit15 = Unit.INSTANCE;
                                    break;
                                case 22:
                                    modelNo5 = ModelNameList.MODEL_NUMBER_WA29V1.getValue();
                                    Unit unit16 = Unit.INSTANCE;
                                    break;
                                case 23:
                                    modelNo5 = ModelNameList.MODEL_NUMBER_WA29V2.getValue();
                                    Unit unit17 = Unit.INSTANCE;
                                    break;
                                case 24:
                                    modelNo5 = ModelNameList.MODEL_NUMBER_WA29V3.getValue();
                                    Unit unit18 = Unit.INSTANCE;
                                    break;
                                case 25:
                                    modelNo5 = ModelNameList.MODEL_NUMBER_WA30V1.getValue();
                                    Unit unit19 = Unit.INSTANCE;
                                    break;
                                case 26:
                                    modelNo5 = ModelNameList.MODEL_NUMBER_WA30V2.getValue();
                                    Unit unit20 = Unit.INSTANCE;
                                    break;
                                case 27:
                                    modelNo5 = ModelNameList.MODEL_NUMBER_WA30V3.getValue();
                                default:
                                    Unit unit21 = Unit.INSTANCE;
                                    break;
                            }
                            Intrinsics.checkNotNull(modelNo5);
                            String lowerCase15 = modelNo5.toLowerCase();
                            Intrinsics.checkNotNullExpressionValue(lowerCase15, "this as java.lang.String).toLowerCase()");
                            bleDeviceInfo8.setmModelNumber(lowerCase15);
                            bleDeviceInfo8.setSerialNumber(serialNo7);
                            bleDeviceInfo8.setmDeviceName(this.j);
                            bleDeviceInfo8.setFirmwareRevision(deviceInfo.getFwVersion());
                            bleDeviceInfo8.setmManufacturerName("KaHa");
                            bleDeviceInfo8.setHwRevision("1.0");
                            PreferenceManager preferenceManager8 = PreferenceManager.getInstance();
                            preferenceManager8.saveDeviceAgent(this.h.getString(R.string.cove_client_id) + ';' + modelNo5 + ';' + serialNo7);
                            SessionManager.getInstance(this.h).saveBleDeviceInfo(new Gson().toJson(bleDeviceInfo8));
                            SessionManager sessionManager = SessionManager.getInstance(this.h);
                            Gson gson = new Gson();
                            Object responseData2 = response.getResponseData();
                            Intrinsics.checkNotNull(responseData2, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.DeviceInfoResponse");
                            sessionManager.saveBleDeviceUIInfo(gson.toJson((DeviceInfoResponse) responseData2));
                            ActivityPairing activityPairing8 = this.h;
                            Intrinsics.checkNotNullExpressionValue(bleDeviceInfo8, "bleDeviceInfo");
                            activityPairing8.x(bleDeviceInfo8);
                        }
                    }
                } else {
                    String tag = this.h.getTAG();
                    LogHelper.d(tag, "deviceInfoData: " + deviceInfo);
                    z = this.h.s;
                    if (!z && deviceInfo != null && deviceInfo.getSerialNo() != null && deviceInfo.getManufacturerName() != null && deviceInfo.getModelNo() != null && deviceInfo.getFwVersion() != null && deviceInfo.getHwVersion() != null) {
                        this.h.s = true;
                        final BleDeviceInfo bleDeviceInfo9 = BleDeviceInfo.getInstance();
                        bleDeviceInfo9.setMacAddress(this.i);
                        String serialNo8 = deviceInfo.getSerialNo();
                        if (serialNo8 == null) {
                            String str9 = this.i;
                            serialNo8 = str9 != null ? new Regex(":").replace(str9, "") : null;
                        }
                        String modelNo6 = deviceInfo.getModelNo();
                        if (SessionManager.getInstance(this.h).getDeviceType().equals(this.h.getString(R.string.cove_cz1))) {
                            modelNo6 = "WA7V2";
                        }
                        if (SessionManager.getInstance(this.h).getDeviceType().equals(this.h.getString(R.string.cove_cz3))) {
                            modelNo6 = "WA7V3";
                        }
                        if (SessionManager.getInstance(this.h).getDeviceType().equals(this.h.getString(R.string.cove_wave_prime))) {
                            modelNo6 = "WA7V1";
                        }
                        if (SessionManager.getInstance(this.h).getDeviceType().equals(this.h.getString(R.string.cove_wave_elite))) {
                            modelNo6 = "WA7V4";
                        }
                        if (SessionManager.getInstance(this.h).getDeviceType().equals(this.h.getString(R.string.cove_ca0))) {
                            modelNo6 = "WA12V1";
                        }
                        if (SessionManager.getInstance(this.h).getDeviceType().equals(this.h.getString(R.string.cove_ca3))) {
                            modelNo6 = "WA13V1";
                        }
                        if (SessionManager.getInstance(this.h).getDeviceType().equals(this.h.getString(R.string.cove_ca2))) {
                            modelNo6 = "WA19V1";
                        }
                        if (SessionManager.getInstance(this.h).getDeviceType().equals(this.h.getString(R.string.cove_ca3_bt))) {
                            modelNo6 = "WA13V2";
                        }
                        if (SessionManager.getInstance(this.h).getDeviceType().equals(this.h.getString(R.string.cove_ca3_bt_stormpro_call))) {
                            modelNo6 = ModelNameList.MODEL_NUMBER_WA13V5.getValue();
                        }
                        if (SessionManager.getInstance(this.h).getDeviceType().equals(this.h.getString(R.string.cove_ca3_bt_wave_cosmsos_pro))) {
                            modelNo6 = ModelNameList.MODEL_NUMBER_WA13V4.getValue();
                        }
                        if (SessionManager.getInstance(this.h).getDeviceType().equals(this.h.getString(R.string.cove_ca3_wave_cosmos))) {
                            modelNo6 = ModelNameList.MODEL_NUMBER_WA13V3.getValue();
                        }
                        if (SessionManager.getInstance(this.h).getDeviceType().equals(this.h.getString(R.string.cove_ca5_wave_style))) {
                            modelNo6 = ModelNameList.MODEL_NUMBER_WA18V1.getValue();
                        }
                        if (SessionManager.getInstance(this.h).getDeviceType().equals(this.h.getString(R.string.cove_ca5_wave_beat))) {
                            modelNo6 = ModelNameList.MODEL_NUMBER_WA18V2.getValue();
                        }
                        if (SessionManager.getInstance(this.h).getDeviceType().equals(this.h.getString(R.string.cove_ca5_wave_play))) {
                            modelNo6 = ModelNameList.MODEL_NUMBER_WA18V3.getValue();
                        }
                        if (SessionManager.getInstance(this.h).getDeviceType().equals(this.h.getString(R.string.cove_ulc3_wave_smart))) {
                            modelNo6 = ModelNameList.MODEL_NUMBER_WA18V4.getValue();
                        }
                        if (SessionManager.getInstance(this.h).getDeviceType().equals(this.h.getString(R.string.cove_ulc2_wave_beat_plus))) {
                            modelNo6 = ModelNameList.MODEL_NUMBER_WA18V5.getValue();
                        }
                        if (SessionManager.getInstance(this.h).getDeviceType().equals(this.h.getString(R.string.cove_ulc2_wave_style_plus))) {
                            modelNo6 = ModelNameList.MODEL_NUMBER_WA18V6.getValue();
                        }
                        if (SessionManager.getInstance(this.h).getDeviceType().equals(this.h.getString(R.string.cove_ulc2_wave_smart_plus))) {
                            modelNo6 = ModelNameList.MODEL_NUMBER_WA18V7.getValue();
                        }
                        if (SessionManager.getInstance(this.h).getDeviceType().equals(this.h.getString(R.string.cove_ulc2_wave_lync))) {
                            modelNo6 = ModelNameList.MODEL_NUMBER_WA18V8.getValue();
                        }
                        if (SessionManager.getInstance(this.h).getDeviceType().equals(this.h.getString(R.string.ultima_call))) {
                            modelNo6 = ModelNameList.MODEL_NUMBER_WA18V9.getValue();
                        }
                        if (SessionManager.getInstance(this.h).getDeviceType().equals(this.h.getString(R.string.ultima_connect))) {
                            modelNo6 = ModelNameList.MODEL_NUMBER_WA18V10.getValue();
                        } else if (SessionManager.getInstance(this.h).getDeviceType().equals(this.h.getString(R.string.wave_neo_plus))) {
                            modelNo6 = ModelNameList.MODEL_NUMBER_WA36V1.getValue();
                        } else if (SessionManager.getInstance(this.h).getDeviceType().equals(this.h.getString(R.string.wave_active))) {
                            modelNo6 = ModelNameList.MODEL_NUMBER_WA36V2.getValue();
                        } else if (SessionManager.getInstance(this.h).getDeviceType().equals(this.h.getString(R.string.xtend_pro_2))) {
                            modelNo6 = ModelNameList.MODEL_NUMBER_WA32V1.getValue();
                        } else if (SessionManager.getInstance(this.h).getDeviceType().equals(this.h.getString(R.string.storm_pro_call_2))) {
                            modelNo6 = ModelNameList.MODEL_NUMBER_WA32V2.getValue();
                        } else if (SessionManager.getInstance(this.h).getDeviceType().equals(this.h.getString(R.string.lunar_call_pro_2))) {
                            modelNo6 = ModelNameList.MODEL_NUMBER_WA20V4.getValue();
                        } else if (SessionManager.getInstance(this.h).getDeviceType().equals(this.h.getString(R.string.lunar_connect_pro_2))) {
                            modelNo6 = ModelNameList.MODEL_NUMBER_WA20V5.getValue();
                        } else if (SessionManager.getInstance(this.h).getDeviceType().equals(this.h.getString(R.string.wave_primia_talk_2))) {
                            modelNo6 = ModelNameList.MODEL_NUMBER_WA20V6.getValue();
                        } else if (SessionManager.getInstance(this.h).getDeviceType().equals(this.h.getString(R.string.ultima_call_pro))) {
                            modelNo6 = ModelNameList.MODEL_NUMBER_WA21V1.getValue();
                        } else if (SessionManager.getInstance(this.h).getDeviceType().equals(this.h.getString(R.string.ultima_connect_pro))) {
                            modelNo6 = ModelNameList.MODEL_NUMBER_WA21V2.getValue();
                        } else if (SessionManager.getInstance(this.h).getDeviceType().equals(this.h.getString(R.string.wave_cosmos_talk))) {
                            modelNo6 = ModelNameList.MODEL_NUMBER_WA21V3.getValue();
                        }
                        bleDeviceInfo9.setmModelNumber(modelNo6);
                        bleDeviceInfo9.setSerialNumber(serialNo8);
                        bleDeviceInfo9.setmDeviceName(this.j);
                        bleDeviceInfo9.setFirmwareRevision(deviceInfo.getFwVersion());
                        PreferenceManagerAbstract.getInstance(this.h).saveDeviceVersionNumber(deviceInfo.getFwVersion());
                        bleDeviceInfo9.setmManufacturerName(deviceInfo.getManufacturerName());
                        bleDeviceInfo9.setHwRevision(deviceInfo.getHwVersion());
                        PreferenceManager preferenceManager9 = PreferenceManager.getInstance();
                        preferenceManager9.saveDeviceAgent(this.h.getString(R.string.cove_client_id) + ';' + modelNo6 + ';' + serialNo8);
                        SessionManager.getInstance(this.h).saveBleDeviceInfo(new Gson().toJson(bleDeviceInfo9));
                        this.h.J();
                        if (!companion.isCADevice(this.h) && !companion.isCYDevice(this.h) && !companion.isPS1Device(this.h) && !companion.isBESDevice(this.h)) {
                            ActivityPairing activityPairing9 = this.h;
                            Intrinsics.checkNotNullExpressionValue(bleDeviceInfo9, "bleDeviceInfo");
                            activityPairing9.x(bleDeviceInfo9);
                        } else {
                            BleApiManager.getInstance(this.h).getBleApi().clearCommandQueue();
                            BleApi bleApi2 = BleApiManager.getInstance(this.h).getBleApi();
                            GetFirmwareCapabilityRequest getFirmwareCapabilityRequest = new GetFirmwareCapabilityRequest();
                            final ActivityPairing activityPairing10 = this.h;
                            bleApi2.getData(getFirmwareCapabilityRequest, new DataResultListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.activities.ActivityPairing$getDeviceInfo$1$onDataResponse$2
                                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                                public void onDataError(@NotNull BleBaseError error) {
                                    Intrinsics.checkNotNullParameter(error, "error");
                                    LogHelper.d(ActivityPairing.this.getTAG(), error.getErrorMsg());
                                    SessionManager.getInstance(ActivityPairing.this).saveFirmwareCapability(BleApiManager.getInstance(ActivityPairing.this).getBleApi().getMacAddress(), new FirmwareCapabilityData());
                                    PreferenceManagerAbstract.getInstance(ActivityPairing.this).saveFirmwareCapability(BleApiManager.getInstance(ActivityPairing.this).getBleApi().getMacAddress(), new com.coveiot.android.bleabstract.models.FirmwareCapabilityData());
                                    ActivityPairing activityPairing11 = ActivityPairing.this;
                                    BleDeviceInfo bleDeviceInfo10 = bleDeviceInfo9;
                                    Intrinsics.checkNotNullExpressionValue(bleDeviceInfo10, "bleDeviceInfo");
                                    activityPairing11.x(bleDeviceInfo10);
                                }

                                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                                public void onDataResponse(@NotNull BleBaseResponse response2) {
                                    Intrinsics.checkNotNullParameter(response2, "response");
                                    if (response2.getResponseData() != null && (response2.getResponseData() instanceof GetFirmwareCapabilityResponse)) {
                                        Object responseData3 = response2.getResponseData();
                                        Intrinsics.checkNotNull(responseData3, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.GetFirmwareCapabilityResponse");
                                        GetFirmwareCapabilityResponse getFirmwareCapabilityResponse = (GetFirmwareCapabilityResponse) responseData3;
                                        FirmwareCapabilityData firmwareCapabilityData = new FirmwareCapabilityData();
                                        firmwareCapabilityData.setCapabilities(getFirmwareCapabilityResponse.getCapabilities());
                                        SessionManager.getInstance(ActivityPairing.this).saveFirmwareCapability(BleApiManager.getInstance(ActivityPairing.this).getBleApi().getMacAddress(), firmwareCapabilityData);
                                        com.coveiot.android.bleabstract.models.FirmwareCapabilityData firmwareCapabilityData2 = new com.coveiot.android.bleabstract.models.FirmwareCapabilityData();
                                        firmwareCapabilityData2.setCapabilities(getFirmwareCapabilityResponse.getCapabilities());
                                        PreferenceManagerAbstract.getInstance(ActivityPairing.this).saveFirmwareCapability(BleApiManager.getInstance(ActivityPairing.this).getBleApi().getMacAddress(), firmwareCapabilityData2);
                                    } else {
                                        SessionManager.getInstance(ActivityPairing.this).saveFirmwareCapability(BleApiManager.getInstance(ActivityPairing.this).getBleApi().getMacAddress(), new FirmwareCapabilityData());
                                        PreferenceManagerAbstract.getInstance(ActivityPairing.this).saveFirmwareCapability(BleApiManager.getInstance(ActivityPairing.this).getBleApi().getMacAddress(), new com.coveiot.android.bleabstract.models.FirmwareCapabilityData());
                                    }
                                    ActivityPairing activityPairing11 = ActivityPairing.this;
                                    BleDeviceInfo bleDeviceInfo10 = bleDeviceInfo9;
                                    Intrinsics.checkNotNullExpressionValue(bleDeviceInfo10, "bleDeviceInfo");
                                    activityPairing11.x(bleDeviceInfo10);
                                }

                                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                                public void onProgressUpdate(@NotNull ProgressData progress) {
                                    Intrinsics.checkNotNullParameter(progress, "progress");
                                }
                            });
                        }
                    }
                }
            }
            if (SessionManager.getInstance(this.h).isPairDeviceAfterLogin()) {
                this.h.W();
            }
        }
    }

    @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
    public void onProgressUpdate(@NotNull ProgressData progress) {
        Intrinsics.checkNotNullParameter(progress, "progress");
    }
}
