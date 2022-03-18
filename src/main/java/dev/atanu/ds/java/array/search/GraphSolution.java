package dev.atanu.ds.java.array.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class GraphSolution {

	public static void main(String[] args) {
		GraphSolution solution = new GraphSolution();
		char[][] grid = new char[][] { { '1', '1', '1' }, { '0', '1', '0' }, { '1', '1', '1' } };
		int count = solution.numIslands(grid);
		System.out.println(count);
	}

	/**
	 * https://leetcode.com/problems/word-ladder/
	 * 
	 * @param beginWord
	 * @param endWord
	 * @param wordList
	 * @return
	 */
	public int ladderLength(String beginWord, String endWord, List<String> wordList) {
		Set<String> visited = new HashSet<>(wordList);
		if (!visited.contains(endWord)) {
			return 0;
		}

		Queue<String> queue = new LinkedList<>();
		queue.offer(beginWord);
		int depth = 0;

		while (!queue.isEmpty()) {
			int size = queue.size();
			depth += 1;
			for (int s = 0; s < size; s++) {
				String word = queue.poll();
				if (word.equals(endWord)) {
					return depth;
				}
				for (int i = 0; i < word.length(); i++) {
					char[] temp = word.toCharArray();
					for (int j = 97; j <= 122; j++) {
						if (temp[i] == (char) j) {
							continue;
						}
						temp[i] = (char) j;
						String tempStr = new String(temp);
						if (visited.contains(tempStr)) {
							queue.offer(tempStr);
							visited.remove(tempStr);
						}
					}
				}
			}
		}
		return 0;
	}

	/**
	 * https://leetcode.com/problems/word-ladder-ii/
	 * 
	 * @param beginWord
	 * @param endWord
	 * @param wordList
	 * @return
	 */
	public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
		List<List<String>> paths = new ArrayList<>();
		Set<String> dictionary = new HashSet<>(wordList);
		Map<String, Integer> visited = new HashMap<>();
		Map<String, List<String>> adjacents = new HashMap<>();
		if (!dictionary.contains(endWord)) {
			return paths;
		}

		int depth = 0;
		Queue<String> queue = new LinkedList<>();
		queue.offer(beginWord);

		while (!queue.isEmpty()) {
			int size = queue.size();
			depth += 1;
			for (int s = 0; s < size; s++) {
				String word = queue.poll();
				for (int i = 0; i < word.length(); i++) {
					char[] temp = word.toCharArray();
					if (temp[i] == (char) i) {
						continue;
					}
					for (int j = 97; j <= 122; j++) {
						temp[i] = (char) j;
						String tempStr = new String(temp);
						if (dictionary.contains(tempStr)) {
							if (!visited.containsKey(tempStr)) {
								queue.offer(tempStr);
								visited.put(tempStr, depth + 1);
								List<String> adjs = adjacents.get(word);
								if (adjs == null) {
									adjs = new ArrayList<>();
								}
								adjs.add(tempStr);
								adjacents.put(word, adjs);
							} else if (visited.get(tempStr) == depth + 1) {
								List<String> adjs = adjacents.get(word);
								if (adjs == null) {
									adjs = new ArrayList<>();
								}
								adjs.add(tempStr);
								adjacents.put(word, adjs);
							}
						}
					}
				}

			}
		}
		List<String> path = new ArrayList<>();
		path.add(beginWord);
		findLadders(beginWord, endWord, adjacents, path, paths);
		return paths;
	}

	private void findLadders(String beginWord, String endWord, Map<String, List<String>> adjacents, List<String> path,
			List<List<String>> paths) {
		if (beginWord.equals(endWord)) {
			paths.add(new ArrayList<>(path));
		} else {
			List<String> adjs = adjacents.get(beginWord);
			if (adjs != null) {
				for (String word : adjs) {
					if (!beginWord.equals(word)) {
						path.add(word);
						findLadders(word, endWord, adjacents, path, paths);
						path.remove(path.size() - 1);
					}
				}
			}
		}
	}

	/**
	 * https://leetcode.com/problems/number-of-islands/
	 * 
	 * @param grid
	 * @return
	 */
	public int numIslands(char[][] grid) {
		int count = 0;
		for (int x = 0; x < grid.length; x++) {
			for (int y = 0; y < grid[0].length; y++) {
				if (grid[x][y] == '1') {
					count += 1;
				}
				markGrid(grid, x, y);
			}
		}
		return count;
	}

	private void markGrid(char[][] grid, int x, int y) {
		
		if (x >= grid.length || x < 0 || y >= grid[0].length || y < 0 || grid[x][y] != '1') {
			return;
		}
		grid[x][y] = '2';
		markGrid(grid, x - 1, y);
		markGrid(grid, x, y - 1);
		markGrid(grid, x + 1, y);
		markGrid(grid, x, y + 1);
	}

}
