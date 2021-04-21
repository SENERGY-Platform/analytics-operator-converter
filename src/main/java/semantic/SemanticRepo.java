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

import exceptions.NotUniqueException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class SemanticRepo {

    private final Characteristic[] characteristics;

    public SemanticRepo(String semanticRepoUrl, String token) throws IOException, ParseException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(semanticRepoUrl + "/characteristics?leafsOnly=true");
        request.addHeader("content-type", "application/json");
        request.addHeader("Authorization", "Bearer " + token);
        CloseableHttpResponse resp = httpClient.execute(request);
        String respStr = new BasicResponseHandler().handleResponse(resp);
        JSONArray respArr = (JSONArray) new JSONParser().parse(respStr);
        characteristics = new Characteristic[respArr.size()];
        for (int i = 0; i < respArr.size(); i++) {
            characteristics[i] = Characteristic.fromJson((JSONObject) respArr.get(i));
        }
    }

    public Characteristic byName(String name) throws NotUniqueException {
        Characteristic found = null;
        for (Characteristic c : characteristics) {
            if (c.getName().equals(name)) {
                if (found != null) {
                    throw new NotUniqueException();
                }
                found = c;
            }
        }
        return found;
    }
}



