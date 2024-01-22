package com.coveiot.android.bleabstract.request;

import com.coveiot.sdk.ble.api.model.SendWeatherModel;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class SendWeatherRequest extends BleBaseRequest {
    @Nullable
    public final String f;
    @Nullable
    public final List<SendWeatherModel> g;
    @Nullable
    public final List<SendWeatherModel> h;
    @Nullable
    public TemperatureUnitType i;

    /* loaded from: classes2.dex */
    public static final class Builder {
        @Nullable

        /* renamed from: a  reason: collision with root package name */
        public String f3517a;
        @Nullable
        public List<? extends SendWeatherModel> b;
        @Nullable
        public List<? extends SendWeatherModel> c;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Builder sendWeather$default(Builder builder, String str, List list, List list2, int i, Object obj) {
            if ((i & 4) != 0) {
                list2 = null;
            }
            return builder.sendWeather(str, list, list2);
        }

        @NotNull
        public final SendWeatherRequest build() {
            return new SendWeatherRequest(this.f3517a, this.b, this.c);
        }

        @Nullable
        public final List<SendWeatherModel> getListHourlyWeatherModel() {
            return this.c;
        }

        @Nullable
        public final List<SendWeatherModel> getListSendWeatherModel() {
            return this.b;
        }

        @Nullable
        public final String getPlaceName() {
            return this.f3517a;
        }

        @NotNull
        public final Builder sendWeather(@NotNull String placeName, @Nullable List<? extends SendWeatherModel> list, @Nullable List<? extends SendWeatherModel> list2) {
            Intrinsics.checkNotNullParameter(placeName, "placeName");
            this.f3517a = placeName;
            this.b = list;
            this.c = list2;
            return this;
        }

        public final void setListHourlyWeatherModel(@Nullable List<? extends SendWeatherModel> list) {
            this.c = list;
        }

        public final void setListSendWeatherModel(@Nullable List<? extends SendWeatherModel> list) {
            this.b = list;
        }

        public final void setPlaceName$bleabstract_release(@Nullable String str) {
            this.f3517a = str;
        }
    }

    /* loaded from: classes2.dex */
    public enum TemperatureUnitType {
        CENTIGRADE,
        FAHRENHEIT
    }

    public /* synthetic */ SendWeatherRequest(String str, List list, List list2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, list, (i & 4) != 0 ? null : list2);
    }

    @Nullable
    public final List<SendWeatherModel> getListHourlyWeatherModel() {
        return this.h;
    }

    @Nullable
    public final List<SendWeatherModel> getListSendWeatherModel() {
        return this.g;
    }

    @Nullable
    public final String getPlaceName() {
        return this.f;
    }

    @Nullable
    public final TemperatureUnitType getTempUnitType() {
        return this.i;
    }

    public final void setTempUnitType(@Nullable TemperatureUnitType temperatureUnitType) {
        this.i = temperatureUnitType;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public SendWeatherRequest(@Nullable String str, @Nullable List<? extends SendWeatherModel> list, @Nullable List<? extends SendWeatherModel> list2) {
        this.f = str;
        this.g = list;
        this.h = list2;
    }
}
