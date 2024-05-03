package interpreter.instruction.loads;

import interpreter.instruction.Instruction;
import runtime.JFrame;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.classdata.Method;

public class DLOAD_2 implements Instruction {

    public DLOAD_2(ProgramCounter programCounter, Method method) {
    }

    @Override
    public void run(JThread thread) {
        JFrame curFrame = thread.top();
        curFrame.getOperandStack().pushDouble(curFrame.getLocalVariables().getDouble(2));
    }

    @Override
    public String toString(){
        return "dload_2";
    }
}
