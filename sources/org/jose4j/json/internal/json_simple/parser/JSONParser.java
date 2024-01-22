package org.jose4j.json.internal.json_simple.parser;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.jose4j.json.internal.json_simple.JSONArray;
import org.jose4j.json.internal.json_simple.JSONObject;
/* loaded from: classes13.dex */
public class JSONParser {
    public static final int S_END = 6;
    public static final int S_INIT = 0;
    public static final int S_IN_ARRAY = 3;
    public static final int S_IN_ERROR = -1;
    public static final int S_IN_FINISHED_VALUE = 1;
    public static final int S_IN_OBJECT = 2;
    public static final int S_IN_PAIR_VALUE = 5;
    public static final int S_PASSED_PAIR_KEY = 4;

    /* renamed from: a  reason: collision with root package name */
    public LinkedList f15505a;
    public a b = new a(null);
    public Yytoken c = null;
    public int d = 0;

    public final List a(ContainerFactory containerFactory) {
        if (containerFactory == null) {
            return new JSONArray();
        }
        List creatArrayContainer = containerFactory.creatArrayContainer();
        return creatArrayContainer == null ? new JSONArray() : creatArrayContainer;
    }

    public final Map b(ContainerFactory containerFactory) {
        if (containerFactory == null) {
            return new JSONObject();
        }
        Map createObjectContainer = containerFactory.createObjectContainer();
        return createObjectContainer == null ? new JSONObject() : createObjectContainer;
    }

    public final void c() throws ParseException, IOException {
        Yytoken d = this.b.d();
        this.c = d;
        if (d == null) {
            this.c = new Yytoken(-1, null);
        }
    }

    public final int d(LinkedList linkedList) {
        if (linkedList.size() == 0) {
            return -1;
        }
        return ((Integer) linkedList.getFirst()).intValue();
    }

    public int getPosition() {
        return this.b.a();
    }

    public Object parse(String str) throws ParseException {
        return parse(str, (ContainerFactory) null);
    }

    public void reset() {
        this.c = null;
        this.d = 0;
        this.f15505a = null;
    }

    public Object parse(String str, ContainerFactory containerFactory) throws ParseException {
        try {
            return parse(new StringReader(str), containerFactory);
        } catch (IOException e) {
            throw new ParseException(-1, 2, e);
        }
    }

    public void reset(Reader reader) {
        this.b.e(reader);
        reset();
    }

    public Object parse(Reader reader) throws IOException, ParseException {
        return parse(reader, (ContainerFactory) null);
    }

