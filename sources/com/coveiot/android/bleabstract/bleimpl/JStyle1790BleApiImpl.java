package com.coveiot.android.bleabstract.bleimpl;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;
import com.coveiot.android.bleabstract.BleApiUtils;
import com.coveiot.android.bleabstract.R;
import com.coveiot.android.bleabstract.bleimpl.JStyleCommonBleApiImpl;
import com.coveiot.android.bleabstract.listeners.BaseListener;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.Clove1790BleState;
import com.coveiot.android.bleabstract.request.BleBaseRequest;
import com.coveiot.android.bleabstract.request.EcgDataRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.LiveECGDataResponse;
import com.coveiot.android.bleabstract.services.KHV5BleService;
import com.coveiot.android.jstylesdk.error.JstyleError;
import com.coveiot.android.jstylesdk.error.JstyleErrorType;
import com.coveiot.android.jstylesdk.model.JstyleLiveResponse;
import com.coveiot.sdk.ble.utils.ModuleNames;
import com.coveiot.utils.utility.LogHelper;
import com.jstyle.blesdk.Util.ResolveUtil;
import com.jstyle.blesdk1860.constant.DeviceKey;
import com.neurosky.AlgoSdk.NskAlgoSdk;
import com.squareup.otto.Subscribe;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
/* loaded from: classes2.dex */
public class JStyle1790BleApiImpl extends JStyleCommonBleApiImpl {
    public static JStyle1790BleApiImpl u;
    public static final String v = JStyle1790BleApiImpl.class.getSimpleName();
    public int q;
    public int s;
    public ArrayList<Integer> m = new ArrayList<>();
    public ArrayList<Float> n = new ArrayList<>();
    public int o = 1536;
    public int p = 1200;
    public String r = "";
    public int t = 0;

