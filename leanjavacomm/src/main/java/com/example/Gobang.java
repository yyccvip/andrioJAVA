
import java.io.*;
/**
 * Description:
 * <br/>��վ: <a href="http://www.crazyit.org">���Java����</a>
 * <br/>Copyright (C), 2001-2016, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author Yeeku.H.Lee kongyeeku@163.com
 * @version 1.0
 */
public class Gobang
{
	// �������̵Ĵ�С
	static final int BOARD_SIZE = 15;
	//�������������ɵ�����
	static final int FIVE=5;
	// ����һ����ά�������䵱����
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
		// ��ʼ����������
		board = new String[BOARD_SIZE][BOARD_SIZE];
		// ��ÿ��Ԫ�ظ�Ϊ"��"�������ڿ���̨��������
		for (int i = 0 ; i < BOARD_SIZE ; i++)
		{
			for ( int j = 0 ; j < BOARD_SIZE ; j++)
			{
				board[i][j] = "��";
			}
		}
	}
	// �ڿ���̨������̵ķ���
	public void printBoard()
	{
		// ��ӡÿ������Ԫ��
		for (int i = 0 ; i < BOARD_SIZE ; i++)
		{
			for ( int j = 0 ; j < BOARD_SIZE ; j++)
			{
				// ��ӡ����Ԫ�غ󲻻���
				System.out.print(board[i][j]);
			}
			// ÿ��ӡ��һ������Ԫ�غ����һ�����з�
			System.out.print("\n");
		}
	}
	boolean checkline(int inXpos,int inYpos,Direction dir){
		//������������ total �������������� togΪ��ͬ������ҵı�ʶ
		int total=1,tog=-1;
		//�ֱ�����������ָʾ������ȡ����������
		int tmpx=0,tmpy=0;
		//��һ���� ���ϲ���
		tmpx=inXpos;
		tmpy=inYpos;
		for(int j=0;j<FIVE-1;j++){
			//��ȡ��һ��������
			tmpx+=dir.x;
			tmpy+=dir.y;
			//����������̱߽� ��ֹ���������
			if(tmpx<0||tmpx>BOARD_SIZE -1||tmpy<0||tmpy>BOARD_SIZE -1){
				break;
			}
			//�����һ�����뱻��������ͬ������ͬ������1
			if(board[inXpos][inYpos]==board[tmpx][tmpy]){
				total+=1;
				//�ж���ͬ�����Ƿ񳬹�5�����������ʾӮ��
				if(total>=FIVE){
					return true;
				}
			}
			//�����һ�����뱻�����Ӳ���ͬ����ǰ���������ֹ
			else{
				break;
			}
		}
		//�ڶ��β��� ���²���
		tmpx=inXpos;
		tmpy=inYpos;
		//�ڶ��β��ҵĴ���ΪFIVE-total(�Ѿ��ҵ�������)
		for(int j=FIVE-total;j>0;j--){
			//��ȡ��һ��������,��������
			tmpx-=dir.x;
			tmpy-=dir.y;
			//����������̱߽� ��ֹ���������
			if(tmpx<0||tmpx>BOARD_SIZE -1||tmpy<0||tmpy>BOARD_SIZE -1){
				break;
			}
			//�����һ�����뱻��������ͬ������ͬ������1
			if(board[inXpos][inYpos]==board[tmpx][tmpy]){
				total+=1;
				//�ж���ͬ�����Ƿ񳬹�5�����������ʾӮ��
				if(total>=FIVE){
					return true;
				}
			}
			//�����һ�����뱻�����Ӳ���ͬ������ͬ��������Ϊ1����ǰ���������ֹ
			else{
				break;
			}
		}

//������������total������Five ����û�ҵ�
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
		System.out.println("�������������꣬�ԣ�x,y����ʽ");
		// �������ڻ�ȡ��������ķ���
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String inputStr = null;
		// br.readLine()��ÿ���ڼ���������һ�����ݰ��س����û�����������ݽ���br��ȡ����
		while ((inputStr = br.readLine()) != null)
		{
			if(inputStr.matches("[ |\\t]*[\\d]*,[\\d]*")!=true){
				System.out.println("��������ݳ��������̷�Χ");
				continue;
			}

			// ���û�������ַ����Զ��ţ�,����Ϊ�ָ������ָ���2���ַ���
			String[] posStrArr = inputStr.split("[,| ]");
			// ��2���ַ���ת�����û����������
			int xPos = Integer.parseInt(posStrArr[0]);
			int yPos = Integer.parseInt(posStrArr[1]);


			// �Ѷ�Ӧ������Ԫ�ظ�Ϊ"��"��
			gb.board[yPos - 1][xPos - 1] = "��";
			/*
			 �����������2����������Ϊ������������꣬����board���顣
			 ���漰
				1.�������Ч�ԣ�ֻ�������֣����ܳ������̷�Χ
				2.����µ���ĵ㣬�����ظ����塣
				3.ÿ���������Ҫɨ��˭Ӯ��
			 */
			gb.printBoard();
			if (gb.check(yPos - 1,xPos - 1)){
				gb.initBoard();
				gb.printBoard();
				System.out.println("��Ӯ�ˣ�����һ��");
				System.out.println("�������������꣬�ԣ�x,y����ʽ");
			}
			else
				System.out.println("ûӮ������Ŭ��");


		}
	}
}