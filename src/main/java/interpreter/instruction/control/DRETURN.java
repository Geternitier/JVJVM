package interpreter.instruction.control;

import interpreter.instruction.Instruction;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.classdata.Method;

public class DRETURN implements Instruction {

    public DRETURN(ProgramCounter pc, Method method){
    }

    @Override
    public void run(JThread thread) {
        double value = thread.top().getOperandStack().popDouble();
        thread.pop();
        thread.top().getOperandStack().pushDouble(value);
    }

    @Override
    public String toString(){
        return "dreturn";
    }
}
