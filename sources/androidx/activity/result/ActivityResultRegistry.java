package androidx.activity.result;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityOptionsCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
/* loaded from: classes.dex */
public abstract class ActivityResultRegistry {

    /* renamed from: a  reason: collision with root package name */
    public Random f353a = new Random();
    public final Map<Integer, String> b = new HashMap();
    public final Map<String, Integer> c = new HashMap();
    public final Map<String, d> d = new HashMap();
    public ArrayList<String> e = new ArrayList<>();
    public final transient Map<String, c<?>> f = new HashMap();
    public final Map<String, Object> g = new HashMap();
    public final Bundle h = new Bundle();

    /* JADX INFO: Add missing generic type declarations: [I] */
    /* loaded from: classes.dex */
    public class a<I> extends ActivityResultLauncher<I> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ String f354a;
        public final /* synthetic */ ActivityResultContract b;

        public a(String str, ActivityResultContract activityResultContract) {
            this.f354a = str;
            this.b = activityResultContract;
        }

        @Override // androidx.activity.result.ActivityResultLauncher
        @NonNull
        public ActivityResultContract<I, ?> getContract() {
            return this.b;
        }

        @Override // androidx.activity.result.ActivityResultLauncher
        public void launch(I i, @Nullable ActivityOptionsCompat activityOptionsCompat) {
            Integer num = ActivityResultRegistry.this.c.get(this.f354a);
            if (num != null) {
                ActivityResultRegistry.this.e.add(this.f354a);
                try {
                    ActivityResultRegistry.this.onLaunch(num.intValue(), this.b, i, activityOptionsCompat);
                    return;
                } catch (Exception e) {
                    ActivityResultRegistry.this.e.remove(this.f354a);
                    throw e;
                }
            }
            throw new IllegalStateException("Attempting to launch an unregistered ActivityResultLauncher with contract " + this.b + " and input " + i + ". You must ensure the ActivityResultLauncher is registered before calling launch().");
        }

        @Override // androidx.activity.result.ActivityResultLauncher
        public void unregister() {
            ActivityResultRegistry.this.e(this.f354a);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [I] */
    /* loaded from: classes.dex */
    public class b<I> extends ActivityResultLauncher<I> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ String f355a;
        public final /* synthetic */ ActivityResultContract b;

        public b(String str, ActivityResultContract activityResultContract) {
            this.f355a = str;
            this.b = activityResultContract;
        }

        @Override // androidx.activity.result.ActivityResultLauncher
        @NonNull
        public ActivityResultContract<I, ?> getContract() {
            return this.b;
        }

        @Override // androidx.activity.result.ActivityResultLauncher
        public void launch(I i, @Nullable ActivityOptionsCompat activityOptionsCompat) {
            Integer num = ActivityResultRegistry.this.c.get(this.f355a);
            if (num != null) {
                ActivityResultRegistry.this.e.add(this.f355a);
                try {
                    ActivityResultRegistry.this.onLaunch(num.intValue(), this.b, i, activityOptionsCompat);
                    return;
                } catch (Exception e) {
                    ActivityResultRegistry.this.e.remove(this.f355a);
                    throw e;
                }
            }
            throw new IllegalStateException("Attempting to launch an unregistered ActivityResultLauncher with contract " + this.b + " and input " + i + ". You must ensure the ActivityResultLauncher is registered before calling launch().");
        }

        @Override // androidx.activity.result.ActivityResultLauncher
        public void unregister() {
            ActivityResultRegistry.this.e(this.f355a);
        }
    }

    /* loaded from: classes.dex */
    public static class c<O> {

        /* renamed from: a  reason: collision with root package name */
        public final ActivityResultCallback<O> f356a;
        public final ActivityResultContract<?, O> b;

        public c(ActivityResultCallback<O> activityResultCallback, ActivityResultContract<?, O> activityResultContract) {
            this.f356a = activityResultCallback;
            this.b = activityResultContract;
        }
    }

    /* loaded from: classes.dex */
    public static class d {

        /* renamed from: a  reason: collision with root package name */
        public final Lifecycle f357a;
        public final ArrayList<LifecycleEventObserver> b = new ArrayList<>();

