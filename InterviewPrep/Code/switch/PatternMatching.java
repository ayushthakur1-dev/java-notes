public class PatternMatching {
    public static void main(String[] args) {
        Object o = "Ayush";

        // '_' is used for unamed variable that are not intended for use so we
        // tell the compiler that i don't want to use it and don't let me
        String result = switch (o) {
            case null -> "Null";
            case Integer _ -> "Integer";
            case String s when s.length() > 6 -> "large string";
            case String s when s.length() <= 6 -> "small string";
            default -> "unknown";
        };

        System.out.println(result);
    }
}
