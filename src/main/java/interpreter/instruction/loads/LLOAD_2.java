package interpreter.instruction.loads;

import interpreter.instruction.Instruction;
import runtime.JFrame;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.classdata.Method;

public class LLOAD_2 implements Instruction {

    public LLOAD_2(ProgramCounter programCounter, Method method) {
    }

    @Override
    public void run(JThread thread) {
        JFrame curFrame = thread.top();
        curFrame.getOperandStack().pushLong(curFrame.getLocalVariables().getLong(2));
    }

    @Override
    public String toString(){
        return "lload_2";
    }
}