        public d(@NonNull Lifecycle lifecycle) {
            this.f357a = lifecycle;
        }

        public void a(@NonNull LifecycleEventObserver lifecycleEventObserver) {
            this.f357a.addObserver(lifecycleEventObserver);
            this.b.add(lifecycleEventObserver);
        }

        public void b() {
            Iterator<LifecycleEventObserver> it = this.b.iterator();
            while (it.hasNext()) {
                this.f357a.removeObserver(it.next());
            }
            this.b.clear();
        }
    }

    public final void a(int i, String str) {
        this.b.put(Integer.valueOf(i), str);
        this.c.put(str, Integer.valueOf(i));
    }

    public final <O> void b(String str, int i, @Nullable Intent intent, @Nullable c<O> cVar) {
        if (cVar != null && cVar.f356a != null && this.e.contains(str)) {
            cVar.f356a.onActivityResult(cVar.b.parseResult(i, intent));
            this.e.remove(str);
            return;
        }
        this.g.remove(str);
        this.h.putParcelable(str, new ActivityResult(i, intent));
    }

    public final int c() {
        int nextInt = this.f353a.nextInt(2147418112);
        while (true) {
            int i = nextInt + 65536;
            if (!this.b.containsKey(Integer.valueOf(i))) {
                return i;
            }
            nextInt = this.f353a.nextInt(2147418112);
        }
    }

    public final void d(String str) {
        if (this.c.get(str) != null) {
            return;
        }
        a(c(), str);
    }

    @MainThread
    public final boolean dispatchResult(int i, int i2, @Nullable Intent intent) {
        String str = this.b.get(Integer.valueOf(i));
        if (str == null) {
            return false;
        }
        b(str, i2, intent, this.f.get(str));
        return true;
    }

    @MainThread
    public final void e(@NonNull String str) {
        Integer remove;
        if (!this.e.contains(str) && (remove = this.c.remove(str)) != null) {
            this.b.remove(remove);
        }
        this.f.remove(str);
        if (this.g.containsKey(str)) {
            Log.w("ActivityResultRegistry", "Dropping pending result for request " + str + ": " + this.g.get(str));
            this.g.remove(str);
        }
        if (this.h.containsKey(str)) {
            Log.w("ActivityResultRegistry", "Dropping pending result for request " + str + ": " + this.h.getParcelable(str));
            this.h.remove(str);
        }
        d dVar = this.d.get(str);
        if (dVar != null) {
            dVar.b();
            this.d.remove(str);
        }
    }

    @MainThread
    public abstract <I, O> void onLaunch(int i, @NonNull ActivityResultContract<I, O> activityResultContract, @SuppressLint({"UnknownNullness"}) I i2, @Nullable ActivityOptionsCompat activityOptionsCompat);

    public final void onRestoreInstanceState(@Nullable Bundle bundle) {
        if (bundle == null) {
            return;
        }
        ArrayList<Integer> integerArrayList = bundle.getIntegerArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_RCS");
        ArrayList<String> stringArrayList = bundle.getStringArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_KEYS");
        if (stringArrayList == null || integerArrayList == null) {
            return;
        }
        this.e = bundle.getStringArrayList("KEY_COMPONENT_ACTIVITY_LAUNCHED_KEYS");
        this.f353a = (Random) bundle.getSerializable("KEY_COMPONENT_ACTIVITY_RANDOM_OBJECT");
        this.h.putAll(bundle.getBundle("KEY_COMPONENT_ACTIVITY_PENDING_RESULT"));
        for (int i = 0; i < stringArrayList.size(); i++) {
            String str = stringArrayList.get(i);
            if (this.c.containsKey(str)) {
                Integer remove = this.c.remove(str);
                if (!this.h.containsKey(str)) {
                    this.b.remove(remove);
                }
            }
            a(integerArrayList.get(i).intValue(), stringArrayList.get(i));
        }
    }

