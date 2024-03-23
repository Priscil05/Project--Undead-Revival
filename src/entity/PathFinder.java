package entity;

import java.awt.*;
import java.util.*;
import java.util.List;

public class PathFinder {

    private final boolean[][] grid;
    private final int rows = 85;
    private final int cols = 135;
    private Point playerPosition = new Point();
    private Point enemyPosition = new Point();


    public PathFinder(boolean[][] grid, Point playerPosition, Point enemyPosition)
    {
        this.grid = grid;
        this.playerPosition.y = playerPosition.x/48;
        this.playerPosition.x = playerPosition.y/48;
        this.enemyPosition.y = enemyPosition.x/48;
        this.enemyPosition.x = enemyPosition.y/48;
    }

    public void set_parameters(/*Point playerPosition*/int player_x, int player_y, Point enemyPosition)
    {
        this.playerPosition.y = player_x/48;
        this.playerPosition.x = player_y/48;
        this.enemyPosition.y = enemyPosition.x/48;
        this.enemyPosition.x = enemyPosition.y/48;
    }

    public List<Integer> findPath() {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(node -> node.heuristic));
        Node start = new Node(enemyPosition.x, enemyPosition.y, calculateHeuristic(enemyPosition.x, enemyPosition.y));
        priorityQueue.offer(start);

        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};  // sus, jos, stanga, dreapta
        boolean[][] visited = new boolean[rows][cols];
        visited[enemyPosition.x][enemyPosition.y] = true;

        while (!priorityQueue.isEmpty()) {
            Node current = priorityQueue.poll();
            int row = current.row;
            int col = current.col;

            if (row == playerPosition.x && col == playerPosition.y) {
                return reconstructPath(current);
            }

            for (int[] direction : directions) {
                int newRow = row + direction[0];
                int newCol = col + direction[1];

                if (isValid(newRow, newCol) && grid[newRow][newCol] && !visited[newRow][newCol]) {
                    int heuristic = calculateHeuristic(newRow, newCol);
                    Node newNode = new Node(newRow, newCol, heuristic);
                    priorityQueue.offer(newNode);
                    visited[newRow][newCol] = true;
                    newNode.parent = current;
                }
            }
        }

        return new ArrayList<>();  // Nu a fost găsit niciun drum
    }

    private boolean isValid(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    private int calculateHeuristic(int row, int col) {
        // Folosim distanta Manhattan ca euristica (suma valorilor absolute ale diferentelor coordonatelor)
        return Math.abs(row - playerPosition.x) + Math.abs(col - playerPosition.y);
    }

    private List<Integer> reconstructPath(Node node) {
        List<Integer> path = new ArrayList<>();
        Node current = node;

        while (current.parent != null) {
            int parentRow = current.parent.row;
            int parentCol = current.parent.col;
            int currentRow = current.row;
            int currentCol = current.col;

            if (parentRow < currentRow) {
                path.add(1);  // jos
            } else if (parentRow > currentRow) {
                path.add(0);  // sus
            } else if (parentCol < currentCol) {
                path.add(3);  // dreapta
            } else if (parentCol > currentCol) {
                path.add(2);  // stanga
            }

            current = current.parent;
        }

        Collections.reverse(path);
        return path;
    }

    private static class Node {
        int row;
        int col;
        int heuristic;
        Node parent;

        Node(int row, int col, int heuristic) {
            this.row = row;
            this.col = col;
            this.heuristic = heuristic;
        }
    }
}

