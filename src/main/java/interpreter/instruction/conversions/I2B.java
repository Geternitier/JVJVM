package interpreter.instruction.conversions;

import interpreter.instruction.Instruction;
import runtime.JFrame;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.classdata.Method;

public class I2B implements Instruction {
    public I2B(ProgramCounter programCounter, Method method) {

    }

    @Override
    public void run(JThread thread) {

    }

    @Override
    public String toString(){
        return "i2b";
    }
}
