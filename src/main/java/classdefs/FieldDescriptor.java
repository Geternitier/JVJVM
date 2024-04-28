package classdefs;

public class FieldDescriptor {
    public static final char DESC_byte = 'B';
    public static final char DESC_char = 'C';
    public static final char DESC_double = 'D';
    public static final char DESC_float = 'F';
    public static final char DESC_int = 'I';
    public static final char DESC_long = 'J';
    public static final char DESC_reference = 'L';
    public static final char DESC_short = 'S';
    public static final char DESC_boolean = 'Z';
    public static final char DESC_array = '[';
    public static final char DESC_void = 'V';

    public static String of(String name) {
        return switch (name) {
            case "boolean" -> Character.toString(DESC_boolean);
            case "byte" -> Character.toString(DESC_byte);
            case "char" -> Character.toString(DESC_char);
            case "double" -> Character.toString(DESC_double);
            case "float" -> Character.toString(DESC_float);
            case "int" -> Character.toString(DESC_int);
            case "long" -> Character.toString(DESC_long);
            case "short" -> Character.toString(DESC_short);
            case "void" -> Character.toString(DESC_void);
            default -> 'L' + name.replace('.', '/') + ';';
        };
    }
}
