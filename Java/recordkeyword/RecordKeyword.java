public class RecordKeyword {
    public static void main(String[] args) {
        Student student1 = new Student("Divit", 1, 'A');
        Student student2 = new Student("Divit", 1, 'A');

        // now constructor, getter, equals, toString, hasCode are handled by record
        System.out.println(student1.name());
        System.out.println(student1.equals(student2));
        System.out.println(student1);
    }
}

record Student(String name, int division, char section) {
    // we can create all the fields just like any other class
}