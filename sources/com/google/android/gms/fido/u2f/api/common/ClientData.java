package com.google.android.gms.fido.u2f.api.common;

import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.fido.u2f.api.common.ChannelIdValue;
import org.json.JSONException;
import org.json.JSONObject;
@Deprecated
/* loaded from: classes6.dex */
public class ClientData {
    @NonNull
    public static final String KEY_CHALLENGE = "challenge";
    @NonNull
    public static final String KEY_CID_PUBKEY = "cid_pubkey";
    @NonNull
    public static final String KEY_ORIGIN = "origin";
    @NonNull
    public static final String KEY_TYPE = "typ";
    @NonNull
    public static final String TYPE_FINISH_ENROLLMENT = "navigator.id.finishEnrollment";
    @NonNull
    public static final String TYPE_GET_ASSERTION = "navigator.id.getAssertion";

    /* renamed from: a  reason: collision with root package name */
    public final String f8416a;
    public final String b;
    public final String c;
    public final ChannelIdValue d;

    /* loaded from: classes6.dex */
    public static class Builder implements Cloneable {
        public String h;
        public String i;
        public String j;
        public ChannelIdValue k;

        public Builder() {
            this.k = ChannelIdValue.ABSENT;
        }

        public Builder(String str, String str2, String str3, ChannelIdValue channelIdValue) {
            this.h = str;
            this.i = str2;
            this.j = str3;
            this.k = channelIdValue;
        }

        @NonNull
        public static Builder newInstance() {
            return new Builder();
        }

        @NonNull
        public ClientData build() {
            return new ClientData(this.h, this.i, this.j, this.k);
        }

        @NonNull
        /* renamed from: clone */
        public Builder m109clone() {
            return new Builder(this.h, this.i, this.j, this.k);
        }

        @NonNull
        public Builder setChallenge(@NonNull String str) {
            this.i = str;
            return this;
        }

        @NonNull
        public Builder setChannelId(@NonNull ChannelIdValue channelIdValue) {
            this.k = channelIdValue;
            return this;
        }

        @NonNull
        public Builder setOrigin(@NonNull String str) {
            this.j = str;
            return this;
        }

        @NonNull
        public Builder setType(@NonNull String str) {
            this.h = str;
            return this;
        }
    }

    public ClientData(String str, String str2, String str3, ChannelIdValue channelIdValue) {
        this.f8416a = (String) Preconditions.checkNotNull(str);
        this.b = (String) Preconditions.checkNotNull(str2);
        this.c = (String) Preconditions.checkNotNull(str3);
        this.d = (ChannelIdValue) Preconditions.checkNotNull(channelIdValue);
    }

    public boolean equals(@NonNull Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ClientData) {
            ClientData clientData = (ClientData) obj;
            return this.f8416a.equals(clientData.f8416a) && this.b.equals(clientData.b) && this.c.equals(clientData.c) && this.d.equals(clientData.d);
        }
        return false;
    }

    public int hashCode() {
        return ((((((this.f8416a.hashCode() + 31) * 31) + this.b.hashCode()) * 31) + this.c.hashCode()) * 31) + this.d.hashCode();
    }

    @NonNull
    public String toJsonString() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("typ", this.f8416a);
            jSONObject.put(KEY_CHALLENGE, this.b);
            jSONObject.put("origin", this.c);
            ChannelIdValue.ChannelIdValueType channelIdValueType = ChannelIdValue.ChannelIdValueType.ABSENT;
            int ordinal = this.d.getType().ordinal();
            if (ordinal == 1) {
                jSONObject.put(KEY_CID_PUBKEY, this.d.getStringValue());
            } else if (ordinal == 2) {
                jSONObject.put(KEY_CID_PUBKEY, this.d.getObjectValue());
            }
            return jSONObject.toString();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
