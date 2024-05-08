package runtime.reference;

import lombok.Getter;
import runtime.JClass;
import runtime.classdata.constant.ClassConstant;

public class ClassReference implements Reference{
    private final ClassConstant constant;
    @Getter
    private final JClass value;

    public ClassReference(ClassConstant constant){
        this.constant = constant;
        try {
            value = constant.getValue();
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.toString());
        }
    }

    @Override
    public String toString(){
        return "Reference to Class: "+constant.getName();
    }
}
