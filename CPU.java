/*
 * CPU CLASS
 * keeps track of hits and misses w each search
 */
class CPU {
    private int hits;
    private int misses;
    private double ratio;
    private Cache myCache;

    public CPU(Cache mc){
        hits = 0;
        misses = 0;
        ratio=0;
        myCache = mc;
    }
    public void clear(){
        myCache.clear(); //go to cache to clear
    }
    public void delBlock(int blockNum){
        myCache.deleteBlock(blockNum); //go to cache to del block
    }
    public void search(Numb instrNum){ //instr num is base16 rn
        long inty = instrNum.convertRToBase();
        int potentialBlock = (int)((inty / myCache.getBlockSize()) % myCache.getNumBlocks());
        int blockOfMM = (int)(inty / myCache.getBlockSize());
        int offset = (int)(inty % myCache.getBlockSize());

        Numb numInBase10 = new Numb(Long.toString(inty), 10, 16, 8);
        boolean found = this.myCache.checkForInstr(potentialBlock, offset, numInBase10); //look in cache for specific instr
        if (found){
            this.hits += 1; //nothing needs to be brought in or changed, just increase misses.
        }
        else{
            this.misses += 1; //incr misses
            Numb[] nummies = new Numb[(int)myCache.getBlockSize()]; //nummies is the list of x instructions located within a block
            int count = 0;
            for (long i = inty-offset; i<(inty-offset+myCache.getBlockSize()); i++){ //populate nummies with the instructions of the block youre bringing in
                Numb hexxyBaby = new Numb(Long.toString(i),10,16,8);
                nummies[count] = hexxyBaby; 
                count++;
            }
            myCache.bringBlock(potentialBlock, nummies, blockOfMM); //brings block into cache
        }
        ratio = (double)hits/(hits+misses); //update the ratio

    }

    public String toString(){
        return("H - " + ratio + "\n" + myCache);
    }
}