package interpreter.instruction.math;

import interpreter.instruction.Instruction;
import runtime.JFrame;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.classdata.Method;

public class IINC implements Instruction {
    private final int index;
    private final int value;

    public IINC(ProgramCounter programCounter, Method method) {
        index = programCounter.ubyte();
        value = programCounter.byte_();
    }


    @Override
    public void run(JThread thread) {
        JFrame curFrame = thread.top();
        curFrame.getLocalVariables().setInt(index, curFrame.getLocalVariables().getInt(index) + value);
    }
}
