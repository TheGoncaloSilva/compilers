public class Fraction{
    private int num;
    private int den;

    public Fraction(int val){
        this.num = val;
        this.den = 1;
    }

    public Fraction(int val1, int val2){
        this.num = val1;
        this.den = val2;
    }

    public Fraction(Fraction frac){
        this.num = frac.num;
        this.den = frac.den;
    }

    public int getNum(){ return num; }
    public void setNum(int n){ this.num = n; }
    public int getDen(){ return den; }
    public void setDen(int d){ this.den = d; }

    public static Fraction sum(Fraction frac1, Fraction frac2){
        int num = (frac1.num*frac2.den + frac2.num*frac1.den);
        int den = frac1.den*frac2.den;
        return new Fraction(num, den);
    }

    public static Fraction sub(Fraction frac1, Fraction frac2){
        int num = (frac1.num*frac2.den - frac2.num*frac1.den);
        int den = frac1.den*frac2.den;
        return new Fraction(num, den);
    }

    public static Fraction mull(Fraction frac1, Fraction frac2){
        int num = frac1.num*frac2.num;
        int den = frac1.den*frac2.den;
        return new Fraction(num, den);
    }

    public static Fraction div(Fraction frac1, Fraction frac2){
        int val1 = frac1.num/frac1.den;
        int val2 = frac2.den/frac2.num;
        return new Fraction(val1*val2);
    }

    public static Fraction reduce(Fraction frac){

        return null;
    }

    @Override 
    public String toString(){
        // change this with unary
        if (this.den == 1)
            return this.num + "";
        else
            return this.num + "/" + this.den;
    }
}