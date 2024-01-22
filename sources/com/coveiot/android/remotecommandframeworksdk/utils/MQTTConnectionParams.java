package com.coveiot.android.remotecommandframeworksdk.utils;

import com.google.android.gms.fitness.FitnessActivities;
import com.google.android.material.color.c;
import java.io.InputStream;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jose4j.jwk.OctetSequenceJsonWebKey;
import org.jose4j.jwk.RsaJsonWebKey;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\bX\b\u0086\b\u0018\u00002\u00020\u0001B³\u0001\u0012\u0006\u0010\u001b\u001a\u00020\u0002\u0012\u0006\u0010\u001c\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u001d\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u001e\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010 \u001a\u00020\u0002\u0012\b\b\u0002\u0010!\u001a\u00020\n\u0012\b\b\u0002\u0010\"\u001a\u00020\n\u0012\b\b\u0002\u0010#\u001a\u00020\u000e\u0012\b\b\u0002\u0010$\u001a\u00020\u0002\u0012\b\b\u0002\u0010%\u001a\u00020\u0002\u0012\b\b\u0002\u0010&\u001a\u00020\u0002\u0012\b\b\u0002\u0010'\u001a\u00020\u000e\u0012\b\b\u0002\u0010(\u001a\u00020\u000e\u0012\n\b\u0002\u0010)\u001a\u0004\u0018\u00010\u0016\u0012\n\b\u0002\u0010*\u001a\u0004\u0018\u00010\u0016\u0012\n\b\u0002\u0010+\u001a\u0004\u0018\u00010\u0016¢\u0006\u0004\bl\u0010mJ\u0010\u0010\u0003\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0005\u0010\u0004J\u0010\u0010\u0006\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0006\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0007\u0010\u0004J\u0012\u0010\b\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\b\u0010\u0004J\u0010\u0010\t\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\t\u0010\u0004J\u0010\u0010\u000b\u001a\u00020\nHÆ\u0003¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\r\u001a\u00020\nHÆ\u0003¢\u0006\u0004\b\r\u0010\fJ\u0010\u0010\u000f\u001a\u00020\u000eHÆ\u0003¢\u0006\u0004\b\u000f\u0010\u0010J\u0010\u0010\u0011\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0011\u0010\u0004J\u0010\u0010\u0012\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0012\u0010\u0004J\u0010\u0010\u0013\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0013\u0010\u0004J\u0010\u0010\u0014\u001a\u00020\u000eHÆ\u0003¢\u0006\u0004\b\u0014\u0010\u0010J\u0010\u0010\u0015\u001a\u00020\u000eHÆ\u0003¢\u0006\u0004\b\u0015\u0010\u0010J\u0012\u0010\u0017\u001a\u0004\u0018\u00010\u0016HÆ\u0003¢\u0006\u0004\b\u0017\u0010\u0018J\u0012\u0010\u0019\u001a\u0004\u0018\u00010\u0016HÆ\u0003¢\u0006\u0004\b\u0019\u0010\u0018J\u0012\u0010\u001a\u001a\u0004\u0018\u00010\u0016HÆ\u0003¢\u0006\u0004\b\u001a\u0010\u0018JÂ\u0001\u0010,\u001a\u00020\u00002\b\b\u0002\u0010\u001b\u001a\u00020\u00022\b\b\u0002\u0010\u001c\u001a\u00020\u00022\b\b\u0002\u0010\u001d\u001a\u00020\u00022\b\b\u0002\u0010\u001e\u001a\u00020\u00022\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u00022\b\b\u0002\u0010 \u001a\u00020\u00022\b\b\u0002\u0010!\u001a\u00020\n2\b\b\u0002\u0010\"\u001a\u00020\n2\b\b\u0002\u0010#\u001a\u00020\u000e2\b\b\u0002\u0010$\u001a\u00020\u00022\b\b\u0002\u0010%\u001a\u00020\u00022\b\b\u0002\u0010&\u001a\u00020\u00022\b\b\u0002\u0010'\u001a\u00020\u000e2\b\b\u0002\u0010(\u001a\u00020\u000e2\n\b\u0002\u0010)\u001a\u0004\u0018\u00010\u00162\n\b\u0002\u0010*\u001a\u0004\u0018\u00010\u00162\n\b\u0002\u0010+\u001a\u0004\u0018\u00010\u0016HÆ\u0001¢\u0006\u0004\b,\u0010-J\u0010\u0010.\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b.\u0010\u0004J\u0010\u0010/\u001a\u00020\u000eHÖ\u0001¢\u0006\u0004\b/\u0010\u0010J\u001a\u00101\u001a\u00020\n2\b\u00100\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b1\u00102R\"\u0010\u001b\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b3\u00104\u001a\u0004\b5\u0010\u0004\"\u0004\b6\u00107R\"\u0010\u001c\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b8\u00104\u001a\u0004\b9\u0010\u0004\"\u0004\b:\u00107R\"\u0010\u001d\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b;\u00104\u001a\u0004\b<\u0010\u0004\"\u0004\b=\u00107R\"\u0010\u001e\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b>\u00104\u001a\u0004\b?\u0010\u0004\"\u0004\b@\u00107R$\u0010\u001f\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bA\u00104\u001a\u0004\bB\u0010\u0004\"\u0004\bC\u00107R\"\u0010 \u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bD\u00104\u001a\u0004\bE\u0010\u0004\"\u0004\bF\u00107R\"\u0010!\u001a\u00020\n8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bG\u0010H\u001a\u0004\b!\u0010\f\"\u0004\bI\u0010JR\"\u0010\"\u001a\u00020\n8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bK\u0010H\u001a\u0004\b\"\u0010\f\"\u0004\bL\u0010JR\"\u0010#\u001a\u00020\u000e8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bM\u0010N\u001a\u0004\bO\u0010\u0010\"\u0004\bP\u0010QR\"\u0010$\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bR\u00104\u001a\u0004\bS\u0010\u0004\"\u0004\bT\u00107R\"\u0010%\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bU\u00104\u001a\u0004\bV\u0010\u0004\"\u0004\bW\u00107R\"\u0010&\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bX\u00104\u001a\u0004\bY\u0010\u0004\"\u0004\bZ\u00107R\"\u0010'\u001a\u00020\u000e8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b[\u0010N\u001a\u0004\b\\\u0010\u0010\"\u0004\b]\u0010QR\"\u0010(\u001a\u00020\u000e8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b^\u0010N\u001a\u0004\b_\u0010\u0010\"\u0004\b`\u0010QR$\u0010)\u001a\u0004\u0018\u00010\u00168\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\ba\u0010b\u001a\u0004\bc\u0010\u0018\"\u0004\bd\u0010eR$\u0010*\u001a\u0004\u0018\u00010\u00168\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bf\u0010b\u001a\u0004\bg\u0010\u0018\"\u0004\bh\u0010eR$\u0010+\u001a\u0004\u0018\u00010\u00168\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bi\u0010b\u001a\u0004\bj\u0010\u0018\"\u0004\bk\u0010e¨\u0006n"}, d2 = {"Lcom/coveiot/android/remotecommandframeworksdk/utils/MQTTConnectionParams;", "", "", "component1", "()Ljava/lang/String;", "component2", "component3", "component4", "component5", "component6", "", "component7", "()Z", "component8", "", "component9", "()I", "component10", "component11", "component12", "component13", "component14", "Ljava/io/InputStream;", "component15", "()Ljava/io/InputStream;", "component16", "component17", "clientId", "host", "portNumber", "protocol", "topicToPublish", "topicToSubscribe", "isAutomaticReconnect", "isCleanSession", "reqQos", "reqContentType", "username", "password", "connectionTimeout", "keepAliveInterval", "caFileInputStream", "keyFileInputStream", "certFileInputStream", com.clevertap.android.sdk.Constants.COPY_TYPE, "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZILjava/lang/String;Ljava/lang/String;Ljava/lang/String;IILjava/io/InputStream;Ljava/io/InputStream;Ljava/io/InputStream;)Lcom/coveiot/android/remotecommandframeworksdk/utils/MQTTConnectionParams;", "toString", "hashCode", FitnessActivities.OTHER, "equals", "(Ljava/lang/Object;)Z", "a", "Ljava/lang/String;", "getClientId", "setClientId", "(Ljava/lang/String;)V", "b", "getHost", "setHost", c.f10260a, "getPortNumber", "setPortNumber", "d", "getProtocol", "setProtocol", RsaJsonWebKey.EXPONENT_MEMBER_NAME, "getTopicToPublish", "setTopicToPublish", "f", "getTopicToSubscribe", "setTopicToSubscribe", "g", "Z", "setAutomaticReconnect", "(Z)V", "h", "setCleanSession", "i", "I", "getReqQos", "setReqQos", "(I)V", "j", "getReqContentType", "setReqContentType", OctetSequenceJsonWebKey.KEY_VALUE_MEMBER_NAME, "getUsername", "setUsername", "l", "getPassword", "setPassword", "m", "getConnectionTimeout", "setConnectionTimeout", "n", "getKeepAliveInterval", "setKeepAliveInterval", "o", "Ljava/io/InputStream;", "getCaFileInputStream", "setCaFileInputStream", "(Ljava/io/InputStream;)V", RsaJsonWebKey.FIRST_PRIME_FACTOR_MEMBER_NAME, "getKeyFileInputStream", "setKeyFileInputStream", RsaJsonWebKey.SECOND_PRIME_FACTOR_MEMBER_NAME, "getCertFileInputStream", "setCertFileInputStream", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZILjava/lang/String;Ljava/lang/String;Ljava/lang/String;IILjava/io/InputStream;Ljava/io/InputStream;Ljava/io/InputStream;)V", "remotecommandframeworksdk_prodRelease"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes6.dex */
public final class MQTTConnectionParams {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public String f5652a;
    @NotNull
    public String b;
    @NotNull
    public String c;
    @NotNull
    public String d;
    @Nullable
    public String e;
    @NotNull
    public String f;
    public boolean g;
    public boolean h;
    public int i;
    @NotNull
    public String j;
    @NotNull
    public String k;
    @NotNull
    public String l;
    public int m;
    public int n;
    @Nullable
    public InputStream o;
    @Nullable
    public InputStream p;
    @Nullable
    public InputStream q;

