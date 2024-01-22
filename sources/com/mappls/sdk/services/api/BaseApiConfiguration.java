package com.mappls.sdk.services.api;

import android.location.Location;
/* loaded from: classes11.dex */
public class BaseApiConfiguration {
    public Boolean clusterId;
    public Boolean deviceFingerPrint;
    public Boolean isLoginRequired;
    public Location location;
    public String proxyHost;
    public Integer proxyPort;
    public String publicKey;
    public long publicKeyExpirationTime;
    public Boolean userId;
    public Boolean vin;
    public Boolean xDh;
    public Boolean xMsSeh;
    public ITokenRepo iTokenRepo = new a();
    public boolean isNavigating = false;
}
