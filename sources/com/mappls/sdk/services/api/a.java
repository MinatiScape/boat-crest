package com.mappls.sdk.services.api;

import com.mappls.sdk.services.utils.MapplsUtils;
/* loaded from: classes11.dex */
public class a implements ITokenRepo {
    @Override // com.mappls.sdk.services.api.ITokenRepo
    public void clearToken() {
        SDKPreferenceHelper.getInstance(MapplsUtils.getSDKContext()).a();
    }

    @Override // com.mappls.sdk.services.api.ITokenRepo
    public String getToken() {
        return SDKPreferenceHelper.getInstance(MapplsUtils.getSDKContext()).b();
    }

    @Override // com.mappls.sdk.services.api.ITokenRepo
    public void setToken(String str) {
        SDKPreferenceHelper.getInstance(MapplsUtils.getSDKContext()).setApiDetail(str);
    }
}
