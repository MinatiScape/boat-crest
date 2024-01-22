package com.github.anastr.speedviewlib;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.InputDeviceCompat;
import androidx.core.view.ViewCompat;
import androidx.exifinterface.media.ExifInterface;
import com.clevertap.android.sdk.Constants;
import com.coveiot.android.fitnesschallenges.utils.FitnessChallengeConstants;
import com.coveiot.android.leonardo.utils.MusicConstants;
import com.github.anastr.speedviewlib.components.Section;
import com.github.anastr.speedviewlib.components.Style;
import com.github.anastr.speedviewlib.util.UtilsKt;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.mappls.sdk.maps.style.layers.Property;
import com.mappls.sdk.services.api.weather.WeatherCriteria;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.TypeIntrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jose4j.jwk.EllipticCurveJsonWebKey;
import org.jose4j.jwk.RsaJsonWebKey;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Þ\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b'\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b#\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b$\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b&\u0018\u00002\u00020\u00012\u00020\u0002:\u0002£\u0002B,\u0012\b\u0010\u009d\u0002\u001a\u00030\u009c\u0002\u0012\f\b\u0002\u0010\u009f\u0002\u001a\u0005\u0018\u00010\u009e\u0002\u0012\t\b\u0002\u0010 \u0002\u001a\u00020\u0006¢\u0006\u0006\b¡\u0002\u0010¢\u0002J\b\u0010\u0004\u001a\u00020\u0003H\u0002J\b\u0010\u0005\u001a\u00020\u0003H\u0002J(\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u0006H\u0014J\u0017\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\rH\u0000¢\u0006\u0004\b\u000f\u0010\u0010J\u000e\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u0003J\u000e\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u0003J\b\u0010\u0016\u001a\u00020\u000bH$J\b\u0010\u0017\u001a\u00020\u000bH$J\b\u0010\u0019\u001a\u00020\u0018H\u0004J\b\u0010\u001b\u001a\u00020\u001aH\u0004J\u0006\u0010\u001c\u001a\u00020\u0003J\u0006\u0010\u001d\u001a\u00020\u0003J\u000f\u0010 \u001a\u00020\u000bH\u0000¢\u0006\u0004\b\u001e\u0010\u001fJ\u000f\u0010\"\u001a\u00020\u000bH\u0000¢\u0006\u0004\b!\u0010\u001fJ\u0010\u0010%\u001a\u00020\u000b2\u0006\u0010$\u001a\u00020#H\u0014J\u0010\u0010&\u001a\u00020\u000b2\u0006\u0010$\u001a\u00020#H\u0004J\b\u0010'\u001a\u00020#H\u0014J\u001c\u0010*\u001a\u00020\u000b2\b\u0010(\u001a\u0004\u0018\u00010\r2\b\u0010)\u001a\u0004\u0018\u00010\rH\u0004J\u0006\u0010+\u001a\u00020\u000bJ\b\u0010,\u001a\u00020\u000bH\u0004J\u000e\u0010.\u001a\u00020\u000b2\u0006\u0010-\u001a\u00020\u0003J\u001a\u00102\u001a\u00020\u000b2\u0006\u0010/\u001a\u00020\u00062\b\b\u0002\u00101\u001a\u000200H\u0007J\u001a\u00103\u001a\u00020\u000b2\u0006\u0010-\u001a\u00020\u00032\b\b\u0002\u00101\u001a\u000200H\u0007J\u0006\u00104\u001a\u00020\u000bJ\u0006\u00105\u001a\u00020\u000bJ\u000e\u00106\u001a\u00020\u000b2\u0006\u0010/\u001a\u00020\u0003J\u000e\u00107\u001a\u00020\u000b2\u0006\u0010-\u001a\u00020\u0003J\b\u00108\u001a\u00020\u000bH\u0004J\u0010\u0010;\u001a\u00020\u000b2\u0006\u0010:\u001a\u000209H\u0016J\b\u0010<\u001a\u00020\u000bH\u0016J\b\u0010=\u001a\u00020\u000bH\u0014J\b\u0010>\u001a\u00020\u000bH\u0014J\u0016\u0010A\u001a\u00020\u000b2\u0006\u0010?\u001a\u00020\u00032\u0006\u0010@\u001a\u00020\u0006J\u0016\u0010D\u001a\u00020\u000b2\u0006\u0010B\u001a\u00020\u00032\u0006\u0010C\u001a\u00020\u0003J!\u0010G\u001a\u00020\u000b2\u0012\u0010F\u001a\n\u0012\u0006\b\u0001\u0012\u00020\r0E\"\u00020\r¢\u0006\u0004\bG\u0010HJ\u0014\u0010G\u001a\u00020\u000b2\f\u0010F\u001a\b\u0012\u0004\u0012\u00020\r0IJ\u001e\u0010N\u001a\u00020\u000b2\u0006\u0010J\u001a\u00020\u00062\u0006\u0010K\u001a\u00020\u00062\u0006\u0010M\u001a\u00020LJ\u0010\u0010O\u001a\u00020\u000b2\b\u0010\u000e\u001a\u0004\u0018\u00010\rJ\u0006\u0010P\u001a\u00020\u000bJ\u001c\u0010T\u001a\u00020\u000b2\b\u0010\u000e\u001a\u0004\u0018\u00010Q2\b\u0010S\u001a\u0004\u0018\u00010RH\u0016J(\u0010Y\u001a\u00020\u000b2\u0006\u0010U\u001a\u00020\u00062\u0006\u0010V\u001a\u00020\u00062\u0006\u0010W\u001a\u00020\u00062\u0006\u0010X\u001a\u00020\u0006H\u0016J(\u0010\\\u001a\u00020\u000b2\u0006\u0010Z\u001a\u00020\u00062\u0006\u0010V\u001a\u00020\u00062\u0006\u0010[\u001a\u00020\u00062\u0006\u0010X\u001a\u00020\u0006H\u0016J\b\u0010]\u001a\u000209H\u0016J\u0006\u0010^\u001a\u00020\u000bR\"\u0010f\u001a\u00020_8\u0004@\u0004X\u0084\u000e¢\u0006\u0012\n\u0004\b`\u0010a\u001a\u0004\bb\u0010c\"\u0004\bd\u0010eR*\u0010h\u001a\u00020g2\u0006\u0010h\u001a\u00020g8\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\bi\u0010j\u001a\u0004\bk\u0010l\"\u0004\bm\u0010nR*\u0010o\u001a\u0002092\u0006\u0010o\u001a\u0002098\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\bp\u0010q\u001a\u0004\br\u0010s\"\u0004\bt\u0010uR$\u0010-\u001a\u00020\u00032\u0006\u0010v\u001a\u00020\u00038\u0006@BX\u0086\u000e¢\u0006\f\n\u0004\bw\u0010x\u001a\u0004\by\u0010zR$\u0010\u007f\u001a\u00020\u00062\u0006\u0010v\u001a\u00020\u00068\u0006@BX\u0086\u000e¢\u0006\f\n\u0004\b{\u0010|\u001a\u0004\b}\u0010~R0\u0010\u0085\u0001\u001a\u00020\u00032\u0007\u0010\u0080\u0001\u001a\u00020\u00038\u0006@BX\u0086\u000e¢\u0006\u0016\n\u0005\b\u0081\u0001\u0010x\u001a\u0005\b\u0082\u0001\u0010z\"\u0006\b\u0083\u0001\u0010\u0084\u0001R'\u0010\u0087\u0001\u001a\u0002092\u0006\u0010v\u001a\u0002098\u0006@BX\u0086\u000e¢\u0006\u000e\n\u0005\b\u0086\u0001\u0010q\u001a\u0005\b\u0087\u0001\u0010sR.\u0010?\u001a\u00020\u00032\u0006\u0010?\u001a\u00020\u00038\u0006@FX\u0086\u000e¢\u0006\u0016\n\u0005\b\u0088\u0001\u0010x\u001a\u0005\b\u0089\u0001\u0010z\"\u0006\b\u008a\u0001\u0010\u0084\u0001R.\u0010@\u001a\u00020\u00062\u0006\u0010@\u001a\u00020\u00068\u0006@FX\u0086\u000e¢\u0006\u0016\n\u0005\b\u008b\u0001\u0010|\u001a\u0005\b\u008c\u0001\u0010~\"\u0006\b\u008d\u0001\u0010\u008e\u0001R\u0081\u0001\u0010\u009c\u0001\u001aZ\u0012\u0016\u0012\u00140\u0000¢\u0006\u000f\b\u0090\u0001\u0012\n\b\u0091\u0001\u0012\u0005\b\b(\u0092\u0001\u0012\u0016\u0012\u001409¢\u0006\u000f\b\u0090\u0001\u0012\n\b\u0091\u0001\u0012\u0005\b\b(\u0093\u0001\u0012\u0016\u0012\u001409¢\u0006\u000f\b\u0090\u0001\u0012\n\b\u0091\u0001\u0012\u0005\b\b(\u0094\u0001\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u008f\u0001j\u0005\u0018\u0001`\u0095\u00018\u0006@\u0006X\u0086\u000e¢\u0006\u0018\n\u0006\b\u0096\u0001\u0010\u0097\u0001\u001a\u0006\b\u0098\u0001\u0010\u0099\u0001\"\u0006\b\u009a\u0001\u0010\u009b\u0001Rk\u0010¥\u0001\u001aD\u0012\u0017\u0012\u0015\u0018\u00010\r¢\u0006\u000e\b\u0090\u0001\u0012\t\b\u0091\u0001\u0012\u0004\b\b((\u0012\u0017\u0012\u0015\u0018\u00010\r¢\u0006\u000e\b\u0090\u0001\u0012\t\b\u0091\u0001\u0012\u0004\b\b()\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u009d\u0001j\u0005\u0018\u0001`\u009e\u00018\u0006@\u0006X\u0086\u000e¢\u0006\u0018\n\u0006\b\u009f\u0001\u0010 \u0001\u001a\u0006\b¡\u0001\u0010¢\u0001\"\u0006\b£\u0001\u0010¤\u0001R*\u0010\u00ad\u0001\u001a\u00030¦\u00018\u0004@\u0004X\u0084\u000e¢\u0006\u0018\n\u0006\b§\u0001\u0010¨\u0001\u001a\u0006\b©\u0001\u0010ª\u0001\"\u0006\b«\u0001\u0010¬\u0001R'\u0010°\u0001\u001a\u00020\u00062\u0006\u0010v\u001a\u00020\u00068\u0006@BX\u0086\u000e¢\u0006\u000e\n\u0005\b®\u0001\u0010|\u001a\u0005\b¯\u0001\u0010~R&\u0010²\u0001\u001a\u00020\u00062\u0006\u0010v\u001a\u00020\u00068\u0006@BX\u0086\u000e¢\u0006\r\n\u0004\bx\u0010|\u001a\u0005\b±\u0001\u0010~R'\u0010µ\u0001\u001a\u00020\u00062\u0006\u0010v\u001a\u00020\u00068\u0006@BX\u0086\u000e¢\u0006\u000e\n\u0005\b³\u0001\u0010|\u001a\u0005\b´\u0001\u0010~R,\u0010¹\u0001\u001a\u0004\u0018\u00010\r2\b\u0010v\u001a\u0004\u0018\u00010\r8\u0006@BX\u0086\u000e¢\u0006\u000f\n\u0005\b|\u0010¶\u0001\u001a\u0006\b·\u0001\u0010¸\u0001R0\u0010º\u0001\u001a\u00020\u00032\u0007\u0010º\u0001\u001a\u00020\u00038\u0016@VX\u0096\u000e¢\u0006\u0016\n\u0005\b»\u0001\u0010x\u001a\u0005\b¼\u0001\u0010z\"\u0006\b½\u0001\u0010\u0084\u0001R/\u0010¾\u0001\u001a\u0002092\u0007\u0010¾\u0001\u001a\u0002098\u0006@FX\u0086\u000e¢\u0006\u0015\n\u0005\b¿\u0001\u0010q\u001a\u0005\bÀ\u0001\u0010s\"\u0005\bÁ\u0001\u0010uR'\u0010Å\u0001\u001a\u00020\u00038\u0004@\u0004X\u0084\u000e¢\u0006\u0016\n\u0005\bÂ\u0001\u0010x\u001a\u0005\bÃ\u0001\u0010z\"\u0006\bÄ\u0001\u0010\u0084\u0001R'\u0010É\u0001\u001a\u00020\u00038\u0004@\u0004X\u0084\u000e¢\u0006\u0016\n\u0005\bÆ\u0001\u0010x\u001a\u0005\bÇ\u0001\u0010z\"\u0006\bÈ\u0001\u0010\u0084\u0001R4\u0010Ë\u0001\u001a\u00030Ê\u00012\b\u0010Ë\u0001\u001a\u00030Ê\u00018\u0006@FX\u0086\u000e¢\u0006\u0018\n\u0006\bÌ\u0001\u0010Í\u0001\u001a\u0006\bÎ\u0001\u0010Ï\u0001\"\u0006\bÐ\u0001\u0010Ñ\u0001R0\u0010Ò\u0001\u001a\u00020\u00032\u0007\u0010Ò\u0001\u001a\u00020\u00038\u0006@FX\u0086\u000e¢\u0006\u0016\n\u0005\bÓ\u0001\u0010x\u001a\u0005\bÔ\u0001\u0010z\"\u0006\bÕ\u0001\u0010\u0084\u0001R0\u0010Ö\u0001\u001a\u00020\u00032\u0007\u0010Ö\u0001\u001a\u00020\u00038\u0006@FX\u0086\u000e¢\u0006\u0016\n\u0005\b×\u0001\u0010x\u001a\u0005\bØ\u0001\u0010z\"\u0006\bÙ\u0001\u0010\u0084\u0001R4\u0010Û\u0001\u001a\u00030Ú\u00012\b\u0010Û\u0001\u001a\u00030Ú\u00018\u0006@FX\u0086\u000e¢\u0006\u0018\n\u0006\bÜ\u0001\u0010Ý\u0001\u001a\u0006\bÞ\u0001\u0010ß\u0001\"\u0006\bà\u0001\u0010á\u0001R)\u0010â\u0001\u001a\u00020\u00032\u0007\u0010â\u0001\u001a\u00020\u00038\u0002@BX\u0082\u000e¢\u0006\u000f\n\u0005\bã\u0001\u0010x\"\u0006\bä\u0001\u0010\u0084\u0001R)\u0010å\u0001\u001a\u00020\u00032\u0007\u0010å\u0001\u001a\u00020\u00038\u0002@BX\u0082\u000e¢\u0006\u000f\n\u0005\bæ\u0001\u0010x\"\u0006\bç\u0001\u0010\u0084\u0001R/\u0010è\u0001\u001a\u0002092\u0007\u0010è\u0001\u001a\u0002098\u0006@FX\u0086\u000e¢\u0006\u0015\n\u0005\bé\u0001\u0010q\u001a\u0005\bê\u0001\u0010s\"\u0005\bë\u0001\u0010uRx\u0010õ\u0001\u001a%\u0012\u0015\u0012\u00130\u0003¢\u0006\u000e\b\u0090\u0001\u0012\t\b\u0091\u0001\u0012\u0004\b\b(-\u0012\u0004\u0012\u00020\u001a0ì\u0001j\u0003`í\u00012*\u0010î\u0001\u001a%\u0012\u0015\u0012\u00130\u0003¢\u0006\u000e\b\u0090\u0001\u0012\t\b\u0091\u0001\u0012\u0004\b\b(-\u0012\u0004\u0012\u00020\u001a0ì\u0001j\u0003`í\u00018\u0006@FX\u0086\u000e¢\u0006\u0018\n\u0006\bï\u0001\u0010ð\u0001\u001a\u0006\bñ\u0001\u0010ò\u0001\"\u0006\bó\u0001\u0010ô\u0001R(\u0010B\u001a\u00020\u00032\u0007\u0010\u0080\u0001\u001a\u00020\u00038F@FX\u0086\u000e¢\u0006\u000f\u001a\u0005\bö\u0001\u0010z\"\u0006\b÷\u0001\u0010\u0084\u0001R(\u0010C\u001a\u00020\u00032\u0007\u0010\u0080\u0001\u001a\u00020\u00038F@FX\u0086\u000e¢\u0006\u000f\u001a\u0005\bø\u0001\u0010z\"\u0006\bù\u0001\u0010\u0084\u0001R\u001b\u0010F\u001a\b\u0012\u0004\u0012\u00020\r0I8F@\u0006¢\u0006\b\u001a\u0006\bú\u0001\u0010û\u0001R)\u0010ü\u0001\u001a\u00020\u00062\u0007\u0010ü\u0001\u001a\u00020\u00068F@FX\u0086\u000e¢\u0006\u000f\u001a\u0005\bý\u0001\u0010~\"\u0006\bþ\u0001\u0010\u008e\u0001R)\u0010ÿ\u0001\u001a\u00020\u00062\u0007\u0010ÿ\u0001\u001a\u00020\u00068F@FX\u0086\u000e¢\u0006\u000f\u001a\u0005\b\u0080\u0002\u0010~\"\u0006\b\u0081\u0002\u0010\u008e\u0001R)\u0010\u0082\u0002\u001a\u00020\u00062\u0007\u0010\u0082\u0002\u001a\u00020\u00068F@FX\u0086\u000e¢\u0006\u000f\u001a\u0005\b\u0083\u0002\u0010~\"\u0006\b\u0084\u0002\u0010\u008e\u0001R)\u0010\u0085\u0002\u001a\u00020\u00032\u0007\u0010\u0085\u0002\u001a\u00020\u00038F@FX\u0086\u000e¢\u0006\u000f\u001a\u0005\b\u0086\u0002\u0010z\"\u0006\b\u0087\u0002\u0010\u0084\u0001R)\u0010\u0088\u0002\u001a\u00020\u00032\u0007\u0010\u0088\u0002\u001a\u00020\u00038F@FX\u0086\u000e¢\u0006\u000f\u001a\u0005\b\u0089\u0002\u0010z\"\u0006\b\u008a\u0002\u0010\u0084\u0001R)\u0010\u008b\u0002\u001a\u00020\u00032\u0007\u0010\u008b\u0002\u001a\u00020\u00038F@FX\u0086\u000e¢\u0006\u000f\u001a\u0005\b\u008c\u0002\u0010z\"\u0006\b\u008d\u0002\u0010\u0084\u0001R\u0015\u0010\u008f\u0002\u001a\u00020\u00068F@\u0006¢\u0006\u0007\u001a\u0005\b\u008e\u0002\u0010~R\u0015\u0010\u0091\u0002\u001a\u00020\u00068F@\u0006¢\u0006\u0007\u001a\u0005\b\u0090\u0002\u0010~R0\u0010\u0098\u0002\u001a\u0005\u0018\u00010\u0092\u00022\n\u0010\u0093\u0002\u001a\u0005\u0018\u00010\u0092\u00028F@FX\u0086\u000e¢\u0006\u0010\u001a\u0006\b\u0094\u0002\u0010\u0095\u0002\"\u0006\b\u0096\u0002\u0010\u0097\u0002R0\u0010\u009b\u0002\u001a\u0005\u0018\u00010\u0092\u00022\n\u0010\u0093\u0002\u001a\u0005\u0018\u00010\u0092\u00028F@FX\u0086\u000e¢\u0006\u0010\u001a\u0006\b\u0099\u0002\u0010\u0095\u0002\"\u0006\b\u009a\u0002\u0010\u0097\u0002¨\u0006¤\u0002"}, d2 = {"Lcom/github/anastr/speedviewlib/Gauge;", "Landroid/view/View;", "Ljava/util/Observer;", "", "getSpeedUnitTextWidth", "getSpeedUnitTextHeight", "", Constants.INAPP_WINDOW, "h", "oldW", "oldH", "", "onSizeChanged", "Lcom/github/anastr/speedviewlib/components/Section;", "section", "checkSection$speedviewlib_release", "(Lcom/github/anastr/speedviewlib/components/Section;)V", "checkSection", RsaJsonWebKey.FIRST_FACTOR_CRT_EXPONENT_MEMBER_NAME, "dpTOpx", "px", "pxTOdp", "defaultGaugeValues", "updateBackgroundBitmap", "Landroid/graphics/RectF;", "getSpeedUnitTextBounds", "", "getSpeedText", "getPercentSpeed", "getOffsetSpeed", "checkSpeedIntChange$speedviewlib_release", "()V", "checkSpeedIntChange", "checkSectionChange$speedviewlib_release", "checkSectionChange", "Landroid/graphics/Canvas;", "canvas", "onDraw", "drawSpeedUnitText", "createBackgroundBitmapCanvas", "previousSection", "newSection", "onSectionChangeEvent", MusicConstants.CMDSTOP, "cancelSpeedAnimator", "speed", "setSpeedAt", "percent", "", "moveDuration", "speedPercentTo", "speedTo", "speedUp", "slowDown", "realSpeedPercentTo", "realSpeedTo", "tremble", "", "isVisible", "onVisibilityAggregated", "jumpDrawablesToCurrentState", "onAttachedToWindow", "onDetachedFromWindow", "trembleDegree", "trembleDuration", "setTrembleData", "minSpeed", "maxSpeed", "setMinMaxSpeed", "", "sections", "addSections", "([Lcom/github/anastr/speedviewlib/components/Section;)V", "", "numberOfSections", "color", "Lcom/github/anastr/speedviewlib/components/Style;", "style", "makeSections", "removeSection", "clearSections", "Ljava/util/Observable;", "", "isPercentChanged", "update", "left", "top", "right", "bottom", "setPadding", "start", "end", "setPaddingRelative", "isAttachedToWindow", "invalidateGauge", "Landroid/text/TextPaint;", "i", "Landroid/text/TextPaint;", "getTextPaint", "()Landroid/text/TextPaint;", "setTextPaint", "(Landroid/text/TextPaint;)V", "textPaint", "", "unit", "l", "Ljava/lang/String;", "getUnit", "()Ljava/lang/String;", "setUnit", "(Ljava/lang/String;)V", "withTremble", "m", "Z", "getWithTremble", "()Z", "setWithTremble", "(Z)V", "<set-?>", RsaJsonWebKey.FIRST_PRIME_FACTOR_MEMBER_NAME, WeatherCriteria.UNIT_FARENHEIT, "getSpeed", "()F", RsaJsonWebKey.SECOND_PRIME_FACTOR_MEMBER_NAME, "I", "getCurrentIntSpeed", "()I", "currentIntSpeed", "value", RsaJsonWebKey.PRIME_FACTOR_OTHER_MEMBER_NAME, "getCurrentSpeed", "setCurrentSpeed", "(F)V", "currentSpeed", "s", "isSpeedIncrease", RsaJsonWebKey.FACTOR_CRT_COEFFICIENT, "getTrembleDegree", "setTrembleDegree", "u", "getTrembleDuration", "setTrembleDuration", "(I)V", "Lkotlin/Function3;", "Lkotlin/ParameterName;", AppMeasurementSdk.ConditionalUserProperty.NAME, "gauge", "isSpeedUp", "isByTremble", "Lcom/github/anastr/speedviewlib/util/OnSpeedChangeListener;", "z", "Lkotlin/jvm/functions/Function3;", "getOnSpeedChangeListener", "()Lkotlin/jvm/functions/Function3;", "setOnSpeedChangeListener", "(Lkotlin/jvm/functions/Function3;)V", "onSpeedChangeListener", "Lkotlin/Function2;", "Lcom/github/anastr/speedviewlib/util/OnSectionChangeListener;", ExifInterface.GPS_MEASUREMENT_IN_PROGRESS, "Lkotlin/jvm/functions/Function2;", "getOnSectionChangeListener", "()Lkotlin/jvm/functions/Function2;", "setOnSectionChangeListener", "(Lkotlin/jvm/functions/Function2;)V", "onSectionChangeListener", "Landroid/graphics/Bitmap;", WeatherCriteria.UNIT_CELSIUS, "Landroid/graphics/Bitmap;", "getBackgroundBitmap", "()Landroid/graphics/Bitmap;", "setBackgroundBitmap", "(Landroid/graphics/Bitmap;)V", "backgroundBitmap", ExifInterface.LONGITUDE_EAST, "getPadding", "padding", "getWidthPa", "widthPa", "G", "getHeightPa", "heightPa", "Lcom/github/anastr/speedviewlib/components/Section;", "getCurrentSection", "()Lcom/github/anastr/speedviewlib/components/Section;", "currentSection", "speedometerWidth", "J", "getSpeedometerWidth", "setSpeedometerWidth", "speedometerTextRightToLeft", "K", "getSpeedometerTextRightToLeft", "setSpeedometerTextRightToLeft", "M", "getTranslatedDx", "setTranslatedDx", "translatedDx", "N", "getTranslatedDy", "setTranslatedDy", "translatedDy", "Ljava/util/Locale;", "locale", "O", "Ljava/util/Locale;", "getLocale", "()Ljava/util/Locale;", "setLocale", "(Ljava/util/Locale;)V", "accelerate", "P", "getAccelerate", "setAccelerate", "decelerate", "Q", "getDecelerate", "setDecelerate", "Lcom/github/anastr/speedviewlib/Gauge$Position;", "speedTextPosition", "R", "Lcom/github/anastr/speedviewlib/Gauge$Position;", "getSpeedTextPosition", "()Lcom/github/anastr/speedviewlib/Gauge$Position;", "setSpeedTextPosition", "(Lcom/github/anastr/speedviewlib/Gauge$Position;)V", "unitSpeedInterval", ExifInterface.LATITUDE_SOUTH, "setUnitSpeedInterval", "speedTextPadding", ExifInterface.GPS_DIRECTION_TRUE, "setSpeedTextPadding", "unitUnderSpeedText", "U", "getUnitUnderSpeedText", "setUnitUnderSpeedText", "Lkotlin/Function1;", "Lcom/github/anastr/speedviewlib/SpeedTextListener;", "speedTextFormat", "a0", "Lkotlin/jvm/functions/Function1;", "getSpeedTextListener", "()Lkotlin/jvm/functions/Function1;", "setSpeedTextListener", "(Lkotlin/jvm/functions/Function1;)V", "speedTextListener", "getMinSpeed", "setMinSpeed", "getMaxSpeed", "setMaxSpeed", "getSections", "()Ljava/util/List;", "textColor", "getTextColor", "setTextColor", "speedTextColor", "getSpeedTextColor", "setSpeedTextColor", "unitTextColor", "getUnitTextColor", "setUnitTextColor", "textSize", "getTextSize", "setTextSize", "speedTextSize", "getSpeedTextSize", "setSpeedTextSize", "unitTextSize", "getUnitTextSize", "setUnitTextSize", "getViewSize", "viewSize", "getViewSizePa", "viewSizePa", "Landroid/graphics/Typeface;", "typeface", "getSpeedTextTypeface", "()Landroid/graphics/Typeface;", "setSpeedTextTypeface", "(Landroid/graphics/Typeface;)V", "speedTextTypeface", "getTextTypeface", "setTextTypeface", "textTypeface", "Landroid/content/Context;", "context", "Landroid/util/AttributeSet;", "attrs", "defStyleAttr", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "Position", "speedviewlib_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes9.dex */
public abstract class Gauge extends View implements Observer {
    @Nullable
    public Function2<? super Section, ? super Section, Unit> A;
    @NotNull
    public final Gauge$animatorListener$1 B;
    @NotNull
    public Bitmap C;
    @NotNull
    public final Paint D;
    public int E;
    public int F;
    public int G;
    @NotNull
    public final List<Section> H;
    @Nullable
    public Section I;
    public float J;
    public boolean K;
    public boolean L;
    public float M;
    public float N;
    @NotNull
    public Locale O;
    public float P;
    public float Q;
    @NotNull
    public Position R;
    public float S;
    public float T;
    public boolean U;
    @NotNull
    public Bitmap V;
    @Nullable
    public Canvas W;
    @NotNull
    public Function1<? super Float, ? extends CharSequence> a0;
    @NotNull
    public final Paint h;
    @NotNull
    public TextPaint i;
    @NotNull
    public final TextPaint j;
    @NotNull
    public final TextPaint k;
    @NotNull
    public String l;
    public boolean m;
    public float n;
    public float o;
    public float p;
    public int q;
    public float r;
    public boolean s;
    public float t;
    public int u;
    @Nullable
    public ValueAnimator v;
    @Nullable
    public ValueAnimator w;
    @Nullable
    public ValueAnimator x;
    public boolean y;
    @Nullable
    public Function3<? super Gauge, ? super Boolean, ? super Boolean, Unit> z;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0010\u0007\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0012\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B9\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\u000b\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\u0006\u0010\u0012\u001a\u00020\r¢\u0006\u0004\b\u0014\u0010\u0015R\u001c\u0010\u0003\u001a\u00020\u00028\u0000@\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R\u001c\u0010\u0007\u001a\u00020\u00028\u0000@\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0007\u0010\u0004\u001a\u0004\b\b\u0010\u0006R\u001c\u0010\t\u001a\u00020\u00028\u0000@\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\t\u0010\u0004\u001a\u0004\b\n\u0010\u0006R\u001c\u0010\u000b\u001a\u00020\u00028\u0000@\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u000b\u0010\u0004\u001a\u0004\b\f\u0010\u0006R\u001c\u0010\u000e\u001a\u00020\r8\u0000@\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0012\u001a\u00020\r8\u0000@\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0012\u0010\u000f\u001a\u0004\b\u0013\u0010\u0011j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001dj\u0002\b\u001e¨\u0006\u001f"}, d2 = {"Lcom/github/anastr/speedviewlib/Gauge$Position;", "", "", "x", WeatherCriteria.UNIT_FARENHEIT, "getX$speedviewlib_release", "()F", EllipticCurveJsonWebKey.Y_MEMBER_NAME, "getY$speedviewlib_release", Property.ICON_TEXT_FIT_WIDTH, "getWidth$speedviewlib_release", Property.ICON_TEXT_FIT_HEIGHT, "getHeight$speedviewlib_release", "", "paddingH", "I", "getPaddingH$speedviewlib_release", "()I", "paddingV", "getPaddingV$speedviewlib_release", "<init>", "(Ljava/lang/String;IFFFFII)V", "TOP_LEFT", "TOP_CENTER", "TOP_RIGHT", FitnessChallengeConstants.LEFT, "CENTER", "RIGHT", "BOTTOM_LEFT", "BOTTOM_CENTER", "BOTTOM_RIGHT", "speedviewlib_release"}, k = 1, mv = {1, 5, 1})
    /* loaded from: classes9.dex */
    public enum Position {
        TOP_LEFT(0.0f, 0.0f, 0.0f, 0.0f, 1, 1),
        TOP_CENTER(0.5f, 0.0f, 0.5f, 0.0f, 0, 1),
        TOP_RIGHT(1.0f, 0.0f, 1.0f, 0.0f, -1, 1),
        LEFT(0.0f, 0.5f, 0.0f, 0.5f, 1, 0),
        CENTER(0.5f, 0.5f, 0.5f, 0.5f, 0, 0),
        RIGHT(1.0f, 0.5f, 1.0f, 0.5f, -1, 0),
        BOTTOM_LEFT(0.0f, 1.0f, 0.0f, 1.0f, 1, -1),
        BOTTOM_CENTER(0.5f, 1.0f, 0.5f, 1.0f, 0, -1),
        BOTTOM_RIGHT(1.0f, 1.0f, 1.0f, 1.0f, -1, -1);
        
