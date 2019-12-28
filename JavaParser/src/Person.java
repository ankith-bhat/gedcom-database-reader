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

class Person {
    // Private classes are added for all the possible attributes for a person

    private class Name {
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
    }

    private class Address {
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

    private class Phone {
        private String number;

        public Phone() {

        }
    }

    private Name name; // can a person have more than one name?
    private ArrayList<Address> addrs[]; // person may have more than one address
    private ArrayList<Phone> phone[];

    public void addAttribute(){


    }



}
