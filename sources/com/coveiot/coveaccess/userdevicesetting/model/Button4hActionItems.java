package com.coveiot.coveaccess.userdevicesetting.model;

import com.clevertap.android.sdk.Constants;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class Button4hActionItems {
    @SerializedName("buttonC11n")

    /* renamed from: a  reason: collision with root package name */
    private ButtonC11n f6890a;

    /* loaded from: classes8.dex */
    public static class ActionItem {
        @SerializedName("event")

        /* renamed from: a  reason: collision with root package name */
        private String f6891a;
        @SerializedName("actionId")
        private String b;

        public String getActionId() {
            return this.b;
        }

        public String getEvent() {
            return this.f6891a;
        }

        public void setActionId(String str) {
            this.b = str;
        }

        public void setEvent(String str) {
            this.f6891a = str;
        }
    }

    /* loaded from: classes8.dex */
    public static class Button4h {
        @SerializedName("actionVersion")
        public int actionVersion;
        @SerializedName(Constants.KEY_ACTIONS)
        public List<ActionItem> actions;

        public int getActionVersion() {
            return this.actionVersion;
        }

        public List<ActionItem> getActions() {
            return this.actions;
        }

        public void setActionVersion(int i) {
            this.actionVersion = i;
        }

        public void setActions(List<ActionItem> list) {
            this.actions = list;
        }
    }

    /* loaded from: classes8.dex */
    public static class ButtonC11n {
        @SerializedName("button4h")

        /* renamed from: a  reason: collision with root package name */
        private Button4h f6892a;

        public Button4h getButton4h() {
            return this.f6892a;
        }

        public void setButton4h(Button4h button4h) {
            this.f6892a = button4h;
        }
    }

    public ButtonC11n getButtonC11n() {
        return this.f6890a;
    }

    public void setButtonC11n(ButtonC11n buttonC11n) {
        this.f6890a = buttonC11n;
    }
}
