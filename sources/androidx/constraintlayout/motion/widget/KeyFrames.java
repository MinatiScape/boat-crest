package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.util.Log;
import android.util.Xml;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintAttribute;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
/* loaded from: classes.dex */
public class KeyFrames {
    public static final int UNSET = -1;
    public static HashMap<String, Constructor<? extends Key>> b;

    /* renamed from: a  reason: collision with root package name */
    public HashMap<Integer, ArrayList<Key>> f942a = new HashMap<>();

    static {
        HashMap<String, Constructor<? extends Key>> hashMap = new HashMap<>();
        b = hashMap;
        try {
            hashMap.put("KeyAttribute", KeyAttributes.class.getConstructor(new Class[0]));
            b.put(TypedValues.PositionType.NAME, KeyPosition.class.getConstructor(new Class[0]));
            b.put(TypedValues.CycleType.NAME, KeyCycle.class.getConstructor(new Class[0]));
            b.put("KeyTimeCycle", KeyTimeCycle.class.getConstructor(new Class[0]));
            b.put(TypedValues.TriggerType.NAME, KeyTrigger.class.getConstructor(new Class[0]));
        } catch (NoSuchMethodException e) {
            Log.e("KeyFrames", "unable to load", e);
        }
    }

    public KeyFrames() {
    }

    public void addAllFrames(MotionController motionController) {
        ArrayList<Key> arrayList = this.f942a.get(-1);
        if (arrayList != null) {
            motionController.a(arrayList);
        }
    }

    public void addFrames(MotionController motionController) {
        ArrayList<Key> arrayList = this.f942a.get(Integer.valueOf(motionController.c));
        if (arrayList != null) {
            motionController.a(arrayList);
        }
        ArrayList<Key> arrayList2 = this.f942a.get(-1);
        if (arrayList2 != null) {
            Iterator<Key> it = arrayList2.iterator();
            while (it.hasNext()) {
                Key next = it.next();
                if (next.a(((ConstraintLayout.LayoutParams) motionController.b.getLayoutParams()).constraintTag)) {
                    motionController.addKey(next);
                }
            }
        }
    }

    public void addKey(Key key) {
        if (!this.f942a.containsKey(Integer.valueOf(key.b))) {
            this.f942a.put(Integer.valueOf(key.b), new ArrayList<>());
        }
        ArrayList<Key> arrayList = this.f942a.get(Integer.valueOf(key.b));
        if (arrayList != null) {
            arrayList.add(key);
        }
    }

    public ArrayList<Key> getKeyFramesForView(int i) {
        return this.f942a.get(Integer.valueOf(i));
    }

    public Set<Integer> getKeys() {
        return this.f942a.keySet();
    }

    public KeyFrames(Context context, XmlPullParser xmlPullParser) {
        Key key;
        Exception e;
        Constructor<? extends Key> constructor;
        HashMap<String, ConstraintAttribute> hashMap;
        HashMap<String, ConstraintAttribute> hashMap2;
        Key key2 = null;
        try {
            int eventType = xmlPullParser.getEventType();
            while (eventType != 1) {
                if (eventType != 2) {
                    if (eventType == 3 && ViewTransition.KEY_FRAME_SET_TAG.equals(xmlPullParser.getName())) {
                        return;
                    }
                } else {
                    String name = xmlPullParser.getName();
                    if (b.containsKey(name)) {
                        try {
                            constructor = b.get(name);
                        } catch (Exception e2) {
                            key = key2;
                            e = e2;
                        }
                        if (constructor != null) {
                            key = constructor.newInstance(new Object[0]);
                            try {
                                key.load(context, Xml.asAttributeSet(xmlPullParser));
                                addKey(key);
                            } catch (Exception e3) {
                                e = e3;
                                Log.e("KeyFrames", "unable to create ", e);
                                key2 = key;
                                continue;
                                eventType = xmlPullParser.next();
                            }
                            key2 = key;
                            continue;
                        } else {
                            throw new NullPointerException("Keymaker for " + name + " not found");
                            break;
                        }
                    } else if (name.equalsIgnoreCase(ViewTransition.CUSTOM_ATTRIBUTE)) {
                        if (key2 != null && (hashMap2 = key2.d) != null) {
                            ConstraintAttribute.parse(context, xmlPullParser, hashMap2);
                            continue;
                        }
                    } else if (name.equalsIgnoreCase(ViewTransition.CUSTOM_METHOD) && key2 != null && (hashMap = key2.d) != null) {
                        ConstraintAttribute.parse(context, xmlPullParser, hashMap);
                        continue;
                    }
                }
                eventType = xmlPullParser.next();
            }
        } catch (IOException e4) {
            e4.printStackTrace();
        } catch (XmlPullParserException e5) {
            e5.printStackTrace();
        }
    }
}
