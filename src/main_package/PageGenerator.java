package main_package;

import java.util.Random;

public class PageGenerator{
	
  private Random randomico;
  private int[][][] matrix;
  private int firstfreesheet;
  private int maxnumsheets;

  
  
  public PageGenerator(int paramInt)
  {
    this.matrix = new int[paramInt][18][9];
    this.maxnumsheets = paramInt;
    clearall();
    this.randomico = new Random();
  }
  
  private void clearall()
  {
    this.firstfreesheet = 0;
    for (int k = 0; k < this.maxnumsheets; k++)
      for (int i = 0; i < 18; i++)
        for (int j = 0; j < 9; j++)
          this.matrix[k][i][j] = 0;
  }


  public void append(int paramInt)
  {
    int i = this.firstfreesheet;
    int j = 0;
    while ((i < paramInt + this.firstfreesheet) && (j < paramInt)) {
      riempi(this.matrix[i]);

      cinquine(this.matrix[i]);

      doppio(this.matrix[i]);
      	
      orderNumber(this.matrix[i]);
      
      System.out.println("Created "+j);

      if (check(this.matrix, i)) i++;
      else {
          System.out.println("Error table "+j);

    	  while(!check(this.matrix, i)){
              System.out.println("Recreating "+j);
              for (int k = 0; k < 18; k++) {
                  for (int m = 0; m < 9; m++) {
                    this.matrix[i][k][m] = 0;
                  }
                }

    	      riempi(this.matrix[i]);
              System.out.println("riempi "+j);

    	      cinquine(this.matrix[i]);
              System.out.println("cinquine "+j);

    	      doppio(this.matrix[i]);
              System.out.println("doppio "+j);

    	      orderNumber(this.matrix[i]);
              System.out.println("order "+j);

    	  }
    	  i++;
        
      }
      j++;
    }
    this.firstfreesheet += paramInt;
  }

  private void riempi(int[][] oneListTables)
  {
    int i = 1;

    while (i <= 90)
    {
      int j = this.randomico.nextInt(18);
      int k;
      if (i == 90)
        k = 8;
      else {
        k = i / 10;
      }
      if (oneListTables[j][k] == 0)
      {
        oneListTables[j][k] = i;
        i++;
      }
    }
  }

  private void cinquine(int[][] oneListTables)
  {
    int[] arrayOfInt = new int[18];
    int j;
    for (int i = 0; i < 18; i++) {
      arrayOfInt[i] = 0;
      for (j = 0; j < 9; j++) {
        if (oneListTables[i][j] != 0) arrayOfInt[i] += 1;
      }
    }
    int i = 0;
    label289: while (i < 17)
      if (arrayOfInt[i] == 5) {
        i++;
      }
      else
      {
        int k;
        boolean label = false;
        label182:
        if (arrayOfInt[i] < 5 && !label) {
          for (j = i; (j < 18) && 
            (arrayOfInt[j] <= 5); j++);
          for (k = 0; ; k++){ 
        	  if (k >= 9) {
        		  label = true;
        		  break label182;
        	  }
	          if ((oneListTables[i][k] == 0) && (oneListTables[j][k] != 0)) {
	              oneListTables[i][k] = oneListTables[j][k];
	              oneListTables[j][k] = 0;
	              arrayOfInt[i] += 1;
	              arrayOfInt[j] -= 1;
	              break;
	            } 
	        }
        }
        
        else
        {
           if (arrayOfInt[i] > 5) {
            for (j = i; (j < 18) && 
              (arrayOfInt[j] >= 5); j++);
            for (k = 0; ; k++) { if (k >= 9) break label289;
              if ((oneListTables[i][k] != 0) && (oneListTables[j][k] == 0)) {
                oneListTables[j][k] = oneListTables[i][k];
                oneListTables[i][k] = 0;
                arrayOfInt[i] -= 1;
                arrayOfInt[j] += 1;
                break;
              }
            }
          }
        }
      }
  }

