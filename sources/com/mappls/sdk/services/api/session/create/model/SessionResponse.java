package com.mappls.sdk.services.api.session.create.model;

import androidx.annotation.Keep;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
@Keep
/* loaded from: classes8.dex */
public class SessionResponse {
    @SerializedName("expiresAfter")
    @Expose
    public double expiresAfter;
    @SerializedName("passport")
    @Expose
    public String passport;
    @SerializedName(alternate = {"sessionLink"}, value = "link")
    @Expose
    public String passportLink;
}
