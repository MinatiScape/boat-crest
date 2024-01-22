package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.View;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.motion.utils.ViewSpline;
import androidx.constraintlayout.widget.ConstraintAttribute;
import androidx.constraintlayout.widget.R;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
/* loaded from: classes.dex */
public class KeyTrigger extends Key {
    public static final String CROSS = "CROSS";
    public static final int KEY_TYPE = 5;
    public static final String NEGATIVE_CROSS = "negativeCross";
    public static final String POSITIVE_CROSS = "positiveCross";
    public static final String POST_LAYOUT = "postLayout";
    public static final String TRIGGER_COLLISION_ID = "triggerCollisionId";
    public static final String TRIGGER_COLLISION_VIEW = "triggerCollisionView";
    public static final String TRIGGER_ID = "triggerID";
    public static final String TRIGGER_RECEIVER = "triggerReceiver";
    public static final String TRIGGER_SLACK = "triggerSlack";
    public static final String VIEW_TRANSITION_ON_CROSS = "viewTransitionOnCross";
    public static final String VIEW_TRANSITION_ON_NEGATIVE_CROSS = "viewTransitionOnNegativeCross";
    public static final String VIEW_TRANSITION_ON_POSITIVE_CROSS = "viewTransitionOnPositiveCross";
    public int e = -1;
    public String f = null;
    public int g;
    public String h;
    public String i;
    public int j;
    public int k;
    public View l;
    public float m;
    public boolean n;
    public boolean o;
    public boolean p;
    public float q;
    public float r;
    public boolean s;
    public int t;
    public int u;
    public int v;
    public RectF w;
    public RectF x;
    public HashMap<String, Method> y;

    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public static SparseIntArray f945a;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            f945a = sparseIntArray;
            sparseIntArray.append(R.styleable.KeyTrigger_framePosition, 8);
            f945a.append(R.styleable.KeyTrigger_onCross, 4);
            f945a.append(R.styleable.KeyTrigger_onNegativeCross, 1);
            f945a.append(R.styleable.KeyTrigger_onPositiveCross, 2);
            f945a.append(R.styleable.KeyTrigger_motionTarget, 7);
            f945a.append(R.styleable.KeyTrigger_triggerId, 6);
            f945a.append(R.styleable.KeyTrigger_triggerSlack, 5);
            f945a.append(R.styleable.KeyTrigger_motion_triggerOnCollision, 9);
            f945a.append(R.styleable.KeyTrigger_motion_postLayoutCollision, 10);
            f945a.append(R.styleable.KeyTrigger_triggerReceiver, 11);
            f945a.append(R.styleable.KeyTrigger_viewTransitionOnCross, 12);
            f945a.append(R.styleable.KeyTrigger_viewTransitionOnNegativeCross, 13);
            f945a.append(R.styleable.KeyTrigger_viewTransitionOnPositiveCross, 14);
        }

        public static void a(KeyTrigger keyTrigger, TypedArray typedArray, Context context) {
            int indexCount = typedArray.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArray.getIndex(i);
                switch (f945a.get(index)) {
                    case 1:
                        keyTrigger.h = typedArray.getString(index);
                        break;
                    case 2:
                        keyTrigger.i = typedArray.getString(index);
                        break;
                    case 3:
                    default:
                        Log.e(TypedValues.TriggerType.NAME, "unused attribute 0x" + Integer.toHexString(index) + "   " + f945a.get(index));
                        break;
                    case 4:
                        keyTrigger.f = typedArray.getString(index);
                        break;
                    case 5:
                        keyTrigger.m = typedArray.getFloat(index, keyTrigger.m);
                        break;
                    case 6:
                        keyTrigger.j = typedArray.getResourceId(index, keyTrigger.j);
                        break;
                    case 7:
                        if (MotionLayout.IS_IN_EDIT_MODE) {
                            int resourceId = typedArray.getResourceId(index, keyTrigger.b);
                            keyTrigger.b = resourceId;
                            if (resourceId == -1) {
                                keyTrigger.c = typedArray.getString(index);
                                break;
                            } else {
                                break;
                            }
                        } else if (typedArray.peekValue(index).type == 3) {
                            keyTrigger.c = typedArray.getString(index);
                            break;
                        } else {
                            keyTrigger.b = typedArray.getResourceId(index, keyTrigger.b);
                            break;
                        }
                    case 8:
                        int integer = typedArray.getInteger(index, keyTrigger.f939a);
                        keyTrigger.f939a = integer;
                        keyTrigger.q = (integer + 0.5f) / 100.0f;
                        break;
                    case 9:
                        keyTrigger.k = typedArray.getResourceId(index, keyTrigger.k);
                        break;
                    case 10:
                        keyTrigger.s = typedArray.getBoolean(index, keyTrigger.s);
                        break;
                    case 11:
                        keyTrigger.g = typedArray.getResourceId(index, keyTrigger.g);
                        break;
                    case 12:
                        keyTrigger.v = typedArray.getResourceId(index, keyTrigger.v);
                        break;
                    case 13:
                        keyTrigger.t = typedArray.getResourceId(index, keyTrigger.t);
                        break;
                    case 14:
                        keyTrigger.u = typedArray.getResourceId(index, keyTrigger.u);
                        break;
                }
            }
        }
    }

    public KeyTrigger() {
        int i = Key.UNSET;
        this.g = i;
        this.h = null;
        this.i = null;
        this.j = i;
        this.k = i;
        this.l = null;
        this.m = 0.1f;
        this.n = true;
        this.o = true;
        this.p = true;
        this.q = Float.NaN;
        this.s = false;
        this.t = i;
        this.u = i;
        this.v = i;
        this.w = new RectF();
        this.x = new RectF();
        this.y = new HashMap<>();
        this.mType = 5;
        this.d = new HashMap<>();
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    public void addValues(HashMap<String, ViewSpline> hashMap) {
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00ce  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void conditionallyFire(float r10, android.view.View r11) {
        /*
            Method dump skipped, instructions count: 354
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.KeyTrigger.conditionallyFire(float, android.view.View):void");
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    public Key copy(Key key) {
        super.copy(key);
        KeyTrigger keyTrigger = (KeyTrigger) key;
        this.e = keyTrigger.e;
        this.f = keyTrigger.f;
        this.g = keyTrigger.g;
        this.h = keyTrigger.h;
        this.i = keyTrigger.i;
        this.j = keyTrigger.j;
        this.k = keyTrigger.k;
        this.l = keyTrigger.l;
        this.m = keyTrigger.m;
        this.n = keyTrigger.n;
        this.o = keyTrigger.o;
        this.p = keyTrigger.p;
        this.q = keyTrigger.q;
        this.r = keyTrigger.r;
        this.s = keyTrigger.s;
        this.w = keyTrigger.w;
        this.x = keyTrigger.x;
        this.y = keyTrigger.y;
        return this;
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    public void getAttributeNames(HashSet<String> hashSet) {
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    public void load(Context context, AttributeSet attributeSet) {
        a.a(this, context.obtainStyledAttributes(attributeSet, R.styleable.KeyTrigger), context);
    }

    public final void q(String str, View view) {
        Method method;
        if (str == null) {
            return;
        }
        if (str.startsWith(".")) {
            r(str, view);
            return;
        }
        if (this.y.containsKey(str)) {
            method = this.y.get(str);
            if (method == null) {
                return;
            }
        } else {
            method = null;
        }
        if (method == null) {
            try {
                method = view.getClass().getMethod(str, new Class[0]);
                this.y.put(str, method);
            } catch (NoSuchMethodException unused) {
                this.y.put(str, null);
                Log.e(TypedValues.TriggerType.NAME, "Could not find method \"" + str + "\"on class " + view.getClass().getSimpleName() + HexStringBuilder.DEFAULT_SEPARATOR + Debug.getName(view));
                return;
            }
        }
        try {
            method.invoke(view, new Object[0]);
        } catch (Exception unused2) {
            Log.e(TypedValues.TriggerType.NAME, "Exception in call \"" + this.f + "\"on class " + view.getClass().getSimpleName() + HexStringBuilder.DEFAULT_SEPARATOR + Debug.getName(view));
        }
    }

    public final void r(String str, View view) {
        boolean z = str.length() == 1;
        if (!z) {
            str = str.substring(1).toLowerCase(Locale.ROOT);
        }
        for (String str2 : this.d.keySet()) {
            String lowerCase = str2.toLowerCase(Locale.ROOT);
            if (z || lowerCase.matches(str)) {
                ConstraintAttribute constraintAttribute = this.d.get(str2);
                if (constraintAttribute != null) {
                    constraintAttribute.applyCustom(view);
                }
            }
        }
    }

    public final void s(RectF rectF, View view, boolean z) {
        rectF.top = view.getTop();
        rectF.bottom = view.getBottom();
        rectF.left = view.getLeft();
        rectF.right = view.getRight();
        if (z) {
            view.getMatrix().mapRect(rectF);
        }
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    public void setValue(String str, Object obj) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1594793529:
                if (str.equals("positiveCross")) {
                    c = 0;
                    break;
                }
                break;
            case -966421266:
                if (str.equals("viewTransitionOnPositiveCross")) {
                    c = 1;
                    break;
                }
                break;
            case -786670827:
                if (str.equals("triggerCollisionId")) {
                    c = 2;
                    break;
                }
                break;
            case -648752941:
                if (str.equals("triggerID")) {
                    c = 3;
                    break;
                }
                break;
            case -638126837:
                if (str.equals("negativeCross")) {
                    c = 4;
                    break;
                }
                break;
            case -76025313:
                if (str.equals("triggerCollisionView")) {
                    c = 5;
                    break;
                }
                break;
            case -9754574:
                if (str.equals("viewTransitionOnNegativeCross")) {
                    c = 6;
                    break;
                }
                break;
            case 64397344:
                if (str.equals("CROSS")) {
                    c = 7;
                    break;
                }
                break;
            case 364489912:
                if (str.equals("triggerSlack")) {
                    c = '\b';
                    break;
                }
                break;
            case 1301930599:
                if (str.equals("viewTransitionOnCross")) {
                    c = '\t';
                    break;
                }
                break;
            case 1401391082:
                if (str.equals("postLayout")) {
                    c = '\n';
                    break;
                }
                break;
            case 1535404999:
                if (str.equals("triggerReceiver")) {
                    c = 11;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                this.i = obj.toString();
                return;
            case 1:
                this.u = d(obj);
                return;
            case 2:
                this.k = d(obj);
                return;
            case 3:
                this.j = d(obj);
                return;
            case 4:
                this.h = obj.toString();
                return;
            case 5:
                this.l = (View) obj;
                return;
            case 6:
                this.t = d(obj);
                return;
            case 7:
                this.f = obj.toString();
                return;
            case '\b':
                this.m = c(obj);
                return;
            case '\t':
                this.v = d(obj);
                return;
            case '\n':
                this.s = b(obj);
                return;
            case 11:
                this.g = d(obj);
                return;
            default:
                return;
        }
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    /* renamed from: clone */
    public Key mo7clone() {
        return new KeyTrigger().copy(this);
    }
}
