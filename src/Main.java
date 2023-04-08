import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        long stream1 = persons.stream()
                .filter(p1 ->p1.getAge()<18)
                .count();
        System.out.println("Количество несовершеннолетних равно: " + stream1);
        List<String> stream2 = persons.stream()
                .filter(p1 -> p1.getAge()>17 && p1.getAge()<28 && p1.getSex().equals(Sex.Man))
                .map(Person::getFamily)
                .toList();
       // МНОГО  System.out.println("Фамилии призывников: " + stream2);
        List<Person> stream3 = persons.stream()
                .filter(p1-> (p1.getAge()<61 && p1.getAge()>17 && p1.getSex().equals(Sex.Woman)
                        ||p1.getAge()<66 && p1.getAge()>17 && p1.getSex().equals(Sex.Man))
                        && p1.getEducation().equals(Education.Higher))
                .sorted(Comparator.comparing(Person::getFamily))
                .toList();
       // МНОГО System.out.println("Работоспособные люди: " + stream3);
    }
}