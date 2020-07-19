//给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
//
//
//
// 示例 1:
//
// 输入: nums = [1,1,1,2,2,3], k = 2
//输出: [1,2]
//
//
// 示例 2:
//
// 输入: nums = [1], k = 1
//输出: [1]
//
//
//
// 提示：
//
//
// 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
// 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
// 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的。
// 你可以按任意顺序返回答案。
//
// Related Topics 堆 哈希表
// 👍 402 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
  public int[] topKFrequent(int[] nums, int k) {
    HashMap<Integer, Integer> map = new HashMap<>();
    for (int num : nums)
    {
      map.put(num, map.getOrDefault(num, 0) + 1);
    }
    PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>()
    {
      public int compare(Integer a, Integer b)
      {
        return map.get(a) - map.get(b);
      }
    });
    for (int key : map.keySet())
    {
      if (queue.size() < k)
        queue.add(key);
      else if (map.get(key) > map.get(queue.peek()))
      {
        queue.remove();
        queue.add(key);
      }
    }
    List<Integer> temp = new ArrayList<>();
    while (!queue.isEmpty())
    {
      temp.add(queue.remove());
    }
    int size = temp.size();
    int[] result = new int[size];
    for(int i = 0; i < size; i++){
      result[i] = temp.get(i);
    }
    return result;
  }
}
//leetcode submit region end(Prohibit modification and deletion)
