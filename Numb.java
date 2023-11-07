
/*
 * NUMBER CLASS
 */

class Numb{
    private String number;
    private int currentBase;
    private int baseToConvertTo;
    private int MaxNumberOfBits;
    private char[] letters;

    public Numb(String number, int currentBase, int baseToConvertTo, int MaxNumberOfBits){ //constructor
        this.number = number;
        this.currentBase = currentBase;
        this.baseToConvertTo = baseToConvertTo;
        this.MaxNumberOfBits = MaxNumberOfBits;
        this.letters = new char[]{'A','B', 'C','D','E','F','G','H','I','J','K','L','M','N'}; //list of letters to be used when base utilizes chars
    }
    public int getCurrentBase(){ //getters and setters as needed
        return this.currentBase;
    }
    public void setCurrentBase(int b){
        this.currentBase = b;
    }
    public String getNumber(){
        return this.number;
    }
    public void setNumber(String n){
        this.number = n;
    }
    public int getMaxNumberOfBits(){
        return this .MaxNumberOfBits;
    }
    public String convertBasetoR(){
        //keep dividing and appending remainder to the beginning
        //do this til youre left with 0
        int temp = Integer.parseInt(this.number);
        int remainder;
        String rBase = "";
        while (!(temp==0)){
            remainder = temp%this.baseToConvertTo;
            if (remainder>9){ //if  remainder is larger than 9, it should be a letter char (from the letters list!)
                char curLet = this.letters[remainder-10]; //get the letter it should be using the indices of the list
                rBase = curLet + rBase;
            }
            else{
                rBase = remainder + rBase;
            }
            temp/= this.baseToConvertTo;
        }

        while (rBase.length() < this.MaxNumberOfBits){
            rBase = "0" + rBase;
        } //add 0 bits to the front if its not fully filled in based on "maxbits"
        return rBase;
    }
    public long convertRToBase(){ //converts from a random base to base 10
        long dig = 0;
        long base10 = 0; //initializing dig and base10 (final answer to be returned)
        String strNum = this.number;
        for (int bit = strNum.length()-1; bit>=0; bit--){ //for however many digs there are
            for (int chrInd =0;chrInd<this.letters.length;chrInd++){ //check if theres a char in the base
                if (this.letters[chrInd] == strNum.charAt(bit)){
                    dig = chrInd + 10; //if there is, quickly convert it to its numerical equivalent
                    break;
                }
                else{
                    dig = (strNum.charAt(bit)-48); //get cur dig
                }
            }
            double num = dig *(Math.pow(this.currentBase, (double)(strNum.length() - bit- 1))); //convert the digit to the base 10 equiv 
            
            base10+=num;
        }
        
        return base10;
    }
}