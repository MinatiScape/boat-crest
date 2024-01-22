package com.coveiot.android.leonardo.firebaseservices.service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import com.clevertap.android.sdk.CleverTapAPI;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.pushnotification.CTPushNotificationListener;
import com.clevertap.android.sdk.pushnotification.NotificationInfo;
import com.clevertap.android.sdk.pushnotification.fcm.CTFcmMessageHandler;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.boat.R;
import com.coveiot.android.fitnessbuddies.models.FitnessAceptRejectEvent;
import com.coveiot.android.fitnessbuddies.models.FitnessCheerEvent;
import com.coveiot.android.fitnessbuddies.utils.PreferenceManager;
import com.coveiot.android.healthbuddies.model.GuardianDeletionEvent;
import com.coveiot.android.leonardo.dashboard.ActivityDashboardNew;
import com.coveiot.android.leonardo.firebaseservices.enums.FcmMessageTypes;
import com.coveiot.android.leonardo.firebaseservices.model.AppUpdateCoveFCMessage;
import com.coveiot.android.leonardo.firebaseservices.model.CustomNotifiacationModel;
import com.coveiot.android.leonardo.firebaseservices.model.FCMAlexaAccountLinkingStatus;
import com.coveiot.android.leonardo.firebaseservices.model.FCMBoatCoinsEarned;
import com.coveiot.android.leonardo.firebaseservices.model.FCMBoatCoinsFeatureMessage;
import com.coveiot.android.leonardo.firebaseservices.model.FCMFitnessChallengeFeatureMessage;
import com.coveiot.android.leonardo.firebaseservices.model.FCMFitnessReportSubscribeMessage;
import com.coveiot.android.leonardo.firebaseservices.model.FCMFitnessVideoMessage;
import com.coveiot.android.leonardo.firebaseservices.model.FCMWatchFaceUpdate;
import com.coveiot.android.leonardo.firebaseservices.model.FcmGaurdianDeleteMessage;
import com.coveiot.android.leonardo.firebaseservices.model.FcmGenericMessage;
import com.coveiot.android.leonardo.firebaseservices.model.FcmMessage;
import com.coveiot.android.leonardo.firebaseservices.model.FcmShipbookLoggingControl;
import com.coveiot.android.leonardo.firebaseservices.model.FcmSportsSelectMatchMessage;
import com.coveiot.android.leonardo.firebaseservices.model.FcmSportsSettingsMessage;
import com.coveiot.android.leonardo.firebaseservices.model.FirmwareUpdateCoveFCMessage;
import com.coveiot.android.leonardo.firebaseservices.model.FitnessCMCheerCloverMessage;
import com.coveiot.android.leonardo.firebaseservices.model.FitnessCMMessage;
import com.coveiot.android.leonardo.firebaseservices.model.FitnessPendingRequestModel;
import com.coveiot.android.leonardo.firebaseservices.model.LeaderBoardNotificationModel;
import com.coveiot.android.leonardo.firebaseservices.model.NewBadgeAddedModel;
import com.coveiot.android.leonardo.firebaseservices.model.SurveyModel;
import com.coveiot.android.leonardo.personalization.PersonalizationCommandManager;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.leonardo.utils.PayUtils;
import com.coveiot.android.leonardo.utils.SendMessageToWatchUtil;
import com.coveiot.android.remotecommandframework.alexa.model.AlexaAccountLinkingDisabledEvent;
import com.coveiot.android.remotecommandframework.alexa.model.AlexaAccountLinkingEnabledEvent;
import com.coveiot.android.smartalert.util.SmartAlertUtils;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.leaderboard.views.activities.AllBadgesActivity;
import com.coveiot.leaderboard.views.activities.LeaderBoardNotificationActivity;
import com.coveiot.sdk.ble.helper.LogsHelper;
import com.coveiot.utils.CoveEventBusManager;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;
import io.shipbook.shipbooksdk.ShipBook;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes2.dex */
public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService implements CTPushNotificationListener {
    public static final String k = FirebaseMessagingService.class.getName();
    public Handler i;
    public Handler h = new Handler();
    public String j = AppConstants.CHANNEL_ID.getValue();

    /* loaded from: classes2.dex */
    public static class OnClickListener extends BroadcastReceiver {
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String string = intent.getExtras().getString(AppConstants.SURVEY.getValue());
            ((NotificationManager) context.getSystemService("notification")).cancel(0);
            SurveyModel surveyModel = (SurveyModel) new Gson().fromJson(string, (Class<Object>) SurveyModel.class);
            Intent intent2 = new Intent(context.getResources().getString(R.string.dashboard_activityif));
            intent2.setFlags(268435456);
            context.startActivity(intent2);
        }
    }

    /* loaded from: classes2.dex */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            LogHelper.d(FirebaseMessagingService.k, "Shipbook testing done, rolling back to old shipbook cred");
            ShipBook.start(FirebaseMessagingService.this.getApplication(), AppConstants.SHIPBOOK_APP_ID.getValue(), AppConstants.SHIPBOOK_APP_KEY.getValue());
            LogHelper.initialize(false);
            LogsHelper.init(false);
        }
    }

    /* loaded from: classes2.dex */
    public class b implements Runnable {
        public b(FirebaseMessagingService firebaseMessagingService) {
        }

        @Override // java.lang.Runnable
        public void run() {
            CoveEventBusManager.getInstance().getEventBus().post(new GuardianDeletionEvent());
        }
    }

    /* loaded from: classes2.dex */
    public class c implements Runnable {
        public c(FirebaseMessagingService firebaseMessagingService) {
        }

        @Override // java.lang.Runnable
        public void run() {
            CoveEventBusManager.getInstance().getEventBus().post(new FitnessAceptRejectEvent());
        }
    }

    /* loaded from: classes2.dex */
    public class d implements Runnable {
        public d(FirebaseMessagingService firebaseMessagingService) {
        }

        @Override // java.lang.Runnable
        public void run() {
            CoveEventBusManager.getInstance().getEventBus().post(new FitnessAceptRejectEvent());
        }
    }

    /* loaded from: classes2.dex */
    public class e implements Runnable {
        public e(FirebaseMessagingService firebaseMessagingService) {
        }

        @Override // java.lang.Runnable
        public void run() {
            CoveEventBusManager.getInstance().getEventBus().post(new FitnessAceptRejectEvent());
        }
    }

    /* loaded from: classes2.dex */
    public class f implements Runnable {
        public f(FirebaseMessagingService firebaseMessagingService) {
        }

        @Override // java.lang.Runnable
        public void run() {
            CoveEventBusManager.getInstance().getEventBus().post(new FitnessCheerEvent());
        }
    }

    /* loaded from: classes2.dex */
    public class g implements Runnable {
        public g(FirebaseMessagingService firebaseMessagingService) {
        }

        @Override // java.lang.Runnable
        public void run() {
            CoveEventBusManager.getInstance().getEventBus().post(new AlexaAccountLinkingEnabledEvent());
        }
    }

    /* loaded from: classes2.dex */
    public class h implements Runnable {
        public h(FirebaseMessagingService firebaseMessagingService) {
        }

        @Override // java.lang.Runnable
        public void run() {
            CoveEventBusManager.getInstance().getEventBus().post(new AlexaAccountLinkingDisabledEvent());
        }
    }

    /* loaded from: classes2.dex */
    public static /* synthetic */ class i {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f4829a;

        static {
            int[] iArr = new int[FcmMessageTypes.values().length];
            f4829a = iArr;
            try {
                iArr[FcmMessageTypes.GENERIC_MESSAGE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f4829a[FcmMessageTypes.REMOTE_LOGGING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f4829a[FcmMessageTypes.COINS_EARNED_MESSAGE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f4829a[FcmMessageTypes.REL_GUARDIAN_UNAVAILABLE_MESSAGE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f4829a[FcmMessageTypes.FITNESSBUDDY_ACCEPT_REJECT_REQUEST_MESSAGE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f4829a[FcmMessageTypes.FITNESSBUDDY_NEW_REQUEST_MESSAGE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f4829a[FcmMessageTypes.FITNESSBUDDY_UNFRIEND_MESSAGE.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f4829a[FcmMessageTypes.FITNESSBUDDY_CHEER_MESSAGE.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f4829a[FcmMessageTypes.LEADERBOARD_BADGE_OBTAINED_MESSAGE.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f4829a[FcmMessageTypes.LEADERBOARD_NEW_BADGE_MESSAGE.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f4829a[FcmMessageTypes.FITNESSBUDDY_REQUEST_PENDING_MESSAGE.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f4829a[FcmMessageTypes.CUSTOM_MESSAGE.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                f4829a[FcmMessageTypes.FIRMWARE_UPDATE_MESSAGE.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                f4829a[FcmMessageTypes.APP_UPDATE_MESSAGE.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                f4829a[FcmMessageTypes.MSG_SUBSCRIBE_TO_TOPIC.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                f4829a[FcmMessageTypes.FITNESS_VIDEO_MESSAGE.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                f4829a[FcmMessageTypes.SPORT_SETTINGS_MESSAGE.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                f4829a[FcmMessageTypes.SPORT_SELECT_MATCH_MESSAGE.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                f4829a[FcmMessageTypes.ALEXA_SKILL_STATUS.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                f4829a[FcmMessageTypes.WATCHFACES_UPDATE_ON_WATCH_MESSAGE.ordinal()] = 20;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                f4829a[FcmMessageTypes.WATCHFACES_NEWLIST_UPDATE_MESSAGE.ordinal()] = 21;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                f4829a[FcmMessageTypes.WATCHFACE_STUDIO_UPDATE_MESSAGE.ordinal()] = 22;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                f4829a[FcmMessageTypes.COINS_FEATURE_MESSAGE.ordinal()] = 23;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                f4829a[FcmMessageTypes.FITCHALLENGE_PARTICIPANT_ADDED.ordinal()] = 24;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                f4829a[FcmMessageTypes.FITCHALLENGE_STARTS.ordinal()] = 25;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                f4829a[FcmMessageTypes.FITCHALLENGE_USER_PROGRESS.ordinal()] = 26;
            } catch (NoSuchFieldError unused26) {
            }
            try {
                f4829a[FcmMessageTypes.SMART_ALERT_CONFIG_CHANGE.ordinal()] = 27;
            } catch (NoSuchFieldError unused27) {
            }
        }
    }

    public final void b(Context context, int i2, String str, String str2, String str3) {
        Intent intent = new Intent(context, LeaderBoardNotificationActivity.class);
        intent.putExtra(context.getResources().getString(R.string.data), str3);
        NotificationCompat.Builder contentIntent = new NotificationCompat.Builder(context, this.j).setSmallIcon(i2).setContentTitle(str).setContentText(str2).setAutoCancel(true).setStyle(new NotificationCompat.BigTextStyle().bigText(str2)).setColor(ContextCompat.getColor(context, R.color.colorPrimary)).setSound(RingtoneManager.getDefaultUri(2)).setContentIntent(PendingIntent.getActivity(context, 0, intent, 67108864));
        contentIntent.setSmallIcon(i2);
        contentIntent.setContentTitle(str);
        contentIntent.setColor(ContextCompat.getColor(context, R.color.colorPrimary));
        contentIntent.setContentText(str2);
        contentIntent.setSound(RingtoneManager.getDefaultUri(2));
        contentIntent.setStyle(new NotificationCompat.BigTextStyle().bigText(str2));
        contentIntent.setAutoCancel(true);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
        if (Build.VERSION.SDK_INT >= 26) {
            notificationManager.createNotificationChannel(new NotificationChannel(this.j, getResources().getString(R.string.notification_name), 3));
        }
        notificationManager.notify(0, contentIntent.build());
    }

    public final void c(Context context, int i2, String str, String str2) {
        NotificationCompat.Builder contentIntent = new NotificationCompat.Builder(context, this.j).setSmallIcon(i2).setContentTitle(str).setContentText(str2).setAutoCancel(true).setStyle(new NotificationCompat.BigTextStyle().bigText(str2)).setColor(ContextCompat.getColor(context, 17170445)).setSound(RingtoneManager.getDefaultUri(2)).setContentIntent(PendingIntent.getActivity(context, 0, new Intent(context, AllBadgesActivity.class), 67108864));
        contentIntent.setSmallIcon(i2);
        contentIntent.setContentTitle(str);
        contentIntent.setColor(ContextCompat.getColor(context, 17170445));
        contentIntent.setContentText(str2);
        contentIntent.setSound(RingtoneManager.getDefaultUri(2));
        contentIntent.setStyle(new NotificationCompat.BigTextStyle().bigText(str2));
        contentIntent.setAutoCancel(true);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
        if (Build.VERSION.SDK_INT >= 26) {
            notificationManager.createNotificationChannel(new NotificationChannel(this.j, getResources().getString(R.string.notification_name), 3));
        }
        notificationManager.notify(0, contentIntent.build());
    }

    @Override // com.google.firebase.messaging.FirebaseMessagingService
    public void onMessageReceived(RemoteMessage remoteMessage) {
        NotificationInfo notificationInfo;
        int i2;
        FCMAlexaAccountLinkingStatus fCMAlexaAccountLinkingStatus;
        this.i = new Handler(Looper.getMainLooper());
        if (remoteMessage != null) {
            try {
                if (remoteMessage.getData() != null && remoteMessage.getData().size() > 0) {
                    Bundle bundle = new Bundle();
                    for (Map.Entry<String, String> entry : remoteMessage.getData().entrySet()) {
                        bundle.putString(entry.getKey(), entry.getValue());
                    }
                    if (CleverTapAPI.getNotificationInfo(bundle).fromCleverTap) {
                        new CTFcmMessageHandler().createNotification(getApplicationContext(), remoteMessage);
                        LogHelper.d(k, "info  : " + notificationInfo.toString());
                        if (bundle.getString("NOTIFICATION_TYPE") != null && bundle.getString("NOTIFICATION_TYPE").equalsIgnoreCase("FIRMWARE_UPDATE_MESSAGE")) {
                            CleverTapAPI.getDefaultInstance(getApplicationContext()).setCTPushNotificationListener(this);
                            if (bundle.getString(Constants.NOTIF_MSG) == null || bundle.getString(Constants.NOTIF_TITLE) == null) {
                                return;
                            }
                            if (BleApiManager.getInstance(this).getBleApi() != null && BleApiManager.getInstance(this).getBleApi().getDeviceSupportedFeatures() != null && BleApiManager.getInstance(this).getBleApi().getDeviceSupportedFeatures().isPersonalizedMessageSupported()) {
                                PersonalizationCommandManager.Companion.sendOTAMessageToBle(this, bundle.getString(Constants.NOTIF_MSG), 49010, bundle.getString(Constants.NOTIF_TITLE));
                                return;
                            } else {
                                SendMessageToWatchUtil.sendMessageToBleForOTA(getApplicationContext(), bundle.getString(Constants.NOTIF_MSG), com.coveiot.android.leonardo.performance.Constants.GENERAL_NUDGE_IMAGE_ID, bundle.getString(Constants.NOTIF_TITLE));
                                return;
                            }
                        }
                        CleverTapAPI.getDefaultInstance(getApplicationContext()).setCTPushNotificationListener(null);
                        return;
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        String str = k;
        LogHelper.d(str, "From: " + remoteMessage.getFrom());
        Gson gson = new Gson();
        String json = gson.toJson(remoteMessage.getData());
        String json2 = gson.toJson(remoteMessage.getNotification());
        LogHelper.d(str, json);
        LogHelper.d(str, "NotificationJson" + json2);
        try {
            FcmMessage fcmMessage = (FcmMessage) gson.fromJson(json, (Class<Object>) FcmMessage.class);
            long timeStamp = fcmMessage.getTimeStamp();
            if (fcmMessage.getMessageType() == null) {
                fcmMessage.setMessageType(FcmMessageTypes.BAD_MESSAGE);
            }
            switch (i.f4829a[fcmMessage.getMessageType().ordinal()]) {
                case 1:
                    LogHelper.d(str, "Generic Message received " + fcmMessage.getMessageJsonString());
                    FcmGenericMessage fcmGenericMessage = (FcmGenericMessage) gson.fromJson(fcmMessage.getMessageJsonString(), (Class<Object>) FcmGenericMessage.class);
                    com.coveiot.android.leonardo.firebaseservices.utils.NotificationManager.getInstance().notifyGenericMessage(getApplicationContext(), fcmGenericMessage.getTitle(), fcmGenericMessage.getMessage(), timeStamp);
                    break;
                case 2:
                    LogHelper.d(str, "Generic Message received " + fcmMessage.getMessageJsonString());
                    FcmShipbookLoggingControl fcmShipbookLoggingControl = (FcmShipbookLoggingControl) gson.fromJson(fcmMessage.getMessageJsonString(), (Class<Object>) FcmShipbookLoggingControl.class);
                    if (fcmShipbookLoggingControl.getEnable().booleanValue()) {
                        ShipBook.start(getApplication(), fcmShipbookLoggingControl.getShipbookKeys().getShipbookId(), fcmShipbookLoggingControl.getShipbookKeys().getShipbookKey());
                        ShipBook.registerUser("", null, null, null, AppUtils.convertStringToMD5(SessionManager.getInstance(this).getUserDetails().getMobileNumber()));
                        LogHelper.initialize(true);
                        LogsHelper.init(true);
                        this.h.postDelayed(new a(), fcmShipbookLoggingControl.getExpiryGMTTimeStamp().longValue() - System.currentTimeMillis());
                        break;
                    } else {
                        ShipBook.start(getApplication(), AppConstants.SHIPBOOK_APP_ID.getValue(), AppConstants.SHIPBOOK_APP_KEY.getValue());
                        LogHelper.initialize(false);
                        LogsHelper.init(false);
                        break;
                    }
                case 3:
                    LogHelper.d(str, "Coins Earned  Message received " + fcmMessage.getMessageJsonString());
                    FCMBoatCoinsEarned fCMBoatCoinsEarned = (FCMBoatCoinsEarned) gson.fromJson(fcmMessage.getMessageJsonString(), (Class<Object>) FCMBoatCoinsEarned.class);
                    com.coveiot.android.leonardo.firebaseservices.utils.NotificationManager.getInstance().notifyBoatCoinsEarnedMessage(getApplicationContext(), fCMBoatCoinsEarned.getNotifIdentifier(), fCMBoatCoinsEarned.getTitle(), fCMBoatCoinsEarned.getMessage(), fCMBoatCoinsEarned.getBgImageUrl());
                    break;
                case 4:
                    LogHelper.d(str, "Generic Message received " + fcmMessage.getMessageJsonString());
                    FcmGaurdianDeleteMessage fcmGaurdianDeleteMessage = (FcmGaurdianDeleteMessage) gson.fromJson(fcmMessage.getMessageJsonString(), (Class<Object>) FcmGaurdianDeleteMessage.class);
                    if (fcmGaurdianDeleteMessage != null) {
                        if (fcmGaurdianDeleteMessage.getRelType().equals(getResources().getString(R.string.familydoc_dependent))) {
                            i2 = 1;
                        } else {
                            i2 = 0;
                        }
                        com.coveiot.android.leonardo.firebaseservices.utils.NotificationManager.getInstance().notifyGuardianDeletionMessage(i2, getApplicationContext(), fcmGaurdianDeleteMessage.getTitle(), fcmGaurdianDeleteMessage.getMessage(), timeStamp);
                    }
                    this.i.post(new b(this));
                    break;
                case 5:
                    FitnessCMMessage fitnessCMMessage = (FitnessCMMessage) gson.fromJson(fcmMessage.getMessageJsonString(), (Class<Object>) FitnessCMMessage.class);
                    PreferenceManager.Companion companion = PreferenceManager.Companion;
                    companion.saveFitnessNotificationCount(this, companion.getFitnessNotificationCount(this) + 1);
                    com.coveiot.android.leonardo.firebaseservices.utils.NotificationManager.getInstance().notifyFitnessCloversAcceptReject(getApplicationContext(), fitnessCMMessage.getBuddyUserName(), fitnessCMMessage.getMessage());
                    this.i.post(new c(this));
                    break;
                case 6:
                    FitnessCMMessage fitnessCMMessage2 = (FitnessCMMessage) gson.fromJson(fcmMessage.getMessageJsonString(), (Class<Object>) FitnessCMMessage.class);
                    PreferenceManager.Companion companion2 = PreferenceManager.Companion;
                    companion2.saveFitnessNotificationCount(this, companion2.getFitnessNotificationCount(this) + 1);
                    com.coveiot.android.leonardo.firebaseservices.utils.NotificationManager.getInstance().notifyFitnessCloversAcceptReject(getApplicationContext(), fitnessCMMessage2.getBuddyUserName(), fitnessCMMessage2.getMessage());
                    this.i.post(new d(this));
                    break;
                case 7:
                    FitnessCMMessage fitnessCMMessage3 = (FitnessCMMessage) gson.fromJson(fcmMessage.getMessageJsonString(), (Class<Object>) FitnessCMMessage.class);
                    this.i.post(new e(this));
                    break;
                case 8:
                    PreferenceManager.Companion companion3 = PreferenceManager.Companion;
                    companion3.saveFitnessCheerCount(this, companion3.getFitnessCheerCount(this) + 1);
                    com.coveiot.android.leonardo.firebaseservices.utils.NotificationManager.getInstance().notifyFitnessCheerClover(getApplicationContext(), (FitnessCMCheerCloverMessage) gson.fromJson(fcmMessage.getMessageJsonString(), (Class<Object>) FitnessCMCheerCloverMessage.class));
                    this.i.post(new f(this));
                    break;
                case 9:
                    PayUtils.fetchBadgesInBackground(this);
                    LeaderBoardNotificationModel leaderBoardNotificationModel = (LeaderBoardNotificationModel) gson.fromJson(fcmMessage.getMessageJsonString(), (Class<Object>) LeaderBoardNotificationModel.class);
                    if (leaderBoardNotificationModel != null) {
                        b(this, R.drawable.ic_stat_notification_icon_cove, leaderBoardNotificationModel.getTitle(), leaderBoardNotificationModel.getMessage(), fcmMessage.getMessageJsonString());
                    }
                    LogHelper.d(str, "LEADERBOARD_BADGE_OBTAINED_MESSAGE" + remoteMessage.getData());
                    break;
                case 10:
                    NewBadgeAddedModel newBadgeAddedModel = (NewBadgeAddedModel) gson.fromJson(fcmMessage.getMessageJsonString(), (Class<Object>) NewBadgeAddedModel.class);
                    if (newBadgeAddedModel != null) {
                        c(this, R.drawable.ic_stat_notification_icon_cove, newBadgeAddedModel.getTitle(), newBadgeAddedModel.getMessage());
                    }
                    PayUtils.fetchBadgesInBackground(this);
                    break;
                case 11:
                    LogHelper.d(str, fcmMessage.getMessageJsonString());
                    FitnessPendingRequestModel fitnessPendingRequestModel = (FitnessPendingRequestModel) gson.fromJson(fcmMessage.getMessageJsonString(), (Class<Object>) FitnessPendingRequestModel.class);
                    com.coveiot.android.leonardo.firebaseservices.utils.NotificationManager.getInstance().notifyFitnessCloversAcceptReject(getApplicationContext(), fitnessPendingRequestModel.getTitle(), fitnessPendingRequestModel.getMessage());
                    break;
                case 12:
                    LogHelper.d(str, fcmMessage.getMessageJsonString());
                    CustomNotifiacationModel customNotifiacationModel = (CustomNotifiacationModel) gson.fromJson(fcmMessage.getMessageJsonString(), (Class<Object>) CustomNotifiacationModel.class);
                    com.coveiot.android.leonardo.firebaseservices.utils.NotificationManager.getInstance().notifyCustomNotification(getApplicationContext(), customNotifiacationModel.getTitle(), customNotifiacationModel.getMessage(), customNotifiacationModel);
                    break;
                case 13:
                    if (SessionManager.getInstance(getApplicationContext()).isDevicePaired()) {
                        com.coveiot.android.leonardo.firebaseservices.utils.NotificationManager.getInstance().notifyFirmwareUpdate(getApplicationContext(), (FirmwareUpdateCoveFCMessage) gson.fromJson(fcmMessage.getMessageJsonString(), (Class<Object>) FirmwareUpdateCoveFCMessage.class));
                        break;
                    }
                    break;
                case 14:
                    com.coveiot.android.leonardo.firebaseservices.utils.NotificationManager.getInstance().notifyAppUpdate(getApplicationContext(), (AppUpdateCoveFCMessage) gson.fromJson(fcmMessage.getMessageJsonString(), (Class<Object>) AppUpdateCoveFCMessage.class));
                    break;
                case 15:
                    if (SessionManager.getInstance(getApplicationContext()).isDevicePaired()) {
                        com.coveiot.android.leonardo.firebaseservices.utils.NotificationManager.getInstance().notifyFitnessReportSubscription(getApplicationContext(), (FCMFitnessReportSubscribeMessage) gson.fromJson(fcmMessage.getMessageJsonString(), (Class<Object>) FCMFitnessReportSubscribeMessage.class));
                        break;
                    }
                    break;
                case 16:
                    if (SessionManager.getInstance(getApplicationContext()).isDevicePaired()) {
                        com.coveiot.android.leonardo.firebaseservices.utils.NotificationManager.getInstance().notifyFitnessVideoUpdate(getApplicationContext(), (FCMFitnessVideoMessage) gson.fromJson(fcmMessage.getMessageJsonString(), (Class<Object>) FCMFitnessVideoMessage.class));
                    }
                case 17:
                    if (SessionManager.getInstance(getApplicationContext()).isDevicePaired()) {
                        com.coveiot.android.leonardo.firebaseservices.utils.NotificationManager.getInstance().notifySportSettings(getApplicationContext(), (FcmSportsSettingsMessage) gson.fromJson(fcmMessage.getMessageJsonString(), (Class<Object>) FcmSportsSettingsMessage.class));
                        break;
                    }
                    break;
                case 18:
                    if (SessionManager.getInstance(getApplicationContext()).isDevicePaired()) {
                        com.coveiot.android.leonardo.firebaseservices.utils.NotificationManager.getInstance().notifySportSelection(getApplicationContext(), (FcmSportsSelectMatchMessage) gson.fromJson(fcmMessage.getMessageJsonString(), (Class<Object>) FcmSportsSelectMatchMessage.class));
                        break;
                    }
                    break;
                case 19:
                    if (SessionManager.getInstance(getApplicationContext()).isDevicePaired() && (fCMAlexaAccountLinkingStatus = (FCMAlexaAccountLinkingStatus) gson.fromJson(fcmMessage.getMessageJsonString(), (Class<Object>) FCMAlexaAccountLinkingStatus.class)) != null && fCMAlexaAccountLinkingStatus.getUserDeviceId() != null && fCMAlexaAccountLinkingStatus.getUserDeviceId().toString().equalsIgnoreCase(com.coveiot.coveaccess.prefs.PreferenceManager.getInstance().getUserDeviceID())) {
                        if (fCMAlexaAccountLinkingStatus.getStatus() != null && (fCMAlexaAccountLinkingStatus.getStatus().equalsIgnoreCase("ENABLED") || fCMAlexaAccountLinkingStatus.getStatus().equalsIgnoreCase("ENABLING") || fCMAlexaAccountLinkingStatus.getStatus().equalsIgnoreCase("LINKED"))) {
                            SessionManager.getInstance(this).setAlexaAccountLinkingStatus(true);
                            this.i.post(new g(this));
                            break;
                        } else {
                            SessionManager.getInstance(this).setAlexaAccountLinkingStatus(false);
                            this.i.post(new h(this));
                            break;
                        }
                    }
                    break;
                case 20:
                    LogHelper.d(str, "Watchface update on watch Message received " + fcmMessage.getMessageJsonString());
                    FCMWatchFaceUpdate fCMWatchFaceUpdate = (FCMWatchFaceUpdate) gson.fromJson(fcmMessage.getMessageJsonString(), (Class<Object>) FCMWatchFaceUpdate.class);
                    com.coveiot.android.leonardo.firebaseservices.utils.NotificationManager.getInstance().notifyWFUpdateOnWatchMessage(getApplicationContext(), fCMWatchFaceUpdate.getNotifIdentifier(), fCMWatchFaceUpdate.getTitle(), fCMWatchFaceUpdate.getMessage(), fCMWatchFaceUpdate.getBgImageUrl());
                    break;
                case 21:
                    LogHelper.d(str, "New Watchface List updated  Message received " + fcmMessage.getMessageJsonString());
                    FCMWatchFaceUpdate fCMWatchFaceUpdate2 = (FCMWatchFaceUpdate) gson.fromJson(fcmMessage.getMessageJsonString(), (Class<Object>) FCMWatchFaceUpdate.class);
                    com.coveiot.android.leonardo.firebaseservices.utils.NotificationManager.getInstance().notifyWFNewListUpdatedMessage(getApplicationContext(), fCMWatchFaceUpdate2.getNotifIdentifier(), fCMWatchFaceUpdate2.getTitle(), fCMWatchFaceUpdate2.getMessage(), fCMWatchFaceUpdate2.getBgImageUrl());
                    break;
                case 22:
                    LogHelper.d(str, "Use the watch face Studio Message received " + fcmMessage.getMessageJsonString());
                    FCMWatchFaceUpdate fCMWatchFaceUpdate3 = (FCMWatchFaceUpdate) gson.fromJson(fcmMessage.getMessageJsonString(), (Class<Object>) FCMWatchFaceUpdate.class);
                    com.coveiot.android.leonardo.firebaseservices.utils.NotificationManager.getInstance().notifyUseWFStudioMessage(getApplicationContext(), fCMWatchFaceUpdate3.getNotifIdentifier(), fCMWatchFaceUpdate3.getTitle(), fCMWatchFaceUpdate3.getMessage(), fCMWatchFaceUpdate3.getBgImageUrl());
                    break;
                case 23:
                    LogHelper.d(str, "Coins Feature  Message received " + fcmMessage.getMessageJsonString());
                    FCMBoatCoinsFeatureMessage fCMBoatCoinsFeatureMessage = (FCMBoatCoinsFeatureMessage) gson.fromJson(fcmMessage.getMessageJsonString(), (Class<Object>) FCMBoatCoinsFeatureMessage.class);
                    com.coveiot.android.leonardo.firebaseservices.utils.NotificationManager.getInstance().notifyBoatCoinsFeatureMessage(getApplicationContext(), fCMBoatCoinsFeatureMessage.getNotifIdentifier(), fCMBoatCoinsFeatureMessage.getWebViewUrl(), fCMBoatCoinsFeatureMessage.getTitle(), fCMBoatCoinsFeatureMessage.getMessage(), fCMBoatCoinsFeatureMessage.getBgImageUrl());
                    break;
                case 24:
                case 25:
                case 26:
                    LogHelper.d(str, "Fitness challenge feature Message received " + fcmMessage.getMessageJsonString());
                    FCMFitnessChallengeFeatureMessage fCMFitnessChallengeFeatureMessage = (FCMFitnessChallengeFeatureMessage) gson.fromJson(fcmMessage.getMessageJsonString(), (Class<Object>) FCMFitnessChallengeFeatureMessage.class);
                    com.coveiot.android.leonardo.firebaseservices.utils.NotificationManager.getInstance().notifyFitnessChallengeFeatureMessage(getApplicationContext(), fCMFitnessChallengeFeatureMessage.getNotifIdentifier(), fCMFitnessChallengeFeatureMessage.getTitle(), fCMFitnessChallengeFeatureMessage.getMessage(), fCMFitnessChallengeFeatureMessage.getBgImageUrl(), fCMFitnessChallengeFeatureMessage.getChallengeId(), fCMFitnessChallengeFeatureMessage.getChallengeType());
                    break;
                case 27:
                    SmartAlertUtils.Companion.pullSmartAlertAppConfigFromServerByPushNotification(getApplicationContext());
                    break;
                default:
                    LogHelper.d(str, "Unknown Cloud Message type received " + fcmMessage);
                    break;
            }
        } catch (Exception e3) {
            LogHelper.d(k, "JSON De-serialization exception " + e3);
        }
        String str2 = k;
        LogHelper.d(str2, remoteMessage.toString());
        LogHelper.d(str2, (getResources().getString(R.string.message_id) + remoteMessage.getMessageId()) + getResources().getString(R.string.sent_time) + remoteMessage.getSentTime());
    }

    @Override // com.google.firebase.messaging.FirebaseMessagingService
    public void onNewToken(String str) {
        super.onNewToken(str);
        SessionManager.getInstance(this).saveRegistrationToken(str);
        CleverTapAPI.getDefaultInstance(getApplicationContext()).pushFcmRegistrationId(str, true);
        String str2 = k;
        LogHelper.d(str2, "Refreshed token: " + str);
    }

    @Override // com.clevertap.android.sdk.pushnotification.CTPushNotificationListener
    public void onNotificationClickedPayloadReceived(HashMap<String, Object> hashMap) {
        LogHelper.e("ONCTEventClicked", "onNotificationClickedPayloadReceived called" + hashMap.entrySet());
        if (hashMap.get("NOTIFICATION_TYPE").equals("FIRMWARE_UPDATE_MESSAGE") && SessionManager.getInstance(getApplicationContext()).isDevicePaired()) {
            Intent intent = new Intent(this, ActivityDashboardNew.class);
            intent.setFlags(268435456);
            startActivity(intent);
        }
    }
}
