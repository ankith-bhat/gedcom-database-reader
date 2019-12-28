/*
 * Copyright (c) 2019 Ankith Bhat, Amith Bhat
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

class Parser {

    public Parser() {

    }

    public static void main(String[] args) throws IOException {
        System.out.println("Program Start");

        File file = new File("555SAMPLE.GED");

        // todo try-catch
        BufferedReader file_buffer = new BufferedReader(new FileReader(file));

        // todo try-catch
        String line_string;
        int line_num = 0;

        // read line-by-line
        while ((line_string = file_buffer.readLine()) != null) {
            // edge case: first line has extra character
            if (line_num == 0){
               line_string = line_string.substring(1);
               if (!line_string.equals("0 HEAD")){
                  throw new RuntimeException("GEDCOM file does not start correctly");
                }
               System.out.println("Head found");
            }

            else if (line_string.equals("0 TRLR")) {
                System.out.println("Trailer found");
            }

            else
            {
                if (line_string.charAt(0) == '0') {
                    System.out.println("New Person");
                }
            }

            System.out.println(line_string);
            line_num++;
        }


        System.out.println("Program Completed");

    }
}
