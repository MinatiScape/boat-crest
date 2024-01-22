package com.coveiot.coveaccess.model.server;

import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
/* loaded from: classes8.dex */
public class GetAppConfigRes implements Serializable {
    @SerializedName("data")
    @Expose
    private ActionItemsData data;
    @SerializedName(Constants.KEY_MESSAGE)
    @Expose
    private String message;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    @Expose
    private String status;

    /* loaded from: classes8.dex */
    public static class ActionItemsData implements Serializable {
        @SerializedName("actionVersion")
        @Expose
        private Integer actionVersion;
        @SerializedName(Constants.KEY_ACTIONS)
        @Expose
        private List<ActionItem> actions;

        /* loaded from: classes8.dex */
        public static class ActionItem implements Serializable {
            @SerializedName("actionId")
            @Expose
            private String actionId;
            @SerializedName("fwCode")
            @Expose
            private Integer fwCode;
            @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)
            @Expose
            private String name;

            public String getActionId() {
                return this.actionId;
            }

            public int getFwCode() {
                return this.fwCode.intValue();
            }

            public String getName() {
                return this.name;
            }

            public void setActionId(String str) {
                this.actionId = str;
            }

            public void setFwCode(int i) {
                this.fwCode = Integer.valueOf(i);
            }

            public void setName(String str) {
                this.name = str;
            }
        }

        public int getActionVersion() {
            return this.actionVersion.intValue();
        }

        public List<ActionItem> getActions() {
            return this.actions;
        }

        public void setActionVersion(int i) {
            this.actionVersion = Integer.valueOf(i);
        }

        public void setActions(List<ActionItem> list) {
            this.actions = list;
        }
    }

    public ActionItemsData getData() {
        return this.data;
    }

    public String getMessage() {
        return this.message;
    }

    public String getStatus() {
        return this.status;
    }

    public void setData(ActionItemsData actionItemsData) {
        this.data = actionItemsData;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public void setStatus(String str) {
        this.status = str;
    }
}
