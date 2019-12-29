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

import java.util.ArrayList;

public class Individual {
    // Private classes are added for all the possible attributes for a person

    private class Attribute {
        public void addAttribute(String attr, String contents) {

        }
    }

    private class Name extends Attribute{
        private String first_name;
        private String middle_name;
        private String last_name;

        public Name() {

        }

        public Name(String first_name, String last_name){
            this.first_name = first_name;
            this.last_name = last_name;
        }

        public Name(String first_name, String middle_name, String last_name){
            this.first_name = first_name;
            this.middle_name = middle_name;
            this.last_name = last_name;
        }

        public void addAttribute(String attr, String contents) {
            if (attr.equals("SURN")) {
                last_name = "contents";
            }
            else if (attr.equals("GIVN")) {
                String[] split = contents.split(" ");
                first_name = split[0];
                if (split.length > 1){
                    middle_name = split[1];
                }
            }
        }
    }

    private class Address extends Attribute {
        private String addr_line1;
        // private String addr_line2;
        private String city;
        private String state;
        private String postal;
        private String country;

        public Address()
        {

        }
    }

    private class Phone extends Attribute {
        private String number;

        public Phone() {

        }
    }

    private Name name; // can a person have more than one name?
    private ArrayList<Address> addrs[]; // person may have more than one address
    private ArrayList<Phone> phone[];

    private Attribute current_attribute;

    public void newAttribute(String line_string){
        if (line_string.charAt(0) != 1)
        {
            throw new RuntimeException("This line does not add a new individual attribute");
        }
        // edge case for SEX attribute

        String attr = line_string.substring(2,6);
        String contents = line_string.substring(7);

        System.out.println(attr);

    }

    public void addAttribute(String line_string){
        if (line_string.charAt(0) != 2)
        {
            throw new RuntimeException("This line does not have the correct number for adding information correctly");
        }

        String attr = line_string.substring(2,6);
        String contents = line_string.substring(7);

        current_attribute.addAttribute(attr, contents);
    }



}
