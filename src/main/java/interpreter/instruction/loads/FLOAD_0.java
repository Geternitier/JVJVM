package interpreter.instruction.loads;

import interpreter.instruction.Instruction;
import runtime.JFrame;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.classdata.Method;

public class FLOAD_0 implements Instruction {

    public FLOAD_0(ProgramCounter programCounter, Method method) {
    }

    @Override
    public void run(JThread thread) {
        JFrame curFrame = thread.top();
        curFrame.getOperandStack().pushFloat(curFrame.getLocalVariables().getFloat(0));
    }

    @Override
    public String toString(){
        return "fload_0";
    }
}
