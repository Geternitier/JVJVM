package runtime.classdata;

import lombok.Getter;
import lombok.SneakyThrows;
import runtime.JClass;
import runtime.classdata.attribute.Attribute;
import runtime.classdata.attribute.Code;
import runtime.classdata.constant.UTF8Constant;

import java.io.DataInput;

import static classdefs.MethodAccessFlags.*;

public class Method {
    @Getter
    private final JClass jClass;
    private final int accessFlags;
    @Getter
    private final String name;
    @Getter
    private final String descriptor;
    private final Attribute[] attributes;
    @Getter
    private Code code;

    @SneakyThrows
    public Method(DataInput dataInput, JClass jClass){
        this.jClass = jClass;
        accessFlags = dataInput.readUnsignedShort();
        int nameIndex = dataInput.readUnsignedShort();
        int descriptorIndex = dataInput.readUnsignedShort();
        name = ((UTF8Constant) jClass.getConstantPool().getConstant(nameIndex)).getValue();
        descriptor = ((UTF8Constant) jClass.getConstantPool().getConstant(descriptorIndex)).getValue();

        int attributeCount = dataInput.readUnsignedShort();
        attributes = new Attribute[attributeCount];
        for(int i = 0;i < attributeCount;i++){
            attributes[i] = Attribute.constructFromData(dataInput, jClass.getConstantPool());
        }

        for(Attribute i: attributes){
            if(i instanceof Code){
                code = (Code) i;
                break;
            }
        }
    }

    public int getAttributeCount(){
        return attributes.length;
    }

    public Attribute getAttribute(int index){
        return attributes[index];
    }

    public boolean public_() {
        return (accessFlags & ACC_PUBLIC) != 0;
    }

    public boolean private_() {
        return (accessFlags & ACC_PRIVATE) != 0;
    }

    public boolean protected_() {
        return (accessFlags & ACC_PROTECTED) != 0;
    }

    public boolean static_() {
        return (accessFlags & ACC_STATIC) != 0;
    }

    public boolean final_() {
        return (accessFlags & ACC_FINAL) != 0;
    }

    public boolean synchronized_() {
        return (accessFlags & ACC_SYNCHRONIZED) != 0;
    }

    public boolean bridge() {
        return (accessFlags & ACC_BRIDGE) != 0;
    }

    public boolean varargs() {
        return (accessFlags & ACC_VARARGS) != 0;
    }

    public boolean native_() {
        return (accessFlags & ACC_NATIVE) != 0;
    }

    public boolean abstract_() {
        return (accessFlags & ACC_ABSTRACT) != 0;
    }

    public boolean strict() {
        return (accessFlags & ACC_STRICT) != 0;
    }

    public boolean synthetic() {
        return (accessFlags & ACC_SYNTHETIC) != 0;
    }
}