    public Object parse(Reader reader, ContainerFactory containerFactory) throws IOException, ParseException {
        reset(reader);
        LinkedList linkedList = new LinkedList();
        LinkedList linkedList2 = new LinkedList();
        do {
            try {
                c();
                int i = this.d;
                if (i != -1) {
                    if (i == 0) {
                        int i2 = this.c.type;
                        if (i2 == 0) {
                            this.d = 1;
                            linkedList.addFirst(new Integer(1));
                            linkedList2.addFirst(this.c.value);
                        } else if (i2 == 1) {
                            this.d = 2;
                            linkedList.addFirst(new Integer(2));
                            linkedList2.addFirst(b(containerFactory));
                        } else if (i2 != 3) {
                            this.d = -1;
                        } else {
                            this.d = 3;
                            linkedList.addFirst(new Integer(3));
                            linkedList2.addFirst(a(containerFactory));
                        }
                    } else if (i == 1) {
                        if (this.c.type == -1) {
                            return linkedList2.removeFirst();
                        }
                        throw new ParseException(getPosition(), 1, this.c);
                    } else if (i == 2) {
                        Yytoken yytoken = this.c;
                        int i3 = yytoken.type;
                        if (i3 == 0) {
                            Object obj = yytoken.value;
                            if (obj instanceof String) {
                                linkedList2.addFirst((String) obj);
                                this.d = 4;
                                linkedList.addFirst(new Integer(4));
                            } else {
                                this.d = -1;
                            }
                        } else if (i3 != 2) {
                            if (i3 != 5) {
                                this.d = -1;
                            }
                        } else if (linkedList2.size() > 1) {
                            linkedList.removeFirst();
                            linkedList2.removeFirst();
                            this.d = d(linkedList);
                        } else {
                            this.d = 1;
                        }
                    } else if (i == 3) {
                        int i4 = this.c.type;
                        if (i4 == 0) {
                            ((List) linkedList2.getFirst()).add(this.c.value);
                        } else if (i4 == 1) {
                            Map b = b(containerFactory);
                            ((List) linkedList2.getFirst()).add(b);
                            this.d = 2;
                            linkedList.addFirst(new Integer(2));
                            linkedList2.addFirst(b);
                        } else if (i4 == 3) {
                            List a2 = a(containerFactory);
                            ((List) linkedList2.getFirst()).add(a2);
                            this.d = 3;
                            linkedList.addFirst(new Integer(3));
                            linkedList2.addFirst(a2);
                        } else if (i4 != 4) {
                            if (i4 != 5) {
                                this.d = -1;
                            }
                        } else if (linkedList2.size() > 1) {
                            linkedList.removeFirst();
                            linkedList2.removeFirst();
                            this.d = d(linkedList);
                        } else {
                            this.d = 1;
                        }
                    } else if (i == 4) {
                        int i5 = this.c.type;
                        if (i5 == 0) {
                            linkedList.removeFirst();
                            ((Map) linkedList2.getFirst()).put((String) linkedList2.removeFirst(), this.c.value);
                            this.d = d(linkedList);
                        } else if (i5 == 1) {
                            linkedList.removeFirst();
                            Map map = (Map) linkedList2.getFirst();
                            Map b2 = b(containerFactory);
                            map.put((String) linkedList2.removeFirst(), b2);
                            this.d = 2;
                            linkedList.addFirst(new Integer(2));
                            linkedList2.addFirst(b2);
                        } else if (i5 == 3) {
                            linkedList.removeFirst();
                            Map map2 = (Map) linkedList2.getFirst();
                            List a3 = a(containerFactory);
                            map2.put((String) linkedList2.removeFirst(), a3);
                            this.d = 3;
                            linkedList.addFirst(new Integer(3));
                            linkedList2.addFirst(a3);
                        } else if (i5 != 6) {
                            this.d = -1;
                        }
                    }
                    if (this.d == -1) {
                        throw new ParseException(getPosition(), 1, this.c);
                    }
                } else {
                    throw new ParseException(getPosition(), 1, this.c);
                }
            } catch (IOException e) {
                throw e;
            }
        } while (this.c.type != -1);
        throw new ParseException(getPosition(), 1, this.c);
    }

    public void parse(String str, ContentHandler contentHandler) throws ParseException {
        parse(str, contentHandler, false);
    }

    public void parse(String str, ContentHandler contentHandler, boolean z) throws ParseException {
        try {
            parse(new StringReader(str), contentHandler, z);
        } catch (IOException e) {
            throw new ParseException(-1, 2, e);
        }
    }

    public void parse(Reader reader, ContentHandler contentHandler) throws IOException, ParseException {
        parse(reader, contentHandler, false);
    }

