import edu.gwu.algtest.*;
import edu.gwu.util.*;

public class NaiveVIP implements VIPAlgorithm{

	@Override
	public String getName() {
		return "Jinwen Zhang, implementation of NaiveVIP";
	}

	@Override
	public void setPropertyExtractor(int arg0, PropertyExtractor arg1) {
		
	}

	@Override
	public boolean VIPexists(VIPProblemData data) {
		
		for (int i = 0; i < data.numPeople(); i++) {
			for (int j = 0; j < data.numPeople(); j++) {
				if (data.knows(i, j)) continue;
				if (j == data.numPeople() - 1) return true; 
			}
			
		}
		System.out.println("number " + data.numQueriesMade());
		return false;
	}

}
