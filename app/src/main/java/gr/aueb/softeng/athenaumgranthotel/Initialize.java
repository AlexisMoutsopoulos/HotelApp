package gr.aueb.softeng.athenaumgranthotel;

import android.util.Log;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Alexis on 30/7/2018.
 * This class create arraylists with all rooms in each floor.
 * There aren't room with number 13 on any floor
 */

public class Initialize implements Serializable {
    public ArrayList<Room> floor1;
    public ArrayList<Room> floor2;
    public ArrayList<Room> floor3;
    public ArrayList<Room> floor4;
    public ArrayList<Room> floor5;
    public ArrayList<Room> floor6;
    public ArrayList<Room> floor7;
    // The final arraylist with all rooms and floor
    public static ArrayList<Room> all;

    public Initialize(){
        floor1 = new ArrayList<Room>(23);
        floor2 = new ArrayList<Room>(16);
        floor3 = new ArrayList<Room>(16);
        floor4 = new ArrayList<Room>(16);
        floor5 = new ArrayList<Room>(16);
        floor6 = new ArrayList<Room>(16);
        floor7 = new ArrayList<Room>(5);
        all = new ArrayList<>(108);
        // The 1st floor has 23 rooms
        for(int i = 0 ; i<=22;i++){
            // No room has the number 13 in last 2 digit
            if(i<12){
                floor1.add( new Room(100 + i + 1));
            }else{
                floor1.add( new Room(100 + i + 2));
            }
        }
        // All floors have 16 rooms except from 1st and 7th
        for(int i = 0 ; i<=15;i++){
            if(i<12){
                floor2.add( new Room(200 + i + 1));
            }else{
                floor2.add( new Room(200 + i + 2));
            }
        }
        for(int i = 0 ; i<=15;i++){

            if(i<12){
                floor3.add(new Room(300 + i + 1));
            }else{
                floor3.add( new Room(300 + i + 2));
            }
        }
        for(int i = 0 ; i<=15;i++){

            if(i<12){
                floor4.add( new Room(400 + i + 1));
            }else{
                floor4.add(new Room(400 + i + 2));
            }
        }
        for(int i = 0 ; i<=15;i++){

            if(i<12){
                floor5.add( new Room(500 + i + 1));
            }else{
                floor5.add(new Room(500 + i + 2));
            }
        }

        for(int i = 0 ; i<=15;i++){

            if(i<12){
                floor6.add(new Room(600 + i + 1));
            }else{
                floor6.add(new Room(600 + i + 2));
            }
        }
        // 7th floor has 5 rooms
        floor7.add( new Room(701));
        floor7.add(new Room(702));
        floor7.add( new Room(703));
        floor7.add(new Room(704));
        floor7.add( new Room(705));
        /////////////////////////////////////
        all.addAll(floor1);
        all.addAll(floor2);
        all.addAll(floor3);
        all.addAll(floor4);
        all.addAll(floor5);
        all.addAll(floor6);
        all.addAll(floor7);
        ////////////////////////////////////
    }
}
