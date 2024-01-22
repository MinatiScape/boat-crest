package com.coveiot.android.bleabstract.bleimpl;

import android.content.Context;
import com.coveiot.android.bleabstract.R;
import com.coveiot.android.bleabstract.SingletonHolder;
import com.coveiot.android.bleabstract.bleimpl.SmaBaseBleApiImpl;
import com.coveiot.android.bleabstract.listeners.BaseListener;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.BleCommand;
import com.coveiot.android.bleabstract.models.CloveSmaBleState;
import com.coveiot.android.bleabstract.models.ContactData;
import com.coveiot.android.bleabstract.request.ActivityModeSummaryRequest;
import com.coveiot.android.bleabstract.request.ActivityModeWithSamplesRequest;
import com.coveiot.android.bleabstract.request.BleBaseRequest;
import com.coveiot.android.bleabstract.request.SendWeatherRequest;
import com.coveiot.android.bleabstract.request.SessionGPSDataRequest;
import com.coveiot.android.bleabstract.request.SessionHeartRateRequest;
import com.coveiot.android.bleabstract.request.SetMusicMetaDataRequest;
import com.coveiot.android.bleabstract.request.SetMusicPlaybackStateChangedRequest;
import com.coveiot.android.bleabstract.request.SetMusicVolumeRequest;
import com.coveiot.android.bleabstract.request.StressDataRequest;
import com.coveiot.android.bleabstract.request.SyncContactsRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.bleabstract.response.MusicControlState;
import com.coveiot.android.bleabstract.services.SmaBaseBleService;
import com.coveiot.android.bleabstract.utils.smaUtils.SmaUtils;
import com.coveiot.android.smasdk.api.SmaBaseReq;
import com.coveiot.android.smasdk.api.SmaCurrentWeatherReq2;
import com.coveiot.android.smasdk.api.SmaManualBPDataReq;
import com.coveiot.android.smasdk.api.SmaSetMusicMetaDataReq;
import com.coveiot.android.smasdk.api.SmaSetPlaybackStateReq;
import com.coveiot.android.smasdk.api.SmaStressDataReq;
import com.coveiot.android.smasdk.api.SmaSyncContactsReq;
import com.coveiot.android.smasdk.api.SmaWorkout2DataReq;
import com.coveiot.android.smasdk.model.Contact;
import com.coveiot.sdk.ble.api.model.SendWeatherModel;
import com.coveiot.sdk.ble.events.ResponseEvent;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.squareup.otto.Subscribe;
import com.szabh.smable3.BleKey;
import com.szabh.smable3.BleKeyFlag;
import com.szabh.smable3.component.BleCache;
import com.szabh.smable3.entity.BleDeviceInfo;
import com.szabh.smable3.entity.BleMusicControl;
import com.szabh.smable3.entity.BleWeather2;
import com.szabh.smable3.entity.BleWeatherForecast2;
import com.szabh.smable3.entity.BleWeatherRealtime2;
import com.szabh.smable3.entity.MusicAttr;
import com.szabh.smable3.entity.MusicEntity;
import com.szabh.smable3.entity.PlaybackState;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public class SmaLunarSeekBleApiImpl extends SmaBaseBleApiImpl {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public String y;

    /* loaded from: classes2.dex */
    public static final class Companion extends SingletonHolder<SmaLunarSeekBleApiImpl, Context> {

        /* renamed from: com.coveiot.android.bleabstract.bleimpl.SmaLunarSeekBleApiImpl$Companion$1  reason: invalid class name */
        /* loaded from: classes2.dex */
        public /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<Context, SmaLunarSeekBleApiImpl> {

            /* renamed from: a  reason: collision with root package name */
            public static final AnonymousClass1 f3241a = new AnonymousClass1();

            public AnonymousClass1() {
                super(1, SmaLunarSeekBleApiImpl.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public SmaLunarSeekBleApiImpl invoke(Context context) {
                Context p0 = context;
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new SmaLunarSeekBleApiImpl(p0);
            }
        }

        public Companion() {
            super(AnonymousClass1.f3241a);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* loaded from: classes2.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[BleKey.values().length];
            try {
                iArr[BleKey.LOCATION.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[BleKey.WORKOUT2.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[BleKey.PRESSURE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[BleKey.USER_PROFILE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[BleKey.STEP_GOAL.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[BleKey.HR_MONITORING.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[BleKey.TEMPERATURE_DETECTING.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr[BleKey.HOUR_SYSTEM.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                iArr[BleKey.ALARM.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                iArr[BleKey.SEDENTARINESS.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                iArr[BleKey.NOTIFICATION.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                iArr[BleKey.GESTURE_WAKE.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                iArr[BleKey.MUSIC_CONTROL.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                iArr[BleKey.WEATHER_REALTIME2.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SmaLunarSeekBleApiImpl(@NotNull Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        String simpleName = SmaLunarSeekBleApiImpl.class.getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "SmaLunarSeekBleApiImpl::class.java.simpleName");
        this.y = simpleName;
        super.registerEvenBus();
        checkAndStartService();
    }

    public static final void a(Ref.ObjectRef bleBaseRequest) {
        Intrinsics.checkNotNullParameter(bleBaseRequest, "$bleBaseRequest");
        SettingsResultListener settingsResultListener = (SettingsResultListener) ((BleBaseRequest) bleBaseRequest.element).getResponseListener();
        Intrinsics.checkNotNull(settingsResultListener);
        settingsResultListener.onSettingsResponse(new BleBaseResponse((BleBaseRequest) bleBaseRequest.element));
    }

    public static final void g(Ref.ObjectRef bleBaseRequest, DataResultListener dataResultListener) {
        Intrinsics.checkNotNullParameter(bleBaseRequest, "$bleBaseRequest");
        T t = bleBaseRequest.element;
        Intrinsics.checkNotNull(t);
        BleBaseResponse bleBaseResponse = new BleBaseResponse((BleBaseRequest) t);
        bleBaseResponse.setResponseData(null);
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(bleBaseResponse);
    }

    public static final void j(DataResultListener dataResultListener, BleBaseResponse sportResponse) {
        Intrinsics.checkNotNullParameter(sportResponse, "$sportResponse");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(sportResponse);
    }

    public static final void k(DataResultListener dataResultListener, BleBaseResponse sportResponse) {
        Intrinsics.checkNotNullParameter(sportResponse, "$sportResponse");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(sportResponse);
    }

    public static final void l(DataResultListener dataResultListener, BleBaseResponse tempResponse) {
        Intrinsics.checkNotNullParameter(tempResponse, "$tempResponse");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(tempResponse);
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.SmaBaseBleApiImpl
    public void addToQueue(@NotNull BleBaseRequest baseRequest) {
        Intrinsics.checkNotNullParameter(baseRequest, "baseRequest");
        synchronized (getQueue()) {
            SmaBaseReq smaBleReq = getSmaBleReq(baseRequest);
            if (smaBleReq != null) {
                if (smaBleReq.isPriority()) {
                    getQueue().addFirst(new SmaBaseBleApiImpl.QueueObject(baseRequest));
                    String tag = getTAG();
                    StringBuilder sb = new StringBuilder();
                    sb.append("addToQueue-> added ");
                    BleCommand bleCommand = baseRequest.getBleCommand();
                    sb.append(bleCommand != null ? bleCommand.name() : null);
                    LogHelper.d(tag, sb.toString());
                } else {
                    getQueue().add(new SmaBaseBleApiImpl.QueueObject(baseRequest));
                    String tag2 = getTAG();
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("addToQueue-> added ");
                    BleCommand bleCommand2 = baseRequest.getBleCommand();
                    sb2.append(bleCommand2 != null ? bleCommand2.name() : null);
                    LogHelper.d(tag2, sb2.toString());
                }
            } else if (!(baseRequest instanceof ActivityModeWithSamplesRequest)) {
                sendCommandNotFoundError(baseRequest);
            }
        }
    }

    public final void checkAndStartService() {
        if (!checkIfServiceIsRunning()) {
            LogHelper.d(getTAG(), "checkAndStartService-> service is not running ++ ");
            startBleService();
            return;
        }
        bindBleService();
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.SmaBaseBleApiImpl, com.coveiot.android.bleabstract.api.BleApi
    public void getData(@NotNull BleBaseRequest request, @NotNull DataResultListener listener) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(listener, "listener");
        if (request instanceof ActivityModeWithSamplesRequest) {
            if (getBleService() != null) {
                SmaBaseBleService bleService = getBleService();
                Intrinsics.checkNotNull(bleService);
                if (bleService.getConnectionState() == CloveSmaBleState.BleState.CONNECTED) {
                    request.setRequId(UUID.randomUUID().toString());
                    request.setResponseListener(listener);
                    addToQueue(request);
                    sendCommandRequest();
                    return;
                }
            }
            String string = getContext().getString(R.string.band_not_connected);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.band_not_connected)");
            listener.onDataError(new BleBaseError(string));
        } else if (request instanceof SessionGPSDataRequest) {
            if (getBleService() != null) {
                SmaBaseBleService bleService2 = getBleService();
                Intrinsics.checkNotNull(bleService2);
                if (bleService2.getConnectionState() == CloveSmaBleState.BleState.CONNECTED) {
                    request.setRequId(UUID.randomUUID().toString());
                    request.setResponseListener(listener);
                    addToQueue(request);
                    sendCommandRequest();
                    return;
                }
            }
            String string2 = getContext().getString(R.string.band_not_connected);
            Intrinsics.checkNotNullExpressionValue(string2, "context.getString(R.string.band_not_connected)");
            listener.onDataError(new BleBaseError(string2));
        } else if (request instanceof ActivityModeSummaryRequest) {
            if (getBleService() != null) {
                SmaBaseBleService bleService3 = getBleService();
                Intrinsics.checkNotNull(bleService3);
                if (bleService3.getConnectionState() == CloveSmaBleState.BleState.CONNECTED) {
                    request.setRequId(UUID.randomUUID().toString());
                    request.setResponseListener(listener);
                    addToQueue(request);
                    sendCommandRequest();
                    return;
                }
            }
            String string3 = getContext().getString(R.string.band_not_connected);
            Intrinsics.checkNotNullExpressionValue(string3, "context.getString(R.string.band_not_connected)");
            listener.onDataError(new BleBaseError(string3));
        } else if (request instanceof StressDataRequest) {
            if (getBleService() != null) {
                SmaBaseBleService bleService4 = getBleService();
                Intrinsics.checkNotNull(bleService4);
                if (bleService4.getConnectionState() == CloveSmaBleState.BleState.CONNECTED) {
                    request.setRequId(UUID.randomUUID().toString());
                    request.setResponseListener(listener);
                    addToQueue(request);
                    sendCommandRequest();
                    return;
                }
            }
            String string4 = getContext().getString(R.string.band_not_connected);
            Intrinsics.checkNotNullExpressionValue(string4, "context.getString(R.string.band_not_connected)");
            listener.onDataError(new BleBaseError(string4));
        } else {
            super.getData(request, listener);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:252:0x032e, code lost:
        if (r1.getMSupportNoDisturbSet() == 1) goto L190;
     */
    @Override // com.coveiot.android.bleabstract.bleimpl.SmaBaseBleApiImpl, com.coveiot.android.bleabstract.api.BleApi
    @org.jetbrains.annotations.NotNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.coveiot.android.bleabstract.models.DeviceSupportedFeatures getDeviceSupportedFeatures() {
        /*
            Method dump skipped, instructions count: 829
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.bleabstract.bleimpl.SmaLunarSeekBleApiImpl.getDeviceSupportedFeatures():com.coveiot.android.bleabstract.models.DeviceSupportedFeatures");
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.coveiot.android.bleabstract.bleimpl.SmaBaseBleApiImpl
    @Nullable
    public SmaBaseReq getSmaBleReq(@NotNull BleBaseRequest request) {
        BleWeather2 bleWeather2;
        BleWeather2 bleWeather22;
        BleWeather2 bleWeather23;
        BleWeather2 bleWeather24;
        BleWeather2 bleWeather25;
        BleWeather2 bleWeather26;
        BleWeather2 bleWeather27;
        String format;
        SmaBaseReq smaBaseReq;
        Intrinsics.checkNotNullParameter(request, "request");
        LogHelper.d(getTAG(), "getSmaBleReq " + request);
        BleWeather2 bleWeather28 = null;
        if (request instanceof ActivityModeWithSamplesRequest) {
            if (isHeartRateDataSyncRequired()) {
                SessionHeartRateRequest sessionHeartRateRequest = new SessionHeartRateRequest();
                BaseListener responseListener = request.getResponseListener();
                Intrinsics.checkNotNull(responseListener, "null cannot be cast to non-null type com.coveiot.android.bleabstract.listeners.DataResultListener");
                getData(sessionHeartRateRequest, (DataResultListener) responseListener);
            }
            SessionGPSDataRequest sessionGPSDataRequest = new SessionGPSDataRequest();
            BaseListener responseListener2 = request.getResponseListener();
            Intrinsics.checkNotNull(responseListener2, "null cannot be cast to non-null type com.coveiot.android.bleabstract.listeners.DataResultListener");
            getData(sessionGPSDataRequest, (DataResultListener) responseListener2);
            ActivityModeSummaryRequest activityModeSummaryRequest = new ActivityModeSummaryRequest();
            BaseListener responseListener3 = request.getResponseListener();
            Intrinsics.checkNotNull(responseListener3, "null cannot be cast to non-null type com.coveiot.android.bleabstract.listeners.DataResultListener");
            getData(activityModeSummaryRequest, (DataResultListener) responseListener3);
            return null;
        } else if (request instanceof SessionGPSDataRequest) {
            SmaManualBPDataReq smaManualBPDataReq = new SmaManualBPDataReq();
            smaManualBPDataReq.setReqId(request.getRequId());
            smaManualBPDataReq.setKey(BleKey.LOCATION);
            smaManualBPDataReq.setKeyFlag(BleKeyFlag.READ);
            return smaManualBPDataReq;
        } else if (request instanceof ActivityModeSummaryRequest) {
            SmaWorkout2DataReq smaWorkout2DataReq = new SmaWorkout2DataReq();
            smaWorkout2DataReq.setReqId(request.getRequId());
            smaWorkout2DataReq.setKey(BleKey.WORKOUT2);
            smaWorkout2DataReq.setKeyFlag(BleKeyFlag.READ);
            return smaWorkout2DataReq;
        } else {
            if (request instanceof SyncContactsRequest) {
                SyncContactsRequest syncContactsRequest = (SyncContactsRequest) request;
                if (AppUtils.isEmpty(syncContactsRequest.getContactDataArrayList())) {
                    SmaBaseReq smaSyncContactsReq = new SmaSyncContactsReq(null);
                    smaSyncContactsReq.setKey(BleKey.CONTACT);
                    smaSyncContactsReq.setKeyFlag(BleKeyFlag.DELETE);
                    smaSyncContactsReq.setReqId(request.getRequId());
                    smaBaseReq = smaSyncContactsReq;
                } else {
                    ArrayList<ContactData> contactDataArrayList = syncContactsRequest.getContactDataArrayList();
                    Intrinsics.checkNotNullExpressionValue(contactDataArrayList, "syncContactRequest.contactDataArrayList");
                    ArrayList arrayList = new ArrayList();
                    Iterator<ContactData> it = contactDataArrayList.iterator();
                    while (it.hasNext()) {
                        ContactData next = it.next();
                        arrayList.add(new Contact(next.getName(), next.getMobileNumber()));
                    }
                    SmaSyncContactsReq smaSyncContactsReq2 = new SmaSyncContactsReq(arrayList);
                    smaSyncContactsReq2.setKey(BleKey.CONTACT);
                    smaSyncContactsReq2.setKeyFlag(BleKeyFlag.UPDATE);
                    smaSyncContactsReq2.setReqId(request.getRequId());
                    return smaSyncContactsReq2;
                }
            } else if (request instanceof StressDataRequest) {
                SmaStressDataReq smaStressDataReq = new SmaStressDataReq();
                smaStressDataReq.setKey(BleKey.PRESSURE);
                smaStressDataReq.setKeyFlag(BleKeyFlag.READ);
                smaStressDataReq.setReqId(request.getRequId());
                return smaStressDataReq;
            } else if (request instanceof SetMusicMetaDataRequest) {
                SmaSetMusicMetaDataReq smaSetMusicMetaDataReq = new SmaSetMusicMetaDataReq();
                ArrayList arrayList2 = new ArrayList();
                SetMusicMetaDataRequest setMusicMetaDataRequest = (SetMusicMetaDataRequest) request;
                String str = setMusicMetaDataRequest.title;
                if (str != null) {
                    MusicEntity musicEntity = MusicEntity.TRACK;
                    MusicAttr musicAttr = MusicAttr.TRACK_TITLE;
                    Intrinsics.checkNotNullExpressionValue(str, "request.title");
                    arrayList2.add(new BleMusicControl(musicEntity, musicAttr, str));
                }
                String str2 = setMusicMetaDataRequest.album;
                if (str2 != null) {
                    MusicEntity musicEntity2 = MusicEntity.TRACK;
                    MusicAttr musicAttr2 = MusicAttr.TRACK_ALBUM;
                    Intrinsics.checkNotNullExpressionValue(str2, "request.album");
                    arrayList2.add(new BleMusicControl(musicEntity2, musicAttr2, str2));
                }
                String str3 = setMusicMetaDataRequest.artist;
                if (str3 != null) {
                    MusicEntity musicEntity3 = MusicEntity.TRACK;
                    MusicAttr musicAttr3 = MusicAttr.TRACK_ARTIST;
                    Intrinsics.checkNotNullExpressionValue(str3, "request.artist");
                    arrayList2.add(new BleMusicControl(musicEntity3, musicAttr3, str3));
                }
                smaSetMusicMetaDataReq.setMusicControls(arrayList2);
                smaSetMusicMetaDataReq.setKey(BleKey.MUSIC_CONTROL);
                smaSetMusicMetaDataReq.setKeyFlag(BleKeyFlag.UPDATE);
                smaSetMusicMetaDataReq.setReqId(request.getRequId());
                return smaSetMusicMetaDataReq;
            } else if (request instanceof SetMusicPlaybackStateChangedRequest) {
                SmaSetPlaybackStateReq smaSetPlaybackStateReq = new SmaSetPlaybackStateReq();
                SetMusicPlaybackStateChangedRequest setMusicPlaybackStateChangedRequest = (SetMusicPlaybackStateChangedRequest) request;
                if (setMusicPlaybackStateChangedRequest.getMusicControlState() != null && setMusicPlaybackStateChangedRequest.getMusicControlState() == MusicControlState.PLAY) {
                    bleWeather28 = new BleMusicControl(MusicEntity.PLAYER, MusicAttr.PLAYER_PLAYBACK_INFO, String.valueOf((int) PlaybackState.PLAYING.getMState()));
                }
                if (setMusicPlaybackStateChangedRequest.getMusicControlState() != null && setMusicPlaybackStateChangedRequest.getMusicControlState() == MusicControlState.PAUSE) {
                    bleWeather28 = new BleMusicControl(MusicEntity.PLAYER, MusicAttr.PLAYER_PLAYBACK_INFO, String.valueOf((int) PlaybackState.PAUSED.getMState()));
                }
                smaSetPlaybackStateReq.setMusicControl(bleWeather28);
                smaSetPlaybackStateReq.setKey(BleKey.MUSIC_CONTROL);
                smaSetPlaybackStateReq.setKeyFlag(BleKeyFlag.UPDATE);
                smaSetPlaybackStateReq.setReqId(request.getRequId());
                smaBaseReq = smaSetPlaybackStateReq;
            } else if (request instanceof SetMusicVolumeRequest) {
                SmaSetMusicMetaDataReq smaSetMusicMetaDataReq2 = new SmaSetMusicMetaDataReq();
                ArrayList arrayList3 = new ArrayList();
                SetMusicVolumeRequest setMusicVolumeRequest = (SetMusicVolumeRequest) request;
                float volume = setMusicVolumeRequest.getVolume() / setMusicVolumeRequest.getMaxVolume();
                if ((Float.isInfinite(volume) || Float.isNaN(volume)) ? false : true) {
                    format = new DecimalFormat("#.##", DecimalFormatSymbols.getInstance(Locale.ENGLISH)).format(new BigDecimal(String.valueOf(volume)));
                    Intrinsics.checkNotNullExpressionValue(format, "{\n            DecimalFor…toBigDecimal())\n        }");
                } else {
                    format = "0.00";
                }
                arrayList3.add(new BleMusicControl(MusicEntity.PLAYER, MusicAttr.PLAYER_VOLUME, format));
                smaSetMusicMetaDataReq2.setMusicControls(arrayList3);
                smaSetMusicMetaDataReq2.setKey(BleKey.MUSIC_CONTROL);
                smaSetMusicMetaDataReq2.setKeyFlag(BleKeyFlag.UPDATE);
                smaSetMusicMetaDataReq2.setReqId(request.getRequId());
                return smaSetMusicMetaDataReq2;
            } else {
                if (request instanceof SendWeatherRequest) {
                    BleCache bleCache = BleCache.INSTANCE;
                    if (bleCache.getMDeviceInfo() != null) {
                        BleDeviceInfo mDeviceInfo = bleCache.getMDeviceInfo();
                        Intrinsics.checkNotNull(mDeviceInfo);
                        mDeviceInfo.getMSupportWeather2();
                        BleDeviceInfo mDeviceInfo2 = bleCache.getMDeviceInfo();
                        Intrinsics.checkNotNull(mDeviceInfo2);
                        if (mDeviceInfo2.getMSupportWeather2() == 1) {
                            int currentTimeMillis = (int) (System.currentTimeMillis() / 1000);
                            SendWeatherRequest sendWeatherRequest = (SendWeatherRequest) request;
                            List<SendWeatherModel> listSendWeatherModel = sendWeatherRequest.getListSendWeatherModel();
                            if (listSendWeatherModel == null || listSendWeatherModel.isEmpty()) {
                                bleWeather2 = 0;
                                bleWeather22 = null;
                                bleWeather23 = null;
                                bleWeather24 = null;
                                bleWeather25 = null;
                                bleWeather26 = null;
                                bleWeather27 = null;
                            } else {
                                List<SendWeatherModel> listSendWeatherModel2 = sendWeatherRequest.getListSendWeatherModel();
                                Intrinsics.checkNotNull(listSendWeatherModel2);
                                BleWeather2 bleWeather29 = null;
                                BleWeather2 bleWeather210 = null;
                                BleWeather2 bleWeather211 = null;
                                BleWeather2 bleWeather212 = null;
                                BleWeather2 bleWeather213 = null;
                                BleWeather2 bleWeather214 = null;
                                int i = 0;
                                for (SendWeatherModel sendWeatherModel : listSendWeatherModel2) {
                                    int i2 = i + 1;
                                    BleWeather2 bleWeather215 = new BleWeather2(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 32767, null);
                                    Intrinsics.checkNotNull(sendWeatherModel);
                                    bleWeather215.setMCurrentTemperature(sendWeatherModel.getTemp());
                                    bleWeather215.setMHumidity(sendWeatherModel.getHumidity());
                                    bleWeather215.setMMaxTemperature(sendWeatherModel.getTempMax());
                                    bleWeather215.setMMinTemperature(sendWeatherModel.getTempMin());
                                    SmaUtils smaUtils = SmaUtils.INSTANCE;
                                    String weatherText = sendWeatherModel.getWeatherText();
                                    Intrinsics.checkNotNullExpressionValue(weatherText, "value!!.weatherText");
                                    bleWeather215.setMWeatherCode(smaUtils.getWeatherType(weatherText));
                                    bleWeather215.setMWindSpeed((int) ((sendWeatherModel.getWindSpeed() / 100) * 3.6d));
                                    bleWeather215.setMSunriseHour(sendWeatherModel.getSunRise() / 60);
                                    bleWeather215.setMSunrisMinute(sendWeatherModel.getSunRise() % 60);
                                    bleWeather215.setMSunsetHour(sendWeatherModel.getSunSet() / 60);
                                    bleWeather215.setMSunsetMinute(sendWeatherModel.getSunSet() % 60);
                                    bleWeather215.setMVisibility(sendWeatherModel.getVisibility() / 1000);
                                    switch (i) {
                                        case 0:
                                            bleWeather28 = bleWeather215;
                                            continue;
                                            i = i2;
                                        case 1:
                                            bleWeather29 = bleWeather215;
                                            continue;
                                            i = i2;
                                        case 2:
                                            bleWeather210 = bleWeather215;
                                            continue;
                                            i = i2;
                                        case 3:
                                            bleWeather211 = bleWeather215;
                                            continue;
                                            i = i2;
                                        case 4:
                                            bleWeather212 = bleWeather215;
                                            continue;
                                            i = i2;
                                        case 5:
                                            bleWeather213 = bleWeather215;
                                            continue;
                                            i = i2;
                                        case 6:
                                            bleWeather214 = bleWeather215;
                                            break;
                                    }
                                    i = i2;
                                }
                                bleWeather25 = bleWeather212;
                                bleWeather26 = bleWeather213;
                                bleWeather27 = bleWeather214;
                                bleWeather22 = bleWeather29;
                                bleWeather23 = bleWeather210;
                                bleWeather24 = bleWeather211;
                                bleWeather2 = bleWeather28;
                            }
                            String placeName = sendWeatherRequest.getPlaceName();
                            Intrinsics.checkNotNull(placeName);
                            BleWeatherRealtime2 bleWeatherRealtime2 = new BleWeatherRealtime2(currentTimeMillis, placeName, bleWeather2);
                            String placeName2 = sendWeatherRequest.getPlaceName();
                            Intrinsics.checkNotNull(placeName2);
                            SmaCurrentWeatherReq2 smaCurrentWeatherReq2 = new SmaCurrentWeatherReq2(sendWeatherRequest.getTempUnitType() == SendWeatherRequest.TemperatureUnitType.CENTIGRADE ? 0 : 1, bleWeatherRealtime2, new BleWeatherForecast2(currentTimeMillis, placeName2, bleWeather2, bleWeather22, bleWeather23, bleWeather24, bleWeather25, bleWeather26, bleWeather27));
                            smaCurrentWeatherReq2.setKey(BleKey.WEATHER_REALTIME2);
                            smaCurrentWeatherReq2.setReqId(request.getRequId());
                            smaCurrentWeatherReq2.setKeyFlag(BleKeyFlag.UPDATE);
                            return smaCurrentWeatherReq2;
                        }
                    }
                }
                return super.getSmaBleReq(request);
            }
            return smaBaseReq;
        }
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.SmaBaseBleApiImpl
    @NotNull
    public String getTAG() {
        return this.y;
    }

    @Subscribe
    public void onConnectionStateChanged(@Nullable CloveSmaBleState cloveSmaBleState) {
        super.onConnectionStateChangedHandler(cloveSmaBleState);
    }

    /* JADX WARN: Removed duplicated region for block: B:49:0x0174 A[Catch: Exception -> 0x023d, TryCatch #0 {Exception -> 0x023d, blocks: (B:3:0x0005, B:5:0x0043, B:7:0x004d, B:9:0x0063, B:11:0x0071, B:17:0x0097, B:18:0x009c, B:20:0x00a2, B:21:0x00a5, B:23:0x00ab, B:25:0x00b3, B:26:0x00c2, B:28:0x00c8, B:30:0x00d0, B:32:0x00f6, B:33:0x012d, B:34:0x013e, B:36:0x0144, B:37:0x0147, B:39:0x0158, B:43:0x0168, B:49:0x0174, B:51:0x0193, B:53:0x019d, B:57:0x01ad, B:58:0x01ce, B:59:0x01d2, B:60:0x01d6, B:62:0x01e2, B:63:0x01f2, B:64:0x01f5, B:65:0x01f9, B:67:0x01ff, B:68:0x0202, B:69:0x0239), top: B:74:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0193 A[Catch: Exception -> 0x023d, TryCatch #0 {Exception -> 0x023d, blocks: (B:3:0x0005, B:5:0x0043, B:7:0x004d, B:9:0x0063, B:11:0x0071, B:17:0x0097, B:18:0x009c, B:20:0x00a2, B:21:0x00a5, B:23:0x00ab, B:25:0x00b3, B:26:0x00c2, B:28:0x00c8, B:30:0x00d0, B:32:0x00f6, B:33:0x012d, B:34:0x013e, B:36:0x0144, B:37:0x0147, B:39:0x0158, B:43:0x0168, B:49:0x0174, B:51:0x0193, B:53:0x019d, B:57:0x01ad, B:58:0x01ce, B:59:0x01d2, B:60:0x01d6, B:62:0x01e2, B:63:0x01f2, B:64:0x01f5, B:65:0x01f9, B:67:0x01ff, B:68:0x0202, B:69:0x0239), top: B:74:0x0005 }] */
    /* JADX WARN: Type inference failed for: r1v3, types: [T, com.coveiot.android.bleabstract.request.BleBaseRequest] */
    @Override // com.coveiot.android.bleabstract.bleimpl.SmaBaseBleApiImpl, com.coveiot.android.smasdk.SmaResponseListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onResponse(@org.jetbrains.annotations.NotNull com.coveiot.android.smasdk.api.SmaBaseRes r12) {
        /*
            Method dump skipped, instructions count: 604
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.bleabstract.bleimpl.SmaLunarSeekBleApiImpl.onResponse(com.coveiot.android.smasdk.api.SmaBaseRes):void");
    }

    @Subscribe
    public void onResponseEventReceived(@NotNull ResponseEvent responseEvent) {
        Intrinsics.checkNotNullParameter(responseEvent, "responseEvent");
        super.processResponseEventReceived(responseEvent);
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.SmaBaseBleApiImpl
    public void processNextCommand() {
        if (getQueue() == null || getQueue().size() <= 0) {
            return;
        }
        SmaBaseReq smaBleReq = getSmaBleReq(getQueue().get(0).getBaseRequest());
        if (smaBleReq != null) {
            if (getKhCurrentCommand() != null) {
                BleBaseRequest khCurrentCommand = getKhCurrentCommand();
                Intrinsics.checkNotNull(khCurrentCommand);
                if (!khCurrentCommand.isComplete()) {
                    return;
                }
            }
            sendRequestToBleService(smaBleReq);
            return;
        }
        sendCommandNotFoundError(getQueue().get(0).getBaseRequest());
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.SmaBaseBleApiImpl
    public void sendCommandRequest() {
        if (getQueue() == null || getQueue().size() <= 0 || getKhCurrentCommand() != null) {
            return;
        }
        processNextCommand();
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.SmaBaseBleApiImpl
    public void setTAG(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.y = str;
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.SmaBaseBleApiImpl, com.coveiot.android.bleabstract.api.BleApi
    public void setUserSettings(@NotNull BleBaseRequest request, @NotNull SettingsResultListener listener) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(listener, "listener");
        super.setUserSettings(request, listener);
    }
}
