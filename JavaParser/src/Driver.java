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


import java.util.Scanner;

public class Driver {


    public static void main(String[] args) throws Exception {
        System.out.println("Program Start");
        String error_message = "Only create, drop, and parse [file] commands accepted";

        // todo combine parse and create into one, checks if tables have been created or not
        // args lenght > 1 then create or drop
        if (args.length == 1){

            if (args[0].toLowerCase().equals("create")){
                Scanner scanner = new Scanner(System.in);
                System.out.print("(The default user is root: ");
                System.out.print("Enter the mySQL DB username: ");
                String user = scanner.nextLine();


                System.out.print("(The default password is password: ");
                System.out.print("Enter the mySQL DB password: ");
                String password = scanner.nextLine();

                DBWriter queryManager = new DBWriter(user, password);

                queryManager.createTables();
            }
            else if (args[0].toLowerCase().equals("drop")){
                Scanner scanner = new Scanner(System.in);
                System.out.print("(The default user is root: ");
                System.out.print("Enter the mySQL DB username: ");
                String user = scanner.nextLine();


                System.out.print("(The default password is password: ");
                System.out.print("Enter the mySQL DB password: ");
                String password = scanner.nextLine();

                DBWriter queryManager = new DBWriter(user, password);

                queryManager.dropTables();
            }
            else if (args[0].toLowerCase().equals("parse")){
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter the GEDCOM filename: ");
                String file_name = scanner.nextLine();

                Parser.parser(file_name);
            }
            else {
                throw new IllegalArgumentException(error_message);
            }
        }

        else
        {
            throw new IllegalArgumentException(error_message);
        }

        System.out.println("Program Completed");

    }
}
