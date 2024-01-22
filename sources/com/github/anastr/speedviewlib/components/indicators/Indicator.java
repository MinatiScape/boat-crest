package com.github.anastr.speedviewlib.components.indicators;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import com.github.anastr.speedviewlib.Speedometer;
import com.github.anastr.speedviewlib.components.indicators.Indicator;
import com.google.android.material.color.c;
import com.mappls.sdk.maps.style.layers.Property;
import com.mappls.sdk.services.api.weather.WeatherCriteria;
import java.util.Observable;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jose4j.jwk.RsaJsonWebKey;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\b&\u0018\u0000 :*\u0010\b\u0000\u0010\u0001 \u0001*\b\u0012\u0004\u0012\u00028\u00000\u00002\u00020\u0002:\u0002:;B\u000f\u0012\u0006\u00107\u001a\u000206¢\u0006\u0004\b8\u00109J\b\u0010\u0004\u001a\u00020\u0003H\u0016J\b\u0010\u0005\u001a\u00020\u0003H\u0016J\u0006\u0010\u0006\u001a\u00020\u0003J\u0006\u0010\u0007\u001a\u00020\u0003J\u0006\u0010\b\u001a\u00020\u0003J\u0010\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\tH&J\b\u0010\r\u001a\u00020\u000bH&J\u0010\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000eH$J\u0015\u0010\u0013\u001a\u00028\u00002\u0006\u0010\u0012\u001a\u00020\u0011¢\u0006\u0004\b\u0013\u0010\u0014J\u000e\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u0003J\u0006\u0010\u0017\u001a\u00020\u0003J\u000e\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000eR\"\u0010\u001f\u001a\u00020\u00188\u0004@\u0004X\u0084\u000e¢\u0006\u0012\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR$\u0010\u0012\u001a\u0004\u0018\u00010\u00118\u0004@\u0004X\u0084\u000e¢\u0006\u0012\n\u0004\b \u0010!\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R*\u0010-\u001a\u00020\u00032\u0006\u0010&\u001a\u00020\u00038\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\b'\u0010(\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R*\u00105\u001a\u00020.2\u0006\u0010/\u001a\u00020.8\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\b0\u0010\u0001\u001a\u0004\b1\u00102\"\u0004\b3\u00104¨\u0006<"}, d2 = {"Lcom/github/anastr/speedviewlib/components/indicators/Indicator;", "I", "Ljava/util/Observable;", "", "getTop", "getBottom", "getLightBottom", "getCenterX", "getCenterY", "Landroid/graphics/Canvas;", "canvas", "", "draw", "updateIndicator", "", "withEffects", "setWithEffects", "Lcom/github/anastr/speedviewlib/Speedometer;", "speedometer", "setTargetSpeedometer", "(Lcom/github/anastr/speedviewlib/Speedometer;)Lcom/github/anastr/speedviewlib/components/indicators/Indicator;", RsaJsonWebKey.FIRST_FACTOR_CRT_EXPONENT_MEMBER_NAME, "dpTOpx", "getViewSize", "Landroid/graphics/Paint;", "a", "Landroid/graphics/Paint;", "getIndicatorPaint", "()Landroid/graphics/Paint;", "setIndicatorPaint", "(Landroid/graphics/Paint;)V", "indicatorPaint", c.f10260a, "Lcom/github/anastr/speedviewlib/Speedometer;", "getSpeedometer", "()Lcom/github/anastr/speedviewlib/Speedometer;", "setSpeedometer", "(Lcom/github/anastr/speedviewlib/Speedometer;)V", "indicatorWidth", "d", WeatherCriteria.UNIT_FARENHEIT, "getWidth", "()F", "setWidth", "(F)V", Property.ICON_TEXT_FIT_WIDTH, "", "indicatorColor", RsaJsonWebKey.EXPONENT_MEMBER_NAME, "getColor", "()I", "setColor", "(I)V", "color", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "Companion", "Indicators", "speedviewlib_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes9.dex */
public abstract class Indicator<I extends Indicator<? extends I>> extends Observable {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public Paint f7905a;
    public final float b;
    @Nullable
    public Speedometer c;
    public float d;
    public int e;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\n\u0010\u000bJ\"\u0010\t\u001a\u0006\u0012\u0002\b\u00030\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006¨\u0006\f"}, d2 = {"Lcom/github/anastr/speedviewlib/components/indicators/Indicator$Companion;", "", "Landroid/content/Context;", "context", "Lcom/github/anastr/speedviewlib/Speedometer;", "speedometer", "Lcom/github/anastr/speedviewlib/components/indicators/Indicator$Indicators;", "indicator", "Lcom/github/anastr/speedviewlib/components/indicators/Indicator;", "createIndicator", "<init>", "()V", "speedviewlib_release"}, k = 1, mv = {1, 5, 1})
    /* loaded from: classes9.dex */
    public static final class Companion {

        @Metadata(bv = {1, 0, 3}, d1 = {}, d2 = {}, k = 3, mv = {1, 5, 1})
        /* loaded from: classes9.dex */
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[Indicators.values().length];
                iArr[Indicators.NoIndicator.ordinal()] = 1;
                iArr[Indicators.NormalIndicator.ordinal()] = 2;
                iArr[Indicators.NormalSmallIndicator.ordinal()] = 3;
                iArr[Indicators.TriangleIndicator.ordinal()] = 4;
                iArr[Indicators.SpindleIndicator.ordinal()] = 5;
                iArr[Indicators.LineIndicator.ordinal()] = 6;
                iArr[Indicators.HalfLineIndicator.ordinal()] = 7;
                iArr[Indicators.QuarterLineIndicator.ordinal()] = 8;
                iArr[Indicators.KiteIndicator.ordinal()] = 9;
                iArr[Indicators.NeedleIndicator.ordinal()] = 10;
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final Indicator<?> createIndicator(@NotNull Context context, @NotNull Speedometer speedometer, @NotNull Indicators indicator) {
            Indicator noIndicator;
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(speedometer, "speedometer");
            Intrinsics.checkNotNullParameter(indicator, "indicator");
            switch (WhenMappings.$EnumSwitchMapping$0[indicator.ordinal()]) {
                case 1:
                    noIndicator = new NoIndicator(context);
                    break;
                case 2:
                    noIndicator = new NormalIndicator(context);
                    break;
                case 3:
                    noIndicator = new NormalSmallIndicator(context);
                    break;
                case 4:
                    noIndicator = new TriangleIndicator(context);
                    break;
                case 5:
                    noIndicator = new SpindleIndicator(context);
                    break;
                case 6:
                    noIndicator = new LineIndicator(context, 1.0f);
                    break;
                case 7:
                    noIndicator = new LineIndicator(context, 0.5f);
                    break;
                case 8:
                    noIndicator = new LineIndicator(context, 0.25f);
                    break;
                case 9:
                    noIndicator = new KiteIndicator(context);
                    break;
                case 10:
                    noIndicator = new NeedleIndicator(context);
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
            return noIndicator.setTargetSpeedometer(speedometer);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\r\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\r¨\u0006\u000e"}, d2 = {"Lcom/github/anastr/speedviewlib/components/indicators/Indicator$Indicators;", "", "<init>", "(Ljava/lang/String;I)V", "NoIndicator", "NormalIndicator", "NormalSmallIndicator", "TriangleIndicator", "SpindleIndicator", "LineIndicator", "HalfLineIndicator", "QuarterLineIndicator", "KiteIndicator", "NeedleIndicator", "speedviewlib_release"}, k = 1, mv = {1, 5, 1})
    /* loaded from: classes9.dex */
    public enum Indicators {
        NoIndicator,
        NormalIndicator,
        NormalSmallIndicator,
        TriangleIndicator,
        SpindleIndicator,
        LineIndicator,
        HalfLineIndicator,
        QuarterLineIndicator,
        KiteIndicator,
        NeedleIndicator
    }

    public Indicator(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f7905a = new Paint(1);
        this.b = context.getResources().getDisplayMetrics().density;
        this.e = -14575885;
        this.f7905a.setColor(-14575885);
    }

    public final float dpTOpx(float f) {
        return f * this.b;
    }

    public abstract void draw(@NotNull Canvas canvas);

    public float getBottom() {
        return getCenterY();
    }

    public final float getCenterX() {
        Speedometer speedometer = this.c;
        if (speedometer != null) {
            Intrinsics.checkNotNull(speedometer);
            return speedometer.getSize() / 2.0f;
        }
        return 0.0f;
    }

    public final float getCenterY() {
        Speedometer speedometer = this.c;
        if (speedometer != null) {
            Intrinsics.checkNotNull(speedometer);
            return speedometer.getSize() / 2.0f;
        }
        return 0.0f;
    }

    public final int getColor() {
        return this.e;
    }

    @NotNull
    public final Paint getIndicatorPaint() {
        return this.f7905a;
    }

    public final float getLightBottom() {
        return getCenterY() > getBottom() ? getBottom() : getCenterY();
    }

    @Nullable
    public final Speedometer getSpeedometer() {
        return this.c;
    }

    public float getTop() {
        Speedometer speedometer = this.c;
        if (speedometer != null) {
            Intrinsics.checkNotNull(speedometer);
            return speedometer.getPadding();
        }
        return 0.0f;
    }

    public final float getViewSize() {
        Speedometer speedometer = this.c;
        if (speedometer == null) {
            return 0.0f;
        }
        return speedometer.getSize() - (speedometer.getPadding() * 2.0f);
    }

    public final float getWidth() {
        return this.d;
    }

    public final void setColor(int i) {
        this.e = i;
        if (this.c != null) {
            updateIndicator();
        }
        setChanged();
        notifyObservers(null);
    }

    public final void setIndicatorPaint(@NotNull Paint paint) {
        Intrinsics.checkNotNullParameter(paint, "<set-?>");
        this.f7905a = paint;
    }

    public final void setSpeedometer(@Nullable Speedometer speedometer) {
        this.c = speedometer;
    }

    @NotNull
    public final I setTargetSpeedometer(@NotNull Speedometer speedometer) {
        Intrinsics.checkNotNullParameter(speedometer, "speedometer");
        deleteObservers();
        addObserver(speedometer);
        this.c = speedometer;
        updateIndicator();
        return this;
    }

    public final void setWidth(float f) {
        this.d = f;
        if (this.c != null) {
            updateIndicator();
        }
        setChanged();
        notifyObservers(null);
    }

    public abstract void setWithEffects(boolean z);

    public abstract void updateIndicator();

    public final void withEffects(boolean z) {
        setWithEffects(z);
        if (this.c == null) {
            return;
        }
        updateIndicator();
    }
}
