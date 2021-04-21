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

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class FixConfig {

    public static String SEMANTIC_REPO_URL = "semantic_repo_url";
    public static String PREFIX_CHARACTERISTIC_ID = "prefix_characteristic_id";
    public static String TOKEN = "token";
    public static String CONVERTER_URL = "converter_url";

    private JSONObject configs;

    public FixConfig() throws IOException, ParseException {
        configs = (JSONObject) new JSONParser().parse(new FileReader("config.json"));
    }

    public <T> T getValue(String name) throws ClassCastException {
        Object val = configs.get(name);
        return (T) val;
    }
}