    public MQTTConnectionParams(@NotNull String clientId, @NotNull String host, @NotNull String portNumber, @NotNull String protocol, @Nullable String str, @NotNull String topicToSubscribe, boolean z, boolean z2, int i, @NotNull String reqContentType, @NotNull String username, @NotNull String password, int i2, int i3, @Nullable InputStream inputStream, @Nullable InputStream inputStream2, @Nullable InputStream inputStream3) {
        Intrinsics.checkNotNullParameter(clientId, "clientId");
        Intrinsics.checkNotNullParameter(host, "host");
        Intrinsics.checkNotNullParameter(portNumber, "portNumber");
        Intrinsics.checkNotNullParameter(protocol, "protocol");
        Intrinsics.checkNotNullParameter(topicToSubscribe, "topicToSubscribe");
        Intrinsics.checkNotNullParameter(reqContentType, "reqContentType");
        Intrinsics.checkNotNullParameter(username, "username");
        Intrinsics.checkNotNullParameter(password, "password");
        this.f5652a = clientId;
        this.b = host;
        this.c = portNumber;
        this.d = protocol;
        this.e = str;
        this.f = topicToSubscribe;
        this.g = z;
        this.h = z2;
        this.i = i;
        this.j = reqContentType;
        this.k = username;
        this.l = password;
        this.m = i2;
        this.n = i3;
        this.o = inputStream;
        this.p = inputStream2;
        this.q = inputStream3;
    }

