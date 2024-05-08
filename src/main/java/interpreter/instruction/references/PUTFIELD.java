package interpreter.instruction.references;

import interpreter.instruction.Instruction;
import runtime.JFrame;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.Variable;
import runtime.classdata.Method;
import runtime.classdata.constant.FieldRef;
import runtime.reference.ClassReference;
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
        FieldRef ref = (FieldRef) curFrame.getDynamicLink().getConstant(index);
        try {
            if(ref.getField().getDescriptor().equals("D") || ref.getField().getDescriptor().equals("J")){
                Variable low = curFrame.getOperandStack().pop();
                Variable high = curFrame.getOperandStack().pop();
                ClassReference classReference = (ClassReference) curFrame.getOperandStack().popReference();
                FieldReference fieldReference = new FieldReference(ref.getField());
                fieldReference.getValue().set(ReferenceValue.of(high, low));
                classReference.setField(fieldReference.getField().getName(), fieldReference);
            } else {
                Variable value = curFrame.getOperandStack().pop();
                ClassReference classReference = (ClassReference) curFrame.getOperandStack().popReference();
                FieldReference fieldReference = new FieldReference(ref.getField());
                fieldReference.getValue().set(ReferenceValue.of(value));
                classReference.setField(fieldReference.getField().getName(), fieldReference);
            }
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.toString());
        }
    }

    @Override
    public String toString(){
        return "putfield";
    }

}
