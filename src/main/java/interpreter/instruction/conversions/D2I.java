package interpreter.instruction.conversions;

import interpreter.instruction.Instruction;
import runtime.JFrame;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.classdata.Method;

public class D2I implements Instruction {
    public D2I(ProgramCounter programCounter, Method method) {

    }

    @Override
    public void run(JThread thread) {
        JFrame curFrame = thread.top();
        double value = curFrame.getOperandStack().popDouble();
        curFrame.getOperandStack().pushInt((int) value);
    }

    @Override
    public String toString(){
        return "d2i";
    }
}
