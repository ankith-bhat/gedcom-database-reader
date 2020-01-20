/*
 * Copyright (c) 2019, 2020 Ankith Bhat, Amith Bhat
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Parser {

    public static void parser(String file_name) throws Exception {
        System.out.println("Parsing file...");

        // todo replace w/ File file = new File(args[1]);
        File file = new File("../" + file_name);

        BufferedReader file_buffer = new BufferedReader(new FileReader(file));

        String line_string;
        int line_num = 0;

        ArrayList<Individual> individuals = new ArrayList<>();
        ArrayList<Family> families = new ArrayList<>();
        Individual current_individual = null;
        Family current_family = null;
        boolean adding_indi = false;
        boolean adding_fam = false;

        // read line-by-line
        while ((line_string = file_buffer.readLine()) != null) {
            // edge case: first line has extra character
            if (line_num == 0){
                if (!line_string.contains("0 HEAD")){
                    throw new RuntimeException("GEDCOM file does not start correctly");
                }
                System.out.println("Head found");
            }
            else if (line_string.contains("0 TRLR")) {
                System.out.println("Trailer found");
            }
            else
            {
                if (line_string.charAt(0) == '0' && line_string.contains("INDI")) {
                    System.out.println("New Individual");
                    current_individual = new Individual(line_string);
                    individuals.add(current_individual);
                    adding_indi = true;
                    adding_fam = false;
                }
                else if (line_string.charAt(0) == '1' && adding_indi) {
                    if (current_individual == null){
                        throw new RuntimeException("Invalid individual to add attribute to");
                    }
                    current_individual.newAttribute(line_string);
                }
                else if (line_string.charAt(0) == '2'&& adding_indi) {
                    if (current_individual == null){
                        throw new RuntimeException("Invalid individual to add attribute to");
                    }
                    current_individual.addAttribute(line_string);
                }
                else if (line_string.charAt(0) == '0' && line_string.contains("FAM")) {
                    System.out.println("New Family");
                    current_family = new Family(line_string);
                    families.add(current_family);
                    adding_indi = false;
                    adding_fam = true;
                }
                else if (line_string.charAt(0) == '1' && adding_fam) {
                    if (current_family == null){
                        throw new RuntimeException("Invalid family to add attribute to");
                    }
                    current_family.newAttribute(line_string);
                }
                else if (line_string.charAt(0) == '2'&& adding_fam) {
                    if (current_family == null){
                        throw new RuntimeException("Invalid family to add attribute to");
                    }
                    current_family.addAttribute(line_string);
                }
            }

            System.out.println(line_string);
            line_num++;
        }

        for (Individual individual : individuals){
            System.out.println(individual.toString());
        }
        for (Family family : families){
            System.out.println(family.toString());
        }

        System.out.println("Writing to Database...");

        Scanner scanner = new Scanner(System.in);
        System.out.println("(The default user is root: ");
        System.out.print("Enter the mySQL DB username: ");
        String user = scanner.nextLine();


        System.out.println("(The default password is password: ");
        System.out.print("Enter the mySQL DB password: ");
        String password = scanner.nextLine();

        DBWriter writer = new DBWriter(user, password);

        if (writer.hasConnection()) {
            for (Individual individual : individuals){
                String[] queries = individual.getQueries();
                for (String query: queries){
                    System.out.println(query);
                     writer.executeQuery(query);
                }
            }

            for (Family family : families){
                String[] queries = family.getQueries();
                for (String query: queries){
                    System.out.println(query);
                     writer.executeQuery(query);
                }
            }
        }
    }
}