    public final void onSaveInstanceState(@NonNull Bundle bundle) {
        bundle.putIntegerArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_RCS", new ArrayList<>(this.c.values()));
        bundle.putStringArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_KEYS", new ArrayList<>(this.c.keySet()));
        bundle.putStringArrayList("KEY_COMPONENT_ACTIVITY_LAUNCHED_KEYS", new ArrayList<>(this.e));
        bundle.putBundle("KEY_COMPONENT_ACTIVITY_PENDING_RESULT", (Bundle) this.h.clone());
        bundle.putSerializable("KEY_COMPONENT_ACTIVITY_RANDOM_OBJECT", this.f353a);
    }

    @NonNull
    public final <I, O> ActivityResultLauncher<I> register(@NonNull final String str, @NonNull LifecycleOwner lifecycleOwner, @NonNull final ActivityResultContract<I, O> activityResultContract, @NonNull final ActivityResultCallback<O> activityResultCallback) {
        Lifecycle lifecycle = lifecycleOwner.getLifecycle();
        if (!lifecycle.getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            d(str);
            d dVar = this.d.get(str);
            if (dVar == null) {
                dVar = new d(lifecycle);
            }
            dVar.a(new LifecycleEventObserver() { // from class: androidx.activity.result.ActivityResultRegistry.1
                @Override // androidx.lifecycle.LifecycleEventObserver
                public void onStateChanged(@NonNull LifecycleOwner lifecycleOwner2, @NonNull Lifecycle.Event event) {
                    if (Lifecycle.Event.ON_START.equals(event)) {
                        ActivityResultRegistry.this.f.put(str, new c<>(activityResultCallback, activityResultContract));
                        if (ActivityResultRegistry.this.g.containsKey(str)) {
                            Object obj = ActivityResultRegistry.this.g.get(str);
                            ActivityResultRegistry.this.g.remove(str);
                            activityResultCallback.onActivityResult(obj);
                        }
                        ActivityResult activityResult = (ActivityResult) ActivityResultRegistry.this.h.getParcelable(str);
                        if (activityResult != null) {
                            ActivityResultRegistry.this.h.remove(str);
                            activityResultCallback.onActivityResult(activityResultContract.parseResult(activityResult.getResultCode(), activityResult.getData()));
                        }
                    } else if (Lifecycle.Event.ON_STOP.equals(event)) {
                        ActivityResultRegistry.this.f.remove(str);
                    } else if (Lifecycle.Event.ON_DESTROY.equals(event)) {
                        ActivityResultRegistry.this.e(str);
                    }
                }
            });
            this.d.put(str, dVar);
            return new a(str, activityResultContract);
        }
        throw new IllegalStateException("LifecycleOwner " + lifecycleOwner + " is attempting to register while current state is " + lifecycle.getCurrentState() + ". LifecycleOwners must call register before they are STARTED.");
    }

    @MainThread
    public final <O> boolean dispatchResult(int i, @SuppressLint({"UnknownNullness"}) O o) {
        ActivityResultCallback<?> activityResultCallback;
        String str = this.b.get(Integer.valueOf(i));
        if (str == null) {
            return false;
        }
        c<?> cVar = this.f.get(str);
        if (cVar != null && (activityResultCallback = cVar.f356a) != null) {
            if (this.e.remove(str)) {
                activityResultCallback.onActivityResult(o);
                return true;
            }
            return true;
        }
        this.h.remove(str);
        this.g.put(str, o);
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NonNull
    public final <I, O> ActivityResultLauncher<I> register(@NonNull String str, @NonNull ActivityResultContract<I, O> activityResultContract, @NonNull ActivityResultCallback<O> activityResultCallback) {
        d(str);
        this.f.put(str, new c<>(activityResultCallback, activityResultContract));
        if (this.g.containsKey(str)) {
            Object obj = this.g.get(str);
            this.g.remove(str);
            activityResultCallback.onActivityResult(obj);
        }
        ActivityResult activityResult = (ActivityResult) this.h.getParcelable(str);
        if (activityResult != null) {
            this.h.remove(str);
            activityResultCallback.onActivityResult(activityResultContract.parseResult(activityResult.getResultCode(), activityResult.getData()));
        }
        return new b(str, activityResultContract);
    }
}
