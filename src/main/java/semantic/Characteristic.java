/*
 * Copyright 2021 InfAI (CC SES)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package semantic;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Characteristic {
    public static Characteristic fromJson(JSONObject jo) {
        Characteristic c = new Characteristic();
        c.setId((String) jo.get("id"));
        c.setName((String) jo.get("name"));
        c.setRdf_type((String) jo.get("rdf_type"));
        c.setType((String) jo.get("type"));
        Object subsObj = jo.get("sub_characteristics");
        if (subsObj != null) {
            JSONArray subs = (JSONArray) subsObj;
            Characteristic[] sub_characteristics = new Characteristic[subs.size()];
            for (int i = 0; i < subs.size(); i++) {
                sub_characteristics[i] = Characteristic.fromJson((JSONObject) subs.get(i));
            }
            c.setSub_characteristics(sub_characteristics);
        }
        return c;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Characteristic[] getSub_characteristics() {
        return sub_characteristics;
    }

    public void setSub_characteristics(Characteristic[] sub_characteristics) {
        this.sub_characteristics = sub_characteristics;
    }

    public String getRdf_type() {
        return rdf_type;
    }

    public void setRdf_type(String rdf_type) {
        this.rdf_type = rdf_type;
    }

    private String id;
    private String name;
    private String type;
    private Characteristic[] sub_characteristics;
    private String rdf_type;
}
