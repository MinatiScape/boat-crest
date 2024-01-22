package butterknife;

import androidx.annotation.UiThread;
/* loaded from: classes.dex */
public interface Unbinder {
    public static final Unbinder EMPTY = new Unbinder() { // from class: butterknife.a
        @Override // butterknife.Unbinder
        public final void unbind() {
            Unbinder.a();
        }
    };

    static /* synthetic */ void a() {
    }

    @UiThread
    void unbind();
}
