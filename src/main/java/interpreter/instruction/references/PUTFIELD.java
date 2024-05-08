package interpreter.instruction.references;

import interpreter.instruction.Instruction;
import runtime.JFrame;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.Variable;
import runtime.classdata.Method;
import runtime.reference.FieldReference;
import runtime.reference.ReferenceValue;

public class PUTFIELD implements Instruction {
    private final int index;

    public PUTFIELD(ProgramCounter programCounter, Method method){
        index = programCounter.readShort();
    }

    @Override
    public void run(JThread thread) {
        JFrame curFrame = thread.top();
        FieldReference reference = (FieldReference) curFrame.getDynamicLink().getConstant(index);
        if(reference.getField().getDescriptor().equals("D") || reference.getField().getDescriptor().equals("J")){
            Variable low = curFrame.getOperandStack().pop();
            Variable high = curFrame.getOperandStack().pop();
            FieldReference fieldReference = (FieldReference) curFrame.getOperandStack().popReference();
            fieldReference.getValue().set(ReferenceValue.of(high, low));
        } else {
            Variable value = curFrame.getOperandStack().pop();
            FieldReference fieldReference = (FieldReference) curFrame.getOperandStack().popReference();
            fieldReference.getValue().set(ReferenceValue.of(value));
        }
    }

    @Override
    public String toString(){
        return "getfield";
    }

}
