package rip.diamond.sanddropper.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class ShapeUtil {

    @Getter
    @AllArgsConstructor
    public static class Location {
        int x;
        int z;
        int order;
    }

    //https://www.geeksforgeeks.org/program-to-print-spiral-pattern/
    public static List<Location> createSpiral(int size) {
        // Create row and col
        // to traverse rows and columns
        int row = 0, col = 0;

        int boundary = size - 1;
        int sizeLeft = size - 1;
        int flag = 1;

        // Variable to determine the movement
        // r = right, l = left, d = down, u = upper
        char move = 'r';

        // Array for matrix
        List<Location> locations = new ArrayList<>();

        for (int i = 1; i < size * size + 1; i++) {

            // Assign the value
            locations.add(new Location(row, col, i));

            // switch-case to determine the next index
            switch (move) {
                // If right, go right
                case 'r' -> col += 1;
                // if left, go left
                case 'l' -> col -= 1;
                // if up, go up
                case 'u' -> row -= 1;
                // if down, go down
                case 'd' -> row += 1;
            }

            // Check if the matrix
            // has reached array boundary
            if (i == boundary) {

                // Add the left size for the next boundary
                boundary += sizeLeft;

                // If 2 rotations has been made,
                // decrease the size left by 1
                if (flag != 2) {
                    flag = 2;
                } else {
                    flag = 1;
                    sizeLeft -= 1;
                }

                // switch-case to rotate the movement
                switch (move) {
                    // if right, rotate to down
                    case 'r' -> move = 'd';
                    // if down, rotate to left
                    case 'd' -> move = 'l';
                    // if left, rotate to up
                    case 'l' -> move = 'u';
                    // if up, rotate to right
                    case 'u' -> move = 'r';
                }
            }
        }

        return locations;
    }

}
