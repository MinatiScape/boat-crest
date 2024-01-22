package com.clevertap.android.sdk.variables;

import android.text.TextUtils;
import androidx.annotation.VisibleForTesting;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.variables.annotations.Variable;
import com.clevertap.android.sdk.variables.callbacks.VariableCallback;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.Map;
/* loaded from: classes2.dex */
public class Parser {

    /* renamed from: a  reason: collision with root package name */
    public final CTVariables f2693a;

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes2.dex */
    public class a<T> extends VariableCallback<T> {
        public final /* synthetic */ WeakReference i;
        public final /* synthetic */ boolean j;
        public final /* synthetic */ Field k;
        public final /* synthetic */ Var l;

        public a(Parser parser, WeakReference weakReference, boolean z, Field field, Var var) {
            this.i = weakReference;
            this.j = z;
            this.k = field;
            this.l = var;
        }

        @Override // com.clevertap.android.sdk.variables.callbacks.VariableCallback
        public void onValueChanged(Var<T> var) {
            Field field;
            Object obj = this.i.get();
            if ((this.j && obj == null) || (field = this.k) == null) {
                this.l.removeValueChangedHandler(this);
                return;
            }
            try {
                boolean isAccessible = field.isAccessible();
                if (!isAccessible) {
                    this.k.setAccessible(true);
                }
                this.k.set(obj, this.l.value());
                if (isAccessible) {
                    return;
                }
                this.k.setAccessible(false);
            } catch (IllegalAccessException e) {
                Parser.d("Error setting value for field " + this.l.name(), e);
            } catch (IllegalArgumentException e2) {
                Parser.d("Invalid value " + this.l.value() + " for field " + this.l.name(), e2);
            }
        }
    }

    public Parser(CTVariables cTVariables) {
        this.f2693a = cTVariables;
    }

    public static void c(String str) {
        Logger.v("variables", str);
    }

    public static void d(String str, Throwable th) {
        Logger.v("variables", str, th);
    }

    @VisibleForTesting
    public <T> void b(Object obj, String str, T t, String str2, Field field) {
        Var define = Var.define(str, t, str2, this.f2693a);
        if (define == null) {
            c("Something went wrong, var is null, returning");
            return;
        }
        define.addValueChangedCallback(new a(this, new WeakReference(obj), obj != null, field, define));
    }

    @VisibleForTesting
    public void e(Object obj, Class<?> cls) {
        Field[] fields;
        String str;
        try {
            for (Field field : cls.getFields()) {
                if (field.isAnnotationPresent(Variable.class)) {
                    Variable variable = (Variable) field.getAnnotation(Variable.class);
                    String str2 = "";
                    if (variable != null) {
                        String group = variable.group();
                        str2 = variable.name();
                        str = group;
                    } else {
                        str = "";
                    }
                    if (TextUtils.isEmpty(str2)) {
                        str2 = field.getName();
                    }
                    String str3 = TextUtils.isEmpty(str) ? str2 : str + "." + str2;
                    Class<?> type = field.getType();
                    String cls2 = type.toString();
                    if (cls2.equals("int")) {
                        b(obj, str3, Integer.valueOf(field.getInt(obj)), CTVariableUtils.NUMBER, field);
                    } else if (cls2.equals("byte")) {
                        b(obj, str3, Byte.valueOf(field.getByte(obj)), CTVariableUtils.NUMBER, field);
                    } else if (cls2.equals("short")) {
                        b(obj, str3, Short.valueOf(field.getShort(obj)), CTVariableUtils.NUMBER, field);
                    } else if (cls2.equals("long")) {
                        b(obj, str3, Long.valueOf(field.getLong(obj)), CTVariableUtils.NUMBER, field);
                    } else if (cls2.equals("char")) {
                        b(obj, str3, Character.valueOf(field.getChar(obj)), CTVariableUtils.NUMBER, field);
                    } else if (cls2.equals(TypedValues.Custom.S_FLOAT)) {
                        b(obj, str3, Float.valueOf(field.getFloat(obj)), CTVariableUtils.NUMBER, field);
                    } else if (cls2.equals("double")) {
                        b(obj, str3, Double.valueOf(field.getDouble(obj)), CTVariableUtils.NUMBER, field);
                    } else if (cls2.equals("boolean")) {
                        b(obj, str3, Boolean.valueOf(field.getBoolean(obj)), "boolean", field);
                    } else if (type.isPrimitive()) {
                        c("Variable " + str3 + " is an unsupported primitive type.");
                    } else if (type.isArray()) {
                        c("Variable " + str3 + " is an unsupported type of Array.");
                    } else if (Map.class.isAssignableFrom(type)) {
                        b(obj, str3, field.get(obj), "group", field);
                    } else {
                        Object obj2 = field.get(obj);
                        b(obj, str3, obj2 == null ? null : obj2.toString(), "string", field);
                    }
                }
            }
        } catch (Throwable th) {
            d("Error parsing variables:", th);
            th.printStackTrace();
        }
    }

    public void parseVariables(Object... objArr) {
        try {
            for (Object obj : objArr) {
                e(obj, obj.getClass());
            }
        } catch (Throwable th) {
            d("Error parsing variables", th);
        }
    }

    public void parseVariablesForClasses(Class<?>... clsArr) {
        try {
            for (Class<?> cls : clsArr) {
                e(null, cls);
            }
        } catch (Throwable th) {
            d("Error parsing variables", th);
        }
    }
}
