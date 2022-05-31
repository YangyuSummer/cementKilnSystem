package com.wang.core;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator 单纯型算法
 */
public class LinearProgram {

	private int m; // 约束条件的个数
	private int n; // 变量个数
	private int m1; // <=的约束条件个数
	private int m2; // =的约束条件个数
	private int m3; // >=的约束条件个数
	private int error; // 判断是否是错误的
	private int basic[];
	private int nonbasic[];
	private double a[][]; // 约束条件的系数矩阵
	private double minmax; // 目标函数的最大值或最小值

	/**
	 * 
	 * @param minmax
	 *            -求函数的最大值或最小值
	 * @param m
	 *            -约束条件的个数
	 * @param n
	 *            -变量个数
	 * @param m1
	 *            -<=的约束条件个数
	 * @param m2
	 *            -=的约束条件个数
	 * @param m3
	 *            ->=的约束条件个数
	 * @param a
	 *            -约束条件的系数矩阵
	 * @param x
	 *            -目标函数的价值系数
	 */
	public LinearProgram(double minmax, int m, int n, int m1, int m2, int m3,
			double a[][], double x[]) {
		String minmaxstring = null;
		double value;
		this.error = 0;
		this.minmax = minmax;
		this.m = m;
		this.n = n;
		this.m1 = m1;
		this.m2 = m2;
		this.m3 = m3;
		//---------打印目标函数和约束方程-------start--------------//
		if (minmax == 1) {
			minmaxstring = "max";
		} else {
			minmaxstring = "min";
		}
		System.out.print("--目标函数" + minmaxstring + ": ");
		for (int i = 1; i < n; i++) {
			System.out.print(x[i - 1] + "X" + (i-1) + "+");
		}
		System.out.println(x[n - 1] + "X" + (n-1));
		System.out.println("--约束方程 :");
		for (int i = 0; i < m; i++) {
			for (int j = 0; j <= n; j++) {
				if (j > 0 && j < n) {
					if (a[i][j] >= 0) {
						System.out.print("+");
					}
					System.out.print(a[i][j] + "X" + (j ));
				} else if (j == n) {
					if (i < m1) {
						System.out.print("<=");
					} else if (i < m1 + m2) {
						System.out.print("=");
					} else {
						System.out.print(">=");
					}
					System.out.print(a[i][j]);
				} else {
					System.out.print(a[i][j] + "X" + (j ));
				}
			}
			System.out.print("\n");
		}
		//---------打印目标函数和约束方程-------end--------------//
		if (m != m1 + m2 + m3) {
			this.error = 1;
		}
		this.a = new double[m + 2][];
		for (int i = 0; i < m + 2; i++) {
			this.a[i] = new double[n + m + m3 + 1];
		}
		this.basic = new int[m + 2];
		this.nonbasic = new int[n + m3 + 1];
		/*
		  for(int i = 0; i <= m+1; i++) { for(int j = 0; j <= n+m+m3; j++) {
		  this.a[i][j] = 0.0;//初始化约束条件的系数矩阵[m+2]*[n+m+m3+1]所有元素为0 
		  } 
		  }*/
		 
		for (int j = 0; j <= n + m3; j++) {
			nonbasic[j] = j;// 存储非基变量
		}
		for (int i = 1, j = n + m3 + 1; i <= m; i++, j++) {
			basic[i] = j;// 存储基变量
		}
		for (int i = m - m3 + 1, j = n + 1; i <= m; i++, j++) {
			this.a[i][j] = -1;
			this.a[m + 1][j] = -1;
		}
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				value = a[i - 1][j - 1];
				this.a[i][j] = value;
			}
			value = a[i - 1][n];
			if (value < 0) {
				error = 1;
			}
			this.a[i][0] = value;
		}
		for (int j = 1; j <= n; j++) // 价值系数
		{
			value = x[j - 1];
			this.a[0][j] = value * minmax;
		}
		for (int j = 1; j <= n; j++) {
			value = 0;
			for (int i = m1 + 1; i <= m; i++)
				value += this.a[i][j];
			this.a[m + 1][j] = value;
		}

	}

	public int enter(int objrow) {
		int col = 0;
		for (int j = 1; j <= this.n + this.m3; j++) {
			if (this.nonbasic[j] <= this.n + this.m1 + this.m3
					&& this.a[objrow][j] > 10e-8) {
				col = j;
				break;
			}
		}
		return col;
	}

	public int leave(int col) {
		double temp = -1;
		int row = 0;
		for (int i = 1; i <= this.m; i++) {
			double val = this.a[i][col];
			if (val > 10e-8) {
				val = this.a[i][0] / val;
				if (val < temp || temp == -1) {
					row = i;
					temp = val;
				}
			}
		}
		return row;
	}

	public void swapbasic(int row, int col) {
		int temp = this.basic[row];
		this.basic[row] = this.nonbasic[col];
		this.nonbasic[col] = temp;
	}

	public void pivot(int row, int col) {
		for (int j = 0; j <= this.n + this.m3; j++) {
			if (j != col) {
				this.a[row][j] = this.a[row][j] / this.a[row][col];
			}
		}
		this.a[row][col] = 1.0 / this.a[row][col];
		for (int i = 0; i <= this.m + 1; i++) {
			if (i != row) {
				for (int j = 0; j <= this.n + this.m3; j++) {
					if (j != col) {
						this.a[i][j] = this.a[i][j] - this.a[i][col]
								* this.a[row][j];
						if (Math.abs(this.a[i][j]) < 10e-8)
							this.a[i][j] = 0;
					}
				}
				this.a[i][col] = -this.a[i][col] * this.a[row][col];
			}
		}
		swapbasic(row, col);
	}

	public int simplex(int objrow) {
		int row = 0;
		while (true) {
			int col = enter(objrow);
			if (col > 0) {
				row = leave(col);
			} else {
				return 0;
			}
			if (row > 0) {
				pivot(row, col);
			} else {
				return 2;
			}
		}
	}

	public int phase1() {
		this.error = simplex(this.m + 1);
		if (this.error > 0) {
			return this.error;
		}
		for (int i = 1; i <= this.m; i++) {
			if (this.basic[i] > this.n + this.m1 + this.m3) {
				if (this.a[i][0] > 10e-8) {
					return 3;
				}
				for (int j = 1; j <= this.n; j++) {
					if (Math.abs(this.a[i][j]) >= 10e-8) {
						pivot(i, j);
						break;
					}
				}
			}
		}
		return 0;
	}

	public int phase2() {
		return simplex(0);
	}

	public int compute() {
		if (this.error > 0)
			return this.error;
		if (this.m != this.m1) {
			this.error = phase1();
			if (this.error > 0)
				return this.error;
		}
		return phase2();
	}
	
	
	
	public String solve() {
		error = compute();
		if(error==0){
			output0();
			return "可行方案";
		}else if(error==1){
			return "输入数据错误";
		}else if(error==2){
			return "无界解";
		}else{
			return "无可行解";
		}
	}

	public  Map<String,Double> solve0()
    {
        Map<String,Double> backObject0=new HashMap<String, Double>();
        error = compute();
        double out=0;
        String outs=null;
		if(error==0){
			out=output0();
			outs="可行方案";
		}else if(error==1){
			outs="输入数据错误";
		}else if(error==2){
			outs="无界解";
		}else{
			outs="无可行解";
		}
        
		backObject0.put(outs, out);
        return backObject0;
    }
	public double output0() {
		int basicp[] = new int[n + 1];
		for (int i = 0; i <= n; i++) {
			basicp[i] = 0;
		}
		for (int i = 1; i <= m; i++) {
			if (basic[i] >= 1 && basic[i] <= n) {
				basicp[basic[i]] = i;
			}
		}
		for (int i = 1; i <= n; i++) {
			System.out.print("x" + (i-1) + "=");
			if (basicp[i] != 0) {
				System.out.print(a[basicp[i]][0]);
			} else {
				System.out.print("0");
			}
			System.out.println();
		}
		System.out.println("最优值:" + -minmax * a[0][0] );
		double xxx=-minmax * a[0][0];
		return xxx;
	}
	public  Map<String,Map<String,Double>> solve1(){
		Map<String,Map<String,Double>> backObject1=new HashMap<String,Map<String,Double>>();
		error = compute();
		Map<String,Double> out=new HashMap<String,Double>();
        String outs=null;
		if(error==0){
			out=output1();
			outs="可行方案";
		}else if(error==1){
			outs="输入数据错误";
		}else if(error==2){
			outs="无界解";
		}else{
			outs="无可行解";
		}
		backObject1.put(outs, out);
		return backObject1;
	}
	public Map<String,Double> output1() {
		 Map<String,Double> xxxObject=new HashMap<String, Double>();
		int basicp[] = new int[n + 1];
		for (int i = 0; i <= n; i++) {
			basicp[i] = 0;
		}
		for (int i = 1; i <= m; i++) {
			if (basic[i] >= 1 && basic[i] <= n) {
				basicp[basic[i]] = i;
			}
		}
		for (int i = 1; i <= n; i++) {
			System.out.print("x" + (i-1) + "=");
			if (basicp[i] != 0) {
				System.out.print(a[basicp[i]][0]);
				xxxObject.put("x"+(i-1), a[basicp[i]][0]);
			} else {
				System.out.print("0");
				xxxObject.put("x"+(i-1),Double.valueOf(0));
			}
			System.out.println();
		}
		System.out.println("最优值:" + -minmax * a[0][0] );
		//xxxObject.put("最优值",-minmax * a[0][0]);
		return xxxObject;
	}

}
