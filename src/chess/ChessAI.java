package chess;

import java.util.ArrayList;
import java.util.List;


public class ChessAI {

	private int[][] data;
	private final int ROWS =10;
	private final int COLS=9;
	private int MAXDEPTH=2;
	private int Goi=-1;
	@SuppressWarnings("unused")
	private int scoreAfter;
	private int scoreBegin;
	private int[][][] scoreMap=new int[8][10][9];// diem cua quan ? tai vi tri ?x?
	private int scoreChess[]= {0,90,40,20,20,1000,45,10};//gia tri quan co
	
	
	public ChessAI(int[][] data){
		this.data=data;
		InitScoreMap();
		scoreBegin=ComputeChessScore(this.data);
		
	}
	// score ban co hien tai
	public int ComputeChessScore(int[][] data) {
		int nowScore=0;
		for(int i=0;i<ROWS ;i++){
			for(int j=0;j<COLS;j++){
				if(data[i][j]>=8){
					
					nowScore+=(scoreMap[data[i][j]-7][ROWS -i-1][j]+scoreChess[data[i][j]-7]);
				}else if(data[i][j]==0){
					continue;
				}else{
					nowScore-=(scoreMap[data[i][j]][i][j]+scoreChess[data[i][j]]);
				}
			}
		}
		return nowScore;
	}
	//value chess
	public void InitScoreMap() {
		 int[][] rook={
				 		{90, 90, 90, 90, 90, 90, 90, 90, 90}, 		/* ROOK */
						{90, 91, 91, 92, 90, 92, 91, 91, 90},
						{90, 91, 90, 90, 90, 90, 90, 91, 90},
						{90, 91, 90, 91, 90, 91, 90, 91, 90},
						{90, 93, 90, 91, 90, 91, 90, 93, 90},
						{90, 94, 90, 94, 90, 94, 90, 94, 90},
						{90, 91, 90, 91, 90, 91, 90, 91, 90},
						{90, 92, 90, 91, 90, 91, 90, 92, 90},
						{91, 92, 90, 93, 90, 93, 90, 92, 91},
						{89, 92, 90, 90, 90, 90, 90, 92, 89}
		 };
		 
		 int[][] knight={
				 		{40, 40, 40, 40, 40, 40, 40, 40, 40}, 		/* KNIGHT */
						{40, 41, 42, 42, 42, 42, 42, 41, 40},
						{40, 43, 44, 44, 44, 44, 44, 43, 40},
						{40, 43, 44, 44, 44, 44, 44, 43, 40},
						{40, 43, 44, 44, 44, 44, 44, 43, 40},
						{40, 43, 44, 44, 44, 44, 44, 43, 40},
						{40, 42, 43, 43, 43, 43, 43, 42, 40},
						{40, 42, 43, 40, 40, 40, 43, 42, 40},
						{40, 41, 42, 40, 20, 40, 42, 41, 40},
						{40, 35, 40, 40, 40, 40, 40, 35, 40}
		 };
		 
		 int[][] elephant={
				 		{0,  0,  25,  0,  0,  0,  25,  0,  0},          /* ELEPHAN */
						{0,  0,  0,  0,  0,  0,  0,  0,  0},
						{0,  0,  0,  0,  28,  0,  0,  0,  0},
						{0,  0,  0,  0,  0,  0,  0,  0,  0},
						{0,  0,  22,  0,  0,  0, 22,  0,  0},
						{0,  0, 22,  0,  0,  0, 22,  0,  0},
						{0,  0,  0,  0,  0,  0,  0,  0,  0},
					    {23,  0,  0,  0, 28,  0,  0,  0, 23},
						{0,  0,  0,  0,  0,  0,  0,  0,  0},
						{0,  0, 25,  0,  0,  0, 25,  0,  0}
		 };
		 
		 int[][] guard={
				 		{0,  0,  0, 19,  0, 19,  0,  0,  0},
						{0,  0,  0,  0, 22,  0,  0,  0,  0},
						{0,  0,  0, 20,  0, 20,  0,  0,  0},
						{0,  0,  0,  0,  0,  0,  0,  0,  0},
						{0,  0,  0,  0,  0,  0,  0,  0,  0},
						{0,  0,  0,  0,  0,  0,  0,  0,  0},
						{0,  0,  0,  0,  0,  0,  0,  0,  0},
						{0,  0,  0, 19,  0, 19,  0,  0,  0},
						{0,  0,  0,  0, 22,  0,  0,  0,  0},
						{0,  0,  0, 20,  0, 20,  0,  0,  0}
		 };
		 
		 int[][] king={
				    {0, 0, 0, 25, 35, 25, 0, 0, 0},
	        		{0, 0, 0, 15, 15, 15, 0, 0, 0}, 
	        		{0, 0, 0, 1, 1, 1, 0, 0, 0},
	        		{0, 0, 0, 0, 0, 0, 0, 0, 0},
	        		{0, 0, 0, 0, 0, 0, 0, 0, 0},
	 
	        		{0, 0, 0, 0, 0, 0, 0, 0, 0},
	        		{0, 0, 0, 0, 0, 0, 0, 0, 0},
	        		{0, 0, 0, 1, 1, 1, 0, 0, 0},
	        		{0, 0, 0, 15, 15, 15, 0, 0, 0}, 
	        		{0, 0, 0, 25, 35, 25, 0, 0, 0}
		 };
		 
		 int[][] connon={
				 		{50, 50, 50, 50, 50, 50, 50, 50, 50}, 		/* CANNON */
						{50, 51, 50, 50, 50, 50, 50, 51, 50},
						{50, 51, 50, 50, 50, 50, 50, 51, 50},
						{50, 51, 50, 50, 50, 50, 50, 51, 50},
						{50, 51, 50, 50, 50, 50, 50, 51, 50},
						{50, 51, 51, 51, 51, 51, 51, 51, 50},
						{50, 51, 50, 50, 50, 50, 50, 51, 50},
						{50, 51, 53, 53, 55, 53, 53, 51, 50},
						{50, 50, 50, 50, 50, 50, 50, 50, 50},
						{50, 50, 50, 50, 50, 50, 50, 50, 50}	
		 };
		 
		 int[][] pawn={
				 		{11, 12, 13, 14, 14, 14, 13, 12, 11},          /* PAWN*/
						{20, 21, 21, 23, 22, 23, 21, 21, 20},
						{20, 21, 21, 23, 23, 23, 21, 21, 20},
						{20, 21, 21, 22, 22, 22, 21, 21, 20},
						{20, 20, 20, 20, 20, 20, 20, 20, 20},
						{10,  0, 13,  0, 10,  0, 13,  0, 10},
						{10,  0, 12,  0, 15,  0, 12,  0, 10},
						{0,  0,  0,  0,  0,  0,  0,  0,  0},
						{0,  0,  0,  0,  0,  0,  0,  0,  0},
						{0,  0,  0,  0,  0,  0,  0,  0,  0}
		 };
		 
		 int[][] kong={{},{},{},{},{},{},{},{},{},{}};
		 this.scoreMap[0]=kong;
		 this.scoreMap[1]=rook;
		 this.scoreMap[2]=knight;
		 this.scoreMap[3]=elephant;
		 this.scoreMap[4]=guard;
		 this.scoreMap[5]=king;
		 this.scoreMap[6]=connon;
		 this.scoreMap[7]=pawn;
	}
	
