package com.coveiot.android.crpsdk.model;

import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.android.gms.fitness.FitnessActivities;
import java.io.Serializable;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0011\b\u0086\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\b\u001a\u00020\u0002\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\n\u001a\u00020\u0002\u0012\u0006\u0010\u000b\u001a\u00020\u0002\u0012\u0006\u0010\f\u001a\u00020\u0002¢\u0006\u0004\b\"\u0010#J\t\u0010\u0003\u001a\u00020\u0002HÆ\u0003J\t\u0010\u0004\u001a\u00020\u0002HÆ\u0003J\t\u0010\u0005\u001a\u00020\u0002HÆ\u0003J\t\u0010\u0006\u001a\u00020\u0002HÆ\u0003J\t\u0010\u0007\u001a\u00020\u0002HÆ\u0003J;\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\b\u001a\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\u00022\b\b\u0002\u0010\n\u001a\u00020\u00022\b\b\u0002\u0010\u000b\u001a\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\u0002HÆ\u0001J\t\u0010\u000f\u001a\u00020\u000eHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0002HÖ\u0001J\u0013\u0010\u0014\u001a\u00020\u00132\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011HÖ\u0003R\"\u0010\b\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\b\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\"\u0010\t\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\t\u0010\u0015\u001a\u0004\b\u001a\u0010\u0017\"\u0004\b\u001b\u0010\u0019R\"\u0010\n\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\n\u0010\u0015\u001a\u0004\b\u001c\u0010\u0017\"\u0004\b\u001d\u0010\u0019R\"\u0010\u000b\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u000b\u0010\u0015\u001a\u0004\b\u001e\u0010\u0017\"\u0004\b\u001f\u0010\u0019R\"\u0010\f\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\f\u0010\u0015\u001a\u0004\b \u0010\u0017\"\u0004\b!\u0010\u0019¨\u0006$"}, d2 = {"Lcom/coveiot/android/crpsdk/model/KhCRPStepInfo;", "Ljava/io/Serializable;", "", "component1", "component2", "component3", "component4", "component5", NotificationCompat.MessagingStyle.Message.KEY_TIMESTAMP, "steps", "distance", "calories", "dayType", Constants.COPY_TYPE, "", "toString", "hashCode", "", FitnessActivities.OTHER, "", "equals", "I", "getTime", "()I", "setTime", "(I)V", "getSteps", "setSteps", "getDistance", "setDistance", "getCalories", "setCalories", "getDayType", "setDayType", "<init>", "(IIIII)V", "crpSdk_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes3.dex */
public final class KhCRPStepInfo implements Serializable {

    /* renamed from: a  reason: collision with root package name */
    public int f4122a;
    public int b;
    public int c;
    public int d;
    public int e;

    public KhCRPStepInfo(int i, int i2, int i3, int i4, int i5) {
        this.f4122a = i;
        this.b = i2;
        this.c = i3;
        this.d = i4;
        this.e = i5;
    }

    public static /* synthetic */ KhCRPStepInfo copy$default(KhCRPStepInfo khCRPStepInfo, int i, int i2, int i3, int i4, int i5, int i6, Object obj) {
        if ((i6 & 1) != 0) {
            i = khCRPStepInfo.f4122a;
        }
        if ((i6 & 2) != 0) {
            i2 = khCRPStepInfo.b;
        }
        int i7 = i2;
        if ((i6 & 4) != 0) {
            i3 = khCRPStepInfo.c;
        }
        int i8 = i3;
        if ((i6 & 8) != 0) {
            i4 = khCRPStepInfo.d;
        }
        int i9 = i4;
        if ((i6 & 16) != 0) {
            i5 = khCRPStepInfo.e;
        }
        return khCRPStepInfo.copy(i, i7, i8, i9, i5);
    }

    public final int component1() {
        return this.f4122a;
    }

    public final int component2() {
        return this.b;
    }

    public final int component3() {
        return this.c;
    }

    public final int component4() {
        return this.d;
    }

    public final int component5() {
        return this.e;
    }

    @NotNull
    public final KhCRPStepInfo copy(int i, int i2, int i3, int i4, int i5) {
        return new KhCRPStepInfo(i, i2, i3, i4, i5);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof KhCRPStepInfo) {
            KhCRPStepInfo khCRPStepInfo = (KhCRPStepInfo) obj;
            return this.f4122a == khCRPStepInfo.f4122a && this.b == khCRPStepInfo.b && this.c == khCRPStepInfo.c && this.d == khCRPStepInfo.d && this.e == khCRPStepInfo.e;
        }
        return false;
    }

    public final int getCalories() {
        return this.d;
    }

    public final int getDayType() {
        return this.e;
    }

    public final int getDistance() {
        return this.c;
    }

    public final int getSteps() {
        return this.b;
    }

    public final int getTime() {
        return this.f4122a;
    }

    public int hashCode() {
        return (((((((Integer.hashCode(this.f4122a) * 31) + Integer.hashCode(this.b)) * 31) + Integer.hashCode(this.c)) * 31) + Integer.hashCode(this.d)) * 31) + Integer.hashCode(this.e);
    }

    public final void setCalories(int i) {
        this.d = i;
    }

    public final void setDayType(int i) {
        this.e = i;
    }

    public final void setDistance(int i) {
        this.c = i;
    }

    public final void setSteps(int i) {
        this.b = i;
    }

    public final void setTime(int i) {
        this.f4122a = i;
    }

    @NotNull
    public String toString() {
        return "KhCRPStepInfo(time=" + this.f4122a + ", steps=" + this.b + ", distance=" + this.c + ", calories=" + this.d + ", dayType=" + this.e + HexStringBuilder.COMMENT_END_CHAR;
    }
}
