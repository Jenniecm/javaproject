package fr.troisil.info.java.funcprog;

import beans.Personne;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Hello world!
 *
 */
@Slf4j
public class App {
    public static void main( String[] args ) throws IOException {
        String filePath = "src/main/resources/personne.csv";

        try{
            List<Personne> personnes = Files.lines(Paths.get(filePath))
                    .map(line-> {
                        String[] elements = line.split(",");

                            String prenom = elements[0].trim();
                            int age = Integer.valueOf(elements[1].trim());
                            return new Personne(prenom, age);

                    })
                    .filter(personne ->  personne != null)
                    .collect(Collectors.toList());

            //Affichage Liste de personnes
            personnes.forEach(System.out::println);

            // Trier les personne par age croissant
            personnes = (List<Personne>) personnes.stream()
                    .sorted((p1, p2)-> Integer.compare(p1.getAge(), p2.getAge()))
                    .collect(Collectors.toList());
            personnes.forEach(System.out::println);

            // Trier les personnes en utilisant la classe comparator
                   personnes = (List<Personne>) personnes.stream()
                            .sorted(Comparator.comparingInt(Personne::getAge))
                            .collect(Collectors.toList());
                    personnes.forEach(System.out::println);


                    //Trier par age decroissant puis par prenom croissant
                    personnes = (List<Personne>) personnes.stream()
                            .sorted(Comparator.comparing(Personne::getPrenom)
                                    .thenComparing(Comparator.comparingInt(Personne::getAge).reversed()))
                            .collect(Collectors.toList());
                    personnes.forEach(System.out::println);

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
