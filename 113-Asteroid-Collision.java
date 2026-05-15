// https://leetcode.com/problems/asteroid-collision/


// given: an array of integers representing asteroids
// required: return the state of the asteroids after collisions

// constraints
// length of the array in [2, 10k]
// each value in [-1000, 1000]
// asteroid != 0

// tc: O(n), sc: O(n)
// case 1: + + No collision
// case 2: + - Collison
// case 3: - + No Collision
// case 4: - - No Collision
class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> stack = new ArrayDeque<>();

        for(int a: asteroids) {
            if(a < 0) {
                boolean flag = true;

                while(!stack.isEmpty() && stack.peek() > 0) {
                    if(Math.abs(a) == Math.abs(stack.peek())) {
                        stack.pop();
                        flag = false;
                        break;
                    }
                    else if(Math.abs(a) < Math.abs(stack.peek())) {
                        flag = false;
                        break;
                    }
                    else {
                        stack.pop();
                    }
                }

                if(flag) {
                    stack.push(a);
                }
            }
            else {
                stack.push(a);
            }
        }

        int[] result = new int[stack.size()];
        int i = stack.size() - 1;
        while(!stack.isEmpty()) {
            result[i] = stack.pop();
            i--;
        }

        return result;
    }
}




// tc: O(n), sc: O(n)
// case 1: + + No collision
// case 2: + - Collison
// case 3: - + No Collision
// case 4: - - No Collision
class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> stack = new ArrayDeque<>();

        for(int a: asteroids) {
            if(a < 0) {                
                while(!stack.isEmpty() && stack.peek() > 0) {
                    if(Math.abs(a) == Math.abs(stack.peek())) {
                        stack.pop();
                        a = 0;
                        break;
                    }
                    else if(Math.abs(a) < Math.abs(stack.peek())) {
                        a = 0;
                        break;
                    }
                    else {
                        stack.pop();
                    }
                }

                if(a != 0) {
                    stack.push(a);
                }
            }
            else {
                stack.push(a);
            }
        }

        int[] result = new int[stack.size()];
        int i = stack.size() - 1;
        while(!stack.isEmpty()) {
            result[i] = stack.pop();
            i--;
        }

        return result;
    }
}