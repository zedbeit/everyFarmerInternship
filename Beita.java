/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment1;

/**
 *
 * @author Ubaita
 */
public class MatrixMultiplication {

    public static void main(String[] args) {
        int[][] medals = {
            {3, 8, 5},
            {5, 6, 6}
        };
        int[][] score = {
            {3, 4},
            {2, 3},
            {1, 2}
        };
        int[][] finalScore = new int[2][2];

        int r00 = 0, r01 = 0, r10 = 0, r11 = 0;
        
        for (int i = 0; i < medals.length; i++) {
            for (int j = 0; j < score.length; j++) {
                if (i == 0) {
                    r00 += medals[i][j] * score[j][i]; //00
                    r01 += medals[i][j] * score[j][i + 1]; //01
                }
                if (i == 1) {
                    r10 += medals[i][j] * score[j][i - 1]; //10
                    r11 += medals[i][j] * score[j][i];	//11
                }
            }
        }
        
        finalScore[0][0] = r00;
        finalScore[0][1] = r01;
        finalScore[1][0] = r10;
        finalScore[1][1] = r11;
        
        for(int i=0; i<2;i++){
            for(int j=0; j<2;j++){
                System.out.print(finalScore[i][j] + " ");
            }
            System.out.println();
        }
    }
}
