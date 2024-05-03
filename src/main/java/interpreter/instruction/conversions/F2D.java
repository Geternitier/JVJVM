package interpreter.instruction.conversions;

import interpreter.instruction.Instruction;
import runtime.JFrame;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.classdata.Method;

public class F2D implements Instruction {
    public F2D(ProgramCounter programCounter, Method method) {

    }

    @Override
    public void run(JThread thread) {
        JFrame curFrame = thread.top();
        float value = curFrame.getOperandStack().popFloat();
        curFrame.getOperandStack().pushDouble((double) value);
    }

    @Override
    public String toString(){
        return "f2d";
    }
}
