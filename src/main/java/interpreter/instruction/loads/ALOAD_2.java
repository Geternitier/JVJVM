package interpreter.instruction.loads;

import interpreter.instruction.Instruction;
import runtime.JFrame;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.classdata.Method;

public class ALOAD_2 implements Instruction {

    public ALOAD_2(ProgramCounter programCounter, Method method) {

    }

    @Override
    public void run(JThread thread) {
        JFrame curFrame = thread.top();
        curFrame.getOperandStack().pushReference(curFrame.getLocalVariables().getReference(2));
    }

    @Override
    public String toString(){
        return "aload_2";
    }
}
