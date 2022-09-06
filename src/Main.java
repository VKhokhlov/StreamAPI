import java.util.*;
import java.util.stream.Collectors;

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

        long before18Persons = persons.stream()
                .filter(person -> person.getAge() < 18)
                .count();

        List<String> militaryPersons = persons.stream()
                .filter(p -> p.getSex() == Sex.MAN)
                .filter(p -> p.getAge() >= 18)
                .filter(p -> p.getAge() < 27)
                .map(p -> p.getFamily())
                .collect(Collectors.toList());

        Collection<Person> workablePersons = persons.stream()
                .filter(p -> p.getAge() > 18)
                .filter(p -> p.getEducation() == Education.HIGHER)
                .filter(p -> (p.getSex() == Sex.MAN && p.getAge() < 65) || (p.getSex() == Sex.WOMAN && p.getAge() < 60))
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());

        System.out.println("Количество несовершеннолетних: " + before18Persons);
        System.out.println("Количество призывников:" + militaryPersons.size());
        System.out.println("Количество работоспособных: " + workablePersons.size());
    }
}