        private final float height;
        private final int paddingH;
        private final int paddingV;
        private final float width;
        private final float x;
        private final float y;

        Position(float f, float f2, float f3, float f4, int i, int i2) {
            this.x = f;
            this.y = f2;
            this.width = f3;
            this.height = f4;
            this.paddingH = i;
            this.paddingV = i2;
        }

        public final float getHeight$speedviewlib_release() {
            return this.height;
        }

        public final int getPaddingH$speedviewlib_release() {
            return this.paddingH;
        }

        public final int getPaddingV$speedviewlib_release() {
            return this.paddingV;
        }

        public final float getWidth$speedviewlib_release() {
            return this.width;
        }

        public final float getX$speedviewlib_release() {
            return this.x;
        }

        public final float getY$speedviewlib_release() {
            return this.y;
        }
    }

    /* loaded from: classes9.dex */
    public static final class a extends Lambda implements Function1<Float, String> {
        public a() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ String invoke(Float f) {
            return invoke(f.floatValue());
        }

        @NotNull
        public final String invoke(float f) {
            String format = String.format(Gauge.this.getLocale(), "%.0f", Arrays.copyOf(new Object[]{Float.valueOf(f)}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "java.lang.String.format(locale, this, *args)");
            return format;
        }
    }

    /* loaded from: classes9.dex */
    public static final class b extends Lambda implements Function1<Float, String> {
        public b() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ String invoke(Float f) {
            return invoke(f.floatValue());
        }