	public int[][] Compute() {
		List<List<Integer>> FirstMove=GenerateAllMove(data, true);
		int[][] subData=CloneData(data);
		this.scoreAfter=AlphaBeta(Integer.MIN_VALUE, Integer.MAX_VALUE, 0, data);
		if(Goi==-1){
			for(int i=0;i<FirstMove.get(0).size();i+=2){
				int[][] temp=CloneData(subData);
				temp[FirstMove.get(0).get(i+1)][FirstMove.get(1).get(i+1)]=
						temp[FirstMove.get(0).get(i)][FirstMove.get(1).get(i)];
				temp[FirstMove.get(0).get(i)][FirstMove.get(1).get(i)]=0;
				if(!CanEatAiGerNow(temp)){
					Goi=i;
					return temp;
				}
			}
			if(Goi==-1){
				return null;
			}
		}
		subData[FirstMove.get(0).get(Goi+1)][FirstMove.get(1).get(Goi+1)]=
				subData[FirstMove.get(0).get(Goi)][FirstMove.get(1).get(Goi)];
		subData[FirstMove.get(0).get(Goi)][FirstMove.get(1).get(Goi)]=0;
		return subData;
	}
//	search
	public boolean CanEatAiGerNow(int[][] subuc) {
		
		int jx = -1;
		int jy = -1;
		for(int i=0;i<ROWS ;i++){
			for(int j=0;j<COLS;j++){
				if(subuc[i][j]==12){
					jx=i;
					jy=j;
				}
			}
		}
		if(jx==-1)
			return true;
		
		for(int h=jy-1;h>=0;h--){
			if(subuc[jx][h]==0)
				continue;
			else if(subuc[jx][h]==1){
				return true;
			}else {
				break;
			}
		}
		for(int h=jy+1;h<COLS;h++){
			if(subuc[jx][h]==0)
				continue;
			else if(subuc[jx][h]==1)
				return true;
			else {
				break;
			}
		}
		
		if(jx-1>=0){
			for(int h=jx-1;h>=0;h--){
				if(subuc[h][jy]==0)
					continue;
				else if(subuc[h][jy]==1)
					return true;
				else {
					break;
				}
			}
		}
		for(int h=jx+1;h<ROWS ;h++){
			if(subuc[h][jy]==0)
				continue;
			else if(subuc[h][jy]==1)
				return true;
			else {
				break;
			}
		}
		
		
		for(int h=jy-1;h>=0;h--){
			if(subuc[jx][h]==0)
				continue;
			else{
				
				if(h-1>=0){
					for(int k=h-1;k>=0;k--){
						if(subuc[jx][k]==0)
							continue;
						else if(subuc[jx][k]==6)
							return true;
						else {
							break;
						}
					}
				}
				break;
			}
		}
		for(int h=jy+1;h<COLS;h++){
			if(subuc[jx][h]==0)
				continue;
			else{
				if(h+1<COLS){
					for(int k=h+1;k<COLS;k++){
						if(subuc[jx][k]==0)
							continue;
						else if(subuc[jx][k]==6)
							return true;
						else {
							break;
						}
					}
				}
				break;
			}
		}
		if(jx-1>=0){
			for(int h=jx-1;h>=0;h--){
				if(subuc[h][jy]==0)
					continue;
				else {
					if(h-1>=0){
						for(int k=h-1;k>=0;k--){
							if(subuc[k][jy]==0)
								continue;
							else if(subuc[k][jy]==6)
								return true;
							else {
								break;
							}
						}
					}
					break;
				}
			}
		}
			for(int h=jx+1;h<ROWS ;h++){
				if(subuc[h][jy]==0)
					continue;
				else{
					if(h+1<ROWS ){
						for(int k=h+1;k<ROWS ;k++){
							if(subuc[k][jy]==0)
								continue;
							else if(subuc[k][jy]==6)
								return true;
							else {
								break;
							}
						}
					}
					break;
				}
			}
			
			
			if(jx-1>=0 && subuc[jx-1][jy+1]==0){
				if((jx-2>=0 && subuc[jx-2][jy+1]==2) || subuc[jx-1][jy+2]==2){
					return true;
				}
			}
		
			if(jx-1>=0 && subuc[jx-1][jy-1]==0){
				if((jx-2>=0 && subuc[jx-2][jy-1]==2) || subuc[jx-1][jy-2]==2){
					return true;
				}
			}
			
			if(subuc[jx+1][jy+1]==0){
				if(subuc[jx+2][jy+1]==2 || subuc[jx+1][jy+2]==2){
					return true;
				}
			}
			
			if(subuc[jx+1][jy-1]==0){
				if(subuc[jx+2][jy-1]==2 || subuc[jx+1][jy-2]==2){
					return true;
				}
			}
			
			
			for(int h=jx+1;h<ROWS ;h++){
				if(subuc[h][jy]==0)
					continue;
				else if(subuc[h][jy]==5)
					return true;
				else {
					break;
				}
			}
			
			
			if(subuc[jx+1][jy]==7 || subuc[jx][jy-1]==7 || subuc[jx][jy+1]==7)
				return true;
			
			return false;
		}
	
