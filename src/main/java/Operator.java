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

import org.infai.seits.sepl.operators.Config;
import org.infai.seits.sepl.operators.Message;
import org.infai.seits.sepl.operators.OperatorInterface;
import org.infai.seits.sepl.operators.Stream;

import java.io.IOException;

public class Operator {

    public static void main(String[] args) {
        Stream stream  = new Stream();
        Config config = new Config();
        stream.start(new OperatorConverter(config.getConfigValue("converter-service-url", ""),
                config.getConfigValue("input-unit", ""),
                config.getConfigValue("output-unit", "")));
    }
}



class OperatorConverter implements OperatorInterface {
    private Converter converter;

    public OperatorConverter(String url, String inputUnit, String outputUnit) {
        converter = new Converter(url, inputUnit, outputUnit);
    }

    @Override
    public void run(Message message) {
        try {
            Object value = converter.convert(message.getInput("value").getValue());
            message.output("value", value);
        } catch (IOException e) {
            System.err.println("Error converting a value, skipping message....");
            e.printStackTrace();
        }
    }

    @Override
    public void config(Message message) {
        message.addInput("value");
    }
}