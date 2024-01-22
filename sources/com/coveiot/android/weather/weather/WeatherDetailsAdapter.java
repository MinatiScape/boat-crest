package com.coveiot.android.weather.weather;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.weather.R;
import com.coveiot.android.weather.response.forecastmodel.WeatherDesc;
import com.coveiot.android.weather.response.forecastmodel.WeatherDetail;
import com.coveiot.android.weathersdk.WeatherConditionType;
import java.util.Calendar;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes8.dex */
public final class WeatherDetailsAdapter extends RecyclerView.Adapter<WeatherViewHolder> {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public final Context f6176a;
    @Nullable
    public List<WeatherDetail> b;
    @NotNull
    public final WeatherViewModel c;
    public int d;
    @NotNull
    public String[] e;

    /* loaded from: classes8.dex */
    public static final class WeatherViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        public ImageView f6177a;
        public final TextView b;
        public final TextView c;
        public final TextView d;
        public final View e;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public WeatherViewHolder(@Nullable View view) {
            super(view);
            Intrinsics.checkNotNull(view);
            this.f6177a = (ImageView) view.findViewById(R.id.img_weather);
            this.b = (TextView) view.findViewById(R.id.txt_temp);
            this.c = (TextView) view.findViewById(R.id.txt_status);
            this.d = (TextView) view.findViewById(R.id.txt_day);
            this.e = view.findViewById(R.id.divider2);
        }

        public final View getDivider() {
            return this.e;
        }

        public final ImageView getImgWeather() {
            return this.f6177a;
        }

        public final TextView getTxtDay() {
            return this.d;
        }

        public final TextView getTxtStatus() {
            return this.c;
        }

        public final TextView getTxtTemp() {
            return this.b;
        }

        public final void setImgWeather(ImageView imageView) {
            this.f6177a = imageView;
        }
    }

    public WeatherDetailsAdapter(@Nullable Context context, @Nullable List<WeatherDetail> list, @NotNull WeatherViewModel viewmodel) {
        Intrinsics.checkNotNullParameter(viewmodel, "viewmodel");
        this.f6176a = context;
        this.b = list;
        this.c = viewmodel;
        this.d = -1;
        this.e = new String[]{"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
    }

    @Nullable
    public final Context getContext() {
        return this.f6176a;
    }

    @NotNull
    public final String[] getDaysArray() {
        return this.e;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<WeatherDetail> list = this.b;
        Intrinsics.checkNotNull(list);
        return list.size();
    }

    public final int getSelectedPosition() {
        return this.d;
    }

    @NotNull
    public final WeatherViewModel getViewmodel() {
        return this.c;
    }

    @Nullable
    public final List<WeatherDetail> getWeatherList() {
        return this.b;
    }

    public final void setDaysArray(@NotNull String[] strArr) {
        Intrinsics.checkNotNullParameter(strArr, "<set-?>");
        this.e = strArr;
    }

    public final void setSelectedPosition(int i) {
        this.d = i;
    }

    public final void setWeatherList(@Nullable List<WeatherDetail> list) {
        this.b = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull WeatherViewHolder holder, int i) {
        List<WeatherDetail> list;
        Intrinsics.checkNotNullParameter(holder, "holder");
        List<WeatherDetail> list2 = this.b;
        Intrinsics.checkNotNull(list2);
        WeatherDetail weatherDetail = list2.get(i);
        ImageView imgWeather = holder.getImgWeather();
        Context context = this.f6176a;
        Intrinsics.checkNotNull(context);
        WeatherViewModel weatherViewModel = this.c;
        List<WeatherDesc> weather = weatherDetail.getWeather();
        Intrinsics.checkNotNull(weather);
        WeatherConditionType.WeatherConditionEnum iconType = weather.get(0).getIconType();
        Intrinsics.checkNotNull(iconType);
        imgWeather.setImageDrawable(context.getDrawable(weatherViewModel.getWeatherForecastDrawable(iconType)));
        Calendar calendar = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(calendar, "getInstance()");
        calendar.setTime(weatherDetail.getDate());
        int i2 = calendar.get(7) - 1;
        if (i2 < 0) {
            i2 += 7;
        }
        holder.getTxtDay().setText(this.e[i2]);
        TextView txtStatus = holder.getTxtStatus();
        List<WeatherDesc> weather2 = weatherDetail.getWeather();
        Intrinsics.checkNotNull(weather2);
        txtStatus.setText(weather2.get(0).getMain());
        TextView txtTemp = holder.getTxtTemp();
        StringBuilder sb = new StringBuilder();
        Double minTemp = weatherDetail.getMinTemp();
        Intrinsics.checkNotNull(minTemp);
        sb.append((int) minTemp.doubleValue());
        sb.append('/');
        Double maxTemp = weatherDetail.getMaxTemp();
        Intrinsics.checkNotNull(maxTemp);
        sb.append((int) maxTemp.doubleValue());
        sb.append(this.c.getTemmpratueUnit());
        txtTemp.setText(sb.toString());
        Intrinsics.checkNotNull(this.b);
        if (i == list.size() - 1) {
            holder.getDivider().setVisibility(8);
        } else {
            holder.getDivider().setVisibility(0);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public WeatherViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        return new WeatherViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weather_detail, parent, false));
    }
}
