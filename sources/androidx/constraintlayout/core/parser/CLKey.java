package androidx.constraintlayout.core.parser;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.ArrayList;
/* loaded from: classes.dex */
public class CLKey extends CLContainer {
    public static ArrayList<String> k;

    static {
        ArrayList<String> arrayList = new ArrayList<>();
        k = arrayList;
        arrayList.add("ConstraintSets");
        k.add("Variables");
        k.add("Generate");
        k.add(TypedValues.TransitionType.NAME);
        k.add("KeyFrames");
        k.add(TypedValues.AttributesType.NAME);
        k.add("KeyPositions");
        k.add("KeyCycles");
    }

    public CLKey(char[] cArr) {
        super(cArr);
    }

    public static CLElement allocate(char[] cArr) {
        return new CLKey(cArr);
    }

    public String getName() {
        return content();
    }

    public CLElement getValue() {
        if (this.j.size() > 0) {
            return this.j.get(0);
        }
        return null;
    }

    public void set(CLElement cLElement) {
        if (this.j.size() > 0) {
            this.j.set(0, cLElement);
        } else {
            this.j.add(cLElement);
        }
    }

    @Override // androidx.constraintlayout.core.parser.CLElement
    public String toFormattedJSON(int i, int i2) {
        StringBuilder sb = new StringBuilder(getDebugName());
        addIndent(sb, i);
        String content = content();
        if (this.j.size() > 0) {
            sb.append(content);
            sb.append(": ");
            if (k.contains(content)) {
                i2 = 3;
            }
            if (i2 > 0) {
                sb.append(this.j.get(0).toFormattedJSON(i, i2 - 1));
            } else {
                String json = this.j.get(0).toJSON();
                if (json.length() + i < CLElement.MAX_LINE) {
                    sb.append(json);
                } else {
                    sb.append(this.j.get(0).toFormattedJSON(i, i2 - 1));
                }
            }
            return sb.toString();
        }
        return content + ": <> ";
    }

    @Override // androidx.constraintlayout.core.parser.CLElement
    public String toJSON() {
        if (this.j.size() > 0) {
            return getDebugName() + content() + ": " + this.j.get(0).toJSON();
        }
        return getDebugName() + content() + ": <> ";
    }

    public static CLElement allocate(String str, CLElement cLElement) {
        CLKey cLKey = new CLKey(str.toCharArray());
        cLKey.setStart(0L);
        cLKey.setEnd(str.length() - 1);
        cLKey.set(cLElement);
        return cLKey;
    }
}
