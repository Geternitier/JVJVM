package runtime;

import lombok.Getter;
import machine.JVirtualMachine;

import java.util.HashMap;
import java.util.Map;

public class JHeap {
    @Getter
    private final JVirtualMachine virtualMachine;
    private final Map<String, JClass> classTable = new HashMap<>();

    public JHeap(JVirtualMachine virtualMachine){
        this.virtualMachine = virtualMachine;
    }

    public void registerClass(String name, JClass jClass){
        classTable.putIfAbsent(name, jClass);
    }

    public JClass getClass(String name){
        return classTable.get(name);
    }
}
