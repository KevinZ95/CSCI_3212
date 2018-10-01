import java.util.Deque;

import edu.gwu.algtest.*;
import edu.gwu.util.*;

public class FastVIP implements VIPAlgorithm{

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Jinwen Zhang, implementation of FastVIP";
	}

	@Override
	public void setPropertyExtractor(int arg0, PropertyExtractor arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean VIPexists(VIPProblemData data) {
		Deque<Integer> s = null; 
		int VIP; 
		
		for (int i = 0; i < data.numPeople(); i++) {
			s.push(i);
		}
		
		int A = s.pollFirst(); 
		int B = s.pollFirst();
		
		while (s.size() > 1) {
			if (data.knows(A, B)) {	// if A knows B, A is not a VIP
				s.push(B);
			} else {				// if A does not know B, B is not a VIP
				s.push(A);
			}
		}
		
		// check if the last person is VIP
		VIP = s.pollFirst(); 
		for (int i = 0; i < data.numPeople(); i++) {
			if (data.knows(VIP, i)) return false; 
		}
		
		
		System.out.println("number " + data.numQueriesMade());
		return true;
	}

}
