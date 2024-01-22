package com.coveiot.android.leonardo.firebaseservices.model;

import com.clevertap.android.sdk.Constants;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
/* loaded from: classes2.dex */
public class CustomNotifiacationModel implements Serializable {
    @SerializedName("bannerNotificationMessage")
    @Expose
    private BannerNotificationMessageBean bannerNotificationMessage;
    @SerializedName("mediaUrl")
    @Expose
    private String mediaUrl;
    @SerializedName(Constants.KEY_MESSAGE)
    @Expose
    private String message;
    @SerializedName("targetScreen")
    @Expose
    private String targetScreen;
    @SerializedName("title")
    @Expose
    private String title;

    /* loaded from: classes2.dex */
    public static class BannerNotificationMessageBean implements Serializable {
        @SerializedName("backgroundColor")
        @Expose
        private String backgroundColor;
        @SerializedName("buttonBackgroundColor")
        @Expose
        private String buttonBackgroundColor;
        @SerializedName("buttonFontColor")
        @Expose
        private String buttonFontColor;
        @SerializedName("callToActionAndroid")
        @Expose
        private String callToActionAndroid;
        @SerializedName("callToActionName")
        @Expose
        private String callToActionName;
        @SerializedName("callToActionType")
        @Expose
        private String callToActionType;
        @SerializedName("isSharable")
        @Expose
        private boolean isSharable;
        @SerializedName("mediaType")
        @Expose
        private String mediaType;
        @SerializedName("mediaUrl")
        @Expose
        private String mediaUrl;
        @SerializedName(Constants.KEY_MESSAGE)
        @Expose
        private String message;
        @SerializedName("messageFontColor")
        @Expose
        private String messageFontColor;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("titleFontColor")
        @Expose
        private String titleFontColor;

        public String getBackgroundColor() {
            return this.backgroundColor;
        }

        public String getButtonBackgroundColor() {
            return this.buttonBackgroundColor;
        }

        public String getButtonFontColor() {
            return this.buttonFontColor;
        }

        public String getCallToActionAndroid() {
            return this.callToActionAndroid;
        }

        public String getCallToActionName() {
            return this.callToActionName;
        }

        public String getCallToActionType() {
            return this.callToActionType;
        }

        public String getMediaType() {
            return this.mediaType;
        }

        public String getMediaUrl() {
            return this.mediaUrl;
        }

        public String getMessage() {
            return this.message;
        }

        public String getMessageFontColor() {
            return this.messageFontColor;
        }

        public String getTitle() {
            return this.title;
        }

        public String getTitleFontColor() {
            return this.titleFontColor;
        }

        public boolean isSharable() {
            return this.isSharable;
        }

        public void setBackgroundColor(String str) {
            this.backgroundColor = str;
        }

        public void setButtonBackgroundColor(String str) {
            this.buttonBackgroundColor = str;
        }

        public void setButtonFontColor(String str) {
            this.buttonFontColor = str;
        }

        public void setCallToActionAndroid(String str) {
            this.callToActionAndroid = str;
        }

        public void setCallToActionName(String str) {
            this.callToActionName = str;
        }

        public void setCallToActionType(String str) {
            this.callToActionType = str;
        }

        public void setMediaType(String str) {
            this.mediaType = str;
        }

        public void setMediaUrl(String str) {
            this.mediaUrl = str;
        }

        public void setMessage(String str) {
            this.message = str;
        }

        public void setMessageFontColor(String str) {
            this.messageFontColor = str;
        }

        public void setSharable(boolean z) {
            this.isSharable = z;
        }

        public void setTitle(String str) {
            this.title = str;
        }

        public void setTitleFontColor(String str) {
            this.titleFontColor = str;
        }
    }

    public BannerNotificationMessageBean getBannerNotificationMessage() {
        return this.bannerNotificationMessage;
    }

    public String getMediaUrl() {
        return this.mediaUrl;
    }

    public String getMessage() {
        return this.message;
    }

    public String getTargetScreen() {
        return this.targetScreen;
    }

    public String getTitle() {
        return this.title;
    }

    public void setBannerNotificationMessage(BannerNotificationMessageBean bannerNotificationMessageBean) {
        this.bannerNotificationMessage = bannerNotificationMessageBean;
    }

    public void setMediaUrl(String str) {
        this.mediaUrl = str;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public void setTargetScreen(String str) {
        this.targetScreen = str;
    }

    public void setTitle(String str) {
        this.title = str;
    }
}
