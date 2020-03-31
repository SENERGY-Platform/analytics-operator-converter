# operator-converter

Converts value using a web service

## Inputs

* value (float): Reading from device

## Outputs

* value (float): Converted value

## Configs

* converter-service-url (string): URL of the converter service in the form "http[s]://url:port" (default: "")
* input-unit (string): Characteristic ID of unit of the input value, something like urn:infai:ses:characteristic:<id> (default: "")
* output-unit (string): Characteristic ID of unit of the output value, something like urn:infai:ses:characteristic:<id> (default: "")