    public void parse(Reader reader, ContentHandler contentHandler, boolean z) throws IOException, ParseException {
        if (!z) {
            reset(reader);
            this.f15505a = new LinkedList();
        } else if (this.f15505a == null) {
            reset(reader);
            this.f15505a = new LinkedList();
        }
        LinkedList linkedList = this.f15505a;
        do {
            try {
                switch (this.d) {
                    case -1:
                        throw new ParseException(getPosition(), 1, this.c);
                    case 0:
                        contentHandler.startJSON();
                        c();
                        int i = this.c.type;
                        if (i == 0) {
                            this.d = 1;
                            linkedList.addFirst(new Integer(1));
                            if (!contentHandler.primitive(this.c.value)) {
                                return;
                            }
                        } else if (i == 1) {
                            this.d = 2;
                            linkedList.addFirst(new Integer(2));
                            if (!contentHandler.startObject()) {
                                return;
                            }
                        } else if (i != 3) {
                            this.d = -1;
                            break;
                        } else {
                            this.d = 3;
                            linkedList.addFirst(new Integer(3));
                            if (!contentHandler.startArray()) {
                                return;
                            }
                        }
                        break;
                    case 1:
                        c();
                        if (this.c.type == -1) {
                            contentHandler.endJSON();
                            this.d = 6;
                            return;
                        }
                        this.d = -1;
                        throw new ParseException(getPosition(), 1, this.c);
                    case 2:
                        c();
                        Yytoken yytoken = this.c;
                        int i2 = yytoken.type;
                        if (i2 == 0) {
                            Object obj = yytoken.value;
                            if (obj instanceof String) {
                                this.d = 4;
                                linkedList.addFirst(new Integer(4));
                                if (!contentHandler.startObjectEntry((String) obj)) {
                                    return;
                                }
                            } else {
                                this.d = -1;
                                break;
                            }
                        } else if (i2 == 2) {
                            if (linkedList.size() > 1) {
                                linkedList.removeFirst();
                                this.d = d(linkedList);
                            } else {
                                this.d = 1;
                            }
                            if (!contentHandler.endObject()) {
                                return;
                            }
                        } else if (i2 != 5) {
                            this.d = -1;
                            break;
                        }
                        break;
                    case 3:
                        c();
                        Yytoken yytoken2 = this.c;
                        int i3 = yytoken2.type;
                        if (i3 != 0) {
                            if (i3 == 1) {
                                this.d = 2;
                                linkedList.addFirst(new Integer(2));
                                if (!contentHandler.startObject()) {
                                    return;
                                }
                            } else if (i3 == 3) {
                                this.d = 3;
                                linkedList.addFirst(new Integer(3));
                                if (!contentHandler.startArray()) {
                                    return;
                                }
                            } else if (i3 == 4) {
                                if (linkedList.size() > 1) {
                                    linkedList.removeFirst();
                                    this.d = d(linkedList);
                                } else {
                                    this.d = 1;
                                }
                                if (!contentHandler.endArray()) {
                                    return;
                                }
                            } else if (i3 != 5) {
                                this.d = -1;
                                break;
                            }
                        } else if (!contentHandler.primitive(yytoken2.value)) {
                            return;
                        }
                        break;
                    case 4:
                        c();
                        int i4 = this.c.type;
                        if (i4 == 0) {
                            linkedList.removeFirst();
                            this.d = d(linkedList);
                            if (!contentHandler.primitive(this.c.value) || !contentHandler.endObjectEntry()) {
                                return;
                            }
                        } else if (i4 == 1) {
                            linkedList.removeFirst();
                            linkedList.addFirst(new Integer(5));
                            this.d = 2;
                            linkedList.addFirst(new Integer(2));
                            if (!contentHandler.startObject()) {
                                return;
                            }
                        } else if (i4 == 3) {
                            linkedList.removeFirst();
                            linkedList.addFirst(new Integer(5));
                            this.d = 3;
                            linkedList.addFirst(new Integer(3));
                            if (!contentHandler.startArray()) {
                                return;
                            }
                        } else if (i4 != 6) {
                            this.d = -1;
                            break;
                        }
                        break;
                    case 5:
                        linkedList.removeFirst();
                        this.d = d(linkedList);
                        if (!contentHandler.endObjectEntry()) {
                            return;
                        }
                        break;
                    case 6:
                        return;
                }
                if (this.d == -1) {
                    throw new ParseException(getPosition(), 1, this.c);
                }
            } catch (IOException e) {
                this.d = -1;
                throw e;
            } catch (Error e2) {
                this.d = -1;
                throw e2;
            } catch (RuntimeException e3) {
                this.d = -1;
                throw e3;
            } catch (ParseException e4) {
                this.d = -1;
                throw e4;
            }
        } while (this.c.type != -1);
        this.d = -1;
        throw new ParseException(getPosition(), 1, this.c);
    }
}
