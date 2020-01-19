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

import java.util.ArrayList;

public class Family {
    private class Marriage extends Event{
        public Marriage() {}
    }

    private class Divorce extends Event {
        public Divorce() {}
    }

    private String family_id;
    private String spouse1_id; // Husband
    private String spouse2_id; // Wife
    private Marriage marriage;
    private Divorce divorce;
    private ArrayList<String> children_id;
    private Attribute current_attribute;


    public Family(String line_string)
    {
        if (line_string.charAt(0) != '0' || !(line_string.contains("FAM")))
        {
            throw new RuntimeException("This line does not add a new family " + line_string);
        }
        String split[] = line_string.split(" ");
        family_id = split[1].replaceAll("@", "");

        children_id = new ArrayList<>();
        current_attribute = null;
    }

    public void newAttribute(String line_string){
        if (line_string.charAt(0) != '1')
        {
            throw new RuntimeException("This line does not add a new family attribute: " + line_string);
        }
        String attr;
        String contents = "";

        attr = line_string.substring(2,6);
        if (line_string.length() >= 8) contents = line_string.substring(7);

        if (attr.equals("HUSB")){
            spouse1_id = contents.replaceAll("@", "");
            current_attribute = null;
        }
        else if (attr.equals("WIFE")){
            spouse2_id = contents.replaceAll("@", "");
            current_attribute = null;
        }
        else if (attr.equals("CHIL")){
            children_id.add(contents.replaceAll("@", ""));
            current_attribute = null;
        }
        else if (attr.contains("MARR")){
            marriage = new Marriage();
            current_attribute = marriage;

        }
        else if (attr.contains("DIV")){
            divorce = new Divorce();
            current_attribute = divorce;
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

        String attr = line_string.substring(2,6);
        String contents = line_string.substring(7);

        current_attribute.addAttribute(attr, contents);
    }

    public String [] getQueries(){
        ArrayList<String> queries = new ArrayList<>();


        String spouse_query = "INSERT INTO FamilySpouse VALUES ('" + family_id + "', '" + spouse1_id + "', '" + spouse2_id + "');";
        queries.add(spouse_query);

        int child_order = 1;
        for (String child_id : children_id){
            String child_query = "INSERT INTO FamilyChild VALUES ('" + family_id + "', '" + child_id + "', '" + child_order + "');";
            queries.add(child_query);
            child_order++;
        }

        return queries.toArray(new String[queries.size()]);
    }

    @Override
    public String toString(){
        StringBuilder family_string = new StringBuilder();

        family_string.append("Family id: " + family_id);
        family_string.append(" Husband: " + spouse1_id + " Wife: " + spouse2_id);
        family_string.append(" Children: " + children_id.size());

        return family_string.toString();
    }


}
