package interpreter.instruction.loads;

import interpreter.instruction.Instruction;
import runtime.JFrame;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.classdata.Method;

public class ALOAD implements Instruction {
    private final int index;

    public ALOAD(ProgramCounter programCounter, Method method) {
        index = programCounter.ubyte();
    }

    @Override
    public void run(JThread thread) {
        JFrame curFrame = thread.top();
        // TODO
    }
}
