package com.mappls.sdk.services.api;

import androidx.annotation.Keep;
@Keep
/* loaded from: classes11.dex */
public interface ITokenRepo {
    void clearToken();

    String getToken();

    void setToken(String str);
}