	// khong th gop vi co mot so thuat toan ( quan ma an bi bat doi xung )
	public boolean CanEatUserGer(int[][] subuc) {
		
		int XE=1;
		int PHAO=6;
		int MA=2;
		int TUONG=5;
		int TOT=7;
	
		
		int jx = -1;
		int jy = -1;
		for(int i=0;i<ROWS ;i++){
			for(int j=0;j<COLS;j++){
				if(subuc[i][j]==TUONG){
					jx=i;
					jy=j;
				}
			}
		}
		if(jx==-1)
			return true;
//##################################################
// check xe co the an tuong luon 
		for(int h=jy-1;h>=0;h--){
			if(subuc[jx][h]==0)
				continue;
			else if(subuc[jx][h]==XE){
				return true;
			}else {
				break;
			}
		}
		for(int h=jy+1;h<COLS;h++){
			if(subuc[jx][h]==0)
				continue;
			else if(subuc[jx][h]==XE)
				return true;
			else {
				break;
			}
		}
		
		for(int h=jx-1;h>=0;h--){
			if(subuc[h][jy]==0)
				continue;
			else if(subuc[h][jy]==XE)
				return true;
			else {
				break;
			}
		}
		
		if(jx+1<ROWS ){
			for(int h=jx+1;h<ROWS ;h++){
				if(subuc[h][jy]==0)
					continue;
				else if(subuc[h][jy]==XE)
					return true;
				else {
					break;
				}
			}
		}
//##################################################
// check phao  co the an tuong luon 
		
		
		for(int h=jy-1;h>=0;h--){
			if(subuc[jx][h]==0)
				continue;
			else{
				
				if(h-1>=0){
					for(int k=h-1;k>=0;k--){
						if(subuc[jx][k]==0)
							continue;
						else if(subuc[jx][k]==PHAO)
							return true;
						else {
							break;
						}
					}
				}
				break;
			}
		}
		for(int h=jy+1;h<COLS;h++){
			if(subuc[jx][h]==0)
				continue;
			else{
				if(h+1<COLS){
					for(int k=h+1;k<COLS;k++){
						if(subuc[jx][k]==0)
							continue;
						else if(subuc[jx][k]==PHAO)
							return true;
						else {
							break;
						}
					}
				}
				break;
			}
		}

			for(int h=jx-1;h>=0;h--){
				if(subuc[h][jy]==0)
					continue;
				else {
					if(h-1>=0){
						for(int k=h-1;k>=0;k--){
							if(subuc[k][jy]==0)
								continue;
							else if(subuc[k][jy]==PHAO)
								return true;
							else {
								break;
							}
						}
					}
					break;
				}
			}
		
			if(jx+1<ROWS ){
			for(int h=jx+1;h<ROWS ;h++){
				if(subuc[h][jy]==0)
					continue;
				else{
					if(h+1<ROWS ){
						for(int k=h+1;k<ROWS ;k++){
							if(subuc[k][jy]==0)
								continue;
							else if(subuc[k][jy]==PHAO)
								return true;
							else {
								break;
							}
						}
					}
					break;
				}
			}
			}
//##################################################
// check ma co the an tuong luon 
			
			
			
			if(subuc[jx-1][jy+1]==0){
				if(subuc[jx-2][jy+1]==MA || subuc[jx-1][jy+2]==MA){
					return true;
				}
			}
		
			if(subuc[jx-1][jy-1]==0){
				if(subuc[jx-2][jy-1]==MA || subuc[jx-1][jy-2]==MA){
					return true;
				}
			}
		
		
			if(jx+1<ROWS  && subuc[jx+1][jy+1]==0){
				if((jx+2<ROWS  && subuc[jx+2][jy+1]==MA) || subuc[jx+1][jy+2]==MA){
					return true;
				}
			}
			
			if(jx+1<ROWS  && subuc[jx+1][jy-1]==0){
				if((jx+2<ROWS  && subuc[jx+2][jy-1]==MA) || subuc[jx+1][jy-2]==MA){
					return true;
				}
			}
		
//##################################################
// check tuong co the an tuong luon 
		
			
			for(int h=jx-1;h>=0;h--){
				if(subuc[h][jy]==0)
					continue;
				else if(subuc[h][jy]==TUONG)
					return true;
				else {
					break;
				}
			}
//##################################################
// check tot co the an tuong luon                          
			
			
			if(subuc[jx-1][jy]==TOT || subuc[jx][jy-1]==TOT || subuc[jx][jy+1]==TOT)
				return true;
			
			return false;
	}
	
