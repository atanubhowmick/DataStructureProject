package dev.atanu.ds.java.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 
 * @author Atanu Bhowmick
 *
 */
public class ArraySolution2D {

	public static void main(String[] args) {
		int[][] arr = new int[][] { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } };
		ArraySolution2D solution = new ArraySolution2D();
		System.out.println(solution.uniquePaths(3, 2));
	}

	/**
	 * https://leetcode.com/problems/rotate-image/
	 * 
	 * @param matrix
	 */
	public void rotate(int[][] matrix) {
		int start = 0, end = matrix.length - 1;
		while (start < end) {
			for (int i = 0; i < matrix[start].length; i++) {
				int temp = matrix[start][i];
				matrix[start][i] = matrix[end][i];
				matrix[end][i] = temp;
			}
			start++;
			end--;
		}
		transposeMatrix(matrix);
	}

	private void transposeMatrix(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = i + 1; j < matrix[i].length; j++) {
				int temp = matrix[i][j];
				matrix[i][j] = matrix[j][i];
				matrix[j][i] = temp;
			}
		}
	}
	
	/**
	 * https://leetcode.com/problems/shift-2d-grid/
	 * https://leetcode.com/problems/rotate-array/
	 * 
	 * @param grid
	 * @param k
	 * @return
	 */
	public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        k %= m * n;
        reverse(grid, 0, m * n - 1);
        reverse(grid, 0, k - 1);
        reverse(grid, k, m * n - 1);
        
        List<List<Integer>> result = new ArrayList<>();
        for(int i = 0; i < m; i++) {
        	List<Integer> list = new ArrayList<>();
        	for(int j = 0; j < n; j++) {
        		list.add(grid[i][j]);
        	}
        	result.add(list);
        }
        return result;
    }
    
    private void reverse(int[][] grid, int left, int right) {
        int n = grid[0].length;
        while (left < right) {
            int leftRow = left / n, leftCol = left % n; 
            int rightRow = right / n, rightCol = right % n;
            
            int tmp = grid[leftRow][leftCol];
            grid[leftRow][leftCol] = grid[rightRow][rightCol];
            grid[rightRow][rightCol] = tmp;
            
            left++;
            right--;
        }
    }
	
	/**
	 * https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/
	 * 
	 * @param matrix
	 * @param k
	 * @return
	 */
	public int kthSmallest(int[][] matrix, int k) {
		int m = matrix.length;
		int n = matrix[0].length;

		int ans = -1;
		PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(arr -> arr[0]));

		for (int row = 0; row < Math.min(m, k); ++row) {
			// Take the values from first column initially
			minHeap.offer(new int[] { matrix[row][0], row, 0 });
		}

		for (int i = 1; i <= k; ++i) {
			int[] top = minHeap.poll();
			int row = top[1];
			int col = top[2];
			ans = top[0];
			if (col + 1 < n) {
				// Add values through the row in queue.
				minHeap.offer(new int[] { matrix[row][col + 1], row, col + 1 });
			}
		}
		return ans;
	}
	
	/**
	 * https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/
	 * 
	 * @param matrix
	 * @param k
	 * @return
	 */
	public int kthSmallestWithBinarySearch(int[][] matrix, int k) {
		int m = matrix.length;
		int n = matrix[0].length; // For general, the matrix need not be a square

		int left = matrix[0][0];
		int right = matrix[m - 1][n - 1];

		int ans = -1;

		while (left <= right) {
			int mid = (left + right) >> 1;
			if (countLessOrEqual(matrix, mid) >= k) {
				ans = mid;
				right = mid - 1; // try to looking for a smaller value in the left side
			} else {
				left = mid + 1; // try to looking for a bigger value in the right side
			}
		}
		return ans;
	}

	private int countLessOrEqual(int[][] matrix, int x) {
		int m = matrix.length;
		int n = matrix[0].length;
		int count = 0;
		int col = n - 1; // start with the rightmost column
		for (int row = 0; row < m; ++row) {
			while (col >= 0 && matrix[row][col] > x) {
				--col; // decrease column until matrix[r][c] <= x
			}
			count += (col + 1);
		}
		return count;
	}
	
	/**
	 * https://leetcode.com/problems/sort-the-matrix-diagonally
	 * 
	 * @param mat
	 * @return
	 */
	public int[][] diagonalSort(int[][] mat) {
		int m = mat.length, n = mat[0].length;
		HashMap<Integer, PriorityQueue<Integer>> map = new HashMap<>();
		for (int r = 0; r < m; r++) {
			for (int c = 0; c < n; c++) {
				map.putIfAbsent(r - c, new PriorityQueue<>());
				map.get(r - c).add(mat[r][c]);
			}
		}
		for (int r = 0; r < m; r++) {
			for (int c = 0; c < n; c++) {
				mat[r][c] = map.get(r - c).poll();
			}
		}
		return mat;
	}
	
	/**
	 * https://leetcode.com/problems/trapping-rain-water-ii/
	 * 
	 * @param heightMap
	 * @return
	 */
	public int trapRainWater(int[][] heightMap) {
		int m = heightMap.length;
		int n = heightMap[0].length;
		boolean[][] visited = new boolean[m][n];

		PriorityQueue<Cell> queue = new PriorityQueue<>((a, b) -> a.height - b.height);

		for (int i = 0; i < m; i++) {
			visited[i][0] = true;
			visited[i][n - 1] = true;
			queue.offer(new Cell(i, 0, heightMap[i][0]));
			queue.offer(new Cell(i, n - 1, heightMap[i][n - 1]));
		}

		for (int i = 0; i < n; i++) {
			visited[0][i] = true;
			visited[m - 1][i] = true;
			queue.offer(new Cell(0, i, heightMap[0][i]));
			queue.offer(new Cell(m - 1, i, heightMap[m - 1][i]));
		}

		int[][] dirs = new int[][] { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
		int res = 0;
		while (!queue.isEmpty()) {
			Cell cell = queue.poll();
			for (int[] dir : dirs) {
				int row = cell.row + dir[0];
				int col = cell.col + dir[1];
				if (row >= 0 && row < m && col >= 0 && col < n && !visited[row][col]) {
					visited[row][col] = true;
					res += Math.max(0, cell.height - heightMap[row][col]);
					queue.offer(new Cell(row, col, Math.max(heightMap[row][col], cell.height)));
				}
			}
		}

		return res;
	}
	
	private class Cell {
		int row;
		int col;
		int height;

		public Cell(int row, int col, int height) {
			this.row = row;
			this.col = col;
			this.height = height;
		}
	}
	
	/**
	 * https://leetcode.com/problems/average-waiting-time/
	 * 
	 * @param customers
	 * @return
	 */
	public double averageWaitingTime(int[][] customers) {
		long waitingTime = 0;
		long currentTime = customers[0][0];
		for (int i = 0; i < customers.length; i++) {
			if (currentTime > customers[i][0]) {
				waitingTime += currentTime - customers[i][0];
			} else {
				currentTime = customers[i][0];
			}
			currentTime += customers[i][1];
			waitingTime += customers[i][1];
		}

		return (double) waitingTime / customers.length;
	}
	
	/**
	 * https://leetcode.com/problems/where-will-the-ball-fall/
	 * 
	 * @param grid
	 * @return
	 */
	public int[] findBall(int[][] grid) {
		int[] result = new int[grid[0].length];
		// Each loop computes the result for when be drop a ball in column col.
		for (int col = 0; col < grid[0].length; ++col) {
			int currRow = 0, currCol = col;
			while (currRow < grid.length) {
				if (grid[currRow][currCol] == 1 && currCol + 1 < grid[0].length && grid[currRow][currCol + 1] == 1) {
					// We go to the right if the current value and the value to the right are both
					// equal to 1.
					currRow++;
					currCol++;
				} else if (grid[currRow][currCol] == -1 && currCol - 1 >= 0 && grid[currRow][currCol - 1] == -1) {
					// We go to the left if the current value and the value to the left are both
					// equal to -1.
					currRow++;
					currCol--;
				} else {
					break; // If we can't move to the left or right, then the ball is stuck.
				}
			}
			result[col] = currRow == grid.length ? currCol : -1; // Store -1 if the ball got stuck.
		}
		return result;
	}

	/**
	 * https://leetcode.com/problems/spiral-matrix/
	 * 
	 * @param matrix
	 * @return
	 */
	public List<Integer> spiralOrder(int[][] matrix) {
		List<Integer> result = new ArrayList<>();
		int rowStart = 0, rowEnd = matrix.length - 1;
		int colStart = 0, colEnd = matrix[0].length - 1;

		while (rowStart <= rowEnd && colStart <= colEnd) {
			for (int i = colStart; i <= colEnd; i++) {
				result.add(matrix[rowStart][i]);
			}
			rowStart++;

			for (int j = rowStart; j <= rowEnd; j++) {
				result.add(matrix[j][colEnd]);
			}
			colEnd--;

			if (rowStart <= rowEnd) {
				for (int i = colEnd; i >= colStart; i--) {
					result.add(matrix[rowEnd][i]);
				}
				rowEnd--;
			}

			if (colStart <= colEnd) {
				for (int j = rowEnd; j >= rowStart; j--) {
					result.add(matrix[j][colStart]);
				}
				colStart++;
			}
		}

		return result;
	}

	/**
	 * https://leetcode.com/problems/spiral-matrix-ii/
	 * 
	 * @param n
	 * @return
	 */
	public int[][] generateMatrix(int n) {
		int[][] matrix = new int[n][n];
		int val = 1;

		int rowStart = 0, rowEnd = n - 1;
		int colStart = 0, colEnd = n - 1;

		while (rowStart <= rowEnd && colStart <= colEnd) {
			for (int i = colStart; i <= colEnd; i++) {
				matrix[rowStart][i] = val++;
			}
			rowStart++;

			for (int j = rowStart; j <= rowEnd; j++) {
				matrix[j][colEnd] = val++;
			}
			colEnd--;

			if (rowStart <= rowEnd) {
				for (int i = colEnd; i >= colStart; i--) {
					matrix[rowEnd][i] = val++;
				}
				rowEnd--;
			}

			if (colStart <= colEnd) {
				for (int j = rowEnd; j >= rowStart; j--) {
					matrix[j][colStart] = val++;
				}
				colStart++;
			}
		}

		return matrix;
	}

	/**
	 * https://leetcode.com/problems/set-matrix-zeroes/submissions/
	 * 
	 * @param matrix
	 */
	public void setZeroes(int[][] matrix) {
		boolean isRowZero = false;
		boolean isColZero = false;

		int m = matrix.length;
		int n = matrix[0].length;

		for (int i = 0; i < m; i++) {
			if (matrix[i][0] == 0) {
				isColZero = true;
				break;
			}
		}

		for (int j = 0; j < n; j++) {
			if (matrix[0][j] == 0) {
				isRowZero = true;
				break;
			}
		}

		// Mark all the zeros in first row and column
		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				if (matrix[i][j] == 0) {
					matrix[i][0] = 0;
					matrix[0][j] = 0;
				}
			}
		}

		// Traverse from the end and set zero based on first row and column
		for (int i = m - 1; i > 0; i--) {
			for (int j = n - 1; j > 0; j--) {
				if (matrix[i][0] == 0 || matrix[0][j] == 0) {
					matrix[i][j] = 0;
				}
			}
		}

		if (isRowZero) {
			for (int j = 0; j < n; j++) {
				matrix[0][j] = 0;
			}
		}

		if (isColZero) {
			for (int i = 0; i < m; i++) {
				matrix[i][0] = 0;
			}
		}
	}
	
	/**
	 * https://leetcode.com/problems/max-area-of-island/
	 * 
	 * @param grid
	 * @return
	 */
	public int maxAreaOfIsland(int[][] grid) {
		int maxArea = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == 1) {
					maxArea = Math.max(maxArea, areaOfIsland(grid, i, j));
				}
			}
		}
		return maxArea;
	}

	private int areaOfIsland(int[][] grid, int i, int j) {
		if (i >= 0 && i < grid.length && j >= 0 && j < grid[0].length && grid[i][j] == 1) {
			grid[i][j] = 0;
			return 1 + areaOfIsland(grid, i + 1, j) + areaOfIsland(grid, i - 1, j) + areaOfIsland(grid, i, j - 1)
					+ areaOfIsland(grid, i, j + 1);
		}
		return 0;
	}
	
	/**
	 * https://leetcode.com/problems/count-sub-islands/
	 * 
	 * @param grid1
	 * @param grid2
	 * @return
	 */
	public int countSubIslands(int[][] grid1, int[][] grid2) {
		int count = 0;
		for (int i = 0; i < grid2.length; i++) {
			for (int j = 0; j < grid2[i].length; j++) {
				if (grid2[i][j] == 1 && traverseGrid(grid1, grid2, i, j)) {
					count++;
				}
			}
		}
		return count;
	}

	private boolean traverseGrid(int[][] grid1, int[][] grid2, int x, int y) {
		if (x < 0 || x >= grid2.length || y < 0 || y >= grid2[x].length || grid2[x][y] != 1) {
			return true;
		}
		grid2[x][y] = 2;

		boolean isValid = true;
		if (grid1[x][y] != 1) {
			isValid = false;
		}

		isValid = traverseGrid(grid1, grid2, x - 1, y) && isValid;
		isValid = traverseGrid(grid1, grid2, x + 1, y) && isValid;
		isValid = traverseGrid(grid1, grid2, x, y - 1) && isValid;
		isValid = traverseGrid(grid1, grid2, x, y + 1) && isValid;

		return isValid;
	}

	/**
	 * https://leetcode.com/problems/score-after-flipping-matrix/
	 * 
	 * @param grid
	 * @return
	 */
	public int matrixScore(int[][] grid) {
		int m = grid.length, n = grid[0].length;
		int sum = 0;
		for (int i = 0; i < m; i++) {
			if (grid[i][0] == 0) {
				for (int j = 0; j < n; j++) {
					grid[i][j] = grid[i][j] == 0 ? 1 : 0;
				}
			}
		}

		for (int j = 1; j < n; j++) {
			int count = 0;
			for (int i = 0; i < m; i++) {
				if (grid[i][j] == 1) {
					count++;
				}
			}
			if (count <= grid.length / 2) {
				for (int i = 0; i < m; i++) {
					grid[i][j] = grid[i][j] == 0 ? 1 : 0;
				}
			}
		}

		for (int i = 0; i < m; i++) {
			int decimal = 0;
			int p = 0;
			for (int j = grid[i].length - 1; j >= 0; j--) {
				decimal += grid[i][j] * Math.pow(2, p++);
			}
			sum += decimal;
		}
		return sum;
	}
	
	/**
	 * https://leetcode.com/problems/queens-that-can-attack-the-king/
	 * 
	 * @param queens
	 * @param king
	 * @return
	 */
	public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
        
        int[][] board = new int[8][8];
        List<List<Integer>> result = new ArrayList<>();
        
        for(int i = 0; i < queens.length; i++) {
            board[queens[i][0]][queens[i][1]] = 1;
        }
        
        // Moves of the King in all direction
        int[][] moves = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}, 
                         {1, 1}, {-1, -1}, {-1, 1}, {1, -1}};
        
        for(int i = 0; i < moves.length; i++) {
            int x = king[0] + moves[i][0];
            int y = king[1] + moves[i][1];
            while(x >= 0 && x < 8 && y>= 0 && y < 8) {
                if(board[x][y] == 1) {
                    List<Integer> coordinate = new ArrayList<>();
                    coordinate.add(x);
                    coordinate.add(y);
                    result.add(coordinate);
                    break;
                }
                x += moves[i][0];
                y += moves[i][1];
            }
        }
        return result;
    }
	
	/**
	 * https://leetcode.com/problems/queue-reconstruction-by-height/
	 * 
	 * @param people
	 * @return
	 */
	public int[][] reconstructQueue(int[][] people) {
		Arrays.sort(people, (a, b) -> a[0] == b[0] ? a[1] - b[1] : b[0] - a[0]);
		List<int[]> list = new LinkedList<>();
		for (int[] p : people) {
			list.add(p[1], p);
		}
		return list.toArray(new int[list.size()][]);
	}
	
	/**
	 * https://leetcode.com/problems/stone-game/
	 * 
	 * @param piles
	 * @return
	 */
	public boolean stoneGame(int[] piles) {
		int n = piles.length;
		int[][][] memo = new int[n + 1][n + 1][2];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				Arrays.fill(memo[i][j], -1);
			}
		}
		int diff = stoneGameHelper(0, n - 1, piles, memo, 1);
		return diff >= 0;
	}

	private int stoneGameHelper(int left, int right, int[] piles, int[][][] memo, int id) {
		if (left < right) {
			return 0;
		}
		if (memo[left][right][id] != -1) {
			return memo[left][right][id];
		}
		int next = id == 1 ? 0 : 1;
		if (id == 1) {
			memo[left][right][id] = Math.max((stoneGameHelper(left + 1, right, piles, memo, next) + piles[left]),
					(stoneGameHelper(left, right - 1, piles, memo, next) + piles[right]));
		} else {
			memo[left][right][id] = Math.max((stoneGameHelper(left + 1, right, piles, memo, next) - piles[left]),
					(stoneGameHelper(left, right - 1, piles, memo, next) - piles[right]));
		}
		return memo[left][right][id];
	}
	
	/**
	 * https://leetcode.com/problems/k-closest-points-to-origin/
	 * @param points
	 * @param k
	 * @return
	 */
	public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<double[]> queue = new PriorityQueue<>(
            (p1, p2) -> Double.compare(p1[0], p2[0]));
        for(int[] point : points) {
            int x = point[0] - 0;
            int y = point[1] - 0;
            double distance = Math.sqrt((double) x*x + (double) y*y);
            queue.offer(new double[]{distance, point[0], point[1]});
            if(queue.size() > k) {
                queue.poll();
            }
        }
        
        int[][] result = new int[k][2];
        int i = 0;
        while(!queue.isEmpty()) {
            double[] arr = queue.poll();
            result[i++] = new int[] {(int)arr[1], (int)arr[2]};
        }
        return result;
    }
	
	/**
	 * https://leetcode.com/problems/game-of-life/
	 * https://leetcode.com/problems/game-of-life/discuss/73223/Easiest-JAVA-solution-with-explanation
	 * 
	 * @param board
	 */
	public void gameOfLife(int[][] board) {
        int m = board.length, n = board[0].length;
        
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                int count  = checkLiveNeighbors(board, i, j);
                if(board[i][j] == 1 && (count == 2 || count == 3)) {
                    // Set the cell as 3 as 11 bits for live from live
                    board[i][j] = 3;
                } else if(board[i][j] == 0 && count == 3) {
                    // Set the cell as 2 as 11 bits for live from dead
                    board[i][j] = 2;
                }
            }
        }
        
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                board[i][j] = board[i][j] >> 1;
            }
        }
    }
    
    private int checkLiveNeighbors(int[][] board, int i, int j) {
        int m = board.length, n = board[0].length;
        int count = 0;
        
        int[][] dirs = new int[][] {{-1, 0}, {0, -1}, {-1, -1}, {-1, 1},
                                    {1, -1}, {0, 1}, {1, 0}, {1, 1}};
        
        for(int[] dir : dirs) {
            int x = i + dir[0];
            int y = j + dir[1];
            
            if(x >= 0 && x < m && y >= 0 && y < n) {
                // To determine the next step, LSB of the current state to be taken
                count += board[x][y] & 1;
            }
        }
        
        return count;
    }
    
    /**
     * https://leetcode.com/problems/valid-sudoku/
     * 
     * @param board
     * @return
     */
	public boolean isValidSudoku(char[][] board) {
		Set<String> set = new HashSet<>();

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				char num = board[i][j];
				boolean validRow = set.add(num + " row at:" + i);
				boolean validCol = set.add(num + " col at:" + j);
				boolean validRowCol = set.add(num + " row at:" + i / 3 + " col at:" + j / 3);
				if (num != '.' && !(validRow && validCol && validRowCol)) {
					return false;
				}
			}
		}
		return true;
	}

	/**
     * https://leetcode.com/problems/valid-sudoku/
     * 
     * @param board
     * @return
     */
	public boolean isValidSudokuWithoutSet(char[][] board) {
		int number;
		for (int row = 0; row < board.length; row++) {
			boolean[] rowCheck = new boolean[9];
			boolean[] colCheck = new boolean[9];
			boolean[] boxCheck = new boolean[9];

			for (int col = 0; col < board[0].length; col++) {
				// check the row
				if (board[row][col] != '.') {
					number = board[row][col] - '1';
					if (rowCheck[number]) {
						return false;
					}
					rowCheck[number] = true;
				}

				// check the column
				if (board[col][row] != '.') {
					number = board[col][row] - '1';
					if (colCheck[number]) {
						return false;
					}
					colCheck[number] = true;
				}

				// check the sub-box
				int rowBox = (row / 3) * 3 + col / 3;
				int colBox = (row % 3) * 3 + col % 3;
				if (board[rowBox][colBox] != '.') {
					number = board[rowBox][colBox] - '1';
					if (boxCheck[number]) {
						return false;
					}
					boxCheck[number] = true;
				}
			}
		}
		return true;
	}
	
	
	/**
	 * https://leetcode.com/problems/unique-paths/
	 * Simple recurssion
	 * 
	 * @param m
	 * @param n
	 * @return
	 */
	public int uniquePaths(int m, int n) {
        return uniquePathsHelper(m - 1, n - 1);
    }

    private int uniquePathsHelper(int row, int col) {
        if(row < 0 || col < 0) {
            return 0;
        } else if(row == 0 && col == 0) {
            return 1;
        }

        int count = 0;
        count += uniquePathsHelper(row, col - 1);
        count += uniquePathsHelper(row - 1, col);
        return count;
    }
    
    /**
	 * https://leetcode.com/problems/unique-paths/
	 * DP Memoization
	 * 
	 * @param m
	 * @param n
	 * @return
	 */
	public int uniquePathsDPMemoization(int m, int n) {
		int[][] dp = new int[m + 1][n + 1];
		for(int i = 0; i <= m; i++) {
			for(int j = 0; j <= n; j++) {
				dp[i][j] = -1;
			}
		}
		dp[0][0] = 1;
        return uniquePathsDPMemoization(dp, m - 1, n - 1);
    }

    private int uniquePathsDPMemoization(int[][] dp, int row, int col) {
        if(row < 0 || col < 0) {
            return 0;
        } 
        if(row == 0 && col == 0) {
            return 1;
        }
        if(dp[row][col] != -1) {
        	return dp[row][col];
        }

        int count = 0;
        count += uniquePathsDPMemoization(dp,row, col - 1);
        count += uniquePathsDPMemoization(dp, row - 1, col);
        return dp[row][col] = count;
    }
    
    
    /**
     * https://leetcode.com/problems/unique-paths-ii/
     * 
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m + 1][n + 1];
		for(int i = 0; i <= m; i++) {
			for(int j = 0; j <= n; j++) {
				dp[i][j] = -1;
			}
		}
        return uniquePathsWithObstacles(dp, obstacleGrid, m - 1, n - 1);
    }

    private int uniquePathsWithObstacles(int[][] dp, int[][] obstacleGrid, int row, int col) {
        if(row < 0 || col < 0) {
            return 0;
        }
        
        if(obstacleGrid[row][col] == 1) {
            return 0; // Obstacle present
        }

        if(row == 0 && col == 0) {
            return 1;
        }
        
        if(dp[row][col] != -1) {
        	return dp[row][col];
        }

        int count = 0;
        count += uniquePathsWithObstacles(dp, obstacleGrid, row, col - 1);
        count += uniquePathsWithObstacles(dp, obstacleGrid, row - 1, col);
        return dp[row][col] = count;
    }
    
}
