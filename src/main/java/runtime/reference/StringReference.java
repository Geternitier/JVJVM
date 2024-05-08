package runtime.reference;

import runtime.classdata.constant.StringConstant;

public class StringReference implements Reference{
    private final StringConstant constant;

    public StringReference(StringConstant constant){
        this.constant = constant;
    }

    public String getConstant(){
        return constant.string();
    }

    @Override
    public String toString(){
        return "Reference to String: "+constant.string();
    }
}
