package interpreter.instruction.conversions;

import interpreter.instruction.Instruction;
import runtime.JFrame;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.classdata.Method;

public class D2F implements Instruction {
    public D2F(ProgramCounter programCounter, Method method) {

    }

    @Override
    public void run(JThread thread) {
        JFrame curFrame = thread.top();
        double value = curFrame.getOperandStack().popDouble();
        curFrame.getOperandStack().pushFloat((float) value);
    }

    @Override
    public String toString(){
        return "d2f";
    }
}
