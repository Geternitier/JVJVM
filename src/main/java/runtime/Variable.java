package runtime;

import lombok.Getter;

@Getter
public class Variable {
    private final Integer integer;
    private final Reference reference;

    public Variable(int value){
        integer = value;
        reference = null;
    }

    public Variable(Reference value){
        integer = null;
        reference = value;
    }

    public Variable(Variable variable){
        integer = variable.getInteger();
        reference = variable.getReference() == null?null: variable.getReference().clone();
    }

    @Override
    public String toString(){
        if(integer == null){
            return "ref: "+reference;
        } else return "int: "+integer;
    }
}
