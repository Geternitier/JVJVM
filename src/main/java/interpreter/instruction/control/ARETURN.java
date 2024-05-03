package interpreter.instruction.control;

import interpreter.instruction.Instruction;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.reference.Reference;
import runtime.classdata.Method;

public class ARETURN implements Instruction {

    public ARETURN(ProgramCounter pc, Method method){

    }

    @Override
    public void run(JThread thread) {
        Reference value = thread.top().getOperandStack().popReference();
        thread.pop();
        thread.top().getOperandStack().pushReference(value);
    }

    @Override
    public String toString(){
        return "areturn";
    }
}
