package interpreter.instruction.stores;

import interpreter.instruction.Instruction;
import runtime.JFrame;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.classdata.Method;

public class ASTORE_0 implements Instruction {
    public ASTORE_0(ProgramCounter pc, Method method){

    }

    @Override
    public void run(JThread thread) {
        JFrame curFrame = thread.top();
        curFrame.getLocalVariables().setReference(0, curFrame.getOperandStack().popReference());
    }

    @Override
    public String toString(){
        return "astore_0";
    }
}
