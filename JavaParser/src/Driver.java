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


public class Driver {


    public static void main(String[] args) throws Exception {
        System.out.println("Program Start");
        String error_message = "Only create, drop, and parse [file] commands accepted";

        // args lenght > 1 then create or drop
        if (args.length == 1){
            String user = "root";
            String password = "password";
            // todo prompt for username and password

            DBWriter queryManager = new DBWriter(user, password);
            if (args[0].toLowerCase().equals("create")){
                queryManager.createTables();
            }
            else if (args[0].toLowerCase().equals("drop")){
                queryManager.dropTables();
            }
            else {
                throw new IllegalArgumentException(error_message);
            }
        }
        else if (args.length == 2){
            if (args[0].toLowerCase().equals("parse")){
                Parser.parser(args);
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
