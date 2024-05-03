package interpreter.instruction.loads;

import interpreter.instruction.Instruction;
import runtime.JFrame;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.classdata.Method;

public class DLOAD_0 implements Instruction {

    public DLOAD_0(ProgramCounter programCounter, Method method) {
    }

    @Override
    public void run(JThread thread) {
        JFrame curFrame = thread.top();
        curFrame.getOperandStack().pushDouble(curFrame.getLocalVariables().getDouble(0));
    }

    @Override
    public String toString(){
        return "dload_0";
    }
}
