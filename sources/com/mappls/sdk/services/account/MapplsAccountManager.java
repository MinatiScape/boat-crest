package com.mappls.sdk.services.account;

import android.content.Context;
import androidx.annotation.Keep;
import com.mappls.android.lms.MapplsLMSManager;
import com.mappls.sdk.services.BuildConfig;
import com.mappls.sdk.services.api.ITokenRepo;
import com.mappls.sdk.services.api.MapplsApiConfiguration;
import com.mappls.sdk.services.utils.MapplsUtils;
@Keep
/* loaded from: classes11.dex */
public class MapplsAccountManager {
    private static final MapplsAccountManager ourInstance = new MapplsAccountManager();
    private String associationId;
    private String atlasClientId;
    private String atlasClientSecret;
    private String clusterId;
    private String deviceAlias;
    private String mapSDKKey;
    private String restAPIKey;
    private String userId;
    private String accessToken = null;
    private String refreshToken = null;
    private String region = null;

    private MapplsAccountManager() {
    }

    public static MapplsAccountManager getInstance() {
        return ourInstance;
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public String getAssociationId() {
        return this.associationId;
    }

    public String getAtlasClientId() {
        return this.atlasClientId;
    }

    public String getAtlasClientSecret() {
        return this.atlasClientSecret;
    }

    public String getClusterId() {
        return this.clusterId;
    }

    public String getDeviceAlias() {
        return this.deviceAlias;
    }

    public String getMapSDKKey() {
        return this.mapSDKKey;
    }

    public String getRefreshToken() {
        return this.refreshToken;
    }

    public String getRegion() {
        return this.region;
    }

    public String getRestAPIKey() {
        return this.restAPIKey;
    }

    public String getUserId() {
        return this.userId;
    }

    public void init(Context context) {
        MapplsUtils.setSDKContext(context);
        if (!MapplsLMSManager.isInitialised()) {
            MapplsLMSManager.initialize(context);
        }
        MapplsLMSManager.getInstance().setRestApiVersion(BuildConfig.VERSION_NAME);
    }

    public void setAccessToken(String str) {
        this.accessToken = str;
    }

    public void setAssociationId(String str) {
        this.associationId = str;
    }

    public void setAtlasClientId(String str) {
        this.atlasClientId = str;
    }

    public void setAtlasClientSecret(String str) {
        this.atlasClientSecret = str;
    }

    public void setClusterId(String str) {
        this.clusterId = str;
        MapplsApiConfiguration.getInstance().init();
    }

    public void setCustomTokenRepo(ITokenRepo iTokenRepo) {
        MapplsApiConfiguration.getInstance().setCustomTokenRepo(iTokenRepo);
    }

    public void setMapSDKKey(String str) {
        this.mapSDKKey = str;
    }

    public void setProxy(String str, int i) {
        MapplsApiConfiguration.getInstance().setProxy(str, Integer.valueOf(i));
    }

    public void setRefreshToken(String str, String str2) {
        this.refreshToken = str;
        this.accessToken = str2;
    }

    public void setRegion(String str) {
        this.region = str;
    }

    public void setRestAPIKey(String str) {
        this.restAPIKey = str;
    }

    public void setUserId(String str) {
        this.userId = str;
    }

    public void setClusterId(String str, String str2) {
        this.clusterId = str;
        this.deviceAlias = str2;
        MapplsApiConfiguration.getInstance().init();
    }
}
