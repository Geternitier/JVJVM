package interpreter.instruction;

import interpreter.instruction.constants.*;
import interpreter.instruction.extended.*;
import interpreter.instruction.loads.*;
import interpreter.instruction.stores.*;
import interpreter.instruction.control.*;
import interpreter.instruction.stack.*;
import interpreter.instruction.math.*;
import interpreter.instruction.conversions.*;
import interpreter.instruction.comparisons.*;
import interpreter.instruction.references.*;
import runtime.ProgramCounter;
import runtime.classdata.Method;

import java.util.function.BiFunction;

public class Decoder {
    public static Instruction decode(ProgramCounter pc, Method method){
        int opCode = Byte.toUnsignedInt(pc.readByte());
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
            /* 0x00 */  NOP::new, ACONST_NULL::new, ICONST_M1::iconst_m1, ICONST_0::new,
            /* 0x04 */  ICONST_1::new, ICONST_2::new, ICONST_3::new, ICONST_4::new,
            /* 0x08 */  ICONST_5::new, LCONST_0::new, LCONST_1::new, FCONST_0::new,
            /* 0x0c */  FCONST_1::new, FCONST_2::new, DCONST_0::new, DCONST_1::new,
            /* 0x10 */  BIPUSH::new, SIPUSH::new, LDC::new, LDC_W::new,
            /* 0x14 */  LDC2_W::new, ILOAD::new, LLOAD::new, FLOAD::new,
            /* 0x18 */  DLOAD::new, ALOAD::new, ILOAD_0::new, ILOAD_1::new,
            /* 0x1c */  ILOAD_2::new, ILOAD_3::new, LLOAD_0::new, LLOAD_1::new,
            /* 0x20 */  LLOAD_2::new, LLOAD_3::new, FLOAD_0::new, FLOAD_1::new,
            /* 0x24 */  FLOAD_2::new, FLOAD_3::new, DLOAD_0::new, DLOAD_1::new,
            /* 0x28 */  DLOAD_2::new, DLOAD_3::new, ALOAD_0::new, ALOAD_1::new,
            /* 0x2c */  ALOAD_2::new, ALOAD_3::new, null, null,
            /* 0x30 */  null, null, null, null,
            /* 0x34 */  null, null, ISTORE::new, LSTORE::new,
            /* 0x38 */  FSTORE::new, DSTORE::new, ASTORE::new, ISTORE_0::new,
            /* 0x3c */  ISTORE_1::new, ISTORE_2::new, ISTORE_3::new, LSTORE_0::new,
            /* 0x40 */  LSTORE_1::new, LSTORE_2::new, LSTORE_3::new, FSTORE_0::new,
            /* 0x44 */  FSTORE_1::new, FSTORE_2::new, FSTORE_3::new, DSTORE_0::new,
            /* 0x48 */  DSTORE_1::new, DSTORE_2::new, DSTORE_3::new, ASTORE_0::new,
            /* 0x4c */  ASTORE_1::new, ASTORE_2::new, ASTORE_3::new, null,
            /* 0x50 */  null, null, null, null,
            /* 0x54 */  null, null, null, POP::new,
            /* 0x58 */  POP2::new, DUP::new, DUP_X1::new, DUP_X2::new,
            /* 0x5c */  DUP2::new, DUP2_X1::new, DUP2_X2::new, SWAP::new,
            /* 0x60 */  IADD::new, LADD::new, FADD::new, DADD::new,
            /* 0x64 */  ISUB::new, LSUB::new, FSUB::new, DSUB::new,
            /* 0x68 */  IMUL::new, LMUL::new, FMUL::new, DMUL::new,
            /* 0x6c */  IDIV::new, LDIV::new, FDIV::new, DDIV::new,
            /* 0x70 */  IREM::new, LREM::new, FREM::new, DREM::new,
            /* 0x74 */  INEG::new, LNEG::new, FNEG::new, DNEG::new,
            /* 0x78 */  ISHL::new, LSHL::new, ISHR::new, LSHR::new,
            /* 0x7c */  IUSHR::new, LUSHR::new, IAND::new, LAND::new,
            /* 0x80 */  IOR::new, LOR::new, IXOR::new, LXOR::new,
            /* 0x84 */  IINC::new, I2L::new, I2F::new, I2D::new,
            /* 0x88 */  L2I::new, L2F::new, L2D::new, F2I::new,
            /* 0x8c */  F2L::new, F2D::new, D2I::new, D2L::new,
            /* 0x90 */  D2F::new, I2B::new, I2C::new, I2S::new,
            /* 0x94 */  LCMP::new, FCMPL::new, FCMPG::new, DCMPL::new,
            /* 0x98 */  DCMPG::new, IFEQ::new, IFNE::new, IFLT::new,
            /* 0x9c */  IFGE::new, IFGT::new, IFLE::new, IF_ICMPEQ::new,
            /* 0xa0 */  IF_ICMPNE::new, IF_ICMPLT::new, IF_ICMPGE::new, IF_ICMPGT::new,
            /* 0xa4 */  IF_ICMPLE::new, IF_ACMPEQ::new, IF_ACMPNE::new, GOTO::new,
            /* 0xa8 */  JSR::new, RET::new, TABLESWITCH::new, LOOKUPSWITCH::new,
            /* 0xac */  IRETURN::new, LRETURN::new, FRETURN::new, DRETURN::new,
            /* 0xb0 */  ARETURN::new, RETURN::new, GETSTATIC::new, null,
            /* 0xb4 */  GETFIELD::new, PUTFIELD::new, INVOKEVIRTUAL::new, INVOKESPECIAL::new,
            /* 0xb8 */  INVOKESTATIC::new, null, null, NEW::new,
            /* 0xbc */  null, null, null, null,
            /* 0xc0 */  null, null, MONITORENTER::new, null,
            /* 0xc4 */  null, null, IFNULL::new, IFNONNULL::new,
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
