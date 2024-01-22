package com.coveiot.android.weathersdk.response.forecastmodel;

import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.product_config.CTProductConfigConstants;
import com.google.android.gms.fitness.FitnessActivities;
import com.google.android.material.color.c;
import java.util.Date;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jose4j.jwk.EllipticCurveJsonWebKey;
import org.jose4j.jwk.OctetSequenceJsonWebKey;
import org.jose4j.jwk.RsaJsonWebKey;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0006\n\u0002\b\u0010\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u001e\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b]\b\u0086\b\u0018\u00002\u00020\u0001BÇ\u0002\u0012\n\b\u0002\u0010'\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010(\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010)\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010*\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010+\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010,\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010-\u001a\u0004\u0018\u00010\f\u0012\n\b\u0002\u0010.\u001a\u0004\u0018\u00010\f\u0012\n\b\u0002\u0010/\u001a\u0004\u0018\u00010\f\u0012\n\b\u0002\u00100\u001a\u0004\u0018\u00010\f\u0012\n\b\u0002\u00101\u001a\u0004\u0018\u00010\f\u0012\n\b\u0002\u00102\u001a\u0004\u0018\u00010\f\u0012\n\b\u0002\u00103\u001a\u0004\u0018\u00010\f\u0012\n\b\u0002\u00104\u001a\u0004\u0018\u00010\f\u0012\n\b\u0002\u00105\u001a\u0004\u0018\u00010\f\u0012\n\b\u0002\u00106\u001a\u0004\u0018\u00010\f\u0012\n\b\u0002\u00107\u001a\u0004\u0018\u00010\f\u0012\n\b\u0002\u00108\u001a\u0004\u0018\u00010\f\u0012\n\b\u0002\u00109\u001a\u0004\u0018\u00010\f\u0012\n\b\u0002\u0010:\u001a\u0004\u0018\u00010\f\u0012\n\b\u0002\u0010;\u001a\u0004\u0018\u00010\f\u0012\n\b\u0002\u0010<\u001a\u0004\u0018\u00010\u001d\u0012\n\b\u0002\u0010=\u001a\u0004\u0018\u00010\f\u0012\n\b\u0002\u0010>\u001a\u0004\u0018\u00010\f\u0012\n\b\u0002\u0010?\u001a\u0004\u0018\u00010\f\u0012\u0010\b\u0002\u0010@\u001a\n\u0012\u0004\u0012\u00020$\u0018\u00010#¢\u0006\u0006\b¤\u0001\u0010¥\u0001J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u0003\u0010\u0004J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u0012\u0010\b\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\b\u0010\u0004J\u0012\u0010\t\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0004\b\t\u0010\u0007J\u0012\u0010\n\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\n\u0010\u0004J\u0012\u0010\u000b\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0004\b\u000b\u0010\u0007J\u0012\u0010\r\u001a\u0004\u0018\u00010\fHÆ\u0003¢\u0006\u0004\b\r\u0010\u000eJ\u0012\u0010\u000f\u001a\u0004\u0018\u00010\fHÆ\u0003¢\u0006\u0004\b\u000f\u0010\u000eJ\u0012\u0010\u0010\u001a\u0004\u0018\u00010\fHÆ\u0003¢\u0006\u0004\b\u0010\u0010\u000eJ\u0012\u0010\u0011\u001a\u0004\u0018\u00010\fHÆ\u0003¢\u0006\u0004\b\u0011\u0010\u000eJ\u0012\u0010\u0012\u001a\u0004\u0018\u00010\fHÆ\u0003¢\u0006\u0004\b\u0012\u0010\u000eJ\u0012\u0010\u0013\u001a\u0004\u0018\u00010\fHÆ\u0003¢\u0006\u0004\b\u0013\u0010\u000eJ\u0012\u0010\u0014\u001a\u0004\u0018\u00010\fHÆ\u0003¢\u0006\u0004\b\u0014\u0010\u000eJ\u0012\u0010\u0015\u001a\u0004\u0018\u00010\fHÆ\u0003¢\u0006\u0004\b\u0015\u0010\u000eJ\u0012\u0010\u0016\u001a\u0004\u0018\u00010\fHÆ\u0003¢\u0006\u0004\b\u0016\u0010\u000eJ\u0012\u0010\u0017\u001a\u0004\u0018\u00010\fHÆ\u0003¢\u0006\u0004\b\u0017\u0010\u000eJ\u0012\u0010\u0018\u001a\u0004\u0018\u00010\fHÆ\u0003¢\u0006\u0004\b\u0018\u0010\u000eJ\u0012\u0010\u0019\u001a\u0004\u0018\u00010\fHÆ\u0003¢\u0006\u0004\b\u0019\u0010\u000eJ\u0012\u0010\u001a\u001a\u0004\u0018\u00010\fHÆ\u0003¢\u0006\u0004\b\u001a\u0010\u000eJ\u0012\u0010\u001b\u001a\u0004\u0018\u00010\fHÆ\u0003¢\u0006\u0004\b\u001b\u0010\u000eJ\u0012\u0010\u001c\u001a\u0004\u0018\u00010\fHÆ\u0003¢\u0006\u0004\b\u001c\u0010\u000eJ\u0012\u0010\u001e\u001a\u0004\u0018\u00010\u001dHÆ\u0003¢\u0006\u0004\b\u001e\u0010\u001fJ\u0012\u0010 \u001a\u0004\u0018\u00010\fHÆ\u0003¢\u0006\u0004\b \u0010\u000eJ\u0012\u0010!\u001a\u0004\u0018\u00010\fHÆ\u0003¢\u0006\u0004\b!\u0010\u000eJ\u0012\u0010\"\u001a\u0004\u0018\u00010\fHÆ\u0003¢\u0006\u0004\b\"\u0010\u000eJ\u0018\u0010%\u001a\n\u0012\u0004\u0012\u00020$\u0018\u00010#HÆ\u0003¢\u0006\u0004\b%\u0010&JÎ\u0002\u0010A\u001a\u00020\u00002\n\b\u0002\u0010'\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010(\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010)\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010*\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010+\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010,\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010-\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010.\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010/\u001a\u0004\u0018\u00010\f2\n\b\u0002\u00100\u001a\u0004\u0018\u00010\f2\n\b\u0002\u00101\u001a\u0004\u0018\u00010\f2\n\b\u0002\u00102\u001a\u0004\u0018\u00010\f2\n\b\u0002\u00103\u001a\u0004\u0018\u00010\f2\n\b\u0002\u00104\u001a\u0004\u0018\u00010\f2\n\b\u0002\u00105\u001a\u0004\u0018\u00010\f2\n\b\u0002\u00106\u001a\u0004\u0018\u00010\f2\n\b\u0002\u00107\u001a\u0004\u0018\u00010\f2\n\b\u0002\u00108\u001a\u0004\u0018\u00010\f2\n\b\u0002\u00109\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010:\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010;\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010<\u001a\u0004\u0018\u00010\u001d2\n\b\u0002\u0010=\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010>\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010?\u001a\u0004\u0018\u00010\f2\u0010\b\u0002\u0010@\u001a\n\u0012\u0004\u0012\u00020$\u0018\u00010#HÆ\u0001¢\u0006\u0004\bA\u0010BJ\u0010\u0010D\u001a\u00020CHÖ\u0001¢\u0006\u0004\bD\u0010EJ\u0010\u0010F\u001a\u00020\u001dHÖ\u0001¢\u0006\u0004\bF\u0010GJ\u001a\u0010J\u001a\u00020I2\b\u0010H\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\bJ\u0010KR$\u0010'\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bL\u0010M\u001a\u0004\bN\u0010\u0004\"\u0004\bO\u0010PR$\u0010(\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bQ\u0010R\u001a\u0004\bS\u0010\u0007\"\u0004\bT\u0010UR$\u0010)\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bV\u0010M\u001a\u0004\bW\u0010\u0004\"\u0004\bX\u0010PR$\u0010*\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bY\u0010R\u001a\u0004\bZ\u0010\u0007\"\u0004\b[\u0010UR$\u0010+\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\\\u0010M\u001a\u0004\b]\u0010\u0004\"\u0004\b^\u0010PR$\u0010,\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b_\u0010R\u001a\u0004\b`\u0010\u0007\"\u0004\ba\u0010UR$\u0010-\u001a\u0004\u0018\u00010\f8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bb\u0010c\u001a\u0004\bd\u0010\u000e\"\u0004\be\u0010fR$\u0010.\u001a\u0004\u0018\u00010\f8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bg\u0010c\u001a\u0004\bh\u0010\u000e\"\u0004\bi\u0010fR$\u0010/\u001a\u0004\u0018\u00010\f8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bj\u0010c\u001a\u0004\bk\u0010\u000e\"\u0004\bl\u0010fR$\u00100\u001a\u0004\u0018\u00010\f8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bm\u0010c\u001a\u0004\bn\u0010\u000e\"\u0004\bo\u0010fR$\u00101\u001a\u0004\u0018\u00010\f8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bp\u0010c\u001a\u0004\bq\u0010\u000e\"\u0004\br\u0010fR$\u00102\u001a\u0004\u0018\u00010\f8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bs\u0010c\u001a\u0004\bt\u0010\u000e\"\u0004\bu\u0010fR$\u00103\u001a\u0004\u0018\u00010\f8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bv\u0010c\u001a\u0004\bw\u0010\u000e\"\u0004\bx\u0010fR$\u00104\u001a\u0004\u0018\u00010\f8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\by\u0010c\u001a\u0004\bz\u0010\u000e\"\u0004\b{\u0010fR$\u00105\u001a\u0004\u0018\u00010\f8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b|\u0010c\u001a\u0004\b}\u0010\u000e\"\u0004\b~\u0010fR&\u00106\u001a\u0004\u0018\u00010\f8\u0006@\u0006X\u0086\u000e¢\u0006\u0014\n\u0004\b\u007f\u0010c\u001a\u0005\b\u0080\u0001\u0010\u000e\"\u0005\b\u0081\u0001\u0010fR'\u00107\u001a\u0004\u0018\u00010\f8\u0006@\u0006X\u0086\u000e¢\u0006\u0015\n\u0005\b\u0082\u0001\u0010c\u001a\u0005\b\u0083\u0001\u0010\u000e\"\u0005\b\u0084\u0001\u0010fR'\u00108\u001a\u0004\u0018\u00010\f8\u0006@\u0006X\u0086\u000e¢\u0006\u0015\n\u0005\b\u0085\u0001\u0010c\u001a\u0005\b\u0086\u0001\u0010\u000e\"\u0005\b\u0087\u0001\u0010fR'\u00109\u001a\u0004\u0018\u00010\f8\u0006@\u0006X\u0086\u000e¢\u0006\u0015\n\u0005\b\u0088\u0001\u0010c\u001a\u0005\b\u0089\u0001\u0010\u000e\"\u0005\b\u008a\u0001\u0010fR'\u0010:\u001a\u0004\u0018\u00010\f8\u0006@\u0006X\u0086\u000e¢\u0006\u0015\n\u0005\b\u008b\u0001\u0010c\u001a\u0005\b\u008c\u0001\u0010\u000e\"\u0005\b\u008d\u0001\u0010fR'\u0010;\u001a\u0004\u0018\u00010\f8\u0006@\u0006X\u0086\u000e¢\u0006\u0015\n\u0005\b\u008e\u0001\u0010c\u001a\u0005\b\u008f\u0001\u0010\u000e\"\u0005\b\u0090\u0001\u0010fR)\u0010<\u001a\u0004\u0018\u00010\u001d8\u0006@\u0006X\u0086\u000e¢\u0006\u0017\n\u0006\b\u0091\u0001\u0010\u0092\u0001\u001a\u0005\b\u0093\u0001\u0010\u001f\"\u0006\b\u0094\u0001\u0010\u0095\u0001R'\u0010=\u001a\u0004\u0018\u00010\f8\u0006@\u0006X\u0086\u000e¢\u0006\u0015\n\u0005\b\u0096\u0001\u0010c\u001a\u0005\b\u0097\u0001\u0010\u000e\"\u0005\b\u0098\u0001\u0010fR'\u0010>\u001a\u0004\u0018\u00010\f8\u0006@\u0006X\u0086\u000e¢\u0006\u0015\n\u0005\b\u0099\u0001\u0010c\u001a\u0005\b\u009a\u0001\u0010\u000e\"\u0005\b\u009b\u0001\u0010fR'\u0010?\u001a\u0004\u0018\u00010\f8\u0006@\u0006X\u0086\u000e¢\u0006\u0015\n\u0005\b\u009c\u0001\u0010c\u001a\u0005\b\u009d\u0001\u0010\u000e\"\u0005\b\u009e\u0001\u0010fR/\u0010@\u001a\n\u0012\u0004\u0012\u00020$\u0018\u00010#8\u0006@\u0006X\u0086\u000e¢\u0006\u0017\n\u0006\b\u009f\u0001\u0010 \u0001\u001a\u0005\b¡\u0001\u0010&\"\u0006\b¢\u0001\u0010£\u0001¨\u0006¦\u0001"}, d2 = {"Lcom/coveiot/android/weathersdk/response/forecastmodel/WeatherDetails;", "", "", "component1", "()Ljava/lang/Long;", "Ljava/util/Date;", "component2", "()Ljava/util/Date;", "component3", "component4", "component5", "component6", "", "component7", "()Ljava/lang/Double;", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component20", "component21", "", "component22", "()Ljava/lang/Integer;", "component23", "component24", "component25", "", "Lcom/coveiot/android/weathersdk/response/forecastmodel/Weather;", "component26", "()Ljava/util/List;", "DateTimeSatmp", "date", "sunrise", "sunriseDate", "sunset", "sunsetDate", "dayTemp", "minTemp", "maxTemp", "nightTemp", "eveningTemp", "mornTemp", "dayFeelsLike", "nightFeelsLike", "eveFeelsLike", "mornFeelsLike", "pressure", "humidity", "speed", "deg", "gust", "clouds", "pop", "rain", "snow", "weather", Constants.COPY_TYPE, "(Ljava/lang/Long;Ljava/util/Date;Ljava/lang/Long;Ljava/util/Date;Ljava/lang/Long;Ljava/util/Date;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Integer;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/util/List;)Lcom/coveiot/android/weathersdk/response/forecastmodel/WeatherDetails;", "", "toString", "()Ljava/lang/String;", "hashCode", "()I", FitnessActivities.OTHER, "", "equals", "(Ljava/lang/Object;)Z", "a", "Ljava/lang/Long;", "getDateTimeSatmp", "setDateTimeSatmp", "(Ljava/lang/Long;)V", "b", "Ljava/util/Date;", "getDate", "setDate", "(Ljava/util/Date;)V", c.f10260a, "getSunrise", "setSunrise", "d", "getSunriseDate", "setSunriseDate", RsaJsonWebKey.EXPONENT_MEMBER_NAME, "getSunset", "setSunset", "f", "getSunsetDate", "setSunsetDate", "g", "Ljava/lang/Double;", "getDayTemp", "setDayTemp", "(Ljava/lang/Double;)V", "h", "getMinTemp", "setMinTemp", "i", "getMaxTemp", "setMaxTemp", "j", "getNightTemp", "setNightTemp", OctetSequenceJsonWebKey.KEY_VALUE_MEMBER_NAME, "getEveningTemp", "setEveningTemp", "l", "getMornTemp", "setMornTemp", "m", "getDayFeelsLike", "setDayFeelsLike", "n", "getNightFeelsLike", "setNightFeelsLike", "o", "getEveFeelsLike", "setEveFeelsLike", RsaJsonWebKey.FIRST_PRIME_FACTOR_MEMBER_NAME, "getMornFeelsLike", "setMornFeelsLike", RsaJsonWebKey.SECOND_PRIME_FACTOR_MEMBER_NAME, "getPressure", "setPressure", RsaJsonWebKey.PRIME_FACTOR_OTHER_MEMBER_NAME, "getHumidity", "setHumidity", "s", "getSpeed", "setSpeed", RsaJsonWebKey.FACTOR_CRT_COEFFICIENT, "getDeg", "setDeg", "u", "getGust", "setGust", CTProductConfigConstants.PRODUCT_CONFIG_JSON_KEY_FOR_VALUE, "Ljava/lang/Integer;", "getClouds", "setClouds", "(Ljava/lang/Integer;)V", Constants.INAPP_WINDOW, "getPop", "setPop", "x", "getRain", "setRain", EllipticCurveJsonWebKey.Y_MEMBER_NAME, "getSnow", "setSnow", "z", "Ljava/util/List;", "getWeather", "setWeather", "(Ljava/util/List;)V", "<init>", "(Ljava/lang/Long;Ljava/util/Date;Ljava/lang/Long;Ljava/util/Date;Ljava/lang/Long;Ljava/util/Date;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Integer;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/util/List;)V", "weather_prodRelease"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes8.dex */
public final class WeatherDetails {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public Long f6215a;
    @Nullable
    public Date b;
    @Nullable
    public Long c;
    @Nullable
    public Date d;
    @Nullable
    public Long e;
    @Nullable
    public Date f;
    @Nullable
    public Double g;
    @Nullable
    public Double h;
    @Nullable
    public Double i;
    @Nullable
    public Double j;
    @Nullable
    public Double k;
    @Nullable
    public Double l;
    @Nullable
    public Double m;
    @Nullable
    public Double n;
    @Nullable
    public Double o;
    @Nullable
    public Double p;
    @Nullable
    public Double q;
    @Nullable
    public Double r;
    @Nullable
    public Double s;
    @Nullable
    public Double t;
    @Nullable
    public Double u;
    @Nullable
    public Integer v;
    @Nullable
    public Double w;
    @Nullable
    public Double x;
    @Nullable
    public Double y;
    @Nullable
    public List<Weather> z;

