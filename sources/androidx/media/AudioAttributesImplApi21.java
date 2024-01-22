package androidx.media;

import android.annotation.TargetApi;
import android.media.AudioAttributes;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.NonNull;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
@TargetApi(21)
/* loaded from: classes.dex */
class AudioAttributesImplApi21 implements AudioAttributesImpl {
    public static Method c;

    /* renamed from: a  reason: collision with root package name */
    public AudioAttributes f1396a;
    public int b;

    public AudioAttributesImplApi21() {
        this.b = -1;
    }

    public static AudioAttributesImpl c(Bundle bundle) {
        AudioAttributes audioAttributes;
        if (bundle == null || (audioAttributes = (AudioAttributes) bundle.getParcelable("androidx.media.audio_attrs.FRAMEWORKS")) == null) {
            return null;
        }
        return new AudioAttributesImplApi21(audioAttributes, bundle.getInt("androidx.media.audio_attrs.LEGACY_STREAM_TYPE", -1));
    }

    public static Method d() {
        try {
            if (c == null) {
                c = AudioAttributes.class.getMethod("toLegacyStreamType", AudioAttributes.class);
            }
            return c;
        } catch (NoSuchMethodException unused) {
            return null;
        }
    }

    @Override // androidx.media.AudioAttributesImpl
    public int a() {
        return this.b;
    }

    @Override // androidx.media.AudioAttributesImpl
    public Object b() {
        return this.f1396a;
    }

    public boolean equals(Object obj) {
        if (obj instanceof AudioAttributesImplApi21) {
            return this.f1396a.equals(((AudioAttributesImplApi21) obj).f1396a);
        }
        return false;
    }

    @Override // androidx.media.AudioAttributesImpl
    public int getContentType() {
        return this.f1396a.getContentType();
    }

    @Override // androidx.media.AudioAttributesImpl
    public int getFlags() {
        return this.f1396a.getFlags();
    }

    @Override // androidx.media.AudioAttributesImpl
    public int getLegacyStreamType() {
        int i = this.b;
        if (i != -1) {
            return i;
        }
        Method d = d();
        if (d == null) {
            Log.w("AudioAttributesCompat21", "No AudioAttributes#toLegacyStreamType() on API: " + Build.VERSION.SDK_INT);
            return -1;
        }
        try {
            return ((Integer) d.invoke(null, this.f1396a)).intValue();
        } catch (IllegalAccessException | InvocationTargetException e) {
            Log.w("AudioAttributesCompat21", "getLegacyStreamType() failed on API: " + Build.VERSION.SDK_INT, e);
            return -1;
        }
    }

    @Override // androidx.media.AudioAttributesImpl
    public int getUsage() {
        return this.f1396a.getUsage();
    }

    @Override // androidx.media.AudioAttributesImpl
    public int getVolumeControlStream() {
        if (Build.VERSION.SDK_INT >= 26) {
            return this.f1396a.getVolumeControlStream();
        }
        return AudioAttributesCompat.c(true, getFlags(), getUsage());
    }

    public int hashCode() {
        return this.f1396a.hashCode();
    }

    @Override // androidx.media.AudioAttributesImpl
    @NonNull
    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("androidx.media.audio_attrs.FRAMEWORKS", this.f1396a);
        int i = this.b;
        if (i != -1) {
            bundle.putInt("androidx.media.audio_attrs.LEGACY_STREAM_TYPE", i);
        }
        return bundle;
    }

    public String toString() {
        return "AudioAttributesCompat: audioattributes=" + this.f1396a;
    }

    public AudioAttributesImplApi21(AudioAttributes audioAttributes) {
        this(audioAttributes, -1);
    }

    public AudioAttributesImplApi21(AudioAttributes audioAttributes, int i) {
        this.b = -1;
        this.f1396a = audioAttributes;
        this.b = i;
    }
}
