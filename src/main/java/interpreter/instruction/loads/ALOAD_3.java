package interpreter.instruction.loads;

import interpreter.instruction.Instruction;
import runtime.JFrame;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.classdata.Method;

public class ALOAD_3 implements Instruction {

    public ALOAD_3(ProgramCounter programCounter, Method method) {

    }

    @Override
    public void run(JThread thread) {
        JFrame curFrame = thread.top();
        curFrame.getOperandStack().pushReference(curFrame.getLocalVariables().getReference(3));
    }

    @Override
    public String toString(){
        return "aload_3";
    }
}
