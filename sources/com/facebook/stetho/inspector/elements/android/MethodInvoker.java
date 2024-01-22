package com.facebook.stetho.inspector.elements.android;

import com.facebook.stetho.common.LogUtil;
import com.facebook.stetho.common.Util;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
/* loaded from: classes9.dex */
public class MethodInvoker {
    private static final List<TypedMethodInvoker<?>> invokers = Arrays.asList(new StringMethodInvoker(), new CharSequenceMethodInvoker(), new IntegerMethodInvoker(), new FloatMethodInvoker(), new BooleanMethodInvoker());

    /* loaded from: classes9.dex */
    public static class BooleanMethodInvoker extends TypedMethodInvoker<Boolean> {
        public BooleanMethodInvoker() {
            super(Boolean.TYPE);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.facebook.stetho.inspector.elements.android.MethodInvoker.TypedMethodInvoker
        public Boolean convertArgument(String str) {
            return Boolean.valueOf(Boolean.parseBoolean(str));
        }
    }

    /* loaded from: classes9.dex */
    public static class CharSequenceMethodInvoker extends TypedMethodInvoker<CharSequence> {
        public CharSequenceMethodInvoker() {
            super(CharSequence.class);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.facebook.stetho.inspector.elements.android.MethodInvoker.TypedMethodInvoker
        public CharSequence convertArgument(String str) {
            return str;
        }
    }

    /* loaded from: classes9.dex */
    public static class FloatMethodInvoker extends TypedMethodInvoker<Float> {
        public FloatMethodInvoker() {
            super(Float.TYPE);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.facebook.stetho.inspector.elements.android.MethodInvoker.TypedMethodInvoker
        public Float convertArgument(String str) {
            return Float.valueOf(Float.parseFloat(str));
        }
    }

    /* loaded from: classes9.dex */
    public static class IntegerMethodInvoker extends TypedMethodInvoker<Integer> {
        public IntegerMethodInvoker() {
            super(Integer.TYPE);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.facebook.stetho.inspector.elements.android.MethodInvoker.TypedMethodInvoker
        public Integer convertArgument(String str) {
            return Integer.valueOf(Integer.parseInt(str));
        }
    }

    /* loaded from: classes9.dex */
    public static class StringMethodInvoker extends TypedMethodInvoker<String> {
        public StringMethodInvoker() {
            super(String.class);
        }

        @Override // com.facebook.stetho.inspector.elements.android.MethodInvoker.TypedMethodInvoker
        public String convertArgument(String str) {
            return str;
        }
    }

    /* loaded from: classes9.dex */
    public static abstract class TypedMethodInvoker<T> {
        private final Class<T> mArgType;

        public TypedMethodInvoker(Class<T> cls) {
            this.mArgType = cls;
        }

        public abstract T convertArgument(String str);

        public boolean invoke(Object obj, String str, String str2) {
            try {
                obj.getClass().getMethod(str, this.mArgType).invoke(obj, convertArgument(str2));
                return true;
            } catch (IllegalAccessException e) {
                LogUtil.w("IllegalAccessException: " + e.getMessage());
                return false;
            } catch (IllegalArgumentException e2) {
                LogUtil.w("IllegalArgumentException: " + e2.getMessage());
                return false;
            } catch (NoSuchMethodException unused) {
                return false;
            } catch (InvocationTargetException e3) {
                LogUtil.w("InvocationTargetException: " + e3.getMessage());
                return false;
            }
        }
    }

    public void invoke(Object obj, String str, String str2) {
        Util.throwIfNull(obj, str, str2);
        int size = invokers.size();
        for (int i = 0; i < size; i++) {
            if (invokers.get(i).invoke(obj, str, str2)) {
                return;
            }
        }
        LogUtil.w("Method with name " + str + " not found for any of the MethodInvoker supported argument types.");
    }
}
