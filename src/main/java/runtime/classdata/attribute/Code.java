package runtime.classdata.attribute;

import lombok.Getter;
import lombok.SneakyThrows;
import runtime.classdata.ConstantPool;

import java.io.DataInput;

@Getter
public class Code extends Attribute{
    private final int maxStack;
    private final int maxLocals;
    private final byte[] code;
    private final ExceptionTable[] exceptionTables;
    private final Attribute[] attributes;

    @SneakyThrows
    public Code(String name, int length, DataInput dataInput, ConstantPool constantPool){
        this.name = name;
        attributeLength = length;
        maxStack = dataInput.readUnsignedShort();
        maxLocals = dataInput.readUnsignedShort();

        int codeLength = dataInput.readInt();
        code = new byte[codeLength];
        dataInput.readFully(code);

        int exceptionTableLength = dataInput.readUnsignedShort();
        exceptionTables = new ExceptionTable[exceptionTableLength];
        for(int i = 0;i < exceptionTableLength;i++){
            exceptionTables[i] = new ExceptionTable(dataInput);
        }

        int attributesCount = dataInput.readUnsignedShort();
        attributes = new Attribute[attributesCount];
        for (int i = 0;i < attributesCount;i++){
            attributes[i] = Attribute.constructFromData(dataInput, constantPool);
        }
    }

    public int getExceptionTableLength(){
        return exceptionTables.length;
    }

    public ExceptionTable getExceptionTable(int index){
        return exceptionTables[index];
    }

    public int getAttributeCount(){
        return attributes.length;
    }

    public Attribute getAttribute(int index){
        return attributes[index];
    }
}
