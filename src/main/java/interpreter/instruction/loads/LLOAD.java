package interpreter.instruction.loads;

import interpreter.instruction.Instruction;
import runtime.JFrame;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.classdata.Method;

public class LLOAD implements Instruction {
    private final int index;

    public LLOAD(ProgramCounter programCounter, Method method) {
        index = programCounter.readUnsignedByte();
    }

    @Override
    public void run(JThread thread) {
        JFrame curFrame = thread.top();
        curFrame.getOperandStack().pushLong(curFrame.getLocalVariables().getLong(index));
    }

    @Override
    public String toString(){
        return "lload";
    }
}
