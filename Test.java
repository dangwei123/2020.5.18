木棒拼图
public class Main{
        public static void main(String[] args){
            Scanner sc=new Scanner(System.in);
            while(sc.hasNext()){
                int n=sc.nextInt();
                List<Integer> list=new ArrayList<>();
                for(int j=0;j<n;j++){
                    int flag=sc.nextInt();
                    if(flag==1){
                        list.add(sc.nextInt());
                    }else{
                        int tmp=sc.nextInt();
                        if(list.contains(tmp))
                        list.remove(Integer.valueOf(tmp));
                    }
                    Collections.sort(list);
                    int sum=0;
                    int max=list.get(list.size()-1);
                    for(int i=0;i<list.size()-1;i++){
                        sum+=list.get(i);
                    }
                    if(sum>max){
                        System.out.println("Yes");
                    }else{
                        System.out.println("No");
                    }
                }

            }
        }
}


青蛙逃生
public class Main{
        public static String path = "";
        public static void main(String[] args) {
            Scanner in = new Scanner(System.in);
            while(in.hasNext()){
                int n = in.nextInt();
                int m = in.nextInt();
                int p = in.nextInt();
                int[][] grid = new int[n][m];
                for(int i=0; i<n; i++){
                    for(int j=0; j<m; j++){
                        grid[i][j] = in.nextInt();
                    }
                }
                Helper(grid,0,0,new boolean[n][m],"",p);
                System.out.println(path);
            }
        }
        public static void Helper(int[][] grid,int i,int j, boolean[][] visited,String res,int p) {
            if(i<0||j<0||i>=grid.length||j>=grid[0].length||grid[i][j]==0||visited[i][j]){
                return;
            }
            if(i == 0 && j == grid[0].length-1){
                if(p >=0 ){
                    path = res + "["+i+","+j+"]";
                }else{
                    path = "Can not escape!";
                }
                return;
            }
                visited[i][j] = true;
                int len=res.length();
                res += "["+i+","+j+"],";
                Helper(grid,i,j+1,visited,res,p-1);
                Helper(grid,i+1,j,visited,res,p);
                Helper(grid,i,j-1,visited,res,p-1);
                Helper(grid,i-1,j,visited,res,p-3);
                
        }

}


众所周知，牛妹非常喜欢吃蛋糕。
第一天牛妹吃掉蛋糕总数三分之一（向下取整）多一个，第二天又将剩下的蛋糕吃掉三分之一（向下取整）多一个，以后每天吃掉前一天剩下的三分之一（向下取整）多一个，到第n天准备吃的时候只剩下一个蛋糕。
牛妹想知道第一天开始吃的时候蛋糕一共有多少呢？
public class Solution {
    /**
     * 
     * @param n int整型 只剩下一只蛋糕的时候是在第n天发生的．
     * @return int整型
     */
    public int cakeNumber (int n) {
        // write code here
        int sum=1;
        for(int i=0;i<n-1;i++){
            sum=(sum+1)*3/2;
        }
        return sum;
    }
}

众所周知，牛妹有很多很多粉丝，粉丝送了很多很多礼物给牛妹，牛妹的礼物摆满了地板。
地板是N\times MN×M的格子，每个格子有且只有一个礼物，牛妹已知每个礼物的体积。
地板的坐标是左上角(1,1)  右下角（N, M）。
牛妹只想要从屋子左上角走到右下角，每次走一步，每步只能向下走一步或者向右走一步或者向右下走一步
每次走过一个格子，拿起（并且必须拿上）这个格子上的礼物。
牛妹想知道，她能走到最后拿起的所有礼物体积最小和是多少？

