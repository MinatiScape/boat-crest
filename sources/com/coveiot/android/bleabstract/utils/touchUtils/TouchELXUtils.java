package com.coveiot.android.bleabstract.utils.touchUtils;

import com.coveiot.coveaccess.constants.CoveApiConstants;
import com.touchgui.sdk.TGCallback;
import com.touchgui.sdk.TGCommand;
import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.CompletableSource;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.android.MainThreadDisposable;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.concurrent.Callable;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class TouchELXUtils {
    @NotNull
    public static final TouchELXUtils INSTANCE = new TouchELXUtils();

    public static final ObservableSource a(final TGCommand command) {
        Intrinsics.checkNotNullParameter(command, "$command");
        return Observable.create(new ObservableOnSubscribe() { // from class: com.coveiot.android.bleabstract.utils.touchUtils.b
            @Override // io.reactivex.ObservableOnSubscribe
            public final void subscribe(ObservableEmitter observableEmitter) {
                TouchELXUtils.a(TGCommand.this, observableEmitter);
            }
        });
    }

    public static final CompletableSource b(final TGCommand command) {
        Intrinsics.checkNotNullParameter(command, "$command");
        return Completable.create(new CompletableOnSubscribe() { // from class: com.coveiot.android.bleabstract.utils.touchUtils.a
            @Override // io.reactivex.CompletableOnSubscribe
            public final void subscribe(CompletableEmitter completableEmitter) {
                TouchELXUtils.a(TGCommand.this, completableEmitter);
            }
        });
    }

    @Nullable
    public final String getActivityMode(int i) {
        switch (i) {
            case 1:
            case 48:
            case 49:
                return CoveApiConstants.RUN;
            case 2:
            case 12:
            case 13:
            case 16:
            case 19:
            case 23:
            case 24:
            case 25:
            case 28:
            case 30:
            case 41:
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 90:
            case 113:
            default:
                return "WALK";
            case 3:
            case 10:
            case 11:
            case 50:
                return CoveApiConstants.CYCLE;
            case 4:
            case 5:
                return CoveApiConstants.HIKING;
            case 6:
                return "MOUNTAINEERING";
            case 7:
                return "BADMINTON";
            case 8:
                return "STRENGTH_TRAINING";
            case 9:
                return "PING_PONG";
            case 14:
                return "TENNIS";
            case 15:
                return "FOLK_DANCE";
            case 17:
                return "PILATES";
            case 18:
                return "YOGA";
            case 20:
            case 22:
                return "CROSS_FIT";
            case 21:
                return "BASKETBALL";
            case 26:
                return "LATIN_DANCE";
            case 27:
                return "POPPING";
            case 29:
                return "BALLET";
            case 31:
                return "BASEBALL";
            case 32:
                return "BOWLING";
            case 33:
                return "RACQUETBALL";
            case 34:
                return "CURLING";
            case 35:
                return "HUNTING";
            case 36:
                return "SNOWBOARDING";
            case 37:
                return "FISHING";
            case 38:
                return "DISC_SPORTS";
            case 39:
                return "RUGBY";
            case 40:
                return "GOLF";
            case 42:
                return "CORE_TRAINING";
            case 43:
                return "SKI";
            case 44:
                return "FITNESS_GAMING";
            case 45:
                return "AEROBICS";
            case 46:
                return "GROUP_TRAINING";
            case 47:
                return "CARDIO_BOXING";
            case 56:
                return "ELLIPTICAL";
            case 57:
                return "ROWING";
            case 58:
                return "FREE_EXERCISE";
            case 59:
                return "FENCING";
            case 60:
                return "SOFTBALL";
            case 61:
                return "CLIMB_STAIRS";
            case 62:
                return "AMERICAN_FOOTBALL";
            case 63:
                return "VOLLEYBALL";
            case 64:
                return "ROLLING";
            case 65:
                return "PICKLEBALL";
            case 66:
                return "HOCKEY";
            case 67:
                return "BOXING";
            case 68:
                return "TAEKWONDO";
            case 69:
                return "KARATE";
            case 70:
                return "FLEXIBILITY";
            case 71:
                return "HANDBALL";
            case 72:
                return "HAND_CYCLING";
            case 73:
                return "KENDO";
            case 74:
            case 120:
                return "HIIT";
            case 75:
                return "CRICKET";
            case 76:
                return "FOOTBALL";
            case 77:
                return "MEDITATION";
            case 78:
                return "WRESTLING";
            case 79:
                return "STEPPER";
            case 80:
                return "TAI_CHI";
            case 81:
                return "GYMNASTICS";
            case 82:
                return "TRACK_FIELD";
            case 83:
                return "SKIPPING";
            case 84:
                return "MARTIAL_ARTS";
            case 85:
                return "WARM_UP";
            case 86:
                return "SNOW_SPORTS";
            case 87:
                return "LACROSSE";
            case 88:
                return "HORIZONTAL_BAR";
            case 89:
                return "PARALLEL_BARS";
            case 91:
                return "HULA_HOOP";
            case 92:
                return "DARTS";
            case 93:
                return "ARCHERY";
            case 94:
                return "HORSE_RIDING";
            case 95:
                return "SHUTTLECOCK";
            case 96:
                return "ICE_HOCKEY";
            case 97:
                return "SIT_UPS";
            case 98:
                return "WAIST_TRAINING";
            case 99:
                return "PUSH_UPS";
            case 100:
                return "TREADMILL";
            case 101:
                return "BATTLE_ROPE";
            case 102:
                return "SMITH_MACHINE";
            case 103:
                return "PULL_UPS";
            case 104:
                return "ZUMBA";
            case 105:
                return "PLANK";
            case 106:
                return "KABADDI";
            case 107:
                return "SHOT_PUT";
            case 108:
                return "SOLID_BALL";
            case 109:
                return "JAVELIN";
            case 110:
                return "LONG_JUMP";
            case 111:
                return "HIGH_JUMP";
            case 112:
                return "ROCK_CLIMBING";
            case 114:
                return "SQUARE_DANCE";
            case 115:
                return "DUMBBELLS";
            case 116:
                return "KARTING";
            case 117:
                return "DODGEBALL";
            case 118:
                return "YOYO";
            case 119:
                return "LOCKING";
            case 121:
                return "BURPEES";
            case 122:
                return "BELLY_DANCE";
            case 123:
                return "SKATEBOARDING";
            case 124:
                return "PARKOUR";
            case 125:
                return "JAZZ_DANCE";
            case 126:
                return "MODERN_DANCE";
            case 127:
                return "AEROBICS_GYMS";
        }
    }

    @Nullable
    public final String getActivitySite(int i) {
        return (i == 1 || i == 2 || i == 3 || i == 48 || i == 50 || i == 52) ? "OUTDOOR" : "INDOOR";
    }

    public final double getAverageSpeed(int i, int i2) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        decimalFormat.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.ENGLISH));
        decimalFormat.setRoundingMode(RoundingMode.DOWN);
        String format = decimalFormat.format((i / i2) * 3.6d);
        Intrinsics.checkNotNullExpressionValue(format, "decimalFormat.format(dis…uration.toDouble() * 3.6)");
        return Double.parseDouble(format) * 100;
    }

    public final int getAverageStrideLength(int i, int i2) {
        return kotlin.math.c.roundToInt((i / i2) * 100);
    }

    public final int getWeatherType(@NotNull String weatherType) {
        Intrinsics.checkNotNullParameter(weatherType, "weatherType");
        if (StringsKt__StringsKt.contains((CharSequence) weatherType, (CharSequence) "Clear", true)) {
            return 1;
        }
        if (StringsKt__StringsKt.contains((CharSequence) weatherType, (CharSequence) "Clouds", true)) {
            return 2;
        }
        if (StringsKt__StringsKt.contains((CharSequence) weatherType, (CharSequence) "Rain", true)) {
            return 4;
        }
        if (StringsKt__StringsKt.contains((CharSequence) weatherType, (CharSequence) "Thunderstorm", true)) {
            return 6;
        }
        return StringsKt__StringsKt.contains((CharSequence) weatherType, (CharSequence) "Snow", true) ? 7 : 1;
    }

    @Nullable
    public final <T> Observable<T> observeCommand(@NotNull final TGCommand<T> command) {
        Intrinsics.checkNotNullParameter(command, "command");
        return Observable.defer(new Callable() { // from class: com.coveiot.android.bleabstract.utils.touchUtils.c
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return TouchELXUtils.a(TGCommand.this);
            }
        });
    }

    @NotNull
    public final Completable observeCommandNoResult(@NotNull final TGCommand<Void> command) {
        Intrinsics.checkNotNullParameter(command, "command");
        Completable defer = Completable.defer(new Callable() { // from class: com.coveiot.android.bleabstract.utils.touchUtils.d
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return TouchELXUtils.b(TGCommand.this);
            }
        });
        Intrinsics.checkNotNullExpressionValue(defer, "defer {\n            Comp…)\n            }\n        }");
        return defer;
    }

    public static final void a(final TGCommand command, final ObservableEmitter emitter) {
        Intrinsics.checkNotNullParameter(command, "$command");
        Intrinsics.checkNotNullParameter(emitter, "emitter");
        emitter.setDisposable(new MainThreadDisposable() { // from class: com.coveiot.android.bleabstract.utils.touchUtils.TouchELXUtils$observeCommand$1$1$1
            @Override // io.reactivex.android.MainThreadDisposable
            public void onDispose() {
                command.cancel();
            }
        });
        command.execute(new TGCallback<T>() { // from class: com.coveiot.android.bleabstract.utils.touchUtils.TouchELXUtils$observeCommand$1$1$2
            @Override // com.touchgui.sdk.TGCallback
            public void onFailure(@NotNull Throwable throwable) {
                Intrinsics.checkNotNullParameter(throwable, "throwable");
                emitter.isDisposed();
            }

            @Override // com.touchgui.sdk.TGCallback
            public void onSuccess(@Nullable T t) {
                if (emitter.isDisposed()) {
                    return;
                }
                if (t != null) {
                    emitter.onNext(t);
                }
                emitter.onComplete();
            }
        });
    }

    public static final void a(final TGCommand command, final CompletableEmitter emitter) {
        Intrinsics.checkNotNullParameter(command, "$command");
        Intrinsics.checkNotNullParameter(emitter, "emitter");
        emitter.setDisposable(new MainThreadDisposable() { // from class: com.coveiot.android.bleabstract.utils.touchUtils.TouchELXUtils$observeCommandNoResult$1$1$1
            @Override // io.reactivex.android.MainThreadDisposable
            public void onDispose() {
                command.cancel();
            }
        });
        command.execute(new TGCallback<Void>() { // from class: com.coveiot.android.bleabstract.utils.touchUtils.TouchELXUtils$observeCommandNoResult$1$1$2
            @Override // com.touchgui.sdk.TGCallback
            public void onFailure(@NotNull Throwable throwable) {
                Intrinsics.checkNotNullParameter(throwable, "throwable");
                CompletableEmitter.this.isDisposed();
            }

            @Override // com.touchgui.sdk.TGCallback
            public void onSuccess(@Nullable Void r1) {
                if (CompletableEmitter.this.isDisposed()) {
                    return;
                }
                CompletableEmitter.this.onComplete();
            }
        });
    }
}