    public WeatherDetails() {
        this(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 67108863, null);
    }

    public WeatherDetails(@Nullable Long l, @Nullable Date date, @Nullable Long l2, @Nullable Date date2, @Nullable Long l3, @Nullable Date date3, @Nullable Double d, @Nullable Double d2, @Nullable Double d3, @Nullable Double d4, @Nullable Double d5, @Nullable Double d6, @Nullable Double d7, @Nullable Double d8, @Nullable Double d9, @Nullable Double d10, @Nullable Double d11, @Nullable Double d12, @Nullable Double d13, @Nullable Double d14, @Nullable Double d15, @Nullable Integer num, @Nullable Double d16, @Nullable Double d17, @Nullable Double d18, @Nullable List<Weather> list) {
        this.f6215a = l;
        this.b = date;
        this.c = l2;
        this.d = date2;
        this.e = l3;
        this.f = date3;
        this.g = d;
        this.h = d2;
        this.i = d3;
        this.j = d4;
        this.k = d5;
        this.l = d6;
        this.m = d7;
        this.n = d8;
        this.o = d9;
        this.p = d10;
        this.q = d11;
        this.r = d12;
        this.s = d13;
        this.t = d14;
        this.u = d15;
        this.v = num;
        this.w = d16;
        this.x = d17;
        this.y = d18;
        this.z = list;
    }

