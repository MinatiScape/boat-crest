package com.coveiot.android.leonardo.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import com.coveiot.android.boat.R;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.data.DeviceModelBean;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class MoreOptionsUtil {
    @NotNull
    public static final MoreOptionsUtil INSTANCE = new MoreOptionsUtil();

    /* loaded from: classes5.dex */
    public static final class Option {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final String f5427a;
        @NotNull
        public final Drawable b;

        public Option(@NotNull String name, @NotNull Drawable drawable) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(drawable, "drawable");
            this.f5427a = name;
            this.b = drawable;
        }

        public static /* synthetic */ Option copy$default(Option option, String str, Drawable drawable, int i, Object obj) {
            if ((i & 1) != 0) {
                str = option.f5427a;
            }
            if ((i & 2) != 0) {
                drawable = option.b;
            }
            return option.copy(str, drawable);
        }

        @NotNull
        public final String component1() {
            return this.f5427a;
        }

        @NotNull
        public final Drawable component2() {
            return this.b;
        }

        @NotNull
        public final Option copy(@NotNull String name, @NotNull Drawable drawable) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(drawable, "drawable");
            return new Option(name, drawable);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Option) {
                Option option = (Option) obj;
                return Intrinsics.areEqual(this.f5427a, option.f5427a) && Intrinsics.areEqual(this.b, option.b);
            }
            return false;
        }

        @NotNull
        public final Drawable getDrawable() {
            return this.b;
        }

        @NotNull
        public final String getName() {
            return this.f5427a;
        }

        public int hashCode() {
            return (this.f5427a.hashCode() * 31) + this.b.hashCode();
        }

        @NotNull
        public String toString() {
            return "Option(name=" + this.f5427a + ", drawable=" + this.b + HexStringBuilder.COMMENT_END_CHAR;
        }
    }

    @NotNull
    public final List<Option> get(@NotNull Context context, @NotNull Control control) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(control, "control");
        ArrayList arrayList = new ArrayList();
        if (control.isMyProfile()) {
            String string = context.getString(R.string.my_profile);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.my_profile)");
            Drawable drawable = context.getDrawable(2131232331);
            Intrinsics.checkNotNull(drawable);
            arrayList.add(new Option(string, drawable));
        }
        if (control.isDeviceFeature()) {
            DeviceModelBean deviceModelBean = SessionManager.getInstance(context).getDeviceModelBean();
            String str = null;
            if ((deviceModelBean != null ? deviceModelBean.getName() : null) != null) {
                DeviceModelBean deviceModelBean2 = SessionManager.getInstance(context).getDeviceModelBean();
                if (deviceModelBean2 != null) {
                    str = deviceModelBean2.getName();
                }
            } else {
                str = DeviceUtils.Companion.getModelNumber(context);
            }
            Drawable drawable2 = context.getDrawable(R.drawable.ic_watch_feature);
            Intrinsics.checkNotNull(drawable2);
            arrayList.add(new Option(str + ' ' + context.getString(R.string.features), drawable2));
        }
        if (control.isSettings()) {
            String string2 = context.getString(R.string.settings);
            Intrinsics.checkNotNullExpressionValue(string2, "context.getString(R.string.settings)");
            Drawable drawable3 = context.getDrawable(2131232256);
            Intrinsics.checkNotNull(drawable3);
            arrayList.add(new Option(string2, drawable3));
        }
        if (control.isAboutYourDevice()) {
            String string3 = context.getString(R.string.abt_your_device);
            Intrinsics.checkNotNullExpressionValue(string3, "context.getString(R.string.abt_your_device)");
            Drawable drawable4 = context.getDrawable(2131231859);
            Intrinsics.checkNotNull(drawable4);
            arrayList.add(new Option(string3, drawable4));
        }
        if (control.isHelpFeedback()) {
            String string4 = context.getString(R.string.help_feedback);
            Intrinsics.checkNotNullExpressionValue(string4, "context.getString(R.string.help_feedback)");
            Drawable drawable5 = context.getDrawable(2131231848);
            Intrinsics.checkNotNull(drawable5);
            arrayList.add(new Option(string4, drawable5));
        }
        if (control.isFitnessReport()) {
            String string5 = context.getString(R.string.fitness_report);
            Intrinsics.checkNotNullExpressionValue(string5, "context.getString(R.string.fitness_report)");
            Drawable drawable6 = context.getDrawable(R.drawable.fitness_report_image);
            Intrinsics.checkNotNull(drawable6);
            arrayList.add(new Option(string5, drawable6));
        }
        if (control.isAboutUs()) {
            String string6 = context.getString(R.string.about_us);
            Intrinsics.checkNotNullExpressionValue(string6, "context.getString(R.string.about_us)");
            Drawable drawable7 = context.getDrawable(2131231349);
            Intrinsics.checkNotNull(drawable7);
            arrayList.add(new Option(string6, drawable7));
        }
        if (control.isLogout()) {
            String string7 = context.getString(R.string.logout);
            Intrinsics.checkNotNullExpressionValue(string7, "context.getString(R.string.logout)");
            Drawable drawable8 = context.getDrawable(R.drawable.logout);
            Intrinsics.checkNotNull(drawable8);
            arrayList.add(new Option(string7, drawable8));
        }
        return arrayList;
    }

    /* loaded from: classes5.dex */
    public static final class Control {

        /* renamed from: a  reason: collision with root package name */
        public boolean f5426a;
        public boolean b;
        public boolean c;
        public boolean d;
        public boolean e;
        public boolean f;
        public boolean g;
        public boolean h;

        public Control() {
            this(false, false, false, false, false, false, false, false, 255, null);
        }

        public Control(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8) {
            this.f5426a = z;
            this.b = z2;
            this.c = z3;
            this.d = z4;
            this.e = z5;
            this.f = z6;
            this.g = z7;
            this.h = z8;
        }

        public final boolean component1() {
            return this.f5426a;
        }

        public final boolean component2() {
            return this.b;
        }

        public final boolean component3() {
            return this.c;
        }

        public final boolean component4() {
            return this.d;
        }

        public final boolean component5() {
            return this.e;
        }

        public final boolean component6() {
            return this.f;
        }

        public final boolean component7() {
            return this.g;
        }

        public final boolean component8() {
            return this.h;
        }

        @NotNull
        public final Control copy(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8) {
            return new Control(z, z2, z3, z4, z5, z6, z7, z8);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Control) {
                Control control = (Control) obj;
                return this.f5426a == control.f5426a && this.b == control.b && this.c == control.c && this.d == control.d && this.e == control.e && this.f == control.f && this.g == control.g && this.h == control.h;
            }
            return false;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v1, types: [int] */
        /* JADX WARN: Type inference failed for: r0v16 */
        /* JADX WARN: Type inference failed for: r0v17 */
        /* JADX WARN: Type inference failed for: r2v0, types: [boolean] */
        /* JADX WARN: Type inference failed for: r2v10, types: [boolean] */
        /* JADX WARN: Type inference failed for: r2v2, types: [boolean] */
        /* JADX WARN: Type inference failed for: r2v4, types: [boolean] */
        /* JADX WARN: Type inference failed for: r2v6, types: [boolean] */
        /* JADX WARN: Type inference failed for: r2v8, types: [boolean] */
        public int hashCode() {
            boolean z = this.f5426a;
            ?? r0 = z;
            if (z) {
                r0 = 1;
            }
            int i = r0 * 31;
            ?? r2 = this.b;
            int i2 = r2;
            if (r2 != 0) {
                i2 = 1;
            }
            int i3 = (i + i2) * 31;
            ?? r22 = this.c;
            int i4 = r22;
            if (r22 != 0) {
                i4 = 1;
            }
            int i5 = (i3 + i4) * 31;
            ?? r23 = this.d;
            int i6 = r23;
            if (r23 != 0) {
                i6 = 1;
            }
            int i7 = (i5 + i6) * 31;
            ?? r24 = this.e;
            int i8 = r24;
            if (r24 != 0) {
                i8 = 1;
            }
            int i9 = (i7 + i8) * 31;
            ?? r25 = this.f;
            int i10 = r25;
            if (r25 != 0) {
                i10 = 1;
            }
            int i11 = (i9 + i10) * 31;
            ?? r26 = this.g;
            int i12 = r26;
            if (r26 != 0) {
                i12 = 1;
            }
            int i13 = (i11 + i12) * 31;
            boolean z2 = this.h;
            return i13 + (z2 ? 1 : z2 ? 1 : 0);
        }

        public final boolean isAboutUs() {
            return this.g;
        }

        public final boolean isAboutYourDevice() {
            return this.d;
        }

        public final boolean isDeviceFeature() {
            return this.b;
        }

        public final boolean isFitnessReport() {
            return this.f;
        }

        public final boolean isHelpFeedback() {
            return this.e;
        }

        public final boolean isLogout() {
            return this.h;
        }

        public final boolean isMyProfile() {
            return this.f5426a;
        }

        public final boolean isSettings() {
            return this.c;
        }

        public final void setAboutUs(boolean z) {
            this.g = z;
        }

        public final void setAboutYourDevice(boolean z) {
            this.d = z;
        }

        public final void setDeviceFeature(boolean z) {
            this.b = z;
        }

        public final void setFitnessReport(boolean z) {
            this.f = z;
        }

        public final void setHelpFeedback(boolean z) {
            this.e = z;
        }

        public final void setLogout(boolean z) {
            this.h = z;
        }

        public final void setMyProfile(boolean z) {
            this.f5426a = z;
        }

        public final void setSettings(boolean z) {
            this.c = z;
        }

        @NotNull
        public String toString() {
            return "Control(isMyProfile=" + this.f5426a + ", isDeviceFeature=" + this.b + ", isSettings=" + this.c + ", isAboutYourDevice=" + this.d + ", isHelpFeedback=" + this.e + ", isFitnessReport=" + this.f + ", isAboutUs=" + this.g + ", isLogout=" + this.h + HexStringBuilder.COMMENT_END_CHAR;
        }

        public /* synthetic */ Control(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? true : z, (i & 2) != 0 ? true : z2, (i & 4) != 0 ? true : z3, (i & 8) != 0 ? true : z4, (i & 16) != 0 ? true : z5, (i & 32) != 0 ? true : z6, (i & 64) != 0 ? true : z7, (i & 128) == 0 ? z8 : true);
        }
    }
}
