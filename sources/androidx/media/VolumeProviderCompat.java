package androidx.media;

import android.os.Build;
import androidx.annotation.RestrictTo;
import androidx.media.VolumeProviderCompatApi21;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: classes.dex */
public abstract class VolumeProviderCompat {
    public static final int VOLUME_CONTROL_ABSOLUTE = 2;
    public static final int VOLUME_CONTROL_FIXED = 0;
    public static final int VOLUME_CONTROL_RELATIVE = 1;

    /* renamed from: a  reason: collision with root package name */
    public final int f1412a;
    public final int b;
    public int c;
    public Callback d;
    public Object e;

    /* loaded from: classes.dex */
    public static abstract class Callback {
        public abstract void onVolumeChanged(VolumeProviderCompat volumeProviderCompat);
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes.dex */
    public @interface ControlType {
    }

    /* loaded from: classes.dex */
    public class a implements VolumeProviderCompatApi21.Delegate {
        public a() {
        }

        @Override // androidx.media.VolumeProviderCompatApi21.Delegate
        public void onAdjustVolume(int i) {
            VolumeProviderCompat.this.onAdjustVolume(i);
        }

        @Override // androidx.media.VolumeProviderCompatApi21.Delegate
        public void onSetVolumeTo(int i) {
            VolumeProviderCompat.this.onSetVolumeTo(i);
        }
    }

    public VolumeProviderCompat(int i, int i2, int i3) {
        this.f1412a = i;
        this.b = i2;
        this.c = i3;
    }

    public final int getCurrentVolume() {
        return this.c;
    }

    public final int getMaxVolume() {
        return this.b;
    }

    public final int getVolumeControl() {
        return this.f1412a;
    }

    public Object getVolumeProvider() {
        if (this.e == null && Build.VERSION.SDK_INT >= 21) {
            this.e = VolumeProviderCompatApi21.a(this.f1412a, this.b, this.c, new a());
        }
        return this.e;
    }

    public void onAdjustVolume(int i) {
    }

    public void onSetVolumeTo(int i) {
    }

    public void setCallback(Callback callback) {
        this.d = callback;
    }

    public final void setCurrentVolume(int i) {
        this.c = i;
        Object volumeProvider = getVolumeProvider();
        if (volumeProvider != null && Build.VERSION.SDK_INT >= 21) {
            VolumeProviderCompatApi21.b(volumeProvider, i);
        }
        Callback callback = this.d;
        if (callback != null) {
            callback.onVolumeChanged(this);
        }
    }
}
