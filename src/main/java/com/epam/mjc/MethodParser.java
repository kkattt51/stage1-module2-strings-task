package com.epam.mjc;

import java.util.ArrayList;
import java.util.List;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     * 1. access modifier - optional, followed by space: ' '
     * 2. return type - followed by space: ' '
     * 3. method name
     * 4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     * accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     * private void log(String value)
     * Vector3 distort(int x, int y, int z, float magnitude)
     * public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        String accessModifier;
        String returnType;
        String methodName;
        List<MethodSignature.Argument> arguments = new ArrayList<>();
        String[] splitByBraces = signatureString.split("[()]");
        String[] splitLeftPart = splitByBraces[0].split(" ");
        if (splitLeftPart.length == 3) {
            accessModifier = splitLeftPart[0];
            returnType = splitLeftPart[1];
            methodName = splitLeftPart[2];
        } else {
            accessModifier = null;
            returnType = splitLeftPart[0];
            methodName = splitLeftPart[1];
        }
        if (splitByBraces.length != 1) {
            String[] splitRightPart = splitByBraces[1].split(", ");
            for (int i = 0; i < splitRightPart.length; i++) {
                String[] typeAndName = splitRightPart[i].split(" ");
                String type = typeAndName[0];
                String name = typeAndName[1];
                MethodSignature.Argument argument = new MethodSignature.Argument(type, name);
                arguments.add(argument);
            }
        }
        MethodSignature resultObject = new MethodSignature(methodName, arguments);
        resultObject.setAccessModifier(accessModifier);
        resultObject.setReturnType(returnType);
        return resultObject;
    }
}
