import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<String> nams = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "john");
        List<String> families = Arrays.asList("Evans", "Yong", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> person = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            person.add(new Person(
                    nams.get(new Random().nextInt(nams.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        long count = person.stream()
                .filter(x -> x.getAge() < 18)
                .count();
        System.out.println("В Лондоне проживает " + count + " несовершеннолетних человек");

        List<String> count1 = person.stream()
                .filter(x -> (x.getAge() >= 18 && x.getAge() <= 27))
                .map(x -> x.getFamily())
                .collect(Collectors.toList());

        //System.out.println("Список призывников в Лондоне " + count1);

        List<Person> count2 = person.stream()
                .filter(x -> x.getEducation().equals(Education.HIGHER))
                .filter(x -> x.getAge() >= 18 && x.getAge() <= 65)
                .filter(x -> x.getSex() == Sex.MAN || (x.getSex() == Sex.WOMAN && x.getAge() <= 60))
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
        //System.out.println("Отсортированный по фамилии список потенциально работающих граждан Лондона: \n" + count2);

    }
}
