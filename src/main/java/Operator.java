/*
 * Copyright 2020 InfAI (CC SES)
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

import org.infai.ses.senergy.operators.BaseOperator;
import org.infai.ses.senergy.operators.Config;
import org.infai.ses.senergy.operators.Stream;
import org.infai.ses.senergy.utils.ConfigProvider;
import semantic.SemanticRepo;

public class Operator {

    public static void main(String[] args) throws Exception {
        FixConfig fix = new FixConfig();
        Config config = ConfigProvider.getConfig();

        String inputUnit = config.getConfigValue("input-unit", "");
        String outputUnit = config.getConfigValue("output-unit", "");
        SemanticRepo semantic = null;
        String prefix = fix.getValue(FixConfig.PREFIX_CHARACTERISTIC_ID);
        if (!inputUnit.startsWith(prefix)) {
            semantic = new SemanticRepo(fix.getValue(FixConfig.SEMANTIC_REPO_URL), fix.getValue(FixConfig.TOKEN));
            inputUnit = semantic.byName(inputUnit).getId();
        }
        if (!outputUnit.startsWith(prefix)) {
            if (semantic == null) {
                semantic = new SemanticRepo(fix.getValue(FixConfig.SEMANTIC_REPO_URL), fix.getValue(FixConfig.TOKEN));
            }
            outputUnit = semantic.byName(outputUnit).getId();
        }
        System.out.println("inputUnit: " + inputUnit);
        System.out.println("outputUnit: " + outputUnit);

        BaseOperator op = new OperatorConverter(fix.getValue(FixConfig.CONVERTER_URL),
                inputUnit,
                outputUnit);
        Stream stream  = new Stream();
        stream.start(op);


    }
}
