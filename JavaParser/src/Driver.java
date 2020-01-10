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

import java.io.*;
import java.util.ArrayList;

public class Driver {


    public static void main(String[] args) throws IOException {
        System.out.println("Program Start");

        // args lenght > 1 then create or drop
        if (args.length == 1){
            String user = "user";
            String password = "password";
            // todo prompt for username and password

            DBWriter queryManager = new DBWriter(user, password);
            if (args[0].equals("create")){
                queryManager.createTables();
            }
            else if (args[0].equals("drop")){
                queryManager.dropTables();
            }
            else {
                // throw error
            }
        }
        else if (args.length == 2){
            if (args[0].equals("parse")){
                Parser.parser(args);
            }
            else {
                // throw error
            }
        }
        else
        {
            // throw error
        }
        System.out.println("Program Completed");

    }
}