  private void doppio(int[][] oneListTables)
  {
    for (int i = 0; i < 18; i++)
      for (int j = 2; j < 9; j++)
        if ((oneListTables[i][j] != 0) && (oneListTables[i][(j - 1)] != 0) && (oneListTables[i][(j - 2)] != 0)) {
          int k = 0; int m = 0;
          do {
            if ((oneListTables[k][j] == 0) && (oneListTables[i][m] == 0) && (oneListTables[k][m] != 0) && ((m < 2) || (oneListTables[i][(m - 1)] == 0) || (oneListTables[i][(m - 2)] == 0)) && ((m < 1) || (m > 7) || (oneListTables[i][(m - 1)] == 0) || (oneListTables[i][(m + 1)] == 0)) && ((m > 6) || (oneListTables[i][(m + 1)] == 0) || (oneListTables[i][(m + 2)] == 0)) && ((j < 2) || (oneListTables[k][(j - 1)] == 0) || (oneListTables[k][(j - 2)] == 0)) && ((j < 1) || (j > 7) || (oneListTables[k][(j - 1)] == 0) || (oneListTables[k][(j + 1)] == 0)) && ((j > 6) || (oneListTables[k][(j + 1)] == 0) || (oneListTables[k][(j + 2)] == 0)))
            {
              oneListTables[k][j] = oneListTables[i][j];
              oneListTables[i][j] = 0;
              oneListTables[i][m] = oneListTables[k][m];
              oneListTables[k][m] = 0;
              break;
            }
            m++; if (m >= 9) { m = 0; k++; } 
          }while (k < 18);
        }
  }
  
  private void orderNumber(int[][] matrix){
	  for(int interval = 0; interval<6;interval++){
		  boolean scambio = true;
		  while(scambio){
			  scambio = false;
			  for(int row_count = 0; row_count < 2; row_count++){
				  for(int column = 0; column<matrix[0].length; column++){
					  if(matrix[3*interval+row_count][column] != 0 && matrix[3*interval+row_count+1][column] != 0){
						  if(matrix[3*interval+row_count][column] > matrix[3*interval+row_count+1][column]){
							  int tmp = matrix[3*interval+row_count+1][column];
							  matrix[3*interval+row_count+1][column] = matrix[3*interval+row_count][column];
							  matrix[3*interval+row_count][column] = tmp;
							  scambio = true;
						  }
					  }else if(row_count == 0 && matrix[3*interval+row_count][column] != 0 && matrix[3*interval+row_count+1][column] == 0 && matrix[3*interval+row_count+2][column] != 0){
						  if(matrix[3*interval+row_count][column] > matrix[3*interval+row_count+2][column]){
							  int tmp = matrix[3*interval+row_count+2][column];
							  matrix[3*interval+row_count+2][column] = matrix[3*interval+row_count][column];
							  matrix[3*interval+row_count][column] = tmp;
							  scambio = true;
						  }
					  }
				  }
			  }
		  }
	  }
  }

  private boolean check(int[][][] allPages, int paramInt)
  {
    for (int i = 0; i < 18; i++) {
      for (int j = 0; j < paramInt; j++) {
        for (int k = 0; k < 18; k++) {
        	int m = 0;
          for (; (m < 9) && 
            (allPages[paramInt][i][m] == allPages[j][k][m]); m++);
          if (m == 9) {
            return false;
          }
        }
      }
    }
    return true;
  }
  
  public void printTable(){
	  int tableNumber = 1;
	  for(int i=0; i< matrix.length; i++){
		  for(int j=0; j< matrix[i].length; j++){
			  if(j % 3 == 0){
				  System.out.println("Table "+tableNumber+"\n");
				  tableNumber++;
			  }
			  for(int k=0; k < matrix[i][j].length; k++){
				  System.out.print(matrix[i][j][k]+" ");
			  }
			  System.out.println("\n");

		  }
	  }
  }
  
  public int[][][] getPages(){
	  return this.matrix;
  }
  

}