	//AlphaBeta
	public int AlphaBeta(int alpha,int beta,int depth,int[][] data) {
		if(depth==MAXDEPTH)
			return ComputeChessScore(data)-scoreBegin;  // tra ve gia tri ban co 
		List<List<Integer>> tmp=GenerateAllMove(data, depth%2==0?true:false);
		int best=depth%2==0?-Integer.MIN_VALUE:Integer.MAX_VALUE;
		for(int i=0;i<tmp.get(0).size();i+=2){
			int ox=tmp.get(0).get(i);
			int oy=tmp.get(1).get(i);
			int nx=tmp.get(0).get(i+1);
			int ny=tmp.get(1).get(i+1);
			int[][] sub=CloneData(data);
			sub[ox][oy]=0;
			sub[nx][ny]=data[ox][oy];
			if(depth%2==0){
				if(CanEatAiGerNow(sub))
					continue;
			}else{
				if(CanEatUserGer(sub))
					continue;
			}
			int value=AlphaBeta(alpha, beta, depth+1, sub);
			if(depth%2==0){//max
				if(value>best){
					best=value; alpha=value;
					if(depth==0)
						Goi=i;
				}
			}else{//min
				if(value<best){
					best=value;
					beta=value;
				}
			}
			if(alpha>=beta)
				return best;
		}
		return best;
	}
	
