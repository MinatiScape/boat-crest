package com.mappls.sdk.services.api;

import android.location.Location;
import androidx.annotation.Keep;
import com.google.gson.Gson;
import com.mappls.android.lms.MapplsLMSManager;
import com.mappls.sdk.services.api.sdkconfig.ServerInfo;
import com.mappls.sdk.services.api.sdkconfig.UrlData;
import com.mappls.sdk.services.api.utils.EncryptionUtility;
import com.mappls.sdk.services.utils.Constants;
import java.util.List;
@Keep
/* loaded from: classes11.dex */
public class MapplsApiConfiguration extends BaseApiConfiguration {
    private static final MapplsApiConfiguration OUR_INSTANCE = new MapplsApiConfiguration();
    private List<String> certificateHash;
    private Long expiry;
    private UrlData urlData;

    private MapplsApiConfiguration() {
    }

    public static MapplsApiConfiguration getInstance() {
        return OUR_INSTANCE;
    }

    public List<String> getCertificateHash() {
        return this.certificateHash;
    }

    public Long getExpiry() {
        return this.expiry;
    }

    public Location getLocation() {
        return this.location;
    }

    public String getMGISApiUrl() {
        UrlData urlData = this.urlData;
        return (urlData == null || urlData.getMgisApiUrl() == null) ? Constants.MGIS_APIS_BASE_URL : this.urlData.getMgisApiUrl();
    }

    public String getMGISUrl() {
        UrlData urlData = this.urlData;
        return (urlData == null || urlData.getMgisUrl() == null) ? Constants.MGIS_BASE_URL : this.urlData.getMgisUrl();
    }

    public String getProxyHost() {
        return this.proxyHost;
    }

    public int getProxyPort() {
        return this.proxyPort.intValue();
    }

    public String getPublicKey() {
        return this.publicKey;
    }

    public long getPublicKeyExpirationTime() {
        return this.publicKeyExpirationTime;
    }

    public ITokenRepo getTokenRepo() {
        return this.iTokenRepo;
    }

    public UrlData getUrlData() {
        return this.urlData;
    }

    public void init() {
        this.isLoginRequired = null;
    }

    public Boolean isLoginRequired() {
        return this.isLoginRequired;
    }

    public boolean isNavigating() {
        return this.isNavigating;
    }

    public void setCustomTokenRepo(ITokenRepo iTokenRepo) {
        this.iTokenRepo = iTokenRepo;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setNavigating(boolean z) {
        this.isNavigating = z;
    }

    public void setProxy(String str, Integer num) {
        this.proxyHost = str;
        this.proxyPort = num;
        MapplsService.initProxy();
        if (MapplsLMSManager.isInitialised()) {
            MapplsLMSManager.getInstance().setProxy(str, num);
        }
    }

    public void setPublicKey(String str) {
        this.publicKey = str;
    }

    public void setPublicKeyExpirationTime(long j) {
        this.publicKeyExpirationTime = j;
    }

    public void setValidationData(String str) {
        if (str == null) {
            this.urlData = null;
            this.certificateHash = null;
            this.expiry = null;
            return;
        }
        ServerInfo serverInfo = (ServerInfo) new Gson().fromJson(EncryptionUtility.decryptData(str), (Class<Object>) ServerInfo.class);
        if (serverInfo != null) {
            UrlData urlData = serverInfo.getUrlData();
            this.urlData = urlData;
            if (urlData != null && urlData.getLmsUrl() != null && MapplsLMSManager.isInitialised()) {
                MapplsLMSManager.getInstance().setBaseUrl(this.urlData.getLmsUrl());
            }
            this.certificateHash = serverInfo.getCh();
            this.expiry = serverInfo.getExpiry();
        }
    }
}
