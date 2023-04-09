import javax.management.RuntimeErrorException;

public class Fraction {
    private int numerator;
    private int denominator;

    /*
     *  Default constructor
     */
    public Fraction(){
        this.numerator = 0;
        this.denominator = 1;
    }

    /*
     *  Default constructor with only one number
     */
    public Fraction(int numerator){
        this.numerator = numerator;
        this.denominator = 1;
    }

    /*
     *  Default constructor for both numbers
     */
    public Fraction(int numerator, int denominator){
        this.numerator = numerator;
        this.denominator = denominator;

        check_no_div_by_zero(this);
    }

    /*
     *  Getters and Setters
     */
    public int getNumerator() { return numerator; }
    public int getDenominator() { return denominator; }
    public void setNumerator(int numerator) { this.numerator = numerator; }
    public void setDenominator(int denominator) { this.denominator = denominator; check_no_div_by_zero(this); }

    /* 
     * Add two fractions and return the result
     */
    public static Fraction add(Fraction frac1, Fraction frac2){
        Fraction resultFrac = new Fraction();
        resultFrac.numerator = (frac1.numerator * frac2.denominator) + 
                               (frac2.numerator * frac1.denominator);
        resultFrac.denominator = frac1.denominator * frac2.denominator;

        check_no_div_by_zero(resultFrac);

        return resultFrac;
    }

    /*
     * Subtract two fractions and return the result
     */
    public static Fraction sub(Fraction frac1, Fraction frac2){
        Fraction resultFrac = new Fraction();
        resultFrac.numerator = (frac1.numerator * frac2.denominator) + 
                                (-frac2.numerator * frac1.denominator);
        resultFrac.denominator = frac1.denominator * frac2.denominator;

        check_no_div_by_zero(resultFrac);

        return resultFrac;
    }

    /*
     * Multiply two fractions and return the result
     */
    public static Fraction mul(Fraction frac1, Fraction frac2){
        Fraction resultFrac = new Fraction();
        resultFrac.numerator = (frac1.numerator * frac2.numerator);
        resultFrac.denominator = frac1.denominator * frac2.denominator;

        check_no_div_by_zero(resultFrac);

        return resultFrac;
    }

    /*
     * Multiply two fractions and return the result
     */
    public static Fraction div(Fraction frac1, Fraction frac2){
        Fraction resultFrac = new Fraction();
        resultFrac.numerator = (frac1.denominator * frac2.numerator);
        resultFrac.denominator = frac2.denominator * frac1.numerator;

        check_no_div_by_zero(resultFrac);

        return resultFrac;
    }

    /*
     * Calculte Power of fraction to a Integer
     */
    public static Fraction power(Fraction frac, int power){
        Fraction resultFrac = new Fraction();
        resultFrac.numerator = (int) Math.pow((double) frac.numerator, (double) power);
        resultFrac.denominator = (int) Math.pow((double) frac.denominator, (double) power);

        check_no_div_by_zero(resultFrac);

        return resultFrac;
    }

    /*
     * Reduce Fraction
     */
    public void reduce(){
        int gcd = getGCD(getNumerator(), getDenominator());
        setNumerator(numerator/gcd);
        setDenominator(denominator/gcd);
    }

    /*
     * Function to calculate the greatest common divisor using Euclid's algorithm
     */
    public static int getGCD(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public static void check_no_div_by_zero(Fraction frac){
        // Check no div by zero
        if(frac.denominator == 0)
            throw new RuntimeException("Error: Division by 0");
    }

    public String toString(){
        if(denominator == 1 || denominator == -1)
            return Integer.toString(numerator);
        
        String result = "";
        if(numerator < 0 || denominator < 0)
            result += "-";
        return result + Integer.toString(numerator) + "/" + Integer.toString(denominator);
    }

    public boolean equals(Fraction frac){
        return (numerator == frac.numerator) && 
               (denominator == frac.denominator);
    }

}
