package butterknife.internal;

import android.view.View;
/* loaded from: classes.dex */
public abstract class DebouncingOnClickListener implements View.OnClickListener {
    public static boolean h = true;
    public static final Runnable i = new Runnable() { // from class: butterknife.internal.a
        @Override // java.lang.Runnable
        public final void run() {
            DebouncingOnClickListener.h = true;
        }
    };

    public abstract void doClick(View view);

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        if (h) {
            h = false;
            view.post(i);
            doClick(view);
        }
    }
}
