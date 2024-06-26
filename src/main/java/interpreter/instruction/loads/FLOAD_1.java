package interpreter.instruction.loads;

import interpreter.instruction.Instruction;
import runtime.JFrame;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.classdata.Method;

public class FLOAD_1 implements Instruction {

    public FLOAD_1(ProgramCounter programCounter, Method method) {
    }

    @Override
    public void run(JThread thread) {
        JFrame curFrame = thread.top();
        curFrame.getOperandStack().pushFloat(curFrame.getLocalVariables().getFloat(1));
    }

    @Override
    public String toString(){
        return "fload_1";
    }
}
