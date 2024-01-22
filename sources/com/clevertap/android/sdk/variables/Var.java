package com.clevertap.android.sdk.variables;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.Utils;
import com.clevertap.android.sdk.variables.callbacks.VariableCallback;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/* loaded from: classes2.dex */
public class Var<T> {
    public static boolean j;

    /* renamed from: a  reason: collision with root package name */
    public final CTVariables f2694a;
    public String b;
    public String[] c;
    public Double d;
    public T e;
    public T f;
    public String g;
    public boolean h = false;
    public final List<VariableCallback<T>> i = new ArrayList();
    public String stringValue;

    public Var(CTVariables cTVariables) {
        this.f2694a = cTVariables;
    }

    public static void c(String str) {
        Logger.v("variable", str);
    }

    public static <T> Var<T> define(String str, T t, CTVariables cTVariables) {
        return define(str, t, CTVariableUtils.kindFromValue(t), cTVariables);
    }

    public final void a() {
        T t = this.f;
        if (t instanceof String) {
            String str = (String) t;
            this.stringValue = str;
            d(str);
            e(this.d);
        } else if (t instanceof Number) {
            this.stringValue = "" + this.f;
            this.d = Double.valueOf(((Number) this.f).doubleValue());
            e((Number) this.f);
        } else if (t != null && !(t instanceof Iterable) && !(t instanceof Map)) {
            this.stringValue = t.toString();
            this.d = null;
        } else {
            this.stringValue = null;
            this.d = null;
        }
    }

    public void addValueChangedCallback(VariableCallback<T> variableCallback) {
        if (variableCallback == null) {
            c("Invalid callback parameter provided.");
            return;
        }
        synchronized (this.i) {
            this.i.add(variableCallback);
        }
        if (this.f2694a.hasVarsRequestCompleted().booleanValue()) {
            variableCallback.onValueChanged(this);
        }
    }

    public void b() {
        this.h = false;
    }

    public final void d(String str) {
        try {
            this.d = Double.valueOf(str);
        } catch (NumberFormatException unused) {
            this.d = null;
            T t = this.e;
            if (t instanceof Number) {
                this.d = Double.valueOf(((Number) t).doubleValue());
            }
        }
    }

    public T defaultValue() {
        return this.e;
    }

    public final void e(Number number) {
        if (number == null) {
            return;
        }
        T t = this.e;
        if (t instanceof Byte) {
            this.f = (T) Byte.valueOf(number.byteValue());
        } else if (t instanceof Short) {
            this.f = (T) Short.valueOf(number.shortValue());
        } else if (t instanceof Integer) {
            this.f = (T) Integer.valueOf(number.intValue());
        } else if (t instanceof Long) {
            this.f = (T) Long.valueOf(number.longValue());
        } else if (t instanceof Float) {
            this.f = (T) Float.valueOf(number.floatValue());
        } else if (t instanceof Double) {
            this.f = (T) Double.valueOf(number.doubleValue());
        } else if (t instanceof Character) {
            this.f = (T) Character.valueOf((char) number.intValue());
        }
    }

    public final void f() {
        synchronized (this.i) {
            for (VariableCallback<T> variableCallback : this.i) {
                variableCallback.setVariable(this);
                Utils.runOnUiThread(variableCallback);
            }
        }
    }

    public void g() {
        if (this.f2694a.hasVarsRequestCompleted().booleanValue() || j) {
            return;
        }
        c("CleverTap hasn't finished retrieving values from the server. You should use a callback to make sure the value for " + this.b + " is ready. Otherwise, your app may not use the most up-to-date value.");
        j = true;
    }

    public String kind() {
        return this.g;
    }

    public String name() {
        return this.b;
    }

    public String[] nameComponents() {
        return this.c;
    }

    public Number numberValue() {
        g();
        return this.d;
    }

    public void removeValueChangedHandler(VariableCallback<T> variableCallback) {
        synchronized (this.i) {
            this.i.remove(variableCallback);
        }
    }

    public String stringValue() {
        g();
        return this.stringValue;
    }

    @NonNull
    public String toString() {
        return "Var(" + this.b + Constants.SEPARATOR_COMMA + this.f + ")";
    }

    public synchronized void update() {
        T t = this.f;
        T t2 = (T) this.f2694a.b().getMergedValueFromComponentArray(this.c);
        this.f = t2;
        if (t2 == null && t == null) {
            return;
        }
        if (t2 != null && t2.equals(t) && this.h) {
            return;
        }
        a();
        if (this.f2694a.hasVarsRequestCompleted().booleanValue()) {
            this.h = true;
            f();
        }
    }

    public T value() {
        g();
        return this.f;
    }

    public static <T> Var<T> define(String str, T t, String str2, CTVariables cTVariables) {
        if (TextUtils.isEmpty(str)) {
            c("Empty name parameter provided.");
            return null;
        } else if (!str.startsWith(".") && !str.endsWith(".")) {
            Var<T> variable = cTVariables.b().getVariable(str);
            if (variable != null) {
                return variable;
            }
            Var<T> var = new Var<>(cTVariables);
            try {
                var.b = str;
                var.c = CTVariableUtils.getNameComponents(str);
                var.e = t;
                var.f = t;
                var.g = str2;
                var.a();
                cTVariables.b().registerVariable(var);
                var.update();
            } catch (Throwable th) {
                th.printStackTrace();
            }
            return var;
        } else {
            c("Variable name starts or ends with a `.` which is not allowed: " + str);
            return null;
        }
    }
}
