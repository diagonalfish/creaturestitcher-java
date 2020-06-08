/*
 * NameSets.java
 * Creature Stitcher
 *
 * Copyright (C) 2008-2020 Eric Goodwin
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package org.ccdevnet.cstitcher.util;

/**
 * Some conversion functions for the integers used internally for various
 * things to user-friendly strings (and integer-to-letter)
 * Some things based on:  http://www.gamewaredevelopment.co.uk/cdn/cdn_more.php?CDN_article_id=31
 * and http://wiki.ccdevnet.org/index.php/Creating_Body_Data_files_(.ATT)
 */
public class NameSets {

    public static String bodyPartName(int i, boolean capitalize) {
        String part;
        switch (i) {
            case 0:
                part = "head";
                break;
            case 1:
                part = "body";
                break;
            case 2:
                part = "left upper leg";
                break;
            case 3:
                part = "left lower leg";
                break;
            case 4:
                part = "left foot";
                break;
            case 5:
                part = "right upper leg";
                break;
            case 6:
                part = "right lower leg";
                break;
            case 7:
                part = "right foot";
                break;
            case 8:
                part = "left upper arm";
                break;
            case 9:
                part = "left lower arm";
                break;
            case 10:
                part = "right upper arm";
                break;
            case 11:
                part = "right lower arm";
                break;
            case 12:
                part = "tail base";
                break;
            case 13:
                part = "tail tip";
                break;
            default:
                part = "Unknown";
        }

        if (capitalize) return part.substring(0, 1).toUpperCase() + part.substring(1);
        else return part;
    }

    public static String intToLetter(int i) {
        String part;
        switch (i) {
            case 0:
                return "a";
            case 1:
                return "b";
            case 2:
                return "c";
            case 3:
                return "d";
            case 4:
                return "e";
            case 5:
                return "f";
            case 6:
                return "g";
            case 7:
                return "h";
            case 8:
                return "i";
            case 9:
                return "j";
            case 10:
                return "k";
            case 11:
                return "l";
            case 12:
                return "m";
            case 13:
                return "n";
            default:
                part = "?";
        }

        return part;
    }

    public static String genderName(int i, boolean capitalize) {
        String genname;
        switch (i) {
            case 0:
                genname = "male Norn";
                break;
            case 1:
                genname = "male Grendel";
                break;
            case 2:
                genname = "male Ettin";
                break;
            case 3:
                genname = "male Geat";
                break;
            case 4:
                genname = "female Norn";
                break;
            case 5:
                genname = "female Grendel";
                break;
            case 6:
                genname = "female Ettin";
                break;
            case 7:
                genname = "female Geat";
                break;
            default:
                genname = "unknown gender";
        }
        if (capitalize) return genname.substring(0, 1).toUpperCase() + genname.substring(1);
        else return genname;

    }

    public static String lifestageName(int i, boolean capitalize) {
        String stgname;
        switch (i) {
            case 0:
                stgname = "baby";
                break;
            case 1:
                stgname = "child";
                break;
            case 2:
                stgname = "adolescent";
                break;
            case 3:
                stgname = "youth";
                break;
            case 4:
                stgname = "adult";
                break;
            case 5:
                stgname = "old";
                break;
            case 6:
                stgname = "senile";
                break;
            default:
                stgname = "unknown lifestage";
        }
        if (capitalize) return stgname.substring(0, 1).toUpperCase() + stgname.substring(1);
        else return stgname;

    }

}
