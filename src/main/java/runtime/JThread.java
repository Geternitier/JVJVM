package runtime;

import lombok.Getter;
import machine.JVirtualMachine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JThread {
    @Getter
    private final JVirtualMachine virtualMachine;
    private final ArrayList<JFrame> frames = new ArrayList<>();

    public JThread(JVirtualMachine virtualMachine){
        this.virtualMachine = virtualMachine;
    }

    public boolean isEmpty(){
        return frames.isEmpty();
    }

    public JFrame top() {
        return isEmpty() ? null : frames.get(frames.size() - 1);
    }

    public void pop() {
        frames.remove(frames.size() - 1);
    }

    public void push(JFrame frame) {
        frames.add(frame);
    }

    public ProgramCounter getProgramCounter(){
        return isEmpty() ? null : top().getProgramCounter();
    }

    public List<JFrame> frames() {
        return Collections.unmodifiableList(frames);
    }
}
