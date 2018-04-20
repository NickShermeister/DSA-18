public class FirstFailingVersion {

    public static long firstBadVersion(long n, IsFailingVersion isBadVersion) {
        // TODO
        long bot = 0;
        long top = n-1;
        long curr = 0;
        while(bot != top){
            curr = (bot + top)/2;
            if (isBadVersion.isFailingVersion(curr)){ //if it's a failing version
                top = curr;
            }
            else {
                bot = curr+1;
            }
            if (Math.abs(top-bot) <= 1){
                if(isBadVersion.isFailingVersion(bot)){
                    curr = bot;
                    break;
                }
                else {
                    curr = top;
                    break;
                }
            }
        }
        return curr;
    }
}
