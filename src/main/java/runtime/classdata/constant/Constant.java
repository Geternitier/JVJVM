package runtime.classdata.constant;

import lombok.SneakyThrows;
import org.apache.commons.lang3.tuple.Pair;
import runtime.JClass;

import java.io.DataInput;

import static classdefs.ConstantPoolTags.*;

public interface Constant {
    @SneakyThrows
    static Pair<Constant, Integer> constructFromData(DataInput dataInput, JClass jClass){
        byte tag = dataInput.readByte();
        int count = 1;

        Constant result;
        switch (tag){
            case CONSTANT_Integer -> result = new IntegerConstant(dataInput);
            case CONSTANT_Float -> result = new FloatConstant(dataInput);
            case CONSTANT_Utf8 -> result = new UTF8Constant(dataInput);
            case CONSTANT_Long -> {
                result = new LongConstant(dataInput);
                count = 2;
            }
            case CONSTANT_Double -> {
                result = new DoubleConstant(dataInput);
                count = 2;
            }

            case CONSTANT_Fieldref -> result = new FieldRef(dataInput, jClass);
            case CONSTANT_Methodref -> result = new MethodRef(dataInput, jClass);
            case CONSTANT_InterfaceMethodref -> result = new InterfaceMethodRef(dataInput, jClass);

            case CONSTANT_Class -> result = new ClassConstant(dataInput, jClass);

            case CONSTANT_String -> result = new StringConstant(dataInput, jClass);
            case CONSTANT_NameAndType -> result = new NameAndTypeConstant(dataInput, jClass);

            case CONSTANT_MethodHandle -> result = new TODOConstant(dataInput, 3);
            case CONSTANT_MethodType -> result = new MethodTypeConstant(dataInput, jClass);

            case CONSTANT_InvokeDynamic -> result = new TODOConstant(dataInput, 4);
            default -> throw new ClassFormatError();
        }
        return Pair.of(result, count);
    }
}