        @NotNull
        public final String invoke(float f) {
            String format = String.format(Gauge.this.getLocale(), "%.1f", Arrays.copyOf(new Object[]{Float.valueOf(f)}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "java.lang.String.format(locale, this, *args)");
            return format;
        }
    }

    /* loaded from: classes9.dex */
    public static final class c extends Lambda implements Function1<Float, String> {
        public c() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ String invoke(Float f) {
            return invoke(f.floatValue());
        }

        @NotNull
        public final String invoke(float f) {
            String format = String.format(Gauge.this.getLocale(), "%.1f", Arrays.copyOf(new Object[]{Float.valueOf(f)}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "java.lang.String.format(locale, this, *args)");
            return format;
        }
    }

    /* loaded from: classes9.dex */
    public static final class d extends Lambda implements Function1<Section, Unit> {
        public final /* synthetic */ float $speedometerWidth;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public d(float f) {
            super(1);
            this.$speedometerWidth = f;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Section section) {
            invoke2(section);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull Section it) {
            Intrinsics.checkNotNullParameter(it, "it");
            it.setWidth(this.$speedometerWidth);
        }
    }

    public /* synthetic */ Gauge(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    private final float getSpeedUnitTextHeight() {
        if (this.U) {
            return this.j.getTextSize() + this.k.getTextSize() + this.S;
        }
        return Math.max(this.j.getTextSize(), this.k.getTextSize());
    }

    private final float getSpeedUnitTextWidth() {
        if (this.U) {
            return Math.max(this.j.measureText(getSpeedText().toString()), this.k.measureText(this.l));
        }
        return this.j.measureText(getSpeedText().toString()) + this.k.measureText(this.l) + this.S;
    }

    public static final void m(Gauge this$0, float f, ValueAnimator valueAnimator) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.isSpeedIncrease()) {
            this$0.setCurrentSpeed(this$0.getCurrentSpeed() + (this$0.getAccelerate() * 10.0f * (100.005f - this$0.getPercentSpeed()) * 0.01f));
            if (this$0.getCurrentSpeed() > f) {
                this$0.setCurrentSpeed(f);
            }
        } else {
            this$0.setCurrentSpeed(this$0.getCurrentSpeed() - ((((this$0.getDecelerate() * 10.0f) * (this$0.getPercentSpeed() + 0.005f)) * 0.01f) + 0.1f));
            if (this$0.getCurrentSpeed() < f) {
                this$0.setCurrentSpeed(f);
            }
        }
        this$0.postInvalidate();
        if (f == this$0.getCurrentSpeed()) {
            this$0.stop();
        }
    }

