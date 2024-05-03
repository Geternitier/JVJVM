package interpreter.instruction.loads;

import interpreter.instruction.Instruction;
import runtime.JFrame;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.classdata.Method;

public class FLOAD_2 implements Instruction {

    public FLOAD_2(ProgramCounter programCounter, Method method) {
    }

    @Override
    public void run(JThread thread) {
        JFrame curFrame = thread.top();
        curFrame.getOperandStack().pushFloat(curFrame.getLocalVariables().getFloat(2));
    }

    @Override
    public String toString(){
        return "fload_2";
    }
}
