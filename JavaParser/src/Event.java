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

public class Event extends Attribute {
    private String place;
    private String date;

    public Event() {

    }

    @Override
    public void addAttribute(String attr, String contents) {
        if (attr.equals("PLAC"))
        {
            place = contents;
            return;
        }
        else if (attr.equals("DATE"));
        {
            date = contents;
            return;
        }

    }

    public String getPlace(){
        return place;
    }

    public String getDate(){
        return date;
    }

    public String toString(){
        StringBuilder stringbuilder = new StringBuilder();

        if (place != null) stringbuilder.append(" place : " + place);
        if (date != null) stringbuilder.append(" date: " + date);

        return stringbuilder.toString();

    }
}
