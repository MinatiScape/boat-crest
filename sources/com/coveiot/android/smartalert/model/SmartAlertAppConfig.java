package com.coveiot.android.smartalert.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public class SmartAlertAppConfig implements Serializable {
    @SerializedName(alternate = {"smart_alert_config_uber", "smart_alert_config_swiggy"}, value = "smart_alert_config")
    @Expose
    @Nullable
    private SmartAlertApp smartAlertApp;

    /* loaded from: classes6.dex */
    public static final class SmartAlertApp {
        @SerializedName("messages_to_parse")
        @Expose
        @Nullable
        private List<MessageToParse> messagesToParse;

        /* loaded from: classes6.dex */
        public static final class MessageToParse implements Serializable {
            @SerializedName("groupIdMap")
            @Expose
            @Nullable
            private Map<String, Integer> groupIdMap;
            @SerializedName("messagePattern")
            @Expose
            @Nullable
            private ArrayList<String> patterns;
            @SerializedName("transform_to")
            @Expose
            @Nullable
            private List<TransformTo> transformTo;

            /* loaded from: classes6.dex */
            public static final class TransformTo implements Serializable {
                @SerializedName("progress")
                @Expose
                @Nullable
                private Integer progress;
                @SerializedName("templateId")
                @Expose
                @Nullable
                private Integer templateId;
                @SerializedName("text")
                @Expose
                @Nullable
                private String text;
                @SerializedName("type")
                @Expose
                @Nullable
                private String type;

                @Nullable
                public final Integer getProgress() {
                    return this.progress;
                }

                @Nullable
                public final Integer getTemplateId() {
                    return this.templateId;
                }

                @Nullable
                public final String getText() {
                    return this.text;
                }

                @Nullable
                public final String getType() {
                    return this.type;
                }

                public final void setProgress(@Nullable Integer num) {
                    this.progress = num;
                }

                public final void setTemplateId(@Nullable Integer num) {
                    this.templateId = num;
                }

                public final void setText(@Nullable String str) {
                    this.text = str;
                }

                public final void setType(@Nullable String str) {
                    this.type = str;
                }
            }

            @Nullable
            public final Map<String, Integer> getGroupIdMap() {
                return this.groupIdMap;
            }

            @Nullable
            public final ArrayList<String> getPatterns() {
                return this.patterns;
            }

            @Nullable
            public final List<TransformTo> getTransformTo() {
                return this.transformTo;
            }

            public final void setGroupIdMap(@Nullable Map<String, Integer> map) {
                this.groupIdMap = map;
            }

            public final void setPatterns(@Nullable ArrayList<String> arrayList) {
                this.patterns = arrayList;
            }

            public final void setTransformTo(@Nullable List<TransformTo> list) {
                this.transformTo = list;
            }
        }

        @Nullable
        public final List<MessageToParse> getMessagesToParse() {
            return this.messagesToParse;
        }

        public final void setMessagesToParse(@Nullable List<MessageToParse> list) {
            this.messagesToParse = list;
        }
    }

    @Nullable
    public final SmartAlertApp getSmartAlertApp() {
        return this.smartAlertApp;
    }

    public final void setSmartAlertApp(@Nullable SmartAlertApp smartAlertApp) {
        this.smartAlertApp = smartAlertApp;
    }
}
