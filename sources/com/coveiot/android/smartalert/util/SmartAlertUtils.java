package com.coveiot.android.smartalert.util;

import android.content.Context;
import android.util.Log;
import com.clevertap.android.sdk.Constants;
import com.coveiot.android.bleabstract.models.DynamicSportFieldAnimation;
import com.coveiot.android.bleabstract.models.DynamicSportFieldButton;
import com.coveiot.android.bleabstract.models.DynamicSportFieldButtonImage;
import com.coveiot.android.bleabstract.models.DynamicSportFieldCircular;
import com.coveiot.android.bleabstract.models.DynamicSportFieldImage;
import com.coveiot.android.bleabstract.models.DynamicSportFieldNumber;
import com.coveiot.android.bleabstract.models.DynamicSportFieldProgressBar;
import com.coveiot.android.bleabstract.models.DynamicSportFieldRectangle;
import com.coveiot.android.bleabstract.models.DynamicSportFieldSquare;
import com.coveiot.android.bleabstract.models.DynamicSportFieldText;
import com.coveiot.android.bleabstract.models.DynamicSportFieldTextV2;
import com.coveiot.android.bleabstract.models.DynamicSportsField;
import com.coveiot.android.bleabstract.models.SmartAlertAppData;
import com.coveiot.android.smartalert.SmartAlertHandler;
import com.coveiot.android.smartalert.SmartAlertPreferenceManager;
import com.coveiot.android.smartalert.model.InputDynamicFieldConfiguration;
import com.coveiot.android.smartalert.model.MessageDynamicFieldType;
import com.coveiot.android.smartalert.model.MessageTargetType;
import com.coveiot.android.smartalert.util.SmartAlertUtils;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.CoveUserDeviceSettings;
import com.coveiot.coveaccess.device.model.BleDeviceInfo;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.SGetSmartAlertAppsData;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.data.AppNotificationData;
import com.coveiot.covepreferences.data.SmartAlertAppServerConfData;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.FileDownloadTask;
import com.coveiot.utils.utility.LogHelper;
import com.goodix.ble.libcomx.logger.RingLogger;
import com.google.gson.Gson;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.e;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class SmartAlertUtils {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public static final String f5758a = "SmartAlertUtils";

    /* loaded from: classes6.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static final void e(Context context) {
            BleDeviceInfo bleDeviceInfo;
            Intrinsics.checkNotNullParameter(context, "$context");
            try {
                if (!AppUtils.isNetConnected(context) || (bleDeviceInfo = (BleDeviceInfo) new Gson().fromJson(SessionManager.getInstance(context).getBleDeviceInfo(), (Class<Object>) BleDeviceInfo.class)) == null || bleDeviceInfo.getFirmwareRevision() == null) {
                    return;
                }
                Companion companion = SmartAlertUtils.Companion;
                Context applicationContext = context.getApplicationContext();
                Intrinsics.checkNotNullExpressionValue(applicationContext, "context.applicationContext");
                companion.getSmartAlertAppsFromServer(applicationContext, null, true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public static /* synthetic */ DynamicSportsField getDynamicSportFieldFrom$default(Companion companion, String str, String str2, Integer num, MessageTargetType messageTargetType, InputDynamicFieldConfiguration inputDynamicFieldConfiguration, int i, Object obj) {
            if ((i & 2) != 0) {
                str2 = null;
            }
            String str3 = str2;
            if ((i & 4) != 0) {
                num = 0;
            }
            return companion.getDynamicSportFieldFrom(str, str3, num, messageTargetType, inputDynamicFieldConfiguration);
        }

        public static /* synthetic */ void getSmartAlertAppsFromServer$default(Companion companion, Context context, String str, boolean z, int i, Object obj) {
            if ((i & 4) != 0) {
                z = false;
            }
            companion.getSmartAlertAppsFromServer(context, str, z);
        }

        public final void b(File file) {
            if (file != null) {
                try {
                    if (file.exists()) {
                        if (file.isDirectory()) {
                            File[] listFiles = file.listFiles();
                            Intrinsics.checkNotNullExpressionValue(listFiles, "fileOrDirectory.listFiles()");
                            for (File file2 : listFiles) {
                                b(file2);
                            }
                        }
                        file.delete();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        public final Object c(final Context context, final int i, final String str, String str2, Continuation<? super Unit> continuation) {
            String tag = getTAG();
            LogHelper.d(tag, "downloadFile->" + str);
            File filesDir = context.getFilesDir();
            Intrinsics.checkNotNullExpressionValue(filesDir, "context.filesDir");
            b(d(filesDir, str));
            FileDownloadTask fileDownloadTask = new FileDownloadTask(context, str);
            fileDownloadTask.setDownloadFinishListener(new FileDownloadTask.DownloadFinishListener() { // from class: com.coveiot.android.smartalert.util.SmartAlertUtils$Companion$downloadFile$2
                @Override // com.coveiot.utils.utility.FileDownloadTask.DownloadFinishListener
                public void onDownloadError(@NotNull String msg) {
                    Intrinsics.checkNotNullParameter(msg, "msg");
                    String tag2 = SmartAlertUtils.Companion.getTAG();
                    LogHelper.d(tag2, "onDownloadError->" + str + " -> " + msg);
                }

                /* JADX WARN: Removed duplicated region for block: B:13:0x0059 A[Catch: Exception -> 0x00be, TryCatch #2 {Exception -> 0x00be, blocks: (B:2:0x0000, B:5:0x003a, B:7:0x004d, B:13:0x0059, B:14:0x005d, B:16:0x0063, B:18:0x006b, B:20:0x0071, B:23:0x0079, B:26:0x0089, B:28:0x008f, B:30:0x0095, B:31:0x009d, B:3:0x0035), top: B:41:0x0000 }] */
                /* JADX WARN: Removed duplicated region for block: B:54:? A[RETURN, SYNTHETIC] */
                @Override // com.coveiot.utils.utility.FileDownloadTask.DownloadFinishListener
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public void onDownloadFinish() {
                    /*
                        r7 = this;
                        com.coveiot.android.smartalert.util.SmartAlertUtils$Companion r0 = com.coveiot.android.smartalert.util.SmartAlertUtils.Companion     // Catch: java.lang.Exception -> Lbe
                        java.lang.String r0 = r0.getTAG()     // Catch: java.lang.Exception -> Lbe
                        java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> Lbe
                        r1.<init>()     // Catch: java.lang.Exception -> Lbe
                        java.lang.String r2 = "onDownloadFinish->"
                        r1.append(r2)     // Catch: java.lang.Exception -> Lbe
                        java.lang.String r2 = r1     // Catch: java.lang.Exception -> Lbe
                        r1.append(r2)     // Catch: java.lang.Exception -> Lbe
                        java.lang.String r1 = r1.toString()     // Catch: java.lang.Exception -> Lbe
                        com.coveiot.utils.utility.LogHelper.d(r0, r1)     // Catch: java.lang.Exception -> Lbe
                        java.io.File r0 = new java.io.File     // Catch: java.lang.Exception -> Lbe
                        android.content.Context r1 = r2     // Catch: java.lang.Exception -> Lbe
                        java.io.File r1 = r1.getFilesDir()     // Catch: java.lang.Exception -> Lbe
                        java.lang.String r2 = r1     // Catch: java.lang.Exception -> Lbe
                        r0.<init>(r1, r2)     // Catch: java.lang.Exception -> Lbe
                        java.nio.charset.Charset r1 = kotlin.text.Charsets.UTF_8     // Catch: java.lang.Exception -> Lbe
                        java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch: java.lang.Exception -> Lbe
                        java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch: java.lang.Exception -> Lbe
                        r3.<init>(r0)     // Catch: java.lang.Exception -> Lbe
                        r2.<init>(r3, r1)     // Catch: java.lang.Exception -> Lbe
                        java.lang.String r0 = kotlin.io.TextStreamsKt.readText(r2)     // Catch: java.lang.Throwable -> Lb7
                        r1 = 0
                        kotlin.io.CloseableKt.closeFinally(r2, r1)     // Catch: java.lang.Exception -> Lbe
                        com.coveiot.android.smartalert.SmartAlertPreferenceManager$Companion r2 = com.coveiot.android.smartalert.SmartAlertPreferenceManager.Companion     // Catch: java.lang.Exception -> Lbe
                        android.content.Context r3 = r2     // Catch: java.lang.Exception -> Lbe
                        java.lang.Object r2 = r2.getInstance(r3)     // Catch: java.lang.Exception -> Lbe
                        com.coveiot.android.smartalert.SmartAlertPreferenceManager r2 = (com.coveiot.android.smartalert.SmartAlertPreferenceManager) r2     // Catch: java.lang.Exception -> Lbe
                        java.util.List r2 = r2.getSmartAlertAppServerConfigData()     // Catch: java.lang.Exception -> Lbe
                        if (r2 == 0) goto L56
                        boolean r3 = r2.isEmpty()     // Catch: java.lang.Exception -> Lbe
                        if (r3 == 0) goto L54
                        goto L56
                    L54:
                        r3 = 0
                        goto L57
                    L56:
                        r3 = 1
                    L57:
                        if (r3 != 0) goto Lc2
                        java.util.Iterator r3 = r2.iterator()     // Catch: java.lang.Exception -> Lbe
                    L5d:
                        boolean r4 = r3.hasNext()     // Catch: java.lang.Exception -> Lbe
                        if (r4 == 0) goto Lc2
                        java.lang.Object r4 = r3.next()     // Catch: java.lang.Exception -> Lbe
                        com.coveiot.covepreferences.data.SmartAlertAppServerConfData r4 = (com.coveiot.covepreferences.data.SmartAlertAppServerConfData) r4     // Catch: java.lang.Exception -> Lbe
                        if (r4 == 0) goto L5d
                        com.coveiot.covepreferences.data.SmartAlertAppServerConfData$DeviceData r5 = r4.getDeviceData()     // Catch: java.lang.Exception -> Lbe
                        if (r5 == 0) goto L76
                        java.lang.Integer r5 = r5.getAppId()     // Catch: java.lang.Exception -> Lbe
                        goto L77
                    L76:
                        r5 = r1
                    L77:
                        if (r5 == 0) goto L5d
                        com.coveiot.covepreferences.data.SmartAlertAppServerConfData$DeviceData r5 = r4.getDeviceData()     // Catch: java.lang.Exception -> Lbe
                        kotlin.jvm.internal.Intrinsics.checkNotNull(r5)     // Catch: java.lang.Exception -> Lbe
                        java.lang.Integer r5 = r5.getAppId()     // Catch: java.lang.Exception -> Lbe
                        int r6 = r3     // Catch: java.lang.Exception -> Lbe
                        if (r5 != 0) goto L89
                        goto L5d
                    L89:
                        int r5 = r5.intValue()     // Catch: java.lang.Exception -> Lbe
                        if (r5 != r6) goto L5d
                        java.util.HashMap r1 = r4.getJsonFileMap()     // Catch: java.lang.Exception -> Lbe
                        if (r1 != 0) goto L9d
                        java.util.HashMap r1 = new java.util.HashMap     // Catch: java.lang.Exception -> Lbe
                        r1.<init>()     // Catch: java.lang.Exception -> Lbe
                        r4.setJsonFileMap(r1)     // Catch: java.lang.Exception -> Lbe
                    L9d:
                        java.util.HashMap r1 = r4.getJsonFileMap()     // Catch: java.lang.Exception -> Lbe
                        kotlin.jvm.internal.Intrinsics.checkNotNull(r1)     // Catch: java.lang.Exception -> Lbe
                        java.lang.String r3 = r1     // Catch: java.lang.Exception -> Lbe
                        r1.put(r3, r0)     // Catch: java.lang.Exception -> Lbe
                        com.coveiot.android.smartalert.SmartAlertPreferenceManager$Companion r0 = com.coveiot.android.smartalert.SmartAlertPreferenceManager.Companion     // Catch: java.lang.Exception -> Lbe
                        android.content.Context r1 = r2     // Catch: java.lang.Exception -> Lbe
                        java.lang.Object r0 = r0.getInstance(r1)     // Catch: java.lang.Exception -> Lbe
                        com.coveiot.android.smartalert.SmartAlertPreferenceManager r0 = (com.coveiot.android.smartalert.SmartAlertPreferenceManager) r0     // Catch: java.lang.Exception -> Lbe
                        r0.saveSmartAlertAppServerConfigData(r2)     // Catch: java.lang.Exception -> Lbe
                        goto Lc2
                    Lb7:
                        r0 = move-exception
                        throw r0     // Catch: java.lang.Throwable -> Lb9
                    Lb9:
                        r1 = move-exception
                        kotlin.io.CloseableKt.closeFinally(r2, r0)     // Catch: java.lang.Exception -> Lbe
                        throw r1     // Catch: java.lang.Exception -> Lbe
                    Lbe:
                        r0 = move-exception
                        r0.printStackTrace()
                    Lc2:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.smartalert.util.SmartAlertUtils$Companion$downloadFile$2.onDownloadFinish():void");
                }

                @Override // com.coveiot.utils.utility.FileDownloadTask.DownloadFinishListener
                public void onDownloadProgress(int i2) {
                    String tag2 = SmartAlertUtils.Companion.getTAG();
                    LogHelper.d(tag2, "Download progress: " + i2);
                }
            });
            fileDownloadTask.download(str2);
            return Unit.INSTANCE;
        }

        public final int convertHexTo565(@NotNull String hexColor) {
            Intrinsics.checkNotNullParameter(hexColor, "hexColor");
            String substring = hexColor.substring(1, 3);
            Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
            Integer.parseInt(substring, kotlin.text.a.checkRadix(16));
            String substring2 = hexColor.substring(3, 5);
            Intrinsics.checkNotNullExpressionValue(substring2, "this as java.lang.String…ing(startIndex, endIndex)");
            Integer.parseInt(substring2, kotlin.text.a.checkRadix(16));
            String substring3 = hexColor.substring(5, 7);
            Intrinsics.checkNotNullExpressionValue(substring3, "this as java.lang.String…ing(startIndex, endIndex)");
            Integer.parseInt(substring3, kotlin.text.a.checkRadix(16));
            String substring4 = hexColor.substring(1);
            Intrinsics.checkNotNullExpressionValue(substring4, "this as java.lang.String).substring(startIndex)");
            int parseInt = Integer.parseInt(substring4, kotlin.text.a.checkRadix(16));
            return ((parseInt & RingLogger.EVT_UPDATE) >> 3) | ((16252928 & parseInt) >> 8) | ((64512 & parseInt) >> 5);
        }

        public final File d(File file, String str) {
            File[] files = file.listFiles();
            Log.d("Files", "Size: " + files.length);
            Intrinsics.checkNotNullExpressionValue(files, "files");
            int length = files.length;
            for (int i = 0; i < length; i++) {
                File file2 = files[i];
                String name = file2.getName();
                Intrinsics.checkNotNullExpressionValue(name, "file.name");
                if (StringsKt__StringsKt.contains$default((CharSequence) name, (CharSequence) str, false, 2, (Object) null)) {
                    String tag = getTAG();
                    Log.d(tag, "FileName:" + file2.getName());
                    return file2;
                }
            }
            return null;
        }

        public final void f(Context context, List<SmartAlertAppData> list, List<? extends AppNotificationData> list2) {
            e.e(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new SmartAlertUtils$Companion$setSmartAlertConfig$1(context, list, list2, null), 2, null);
        }

        public final void g(Context context) {
            List<AppNotificationData> smartAlertAppNotificationsSettings = SmartAlertPreferenceManager.Companion.getInstance(context).getSmartAlertAppNotificationsSettings();
            if (smartAlertAppNotificationsSettings == null) {
                smartAlertAppNotificationsSettings = new ArrayList<>();
            }
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            for (AppNotificationData appNotificationData : smartAlertAppNotificationsSettings) {
                if (appNotificationData != null) {
                    String packageName = appNotificationData.getPackageName();
                    boolean z = false;
                    if (!(packageName == null || packageName.length() == 0)) {
                        String packageName2 = appNotificationData.getPackageName();
                        Intrinsics.checkNotNullExpressionValue(packageName2, "appData.packageName");
                        SmartAlertAppServerConfData smartAlertServerAppConfigByPackageName = getSmartAlertServerAppConfigByPackageName(context, packageName2);
                        if (smartAlertServerAppConfigByPackageName != null) {
                            SmartAlertAppServerConfData.DeviceData deviceData = smartAlertServerAppConfigByPackageName.getDeviceData();
                            if ((deviceData != null ? deviceData.getAppId() : null) != null) {
                                String name = smartAlertServerAppConfigByPackageName.getName();
                                if (!(name == null || name.length() == 0)) {
                                    SmartAlertAppServerConfData.DeviceData deviceData2 = smartAlertServerAppConfigByPackageName.getDeviceData();
                                    if ((deviceData2 != null ? deviceData2.getFontSize() : null) != null) {
                                        SmartAlertAppServerConfData.DeviceData deviceData3 = smartAlertServerAppConfigByPackageName.getDeviceData();
                                        String fontColor = deviceData3 != null ? deviceData3.getFontColor() : null;
                                        if (!((fontColor == null || fontColor.length() == 0) ? true : true)) {
                                            SmartAlertAppServerConfData.DeviceData deviceData4 = smartAlertServerAppConfigByPackageName.getDeviceData();
                                            Intrinsics.checkNotNull(deviceData4);
                                            Integer appId = deviceData4.getAppId();
                                            Intrinsics.checkNotNull(appId);
                                            int intValue = appId.intValue();
                                            String name2 = smartAlertServerAppConfigByPackageName.getName();
                                            Intrinsics.checkNotNull(name2);
                                            SmartAlertAppServerConfData.DeviceData deviceData5 = smartAlertServerAppConfigByPackageName.getDeviceData();
                                            Intrinsics.checkNotNull(deviceData5);
                                            Integer fontSize = deviceData5.getFontSize();
                                            Intrinsics.checkNotNull(fontSize);
                                            int intValue2 = fontSize.intValue();
                                            SmartAlertAppServerConfData.DeviceData deviceData6 = smartAlertServerAppConfigByPackageName.getDeviceData();
                                            Intrinsics.checkNotNull(deviceData6);
                                            String fontColor2 = deviceData6.getFontColor();
                                            Intrinsics.checkNotNull(fontColor2);
                                            arrayList.add(new SmartAlertAppData(intValue, name2, intValue2, convertHexTo565(fontColor2)));
                                            arrayList2.add(appNotificationData);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            f(context, arrayList, arrayList2);
        }

        @Nullable
        public final DynamicSportsField getDynamicSportFieldFrom(@Nullable String str, @Nullable String str2, @Nullable Integer num, @NotNull MessageTargetType messageTargetType, @NotNull InputDynamicFieldConfiguration inDynamicFieldConf) {
            DynamicSportsField dynamicSportFieldProgressBar;
            DynamicSportsField dynamicSportFieldButtonImage;
            Intrinsics.checkNotNullParameter(messageTargetType, "messageTargetType");
            Intrinsics.checkNotNullParameter(inDynamicFieldConf, "inDynamicFieldConf");
            boolean areEqual = Intrinsics.areEqual(str, MessageDynamicFieldType.TEXT.getType());
            String str3 = Constants.WHITE;
            boolean z = false;
            if (areEqual) {
                if (Intrinsics.areEqual(messageTargetType.getType(), MessageTargetType.SMART_ALERT.getType())) {
                    if (inDynamicFieldConf.getColor() != null && inDynamicFieldConf.getXPosition() != null && inDynamicFieldConf.getYPosition() != null && inDynamicFieldConf.getHeight() != null && inDynamicFieldConf.getWidth() != null && inDynamicFieldConf.getTextSize() != null && inDynamicFieldConf.getTypeface() != null && inDynamicFieldConf.getFormat() != null) {
                        if (!(str2 == null || str2.length() == 0)) {
                            String color = inDynamicFieldConf.getColor();
                            if (color == null || color.length() == 0) {
                                z = true;
                            }
                            if (!z) {
                                str3 = inDynamicFieldConf.getColor();
                                Intrinsics.checkNotNull(str3);
                            }
                            int convertHexTo565 = convertHexTo565(str3);
                            Integer xPosition = inDynamicFieldConf.getXPosition();
                            Intrinsics.checkNotNull(xPosition);
                            int intValue = xPosition.intValue();
                            Integer yPosition = inDynamicFieldConf.getYPosition();
                            Intrinsics.checkNotNull(yPosition);
                            int intValue2 = yPosition.intValue();
                            Integer height = inDynamicFieldConf.getHeight();
                            Intrinsics.checkNotNull(height);
                            int intValue3 = height.intValue();
                            Integer width = inDynamicFieldConf.getWidth();
                            Intrinsics.checkNotNull(width);
                            int intValue4 = width.intValue();
                            Integer textSize = inDynamicFieldConf.getTextSize();
                            Intrinsics.checkNotNull(textSize);
                            int intValue5 = textSize.intValue();
                            Integer typeface = inDynamicFieldConf.getTypeface();
                            Intrinsics.checkNotNull(typeface);
                            return new DynamicSportFieldTextV2(1, convertHexTo565, intValue, intValue2, intValue3, intValue4, intValue5, typeface.intValue(), str2, SmartAlertHandler.Companion.getFormatByte(inDynamicFieldConf.getFormat()));
                        }
                    }
                } else if (inDynamicFieldConf.getColor() != null && inDynamicFieldConf.getXPosition() != null && inDynamicFieldConf.getYPosition() != null && inDynamicFieldConf.getHeight() != null && inDynamicFieldConf.getWidth() != null && inDynamicFieldConf.getTextSize() != null && inDynamicFieldConf.getTypeface() != null) {
                    if (!(str2 == null || str2.length() == 0)) {
                        String color2 = inDynamicFieldConf.getColor();
                        if (color2 == null || color2.length() == 0) {
                            z = true;
                        }
                        if (!z) {
                            str3 = inDynamicFieldConf.getColor();
                            Intrinsics.checkNotNull(str3);
                        }
                        int convertHexTo5652 = convertHexTo565(str3);
                        Integer xPosition2 = inDynamicFieldConf.getXPosition();
                        Intrinsics.checkNotNull(xPosition2);
                        int intValue6 = xPosition2.intValue();
                        Integer yPosition2 = inDynamicFieldConf.getYPosition();
                        Intrinsics.checkNotNull(yPosition2);
                        int intValue7 = yPosition2.intValue();
                        Integer height2 = inDynamicFieldConf.getHeight();
                        Intrinsics.checkNotNull(height2);
                        int intValue8 = height2.intValue();
                        Integer width2 = inDynamicFieldConf.getWidth();
                        Intrinsics.checkNotNull(width2);
                        int intValue9 = width2.intValue();
                        Integer textSize2 = inDynamicFieldConf.getTextSize();
                        Intrinsics.checkNotNull(textSize2);
                        int intValue10 = textSize2.intValue();
                        Integer typeface2 = inDynamicFieldConf.getTypeface();
                        Intrinsics.checkNotNull(typeface2);
                        return new DynamicSportFieldText(1, convertHexTo5652, intValue6, intValue7, intValue8, intValue9, intValue10, typeface2.intValue(), str2);
                    }
                }
            } else if (Intrinsics.areEqual(str, MessageDynamicFieldType.NUMBER.getType())) {
                if (inDynamicFieldConf.getColor() != null && inDynamicFieldConf.getXPosition() != null && inDynamicFieldConf.getYPosition() != null && inDynamicFieldConf.getHeight() != null && inDynamicFieldConf.getWidth() != null && inDynamicFieldConf.getTextSize() != null && inDynamicFieldConf.getTypeface() != null) {
                    if (!(str2 == null || str2.length() == 0)) {
                        String color3 = inDynamicFieldConf.getColor();
                        if (color3 == null || color3.length() == 0) {
                            z = true;
                        }
                        if (!z) {
                            str3 = inDynamicFieldConf.getColor();
                            Intrinsics.checkNotNull(str3);
                        }
                        int convertHexTo5653 = convertHexTo565(str3);
                        Integer xPosition3 = inDynamicFieldConf.getXPosition();
                        Intrinsics.checkNotNull(xPosition3);
                        int intValue11 = xPosition3.intValue();
                        Integer yPosition3 = inDynamicFieldConf.getYPosition();
                        Intrinsics.checkNotNull(yPosition3);
                        int intValue12 = yPosition3.intValue();
                        Integer height3 = inDynamicFieldConf.getHeight();
                        Intrinsics.checkNotNull(height3);
                        int intValue13 = height3.intValue();
                        Integer width3 = inDynamicFieldConf.getWidth();
                        Intrinsics.checkNotNull(width3);
                        int intValue14 = width3.intValue();
                        Integer textSize3 = inDynamicFieldConf.getTextSize();
                        Intrinsics.checkNotNull(textSize3);
                        int intValue15 = textSize3.intValue();
                        Integer typeface3 = inDynamicFieldConf.getTypeface();
                        Intrinsics.checkNotNull(typeface3);
                        return new DynamicSportFieldNumber(1, convertHexTo5653, intValue11, intValue12, intValue13, intValue14, intValue15, typeface3.intValue(), str2);
                    }
                }
            } else if (Intrinsics.areEqual(str, MessageDynamicFieldType.CIRCULAR.getType())) {
                if (inDynamicFieldConf.getColor() != null && inDynamicFieldConf.getXPosition() != null && inDynamicFieldConf.getYPosition() != null && inDynamicFieldConf.getHeight() != null && inDynamicFieldConf.getWidth() != null) {
                    String color4 = inDynamicFieldConf.getColor();
                    if (color4 == null || color4.length() == 0) {
                        z = true;
                    }
                    if (!z) {
                        str3 = inDynamicFieldConf.getColor();
                        Intrinsics.checkNotNull(str3);
                    }
                    int convertHexTo5654 = convertHexTo565(str3);
                    Integer xPosition4 = inDynamicFieldConf.getXPosition();
                    Intrinsics.checkNotNull(xPosition4);
                    int intValue16 = xPosition4.intValue();
                    Integer yPosition4 = inDynamicFieldConf.getYPosition();
                    Intrinsics.checkNotNull(yPosition4);
                    int intValue17 = yPosition4.intValue();
                    Integer height4 = inDynamicFieldConf.getHeight();
                    Intrinsics.checkNotNull(height4);
                    int intValue18 = height4.intValue();
                    Integer width4 = inDynamicFieldConf.getWidth();
                    Intrinsics.checkNotNull(width4);
                    dynamicSportFieldProgressBar = new DynamicSportFieldCircular(1, convertHexTo5654, intValue16, intValue17, intValue18, width4.intValue());
                    return dynamicSportFieldProgressBar;
                }
            } else if (Intrinsics.areEqual(str, MessageDynamicFieldType.SQUARE.getType())) {
                if (inDynamicFieldConf.getColor() != null && inDynamicFieldConf.getXPosition() != null && inDynamicFieldConf.getYPosition() != null && inDynamicFieldConf.getHeight() != null && inDynamicFieldConf.getWidth() != null) {
                    String color5 = inDynamicFieldConf.getColor();
                    if (color5 == null || color5.length() == 0) {
                        z = true;
                    }
                    if (!z) {
                        str3 = inDynamicFieldConf.getColor();
                        Intrinsics.checkNotNull(str3);
                    }
                    int convertHexTo5655 = convertHexTo565(str3);
                    Integer xPosition5 = inDynamicFieldConf.getXPosition();
                    Intrinsics.checkNotNull(xPosition5);
                    int intValue19 = xPosition5.intValue();
                    Integer yPosition5 = inDynamicFieldConf.getYPosition();
                    Intrinsics.checkNotNull(yPosition5);
                    int intValue20 = yPosition5.intValue();
                    Integer height5 = inDynamicFieldConf.getHeight();
                    Intrinsics.checkNotNull(height5);
                    int intValue21 = height5.intValue();
                    Integer width5 = inDynamicFieldConf.getWidth();
                    Intrinsics.checkNotNull(width5);
                    dynamicSportFieldProgressBar = new DynamicSportFieldSquare(1, convertHexTo5655, intValue19, intValue20, intValue21, width5.intValue());
                    return dynamicSportFieldProgressBar;
                }
            } else if (Intrinsics.areEqual(str, MessageDynamicFieldType.RECTANGULAR.getType())) {
                if (inDynamicFieldConf.getColor() != null && inDynamicFieldConf.getXPosition() != null && inDynamicFieldConf.getYPosition() != null && inDynamicFieldConf.getHeight() != null && inDynamicFieldConf.getWidth() != null) {
                    String color6 = inDynamicFieldConf.getColor();
                    if (color6 == null || color6.length() == 0) {
                        z = true;
                    }
                    if (!z) {
                        str3 = inDynamicFieldConf.getColor();
                        Intrinsics.checkNotNull(str3);
                    }
                    int convertHexTo5656 = convertHexTo565(str3);
                    Integer xPosition6 = inDynamicFieldConf.getXPosition();
                    Intrinsics.checkNotNull(xPosition6);
                    int intValue22 = xPosition6.intValue();
                    Integer yPosition6 = inDynamicFieldConf.getYPosition();
                    Intrinsics.checkNotNull(yPosition6);
                    int intValue23 = yPosition6.intValue();
                    Integer height6 = inDynamicFieldConf.getHeight();
                    Intrinsics.checkNotNull(height6);
                    int intValue24 = height6.intValue();
                    Integer width6 = inDynamicFieldConf.getWidth();
                    Intrinsics.checkNotNull(width6);
                    dynamicSportFieldProgressBar = new DynamicSportFieldRectangle(1, convertHexTo5656, intValue22, intValue23, intValue24, width6.intValue());
                    return dynamicSportFieldProgressBar;
                }
            } else if (Intrinsics.areEqual(str, MessageDynamicFieldType.IMAGE.getType())) {
                if (inDynamicFieldConf.getColor() != null && inDynamicFieldConf.getXPosition() != null && inDynamicFieldConf.getYPosition() != null && inDynamicFieldConf.getHeight() != null && inDynamicFieldConf.getWidth() != null && inDynamicFieldConf.getImageId() != null) {
                    Integer imageId = inDynamicFieldConf.getImageId();
                    Intrinsics.checkNotNull(imageId);
                    if (imageId.intValue() > 0) {
                        String color7 = inDynamicFieldConf.getColor();
                        if (color7 == null || color7.length() == 0) {
                            z = true;
                        }
                        if (!z) {
                            str3 = inDynamicFieldConf.getColor();
                            Intrinsics.checkNotNull(str3);
                        }
                        int convertHexTo5657 = convertHexTo565(str3);
                        Integer xPosition7 = inDynamicFieldConf.getXPosition();
                        Intrinsics.checkNotNull(xPosition7);
                        int intValue25 = xPosition7.intValue();
                        Integer yPosition7 = inDynamicFieldConf.getYPosition();
                        Intrinsics.checkNotNull(yPosition7);
                        int intValue26 = yPosition7.intValue();
                        Integer height7 = inDynamicFieldConf.getHeight();
                        Intrinsics.checkNotNull(height7);
                        int intValue27 = height7.intValue();
                        Integer width7 = inDynamicFieldConf.getWidth();
                        Intrinsics.checkNotNull(width7);
                        int intValue28 = width7.intValue();
                        Integer imageId2 = inDynamicFieldConf.getImageId();
                        Intrinsics.checkNotNull(imageId2);
                        dynamicSportFieldProgressBar = new DynamicSportFieldImage(1, convertHexTo5657, intValue25, intValue26, intValue27, intValue28, imageId2.intValue());
                        return dynamicSportFieldProgressBar;
                    }
                }
            } else if (Intrinsics.areEqual(str, MessageDynamicFieldType.ANIMATION.getType())) {
                if (inDynamicFieldConf.getColor() != null && inDynamicFieldConf.getXPosition() != null && inDynamicFieldConf.getYPosition() != null && inDynamicFieldConf.getHeight() != null && inDynamicFieldConf.getWidth() != null && inDynamicFieldConf.getFormat() != null && inDynamicFieldConf.getFrameTime() != null) {
                    ArrayList<Integer> imgIdList = inDynamicFieldConf.getImgIdList();
                    if (!(imgIdList == null || imgIdList.isEmpty())) {
                        String color8 = inDynamicFieldConf.getColor();
                        if (color8 == null || color8.length() == 0) {
                            z = true;
                        }
                        if (!z) {
                            str3 = inDynamicFieldConf.getColor();
                            Intrinsics.checkNotNull(str3);
                        }
                        int convertHexTo5658 = convertHexTo565(str3);
                        Integer xPosition8 = inDynamicFieldConf.getXPosition();
                        Intrinsics.checkNotNull(xPosition8);
                        int intValue29 = xPosition8.intValue();
                        Integer yPosition8 = inDynamicFieldConf.getYPosition();
                        Intrinsics.checkNotNull(yPosition8);
                        int intValue30 = yPosition8.intValue();
                        Integer height8 = inDynamicFieldConf.getHeight();
                        Intrinsics.checkNotNull(height8);
                        int intValue31 = height8.intValue();
                        Integer width8 = inDynamicFieldConf.getWidth();
                        Intrinsics.checkNotNull(width8);
                        int intValue32 = width8.intValue();
                        SmartAlertHandler.Companion companion = SmartAlertHandler.Companion;
                        String format = inDynamicFieldConf.getFormat();
                        Intrinsics.checkNotNull(format);
                        int formatByte = companion.getFormatByte(format);
                        Integer frameTime = inDynamicFieldConf.getFrameTime();
                        Intrinsics.checkNotNull(frameTime);
                        int intValue33 = frameTime.intValue();
                        ArrayList<Integer> imgIdList2 = inDynamicFieldConf.getImgIdList();
                        Intrinsics.checkNotNull(imgIdList2);
                        int size = imgIdList2.size();
                        ArrayList<Integer> imgIdList3 = inDynamicFieldConf.getImgIdList();
                        Intrinsics.checkNotNull(imgIdList3);
                        dynamicSportFieldProgressBar = new DynamicSportFieldAnimation(1, convertHexTo5658, intValue29, intValue30, intValue31, intValue32, formatByte, intValue33, size, imgIdList3);
                        return dynamicSportFieldProgressBar;
                    }
                }
            } else if (Intrinsics.areEqual(str, MessageDynamicFieldType.BUTTON.getType())) {
                if (inDynamicFieldConf.getColor() != null && inDynamicFieldConf.getXPosition() != null && inDynamicFieldConf.getYPosition() != null && inDynamicFieldConf.getSideBySide() != null && inDynamicFieldConf.getHeight() != null && inDynamicFieldConf.getWidth() != null && inDynamicFieldConf.getButtonAction() != null && inDynamicFieldConf.getButtonRgb565Color() != null && inDynamicFieldConf.getTextSize() != null && inDynamicFieldConf.getButtonTextLength() != null) {
                    if (!(str2 == null || str2.length() == 0)) {
                        Integer sideBySide = inDynamicFieldConf.getSideBySide();
                        Intrinsics.checkNotNull(sideBySide);
                        int intValue34 = sideBySide.intValue();
                        Integer xPosition9 = inDynamicFieldConf.getXPosition();
                        Intrinsics.checkNotNull(xPosition9);
                        int intValue35 = xPosition9.intValue();
                        Integer yPosition9 = inDynamicFieldConf.getYPosition();
                        Intrinsics.checkNotNull(yPosition9);
                        int intValue36 = yPosition9.intValue();
                        Integer height9 = inDynamicFieldConf.getHeight();
                        Intrinsics.checkNotNull(height9);
                        int intValue37 = height9.intValue();
                        Integer width9 = inDynamicFieldConf.getWidth();
                        Intrinsics.checkNotNull(width9);
                        int intValue38 = width9.intValue();
                        Integer buttonAction = inDynamicFieldConf.getButtonAction();
                        Intrinsics.checkNotNull(buttonAction);
                        int intValue39 = buttonAction.intValue();
                        Integer buttonRgb565Color = inDynamicFieldConf.getButtonRgb565Color();
                        Intrinsics.checkNotNull(buttonRgb565Color);
                        int intValue40 = buttonRgb565Color.intValue();
                        String color9 = inDynamicFieldConf.getColor();
                        if (color9 == null || color9.length() == 0) {
                            z = true;
                        }
                        if (!z) {
                            str3 = inDynamicFieldConf.getColor();
                            Intrinsics.checkNotNull(str3);
                        }
                        int convertHexTo5659 = convertHexTo565(str3);
                        Integer textSize4 = inDynamicFieldConf.getTextSize();
                        Intrinsics.checkNotNull(textSize4);
                        int intValue41 = textSize4.intValue();
                        Intrinsics.checkNotNull(str2);
                        dynamicSportFieldButtonImage = new DynamicSportFieldButton(1, 1, intValue34, intValue35, intValue36, intValue37, intValue38, intValue39, intValue40, convertHexTo5659, intValue41, str2);
                        return dynamicSportFieldButtonImage;
                    }
                }
            } else if (Intrinsics.areEqual(str, MessageDynamicFieldType.BUTTON_IMG.getType())) {
                if (inDynamicFieldConf.getColor() != null && inDynamicFieldConf.getXPosition() != null && inDynamicFieldConf.getYPosition() != null && inDynamicFieldConf.getHeight() != null && inDynamicFieldConf.getWidth() != null && inDynamicFieldConf.getButtonAction() != null && inDynamicFieldConf.getImageId() != null && inDynamicFieldConf.getTextSize() != null) {
                    if (!(str2 == null || str2.length() == 0)) {
                        Integer sideBySide2 = inDynamicFieldConf.getSideBySide();
                        Intrinsics.checkNotNull(sideBySide2);
                        int intValue42 = sideBySide2.intValue();
                        Integer xPosition10 = inDynamicFieldConf.getXPosition();
                        Intrinsics.checkNotNull(xPosition10);
                        int intValue43 = xPosition10.intValue();
                        Integer yPosition10 = inDynamicFieldConf.getYPosition();
                        Intrinsics.checkNotNull(yPosition10);
                        int intValue44 = yPosition10.intValue();
                        Integer height10 = inDynamicFieldConf.getHeight();
                        Intrinsics.checkNotNull(height10);
                        int intValue45 = height10.intValue();
                        Integer width10 = inDynamicFieldConf.getWidth();
                        Intrinsics.checkNotNull(width10);
                        int intValue46 = width10.intValue();
                        Integer buttonAction2 = inDynamicFieldConf.getButtonAction();
                        Intrinsics.checkNotNull(buttonAction2);
                        int intValue47 = buttonAction2.intValue();
                        Integer imageId3 = inDynamicFieldConf.getImageId();
                        Intrinsics.checkNotNull(imageId3);
                        int intValue48 = imageId3.intValue();
                        String color10 = inDynamicFieldConf.getColor();
                        if (color10 == null || color10.length() == 0) {
                            z = true;
                        }
                        if (!z) {
                            str3 = inDynamicFieldConf.getColor();
                            Intrinsics.checkNotNull(str3);
                        }
                        int convertHexTo56510 = convertHexTo565(str3);
                        Integer textSize5 = inDynamicFieldConf.getTextSize();
                        Intrinsics.checkNotNull(textSize5);
                        int intValue49 = textSize5.intValue();
                        Intrinsics.checkNotNull(str2);
                        dynamicSportFieldButtonImage = new DynamicSportFieldButtonImage(1, 1, intValue42, intValue43, intValue44, intValue45, intValue46, intValue47, intValue48, convertHexTo56510, intValue49, str2);
                        return dynamicSportFieldButtonImage;
                    }
                }
            } else if (Intrinsics.areEqual(str, MessageDynamicFieldType.BAR.getType()) && inDynamicFieldConf.getProgressColor() != null && inDynamicFieldConf.getXPosition() != null && inDynamicFieldConf.getYPosition() != null && inDynamicFieldConf.getProgressBgImgId() != null && inDynamicFieldConf.getSliderImageId() != null && inDynamicFieldConf.getEndImageId() != null && num != null) {
                String color11 = inDynamicFieldConf.getColor();
                if (color11 == null || color11.length() == 0) {
                    z = true;
                }
                if (!z) {
                    str3 = inDynamicFieldConf.getColor();
                    Intrinsics.checkNotNull(str3);
                }
                int convertHexTo56511 = convertHexTo565(str3);
                Integer xPosition11 = inDynamicFieldConf.getXPosition();
                Intrinsics.checkNotNull(xPosition11);
                int intValue50 = xPosition11.intValue();
                Integer yPosition11 = inDynamicFieldConf.getYPosition();
                Intrinsics.checkNotNull(yPosition11);
                int intValue51 = yPosition11.intValue();
                Integer progressBgImgId = inDynamicFieldConf.getProgressBgImgId();
                Intrinsics.checkNotNull(progressBgImgId);
                int intValue52 = progressBgImgId.intValue();
                Integer sliderImageId = inDynamicFieldConf.getSliderImageId();
                Intrinsics.checkNotNull(sliderImageId);
                int intValue53 = sliderImageId.intValue();
                Integer endImageId = inDynamicFieldConf.getEndImageId();
                Intrinsics.checkNotNull(endImageId);
                dynamicSportFieldProgressBar = new DynamicSportFieldProgressBar(1, convertHexTo56511, intValue50, intValue51, intValue52, intValue53, endImageId.intValue(), num.intValue());
                return dynamicSportFieldProgressBar;
            }
            return null;
        }

        public final void getSmartAlertAppsFromServer(@NotNull final Context applicationContext, @Nullable String str, final boolean z) {
            Intrinsics.checkNotNullParameter(applicationContext, "applicationContext");
            CoveUserDeviceSettings.getSmartAlertApps(str, new CoveApiListener<SGetSmartAlertAppsData, CoveApiErrorModel>() { // from class: com.coveiot.android.smartalert.util.SmartAlertUtils$Companion$getSmartAlertAppsFromServer$1

                @DebugMetadata(c = "com.coveiot.android.smartalert.util.SmartAlertUtils$Companion$getSmartAlertAppsFromServer$1$onSuccess$1", f = "SmartAlertUtils.kt", i = {0}, l = {375, 383}, m = "invokeSuspend", n = {"confFromPref"}, s = {"L$1"})
                /* loaded from: classes6.dex */
                public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    public final /* synthetic */ Context $applicationContext;
                    public final /* synthetic */ boolean $isFromThePushNotification;
                    public final /* synthetic */ SGetSmartAlertAppsData $p0;
                    public Object L$0;
                    public Object L$1;
                    public int label;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public a(SGetSmartAlertAppsData sGetSmartAlertAppsData, Context context, boolean z, Continuation<? super a> continuation) {
                        super(2, continuation);
                        this.$p0 = sGetSmartAlertAppsData;
                        this.$applicationContext = context;
                        this.$isFromThePushNotification = z;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @NotNull
                    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                        return new a(this.$p0, this.$applicationContext, this.$isFromThePushNotification, continuation);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    @Nullable
                    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                        return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    /* JADX WARN: Removed duplicated region for block: B:44:0x0121  */
                    /* JADX WARN: Removed duplicated region for block: B:74:0x01b7  */
                    /* JADX WARN: Removed duplicated region for block: B:80:0x01c3  */
                    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:91:0x0224 -> B:42:0x011b). Please submit an issue!!! */
                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @org.jetbrains.annotations.Nullable
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct add '--show-bad-code' argument
                    */
                    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r17) {
                        /*
                            Method dump skipped, instructions count: 554
                            To view this dump add '--comments-level debug' option
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.smartalert.util.SmartAlertUtils$Companion$getSmartAlertAppsFromServer$1.a.invokeSuspend(java.lang.Object):java.lang.Object");
                    }
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable SGetSmartAlertAppsData sGetSmartAlertAppsData) {
                    e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new a(sGetSmartAlertAppsData, applicationContext, z, null), 2, null);
                }
            });
        }

        @Nullable
        public final SmartAlertAppServerConfData getSmartAlertServerAppConfigByPackageName(@NotNull Context context, @NotNull String packageName) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(packageName, "packageName");
            List<SmartAlertAppServerConfData> smartAlertAppServerConfigData = SmartAlertPreferenceManager.Companion.getInstance(context).getSmartAlertAppServerConfigData();
            if (!(smartAlertAppServerConfigData == null || smartAlertAppServerConfigData.isEmpty())) {
                for (SmartAlertAppServerConfData smartAlertAppServerConfData : smartAlertAppServerConfigData) {
                    if (smartAlertAppServerConfData != null && Intrinsics.areEqual(smartAlertAppServerConfData.getPackageName(), packageName)) {
                        return smartAlertAppServerConfData;
                    }
                }
            }
            return null;
        }

        @NotNull
        public final String getTAG() {
            return SmartAlertUtils.f5758a;
        }

        public final boolean isSmartAlertSupportedByPackageName(@NotNull Context context, @NotNull String packageName, @NotNull List<SmartAlertAppServerConfData> smartAlertAppServerConfDataList) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(packageName, "packageName");
            Intrinsics.checkNotNullParameter(smartAlertAppServerConfDataList, "smartAlertAppServerConfDataList");
            if (!smartAlertAppServerConfDataList.isEmpty()) {
                for (SmartAlertAppServerConfData smartAlertAppServerConfData : smartAlertAppServerConfDataList) {
                    if (smartAlertAppServerConfData != null && Intrinsics.areEqual(smartAlertAppServerConfData.getPackageName(), packageName)) {
                        return true;
                    }
                }
            }
            return false;
        }

        @JvmStatic
        public final void pullSmartAlertAppConfigFromServerByPushNotification(@NotNull final Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            new Thread(new Runnable() { // from class: com.coveiot.android.smartalert.util.a
                @Override // java.lang.Runnable
                public final void run() {
                    SmartAlertUtils.Companion.e(context);
                }
            }).start();
        }
    }

    @JvmStatic
    public static final void pullSmartAlertAppConfigFromServerByPushNotification(@NotNull Context context) {
        Companion.pullSmartAlertAppConfigFromServerByPushNotification(context);
    }
}
