import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        long under18List = persons.stream()
                .filter(person -> person.getAge() < 18)
                .count();
        System.out.println("<<<Количество несовершеннолетних лиц>>>");
        System.out.println("Количество человек младше 18 лет - " + under18List);

        List<String> recruitList = persons.stream()
                .filter(person -> person.getSex() == Sex.MAN)
                .filter(person -> person.getAge() > 18 && person.getAge() < 27)
                .map(Person::getFamily)
                .collect(Collectors.toList());
        System.out.println("<<<Список фамилий призывников>>>");
        System.out.println(recruitList);

        List<Person> workablePersonList = persons.stream()
                .filter(person -> person.getEducation() == Education.HIGHER)
                .filter(person -> person.getAge() > 18 && person.getAge() < 65 && person.getSex() == Sex.MAN ||
                        person.getAge() > 18 && person.getAge() < 60 && person.getSex() == Sex.WOMAN)
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
        System.out.println("<<<Список работоспособного населения с высшим образованием>>>");
        for (Person person : workablePersonList) {
            System.out.println(person);
        }
    }
}