package androidx.transition;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
/* loaded from: classes.dex */
public class Scene {

    /* renamed from: a  reason: collision with root package name */
    public Context f1701a;
    public int b;
    public ViewGroup c;
    public View d;
    public Runnable e;
    public Runnable f;

    public Scene(@NonNull ViewGroup viewGroup) {
        this.b = -1;
        this.c = viewGroup;
    }

    public static void b(@NonNull ViewGroup viewGroup, @Nullable Scene scene) {
        viewGroup.setTag(R.id.transition_current_scene, scene);
    }

    @Nullable
    public static Scene getCurrentScene(@NonNull ViewGroup viewGroup) {
        return (Scene) viewGroup.getTag(R.id.transition_current_scene);
    }

    @NonNull
    public static Scene getSceneForLayout(@NonNull ViewGroup viewGroup, @LayoutRes int i, @NonNull Context context) {
        int i2 = R.id.transition_scene_layoutid_cache;
        SparseArray sparseArray = (SparseArray) viewGroup.getTag(i2);
        if (sparseArray == null) {
            sparseArray = new SparseArray();
            viewGroup.setTag(i2, sparseArray);
        }
        Scene scene = (Scene) sparseArray.get(i);
        if (scene != null) {
            return scene;
        }
        Scene scene2 = new Scene(viewGroup, i, context);
        sparseArray.put(i, scene2);
        return scene2;
    }

    public boolean a() {
        return this.b > 0;
    }

    public void enter() {
        if (this.b > 0 || this.d != null) {
            getSceneRoot().removeAllViews();
            if (this.b > 0) {
                LayoutInflater.from(this.f1701a).inflate(this.b, this.c);
            } else {
                this.c.addView(this.d);
            }
        }
        Runnable runnable = this.e;
        if (runnable != null) {
            runnable.run();
        }
        b(this.c, this);
    }

    public void exit() {
        Runnable runnable;
        if (getCurrentScene(this.c) != this || (runnable = this.f) == null) {
            return;
        }
        runnable.run();
    }

    @NonNull
    public ViewGroup getSceneRoot() {
        return this.c;
    }

    public void setEnterAction(@Nullable Runnable runnable) {
        this.e = runnable;
    }

    public void setExitAction(@Nullable Runnable runnable) {
        this.f = runnable;
    }

    public Scene(ViewGroup viewGroup, int i, Context context) {
        this.b = -1;
        this.f1701a = context;
        this.c = viewGroup;
        this.b = i;
    }

    public Scene(@NonNull ViewGroup viewGroup, @NonNull View view) {
        this.b = -1;
        this.c = viewGroup;
        this.d = view;
    }
}
