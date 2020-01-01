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
import java.util.HashMap;
import java.util.Map;

public class Individual {
    // Private classes are added for all the possible attributes for a person

    private class Name extends Attribute{
        private String first_name;
        private String middle_name;
        private String last_name;

        public Name(){

        }

        public Name(String contents){
            String[] split = contents.split(" ");

            if (split.length == 1){
                first_name = split[0];
            }
            else if (split.length == 2) {
                first_name = split[0];
                last_name = split[1];
            }
            else {
                first_name = split[0];
                for (int i = 1; i < split.length - 1; i++)
                {
                    if (middle_name == null){
                        middle_name = split[i];
                        continue;
                    }

                    if (i != 1) middle_name += " ";
                    middle_name+= split[i];
                }
                last_name = split[split.length -1];
            }
        }

        @Override
        public void addAttribute(String attr, String contents) {
            if (attr.equals("SURN")) {
                last_name = contents;
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

    private class Sex extends Attribute {
        private String sex;

        public Sex(String sex) {
            this.sex = sex;
        }
    }

    private class Caste extends Attribute{
        private String caste;

        public Caste(String caste) {
            this.caste = caste;
        }
    }

    private class Birth extends Event {

        public Birth() {}
    }

    private class Baptism extends Event {
        public Baptism() {}
    }

    private class Death extends Event {
        public Death() {}
    }

    private class Spouse extends Attribute {
        private String family_id;

        public Spouse(String family_id) {
            this.family_id = family_id.replaceAll("@", "");;
        }
    }

    private class Child extends Attribute {
        private String family_id;

        public Child(String family_id) {
            this.family_id = family_id.replaceAll("@", "");
        }
    }

    private class Flag extends Attribute {
        private String flag;

        public Flag() {

        }

        @Override
        public void addAttribute(String line_string) {
            flag = line_string.substring(11);
        }
    }

    private String id;
    private Name name; // can a person have more than one name?
    private Sex sex;
    private Caste caste;
    private Birth birth;
    private Baptism baptism;
    private Death death;
    private ArrayList<Spouse> spouses;
    private Child child; // can you belong to more than one family?
    private Flag flag;

    private Attribute current_attribute;

    public Individual(String line_string) {
        String[] split = line_string.split(" ");
        this.id = split[1].replaceAll("@", "");

        spouses = new ArrayList<>();
    }

    public void newAttribute(String line_string){
        if (line_string.charAt(0) != '1')
        {
            throw new RuntimeException("This line does not add a new individual attribute: " + line_string);
        }
        String attr;
        String contents = "";

        // edge case for SEX attribute, b/c its 3 chars, not 4 chars
        if (line_string.substring(2,5).equals("SEX"))
        {
            attr = line_string.substring(2,5); // SEX
            contents =  line_string.substring(6);
            sex = new Sex(contents);
            current_attribute = null;
        }
        // edge case for _FLGS attribute
        else if (line_string.contains("_FLGS")){
            attr = line_string.substring(2,7);
            flag = new Flag();
            current_attribute = flag;
        }
        else
        {
            attr = line_string.substring(2,6);
            if (line_string.length() >= 8) contents = line_string.substring(7);

            if (attr.equals("NAME"))
            {
                name = new Name(contents);
                current_attribute = name;
            }
            else if (attr.equals("CAST"))
            {
                caste = new Caste(contents);
                current_attribute = null;
            }
            else if (attr.equals("BIRT"))
            {
                birth = new Birth();
                current_attribute = birth;
            }
            else if (attr.equals("BAPT"))
            {
                baptism = new Baptism();
                current_attribute = baptism;
            }
            else if (attr.equals("DEAT"))
            {
                death = new Death();
                current_attribute = death;
            }
            else if (attr.equals("FAMS"))
            {
                Spouse new_spouse = new Spouse(contents);
                spouses.add(new_spouse);
                current_attribute = new_spouse;
            }
            else if (attr.equals("FAMC"))
            {
                child = new Child(contents);
                current_attribute = child;
            }
        }

    }

    public void addAttribute(String line_string){
        if (line_string.charAt(0) !=  '2')
        {
            throw new RuntimeException("This line does not have the correct number for adding information correctly: " + line_string);
        }
        else if (current_attribute == null)
        {
            throw new RuntimeException("Error: No individual attribute has been chosen to update");
        }
        else if (current_attribute instanceof Flag){
            current_attribute.addAttribute(line_string);
        }

        String attr = line_string.substring(2,6);
        String contents = line_string.substring(7);

        current_attribute.addAttribute(attr, contents);
    }

    @Override
    public String toString(){
        if (name == null) return "";
        StringBuilder name_string = new StringBuilder();

        if (name.first_name != null) name_string.append(name.first_name + " ");
        if (name.middle_name != null) name_string.append(name.middle_name + " ");
        if (name.last_name != null) name_string.append(name.last_name);

        return name_string.toString();
    }

}
