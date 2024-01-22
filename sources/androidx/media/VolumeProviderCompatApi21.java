package androidx.media;

import android.media.VolumeProvider;
import androidx.annotation.RequiresApi;
@RequiresApi(21)
/* loaded from: classes.dex */
public class VolumeProviderCompatApi21 {

    /* loaded from: classes.dex */
    public interface Delegate {
        void onAdjustVolume(int i);

        void onSetVolumeTo(int i);
    }

    /* loaded from: classes.dex */
    public static class a extends VolumeProvider {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Delegate f1414a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(int i, int i2, int i3, Delegate delegate) {
            super(i, i2, i3);
            this.f1414a = delegate;
        }

        @Override // android.media.VolumeProvider
        public void onAdjustVolume(int i) {
            this.f1414a.onAdjustVolume(i);
        }

        @Override // android.media.VolumeProvider
        public void onSetVolumeTo(int i) {
            this.f1414a.onSetVolumeTo(i);
        }
    }

    public static Object a(int i, int i2, int i3, Delegate delegate) {
        return new a(i, i2, i3, delegate);
    }

    public static void b(Object obj, int i) {
        ((VolumeProvider) obj).setCurrentVolume(i);
    }
}
