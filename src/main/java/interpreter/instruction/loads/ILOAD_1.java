package interpreter.instruction.loads;

import interpreter.instruction.Instruction;
import runtime.JFrame;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.classdata.Method;

public class ILOAD_1 implements Instruction {

    public ILOAD_1(ProgramCounter programCounter, Method method) {
    }

    @Override
    public void run(JThread thread) {
        JFrame curFrame = thread.top();
        curFrame.getOperandStack().pushInt(curFrame.getLocalVariables().getInt(1));
    }

    @Override
    public String toString(){
        return "iload_1";
    }
}
