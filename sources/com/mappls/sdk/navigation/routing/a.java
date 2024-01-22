package com.mappls.sdk.navigation.routing;

import android.content.Context;
import androidx.core.app.NotificationCompat;
import com.mappls.sdk.navigation.R;
import com.mappls.sdk.navigation.data.LocationPoint;
/* loaded from: classes11.dex */
public final class a implements LocationPoint {

    /* renamed from: a  reason: collision with root package name */
    public final int f12945a = -1;
    public EnumC0643a b;

    /* JADX WARN: $VALUES field not found */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* renamed from: com.mappls.sdk.navigation.routing.a$a  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public static final class EnumC0643a {
        public static final EnumC0643a b = new EnumC0643a("SPEED_CAMERA", 0, R.string.mappls_traffic_warning_speed_camera);
        public static final EnumC0643a c = new EnumC0643a("SPEED_LIMIT", 1, R.string.mappls_traffic_warning_speed_limit);
        public static final EnumC0643a d = new EnumC0643a("TOLL_BOOTH", 5, R.string.mappls_traffic_warning_payment);
        public static final EnumC0643a e = new EnumC0643a("PEDESTRIAN", 7, R.string.mappls_traffic_warning_pedestrian);

        /* renamed from: a  reason: collision with root package name */
        private int f12946a;

        private EnumC0643a(String str, int i, int i2) {
            this.f12946a = i2;
        }

        public final String a(Context context) {
            return context.getString(this.f12946a);
        }
    }

    public a(EnumC0643a enumC0643a) {
        this.b = enumC0643a;
    }

    public final int a() {
        return this.f12945a;
    }

    public final EnumC0643a b() {
        return this.b;
    }

    @Override // com.mappls.sdk.navigation.data.LocationPoint
    public final double getLatitude() {
        return 0.0d;
    }

    @Override // com.mappls.sdk.navigation.data.LocationPoint
    public final double getLongitude() {
        return 0.0d;
    }

    @Override // com.mappls.sdk.navigation.data.LocationPoint
    public final com.mappls.sdk.navigation.data.a getPointDescription(Context context) {
        return new com.mappls.sdk.navigation.data.a(NotificationCompat.CATEGORY_ALARM, this.b.a(context));
    }
}
