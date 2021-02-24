package cs301.birthdaycake;

public class cakeModel {
    //Instance variables
    public boolean lit = true;
    public int candleNum = 2;
    public boolean frosting = true;
    public boolean candles = true;
    //
    public float bx = 400f;
    public float by = 400f;

    //checkpt #2
    public boolean isBalloon = false;

    ///get & set
    public void setIsBalloon (boolean isBalloon) {
        this.isBalloon = isBalloon;
    }
    public boolean getCandleIsLit(){
        return isBalloon;
    }
}
