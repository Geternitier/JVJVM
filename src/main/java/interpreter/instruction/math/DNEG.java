package interpreter.instruction.math;

import interpreter.instruction.Instruction;
import runtime.JFrame;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.classdata.Method;

public class DNEG implements Instruction {
    public DNEG(ProgramCounter programCounter, Method method) {

    }

    @Override
    public void run(JThread thread) {
        JFrame curFrame = thread.top();
        double value = curFrame.getOperandStack().popDouble();
        curFrame.getOperandStack().pushDouble(-value);
    }

    @Override
    public String toString(){
        return "dneg";
    }
}
