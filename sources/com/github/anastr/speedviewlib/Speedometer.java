package com.github.anastr.speedviewlib;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.os.Build;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import com.clevertap.android.sdk.Constants;
import com.coveiot.android.fitnesschallenges.utils.FitnessChallengeConstants;
import com.github.anastr.speedviewlib.components.Style;
import com.github.anastr.speedviewlib.components.indicators.Indicator;
import com.github.anastr.speedviewlib.components.indicators.NoIndicator;
import com.github.anastr.speedviewlib.components.note.Note;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.mappls.sdk.services.api.weather.WeatherCriteria;
import com.realsil.sdk.dfu.DfuException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.ranges.g;
import org.eclipse.paho.android.service.MqttServiceConstants;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0094\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\r\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b&\u0018\u00002\u00020\u0001:\u0002¶\u0001B.\b\u0007\u0012\b\u0010°\u0001\u001a\u00030¯\u0001\u0012\f\b\u0002\u0010²\u0001\u001a\u0005\u0018\u00010±\u0001\u0012\t\b\u0002\u0010³\u0001\u001a\u00020\u0002¢\u0006\u0006\b´\u0001\u0010µ\u0001J\u0018\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002H\u0014J(\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u0002H\u0014J\b\u0010\f\u001a\u00020\u0005H$J\u0010\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\rH\u0014J\u0010\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\rH\u0004J\u0010\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\rH\u0004J\u0010\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\rH\u0004J\u0010\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\rH\u0004J\b\u0010\u0014\u001a\u00020\rH\u0014J\u0010\u0010\u0017\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0015H\u0004J\u0010\u0010\u0019\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u0015H\u0004J\b\u0010\u001a\u001a\u00020\u0002H\u0004J\u000e\u0010\u001c\u001a\u00020\u00052\u0006\u0010\u001b\u001a\u00020\u0002J\b\u0010\u001d\u001a\u00020\u0002H\u0004J\u000e\u0010\u001f\u001a\u00020\u00052\u0006\u0010\u001e\u001a\u00020\u0002J\u0016\u0010 \u001a\u00020\u00052\u0006\u0010\u001b\u001a\u00020\u00022\u0006\u0010\u001e\u001a\u00020\u0002J\u0016\u0010#\u001a\u00020\u00052\u0006\u0010!\u001a\u00020\u00152\u0006\u0010\"\u001a\u00020\u0015J\u001c\u0010(\u001a\u00020\u00052\n\u0010%\u001a\u0006\u0012\u0002\b\u00030$2\b\b\u0002\u0010'\u001a\u00020&J\u0006\u0010)\u001a\u00020\u0005J\u0010\u0010+\u001a\u00020\u00052\u0006\u0010*\u001a\u00020\rH\u0004J\u0010\u0010,\u001a\u00020\u00052\u0006\u0010*\u001a\u00020\rH\u0004J\u0010\u0010/\u001a\u00020\u00052\u0006\u0010.\u001a\u00020-H\u0016R2\u0010.\u001a\u0006\u0012\u0002\b\u0003002\n\u0010.\u001a\u0006\u0012\u0002\b\u0003008\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\b1\u00102\u001a\u0004\b3\u00104\"\u0004\b/\u00105R\"\u00109\u001a\u0002068\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b7\u00108\u001a\u0004\b9\u0010:\"\u0004\b;\u0010<R\"\u0010C\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b=\u0010>\u001a\u0004\b?\u0010@\"\u0004\bA\u0010BR\u001c\u0010I\u001a\u00020D8\u0004@\u0004X\u0084\u0004¢\u0006\f\n\u0004\bE\u0010F\u001a\u0004\bG\u0010HR*\u0010J\u001a\u00020\u00022\u0006\u0010J\u001a\u00020\u00028\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\bK\u0010>\u001a\u0004\bL\u0010@\"\u0004\bM\u0010BR*\u0010N\u001a\u00020\u00152\u0006\u0010N\u001a\u00020\u00158\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\bO\u0010P\u001a\u0004\bQ\u0010R\"\u0004\bS\u0010TR*\u0010U\u001a\u00020\u00152\u0006\u0010U\u001a\u00020\u00158\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\bV\u0010P\u001a\u0004\bW\u0010R\"\u0004\bX\u0010TR*\u0010Y\u001a\u00020\u00022\u0006\u0010Y\u001a\u00020\u00028\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\bZ\u0010>\u001a\u0004\b[\u0010@\"\u0004\b\\\u0010BR$\u0010\u0018\u001a\u00020\u00152\u0006\u0010]\u001a\u00020\u00158\u0004@BX\u0084\u000e¢\u0006\f\n\u0004\b^\u0010P\u001a\u0004\b_\u0010RR*\u0010a\u001a\u00020`2\u0006\u0010a\u001a\u00020`8\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\bb\u0010c\u001a\u0004\bd\u0010e\"\u0004\bf\u0010gR6\u0010i\u001a\b\u0012\u0004\u0012\u00020\u00150h2\f\u0010i\u001a\b\u0012\u0004\u0012\u00020\u00150h8\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\bj\u0010k\u001a\u0004\bl\u0010m\"\u0004\bn\u0010oR\"\u0010s\u001a\u00020\u00158\u0004@\u0004X\u0084\u000e¢\u0006\u0012\n\u0004\bp\u0010P\u001a\u0004\bq\u0010R\"\u0004\br\u0010TR*\u0010t\u001a\u00020\u00152\u0006\u0010t\u001a\u00020\u00158\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\bu\u0010P\u001a\u0004\bv\u0010R\"\u0004\bw\u0010TR¤\u0001\u0010\u007f\u001a<\u0012\u0013\u0012\u00110\u0002¢\u0006\f\by\u0012\b\bz\u0012\u0004\b\b({\u0012\u0013\u0012\u00110\u0015¢\u0006\f\by\u0012\b\bz\u0012\u0004\b\b(|\u0012\u0006\u0012\u0004\u0018\u00010}\u0018\u00010xj\u0004\u0018\u0001`~2@\u0010\u007f\u001a<\u0012\u0013\u0012\u00110\u0002¢\u0006\f\by\u0012\b\bz\u0012\u0004\b\b({\u0012\u0013\u0012\u00110\u0015¢\u0006\f\by\u0012\b\bz\u0012\u0004\b\b(|\u0012\u0006\u0012\u0004\u0018\u00010}\u0018\u00010xj\u0004\u0018\u0001`~8\u0006@FX\u0086\u000e¢\u0006\u0018\n\u0006\b\u0080\u0001\u0010\u0081\u0001\u001a\u0006\b\u0082\u0001\u0010\u0083\u0001\"\u0006\b\u0084\u0001\u0010\u0085\u0001R\u0015\u0010\u0087\u0001\u001a\u00020\u00158F@\u0006¢\u0006\u0007\u001a\u0005\b\u0086\u0001\u0010RR\u0015\u0010\u0089\u0001\u001a\u00020\u00158F@\u0006¢\u0006\u0007\u001a\u0005\b\u0088\u0001\u0010RR(\u0010\u008a\u0001\u001a\u00020\u00152\u0007\u0010\u008a\u0001\u001a\u00020\u00158V@VX\u0096\u000e¢\u0006\u000e\u001a\u0005\b\u008b\u0001\u0010R\"\u0005\b\u008c\u0001\u0010TR(\u0010\u008d\u0001\u001a\u00020\u00022\u0007\u0010\u008d\u0001\u001a\u00020\u00028F@FX\u0086\u000e¢\u0006\u000e\u001a\u0005\b\u008e\u0001\u0010@\"\u0005\b\u008f\u0001\u0010BR(\u0010\u0090\u0001\u001a\u00020\u00152\u0007\u0010\u0090\u0001\u001a\u00020\u00158F@FX\u0086\u000e¢\u0006\u000e\u001a\u0005\b\u0091\u0001\u0010R\"\u0005\b\u0092\u0001\u0010TR,\u0010\u0094\u0001\u001a\u00030\u0093\u00012\b\u0010\u0094\u0001\u001a\u00030\u0093\u00018F@FX\u0086\u000e¢\u0006\u0010\u001a\u0006\b\u0095\u0001\u0010\u0096\u0001\"\u0006\b\u0097\u0001\u0010\u0098\u0001R\u0015\u0010\u009a\u0001\u001a\u00020\u00028F@\u0006¢\u0006\u0007\u001a\u0005\b\u0099\u0001\u0010@R\u0015\u0010\u009c\u0001\u001a\u00020\u00028F@\u0006¢\u0006\u0007\u001a\u0005\b\u009b\u0001\u0010@R(\u0010\u009d\u0001\u001a\u00020\u00022\u0007\u0010\u009d\u0001\u001a\u00020\u00028F@FX\u0086\u000e¢\u0006\u000e\u001a\u0005\b\u009e\u0001\u0010@\"\u0005\b\u009f\u0001\u0010BR(\u0010¡\u0001\u001a\u0002062\u0007\u0010 \u0001\u001a\u0002068F@FX\u0086\u000e¢\u0006\u000e\u001a\u0005\b¡\u0001\u0010:\"\u0005\b¢\u0001\u0010<R\u0018\u0010¤\u0001\u001a\u00020\u00158D@\u0004X\u0084\u0004¢\u0006\u0007\u001a\u0005\b£\u0001\u0010RR\u0018\u0010¦\u0001\u001a\u00020\u00158D@\u0004X\u0084\u0004¢\u0006\u0007\u001a\u0005\b¥\u0001\u0010RR\u0018\u0010¨\u0001\u001a\u00020\u00158D@\u0004X\u0084\u0004¢\u0006\u0007\u001a\u0005\b§\u0001\u0010RR\u0018\u0010ª\u0001\u001a\u00020\u00158D@\u0004X\u0084\u0004¢\u0006\u0007\u001a\u0005\b©\u0001\u0010RR\u0018\u0010¬\u0001\u001a\u00020\u00158D@\u0004X\u0084\u0004¢\u0006\u0007\u001a\u0005\b«\u0001\u0010RR\u0018\u0010®\u0001\u001a\u00020\u00158D@\u0004X\u0084\u0004¢\u0006\u0007\u001a\u0005\b\u00ad\u0001\u0010R¨\u0006·\u0001"}, d2 = {"Lcom/github/anastr/speedviewlib/Speedometer;", "Lcom/github/anastr/speedviewlib/Gauge;", "", "widthMeasureSpec", "heightMeasureSpec", "", "onMeasure", Constants.INAPP_WINDOW, "h", "oldW", "oldH", "onSizeChanged", "defaultSpeedometerValues", "Landroid/graphics/Canvas;", "canvas", "onDraw", "drawMarks", "drawIndicator", "drawIndicatorLight", "drawNotes", "createBackgroundBitmapCanvas", "", "speed", "getDegreeAtSpeed", "degree", "getSpeedAtDegree", "getStartDegree", "startDegree", "setStartDegree", "getEndDegree", "endDegree", "setEndDegree", "setStartEndDegree", "xOffset", "yOffset", "setFulcrum", "Lcom/github/anastr/speedviewlib/components/note/Note;", "note", "", "showTimeMillisecond", "addNote", "removeAllNotes", com.google.android.material.color.c.f10260a, "drawDefMinMaxSpeedPosition", "drawTicks", "Lcom/github/anastr/speedviewlib/components/indicators/Indicator$Indicators;", "indicator", "setIndicator", "Lcom/github/anastr/speedviewlib/components/indicators/Indicator;", "b0", "Lcom/github/anastr/speedviewlib/components/indicators/Indicator;", "getIndicator", "()Lcom/github/anastr/speedviewlib/components/indicators/Indicator;", "(Lcom/github/anastr/speedviewlib/components/indicators/Indicator;)V", "", "d0", "Z", "isWithIndicatorLight", "()Z", "setWithIndicatorLight", "(Z)V", "e0", "I", "getIndicatorLightColor", "()I", "setIndicatorLightColor", "(I)V", "indicatorLightColor", "Landroid/graphics/Paint;", "h0", "Landroid/graphics/Paint;", "getMarkPaint", "()Landroid/graphics/Paint;", "markPaint", "marksNumber", "j0", "getMarksNumber", "setMarksNumber", "marksPadding", "k0", WeatherCriteria.UNIT_FARENHEIT, "getMarksPadding", "()F", "setMarksPadding", "(F)V", "markHeight", "l0", "getMarkHeight", "setMarkHeight", "backgroundCircleColor", "m0", "getBackgroundCircleColor", "setBackgroundCircleColor", "<set-?>", "p0", "getDegree", "Lcom/github/anastr/speedviewlib/Speedometer$Mode;", "speedometerMode", "r0", "Lcom/github/anastr/speedviewlib/Speedometer$Mode;", "getSpeedometerMode", "()Lcom/github/anastr/speedviewlib/Speedometer$Mode;", "setSpeedometerMode", "(Lcom/github/anastr/speedviewlib/Speedometer$Mode;)V", "", "ticks", "t0", "Ljava/util/List;", "getTicks", "()Ljava/util/List;", "setTicks", "(Ljava/util/List;)V", MqttServiceConstants.VERSION, "getInitTickPadding", "setInitTickPadding", "initTickPadding", "tickPadding", "w0", "getTickPadding", "setTickPadding", "Lkotlin/Function2;", "Lkotlin/ParameterName;", AppMeasurementSdk.ConditionalUserProperty.NAME, "tickPosition", "tick", "", "Lcom/github/anastr/speedviewlib/util/OnPrintTickLabelListener;", "onPrintTickLabel", "x0", "Lkotlin/jvm/functions/Function2;", "getOnPrintTickLabel", "()Lkotlin/jvm/functions/Function2;", "setOnPrintTickLabel", "(Lkotlin/jvm/functions/Function2;)V", "getFulcrumX", "fulcrumX", "getFulcrumY", "fulcrumY", "speedometerWidth", "getSpeedometerWidth", "setSpeedometerWidth", "markColor", "getMarkColor", "setMarkColor", "markWidth", "getMarkWidth", "setMarkWidth", "Lcom/github/anastr/speedviewlib/components/Style;", "markStyle", "getMarkStyle", "()Lcom/github/anastr/speedviewlib/components/Style;", "setMarkStyle", "(Lcom/github/anastr/speedviewlib/components/Style;)V", "getSize", "size", "getSizePa", "sizePa", "tickNumber", "getTickNumber", "setTickNumber", "tickRotation", "isTickRotation", "setTickRotation", "getViewCenterX", "viewCenterX", "getViewCenterY", "viewCenterY", "getViewLeft", "viewLeft", "getViewTop", "viewTop", "getViewRight", "viewRight", "getViewBottom", "viewBottom", "Landroid/content/Context;", "context", "Landroid/util/AttributeSet;", "attrs", "defStyleAttr", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "Mode", "speedviewlib_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes9.dex */
public abstract class Speedometer extends Gauge {
    @NotNull
    public Indicator<?> b0;
    @NotNull
    public final PointF c0;
    public boolean d0;
    public int e0;
    @NotNull
    public final Paint f0;
    @NotNull
    public final Paint g0;
    @NotNull
    public final Paint h0;
    @NotNull
    public final Path i0;
    public int j0;
    public float k0;
    public float l0;
    public int m0;
    public int n0;
    public int o0;
    public float p0;
    @NotNull
    public final ArrayList<Note<?>> q0;
    @NotNull
    public Mode r0;
    public int s0;
    @NotNull
    public List<Float> t0;
    public boolean u0;
    public float v0;
    public float w0;
    @Nullable
    public Function2<? super Integer, ? super Float, ? extends CharSequence> x0;
    public float y0;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0018\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B1\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\r\u001a\u00020\u0002\u0012\u0006\u0010\u000f\u001a\u00020\u0002¢\u0006\u0004\b\u0016\u0010\u0017R\u001c\u0010\u0003\u001a\u00020\u00028\u0000@\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R\u001c\u0010\u0007\u001a\u00020\u00028\u0000@\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0007\u0010\u0004\u001a\u0004\b\b\u0010\u0006R\u0019\u0010\n\u001a\u00020\t8\u0006@\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\n\u0010\fR\u001c\u0010\r\u001a\u00020\u00028\u0000@\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\r\u0010\u0004\u001a\u0004\b\u000e\u0010\u0006R\u001c\u0010\u000f\u001a\u00020\u00028\u0000@\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u000f\u0010\u0004\u001a\u0004\b\u0010\u0010\u0006R\u0013\u0010\u0011\u001a\u00020\t8F@\u0006¢\u0006\u0006\u001a\u0004\b\u0011\u0010\fR\u0013\u0010\u0012\u001a\u00020\t8F@\u0006¢\u0006\u0006\u001a\u0004\b\u0012\u0010\fR\u0013\u0010\u0013\u001a\u00020\t8F@\u0006¢\u0006\u0006\u001a\u0004\b\u0013\u0010\fR\u0013\u0010\u0014\u001a\u00020\t8F@\u0006¢\u0006\u0006\u001a\u0004\b\u0014\u0010\fR\u0013\u0010\u0015\u001a\u00020\t8F@\u0006¢\u0006\u0006\u001a\u0004\b\u0015\u0010\fj\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001dj\u0002\b\u001ej\u0002\b\u001fj\u0002\b ¨\u0006!"}, d2 = {"Lcom/github/anastr/speedviewlib/Speedometer$Mode;", "", "", "minDegree", "I", "getMinDegree$speedviewlib_release", "()I", "maxDegree", "getMaxDegree$speedviewlib_release", "", "isHalf", "Z", "()Z", "divWidth", "getDivWidth$speedviewlib_release", "divHeight", "getDivHeight$speedviewlib_release", "isLeft", "isTop", "isRight", "isBottom", "isQuarter", "<init>", "(Ljava/lang/String;IIIZII)V", "NORMAL", FitnessChallengeConstants.LEFT, "TOP", "RIGHT", "BOTTOM", "TOP_LEFT", "TOP_RIGHT", "BOTTOM_RIGHT", "BOTTOM_LEFT", "speedviewlib_release"}, k = 1, mv = {1, 5, 1})
    /* loaded from: classes9.dex */
    public enum Mode {
        NORMAL(0, 720, false, 1, 1),
        LEFT(90, DfuException.ERROR_READ_DEVICE_INFO_ERROR, true, 2, 1),
        TOP(180, 360, true, 1, 2),
        RIGHT(DfuException.ERROR_READ_DEVICE_INFO_ERROR, 450, true, 2, 1),
        BOTTOM(0, 180, true, 1, 2),
        TOP_LEFT(180, DfuException.ERROR_READ_DEVICE_INFO_ERROR, false, 1, 1),
        TOP_RIGHT(DfuException.ERROR_READ_DEVICE_INFO_ERROR, 360, false, 1, 1),
        BOTTOM_RIGHT(0, 90, false, 1, 1),
        BOTTOM_LEFT(90, 180, false, 1, 1);
        
        private final int divHeight;
        private final int divWidth;
        private final boolean isHalf;
        private final int maxDegree;
        private final int minDegree;

        Mode(int i, int i2, boolean z, int i3, int i4) {
            this.minDegree = i;
            this.maxDegree = i2;
            this.isHalf = z;
            this.divWidth = i3;
            this.divHeight = i4;
        }

        public final int getDivHeight$speedviewlib_release() {
            return this.divHeight;
        }

        public final int getDivWidth$speedviewlib_release() {
            return this.divWidth;
        }

        public final int getMaxDegree$speedviewlib_release() {
            return this.maxDegree;
        }

        public final int getMinDegree$speedviewlib_release() {
            return this.minDegree;
        }

        public final boolean isBottom() {
            return this == BOTTOM || this == BOTTOM_LEFT || this == BOTTOM_RIGHT;
        }

        public final boolean isHalf() {
            return this.isHalf;
        }

        public final boolean isLeft() {
            return this == LEFT || this == TOP_LEFT || this == BOTTOM_LEFT;
        }

        public final boolean isQuarter() {
            return (this.isHalf || this == NORMAL) ? false : true;
        }

        public final boolean isRight() {
            return this == RIGHT || this == TOP_RIGHT || this == BOTTOM_RIGHT;
        }

        public final boolean isTop() {
            return this == TOP || this == TOP_LEFT || this == TOP_RIGHT;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {}, d2 = {}, k = 3, mv = {1, 5, 1})
    /* loaded from: classes9.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[Mode.values().length];
            iArr[Mode.LEFT.ordinal()] = 1;
            iArr[Mode.TOP_LEFT.ordinal()] = 2;
            iArr[Mode.BOTTOM_LEFT.ordinal()] = 3;
            iArr[Mode.RIGHT.ordinal()] = 4;
            iArr[Mode.TOP_RIGHT.ordinal()] = 5;
            iArr[Mode.BOTTOM_RIGHT.ordinal()] = 6;
            iArr[Mode.TOP.ordinal()] = 7;
            iArr[Mode.BOTTOM.ordinal()] = 8;
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[Note.Position.values().length];
            iArr2[Note.Position.TopIndicator.ordinal()] = 1;
            iArr2[Note.Position.CenterIndicator.ordinal()] = 2;
            iArr2[Note.Position.BottomIndicator.ordinal()] = 3;
            iArr2[Note.Position.TopSpeedometer.ordinal()] = 4;
            iArr2[Note.Position.QuarterSpeedometer.ordinal()] = 5;
            iArr2[Note.Position.CenterSpeedometer.ordinal()] = 6;
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    /* loaded from: classes9.dex */
    public static final class a extends Lambda implements Function2<Integer, Float, String> {
        public a() {
            super(2);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ String invoke(Integer num, Float f) {
            return invoke(num.intValue(), f.floatValue());
        }

        @NotNull
        public final String invoke(int i, float f) {
            String format = String.format(Speedometer.this.getLocale(), "%.0f", Arrays.copyOf(new Object[]{Float.valueOf(f)}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "java.lang.String.format(locale, this, *args)");
            return format;
        }
    }

    /* loaded from: classes9.dex */
    public static final class b extends Lambda implements Function2<Integer, Float, String> {
        public b() {
            super(2);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ String invoke(Integer num, Float f) {
            return invoke(num.intValue(), f.floatValue());
        }

        @NotNull
        public final String invoke(int i, float f) {
            String format = String.format(Speedometer.this.getLocale(), "%.1f", Arrays.copyOf(new Object[]{Float.valueOf(f)}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "java.lang.String.format(locale, this, *args)");
            return format;
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public Speedometer(@NotNull Context context) {
        this(context, null, 0, 6, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public Speedometer(@NotNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public /* synthetic */ Speedometer(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    public static /* synthetic */ void addNote$default(Speedometer speedometer, Note note, long j, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: addNote");
        }
        if ((i & 2) != 0) {
            j = 3000;
        }
        speedometer.addNote(note, j);
    }

    private final void k() {
        this.g0.setStyle(Paint.Style.STROKE);
        this.h0.setStyle(Paint.Style.STROKE);
        setMarkColor(-1);
        setMarkWidth(dpTOpx(3.0f));
        setMarkStyle(Style.BUTT);
        defaultSpeedometerValues();
    }

    private final void l(Context context, AttributeSet attributeSet) {
        if (attributeSet == null) {
            return;
        }
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.Speedometer, 0, 0);
        Intrinsics.checkNotNullExpressionValue(obtainStyledAttributes, "context.theme.obtainStyl…leable.Speedometer, 0, 0)");
        int i = obtainStyledAttributes.getInt(R.styleable.Speedometer_sv_speedometerMode, -1);
        if (i != -1 && i != 0) {
            setSpeedometerMode(Mode.values()[i]);
        }
        int i2 = obtainStyledAttributes.getInt(R.styleable.Speedometer_sv_indicator, -1);
        if (i2 != -1) {
            setIndicator(Indicator.Indicators.values()[i2]);
        }
        setMarksNumber(obtainStyledAttributes.getInt(R.styleable.Speedometer_sv_marksNumber, this.j0));
        setMarksPadding(obtainStyledAttributes.getDimension(R.styleable.Speedometer_sv_marksPadding, this.k0));
        setMarkHeight(obtainStyledAttributes.getDimension(R.styleable.Speedometer_sv_markHeight, this.l0));
        setMarkWidth(obtainStyledAttributes.getDimension(R.styleable.Speedometer_sv_markWidth, getMarkWidth()));
        setMarkColor(obtainStyledAttributes.getColor(R.styleable.Speedometer_sv_markColor, getMarkColor()));
        int i3 = obtainStyledAttributes.getInt(R.styleable.Speedometer_sv_markStyle, -1);
        if (i3 != -1) {
            setMarkStyle(Style.values()[i3]);
        }
        setBackgroundCircleColor(obtainStyledAttributes.getColor(R.styleable.Speedometer_sv_backgroundCircleColor, this.m0));
        this.n0 = obtainStyledAttributes.getInt(R.styleable.Speedometer_sv_startDegree, this.n0);
        this.o0 = obtainStyledAttributes.getInt(R.styleable.Speedometer_sv_endDegree, this.o0);
        Indicator<?> indicator = this.b0;
        indicator.setWidth(obtainStyledAttributes.getDimension(R.styleable.Speedometer_sv_indicatorWidth, indicator.getWidth()));
        this.s0 = (int) obtainStyledAttributes.getDimension(R.styleable.Speedometer_sv_cutPadding, this.s0);
        setTickNumber(obtainStyledAttributes.getInteger(R.styleable.Speedometer_sv_tickNumber, this.t0.size()));
        this.u0 = obtainStyledAttributes.getBoolean(R.styleable.Speedometer_sv_tickRotation, this.u0);
        setTickPadding(obtainStyledAttributes.getDimension(R.styleable.Speedometer_sv_tickPadding, this.w0));
        Indicator<?> indicator2 = this.b0;
        indicator2.setColor(obtainStyledAttributes.getColor(R.styleable.Speedometer_sv_indicatorColor, indicator2.getColor()));
        this.d0 = obtainStyledAttributes.getBoolean(R.styleable.Speedometer_sv_withIndicatorLight, this.d0);
        this.e0 = obtainStyledAttributes.getColor(R.styleable.Speedometer_sv_indicatorLightColor, this.e0);
        int i4 = obtainStyledAttributes.getInt(R.styleable.Speedometer_sv_tickTextFormat, -1);
        if (i4 == 0) {
            setOnPrintTickLabel(new a());
        } else if (i4 == 1) {
            setOnPrintTickLabel(new b());
        }
        this.p0 = this.n0;
        obtainStyledAttributes.recycle();
        t();
    }

    public static final void s(Speedometer this$0, Note note) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(note, "$note");
        if (this$0.isAttachedToWindow()) {
            this$0.q0.remove(note);
            this$0.postInvalidate();
        }
    }

    public final void addNote(@NotNull final Note<?> note, long j) {
        Intrinsics.checkNotNullParameter(note, "note");
        note.build(getWidth());
        this.q0.add(note);
        if (j == -1) {
            return;
        }
        postDelayed(new Runnable() { // from class: com.github.anastr.speedviewlib.d
            @Override // java.lang.Runnable
            public final void run() {
                Speedometer.s(Speedometer.this, note);
            }
        }, j);
        invalidate();
    }

    @Override // com.github.anastr.speedviewlib.Gauge
    @NotNull
    public Canvas createBackgroundBitmapCanvas() {
        if (getSize() == 0) {
            return new Canvas();
        }
        Bitmap createBitmap = Bitmap.createBitmap(getSize(), getSize(), Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(size, size, Bitmap.Config.ARGB_8888)");
        setBackgroundBitmap(createBitmap);
        Canvas canvas = new Canvas(getBackgroundBitmap());
        canvas.drawCircle(getSize() * 0.5f, getSize() * 0.5f, (getSize() * 0.5f) - getPadding(), this.f0);
        canvas.clipRect(0, 0, getSize(), getSize());
        return canvas;
    }

    public abstract void defaultSpeedometerValues();

    public final void drawDefMinMaxSpeedPosition(@NotNull Canvas c) {
        Paint.Align align;
        CharSequence charSequence;
        Paint.Align align2;
        CharSequence charSequence2;
        Intrinsics.checkNotNullParameter(c, "c");
        TextPaint textPaint = getTextPaint();
        int i = this.n0;
        if (i % 360 <= 90) {
            align = Paint.Align.RIGHT;
        } else if (i % 360 <= 180) {
            align = Paint.Align.LEFT;
        } else {
            align = i % 360 <= 270 ? Paint.Align.CENTER : Paint.Align.RIGHT;
        }
        textPaint.setTextAlign(align);
        Function2<? super Integer, ? super Float, ? extends CharSequence> function2 = this.x0;
        if (function2 != null) {
            Intrinsics.checkNotNull(function2);
            charSequence = function2.invoke(0, Float.valueOf(getMinSpeed()));
        } else {
            charSequence = null;
        }
        if (charSequence == null) {
            charSequence = String.format(getLocale(), "%.0f", Arrays.copyOf(new Object[]{Float.valueOf(getMinSpeed())}, 1));
            Intrinsics.checkNotNullExpressionValue(charSequence, "java.lang.String.format(locale, this, *args)");
        }
        c.save();
        c.rotate(this.n0 + 90.0f, getSize() * 0.5f, getSize() * 0.5f);
        c.rotate(-(this.n0 + 90.0f), ((getSizePa() * 0.5f) - getTextPaint().getTextSize()) + getPadding(), getTextPaint().getTextSize() + getPadding());
        c.drawText(charSequence.toString(), ((getSizePa() * 0.5f) - getTextPaint().getTextSize()) + getPadding(), getTextPaint().getTextSize() + getPadding(), getTextPaint());
        c.restore();
        TextPaint textPaint2 = getTextPaint();
        int i2 = this.o0;
        if (i2 % 360 <= 90) {
            align2 = Paint.Align.RIGHT;
        } else if (i2 % 360 <= 180) {
            align2 = Paint.Align.LEFT;
        } else {
            align2 = i2 % 360 <= 270 ? Paint.Align.CENTER : Paint.Align.RIGHT;
        }
        textPaint2.setTextAlign(align2);
        Function2<? super Integer, ? super Float, ? extends CharSequence> function22 = this.x0;
        if (function22 != null) {
            Intrinsics.checkNotNull(function22);
            charSequence2 = function22.invoke(1, Float.valueOf(getMaxSpeed()));
        } else {
            charSequence2 = null;
        }
        if (charSequence2 == null) {
            charSequence2 = String.format(getLocale(), "%.0f", Arrays.copyOf(new Object[]{Float.valueOf(getMaxSpeed())}, 1));
            Intrinsics.checkNotNullExpressionValue(charSequence2, "java.lang.String.format(locale, this, *args)");
        }
        c.save();
        c.rotate(this.o0 + 90.0f, getSize() * 0.5f, getSize() * 0.5f);
        c.rotate(-(this.o0 + 90.0f), (getSizePa() * 0.5f) + getTextPaint().getTextSize() + getPadding(), getTextPaint().getTextSize() + getPadding());
        c.drawText(charSequence2.toString(), (getSizePa() * 0.5f) + getTextPaint().getTextSize() + getPadding(), getTextPaint().getTextSize() + getPadding(), getTextPaint());
        c.restore();
    }

    public final void drawIndicator(@NotNull Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        canvas.save();
        canvas.translate(getSize() * (getFulcrumX() - 0.5f), getSize() * (getFulcrumY() - 0.5f));
        canvas.rotate(this.p0 + 90.0f, getSize() * 0.5f, getSize() * 0.5f);
        if (this.d0) {
            drawIndicatorLight(canvas);
        }
        this.b0.draw(canvas);
        canvas.restore();
    }

    public final void drawIndicatorLight(@NotNull Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        float abs = Math.abs(getPercentSpeed() - this.y0) * 30.0f;
        this.y0 = getPercentSpeed();
        float f = abs > 30.0f ? 30.0f : abs;
        this.g0.setShader(new SweepGradient(getSize() * 0.5f, getSize() * 0.5f, new int[]{this.e0, 16777215}, new float[]{0.0f, f / 360.0f}));
        this.g0.setStrokeWidth(this.b0.getLightBottom() - this.b0.getTop());
        float top = this.b0.getTop() + (this.g0.getStrokeWidth() * 0.5f);
        RectF rectF = new RectF(top, top, getSize() - top, getSize() - top);
        canvas.save();
        canvas.rotate(-90.0f, getSize() * 0.5f, getSize() * 0.5f);
        if (isSpeedIncrease()) {
            canvas.scale(1.0f, -1.0f, getSize() * 0.5f, getSize() * 0.5f);
        }
        canvas.drawArc(rectF, 0.0f, f, false, this.g0);
        canvas.restore();
    }

    public final void drawMarks(@NotNull Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        this.i0.reset();
        this.i0.moveTo(getSize() * 0.5f, this.k0 + getPadding());
        this.i0.lineTo(getSize() * 0.5f, this.k0 + this.l0 + getPadding());
        canvas.save();
        canvas.rotate(getStartDegree() + 90.0f, getSize() * 0.5f, getSize() * 0.5f);
        int i = this.j0;
        float endDegree = (getEndDegree() - getStartDegree()) / (i + 1.0f);
        int i2 = 1;
        if (1 <= i) {
            while (true) {
                int i3 = i2 + 1;
                canvas.rotate(endDegree, getSize() * 0.5f, getSize() * 0.5f);
                canvas.drawPath(this.i0, this.h0);
                if (i2 == i) {
                    break;
                }
                i2 = i3;
            }
        }
        canvas.restore();
    }

    public final void drawNotes(@NotNull Canvas canvas) {
        float top;
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        Iterator<Note<?>> it = this.q0.iterator();
        while (it.hasNext()) {
            Note<?> next = it.next();
            if (next.getPosition() == Note.Position.CenterSpeedometer) {
                next.draw(canvas, getWidth() * 0.5f, getHeight() * 0.5f);
            } else {
                switch (WhenMappings.$EnumSwitchMapping$1[next.getPosition().ordinal()]) {
                    case 1:
                        top = this.b0.getTop();
                        break;
                    case 2:
                        top = (this.b0.getTop() + this.b0.getBottom()) * 0.5f;
                        break;
                    case 3:
                        top = this.b0.getBottom();
                        break;
                    case 4:
                        top = getPadding();
                        break;
                    case 5:
                        top = (getHeightPa() * 0.25f) + getPadding();
                        break;
                    case 6:
                        top = getViewCenterY();
                        break;
                    default:
                        throw new NoWhenBranchMatchedException();
                }
                canvas.save();
                canvas.rotate(this.p0 + 90.0f, getWidth() * 0.5f, getHeight() * 0.5f);
                canvas.rotate(-(this.p0 + 90.0f), getWidth() * 0.5f, top);
                next.draw(canvas, getWidth() * 0.5f, top);
                canvas.restore();
            }
        }
    }

    public final void drawTicks(@NotNull Canvas c) {
        Intrinsics.checkNotNullParameter(c, "c");
        if (this.t0.isEmpty()) {
            return;
        }
        getTextPaint().setTextAlign(Paint.Align.LEFT);
        int i = this.o0 - this.n0;
        int i2 = 0;
        for (Object obj : this.t0) {
            int i3 = i2 + 1;
            if (i2 < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
            }
            float floatValue = this.n0 + (i * ((Number) obj).floatValue());
            c.save();
            float f = 90.0f + floatValue;
            c.rotate(f, getSize() * 0.5f, getSize() * 0.5f);
            if (!this.u0) {
                c.rotate(-f, getSize() * 0.5f, getInitTickPadding() + getTextPaint().getTextSize() + getPadding() + getTickPadding());
            }
            CharSequence charSequence = null;
            if (getOnPrintTickLabel() != null) {
                Function2<Integer, Float, CharSequence> onPrintTickLabel = getOnPrintTickLabel();
                Intrinsics.checkNotNull(onPrintTickLabel);
                charSequence = onPrintTickLabel.invoke(Integer.valueOf(i2), Float.valueOf(getSpeedAtDegree(floatValue)));
            }
            if (charSequence == null) {
                charSequence = String.format(getLocale(), "%.0f", Arrays.copyOf(new Object[]{Float.valueOf(getSpeedAtDegree(floatValue))}, 1));
                Intrinsics.checkNotNullExpressionValue(charSequence, "java.lang.String.format(locale, this, *args)");
            }
            CharSequence charSequence2 = charSequence;
            c.translate(0.0f, getInitTickPadding() + getPadding() + getTickPadding());
            if (Build.VERSION.SDK_INT >= 23) {
                StaticLayout.Builder.obtain(charSequence2, 0, charSequence2.length(), getTextPaint(), getSize()).setAlignment(Layout.Alignment.ALIGN_CENTER).build().draw(c);
            } else {
                new StaticLayout(charSequence2, getTextPaint(), getSize(), Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, true).draw(c);
            }
            c.restore();
            i2 = i3;
        }
    }

    public final int getBackgroundCircleColor() {
        return this.m0;
    }

    public final float getDegree() {
        return this.p0;
    }

    public final float getDegreeAtSpeed(float f) {
        return (((f - getMinSpeed()) * (this.o0 - this.n0)) / (getMaxSpeed() - getMinSpeed())) + this.n0;
    }

    public final int getEndDegree() {
        return this.o0;
    }

    public final float getFulcrumX() {
        return this.c0.x;
    }

    public final float getFulcrumY() {
        return this.c0.y;
    }

    @NotNull
    public final Indicator<?> getIndicator() {
        return this.b0;
    }

    public final int getIndicatorLightColor() {
        return this.e0;
    }

    public final float getInitTickPadding() {
        return this.v0;
    }

    public final int getMarkColor() {
        return this.h0.getColor();
    }

    public final float getMarkHeight() {
        return this.l0;
    }

    @NotNull
    public final Paint getMarkPaint() {
        return this.h0;
    }

    @NotNull
    public final Style getMarkStyle() {
        return this.h0.getStrokeCap() == Paint.Cap.ROUND ? Style.ROUND : Style.BUTT;
    }

    public final float getMarkWidth() {
        return this.h0.getStrokeWidth();
    }

    public final int getMarksNumber() {
        return this.j0;
    }

    public final float getMarksPadding() {
        return this.k0;
    }

    @Nullable
    public final Function2<Integer, Float, CharSequence> getOnPrintTickLabel() {
        return this.x0;
    }

    public final int getSize() {
        Mode mode = this.r0;
        if (mode == Mode.NORMAL) {
            return getWidth();
        }
        return mode.isHalf() ? Math.max(getWidth(), getHeight()) : (Math.max(getWidth(), getHeight()) * 2) - (this.s0 * 2);
    }

    public final int getSizePa() {
        return getSize() - (getPadding() * 2);
    }

    public final float getSpeedAtDegree(float f) {
        return (((f - this.n0) * (getMaxSpeed() - getMinSpeed())) / (this.o0 - this.n0)) + getMinSpeed();
    }

    @NotNull
    public final Mode getSpeedometerMode() {
        return this.r0;
    }

    @Override // com.github.anastr.speedviewlib.Gauge
    public float getSpeedometerWidth() {
        return super.getSpeedometerWidth();
    }

    public final int getStartDegree() {
        return this.n0;
    }

    public final int getTickNumber() {
        return this.t0.size();
    }

    public final float getTickPadding() {
        return this.w0;
    }

    @NotNull
    public final List<Float> getTicks() {
        return this.t0;
    }

    public final float getViewBottom() {
        return getViewCenterY() + (getHeight() * 0.5f);
    }

    public final float getViewCenterX() {
        switch (WhenMappings.$EnumSwitchMapping$0[this.r0.ordinal()]) {
            case 1:
            case 2:
            case 3:
                return (getSize() * 0.5f) - (getWidth() * 0.5f);
            case 4:
            case 5:
            case 6:
                return (getSize() * 0.5f) + (getWidth() * 0.5f);
            default:
                return getSize() * 0.5f;
        }
    }

    public final float getViewCenterY() {
        int i = WhenMappings.$EnumSwitchMapping$0[this.r0.ordinal()];
        if (i != 2) {
            if (i != 3) {
                if (i != 5) {
                    if (i != 6) {
                        if (i != 7) {
                            if (i != 8) {
                                return getSize() * 0.5f;
                            }
                        }
                    }
                }
            }
            return (getSize() * 0.5f) + (getHeight() * 0.5f);
        }
        return (getSize() * 0.5f) - (getHeight() * 0.5f);
    }

    public final float getViewLeft() {
        return getViewCenterX() - (getWidth() * 0.5f);
    }

    public final float getViewRight() {
        return getViewCenterX() + (getWidth() * 0.5f);
    }

    public final float getViewTop() {
        return getViewCenterY() - (getHeight() * 0.5f);
    }

    public final boolean isTickRotation() {
        return this.u0;
    }

    public final boolean isWithIndicatorLight() {
        return this.d0;
    }

    @Override // com.github.anastr.speedviewlib.Gauge, android.view.View
    public void onDraw(@NotNull Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        super.onDraw(canvas);
        this.p0 = getDegreeAtSpeed(getCurrentSpeed());
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        int dpTOpx = (int) dpTOpx(250.0f);
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        if (mode == 1073741824 && mode2 == 1073741824) {
            size = Math.min(size, size2);
        } else if (mode != 1073741824) {
            if (mode2 == 1073741824) {
                size = size2;
            } else if ((mode == 0 && mode2 == 0) || (mode == Integer.MIN_VALUE && mode2 == Integer.MIN_VALUE)) {
                size = Math.min(dpTOpx, Math.min(size, size2));
            } else if (mode == Integer.MIN_VALUE) {
                size = Math.min(dpTOpx, size);
            } else {
                size = Math.min(dpTOpx, size2);
            }
        }
        int max = Math.max(size, Math.max(getSuggestedMinimumWidth(), getSuggestedMinimumHeight()));
        int divWidth$speedviewlib_release = max / this.r0.getDivWidth$speedviewlib_release();
        int divHeight$speedviewlib_release = max / this.r0.getDivHeight$speedviewlib_release();
        if (this.r0.isHalf()) {
            if (this.r0.getDivWidth$speedviewlib_release() == 2) {
                divWidth$speedviewlib_release += this.s0;
            } else {
                divHeight$speedviewlib_release += this.s0;
            }
        }
        setMeasuredDimension(divWidth$speedviewlib_release, divHeight$speedviewlib_release);
    }

    @Override // com.github.anastr.speedviewlib.Gauge, android.view.View
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.b0.updateIndicator();
        w();
    }

    public final void removeAllNotes() {
        this.q0.clear();
        invalidate();
    }

    public final void setBackgroundCircleColor(int i) {
        this.m0 = i;
        this.f0.setColor(i);
        invalidateGauge();
    }

    public final void setEndDegree(int i) {
        setStartEndDegree(this.n0, i);
    }

    public final void setFulcrum(float f, float f2) {
        if (g.rangeTo(0.0f, 1.0f).contains(Float.valueOf(f))) {
            if (g.rangeTo(0.0f, 1.0f).contains(Float.valueOf(f2))) {
                this.c0.set(f, f2);
                if (isAttachedToWindow()) {
                    invalidate();
                    return;
                }
                return;
            }
            throw new IllegalArgumentException("Fulcrum Y should be between [0f, 1f]".toString());
        }
        throw new IllegalArgumentException("Fulcrum X should be between [0f, 1f]".toString());
    }

    public final void setIndicator(@NotNull Indicator<?> indicator) {
        Intrinsics.checkNotNullParameter(indicator, "indicator");
        this.b0.deleteObservers();
        indicator.setTargetSpeedometer(this);
        this.b0 = indicator;
        if (isAttachedToWindow()) {
            this.b0.setTargetSpeedometer(this);
            invalidate();
        }
    }

    public final void setIndicatorLightColor(int i) {
        this.e0 = i;
    }

    public final void setInitTickPadding(float f) {
        this.v0 = f;
    }

    public final void setMarkColor(int i) {
        this.h0.setColor(i);
    }

    public final void setMarkHeight(float f) {
        this.l0 = f;
        invalidateGauge();
    }

    public final void setMarkStyle(@NotNull Style markStyle) {
        Intrinsics.checkNotNullParameter(markStyle, "markStyle");
        if (markStyle == Style.ROUND) {
            this.h0.setStrokeCap(Paint.Cap.ROUND);
        } else {
            this.h0.setStrokeCap(Paint.Cap.BUTT);
        }
        invalidateGauge();
    }

    public final void setMarkWidth(float f) {
        this.h0.setStrokeWidth(f);
        invalidateGauge();
    }

    public final void setMarksNumber(int i) {
        this.j0 = i;
        invalidateGauge();
    }

    public final void setMarksPadding(float f) {
        this.k0 = f;
        invalidateGauge();
    }

    public final void setOnPrintTickLabel(@Nullable Function2<? super Integer, ? super Float, ? extends CharSequence> function2) {
        this.x0 = function2;
        invalidateGauge();
    }

    public final void setSpeedometerMode(@NotNull Mode speedometerMode) {
        Intrinsics.checkNotNullParameter(speedometerMode, "speedometerMode");
        this.r0 = speedometerMode;
        if (speedometerMode != Mode.NORMAL) {
            this.n0 = speedometerMode.getMinDegree$speedviewlib_release();
            this.o0 = speedometerMode.getMaxDegree$speedviewlib_release();
        }
        w();
        cancelSpeedAnimator();
        this.p0 = getDegreeAtSpeed(getSpeed());
        this.b0.updateIndicator();
        if (isAttachedToWindow()) {
            requestLayout();
            invalidateGauge();
            tremble();
        }
    }

    @Override // com.github.anastr.speedviewlib.Gauge
    public void setSpeedometerWidth(float f) {
        super.setSpeedometerWidth(f);
        if (isAttachedToWindow()) {
            this.b0.updateIndicator();
        }
    }

    public final void setStartDegree(int i) {
        setStartEndDegree(i, this.o0);
    }

    public final void setStartEndDegree(int i, int i2) {
        this.n0 = i;
        this.o0 = i2;
        t();
        cancelSpeedAnimator();
        this.p0 = getDegreeAtSpeed(getSpeed());
        if (isAttachedToWindow()) {
            invalidateGauge();
            tremble();
        }
    }

    public final void setTickNumber(int i) {
        int i2 = 0;
        if (i >= 0) {
            ArrayList arrayList = new ArrayList();
            float f = i == 1 ? 0.0f : 1.0f / (i - 1);
            if (i > 0) {
                while (true) {
                    int i3 = i2 + 1;
                    arrayList.add(Float.valueOf(i2 * f));
                    if (i3 >= i) {
                        break;
                    }
                    i2 = i3;
                }
            }
            setTicks(arrayList);
            return;
        }
        throw new IllegalArgumentException("tickNumber mustn't be negative".toString());
    }

    public final void setTickPadding(float f) {
        this.w0 = f;
        invalidateGauge();
    }

    public final void setTickRotation(boolean z) {
        this.u0 = z;
        invalidateGauge();
    }

    public final void setTicks(@NotNull List<Float> ticks) {
        Intrinsics.checkNotNullParameter(ticks, "ticks");
        this.t0 = ticks;
        u();
        invalidateGauge();
    }

    public final void setWithIndicatorLight(boolean z) {
        this.d0 = z;
    }

    public final void t() {
        int i = this.n0;
        if (i >= 0) {
            int i2 = this.o0;
            if (!(i2 >= 0)) {
                throw new IllegalArgumentException("EndDegree can't be Negative".toString());
            }
            if (!(i < i2)) {
                throw new IllegalArgumentException("EndDegree must be bigger than StartDegree !".toString());
            }
            if (i2 - i <= 360) {
                if (i >= this.r0.getMinDegree$speedviewlib_release()) {
                    if (this.o0 <= this.r0.getMaxDegree$speedviewlib_release()) {
                        return;
                    }
                    throw new IllegalArgumentException(("EndDegree must be smaller than " + getSpeedometerMode().getMaxDegree$speedviewlib_release() + " in " + getSpeedometerMode() + " Mode !").toString());
                }
                throw new IllegalArgumentException(("StartDegree must be bigger than " + getSpeedometerMode().getMinDegree$speedviewlib_release() + " in " + getSpeedometerMode() + " Mode !").toString());
            }
            throw new IllegalArgumentException("(EndDegree - StartDegree) must be smaller than 360 !".toString());
        }
        throw new IllegalArgumentException("StartDegree can't be Negative".toString());
    }

    public final void u() {
        boolean z;
        for (Float f : this.t0) {
            float floatValue = f.floatValue();
            if (floatValue < 0.0f || floatValue > 1.0f) {
                z = false;
                continue;
            } else {
                z = true;
                continue;
            }
            if (!z) {
                throw new IllegalArgumentException("ticks must be between [0f, 1f] !!".toString());
            }
        }
    }

    public final void v() {
        this.f0.setColor(this.m0);
    }

    public final void w() {
        setTranslatedDx(this.r0.isRight() ? ((-getSize()) * 0.5f) + this.s0 : 0.0f);
        setTranslatedDy(this.r0.isBottom() ? ((-getSize()) * 0.5f) + this.s0 : 0.0f);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public Speedometer(@NotNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        this.b0 = new NoIndicator(context);
        this.c0 = new PointF(0.5f, 0.5f);
        this.e0 = -1140893918;
        this.f0 = new Paint(1);
        this.g0 = new Paint(1);
        this.h0 = new Paint(1);
        this.i0 = new Path();
        this.l0 = dpTOpx(9.0f);
        this.m0 = -1;
        this.n0 = 135;
        this.o0 = com.veryfit.multi.nativeprotocol.b.z1;
        this.p0 = 135;
        this.q0 = new ArrayList<>();
        this.r0 = Mode.NORMAL;
        this.t0 = CollectionsKt__CollectionsKt.emptyList();
        this.u0 = true;
        this.w0 = getSpeedometerWidth() + dpTOpx(3.0f);
        k();
        l(context, attributeSet);
        v();
    }

    public void setIndicator(@NotNull Indicator.Indicators indicator) {
        Intrinsics.checkNotNullParameter(indicator, "indicator");
        Indicator.Companion companion = Indicator.Companion;
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        setIndicator(companion.createIndicator(context, this, indicator));
    }
}
