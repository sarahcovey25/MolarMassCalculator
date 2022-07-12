import java.util.ArrayList;

public class MolarMassCalculator{

	public static void main(String[] args) throws IllegalArgumentException{
		ArrayList<Double> weights = new ArrayList<Double>();
		ArrayList<Integer> numElements = new ArrayList<Integer>();

		if(args[0].charAt(0) == '(') args[0] = "."+args[0];

		int startIndex = -1;
		String temp = ""+args[0].charAt(0);
		
		for(int i=1; i<args[0].length(); i++){
			char ch = args[0].charAt(i);
			if(Character.isDigit(ch)){
				String num = "";
				while(i<args[0].length()){
					ch = args[0].charAt(i);
					if(!Character.isDigit(ch)) break;
					num += ch;
					i++;
				}
				i--;
				numElements.add(Integer.parseInt(num));
			}
			else if(Character.isUpperCase(ch)){
				try{
					weights.add(ElementsMap.get(temp));
				} catch(IllegalArgumentException e){
					throw new IllegalArgumentException("The element \""+temp+"\" of the compound "+args[0]+" does not exist.", e);
				}

				if(Character.isLetter(args[0].charAt(i-1))){
					numElements.add(1);
				}
				temp = ""+ch;
			}
			else if(ch=='('){
				if(startIndex!=-1){
					throw new IllegalArgumentException("There are two left parentheses before a right one.");
				}
				if(!Character.isDigit(args[0].charAt(i-1))) numElements.add(1);

				startIndex = numElements.size();
			}
			else if(ch==')'){
				if(startIndex==-1){
					throw new IllegalArgumentException("There is a right parenthesis before a left one.");
				}
				if(!Character.isDigit(args[0].charAt(i-1))){
					numElements.add(1);
				}
				String num = "";
				i++;
				while(i<args[0].length()){
					ch = args[0].charAt(i);
					if(!Character.isDigit(ch)) break;
					num += ch;
					i++;
				}
				i--;
				for(int j=startIndex; j<numElements.size(); j++){
					numElements.set(j, numElements.get(j)*Integer.parseInt(num));
				}
				startIndex = -1;
			}
			else {
				temp += ch;
			}
		}
		try{
			weights.add(ElementsMap.get(temp));
		} catch(IllegalArgumentException e){
			throw new IllegalArgumentException("The element \""+temp+"\" of the compound "+args[0]+" does not exist.", e);
		}

		if(startIndex!=-1){
			throw new IllegalArgumentException("There are more left parentheses than right ones.");
		}
		if(!Character.isDigit(args[0].charAt(args[0].length()-1))) numElements.add(1);

		System.out.println("\n"+calculate(weights, numElements)+" g/mol\n");
	}

	/**
	* This method calculates the molar mass of an element sorted into two parrallel arraylists, the weights and number of elements with that weight
	* 
	* @param: ArrayList<Double> weights - the list of weights to add to
	* @param: ArrayList<Integer> weights - the list of weights to add to
	* 
	* @return: the molar mass of the molecule
	*/
	public static double calculate(ArrayList<Double> elementWeights, ArrayList<Integer> numElements){
		double r = 0.0;
		for(int i=0; i<elementWeights.size(); i++){
			r += elementWeights.get(i) * numElements.get(i);
		}
		return r;
	}

}