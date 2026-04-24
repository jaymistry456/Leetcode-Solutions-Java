// tc: O(n^2), sc: O(n)
class Main {
    public static int partition(int[] nums, int start, int end) {
        int pivot = start;
        int i = start + 1;
        int j = end;
        
        while(i <= j) {
            while(i <= j && nums[i] < nums[pivot]) {
                i++;
            }
            while(i <= j && nums[j] > nums[pivot]) {
                j--;
            }
            
            if(i > j) {
                break;
            }
            
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            i++;
            j--;
        }
        
        int temp = nums[pivot];
        nums[pivot] = nums[j];
        nums[j] = temp;
        
        return j;
    }
    
    public static void quickSort(int[] nums, int start, int end) {
        if(start >= end) {
            return;
        }
        
        int partitionIdx = partition(nums, start, end);
        quickSort(nums, start, partitionIdx - 1);
        quickSort(nums, partitionIdx + 1, end);
    }
    
    public static void main(String[] args) {
        int[] nums = new int[]{7, 6, 5, 4, 3, 2, 1};
        
        quickSort(nums, 0, nums.length - 1);
        
        for(int num: nums) {
            System.out.print(num + " ");
        }
    }
}