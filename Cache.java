/*
 * fake cache
 * checks for instructions, brings blocks, clears, deletes blocks
 */
class Cache {

    private Numb[][] cacheSpots; //2d array of the cache 
    private String[] forOutp;
    private long numBlocks;
    private long blockSize;

    public Cache(int instructionSize, int blocks){
        numBlocks = blocks;
        blockSize = instructionSize;
        forOutp = new String[blocks];
        cacheSpots = new Numb[blocks][instructionSize];
        }

    public long getBlockSize(){
        return(this.blockSize);
    }

    public long getNumBlocks(){
        return this.numBlocks;
    }

    public Numb getCacheSpot(int row,int col){ //i think i just used this for debugging
        return cacheSpots[3][3];
    }

    public void bringBlock(int block, Numb[] instructions, int blockOfMM){ //brings a block into mm 
        for (int i= 0;i<instructions.length;i++){ //populates the correct row of the 2d array of the cache w/ instructions 
            this.cacheSpots[block][i] = instructions[i];
        }
        this.forOutp[block] = Integer.toString(blockOfMM); //updates which block is in the mm for the output
    }

    public boolean checkForInstr(int potentialBlock,int offset,Numb specInstr){
        boolean there = false; //to track if instr is in cache or not
        if (this.cacheSpots[potentialBlock][offset] != null){ //if something is in this spot
            if (this.cacheSpots[potentialBlock][offset].getNumber().equals(specInstr.getNumber())){ //if that "something" is the right instr
            there = true;
            }
        }
        return there;
    }
    public void clear(){
        for (int b= 0;b<numBlocks;b++){ 
            for (int i= 0;i<blockSize;i++){
                cacheSpots[b][i] = null; //make entire 2d array NULLLLLL
            }
            forOutp[b] = "---"; //changing how it appears in the output
        }
    }
    public void deleteBlock(int blockNum){
        for (int i= 0;i<blockSize;i++){ //deletes the block/row in the cache's 2d array which was meant to be deleted
            this.cacheSpots[blockNum][i] = null;
        }
        this.forOutp[blockNum] = "---"; //change how the output appears
    }
    
    public String toString(){ //for printing
        String toRet = "";
        for (String spot:forOutp){
            if (!(spot == null)){
                toRet += (spot + " ");
            }
            else{
                toRet += "____    ";
            }
            
        }
        return toRet;
    }
}

