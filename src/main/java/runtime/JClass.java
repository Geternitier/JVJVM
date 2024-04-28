package runtime;

import classloader.JClassLoader;
import lombok.Getter;
import lombok.SneakyThrows;
import runtime.classdata.attribute.Attribute;
import runtime.classdata.ConstantPool;
import runtime.classdata.Field;
import runtime.classdata.constant.ClassConstant;
import runtime.classdata.Method;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;

import static classdefs.ClassAccessFlags.*;

public class JClass {
    @Getter
    private final int minorVersion;
    @Getter
    private final int majorVersion;
    @Getter
    private final ConstantPool constantPool;
    private final int accessFlags;
    @Getter
    private final ClassConstant thisClass;
    @Getter
    private final ClassConstant superClass;
    private final ClassConstant[] interfaces;
    private final Field[] fields;
    private final Method[] methods;
    private final Attribute[] attributes;
    @Getter
    private final JClassLoader classLoader;

    @SneakyThrows
    public JClass(DataInput dataInput, JClassLoader jClassLoader){
        classLoader = jClassLoader;
        int magic = dataInput.readInt();
        assert magic == 0xcafebabe;

        minorVersion = dataInput.readUnsignedShort();
        majorVersion = dataInput.readUnsignedShort();

        constantPool = new ConstantPool(dataInput, this);
        accessFlags = dataInput.readUnsignedShort();

        thisClass = (ClassConstant) constantPool.getConstant(dataInput.readUnsignedShort());
        int superIndex = dataInput.readUnsignedShort();
        if(superIndex == 0) superClass = null;
        else superClass = (ClassConstant) constantPool.getConstant(superIndex);

        int interfaceCount = dataInput.readUnsignedShort();
        interfaces = new ClassConstant[interfaceCount];
        for(int i = 0;i < interfaceCount;i++){
            int interfaceIndex = dataInput.readUnsignedShort();
            interfaces[i] = (ClassConstant) constantPool.getConstant(interfaceIndex);
        }

        int fieldCount = dataInput.readUnsignedShort();
        fields = new Field[fieldCount];
        for(int i = 0;i < fieldCount;i++){
            fields[i] = new Field(dataInput, this);
        }

        int methodCount = dataInput.readUnsignedShort();
        methods = new Method[methodCount];
        for(int i = 0;i < methodCount;i++){
            methods[i] = new Method(dataInput, this);
        }

        int attributeCount = dataInput.readUnsignedShort();
        attributes = new Attribute[attributeCount];
        for(int i = 0;i < attributeCount;i++){
            attributes[i] = Attribute.constructFromData(dataInput, this.getConstantPool());
        }

    }

    public int getInterfaceCount(){
        return interfaces.length;
    }

    public int getFieldCount(){
        return fields.length;
    }

    public int getMethodCount(){
        return methods.length;
    }

    public int getAttributeCount(){
        return attributes.length;
    }

    public ClassConstant getInterface(int index){
        return interfaces[index];
    }

    public Field getField(int index){
        return fields[index];
    }

    public Method getMethod(int index){
        return methods[index];
    }

    public Attribute getAttribute(int index){
        return attributes[index];
    }

    public boolean public_() {
        return (accessFlags & ACC_PUBLIC) != 0;
    }

    public boolean final_() {
        return (accessFlags & ACC_FINAL) != 0;
    }

    public boolean super_() {
        return (accessFlags & ACC_SUPER) != 0;
    }

    public boolean interface_() {
        return (accessFlags & ACC_INTERFACE) != 0;
    }

    public boolean abstract_() {
        return (accessFlags & ACC_ABSTRACT) != 0;
    }

    public boolean synthetic() {
        return (accessFlags & ACC_SYNTHETIC) != 0;
    }

    public boolean annotation() {
        return (accessFlags & ACC_ANNOTATION) != 0;
    }

    public boolean enum_() {
        return (accessFlags & ACC_ENUM) != 0;
    }

    public boolean module() {
        return (accessFlags & ACC_MODULE) != 0;
    }

    @SneakyThrows
    public static void main(String[] args){
        String path = "output/Hello.class";
        File file = new File(path);
        FileInputStream stream = new FileInputStream(file);
        DataInput dataInput = new DataInputStream(stream);
        JClass jClass = new JClass(dataInput, null);
        System.out.println("Hello World!");
    }
}
