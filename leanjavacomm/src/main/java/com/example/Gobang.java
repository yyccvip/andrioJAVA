
import java.io.*;
/**
 * Description:
 * <br/>网站: <a href="http://www.crazyit.org">疯狂Java联盟</a>
 * <br/>Copyright (C), 2001-2016, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author Yeeku.H.Lee kongyeeku@163.com
 * @version 1.0
 */
public class Gobang
{
	// 定义棋盘的大小
	static final int BOARD_SIZE = 15;
	//定义五子棋连成的数量
	static final int FIVE=5;
	// 定义一个二维数组来充当棋盘
	String[][] board;
	class Direction{
		int x;
		int y;
		Direction(int ix,int iy){
			this.x=ix;
			this.y=iy;
		}
	}
	private Direction[] Towards=new Direction[4];
	{
		Towards[0]=new Direction(-1,-1);
		Towards[1]=new Direction(-1,0);
		Towards[2]=new Direction(-1,1);
		Towards[3]=new Direction(0,1);

	}
	public void initBoard()
	{
		// 初始化棋盘数组
		board = new String[BOARD_SIZE][BOARD_SIZE];
		// 把每个元素赋为"╋"，用于在控制台画出棋盘
		for (int i = 0 ; i < BOARD_SIZE ; i++)
		{
			for ( int j = 0 ; j < BOARD_SIZE ; j++)
			{
				board[i][j] = "╋";
			}
		}
	}
	// 在控制台输出棋盘的方法
	public void printBoard()
	{
		// 打印每个数组元素
		for (int i = 0 ; i < BOARD_SIZE ; i++)
		{
			for ( int j = 0 ; j < BOARD_SIZE ; j++)
			{
				// 打印数组元素后不换行
				System.out.print(board[i][j]);
			}
			// 每打印完一行数组元素后输出一个换行符
			System.out.print("\n");
		}
	}
	boolean checkline(int inXpos,int inYpos,Direction dir){
		//定义两个变量 total 相连的棋子数量 tog为向不同方向查找的标识
		int total=1,tog=-1;
		//分别定义两个变量指示即将获取的棋子坐标
		int tmpx=0,tmpy=0;
		//第一查找 向上查找
		tmpx=inXpos;
		tmpy=inYpos;
		for(int j=0;j<FIVE-1;j++){
			//获取下一棋子坐标
			tmpx+=dir.x;
			tmpy+=dir.y;
			//如果到达棋盘边界 中止本方向查找
			if(tmpx<0||tmpx>BOARD_SIZE -1||tmpy<0||tmpy>BOARD_SIZE -1){
				break;
			}
			//如果下一棋子与被测棋子相同，总相同数量加1
			if(board[inXpos][inYpos]==board[tmpx][tmpy]){
				total+=1;
				//判断相同数量是否超过5个，超过则表示赢了
				if(total>=FIVE){
					return true;
				}
			}
			//如果下一棋子与被测棋子不相同，当前方向查找中止
			else{
				break;
			}
		}
		//第二次查找 向下查找
		tmpx=inXpos;
		tmpy=inYpos;
		//第二次查找的次数为FIVE-total(已经找到的数量)
		for(int j=FIVE-total;j>0;j--){
			//获取下一棋子坐标,方向向下
			tmpx-=dir.x;
			tmpy-=dir.y;
			//如果到达棋盘边界 中止本方向查找
			if(tmpx<0||tmpx>BOARD_SIZE -1||tmpy<0||tmpy>BOARD_SIZE -1){
				break;
			}
			//如果下一棋子与被测棋子相同，总相同数量加1
			if(board[inXpos][inYpos]==board[tmpx][tmpy]){
				total+=1;
				//判断相同数量是否超过5个，超过则表示赢了
				if(total>=FIVE){
					return true;
				}
			}
			//如果下一棋子与被测棋子不相同，总相同数量重置为1，当前方向查找中止
			else{
				break;
			}
		}

//如果两个方向的total都不足Five 返回没找到
		return false;
	}
	boolean check(int ix,int iy){

		for (Direction TestDir:Towards) {
			if (this.checkline(ix,iy,TestDir)){
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) throws Exception
	{
		Gobang gb = new Gobang();
		gb.initBoard();
		gb.printBoard();
		System.out.println("请输入棋子坐标，以（x,y）格式");
		// 这是用于获取键盘输入的方法
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String inputStr = null;

		// br.readLine()：每当在键盘上输入一行内容按回车，用户刚输入的内容将被br读取到。
		while ((inputStr = br.readLine()) != null)
		{
			if(inputStr.matches("[\\s]*[\\d]+[\\s]*,[\\s]*[\\d]+[\\s]*")!=true){
				System.out.println("请输入以“，”分割的数字");
				continue;
			}

			// 将用户输入的字符串以逗号（,）作为分隔符，分隔成2个字符串
			String[] posStrArr = inputStr.split(",");
			// 将2个字符串转换成用户下棋的座标
			int xPos = Integer.parseInt(posStrArr[0].trim());
			int yPos = Integer.parseInt(posStrArr[1].trim());


			// 把对应的数组元素赋为"●"。
			gb.board[yPos - 1][xPos - 1] = "●";
			/*
			 电脑随机生成2个整数，作为电脑下棋的座标，赋给board数组。
			 还涉及
				1.座标的有效性，只能是数字，不能超出棋盘范围
				2.如果下的棋的点，不能重复下棋。
				3.每次下棋后，需要扫描谁赢了
			 */
			gb.printBoard();
			if (gb.check(yPos - 1,xPos - 1)){
				gb.initBoard();
				gb.printBoard();
				System.out.println("你赢了，再来一局");
				System.out.println("请输入棋子坐标，以（x,y）格式");
			}
			else
				System.out.println("没赢，继续努力");


		}
	}
}