    /* renamed from: com.coveiot.android.bleabstract.bleimpl.JStyle1790BleApiImpl$2  reason: invalid class name */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class AnonymousClass2 {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f3001a;

        static {
            Clove1790BleState.BleState.values();
            int[] iArr = new int[5];
            f3001a = iArr;
            try {
                iArr[0] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f3001a[1] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f3001a[2] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public JStyle1790BleApiImpl() {
        LogHelper.d(v, "JStyle1790BleApiImpl constructor", ModuleNames.BLEABSTRACT.getModuleName());
        super.registerEvenBus();
        c();
    }

    public static JStyle1790BleApiImpl getInstance(Context context) {
        if (u == null) {
            JStyleCommonBleApiImpl.context = context.getApplicationContext();
            u = new JStyle1790BleApiImpl();
        }
        if (!JStyleCommonBleApiImpl.checkIfServiceIsRunning()) {
            LogHelper.d(v, "service is not running ++ ", ModuleNames.BLEABSTRACT.getModuleName());
            JStyleCommonBleApiImpl.startBleService();
        }
        return u;
    }

    public final void b() {
        this.n.clear();
        this.m.clear();
        this.q = 0;
        this.t = 0;
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x005e, code lost:
        r8.r = r5.substring(r6.regionStart() + 13, r6.regionEnd() - 1);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void c() {
        /*
            Method dump skipped, instructions count: 345
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.bleabstract.bleimpl.JStyle1790BleApiImpl.c():void");
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.JStyleCommonBleApiImpl
    public void clearParameters() {
        b();
        super.clearParameters();
        u = null;
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.JStyleCommonBleApiImpl, com.coveiot.android.bleabstract.api.BleApi
    public void getData(BleBaseRequest bleBaseRequest, DataResultListener dataResultListener) {
        KHV5BleService kHV5BleService = JStyleCommonBleApiImpl.bleService;
        if (kHV5BleService == null || kHV5BleService.getConnectionState() != Clove1790BleState.BleState.CONNECTED) {
            dataResultListener.onDataError(new BleBaseError(JStyleCommonBleApiImpl.context.getString(R.string.band_not_connected)));
        } else if (bleBaseRequest == null || dataResultListener == null) {
            if (dataResultListener != null) {
                Context context = JStyleCommonBleApiImpl.context;
                int i = R.string.request_cannot_null;
                BleBaseError bleBaseError = new BleBaseError(context.getString(i));
                bleBaseError.setErrorMsg(JStyleCommonBleApiImpl.context.getString(i));
                dataResultListener.onDataError(bleBaseError);
            }
        } else if (bleBaseRequest instanceof EcgDataRequest) {
            b();
            bleBaseRequest.setRequId(UUID.randomUUID().toString());
            bleBaseRequest.setResponseListener(dataResultListener);
            addToQueue(bleBaseRequest);
            sendCommandRequest();
        } else {
            super.getData(bleBaseRequest, dataResultListener);
        }
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.JStyleCommonBleApiImpl, com.coveiot.android.bleabstract.api.BleApi
    public boolean isBluetoothServiceRunning() {
        return BleApiUtils.isServiceRunning(KHV5BleService.class.getName(), JStyleCommonBleApiImpl.context);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x001d  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0029  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:21:? A[RETURN, SYNTHETIC] */
    @Override // com.coveiot.android.bleabstract.bleimpl.JStyleCommonBleApiImpl
    @com.squareup.otto.Subscribe
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onConnectionStateChanged(com.coveiot.android.bleabstract.models.Clove1790BleState r3) {
        /*
            r2 = this;
            com.coveiot.android.bleabstract.models.ConnectionStatus r0 = com.coveiot.android.bleabstract.models.ConnectionStatus.DISCONNECTED
            if (r3 == 0) goto L18
            com.coveiot.android.bleabstract.models.Clove1790BleState$BleState r3 = r3.getmState()
            int r3 = r3.ordinal()
            if (r3 == 0) goto L15
            r1 = 1
            if (r3 == r1) goto L12
            goto L18
        L12:
            com.coveiot.android.bleabstract.models.ConnectionStatus r3 = com.coveiot.android.bleabstract.models.ConnectionStatus.CONNECTING
            goto L19
        L15:
            com.coveiot.android.bleabstract.models.ConnectionStatus r3 = com.coveiot.android.bleabstract.models.ConnectionStatus.CONNECTED
            goto L19
        L18:
            r3 = r0
        L19:
            androidx.lifecycle.MutableLiveData<com.coveiot.android.bleabstract.models.ConnectionStatus> r1 = r2.connectionStateLiveData
            if (r1 == 0) goto L25
            r1.setValue(r3)
            androidx.lifecycle.MutableLiveData<com.coveiot.android.bleabstract.models.ConnectionStatus> r1 = r2.connectionStateLiveData
            r1.postValue(r3)
        L25:
            com.coveiot.android.bleabstract.listeners.ConnectionResultListener r1 = r2.h
            if (r1 == 0) goto L2c
            r1.onConnectionResponse(r3)
        L2c:
            if (r3 != r0) goto L3a
            com.coveiot.android.jstylesdk.error.JstyleError r3 = new com.coveiot.android.jstylesdk.error.JstyleError
            com.coveiot.android.jstylesdk.error.JstyleErrorType r0 = com.coveiot.android.jstylesdk.error.JstyleErrorType.DEVICE_NOT_CONNECTED
            java.lang.String r1 = "Device disconnected"
            r3.<init>(r0, r1)
            r2.sendErrorAndClearQueue(r3)
        L3a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.bleabstract.bleimpl.JStyle1790BleApiImpl.onConnectionStateChanged(com.coveiot.android.bleabstract.models.Clove1790BleState):void");
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.JStyleCommonBleApiImpl
    public void onFailure(JstyleError jstyleError) {
        if (jstyleError != null) {
            if (jstyleError.getErrorType() != null && jstyleError.getErrorType() == JstyleErrorType.BAND_INSTRUCTION_NOT_FOLLOWED) {
                b();
                String str = v;
                LogHelper.d(str, "Error " + jstyleError.getMessage(), ModuleNames.BLEABSTRACT.getModuleName());
                if (JStyleCommonBleApiImpl.queue == null || JStyleCommonBleApiImpl.queue.size() <= 0) {
                    return;
                }
                try {
                    Iterator<JStyleCommonBleApiImpl.QueueObject> it = JStyleCommonBleApiImpl.queue.iterator();
                    while (it.hasNext()) {
                        BaseListener responseListener = it.next().f3161a.getResponseListener();
                        if (responseListener instanceof DataResultListener) {
                            ((DataResultListener) responseListener).onDataError(new BleBaseError(jstyleError.getMessage()));
                        } else if (responseListener instanceof SettingsResultListener) {
                            ((SettingsResultListener) responseListener).onSettingsError(new BleBaseError(jstyleError.getMessage()));
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                clearCommandQueue();
                return;
            }
            b();
            super.onFailure(jstyleError);
        }
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.JStyleCommonBleApiImpl
    @Subscribe
    public void onLiveResponseReceived(JstyleLiveResponse jstyleLiveResponse) {
        if (jstyleLiveResponse == null || jstyleLiveResponse.getDataType() == null) {
            return;
        }
        String dataType = jstyleLiveResponse.getDataType();
        dataType.hashCode();
        char c = 65535;
        switch (dataType.hashCode()) {
            case -1200941421:
                if (dataType.equals("ECGDATA")) {
                    c = 0;
                    break;
                }
                break;
            case -13544106:
                if (dataType.equals("ECGQuality")) {
                    c = 1;
                    break;
                }
                break;
            case 343843441:
                if (dataType.equals("PPGDATA")) {
                    c = 2;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                if (jstyleLiveResponse.getObj() != null) {
                    try {
                        byte[] bArr = (byte[]) ((Map) jstyleLiveResponse.getObj()).get(DeviceKey.ECGValue);
                        int i = this.t;
                        if (i == 0 || i % 200 == 0) {
                            NskAlgoSdk.NskAlgoDataStream(7, new short[]{200}, 1);
                        }
                        for (int i2 = 0; i2 < (bArr.length / 2) - 1; i2++) {
                            int i3 = i2 * 2;
                            int value = ResolveUtil.getValue(bArr[i3 + 1], 1) + ResolveUtil.getValue(bArr[i3 + 2], 0);
                            if (value >= 32768) {
                                value -= 65536;
                            }
                            this.t++;
                            NskAlgoSdk.NskAlgoDataStream(6, new short[]{(short) (-value)}, 1);
                        }
                        return;
                    } catch (Exception e) {
                        LogHelper.d(v, "ECGDATA Exception " + e.getMessage(), ModuleNames.BLEABSTRACT.getModuleName());
                        return;
                    }
                }
                return;
            case 1:
                if (jstyleLiveResponse.getObj() != null) {
                    Map map = (Map) jstyleLiveResponse.getObj();
                    String str = (String) map.get("heartValue");
                    String str2 = (String) map.get("hrvValue");
                    int parseInt = Integer.parseInt((String) map.get(DeviceKey.ECGQualityValue));
                    if (parseInt > 0) {
                        LiveECGDataResponse liveECGDataResponse = new LiveECGDataResponse();
                        liveECGDataResponse.setDataType("ECGQuality");
                        liveECGDataResponse.setEcgQuality(parseInt);
                        liveECGDataResponse.setHeartRateValue(str);
                        liveECGDataResponse.setHrvValue(str2);
                        liveECGDataResponse.setDate(Long.valueOf(System.currentTimeMillis()));
                        MutableLiveData<LiveECGDataResponse> mutableLiveData = this.liveECGDataMutableLiveData;
                        if (mutableLiveData != null) {
                            mutableLiveData.postValue(liveECGDataResponse.m103clone());
                            return;
                        }
                        return;
                    }
                    return;
                }
                return;
            case 2:
                if (jstyleLiveResponse.getObj() != null) {
                    try {
                        byte[] bArr2 = (byte[]) ((Map) jstyleLiveResponse.getObj()).get(DeviceKey.PPGValue);
                        for (int i4 = 0; i4 < (bArr2.length / 2) - 1; i4++) {
                            int i5 = i4 * 2;
                            float value2 = ResolveUtil.getValue(bArr2[i5 + 1], 1) + ResolveUtil.getValue(bArr2[i5 + 2], 0);
                            if (value2 >= 32768.0f) {
                                value2 -= 65536.0f;
                            }
                            if (this.n.size() > this.p) {
                                this.n.remove(0);
                            }
                            this.n.add(Float.valueOf(value2));
                        }
                        LiveECGDataResponse liveECGDataResponse2 = new LiveECGDataResponse();
                        liveECGDataResponse2.setDataType("PPGDATA");
                        liveECGDataResponse2.setQueuePpg(this.n);
                        return;
                    } catch (Exception e2) {
                        LogHelper.d(v, "PPGDATA Exception " + e2.getMessage(), ModuleNames.BLEABSTRACT.getModuleName());
                        return;
                    }
                }
                return;
            default:
                super.onLiveResponseReceived(jstyleLiveResponse);
                return;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:41:?, code lost:
        return;
     */
    @Override // com.coveiot.android.bleabstract.bleimpl.JStyleCommonBleApiImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onResponse(@org.jetbrains.annotations.NotNull com.coveiot.android.jstylesdk.api.JstyleBaseRes r8) {
        /*
            r7 = this;
            java.lang.String r0 = "ENTERECG"
            if (r8 == 0) goto Lba
            com.coveiot.android.jstylesdk.api.JstyleBaseReq r1 = r8.getBaseReq()     // Catch: java.lang.Exception -> Lbe
            if (r1 == 0) goto Lba
            com.coveiot.android.jstylesdk.api.JstyleBaseReq r1 = r8.getBaseReq()     // Catch: java.lang.Exception -> Lbe
            java.lang.String r1 = r1.getDataType()     // Catch: java.lang.Exception -> Lbe
            if (r1 == 0) goto Lba
            com.coveiot.android.jstylesdk.api.JstyleBaseReq r1 = r8.getBaseReq()     // Catch: java.lang.Exception -> Lbe
            java.lang.String r1 = r1.getDataType()     // Catch: java.lang.Exception -> Lbe
            boolean r1 = r1.equalsIgnoreCase(r0)     // Catch: java.lang.Exception -> Lbe
            if (r1 == 0) goto Lb6
            com.coveiot.android.jstylesdk.api.JstyleBaseReq r1 = r8.baseReq     // Catch: java.lang.Exception -> Lbe
            com.coveiot.android.bleabstract.request.BleBaseRequest r1 = r7.getFromQueue(r1)     // Catch: java.lang.Exception -> Lbe
            if (r1 == 0) goto Ld0
            com.coveiot.android.bleabstract.listeners.BaseListener r2 = r1.getResponseListener()     // Catch: java.lang.Exception -> Lbe
            if (r2 == 0) goto Ld0
            com.coveiot.android.bleabstract.listeners.BaseListener r2 = r1.getResponseListener()     // Catch: java.lang.Exception -> Lbe
            boolean r2 = r2 instanceof com.coveiot.android.bleabstract.listeners.DataResultListener     // Catch: java.lang.Exception -> Lbe
            if (r2 == 0) goto Ld0
            com.coveiot.android.bleabstract.listeners.BaseListener r2 = r1.getResponseListener()     // Catch: java.lang.Exception -> Lbe
            com.coveiot.android.bleabstract.listeners.DataResultListener r2 = (com.coveiot.android.bleabstract.listeners.DataResultListener) r2     // Catch: java.lang.Exception -> Lbe
            com.coveiot.android.jstylesdk.api.JstyleBaseReq r3 = r8.getBaseReq()     // Catch: java.lang.Exception -> Lbe
            java.lang.String r3 = r3.getDataType()     // Catch: java.lang.Exception -> Lbe
            r4 = -1
            int r5 = r3.hashCode()     // Catch: java.lang.Exception -> Lbe
            r6 = -1323245135(0xffffffffb120e1b1, float:-2.3411355E-9)
            if (r5 == r6) goto L51
            goto L58
        L51:
            boolean r0 = r3.equals(r0)     // Catch: java.lang.Exception -> Lbe
            if (r0 == 0) goto L58
            r4 = 0
        L58:
            if (r4 == 0) goto L5c
            goto Ld0
        L5c:
            android.os.Handler r0 = r7.syncTimeOutHandler     // Catch: java.lang.Exception -> Lbe
            if (r0 == 0) goto L64
            r3 = 0
            r0.removeCallbacksAndMessages(r3)     // Catch: java.lang.Exception -> Lbe
        L64:
            java.lang.Object r8 = r8.obj     // Catch: java.lang.Exception -> Lbe
            java.util.Map r8 = (java.util.Map) r8     // Catch: java.lang.Exception -> Lbe
            if (r8 == 0) goto La5
            java.lang.String r0 = "ECGResultVALUE"
            java.lang.Object r0 = r8.get(r0)     // Catch: java.lang.Exception -> Lbe
            java.lang.String r0 = (java.lang.String) r0     // Catch: java.lang.Exception -> Lbe
            com.coveiot.android.bleabstract.response.BleBaseResponse r0 = new com.coveiot.android.bleabstract.response.BleBaseResponse     // Catch: java.lang.Exception -> Lbe
            r0.<init>(r1)     // Catch: java.lang.Exception -> Lbe
            com.coveiot.android.bleabstract.formatter.JStyleFormatter r1 = new com.coveiot.android.bleabstract.formatter.JStyleFormatter     // Catch: java.lang.Exception -> Lbe
            java.lang.String r3 = r7.getMacAddress()     // Catch: java.lang.Exception -> Lbe
            r1.<init>(r3)     // Catch: java.lang.Exception -> Lbe
            java.util.ArrayList<java.lang.Integer> r3 = r7.m     // Catch: java.lang.Exception -> Lbe
            java.lang.Object r3 = r3.clone()     // Catch: java.lang.Exception -> Lbe
            java.util.ArrayList r3 = (java.util.ArrayList) r3     // Catch: java.lang.Exception -> Lbe
            java.util.ArrayList<java.lang.Float> r4 = r7.n     // Catch: java.lang.Exception -> Lbe
            java.lang.Object r4 = r4.clone()     // Catch: java.lang.Exception -> Lbe
            java.util.ArrayList r4 = (java.util.ArrayList) r4     // Catch: java.lang.Exception -> Lbe
            com.coveiot.android.bleabstract.response.ECGResultResponse r8 = r1.getECGResultResponse(r8, r3, r4)     // Catch: java.lang.Exception -> Lbe
            com.coveiot.android.bleabstract.response.ECGResultResponse r8 = r8.m102clone()     // Catch: java.lang.Exception -> Lbe
            r0.setResponseData(r8)     // Catch: java.lang.Exception -> Lbe
            r2.onDataResponse(r0)     // Catch: java.lang.Exception -> Lbe
            r7.b()     // Catch: java.lang.Exception -> Lbe
            r7.setCompleteAndProcessNext()     // Catch: java.lang.Exception -> Lbe
            goto Ld0
        La5:
            com.coveiot.android.bleabstract.response.BleBaseError r8 = new com.coveiot.android.bleabstract.response.BleBaseError     // Catch: java.lang.Exception -> Lbe
            android.content.Context r0 = com.coveiot.android.bleabstract.bleimpl.JStyleCommonBleApiImpl.context     // Catch: java.lang.Exception -> Lbe
            int r1 = com.coveiot.android.bleabstract.R.string.data_error     // Catch: java.lang.Exception -> Lbe
            java.lang.String r0 = r0.getString(r1)     // Catch: java.lang.Exception -> Lbe
            r8.<init>(r0)     // Catch: java.lang.Exception -> Lbe
            r2.onDataError(r8)     // Catch: java.lang.Exception -> Lbe
            goto Ld0
        Lb6:
            super.onResponse(r8)     // Catch: java.lang.Exception -> Lbe
            goto Ld0
        Lba:
            super.onResponse(r8)     // Catch: java.lang.Exception -> Lbe
            goto Ld0
        Lbe:
            r8 = move-exception
            com.coveiot.android.jstylesdk.error.JstyleError r0 = new com.coveiot.android.jstylesdk.error.JstyleError
            com.coveiot.android.jstylesdk.error.JstyleErrorType r1 = com.coveiot.android.jstylesdk.error.JstyleErrorType.COMMAND_REQUEST_ERROR
            java.lang.String r2 = r8.getMessage()
            r0.<init>(r1, r2)
            r7.onFailure(r0)
            r8.printStackTrace()
        Ld0:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.bleabstract.bleimpl.JStyle1790BleApiImpl.onResponse(com.coveiot.android.jstylesdk.api.JstyleBaseRes):void");
    }
}
