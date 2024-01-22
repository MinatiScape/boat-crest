package com.coveiot.android.leonardo.firebaseservices.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
/* loaded from: classes2.dex */
public class FcmShipbookLoggingControl implements Serializable {
    @SerializedName("enable")
    private Boolean enable;
    @SerializedName("expiryGMTTimeStamp")
    private Long expiryGMTTimeStamp;
    @SerializedName("shipbookKeys")
    private ShipbookKeysDTO shipbookKeys;

    /* loaded from: classes2.dex */
    public static class ShipbookKeysDTO {
        @SerializedName("shipbookId")
        private String shipbookId;
        @SerializedName("shipbookKey")
        private String shipbookKey;

        public String getShipbookId() {
            return this.shipbookId;
        }

        public String getShipbookKey() {
            return this.shipbookKey;
        }

        public void setShipbookId(String str) {
            this.shipbookId = str;
        }

        public void setShipbookKey(String str) {
            this.shipbookKey = str;
        }
    }

    public Boolean getEnable() {
        return this.enable;
    }

    public Long getExpiryGMTTimeStamp() {
        return this.expiryGMTTimeStamp;
    }

    public ShipbookKeysDTO getShipbookKeys() {
        return this.shipbookKeys;
    }

    public void setEnable(Boolean bool) {
        this.enable = bool;
    }

    public void setExpiryGMTTimeStamp(Long l) {
        this.expiryGMTTimeStamp = l;
    }

    public void setShipbookKeys(ShipbookKeysDTO shipbookKeysDTO) {
        this.shipbookKeys = shipbookKeysDTO;
    }
}
