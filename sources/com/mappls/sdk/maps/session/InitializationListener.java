package com.mappls.sdk.maps.session;
/* loaded from: classes11.dex */
public interface InitializationListener {
    void onFailure(AuthenticationError authenticationError, Exception exc);

    void onSuccess();
}
