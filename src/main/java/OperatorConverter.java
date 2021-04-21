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


import org.infai.ses.senergy.exceptions.NoValueException;
import org.infai.ses.senergy.operators.BaseOperator;
import org.infai.ses.senergy.operators.Helper;
import org.infai.ses.senergy.operators.Message;

import java.io.IOException;

public class OperatorConverter extends BaseOperator {
    private Converter converter;
    private boolean DEBUG;

    public OperatorConverter(String url, String inputCharacteristicId, String outputCharacteristicId) {
        converter = new Converter(url, inputCharacteristicId, outputCharacteristicId);
        DEBUG = Boolean.parseBoolean(Helper.getEnv("DEBUG", "false"));
    }

    @Override
    public void run(Message message) {
        try {
            Object in = message.getFlexInput("value").getValue();
            Object value = converter.convert(in);
            if (DEBUG) {
                System.out.println("in: " + in + ", out: " + value);
            }
            message.output("value", value);
        } catch (IOException | NoValueException e) {
            System.err.println("Error converting a value, skipping message....");
            e.printStackTrace();
        }
    }

    @Override
    public Message configMessage(Message message) {
        message.addFlexInput("value");
        return  message;
    }
}