    public /* synthetic */ WeatherDetails(Long l, Date date, Long l2, Date date2, Long l3, Date date3, Double d, Double d2, Double d3, Double d4, Double d5, Double d6, Double d7, Double d8, Double d9, Double d10, Double d11, Double d12, Double d13, Double d14, Double d15, Integer num, Double d16, Double d17, Double d18, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : l, (i & 2) != 0 ? null : date, (i & 4) != 0 ? null : l2, (i & 8) != 0 ? null : date2, (i & 16) != 0 ? null : l3, (i & 32) != 0 ? null : date3, (i & 64) != 0 ? null : d, (i & 128) != 0 ? null : d2, (i & 256) != 0 ? null : d3, (i & 512) != 0 ? null : d4, (i & 1024) != 0 ? null : d5, (i & 2048) != 0 ? null : d6, (i & 4096) != 0 ? null : d7, (i & 8192) != 0 ? null : d8, (i & 16384) != 0 ? null : d9, (i & 32768) != 0 ? null : d10, (i & 65536) != 0 ? null : d11, (i & 131072) != 0 ? null : d12, (i & 262144) != 0 ? null : d13, (i & 524288) != 0 ? null : d14, (i & 1048576) != 0 ? null : d15, (i & 2097152) != 0 ? null : num, (i & 4194304) != 0 ? null : d16, (i & 8388608) != 0 ? null : d17, (i & 16777216) != 0 ? null : d18, (i & 33554432) != 0 ? null : list);
    }

    @Nullable
    public final Long component1() {
        return this.f6215a;
    }

    @Nullable
    public final Double component10() {
        return this.j;
    }

    @Nullable
    public final Double component11() {
        return this.k;
    }

    @Nullable
    public final Double component12() {
        return this.l;
    }

    @Nullable
    public final Double component13() {
        return this.m;
    }

    @Nullable
    public final Double component14() {
        return this.n;
    }

    @Nullable
    public final Double component15() {
        return this.o;
    }

    @Nullable
    public final Double component16() {
        return this.p;
    }

    @Nullable
    public final Double component17() {
        return this.q;
    }

    @Nullable
    public final Double component18() {
        return this.r;
    }

    @Nullable
    public final Double component19() {
        return this.s;
    }

    @Nullable
    public final Date component2() {
        return this.b;
    }

    @Nullable
    public final Double component20() {
        return this.t;
    }

    @Nullable
    public final Double component21() {
        return this.u;
    }

    @Nullable
    public final Integer component22() {
        return this.v;
    }

    @Nullable
    public final Double component23() {
        return this.w;
    }

    @Nullable
    public final Double component24() {
        return this.x;
    }

    @Nullable
    public final Double component25() {
        return this.y;
    }

    @Nullable
    public final List<Weather> component26() {
        return this.z;
    }

    @Nullable
    public final Long component3() {
        return this.c;
    }

    @Nullable
    public final Date component4() {
        return this.d;
    }

    @Nullable
    public final Long component5() {
        return this.e;
    }

    @Nullable
    public final Date component6() {
        return this.f;
    }

    @Nullable
    public final Double component7() {
        return this.g;
    }

    @Nullable
    public final Double component8() {
        return this.h;
    }

    @Nullable
    public final Double component9() {
        return this.i;
    }

    @NotNull
    public final WeatherDetails copy(@Nullable Long l, @Nullable Date date, @Nullable Long l2, @Nullable Date date2, @Nullable Long l3, @Nullable Date date3, @Nullable Double d, @Nullable Double d2, @Nullable Double d3, @Nullable Double d4, @Nullable Double d5, @Nullable Double d6, @Nullable Double d7, @Nullable Double d8, @Nullable Double d9, @Nullable Double d10, @Nullable Double d11, @Nullable Double d12, @Nullable Double d13, @Nullable Double d14, @Nullable Double d15, @Nullable Integer num, @Nullable Double d16, @Nullable Double d17, @Nullable Double d18, @Nullable List<Weather> list) {
        return new WeatherDetails(l, date, l2, date2, l3, date3, d, d2, d3, d4, d5, d6, d7, d8, d9, d10, d11, d12, d13, d14, d15, num, d16, d17, d18, list);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof WeatherDetails) {
                WeatherDetails weatherDetails = (WeatherDetails) obj;
                return Intrinsics.areEqual(this.f6215a, weatherDetails.f6215a) && Intrinsics.areEqual(this.b, weatherDetails.b) && Intrinsics.areEqual(this.c, weatherDetails.c) && Intrinsics.areEqual(this.d, weatherDetails.d) && Intrinsics.areEqual(this.e, weatherDetails.e) && Intrinsics.areEqual(this.f, weatherDetails.f) && Intrinsics.areEqual((Object) this.g, (Object) weatherDetails.g) && Intrinsics.areEqual((Object) this.h, (Object) weatherDetails.h) && Intrinsics.areEqual((Object) this.i, (Object) weatherDetails.i) && Intrinsics.areEqual((Object) this.j, (Object) weatherDetails.j) && Intrinsics.areEqual((Object) this.k, (Object) weatherDetails.k) && Intrinsics.areEqual((Object) this.l, (Object) weatherDetails.l) && Intrinsics.areEqual((Object) this.m, (Object) weatherDetails.m) && Intrinsics.areEqual((Object) this.n, (Object) weatherDetails.n) && Intrinsics.areEqual((Object) this.o, (Object) weatherDetails.o) && Intrinsics.areEqual((Object) this.p, (Object) weatherDetails.p) && Intrinsics.areEqual((Object) this.q, (Object) weatherDetails.q) && Intrinsics.areEqual((Object) this.r, (Object) weatherDetails.r) && Intrinsics.areEqual((Object) this.s, (Object) weatherDetails.s) && Intrinsics.areEqual((Object) this.t, (Object) weatherDetails.t) && Intrinsics.areEqual((Object) this.u, (Object) weatherDetails.u) && Intrinsics.areEqual(this.v, weatherDetails.v) && Intrinsics.areEqual((Object) this.w, (Object) weatherDetails.w) && Intrinsics.areEqual((Object) this.x, (Object) weatherDetails.x) && Intrinsics.areEqual((Object) this.y, (Object) weatherDetails.y) && Intrinsics.areEqual(this.z, weatherDetails.z);
            }
            return false;
        }
        return true;
    }

    @Nullable
    public final Integer getClouds() {
        return this.v;
    }

    @Nullable
    public final Date getDate() {
        return this.b;
    }

    @Nullable
    public final Long getDateTimeSatmp() {
        return this.f6215a;
    }

    @Nullable
    public final Double getDayFeelsLike() {
        return this.m;
    }

    @Nullable
    public final Double getDayTemp() {
        return this.g;
    }

    @Nullable
    public final Double getDeg() {
        return this.t;
    }

    @Nullable
    public final Double getEveFeelsLike() {
        return this.o;
    }

    @Nullable
    public final Double getEveningTemp() {
        return this.k;
    }

    @Nullable
    public final Double getGust() {
        return this.u;
    }

    @Nullable
    public final Double getHumidity() {
        return this.r;
    }

    @Nullable
    public final Double getMaxTemp() {
        return this.i;
    }

    @Nullable
    public final Double getMinTemp() {
        return this.h;
    }

    @Nullable
    public final Double getMornFeelsLike() {
        return this.p;
    }

    @Nullable
    public final Double getMornTemp() {
        return this.l;
    }

    @Nullable
    public final Double getNightFeelsLike() {
        return this.n;
    }

    @Nullable
    public final Double getNightTemp() {
        return this.j;
    }

    @Nullable
    public final Double getPop() {
        return this.w;
    }

    @Nullable
    public final Double getPressure() {
        return this.q;
    }

    @Nullable
    public final Double getRain() {
        return this.x;
    }

    @Nullable
    public final Double getSnow() {
        return this.y;
    }

    @Nullable
    public final Double getSpeed() {
        return this.s;
    }

    @Nullable
    public final Long getSunrise() {
        return this.c;
    }

    @Nullable
    public final Date getSunriseDate() {
        return this.d;
    }

    @Nullable
    public final Long getSunset() {
        return this.e;
    }

    @Nullable
    public final Date getSunsetDate() {
        return this.f;
    }

    @Nullable
    public final List<Weather> getWeather() {
        return this.z;
    }

    public int hashCode() {
        Long l = this.f6215a;
        int hashCode = (l != null ? l.hashCode() : 0) * 31;
        Date date = this.b;
        int hashCode2 = (hashCode + (date != null ? date.hashCode() : 0)) * 31;
        Long l2 = this.c;
        int hashCode3 = (hashCode2 + (l2 != null ? l2.hashCode() : 0)) * 31;
        Date date2 = this.d;
        int hashCode4 = (hashCode3 + (date2 != null ? date2.hashCode() : 0)) * 31;
        Long l3 = this.e;
        int hashCode5 = (hashCode4 + (l3 != null ? l3.hashCode() : 0)) * 31;
        Date date3 = this.f;
        int hashCode6 = (hashCode5 + (date3 != null ? date3.hashCode() : 0)) * 31;
        Double d = this.g;
        int hashCode7 = (hashCode6 + (d != null ? d.hashCode() : 0)) * 31;
        Double d2 = this.h;
        int hashCode8 = (hashCode7 + (d2 != null ? d2.hashCode() : 0)) * 31;
        Double d3 = this.i;
        int hashCode9 = (hashCode8 + (d3 != null ? d3.hashCode() : 0)) * 31;
        Double d4 = this.j;
        int hashCode10 = (hashCode9 + (d4 != null ? d4.hashCode() : 0)) * 31;
        Double d5 = this.k;
        int hashCode11 = (hashCode10 + (d5 != null ? d5.hashCode() : 0)) * 31;
        Double d6 = this.l;
        int hashCode12 = (hashCode11 + (d6 != null ? d6.hashCode() : 0)) * 31;
        Double d7 = this.m;
        int hashCode13 = (hashCode12 + (d7 != null ? d7.hashCode() : 0)) * 31;
        Double d8 = this.n;
        int hashCode14 = (hashCode13 + (d8 != null ? d8.hashCode() : 0)) * 31;
        Double d9 = this.o;
        int hashCode15 = (hashCode14 + (d9 != null ? d9.hashCode() : 0)) * 31;
        Double d10 = this.p;
        int hashCode16 = (hashCode15 + (d10 != null ? d10.hashCode() : 0)) * 31;
        Double d11 = this.q;
        int hashCode17 = (hashCode16 + (d11 != null ? d11.hashCode() : 0)) * 31;
        Double d12 = this.r;
        int hashCode18 = (hashCode17 + (d12 != null ? d12.hashCode() : 0)) * 31;
        Double d13 = this.s;
        int hashCode19 = (hashCode18 + (d13 != null ? d13.hashCode() : 0)) * 31;
        Double d14 = this.t;
        int hashCode20 = (hashCode19 + (d14 != null ? d14.hashCode() : 0)) * 31;
        Double d15 = this.u;
        int hashCode21 = (hashCode20 + (d15 != null ? d15.hashCode() : 0)) * 31;
        Integer num = this.v;
        int hashCode22 = (hashCode21 + (num != null ? num.hashCode() : 0)) * 31;
        Double d16 = this.w;
        int hashCode23 = (hashCode22 + (d16 != null ? d16.hashCode() : 0)) * 31;
        Double d17 = this.x;
        int hashCode24 = (hashCode23 + (d17 != null ? d17.hashCode() : 0)) * 31;
        Double d18 = this.y;
        int hashCode25 = (hashCode24 + (d18 != null ? d18.hashCode() : 0)) * 31;
        List<Weather> list = this.z;
        return hashCode25 + (list != null ? list.hashCode() : 0);
    }

    public final void setClouds(@Nullable Integer num) {
        this.v = num;
    }

    public final void setDate(@Nullable Date date) {
        this.b = date;
    }

    public final void setDateTimeSatmp(@Nullable Long l) {
        this.f6215a = l;
    }

    public final void setDayFeelsLike(@Nullable Double d) {
        this.m = d;
    }

    public final void setDayTemp(@Nullable Double d) {
        this.g = d;
    }

    public final void setDeg(@Nullable Double d) {
        this.t = d;
    }

    public final void setEveFeelsLike(@Nullable Double d) {
        this.o = d;
    }

    public final void setEveningTemp(@Nullable Double d) {
        this.k = d;
    }

    public final void setGust(@Nullable Double d) {
        this.u = d;
    }

    public final void setHumidity(@Nullable Double d) {
        this.r = d;
    }

    public final void setMaxTemp(@Nullable Double d) {
        this.i = d;
    }

    public final void setMinTemp(@Nullable Double d) {
        this.h = d;
    }

    public final void setMornFeelsLike(@Nullable Double d) {
        this.p = d;
    }

    public final void setMornTemp(@Nullable Double d) {
        this.l = d;
    }

    public final void setNightFeelsLike(@Nullable Double d) {
        this.n = d;
    }

    public final void setNightTemp(@Nullable Double d) {
        this.j = d;
    }

    public final void setPop(@Nullable Double d) {
        this.w = d;
    }

    public final void setPressure(@Nullable Double d) {
        this.q = d;
    }

    public final void setRain(@Nullable Double d) {
        this.x = d;
    }

    public final void setSnow(@Nullable Double d) {
        this.y = d;
    }

    public final void setSpeed(@Nullable Double d) {
        this.s = d;
    }

    public final void setSunrise(@Nullable Long l) {
        this.c = l;
    }

    public final void setSunriseDate(@Nullable Date date) {
        this.d = date;
    }

    public final void setSunset(@Nullable Long l) {
        this.e = l;
    }

    public final void setSunsetDate(@Nullable Date date) {
        this.f = date;
    }

    public final void setWeather(@Nullable List<Weather> list) {
        this.z = list;
    }

    @NotNull
    public String toString() {
        return "WeatherDetails(DateTimeSatmp=" + this.f6215a + ", date=" + this.b + ", sunrise=" + this.c + ", sunriseDate=" + this.d + ", sunset=" + this.e + ", sunsetDate=" + this.f + ", dayTemp=" + this.g + ", minTemp=" + this.h + ", maxTemp=" + this.i + ", nightTemp=" + this.j + ", eveningTemp=" + this.k + ", mornTemp=" + this.l + ", dayFeelsLike=" + this.m + ", nightFeelsLike=" + this.n + ", eveFeelsLike=" + this.o + ", mornFeelsLike=" + this.p + ", pressure=" + this.q + ", humidity=" + this.r + ", speed=" + this.s + ", deg=" + this.t + ", gust=" + this.u + ", clouds=" + this.v + ", pop=" + this.w + ", rain=" + this.x + ", snow=" + this.y + ", weather=" + this.z + ")";
    }
}
