package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WordController {

	@Autowired
	private Result result;

	@RequestMapping(value = "/words/{word}", method = RequestMethod.GET)
	public Result checkPalindrome(@PathVariable("word") String word) {
		boolean anagramflag = true;
		String reverse = "";
		boolean flag;
		int length = word.length();

		for (int i = length - 1; i >= 0; i--)
			reverse = reverse + word.charAt(i);
		flag = true;
		if (word.equals(reverse)) {
			flag = true;
		} else {
			anagramflag = checkanagramPalindrome(word);
			flag = false;
		}

		result.setWord(word);
		result.setPalindrome(flag);
		result.setAnagramOfPalindrome(anagramflag);

		return result;
	}

	private boolean checkanagramPalindrome(String word) {
		int[] count = new int[26];
		for (int i = 0; i < word.length(); i++) {
			char ch = word.charAt(i);
			count[ch - 'a']++;
		}
		int oddcount = 0;
		for (int cnt : count) {
			if (oddcount > 1)
				return false;
			if (cnt % 2 == 1)
				oddcount++;
		}

		return true;
	}

}
