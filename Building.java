package assignment3;

public class Building {

	OneBuilding data;
	Building older;
	Building same;
	Building younger;
	
	public Building(OneBuilding data){
		this.data = data;
		this.older = null;
		this.same = null;
		this.younger = null;
	}
	
	public String toString(){
		String result = this.data.toString() + "\n";
		if (this.older != null){
			result += "older than " + this.data.toString() + " :\n";
			result += this.older.toString();
		}
		if (this.same != null){
			result += "same age as " + this.data.toString() + " :\n";
			result += this.same.toString();
		}
		if (this.younger != null){
			result += "younger than " + this.data.toString() + " :\n";
			result += this.younger.toString();
		}
		return result;
	}
	
	public Building addBuilding (OneBuilding b){
		if (this.data==null) {
			return new Building(b);
		}
		
	    if(b.yearOfConstruction>this.data.yearOfConstruction) {
				if(this.younger==null) {
					this.younger=new Building(b);
				}
				else {
					this.younger.addBuilding(b);
				}
			}
	    
	    if(b.yearOfConstruction<this.data.yearOfConstruction) {
	    	if(this.older==null) {
				this.older=new Building(b);
			}
			else {
				this.older.addBuilding(b);
			}
		}
		
		
	    if(b.yearOfConstruction==this.data.yearOfConstruction) {
	    	
				   if(b.height<=this.data.height) {
					   if(this.same==null) {
					   this.same=new Building(b);
					   }
					   else {
						   this.same.addBuilding(b);
					   }
				   }
				   if(b.height>this.data.height) {
					   if(this.same==null) {
					   OneBuilding Temp= this.data;
					   this.data=b;
					   this.same=new Building(Temp);
				   }
					   else {
						   OneBuilding Temp= this.data;
						   this.data=b;
						   this.same.addBuilding(Temp);
					   }
			
				   }
		
	    	
				   
	    	
		}
		
		return this; // DON'T FORGET TO MODIFY THE RETURN IF NEEDS BE
	}
	
	public Building addBuildings (Building b){
		if(b == null) {
			return this;
		}
		if(b.data!=null) {
			this.addBuilding(b.data);
		}
		if(b.younger!=null) {
			this.addBuildings(b.younger);
		}
		if(b.same!=null) {
			this.addBuildings(b.same);
		}
		if(b.older!=null) {
			this.addBuildings(b.older);
		}
		return this; // DON'T FORGET TO MODIFY THE RETURN IF NEEDS BE
	}
	
	public Building removeBuilding (OneBuilding b){
		// ADD YOUR CODE HERE
		if(b.equals(this.data)) {
			
			if(this.same != null) {
				Building temp = this.same;
				this.data = temp.data;
				this.same = temp.same;  
				
			}
			else if(this.older != null) {
				
				Building temp = this.older;
				Building temp2 = this.younger;
				this.data=temp.data;
				this.older=null;
				this.younger=null;
				this.addBuildings(temp.older);
				this.addBuildings(temp.same);
				this.addBuildings(temp.younger);
				this.addBuildings(temp2);
		
			}
			
			else if (this.younger != null){
				
				Building temp = this.younger;
				this.data = temp.data;
				this.younger=null;
				this.addBuildings(temp.older);
				this.addBuildings(temp.same);
				this.addBuildings(temp.younger);
		   }
			else {
				return null;
			}		
		}	
		if(this.older != null) {
			if(this.older.removeBuilding(b) == null) {
				
				this.older=null;
			}
		}
		if(this.same != null) {
			if(this.same.removeBuilding(b) == null) {
				
				this.same=null;
			}
		}
		if(this.younger != null) {
			if(this.younger.removeBuilding(b) == null) {
				
				this.younger=null;
			}
		}
		return this; // DON'T FORGET TO MODIFY THE RETURN IF NEEDS BE
	}
	
	public int oldest(){
		
		if (this.data==null) {
		return 0;
		}
		
		if (this.older==null) {
			return this.data.yearOfConstruction;// DON'T FORGET TO MODIFY THE RETURN IF NEEDS BE
		}
		if (this.older!=null) {
			return this.older.oldest();
		}
		
		return 0;
	}
	
	public int highest(){
		if(this.data==null) {
			return 0;
		}
		int maximum1=this.data.height;
		int maximum2=this.data.height;
		if(this.older!=null) {
			maximum1= Math.max(this.older.highest(), maximum1);
		}
		if(this.younger!=null) {
			maximum2= Math.max(this.younger.highest(), maximum1);
		}
		int maximum=Math.max(maximum1, maximum2);
		return maximum;
		
	}

	
	public OneBuilding highestFromYear (int year){
		if(this.data==null) {
			return null;
		}
		if(this.data.yearOfConstruction==year) {
			return this.data;
		}
		if(year<this.data.yearOfConstruction) {
			if(this.older!=null) {
				return this.older.highestFromYear(year);
			}else {
				return null;
			}
		}if(year>this.data.yearOfConstruction){
			if(this.younger!=null) {
				return this.younger.highestFromYear(year);
			}else {
				return null;
			}
		}
		return null; // DON'T FORGET TO MODIFY THE RETURN IF NEEDS BE
	}
		
		
	public int numberFromYears (int yearMin, int yearMax){
        // ADD YOUR CODE HERE (ADDED)
        if(this.data==null) {
            return 0;
        }
        
        if(yearMin>yearMax) {
            return 0;
        }
        
        int total = 0;
        
        if(this.data.yearOfConstruction>=yearMin && this.data.yearOfConstruction<=yearMax) {
              
                total++;
        }
        
        
        if(this.older!=null) {
            total = total + this.older.numberFromYears(yearMin, yearMax);
        }
        
        if(this.same!=null) {
            total = total + this.same.numberFromYears(yearMin, yearMax);
        }
        
        if(this.younger!=null) {
            total = total + this.younger.numberFromYears(yearMin, yearMax);
        }
        
        
        
        return total;
    }
	
	public int[] costPlanning (int nbYears){
		int[] cost = new int[nbYears];
		if(this.data == null) {
			return cost;
		}
		
		if(this.data.yearForRepair<(2018+nbYears)) {
			cost[this.data.yearForRepair-2018] = this.data.costForRepair;
			
		}
		if(this.older != null) {
			int [] temp = this.older.costPlanning(nbYears);
			for (int i=0; i<nbYears; i++) {
				cost[i]=cost[i]+temp[i];
			}
		}
		if(this.same !=null) {
			int [] temp1 = this.same.costPlanning(nbYears);
			for (int i=0; i<nbYears; i++) {
				cost[i]=cost[i]+temp1[i];
		}
		}
		if(this.younger !=null) {
			int [] temp = this.younger.costPlanning(nbYears);
			for (int i=0; i<nbYears; i++) {
				cost[i]=cost[i]+temp[i];
			}
		}
		return cost;// DON'T FORGET TO MODIFY THE RETURN IF NEEDS BE
	}
	
}
	