public class Solution {
    /**
     * 
     * @param presentVolumn int整型二维数组 N*M的矩阵，每个元素是这个地板砖上的礼物体积
     * @return int整型
     */
    public int selectPresent (int[][] presentVolumn) {
        int n=presentVolumn.length;
        if(n==0) return 0;
        int m=presentVolumn[0].length;
        int[][] dp=new int[n][m];
        dp[0][0]=presentVolumn[0][0];
        for(int i=1;i<m;i++){
            dp[0][i]=dp[0][i-1]+presentVolumn[0][i];
        }
        for(int i=1;i<n;i++){
            dp[i][0]=dp[i-1][0]+presentVolumn[i][0];
        }
        
        for(int i=1;i<n;i++){
            for(int j=1;j<m;j++){
                dp[i][j]=Math.min(dp[i-1][j-1],Math.min(dp[i-1][j],dp[i][j-1]))+presentVolumn[i][j];
                
            }
        }
        return dp[n-1][m-1];
    }
}


题意：
牛妹在和牛牛下牛客象棋。现在轮到牛妹了，牛妹想知道她在这一回合能否战胜牛牛。

棋盘chessboard上只可能包含：炮，将，车，兵

牛客象棋的规则解释：
炮：炮在不吃子的时候，走动与车完全相同，但炮在吃棋子时，必须跳过一个棋子，我方的和敌方的都可以
兵：可以上下左右移动，每次只能移动一格
车：上下左右均可走，只要无棋子阻拦，步数不受限制。
将：可以上下左右移动，每次只能移动一格
接下来给出一个棋盘，牛妹的棋子用大写字母表示，牛牛的棋子用小写字母表示。
将用J,jJ,j表示，炮用P,pP,p表示，车用C,cC,c表示，兵用B,bB,b表示，没有棋子的格子用..表示
保证棋盘上一定同时包含JJ与jj各一个。

public class Solution {
    /**
     * 
     * @param chessboard string字符串一维数组 
     * @return string字符串
     */
    public String playchess (String[] chessboard) {
        // write code here
        int len=chessboard.length;
        char[][] board=new char[len][];
        for(int i=0;i<len;i++){
            board[i]=new char[chessboard[i].length()];
            for(int j=0;j<chessboard[i].length();j++){
                board[i][j]=chessboard[i].charAt(j);
            }
        }
       int x=0;
        int y=0;
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[i].length;j++){
                if(board[i][j]=='j'){
                    x=i;
                    y=j;
                    break;
                }
            }
        }
        
        if(isCanWin(board,x,y)){
            return "Happy";
        }
        return "Sad";
    }
    
    private boolean isCanWin(char[][]board,int x,int y){
        //左
        int num=0;
        for(int i=y-1;i>=0;i--){
            if(board[x][i]=='B'||board[x][i]=='J'){
                if(i==y-1)return true;
                num++;
            }else if(board[x][i]=='C'){
                if(num==0) return true;
                num++;
            }else if(board[x][i]=='P'){
                if(num==1) return true;
                num++;
            }else if(board[x][i]!='.'){
                num++;
            }
        }
        //右
        num=0;
        for(int i=y+1;i<board[x].length;i++){
            if(board[x][i]=='B'||board[x][i]=='J'){
                if(i==y+1)return true;
                num++;
            }else if(board[x][i]=='C'){
                if(num==0) return true;
                num++;
            }else if(board[x][i]=='P'){
                if(num==1) return true;
                num++;
            }else if(board[x][i]!='.'){
                num++;
            }
        }
        //上
        num=0;
        for(int i=x-1;i>=0;i--){
            if(board[i][y]=='B'||board[i][y]=='J'){
                if(i==x-1)return true;
                num++;
            }else if(board[i][y]=='C'){
                if(num==0) return true;
                num++;
            }else if(board[i][y]=='P'){
                if(num==1) return true;
                num++;
            }else if(board[i][y]!='.'){
                num++;
            }
        }
        //下
        num=0;
        for(int i=x+1;i<board.length;i++){
            if(board[i][y]=='B'||board[i][y]=='J'){
                if(i==x+1)return true;
                num++;
            }else if(board[i][y]=='C'){
                if(num==0) return true;
                num++;
            }else if(board[i][y]=='P'){
                if(num==1) return true;
                num++;
            }else if(board[i][y]!='.'){
                num++;
            }
        }
        return false;
    }
}