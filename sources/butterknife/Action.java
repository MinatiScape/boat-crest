package butterknife;

import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
/* loaded from: classes.dex */
public interface Action<T extends View> {
    @UiThread
    void apply(@NonNull T t, int i);
}
