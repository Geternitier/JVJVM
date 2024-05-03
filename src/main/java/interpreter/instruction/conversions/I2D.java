package interpreter.instruction.conversions;

import interpreter.instruction.Instruction;
import runtime.JFrame;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.classdata.Method;

public class I2D implements Instruction {
    public I2D(ProgramCounter programCounter, Method method) {

    }

    @Override
    public void run(JThread thread) {
        JFrame curFrame = thread.top();
        int value = curFrame.getOperandStack().popInt();
        curFrame.getOperandStack().pushDouble(value);
    }

    @Override
    public String toString(){
        return "i2d";
    }
}
