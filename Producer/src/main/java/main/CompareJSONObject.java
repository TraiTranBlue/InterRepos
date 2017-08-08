package main;

import com.sun.istack.internal.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by cpu11118-local on 07/08/2017.
 */
public class CompareJSONObject {

    private HashMap json;

    public CompareJSONObject(HashMap json) {
        this.json = json;
    }

    /**
     * Algorithm compare json
     * @param object1
     * @param object2
     * @return
     * @throws IOException
     */
    private boolean algorithmCompare(Object object1, Object object2) throws IOException {
        if (!object1.getClass().equals(object2.getClass())) {
            return false;
        }
        if (object1 instanceof List && object2 instanceof List) {
            if (((List) object1).size() == ((List) object2).size()) {
                for (int i = 0; i < ((ArrayList) object1).size(); i++) {
                    if (!algorithmCompare(((List) object1).get(i), ((List) object2).get(i))) {
                        return false;
                    }
                }
            }
        }
        if (object2 instanceof HashMap && object1 instanceof HashMap) {
            for (HashMap.Entry<String, Object> obEntry : ((HashMap<String, Object>) object2).entrySet()) {
                if (((HashMap) object1).get(obEntry.getKey()) == null) {
                    return false;
                }
                if (!algorithmCompare(obEntry.getValue(), ((HashMap) object1).get(obEntry.getKey()))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * compare JSON
     * @param object
     * @return True if all jsonCompare's filed had child of json otherwise return false
     * @throws IOException
     */
    public boolean compare(@NotNull CompareJSONObject object) throws IOException {
       return algorithmCompare(json, object.getJson());
    }


    public HashMap getJson() {
        return json;
    }

    public void setJson(HashMap json) {
        this.json = json;
    }
}
