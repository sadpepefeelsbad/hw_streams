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

        long minors = persons.stream().
                filter(person -> person.getAge() < 18).
                count();

        List<String> conscripts = persons.stream().
                filter(person -> person.getSex() == Sex.MAN).
                filter(person -> person.getAge() >= 18 && person.getAge() < 27).
                map(Person::getFamily).
                toList();

        List<Person> candidates = persons.stream().
                filter(person -> person.getEducation() == Education.HIGHER).
                filter(person -> person.getAge() >= 18).
                filter(person -> (person.getSex() == Sex.WOMAN && person.getAge() < 60) || (person.getSex() == Sex.MAN && person.getAge() < 65)).
                sorted(Comparator.comparing(Person::getFamily)).
                toList();
    }
}