package interpreter.instruction.control;

import interpreter.instruction.Instruction;
import runtime.JFrame;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.classdata.Method;

public class GOTO implements Instruction {
    private final int branch;

    public GOTO(ProgramCounter programCounter, Method method) {
        branch = programCounter.readUnsignedShort();
    }

    @Override
    public void run(JThread thread) {
        JFrame curFrame = thread.top();
        ProgramCounter pc = curFrame.getProgramCounter();
        pc.setPosition(pc.getPosition() + branch - 3);
    }

    @Override
    public String toString(){
        return "goto "+branch;
    }
}
