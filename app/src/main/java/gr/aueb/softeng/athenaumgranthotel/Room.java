package gr.aueb.softeng.athenaumgranthotel;

import java.io.Serializable;


/**
 * Created by Alexis on 30/7/2018.
 * Class represent the room Objects
 */

public class Room implements Serializable{
    private  int number;
    private int floor;
    private boolean checked ;
    private boolean isIn ;
    private boolean disturb;
    public boolean water1 ;
    public boolean water2;
    public boolean coca1 ;
    public boolean coca2;
    public boolean fanta ;
    public boolean zero;
    public boolean soda ;
    public boolean whiteWine;
    public boolean redWine ;
    public boolean chips ;
    public boolean light;
    public boolean glass1 ;
    public boolean glass2;
    public boolean tea1 ;
    public boolean tea2;
    public  int NumOfWaters=0;
    public  int NumOfCocas=0;
    public  int NumOfLight=0;
    public  int NumOfZeros=0;
    public  int NumOfFantas=0;
    public  int NumOfSodas=0;
    public  int NumOfRedWines=0;
    public  int NumOfWhiteWines=0;
    public  int NumOfChips=0;

    public Room(int number) {
        this.number = number;
        this.floor = number / 100;
        this.water1 =false;
        this.water2 =false;
        this.coca1 =false;
        this.coca2 =false;
        this.fanta =false;
        this.zero =false;
        this.soda =false;
        this.redWine =false;
        this.whiteWine =false;
        this.chips =false;
        this.light =false;
        this.glass1 =false;
        this.glass2 =false;
        this.tea1 =false;
        this.tea2 =false;
    }

    public int getNumber() {
        return number;
    }

    public int getFloor() {
        return floor;
    }


    @Override
    public  String toString(){
        return "Floor " + floor + " Room " + number;

    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setIn(boolean in) {
        isIn = in;
    }

    public boolean isIn() {
        return isIn;
    }

    public void setDisturb(boolean disturb) {
        this.disturb = disturb;
    }

    public boolean getDisturb() {
        return this.disturb ;
    }

    public boolean isDisturb() {
        return disturb;
    }
}
