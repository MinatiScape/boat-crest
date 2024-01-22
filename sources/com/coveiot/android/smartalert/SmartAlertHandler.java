package com.coveiot.android.smartalert;

import android.content.Context;
import com.blankj.utilcode.util.GsonUtils;
import com.coveiot.android.smartalert.SmartAlertPreferenceManager;
import com.coveiot.android.smartalert.model.DeviceInfo;
import com.coveiot.android.smartalert.model.InputDynamicFieldConfiguration;
import com.coveiot.android.smartalert.model.MessageDynamicFieldType;
import com.coveiot.android.smartalert.model.MessageTargetType;
import com.coveiot.android.smartalert.model.ParsingOutput;
import com.coveiot.android.smartalert.model.SmartAlertAppConfig;
import com.coveiot.android.smartalert.model.SmartAlertTemplateConfig;
import com.coveiot.android.smartalert.model.TextFormatType;
import com.coveiot.android.smartalert.util.SmartAlertUtils;
import com.coveiot.covepreferences.data.SmartAlertAppServerConfData;
import com.google.gson.reflect.TypeToken;
import java.util.HashMap;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public class SmartAlertHandler {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public Context f5752a;
    @NotNull
    public String b;
    @Nullable
    public final SmartAlertAppServerConfData c;

    /* loaded from: classes6.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final int getFormatByte(@Nullable String str) {
            if (str != null) {
                if (Intrinsics.areEqual(str, TextFormatType.RESERVED.getType())) {
                    return 1;
                }
                if (Intrinsics.areEqual(str, TextFormatType.CENTER.getType())) {
                    return 2;
                }
                if (Intrinsics.areEqual(str, TextFormatType.CENTER_VERTICAL.getType())) {
                    return 4;
                }
                if (Intrinsics.areEqual(str, TextFormatType.CENTER_HORIZONTAL.getType())) {
                    return 8;
                }
                if (Intrinsics.areEqual(str, TextFormatType.LEFT.getType())) {
                    return 16;
                }
                if (Intrinsics.areEqual(str, TextFormatType.RIGHT.getType())) {
                    return 32;
                }
                if (Intrinsics.areEqual(str, TextFormatType.TOP.getType())) {
                    return 64;
                }
                if (Intrinsics.areEqual(str, TextFormatType.BOTTOM.getType())) {
                    return 128;
                }
            }
            return 0;
        }

        @NotNull
        public final MessageTargetType getMessageTargetType(@Nullable String str) {
            MessageTargetType messageTargetType = MessageTargetType.NUDGE;
            if ((str == null || str.length() == 0) || Intrinsics.areEqual(str, messageTargetType.getType())) {
                return messageTargetType;
            }
            MessageTargetType messageTargetType2 = MessageTargetType.SMART_ALERT;
            return Intrinsics.areEqual(str, messageTargetType2.getType()) ? messageTargetType2 : messageTargetType;
        }

        @NotNull
        public final TextFormatType getTextFormat(@Nullable String str) {
            TextFormatType textFormatType = TextFormatType.CENTER_HORIZONTAL;
            if (str != null) {
                TextFormatType textFormatType2 = TextFormatType.RESERVED;
                if (!Intrinsics.areEqual(str, textFormatType2.getType())) {
                    textFormatType2 = TextFormatType.CENTER;
                    if (!Intrinsics.areEqual(str, textFormatType2.getType())) {
                        if (Intrinsics.areEqual(str, textFormatType.getType())) {
                            return textFormatType;
                        }
                        textFormatType2 = TextFormatType.CENTER_VERTICAL;
                        if (!Intrinsics.areEqual(str, textFormatType2.getType())) {
                            textFormatType2 = TextFormatType.LEFT;
                            if (!Intrinsics.areEqual(str, textFormatType2.getType())) {
                                textFormatType2 = TextFormatType.RIGHT;
                                if (!Intrinsics.areEqual(str, textFormatType2.getType())) {
                                    textFormatType2 = TextFormatType.TOP;
                                    if (!Intrinsics.areEqual(str, textFormatType2.getType())) {
                                        textFormatType2 = TextFormatType.BOTTOM;
                                        if (!Intrinsics.areEqual(str, textFormatType2.getType())) {
                                            return textFormatType;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                return textFormatType2;
            }
            return textFormatType;
        }
    }

    public SmartAlertHandler(@NotNull Context context, @NotNull String packageName) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        this.f5752a = context;
        this.b = packageName;
        this.c = SmartAlertUtils.Companion.getSmartAlertServerAppConfigByPackageName(context, packageName);
    }

    public static /* synthetic */ ParsingOutput getParsedMessage$default(SmartAlertHandler smartAlertHandler, Context context, String str, String str2, int i, Object obj) {
        if (obj == null) {
            if ((i & 2) != 0) {
                str = null;
            }
            if ((i & 4) != 0) {
                str2 = null;
            }
            return smartAlertHandler.getParsedMessage(context, str, str2);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getParsedMessage");
    }

    public void clearGroupMap() {
        HashMap<String, HashMap<String, String>> messagePatternGroupMap = SmartAlertPreferenceManager.Companion.getInstance(this.f5752a).getMessagePatternGroupMap();
        if (messagePatternGroupMap != null) {
            messagePatternGroupMap.remove(this.b);
        }
    }

    @NotNull
    public final Context getContext() {
        return this.f5752a;
    }

    @NotNull
    public final HashMap<String, String> getGroupInfo() {
        HashMap<String, String> hashMap = SmartAlertPreferenceManager.Companion.getInstance(this.f5752a).getMessagePatternGroupMap().get(this.b);
        return hashMap == null ? new HashMap<>() : hashMap;
    }

    @NotNull
    public final String getPackageName() {
        return this.b;
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0029, code lost:
        if ((r31 == null || r31.length() == 0) == false) goto L219;
     */
    /* JADX WARN: Removed duplicated region for block: B:208:0x033a  */
    /* JADX WARN: Removed duplicated region for block: B:209:0x0344  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x009b  */
    @org.jetbrains.annotations.NotNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.coveiot.android.smartalert.model.ParsingOutput getParsedMessage(@org.jetbrains.annotations.NotNull android.content.Context r29, @org.jetbrains.annotations.Nullable java.lang.String r30, @org.jetbrains.annotations.Nullable java.lang.String r31) {
        /*
            Method dump skipped, instructions count: 840
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.smartalert.SmartAlertHandler.getParsedMessage(android.content.Context, java.lang.String, java.lang.String):com.coveiot.android.smartalert.model.ParsingOutput");
    }

    @Nullable
    public SmartAlertAppConfig getSmartAlertAppConfig() {
        SmartAlertAppServerConfData smartAlertAppServerConfData = this.c;
        if ((smartAlertAppServerConfData != null ? smartAlertAppServerConfData.getName() : null) != null && this.c.getJsonFileMap() != null) {
            HashMap<String, String> jsonFileMap = this.c.getJsonFileMap();
            Intrinsics.checkNotNull(jsonFileMap);
            StringBuilder sb = new StringBuilder();
            String name = this.c.getName();
            Intrinsics.checkNotNull(name);
            String lowerCase = name.toLowerCase();
            Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase()");
            sb.append(lowerCase);
            sb.append("_config.json");
            if (jsonFileMap.get(sb.toString()) != null) {
                HashMap<String, String> jsonFileMap2 = this.c.getJsonFileMap();
                Intrinsics.checkNotNull(jsonFileMap2);
                StringBuilder sb2 = new StringBuilder();
                String name2 = this.c.getName();
                Intrinsics.checkNotNull(name2);
                String lowerCase2 = name2.toLowerCase();
                Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase()");
                sb2.append(lowerCase2);
                sb2.append("_config.json");
                return (SmartAlertAppConfig) GsonUtils.fromJson(jsonFileMap2.get(sb2.toString()), new TypeToken<SmartAlertAppConfig>() { // from class: com.coveiot.android.smartalert.SmartAlertHandler$getSmartAlertAppConfig$1
                }.getType());
            }
        }
        return null;
    }

    @Nullable
    public final SmartAlertAppServerConfData getSmartAlertAppTemplateConfigData() {
        return this.c;
    }

    @Nullable
    public InputDynamicFieldConfiguration getSmartAlertDynamicField(@Nullable InputDynamicFieldConfiguration inputDynamicFieldConfiguration, @Nullable DeviceInfo deviceInfo) {
        if (inputDynamicFieldConfiguration != null) {
            String type = inputDynamicFieldConfiguration.getType();
            boolean z = true;
            if (!(type == null || type.length() == 0) && deviceInfo != null) {
                String resolution = deviceInfo.getResolution();
                if (resolution != null && resolution.length() != 0) {
                    z = false;
                }
                if (!z) {
                    String type2 = inputDynamicFieldConfiguration.getType();
                    if (Intrinsics.areEqual(type2, MessageDynamicFieldType.TEXT.getType())) {
                        try {
                            if (inputDynamicFieldConfiguration.getXPosition() == null) {
                                Intrinsics.checkNotNull(inputDynamicFieldConfiguration);
                                inputDynamicFieldConfiguration.setXPosition(0);
                                inputDynamicFieldConfiguration.setFormat(TextFormatType.CENTER_HORIZONTAL.getType());
                                inputDynamicFieldConfiguration.setWidth(Integer.valueOf(Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) deviceInfo.getResolution(), new String[]{"X"}, false, 0, 6, (Object) null).get(0))));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else if (Intrinsics.areEqual(type2, MessageDynamicFieldType.BAR.getType()) && inputDynamicFieldConfiguration.getWidth() == null) {
                        Intrinsics.checkNotNull(inputDynamicFieldConfiguration);
                        inputDynamicFieldConfiguration.setWidth(Integer.valueOf(Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) deviceInfo.getResolution(), new String[]{"X"}, false, 0, 6, (Object) null).get(0))));
                    }
                }
            }
        }
        return inputDynamicFieldConfiguration;
    }

    @Nullable
    public SmartAlertTemplateConfig getSmartAlertTemplateConfig() {
        SmartAlertAppServerConfData smartAlertAppServerConfData = this.c;
        if ((smartAlertAppServerConfData != null ? smartAlertAppServerConfData.getName() : null) != null && this.c.getJsonFileMap() != null) {
            HashMap<String, String> jsonFileMap = this.c.getJsonFileMap();
            Intrinsics.checkNotNull(jsonFileMap);
            StringBuilder sb = new StringBuilder();
            String name = this.c.getName();
            Intrinsics.checkNotNull(name);
            String lowerCase = name.toLowerCase();
            Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase()");
            sb.append(lowerCase);
            sb.append("_template.json");
            if (jsonFileMap.get(sb.toString()) != null) {
                HashMap<String, String> jsonFileMap2 = this.c.getJsonFileMap();
                Intrinsics.checkNotNull(jsonFileMap2);
                StringBuilder sb2 = new StringBuilder();
                String name2 = this.c.getName();
                Intrinsics.checkNotNull(name2);
                String lowerCase2 = name2.toLowerCase();
                Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase()");
                sb2.append(lowerCase2);
                sb2.append("_template.json");
                return (SmartAlertTemplateConfig) GsonUtils.fromJson(jsonFileMap2.get(sb2.toString()), new TypeToken<SmartAlertTemplateConfig>() { // from class: com.coveiot.android.smartalert.SmartAlertHandler$getSmartAlertTemplateConfig$1
                }.getType());
            }
        }
        return null;
    }

    public final void saveGroupInfo(@NotNull HashMap<String, String> groupInfo) {
        Intrinsics.checkNotNullParameter(groupInfo, "groupInfo");
        SmartAlertPreferenceManager.Companion companion = SmartAlertPreferenceManager.Companion;
        HashMap<String, HashMap<String, String>> messagePatternGroupMap = companion.getInstance(this.f5752a).getMessagePatternGroupMap();
        messagePatternGroupMap.put(this.b, groupInfo);
        companion.getInstance(this.f5752a).saveMessagePatternGroupMap(messagePatternGroupMap);
    }

    public final void setContext(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "<set-?>");
        this.f5752a = context;
    }

    public final void setPackageName(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.b = str;
    }
}