    public /* synthetic */ MQTTConnectionParams(String str, String str2, String str3, String str4, String str5, String str6, boolean z, boolean z2, int i, String str7, String str8, String str9, int i2, int i3, InputStream inputStream, InputStream inputStream2, InputStream inputStream3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i4 & 4) != 0 ? Constants.MQTT_PORT_NUMBER.getValue() : str3, (i4 & 8) != 0 ? Constants.MQTT_SSL_PROTOCOL.getValue() : str4, (i4 & 16) != 0 ? null : str5, str6, (i4 & 64) != 0 ? true : z, (i4 & 128) != 0 ? false : z2, (i4 & 256) != 0 ? Integer.parseInt(Constants.MQTT_QOS_1.getValue()) : i, (i4 & 512) != 0 ? Constants.CONTENT_TYPE_CBOR.getValue() : str7, (i4 & 1024) != 0 ? "" : str8, (i4 & 2048) != 0 ? "" : str9, (i4 & 4096) != 0 ? Integer.parseInt(Constants.CONNECTION_TIMEOUT.getValue()) : i2, (i4 & 8192) != 0 ? Integer.parseInt(Constants.KEEP_ALIVE_INTERVAL.getValue()) : i3, (i4 & 16384) != 0 ? null : inputStream, (32768 & i4) != 0 ? null : inputStream2, (i4 & 65536) != 0 ? null : inputStream3);
    }

    @NotNull
    public final String component1() {
        return this.f5652a;
    }

    @NotNull
    public final String component10() {
        return this.j;
    }

    @NotNull
    public final String component11() {
        return this.k;
    }

    @NotNull
    public final String component12() {
        return this.l;
    }

    public final int component13() {
        return this.m;
    }

    public final int component14() {
        return this.n;
    }

    @Nullable
    public final InputStream component15() {
        return this.o;
    }

    @Nullable
    public final InputStream component16() {
        return this.p;
    }

    @Nullable
    public final InputStream component17() {
        return this.q;
    }

    @NotNull
    public final String component2() {
        return this.b;
    }

    @NotNull
    public final String component3() {
        return this.c;
    }

    @NotNull
    public final String component4() {
        return this.d;
    }

    @Nullable
    public final String component5() {
        return this.e;
    }

    @NotNull
    public final String component6() {
        return this.f;
    }

    public final boolean component7() {
        return this.g;
    }

    public final boolean component8() {
        return this.h;
    }

    public final int component9() {
        return this.i;
    }

    @NotNull
    public final MQTTConnectionParams copy(@NotNull String clientId, @NotNull String host, @NotNull String portNumber, @NotNull String protocol, @Nullable String str, @NotNull String topicToSubscribe, boolean z, boolean z2, int i, @NotNull String reqContentType, @NotNull String username, @NotNull String password, int i2, int i3, @Nullable InputStream inputStream, @Nullable InputStream inputStream2, @Nullable InputStream inputStream3) {
        Intrinsics.checkNotNullParameter(clientId, "clientId");
        Intrinsics.checkNotNullParameter(host, "host");
        Intrinsics.checkNotNullParameter(portNumber, "portNumber");
        Intrinsics.checkNotNullParameter(protocol, "protocol");
        Intrinsics.checkNotNullParameter(topicToSubscribe, "topicToSubscribe");
        Intrinsics.checkNotNullParameter(reqContentType, "reqContentType");
        Intrinsics.checkNotNullParameter(username, "username");
        Intrinsics.checkNotNullParameter(password, "password");
        return new MQTTConnectionParams(clientId, host, portNumber, protocol, str, topicToSubscribe, z, z2, i, reqContentType, username, password, i2, i3, inputStream, inputStream2, inputStream3);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof MQTTConnectionParams) {
                MQTTConnectionParams mQTTConnectionParams = (MQTTConnectionParams) obj;
                return Intrinsics.areEqual(this.f5652a, mQTTConnectionParams.f5652a) && Intrinsics.areEqual(this.b, mQTTConnectionParams.b) && Intrinsics.areEqual(this.c, mQTTConnectionParams.c) && Intrinsics.areEqual(this.d, mQTTConnectionParams.d) && Intrinsics.areEqual(this.e, mQTTConnectionParams.e) && Intrinsics.areEqual(this.f, mQTTConnectionParams.f) && this.g == mQTTConnectionParams.g && this.h == mQTTConnectionParams.h && this.i == mQTTConnectionParams.i && Intrinsics.areEqual(this.j, mQTTConnectionParams.j) && Intrinsics.areEqual(this.k, mQTTConnectionParams.k) && Intrinsics.areEqual(this.l, mQTTConnectionParams.l) && this.m == mQTTConnectionParams.m && this.n == mQTTConnectionParams.n && Intrinsics.areEqual(this.o, mQTTConnectionParams.o) && Intrinsics.areEqual(this.p, mQTTConnectionParams.p) && Intrinsics.areEqual(this.q, mQTTConnectionParams.q);
            }
            return false;
        }
        return true;
    }

    @Nullable
    public final InputStream getCaFileInputStream() {
        return this.o;
    }

    @Nullable
    public final InputStream getCertFileInputStream() {
        return this.q;
    }

    @NotNull
    public final String getClientId() {
        return this.f5652a;
    }

    public final int getConnectionTimeout() {
        return this.m;
    }

    @NotNull
    public final String getHost() {
        return this.b;
    }

    public final int getKeepAliveInterval() {
        return this.n;
    }

    @Nullable
    public final InputStream getKeyFileInputStream() {
        return this.p;
    }

    @NotNull
    public final String getPassword() {
        return this.l;
    }

    @NotNull
    public final String getPortNumber() {
        return this.c;
    }

    @NotNull
    public final String getProtocol() {
        return this.d;
    }

    @NotNull
    public final String getReqContentType() {
        return this.j;
    }

    public final int getReqQos() {
        return this.i;
    }

    @Nullable
    public final String getTopicToPublish() {
        return this.e;
    }

    @NotNull
    public final String getTopicToSubscribe() {
        return this.f;
    }

    @NotNull
    public final String getUsername() {
        return this.k;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        String str = this.f5652a;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.b;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.c;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.d;
        int hashCode4 = (hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.e;
        int hashCode5 = (hashCode4 + (str5 != null ? str5.hashCode() : 0)) * 31;
        String str6 = this.f;
        int hashCode6 = (hashCode5 + (str6 != null ? str6.hashCode() : 0)) * 31;
        boolean z = this.g;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        int i2 = (hashCode6 + i) * 31;
        boolean z2 = this.h;
        int hashCode7 = (((i2 + (z2 ? 1 : z2 ? 1 : 0)) * 31) + Integer.hashCode(this.i)) * 31;
        String str7 = this.j;
        int hashCode8 = (hashCode7 + (str7 != null ? str7.hashCode() : 0)) * 31;
        String str8 = this.k;
        int hashCode9 = (hashCode8 + (str8 != null ? str8.hashCode() : 0)) * 31;
        String str9 = this.l;
        int hashCode10 = (((((hashCode9 + (str9 != null ? str9.hashCode() : 0)) * 31) + Integer.hashCode(this.m)) * 31) + Integer.hashCode(this.n)) * 31;
        InputStream inputStream = this.o;
        int hashCode11 = (hashCode10 + (inputStream != null ? inputStream.hashCode() : 0)) * 31;
        InputStream inputStream2 = this.p;
        int hashCode12 = (hashCode11 + (inputStream2 != null ? inputStream2.hashCode() : 0)) * 31;
        InputStream inputStream3 = this.q;
        return hashCode12 + (inputStream3 != null ? inputStream3.hashCode() : 0);
    }

    public final boolean isAutomaticReconnect() {
        return this.g;
    }

    public final boolean isCleanSession() {
        return this.h;
    }

    public final void setAutomaticReconnect(boolean z) {
        this.g = z;
    }

    public final void setCaFileInputStream(@Nullable InputStream inputStream) {
        this.o = inputStream;
    }

    public final void setCertFileInputStream(@Nullable InputStream inputStream) {
        this.q = inputStream;
    }

    public final void setCleanSession(boolean z) {
        this.h = z;
    }

    public final void setClientId(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.f5652a = str;
    }

    public final void setConnectionTimeout(int i) {
        this.m = i;
    }

    public final void setHost(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.b = str;
    }

    public final void setKeepAliveInterval(int i) {
        this.n = i;
    }

    public final void setKeyFileInputStream(@Nullable InputStream inputStream) {
        this.p = inputStream;
    }

    public final void setPassword(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.l = str;
    }

    public final void setPortNumber(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.c = str;
    }

    public final void setProtocol(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.d = str;
    }

    public final void setReqContentType(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.j = str;
    }

    public final void setReqQos(int i) {
        this.i = i;
    }

    public final void setTopicToPublish(@Nullable String str) {
        this.e = str;
    }

    public final void setTopicToSubscribe(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.f = str;
    }

    public final void setUsername(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.k = str;
    }

    @NotNull
    public String toString() {
        return "MQTTConnectionParams(clientId=" + this.f5652a + ", host=" + this.b + ", portNumber=" + this.c + ", protocol=" + this.d + ", topicToPublish=" + this.e + ", topicToSubscribe=" + this.f + ", isAutomaticReconnect=" + this.g + ", isCleanSession=" + this.h + ", reqQos=" + this.i + ", reqContentType=" + this.j + ", username=" + this.k + ", password=" + this.l + ", connectionTimeout=" + this.m + ", keepAliveInterval=" + this.n + ", caFileInputStream=" + this.o + ", keyFileInputStream=" + this.p + ", certFileInputStream=" + this.q + ")";
    }
}
