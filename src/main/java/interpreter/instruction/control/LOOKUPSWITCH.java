package interpreter.instruction.control;

import interpreter.instruction.Instruction;
import runtime.JFrame;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.classdata.Method;

import java.util.LinkedHashMap;
import java.util.Map;

public class LOOKUPSWITCH implements Instruction {
    private final int opAddress;
    private final int padding;
    private final int defaultOffset;
    private final Map<Integer, Integer> table = new LinkedHashMap<>();

    public LOOKUPSWITCH(ProgramCounter programCounter, Method method) {
        opAddress = programCounter.getPosition() - 1;
        padding = programCounter.readPadding();
        defaultOffset = programCounter.readInt();
        int npairs = programCounter.readInt();
        for(int i = 0; i < npairs; i++){
            int key = programCounter.readInt();
            int offset = programCounter.readInt();
            table.put(key, offset);
        }
    }

    @Override
    public void run(JThread thread) {
        JFrame curFrame = thread.top();
        int key = curFrame.getOperandStack().popInt();
        curFrame.getProgramCounter().setPosition(opAddress + table.getOrDefault(key, defaultOffset));
    }

    @Override
    public String toString(){
        return "lookupswitch";
    }
}