    public static final void n(Gauge this$0, ValueAnimator valueAnimator) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Object animatedValue = valueAnimator.getAnimatedValue();
        Objects.requireNonNull(animatedValue, "null cannot be cast to non-null type kotlin.Float");
        this$0.setCurrentSpeed(((Float) animatedValue).floatValue());
        this$0.postInvalidate();
    }

    public static final void o(Gauge this$0, ValueAnimator valueAnimator) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Object animatedValue = valueAnimator.getAnimatedValue();
        Objects.requireNonNull(animatedValue, "null cannot be cast to non-null type kotlin.Float");
        this$0.s = ((Float) animatedValue).floatValue() > this$0.getCurrentSpeed();
        Object animatedValue2 = valueAnimator.getAnimatedValue();
        Objects.requireNonNull(animatedValue2, "null cannot be cast to non-null type kotlin.Float");
        this$0.setCurrentSpeed(((Float) animatedValue2).floatValue());
        this$0.postInvalidate();
    }

    private final void setCurrentSpeed(float f) {
        this.r = f;
        checkSpeedIntChange$speedviewlib_release();
        checkSectionChange$speedviewlib_release();
    }

    private final void setSpeedTextPadding(float f) {
        this.T = f;
        if (this.L) {
            invalidate();
        }
    }

