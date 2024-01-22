package com.coveiot.android.weathersdk.response.currentweathermodel;

import androidx.exifinterface.media.ExifInterface;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.product_config.CTProductConfigConstants;
import com.google.android.gms.fitness.FitnessActivities;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
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
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b6\n\u0002\u0010\u000b\n\u0002\bb\b\u0086\b\u0018\u00002\u00020\u0001BÓ\u0002\u0012\n\b\u0002\u0010*\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010+\u001a\u0004\u0018\u00010\u0002\u0012\u0010\b\u0002\u0010,\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006\u0012\n\b\u0002\u0010-\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010.\u001a\u0004\u0018\u00010\r\u0012\n\b\u0002\u0010/\u001a\u0004\u0018\u00010\r\u0012\n\b\u0002\u00100\u001a\u0004\u0018\u00010\u0011\u0012\n\b\u0002\u00101\u001a\u0004\u0018\u00010\u0014\u0012\n\b\u0002\u00102\u001a\u0004\u0018\u00010\r\u0012\n\b\u0002\u00103\u001a\u0004\u0018\u00010\r\u0012\n\b\u0002\u00104\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u00105\u001a\u0004\u0018\u00010\r\u0012\n\b\u0002\u00106\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u00107\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u00108\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u00109\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010:\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010;\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010<\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010=\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010>\u001a\u0004\u0018\u00010\r\u0012\n\b\u0002\u0010?\u001a\u0004\u0018\u00010\r\u0012\n\b\u0002\u0010@\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010A\u001a\u0004\u0018\u00010\u0011\u0012\n\b\u0002\u0010B\u001a\u0004\u0018\u00010\u0014\u0012\n\b\u0002\u0010C\u001a\u0004\u0018\u00010\u0011\u0012\n\b\u0002\u0010D\u001a\u0004\u0018\u00010\u0014¢\u0006\u0006\b«\u0001\u0010¬\u0001J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u0003\u0010\u0004J\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u0005\u0010\u0004J\u0018\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006HÆ\u0003¢\u0006\u0004\b\b\u0010\tJ\u0012\u0010\u000b\u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0004\b\u000b\u0010\fJ\u0012\u0010\u000e\u001a\u0004\u0018\u00010\rHÆ\u0003¢\u0006\u0004\b\u000e\u0010\u000fJ\u0012\u0010\u0010\u001a\u0004\u0018\u00010\rHÆ\u0003¢\u0006\u0004\b\u0010\u0010\u000fJ\u0012\u0010\u0012\u001a\u0004\u0018\u00010\u0011HÆ\u0003¢\u0006\u0004\b\u0012\u0010\u0013J\u0012\u0010\u0015\u001a\u0004\u0018\u00010\u0014HÆ\u0003¢\u0006\u0004\b\u0015\u0010\u0016J\u0012\u0010\u0017\u001a\u0004\u0018\u00010\rHÆ\u0003¢\u0006\u0004\b\u0017\u0010\u000fJ\u0012\u0010\u0018\u001a\u0004\u0018\u00010\rHÆ\u0003¢\u0006\u0004\b\u0018\u0010\u000fJ\u0012\u0010\u0019\u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0004\b\u0019\u0010\fJ\u0012\u0010\u001a\u001a\u0004\u0018\u00010\rHÆ\u0003¢\u0006\u0004\b\u001a\u0010\u000fJ\u0012\u0010\u001b\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u001b\u0010\u0004J\u0012\u0010\u001c\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u001c\u0010\u0004J\u0012\u0010\u001d\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u001d\u0010\u0004J\u0012\u0010\u001e\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u001e\u0010\u0004J\u0012\u0010\u001f\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u001f\u0010\u0004J\u0012\u0010 \u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b \u0010\u0004J\u0012\u0010!\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b!\u0010\u0004J\u0012\u0010\"\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\"\u0010\u0004J\u0012\u0010#\u001a\u0004\u0018\u00010\rHÆ\u0003¢\u0006\u0004\b#\u0010\u000fJ\u0012\u0010$\u001a\u0004\u0018\u00010\rHÆ\u0003¢\u0006\u0004\b$\u0010\u000fJ\u0012\u0010%\u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0004\b%\u0010\fJ\u0012\u0010&\u001a\u0004\u0018\u00010\u0011HÆ\u0003¢\u0006\u0004\b&\u0010\u0013J\u0012\u0010'\u001a\u0004\u0018\u00010\u0014HÆ\u0003¢\u0006\u0004\b'\u0010\u0016J\u0012\u0010(\u001a\u0004\u0018\u00010\u0011HÆ\u0003¢\u0006\u0004\b(\u0010\u0013J\u0012\u0010)\u001a\u0004\u0018\u00010\u0014HÆ\u0003¢\u0006\u0004\b)\u0010\u0016JÚ\u0002\u0010E\u001a\u00020\u00002\n\b\u0002\u0010*\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010+\u001a\u0004\u0018\u00010\u00022\u0010\b\u0002\u0010,\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00062\n\b\u0002\u0010-\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010.\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010/\u001a\u0004\u0018\u00010\r2\n\b\u0002\u00100\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u00101\u001a\u0004\u0018\u00010\u00142\n\b\u0002\u00102\u001a\u0004\u0018\u00010\r2\n\b\u0002\u00103\u001a\u0004\u0018\u00010\r2\n\b\u0002\u00104\u001a\u0004\u0018\u00010\n2\n\b\u0002\u00105\u001a\u0004\u0018\u00010\r2\n\b\u0002\u00106\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u00107\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u00108\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u00109\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010:\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010;\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010<\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010=\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010>\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010?\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010@\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010A\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010B\u001a\u0004\u0018\u00010\u00142\n\b\u0002\u0010C\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010D\u001a\u0004\u0018\u00010\u0014HÆ\u0001¢\u0006\u0004\bE\u0010FJ\u0010\u0010G\u001a\u00020\nHÖ\u0001¢\u0006\u0004\bG\u0010\fJ\u0010\u0010H\u001a\u00020\rHÖ\u0001¢\u0006\u0004\bH\u0010IJ\u001a\u0010L\u001a\u00020K2\b\u0010J\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\bL\u0010MR$\u0010*\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bN\u0010O\u001a\u0004\bP\u0010\u0004\"\u0004\bQ\u0010RR$\u0010+\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bS\u0010O\u001a\u0004\bT\u0010\u0004\"\u0004\bU\u0010RR*\u0010,\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00068\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bV\u0010W\u001a\u0004\bX\u0010\t\"\u0004\bY\u0010ZR$\u0010-\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b[\u0010\\\u001a\u0004\b]\u0010\f\"\u0004\b^\u0010_R$\u0010.\u001a\u0004\u0018\u00010\r8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b`\u0010a\u001a\u0004\bb\u0010\u000f\"\u0004\bc\u0010dR$\u0010/\u001a\u0004\u0018\u00010\r8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\be\u0010a\u001a\u0004\bf\u0010\u000f\"\u0004\bg\u0010dR$\u00100\u001a\u0004\u0018\u00010\u00118\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bh\u0010i\u001a\u0004\bj\u0010\u0013\"\u0004\bk\u0010lR$\u00101\u001a\u0004\u0018\u00010\u00148\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bm\u0010n\u001a\u0004\bo\u0010\u0016\"\u0004\bp\u0010qR$\u00102\u001a\u0004\u0018\u00010\r8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\br\u0010a\u001a\u0004\bs\u0010\u000f\"\u0004\bt\u0010dR$\u00103\u001a\u0004\u0018\u00010\r8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bu\u0010a\u001a\u0004\bv\u0010\u000f\"\u0004\bw\u0010dR$\u00104\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bx\u0010\\\u001a\u0004\by\u0010\f\"\u0004\bz\u0010_R$\u00105\u001a\u0004\u0018\u00010\r8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b{\u0010a\u001a\u0004\b|\u0010\u000f\"\u0004\b}\u0010dR%\u00106\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0013\n\u0004\b~\u0010O\u001a\u0004\b\u007f\u0010\u0004\"\u0005\b\u0080\u0001\u0010RR'\u00107\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0015\n\u0005\b\u0081\u0001\u0010O\u001a\u0005\b\u0082\u0001\u0010\u0004\"\u0005\b\u0083\u0001\u0010RR'\u00108\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0015\n\u0005\b\u0084\u0001\u0010O\u001a\u0005\b\u0085\u0001\u0010\u0004\"\u0005\b\u0086\u0001\u0010RR'\u00109\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0015\n\u0005\b\u0087\u0001\u0010O\u001a\u0005\b\u0088\u0001\u0010\u0004\"\u0005\b\u0089\u0001\u0010RR'\u0010:\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0015\n\u0005\b\u008a\u0001\u0010O\u001a\u0005\b\u008b\u0001\u0010\u0004\"\u0005\b\u008c\u0001\u0010RR'\u0010;\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0015\n\u0005\b\u008d\u0001\u0010O\u001a\u0005\b\u008e\u0001\u0010\u0004\"\u0005\b\u008f\u0001\u0010RR'\u0010<\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0015\n\u0005\b\u0090\u0001\u0010O\u001a\u0005\b\u0091\u0001\u0010\u0004\"\u0005\b\u0092\u0001\u0010RR'\u0010=\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0015\n\u0005\b\u0093\u0001\u0010O\u001a\u0005\b\u0094\u0001\u0010\u0004\"\u0005\b\u0095\u0001\u0010RR'\u0010>\u001a\u0004\u0018\u00010\r8\u0006@\u0006X\u0086\u000e¢\u0006\u0015\n\u0005\b\u0096\u0001\u0010a\u001a\u0005\b\u0097\u0001\u0010\u000f\"\u0005\b\u0098\u0001\u0010dR'\u0010?\u001a\u0004\u0018\u00010\r8\u0006@\u0006X\u0086\u000e¢\u0006\u0015\n\u0005\b\u0099\u0001\u0010a\u001a\u0005\b\u009a\u0001\u0010\u000f\"\u0005\b\u009b\u0001\u0010dR'\u0010@\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0086\u000e¢\u0006\u0015\n\u0005\b\u009c\u0001\u0010\\\u001a\u0005\b\u009d\u0001\u0010\f\"\u0005\b\u009e\u0001\u0010_R'\u0010A\u001a\u0004\u0018\u00010\u00118\u0006@\u0006X\u0086\u000e¢\u0006\u0015\n\u0005\b\u009f\u0001\u0010i\u001a\u0005\b \u0001\u0010\u0013\"\u0005\b¡\u0001\u0010lR'\u0010B\u001a\u0004\u0018\u00010\u00148\u0006@\u0006X\u0086\u000e¢\u0006\u0015\n\u0005\b¢\u0001\u0010n\u001a\u0005\b£\u0001\u0010\u0016\"\u0005\b¤\u0001\u0010qR'\u0010C\u001a\u0004\u0018\u00010\u00118\u0006@\u0006X\u0086\u000e¢\u0006\u0015\n\u0005\b¥\u0001\u0010i\u001a\u0005\b¦\u0001\u0010\u0013\"\u0005\b§\u0001\u0010lR'\u0010D\u001a\u0004\u0018\u00010\u00148\u0006@\u0006X\u0086\u000e¢\u0006\u0015\n\u0005\b¨\u0001\u0010n\u001a\u0005\b©\u0001\u0010\u0016\"\u0005\bª\u0001\u0010q¨\u0006\u00ad\u0001"}, d2 = {"Lcom/coveiot/android/weathersdk/response/currentweathermodel/CurrentForecastModel;", "", "", "component1", "()Ljava/lang/Double;", "component2", "", "Lcom/coveiot/android/weathersdk/response/currentweathermodel/CurrentWeather;", "component3", "()Ljava/util/List;", "", "component4", "()Ljava/lang/String;", "", "component5", "()Ljava/lang/Integer;", "component6", "", "component7", "()Ljava/lang/Long;", "Ljava/util/Date;", "component8", "()Ljava/util/Date;", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component27", "lon", "lat", "currentWeather", "base", "visibility", "clouds", "dt", "date", "timezone", "id", AppMeasurementSdk.ConditionalUserProperty.NAME, "cod", "temp", "feelsLike", "tempMin", "tempMax", "pressure", "humidity", "windSpeed", "windDeg", "sysType", "sysId", "sysCountry", "sunrise", "sunriseDate", "sunset", "sunsetDate", Constants.COPY_TYPE, "(Ljava/lang/Double;Ljava/lang/Double;Ljava/util/List;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Long;Ljava/util/Date;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Long;Ljava/util/Date;Ljava/lang/Long;Ljava/util/Date;)Lcom/coveiot/android/weathersdk/response/currentweathermodel/CurrentForecastModel;", "toString", "hashCode", "()I", FitnessActivities.OTHER, "", "equals", "(Ljava/lang/Object;)Z", "a", "Ljava/lang/Double;", "getLon", "setLon", "(Ljava/lang/Double;)V", "b", "getLat", "setLat", c.f10260a, "Ljava/util/List;", "getCurrentWeather", "setCurrentWeather", "(Ljava/util/List;)V", "d", "Ljava/lang/String;", "getBase", "setBase", "(Ljava/lang/String;)V", RsaJsonWebKey.EXPONENT_MEMBER_NAME, "Ljava/lang/Integer;", "getVisibility", "setVisibility", "(Ljava/lang/Integer;)V", "f", "getClouds", "setClouds", "g", "Ljava/lang/Long;", "getDt", "setDt", "(Ljava/lang/Long;)V", "h", "Ljava/util/Date;", "getDate", "setDate", "(Ljava/util/Date;)V", "i", "getTimezone", "setTimezone", "j", "getId", "setId", OctetSequenceJsonWebKey.KEY_VALUE_MEMBER_NAME, "getName", "setName", "l", "getCod", "setCod", "m", "getTemp", "setTemp", "n", "getFeelsLike", "setFeelsLike", "o", "getTempMin", "setTempMin", RsaJsonWebKey.FIRST_PRIME_FACTOR_MEMBER_NAME, "getTempMax", "setTempMax", RsaJsonWebKey.SECOND_PRIME_FACTOR_MEMBER_NAME, "getPressure", "setPressure", RsaJsonWebKey.PRIME_FACTOR_OTHER_MEMBER_NAME, "getHumidity", "setHumidity", "s", "getWindSpeed", "setWindSpeed", RsaJsonWebKey.FACTOR_CRT_COEFFICIENT, "getWindDeg", "setWindDeg", "u", "getSysType", "setSysType", CTProductConfigConstants.PRODUCT_CONFIG_JSON_KEY_FOR_VALUE, "getSysId", "setSysId", Constants.INAPP_WINDOW, "getSysCountry", "setSysCountry", "x", "getSunrise", "setSunrise", EllipticCurveJsonWebKey.Y_MEMBER_NAME, "getSunriseDate", "setSunriseDate", "z", "getSunset", "setSunset", ExifInterface.GPS_MEASUREMENT_IN_PROGRESS, "getSunsetDate", "setSunsetDate", "<init>", "(Ljava/lang/Double;Ljava/lang/Double;Ljava/util/List;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Long;Ljava/util/Date;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Long;Ljava/util/Date;Ljava/lang/Long;Ljava/util/Date;)V", "weather_prodRelease"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes8.dex */
public final class CurrentForecastModel {
    @Nullable
    public Date A;
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public Double f6212a;
    @Nullable
    public Double b;
    @Nullable
    public List<CurrentWeather> c;
    @Nullable
    public String d;
    @Nullable
    public Integer e;
    @Nullable
    public Integer f;
    @Nullable
    public Long g;
    @Nullable
    public Date h;
    @Nullable
    public Integer i;
    @Nullable
    public Integer j;
    @Nullable
    public String k;
    @Nullable
    public Integer l;
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
    public Integer u;
    @Nullable
    public Integer v;
    @Nullable
    public String w;
    @Nullable
    public Long x;
    @Nullable
    public Date y;
    @Nullable
    public Long z;

    public CurrentForecastModel() {
        this(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 134217727, null);
    }

    public CurrentForecastModel(@Nullable Double d, @Nullable Double d2, @Nullable List<CurrentWeather> list, @Nullable String str, @Nullable Integer num, @Nullable Integer num2, @Nullable Long l, @Nullable Date date, @Nullable Integer num3, @Nullable Integer num4, @Nullable String str2, @Nullable Integer num5, @Nullable Double d3, @Nullable Double d4, @Nullable Double d5, @Nullable Double d6, @Nullable Double d7, @Nullable Double d8, @Nullable Double d9, @Nullable Double d10, @Nullable Integer num6, @Nullable Integer num7, @Nullable String str3, @Nullable Long l2, @Nullable Date date2, @Nullable Long l3, @Nullable Date date3) {
        this.f6212a = d;
        this.b = d2;
        this.c = list;
        this.d = str;
        this.e = num;
        this.f = num2;
        this.g = l;
        this.h = date;
        this.i = num3;
        this.j = num4;
        this.k = str2;
        this.l = num5;
        this.m = d3;
        this.n = d4;
        this.o = d5;
        this.p = d6;
        this.q = d7;
        this.r = d8;
        this.s = d9;
        this.t = d10;
        this.u = num6;
        this.v = num7;
        this.w = str3;
        this.x = l2;
        this.y = date2;
        this.z = l3;
        this.A = date3;
    }

    public /* synthetic */ CurrentForecastModel(Double d, Double d2, List list, String str, Integer num, Integer num2, Long l, Date date, Integer num3, Integer num4, String str2, Integer num5, Double d3, Double d4, Double d5, Double d6, Double d7, Double d8, Double d9, Double d10, Integer num6, Integer num7, String str3, Long l2, Date date2, Long l3, Date date3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : d, (i & 2) != 0 ? null : d2, (i & 4) != 0 ? null : list, (i & 8) != 0 ? null : str, (i & 16) != 0 ? null : num, (i & 32) != 0 ? null : num2, (i & 64) != 0 ? null : l, (i & 128) != 0 ? null : date, (i & 256) != 0 ? null : num3, (i & 512) != 0 ? null : num4, (i & 1024) != 0 ? null : str2, (i & 2048) != 0 ? null : num5, (i & 4096) != 0 ? null : d3, (i & 8192) != 0 ? null : d4, (i & 16384) != 0 ? null : d5, (i & 32768) != 0 ? null : d6, (i & 65536) != 0 ? null : d7, (i & 131072) != 0 ? null : d8, (i & 262144) != 0 ? null : d9, (i & 524288) != 0 ? null : d10, (i & 1048576) != 0 ? null : num6, (i & 2097152) != 0 ? null : num7, (i & 4194304) != 0 ? null : str3, (i & 8388608) != 0 ? null : l2, (i & 16777216) != 0 ? null : date2, (i & 33554432) != 0 ? null : l3, (i & 67108864) != 0 ? null : date3);
    }

    @Nullable
    public final Double component1() {
        return this.f6212a;
    }

    @Nullable
    public final Integer component10() {
        return this.j;
    }

    @Nullable
    public final String component11() {
        return this.k;
    }

    @Nullable
    public final Integer component12() {
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
    public final Double component2() {
        return this.b;
    }

    @Nullable
    public final Double component20() {
        return this.t;
    }

    @Nullable
    public final Integer component21() {
        return this.u;
    }

    @Nullable
    public final Integer component22() {
        return this.v;
    }

    @Nullable
    public final String component23() {
        return this.w;
    }

    @Nullable
    public final Long component24() {
        return this.x;
    }

    @Nullable
    public final Date component25() {
        return this.y;
    }

    @Nullable
    public final Long component26() {
        return this.z;
    }

    @Nullable
    public final Date component27() {
        return this.A;
    }

    @Nullable
    public final List<CurrentWeather> component3() {
        return this.c;
    }

    @Nullable
    public final String component4() {
        return this.d;
    }

    @Nullable
    public final Integer component5() {
        return this.e;
    }

    @Nullable
    public final Integer component6() {
        return this.f;
    }

    @Nullable
    public final Long component7() {
        return this.g;
    }

    @Nullable
    public final Date component8() {
        return this.h;
    }

    @Nullable
    public final Integer component9() {
        return this.i;
    }

    @NotNull
    public final CurrentForecastModel copy(@Nullable Double d, @Nullable Double d2, @Nullable List<CurrentWeather> list, @Nullable String str, @Nullable Integer num, @Nullable Integer num2, @Nullable Long l, @Nullable Date date, @Nullable Integer num3, @Nullable Integer num4, @Nullable String str2, @Nullable Integer num5, @Nullable Double d3, @Nullable Double d4, @Nullable Double d5, @Nullable Double d6, @Nullable Double d7, @Nullable Double d8, @Nullable Double d9, @Nullable Double d10, @Nullable Integer num6, @Nullable Integer num7, @Nullable String str3, @Nullable Long l2, @Nullable Date date2, @Nullable Long l3, @Nullable Date date3) {
        return new CurrentForecastModel(d, d2, list, str, num, num2, l, date, num3, num4, str2, num5, d3, d4, d5, d6, d7, d8, d9, d10, num6, num7, str3, l2, date2, l3, date3);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof CurrentForecastModel) {
                CurrentForecastModel currentForecastModel = (CurrentForecastModel) obj;
                return Intrinsics.areEqual((Object) this.f6212a, (Object) currentForecastModel.f6212a) && Intrinsics.areEqual((Object) this.b, (Object) currentForecastModel.b) && Intrinsics.areEqual(this.c, currentForecastModel.c) && Intrinsics.areEqual(this.d, currentForecastModel.d) && Intrinsics.areEqual(this.e, currentForecastModel.e) && Intrinsics.areEqual(this.f, currentForecastModel.f) && Intrinsics.areEqual(this.g, currentForecastModel.g) && Intrinsics.areEqual(this.h, currentForecastModel.h) && Intrinsics.areEqual(this.i, currentForecastModel.i) && Intrinsics.areEqual(this.j, currentForecastModel.j) && Intrinsics.areEqual(this.k, currentForecastModel.k) && Intrinsics.areEqual(this.l, currentForecastModel.l) && Intrinsics.areEqual((Object) this.m, (Object) currentForecastModel.m) && Intrinsics.areEqual((Object) this.n, (Object) currentForecastModel.n) && Intrinsics.areEqual((Object) this.o, (Object) currentForecastModel.o) && Intrinsics.areEqual((Object) this.p, (Object) currentForecastModel.p) && Intrinsics.areEqual((Object) this.q, (Object) currentForecastModel.q) && Intrinsics.areEqual((Object) this.r, (Object) currentForecastModel.r) && Intrinsics.areEqual((Object) this.s, (Object) currentForecastModel.s) && Intrinsics.areEqual((Object) this.t, (Object) currentForecastModel.t) && Intrinsics.areEqual(this.u, currentForecastModel.u) && Intrinsics.areEqual(this.v, currentForecastModel.v) && Intrinsics.areEqual(this.w, currentForecastModel.w) && Intrinsics.areEqual(this.x, currentForecastModel.x) && Intrinsics.areEqual(this.y, currentForecastModel.y) && Intrinsics.areEqual(this.z, currentForecastModel.z) && Intrinsics.areEqual(this.A, currentForecastModel.A);
            }
            return false;
        }
        return true;
    }

    @Nullable
    public final String getBase() {
        return this.d;
    }

    @Nullable
    public final Integer getClouds() {
        return this.f;
    }

    @Nullable
    public final Integer getCod() {
        return this.l;
    }

    @Nullable
    public final List<CurrentWeather> getCurrentWeather() {
        return this.c;
    }

    @Nullable
    public final Date getDate() {
        return this.h;
    }

    @Nullable
    public final Long getDt() {
        return this.g;
    }

    @Nullable
    public final Double getFeelsLike() {
        return this.n;
    }

    @Nullable
    public final Double getHumidity() {
        return this.r;
    }

    @Nullable
    public final Integer getId() {
        return this.j;
    }

    @Nullable
    public final Double getLat() {
        return this.b;
    }

    @Nullable
    public final Double getLon() {
        return this.f6212a;
    }

    @Nullable
    public final String getName() {
        return this.k;
    }

    @Nullable
    public final Double getPressure() {
        return this.q;
    }

    @Nullable
    public final Long getSunrise() {
        return this.x;
    }

    @Nullable
    public final Date getSunriseDate() {
        return this.y;
    }

    @Nullable
    public final Long getSunset() {
        return this.z;
    }

    @Nullable
    public final Date getSunsetDate() {
        return this.A;
    }

    @Nullable
    public final String getSysCountry() {
        return this.w;
    }

    @Nullable
    public final Integer getSysId() {
        return this.v;
    }

    @Nullable
    public final Integer getSysType() {
        return this.u;
    }

    @Nullable
    public final Double getTemp() {
        return this.m;
    }

    @Nullable
    public final Double getTempMax() {
        return this.p;
    }

    @Nullable
    public final Double getTempMin() {
        return this.o;
    }

    @Nullable
    public final Integer getTimezone() {
        return this.i;
    }

    @Nullable
    public final Integer getVisibility() {
        return this.e;
    }

    @Nullable
    public final Double getWindDeg() {
        return this.t;
    }

    @Nullable
    public final Double getWindSpeed() {
        return this.s;
    }

    public int hashCode() {
        Double d = this.f6212a;
        int hashCode = (d != null ? d.hashCode() : 0) * 31;
        Double d2 = this.b;
        int hashCode2 = (hashCode + (d2 != null ? d2.hashCode() : 0)) * 31;
        List<CurrentWeather> list = this.c;
        int hashCode3 = (hashCode2 + (list != null ? list.hashCode() : 0)) * 31;
        String str = this.d;
        int hashCode4 = (hashCode3 + (str != null ? str.hashCode() : 0)) * 31;
        Integer num = this.e;
        int hashCode5 = (hashCode4 + (num != null ? num.hashCode() : 0)) * 31;
        Integer num2 = this.f;
        int hashCode6 = (hashCode5 + (num2 != null ? num2.hashCode() : 0)) * 31;
        Long l = this.g;
        int hashCode7 = (hashCode6 + (l != null ? l.hashCode() : 0)) * 31;
        Date date = this.h;
        int hashCode8 = (hashCode7 + (date != null ? date.hashCode() : 0)) * 31;
        Integer num3 = this.i;
        int hashCode9 = (hashCode8 + (num3 != null ? num3.hashCode() : 0)) * 31;
        Integer num4 = this.j;
        int hashCode10 = (hashCode9 + (num4 != null ? num4.hashCode() : 0)) * 31;
        String str2 = this.k;
        int hashCode11 = (hashCode10 + (str2 != null ? str2.hashCode() : 0)) * 31;
        Integer num5 = this.l;
        int hashCode12 = (hashCode11 + (num5 != null ? num5.hashCode() : 0)) * 31;
        Double d3 = this.m;
        int hashCode13 = (hashCode12 + (d3 != null ? d3.hashCode() : 0)) * 31;
        Double d4 = this.n;
        int hashCode14 = (hashCode13 + (d4 != null ? d4.hashCode() : 0)) * 31;
        Double d5 = this.o;
        int hashCode15 = (hashCode14 + (d5 != null ? d5.hashCode() : 0)) * 31;
        Double d6 = this.p;
        int hashCode16 = (hashCode15 + (d6 != null ? d6.hashCode() : 0)) * 31;
        Double d7 = this.q;
        int hashCode17 = (hashCode16 + (d7 != null ? d7.hashCode() : 0)) * 31;
        Double d8 = this.r;
        int hashCode18 = (hashCode17 + (d8 != null ? d8.hashCode() : 0)) * 31;
        Double d9 = this.s;
        int hashCode19 = (hashCode18 + (d9 != null ? d9.hashCode() : 0)) * 31;
        Double d10 = this.t;
        int hashCode20 = (hashCode19 + (d10 != null ? d10.hashCode() : 0)) * 31;
        Integer num6 = this.u;
        int hashCode21 = (hashCode20 + (num6 != null ? num6.hashCode() : 0)) * 31;
        Integer num7 = this.v;
        int hashCode22 = (hashCode21 + (num7 != null ? num7.hashCode() : 0)) * 31;
        String str3 = this.w;
        int hashCode23 = (hashCode22 + (str3 != null ? str3.hashCode() : 0)) * 31;
        Long l2 = this.x;
        int hashCode24 = (hashCode23 + (l2 != null ? l2.hashCode() : 0)) * 31;
        Date date2 = this.y;
        int hashCode25 = (hashCode24 + (date2 != null ? date2.hashCode() : 0)) * 31;
        Long l3 = this.z;
        int hashCode26 = (hashCode25 + (l3 != null ? l3.hashCode() : 0)) * 31;
        Date date3 = this.A;
        return hashCode26 + (date3 != null ? date3.hashCode() : 0);
    }

    public final void setBase(@Nullable String str) {
        this.d = str;
    }

    public final void setClouds(@Nullable Integer num) {
        this.f = num;
    }

    public final void setCod(@Nullable Integer num) {
        this.l = num;
    }

    public final void setCurrentWeather(@Nullable List<CurrentWeather> list) {
        this.c = list;
    }

    public final void setDate(@Nullable Date date) {
        this.h = date;
    }

    public final void setDt(@Nullable Long l) {
        this.g = l;
    }

    public final void setFeelsLike(@Nullable Double d) {
        this.n = d;
    }

    public final void setHumidity(@Nullable Double d) {
        this.r = d;
    }

    public final void setId(@Nullable Integer num) {
        this.j = num;
    }

    public final void setLat(@Nullable Double d) {
        this.b = d;
    }

    public final void setLon(@Nullable Double d) {
        this.f6212a = d;
    }

    public final void setName(@Nullable String str) {
        this.k = str;
    }

    public final void setPressure(@Nullable Double d) {
        this.q = d;
    }

    public final void setSunrise(@Nullable Long l) {
        this.x = l;
    }

    public final void setSunriseDate(@Nullable Date date) {
        this.y = date;
    }

    public final void setSunset(@Nullable Long l) {
        this.z = l;
    }

    public final void setSunsetDate(@Nullable Date date) {
        this.A = date;
    }

    public final void setSysCountry(@Nullable String str) {
        this.w = str;
    }

    public final void setSysId(@Nullable Integer num) {
        this.v = num;
    }

    public final void setSysType(@Nullable Integer num) {
        this.u = num;
    }

    public final void setTemp(@Nullable Double d) {
        this.m = d;
    }

    public final void setTempMax(@Nullable Double d) {
        this.p = d;
    }

    public final void setTempMin(@Nullable Double d) {
        this.o = d;
    }

    public final void setTimezone(@Nullable Integer num) {
        this.i = num;
    }

    public final void setVisibility(@Nullable Integer num) {
        this.e = num;
    }

    public final void setWindDeg(@Nullable Double d) {
        this.t = d;
    }

    public final void setWindSpeed(@Nullable Double d) {
        this.s = d;
    }

    @NotNull
    public String toString() {
        return "CurrentForecastModel(lon=" + this.f6212a + ", lat=" + this.b + ", currentWeather=" + this.c + ", base=" + this.d + ", visibility=" + this.e + ", clouds=" + this.f + ", dt=" + this.g + ", date=" + this.h + ", timezone=" + this.i + ", id=" + this.j + ", name=" + this.k + ", cod=" + this.l + ", temp=" + this.m + ", feelsLike=" + this.n + ", tempMin=" + this.o + ", tempMax=" + this.p + ", pressure=" + this.q + ", humidity=" + this.r + ", windSpeed=" + this.s + ", windDeg=" + this.t + ", sysType=" + this.u + ", sysId=" + this.v + ", sysCountry=" + this.w + ", sunrise=" + this.x + ", sunriseDate=" + this.y + ", sunset=" + this.z + ", sunsetDate=" + this.A + ")";
    }
}
