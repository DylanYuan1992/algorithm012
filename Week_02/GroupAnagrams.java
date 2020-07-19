//给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
//
// 示例:
//
// 输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
//输出:
//[
//  ["ate","eat","tea"],
//  ["nat","tan"],
//  ["bat"]
//]
//
// 说明：
//
//
// 所有输入均为小写字母。
// 不考虑答案输出的顺序。
//
// Related Topics 哈希表 字符串
// 👍 397 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
  public List<List<String>> groupAnagrams(String[] strs) {
    HashMap<String, List<String>> hash = new HashMap<>();
    for (int i = 0; i < strs.length; i++) {
      char[] s_arr = strs[i].toCharArray();
      //排序
      Arrays.sort(s_arr);
      //映射到 key
      String key = String.valueOf(s_arr);
      //添加到对应的类中
      if (hash.containsKey(key)) {
        hash.get(key).add(strs[i]);
      } else {
        List<String> temp = new ArrayList<String>();
        temp.add(strs[i]);
        hash.put(key, temp);
      }

    }
    return new ArrayList<List<String>>(hash.values());
  }
}
//leetcode submit region end(Prohibit modification and deletion)