    private final void setUnitSpeedInterval(float f) {
        this.S = f;
        invalidateGauge();
    }

    public static /* synthetic */ void speedPercentTo$default(Gauge gauge, int i, long j, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: speedPercentTo");
        }
        if ((i2 & 2) != 0) {
            j = Constants.PN_LARGE_ICON_DOWNLOAD_TIMEOUT_IN_MILLIS;
        }
        gauge.speedPercentTo(i, j);
    }

    public static /* synthetic */ void speedTo$default(Gauge gauge, float f, long j, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: speedTo");
        }
        if ((i & 2) != 0) {
            j = Constants.PN_LARGE_ICON_DOWNLOAD_TIMEOUT_IN_MILLIS;
        }
        gauge.speedTo(f, j);
    }

    public final void addSections(@NotNull Section... sections) {
        Intrinsics.checkNotNullParameter(sections, "sections");
        addSections(ArraysKt___ArraysJvmKt.asList(sections));
    }

    public final void cancelSpeedAnimator() {
        d();
        e();
    }

    public final void checkSection$speedviewlib_release(@NotNull Section section) {
        Intrinsics.checkNotNullParameter(section, "section");
        int indexOf = this.H.indexOf(section);
        boolean z = false;
        if (section.getStartOffset() < section.getEndOffset()) {
            Section section2 = (Section) CollectionsKt___CollectionsKt.getOrNull(this.H, indexOf - 1);
            if (section2 != null) {
                if (!(section2.getEndOffset() <= section.getStartOffset() && section2.getEndOffset() < section.getEndOffset())) {
                    throw new IllegalArgumentException(("Section at index (" + indexOf + ") is conflicted with previous section").toString());
                }
            }
            Section section3 = (Section) CollectionsKt___CollectionsKt.getOrNull(this.H, indexOf + 1);
            if (section3 == null) {
                return;
            }
            if (section3.getStartOffset() >= section.getEndOffset() && section3.getStartOffset() > section.getStartOffset()) {
                z = true;
            }
            if (z) {
                return;
            }
            throw new IllegalArgumentException(("Section at index (" + indexOf + ") is conflicted with next section").toString());
        }
        throw new IllegalArgumentException("endOffset must be bigger than startOffset".toString());
    }

    public final void checkSectionChange$speedviewlib_release() {
        Section i = i();
        Section section = this.I;
        if (section != i) {
            onSectionChangeEvent(section, i);
            this.I = i;
        }
    }

    public final void checkSpeedIntChange$speedviewlib_release() {
        int i = (int) this.r;
        if (i != this.q && this.z != null) {
            ValueAnimator valueAnimator = this.w;
            boolean z = valueAnimator != null && valueAnimator.isRunning();
            boolean z2 = i > this.q;
            int i2 = z2 ? 1 : -1;
            while (true) {
                int i3 = this.q;
                if (i3 == i) {
                    break;
                }
                this.q = i3 + i2;
                Function3<? super Gauge, ? super Boolean, ? super Boolean, Unit> function3 = this.z;
                Intrinsics.checkNotNull(function3);
                function3.invoke(this, Boolean.valueOf(z2), Boolean.valueOf(z));
            }
        }
        this.q = i;
    }

    public final void clearSections() {
        for (Section section : this.H) {
            section.clearGauge$speedviewlib_release();
        }
        this.H.clear();
        invalidateGauge();
    }

    @NotNull
    public Canvas createBackgroundBitmapCanvas() {
        if (getWidth() != 0 && getHeight() != 0) {
            Bitmap createBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
            Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(width, heig… Bitmap.Config.ARGB_8888)");
            this.C = createBitmap;
            return new Canvas(this.C);
        }
        return new Canvas();
    }

    public final void d() {
        this.y = true;
        ValueAnimator valueAnimator = this.v;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        ValueAnimator valueAnimator2 = this.x;
        if (valueAnimator2 != null) {
            valueAnimator2.cancel();
        }
        this.y = false;
    }

    public abstract void defaultGaugeValues();

    public final float dpTOpx(float f) {
        return f * getContext().getResources().getDisplayMetrics().density;
    }

    public final void drawSpeedUnitText(@NotNull Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        RectF speedUnitTextBounds = getSpeedUnitTextBounds();
        q(getSpeedText().toString());
        Bitmap bitmap = this.V;
        canvas.drawBitmap(bitmap, (speedUnitTextBounds.left - (bitmap.getWidth() * 0.5f)) + (speedUnitTextBounds.width() * 0.5f), (speedUnitTextBounds.top - (this.V.getHeight() * 0.5f)) + (speedUnitTextBounds.height() * 0.5f), this.h);
    }

    public final void e() {
        this.y = true;
        ValueAnimator valueAnimator = this.w;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        this.y = false;
        this.w = null;
    }

    public final void f() {
        float f = this.P;
        if (!(f <= 1.0f && f > 0.0f)) {
            throw new IllegalArgumentException("accelerate must be between (0, 1]".toString());
        }
    }

    public final void g() {
        float f = this.Q;
        if (!(f <= 1.0f && f > 0.0f)) {
            throw new IllegalArgumentException("decelerate must be between (0, 1]".toString());
        }
    }

    public final float getAccelerate() {
        return this.P;
    }

    @NotNull
    public final Bitmap getBackgroundBitmap() {
        return this.C;
    }

    public final int getCurrentIntSpeed() {
        return this.q;
    }

    @Nullable
    public final Section getCurrentSection() {
        return this.I;
    }

    public final float getCurrentSpeed() {
        return this.r;
    }

    public final float getDecelerate() {
        return this.Q;
    }

    public final int getHeightPa() {
        return this.G;
    }

    @NotNull
    public final Locale getLocale() {
        return this.O;
    }

    public final float getMaxSpeed() {
        return this.o;
    }

    public final float getMinSpeed() {
        return this.n;
    }

    public final float getOffsetSpeed() {
        return (this.r - getMinSpeed()) / (getMaxSpeed() - getMinSpeed());
    }

    @Nullable
    public final Function2<Section, Section, Unit> getOnSectionChangeListener() {
        return this.A;
    }

    @Nullable
    public final Function3<Gauge, Boolean, Boolean, Unit> getOnSpeedChangeListener() {
        return this.z;
    }

    public final int getPadding() {
        return this.E;
    }

    public final float getPercentSpeed() {
        return ((this.r - getMinSpeed()) * 100.0f) / (getMaxSpeed() - getMinSpeed());
    }

    @NotNull
    public final List<Section> getSections() {
        return this.H;
    }

    public final float getSpeed() {
        return this.p;
    }

    @NotNull
    public final CharSequence getSpeedText() {
        return this.a0.invoke(Float.valueOf(this.r));
    }

    public final int getSpeedTextColor() {
        return this.j.getColor();
    }

    @NotNull
    public final Function1<Float, CharSequence> getSpeedTextListener() {
        return this.a0;
    }

    @NotNull
    public final Position getSpeedTextPosition() {
        return this.R;
    }

    public final float getSpeedTextSize() {
        return this.j.getTextSize();
    }

    @Nullable
    public final Typeface getSpeedTextTypeface() {
        return this.j.getTypeface();
    }

    @NotNull
    public final RectF getSpeedUnitTextBounds() {
        float x$speedviewlib_release = ((((this.F * this.R.getX$speedviewlib_release()) - this.M) + this.E) - (getSpeedUnitTextWidth() * this.R.getWidth$speedviewlib_release())) + (this.T * this.R.getPaddingH$speedviewlib_release());
        float y$speedviewlib_release = ((((this.G * this.R.getY$speedviewlib_release()) - this.N) + this.E) - (getSpeedUnitTextHeight() * this.R.getHeight$speedviewlib_release())) + (this.T * this.R.getPaddingV$speedviewlib_release());
        return new RectF(x$speedviewlib_release, y$speedviewlib_release, getSpeedUnitTextWidth() + x$speedviewlib_release, getSpeedUnitTextHeight() + y$speedviewlib_release);
    }

    public final boolean getSpeedometerTextRightToLeft() {
        return this.K;
    }

    public float getSpeedometerWidth() {
        return this.J;
    }

    public final int getTextColor() {
        return this.i.getColor();
    }

    @NotNull
    public final TextPaint getTextPaint() {
        return this.i;
    }

    public final float getTextSize() {
        return this.i.getTextSize();
    }

    @Nullable
    public final Typeface getTextTypeface() {
        return this.i.getTypeface();
    }

    public final float getTranslatedDx() {
        return this.M;
    }

    public final float getTranslatedDy() {
        return this.N;
    }

    public final float getTrembleDegree() {
        return this.t;
    }

    public final int getTrembleDuration() {
        return this.u;
    }

    @NotNull
    public final String getUnit() {
        return this.l;
    }

    public final int getUnitTextColor() {
        return this.k.getColor();
    }

    public final float getUnitTextSize() {
        return this.k.getTextSize();
    }

    public final boolean getUnitUnderSpeedText() {
        return this.U;
    }

    public final int getViewSize() {
        return Math.max(getWidth(), getHeight());
    }

    public final int getViewSizePa() {
        return Math.max(this.F, this.G);
    }

    public final int getWidthPa() {
        return this.F;
    }

    public final boolean getWithTremble() {
        return this.m;
    }

    public final void h() {
        if (this.t >= 0.0f) {
            if (!(this.u >= 0)) {
                throw new IllegalArgumentException("trembleDuration  can't be Negative".toString());
            }
            return;
        }
        throw new IllegalArgumentException("trembleDegree  can't be Negative".toString());
    }

    public final Section i() {
        for (Section section : this.H) {
            if (((getMaxSpeed() - getMinSpeed()) * section.getStartOffset()) + getMinSpeed() <= getCurrentSpeed() && ((getMaxSpeed() - getMinSpeed()) * section.getEndOffset()) + getMinSpeed() >= getCurrentSpeed()) {
                return section;
            }
        }
        return null;
    }

    public final void invalidateGauge() {
        if (this.L) {
            updateBackgroundBitmap();
            invalidate();
        }
    }

    @Override // android.view.View
    public boolean isAttachedToWindow() {
        return this.L;
    }

    public final boolean isSpeedIncrease() {
        return this.s;
    }

    public final float j(float f) {
        if (f > 100.0f) {
            return getMaxSpeed();
        }
        if (f < 0.0f) {
            return getMinSpeed();
        }
        return (f * (getMaxSpeed() - getMinSpeed()) * 0.01f) + getMinSpeed();
    }

    @Override // android.view.View
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        ValueAnimator valueAnimator = this.w;
        boolean z = false;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            z = true;
        }
        if (z) {
            e();
        }
    }

    public final void k() {
        this.i.setColor(ViewCompat.MEASURED_STATE_MASK);
        this.i.setTextSize(dpTOpx(10.0f));
        this.i.setTextAlign(Paint.Align.CENTER);
        this.j.setColor(ViewCompat.MEASURED_STATE_MASK);
        this.j.setTextSize(dpTOpx(18.0f));
        this.k.setColor(ViewCompat.MEASURED_STATE_MASK);
        this.k.setTextSize(dpTOpx(15.0f));
        this.H.add(new Section(0.0f, 0.6f, -16711936, getSpeedometerWidth(), null, 16, null).inGauge$speedviewlib_release(this));
        this.H.add(new Section(0.6f, 0.87f, InputDeviceCompat.SOURCE_ANY, getSpeedometerWidth(), null, 16, null).inGauge$speedviewlib_release(this));
        this.H.add(new Section(0.87f, 1.0f, SupportMenu.CATEGORY_MASK, getSpeedometerWidth(), null, 16, null).inGauge$speedviewlib_release(this));
        defaultGaugeValues();
    }

    public final void l(Context context, AttributeSet attributeSet) {
        if (attributeSet == null) {
            return;
        }
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.Gauge, 0, 0);
        Intrinsics.checkNotNullExpressionValue(obtainStyledAttributes, "context.theme.obtainStyl… R.styleable.Gauge, 0, 0)");
        setMaxSpeed(obtainStyledAttributes.getFloat(R.styleable.Gauge_sv_maxSpeed, getMaxSpeed()));
        setMinSpeed(obtainStyledAttributes.getFloat(R.styleable.Gauge_sv_minSpeed, getMinSpeed()));
        this.p = getMinSpeed();
        setCurrentSpeed(getMinSpeed());
        setSpeedometerWidth(obtainStyledAttributes.getDimension(R.styleable.Gauge_sv_speedometerWidth, getSpeedometerWidth()));
        for (Section section : this.H) {
            section.setWidth(getSpeedometerWidth());
        }
        setWithTremble(obtainStyledAttributes.getBoolean(R.styleable.Gauge_sv_withTremble, this.m));
        TextPaint textPaint = this.i;
        textPaint.setColor(obtainStyledAttributes.getColor(R.styleable.Gauge_sv_textColor, textPaint.getColor()));
        TextPaint textPaint2 = this.i;
        textPaint2.setTextSize(obtainStyledAttributes.getDimension(R.styleable.Gauge_sv_textSize, textPaint2.getTextSize()));
        TextPaint textPaint3 = this.j;
        textPaint3.setColor(obtainStyledAttributes.getColor(R.styleable.Gauge_sv_speedTextColor, textPaint3.getColor()));
        TextPaint textPaint4 = this.j;
        textPaint4.setTextSize(obtainStyledAttributes.getDimension(R.styleable.Gauge_sv_speedTextSize, textPaint4.getTextSize()));
        TextPaint textPaint5 = this.k;
        textPaint5.setColor(obtainStyledAttributes.getColor(R.styleable.Gauge_sv_unitTextColor, textPaint5.getColor()));
        TextPaint textPaint6 = this.k;
        textPaint6.setTextSize(obtainStyledAttributes.getDimension(R.styleable.Gauge_sv_unitTextSize, textPaint6.getTextSize()));
        String string = obtainStyledAttributes.getString(R.styleable.Gauge_sv_unit);
        if (string == null) {
            string = this.l;
        }
        setUnit(string);
        setTrembleDegree(obtainStyledAttributes.getFloat(R.styleable.Gauge_sv_trembleDegree, this.t));
        setTrembleDuration(obtainStyledAttributes.getInt(R.styleable.Gauge_sv_trembleDuration, this.u));
        setSpeedometerTextRightToLeft(obtainStyledAttributes.getBoolean(R.styleable.Gauge_sv_textRightToLeft, this.K));
        setAccelerate(obtainStyledAttributes.getFloat(R.styleable.Gauge_sv_accelerate, this.P));
        setDecelerate(obtainStyledAttributes.getFloat(R.styleable.Gauge_sv_decelerate, this.Q));
        setUnitUnderSpeedText(obtainStyledAttributes.getBoolean(R.styleable.Gauge_sv_unitUnderSpeedText, this.U));
        setUnitSpeedInterval(obtainStyledAttributes.getDimension(R.styleable.Gauge_sv_unitSpeedInterval, this.S));
        setSpeedTextPadding(obtainStyledAttributes.getDimension(R.styleable.Gauge_sv_speedTextPadding, this.T));
        String string2 = obtainStyledAttributes.getString(R.styleable.Gauge_sv_speedTextTypeface);
        if (string2 != null && !isInEditMode()) {
            setSpeedTextTypeface(Typeface.createFromAsset(context.getAssets(), string2));
        }
        String string3 = obtainStyledAttributes.getString(R.styleable.Gauge_sv_textTypeface);
        if (string3 != null && !isInEditMode()) {
            setTextTypeface(Typeface.createFromAsset(context.getAssets(), string3));
        }
        int i = obtainStyledAttributes.getInt(R.styleable.Gauge_sv_speedTextPosition, -1);
        if (i != -1) {
            setSpeedTextPosition(Position.values()[i]);
        }
        int i2 = obtainStyledAttributes.getInt(R.styleable.Gauge_sv_speedTextFormat, -1);
        if (i2 == 0) {
            setSpeedTextListener(new a());
        } else if (i2 == 1) {
            setSpeedTextListener(new b());
        }
        obtainStyledAttributes.recycle();
        f();
        g();
        h();
    }

    public final void makeSections(int i, int i2, @NotNull Style style) {
        Intrinsics.checkNotNullParameter(style, "style");
        for (Section section : this.H) {
            section.clearGauge$speedviewlib_release();
        }
        this.H.clear();
        float f = 1.0f / i;
        int i3 = 0;
        if (i > 0) {
            float f2 = 0.0f;
            float f3 = f;
            while (true) {
                i3++;
                this.H.add(new Section(f2, f3, i2, getSpeedometerWidth(), style).inGauge$speedviewlib_release(this));
                float f4 = f3 + f;
                if (i3 >= i) {
                    break;
                }
                f2 = f3;
                f3 = f4;
            }
        }
        invalidateGauge();
    }

    @Override // android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.L = true;
        if (isInEditMode()) {
            return;
        }
        updateBackgroundBitmap();
        invalidate();
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        cancelSpeedAnimator();
        this.L = false;
    }

    @Override // android.view.View
    public void onDraw(@NotNull Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        canvas.translate(this.M, this.N);
        canvas.drawBitmap(this.C, 0.0f, 0.0f, this.D);
    }

    public final void onSectionChangeEvent(@Nullable Section section, @Nullable Section section2) {
        Function2<? super Section, ? super Section, Unit> function2 = this.A;
        if (function2 == null) {
            return;
        }
        function2.invoke(section, section2);
    }

    @Override // android.view.View
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        int i5;
        super.onSizeChanged(i, i2, i3, i4);
        setPadding(getPaddingLeft(), getPaddingTop(), getPaddingRight(), getPaddingBottom());
        int i6 = this.F;
        if (i6 > 0 && (i5 = this.G) > 0) {
            Bitmap createBitmap = Bitmap.createBitmap(i6, i5, Bitmap.Config.ARGB_8888);
            Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(widthPa, he… Bitmap.Config.ARGB_8888)");
            this.V = createBitmap;
        }
        this.W = new Canvas(this.V);
    }

    @Override // android.view.View
    public void onVisibilityAggregated(boolean z) {
        super.onVisibilityAggregated(z);
        ValueAnimator valueAnimator = this.v;
        if ((valueAnimator == null || valueAnimator.isRunning()) ? false : true) {
            ValueAnimator valueAnimator2 = this.x;
            if ((valueAnimator2 == null || valueAnimator2.isRunning()) ? false : true) {
                if (z) {
                    tremble();
                } else {
                    e();
                }
            }
        }
    }

    public final void p(int i, int i2, int i3, int i4) {
        this.E = Math.max(Math.max(i, i3), Math.max(i2, i4));
        this.F = getWidth() - (this.E * 2);
        this.G = getHeight() - (this.E * 2);
    }

    public final float pxTOdp(float f) {
        return f / getContext().getResources().getDisplayMetrics().density;
    }

    public final void q(String str) {
        float width;
        float measureText;
        this.V.eraseColor(0);
        if (this.U) {
            Canvas canvas = this.W;
            if (canvas != null) {
                canvas.drawText(str, this.V.getWidth() * 0.5f, (this.V.getHeight() * 0.5f) - (this.S * 0.5f), this.j);
            }
            Canvas canvas2 = this.W;
            if (canvas2 == null) {
                return;
            }
            canvas2.drawText(this.l, this.V.getWidth() * 0.5f, (this.V.getHeight() * 0.5f) + this.k.getTextSize() + (this.S * 0.5f), this.k);
            return;
        }
        if (this.K) {
            measureText = (this.V.getWidth() * 0.5f) - (getSpeedUnitTextWidth() * 0.5f);
            width = this.k.measureText(this.l) + measureText + this.S;
        } else {
            width = (this.V.getWidth() * 0.5f) - (getSpeedUnitTextWidth() * 0.5f);
            measureText = this.j.measureText(str) + width + this.S;
        }
        float height = (this.V.getHeight() * 0.5f) + (getSpeedUnitTextHeight() * 0.5f);
        Canvas canvas3 = this.W;
        if (canvas3 != null) {
            canvas3.drawText(str, width, height, this.j);
        }
        Canvas canvas4 = this.W;
        if (canvas4 == null) {
            return;
        }
        canvas4.drawText(this.l, measureText, height, this.k);
    }

    public final void realSpeedPercentTo(float f) {
        realSpeedTo(j(f));
    }

    public final void realSpeedTo(final float f) {
        boolean z = this.p > this.r;
        if (f > getMaxSpeed()) {
            f = getMaxSpeed();
        } else if (f < getMinSpeed()) {
            f = getMinSpeed();
        }
        if (f == this.p) {
            return;
        }
        this.p = f;
        this.s = f > this.r;
        ValueAnimator valueAnimator = this.x;
        if ((valueAnimator != null && valueAnimator.isRunning()) && z == this.s) {
            return;
        }
        cancelSpeedAnimator();
        ValueAnimator ofInt = ValueAnimator.ofInt((int) this.r, (int) f);
        ofInt.setRepeatCount(-1);
        ofInt.setInterpolator(new LinearInterpolator());
        ofInt.setDuration(Math.abs((f - getCurrentSpeed()) * 10));
        ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.github.anastr.speedviewlib.c
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator2) {
                Gauge.m(Gauge.this, f, valueAnimator2);
            }
        });
        ofInt.addListener(this.B);
        Unit unit = Unit.INSTANCE;
        this.x = ofInt;
        ofInt.start();
    }

    public final void removeSection(@Nullable Section section) {
        if (section != null) {
            section.clearGauge$speedviewlib_release();
        }
        List<Section> list = this.H;
        Objects.requireNonNull(list, "null cannot be cast to non-null type kotlin.collections.MutableCollection<T>");
        TypeIntrinsics.asMutableCollection(list).remove(section);
        invalidateGauge();
    }

    public final void setAccelerate(float f) {
        this.P = f;
        f();
    }

    public final void setBackgroundBitmap(@NotNull Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(bitmap, "<set-?>");
        this.C = bitmap;
    }

    public final void setDecelerate(float f) {
        this.Q = f;
        g();
    }

    public final void setLocale(@NotNull Locale locale) {
        Intrinsics.checkNotNullParameter(locale, "locale");
        this.O = locale;
        if (this.L) {
            invalidate();
        }
    }

    public final void setMaxSpeed(float f) {
        setMinMaxSpeed(getMinSpeed(), f);
    }

    public final void setMinMaxSpeed(float f, float f2) {
        if (f < f2) {
            cancelSpeedAnimator();
            this.n = f;
            this.o = f2;
            checkSectionChange$speedviewlib_release();
            invalidateGauge();
            if (this.L) {
                setSpeedAt(this.p);
                return;
            }
            return;
        }
        throw new IllegalArgumentException("minSpeed must be smaller than maxSpeed !!".toString());
    }

    public final void setMinSpeed(float f) {
        setMinMaxSpeed(f, getMaxSpeed());
    }

    public final void setOnSectionChangeListener(@Nullable Function2<? super Section, ? super Section, Unit> function2) {
        this.A = function2;
    }

    public final void setOnSpeedChangeListener(@Nullable Function3<? super Gauge, ? super Boolean, ? super Boolean, Unit> function3) {
        this.z = function3;
    }

    @Override // android.view.View
    public void setPadding(int i, int i2, int i3, int i4) {
        p(i, i2, i3, i4);
        int i5 = this.E;
        super.setPadding(i5, i5, i5, i5);
    }

    @Override // android.view.View
    public void setPaddingRelative(int i, int i2, int i3, int i4) {
        p(i, i2, i3, i4);
        int i5 = this.E;
        super.setPaddingRelative(i5, i5, i5, i5);
    }

    public final void setSpeedAt(float f) {
        if (f > getMaxSpeed()) {
            f = getMaxSpeed();
        } else if (f < getMinSpeed()) {
            f = getMinSpeed();
        }
        this.s = f > this.r;
        this.p = f;
        setCurrentSpeed(f);
        cancelSpeedAnimator();
        invalidate();
        tremble();
    }

    public final void setSpeedTextColor(int i) {
        this.j.setColor(i);
        if (this.L) {
            invalidate();
        }
    }

    public final void setSpeedTextListener(@NotNull Function1<? super Float, ? extends CharSequence> speedTextFormat) {
        Intrinsics.checkNotNullParameter(speedTextFormat, "speedTextFormat");
        this.a0 = speedTextFormat;
        invalidateGauge();
    }

    public final void setSpeedTextPosition(@NotNull Position speedTextPosition) {
        Intrinsics.checkNotNullParameter(speedTextPosition, "speedTextPosition");
        this.R = speedTextPosition;
        invalidateGauge();
    }

    public final void setSpeedTextSize(float f) {
        this.j.setTextSize(f);
        if (this.L) {
            invalidate();
        }
    }

    public final void setSpeedTextTypeface(@Nullable Typeface typeface) {
        this.j.setTypeface(typeface);
        this.k.setTypeface(typeface);
        invalidateGauge();
    }

    public final void setSpeedometerTextRightToLeft(boolean z) {
        this.K = z;
        invalidateGauge();
    }

    public void setSpeedometerWidth(float f) {
        this.J = f;
        UtilsKt.doOnSections(this, new d(f));
        if (isAttachedToWindow()) {
            invalidateGauge();
        }
    }

    public final void setTextColor(int i) {
        this.i.setColor(i);
        invalidateGauge();
    }

    public final void setTextPaint(@NotNull TextPaint textPaint) {
        Intrinsics.checkNotNullParameter(textPaint, "<set-?>");
        this.i = textPaint;
    }

    public final void setTextSize(float f) {
        this.i.setTextSize(f);
        if (this.L) {
            invalidate();
        }
    }

    public final void setTextTypeface(@Nullable Typeface typeface) {
        this.i.setTypeface(typeface);
        invalidateGauge();
    }

    public final void setTranslatedDx(float f) {
        this.M = f;
    }

    public final void setTranslatedDy(float f) {
        this.N = f;
    }

    public final void setTrembleData(float f, int i) {
        setTrembleDegree(f);
        setTrembleDuration(i);
    }

    public final void setTrembleDegree(float f) {
        this.t = f;
        h();
    }

    public final void setTrembleDuration(int i) {
        this.u = i;
        h();
    }

    public final void setUnit(@NotNull String unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        this.l = unit;
        if (this.L) {
            invalidate();
        }
    }

    public final void setUnitTextColor(int i) {
        this.k.setColor(i);
        if (this.L) {
            invalidate();
        }
    }

    public final void setUnitTextSize(float f) {
        this.k.setTextSize(f);
        invalidateGauge();
    }

    public final void setUnitUnderSpeedText(boolean z) {
        this.U = z;
        if (z) {
            this.j.setTextAlign(Paint.Align.CENTER);
            this.k.setTextAlign(Paint.Align.CENTER);
        } else {
            this.j.setTextAlign(Paint.Align.LEFT);
            this.k.setTextAlign(Paint.Align.LEFT);
        }
        invalidateGauge();
    }

    public final void setWithTremble(boolean z) {
        this.m = z;
        tremble();
    }

    public final void slowDown() {
        realSpeedTo(0.0f);
    }

    @JvmOverloads
    public final void speedPercentTo(int i) {
        speedPercentTo$default(this, i, 0L, 2, null);
    }

    @JvmOverloads
    public final void speedPercentTo(int i, long j) {
        speedTo(j(i), j);
    }

    @JvmOverloads
    public final void speedTo(float f) {
        speedTo$default(this, f, 0L, 2, null);
    }

    @JvmOverloads
    public final void speedTo(float f, long j) {
        if (f > getMaxSpeed()) {
            f = getMaxSpeed();
        } else if (f < getMinSpeed()) {
            f = getMinSpeed();
        }
        if (f == this.p) {
            return;
        }
        this.p = f;
        this.s = f > this.r;
        cancelSpeedAnimator();
        ValueAnimator ofFloat = ValueAnimator.ofFloat(this.r, f);
        ofFloat.setInterpolator(new DecelerateInterpolator());
        ofFloat.setDuration(j);
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.github.anastr.speedviewlib.b
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                Gauge.n(Gauge.this, valueAnimator);
            }
        });
        ofFloat.addListener(this.B);
        Unit unit = Unit.INSTANCE;
        this.v = ofFloat;
        ofFloat.start();
    }

    public final void speedUp() {
        realSpeedTo(getMaxSpeed());
    }

    public final void stop() {
        ValueAnimator valueAnimator = this.v;
        if ((valueAnimator == null || valueAnimator.isRunning()) ? false : true) {
            ValueAnimator valueAnimator2 = this.x;
            if ((valueAnimator2 == null || valueAnimator2.isRunning()) ? false : true) {
                return;
            }
        }
        this.p = this.r;
        cancelSpeedAnimator();
        tremble();
    }

    public final void tremble() {
        float minSpeed;
        float f;
        e();
        if (this.m) {
            Random random = new Random();
            float nextFloat = this.t * random.nextFloat() * (random.nextBoolean() ? -1 : 1);
            if (this.p + nextFloat <= getMaxSpeed()) {
                if (this.p + nextFloat < getMinSpeed()) {
                    minSpeed = getMinSpeed();
                    f = this.p;
                }
                ValueAnimator ofFloat = ValueAnimator.ofFloat(this.r, this.p + nextFloat);
                ofFloat.setInterpolator(new DecelerateInterpolator());
                ofFloat.setDuration(getTrembleDuration());
                ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.github.anastr.speedviewlib.a
                    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                        Gauge.o(Gauge.this, valueAnimator);
                    }
                });
                ofFloat.addListener(this.B);
                Unit unit = Unit.INSTANCE;
                this.w = ofFloat;
                ofFloat.start();
            }
            minSpeed = getMaxSpeed();
            f = this.p;
            nextFloat = minSpeed - f;
            ValueAnimator ofFloat2 = ValueAnimator.ofFloat(this.r, this.p + nextFloat);
            ofFloat2.setInterpolator(new DecelerateInterpolator());
            ofFloat2.setDuration(getTrembleDuration());
            ofFloat2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.github.anastr.speedviewlib.a
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    Gauge.o(Gauge.this, valueAnimator);
                }
            });
            ofFloat2.addListener(this.B);
            Unit unit2 = Unit.INSTANCE;
            this.w = ofFloat2;
            ofFloat2.start();
        }
    }

    @Override // java.util.Observer
    public void update(@Nullable Observable observable, @Nullable Object obj) {
        invalidateGauge();
    }

    public abstract void updateBackgroundBitmap();

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r6v11, types: [com.github.anastr.speedviewlib.Gauge$animatorListener$1] */
    public Gauge(@NotNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        this.h = new Paint(1);
        this.i = new TextPaint(1);
        this.j = new TextPaint(1);
        this.k = new TextPaint(1);
        this.l = "Km/h";
        this.m = true;
        this.o = 100.0f;
        this.p = getMinSpeed();
        this.r = getMinSpeed();
        this.t = 4.0f;
        this.u = 1000;
        this.B = new AnimatorListenerAdapter() { // from class: com.github.anastr.speedviewlib.Gauge$animatorListener$1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(@NotNull Animator animation) {
                boolean z;
                Intrinsics.checkNotNullParameter(animation, "animation");
                z = Gauge.this.y;
                if (z) {
                    return;
                }
                Gauge.this.tremble();
            }
        };
        Bitmap createBitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(1, 1, Bitmap.Config.ARGB_8888)");
        this.C = createBitmap;
        this.D = new Paint(1);
        this.H = new ArrayList();
        this.J = dpTOpx(30.0f);
        Locale locale = Locale.getDefault();
        Intrinsics.checkNotNullExpressionValue(locale, "getDefault()");
        this.O = locale;
        this.P = 0.1f;
        this.Q = 0.1f;
        this.R = Position.BOTTOM_CENTER;
        this.S = dpTOpx(1.0f);
        this.T = dpTOpx(20.0f);
        Bitmap createBitmap2 = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(createBitmap2, "createBitmap(1, 1, Bitmap.Config.ARGB_8888)");
        this.V = createBitmap2;
        this.a0 = new c();
        k();
        l(context, attributeSet);
    }

    public final void addSections(@NotNull List<Section> sections) {
        Intrinsics.checkNotNullParameter(sections, "sections");
        for (Section section : sections) {
            this.H.add(section.inGauge$speedviewlib_release(this));
            checkSection$speedviewlib_release(section);
        }
        invalidateGauge();
    }
}