	public int[][] CloneData(int[][] data) {
		int[][] sub=new int[data.length][data[0].length];
		for(int i=0;i<data.length;i++){
			for(int j=0;j<data[0].length;j++){
				sub[i][j]=data[i][j];
			}
		}
		return sub;
	}

	//all can move
	public List<List<Integer>> GenerateAllMove(int[][] data,boolean isAI) {
		System.out.println("\n ######call GernarateAllMove\n\n");
		List<List<Integer>> total=new ArrayList<>();
		total.add(new ArrayList<Integer>());
		total.add(new ArrayList<Integer>());
		// check tat ca ca vi tri 
		// neu co quan thi di chuyen no theo moi huong 
		for(int i=0;i<ROWS ;i++){
			for(int j=0;j<COLS;j++){
				if(isAI){
					if(data[i][j]>=8){
						List<List<Integer>> part=GenerateMoveByLabel(data, i, j, data[i][j]);
					
						total.get(0).addAll(part.get(0)); 
						// part.get0 => tat ca list x
						total.get(1).addAll(part.get(1));
						// tat ca listy 
					}
				}else{
					if(data[i][j]>0 && data[i][j]<=7){
						List<List<Integer>> part=GenerateMoveByLabel(data, i, j, data[i][j]);
						total.get(0).addAll(part.get(0));
						total.get(1).addAll(part.get(1));
					}
				}
			}
		}
		System.out.println("total"+total.toString());
		return total;
	}
	
private List<List<Integer>> GenerateMoveByLabel(int[][] data,int i,int j,int label) {
	
	java.util.List<Integer> listx=new ArrayList<>();
	java.util.List<Integer> listy=new ArrayList<>();
	switch (label) {
	case 8:
	case 1:
		
		if(j-1>=0){
		for(int h=j-1;h>=0;h--){
				if(data[i][h]==0){
					listx.add(i);
					listy.add(j);
					listx.add(i);
					listy.add(h);
				}else if((label<=7 && data[i][h]>=8) || (label>=8 && data[i][h]<=7)){
					listx.add(i);
					listy.add(j);
					listx.add(i);
					listy.add(h);
					break;
				}else{
					
					break;
				}
			}
		}
		
	
		if(j+1<COLS){
			for(int h=j+1;h<COLS;h++){
				if(data[i][h]==0){
					listx.add(i);
					listy.add(j);
					listx.add(i);
					listy.add(h);
				}else if((label<=7 && data[i][h]>=8) || (label>=8 && data[i][h]<=7)){
					listx.add(i);
					listy.add(j);
					listx.add(i);
					listy.add(h);
					break;
				}else {
					break;
				}
			}
		}

	
		if(i-1>=0){
			for(int k=i-1;k>=0;k--){
				if(data[k][j]==0){
					listx.add(i);
					listy.add(j);
					listx.add(k);
					listy.add(j);
				}else if((label<=7 && data[k][j]>=8) || (label>=8 && data[k][j]<=7)){
					listx.add(i);
					listy.add(j);
					listx.add(k);
					listy.add(j);
					break;
				}else {
					break;
				}
			}
		}

		
		if(i+1<ROWS ){
			for(int k=i+1;k<ROWS ;k++){
				if(data[k][j]==0){
					listx.add(i);
					listy.add(j);
					listx.add(k);
					listy.add(j);
				}else if((label<=7 && data[k][j]>=8) || (label>=8 && data[k][j]<=7)){
					listx.add(i);
					listy.add(j);
					listx.add(k);
					listy.add(j);
					break;
				}else {
					break;
				}
			}
		}
		break;
		
	case 6:
	case 13:
		
		if(j-1>=0){
			for(int h=j-1;h>=0;h--){
				if(data[i][h]==0){
					listx.add(i);
					listy.add(j);
					listx.add(i);
					listy.add(h);
				}else {
					if(h-1>=0){
						for(int k=h-1;k>=0;k--){
							if(data[i][k]==0){
								continue;
							}
							else if((label<=7 && data[i][k]>=8)||(label>=8 && data[i][k]<=7)){
								listx.add(i);
								listy.add(j);
								listx.add(i);
								listy.add(k);
								break;
							}else{
								break;
							}
						}
					}
					break;
				}
			}
		}
		
		if(j+1<COLS){
			for(int h=j+1;h<COLS;h++){
				if(data[i][h]==0){
					listx.add(i);
					listy.add(j);
					listx.add(i);
					listy.add(h);
				}else{
					if(h+1<COLS){
						for(int k=h+1;k<COLS;k++){
							if(data[i][k]==0)
								continue;
							else if((label<=7 && data[i][k]>=8)||(label>=8 && data[i][k]<=7)){
								listx.add(i);
								listy.add(j);
								listx.add(i);
								listy.add(k);
								break;
							}else {
								break;
							}
						}
					}
					break;
				}
			}
		}
		
		if(i-1>=0){
			for(int h=i-1;h>=0;h--){
				if(data[h][j]==0){
					listx.add(i);
					listy.add(j);
					listx.add(h);
					listy.add(j);
				}else{
					if(h-1>=0){
						for(int k=h-1;k>=0;k--){
							if(data[k][j]==0)
								continue;
							else if((label<=7 && data[k][j]>=8)||(label>=8 && data[k][j]<=7)){
								listx.add(i);
								listy.add(j);
								listx.add(k);
								listy.add(j);
								break;
							}else {
								break;
							}
						}
					}
					break;
				}
			}
		}
		
		if(i+1<ROWS ){
			for(int h=i+1;h<ROWS ;h++){
				if(data[h][j]==0){
					listx.add(i);
					listy.add(j);
					listx.add(h);
					listy.add(j);
				}else{
					if(h+1<COLS){
						for(int k=h+1;k<ROWS ;k++){
							if(data[k][j]==0)
								continue;
							else if((label<=7 && data[k][j]>=8)||(label>=8 && data[k][j]<=7)){
								listx.add(i);
								listy.add(j);
								listx.add(k);
								listy.add(j);
								break;
							}else {
								break;
							}
						}
					}
					break;
				}
			}
		}
		break;
		//knight
	case 2:
	case 9:
	
		if(i-2>=0 && j+1<COLS){
			if(data[i-1][j]==0){
				if(data[i-2][j+1]==0 || (label<=7 && data[i-2][j+1]>=8) || (label>=8 && data[i-2][j+1]<=7)){
					listx.add(i);
					listy.add(j);
					listx.add(i-2);
					listy.add(j+1);
				}
			}
		}
		
		if(i-1>=0 && j+2<COLS){
			if(data[i][j+1]==0){
				if(data[i-1][j+2]==0 || (label<=7 && data[i-1][j+2]>=8) || (label>=8 && data[i-1][j+2]<=7)){
					listx.add(i);
					listy.add(j);
					listx.add(i-1);
					listy.add(j+2);
				}
			}
		}
	
		if(i+1<ROWS  && j+2<COLS){
			if(data[i][j+1]==0){
				if(data[i+1][j+2]==0 || (label<=7 && data[i+1][j+2]>=8) || (label>=8 && data[i+1][j+2]<=7)){
					listx.add(i);
					listy.add(j);
					listx.add(i+1);
					listy.add(j+2);
				}
			}
		}
	
		if(i+2<ROWS  && j+1<COLS){
			if(data[i+1][j]==0){
				if(data[i+2][j+1]==0 || (label<=7 && data[i+2][j+1]>=8) || (label>=8 && data[i+2][j+1]<=7)){
					listx.add(i);
					listy.add(j);
					listx.add(i+2);
					listy.add(j+1);
				}
			}
		}
		
		if(i+1<ROWS  && j-2>=0){
			if(data[i][j-1]==0){
				if(data[i+1][j-2]==0 || (label<=7 && data[i+1][j-2]>=8) || (label>=8 && data[i+1][j-2]<=7)){
					listx.add(i);
					listy.add(j);
					listx.add(i+1);
					listy.add(j-2);
				}
			}
		}
	
		if(i+2<ROWS  && j-1>=0){
			if(data[i+1][j]==0){
				if(data[i+2][j-1]==0 || (label<=7 && data[i+2][j-1]>=8) || (label>=8 && data[i+2][j-1]<=7)){
					listx.add(i);
					listy.add(j);
					listx.add(i+2);
					listy.add(j-1);
				}
			}
		}
		
		if(i-1>=0 && j-2>=0){
			if(data[i][j-1]==0){
				if(data[i-1][j-2]==0 || (label<=7 && data[i-1][j-2]>=8) || (label>=8 && data[i-1][j-2]<=7)){
					listx.add(i);
					listy.add(j);
					listx.add(i-1);
					listy.add(j-2);
				}
			}
		}
		
		if(i-2>=0 && j-1>=0){
			if(data[i-1][j]==0){
				if(data[i-2][j-1]==0 || (label<=7 && data[i-2][j-1]>=8) || (label>=8 && data[i-2][j-1]<=7)){
					listx.add(i);
					listy.add(j);
					listx.add(i-2);
					listy.add(j-1);
				}
			}
		}
		break;
		
	case 10:
		
		if(i-2>=0 && j+2<COLS){
			if(data[i-1][j+1]==0){
				if(data[i-2][j+2]<=7){
					listx.add(i);
					listy.add(j);
					listx.add(i-2);
					listy.add(j+2);
				}
			}
		}
		
		if(i+2<ROWS /2 && j+2<COLS){
			if(data[i+1][j+1]==0){
				if(data[i+2][j+2]<=7){
					listx.add(i);
					listy.add(j);
					listx.add(i+2);
					listy.add(j+2);
				}
			}
		}
		
		if(i+2<ROWS /2 && j-2>=0){
			if(data[i+1][j-1]==0){
				if(data[i+2][j-2]<=7){
					listx.add(i);
					listy.add(j);
					listx.add(i+2);
					listy.add(j-2);
				}
			}
		}
		
		if(i-2>=0 && j-2>=0){
			if(data[i-1][j-1]==0){
				if(data[i-2][j-2]<=7){
					listx.add(i);
					listy.add(j);
					listx.add(i-2);
					listy.add(j-2);
				}
			}
		}
		break;
	case 3:
		
		if(i-2>=ROWS /2 && j+2<COLS){
			if(data[i-1][j+1]==0){
				if(data[i-2][j+2]==0 ||  data[i-2][j+2]>=8){
					listx.add(i);
					listy.add(j);
					listx.add(i-2);
					listy.add(j+2);
				}
			}
		}
		
		if(i+2<ROWS  && j+2<COLS){
			if(data[i+1][j+1]==0){
				if(data[i+2][j+2]==0 || data[i+2][j+2]>=8){
					listx.add(i);
					listy.add(j);
					listx.add(i+2);
					listy.add(j+2);
				}
			}
		}
		
		if(i+2<ROWS  && j-2>=0){
			if(data[i+1][j-1]==0){
				if(data[i+2][j-2]==0 || data[i+2][j-2]>=8){
					listx.add(i);
					listy.add(j);
					listx.add(i+2);
					listy.add(j-2);
				}
			}
		}
		
		if(i-2>=ROWS /2 && j-2>=0){
			if(data[i-1][j-1]==0){
				if(data[i-2][j-2]==0 || data[i-2][j-2]>=8){
					listx.add(i);
					listy.add(j);
					listx.add(i-2);
					listy.add(j-2);
				}
			}
		}
		break;
	case 4:
		
		if(i-1>=7 && j+1<=5){
			if(data[i-1][j+1]==0 || data[i-1][j+1]>=8){
				listx.add(i);
				listy.add(j);
				listx.add(i-1);
				listy.add(j+1);
			}
		}
		
		if(i+1<ROWS  && j+1<=5){
			if(data[i-1][j+1]==0 || data[i-1][j+1]>=8){
				listx.add(i);
				listy.add(j);
				listx.add(i+1);
				listy.add(j+1);
			}
		}
		
		if(i+1<ROWS  && j-1>=3){
			if(data[i+1][j-1]==0 || data[i+1][j-1]>=8){
				listx.add(i);
				listy.add(j);
				listx.add(i+1);
				listy.add(j-1);
			}
		}
		
		if(i-1>=7 && j-1>=3){
			if(data[i-1][j-1]==0 || data[i-1][j-1]>=8){
				listx.add(i);
				listy.add(j);
				listx.add(i-1);
				listy.add(j-1);
			}
		}
		break;
	case 11:
		
		if(i-1>=0 && j+1<=5){
			if(data[i-1][j+1]<=7){
				listx.add(i);
				listy.add(j);
				listx.add(i-1);
				listy.add(j+1);
			}
		}
		
		if(i+1<=2 && j+1<=5){
			if(data[i+1][j+1]<=7){
				listx.add(i);
				listy.add(j);
				listx.add(i+1);
				listy.add(j+1);
			}
		}
		
		if(i+1<=2 && j-1>=3){
			if(data[i+1][j-1]<=7){
				listx.add(i);
				listy.add(j);
				listx.add(i+1);
				listy.add(j-1);
			}
		}
		
		if(i-1>=0 && j-1>=3){
			if(data[i-1][j-1]<=7){
				listx.add(i);
				listy.add(j);
				listx.add(i-1);
				listy.add(j-1);
			}
		}
		break;
	case 5:
	
		if(i-1<=7){
			if(data[i-1][j]==0 || data[i-1][j]>=8){
				listx.add(i);
				listy.add(j);
				listx.add(i-1);
				listy.add(j);
			}
		}
		
		if(i+1<ROWS ){
			if(data[i+1][j]==0 || data[i+1][j]>=8){
				listx.add(i);
				listy.add(j);
				listx.add(i+1);
				listy.add(j);
			}
		}
		
		if(j-1>=3){
			if(data[i][j-1]==0 || data[i][j-1]>=8){
				listx.add(i);
				listy.add(j);
				listx.add(i);
				listy.add(j-1);
			}
		}
		
		if(j+1<=5){
			if(data[i][j+1]==0 || data[i][j+1]>=8){
				listx.add(i);
				listy.add(j);
				listx.add(i);
				listy.add(j+1);
			}
		}
		break;
	case 12:
		
		if(i-1>=0){
			if(data[i-1][j]<=7){
				listx.add(i);
				listy.add(j);
				listx.add(i-1);
				listy.add(j);
			}
		}
	
		if(i+1<=2){
			if(data[i+1][j]<=7){
				listx.add(i);
				listy.add(j);
				listx.add(i+1);
				listy.add(j);
			}
		}
		
		if(j-1>=3){
			if(data[i][j-1]<=7){
				listx.add(i);
				listy.add(j);
				listx.add(i);
				listy.add(j-1);
			}
		}
		
		if(j+1<=5){
			if(data[i][j+1]<=7){
				listx.add(i);
				listy.add(j);
				listx.add(i);
				listy.add(j+1);
			}
		}
		break;
	case 7:
		
		if(i>4){
			
			if(data[i-1][j]==0 || data[i-1][j]>=8){
				listx.add(i);
				listy.add(j);
				listx.add(i-1);
				listy.add(j);
			}
		}else{
		
			if(i-1>=0){
				if(data[i-1][j]==0 || data[i-1][j]>=8){
					listx.add(i);
					listy.add(j);
					listx.add(i-1);
					listy.add(j);
				}
			}
		
			if(j-1>=0){
				if(data[i][j-1]==0 || data[i][j-1]>=8){
					listx.add(i);
					listy.add(j);
					listx.add(i);
					listy.add(j-1);
				}
			}
	
			if(j+1<COLS){
				if(data[i][j+1]==0 || data[i][j+1]>=8){
					listx.add(i);
					listy.add(j);
					listx.add(i);
					listy.add(j+1);
				}
			}
		}
		break;
	case 14:
	
		if(i<5){
		
			if(data[i+1][j]<=7){
				listx.add(i);
				listy.add(j);
				listx.add(i+1);
				listy.add(j);
			}
		}else{
						if(i+1<ROWS ){
				if(data[i+1][j]<=7){
					listx.add(i);
					listy.add(j);
					listx.add(i+1);
					listy.add(j);
				}
			}
		
			if(j-1>=0){
				if(data[i][j-1]<=7){
					listx.add(i);
					listy.add(j);
					listx.add(i);
					listy.add(j-1);
				}
			}
			
			if(j+1<COLS){
				if(data[i][j+1]<=7){
					listx.add(i);
					listy.add(j);
					listx.add(i);
					listy.add(j+1);
				}
			}
		}
		break;
	default:
		break;
	}
	List<List<Integer>> res=new ArrayList<>();
	System.out.println("ger by lable"+i+""+j);
	res.add(listx);
	res.add(listy);
	System.out.println(res.toString());
	return res;
  }
}
