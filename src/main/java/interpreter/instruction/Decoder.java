package interpreter.instruction;

import interpreter.instruction.constants.*;
import interpreter.instruction.control.IRETURN;
import interpreter.instruction.control.RETURN;
import runtime.ProgramCounter;
import runtime.classdata.Method;

import java.util.function.BiFunction;

public class Decoder {
    public static Instruction decode(ProgramCounter pc, Method method){
        int opCode = Byte.toUnsignedInt(pc.byte_());
        if (Decoder.decodeTable[opCode] == null) {
            throw new UnimplementedInstructionError(opCode);
        }

        return Decoder.decodeTable[opCode].apply(pc, method);
    }

    @SafeVarargs
    static BiFunction<ProgramCounter, Method, Instruction>[] of(
            BiFunction<ProgramCounter, Method, Instruction>... elem) {
        return elem;
    }

    static final BiFunction<ProgramCounter, Method, Instruction>[] decodeTable = of(
            // @formatter:off
            /* 0x00 */  null, null, ICONST_M1::iconst_m1, ICONST_0::iconst_0,
            /* 0x04 */  ICONST_1::iconst_1, null, null, null,
            /* 0x08 */  null, null, null, null,
            /* 0x0c */  null, null, null, null,
            /* 0x10 */  BIPUSH::bipush, SIPUSH::sipush, null, null,
            /* 0x14 */  null, null, null, null,
            /* 0x18 */  null, null, null, null,
            /* 0x1c */  null, null, null, null,
            /* 0x20 */  null, null, null, null,
            /* 0x24 */  null, null, null, null,
            /* 0x28 */  null, null, null, null,
            /* 0x2c */  null, null, null, null,
            /* 0x30 */  null, null, null, null,
            /* 0x34 */  null, null, null, null,
            /* 0x38 */  null, null, null, null,
            /* 0x3c */  null, null, null, null,
            /* 0x40 */  null, null, null, null,
            /* 0x44 */  null, null, null, null,
            /* 0x48 */  null, null, null, null,
            /* 0x4c */  null, null, null, null,
            /* 0x50 */  null, null, null, null,
            /* 0x54 */  null, null, null, null,
            /* 0x58 */  null, null, null, null,
            /* 0x5c */  null, null, null, null,
            /* 0x60 */  null, null, null, null,
            /* 0x64 */  null, null, null, null,
            /* 0x68 */  null, null, null, null,
            /* 0x6c */  null, null, null, null,
            /* 0x70 */  null, null, null, null,
            /* 0x74 */  null, null, null, null,
            /* 0x78 */  null, null, null, null,
            /* 0x7c */  null, null, null, null,
            /* 0x80 */  null, null, null, null,
            /* 0x84 */  null, null, null, null,
            /* 0x88 */  null, null, null, null,
            /* 0x8c */  null, null, null, null,
            /* 0x90 */  null, null, null, null,
            /* 0x94 */  null, null, null, null,
            /* 0x98 */  null, null, null, null,
            /* 0x9c */  null, null, null, null,
            /* 0xa0 */  null, null, null, null,
            /* 0xa4 */  null, null, null, null,
            /* 0xa8 */  null, null, null, null,
            /* 0xac */  IRETURN::ireturn, null, null, null,
            /* 0xb0 */  null, RETURN::return_, null, null,
            /* 0xb4 */  null, null, null, null,
            /* 0xb8 */  null, null, null, null,
            /* 0xbc */  null, null, null, null,
            /* 0xc0 */  null, null, null, null,
            /* 0xc4 */  null, null, null, null,
            /* 0xc8 */  null, null, null, null,
            /* 0xcc */  null, null, null, null,
            /* 0xd0 */  null, null, null, null,
            /* 0xd4 */  null, null, null, null,
            /* 0xd8 */  null, null, null, null,
            /* 0xdc */  null, null, null, null,
            /* 0xe0 */  null, null, null, null,
            /* 0xe4 */  null, null, null, null,
            /* 0xe8 */  null, null, null, null,
            /* 0xec */  null, null, null, null,
            /* 0xf0 */  null, null, null, null,
            /* 0xf4 */  null, null, null, null,
            /* 0xf8 */  null, null, null, null,
            /* 0xfc */  null, null, null, null
            // @formatter:on
    );
}
