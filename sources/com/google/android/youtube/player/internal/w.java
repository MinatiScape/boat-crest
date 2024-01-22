package com.google.android.youtube.player.internal;

import android.app.Activity;
import android.content.Context;
import android.os.IBinder;
import com.google.android.youtube.player.internal.d;
import java.lang.reflect.InvocationTargetException;
/* loaded from: classes10.dex */
public final class w {

    /* loaded from: classes10.dex */
    public static final class a extends Exception {
        public a(String str) {
            super(str);
        }

        public a(String str, Throwable th) {
            super(str, th);
        }
    }

    public static IBinder a(Class<?> cls, IBinder iBinder, IBinder iBinder2, IBinder iBinder3, boolean z) throws a {
        try {
            return (IBinder) cls.getConstructor(IBinder.class, IBinder.class, IBinder.class, Boolean.TYPE).newInstance(iBinder, iBinder2, iBinder3, Boolean.valueOf(z));
        } catch (IllegalAccessException e) {
            String name = cls.getName();
            throw new a(name.length() != 0 ? "Unable to call the default constructor of ".concat(name) : new String("Unable to call the default constructor of "), e);
        } catch (InstantiationException e2) {
            String name2 = cls.getName();
            throw new a(name2.length() != 0 ? "Unable to instantiate the dynamic class ".concat(name2) : new String("Unable to instantiate the dynamic class "), e2);
        } catch (NoSuchMethodException e3) {
            String name3 = cls.getName();
            throw new a(name3.length() != 0 ? "Could not find the right constructor for ".concat(name3) : new String("Could not find the right constructor for "), e3);
        } catch (InvocationTargetException e4) {
            String name4 = cls.getName();
            throw new a(name4.length() != 0 ? "Exception thrown by invoked constructor in ".concat(name4) : new String("Exception thrown by invoked constructor in "), e4);
        }
    }

    public static d a(Activity activity, IBinder iBinder, boolean z) throws a {
        ab.a(activity);
        ab.a(iBinder);
        Context b = z.b(activity);
        if (b != null) {
            return d.a.a(b(b.getClassLoader(), "com.google.android.youtube.api.jar.client.RemoteEmbeddedPlayer", v.a(b).asBinder(), v.a(activity).asBinder(), iBinder, z));
        }
        throw new a("Could not create remote context");
    }

    public static IBinder b(ClassLoader classLoader, String str, IBinder iBinder, IBinder iBinder2, IBinder iBinder3, boolean z) throws a {
        try {
            return a(classLoader.loadClass(str), iBinder, iBinder2, iBinder3, z);
        } catch (ClassNotFoundException e) {
            String valueOf = String.valueOf(str);
            throw new a(valueOf.length() != 0 ? "Unable to find dynamic class ".concat(valueOf) : new String("Unable to find dynamic class "), e);
        }
    }
}
